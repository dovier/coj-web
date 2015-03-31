package cu.uci.coj.controller;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cu.uci.coj.model.SharedFile;
import cu.uci.coj.model.User;
import cu.uci.coj.utils.HandlerInterceptorImpl;
import cu.uci.coj.utils.SessionsRecord;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

@Controller
@RequestMapping(value = "/")
public class StaticControllers extends BaseController {

	@RequestMapping(value = "/index.xhtml", method = RequestMethod.GET)
	public String processIndex() {
		return "index";
	}

	@RequestMapping(value = "/general/tos.xhtml", method = RequestMethod.GET)
	public String processTOS() {
		return "/general/tos";
	}
	
	@RequestMapping(value = "/general/tools.xhtml", method = RequestMethod.GET)
	public String processTools() throws FileNotFoundException {
		return "/general/tools";
	}
	
	
	@RequestMapping(value = "/general/maintenance.xhtml", method = RequestMethod.GET)
	public String maintenance(Model model) throws FileNotFoundException {
		if (HandlerInterceptorImpl.isMaintenanceMode()) {
			model.addAttribute("sessions", SessionsRecord.countSessions());
			return "/general/maintenance";
		} else
			return "redirect:/index.xhtml";
	}

	@RequestMapping(value = "/general/about.xhtml", method = RequestMethod.GET)
	public String processAbout() {
		return "/general/about";
	}

	@RequestMapping(value = "/general/documentation.xhtml", method = RequestMethod.GET)
	public String processDocumentation() {
		return "/general/documentation";
	}

	@RequestMapping(value = "/general/contact.xhtml", method = RequestMethod.GET)
	public String processContact() {
		return "/general/contact";
	}

	@RequestMapping(value = "/general/links.xhtml", method = RequestMethod.GET)
	public String processLinks() {
		return "/general/links";
	}

	@RequestMapping(value = "/admin/index.xhtml", method = RequestMethod.GET)
	public String processAdmin(Model model) {
		List<Map<String,Object>> maps = baseDAO.maps("count.users.by.status");
		model.addAttribute("userStatus",maps);
		System.getProperty("webapp.root");
		return "/admin/index";
	}

	@RequestMapping(value = "/error/resource.xhtml", method = RequestMethod.GET)
	public String errorResource(Model model) {
		return "/error/resource";
	}

	@RequestMapping(value = "/error/error.xhtml", method = RequestMethod.GET)
	public String errorGeneric(Model model, @RequestParam(defaultValue = "0", required = false, value = "error") Integer error) {
		model.addAttribute("error", error);
		return "/error/error";
	}
	
	@RequestMapping(value = "/lsb/index.xhtml", method = RequestMethod.GET)
	public String processLsb(@RequestParam("cid") Integer cid, Model model) {
		model.addAttribute("cid", cid);
		return "/lsb/index";
	}

}
