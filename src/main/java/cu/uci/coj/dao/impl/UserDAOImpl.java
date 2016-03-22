package cu.uci.coj.dao.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cu.uci.coj.config.Config;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.mail.MailNotificationService;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.ProblemClassification;
import cu.uci.coj.model.ProblemComplexity;
import cu.uci.coj.model.ProblemRichTitle;
import cu.uci.coj.model.Registration;
import cu.uci.coj.model.Roles;
import cu.uci.coj.model.Team;
import cu.uci.coj.model.User;
import cu.uci.coj.model.UserClassificationStats;
import cu.uci.coj.model.UserProfile;
import cu.uci.coj.query.DmlPart;
import cu.uci.coj.query.Order;
import cu.uci.coj.query.Query;
import cu.uci.coj.query.Where;
import cu.uci.coj.utils.Utils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

@Repository("userDAO")
@Transactional
public class UserDAOImpl extends BaseDAOImpl implements UserDAO {

	private int number;

	public void unbanUser(int uid) {
		dml("update users set ban_reason=null,status='inactive',enabled=true,ban_date=null where uid=?",
				uid);
	}

	public void banUser(int uid, String description) {
		dml("update users set ban_reason=?,status='banned',enabled=false,ban_date=now() where uid=?",
				description, uid);
	}

	@Resource
	private MailNotificationService mailNotificationService;

	public void updateUsersStatusOnStartUp() {
		dml("update.users.startup");
	}

	@Transactional(readOnly = true)
	public List<String> getUserInContest(int cid) {
		return strings("get.user.in.contest", cid);
	}

	public void UpdateUser(int id, String pass, int country, int institution,
			boolean enabled, String nick, int locale, Registration registration) {
		dml.update("users", Where.eq("uid", id), DmlPart
				.check("password", pass), DmlPart.check("access_rule",
				registration.getAccess_rule()), DmlPart.check("country_id",
				country), DmlPart.check("nick", nick), DmlPart.check("locale",
				locale), DmlPart.check("inst_id", institution), DmlPart.check(
				"enabled", enabled), DmlPart.check("lid",
				registration.getPlanguage()), DmlPart.check("gender",
				registration.getGender()), DmlPart.check(
				"contest_notifications",
				Boolean.valueOf(registration.isContestNotifications())),
				DmlPart.check("submition_notifications", Boolean
						.valueOf(registration.isSubmissionNotifications())),
				DmlPart.check("entries_notifications",
						Boolean.valueOf(registration.isEntriesNotifications())));
	}

	public void UpdateUserProfile(Registration registration, int uid) {
		dml.update("user_profile", Where.eq("uid", uid), DmlPart.check(
				"fullname", registration.getName()), DmlPart.check("fullname",
				registration.getName()), DmlPart.check("lastname",
				registration.getLastname()), DmlPart.check("fullname",
				registration.getName()), DmlPart.check("email",
				(registration.getEmail() != null && registration
						.isUpdateemail()) ? registration.getEmail() : null),
				DmlPart.check(
						"mail_quote",
						(registration.isUpdateemail()) ? registration
								.getMail_quote() : null), DmlPart.check("dob",
						registration.getDob().toString()), DmlPart.check(
						"show_dob", Boolean.valueOf(registration.isShowdob())));
	}

	@Transactional(readOnly = true)
	public String getUserRule(String username) {
		return string("get.user.rule", username);
	}

	@Transactional(readOnly = true)
	public String getUserLocale(String username) {
		return string("get.user.locale", username);
	}

	public void registerLogin(boolean status, String ip,String username) {
		dml("update.last.ip",ip, username);
        dml("update.last.login.date",username);
        dml("insert.log", "user logged in",username);
		dml("update.user.status", status, username);
	}

	@Transactional(readOnly = true)
	public int countEnabledUsers(String pattern, boolean online) {
		String sql = getSql("count.enabled.users");
		List<String> list = new LinkedList<String>();
		if (online) {
			sql += " " + getSql("count.enabled.users.1");
		}
		if (pattern != null) {
			sql += " " + getSql("count.enabled.users.2");
			list.add("%" + pattern + "%");
			list.add("%" + pattern + "%");
		}
		return integer(sql, list.toArray());
	}

