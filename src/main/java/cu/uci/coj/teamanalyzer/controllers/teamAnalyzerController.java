/*
* teamAnalyzerController.java
*
* v1.0
*
* 14/05/2016
*/

package cu.uci.coj.teamanalyzer.controllers;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.teamanalyzer.dao.analysisDAO;
import cu.uci.coj.teamanalyzer.models.analysis;
import cu.uci.coj.teamanalyzer.models.analyzerTeam;
import cu.uci.coj.teamanalyzer.utils.analysisStats;
import cu.uci.coj.teamanalyzer.utils.teamStats;
import cu.uci.coj.teamanalyzer.utils.userStats;
import cu.uci.coj.teamanalyzer.validators.analysisValidator;
import cu.uci.coj.utils.Notification;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

@Controller("teamAnalyzerController")
@RequestMapping(value = "/teamanalyzer")

public class teamAnalyzerController extends BaseController {

    @Resource
    private analysisDAO analysisDAO;
    @Resource
    private UserDAO userDAO;
    @Resource
    private ContestDAO contestDAO;
    @Resource
    private analysisValidator analysisValidator;

    @RequestMapping(value = "/main.xhtml", method = RequestMethod.GET)
    public String listAnalysis() {
        return "/teamanalyzer/main";
    }


    @RequestMapping(value = "/deleteAnalysis.xhtml", method = RequestMethod.GET)
    public String deleteAnalysis(Principal principal, @RequestParam(required = true, value = "taid") int taid,
                                 RedirectAttributes redirectAttributes) {
        analysisDAO.deleteAnalysis(taid);
        analysisDAO.registerDeletedAnalysis(getUsername(principal), taid);
        redirectAttributes.addFlashAttribute("message", Notification.getDeleteAnalysis());
        return "redirect:/teamanalyzer/main.xhtml";
    }

    @RequestMapping(value = "/viewAnalysis.xhtml", method = RequestMethod.GET)
    public String viewAnalysis(Model model, @RequestParam(required = true, value = "taid") int taid) {
        model.addAttribute("taid", taid);
        return "/teamanalyzer/viewAnalysis";
    }

    @RequestMapping(value = "/dataAnalysis.xhtml", method = RequestMethod.GET)
    public String generateData(Model model, Principal principal, @RequestParam(required = true, value = "taid") int taid) {
        if (taid == 0) {
            analysis analysis = new analysis();
            model.addAttribute(analysis);
            model.addAttribute("allusers", userDAO.loadUsefulUsersForAnalysis(getUid(principal)));
            model.addAttribute("allcontests", contestDAO.loadUsefulContestForAnalysis());
        } else {
            analysis analysis = analysisDAO.getAnalysisById(taid);
            analysis.setUsers(userDAO.loadUsefulUsersInAnalysis(taid));
            analysis.setContest(contestDAO.loadUsefulContestInAnalysis(taid));
            model.addAttribute(analysis);
            model.addAttribute("allusers", userDAO.loadUsefulUsersOffAnalysis(getUid(principal), taid));
            model.addAttribute("allcontests", contestDAO.loadUsefulContestOffAnalysis(taid));
        }

        return "/teamanalyzer/dataAnalysis";
    }

    @RequestMapping(value = "/dataAnalysis.xhtml", method = RequestMethod.POST)
    public String saveAnalysis(Model model, Principal principal, analysis analysis, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        analysisValidator.validate(analysis, bindingResult);
        if (bindingResult.hasErrors()) {
            if (analysis.getId() == 0) {
                model.addAttribute(analysis);
                model.addAttribute("allusers", userDAO.loadUsefulUsersForAnalysis(getUid(principal)));
                model.addAttribute("allcontests", contestDAO.loadUsefulContestForAnalysis());
            } else {
                analysis.setUsers(userDAO.loadUsefulUsersInAnalysis(analysis.getId()));
                analysis.setContest(contestDAO.loadUsefulContestInAnalysis(analysis.getId()));
                model.addAttribute(analysis);
                model.addAttribute("allusers", userDAO.loadUsefulUsersOffAnalysis(getUid(principal), analysis.getId()));
                model.addAttribute("allcontests", contestDAO.loadUsefulContestOffAnalysis(analysis.getId()));
            }
            return "/teamanalyzer/dataAnalysis";
        }
        if (analysis.getId() == 0) {
            analysis.setCoach(userDAO.idByUsername(getUsername(principal)));
            analysisDAO.saveAnalysis(analysis);
            analysis.setId(analysisDAO.integer("max.available.analysis.id"));
            analysisDAO.saveContestsAnalysis(analysis);
            analysisDAO.saveUsersAnalysis(analysis);
            analysisDAO.registerNewAnalysis(getUsername(principal));
            redirectAttributes.addFlashAttribute("message", Notification.getCreateAnalysis());
        } else {
            analysisDAO.updateNameAnalysis(analysis);
            analysisDAO.updateContestsAnalysis(analysis);
            analysisDAO.updateUsersAnalysis(analysis);
            analysisDAO.clearTeamData(analysis);
            analysisDAO.registerEditedAnalysis(getUsername(principal), analysis.getId());
            redirectAttributes.addFlashAttribute("message", Notification.getEditAnalysis());
        }
        doAnalysis(analysis);
        return "redirect:/teamanalyzer/main.xhtml";
    }

    @RequestMapping(value = "/analysisList.xhtml", method = RequestMethod.GET)
    public String tableAnalysis(Model model, Principal principal, PagingOptions options) {

        int uid = getUid(principal);
        List<analysis> analysisList = analysisDAO.getAnalysisesByCoach(uid);
        int found = analysisList.size();

        if (found != 0) {
            IPaginatedList<analysis> analysis = analysisDAO.getPaginatedList(found, uid, options);
            model.addAttribute("analysis", analysis);
        }

        return "/teamanalyzer/analysisList";
    }

    @RequestMapping(value = "/viewTeamsList.xhtml", method = RequestMethod.GET)
    public String tableTeams(Model model, PagingOptions options, @RequestParam(required = true, value = "taid") int taid) {

        List<analyzerTeam> analyzerTeamList = analysisDAO.getTeamsByAnalysisId(taid);
        int found = analyzerTeamList.size();

        if (found != 0) {
            IPaginatedList<analyzerTeam> teams = analysisDAO.getPaginatedTeamList(found, taid, options);
            model.addAttribute("teams", teams);
        }

        return "/teamanalyzer/viewTeamsList";
    }

    @RequestMapping(value = "/viewGraph.xhtml", method = RequestMethod.GET)
    public String generateGraph(Model model, @RequestParam(required = true, value = "tid") int tid, @RequestParam(required = true, value = "taid") int taid) {
        teamStats teamStats = analysisDAO.getTeamStats(tid);
        model.addAttribute("teamStats", teamStats);
        model.addAttribute("taid", taid);
        model.addAttribute("tid", tid);
        return "/teamanalyzer/viewGraph";
    }

    private void doAnalysis(analysis analysis) {
        int[] idsClassifications = analysisDAO.getIdClassifications();
        List<userStats> usersStats = analysisDAO.getUsersStats(analysis, idsClassifications);
        analysisStats analysisStats = new analysisStats(usersStats, idsClassifications, analysis, analysisDAO);
        analysisStats.makeTeams();
    }
}
