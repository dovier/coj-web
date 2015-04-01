package cu.uci.coj.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cu.uci.coj.config.Config;
import cu.uci.coj.controller.interfaces.IACMScoreboard;
import cu.uci.coj.dao.ContestAwardDAO;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.mail.MailNotificationService;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.ContestStyle;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.model.User;
import cu.uci.coj.model.VirtualContest;
import cu.uci.coj.query.Order;
import cu.uci.coj.query.Query;
import cu.uci.coj.query.Where;
import cu.uci.coj.utils.Utils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

@Repository("contestDAO")
@Transactional
public class ContestDAOImpl extends BaseDAOImpl implements ContestDAO {

	List<Problem> problems;
	Map<Integer, Integer> uidPos;
	List<User> users;
	String groupby;
	int toffset;
	int[] minimums;
	boolean solved = false;
	private Contest contest;

	@Resource
	private ContestAwardDAO contestAwardDAO;
	@Resource
	private MailNotificationService mailNotificationService;

	public IPaginatedList<SubmissionJudge> pendingBalloons(int cid,
			PagingOptions options, String group) {
		if (StringUtils.hasText(group))
			return paginated("pending.balloons.grouped", SubmissionJudge.class,
					10, options, cid, group, cid);
		return paginated("pending.balloons", SubmissionJudge.class, 10,
				options, cid, cid);
	}

	public void markBalloon(int sid) {
		dml("mark.balloon", sid);
	}

	public List<Map<String, Object>> baylorXMLData(Integer cid) {
		List<Map<String, Object>> maps = maps("data.for.baylor.xml", cid);
		return maps;
	}

	public void insertData(String site, String[] teams, String teamCoach,
			String[] teamUsers, String[] user, Integer cid, Integer warmupCid) {
		// eliminar las participaciones en los contest para volver a
		// adicionarlos
		dml("insert.contest.data.1", user[0], user[1], user[2], user[3],
				user[4]);
		int uid = integer("select.uid.by.username", user[0]);

		dml("insert.contest.data.2", uid, teamCoach == null ? "none"
				: teamCoach, teamUsers[0] == null ? "none" : teamUsers[0],
				teamUsers[1] == null ? "none" : teamUsers[1],
				teamUsers[2] == null ? "none" : teamUsers[2]);

		dml("insert.contest.data.3", uid, cid, site);
		dml("insert.contest.data.4", uid, cid);
		dml("insert.contest.data.5", user[0]);
		if (warmupCid != null)
			dml("insert.contest.data.6", uid, warmupCid, site);
	}

	public void insertProblemColor(int pid, int cid, String color) {
		dml("insert.problem.color", color, pid, cid);
	}

	@Transactional(readOnly = true)
	public boolean isJudgeInContest(int cid, int uid) {
		return bool("exist.judge.contest", uid, cid);
	}

	public void checkContestsCreated() {
		List<Integer> cids = integers("contest.created.not.processed");
		for (Integer cid : cids) {
			dml("mark.contest.created", cid);
			mailNotificationService.sendContestCreatedNotification(cid);
		}
	}

	public void checkContestsStarted() {
		List<Integer> cids = integers("contest.started.not.processed");
		for (Integer cid : cids) {

			mailNotificationService.sendContestStartedNotification(cid);
			dml("mark.contest.started", cid);
		}
	}

	public void checkContestsEnded() {
		List<Integer> cids = integers("contest.ended.not.processed");
		for (Integer cid : cids) {
			// TODO publicar los problemas en 24 horas
			// hacer autounfreeze
			// desactivar banderas globales
			dml("mark.contest.ended", cid);
		}
	}

	@Transactional(readOnly = true)
	public boolean isInContest(int cid, int uid) {
		return bool("exist.user.in.contest", cid, uid);
	}

	@Transactional(readOnly = true)
	public List<Contest> loadContest() {
		return objects("load.contest", Contest.class);
	}

	public void resetContest(Contest contest) {

		// elimina todos los datos del scoreboard y todas las estadisticas del
		// contest. Necesario para empezar a repuntear
		dml("update.reset.contest", contest.getCid(), contest.getCid());
		dml("update.reset.contest.2", contest.getCid());
		dml("update.reset.contest.3", contest.getCid());
		dml("update.reset.contest.4", contest.getCid());
		dml("update.reset.contest.5", contest.getCid());
	}

	public void repointContest(Contest contest, boolean frozen) {
		resetContest(contest);
		List<SubmissionJudge> subsJudge = objects("repoint.contest.2",
				SubmissionJudge.class, contest.getCid());
		contest.setFrozen(frozen);
		for (SubmissionJudge subJudge : subsJudge) {
			applyEffects(subJudge, contest);
		}

	}

