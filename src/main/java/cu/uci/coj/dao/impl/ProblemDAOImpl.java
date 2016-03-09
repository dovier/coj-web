package cu.uci.coj.dao.impl;

import cu.uci.coj.config.Config;
import cu.uci.coj.controller.interfaces.IForIntegerMap;
import cu.uci.coj.dao.ContributionDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.mail.MailNotificationService;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Limits;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.ProblemClassification;
import cu.uci.coj.model.ProblemSource;
import cu.uci.coj.model.Roles;
import cu.uci.coj.model.Translation;
import cu.uci.coj.model.User;
import cu.uci.coj.model.UserProfile;
import cu.uci.coj.query.Order;
import cu.uci.coj.query.Query;
import cu.uci.coj.query.Where;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("problemDAO")
@Transactional
public class ProblemDAOImpl extends BaseDAOImpl implements ProblemDAO {

	@Resource
	private MailNotificationService mailNotificationService;
	@Resource
	ContributionDAO contributionDAO;

	public int getSourceLimitByPid(int pid,int lid) {
		return integer(0, "select max_source_code_lenght from problem_limits where problem_id=? and language_id = ?", pid,lid);
	}

	@Override
	public void deleteProblemSource(Integer idSource) {
		dml("delete.problemsources.problem", idSource);
		dml("delete.problemsource", idSource);
	}

	@Override
	public IPaginatedList<ProblemSource> getProblemSources(PagingOptions options) {
		return paginated("select.problemsources", ProblemSource.class, 20,
				options, options.getOffset(20));
	}

	@Override
	public List<ProblemSource> getProblemSources() {
		return objects("select.problemsources.all", ProblemSource.class);
	}

	@Override
	public void insertProblemSource(String name, String author) {
		dml("insert.problemsource", name, author);
	}

	@Override
	public void updateProblemSource(int idSource, String name, String author) {
		dml("update.problemsource", name, author, idSource);
	}

	public void checkProblemCreated() {
		List<Integer> pids = integers("problem.created.not.processed");
		for (Integer pid : pids) {

			mailNotificationService.sendProblemNotification(pid);
			dml("mark.problem.created", pid);
		}
	}

	public List<String> usersInProblem(int pid) {

		return strings("users.by.problem");
	}

	public String generateModelSolutionFrom(int problemId) {
		Map<String, Object> map = map("problem.accepted.solutions", problemId);
		if (map == null || map.isEmpty()) {
			return null;
		} else {
			String code = map.get("code").toString();
			try {

				String ext = map.get("ext").toString();
				File modelsol = new File(
						Config.getProperty("problems.directory")
								+ String.valueOf(problemId) + "/Model." + ext);
				FileUtils.writeStringToFile(modelsol, code);
			} catch (IOException ex) {
				return null;
			}
			return code;
		}
	}

	private void setProblemLanguage(String language, Problem problem) {
		problem.setUserLanguage(language);
	}

	private void setProblemLanguage(String language, List<Problem> problems) {
		for (Problem problem : problems) {
			problem.setUserLanguage(language);
		}
	}

	@Transactional(readOnly = true)
	public List<Problem> searchProblems(String language, String pattern,
			boolean admin, String username) {
		List<Problem> problems = null;
		if (pattern != null && pattern.length() > 0) {

			pattern = "%" + pattern + "%";
			if (admin) {
				String sql = replaceSql("search.problems.admin", "<pattern>",
						pattern);
				problems = objects(sql, Problem.class);

			} else {
				String sql = replaceSql("search.problems", "<pattern>", pattern);
				problems = objects(sql, Problem.class);
			}
		}

		setProblemLanguage(language, problems);
		return problems;
	}

	@Transactional(readOnly = true)
	public IPaginatedList<Problem> findAllProblems(String language,
			String pattern, int found, PagingOptions options, String username,
			int uid, Integer filterby, Integer idClassification,
			Integer complexity) {

		int top = options.getOffset(50);
		String orderby = options.getSort();
		String inv = options.getDirection();
		Map<?, ?> solved = null;
		Map<?, ?> tried = null;
		Map<?, ?> favorite = null;

		if (username != null) {
			solved = problemsSolvedByUser(uid);
			tried = problemsTriedByUser(username, uid);
			favorite = userFavorite(uid);
		}

		String sql = null;

		List<Object> list = new LinkedList<Object>();

		if (pattern != null) {
			sql = replaceSql("find.all.problems", "pattern", "%" + pattern
					+ "%");
			list.add("%" + pattern + "%");
			list.add("%" + pattern + "%");
			list.add("%" + pattern + "%");
			list.add("%" + pattern + "%");
			list.add("%" + pattern + "%");
			list.add("%" + pattern + "%");

			if (NumberUtils.isNumber(pattern))
				list.add(NumberUtils.toInt(pattern));
			else
				list.add(0);
		} else {
			sql = getSql("find.all.problems.no.pattern");
		}

		if (orderby != null) {
			sql = sql.replace("<orderby>",
					getSql(inv.equals("asc") ? "order.all.problems.asc"
							: "order.all.problems.desc") + ",pid");
			sql = sql.replace("<orderby>", orderby);
		} else {
			sql = sql.replace("<orderby>", getSql("order.all.problem.pid"));
		}

		if (idClassification != -1 || complexity != -1) {
			sql = sql
					.replace("<and>",
							" AND problem.pid IN (SELECT pid FROM problem_classification WHERE true<and>");

			if (idClassification != -1) {
				sql = sql.replace("<and>", " AND id_classification = ?<and>");
				list.add(idClassification);
			}

			if (complexity != -1) {
				sql = sql.replace("<and>", " AND complexity = ?<and>");
				list.add(complexity);
			}
			sql = sql.replace("<and>", ")<and>");
		}

		if (username != null) {
			switch (filterby) {
			case 1:
				sql = sql.replace("<and>", getSql("find.all.problems.1"));
				list.add(uid);
				list.add(top);
				break;
			case 2:
				sql = sql.replace("<and>", getSql("find.all.problems.2"));
				list.add(username);
				list.add(uid);
				list.add(top);
				break;
			case 3:
				sql = sql.replace("<and>", getSql("find.all.problems.3"));
				list.add(username);
				list.add(top);
				break;
			case 4:
				sql = sql.replace("<and>", getSql("find.all.problems.4"));
				list.add(uid);
				list.add(top);
				break;
			default:
				list.add(top);
				break;
			}
		} else {
			list.add(top);
		}

		sql = sql.replace("<and>", "");
		List<Problem> problems = objects(sql, Problem.class, list.toArray());
		for (Iterator<Problem> it = problems.iterator(); it.hasNext();) {
			Problem problem = it.next();
			problem.setPoints((double) Integer.valueOf(Config
					.getProperty("formula.value"))
					/ (double) (40 + problem.getAccu()));
			if (solved != null) {
				if (solved.containsKey(problem.getPid())) {
					problem.setSolved(true);
				} else if (tried.containsKey(problem.getPid())) {
					problem.setUnsolved(true);
				}
				problem.setFavorite(favorite.containsKey(problem.getPid()));
			}
		}

		setProblemLanguage(language, problems);
		return getPaginatedList(options, problems, 50, found);
	}