	@Transactional(readOnly = true)
	public int countEnabledUsersForScoreboard(String pattern, boolean online,
			Integer uid) {

		String sql = "user_profile join users on user_profile.uid= users.uid join country on country.country_id = users.country_id join user_stats on user_stats.uid = users.uid join institution on institution.inst_id = users.inst_id";
		if (uid != null)
			sql += " join followers on followers.uid = users.uid and followers.fid="
					+ uid;
		Query query = new Query(sql);
		query.where(
				Where.yes("users.enabled"),
				Where.yes(online ? "online" : null),
				Where.or(Where.ilike("username", pattern),
						Where.ilike("nick", pattern)));
		return integer(query.count(), query.arguments());
	}

	private void userOrder(Query query, String sort, boolean asc) {
		if (sort != null) {
			String ord = null;
			if (sort.equals("acc")) {
				ord = "accu";
			} else if (sort.equals("accp")) {
				ord = "percent";
			} else if (sort.equals("sub")) {
				ord = "total";
			} else if (sort.equals("points")) {
				ord = "points";
			}

			query.order(asc ? Order.asc(ord) : Order.desc(ord));
		} else {
			query.order(Order.desc("points"), Order.desc("ac"),
					Order.asc("total"), Order.asc("user_stats.last_accepted"),
					Order.asc("users.registration_date"));
		}
	}

	@Transactional(readOnly = true)
	public String userByMail(String mail) {
		return string("user.by.mail", mail);
	}

	@Transactional(readOnly = true)
	public IPaginatedList<User> getUserRanking(String pattern, int found,
			boolean online, Integer uid, PagingOptions options) {

		String sql = "public.users join country on public.users.country_id=country.country_id join institution on public.users.inst_id= institution.inst_id join user_stats on user_stats.uid= public.users.uid join user_profile on user_profile.uid= users.uid";
		if (uid != null)
			sql += " join followers on followers.uid = users.uid and followers.fid="
					+ uid;
		Query query = new Query(sql);
		query.where(
				Where.yes("users.enabled"),
				Where.or(Where.ilike("users.username", pattern),
						Where.ilike("nick", pattern)),
				Where.yes(online ? "online" : null));

		query.paginate(options, 30);
		userOrder(query, options.getSort(),
				"asc".equals(options.getDirection()));
		List<User> users = objects(
				query.select("status,country.zip as country,country.name as country_desc,institution.zip as institution,institution.name as institution_desc,username,nick,(ac+rte+ce+wa+mle+tle+ole+pe) as total,ac as acc,uaccu as accu,points,online,case when (ac+wa+pe+ce+rte+tle+mle+ole) > 0 then (cast(ac as double precision)/(ac+wa+pe+ce+rte+tle+mle+ole))*100 else 0 end as percent"),
				User.class, query.arguments());

		number = options.getOffset(30);
		for (int i = 0; i < users.size(); i++) {
			users.get(i).setRank(number + i + 1);
		}

		return getPaginatedList(options, users, 30, found);
	}

	@Transactional(readOnly = true)
	public int countEnabledUsersByInstitutions(String pattern, boolean online,
			int inst_id) {
		Query query = new Query(
				"user_profile join users on user_profile.uid= users.uid");
		query.where(
				Where.yes("enabled"),
				Where.eq("inst_id", inst_id),
				online ? Where.yes("online") : Where.noop(),
				Where.or(Where.ilike("username", pattern),
						Where.ilike("nick", pattern)));
		return integer(query.count(), query.arguments());
	}

	@Transactional(readOnly = true)
	public int countEnabledUsersByCountry(String pattern, boolean online,
			int country_id) {
		String sql = getSql("count.enabled.users.bycountry");
		List<Object> list = new LinkedList<Object>();
		list.add(country_id);
		if (online) {
			sql += " " + getSql("count.enabled.users.bycountry.1");
		}
		if (pattern != null) {
			sql += " " + getSql("count.enabled.users.bycountry.2");
			list.add("%" + pattern + "%");
			list.add("%" + pattern + "%");
		}
		return integer(sql, list.toArray());
	}

