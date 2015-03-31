package cu.uci.coj.controller.practice;

import java.security.Principal;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Problem;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

@Controller
@RequestMapping(value = "/practice")
public class VirtualProblemController extends BaseController {

    @Resource
    private ContestDAO contestDAO;
    @Resource
    private ProblemDAO problemDAO;

    @RequestMapping(value = "/vproblems.xhtml", method = RequestMethod.GET)
    public String VirtualProblems(Locale locale,Model model, Principal principal,PagingOptions options, @RequestParam(required = false, value = "status") String status, @RequestParam(required = false, value = "planguage") String language, @RequestParam(required = false,value="pid") Integer pid, @RequestParam(required = false, value = "username") String filter_user) {
        int cid = contestDAO.getVirtualRunning(getUsername(principal));
        int father = contestDAO.integer("load.virtual.contest.father", getUsername(principal));
        Contest contest = contestDAO.loadVirtualContest(cid);
        contest.setVid(father);
        model.addAttribute("contest", contest);
        return "/practice/vproblems";
    }
    
    @RequestMapping(value = "/tables/vproblems.xhtml", method = RequestMethod.GET)
    public String tableVirtualProblems(Locale locale,Model model, Principal principal,PagingOptions options, @RequestParam(required = false, value = "status") String status, @RequestParam(required = false, value = "planguage") String language, @RequestParam(required = false,value="pid") Integer pid, @RequestParam(required = false, value = "username") String filter_user) {
        int cid = contestDAO.getVirtualRunning(getUsername(principal));
        int father = contestDAO.integer("load.virtual.contest.father", getUsername(principal));
        Contest contest = contestDAO.loadVirtualContest(cid);
        contest.setVid(father);
        if (cid > 0) {
            int found = contestDAO.integer("count.virtual.contest.problems", father);
            if (found > 0) {
                if (contest.getStyle() == 4) {
                    problemDAO.getCurrentLevel(problemDAO.integer("select.uid.by.username", getUsername(principal)), cid);
                }
                
                IPaginatedList<Problem> problems = problemDAO.getContestProblems(found,locale.getLanguage(),getUsername(principal), contest, options);                
                model.addAttribute("problems", problems);
            }
        }
        return "/tables/vproblems";
    }

    @RequestMapping(value = "/vproblem.xhtml", method = RequestMethod.GET)
    public String VirtualProblem(Locale locale,Model model, Principal principal, @RequestParam Integer pid) {
        int cid = contestDAO.getVirtualRunning(getUsername(principal));
        int father = contestDAO.integer("load.virtual.contest.father", getUsername(principal));
        if (cid > 0) {
            Contest contest = contestDAO.loadVirtualContest(cid);
            model.addAttribute("contest", contest);
            model.addAttribute("problem", problemDAO.getProblemContest(locale.getLanguage(),pid, new Contest(father)));
        }
        
        return "/practice/vproblem";
    }
}