	@Override
	public IPaginatedList<Problem> findAllProblemsWithoutClassification(
			String language, PagingOptions options) {
		int found = countProblemWithoutClassifications();
		Map<?, ?> solved = null;
		Map<?, ?> tried = null;
		Map<?, ?> favorite = null;

		String sql = null;

		List<Object> list = new LinkedList<Object>();

		sql = getSql("find.all.problems.no.pattern");

		if (options.getSort() != null) {
			sql = sql.replace("<orderby>", getSql(options.getDirection()
					.equals("asc") ? "order.all.problems.asc"
					: "order.all.problems.desc"));
			sql = sql.replace("<orderby>", options.getSort());
		} else {
			sql = sql.replace("<orderby>", getSql("order.all.problem.pid"));
		}

		list.add(options.getOffset(50));
		sql = sql.replace("<and>", getSql("find.all.problems.5"));
		List<Problem> problems = objects(sql, Problem.class, list.toArray());
		for (Iterator<Problem> it = problems.iterator(); it.hasNext();) {
			Problem problem = it.next();
			problem.setPoints((double) Integer.valueOf(Config
					.getProperty("formula.value"))
					/ (double) (40 + problem.getAccu()));
			if (solved != null) {
				if (solved.containsKey(problem.getPid())) {
					problem.setSolved(true);
				} else if (tried.containsKey(problem.getPid())) {
					problem.setUnsolved(true);
				}
				problem.setFavorite(favorite.containsKey(problem.getPid()));
			}
		}

		setProblemLanguage(language, problems);
		return getPaginatedList(options, problems, 50, found);
	}

	@Transactional(readOnly = true)
	public IPaginatedList<Problem> findAllProblems(String language,
			String pattern, int found, PagingOptions options, String username,
			int uid, int filterby) {

		int top = options.getOffset(50);
		Map<?, ?> solved = null;
		Map<?, ?> tried = null;
		Map<?, ?> favorite = null;

		if (username != null) {
			solved = problemsSolvedByUser(uid);
			tried = problemsTriedByUser(username, uid);
			favorite = userFavorite(uid);
		}

		String sql = null;

		List<Object> list = new LinkedList<Object>();

		if (pattern != null) {
			sql = replaceSql("find.all.problems", "pattern", "%" + pattern
					+ "%");
			list.add("%" + pattern + "%");
			list.add("%" + pattern + "%");
		} else {
			sql = getSql("find.all.problems.no.pattern");
		}

		if (options.getSort() != null) {
			sql = sql.replace("<orderby>", getSql(options.getDirection()
					.equals("asc") ? "order.all.problems.asc"
					: "order.all.problems.desc"));
			sql = sql.replace("<orderby>", options.getSort());
		} else {
			sql = sql.replace("<orderby>", getSql("order.all.problem.pid"));
		}

		if (username != null) {
			switch (filterby) {
			case 1:
				sql = sql.replace("<and>", getSql("find.all.problems.1"));
				list.add(uid);
				list.add(top);
				break;
			case 2:
				sql = sql.replace("<and>", getSql("find.all.problems.2"));
				list.add(username);
				list.add(uid);
				list.add(top);
				break;
			case 3:
				sql = sql.replace("<and>", getSql("find.all.problems.3"));
				list.add(username);
				list.add(top);
				break;
			case 4:
				sql = sql.replace("<and>", getSql("find.all.problems.4"));
				list.add(uid);
				list.add(top);
				break;
			default:
				list.add(top);
				break;
			}
		} else {
			list.add(top);
		}
		sql = sql.replace("<and>", "");
		List<Problem> problems = objects(sql, Problem.class, list.toArray());
		for (Iterator<Problem> it = problems.iterator(); it.hasNext();) {
			Problem problem = it.next();
			problem.setPoints((double) Integer.valueOf(Config
					.getProperty("formula.value"))
					/ (double) (40 + problem.getAccu()));
			if (solved != null) {
				if (solved.containsKey(problem.getPid())) {
					problem.setSolved(true);
				} else if (tried.containsKey(problem.getPid())) {
					problem.setUnsolved(true);
				}
				problem.setFavorite(favorite.containsKey(problem.getPid()));
			}
		}

		setProblemLanguage(language, problems);
		return getPaginatedList(options, problems, 50, found);
	}