	@Deprecated
	public void updateAfterSubmitionContestRepoint(SubmissionJudge sub,
			Contest contest) {
		boolean slv;
		switch (contest.getStyle()) {
		case 1: {
			if (!(contest.isFrozen() && (!contest.isFrozen() || sub.isFrozen()))) {
				slv = this.SolvedProblemContestBEF(sub.getUid(), sub.getPid(),
						contest.getCid(), sub.getSid());
				int off = this.getProblemContestOffset(contest.getCid(),
						sub.getPid());
				char c = 'a';
				if (slv) {
					c = (char) (c + (off - 1));
					dml("update user_contest set " + c + "_afterac = " + c
							+ "_afterac + 1 where uid = ? and cid = ?",
							sub.getUid(), contest.getCid());
				}
				if (sub.getStatus().contains(
						(CharSequence) Config.getProperty("judge.status.ac"))) {
					if (slv)
						break;
					dml("update user_contest set lastacc = (select date from contest_submition where submit_id = ?) where uid = ? "
							+ "and cid = ?", sub.getSid(), sub.getUid(),
							contest.getCid());
					dml("update problem_contest set accu = accu + 1 where pid = ? and cid = ?",
							sub.getPid(), contest.getCid());
					c = (char) (c + (off - 1));
					int total_bef = this.countBeforeSubmissionsContest(
							sub.getUid(), sub.getPid(), contest.getCid(),
							sub.getSid());
					int penalty = total_bef
							* contest.getPenalty()
							+ (int) (sub.getDate().getTime() - contest
									.getInitdate().getTime()) / 60000;
					dml("update user_contest set penalty = penalty + ?,accepted = accepted + 1, "
							+ c
							+ "_time = ? from user_contest join contest using(cid) "
							+ "where uid = ? and cid = ?) where uid = ? and cid = ?",
							penalty, sub.getUid(), contest.getCid(),
							sub.getUid(), contest.getCid());
					break;
				}
				if (slv)
					break;
				c = (char) (c + (off - 1));
				dml("update user_contest set " + c + "_beforeac = " + c
						+ "_beforeac + 1 where uid = ? and cid = ?",
						sub.getUid(), contest.getCid());
				break;
			}
			int off = this.getProblemContestOffset(contest.getCid(),
					sub.getPid());
			char c = 'a';
			c = (char) (c + (off - 1));
			dml("update user_contest set " + c + "_pending = " + c
					+ "_pending + 1 where uid = ? and cid = ?", sub.getUid(),
					contest.getCid());
			break;
		}
		case 2: {
			break;
		}
		case 3: {
			slv = this.SolvedProblemContestBEF(sub.getUid(), sub.getPid(),
					contest.getCid(), sub.getSid());
			if (slv
					|| !sub.getStatus().contains(
							(CharSequence) Config
									.getProperty("judge.status.ac")))
				break;
			dml("update user_contest set lastacc = (select date from contest_submition where submit_id = ?) where uid = ? and cid = ?",
					sub.getSid(), sub.getUid(), contest.getCid());
			dml("update problem_contest set accu = accu + 1 where pid = ? and cid = ?",
					sub.getPid(), contest.getCid());
			Map<String, Object> map = map(
					"select (ac+wa+rte+ce+pe+uq+mle+ole+tle) as total,accu from problem_contest where pid = ? and cid = ?",
					sub.getPid(), contest.getCid());
			int ac = (Integer) map.get("accu");
			double totbefore = Utils.formulaFreeContest(ac - 1,
					contest.getPpoints());
			double totAfter = Utils
					.formulaFreeContest(ac, contest.getPpoints());
			double dif = totbefore - totAfter;
			dml("UPDATE user_contest set points = points - ? where uid in (select uid from contest_submition where pid = ? and "
					+ "status = 'Accepted' and cid = ? and contest_submition.date < (select contest_submition.date from contest_submition where submit_id = ?)) "
					+ "and (points - ?) > 0 and cid = ?", dif, sub.getPid(),
					contest.getCid(), sub.getSid(), dif, contest.getCid());
			dml("update user_contest set points = points + ?,accepted = accepted + 1 where uid = ? and cid = ?",
					totAfter, sub.getUid(), contest.getCid());
		}
		case 4: {
			slv = this.SolvedProblemContestBEF(sub.getUid(), sub.getPid(),
					contest.getCid(), sub.getSid());
			if (slv
					|| !sub.getStatus().contains(
							(CharSequence) Config
									.getProperty("judge.status.ac")))
				break;
			dml("update user_contest set lastacc = (select date from contest_submition where submit_id = ?) where uid = ? and cid = ?",
					sub.getSid(), sub.getUid(), contest.getCid());

			dml("update user_contest set points = points + ?, accepted = accepted + 1 where uid = ? and cid = ?",
					contest.getPpoints(), sub.getUid(), contest.getCid());
			dml("update problem_contest set accu = accu + 1 where pid = ? and cid = ?",
					sub.getPid(), contest.getCid());
			this.updateLevel(sub.getUid(), contest.getCid(),
					this.getProblemLevel(sub.getPid(), contest.getCid()),
					this.getAcceptedInContest(sub.getUid(), contest.getCid()),
					contest.getAcbylevels(), contest.getAclimit(), sub.getSid());
		}
		}
		if (!contest.isFrozen()) {
			String key = Config.getProperty(sub.getStatus()
					.replaceAll(" ", "."));
			dml(replaceSql("upsert.user.stats.contest.key", "<key>", key),
					sub.getUid(), contest.getCid(), sub.getUid(),
					contest.getCid());
			dml(replaceSql("upsert.problem.contest.contest.key", "<key>", key),
					sub.getPid(), contest.getCid(), sub.getPid(),
					contest.getCid());
			dml(replaceSql("upsert.language.stats.contest.key", "<key>", key),
					sub.getLang(), contest.getCid(), sub.getLang(),
					contest.getCid());
		}
	}

	@Transactional(readOnly = true)
	public boolean SolvedProblemContestBEF(int uid, int pid, int cid,
			int submit_id) {
		return bool("solved.contest.problem.bef", uid, pid, cid, submit_id);
	}

	@Transactional(readOnly = true)
	public int getProblemContestOffset(int cid, int pid) {
		int res = integer("problem.contest.offset", cid, pid);
		return res == 0 ? -1 : res;
	}

	@Transactional(readOnly = true)
	public int countBeforeSubmissionsContest(int uid, int pid, int cid,
			int submit_id) {
		return integer(0, "count.before.submissions.contest", uid, pid, cid,
				submit_id);
	}

	private void updateLevel(int uid, int cid, int level, int pac,
			int levellimit, int aclimit, int sid) {
		if (pac >= aclimit) {
			dml("update.level", cid, cid, uid);
		} else {
			int t = integer("update.level.2", uid, level, cid, cid, sid);
			if (t >= levellimit) {
				int curr = getCurrentLevel(uid, cid);
				if (curr <= level) {
					dml("update.level.3", cid, uid);
				}
			}
		}

	}

	@Transactional(readOnly = true)
	private int getCurrentLevel(int uid, int cid) {
		int res = integer("current.level.contest", uid, cid);
		return res == 0 ? 1 : res;
	}

	public void applyEffects(SubmissionJudge submit, Contest contest) {

		dml("update.contest.submit", submit.isAccepted(), submit.getStatus(),
				submit.getTimeUsed(), submit.getMemoryUsed(),
				submit.getFirstWaCase(), submit.getMaxTimeUsed(),
				submit.getMinTimeUsed(), submit.getAvgTimeUsed(),
				submit.getSid());

		boolean alreadySolved = false;
		alreadySolved = bool("solved.contest.problem.bef", submit.getUid(),
				submit.getPid(), contest.getCid(), submit.getSid());
		switch (contest.getStyle()) {
		case Contest.ACM_ICPC_STYLE: {
			int off = getProblemContestOffset(submit.getCid(), submit.getPid());
			char c = 'a';
			c += off - 1;
			if (!contest.isFrozen()
					|| !contest.isInFrozen(submit.getDate().getTime())) {

				if (alreadySolved) {
					dml("update user_contest set " + c + "_afterac = " + c
							+ "_afterac + 1 where uid = ? and cid = ?",
							submit.getUid(), contest.getCid());
				}
				if (submit.isAccepted()) {
					dml("update.problem.contest.accu", submit.getPid(),
							submit.getCid(), submit.getPid(), submit.getCid());
					dml("update.user.contest.last.acc", submit.getCid(),
							submit.getCid(), submit.getUid(), submit.getUid(),
							submit.getCid());

					if (!alreadySolved) {
						int total_bef = countBeforeSubmissionsContest(
								submit.getUid(), submit.getPid(),
								submit.getCid(), submit.getSid());
						int penalty = total_bef
								* contest.getPenalty()
								+ (int) (submit.getDate().getTime() - contest
										.getInitdate().getTime()) / 60000;

						dml("update user_contest set penalty = penalty + ?,accepted = accepted + 1,lastacc = '"
								+ submit.getDate().toString()
								+ "', "
								+ c
								+ "_time = ? where uid = ? and cid = ?",
								penalty, (submit.getDate().getTime() - contest
										.getInitdate().getTime()),
								submit.getUid(), submit.getCid());
					}
				} else if (!alreadySolved) {
					dml("update user_contest set " + c + "_beforeac = " + c
							+ "_beforeac + 1 where uid = ? and cid = ?",
							submit.getUid(), submit.getCid());
				}
			} else {
				dml("update user_contest set " + c + "_pending = " + c
						+ "_pending + 1 where uid = ? and cid = ?",
						submit.getUid(), submit.getCid());
			}
			break;
		}
		case 2: {
			// Not Implemented YET
			break;
		}
		case 3: {
			dml("update.problem.contest.accu", submit.getPid(),
					submit.getCid(), submit.getPid(), submit.getCid());
			dml("update.user.contest.last.acc", submit.getCid(),
					submit.getCid(), submit.getUid(), submit.getUid(),
					submit.getCid());
			dml("update.user.contest.accepted", submit.getCid(),
					submit.getUid(), submit.getUid(), submit.getCid());
			dml("update.user.points.free.contest", submit.getCid(),
					submit.getCid(), submit.getCid());
			break;
		}
		case Contest.PROGRESSIVE_STYLE: {
			if (!alreadySolved && submit.isAccepted()) {
				dml("update.after.submit.contest.4", submit.getPid(),
						submit.getCid());
				dml("update.problem.contest.accu", submit.getPid(),
						submit.getCid(), submit.getPid(), submit.getCid());
				dml("update.user.points.free.contest", submit.getCid(),
						submit.getCid(), submit.getCid());

			}
			updateLevel(submit.getUid(), submit.getCid(),
					getProblemLevel(submit.getPid(), submit.getCid()),
					getAcceptedInContest(submit.getUid(), submit.getCid()),
					contest.getAcbylevels(), contest.getAclimit(),
					submit.getSid());
			break;
		}
		}

		if (!contest.isFrozen()) {
			String key = Config.getProperty(submit.getStatus().replaceAll(" ",
					"."));
			if (key != null && !"sie".equals(key) && !"jdg".equals(key)) {
				dml(replaceSql("upsert.user.stats.contest.key", "<key>", key),
						submit.getUid(), submit.getCid(), submit.getUid(),
						submit.getCid());
				dml(replaceSql("upsert.problem.contest.contest.key", "<key>",
						key), submit.getPid(), submit.getCid(),
						submit.getPid(), submit.getCid());
				dml(replaceSql("upsert.language.stats.contest.key", "<key>",
						key), submit.getLang(), submit.getCid(),
						submit.getLang(), submit.getCid());
			} else
				System.out.println(submit.getStatus());
		}
	}

