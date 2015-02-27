package cu.uci.coj.controller.contest;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.model.Contest;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

@Controller
public class ContestController extends BaseController {

	@Resource
	private ContestDAO contestDAO;

	@RequestMapping(value = "/contest/cballoontracker.xhtml", method = RequestMethod.GET)
	public String cballoontracker(Model model, PagingOptions options, @RequestParam(required=false,value="group") String group, @RequestParam("cid") Integer cid) {
		Contest contest = contestDAO.loadContest(cid);
		List<String> groups = contestDAO.getGroups(cid);
		if ("".equals(group))
			group = null;
		model.addAttribute("contest", contest);
		model.addAttribute("groups",groups);
		model.addAttribute("selGroup",group);
		return "/contest/cballoontracker";
	}
	
	@RequestMapping(value = "/tables/cballoontracker.xhtml", method = RequestMethod.GET)
	public String tableCballoontracker(Model model, PagingOptions options, @RequestParam(required=false,value="group") String group, @RequestParam("cid") Integer cid) {
		model.addAttribute("submits", contestDAO.pendingBalloons(cid,options,group));
		return "/tables/cballoontracker";
	}
	
	
	@RequestMapping(value = "/contest/contestview.xhtml", method = RequestMethod.GET)
	public String ContestView(Model model, PagingOptions options, @RequestParam("cid") Integer cid) {
		Contest contest = contestDAO.loadContestFull(cid);
		model.addAttribute("planguages", contestDAO.getContestLanguages(cid));
		model.addAttribute("styles", contestDAO.loadEnabledScoringStyles());
		model.addAttribute("contest", contest);
		Long timeToInit = ((contest.getInitdate().getTime()) - new Date().getTime())/1000L;
		model.addAttribute("timeToInit",timeToInit);
		return "/contest/contestview";
	}

	@RequestMapping(value = "/contest/past.xhtml", method = RequestMethod.GET)
	public String PastList(Model model, PagingOptions options, @RequestParam(value = "pattern", required = false) String pattern) {
		return "/contest/past";
	}
	
	@RequestMapping(value = "/tables/past.xhtml", method = RequestMethod.GET)
	public String tablesPastList(Model model, PagingOptions options, @RequestParam(value = "pattern", required = false) String pattern) {
		IPaginatedList<Contest> contests = contestDAO.getPastContests(options, pattern);
		model.addAttribute("contests", contests);
		return "/tables/past";
	}

	@RequestMapping(value = "/contest/running.xhtml", method = RequestMethod.GET)
	public String RunningList(Model model, PagingOptions options) {
		return "/contest/running";
	}

	@RequestMapping(value = "/tables/running.xhtml", method = RequestMethod.GET)
	public String tablesRunningList(Model model, PagingOptions options) {
		model.addAttribute("contests", contestDAO.getRunningContests(options));
		return "/tables/running";
	}
	
	@RequestMapping(value = "/contest/coming.xhtml", method = RequestMethod.GET)
	public String ComingList(Model model, PagingOptions options) {
		return "/contest/coming";
	}
	
	@RequestMapping(value = "/tables/coming.xhtml", method = RequestMethod.GET)
	public String tablesComingList(Model model, PagingOptions options) {
		model.addAttribute("contests", contestDAO.getComingContests(options));
		return "/tables/coming";
	}
}