	@Override
	public int countProblemWithoutClassifications() {
		//Se debe mejorar la consulta para que devuelva un count (int)
		List<Problem> problems = objects("count.problems.13", Problem.class);
		return problems.size();
		/*return integer("count.problems.12");*/
	}

	@Transactional(readOnly = true)
	public int countProblem(String pattern, Integer filterby, String username,
			Integer idClassification, Integer complexity) {
		if (pattern != null) {
			List<Object> list = new LinkedList<Object>();
			String sql = getSql("count.problems.1");
			list.add("%" + pattern + "%");
			list.add("%" + pattern + "%");
			list.add("%" + pattern + "%");
			list.add("%" + pattern + "%");
			list.add("%" + pattern + "%");
			list.add("%" + pattern + "%");

			if (NumberUtils.isNumber(pattern))
				list.add(NumberUtils.toInt(pattern));
			else
				list.add(0);
			if (username != null) {
				switch (filterby) {
				case 1:
					sql += " " + getSql("count.problems.2") + " ";
					list.add(username);
					break;
				case 2:
					sql += " " + getSql("count.problems.3") + " ";
					list.add(username);
					list.add(username);
					break;
				case 3:
					sql += " " + getSql("count.problems.4") + " ";
					list.add(username);
					break;
				case 4:
					sql += " " + getSql("count.problems.5") + " ";
					list.add(username);
					break;
				}
			}
			if (idClassification != -1 || complexity != -1) {
				sql += " "
						+ getSql("and pid in (SELECT pid FROM problem_classification WHERE true <CLASSIFICATIONWHERE>");
				if (idClassification != -1) {
					sql = sql.replace("<CLASSIFICATIONWHERE>",
							" AND id_classification = ? <CLASSIFICATIONWHERE>");
					list.add(idClassification);
				}
				if (complexity != -1) {
					sql = sql.replace("<CLASSIFICATIONWHERE>",
							" AND complexity = ? <CLASSIFICATIONWHERE>");
					list.add(complexity);
				}
				sql = sql.replace("<CLASSIFICATIONWHERE>", ")");
			}

			return integer(sql, list.toArray());
		} else {
			String sql = getSql("count.problems.6");
			List<Object> list = new LinkedList<Object>();
			if (username != null) {
				switch (filterby) {
				case 1:
					sql += " " + getSql("count.problems.7") + " ";
					list.add(username);
					break;
				case 2:
					sql += " " + getSql("count.problems.8") + " ";
					list.add(username);
					list.add(username);
					break;
				case 3:
					sql += " " + getSql("count.problems.9") + " ";
					list.add(username);
					break;
				case 4:
					sql += " " + getSql("count.problems.10") + " ";
					list.add(username);
					break;
				}
			}
			if (idClassification != -1 || complexity != -1) {
				sql += " " + getSql("count.problems.11");
				if (idClassification != -1) {
					sql += " AND id_classification = ?";
					list.add(idClassification);
				}
				if (complexity != -1) {
					sql += " AND complexity = ?";
					list.add(complexity);
				}
				sql += ")";
			}
			return integer(sql, list.toArray());
		}
	}

	@Transactional(readOnly = true)
	public int countProblem(String pattern, int filterby, String username) {
		if (pattern != null) {
			List<Object> list = new LinkedList<Object>();
			String sql = getSql("count.problems.1");
			list.add("%" + pattern + "%");
			list.add("%" + pattern + "%");
			if (username != null) {
				switch (filterby) {
				case 1:
					sql += " " + getSql("count.problems.2") + " ";
					list.add(username);
					break;
				case 2:
					sql += " " + getSql("count.problems.3") + " ";
					list.add(username);
					list.add(username);
					break;
				case 3:
					sql += " " + getSql("count.problems.4") + " ";
					list.add(username);
					break;
				case 4:
					sql += " " + getSql("count.problems.5") + " ";
					list.add(username);
					break;
				}
			}
			return integer(sql, list.toArray());
		} else {
			String sql = getSql("count.problems.6");
			List<Object> list = new LinkedList<Object>();
			if (username != null) {
				switch (filterby) {
				case 1:
					sql += " " + getSql("count.problems.7") + " ";
					list.add(username);
					break;
				case 2:
					sql += " " + getSql("count.problems.8") + " ";
					list.add(username);
					list.add(username);
					break;
				case 3:
					sql += " " + getSql("count.problems.9") + " ";
					list.add(username);
					break;
				case 4:
					sql += " " + getSql("count.problems.10") + " ";
					list.add(username);
					break;
				}
			}
			return integer(sql, list.toArray());
		}
	}

	@Transactional(readOnly = true)
	public int countProblemAdmin(Integer uid, String role, String pattern) {
		if (!StringUtils.isEmpty(pattern)) {
			int pid = 0;
			if (NumberUtils.isNumber(pattern))
				pid = NumberUtils.toInt(pattern);
			if (role.equals(Roles.ROLE_ADMIN)
					|| role.equals(Roles.ROLE_SUPER_PSETTER))
				return integer("count.problem.admin.all", "%" + pattern + "%",
						pid);
			return integer("count.problem.admin", uid, "%" + pattern + "%", pid);
		}

		if (role.equals(Roles.ROLE_ADMIN)
				|| role.equals(Roles.ROLE_SUPER_PSETTER))
			return integer("count.problem.all");
		return integer("count.problem", uid);
	}