	@Transactional(readOnly = true)
	public int getAcceptedInContest(int uid, int cid) {
		return integer("accepted.in.contest", cid, uid);
	}

	@Transactional(readOnly = true)
	public int getProblemLevel(int pid, int cid) {
		int res = integer("problem.level", cid, pid);
		return res == 0 ? 1 : res;
	}

	@Override
	public Contest loadContest(int cid) {
		String sql = "load.contest.2";
		Contest contest = object(sql, Contest.class, cid);
		contest.setElapsed(contest.getElapsed().split("\\.")[0]);
		contest.setRemaining(contest.getRemaining().split("\\.")[0]);
		contest.setTostart(contest.getTostart().split("\\.")[0]);
		contest.putTimeReferences();
		if (contest.isRunning()) {
			contest.putTimeInformation();
		}

		return contest;
	}

	@Transactional(readOnly = true)
	public Contest loadVirtualContest(int cid) {
		Contest vcontest = object("load.vcont.byusername", Contest.class, cid);
		vcontest.setElapsed(vcontest.getElapsed().split("\\.")[0]);
		vcontest.setRemaining(vcontest.getRemaining().split("\\.")[0]);
		vcontest.setVirtual(true);
		vcontest.putTimeReferences();
		if (vcontest.isRunning()) {
			vcontest.putTimeInformation();
		}
		return vcontest;
	}

	@Transactional(readOnly = true)
	public List<Language> getContestLanguages(int cid) {

		List<Language> langs = objects("contest.languages", Language.class, cid);

		for (Language lang : langs) {
			lang.setDescripcion(lang.getLanguage() + " ("
					+ lang.getDescripcion() + ")");
		}
		return langs;
	}

	@Transactional(readOnly = true)
	public List<Language> getContestLanguagesVirtual(int cid) {

		List<Language> langs = objects("contest.languages.virtual",
				Language.class, cid);

		for (Language lang : langs) {
			lang.setDescripcion(lang.getLanguage() + " ("
					+ lang.getDescripcion() + ")");
		}
		return langs;
	}

	@Transactional(readOnly = true)
	private List<Language> getContestLanguagesToImport(int cid) {
		return objects("contest.languages.import", Language.class, cid);
	}

	@Transactional(readOnly = true)
	public boolean isProblemInContest(Integer pid, int cid, int level) {
		return bool("exist.problem.in.contest", pid, cid, level);
	}

	@Override
	public boolean isProblemInVirtualContest(Integer pid) {
		return bool("exist.problem.in.vcontest", pid);
	}

	public void insertUserContest(int uid, int cid, String gid) {
		dml("insert.user.contest", uid, cid, gid);
		dml("insert.user.stats.contest", uid, cid);
	}

	public void insertBalloonTrackerContest(int uid, int cid) {
		dml("insert.balloontracker.contest", uid, cid);
	}

	@Transactional(readOnly = true)
	public List<ContestStyle> loadEnabledScoringStyles() {
		return objects("contest.style.enabled", ContestStyle.class);
	}

	@Transactional(readOnly = true)
	public ContestStyle loadScoringStyle(int cid) {
		return object("contest.style.enabled.id", ContestStyle.class, cid);
	}

	@Transactional(readOnly = true)
	public Contest loadContestFull(int cid) {
		Contest contest1 = object(getSql("load.contest.full"), Contest.class,
				cid);
		contest1.setElapsed(contest1.getElapsed().split("\\.")[0]);
		contest1.setRemaining(contest1.getRemaining().split("\\.")[0]);
		contest1.setTostart(contest1.getTostart().split("\\.")[0]);
		contest1.putTimeReferences();
		if (contest1.unblock()) {
			contest1.setFrozen(false);
			contest1.setBlocked(false);
		}
		if (contest1.isRunning()) {
			contest1.putTimeInformation();
		}
		return contest1;
	}

	@Transactional(readOnly = true)
	public int[] getRankingAcmMinimun(int cid) {
		RowMapper<?> rowMapper = new RowMapper<Object>() {
			public Object mapRow(ResultSet result, int i) throws SQLException {
				int[] minimums = new int[12];
				minimums[0] = result.getInt(1);
				minimums[1] = result.getInt(2);
				minimums[2] = result.getInt(3);
				minimums[3] = result.getInt(4);
				minimums[4] = result.getInt(5);
				minimums[5] = result.getInt(6);
				minimums[6] = result.getInt(7);
				minimums[7] = result.getInt(8);
				minimums[8] = result.getInt(9);
				minimums[9] = result.getInt(10);
				minimums[10] = result.getInt(11);
				minimums[11] = result.getInt(12);
				return minimums;
			}
		};
		return (int[]) jdbcTemplate.queryForObject(
				getSql("ranking.acm.minimum"), rowMapper, cid);
	}

