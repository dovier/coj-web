package cu.uci.coj.controller;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cu.uci.coj.config.Config;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.SubmissionDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.model.Filter;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.Roles;
import cu.uci.coj.model.Status;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.utils.FileUtils;
import cu.uci.coj.utils.Utils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.submitValidator;

@Controller
public class SubmissionController extends BaseController {

	private static final long serialVersionUID = -6504539294285830687L;
	@Resource
	private SubmissionDAO submissionDAO;
	@Resource
	private UserDAO userDAO;
	@Resource
	private UtilDAO utilDAO;
	@Resource
	private ProblemDAO problemDAO;
	@Resource
	private submitValidator submitValidator;
	@Resource
	private Utils utils;

	@RequestMapping(value = "/24h/status.xhtml", method = RequestMethod.GET)
	public String Judgements(Principal principal, Model model, SecurityContextHolderAwareRequestWrapper requestWrapper, PagingOptions options,
			@RequestParam(required = false, value = "username") String filter_user, @RequestParam(required = false, value = "pid") Integer pid,
			@RequestParam(required = false, value = "status") String status, @RequestParam(required = false, value = "planguage") String language) {

		String lang = submissionDAO.string("select language from language where key=?", language);
		LinkedList<Status> statuslist = new LinkedList<Status>();
		String[] ar = Config.getProperty("judge.status").split(",");
		for (int i = 0; i < ar.length; i++) {
			String string = ar[i];
			statuslist.add(new Status(string, Config.getProperty(string.replaceAll(" ", "."))));
		}
		List<Language> languagelist = submissionDAO.getEnabledLanguages();
		Filter filter = new Filter(null, null, filter_user, null, status, language, pid, languagelist, null);
		model.addAttribute("statuslist", statuslist);
		model.addAttribute("filter", filter);
		return "/24h/status";
	}

	@RequestMapping(value = "/tables/status.xhtml", method = RequestMethod.GET)
	public String JudgementsTable(Principal principal, Model model, SecurityContextHolderAwareRequestWrapper requestWrapper, PagingOptions options,
			@RequestParam(required = false, value = "username") String filter_user, @RequestParam(required = false, value = "pid") Integer pid,
			@RequestParam(required = false, value = "status") String status, @RequestParam(required = false, value = "planguage") String language) {

		String lang = submissionDAO.string("select language from language where key=?", language);
		int found = submissionDAO.countSubmissions(filter_user, lang, pid, Config.getProperty("judge.status." + status));
		if (found != 0) {
			IPaginatedList<SubmissionJudge> submissions = submissionDAO.getSubmissions(filter_user, found, lang, pid, Config.getProperty("judge.status." + status), options, principal != null,
					requestWrapper.isUserInRole(Roles.ROLE_ADMIN), principal == null ? null : getUsername(principal));

			model.addAttribute("submissions", submissions);
		}
		return "/tables/status";
	}
	
	@RequestMapping(value = "/24h/bestsolutions.xhtml", method = RequestMethod.GET)
	public String BestSolutions(Locale locale, Principal principal, SecurityContextHolderAwareRequestWrapper requestWrapper, PagingOptions options, Model model, @RequestParam Integer pid) {
		Problem problem = problemDAO.getStatistics(locale.getLanguage(), pid);
		if (requestWrapper.isUserInRole(Roles.ROLE_USER)) {
			int uid = submissionDAO.integer("user.uid", getUsername(principal));
			problem.setSolved(problemDAO.bool("issolved.byuser", uid, problem.getPid()));
			if (problem.isSolved()) {
				problem.setLocked(problemDAO.isLocked(uid, problem.getPid()));
			}
		}
		model.addAttribute("problem", problem);
		return "/24h/bestsolutions";
	}
	
	@RequestMapping(value = "/tables/bestsolutions.xhtml", method = RequestMethod.GET)
	public String testBestSolutions(Locale locale, Principal principal, SecurityContextHolderAwareRequestWrapper requestWrapper, PagingOptions options, Model model, @RequestParam Integer pid) {
		Problem problem = problemDAO.getStatistics(locale.getLanguage(), pid);
		if (requestWrapper.isUserInRole(Roles.ROLE_USER)) {
			int uid = submissionDAO.integer("user.uid", getUsername(principal));
			problem.setSolved(problemDAO.bool("issolved.byuser", uid, problem.getPid()));
			if (problem.isSolved()) {
				problem.setLocked(problemDAO.isLocked(uid, problem.getPid()));
			}
		}
		int found = submissionDAO.countBestSolutions(pid);
		if (found != 0) {

			IPaginatedList<SubmissionJudge> submissions = submissionDAO.bestSolutions(pid, found, options, requestWrapper, problem);

			model.addAttribute("submissions", submissions);

		}
		return "/tables/bestsolutions";
	}

