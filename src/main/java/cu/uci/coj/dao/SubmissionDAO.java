package cu.uci.coj.dao;

import java.util.List;

import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;

import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Filter;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

public interface SubmissionDAO extends BaseDAO {
	
	public List<SubmissionJudge> rejudgeSubmits(Filter filter,PagingOptions options);
	public void applyEffects(SubmissionJudge submit);
	
	public void removeEffects(SubmissionJudge submit);
	
    public String submitStatus(Integer submitId);
    
    public boolean Solved(int uid, int pid);

    public boolean tryButunSolved(int uid, int pid);

    public List<SubmissionJudge> submissions(Integer uid);
    /**
     * Counts the total number of SubmissionJudge that have been made to the given
     * problem
     *
     * @param id ID of the problem
     * @return A resultSet object with the result of the query or null if there
     * are not any submissions to the problem
     *
     */
    public Integer ProblemSolutionsCount(String id);

    public int countSubmissions(String username, String language, Integer pid, String status);

    public int countSubmissionsAdmin(Filter filter);

    public int countSubmissionsAdminRejudge(String username, String language, Integer pid, String status);

    public int countSubmissionsContest(String username, String language, Integer pid, String status, Contest contest);

    public int countSubmissionsVirtualContest(String username, String language, Integer pid, String status, Contest contest,String current_user);

    public int countSubmissionsContestAdmin(String username, String language, Integer pid, String status, int cid);

    public IPaginatedList<SubmissionJudge> getSubmissions(String username, int found,String language, Integer pid, String status, PagingOptions options,Boolean loggedIn, Boolean admin,String usern);

    public List<SubmissionJudge> getSourceCodes(String username, int type);
            
    public IPaginatedList<SubmissionJudge> getSubmissionsAdmin(Filter filter, int found,PagingOptions options,boolean asc);

    public IPaginatedList<SubmissionJudge> getSubmissionsAdminRejudge(String username, int found, String language, Integer pid, String status, PagingOptions options);

    public IPaginatedList<SubmissionJudge> getContestSubmissions(int found,String username, String language, Integer pid, String status, PagingOptions options, String usern,boolean loggedIn,boolean admin,  Contest contest);

    public IPaginatedList<SubmissionJudge> getVirtualContestSubmissions(int found,String username, String language, Integer pid, String status, PagingOptions options, boolean is_admin, Contest contest,String current_user);

    public IPaginatedList<SubmissionJudge> getContestSubmissionsAdmin(String username, int found,String language, Integer pid, String status, PagingOptions options, int cid);

    public List<Language> getEnabledLanguages();

    public SubmissionJudge getSourceCode(int submit_id);

    public String getCompileInfo(int sid);

    public SubmissionJudge getContestCompileInfo(int sid, int cid);

    public int insertSubmission(int iduser, String username, int pid, String source, String language, boolean locked, Integer courseId);

    public void changeStatus(int sid, String status);

    public int insertContestSubmission(int iduser, String username, int pid, String source, String language,int cid,boolean virtual);

    public int insertVirtualSubmission(int iduser, String username, int pid, String source, String language,int cid,boolean virtual,Contest contest);


    public SubmissionJudge loadSubmissionAdmin(int sid);

    public SubmissionJudge loadContestSubmissionAdmin(int sid);

    public void updateSubmission(SubmissionJudge submission);

    public void updateContestSubmission(SubmissionJudge submission);

    public int getAccu(Integer pid);

    public int countBestSolutions(Integer pid);

    public IPaginatedList<SubmissionJudge> bestSolutions(Integer pid,int found,PagingOptions options, SecurityContextHolderAwareRequestWrapper request, Problem problem);

    public String emailBySubmission(int sid);
    
    public List<String> emailBySubmissionRange(int sid1,int sid2);
}







