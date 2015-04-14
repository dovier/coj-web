package cu.uci.coj.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import cu.uci.coj.board.dao.WbContestDAO;
import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.board.parsing.WbParserManager;
import cu.uci.coj.controller.BaseController;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.WbContestValidator;
import cu.uci.coj.validator.WbSiteValidator;

@Controller("wbBoardAdminController")
@RequestMapping(value = "/admin")
public class WbBoardAdminController extends BaseController {
	@Resource
	WbParserManager wbParserManager;
	@Resource
	WbSiteDAO wbSiteDAO;
	@Resource
	WbContestDAO wbContestDAO;
	@Resource
	WbSiteValidator wbSiteValidator;
	@Resource
	WbContestValidator wbContestValidator;
	
	
	@InitBinder
	private void dateBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
	    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
	    binder.registerCustomEditor(Date.class, editor);
	}	
	
	//Parser manager
	
	@RequestMapping(value = "/manageparsers.xhtml", method = RequestMethod.GET)
	public String manageParsers(Model model){
		return "/admin/manageparsers";
	}	
	
	@RequestMapping(value = "/tables/manageparsers.xhtml", method = RequestMethod.GET)
	public String tablesManageParsers(Model model){
		List<WbSite> list = wbSiteDAO.getSiteList();
		model.addAttribute("sites", list);
		return "/admin/tables/manageparsers";
	}	
	
	@RequestMapping(value = "/manageparsers/enable.xhtml",method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void enableParsers(@RequestParam Integer sid){
		boolean enabled = wbSiteDAO.isSiteEnabled(sid);
		wbSiteDAO.setSiteEnabled(sid, enabled ^ true);		
	}
	
	@RequestMapping(value = "/manageparsers/parse.json", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public boolean parseParsers(@RequestParam Integer sid){
		System.out.println("Beinign parsing for: " + sid);
		return wbParserManager.parseSite(sid);
	}
	
	//WbSite CRUD
	
	@RequestMapping(value = "/wboard/site/list.xhtml", method = RequestMethod.GET)
	public String listSites(Model model, PagingOptions options){
		return "/admin/wbsite/list";
	}
	
	@RequestMapping(value = "/tables/wbsites.xhtml", method = RequestMethod.GET)
	public String tablesListSites(Model model, PagingOptions options){
		if(options.getDirection() == null) {
			options.setDirection("asc");
			options.setSort("site");
		}
		
		IPaginatedList<WbSite> list = wbSiteDAO.getPaginatedSiteList(options);
		model.addAttribute("sites", list);
		return "/admin/tables/wbsites";
	}
	
	@RequestMapping(value = "/wboard/site/details.xhtml", method = RequestMethod.GET)
	public String detailsSite(Model model, @RequestParam("sid") Integer sid){
		WbSite site = wbSiteDAO.getSiteById(sid);
		
		if(site == null) {
			return "redirect:/error/error.xhtml?error=2";
		}
		
		model.addAttribute("site", site);
		return "/admin/wbsite/details";
	}
	
	@RequestMapping(value = "/wboard/site/create.xhtml", method = RequestMethod.GET)
	public String createSite(Model model){
		WbSite wbSite = new WbSite();
		model.addAttribute("timezones", TimeZone.getAvailableIDs());
		model.addAttribute("wbSite", wbSite);
		return "/admin/wbsite/create";
	}
	
	@RequestMapping(value = "/wboard/site/create.xhtml", method = RequestMethod.POST)
	public String createSitePost(Model model, WbSite wbSite, BindingResult result){
		wbSiteValidator.validate(wbSite, result);
		if(result.hasErrors()) {
			model.addAttribute("timezones", TimeZone.getAvailableIDs());
			model.addAttribute("wbSite", wbSite);
			return "/admin/wbsite/create";
		}		
		wbSiteDAO.insertSite(wbSite);
		return "redirect:/admin/wboard/site/list.xhtml";
	}
	
	@RequestMapping(value = "/wboard/site/edit.xhtml", method = RequestMethod.GET)
	public String editSite(Model model, @RequestParam("sid") Integer sid){
		WbSite wbSite = wbSiteDAO.getSiteById(sid);
		
		if(wbSite == null) {
			return "redirect:/error/error.xhtml?error=2";
		}
		
		model.addAttribute("timezones", TimeZone.getAvailableIDs());
		model.addAttribute("wbSite", wbSite);
		return "/admin/wbsite/edit";
	}
	
	@RequestMapping(value = "/wboard/site/edit.xhtml", method = RequestMethod.POST)
	public String editSitePost(Model model, WbSite wbSite, BindingResult result){
		wbSiteValidator.validateUpdate(wbSite, result);
		if(result.hasErrors()) {
			model.addAttribute("timezones", TimeZone.getAvailableIDs());
			model.addAttribute("wbSite", wbSite);
			return "/admin/wbsite/edit";
		}		
		wbSiteDAO.updateSite(wbSite);
		return "redirect:/admin/wboard/site/list.xhtml";
	}
	
	@RequestMapping(value = "/wboard/site/delete.xhtml", method = RequestMethod.GET)
	public String deleteSite(Model model, @RequestParam("sid") Integer sid){
		try {
			wbSiteDAO.deleteSite(sid);
		} catch(Exception e) {
			return "redirect:/error/error.xhtml?error=2";
		}
		return "redirect:/admin/wboard/site/list.xhtml";
	}
	
	//WbContest CRUD
	
	@RequestMapping(value = "/tables/wbcontests.xhtml", method = RequestMethod.GET)
	public String tablesListContests(Model model, PagingOptions options){
		if(options.getDirection() == null) {
			options.setDirection("desc");
			options.setSort("startdate");
		}
		
		List<WbSite> sitelist = wbSiteDAO.getSiteList();
		HashMap<Integer, WbSite> map =  new HashMap<Integer, WbSite>();
		
		for(int i = 0;i<sitelist.size();i++) {
			map.put(sitelist.get(i).getSid(), sitelist.get(i));
		}
		
		model.addAttribute("mapsites", map);
		
		IPaginatedList<WbContest> list = wbContestDAO.getPaginatedContestList(options);
		model.addAttribute("contests", list);
		model.addAttribute("now", new Date());
		
		return "/admin/tables/wbcontests";
	}
	
	@RequestMapping(value = "/wboard/contest/list.xhtml", method = RequestMethod.GET)
	public String listContests(Model model, PagingOptions options){
		return "/admin/wbcontest/list";
	}
	
	@RequestMapping(value = "/wboard/contest/details.xhtml", method = RequestMethod.GET)
	public String detailsContest(Model model, @RequestParam("id") Integer id){
		WbContest contest = wbContestDAO.getContestById(id);
		
		if(contest == null) {
			return "redirect:/error/error.xhtml?error=2";
		}
		
		List<WbSite> sitelist = wbSiteDAO.getSiteList();
		HashMap<Integer, WbSite> map =  new HashMap<Integer, WbSite>();
		
		for(int i = 0;i<sitelist.size();i++) {
			map.put(sitelist.get(i).getSid(), sitelist.get(i));
		}			
		
		model.addAttribute("mapsites", map);
		
		model.addAttribute("wbcontest", contest);
		return "/admin/wbcontest/details";
	}
	
	@RequestMapping(value = "/wboard/contest/create.xhtml", method = RequestMethod.GET)
	public String createContest(Model model){
		WbContest wbContest = new WbContest();
		model.addAttribute("sites", wbSiteDAO.getSiteList());
		model.addAttribute("wbContest", wbContest);
		return "/admin/wbcontest/create";
	}
	
	@RequestMapping(value = "/wboard/contest/create.xhtml", method = RequestMethod.POST)
	public String createContestPost(Model model, WbContest wbContest, BindingResult result){
		wbContestValidator.validate(wbContest, result);
		if(result.hasErrors()) {
			model.addAttribute("sites", wbSiteDAO.getSiteList());
			model.addAttribute("wbContest", wbContest);
			return "/admin/wbcontest/create";
		}		
		wbContestDAO.insertContest(wbContest);
		return "redirect:/admin/wboard/contest/list.xhtml";
	}
	
	@RequestMapping(value = "/wboard/contest/edit.xhtml", method = RequestMethod.GET)
	public String editContest(Model model, @RequestParam("id") Integer id){
		WbContest wbContest = wbContestDAO.getContestById(id);
		
		if(wbContest == null) {
			return "redirect:/error/error.xhtml?error=2";
		}
		model.addAttribute("sites", wbSiteDAO.getSiteList());
		model.addAttribute("wbContest", wbContest);
		return "/admin/wbcontest/edit";
	}
	
	@RequestMapping(value = "/wboard/contest/edit.xhtml", method = RequestMethod.POST)
	public String editContestPost(Model model, WbContest wbContest, BindingResult result){
		wbContestValidator.validateUpdate(wbContest, result);
		if(result.hasErrors()) {
			model.addAttribute("sites", wbSiteDAO.getSiteList());
			model.addAttribute("wbContest", wbContest);
			return "/admin/wbcontest/edit";
		}		
		wbContestDAO.updateContest(wbContest);
		return "redirect:/admin/wboard/contest/list.xhtml";
	}
	
	@RequestMapping(value = "/wboard/contest/delete.xhtml", method = RequestMethod.GET)
	public String deleteContest(Model model, @RequestParam("id") Integer id){
		try {
			wbContestDAO.deleteContest(id);
		} catch(Exception e) {
			return "redirect:/error/error.xhtml?error=2";
		}
		return "redirect:/admin/wboard/contest/list.xhtml";
	}
}
