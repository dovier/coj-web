package cu.uci.coj.dao;

import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Limits;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.ProblemClassification;
import cu.uci.coj.model.ProblemSource;
import cu.uci.coj.model.Translation;
import cu.uci.coj.model.UserProfile;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import java.util.List;

public interface ProblemDAO extends BaseDAO {
	public int getSourceLimitByPid(int pid);

	public void checkProblemCreated();

	public void updateProblemI18N(Problem problem);

	public String generateModelSolutionFrom(int problemId);

	public String creatorUsernameByPid(int pid);

	public List<ProblemClassification> getProblemClassifications(int pid);

	public List<Problem> getAllContestProblems(int cid);

	public List<Problem> searchProblems(String language, String pattern,
			boolean admin, String username);

	public IPaginatedList<Problem> findAllProblems(String language,
			String pattern, int found, PagingOptions options, String username,
			int uid, int filterby);

	public int countProblem(String pattern, int filterby, String username);

	public int countProblemAdmin(Integer uid, String role, String pattern);

	public IPaginatedList<Problem> loadProblemsAdmin(int found, Integer uid,
			String role, String language, String pattern, PagingOptions options);

	public Problem getProblemByCode(String language, Integer pid,
			boolean isAdminOrPsetter);

	public void fillProblemLanguages(Problem problem);

	public void fillProblemSetters(Problem problem);

	public boolean hasLanguageAvailable(Integer pid, int lid);

        public Problem getProblemSubmitDataByAbb(Integer pid, int lid);

	public boolean exists(Integer pid);

	public boolean existProblemByTitle(String title);

	public boolean isEnabled(Integer pid);

	public boolean isDisable24h(Integer pid);

	public int getPidByTitle(String title);

	public ProblemSource getProblemSourceById(Integer pid);

	public List<Language> getEnabledLanguagesByProblem(Integer pid);

	public int countProblemContest(int cid);

	public IPaginatedList<Problem> getContestProblems(int found,
			String language, String username, Contest contest,
			PagingOptions options);

	public IPaginatedList<Problem> getVirtualContestProblems(int found,
			String language, String username, Contest contest,
			PagingOptions options);

	public int getCurrentLevel(int uid, int cid);

	public Problem getProblemContest(String language, Integer pid,
			Contest contest);

	public Problem getContestProblem(String language, Integer pid,
			Contest contest);

	public List<Problem> getContestProblems(String language, int cid);

	public List<Problem> getVirtualContestProblems(String language,
			String username);

	public List<Problem> getProblemsOffContest(String language, int cid);

	public List<Problem> getProblemsOffContestVirtual(String language);

	public void clearProblemsContest(int cid);

	public Problem getProblemByPid(String language, int pid);

	public void addProblem(Problem problem);

	public void insertProblemLanguage(int pid, int lid);

	public void insertProblemStats(int pid);

	public void clearProgrammingLanguages(int pid);

	public void clearPsetters(int pid);

	public void updateProblem(Problem problem);

	public boolean existProblemTitleOffPid(Integer pid, String title);

	public Problem getStatistics(String language, Integer pid);

	public boolean isLocked(int uid, int pid);

	public void insertProblemClassification(int pid, int classificationid);

	public void deleteProblemClassification(int pid, int classificationid);

	public IPaginatedList<ProblemClassification> getClassifications(
			PagingOptions options);

	public List<ProblemClassification> getClassifications();

	public void insertClassification(String nameClassification);

	public void insertProblemClassification(Integer pid, Integer cid,
			Integer complexity);

	public void updateClassification(Integer classid, String name);

	public void deleteClassification(Integer classid);

	public List<ProblemSource> getProblemSources();

	public IPaginatedList<ProblemSource> getProblemSources(PagingOptions options);

	public void insertProblemSource(String name, String author);

	public void updateProblemSource(int idSource, String name, String author);

	public List<Problem> suggestedProblemsColdStart(String language,
			String userName, int uid);

	public List<Problem> suggestedProblems(String language,
			List<UserProfile> knProfiles, UserProfile profile, String pattern,
			int top, String sort, String direction, Integer classificationId,
			Integer complexity);

	public List<Problem> suggestedProblemsColdStart(String language,
			String username, String pattern, int top, String sort,
			String direction, int uid, Integer classificationId,
			Integer complexity);

	public IPaginatedList<Problem> findAllProblems(String language,
			String pattern, int found, PagingOptions options, String username,
			int i0, Integer filterby, Integer idClassification,
			Integer complexity);

	public int countProblem(String pattern, Integer filterby, String username,
			Integer idClassification, Integer complexity);

	public int countProblemWithoutClassifications();

	public IPaginatedList<Problem> findAllProblemsWithoutClassification(
			String language, PagingOptions options);

	public List<String> usersInProblem(int pid);

	public void deleteProblemSource(Integer idSource);

	public List<Problem> fillInformation(String username, int uid,
			List<Problem> problems);

	public void insertTranslation(String username, Translation translation);

	public IPaginatedList<Translation> getTranslationList(String username,
			Integer pid, String locale, PagingOptions options);

	public void deleteTranslation(Integer id);

	public void editTranslation(Translation translation);

	public void approveTranslation(Translation translation, String username);

	public Translation getTranslation(Integer id);
        
        public void insertLimit(Limits limit);

        public void clearLimits(int problemId);

        public void fillProblemLimits(Problem problem);
	
}
