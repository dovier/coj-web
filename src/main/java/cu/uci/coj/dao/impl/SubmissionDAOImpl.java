package cu.uci.coj.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperConfig;

import cu.uci.coj.config.Config;
import cu.uci.coj.controller.interfaces.IBestSolutions;
import cu.uci.coj.controller.interfaces.IVirtualStatusSolutions;
import cu.uci.coj.dao.AchievementDAO;
import cu.uci.coj.dao.SubmissionDAO;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.DataSetVerdictsJson;
import cu.uci.coj.model.DatasetVerdict;
import cu.uci.coj.model.DatasetVerdictJson;
import cu.uci.coj.model.Filter;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.query.Order;
import cu.uci.coj.query.Query;
import cu.uci.coj.query.Where;
import cu.uci.coj.utils.Utils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

import java.io.IOException;
import java.sql.Array;

import javax.swing.JOptionPane;

@Repository("submissionDAO")
@Transactional
public class SubmissionDAOImpl extends BaseDAOImpl implements SubmissionDAO {

	@Resource
	private AchievementDAO achievementDAO;
	
	@Resource
	private ObjectMapper objectMapper;

	@Transactional(readOnly = true)
	public boolean Solved(int uid, int pid) {
		return bool("solved", uid, pid);
	}
 
	public String emailBySubmission(int sid) {
		return string("user.by.bysubmit", sid);

	}

	public List<SubmissionJudge> rejudgeSubmits(Filter filter,
			PagingOptions options) {

		Query query = new Query(filter.getCid() == null ? "submition"
				: "contest_submition");
		query.where(
				Where.bt("submit_id", filter.getStartSid(), filter.getEndSid()),
				Where.bt("date", filter.getStartDate(), filter.getEndDate()),
				Where.eq("username", filter.getUsername()),
				Where.eq("cid", filter.getCid()),
				Where.eq("language", filter.getLanguage()),
				Where.eq("status", Config.getProperty("judge.status."
						+ filter.getStatus())),
				Where.eq("pid", filter.getPid()));
		query.order(Order.desc("submit_id"));
		query.paginate(options, 20);
		return objects(query.select("submit_id as sid,status"),
				SubmissionJudge.class, query.arguments());
	}

	public void applyEffects(SubmissionJudge submit) {
		applyEffects(submit, false);
	}

	public void applyEffects(SubmissionJudge submit, boolean isDisabling) {
		if (!isDisabling) {
			dml("update.submit", submit.isAccepted(), submit.getStatus(),
					submit.getTimeUsed(), submit.getMemoryUsed(),
					submit.getFirstWaCase(), (int) submit.getMaxTimeUsed(),
					(int) submit.getMinTimeUsed(),
					(int) submit.getAvgTimeUsed(), submit.getAcTestCases(),
					submit.getSid());
			dml("update.source.error", submit.getErrMsg(), submit.getSid());
			
		}
		dml("update.last.user.submit", submit.getSid(), submit.getUid());

		// se inserta la rel de user con problem la primera vez, sea aceptado o
		// no.
		if (!bool("exist.problemid", submit.getPid(), submit.getUid())) {
			dml("insert.user.problem.accepted", submit.getPid(),
					submit.getUid(), submit.isAccepted());
			dml("clean.submition.first.ac", submit.getPid(), submit.getUid());
			dml("update.submition.first.ac", submit.getPid(), submit.getUid());
		}
		if (submit.isAccepted()) {
			// esto es importante, debido a que es posible aceptar un problema
			// que se haya intentado antes, en cuyo caso hay que actualizar el
			// intento fallido con el ac, pero no viceversa.
			dml("update.user.problem.accepted", submit.isAccepted(),
					submit.getPid(), submit.getUid());

			dml("update.last.user.accepted.submit", submit.getSid(),
					submit.getUid());

			if (bool("is.first.accepted", submit.getSid(), submit.getPid(),
					submit.getUid())) {
				// marca los aceptados unicos entre user y problem (col accu de
				// problem_stats)
				dml("update.problem.stats", submit.getPid());
				int ac = integer(0, "load.problem.stats", submit.getPid());
				double totbefore = Utils.formula(ac), totAfter = Utils
						.formula(ac + 1);
				dml("update.user.profile.substract", totbefore,
						submit.getPid(), submit.getUid(), totbefore);
				dml("update.user.profile.add", totAfter, submit.getPid());
				dml("update.user.profile.uaccu", submit.getUid());
			}
		}

		String key = Config
				.getProperty(submit.getStatus().replaceAll(" ", "."));
		if (!StringUtils.isEmpty(key) && !"jdg".equals(key)
				&& !"sie".equals(key)) {
			dml(replaceSql("update.user.stats.after.submition", "<key>", key),
					submit.getUid());
			dml(replaceSql("update.problem.stats.after.submition", "<key>", key),
					submit.getPid());
			dml(replaceSql("update.lang.stats.after.submition", "<key>", key),
					submit.getLang());
			achievementDAO.checkSubmits(submit);
		}
	
		//frankr ioi start
		insertDatasetVerdicts(submit);
		//frankr ioi end

		dml("mark.submit", submit.getSid());

	}
	