	@Transactional(readOnly = true)
	public IPaginatedList<User> getInstitutionUsersRanking(String pattern,
			int found, boolean online, PagingOptions options, int inst_id) {
		Query query = new Query(
				"public.users join country on public.users.country_id=country.country_id join institution on public.users.inst_id= institution.inst_id join user_stats on user_stats.uid= public.users.uid join user_profile on user_profile.uid= users.uid");
		query.where(
				Where.yes("users.enabled"),
				Where.eq("users.inst_id", inst_id),
				Where.or(Where.ilike("users.username", pattern),
						Where.ilike("nick", pattern)),
				Where.yes(online ? "online" : null, online));
		userOrder(query, options.getSort(),
				"asc".equals(options.getDirection()));
		query.paginate(options, 30);
		List<User> users = objects(
				query.select("status,country.zip as country,country.name as country_desc,institution.zip as institution,institution.name as institution_desc,username,nick,"
						+ "(ac+rte+ce+wa+mle+tle+ole+pe) as total,"
						+ "ac as acc,ac as accu,points,online,"
						+ "case when (ac+wa+pe+ce+rte+tle+mle+ole) > 0 then (cast(ac as double precision)/(ac+wa+pe+ce+rte+tle+mle+ole))*100 else 0 end as percent"),
				User.class, query.arguments());

		number = options.getOffset(30);
		for (int i = 0; i < users.size(); i++) {
			users.get(i).setRank(number + i + 1);
		}
		return getPaginatedList(options, users, 30, found);
	}

	@Transactional(readOnly = true)
	public IPaginatedList<User> getCountryUsersRanking(String pattern,
			int found, boolean online, PagingOptions options, int country_id) {
		Query query = new Query(
				"public.users join country on public.users.country_id=country.country_id join institution on public.users.inst_id= institution.inst_id join user_stats on user_stats.uid= public.users.uid join user_profile on user_profile.uid= users.uid");
		query.where(
				Where.yes("users.enabled"),
				Where.eq("users.country_id", country_id),
				Where.or(Where.ilike("users.username", pattern),
						Where.ilike("nick", pattern)),
				Where.yes(online ? "online" : null));
		userOrder(query, options.getSort(),
				"asc".equals(options.getDirection()));
		query.paginate(options, 30);
		List<User> users = objects(
				query.select(
						"status",
						"country.zip as country",
						"country.name as country_desc",
						"institution.zip as institution",
						"institution.name as institution_desc",
						"username",
						"nick",
						"(ac+rte+ce+wa+mle+tle+ole+pe) as total",
						"ac as acc",
						"ac as accu",
						"points",
						"online",
						"case when (ac+wa+pe+ce+rte+tle+mle+ole) > 0 then (cast(ac as double precision)/(ac+wa+pe+ce+rte+tle+mle+ole))*100 else 0 end as percent"),
				User.class, query.arguments());
		number = options.getOffset(30);
		for (int i = 0; i < users.size(); i++) {
			users.get(i).setRank(number + i + 1);
		}
		return getPaginatedList(options, users, 30, found);
	}

	@Transactional(readOnly = true)
	public List<Problem> getProblemsTryied(String username) {

		return objects("get.problems.tryied", Problem.class, username, username);
	}

	@Transactional(readOnly = true)
	public List<Problem> getProblemsTryied(int uid) {
		return objects("get.problems.tryied.uid", Problem.class, uid);
	}

	@Transactional(readOnly = true)
	public User loadUserData(String username) {

		User user = object("load.user.data", User.class, username);
		user.setStatus(string("load.user.status", username));
		if (user.getLast_accepted() != null)
			user.setLast_accepted(user.getLast_accepted().substring(0, 19));
		if (user.getLast_submission() != null)
			user.setLast_submission(user.getLast_submission().substring(0, 19));
		user.setRgdate(user.getRgdate().substring(0, 19));
		user.setTeam(false);
		user.builTotal();
		return user;
	}

	@Transactional(readOnly = true)
	public User loadContestUserData(String username) {
		User user = object("load.contest.user.data", User.class, username);
		user.setTeam(true);
		return user;
	}

	@Transactional(readOnly = true)
	public boolean isUser(String username) {
		return bool("is.user", username);
	}

	@Transactional(readOnly = true)
	public int getDefaultProgrammingLanguageId(String username) {
		return integer("get.default.prog.lang", username);
	}

