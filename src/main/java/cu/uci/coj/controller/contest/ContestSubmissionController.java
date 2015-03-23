package cu.uci.coj.controller.contest;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cu.uci.coj.config.Config;
import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.SubmissionDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Filter;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.Roles;
import cu.uci.coj.model.Status;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.model.User;
import cu.uci.coj.utils.Utils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.contestSubmitValidator;

@Controller
public class ContestSubmissionController extends BaseController {

	private static final long serialVersionUID = 1557354496511457369L;
	@Resource
	private SubmissionDAO submissionDAO;
	@Resource
	private ContestDAO contestDAO;
	@Resource
	private UserDAO userDAO;
	@Resource
	private ProblemDAO problemDAO;
	@Resource
	private contestSubmitValidator validator;
	@Resource
	private Utils utils;

	@RequestMapping(value = "/contest/cstatus.xhtml", method = RequestMethod.GET)
	public String Judgements(HttpServletRequest request, Principal principal, PagingOptions options, Model model, @RequestParam("cid") Integer cid,
			@RequestParam(required = false, value = "username") String filter_user, @RequestParam(required = false, value = "pid") Integer pid,
			@RequestParam(required = false, value = "status") String status, @RequestParam(required = false, value = "planguage") String language,
			@RequestParam(required = false, value = "refresh") Boolean refresh) {
		String username = getUsername(principal);
		Contest contest = contestDAO.loadContest(cid);

		contestDAO.unfreezeIfNecessary(contest);

		if (contest.isComing()) {
			return "redirect:/contest/contestview.xhtml?cid=" + cid;
		}
		if (contest.isRunning() || contest.isPast()) {
			if (contest.isShow_status() || (username != null && request.isUserInRole(Roles.ROLE_ADMIN))) {
				contest.setShow_status(false);
				if (contest.isShow_status_out() || (username != null && request.isUserInRole(Roles.ROLE_ADMIN))
						|| (!contest.isShow_status_out() && request.getUserPrincipal() != null && contestDAO.isInContest(cid, userDAO.integer("select.uid.by.username", getUsername(principal))))) {
					contest.setShow_status(true);
					String lang = submissionDAO.string("select language from language where key=?", language);
					LinkedList<Status> statuslist = new LinkedList<Status>();
					String[] ar = Config.getProperty("judge.status").split(",");
					for (int i = 0; i < ar.length; i++) {
						String string = ar[i];
						statuslist.add(new Status(string, Config.getProperty(string.replaceAll(" ", "."))));
					}
					List<Language> languagelist = new LinkedList<Language>();
					try {
						languagelist = contestDAO.getContestLanguages(contest.getCid());
					} catch (Exception e) {
					}
					Filter filter = new Filter(null, null, filter_user, cid, status, language, pid, languagelist, null);
					filter.fillFirstParam();
					filter.fillSecondParam();
					model.addAttribute("filter", filter);
					model.addAttribute("statuslist", statuslist);
				}
			}
		}
		model.addAttribute("contest", contest);

		model.addAttribute("refresh", refresh != null && refresh);
		return "/contest/cstatus";
	}

