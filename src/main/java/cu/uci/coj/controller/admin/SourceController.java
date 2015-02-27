package cu.uci.coj.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.model.ProblemSource;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

@Controller
@RequestMapping(value = "/admin")
public class SourceController extends BaseController {

	@Resource
	private ProblemDAO problemDAO;

	@RequestMapping(value = "/addsource.xhtml", method = RequestMethod.POST)
	public String addSources(Model model, @RequestParam(required = false, value = "username") String filter_user, @RequestParam(required = false,value="pid") Integer pid,
			@RequestParam(required = false, value = "status") String status, @RequestParam(required = false, value = "planguage") String language,
			PagingOptions options, @RequestParam(required = true, value = "name") String name,
			@RequestParam(required = true, value = "author") String author) {

		if (StringUtils.hasText(name) || StringUtils.hasText(author)) {
			problemDAO.insertProblemSource(name, author);
			IPaginatedList<ProblemSource> sources = problemDAO.getProblemSources(options);

			model.addAttribute("sources", sources);
		}
		return "redirect:/admin/managesources.xhtml";
	}

	@RequestMapping(value = "/managesources.xhtml", method = RequestMethod.GET)
	public String allSources(Model model, PagingOptions options) {
		return "/admin/managesources";
	}
	
	@RequestMapping(value = "/tables/managesources.xhtml", method = RequestMethod.GET)
	public String tableAllSources(Model model, PagingOptions options) {
		model.addAttribute("sources", problemDAO.paginated("select.problemsources",ProblemSource.class,20,options));

		return "/admin/tables/managesources";
	}

	@RequestMapping(value = "/updatesource.xhtml", method = RequestMethod.POST)
	public String updateSources(Model model, @RequestParam(required = true, value = "idSource") Integer idSource, @RequestParam(required = true, value = "name") String name,
			@RequestParam(required = true, value = "author") String author) {
		problemDAO.updateProblemSource(idSource, name, author);

		return "/admin/managesources";
	}

	@RequestMapping(value = "/deletesource.xhtml", method = RequestMethod.POST)
	public String deleteSources(Model model, @RequestParam(required = true, value = "idSource") Integer idSource) {
		problemDAO.deleteProblemSource(idSource);

		return "/admin/managesources";
	}
}