	@Transactional(readOnly = true)
	public boolean isJudgeInContest(int uid, int cid) {
		return bool("is.judge.in.contest", uid, cid);
	}

	@Transactional(readOnly = true)
	public boolean isInContest(int uid, int cid) {
		return bool("is.in.contest", cid, uid);
	}

	@Transactional(readOnly = true)
	public User getUserContest(String username, int cid) {
		return object("get.user.contest", User.class, username, cid, cid);
	}

	@Transactional(readOnly = true)
	public List<Problem> getProblemsSolvedInContest(Integer uid, Contest contest) {
		if (contest.isFrozen()) {
			return objects("problems.solved.in.contest.frozen", Problem.class,
					uid, contest.getCid(), contest.getCid());
		}
		return objects("problems.solved.in.contest", Problem.class, uid,
				contest.getCid());
	}

	@Transactional(readOnly = true)
	public List<Problem> getProblemsTriedInContest(Integer uid, Contest contest) {

		return objects("get.problem.tried.in.contest", Problem.class, uid,
				contest.getCid(), uid, contest.getCid());
	}

	@Transactional(readOnly = true)
	public User loadAllUserData(String username) {
		User user = object("load.all.user.data", User.class, username);
		user.setTeam(false);
		user.setPassword(null);
		user.setConfirmPassword(null);
		return user;
	}

	@Transactional(readOnly = true)
	public User loadAllTeamData(String username) {
		User user = object("load.all.team.data", User.class, username);
		user.setTeam(true);
		user.setPassword(null);
		user.setConfirmPassword(null);
		return user;
	}

	public void updateUser(User user) {
		if (user.isTeam()) {
			if (!StringUtils.isEmpty(user.getPassword())
					&& user.getPassword().length() > 7) {
				dml("update.user", user.getNick(), user.getCountry_id(),
						user.getInstitution_id(), user.getLid(),
						user.getLocale(), user.getPassword(),
						user.isEnableadveditor(), user.getUid());
				dml("update.user.by.admin.3", user.getCoach(),
						user.getUser_1(), user.getUser_2(), user.getUser_3(),
						user.getUid());
			} else {
				dml("update.user.1", user.getNick(), user.getCountry_id(),
						user.getInstitution_id(), user.getLid(),
						user.getLocale(), user.isEnableadveditor(),
						user.getUid());
				dml("update.user.by.admin.3", user.getCoach(),
						user.getUser_1(), user.getUser_2(), user.getUser_3(),
						user.getUid());
			}
		} else {
			if (bool("email.changed", user.getEmail(), user.getUid())) {
				dml("enable.user", false, user.getUsername());
				Md5PasswordEncoder md5 = new Md5PasswordEncoder();
				String token = md5.encodePassword(user.getUsername(),
						new Date().toString());
				dml("insert.account.activation", user.getUsername(), token,
						false);
				mailNotificationService.sendEmailChanged(user, token);
			}

			if (!StringUtils.isEmpty(user.getPassword())
					&& user.getPassword().length() > 7) {
				dml("update.user.2", user.getNick(), user.getCountry_id(),
						user.getInstitution_id(), user.getLid(),
						user.getLocale(), user.getPassword(),
						user.isProblemNotifications(),
						user.isContestNotifications(),
						user.isSubmissionNotifications(),
						user.isNewprivatemessageNotifications(),
						user.isWboardNotifications(),
						user.isEntriesNotifications(), user.isShowemail(),
						user.getGender(), user.isSee_solutions(),
						user.isEnableadveditor(), user.getUsername());
			} else {
				dml("update.user.3", user.getNick(), user.getCountry_id(),
						user.getInstitution_id(), user.getLid(),
						user.getLocale(), user.isProblemNotifications(),
						user.isContestNotifications(),
						user.isSubmissionNotifications(),
						user.isNewprivatemessageNotifications(),
						user.isWboardNotifications(),
						user.isEntriesNotifications(), user.isShowemail(),
						user.getGender(), user.isSee_solutions(),
						user.isEnableadveditor(), user.getUsername());
			}
			dml(replaceSql("update.user.4", "<dob>", parseDate(user.getDob())),
					user.getEmail(), user.getName(), user.getLastname(),
					user.isShowdob(), user.isView_problem_info(), user.getUid());

		}
	}

