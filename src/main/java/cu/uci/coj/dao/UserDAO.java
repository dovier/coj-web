package cu.uci.coj.dao;

import java.util.List;
import java.util.Map;

import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.Registration;
import cu.uci.coj.model.Team;
import cu.uci.coj.model.User;
import cu.uci.coj.model.UserClassificationStats;
import cu.uci.coj.model.UserProfile;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

public interface UserDAO extends BaseDAO {

	public void unbanUser(int uid);

	public void banUser(int uid,String description);
	
	public UserProfile loadUserProfile(String userName);

	public boolean hasProfile(String userName);

	public void updateUsersStatusOnStartUp();

	public List<User> loadContestBalloonTrackers(int cid);

	public User getUserContest(String uid, int cid);

	public Map<String, Object> getMailValues(String username);

	public boolean isIn(String user);

	public boolean emailExist(String email);

	public boolean emailExistUpdate(String email, String username);

	public void UpdateUserProfile(Registration registration, int uid);

	public void UpdateUser(int id, String pass, int country, int institution, boolean enabled, String nick, int locale, Registration registration);

	@Deprecated
	public void insertUser(String user, String name, String lastname, String email);

	@Deprecated
	public void insertUserPrivate(String user);

	public String getUserRule(String username);

	public String getUserLocale(String username);

	public void updateUsersStatus(boolean status, String username);

	public int countEnabledUsers(String pattern, boolean online);

	public int countEnabledUsersForScoreboard(String pattern, boolean online,Integer uid);

	public int countEnabledUsersByInstitutions(String pattern, boolean online, int inst_id);

	public int countEnabledUsersByCountry(String pattern, boolean online, int country_id);

	public IPaginatedList<User> getUserRanking(String pattern, int found, boolean online, Integer uid,PagingOptions options);

	public IPaginatedList<User> getInstitutionUsersRanking(String pattern, int found, boolean online, PagingOptions options, int inst_id);

	public IPaginatedList<User> getCountryUsersRanking(String pattern, int found, boolean online, PagingOptions options, int country_id);

	public boolean isUser(String username);

	public User loadUserData(String username);

	public User loadAllUserData(String username);

	public User loadAllTeamData(String username);

	public User loadContestUserData(String username);

	public List<Problem> getProblemsTryied(String username);

	public List<Problem> getProblemsTryied(int uid);

	public List<Problem> getProblemsSolvedInContest(Integer uid, Contest contest);

	public List<Problem> getProblemsTriedInContest(Integer uid, Contest contest);

	public int getDefaultProgrammingLanguageId(String username);

	public boolean isJudgeInContest(int uid, int cid);

	public boolean isInContest(int uid, int cid);

	public void updateUser(User user);

	public void updateUserByAdmin(User user);

	public void clearUserAuthorities(String username);

	public void grantUserAuthority(User user);

	public boolean existUserByMailOffUid(String mail, int uid);

	public void InsertUser(User user);

	public List<String> getUserInContest(int cid);

	public List<User> loadContestUsers(int cid);

	public List<User> loadContestJudges(int cid);

	public List<User> loadJudgesOffContest(int cid);

	public List<User> loadUsersOffContest(Contest contest);

	public List<String> getUserAuthorities(String username);

	public IPaginatedList<User> loadUsersAdmin(String pattern, PagingOptions options);

	public IPaginatedList<User> loadTeamsAdmin(String pattern, PagingOptions options);

	public void createTeams(Team teams);

	public Language getProgrammingLanguageByUsername(String username);

	public void updateChangeTime(int uid);

	public List<User> loadUsersButOne(String username);

	public List<User> loadUsersForVirtualButOne(String username);

	public List<UserProfile> loadRelatedProfiles(UserProfile profile);

	public String loadTags(String username);

	public void updateTags(String username, String tag);

	public void checkUserStatus();

	public String userByMail(String mail);

	public UserClassificationStats getUserClassifications(Integer uid);

	UserClassificationStats getTotalClassifications();
}
