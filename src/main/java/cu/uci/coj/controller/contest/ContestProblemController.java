package cu.uci.coj.controller.contest;

import java.security.Principal;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.Roles;
import cu.uci.coj.utils.paging.PagingOptions;

@Controller
public class ContestProblemController extends BaseController {

    @Resource
    private ContestDAO contestDAO;
    @Resource
    private ProblemDAO problemDAO;
    @Resource
    private UserDAO userDAO;

    @RequestMapping(value = "/contest/cproblems.xhtml", method = RequestMethod.GET)
    public String ContestProblems(Locale locale,Model model, SecurityContextHolderAwareRequestWrapper requestWrapper,Principal principal, PagingOptions options, @RequestParam("cid") Integer cid) {
        Contest contest = contestDAO.loadContest(cid);
        contestDAO.unfreezeIfNecessary(contest);
        
        if (contest.isComing()) {
            return "redirect:/contest/contestview.xhtml?cid=" + cid;
        }
        model.addAttribute("contest", contest);
        return "/contest/cproblems";
    }
    
    @RequestMapping(value = "/tables/cproblems.xhtml", method = RequestMethod.GET)
    public String tablesContestProblems(Locale locale,Model model, SecurityContextHolderAwareRequestWrapper requestWrapper,Principal principal, PagingOptions options, @RequestParam("cid") Integer cid) {
        String username = getUsername(principal);
        Contest contest = contestDAO.loadContest(cid);
        contestDAO.unfreezeIfNecessary(contest);
        model.addAttribute("contest", contest);
        if (contest.isRunning() || contest.isPast()) {
            contest.setShow_status(false);
            if (contest.isShow_problem_out() || (username != null && hasAnyPrivileges(requestWrapper,Roles.ROLE_ADMIN,Roles.ROLE_SUPER_PSETTER,Roles.ROLE_PSETTER)) || (!contest.isShow_problem_out() && username != null && contestDAO.isInContest(cid, contestDAO.integer("select.uid.by.username", username)))) {
                contest.setShow_status(true);
                int found = problemDAO.countProblemContest(cid);
                if (found > 0) {
                    model.addAttribute("problems", problemDAO.getContestProblems(found,locale.getLanguage(),username, contest, options));
                }
            }
        }
        
        return "/tables/cproblems";
    }

    @RequestMapping(value = "/contest/cproblem.xhtml", method = RequestMethod.GET)
    public String ContestProblem(Locale locale,Model model, Principal principal, HttpServletRequest request, @RequestParam("cid") Integer cid, @RequestParam Integer pid) {
        String username = getUsername(principal);
        Contest contest = contestDAO.loadContest(cid);
        model.addAttribute("contest", contest);
        if (contest.isRunning() || contest.isPast()) {
            contest.setShow_status(false);
            if (contest.isShow_problem_out() || (username != null && request.isUserInRole(Roles.ROLE_ADMIN)) || (!contest.isShow_problem_out() && username != null && contestDAO.isInContest(cid, contestDAO.integer("select.uid.by.username", username))) || (request.isUserInRole(Roles.ROLE_JUDGE) && contestDAO.isJudgeInContest(cid, userDAO.integer("select.uid.by.username", principal.getName())))) {
                contest.setShow_status(true);
                Problem problem = problemDAO.getContestProblem(locale.getLanguage(),pid, contest);
                problem.setDate(problem.getDate().split("\\.")[0]);
                int current_level = 1;
                if (contest.getStyle() == 4) {
                    if (username != null) {
                        try {
                            current_level = problemDAO.getCurrentLevel(contestDAO.integer("select.uid.by.username", username), cid);
                        } catch (Exception e) {
                        }
                    }
                }
                if (problem.getLevel() > current_level) {
                    return "/error/wproblem";
                } else {
                    problem.setSee(true);
                    problem.setLanguages(contestDAO.getContestLanguages(cid));
                    model.addAttribute("problem", problem);
                }
            } else {
                return "/error/wproblem";
            }
        } else {
            return "/error/wproblem";
        }
        return "/contest/cproblem";
    }
}