	public void updateUserByAdmin(User user) {

		if (user.isTeam())
			user.setRole(Roles.ROLE_TEAM);

		if (!StringUtils.isEmpty(user.getPassword())
				&& user.getPassword().length() > 0) {
			dml("update.user.by.admin", user.getNick(), user.getCountry_id(),
					user.getInstitution_id(), user.getLid(), user.getLocale(),
					user.getPassword(), user.isContestNotifications(),
					user.isSubmissionNotifications(),
					user.isNewprivatemessageNotifications(),
					user.isWboardNotifications(),
					user.isProblemNotifications(),
					user.isEntriesNotifications(), user.isShowemail(),
					user.getGender(), user.getAccess_rule(), user.isEnabled(),
					user.isUpdate_nick(), user.getUsername());
		} else {
			dml("update.user.by.admin.1", user.getNick(), user.getCountry_id(),
					user.getInstitution_id(), user.getLid(), user.getLocale(),
					user.isContestNotifications(),
					user.isSubmissionNotifications(),
					user.isNewprivatemessageNotifications(),
					user.isWboardNotifications(),
					user.isProblemNotifications(),
					user.isEntriesNotifications(), user.isShowemail(),
					user.getGender(), user.getAccess_rule(), user.isEnabled(),
					user.isUpdate_nick(), user.getUsername());
		}
		if (!user.isTeam()) {
			dml(replaceSql("update.user.by.admin.2", "<dob>", user.getDob()
					.toString()), user.getName(), user.getLastname(),
					user.isShowdob(), user.getEmail(), user.getMail_quote(),
					user.getUid());
		}
		if (user.isTeam()) {
			dml("update.user.by.admin.3", user.getCoach(), user.getUser_1(),
					user.getUser_2(), user.getUser_3(), user.getUid());
		}

		// TODO esto no esta metido en los updates anteriores para mantenerlos
		// en metodos separados. De esta manera puede ser posible, en un futuro,
		// lograr que se activen mediante llamadas AJAX separadas y que no sea
		// necesario editar para banear. Ahora (17/03/2015) no es prioridad.
		if (user.isEnabled())
			unbanUser(user.getUid());
		else
			banUser(user.getUid(), user.getBanReason());
	}

	public void clearUserAuthorities(String username) {
		dml("clear.user.authorities", username);
	}

	public void grantUserAuthority(User user) {
		for (String realAuthority : user.getAuthorities())
			dml("grant.user.authorities", user.getUsername(), realAuthority);
	}

	@Transactional(readOnly = true)
	public boolean existUserByMailOffUid(String mail, int uid) {
		return bool("exist.user.by.mail.off.uid", mail, uid);
	}

	public void InsertUser(User user) {
		dml("insert.user", user.getUsername(), user.getPassword(),
				user.getCountry_id(), user.getInstitution_id(), user.getNick(),
				user.getLocale(), user.getLid(), user.getGender(),
				user.isContestNotifications(),
				user.isSubmissionNotifications(),
				user.isNewprivatemessageNotifications(),
				user.isWboardNotifications(), user.isProblemNotifications(),
				user.isEntriesNotifications(), user.isShowemail());
		int uid = integer("select.uid.by.username", user.getUsername());
		dml(replaceSql("insert.user.1", "<dob>", parseDate(user.getDob())),
				uid, user.getEmail(), user.getName(), user.getLastname(),
				user.isShowdob());
		dml("insert.user.2", uid);
		dml("insert.user.3", user.getUsername(), Roles.ROLE_USER);
		// es necesario que el usuario se autosiga para que salgan sus propios
		// post en su pagina de inicio
		dml("follow.user", uid, uid);
	}

	@Transactional(readOnly = true)
	public List<User> loadContestUsers(int cid) {
		return objects("load.contest.users", User.class, cid);
	}

	@Transactional(readOnly = true)
	public List<User> loadContestBalloonTrackers(int cid) {
		return objects("load.contest.balloontrackers", User.class, cid);
	}

	@Transactional(readOnly = true)
	public List<User> loadContestJudges(int cid) {
		return objects("load.contest.judges", User.class, cid);
	}