	//frankr ioi start
	private void insertDatasetVerdicts(SubmissionJudge submit){
		//ObjectMapper mapper = new ObjectMapper(); //FIXME insertar con DI
		Integer testNum = 0;
		Iterator<DatasetVerdict> it = submit.getDatasetVerdicts().iterator();
		DataSetVerdictsJson dvsjson = new DataSetVerdictsJson();
		while (it.hasNext()){
			DatasetVerdict dv = it.next();
			dv.setSid(submit.getSid());
			dv.setTestnum(testNum++);
			dv.setStatus(dv.getVerdict().associatedMessage());
			dvsjson.addDataSetVerdict(dv);
		}
		String jsonString = "";
		try {
			jsonString = objectMapper.writeValueAsString(dvsjson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		dml("insert.datasetverdictsjson", submit.getSid(), jsonString);
	}
	//frankr ioi end
	
//	//frankr ioi start
//	private void insertDatasetVerdicts(SubmissionJudge submit){
//		Integer testNum = 0;
//		Iterator<DatasetVerdict> it = submit.getDatasetVerdicts().iterator();
//		while (it.hasNext()){
//			DatasetVerdict dv = it.next();
//			dv.setSid(submit.getSid());
//			dv.setTestnum(testNum++);
//			dv.setStatus(dv.getVerdict().associatedMessage());
//			dml("insert.datasetverdict", dv.getSid(), dv.getTestnum(), dv.getMessage(), dv.getStatus(), 
//										 dv.getUserTime(), dv.getCpuTime(), dv.getMemory());
//		}
//		//insert.datasetverdict=insert into dataset_verdict (sid,testnum,message,status,"userTime","cpuTime",memory) values (?,?,?,?,?,?)
//	}
//	//frankr ioi end	

	public void removeEffects(SubmissionJudge submit, boolean isDisabling) {
		if (!isDisabling) {
			dml("update.submit.for.removal", submit.getSid());

			//esto lo comentareo porque esta quitando la info de los CE de la bd
			//dml("update.source.error.for.removal", submit.getSid());
		}
		// INFO: aqui deberiamos limpiar la ocurrencia de este submit en
		// user_problem
		// si este es el unico submit del usuario. No lo hacemos porque es una
		// operacion costosa (hay que revisar la tabla submition) y rejuzgar
		// solo demora algunos segundos, despues de los cuales se restaura la
		// consistencia en la bd ya que se escribe en la tabla user_problem de
		// todas maneras.
		if (submit.isAccepted()) {
			dml("update.problem.stats.for.removal", submit.getPid());

			if (bool("is.first.accepted", submit.getSid(), submit.getPid(),
					submit.getUid())) {
				int ac = integer(0, "load.problem.stats", submit.getPid());
				double totbefore = Utils.formula(ac), totAfter = Utils
						.formula(ac - 1);
				dml("update.user.profile.substract", totbefore,
						submit.getPid(), submit.getUid(), totbefore);
				dml("update.user.profile.add", totAfter, submit.getPid());
				dml("update.user.profile.uaccu.for.removal", submit.getUid());
				dml("delete.user.problem.id", submit.getUid(), submit.getPid());
			}
		}

		String key = Config
				.getProperty(submit.getStatus().replaceAll(" ", "."));
		if (!StringUtils.isEmpty(key) && !"jdg".equals(key)
				&& !"sie".equals(key)) {
			dml(replaceSql("update.user.stats.after.submition.for.removal",
					"<key>", key), submit.getUid());
			dml(replaceSql("update.problem.stats.after.submition.for.removal",
					"<key>", key), submit.getPid());
			dml(replaceSql("update.lang.stats.after.submition.for.removal",
					"<key>", key), submit.getLang());
		}

		if (!"Unqualified".equals(submit.getStatus()))
			achievementDAO.checkSubmits(submit);
		// esto tiene que quedarse al final para asegurarse que cuando se
		// dispare el evento sea cuando se haya calculado todo lo anterior
		
		//frankr ioi start
		dml("remove.datasetverdictjson.by.sid", submit.getSid());
		//dml("remove.datasetverdict.by.sid", submit.getSid());
		//frankr ioi end
		
		dml("reset.event.processing", submit.getSid());
	}

	public void removeEffects(SubmissionJudge submit) {
		removeEffects(submit, false);

	}

	@Override
	public List<SubmissionJudge> submissions(Integer uid) {
		return objects(getSql("select.submission.users.all"),
				SubmissionJudge.class, uid);
	}

	public List<String> emailBySubmissionRange(int sid1, int sid2) {
		return strings("users.by.sid.range", sid1, sid2);

	}

	@Transactional(readOnly = true)
	public boolean tryButunSolved(int uid, int pid) {
		return bool("try.but.unsolved", uid, pid);
	}

	@Transactional(readOnly = true)
	public Integer ProblemSolutionsCount(String id) {
		return integer("problem.solution.count", new Integer(id));
	}

	@Transactional(readOnly = true)
	public int countSubmissions(String username, String language, Integer pid,
			String status) {

		Query query = new Query("submition").where(Where.yes("enabled"),
				Where.eq("username", username), Where.eq("language", language),
				Where.eq("status", status), Where.eq("submition.pid", pid));
		return integer(query.count(), query.arguments());
	}

	@Transactional(readOnly = true)
	public int countSubmissionsAdmin(Filter filter) {

		Query query = new Query(filter.getCid() == null ? "submition"
				: "contest_submition");
		query.where(
				Where.bt("submit_id", filter.getStartSid(), filter.getEndSid()),
				Where.bt("date", filter.getStartDate(), filter.getEndDate()),
				Where.eq("username", filter.getUsername()),
				Where.eq("cid", filter.getCid()),
				Where.eq("language", filter.getLanguage()),
				Where.eq("status", Config.getProperty("judge.status."
						+ filter.getStatus())),
				Where.eq("pid", filter.getPid()));
		return integer(query.count(), query.arguments());
	}

	@Transactional(readOnly = true)
	public int countSubmissionsAdminRejudge(String username, String language,
			Integer pid, String status) {

		Query query = new Query("submition");
		query.where(Where.yes("submition.rejudge"),
				Where.eq("username", username), Where.eq("language", language),
				Where.eq("status", status), Where.eq("submition.pid", pid));
		return integer(query.count(), query.arguments());
	}

	@Transactional(readOnly = true)
	public IPaginatedList<SubmissionJudge> getSubmissions(String username,
			int found, String language, Integer pid, String status,
			PagingOptions options, Boolean loggedIn, Boolean admin, String usern) {

		final boolean logIn = loggedIn == null ? false : loggedIn;
		final boolean ad = admin == null ? false : admin;
		final String usrname = usern;
                boolean lock;
                
		Query query = new Query("submition");
		query.where(Where.eq("username", username),
				Where.eq("language", language), Where.eq("status", status),
				Where.eq("submition.pid", pid));

		query.order(Order.desc("submit_id"));
		query.paginate(options, 20);

		List<SubmissionJudge> submissions = objects(query.select(
				"submition.uid", "username", "submition.pid",
				"submit_id as sid", "submition.time as time_used",
				"submition.memory as memory_used",
				"submition.fontsize as font", "submition.date,status",
				"language as lang", "testcase as FirstWaCase",
				"average_case as avg_time_used"), SubmissionJudge.class,
				query.arguments());
                		for (SubmissionJudge s : submissions) {
                                    lock=false;
                                    List<Integer> obj=integers("problem.locked.all",usrname);
                                    for(int i = 0; i < obj.size(); i++ ){
                                        if(obj.get(i) == s.getPid()){
                                            lock = true;
                                        }
                                    }
			if (logIn && (ad || usrname.equals(s.getUsername()) || lock)) {
				s.setAuthorize(true);
			}
			s.initialize();
		}
		return getPaginatedList(options, submissions, 20, found);
	}

	@Transactional(readOnly = true)
	public List<SubmissionJudge> getSourceCodes(String username, int type) {
		List<SubmissionJudge> subs = null;
		switch (type) {
		case 1: {
			subs = objects("get.source.code.ac.username",
					SubmissionJudge.class, username);
			break;
		}
		default: {
			subs = objects("get.source.code.username", SubmissionJudge.class,
					username);
			break;
		}
		}
		if (subs != null) {
			for (SubmissionJudge sub : subs) {
				sub.initialize();
			}
		}
		return subs;
	}

	@Transactional(readOnly = true)
	public IPaginatedList<SubmissionJudge> getSubmissionsAdmin(Filter filter,
			int found, PagingOptions options, boolean asc) {
		Query query = new Query(filter.getCid() == null ? "submition"
				: "contest_submition");
		query.where(
				Where.bt("submit_id", filter.getStartSid(), filter.getEndSid()),
				Where.bt("date", filter.getStartDate(), filter.getEndDate()),
				Where.eq("username", filter.getUsername()),
				Where.eq("cid", filter.getCid()),
				Where.eq("language", filter.getLanguage()),
				Where.eq("status", Config.getProperty("judge.status."
						+ filter.getStatus())),
				Where.eq("pid", filter.getPid()));

		query.order(asc ? Order.asc("submit_id") : Order.desc("submit_id"));
		query.paginate(options, 20);

		List<SubmissionJudge> submissions = objects(query.select(
				"uid,username", "pid", "submit_id as sid", "time as time_used",
				"memory as memory_used", "fontsize as font", "date", "status",
				"language as lang", "testcase",
				"average_case as avg_time_used", "enabled",
				filter.getCid() == null ? "" : "cid"), SubmissionJudge.class,
				query.arguments());
		for (SubmissionJudge s : submissions) {
			s.setAuthorize(true);
			s.initialize();
		}
		return getPaginatedList(options, submissions, 20, found);
	}

	@Transactional(readOnly = true)
	public IPaginatedList<SubmissionJudge> getSubmissionsAdminRejudge(
			String username, int found, String language, Integer pid,
			String status, PagingOptions options) {
		Query query = new Query("submition");
		query.where(Where.yes("submition.rejudge"),
				Where.eq("username", username), Where.eq("language", language),
				Where.eq("status", status), Where.eq("submition.pid", pid));
		query.order(Order.desc("submit_id"));
		query.paginate(options, 20);
		List<SubmissionJudge> submissions = objects(
				query.select("submition.uid,username,submition.pid,submit_id as sid,submition.time as time_used,submition.memory as memory_used,submition.fontsize as font,submition.date,"
						+ "status,language as lang,testcase,average_case as avg_time_used,enabled,rejudge_status"),
				SubmissionJudge.class, query.arguments());
		for (SubmissionJudge s : submissions) {
			s.setAuthorize(true);
			s.initialize();
		}
		return getPaginatedList(options, submissions, 20, found);
	}

	@Transactional(readOnly = true)
	public List<Language> getEnabledLanguages() {
		return objects("get.enabled.languages", Language.class);
	}

	@Transactional(readOnly = true)
	public SubmissionJudge getSourceCode(int submit_id) {
		SubmissionJudge sub = object("get.source.code", SubmissionJudge.class,
				submit_id);
		//frankr ioi start
		List<DatasetVerdict> datasetVerdicts = getDatasetVerdictsBySid(submit_id);
		sub.setDatasetVerdicts(datasetVerdicts);
		sub.setTotalTestCases(datasetVerdicts.size()); //esto deberia hacerse persistiendo un campo en la BD para totalTestCases 
		//y recuperarlo tambien mediante la consulta get.source.code
		//frankr ioi end
		sub.initialize();
		return sub;
	}
	
	//frankr ioi start
	@Transactional(readOnly = true)
	private List<DatasetVerdict> getDatasetVerdictsBySid(int submit_id){ //FIXME: add to interface
		//ObjectMapper mapper = new ObjectMapper(); //FIXME insertar dependencia 
		String dvsjsonString = this.string("get.datasetverdictsjson.by.sid", submit_id);
		List<DatasetVerdict> result = new ArrayList<DatasetVerdict>();
		if (dvsjsonString != null){
			DataSetVerdictsJson dvsjson = null;
			try {
				dvsjson = objectMapper.readValue(dvsjsonString, DataSetVerdictsJson.class);
			} catch (IOException e) {
				e.printStackTrace();
				return result;
			} 
			List<DatasetVerdictJson> d = dvsjson.getD();
			for (DatasetVerdictJson dvjson : d){
				result.add( new DatasetVerdict(dvjson.getC(), dvjson.getS(), dvjson.getT(), dvjson.getM()) );
			}
		}
		//List<DatasetVerdict> result = objects("get.datasetverdicts.by.sid", DatasetVerdict.class, submit_id);
		//get.datasetverdicts.by.sid=select testnum, message, status, "userTime", "cpuTime", memory from dataset_verdict where sid=? order by testnum
		return result;
	}
	//frankr ioi end
	
	@Transactional(readOnly = true)
	public String getCompileInfo(int sid) {
		return string("get.error", sid);
	}

	@Transactional(readOnly = true)
	private int getMaxSubmitionIDByUsernameAndPcode(String username, Integer pid) {
		return integer("get.max.submit.id.by.user.pcode", username, pid);
	}

	@Transactional(readOnly = true)
	private int getMaxSubmitionIDByUsernameAndPcodeInContest(String username,
			Integer pid) {
		return integer("get.max.submit.id.by.user.pcode.contest", username, pid);
	}

	public int insertSubmission(int iduser, String username, int pid,
			String source, String language, boolean locked, Integer courseId
			) {

		dml("insert.submission", iduser, pid, source.length(), username,
				"Judging", language, Boolean.valueOf(locked));
		int sid = getMaxSubmitionIDByUsernameAndPcode(username, pid);
		dml("insert.submission.1", sid, source);
		return sid;
	}

	public void changeStatus(int sid, String status) {
		dml("change.status", status, sid);
	}

	@Transactional(readOnly = true)
	public String submitStatus(Integer submitId) {
		return string("select.status.submit.by.id", submitId);
	}

	private Query getSubmissionsContestQuery(String username, String language,
			Integer pid, String status, Contest contest) {
		Query query = new Query("contest_submition");
		Where where = Where.and(Where.yes("enabled"), Where.no("virtual"),
				Where.eq("cid", contest.getCid()),
				Where.eq("username", username), Where.eq("language", language),
				Where.eq("status", status), Where.eq("pid", pid));

		boolean filter_status = (status != null && !status.isEmpty());

		if (filter_status && contest.isFrozen()) {
			Query subQuery = new Query("contest", "enddate - interval '"
					+ contest.getFrtime() + " Minutes'").where(Where.eq("cid",
					contest.getCid()));
			query.where(Where.and(where, Where.lt("date", subQuery)));
		} else
			query.where(where);
		return query;
	}

	@Transactional(readOnly = true)
	public int countSubmissionsContest(String username, String language,
			Integer pid, String status, Contest contest) {
		Query query = getSubmissionsContestQuery(username, language, pid,
				status, contest);
		return integer(query.count(), query.arguments());
	}

	@Transactional(readOnly = true)
	public IPaginatedList<SubmissionJudge> getContestSubmissions(int found,
			String username, String language, Integer pid, String status,
			PagingOptions options, String usern, boolean loggedIn,
			boolean admin, Contest contest) {

		Query query = getSubmissionsContestQuery(username, language, pid,
				status, contest);
		query.order(Order.desc("submit_id"));
		query.paginate(options, 20);
		List<SubmissionJudge> submissions = objects(
				query.select("contest_submition.testcase as FirstWaCase,contest_submition.uid,username,contest_submition.pid,submit_id as sid,contest_submition.time as time_used,"
						+ "contest_submition.memory as memory_used,contest_submition.fontsize as font,contest_submition.date,status,language as lang,testcase,average_case as avg_time_used"),
				SubmissionJudge.class, query.arguments());

		for (SubmissionJudge submission : submissions) {
			if (loggedIn && (admin || usern.equals(submission.getUsername()))) {
				submission.setAuthorize(true);
			}
			submission.initialize();
			if (!submission.getStatus().equals("Accepted")
					&& !submission.getStatus().equals("Compilation Error")
					&& !submission.getStatus().equals("Unqualified")
					&& !submission.getStatus().equals("Judging")
					&& contest.isShow_ontest()) {
				submission.setOntest(true);
			}
			if (contest.isFrozen()
					&& (submission.getDate().getTime() >= (contest.getEnddate()
							.getTime() - 60000 * contest.getFrtime()))
					&& !admin) {
				if (!loggedIn
						|| (contest.isFull_frozen() && submission.getDate()
								.getTime() >= contest.getEnddate().getTime()
								- 60000 * contest.getDeadtime())
						|| !submission.getUsername().equals(usern)) {
					submission.froze();
					submission.setOntest(false);
				}
			}
		}
		return getPaginatedList(options, submissions, 20, found);
	}

	private Query getSubmissionsVirtualContest(String username,
			String language, Integer pid, String status, Contest contest,
			String current_user) {
		Query subQuery = new Query(
				"individual_virtual_contest join contest using(cid)",
				"initdate + (now() - start_time)").where(
				Where.eq("vid", contest.getCid()),
				Where.eq("individual_virtual_contest.username", current_user));
		Query query = new Query("contest_submition")
				.where(Where.yes("enabled"), Where.eq("username", username),
						Where.eq("language", language), Where.eq("status",
								status), Where.eq("pid", pid), Where.or(Where
								.eq("cid", contest.getCid()), Where
								.eq("cid",
										new Query("individual_virtual_contest",
												"cid").where(Where.eq("vid",
												contest.getCid())))), Where.le(
								"date", subQuery),
						Where.or(Where.no("virtual"), Where.and(Where
								.yes("virtual"), Where.or(Where.eq("username",
								current_user), Where.in(username, new Query(
								"virtual_contest_guest", "username")
								.where(Where.eq("vid", new Query(
										"individual_virtual_contest", "father")
										.where(Where.eq("username",
												current_user)))))))));
		return query;
	}

	@Transactional(readOnly = true)
	public int countSubmissionsVirtualContest(String username, String language,
			Integer pid, String status, Contest contest, String current_user) {
		Query query = getSubmissionsVirtualContest(username, language, pid,
				status, contest, current_user);
		return integer(query.count(), query.arguments());

	}

	@Transactional(readOnly = true)
	public IPaginatedList<SubmissionJudge> getVirtualContestSubmissions(
			int found, String username, String language, Integer pid,
			String status, PagingOptions options, boolean is_admin,
			Contest contest, String current_user) {
		IVirtualStatusSolutions rowmapper = new IVirtualStatusSolutions(
				current_user, is_admin, contest);

		Query query = getSubmissionsVirtualContest(username, language, pid,
				status, contest, current_user);
		query.order(Order.desc("date"));
		query.paginate(options, 20);
		return getPaginatedList(
				options,
				jdbcTemplate.query(
						query.select("contest_submition.uid,username,contest_submition.pid,submit_id,contest_submition.time,contest_submition.memory,contest_submition.fontsize,contest_submition.date,status,language,testcase,average_case,virtual,Extract(year from contest_submition.date) as year,Extract(month from contest_submition.date) as month,Extract(day from contest_submition.date) as day,Extract(hour from contest_submition.date) as hour,Extract(minute from contest_submition.date) as min,Extract(seconds from contest_submition.date) as sec"),
						query.arguments(), rowmapper), 20, found);
	}

	private Query getContestSubmissionsAdminQuery(String username,
			String language, Integer pid, String status, int cid) {
		return new Query("contest_submition").where(
				Where.eq("username", username), Where.eq("language", language),
				Where.eq("status", status), Where.eq("pid", pid),
				Where.eq("cid", pid));
	}

	@Transactional(readOnly = true)
	public int countSubmissionsContestAdmin(String username, String language,
			Integer pid, String status, int cid) {
		Query query = getContestSubmissionsAdminQuery(username, language, pid,
				status, cid);
		return integer(query.count(), query.arguments());
	}

	@Transactional(readOnly = true)
	public IPaginatedList<SubmissionJudge> getContestSubmissionsAdmin(
			String username, int found, String language, Integer pid,
			String status, PagingOptions options, int cid) {

		Query query = getContestSubmissionsAdminQuery(username, language, pid,
				status, cid);
		query.order(Order.desc("submit_id"));
		query.paginate(options, 20);

		List<SubmissionJudge> submissions = objects(
				query.select("uid,username,pid,submit_id as sid,time as time_used,contest_submition.memory as memory_used,contest_submition.fontsize as font,status,language as lang,testcase,average_case as avg_time_used,date,enabled,cid,virtual"),
				SubmissionJudge.class, query.arguments());
		for (SubmissionJudge submission : submissions) {
			submission.setAuthorize(true);

			submission.initialize();
			if (!submission.getStatus().equals("Accepted")
					&& !submission.getStatus().equals("Compilation Error")
					&& !submission.getStatus().equals("Unqualified")
					&& !submission.getStatus().equals("Judging")) {
				submission.setOntest(true);
			}
		}

		return getPaginatedList(options, submissions, 20, found);
	}

	@Transactional(readOnly = true)
	public SubmissionJudge getContestCompileInfo(int sid, int cid) {
		return object("get.contest.compile.info", SubmissionJudge.class, cid,
				sid);
	}

	public int insertContestSubmission(int iduser, String username, int pid,
			String source, String language, int cid, boolean virtual) {
		dml("insert.contest.submission", iduser, pid, source.length(),
				username, "Judging", language, cid, virtual);
		int sid = getMaxSubmitionIDByUsernameAndPcodeInContest(username, pid);
		dml("insert.contest.submission.1", sid, source);
		return sid;
	}

	@Transactional(readOnly = true)
	public String virtualSubmissionTime(String username, int cid) {
		return string("virtual.submission.time", cid, username);
	}

	public int insertVirtualSubmission(int iduser, String username, int pid,
			String source, String language, int cid, boolean virtual,
			Contest contest) {
		dml(replaceSql("insert.virtual.submission.1", "<vstime>",
				virtualSubmissionTime(username, contest.getCid())), iduser,
				pid, username, "Judging", language, cid, virtual);
		int sid = getMaxSubmitionIDByUsernameAndPcodeInContest(username, pid);
		dml("insert.virtual.submission", sid, source);
		return sid;
	}

	@Transactional(readOnly = true)
	public SubmissionJudge loadSubmissionAdmin(int sid) {
		return object("load.submission.admin", SubmissionJudge.class, sid);
	}

	@Transactional(readOnly = true)
	public SubmissionJudge loadContestSubmissionAdmin(int sid) {
		return object("load.contest.submission.admin", SubmissionJudge.class,
				sid);
	}

	public void updateSubmission(SubmissionJudge submission) {
		dml("update.submission", new Integer(submission.getTimeUsed()),
				submission.getMemoryUsed(), new Integer(submission.getCode()
						.length()), submission.getStatus(),
				submission.isEnabled(), submission.getSid());
		dml("update.submission.2", submission.getCode(), submission.getSid());
	}

	public void updateContestSubmission(SubmissionJudge submission) {
		dml("update.contest.submission", new Integer(submission.getTimeUsed()),
				submission.getMemoryUsed(), new Integer(submission.getCode()
						.length()), submission.getStatus(),
				submission.isEnabled(), submission.getSid());
		dml("update.contest.submission.1", submission.getCode(),
				submission.getSid());
	}

	@Transactional(readOnly = true)
	public int getAccu(Integer pid) {
		return integer("get.accu", pid);
	}

	@Transactional(readOnly = true)
	public IPaginatedList<SubmissionJudge> bestSolutions(Integer pid,
			int found, PagingOptions options,
			SecurityContextHolderAwareRequestWrapper Orequest,
			Problem Oproblem, boolean see) {
		IBestSolutions bsolutions = new IBestSolutions(pid, options.getPage(),
				Orequest, Oproblem);
		List<SubmissionJudge> submissions = jdbcTemplate.query(
				getSql("best.solutions"), bsolutions, pid,
				options.getOffset(20));
		return getPaginatedList(options, submissions, 20, found);
	}

	@Transactional(readOnly = true)
	public int countBestSolutions(Integer pid) {
		return integer("count.best.solutions", pid);
	}

	@Transactional(readOnly = true)
	public IPaginatedList<SubmissionJudge> bestSolutions(Integer pid,
			int found, PagingOptions options,
			SecurityContextHolderAwareRequestWrapper Orequest, Problem Oproblem) {
		IBestSolutions bsolutions = new IBestSolutions(pid,
				options.getPage() - 1, Orequest, Oproblem);
		return getPaginatedList(options, jdbcTemplate.query(
				getSql("best.solutions.1"), bsolutions, pid,
				(options.getPage() - 1) * 20), 20, found);
	}

	@Override
	public void updateDate(SubmissionJudge submit) {
		submit.setDate(date("select date from submition where submit_id=?",submit.getSid()));
	}
    }
