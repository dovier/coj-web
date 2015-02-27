/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.dao;

import cu.uci.coj.model.Problem;
import cu.uci.coj.model.UserProfile;
import java.util.List;

/**
 *
 * @author Jose Carlos
 */
public interface RecommenderDAO extends BaseDAO {
    
    public void registerRecommendationProfile(int uid,List<Problem> recommendation);
    
    public void updateRecommendationProfile(int uid,List<Problem> recommendation);
    
    public void insertRecommendationProfile(int uid,List<Problem> recommendation);
    
    public boolean hasRecommendationProfile(int uid);
    
    public List<Problem> loadRecommendationProfile(String username,int uid,String sort,String direction);
    
    public List<Problem> suggestedProblems(List<UserProfile> profiles, UserProfile profile, String orderby, String inv);
    
    public List<Problem> suggestedProblemsColdStart(String username, int uid, String orderby, String inv);
    
    public List<Problem> findSimilarProblems(int pid);
  
    public List<Problem> findSimilarProblems(String username,int pid);
    
    public List<Problem> findSimilarProblems(String username,int pid,String tags);
    
    public List<Problem> findSimilarProblemsFiltred(String username,int pid, String tags );
    
    public double problemComplexity(int pid);
    
    public boolean isPrefferedForUser(String username,Problem problem,String tags);
    
    
    public void registerPreferenceProfile(int uid,String tags);
    
    public void updatePreferenceProfile(int uid,String tags);
    
    public void insertPreferenceProfile(int uid,String tags);
    
    public boolean hasPreferenceProfile(int uid);
    
    public String loadPreferenceProfile(String username,int uid);
    
   
}