	@Transactional(readOnly = true)
	public List<User> loadJudgesOffContest(int cid) {
		return objects("load.judges.off.contest", User.class, cid);
	}

	@Transactional(readOnly = true)
	public List<User> loadUsersOffContest(Contest contest) {
		switch (contest.getContestant()) {
		case 1: {
			return objects("load.users.off.contest", User.class,
					contest.getCid());
		}
		case 2: {
			return objects("load.users.off.contest.2", User.class,
					contest.getCid());
		}
		case 3: {
			return objects("load.users.off.contest.3", User.class,
					contest.getCid());
		}
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<User> loadBalloonUsersOffContest(Contest contest) {
		return objects("load.users.off.contest.2", User.class, contest.getCid());
	}

	@Transactional(readOnly = true)
	public List<String> getUserAuthorities(String username) {
		return strings("get.user.authorities", username);
	}

	@Transactional(readOnly = true)
	public IPaginatedList<User> loadUsersAdmin(String pattern,
			PagingOptions options) {
		if (pattern != null) {
			return paginated("users.admin.pattern", User.class, 30, options,
					"%" + pattern + "%", "%" + pattern + "%", "%" + pattern
							+ "%");
		}
		return paginated("users.admin", User.class, 30, options);
	}

	@Transactional(readOnly = true)
	public IPaginatedList<User> loadTeamsAdmin(String pattern,
			PagingOptions options) {
		if (pattern != null) {
			return paginated("teams.admin.pattern", User.class, 30, options,
					"%" + pattern + "%", "%" + pattern + "%");
		}
		return paginated("teams.admin", User.class, 30, options);
	}

	private void insertTeam(Team user) {
		dml("insert.team.1", user.getUsername(), user.getPassword(),
				user.getCountry(), user.getInstitution(), user.getNick(),
				user.getLocale(), user.isUpdate_nick(), true);
		dml("grant.user.authorities", user.getUsername(), Roles.ROLE_TEAM);
		int uid = integer("select.uid.by.username", user.getUsername());
		user.setUid(uid);
		dml("insert.team.2", new Object[] { uid });
	}

	public void insertUserContest(int uid, int cid, String gid) {
		if (gid == null)
			gid = "participant";
		dml("insert.user.contest.1", uid, cid, gid);
		dml("insert.user.stats.contest", uid, cid);
	}

	public void createTeams(Team team) {
		for (int i = 0; i < team.getTotal(); i++) {
			String username = buildteamUsername(team.getUsername(),
					team.getTotal(), i + 1);
			String nick = buildteamUsername(team.getNick(), team.getTotal(),
					i + 1);
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			Team add = new Team(username, md5.encodePassword(
					team.getPassword(), "ABC123XYZ789"), nick,
					team.getCountry(), team.getInstitution(), team.getLocale());
			add.setUpdate_nick(team.isUpdate_nick());
			insertTeam(add);
			if (team.getContest() > 0) {
				insertUserContest(add.getUid(), team.getContest(),
						team.getGroup());
			}
		}
	}

	private String buildteamUsername(String username, int total, int real) {
		if (total > 1) {
			int add = (total + "").length() - (real + "").length();
			for (int i = 0; i < add; i++) {
				username += "0";
			}
			username += real;
		}
		return username;
	}

	@Transactional(readOnly = true)
	public Language getProgrammingLanguageByUsername(String username) {

		return object("get.programming.language.by.username", Language.class,
				username);
	}

	public void updateChangeTime(int uid) {
		dml("update.change.time", uid);
	}

	public void insertUser(String user, String name, String lastname,
			String email) {
		dml("insert.user.x", user, email, user, name, lastname);
	}

	public void insertUserPrivate(String user) {
		String password = Utils.Cript(user);
		dml("insert.user.private", user, password, user, true, user);
	}

	@Transactional(readOnly = true)
	public boolean isIn(String user) {
		return bool("is.in", user);
	}

	@Transactional(readOnly = true)
	public boolean emailExist(String email) {
		return bool("email.exist", email);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> getMailValues(String username) {
		return map("mail.values", username);
	}

	@Transactional(readOnly = true)
	public boolean emailExistUpdate(String email, String username) {
		return bool("email.exist.update", email, username);
	}

	@Transactional(readOnly = true)
	public List<User> loadUsersButOne(String username) {
		return objects("load.users.but.one", User.class, username);
	}

	@Transactional(readOnly = true)
	public List<User> loadUsersForVirtualButOne(String username) {
		return objects("load.users.but.one.virtual", User.class, username);
	}

	@Transactional(readOnly = true)
	public boolean hasProfile(String username) {
		// si hay al menos un 1 en el perfil del usuario significa que no
		// tenemos que utilizar el algoritmo de cold start, sino el estandard
		return !objects("load.users.recommender.hasprofile", String.class,
				username).isEmpty();
	}

	@Transactional(readOnly = true)
	public UserProfile loadUserProfile(String username) {
		return object("load.users.recommender.loaduserprofile",
				UserProfile.class, username);
	}

	@Transactional(readOnly = true)
	public List<UserProfile> loadRelatedProfiles(UserProfile profile) {

		String tags = loadTags(profile.getUsername());
		return objects("load.users.recommender.loadrelatedprofile",
				UserProfile.class, profile.getUsername(), tags);
	}

	@Transactional(readOnly = true)
	public String loadTags(String username) {
		return defaultedString(StringUtils.EMPTY,
				"load.users.recommender.loadtags", username);
	}

	public void updateTags(String username, String tag) {
		dml("load.users.recommender.udpatetags", tag, username);
	}

	@Override
	public void checkUserStatus() {
		dml("update.users.status");
	}

	@Override
	public UserClassificationStats getUserClassifications(Integer uid) {
		List<Map<String, Object>> maps = maps("select.user.classif", uid);
		List<Map<String, Object>> probs = maps("select.prob.classif");

		List<Map<String, Object>> timeline = maps("select.user.timeline", uid,
				uid);
		return new UserClassificationStats(maps, probs, timeline);
	}

	@Override
	public UserClassificationStats getTotalClassifications() {
		List<Map<String, Object>> probs = maps("select.prob.classif");
		return new UserClassificationStats(probs);
	}
	
	//frankr addition start
	@Override
	@Transactional(readOnly = true)
	public List<ProblemComplexity> getPublicProblemsSolvedByUIdAndTagId(Integer uid, Integer idClassification) {
		String sqlKey = Config.getProperty("public.problems.solved.by.uid.and.tagid"); 
		List<ProblemComplexity> result = super.objects(sqlKey, ProblemComplexity.class, uid, idClassification);
		return result;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ProblemComplexity> getPublicProblemsTriedByUIdAndTagId(Integer uid, Integer idClassification) {
		String sqlKey = Config.getProperty("public.problems.tried.by.uid.and.tagid"); 
		List<ProblemComplexity> result = super.objects(sqlKey, ProblemComplexity.class, uid, idClassification);
		return result;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ProblemRichTitle> getPublicProblemsSolvedWithRichTitleByUid(Integer uid) {
		String sqlKey = Config.getProperty("public.problems.solved.by.uid"); 
		List<ProblemRichTitle> result = super.objects(sqlKey, ProblemRichTitle.class, uid);
		makeRichTitle(result);
		return result;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ProblemRichTitle> getPublicProblemsTriedWithRichTitleByUid(Integer uid) {
		String sqlKey = Config.getProperty("public.problems.tried.by.uid"); 
		List<ProblemRichTitle> result = super.objects(sqlKey, ProblemRichTitle.class, uid);
		makeRichTitle(result);
		return result;
	}
	
	@Transactional(readOnly = true)
	private void makeRichTitle(List<ProblemRichTitle> result){
		for (ProblemRichTitle prt : result){
			String sqlKey = Config.getProperty("get.problem.classifications");
			List<ProblemClassification> pcs = super.objects(sqlKey, ProblemClassification.class, prt.getPid());
			
			String richTitle = "";
			for (ProblemClassification pc : pcs){
				richTitle += " | " + pc.getName() + " " + Integer.valueOf(pc.getComplexity());
			}
			prt.setRichTitle(richTitle);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean existUsername(String username){
		String sqlKey = Config.getProperty("exist.username");
		boolean result = super.bool(sqlKey, username);
		return result;
	}
	//frankr addition end	


}