	@Transactional(readOnly = true)
	public IPaginatedList<Problem> loadProblemsAdmin(int found, Integer uid,
			String role, String language, String pattern, PagingOptions options) {
		List<Problem> problems = new LinkedList<Problem>();

		if (pattern == null) {
			if (role.equals(Roles.ROLE_ADMIN)
					|| role.equals(Roles.ROLE_SUPER_PSETTER))
				problems = objects("load.problems.admin.all", Problem.class,
						options.getOffset(50));
			else
				problems = objects("load.problems.admin", Problem.class, uid,
						options.getOffset(50));
		} else {
			int pid = 0;
			if (NumberUtils.isNumber(pattern))
				pid = NumberUtils.toInt(pattern);
			if (role.equals(Roles.ROLE_ADMIN)
					|| role.equals(Roles.ROLE_SUPER_PSETTER))
				problems = objects("load.problems.admin.pattern.all",
						Problem.class, "%" + pattern + "%", pid,
						options.getOffset(50));
			else
				problems = objects("load.problems.admin.pattern",
						Problem.class, uid, "%" + pattern + "%", pid,
						options.getOffset(50));
		}

		for (Problem problem : problems) {
			problem.setUserLanguage(language);
		}
		return getPaginatedList(options, problems, 50, found);
	}

	@Transactional(readOnly = true)
	private Map<Integer, Integer> problemsSolvedByUser(String username) {
		Map<Integer, Integer> solved = new HashMap<Integer, Integer>();
		List<Integer> pids = integers("problems.solved.by.user", username);
		for (Integer pid : pids) {
			solved.put(pid, pid);
		}
		return solved;
	}

	@Transactional(readOnly = true)
	private Map<?, ?> userFavorite(int uid) {
		IForIntegerMap mymap = new IForIntegerMap();
		jdbcTemplate.query(getSql("problems.favorite.byuser"),
				new Object[] { uid }, mymap);
		return mymap.getMymap();
	}

	@Transactional(readOnly = true)
	private Map<?, ?> problemsSolvedByUser(int uid) {
		IForIntegerMap mymap = new IForIntegerMap();
		jdbcTemplate.query(getSql("problems.solved.byuser"),
				new Object[] { uid }, mymap);
		return mymap.getMymap();
	}

	@Transactional(readOnly = true)
	private Map<Integer, Integer> problemsSolvedByUserInContest(
			String username, Contest contest) {
		Map<Integer, Integer> solved = new HashMap<Integer, Integer>();
		List<Integer> pids;
		if (contest.isFrozen()) {
			String sql = replaceSql("solved.by.user.cont.frozen", "<frtime>",
					String.valueOf(contest.getFrtime()));
			pids = integers(sql, username, contest.getCid(), contest.getCid());
		} else {
			pids = integers("solved.by.user.cont", username, contest.getCid());
		}
		if (pids != null) {
			for (Integer pid : pids) {
				solved.put(pid, pid);
			}
		}
		return solved;
	}

	@Transactional(readOnly = true)
	private Map<Integer, Integer> problemsSolvedByUserInVirtualContest(
			String username, Contest contest) {
		Map<Integer, Integer> solved = new HashMap<Integer, Integer>();

		List<Integer> pids = null;
		if (contest.isFrozen()) {
			String sql = replaceSql("problem.solved.by.user.vcont.frozen",
					"<frtime>", String.valueOf(contest.getFrtime()));

			pids = integers(sql, username, contest.getCid(), contest.getCid());
		} else {
			pids = integers("problem.solved.by.user.vcont", username,
					contest.getCid());
		}

		if (pids != null) {
			for (Integer pid : pids) {
				solved.put(pid, pid);

			}
		}

		return solved;
	}

	@Transactional(readOnly = true)
	private Map<Integer, Integer> problemsTriedByUser(String username) {
		Map<Integer, Integer> tryied = new HashMap<Integer, Integer>();
		List<Integer> pids = integers("problems.tried.by.user", username,
				username);
		for (Integer pid : pids) {
			tryied.put(pid, pid);
		}
		return tryied;
	}

	@Transactional(readOnly = true)
	private Map<?, ?> problemsTriedByUser(String username, int uid) {
		IForIntegerMap mymap = new IForIntegerMap();
		jdbcTemplate.query(getSql("problems.tried.byuser"), new Object[] {
				username, uid }, mymap);
		return mymap.getMymap();
	}

	@Transactional(readOnly = true)
	private Map<Integer, Integer> problemsTriedByUserInContest(String username,
			Contest contest) {
		Map<Integer, Integer> tryied = new HashMap<Integer, Integer>();
		List<Integer> pids;
		String sql = null;

		if (contest.isFrozen()) {
			sql = replaceSql("problem.tried.user.in.contest.1", "<frtime>",
					String.valueOf(contest.getFrtime()));
			pids = integers(sql, username, contest.getCid(), contest.getCid(),
					username, contest.getCid(), contest.getCid());
		} else {
			pids = integers("problem.tried.user.in.contest", username,
					contest.getCid(), username, contest.getCid());
		}

		for (Integer pid : pids) {
			tryied.put(pid, pid);
		}
		return tryied;
	}

	@Transactional(readOnly = true)
	private Map<Integer, Integer> problemsTriedByUserInVirtualContest(
			String username, Contest contest) {
		Map<Integer, Integer> tryied = new HashMap<Integer, Integer>();

		List<Integer> pids = null;
		String sql = "problems.tried.vcont";

		if (contest.isFrozen()) {

			sql += replaceSql("problems.tried.vcont.1", "<frtime>",
					String.valueOf(contest.getFrtime()));
			sql += getSql("problems.tried.vcont.2");
			sql += replaceSql("problems.tried.vcont.1", "<frtime>",
					String.valueOf(contest.getFrtime()));
			sql += getSql("problems.tried.vcont.3");

			pids = integers(sql, username, contest.getCid(), contest.getCid(),
					username, contest.getCid(), contest.getCid());
		} else {
			sql += getSql("problems.tried.vcont.2");
			sql += getSql("problems.tried.vcont.3");
			pids = integers(sql, username, contest.getCid(), username,
					contest.getCid());
		}

		if (pids != null) {
			for (Integer pid : pids) {
				tryied.put(pid, pid);
			}
		}
		return tryied;
	}

