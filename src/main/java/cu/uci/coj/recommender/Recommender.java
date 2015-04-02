/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.recommender;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.RecommenderDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.UserProfile;

public class Recommender {

    private UserDAO userDAO;
    private ProblemDAO problemDAO;
    private RecommenderDAO recommenderDAO;
    private boolean knnCalculated;
    private List<UserProfile> relatedProfiles;

    public Recommender(UserDAO userDAO, ProblemDAO problemDAO, RecommenderDAO recommenderDAO) {
        this.recommenderDAO = recommenderDAO;
        this.userDAO = userDAO;
        this.problemDAO = problemDAO;
        this.relatedProfiles = new ArrayList<UserProfile>();
        this.knnCalculated = false;
    }

    public List<Problem> recommendations(String username, int uid, String sort, String direction) {
        if (userDAO.hasProfile(username)) {
            UserProfile profile = userDAO.loadUserProfile(username);
            List<Problem> suggestedProblems = new LinkedList<Problem>();
            boolean calculate = true;
            if (recommenderDAO.hasRecommendationProfile(profile.getUid())) {
                suggestedProblems = recommenderDAO.loadRecommendationProfile(username, profile.getUid(), sort, direction);
                if (suggestedProblems.isEmpty() == false){
                    suggestedProblems = problemDAO.fillInformation(username, profile.getUid(), suggestedProblems);
                    calculate = false;
                    for (Problem problem : suggestedProblems) {
                        if (problem.isSolved())
                            calculate = true;
                    }
                }
            }
            if (calculate == true) {
                List<UserProfile> knProfiles = KNN(profile);
                if (knProfiles.size() > 0) {
                    this.relatedProfiles = knProfiles;
                    suggestedProblems = recommenderDAO.suggestedProblems(knProfiles, profile, sort, direction);
                  
                    if (suggestedProblems.isEmpty() == false) {
                        recommenderDAO.registerRecommendationProfile(profile.getUid(), suggestedProblems);
                    }
                }
            }
            if (suggestedProblems.isEmpty() == false) {
                suggestedProblems = problemDAO.fillInformation(username, profile.getUid(), suggestedProblems);
                this.knnCalculated = true;
                return suggestedProblems;
            }
        }
        return coldStart(username, uid, sort, direction);
    }

    private static double pearson(List<Double> l1, List<Double> l2) {

        double suma1 = 0, suma2 = 0, suma1q = 0, suma2q = 0, psum = 0;

        for (int i = Math.max(l1.size(), l2.size()); i >= 0; i--) {
            double a = 0, b = 0;
            if (i < l1.size()) {
                a = l1.get(i);
            }
            if (i < l2.size()) {
                b = l2.get(i);
            }

            suma1 += a;
            suma2 += b;
            suma1q += a*a;
            suma2q += b*b;
            psum += a*b;
        }
        double num = psum;
        double den = Math.sqrt(suma1q * suma2q);
        if (suma1q == 0 || suma2q == 0)
            return 0;
        return num / den;
    }

    // K-nearest neighbours
    private List<UserProfile> KNN(UserProfile profile) {
        List<UserProfile> profiles = userDAO.loadRelatedProfiles(profile);
        int K_INDEX_KNN = 10;
        PriorityQueue<UserProfile> cola = new PriorityQueue<UserProfile>(K_INDEX_KNN, new Comparator<UserProfile>() {
            @Override
            public int compare(UserProfile o1, UserProfile o2) {
                // para ordenar de menor a mayor, con el menor en la punta de la
                // cola
               
                return o2.getPearson().compareTo(o1.getPearson());
            }
        });
        for (UserProfile prof : profiles) {
            double cor = pearson(profile.getTags(), prof.getTags());
            prof.setPearson(cor);
            cola.add(prof);
            if (cola.size() > K_INDEX_KNN) {
                cola.remove();
            }
        }

        profiles.clear();
        profiles.addAll(cola);
        return profiles;
    }

    private List<Problem> coldStart(String username, int uid, String sort, String direction) {
        List<Problem> problems = recommenderDAO.suggestedProblemsColdStart(username, uid, sort, direction);
        problems = problemDAO.fillInformation(username, uid, problems);
        return problems;
    }

    public List<Problem> findSimilarProblems(Problem problem) {
        return recommenderDAO.findSimilarProblems(problem.getPid());
    }

    public List<Problem> findSimilarProblems(String username, Problem problem) {
        List<Problem> problems = new LinkedList<Problem>();

        String tags = userDAO.loadTags(username);
        String filtroTags = "", withOutTags = "";
        for (int i = tags.length() - 1; i >= 0; i--) {
            if (tags.charAt(i) == '1') {
                filtroTags += "-" + i + "-";
            } else {
                withOutTags += "-" + i + "-";
            }
        }
        
        
        int TOTAL = 6;
        int PREFERED = 4;

        List<Problem> B, A = recommenderDAO.findSimilarProblems(username, problem.getPid());
        if (recommenderDAO.isPrefferedForUser(username, problem, filtroTags)) {
            B = recommenderDAO.findSimilarProblemsFiltred(username, problem.getPid(), withOutTags);
        } else {
            B = recommenderDAO.findSimilarProblemsFiltred(username, problem.getPid(), filtroTags);
        }

        for (int i = B.size() - 1; i >= 0; i--) {
            for (int j = A.size() - 1; j >= 0; j--) {
                if (B.get(i).getPid() == A.get(j).getPid()) {
                    B.remove(i);
                    break;
                }
            }
        }

        int la = Math.min(A.size(), PREFERED);
        int lb = Math.min(B.size(), TOTAL - PREFERED);

        for (int i = 0; i < la; i++) {
            problems.add(A.get(i));
        }
        for (int i = 0; i < lb; i++) {
            problems.add(B.get(i));
        }
        for (int i = la; i < A.size() && problems.size() < TOTAL; i++) {
            problems.add(A.get(i));
        }
        for (int i = lb; i < B.size() && problems.size() < TOTAL; i++) {
            problems.add(B.get(i));
        }
        return problems;
    }

    /**
     * @return the knnCalculated
     */
    public boolean isKnnCalculated() {
        return knnCalculated;
    }

    public List<UserProfile> getRelatedProfiles() {
        return relatedProfiles;
    }
}
