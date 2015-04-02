package cu.uci.coj.dao;

import java.util.List;
import java.util.Map;

import cu.uci.coj.controller.interfaces.IACMScoreboard;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.ContestStyle;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.model.User;
import cu.uci.coj.model.VirtualContest;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

public interface ContestDAO extends BaseDAO {

	public int getStyle(int cid);

	public ContestStyle loadScoringStyle(int sid);

	public void insertLanguages(Contest contest);

	public void updateDate(SubmissionJudge submit);

	public void insertLanguages(int cid, int[] languagesids);

	public void freezeContest(Contest contest);

	public IPaginatedList<SubmissionJudge> pendingBalloons(int cid,
			PagingOptions options, String group);

	public List<Problem> getAllContestProblems(int cid);

	public void markBalloon(int sid);

	public List<Map<String, Object>> baylorXMLData(Integer cid);

	public void insertData(String site, String[] teams, String teamCoach,
			String[] teamUsers, String[] user, Integer cid, Integer warmupCid);

	public List<Language> getContestLanguagesVirtual(int cid);

	public void checkContestsCreated();

	public void checkContestsStarted();

	public void checkContestsEnded();

	public void guestGroup(Contest contest);

	public void insertBalloonTrackerContest(Contest contest);

	public void insertBalloonTrackerContest(int uid, int cid);

	public void updateAfterSubmitionContestVirtual(SubmissionJudge sub);

	public void repointContest(Contest contest, boolean frozen);

	// public boolean isRunningOrEndedContest(int cid);

	public boolean isJudgeInContest(int cid, int uid);

	public boolean isInContest(int cid, int uid);

	public List<Contest> loadContest();

	public Contest loadContest(int cid);

	public Contest loadVirtualContest(int cid);

	public Contest loadContestFull(int cid);

	public List<Language> getContestLanguages(int cid);

	public boolean isProblemInContest(Integer pid, int cid, int level);

	public boolean isProblemInVirtualContest(Integer pid);

	public void insertUserContest(int uid, int cid, String gid);

	public List<ContestStyle> loadEnabledScoringStyles();

	public int[] getRankingAcmMinimun(int cid);

	public int[] getGlobalRankingAcmMinimun(List<Integer> cids);

	public IACMScoreboard getRankingAcm(int cid, String selGroup,
			boolean groupby, List<Problem> problems);

	public IACMScoreboard getGlobalRankingAcm(List<Integer> cids,
			boolean groupby, List<Problem> problems);

	public List<User> getUsers();

	public List<String> getGroups(Integer cid);

	public IPaginatedList<User> getFreeContestRanking(int cid,
			PagingOptions options);

	public List<Contest> loadContestToImport();

	public void InsertContest(Contest contest);

	public int getMaxAvailableCID();

	public void importContestData(Contest contest);

	public boolean existsContest(int cid);

	public void updateContestFlags(Contest contest);

	public List<Problem> loadContestProblems(int cid);

	public void insertProblemContest(int pid, int cid, int level, int order);

	public void clearProgrammingLanguages(int cid);

	public void insertLanguageContest(int lid, int cid);

	public Contest loadContestForProblems(int cid);

	public void removeProblemContest(int cid, int pid);

	public Contest getContestForOverview(int cid);

	public void updateContestOverview(Contest contest);

	public Contest loadContestForManageUsers(int cid);

	public void clearUsersContest(int cid);

	public void clearContestJudges(int cid);

	public void deleteUserContest(int cid, int uid);

	public void insertUsersContest(Contest contest);

	public void insertJudgesContest(Contest contest);

	public void insertJudgeContest(int cid, int uid);

	public List<Contest> getComingAndRunningContests();

	public IPaginatedList<Contest> getComingContests(PagingOptions options);

	public IPaginatedList<Contest> getRunningContests(PagingOptions options);

	public IPaginatedList<Contest> getPastContests(PagingOptions options,
			String pattern);

	public int countContest();

<<<<<<< HEAD
	public IPaginatedList<Contest> loadContests(PagingOptions options,
			String access, String enabled, String status);
=======
	public IPaginatedList<Contest> loadContests(PagingOptions options, String access, String enabled, String status);
>>>>>>> branch 'master' of ssh://git@codecomunidades.uci.cu/night91/coj.git

	public Contest loadContestGlobalSettings(int cid);

	public Contest loadContestManage(int cid);

	public void updateContestGlobalSettings(Contest contest);

	public void updateContestManage(Contest contest);

	public boolean existContestByName(String name, int cid);

	public List<Contest> loadVirtualTemplates();

	public List<Contest> loadVirtualTemplates(int style);

	public void deleteVirtualContest(int uid);

	public void joinVirtualContest(int vid, int uid, boolean createjoin);

	public int createVirtualContest(Contest contest, int uid, String username);

	public boolean overlapsContest(Contest contest);

	public IPaginatedList<Contest> loadUserVirtualContests(int uid,
			PagingOptions options);

	public void deleteVirtualContestCid(int cid);

	public List<Contest> loadPublicVirtualContests();

	public int countVirtualContests();

	public int countVirtualGlobalList(String cid, String username, int access,
			int status);

	public IPaginatedList<Contest> loadGlobalVirtualContests(
			PagingOptions options, String cid, String username, int access,
			int status);

	public int getVirtualRunning(String username);

	public int findVirtualContestForUser(String username);

	public List<User> getRankingACMVirtual(int cid, String username,
			List<Problem> problems);

	public void buildRankingACMVirtual(List<Problem> problems,
			List<User> users, Map<Integer, Integer> uidPos, Contest contest,
			String username, int uid);

	public IPaginatedList<VirtualContest> loadVirtualContests(
			PagingOptions options);

	public int countContestGeneralScoreboard(String pattern);

	public IPaginatedList<User> getContestGeneralScoreboard(int found,
			String pattern, PagingOptions options);

	public void updateBlockedContest(int cid, boolean block);

	public int getContestLevel(int cid);

	public Map<Integer, Problem> loadContestProblemsLetters(int cid);

	public void insertProblemColor(int pid, int cid, String color);

	public void applyEffects(SubmissionJudge submit, Contest contest);

	public void unfreezeIfNecessary(Contest contest);
}