	@Transactional(readOnly = true)
	public Problem getProblemByCode(String language, Integer pid,
			boolean isAdminOrPsetter) {
		Problem problem = object(isAdminOrPsetter ? "select.problem.bycode.1"
				: "select.problem.bycode.2", Problem.class, pid);
		problem.setPoints((double) Integer.valueOf(Config
				.getProperty("formula.value"))
				/ (double) (40 + problem.getAccu()));
		setProblemLanguage(language, problem);
		return problem;
	}

	@Transactional(readOnly = true)
	public void fillProblemLanguages(Problem problem) {
		List<Language> languages = objects(getSql("select.problem.languages"),
				Language.class, problem.getPid());
		problem.setLanguages(languages);
	}

	@Transactional(readOnly = true)
	public void fillProblemSetters(Problem problem) {
		List<User> psetters = objects(getSql("select.current.psetters.pid"),
				User.class, problem.getPid());
		problem.setPsetters(psetters);
	}

	@Transactional(readOnly = true)
	public boolean hasLanguageAvailable(Integer pid, int lid) {
		return bool("language.available", pid, lid);
	}

	@Transactional(readOnly = true)
	public Problem getProblemSubmitDataByAbb(Integer pid, int lid) {
		Problem problem = object("problem.submit.data.by.pid", Problem.class,
				pid, lid);
		return problem;
	}

	@Transactional(readOnly = true)
	public boolean exists(Integer pid) {
		return bool("exist.problem", pid);
	}

	@Transactional(readOnly = true)
	public boolean existProblemByTitle(String title) {
		return bool("exist.problem.by.title", title);
	}

	@Transactional(readOnly = true)
	public boolean existProblemTitleOffPid(Integer pid, String title) {
		return bool("problem.title.off.pid", title, pid);
	}

	@Transactional(readOnly = true)
	public boolean existProblemAbbOffPid(int pid) {
		return bool("problem.off.pid", pid);
	}

	@Transactional(readOnly = true)
	public boolean isEnabled(Integer pid) {
		return bool("enabled.by.pid", pid);
	}

	@Transactional(readOnly = true)
	public boolean isDisable24h(Integer pid) {
		return bool("disabled.24h.pid", pid);
	}

	@Transactional(readOnly = true)
	public int getPidByTitle(String title) {
		return integer("submit.pid.title", title);
	}

	@Override
	public ProblemSource getProblemSourceById(Integer pid) {
		return object("select.problemsource.id", ProblemSource.class, pid);
	}

	@Transactional(readOnly = true)
	public List<Language> getEnabledLanguagesByProblem(Integer pid) {

		List<Language> res;
		if (pid != null) {
			res = objects("enabled.language.pid", Language.class, pid);
		} else {
			res = objects("enabled.language", Language.class);
		}
		for (Language lang : res) {
			lang.setDescripcion(lang.getLanguage() + " ("
					+ lang.getDescripcion() + ")");
		}
		return res;
	}

	@Transactional(readOnly = true)
	public int countProblemContest(int cid) {
		return integer("count.prob.cont", cid);
	}

	private double formulaFree(double points, double accu) {
		return points / accu;
	}

	public String creatorUsernameByPid(int pid) {
		return string("select.creator.by.pid", pid);
	}

	@Transactional(readOnly = true)
	public IPaginatedList<Problem> getContestProblems(int found,
			String language, String username, Contest contest,
			PagingOptions options) {
		Map<Integer, Integer> solved = null, tryied = null;
		if (username != null) {
			solved = problemsSolvedByUserInContest(username, contest);
			tryied = problemsTriedByUserInContest(username, contest);
		}
		int curr = 1;
		if (username != null) {
			try {
				curr = getCurrentLevel(
						integer("select.uid.by.username", username),
						contest.getCid());
			} catch (Exception e) {
			}
		}
		List<Problem> probs = objects("contest.problems", Problem.class,
				contest.getCid(), options.getOffset(50));
		for (int i = 0; i < probs.size(); i++) {
			if (contest.getStyle() == 3) {
				probs.get(i).setPoints(
						formulaFree(contest.getPpoints(), probs.get(i)
								.getAccu() + 1));
			}
			if (solved != null && solved.containsKey(probs.get(i).getPid())) {
				probs.get(i).setSolved(true);
			} else if (tryied != null
					&& tryied.containsKey(probs.get(i).getPid())) {
				probs.get(i).setUnsolved(true);
			}
			probs.get(i).setLetter(i);
			if (contest.getStyle() != 4) {
				probs.get(i).setSee(true);
			} else {

				if (probs.get(i).getLevel() == 1
						|| (username != null && probs.get(i).getLevel() <= curr)) {
					probs.get(i).setSee(true);
				}
			}

		}
		setProblemLanguage(language, probs);
		return getPaginatedList(options, probs, 50, found);
	}

	public List<Problem> getAllContestProblems(int cid) {
		return objects("select.contest.problem.letters", Problem.class, cid);
	}

	@Transactional(readOnly = true)
	public IPaginatedList<Problem> getVirtualContestProblems(int found,
			String language, String username, Contest contest,
			PagingOptions options) {

		List<Problem> probs = objects("load.virtual.contest.problems",
				Problem.class, contest.getVid(), options.getOffset(50));
		for (int i = 0; i < probs.size(); i++) {
			probs.get(i).setLetter(i);
			if (contest.getStyle() != 4) {
				probs.get(i).setSee(true);
			}
		}

		setProblemLanguage(language, probs);
		return getPaginatedList(options, probs, 20, found);
	}

	@Transactional(readOnly = true)
	public int getCurrentLevel(int uid, int cid) {
		return integer(1, "current.level", uid, cid);
	}

	@Transactional(readOnly = true)
	public int getCurrentLevel(String username) {
		return integer("current.level.username", username);
	}

