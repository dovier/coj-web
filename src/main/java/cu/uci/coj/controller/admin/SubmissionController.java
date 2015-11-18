package cu.uci.coj.controller.admin;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cu.uci.coj.config.Config;
import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.AchievementDAO;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.SubmissionDAO;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Filter;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Rejudge;
import cu.uci.coj.model.Status;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.utils.Utils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.submissionValidator;
import cu.uci.coj.utils.Notification;

@Controller("ContestSubmissionController")
@RequestMapping(value = "/admin")
public class SubmissionController extends BaseController {

	private static final long serialVersionUID = 8378556627163188126L;
	@Resource
	private ProblemDAO problemDAO;
	@Resource
	private SubmissionDAO submissionDAO;
	@Resource
	private ContestDAO contestDAO;
	@Resource
	private AchievementDAO achievementDAO;
	@Resource
	private submissionValidator validator;
	@Resource
	private Utils utils;

	@RequestMapping(value = "/managesubmissions.xhtml", method = RequestMethod.GET)
	public String allSubmissions(Model model, Filter filter,
			PagingOptions options) {
		LinkedList<Status> statuslist = new LinkedList<Status>();
		String[] ar = Config.getProperty("judge.status").split(",");
		for (int i = 0; i < ar.length; i++) {
			String string = ar[i];
			statuslist.add(new Status(string, Config.getProperty(string
					.replaceAll(" ", "."))));
		}
		List<Language> languagelist = submissionDAO.getEnabledLanguages();
		filter.fillFirstParam();
		filter.fillSecondParam();
		model.addAttribute("statuslist", statuslist);
		model.addAttribute("languagelist", languagelist);
		model.addAttribute("filter", filter);

		return "/admin/managesubmissions";
	}

	@RequestMapping(value = "/tables/managesubmissions.xhtml", method = RequestMethod.GET)
	public String tableSubmissions(Model model, Filter filter,
			PagingOptions options) {
		int found = submissionDAO.countSubmissionsAdmin(filter);
		if (found != 0) {
			IPaginatedList<SubmissionJudge> submissions = submissionDAO
					.getSubmissionsAdmin(filter, found, options, false);
			model.addAttribute("submissions", submissions);
		}
		return "/admin/tables/managesubmissions";
	}

	@RequestMapping(value = "/rejudgesubmissions.xhtml", method = RequestMethod.POST)
	public String rejudgeSubmissions(Model model, Filter filter,
			PagingOptions options, RedirectAttributes redirectAttributes) {
		int found = submissionDAO.countSubmissionsAdmin(filter);
		if (found != 0) {
			IPaginatedList<SubmissionJudge> submissions = submissionDAO
					.getSubmissionsAdmin(filter, found, options, true);

			model.addAttribute("submissions", submissions);

			utils.rejudgeSubmits(filter);
		}
		LinkedList<Status> statuslist = new LinkedList<Status>();
		String[] ar = Config.getProperty("judge.status").split(",");
		for (int i = 0; i < ar.length; i++) {
			String string = ar[i];
			statuslist.add(new Status(string, Config.getProperty(string
					.replaceAll(" ", "."))));
		}
		List<Language> languagelist = submissionDAO.getEnabledLanguages();
		filter.fillFirstParam();
		filter.fillSecondParam();
		model.addAttribute("statuslist", statuslist);
		model.addAttribute("languagelist", languagelist);
		model.addAttribute("filter", filter);
                redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullRejudge());
                
