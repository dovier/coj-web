/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.dao.impl;

import cu.uci.coj.dao.RecommenderDAO;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.ProblemClassification;
import cu.uci.coj.model.UserProfile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jose Carlos
 */
@Repository("RecommenderDAO")
@Transactional
public class RecommenderDAOImpl extends BaseDAOImpl implements RecommenderDAO {

    public RecommenderDAOImpl() {
    }

    @Override
    public void updateRecommendationProfile(int uid, List<Problem> recommendation) {
        String lista = "-";
        for (Iterator<Problem> it = recommendation.iterator(); it.hasNext();) {
            Problem problem = it.next();
            lista += problem.getPid() + "-";
        }
        dml("recommender.profile.update", lista, uid);
    }

    @Override
    public void insertRecommendationProfile(int uid, List<Problem> recommendation) {
        String lista = "-";
        for (Iterator<Problem> it = recommendation.iterator(); it.hasNext();) {
            Problem problem = it.next();
            lista += problem.getPid() + "-";
        }
        dml("recommender.profile.insert", uid, lista);
    }

    @Override
    public void registerRecommendationProfile(int uid, List<Problem> recommendation) {
        if (hasRecommendationProfile(uid)) {
            updateRecommendationProfile(uid, recommendation);
        } else {
            insertRecommendationProfile(uid, recommendation);
        }
    }

    @Transactional(readOnly = true)
    public boolean hasRecommendationProfile(int uid) {
        return bool("recommender.profile.hasprofile", uid);
    }

    @Transactional(readOnly = true)
    public List<Problem> loadRecommendationProfile(String username, int uid, String sort, String direction) {
        String sql = getSql("recommender.profile.load");
        if (sort != null) {
            sql = sql.replace("<orderby>", "ORDER BY " + sort + (direction.equals("asc") ? " ASC" : " DESC"));
        } else {
            sql = sql.replace("<orderby>", "");
        }
        String recommendation = string("recommender.profile.load.recommendation", uid);
        List<Problem> problems = objects(sql, Problem.class, recommendation);

        return problems;
    }

    @Override
    public List<Problem> suggestedProblems(List<UserProfile> profiles, UserProfile profile, String orderby, String inv) {
        StringBuffer buffer = new StringBuffer();
        for (UserProfile userProfile : profiles) {
            buffer.append(userProfile.getUsername()).append(",");
        }
        // eliminar la ultima coma
        if (profiles.isEmpty()) {
            return new ArrayList<Problem>();
        }
        buffer.deleteCharAt(buffer.length() - 1);

        int PROBLEM_SUGGESTION_QTY = 10;  //this is configuration

        List<Object> list = new LinkedList<Object>();
        list.add(profile.getUsername());
        list.add(buffer.toString());
        //list.add(PROBLEM_SUGGESTION_QTY);
        String sql = getSql("load.users.recommender.suggestedproblems.filtered.no.pattern");


        if (orderby != null) {
            sql = sql.replace("<orderby>", getSql(inv.equals("asc") ? "order.all.problems.asc" : "order.all.problems.desc"));
            sql = sql.replace("<orderby>", orderby);
        } else {
            sql = sql.replace("<orderby>", "");
        }

        sql = sql.replace("<and>", "");
        //list.add(PROBLEM_SUGGESTION_QTY);
        list.add(0);
        ///
        String filtroTags = loadPreferenceProfile(profile.getUsername(), profile.getUid());
        

        
        list.add(filtroTags);
        String sqlWhitClassification = getSql("recommender.filte.with.classification");
        sqlWhitClassification = sqlWhitClassification.replace("<SELECTION>", sql);
        List<Problem> sameTags = objects(sqlWhitClassification, Problem.class, list.toArray());

        String sqlWhitoutClassification = getSql("recommender.filte.without.classification");
        sqlWhitoutClassification = sqlWhitoutClassification.replace("<SELECTION>", sql);
        List<Problem> noSameTags = objects(sqlWhitoutClassification, Problem.class, list.toArray());

        int PROBLEM_SUGGESTION_QTY_SAMECLASSIFICATION = 10;
        int PROBLEM_SUGGESTION_QTY_NOSAMECLASSIFICATION = 2;

        
        // Presicion y Recall
        /*
        double Tp = 0, Tn = 0, Fp = 0, Fn = 0;
        for (int i = 0; i < sameTags.size(); i++) {
            if (i < PROBLEM_SUGGESTION_QTY_SAMECLASSIFICATION) {
                Tp++;
            } else {
                Fp++;
            }
        }
        for (int i = 0; i < noSameTags.size(); i++) {
            if (i < PROBLEM_SUGGESTION_QTY_NOSAMECLASSIFICATION) {
                Fn++;
            } else {
                Tn++;
            }
        }
        double presicion = Tp / (Tp + Fp);
        double recall    = Tp / (Tp + Fn);
        
        
        double presicion1 = (double)sameTags.size() / 258.0;
        double presicion2 = (double)sameTags.size() / (double)(sameTags.size() + noSameTags.size());
        */



        List<Problem> problems = new LinkedList<Problem>();
        for (int i = 0; i < Math.min(sameTags.size(), PROBLEM_SUGGESTION_QTY_SAMECLASSIFICATION); i++) {
            problems.add(sameTags.get(i));
        }
        for (int i = 0; i < Math.min(noSameTags.size(), PROBLEM_SUGGESTION_QTY_NOSAMECLASSIFICATION); i++) {
            problems.add(noSameTags.get(i));
        }
        for (int i = PROBLEM_SUGGESTION_QTY_SAMECLASSIFICATION; i < sameTags.size() && problems.size() < PROBLEM_SUGGESTION_QTY_SAMECLASSIFICATION + PROBLEM_SUGGESTION_QTY_NOSAMECLASSIFICATION; i++) {
            problems.add(sameTags.get(i));
        }
        for (int i = PROBLEM_SUGGESTION_QTY_NOSAMECLASSIFICATION; i < noSameTags.size() && problems.size() < PROBLEM_SUGGESTION_QTY_SAMECLASSIFICATION + PROBLEM_SUGGESTION_QTY_NOSAMECLASSIFICATION; i++) {
            problems.add(noSameTags.get(i));
        }

        return problems;
    }