	@RequestMapping(value = "/24h/compileinfo.xhtml", method = RequestMethod.GET)
	public String CompileInfo(Principal principal, Model model, @RequestParam("sid") Integer sid) {
		model.addAttribute("info", submissionDAO.getCompileInfo(sid));
		return "/24h/compileinfo";
	}

	private boolean canAccessSubmission(Principal principal, SecurityContextHolderAwareRequestWrapper requestWrapper, int submit_id) {
		if (requestWrapper.isUserInRole(Roles.ROLE_ADMIN)) {
			return true;
		}
		if (principal == null) {
			return false;
		}
		return submissionDAO.bool("can.access.submission");
	}

	@RequestMapping(value = "/24h/submission.xhtml", method = RequestMethod.GET)
	public String SourceView(Principal principal, SecurityContextHolderAwareRequestWrapper requestWrapper, Model model, @RequestParam("id") Integer id) {
		try {
			if (!canAccessSubmission(principal, requestWrapper, id)) {
				return "/error/cancel";
			} else {
				SubmissionJudge submission = submissionDAO.getSourceCode(id);
				int uid = submissionDAO.integer("user.uid", getUsername(principal));
				if (!requestWrapper.isUserInRole(Roles.ROLE_ADMIN) && !(submission.getUsername().equals(getUsername(principal)))
						&& !(problemDAO.bool("issolved.byuser", uid, submission.getPid()) && problemDAO.isLocked(uid, submission.getPid()))) {
					throw new EmptyResultDataAccessException(0);
				}
				model.addAttribute("submission", submission);
			}
		} catch (EmptyResultDataAccessException ex) {
			return "/error/accessdenied";
		}
		return "/24h/submission";
	}

	@RequestMapping(value = "/24h/downloadsource.xhtml", method = RequestMethod.GET)
	public String SourceDownload(Principal principal, SecurityContextHolderAwareRequestWrapper requestWrapper, HttpServletResponse response, Model model, @RequestParam("sid") Integer id)
			throws Exception {
		try {
			if (!canAccessSubmission(principal, requestWrapper, id)) {
				return "/error/cancel";
			} else {
				SubmissionJudge submission = submissionDAO.getSourceCode(id);
				int uid = submissionDAO.integer("user.uid", getUsername(principal));
				if (!requestWrapper.isUserInRole(Roles.ROLE_ADMIN) && !(submission.getUsername().equals(getUsername(principal)))
						&& !(problemDAO.bool("issolved.byuser", uid, submission.getPid()) && problemDAO.isLocked(uid, submission.getPid()))) {
					throw new EmptyResultDataAccessException(0);
				}
				response.setContentType(Config.getProperty("language.mime." + submission.getLang()));
				response.setHeader("Content-disposition", "inline; filename=" + submission.getFilename());
				response.getOutputStream().write(submission.getCode().getBytes());
				response.getOutputStream().flush();
				return null;
			}
		} catch (EmptyResultDataAccessException ex) {
			return "/error/accessdenied";
		}
	}

	@RequestMapping(value = "/24h/downloadsourcezip.xhtml", method = RequestMethod.GET)
	public String SourceZipDownload(Principal principal, HttpServletResponse response, Model model, @RequestParam(value = "status", required = false, defaultValue = "0") Integer type) {
		try {
			int uid = submissionDAO.integer("user.uid", getUsername(principal));
			response.setContentType("application/octet-stream");
			String name = "coj";
			switch (type) {
			case 1: {
				name += "AC";
				break;
			}
			default: {
				name += "ALL";
				break;
			}
			}
			response.setHeader("Content-disposition", "inline; filename=" + getUsername(principal) + "-" + name + ".zip");
			FileUtils.crearArchivoComprimido(response.getOutputStream(), submissionDAO.getSourceCodes(getUsername(principal), type));
			response.getOutputStream().flush();
			return null;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "/error/accessdenied";
		}
	}

