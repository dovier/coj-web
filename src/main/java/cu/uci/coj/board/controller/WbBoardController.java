package cu.uci.coj.board.controller;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import cu.uci.coj.board.dao.WbContestDAO;
import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.board.service.WbContestService;
import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

@Controller
public class WbBoardController extends BaseController {
	@Resource
	WbContestService wbContestService;
	@Resource
	UserDAO userDAO;
	@Resource
	WbSiteDAO wbSiteDAO;
	
	WbContestDAO wbContestDAO;	

	@RequestMapping(value = "/wboard/contests.xhtml", method = RequestMethod.GET)
	public String listContests(Model model) {
		model.addAttribute("sites", wbSiteDAO.getSiteList());		
		return "/wboard/contests";
	}
	
	@RequestMapping(value = "/tables/wbcontests.xhtml", method = RequestMethod.GET)
	public String tablesListContests(Model model, Principal principal, PagingOptions options,
			@RequestParam(required = false, defaultValue = "0") Integer site,
			@RequestParam(required = false, defaultValue = "0") Integer followed) {	
		
		if(options.getDirection() == null) {
			options.setDirection("asc");
			options.setSort("startDate");
		}
		
		Integer uid = null;
		if(getUsername(principal) != null) {
			uid = userDAO.idByUsername(getUsername(principal));
		} else {
			followed = 0;
		}
		
		IPaginatedList<WbContest> contests = wbContestService.getContestList(site, options, followed, uid);	
		
		List<WbSite> list = wbSiteDAO.getSiteList();
		HashMap<Integer, WbSite> map =  new HashMap<Integer, WbSite>();
		
		for(int i = 0;i<list.size();i++) {
			map.put(list.get(i).getSid(), list.get(i));
		}		
	
		
		model.addAttribute("mapsites", map);
		model.addAttribute("contests", contests);
		
		return "/tables/wbcontests";
	}
	
	@RequestMapping(value = "/wboard/follow.xhtml", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void followSite(Principal principal, @RequestParam Integer sid) {
		try {
			Integer	uid = userDAO.idByUsername(getUsername(principal));
			wbSiteDAO.followSite(uid, sid);
		} catch (Exception e){}	
	}
	
	@RequestMapping(value = "/wboard/unfollow.xhtml", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void unfollowSite(Principal principal, @RequestParam Integer sid) {
		try {
			Integer	uid = userDAO.idByUsername(getUsername(principal));
			wbSiteDAO.unfollowSite(uid, sid);
		} catch (Exception e){}	
	}
	
	@RequestMapping(value = "/wboard/combobox.xhtml", method = RequestMethod.GET)
	public String loadCombobox(Model model, Principal principal, @RequestParam Integer followed) {
		Integer	uid = userDAO.idByUsername(getUsername(principal));
		if(followed == 0) {
			if(uid != null) {
				List<Integer> list = wbSiteDAO.getIdSiteListFollowed(uid);
				HashMap<Integer, Boolean> mapIds = new HashMap<Integer, Boolean>();			
				for(int i = 0;i<list.size();i++)
					mapIds.put(list.get(i), true);
				model.addAttribute("mapIds", mapIds);
			}			
			
			model.addAttribute("sites", wbSiteDAO.getSiteList());
		} else {
			model.addAttribute("sites", wbSiteDAO.getSiteListFollowed(uid));
		}
		model.addAttribute("followed", followed);		
		return "/wboard/combobox";
	}
}