	@Transactional(readOnly = true)
	public int[] getGlobalRankingAcmMinimun(List<Integer> cids) {
		RowMapper<?> rowMapper = new RowMapper<Object>() {
			public Object mapRow(ResultSet result, int i) throws SQLException {
				int[] minimums = new int[12];
				minimums[0] = result.getInt(1);
				minimums[1] = result.getInt(2);
				minimums[2] = result.getInt(3);
				minimums[3] = result.getInt(4);
				minimums[4] = result.getInt(5);
				minimums[5] = result.getInt(6);
				minimums[6] = result.getInt(7);
				minimums[7] = result.getInt(8);
				minimums[8] = result.getInt(9);
				minimums[9] = result.getInt(10);
				minimums[10] = result.getInt(11);
				minimums[11] = result.getInt(12);
				return minimums;
			}
		};
		boolean first = true;
		String rest = "";
		for (Iterator<Integer> it = cids.iterator(); it.hasNext();) {
			Integer integer = it.next();
			if (first) {
				rest += integer;
				first = false;
			} else {
				rest += "," + integer;
			}
		}
		if (rest.isEmpty()) {
			return (int[]) jdbcTemplate.queryForObject(
					getSql("global.ranking.acm.minimum"), rowMapper);
		} else {
			return (int[]) jdbcTemplate.queryForObject(
					replaceSql("global.ranking.acm.minimum.cids", "<cids>",
							rest), rowMapper);
		}
	}

	@Transactional(readOnly = true)
	public IACMScoreboard getRankingAcm(int cid, String selGroup,
			boolean groupby, List<Problem> prbls) {

		IACMScoreboard aCMScoreboard = new IACMScoreboard();
		aCMScoreboard.init(groupby, string("get.contest.guestgroup", cid),
				prbls, getRankingAcmMinimun(cid));

		if (StringUtils.hasText(selGroup))
			jdbcTemplate.query(getSql("ranking.acm.selected.group"),
					aCMScoreboard, cid, selGroup);
		else
			jdbcTemplate.query(getSql("ranking.acm"), aCMScoreboard, cid);
		return aCMScoreboard;
	}

	@Transactional(readOnly = true)
	public IACMScoreboard getGlobalRankingAcm(List<Integer> cids,
			boolean groupby, List<Problem> prbls) {
		IACMScoreboard aCMScoreboard = new IACMScoreboard();
		aCMScoreboard.init2(groupby, prbls, getGlobalRankingAcmMinimun(cids));
		boolean first = true;
		String rest = "";
		for (Iterator<Integer> it = cids.iterator(); it.hasNext();) {
			Integer integer = it.next();
			if (first) {
				rest += integer;
				first = false;
			} else {
				rest += "," + integer;
			}
		}

		String sql = "get.global.rankin.acm";
		if (!rest.isEmpty()) {
			sql = replaceSql("get.global.rankin.acm.cids", "cids", rest);
		}
		jdbcTemplate.query(sql, aCMScoreboard);
		return aCMScoreboard;
	}

	public List<User> getUsers() {
		return users;
	}

	@Transactional(readOnly = true)
	public IPaginatedList<User> getFreeContestRanking(int cid,
			PagingOptions options) {

		IPaginatedList<User> users = paginated("free.contest.ranking",
				User.class, 30, options, cid, cid);
		int i = 0;
		for (User user : users.getList()) {
			user.setRank(options.getOffset(30) + i + 1);
			i++;
		}
		return users;
	}

	@Transactional(readOnly = true)
	public List<Contest> loadContestToImport() {
		return objects("contest.import", Contest.class);
	}

	public void InsertContest(Contest contest) {
		dml("insert.contest", contest.getCid(), contest.getCid(),
				contest.getCid() + "_Default");

		insertLanguages(contest);
	}

	public int getStyle(int cid) {
		return integer("select style from contest where cid=?", cid);
	}

	public void insertLanguages(Contest contest) {
		clearProgrammingLanguages(contest.getCid());
		List<Language> languages = objects(
				contest.isICPC() ? "enabled.icpc.language"
						: "enabled.programming.language", Language.class);
		for (int i = 0; i < languages.size(); i++) {
			insertLanguageContest(languages.get(i).getLid(), contest.getCid());
		}
	}

	public void insertLanguages(int cid, int[] languagesids) {
		clearProgrammingLanguages(cid);
		for (int i = 0; i < languagesids.length; i++) {
			insertLanguageContest(languagesids[i], cid);
		}
	}

	private void insertVirtualLanguages(int cid, List<Language> languages) {
		for (int i = 0; i < languages.size(); i++) {
			insertLanguageContest(languages.get(i).getLid(), cid);
		}
	}

	public void insertLanguageContest(int lid, int cid) {
		dml("insert.language.contest", lid, cid);
		dml("insert.language.stats.contest", lid, cid);
	}

	public void clearProgrammingLanguages(int cid) {
		dml("clear.prog.lang.cid", cid);
		dml("clear.prog.lang.2", cid);
	}

	@Transactional(readOnly = true)
	public int getMaxAvailableCID() {
		return integer("max.available.cid") + 1;
	}

	private void importLanguagesFromContest(int cidto, Contest from) {
		clearProgrammingLanguages(cidto);
		List<Language> languages = getContestLanguagesToImport(from.getCid());
		for (int i = 0; i < languages.size(); i++) {
			insertLanguageContest(languages.get(i).getLid(), cidto);
		}
	}

	private void importFlagsFromContest(int cidto, Contest from) {
		int curr = from.getCid();
		from.setCid(cidto);
		updateContestFlags(from);
		from.setCid(curr);
	}

	public void updateContestFlags(Contest contest) {
		dml("update.contest.flags", contest.isGallery(), contest.isBalloon(),
				contest.isSaris(), contest.isShow_stats(),
				contest.isShow_stats_out(), contest.isShow_status(),
				contest.isShow_status_out(), contest.isShow_scoreboard(),
				contest.isShow_scoreboard_out(),
				contest.isAllow_registration(), contest.isUnfreeze_auto(),
				contest.isShow_problem_out(), contest.isShow_ontest(),
				contest.getCid());
	}

	private void importGeneralFromContest(int cidto, Contest from) {
		dml("update.import.general.from.contest", from.getStyle(),
				from.getRegistration(), from.getTotal_users(),
				from.getDeadtime(), from.getFrtime(), from.getPenalty(),
				from.getIoimark(), from.getPpoints(), from.getUnfreeze_time(),
				cidto);
	}

	@Transactional(readOnly = true)
	public List<Problem> loadContestProblems(int cid) {
		return objects("load.contest.problems", Problem.class, cid);
	}

	private void importProblemsFromContest(int cidto, Contest from) {
		problems = loadContestProblems(from.getCid());
		int pOrder = 1;
		for (Iterator<Problem> it = problems.iterator(); it.hasNext();) {
			Problem problem = it.next();
			insertProblemContest(problem.getPid(), cidto, problem.getLevel(),
					pOrder++);
		}
	}

	public void insertProblemContest(int pid, int cid, int level, int order) {
		dml("insert.contest.problem", pid, cid, level, order);
		dml("insert.contest.problem.stats", pid, cid);
		dml("update.problem.contest", pid);
	}

	public void importContestData(Contest contest) {
		Contest from = loadContestFull(contest.getImportCid());
		if (contest.isImportAll()) {
			importLanguagesFromContest(contest.getCid(), from);
			importFlagsFromContest(contest.getCid(), from);
			importGeneralFromContest(contest.getCid(), from);
			importProblemsFromContest(contest.getCid(), from);
		} else {
			for (int i = 0; i < contest.getImports().length; i++) {
				String key = contest.getImports()[i];
				switch (key.charAt(0)) {
				case 'l': {
					importLanguagesFromContest(contest.getCid(), from);
					break;
				}
				case 'f': {
					importFlagsFromContest(contest.getCid(), from);
					break;
				}
				case 'p': {
					importProblemsFromContest(contest.getCid(), from);
					break;
				}
				case 'u': {
					// Not implemented Yet
					break;
				}
				case 'g': {
					importGeneralFromContest(contest.getCid(), from);
					break;
				}
				}
			}
		}
	}