	@Transactional(readOnly = true)
	public Problem getProblemContest(String language, Integer pid,
			Contest contest) {
		Problem problem = object("load.virtual.contest.problem", Problem.class,
				pid, contest.getCid());
		setProblemLanguage(language, problem);
		return problem;
	}

	@Override
	public Problem getContestProblem(String language, Integer pid,
			Contest contest) {
		Problem problem = object("load.contest.problem", Problem.class, pid,
				contest.getCid());
		setProblemLanguage(language, problem);
		return problem;
	}

	@Transactional(readOnly = true)
	public List<Problem> getContestProblems(String language, int cid) {
		List<Problem> cprobs = objects("problems.contest", Problem.class, cid);
		for (int i = 0; i < cprobs.size(); i++) {
			cprobs.get(i).setLetter(i);
		}
		setProblemLanguage(language, cprobs);
		return cprobs;
	}

	@Override
	public List<Problem> getVirtualContestProblems(String language,
			String username) {
		List<Problem> cprobs = objects("virtual.contest.problems",
				Problem.class, username);
		for (int i = 0; i < cprobs.size(); i++) {
			cprobs.get(i).setLetter(i);
		}
		setProblemLanguage(language, cprobs);
		return cprobs;
	}

	@Transactional(readOnly = true)
	public List<Problem> getProblemsOffContest(String language, int cid) {
		List<Problem> problems = objects("problems.off.contest", Problem.class,
				cid);
		setProblemLanguage(language, problems);
		return problems;
	}

	@Transactional(readOnly = true)
	public List<Problem> getProblemsOffContestVirtual(String language) {
		List<Problem> problems = objects("problems.virtual.enabled",
				Problem.class);
		setProblemLanguage(language, problems);
		return problems;
	}

	@Override
	public void clearProblemsContest(int cid) {
		dml("clear.problem.contest.1", cid);
		dml("clear.problem.contest.2", cid);
	}

	@Transactional(readOnly = true)
	public Problem getProblemByPid(String language, int pid) {
		Problem problem = object("problem.by.pid", Problem.class, pid);
		setProblemLanguage(language, problem);
		return problem;
	}

	@Override
	public void addProblem(Problem problem) {
		dml("add.prob", problem.getForumLink(), problem.getTitle(),
				problem.getTitleEs(), problem.getTitlePt(),
				problem.getDescription(), problem.getDescriptionEs(),
				problem.getDescriptionPt(), problem.getInput(),
				problem.getInputEs(), problem.getInputPt(),
				problem.getOutput(), problem.getOutputEs(),
				problem.getOutputPt(), problem.getInputex(),
				problem.getOutputex(), problem.getId_source(),
				problem.getComments(), problem.getCommentsEs(),
				problem.getCommentsPt(), problem.getTime(),
				problem.getMemory(), problem.getFontsize(),
				problem.getContest(), problem.getUid(), problem.isEnabled(),
				problem.getCasetimelimit(), problem.isMultidata(),
				problem.isSpecial_judge());
	}

	@Override
	public void insertLimit(Limits limit) {
		dml("insert.problem.limit", limit.getProblemId(),
				limit.getLanguageId(), limit.getMaxMemory(),
				limit.getMaxCaseExecutionTime(),
				limit.getMaxTotalExecutionTime(),
				limit.getMaxSourceCodeLenght());
	}

        @Override
        public void clearLimits(int problemId) {
            dml("clear.problem.limits", problemId);
        }

	@Override
	public void insertProblemLanguage(int pid, int lid) {
		dml("insert.prob.lang", pid, lid);
	}

	@Override
	public void insertProblemStats(int pid) {
		dml("insert.prob.stats", pid);
	}

	@Override
	public void clearProgrammingLanguages(int pid) {
		dml("clear.prog.lang.pid", pid);
	}

	@Override
	public void clearPsetters(int pid) {
		dml("clear.psetters.pid", pid);
	}

	@Override
	public void updateProblemI18N(Problem problem) {
		dml("update.problem.i18n", problem.getTitle(), problem.getTitleEs(),
				problem.getTitlePt(), problem.getDescription(),
				problem.getDescriptionEs(), problem.getDescriptionPt(),
				problem.getInput(), problem.getInputEs(), problem.getInputPt(),
				problem.getOutput(), problem.getOutputEs(),
				problem.getOutputPt(), problem.getComments(),
				problem.getCommentsEs(), problem.getCommentsPt(),
				problem.getPid());
	}

	@Override
	public void updateProblem(Problem problem) {
		if (problem.getUid() != 0)
			dml("update.problem", problem.getForumLink(), problem.getTitle(),
					problem.getDescription(), problem.getInput(),
					problem.getOutput(), problem.getInputex(),
					problem.getOutputex(), problem.getId_source(),
					problem.getComments(), problem.getTime(),
					problem.getMemory(), problem.getFontsize(),
					problem.getContest(), problem.getPid(),
					problem.isEnabled(), problem.getCasetimelimit(),
					problem.isMultidata(), problem.isSpecial_judge(),
					problem.isDisable_24h(), problem.getUid(), problem.getPid());
		else
			dml("update.problem.no.uid", problem.getForumLink(),
					problem.getTitle(), problem.getDescription(),
					problem.getInput(), problem.getOutput(),
					problem.getInputex(), problem.getOutputex(),
					problem.getId_source(), problem.getComments(),
					problem.getTime(), problem.getMemory(),
					problem.getFontsize(), problem.getContest(),
					problem.getPid(), problem.isEnabled(),
					problem.getCasetimelimit(), problem.isMultidata(),
					problem.isSpecial_judge(), problem.isDisable_24h(),
					problem.getPid());
	}

	@Transactional(readOnly = true)
	public Problem getStatistics(String language, Integer pid) {
		Problem problem = object("statistics.by.pid", Problem.class, pid);
		setProblemLanguage(language, problem);
		return problem;
	}