	@RequestMapping(value = "/tables/cstatus.xhtml", method = RequestMethod.GET)
	public String tableJudgements(HttpServletRequest request, Principal principal, PagingOptions options, Model model, @RequestParam("cid") Integer cid,
			@RequestParam(required = false, value = "username") String filter_user, @RequestParam(required = false, value = "pid",defaultValue="0") Integer pid,
			@RequestParam(required = false, value = "status") String status, @RequestParam(required = false, value = "planguage") String language,
			@RequestParam(required = false, value = "refresh") Boolean refresh) {
		if (pid==0) pid = null;
		String username = getUsername(principal);
		Contest contest = contestDAO.loadContest(cid);

		contestDAO.unfreezeIfNecessary(contest);

		if (contest.isComing()) {
			return "redirect:/contest/contestview.xhtml?cid=" + cid;
		}
		if (contest.isRunning() || contest.isPast()) {
			if (contest.isShow_status() || (username != null && request.isUserInRole(Roles.ROLE_ADMIN))) {
				contest.setShow_status(false);
				if (contest.isShow_status_out() || (username != null && request.isUserInRole(Roles.ROLE_ADMIN))
						|| (!contest.isShow_status_out() && request.getUserPrincipal() != null && contestDAO.isInContest(cid, userDAO.integer("select.uid.by.username", getUsername(principal))))) {
					contest.setShow_status(true);
					String lang = submissionDAO.string("select language from language where key=?", language);
					int found = submissionDAO.countSubmissionsContest(filter_user, lang, pid, Config.getProperty("judge.status." + status), contest);
					if (found > 0) {
						IPaginatedList<SubmissionJudge> submissions = submissionDAO.getContestSubmissions(
								found,
								filter_user,
								lang,
								pid,
								Config.getProperty("judge.status." + status),
								options,
								getUsername(principal),
								request.getUserPrincipal() != null,
								request.isUserInRole(Roles.ROLE_ADMIN)
										|| (request.isUserInRole(Roles.ROLE_JUDGE) && contestDAO.isJudgeInContest(cid, contestDAO.integer("select.uid.by.username", principal.getName()))), contest);
						if (contest.getStyle() == 1) {
							Map<Integer, Problem> pids = contestDAO.loadContestProblemsLetters(cid);
							for (SubmissionJudge sub : submissions.getList()) {
								if (!request.isUserInRole(Roles.ROLE_ADMIN) && contest.isInFrozen(sub.getDate().getTime()) && contest.isFrozen())
									sub.froze();
								sub.setLetter(pids.get(sub.getPid()).getLetter());
								sub.setProblemTitle(pids.get(sub.getPid()).getTitle());
							}
						}
						model.addAttribute("submissions", submissions);
					}
					LinkedList<Status> statuslist = new LinkedList<Status>();
					String[] ar = Config.getProperty("judge.status").split(",");
					for (int i = 0; i < ar.length; i++) {
						String string = ar[i];
						statuslist.add(new Status(string, Config.getProperty(string.replaceAll(" ", "."))));
					}
					List<Language> languagelist = new LinkedList<Language>();
					try {
						languagelist = contestDAO.getContestLanguages(contest.getCid());
					} catch (Exception e) {
					}
					Filter filter = new Filter(null, null, filter_user, cid, status, language, pid, languagelist, null);
					filter.fillFirstParam();
					filter.fillSecondParam();
					model.addAttribute("filter", filter);
					model.addAttribute("statuslist", statuslist);
				}
			}
		}
		model.addAttribute("contest", contest);

		return "/tables/cstatus";
	}

	@RequestMapping(value = "/contest/ccompileinfo.xhtml", method = RequestMethod.GET)
	public String CompileInfo(HttpServletRequest request, Model model, @RequestParam("cid") Integer cid, @RequestParam("sid") Integer sid) {
		Contest contest = contestDAO.loadContest(cid);
		model.addAttribute("contest", contest);
		SubmissionJudge submission = submissionDAO.getContestCompileInfo(sid, contest.getCid());
		if (!request.isUserInRole(Roles.ROLE_ADMIN) && contest.isFrozen() && submission.isFrozen()) {
			return "/error/accessdenied";
		} else {
			model.addAttribute("info", submission.getCompileInfo());
		}
		return "/contest/ccompileinfo";
	}

	@RequestMapping(value = "/contest/csubmission.xhtml", method = RequestMethod.GET)
	public String Submission(Principal principal, HttpServletRequest request, Model model, @RequestParam("cid") Integer cid, @RequestParam("id") Integer sid) {
		Contest contest = contestDAO.loadContest(cid);
		model.addAttribute("contest", contest);
		model.addAttribute("submission", getContestSourceCode(request, principal, sid, contest));
		return "/contest/csubmission";
	}

	private SubmissionJudge getContestSourceCode(HttpServletRequest request, Principal principal, int submit_id, Contest contest) {

		SubmissionJudge submission = null;
		if (!(request.isUserInRole(Roles.ROLE_ADMIN) || (request.isUserInRole(Roles.ROLE_JUDGE) && contestDAO.isJudgeInContest(contest.getCid(),
				contestDAO.integer("select.uid.by.username", principal.getName()))))) {
			submission = submissionDAO.object("get.contest.source.code", SubmissionJudge.class, contest.getCid(), submit_id, contest.getCid(), getUsername(principal));
		} else {
			submission = submissionDAO.object("get.contest.source.code.admin", SubmissionJudge.class, contest.getCid(), submit_id, contest.getCid());
		}

		if (submission != null) {
			submission.initialize();
			if (!request.isUserInRole(Roles.ROLE_ADMIN) && contest.isFrozen() && submission.isFrozen())
				submission.froze();
		}
		return submission;
	}

	@RequestMapping(value = "/contest/downloadsource.xhtml", method = RequestMethod.GET)
	public String DownloadSubmission(HttpServletRequest request, Principal principal, HttpServletResponse response, Model model, @RequestParam("cid") Integer cid, @RequestParam("sid") Integer sid)
			throws Exception {
		Contest contest = contestDAO.loadContest(cid);
		SubmissionJudge submission = getContestSourceCode(request, principal, sid, contest);
		response.setContentType(Config.getProperty("language.mime." + submission.getLang()));
		response.setHeader("Content-disposition", "inline; filename=" + submission.getFilename());
		response.getOutputStream().write(submission.getCode().getBytes());
		response.getOutputStream().flush();
		return null;
	}