	@Transactional(readOnly = true)
	public boolean existsContest(int cid) {
		return bool("exist.contest", cid);
	}

	@Transactional(readOnly = true)
	public Contest loadContestForProblems(int cid) {
		return object("load.contest.style", Contest.class, cid);
	}

	public void removeProblemContest(int cid, int pid) {
		dml("delete.problem.contest", cid, pid);
		dml("delete.problem.stats.contest", cid, pid);
	}

	@Transactional(readOnly = true)
	public Contest getContestForOverview(int cid) {
		return object("load.contest.overview", Contest.class, cid);
	}

	public void updateContestOverview(Contest contest) {
		dml("update.contest.overview", contest.getOverview(), contest.getCid());
	}

	@Transactional(readOnly = true)
	public Contest loadContestForManageUsers(int cid) {
		return object("load.contest.manage.users", Contest.class, cid);
	}

	public void clearUsersContest(int cid) {
		dml("delete.user.contest.4", cid);
		dml("delete.user.contest.1", cid);
	}

	public void guestGroup(Contest contest) {
		dml("update.contest.guest.group", contest.getGuestGroup(),
				contest.getCid());
	}

	public void clearContestJudges(int cid) {
		dml("clear.contest.judges", cid);
	}

	public void deleteUserContest(int cid, int uid) {
		dml("delete.user.contest.5", cid, uid);
		dml("delete.user.stats.contest", cid, uid);
	}

	public void insertUsersContest(Contest contest) {
		for (int i = 0; i < contest.getUsersids().length; i++) {
			insertUserContest(new Integer(contest.getUsersids()[i].toString()),
					contest.getCid(), contest.getGroupd());
		}
	}

	public void insertBalloonTrackerContest(Contest contest) {
		dml("clear.balloontracker.contest", contest.getCid());
		for (int i = 0; i < contest.getBalloontrackerids().length; i++) {
			insertBalloonTrackerContest(
					new Integer(contest.getBalloontrackerids()[i].toString()),
					contest.getCid());
		}
	}

	public void insertJudgesContest(Contest contest) {
		for (int i = 0; i < contest.getJudgesids().length; i++) {
			insertJudgeContest(contest.getCid(), contest.getJudgesids()[i]);
		}
	}

	public void insertJudgeContest(int cid, int uid) {
		dml("insert.contest.judges", cid, uid);
	}

	@Transactional(readOnly = true)
	public List<Contest> getComingAndRunningContests() {
		return objects("contest.coming.running", Contest.class);
	}

	@Transactional(readOnly = true)
	public IPaginatedList<Contest> getComingContests(PagingOptions options) {
		return paginated("coming.contests", Contest.class, 50, options);
	}

	@Transactional(readOnly = true)
	public IPaginatedList<Contest> getRunningContests(PagingOptions options) {
		return paginated("running.contests", Contest.class, 50, options);
	}

	@Transactional(readOnly = true)
	public IPaginatedList<Contest> getPastContests(PagingOptions options,
			String pattern) {
		if (pattern == null) {
			return paginated("past.contests", Contest.class, 50, options);
		} else {
			return paginated("past.contests.pattern", Contest.class, 50,
					options, "%" + pattern + "%", "%" + pattern + "%");
		}
	}

	@Transactional(readOnly = true)
	public int countContest() {
		return integer("count.contest");
	}

	// load.contests=select cid,name,registration,enabled,initdate > now() as
	// coming,now() between initdate and enddate as running,
	// block as blocked, initdate, enddate from contest order by running
	// desc,coming desc,enddate asc limit 50 offset ?
	@Transactional(readOnly = true)
	public IPaginatedList<Contest> loadContests(PagingOptions options,
			String access, String enabled, String status) {

		Query query = new Query("contest");
		Integer iAccess = "all".equals(access) ? null : Integer
				.parseInt(access);
		Boolean bEnabled = "all".equals(enabled) ? null : Boolean
				.parseBoolean(enabled);
		status = "all".equals(status) ? null : status;

		if (status != null) {
			Where wStatus = Where.noop();

			if (status.equals("running")) {
				wStatus = Where.and(Where.le("initdate", new Date()),
						Where.ge("enddate", new Date()));
			} else if (status.equals("coming")) {
				wStatus = Where.and(Where.gt("initdate", new Date()));
			} else {
				wStatus = Where.and(Where.lt("enddate", new Date()));
			}

			query.where(Where.eq("enabled", bEnabled),
					Where.ne("registration", iAccess), wStatus);
		}

		int found = integer(query.count(), query.arguments());

		query.order(Order.desc("running"), Order.desc("coming"),
				Order.desc("enddate"));
		query.paginate(options, 50);

		List<Contest> contests = objects(
				query.select("cid,name,registration,enabled,initdate > now() as coming,now() between initdate and enddate as running,enddate < now() as past,block as blocked, initdate, enddate"),
				Contest.class, query.arguments());

		return getPaginatedList(options, contests, 50, found);
	}

	@Transactional(readOnly = true)
	public Contest loadContestGlobalSettings(int cid) {
		return object("load.contest.global.settings", Contest.class, cid);
	}

	public void updateContestGlobalSettings(Contest contest) {
		updateContestFlags(contest);
		contest.setStyle(loadScoringStyle(contest.getCid()).getSid());
		switch (contest.getStyle()) {
		case 1:
			if (contest.getRegistration() == 1) {
				dml("update.contest.global.settings.tusers",
						contest.isGallery(), contest.isBalloon(),
						contest.getPenalty(), contest.getFrtime(),
						contest.getDeadtime(), contest.getUnfreeze_time(),
						contest.getTotal_users(), contest.getGold(),
						contest.getSilver(), contest.getBronze(),
						contest.getCid());
			} else {
				dml("update.contest.global.settings", contest.isGallery(),
						contest.isBalloon(), contest.getPenalty(),
						contest.getFrtime(), contest.getDeadtime(),
						contest.getUnfreeze_time(), contest.getGold(),
						contest.getSilver(), contest.getBronze(),
						contest.getCid());
			}
			break;
		case 2:
			if (contest.getRegistration() == 1) {
				dml("update.contest.global.settings.tusers.ioi",
						contest.isGallery(), contest.isBalloon(),
						contest.getIoimark(), contest.getTotal_users(),
						contest.getGold(), contest.getSilver(),
						contest.getBronze(), contest.getCid());
			} else {
				dml("update.contest.global.settings.ioi", contest.isGallery(),
						contest.isBalloon(), contest.getIoimark(),
						contest.getGold(), contest.getSilver(),
						contest.getBronze(), contest.getCid());
			}
			break;
		case 3:
			if (contest.getRegistration() == 1) {
				dml("update.contest.global.settings.tusers.points",
						contest.isGallery(), contest.isBalloon(),
						contest.getPpoints(), contest.getTotal_users(),
						contest.getGold(), contest.getSilver(),
						contest.getBronze(), contest.getCid());
			} else {
				dml("update.contest.global.settings.points",
						contest.isGallery(), contest.isBalloon(),
						contest.getPpoints(), contest.getGold(),
						contest.getSilver(), contest.getBronze(),
						contest.getCid());
			}
			break;
		case 4:
			if (contest.getRegistration() == 1) {
				dml("update.contest.global.settings.tusers.4",
						contest.isGallery(), contest.isBalloon(),
						contest.getTotal_users(), contest.getLevels(),
						contest.getAcbylevels(), contest.getAclimit(),
						contest.getPpoints(), contest.getGold(),
						contest.getSilver(), contest.getBronze(),
						contest.getCid());
			} else {
				dml("update.contest.global.settings.4", contest.isGallery(),
						contest.isBalloon(), contest.getLevels(),
						contest.getAcbylevels(), contest.getAclimit(),
						contest.getPpoints(), contest.getGold(),
						contest.getSilver(), contest.getBronze(),
						contest.getCid());
			}
			break;
		}
	}