	@Transactional(readOnly = true)
	public boolean isLocked(int uid, int pid) {
		return bool("problem.locked", uid, pid);
	}

	@Override
	public void deleteProblemClassification(int pid, int classificationid) {
		dml("delete.problem.classification", pid, classificationid);
	}

	@Override
	public void insertClassification(String nameClassification) {
		dml("insert.classification", nameClassification);
	}

	@Override
	public void insertProblemClassification(int pid, int classificationid) {
		dml("insert.problem.classification", pid, classificationid);
	}

	@Transactional(readOnly = true)
	public List<ProblemClassification> getProblemClassifications(int pid) {
		return objects("select.problem.classifications",
				ProblemClassification.class, pid);
	}

	@Transactional(readOnly = true)
	public IPaginatedList<ProblemClassification> getClassifications(
			PagingOptions options) {
		List<ProblemClassification> classifications = objects(
				"select.classifications", ProblemClassification.class);
		return getPaginatedList(options, classifications, 20,
				classifications.size());
	}

	public List<ProblemClassification> getClassifications() {
		return objects("select.classifications", ProblemClassification.class);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Problem> suggestedProblems(String language,
			List<UserProfile> profiles, UserProfile profile, String pattern,
			int top, String orderby, String inv, Integer idClassification,
			Integer complexity) {
		StringBuffer buffer = new StringBuffer();
		for (UserProfile userProfile : profiles) {
			buffer.append(userProfile.getUsername()).append(",");
		}
		// eliminar la ultima coma
		if (buffer.length() == 0) {
			return new ArrayList<Problem>();
		}
		buffer.deleteCharAt(buffer.length() - 1);

		int PROBLEM_SUGGESTION_QTY = 10; // this is configuration

		// //////
		String sql = null;

		List<Object> list = new LinkedList<Object>();
		list.add(profile.getUsername());
		list.add(buffer.toString());
		list.add(PROBLEM_SUGGESTION_QTY);
		if (pattern != null) {
			sql = replaceSql(
					"load.users.recommender.suggestedproblems.filtered",
					"pattern", "%" + pattern + "%");
			list.add("%" + pattern + "%");
			list.add("%" + pattern + "%");
		} else {
			sql = getSql("load.users.recommender.suggestedproblems.filtered.no.pattern");
		}

		if (orderby != null) {
			sql = sql.replace("<orderby>",
					getSql(inv.equals("asc") ? "order.all.problems.asc"
							: "order.all.problems.desc"));
			sql = sql.replace("<orderby>", orderby);
		} else {
			sql = sql.replace("<orderby>", "");
		}

		if (idClassification != -1 || complexity != -1) {
			sql = sql
					.replace("<and>",
							" AND problem.pid IN (SELECT pid FROM problem_classification WHERE true<and>");

			if (idClassification != -1) {
				sql = sql.replace("<and>", " AND id_classification = ?<and>");
				list.add(idClassification);
			}

			if (complexity != -1) {
				sql = sql.replace("<and>", " AND complexity = ?<and>");
				list.add(complexity);
			}
			sql = sql.replace("<and>", ")<and>");
		}
		sql = sql.replace("<and>", "");
		list.add(PROBLEM_SUGGESTION_QTY);
		list.add(top);
		// /

		Map<?, ?> solved = problemsSolvedByUser(profile.getUid());
		Map<?, ?> tried = problemsTriedByUser(profile.getUsername(),
				profile.getUid());
		Map<?, ?> favorite = userFavorite(profile.getUid());

		List<Problem> problems = objects(sql, Problem.class, list.toArray());
		for (Iterator<Problem> it = problems.iterator(); it.hasNext();) {
			Problem problem = it.next();
			problem.setPoints((double) Integer.valueOf(Config
					.getProperty("formula.value"))
					/ (double) (40 + problem.getAccu()));
			if (solved != null) {
				if (solved.containsKey(problem.getPid())) {
					problem.setSolved(true);
				} else if (tried.containsKey(problem.getPid())) {
					problem.setUnsolved(true);
				}
				problem.setFavorite(favorite.containsKey(problem.getPid()));
			}
		}

		setProblemLanguage(language, problems);
		return problems;
	}

	@Transactional(readOnly = true)
	public List<Problem> suggestedProblemsColdStart(String language,
			String username, String pattern, int top, String orderby,
			String inv, int uid, Integer idClassification, Integer complexity) {

		int PROBLEM_SUGGESTION_QTY = 10; // this is configuration

		// //////
		String sql = null;

		List<Object> list = new LinkedList<Object>();
		list.add(username);
		list.add(PROBLEM_SUGGESTION_QTY);

		if (pattern != null) {
			sql = replaceSql(
					"load.users.recommender.suggestedproblemscoldstart.filtered",
					"pattern", "%" + pattern + "%");
			list.add("%" + pattern + "%");
			list.add("%" + pattern + "%");
		} else {
			sql = getSql("load.users.recommender.suggestedproblemscoldstart.filtered.no.pattern");
		}

		if (orderby != null) {
			sql = sql.replace("<orderby>",
					getSql(inv.equals("asc") ? "order.all.problems.asc"
							: "order.all.problems.desc"));
			sql = sql.replace("<orderby>", orderby);
		} else {
			sql = sql.replace("<orderby>", "");
		}

		if (idClassification != -1 || complexity != -1) {
			sql = sql
					.replace("<and>",
							" AND problem.pid IN (SELECT pid FROM problem_classification WHERE true<and>");

			if (idClassification != -1) {
				sql = sql.replace("<and>", " AND id_classification = ?<and>");
				list.add(idClassification);
			}

			if (complexity != -1) {
				sql = sql.replace("<and>", " AND complexity = ?<and>");
				list.add(complexity);
			}
			sql = sql.replace("<and>", ")<and>");
		}
		sql = sql.replace("<and>", "");
		list.add(PROBLEM_SUGGESTION_QTY);
		list.add(top);
		// /

		Map<?, ?> solved = problemsSolvedByUser(uid);
		Map<?, ?> tried = problemsTriedByUser(username, uid);
		Map<?, ?> favorite = userFavorite(uid);

		List<Problem> problems = objects(sql, Problem.class, list.toArray());
		for (Iterator<Problem> it = problems.iterator(); it.hasNext();) {
			Problem problem = it.next();
			problem.setPoints((double) Integer.valueOf(Config
					.getProperty("formula.value"))
					/ (double) (40 + problem.getAccu()));
			if (solved != null) {
				if (solved.containsKey(problem.getPid())) {
					problem.setSolved(true);
				} else if (tried.containsKey(problem.getPid())) {
					problem.setUnsolved(true);
				}
				problem.setFavorite(favorite.containsKey(problem.getPid()));
			}
		}

		setProblemLanguage(language, problems);
		return problems;
	}

	@Transactional(readOnly = true)
	public List<Problem> suggestedProblemsColdStart(String language,
			String username, int uid) {
		int PROBLEM_SUGGESTION_QTY = 10; // this is configuration

		Map<?, ?> solved = null;
		Map<?, ?> tried = null;
		Map<?, ?> favorite = null;

		if (username != null) {
			solved = problemsSolvedByUser(uid);
			tried = problemsTriedByUser(username, uid);
			favorite = userFavorite(uid);
		}

		List<Problem> problems = objects(
				"load.users.recommender.suggestedproblemscoldstart",
				Problem.class, username, PROBLEM_SUGGESTION_QTY);
		for (Iterator<Problem> it = problems.iterator(); it.hasNext();) {
			Problem problem = it.next();
			problem.setPoints((double) Integer.valueOf(Config
					.getProperty("formula.value"))
					/ (double) (40 + problem.getAccu()));
			if (solved != null) {
				if (solved.containsKey(problem.getPid())) {
					problem.setSolved(true);
				} else if (tried.containsKey(problem.getPid())) {
					problem.setUnsolved(true);
				}
				problem.setFavorite(favorite.containsKey(problem.getPid()));
			}
		}

		setProblemLanguage(language, problems);
		return problems;
	}

	@Override
	public void insertProblemClassification(Integer pid,
			Integer classificationid, Integer complexity) {
		dml("insert.problem.classification.2", pid, classificationid,
				complexity);
	}

	@Override
	public void updateClassification(Integer classid, String name) {
		dml("problem.updateclassification", name, classid);
	}

	@Override
	public void deleteClassification(Integer classid) {
//		dml.delete("classifications", Where.eq("id_classification", classid));
		dml("classification.delete", classid);

	}

	@Override
	public List<Problem> fillInformation(String username, int uid,
			List<Problem> problems) {
		Map solved = problemsSolvedByUser(uid);
		Map tried = problemsTriedByUser(username, uid);
		Map favorite = userFavorite(uid);

		for (Iterator<Problem> it = problems.iterator(); it.hasNext();) {
			Problem problem = it.next();
			problem.setPoints((double) Integer.valueOf(Config
					.getProperty("formula.value"))
					/ (double) (40 + problem.getAccu()));
			if (solved != null) {
				if (solved.containsKey(problem.getPid())) {
					problem.setSolved(true);
				} else if (tried.containsKey(problem.getPid())) {
					problem.setUnsolved(true);
				}
				problem.setFavorite(favorite.containsKey(problem.getPid()));
			}
		}
		return problems;
	}

	@Override
	public void insertTranslation(String username, Translation translation) {
		dml("translation.insert", username, new Date(), translation.getPid(),
				translation.getLocale(), translation.getTitle(),
				translation.getDescription(), translation.getInput(),
				translation.getOutput(), translation.getComments());
	}

	@Override
	@Transactional(readOnly = true)
	public IPaginatedList<Translation> getTranslationList(String username,
			Integer pid, String locale, PagingOptions options) {
		locale = "all".equals(locale) ? null : locale;


		Query query = new Query("translation_pending");
		query.where(Where.eq("username", username), Where.eq("pid", pid),
				Where.eq("locale", locale));

		int found = integer(query.count(), query.arguments());

		query.order(Order.desc("date"));
		query.paginate(options, 20);

		List<Translation> translations = objects(
				query.select("id", "username", "locale", "pid", "date"),
				Translation.class, query.arguments());

		return getPaginatedList(options, translations, 20, found);
	}

	@Override
	public void deleteTranslation(Integer id) {
		dml("translation.delete", id);
	}

	@Override
	public void editTranslation(Translation translation) {
		dml("translation.update", translation.getLocale(),
				translation.getTitle(), translation.getDescription(),
				translation.getInput(), translation.getOutput(),
				translation.getComments(), translation.getId());
	}

	@Override
	public void approveTranslation(Translation translation, String username) {
		dml("translation.approve." + translation.getLocale(),
				translation.getTitle(), translation.getDescription(),
				translation.getInput(), translation.getOutput(),
				translation.getComments(), translation.getPid());

		deleteTranslation(translation.getId());

		log(translation.getUsername() + " translated " + translation.getPid()
				+ " (" + translation.getLocale() + ")", username);
		contributionDAO.insertContribution(translation.getUsername(), 1);
	}

	@Override
	public Translation getTranslation(Integer id) {
		return object("translation.get", Translation.class, id);
	}

	@Transactional(readOnly = true)
	@Override
	public void fillProblemLimits(Problem problem) {
		List<Limits> limits = objects("select.problem.limits", Limits.class,
				problem.getPid());
		problem.setLimits(limits);
	}


}