    @Transactional(readOnly = true)
    public List<Problem> suggestedProblemsColdStart(String username, int uid, String orderby, String inv) {

        int PROBLEM_SUGGESTION_QTY = 5 * 2;  //this is configuration
        String notlogged = "";
        if (username == null) {
            List<Object> list = new LinkedList<Object>();
            list.add(0);//complexity
            list.add(2);//limit
            notlogged += "AND (";
            boolean first = false;
            for (int c = 1; c <= 5; c++) {
                list.set(0, c);
                List<Problem> now = objects("recommender.notlogged.complexity", Problem.class, list.toArray());
                for (Iterator<Problem> it = now.iterator(); it.hasNext();) {
                    Problem problem = it.next();
                    if (first == true) {
                        notlogged += " OR ";
                    } else {
                        first = true;
                    }
                    notlogged += "pid =" + problem.getPid();
                }
            }
            notlogged += ") ";
        }

        ////////
        String sql = getSql("recommender.notlogged");

        List<Object> list = new LinkedList<Object>();
        list.add(username);
        list.add(PROBLEM_SUGGESTION_QTY);

        if (orderby != null) {
            sql = sql.replace("<orderby>", getSql(inv.equals("asc") ? "order.all.problems.asc" : "order.all.problems.desc"));
            sql = sql.replace("<orderby>", orderby);
        } else {
            sql = sql.replace("<orderby>", "");
        }

        //notlogged = "";
        sql = sql.replace("<AND>", notlogged);
        list.add(PROBLEM_SUGGESTION_QTY);
        list.add(0);
        ///
        List<Problem> problems = objects(sql, Problem.class, list.toArray());
        return problems;
    }

    @Transactional(readOnly = true)
    public List<Problem> findSimilarProblems(int pid) {
        int LIMIT = 6;
        List<Problem> problems = objects("recommender.notloged.similar.problems", Problem.class, pid, pid, LIMIT);
        return problems;
    }

    @Transactional(readOnly = true)
    public List<Problem> findSimilarProblems(String username, int pid) {
        int LIMIT = 6;
        List<Problem> problems = objects("recommender.loged.similar.problems", Problem.class, pid, pid, username, LIMIT);
        return problems;
    }

    @Transactional(readOnly = true)
    public List<Problem> findSimilarProblems(String username, int pid, String tags) {
        int LIMIT = 6;
        List<Problem> problems = objects("recommender.loged.similar.problems", Problem.class, pid, pid, username, LIMIT);
        return problems;
    }

    @Transactional(readOnly = true)
    public double problemComplexity(int pid) {
        return real("problem.complexity", pid);
    }

    @Transactional(readOnly = true)
    public List<Problem> findSimilarProblemsFiltred(String username, int pid, String tags) {
        double complexity = problemComplexity(pid);
        int LIMIT = 6;
        List<Problem> problems = objects("recommender.loged.similar.problems.filtred", Problem.class, complexity, tags, pid, username, LIMIT);
        return problems;
    }

    @Transactional(readOnly = true)
    public boolean isPrefferedForUser(String username, Problem problem, String tags) {
        return bool("recommender.is.preffered.for.user", problem.getPid(), tags);
    }

    @Override
    public void registerPreferenceProfile(int uid, String tags) {
        if (hasPreferenceProfile(uid)) {
            updatePreferenceProfile(uid, tags);
        } else {
            insertPreferenceProfile(uid, tags);
        }
    }

    @Override
    public void updatePreferenceProfile(int uid, String tags) {
        dml("recommender.preference.update", tags, uid);
    }

    @Override
    public void insertPreferenceProfile(int uid, String tags) {
        dml("recommender.preference.insert", uid, tags);
    }

    @Transactional(readOnly = true)
    public boolean hasPreferenceProfile(int uid) {
        return bool("recommender.preference.hasprofile", uid);
    }

    @Override
    public String loadPreferenceProfile(String username, int uid) {
        if (hasPreferenceProfile(uid) == false) {
            String tags = string("load.users.recommender.loadtags", username);
            String filtroTags = "";
            for (int i = tags.length() - 1; i >= 0; i--) {
                if (tags.charAt(i) == '1') {
                    filtroTags += "-" + i + "-";
                }
            }

            int CANT_CONSIDER_PREFFERENCE = 10;
            List<Object> param = new LinkedList<Object>();
            param.add(username);
            param.add(CANT_CONSIDER_PREFFERENCE);

            List<ProblemClassification> lista = objects("recommender.solved.user", ProblemClassification.class, param.toArray());

            for (int i = lista.size() - 1; i >= 0; i--) {
                filtroTags += "-" + lista.get(i).getIdClassification() + "-";
            }
            registerPreferenceProfile(uid, filtroTags);
        }
        return string(getSql("recommender.preference.load"), uid );
    }
}