	@RequestMapping(value = "/contest/csubmit.xhtml", method = RequestMethod.GET)
	public String Submit(Principal principal, HttpServletRequest request, Model model, @RequestParam("cid") Integer cid, @RequestParam(required = false, value = "pid") Integer pid,
			@RequestParam(required = false, defaultValue = "0", value = "sid") Integer sid) {
		SubmissionJudge submit = new SubmissionJudge();
		submit.setCid(cid);
		submit.setUsername(getUsername(principal));
		Language language = userDAO.getProgrammingLanguageByUsername(getUsername(principal));
		submit.setLid(language.getLid());
		submit.setKey(language.getKey());
		submit.setLanguages(contestDAO.getContestLanguages(submit.getCid()));
		Contest contest = contestDAO.loadContest(submit.getCid());
		submit.setContest(contest);
		model.addAttribute("languages", submit.getLanguages());
		model.addAttribute("contest", contest);
		model.addAttribute("enableadveditor", submissionDAO.bool("is.adved.enable", getUsername(principal)));
		if (sid != 0) {
			SubmissionJudge submission = getContestSourceCode(request, principal, sid, contest);
			submit.setCode(submission.getCode());
		} else {
			submit.setPid(pid == null ? 0 : pid);

		}
		model.addAttribute("submit", submit);
		return "/contest/csubmit";
	}

	@RequestMapping(value = "/contest/csubmit.xhtml", method = RequestMethod.POST)
	public String Submit(Locale locale, Principal principal, Model model, @ModelAttribute("submit") SubmissionJudge submit, BindingResult result) {
		submit.setUsername(getUsername(principal));
		submit.setLanguages(contestDAO.getContestLanguages(submit.getCid()));
		Contest contest = contestDAO.loadContest(submit.getCid());
		submit.setContest(contest);
		model.addAttribute("languages", submit.getLanguages());
		model.addAttribute("contest", contest);
		model.addAttribute("enableadveditor", submissionDAO.bool("is.adved.enable", getUsername(principal)));
		validator.validate(submit, result);
		if (result.hasErrors()) {
			model.addAttribute("submit", submit);
			return "/contest/csubmit";
		}

		int iduser = userDAO.integer("select.uid.by.username", getUsername(principal));
		if ((submit.getContest().getRegistration() == 0 && submit.getContest().isAllow_registration()) && !contestDAO.isInContest(submit.getCid(), userDAO.integer("select.uid.by.username", submit.getUsername()))) {
			contestDAO.insertUserContest(iduser, submit.getCid(), "General");
		}
		Problem problem = problemDAO.getProblemSubmitDataByAbb(locale.getLanguage(), submit.getPid());
		int sid = submissionDAO.insertContestSubmission(iduser, getUsername(principal), problem.getPid(), submit.getCode(), submit.getLanguageByLid(), submit.getCid(), false);
		SubmissionJudge submission = new SubmissionJudge(sid, iduser, submit.getCode(), problem.getPid(), problem.getTime(), problem.getCasetimelimit(), problem.getMemory(), submit.getLanguageByLid());
		submission.setCid(submit.getCid());
		submission.setSpecialJudge(problem.isSpecial_judge());
		utils.startCalification(submission);
		return "redirect:/contest/cstatus.xhtml?cid=" + submit.getCid();
	}

	@RequestMapping(value = "/contest/cuseraccount.xhtml", method = RequestMethod.GET)
	public String UserAccount(Principal principal, HttpServletRequest request, Model model, @RequestParam("cid") Integer cid, @RequestParam("uid") String uid) {
		String username = getUsername(principal);
		Integer userId = userDAO.integer("select.uid.by.username", username);
		Integer userId2 = userDAO.integer("select.uid.by.username", uid);
		Contest contest = contestDAO.loadContest(cid);
		if (contest.isRunning() || contest.isPast()) {
			if (contest.isShow_status() || (username != null && request.isUserInRole(Roles.ROLE_ADMIN))) {
				contest.setShow_status(false);
				if (contest.isShow_status_out() || (username != null && request.isUserInRole(Roles.ROLE_ADMIN))
						|| (!contest.isShow_status_out() && username != null && contestDAO.isInContest(cid, userId))) {
					contest.setShow_status(true);
					User user = userDAO.getUserContest(uid, cid);
					List<Problem> solved = userDAO.getProblemsSolvedInContest(userId2, contest);
					List<Problem> unsolved = userDAO.getProblemsTriedInContest(userId2, contest);
					user.setSolved(solved.size());
					user.setUnsolved(unsolved.size());
					model.addAttribute("user", user);
					model.addAttribute("solved", solved);
					model.addAttribute("unsolved", unsolved);
				}
			}
		}
		model.addAttribute("contest", contest);
		return "/contest/cuseraccount";
	}

}
