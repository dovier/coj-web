
/*
 * Created by ricardo on 15/05/16.
 */

package cu.uci.coj.teamanalyzer.controllers;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.teamanalyzer.dao.analysisDAO;
import cu.uci.coj.teamanalyzer.models.analysis;
import cu.uci.coj.teamanalyzer.validators.analysisValidator;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String listAnalysis(Model model){
        return "/teamanalyzer/main";
    }


    @RequestMapping(value = "/deleteAnalysis.xhtml", method = RequestMethod.GET)
    public String deleteAnalysis(Principal principal, @RequestParam(required = true, value = "taid") int taid) {
        analysisDAO.deleteAnalysis(taid);
        analysisDAO.registerDeletedAnalysis(getUsername(principal), taid);
        return "/teamanalyzer/deleteAnalysis";
    }

    @RequestMapping(value = "/viewAnalysis.xhtml", method = RequestMethod.GET)
    public String viewAnalysis(@RequestParam(required = true, value = "taid") int taid) {
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
    public String saveAnalysis(Model model, Principal principal, analysis analysis, BindingResult bindingResult) {
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
        } else {
            analysisDAO.updateNameAnalysis(analysis);
            analysisDAO.updateContestsAnalysis(analysis);
            analysisDAO.updateUsersAnalysis(analysis);
            analysisDAO.registerEditedAnalysis(getUsername(principal),analysis.getId());
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

    private void doAnalysis(analysis analysis) {
    }

    @RequestMapping(value = "/viewGraph.xhtml", method = RequestMethod.GET)
    public String generateGraph(@RequestParam(required = true, value = "taid") int taid) {

        return "/teamanalyzer/viewGraph";
    }

}
