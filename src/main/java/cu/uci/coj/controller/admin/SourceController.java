package cu.uci.coj.controller.admin;

import javax.annotation.Resource;

import cu.uci.coj.validator.ProblemSourceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.model.ProblemSource;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.utils.Notification;

@Controller
@RequestMapping(value = "/admin")
public class SourceController extends BaseController {

    @Resource
    private ProblemDAO problemDAO;

    @Resource
    private ProblemSourceValidator problemSourceValidator;

    @RequestMapping(value = "/addsource.xhtml", method = RequestMethod.GET)
    public String createSite(Model model) {
        model.addAttribute("wproblemSource", new ProblemSource());
        return "/admin/wbsource/create";
    }

    @RequestMapping(value = "/addsource.xhtml", method = RequestMethod.POST)
    public String addSources(Model model, @RequestParam(required = false, value = "username") String filter_user, @RequestParam(required = false, value = "pid") Integer pid,
                             @RequestParam(required = false, value = "status") String status, @RequestParam(required = false, value = "planguage") String language,
                             PagingOptions options, @RequestParam(required = true, value = "name") String name,
                             @RequestParam(required = true, value = "author") String author, ProblemSource problemSource, BindingResult result, RedirectAttributes redirectAttributes) {

        problemSourceValidator.validate(problemSource, result);

        if (result.hasErrors()){
            model.addAttribute("wproblemSource", problemSource);
            return "/admin/wbsource/create";
        }

        problemDAO.insertProblemSource(name, author);
        IPaginatedList<ProblemSource> sources = problemDAO.getProblemSources(options);

        model.addAttribute("sources", sources);
        redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullCreate());
        return "redirect:/admin/managesources.xhtml";
    }

    @RequestMapping(value = "/managesources.xhtml", method = RequestMethod.GET)
    public String allSources(Model model, PagingOptions options) {
        return "/admin/wbsource/list";
//        return "/admin/managesources";
    }

    @RequestMapping(value = "/tables/managesources.xhtml", method = RequestMethod.GET)
    public String tableAllSources(Model model, PagingOptions options) {
        model.addAttribute("sources", problemDAO.paginated("select.problemsources", ProblemSource.class, 20, options));

        return "/admin/tables/managesources";
    }



    @RequestMapping(value = "/updatesource.xhtml", method = RequestMethod.GET)
    public String updateSources(Model model, @RequestParam(required = true, value = "idSource") Integer idSource) {

        ProblemSource problemSource = problemDAO.getProblemSourceById(idSource);
        model.addAttribute("wproblemSource", problemSource);
        return "/admin/wbsource/edit";
    }

    @RequestMapping(value = "/updatesource.xhtml", method = RequestMethod.POST)
    public String updateSourcesPost(Model model, @RequestParam(required = true, value = "idSource") Integer idSource, @RequestParam(required = true, value = "name") String name,
                                @RequestParam(required = true, value = "author") String author, RedirectAttributes redirectAttributes) {
        problemDAO.updateProblemSource(idSource, name, author);
        redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullUpdate());
        return "redirect:/admin/managesources.xhtml";
    }

    @RequestMapping(value = "/detailssource.xhtml", method = RequestMethod.GET)
    public String detailsContest(Model model, @RequestParam("idSource") Integer idSource) {

        ProblemSource problemSource = problemDAO.getProblemSourceById(idSource);

        model.addAttribute("wproblemSource", problemSource);
        return "/admin/wbsource/details";
    }

    @RequestMapping(value = "/deletesource.xhtml", method = RequestMethod.GET)
    public String deleteSources(Model model, @RequestParam(required = true, value = "idSource") Integer idSource, RedirectAttributes redirectAttributes) {
        problemDAO.deleteProblemSource(idSource);
        redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullDelete());
        return "redirect:/admin/managesources.xhtml";
    }
}