	@Transactional(readOnly = true)
	public Contest loadContestManage(int cid) {
		return object("load.contest.manage", Contest.class, cid);
	}

	public void updateContestManage(Contest contest) {
		dml("update.contest.manage", contest.getName(), contest.getInitdate(),
				contest.getEnddate(), contest.getRglimit(), contest.getStyle(),
				contest.getRegistration(), contest.isEnabled(),
				contest.getContestant(), contest.isVtemplate(),
				contest.isBlocked(), contest.isGrouped(), contest.getCid());
		if (contest.isICPC()) {
			insertLanguages(contest);
		}
	}

	@Transactional(readOnly = true)
	public boolean existContestByName(String name, int cid) {
		return bool("exist.contest.name", name, cid);
	}

	@Transactional(readOnly = true)
	public List<Contest> loadVirtualTemplates() {
		return objects("load.virtual.template", Contest.class);
	}

	@Override
	public List<Contest> loadVirtualTemplates(int style) {
		if (style == 1) {
			return loadVirtualTemplates();
		}
		return objects("load.practice.template", Contest.class);
	}

	public void deleteVirtualContest(int uid) {

		Integer cid = integer(
				"select cid from contest where virtual = true and uid = ?", uid);
		if (cid != null) {
			deleteVirtualContestCid(cid);
		}
	}

	public void deleteVirtualContestCid(int cid) {
		dml("delete.virtual.contest.problem", cid);
		dml("delete.user.contest.cid", cid);
		dml("delete.contest", cid);
		dml("delete.contest.submit.cid", cid);
	}

	public int createVirtualContest(Contest contest, int uid, String username) {
		Contest template = loadContestFull(contest.getTemplate());
		Date endDate = new Date(contest.getInitdate().getTime()
				+ (template.getEnddate().getTime() - template.getInitdate()
						.getTime()));
		template.setCid(contest.getCid());
		template.setTemplate(contest.getTemplate());
		template.setEnddate(endDate);
		template.setName(username + " - " + template.getName());
		template.setUid(uid);
		template.setVirtual(true);
		template.setInitdate(contest.getInitdate());
		template.setIs_public(contest.isIs_public());

		List<Language> langs = getContestLanguages(template.getCid());
		contest.setLanguages(langs);

		insertVirtualLanguages(template.getCid(), langs);

		dml("insert.contest.full", template.getCid(), template.getName(),
				template.getInitdate(), template.getEnddate(),
				!template.isIs_public(), template.getTotal_users(),
				template.getStyle(), template.getDeadtime(),
				template.getFrtime(), template.isBlocked(),
				template.getPenalty(), template.isEnabled(), new Date(),
				template.getRglimit(), template.getRegistration(),
				template.getIoimark(), template.getPpoints(),
				template.getUnfreeze_time(), template.getContestant(),
				template.getGold(), template.getSilver(), template.getBronze(),
				false, template.getLevels(), template.getAcbylevels(),
				template.getAclimit(), template.isRepointing(),
				template.getGuestGroup(), template.isGrouped(),
				template.isBalloon(), contest.getTemplate(), template.getUid(),
				true, template.isShow_scoreboard(), contest.isSaris(),
				contest.isShow_stats(), contest.isShow_stats_out(),
				contest.isShow_status_out(), contest.isAllow_registration(),
				contest.isUnfreeze_auto(), contest.isShow_scoreboard_out(),
				template.isShow_status(), contest.isShow_problem_out(),
				contest.isShow_ontest(), template.getOverview());
		return contest.getCid();
	}

	public void joinVirtualContest(int cid, int uid, boolean createjoin) {
		Contest cont = loadVirtualContest(cid);
		if (cont.isIs_public() || createjoin) {
			deleteVirtualContest(uid);
			dml("insert.virtual.user.contest", cont.getCid(), uid);
		}
	}

	@Transactional(readOnly = true)
	public boolean overlapsContest(Contest contest) {
		return bool(replaceSql("overlaps.contest", "<initdate>", contest
				.getInitdate().toString()));
	}

	@Transactional(readOnly = true)
	public IPaginatedList<Contest> loadUserVirtualContests(int uid,
			PagingOptions options) {
		IPaginatedList<Contest> contests = paginated("users.vcont",
				Contest.class, 50, options, uid);
		for (Contest cont : contests.getList()) {
			if (!cont.isRunning() && !cont.isComing()) {
				cont.setPast(true);
			}
		}
		return contests;
	}

	@Transactional(readOnly = true)
	public List<Contest> loadPublicVirtualContests() {
		return objects("load.public.vcont", Contest.class);
	}

	@Transactional(readOnly = true)
	public int countVirtualGlobalList(String cid, String username, int access,
			int status) {
		List<Object> list = new LinkedList<Object>();
		String sql = "select count(*) from individual_virtual_contest join contest on contest.cid = individual_virtual_contest.cid where father = vid";
		if (cid != null && !cid.isEmpty()) {
			try {
				int c = new Integer(cid);
				sql += " and contest.cid = ?";
				list.add(new Integer(c));
			} catch (Exception e) {
			}
		}
		if (username != null && !username.isEmpty()) {
			sql += " and username = ?";
			list.add(username);
		}
		if (access == 1 || access == 2) {
			sql += " and is_public = ?";
			list.add(access == 1 ? true : false);
		}
		if (status == 1 || status == 2 || status == 3) {
			switch (status) {
			case 1: {
				sql += " and start_time > now()";
				break;
			}
			case 2: {
				sql += " and now() between start_time and end_time";
				break;
			}
			case 3: {
				sql += " and end_time < now()";
				break;
			}
			}
		}
		return integer(sql, list.toArray());
	}

