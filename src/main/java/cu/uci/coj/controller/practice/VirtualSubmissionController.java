package cu.uci.coj.controller.practice;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import cu.uci.coj.utils.Utils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.virtualSubmitValidator;

@Controller
public class VirtualSubmissionController extends BaseController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 196709206453846061L;
	@Resource
    private ContestDAO contestDAO;
    @Resource
    private SubmissionDAO submissionDAO;
    @Resource
    private UserDAO userDAO;
    @Resource
    private ProblemDAO problemDAO;
    @Resource
    private virtualSubmitValidator virtualSubmitValidator;
    @Resource
    private Utils utils;

    @RequestMapping(value = "/practice/vstatus.xhtml", method = RequestMethod.GET)
    public String Vstatus(Model model, HttpServletRequest request, Principal principal,
    		PagingOptions options, @RequestParam(required = false, value = "status") String status, @RequestParam(required = false, value = "planguage") String language, @RequestParam(required = false,value="pid") Integer pid, @RequestParam(required = false, value = "username") String filter_user) {
        String username = getUsername(principal);
        String lang = submissionDAO.string("select language from language where key=?", language);
        int cid = contestDAO.getVirtualRunning(username);
        if (cid > 0) {
            Contest contest = contestDAO.loadVirtualContest(cid);
            model.addAttribute("contest", contest);
            boolean exist = false;
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
            Filter filter = new Filter(null,null,filter_user, cid,status, language, pid, languagelist, null);
            filter.fillFirstParam();
            filter.fillSecondParam();
            model.addAttribute("filter", filter);
            model.addAttribute("statuslist",statuslist);
            model.addAttribute("exist", exist);
        }
        return "/practice/vstatus";
    }

    @RequestMapping(value = "/tables/vstatus.xhtml", method = RequestMethod.GET)
    public String tablesVstatus(Model model, HttpServletRequest request, Principal principal,
    		PagingOptions options, @RequestParam(required = false, value = "status") String status, @RequestParam(required = false, value = "planguage") String language, @RequestParam(required = false,value="pid") Integer pid, @RequestParam(required = false, value = "username") String filter_user) {
        String username = getUsername(principal);
        String lang = submissionDAO.string("select language from language where key=?", language);
        int cid = contestDAO.getVirtualRunning(username);
        if (cid > 0) {
            Contest contest = contestDAO.loadVirtualContest(cid);
            model.addAttribute("contest", contest);
            int found = submissionDAO.countSubmissionsVirtualContest(filter_user, lang, pid, Config.getProperty("judge.status." + status), contest, username);
            if (found > 0) {
                IPaginatedList<SubmissionJudge> submissions = submissionDAO.getVirtualContestSubmissions(found,filter_user, lang, pid, Config.getProperty("judge.status." + status), options, request.isUserInRole(Roles.ROLE_ADMIN), contest, getUsername(principal));

                model.addAttribute("submissions", submissions);
            }
        }
        return "/tables/vstatus";
    }

    
    @RequestMapping(value = "/practice/compileinfo.xhtml", method = RequestMethod.GET)
    public String VirtualCompileInfo(Model model, Principal principal, @RequestParam(required = false, value = "sid") Integer sid, @RequestParam(required = false, value = "status") String status, @RequestParam(required = false, value = "planguage") String language, @RequestParam(required = false,value="pid") Integer pid, @RequestParam(required = false, value = "username") String filter_user) {
        int cid = contestDAO.getVirtualRunning(getUsername(principal));
        Contest contest = contestDAO.loadVirtualContest(cid);
        model.addAttribute("contest", contest);
        SubmissionJudge submission = submissionDAO.getContestCompileInfo(sid, contestDAO.integer("select cid from individual_virtual_contest where vid=?",contest.getCid()));
        model.addAttribute("info", submission.getCompileInfo());
        return "/practice/vcompileinfo";
    }

    @RequestMapping(value = "/practice/vsubmission.xhtml", method = RequestMethod.GET)
    public String VirtualSubmission(Model model, HttpServletRequest request, Principal principal, @RequestParam("id") Integer id) {
        int cid = contestDAO.getVirtualRunning(getUsername(principal));
        Contest contest = contestDAO.loadVirtualContest(cid);
        model.addAttribute("contest", contest);
        model.addAttribute("submission", getContestSourceCode(request, principal, id, contest));
        return "/practice/vsubmission";
    }

    private SubmissionJudge getContestSourceCode(HttpServletRequest request, Principal principal, int submit_id, Contest contest) {

        SubmissionJudge submission = null;
        if (!request.isUserInRole(Roles.ROLE_ADMIN)) {
        	submission = submissionDAO.object("get.contest.source.code", SubmissionJudge.class, contest.getCid(), submit_id, contest.getCid(), getUsername(principal));
        } else {
        	submission = submissionDAO.object("get.contest.source.code.admin", SubmissionJudge.class, contest.getCid(), submit_id, contest.getCid());
        }

        if (submission != null) {
            submission.initialize();
            if (!request.isUserInRole(Roles.ROLE_ADMIN) && contest.isFrozen() && submission.isFrozen()) {
                submission.froze();
            }
        }
        return submission;
    }

    @RequestMapping(value = "/practice/downloadsource.xhtml", method = RequestMethod.GET)
    public String DownloadSubmission(Model model, Principal principal, HttpServletRequest request, HttpServletResponse response, @RequestParam("sid") Integer id) throws Exception {
        int cid = contestDAO.getVirtualRunning(getUsername(principal));
        Contest contest = contestDAO.loadVirtualContest(cid);
        SubmissionJudge submission = getContestSourceCode(request, principal, id, contest);
        response.setContentType(Config.getProperty("language.mime." + submission.getLang()));
        response.setHeader("Content-disposition", "inline; filename=" + submission.getFilename());
        response.getOutputStream().write(submission.getCode().getBytes());
        response.getOutputStream().flush();
        return null;
    }

    @RequestMapping(value = "/practice/vsubmit.xhtml", method = RequestMethod.GET)
    public String VirtualSubmit(Model model, Principal principal, HttpServletResponse response, @RequestParam(required = false,value="pid") Integer pid) {
        SubmissionJudge submit = new SubmissionJudge();
        submit.setPid(pid == null?0:pid);
        submit.setCid(contestDAO.getVirtualRunning(getUsername(principal)));
        submit.setUsername(getUsername(principal));
        Language language = userDAO.getProgrammingLanguageByUsername(getUsername(principal));
        submit.setLid(language.getLid());        
        submit.setKey(language.getKey());
        submit.setLanguages(contestDAO.getContestLanguagesVirtual(submit.getCid()));
        Contest contest = contestDAO.loadVirtualContest(submit.getCid());
        submit.setContest(contest);
        model.addAttribute("languages", submit.getLanguages());
        model.addAttribute("contest", contest);
        model.addAttribute("enableadveditor", userDAO.bool("is.adved.enable", getUsername(principal)));
        model.addAttribute("submit",submit);
        return "/practice/vsubmit";
    }

    @RequestMapping(value = "/practice/vsubmit.xhtml", method = RequestMethod.POST)
    public String VirtualSubmit(Locale locale,Model model, Principal principal, HttpServletResponse response, SubmissionJudge submit, BindingResult result) {
        submit.setCid(contestDAO.getVirtualRunning(getUsername(principal)));
        submit.setUsername(getUsername(principal));                
        submit.setPid(submit.getPid());
        submit.setLid(userDAO.integer("get.lid.by.key", submit.getKey()));
        submit.setLanguages(contestDAO.getContestLanguagesVirtual(submit.getCid()));
        Contest contest = contestDAO.loadVirtualContest(submit.getCid());
        submit.setContest(contest);
        virtualSubmitValidator.validate(submit, result);
        if (result.hasErrors()) {
        	submit.setLanguages(contestDAO.getContestLanguagesVirtual(submit.getCid()));
            model.addAttribute("languages", submit.getLanguages());
            model.addAttribute("contest", contest);
            model.addAttribute("enableadveditor", userDAO.bool("is.adved.enable", getUsername(principal)));
            model.addAttribute("submit",submit);
            return "/practice/vsubmit";
        }
        int iduser = userDAO.integer("select.uid.by.username", getUsername(principal));
        Problem problem = problemDAO.getProblemSubmitDataByAbb(submit.getPid(),submit.getLid());
        problem.setUserLanguage(locale.getLanguage());
        int sid = submissionDAO.insertVirtualSubmission(iduser, getUsername(principal), problem.getPid(), submit.getCode(), submit.getLanguageByLid(), submit.getCid(), true, submit.getContest());
        SubmissionJudge submission = new SubmissionJudge(sid, iduser, submit.getCode(), problem.getPid(), problem.getTime(), problem.getCasetimelimit(), problem.getMemory(), submit.getLanguageByLid());
        
        submission.setCid(submit.getCid());
        submission.setSpecialJudge(problem.isSpecial_judge());
        submission.setVirtual(true);
        utils.startCalification(submission);
        return "redirect:/practice/vstatus.xhtml";
    }
}