	@RequestMapping(value = "/24h/submit.xhtml", method = RequestMethod.GET)
	public String SubmitController(Principal principal, SecurityContextHolderAwareRequestWrapper requestWrapper, Model model, @RequestParam(required = false, value = "pid") Integer pid,
			@RequestParam(required = false, defaultValue = "0", value = "sid") Integer sid) {
		SubmissionJudge submit = new SubmissionJudge();
		Language language = userDAO.getProgrammingLanguageByUsername(getUsername(principal));
		submit.setPid(pid == null ? 0 : pid);
		if (sid != 0) {
			SubmissionJudge submission = submissionDAO.getSourceCode(sid);
			int uid = userDAO.integer("user.uid", getUsername(principal));
			if (!requestWrapper.isUserInRole(Roles.ROLE_ADMIN) && !(submission.getUsername().equals(getUsername(principal)))
					&& !(problemDAO.bool("issolved.byuser", uid, submission.getPid()) && problemDAO.isLocked(uid, submission.getPid()))) {
			} else {
				submit.setCode(submission.getCode());
				submit.setPid(submission.getPid());
				submit.setLid(submission.getLid());
				submit.setKey(submission.getKey());
			}

		} else {
			submit.setLid(language.getLid());
			submit.setKey(language.getKey());
		}
		List<Language> languages = new LinkedList<Language>();

		try {
			if (problemDAO.exists(submit.getPid())) {
				languages.addAll(utilDAO.getEnabledLanguagesByProblem(submit.getPid()));
			} else {
				languages.addAll(utilDAO.getEnabledLanguagesByProblem(null));
			}
		} catch (EmptyResultDataAccessException e) {
		}
		submit.setLanguages(languages);
		model.addAttribute("languages", languages);
		if (getUsername(principal) != null) {
			model.addAttribute("enableadveditor", userDAO.bool("is.adved.enable", getUsername(principal)));
		}
		model.addAttribute("submit", submit);
		return "/24h/submit";
	}

	@RequestMapping(value = "/24h/submit.xhtml", method = RequestMethod.POST)
	public String SubmitController(SecurityContextHolderAwareRequestWrapper requestWrapper, Locale locale, Principal principal, Model model, SubmissionJudge submit, BindingResult bindingResult) {
		List<Language> languages = new LinkedList<Language>();
		Integer uid = userDAO.integer("user.uid", getUsername(principal));
		try {
			if (problemDAO.exists(submit.getPid())) {
				languages.addAll(utilDAO.getEnabledLanguagesByProblem(submit.getPid()));
			} else {
				languages.addAll(utilDAO.getEnabledLanguagesByProblem(null));
			}
		} catch (EmptyResultDataAccessException e) {
		}
		submit.setLanguages(languages);
		submit.getLanguageIdByKey();
		submit.setHaspriviligeForProblem(requestWrapper.isUserInRole(Roles.ROLE_ADMIN) || requestWrapper.isUserInRole(Roles.ROLE_SUPER_PSETTER)
				|| (problemDAO.bool("is.psetter.problem", uid, Integer.valueOf(submit.getPid())) && requestWrapper.isUserInRole(Roles.ROLE_PSETTER)));
		submitValidator.validate(submit, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("enableadveditor", userDAO.bool("is.adved.enable", getUsername(principal)));
			model.addAttribute("languages", languages);
			model.addAttribute("submit",submit);
			return "/24h/submit";
		}
		int iduser = userDAO.integer("user.uid", getUsername(principal));
		Problem problem = problemDAO.getProblemSubmitDataByAbb(locale.getLanguage(), submit.getPid());
		boolean locked = problemDAO.bool("issolved.byuser", iduser, problem.getPid()) && problemDAO.isLocked(iduser, problem.getPid());
		int sid = submissionDAO.insertSubmission(iduser, getUsername(principal), problem.getPid(), submit.getCode(), submit.getLanguageByLid(), locked, null);
		SubmissionJudge submission = new SubmissionJudge(sid, iduser, submit.getCode(), problem.getPid(), problem.getTime(), problem.getCasetimelimit(), problem.getMemory(), submit.getLanguageByLid());

		submission.setSpecialJudge(problem.isSpecial_judge());
		try {
			utils.startCalification(submission);
		} catch (Exception e) {
			submissionDAO.changeStatus(sid, "Unqualified");
		}
                
		return "redirect:/24h/status.xhtml";
	}
}