	public IPaginatedList<Contest> loadGlobalVirtualContests(
			PagingOptions options, String cid, String username, int access,
			int status) {

		Integer found = countVirtualGlobalList(cid, username, access, status);

		RowMapper<Contest> rowMapper = new RowMapper<Contest>() {
			public Contest mapRow(ResultSet result, int i) throws SQLException {
				Contest contest = new Contest();
				contest.setCid(result.getInt("CID"));
				contest.setVid(result.getInt("VID"));
				contest.setEnddate(new Date(result.getInt("SYEAR") - 1900,
						result.getInt("SMONTH") - 1, result.getInt("SDAY"),
						result.getInt("SHOUR"), result.getInt("SMIN"), result
								.getInt("SSEC")));
				contest.setName(result.getString("name"));
				contest.setIdate(result.getString("initdate"));
				contest.setDuration(result.getString("duration"));
				contest.setUsername(result.getString("USERNAME"));
				contest.setIs_public(result.getBoolean("IS_PUBLIC"));
				contest.setTotal_users(result.getInt("GUESTS"));
				contest.setComing(result.getBoolean("COMING"));
				contest.setRunning(result.getBoolean("RUNNING"));
				if (!contest.isRunning() && !contest.isComing()) {
					contest.setPast(true);
				}
				return contest;
			}
		};
		List<Object> list = new LinkedList<Object>();
		String sql = "select contest.cid,vid,Extract(year from start_time) as syear,Extract(month from start_time) as smonth,Extract(day from start_time) as sday,Extract(hour from start_time) as shour,Extract(minute from start_time) as smin,Extract(seconds from start_time) as ssec,enddate-initdate as duration,name,initdate,individual_virtual_contest.username,is_public,(select count(*) from virtual_contest_guest where vid = individual_virtual_contest.vid) as guests,now() < start_time as coming,now() between start_time and end_time as running from individual_virtual_contest join contest on contest.cid = individual_virtual_contest.cid where father = vid";
		if (cid != null && !cid.isEmpty()) {
			try {
				int c = new Integer(cid);
				sql += " and contest.cid = ?";
				list.add(new Integer(c));
			} catch (Exception e) {
			}
		}
		if (username != null && !username.isEmpty()) {
			sql += " and username = ?";
			list.add(username);
		}
		if (access == 1 || access == 2) {
			sql += " and is_public = ?";
			list.add(access == 1 ? true : false);
		}
		if (status == 1 || status == 2 || status == 3) {
			switch (status) {
			case 1: {
				sql += " and start_time > now()";
				break;
			}
			case 2: {
				sql += " and now() between start_time and end_time";
				break;
			}
			case 3: {
				sql += " and end_time < now()";
				break;
			}
			}
		}
		sql += " order by start_time desc limit 50 offset ?";
		list.add(options.getOffset(50));

		IPaginatedList<Contest> contests = getPaginatedList(options,
				jdbcTemplate.query(sql, list.toArray(), rowMapper), 50,
				found == null ? 0 : found);

		return contests;
	}

	@Transactional(readOnly = true)
	public int getVirtualRunning(String username) {
		return integer("load.running.vcont", username);
	}

	@Transactional(readOnly = true)
	public List<User> getRankingACMVirtual(int cid, String username,
			List<Problem> problemsO) {
		this.problems = problemsO;
		RowMapper<User> rowMapper = new RowMapper<User>() {
			public User mapRow(ResultSet result, int i) throws SQLException {
				User user = new User(result.getString(2), result.getString(3),
						result.getInt(4), result.getInt(5),
						result.getString(6), result.getString(7),
						result.getString(8), result.getString(9),
						result.getString(10), problems);
				user.setVirtual(result.getBoolean("virtual"));
				user.setRank(++i);
				user.setPenalty(0);
				user.setUid(result.getInt("UID"));
				return user;
			}
		};
		return jdbcTemplate.query(getSql("get.ranking.acm.virtual"), rowMapper,
				cid, username, username, username);
	}

	@Transactional(readOnly = true)
	public void buildRankingACMVirtual(List<Problem> problemsO,
			List<User> usersO, Map<Integer, Integer> uidPosO, Contest contestO,
			String username, int uid) {
		this.problems = problemsO;
		this.users = usersO;
		this.uidPos = uidPosO;
		this.contest = contestO;
		this.solved = false;
		RowMapper<?> rowMapper = new RowMapper<Object>() {
			public Object mapRow(ResultSet result, int i) throws SQLException {
				try {
					String status = result.getString(1);
					int pid = result.getInt(2);
					int uid = result.getInt(3);
					Date sub_date = new Date(result.getInt(4) - 1900,
							result.getInt(5) - 1, result.getInt(6),
							result.getInt(7), result.getInt(8),
							result.getInt(9));
					int pos = uidPos.get(uid);
					int pPos = users.get(pos).getProblemPosition(pid);
					problems.get(pPos).incrementSubmissions();
					if (users.get(pos).getProblems().get(pPos).getScoreClass() == null) {
						problems.get(pPos).incrementTeamTry();
					}
					if (pPos == -1) {
						return null;
					}
					if (users.get(pos).getProblems().get(pPos).isAccepted()) {
						users.get(pos).getProblems().get(pPos)
								.increaseAfterAc(1);
					} else {
						if (status.equals("Accepted")) {
							if (!users.get(pos).getProblems().get(pPos)
									.isAccepted()) {
								users.get(pos).setAcc(
										users.get(pos).getAcc() + 1);
								long subtotal = sub_date.getTime() / 60000
										- contest.getRinitdate().getTime()
										/ 60000;
								users.get(pos).setPenalty(
										users.get(pos).getPenalty()
												+ users.get(pos).getProblems()
														.get(pPos)
														.getBeforeac()
												* contest.getPenalty()
												+ (int) subtotal);
								problems.get(pPos).incrementteamSolved();
								problems.get(pPos).setBeforeac(
										users.get(pos).getProblems().get(pPos)
												.getBeforeac() + 1);
							}

							users.get(pos).getProblems().get(pPos)
									.setScoreClass("ACC");
							users.get(pos).getProblems().get(pPos)
									.setAccepted(true);
							if (problems.get(pPos).getPid() == users.get(pos)
									.getProblems().get(pPos).getPid()) {
								if (!problems.get(pPos).isAccepted()) {
									users.get(pos).getProblems().get(pPos)
											.setFsolved(true);
									problems.get(pPos).setAccepted(true);
									users.get(pos).getProblems().get(pPos)
											.setScoreClass("FS");
								}
							}
							if (!solved) {
								users.get(pos).getProblems().get(pPos)
										.setScoreClass("FPS");
								solved = true;
							}
							users.get(pos).getProblems().get(pPos)
									.setAc_time(sub_date.toString());
							long min = (sub_date.getTime() - contest
									.getRinitdate().getTime()) / 60000;
							users.get(pos).getProblems().get(pPos)
									.setAcmin(min + "");

						} else {
							users.get(pos).getProblems().get(pPos)
									.increaseBeforeAc(1);
							users.get(pos).getProblems().get(pPos)
									.setScoreClass("WA");
						}

					}
				} catch (Exception e) {
				}
				return null;
			}
		};
		jdbcTemplate.query(getSql("build.ranking.acm.virtual.2"), rowMapper,
				contestO.getCid(), contestO.getCid(), username, uid, uid, uid);
	}

	@Transactional(readOnly = true)
	public int countVirtualContests() {
		return integer("count.load.vcont.2");
	}

	@Transactional(readOnly = true)
	public IPaginatedList<VirtualContest> loadVirtualContests(
			PagingOptions options) {
		IPaginatedList<VirtualContest> vcontests = paginated("load.vcont.2",
				VirtualContest.class, 50, options);

		for (VirtualContest vcontest : vcontests.getList()) {
			if (!vcontest.isRunning() && !vcontest.isComing()) {
				vcontest.setPast(true);
			}
		}
		return vcontests;
	}