                return "redirect:/admin/managesubmissions.xhtml";
		//return "/admin/managesubmissions";
	}

	@RequestMapping(value = "/managesubmission.xhtml", method = RequestMethod.GET)
	public String manageSubmission(Model model, @RequestParam("sid") Integer sid) {
		SubmissionJudge submission = submissionDAO.loadSubmissionAdmin(sid);
		model.addAttribute("planguages",
				submissionDAO.objects("get.all.languages", Language.class));
		List<Status> statuslist = new LinkedList<Status>();
		String[] ar = Config.getProperty("judge.status").split(",");
		for (int i = 0; i < ar.length; i++) {
			String string = ar[i];
			statuslist.add(new Status(string, Config.getProperty(string
					.replaceAll(" ", "."))));
		}
		model.addAttribute("results", statuslist);
		model.addAttribute("submission", submission);
		return "/admin/managesubmission";
	}

	@RequestMapping(value = "/managesubmission.xhtml", method = RequestMethod.POST)
	public String manageSubmission(Principal principal, Model model,
			SubmissionJudge submission, BindingResult result) {
		validator.validate(submission, result);
		if (result.hasErrors()) {
			model.addAttribute("planguages",
					submissionDAO.objects("get.all.languages", Language.class));
			List<Status> statuslist = new LinkedList<Status>();
			String[] ar = Config.getProperty("judge.status").split(",");
			for (int i = 0; i < ar.length; i++) {
				String string = ar[i];
				statuslist.add(new Status(string, Config.getProperty(string
						.replaceAll(" ", "."))));
			}
			model.addAttribute("results", statuslist);
			model.addAttribute("submission", submission);
			return "/admin/managesubmission";
		}
		submissionDAO.updateSubmission(submission);
		submissionDAO.dml("insert.log",
				"editing SubmissionJudge " + submission.getSid(),
				getUsername(principal));
		return "redirect:/admin/managesubmission.xhtml?sid="
				+ submission.getSid();
	}

	@RequestMapping(produces = "application/json", value = "/24h/rejudge.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody boolean rejudgeSubmit(Model model,
			@RequestParam(value = "sid") int sid) {

		return utils.rejudgeSubmit(sid) != null;
	}

	@RequestMapping(produces = "application/json", value = "/contest/rejudge.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody boolean rejudgeContestSubmit(Model model,
			@RequestParam(value = "sid") int sid) {

		return utils.rejudgeContestSubmit(sid) != null;
	}

	@RequestMapping(produces = "application/json", value = "/24h/togglesub.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody boolean toggleSubmit(Model model,
			@RequestParam(value = "sid") int sid) {
		boolean enabled = baseDAO.bool("toggle.submit", sid);
		SubmissionJudge submit = submissionDAO.object("select.submition.sid",
				SubmissionJudge.class, sid);
		if (enabled)
			submissionDAO.applyEffects(submit, true);
		else
			submissionDAO.removeEffects(submit, true);
		return enabled;
	}

	@RequestMapping(produces = "application/json", value = "/contest/togglesub.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody boolean toggleContestSubmit(Model model,
			@RequestParam(value = "sid") int sid) {
		boolean enabled = baseDAO.bool("toggle.contest.submit", sid);
		// por supuesto es necesario repuntear despues de hacer esto. No se pone
		// automatico porque cuando se marquen varios en una lista, no se puede
		// repuntear en cada una.
		return enabled;
	}

	@RequestMapping(produces = "application/json", value = "/queuecount.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody int rejudgeSubmit(Model model) {

		return utils.getQueuedSubmitsCount();
	}

	@Deprecated
	private void validateRejudge(String sid1, String sid2, String pid,
			String status, BindingResult result) {
		boolean has = false;
		if (sid1 != null && sid2 != null
				&& new Integer(sid2) - new Integer(sid1) > 200) {
			has = true;
		}
		if (has) {
			result.reject("rejudge", "to many submissions");
		}
	}

	@RequestMapping(value = "/contestsubmission.xhtml", method = RequestMethod.GET)
	public String contestSubmission(Model model,
			@RequestParam("sid") Integer sid) {
		SubmissionJudge submission = submissionDAO
				.loadContestSubmissionAdmin(sid);
		List<Status> statuslist = new LinkedList<Status>();
		String[] ar = Config.getProperty("judge.status").split(",");
		for (int i = 0; i < ar.length; i++) {
			String string = ar[i];
			statuslist.add(new Status(string, Config.getProperty(string
					.replaceAll(" ", "."))));
		}
		model.addAttribute("planguages",
				submissionDAO.objects("get.all.languages", Language.class));
		model.addAttribute("results", statuslist);
		model.addAttribute(submission);
		return "/admin/contestsubmission";
	}

	@RequestMapping(value = "/contestsubmission.xhtml", method = RequestMethod.POST)
	public String contestSubmission(Model model, SubmissionJudge submission,
			BindingResult result) {
		validator.validate(submission, result);
		if (result.hasErrors()) {
			List<Status> statuslist = new LinkedList<Status>();
			String[] ar = Config.getProperty("judge.status").split(",");
			for (int i = 0; i < ar.length; i++) {
				String string = ar[i];
				statuslist.add(new Status(string, Config.getProperty(string
						.replaceAll(" ", "."))));
			}
			model.addAttribute("planguages",
					submissionDAO.objects("get.all.languages", Language.class));
			model.addAttribute("results", statuslist);
			model.addAttribute(submission);
			return "/admin/contestsubmission";
		}
		submissionDAO.updateContestSubmission(submission);
		return "redirect:/admin/contestsubmission.xhtml?sid="
				+ submission.getSid();
	}
}