	@Transactional(readOnly = true)
	public int countContestGeneralScoreboard(String pattern) {
		if (pattern == null || pattern.isEmpty()) {
			return integer("count.contest.general.scoreboard");
		} else {
			return integer("count.contest.general.scoreboard.pattern", "%"
					+ pattern + "%");
		}
	}

	public void updateBlockedContest(int cid, boolean block) {
		dml("update.blocked.contest", block, cid);
	}

	@Transactional(readOnly = true)
	public int findVirtualContestForUser(String username) {
		return integer("find.vcont.user", username);
	}

	@Transactional(readOnly = true)
	public int getContestLevel(int cid) {
		return integer(1, "load.contest.level", cid);
	}

	public void updateAfterSubmitionContestVirtual(SubmissionJudge sub) {
		dml("change.status.contest", sub.getStatus(), sub.getSid());
		dml(replaceSql("update.contest.virtual.after.submition", "<key>",
				Config.getProperty(sub.getStatus().replaceAll(" ", "."))),
				sub.getLang());
	}

	@Deprecated
	public boolean SolvedProblemContest(int uid, int pid, int cid) {
		return bool("solved.problem.contest");
	}

	@Deprecated
	public boolean SolvedProblemContestVirtual(int uid, int pid, int cid) {
		return bool("solved.contest.problem.virtual");
	}

	public int isCourseSubmission(int submit_id) {
		return integer(0, "is.course.submission");
	}

	public void resetUserContest(Contest contest, int uid) throws Exception {
		dml("update.reset.contest.user", contest.getCid(), uid);
		dml("update.reset.contest.user.2", contest.getCid(), uid);
		dml("update.reset.contest.user.3", contest.getCid(), uid);
		dml("update.reset.contest.4", contest.getCid(), uid);
		dml("update.reset.contest.5", contest.getCid(), uid);
	}

	@Transactional(readOnly = true)
	public Map<Integer, Problem> loadContestProblemsLetters(int cid) {

		List<Problem> pids = objects("load.problem.contestsetters",
				Problem.class, cid);
		Map<Integer, Problem> map = new HashMap<Integer, Problem>();
		for (int i = 0; i < pids.size(); i++) {
			Problem p = pids.get(i);
			p.setLetter(i);
			map.put(p.getPid(), p);
		}
		return map;
	}

	public void updateAfterSubmitionCourse(int pid, int uid, String status,
			String language, int submit_id, int course_id) {
		dml("insert.course.log.uid", uid, "Status: " + status + " ; Problem "
				+ pid, course_id);
		if (status.contains((CharSequence) Config
				.getProperty("judge.status.ac"))) {
			// para ver si es primera vez que se acepta
			int firstUid = integer(
					0,
					"select uid from submition where status = ? and uid = ? and pid = ? and course_id = ? and submit_id < ?",
					Config.getProperty("judge.status.ac"), uid, pid, course_id,
					submit_id);
			if (firstUid == 0) {
				dml("update course_users set points = points + (select problem_points from course where course_id = ?) where username = (select username from users where uid = ?) and course_id = ?",
						course_id, uid, course_id);
			}
		}
	}

	@Transactional(readOnly = true)
	public IPaginatedList<User> getContestGeneralScoreboard(int found,
			String pattern, PagingOptions options) {
		List<User> users;
		String sql;
		if (pattern == null || pattern.isEmpty()) {
			if (options.getSort() != null && !options.getSort().isEmpty()) {
				if (options.getSort().equals("contests")) {
					if (options.getDirection().equals("asc")) {
						sql = replaceSql("load.contest.global.scoreboard",
								"<orderby>", "contests asc");
					} else {
						sql = replaceSql("load.contest.global.scoreboard",
								"<orderby>", "contests desc");
					}
				} else if (options.getSort().equals("sub")) {
					if (options.getDirection().equals("asc")) {
						sql = replaceSql("load.contest.global.scoreboard",
								"<orderby>", "total asc");
					} else {
						sql = replaceSql("load.contest.global.scoreboard",
								"<orderby>", "total desc");
					}
				} else if (options.getSort().equals("acc")) {
					if (options.getDirection().equals("asc")) {
						sql = replaceSql("load.contest.global.scoreboard",
								"<orderby>", "accu asc");
					} else {
						sql = replaceSql("load.contest.global.scoreboard",
								"<orderby>", "accu desc");
					}
				} else {
					sql = replaceSql("load.contest.global.scoreboard",
							"<orderby>", "accu desc,total asc, contests desc");
				}
			} else {
				sql = replaceSql("load.contest.global.scoreboard", "<orderby>",
						"accu desc,total asc, contests desc");
			}
			users = objects(sql, User.class, options.getOffset(30));
		} else {
			sql = getSql("load.contest.global.scoreboard.pattern");
			if (options.getSort() != null && !options.getSort().isEmpty()) {
				if (options.getSort().equals("contests")) {
					if (options.getDirection().equals("asc")) {
						sql = replaceSql(sql, "<orderby>", "contests asc");
					} else {
						sql = replaceSql(sql, "<orderby>", "contests desc");
					}
				} else if (options.getSort().equals("sub")) {
					if (options.getDirection().equals("asc")) {
						sql = replaceSql(sql, "<orderby>", "total asc");
					} else {
						sql = replaceSql(sql, "<orderby>", "total desc");
					}
				} else if (options.getSort().equals("acc")) {
					if (options.getDirection().equals("asc")) {
						sql = replaceSql(sql, "<orderby>", "accu asc");
					} else {
						sql = replaceSql(sql, "<orderby>", "accu desc");
					}
				} else {
					sql = replaceSql(sql, "<orderby>",
							"accu desc,total asc, contests desc");
				}
			} else {
				sql = replaceSql(sql, "<orderby>",
						"accu desc,total asc, contests desc");
			}
			users = objects(sql, User.class, "%" + pattern + "%",
					options.getOffset(30));
		}
		int i = 0;
		for (User user : users) {
			double round = (double) (user.getAccu() * 100)
					/ (double) user.getTotal();
			round *= 1000;
			round = Math.round(round);
			round /= 1000;
			user.setPercent(user.getTotal() == 0 ? 0 : round);
			user.setRank(options.getOffset(30) + i + 1);
			i++;
		}
		return getPaginatedList(options, users, 30, found);
	}

	@Override
	public List<String> getGroups(Integer cid) {
		return strings("contest.groups", cid);
	}

	@Override
	public void freezeContest(Contest contest) {
		dml("update.freeze.blocked.contest", true, false, contest.getCid());
		dml("update problem set disable_24h=? where pid in (select pid from problem_contest where cid=?)",true,contest.getCid());
		repointContest(contest, true);
	}
	@Override
	public void unfreezeIfNecessary(Contest contest) {
		if (contest.mustUnfreeze(new Date())) {
			dml("update.blocked.contest", false, contest.getCid());
			dml("update problem set disable_24h=? where pid in (select pid from problem_contest where cid=?)",false,contest.getCid());
			repointContest(contest, false);
		}
	}

	@Override
	public void updateDate(SubmissionJudge submit) {
		submit.setDate(date(
				"select date from contest_submition where submit_id=?",
				submit.getSid()));
	}

	@Override
	public List<Problem> getAllContestProblems(int cid) {
		return objects("select.contest.problem.letters", Problem.class, cid);
	}
}
