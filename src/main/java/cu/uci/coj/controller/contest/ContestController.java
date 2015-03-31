package cu.uci.coj.controller.contest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cu.uci.coj.config.Config;
import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Roles;
import cu.uci.coj.utils.Utils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

@Controller
public class ContestController extends BaseController {

	private static final long serialVersionUID = -6646734441619283150L;
	@Resource
	private ContestDAO contestDAO;
	@Resource
	private Utils utils;

	@RequestMapping(value = "/contest/gallery.xhtml", method = RequestMethod.GET)
	public String cgallery(Model model, @RequestParam("cid") Integer cid) {
		Contest contest = contestDAO.loadContestFull(cid);
		model.addAttribute("contest", contest);
		model.addAttribute("images", utils.getContestImages(cid));
		return "/contest/gallery";
	}

	public List<Map<String,Object>> contestSubmissions(int cid, String username) {
		String sql = "select cs.status as state, count(cs.status) as cstats from contest_submition cs join users using(uid) where cs.enabled = true and cs.virtual=false";
		if (!StringUtils.isEmpty(username)) {
			sql += " and cid = ? and users.username = ? group by cs.status order by state ";
			return contestDAO.maps(sql, cid, username);
		} else {
			sql += " and cid = ? group by cs.status order by state ";
			return contestDAO.maps(sql, cid);
		}
	}

	// devuelve los datos relacionados con los envios en un concurso
	@RequestMapping(produces = "application/json", value = "/contest/contestsubmits.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody() List<String>[] contestSubmits(@RequestParam int cid,
			@RequestParam(required = false) String username) {

		List<Map<String,Object>> mapa = contestSubmissions(cid, username);
		List<String> tags = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		List[] other = new ArrayList[2];
		for (Map<String,Object> u : mapa) {
			tags.add(Config.getProperty(String.valueOf(u.get("state"))).replace(" ", "."));
			values.add(String.valueOf(String.valueOf(u.get("cstats"))));
		}
		other[0] = tags;
		other[1] = values;
		return other;
	}

	@RequestMapping(value = "/contest/cballoontracker.xhtml", method = RequestMethod.GET)
	public String cballoontracker(Model model, PagingOptions options,
			@RequestParam(required = false, value = "group") String group,
			@RequestParam("cid") Integer cid) {
		Contest contest = contestDAO.loadContest(cid);
		List<String> groups = contestDAO.getGroups(cid);
		if ("".equals(group))
			group = null;
		model.addAttribute("contest", contest);
		model.addAttribute("groups", groups);
		model.addAttribute("selGroup", group);
		return "/contest/cballoontracker";
	}

	@RequestMapping(produces = "application/json", value = "/contest/markballoon.json", method = RequestMethod.GET, headers = { "Accept=application/json" })
	public @ResponseBody int markBalloon(Model model,
			@RequestParam(value = "sid") int sid) {
		contestDAO.markBalloon(sid);
		return sid;
	}

	@RequestMapping(value = "/tables/cballoontracker.xhtml", method = RequestMethod.GET)
	public String tableCballoontracker(Model model,
			SecurityContextHolderAwareRequestWrapper requestWrapper,
			Principal principal, PagingOptions options,
			@RequestParam(required = false, value = "group") String group,
			@RequestParam("cid") Integer cid) {
		model.addAttribute("submits",
				contestDAO.pendingBalloons(cid, options, group));
		boolean isBalloonTracker = requestWrapper
				.isUserInRole(Roles.ROLE_ADMIN)
				|| contestDAO.bool("balloontracker.in.contest",
						getUid(principal), cid);
		model.addAttribute("showBalloonMark", isBalloonTracker);
		return "/tables/cballoontracker";
	}

	@RequestMapping(value = "/contest/contestview.xhtml", method = RequestMethod.GET)
	public String ContestView(Model model, PagingOptions options,
			@RequestParam("cid") Integer cid) {
		Contest contest = contestDAO.loadContestFull(cid);
		model.addAttribute("planguages", contestDAO.getContestLanguages(cid));
		model.addAttribute("styles", contestDAO.loadEnabledScoringStyles());
		model.addAttribute("contest", contest);
		Long timeToInit = ((contest.getInitdate().getTime()) - new Date()
				.getTime()) / 1000L;
		model.addAttribute("timeToInit", timeToInit);
		return "/contest/contestview";
	}

	@RequestMapping(value = "/contest/past.xhtml", method = RequestMethod.GET)
	public String PastList(Model model, PagingOptions options,
			@RequestParam(value = "pattern", required = false) String pattern) {
		return "/contest/past";
	}

	@RequestMapping(value = "/tables/past.xhtml", method = RequestMethod.GET)
	public String tablesPastList(Model model, PagingOptions options,
			@RequestParam(value = "pattern", required = false) String pattern) {
		IPaginatedList<Contest> contests = contestDAO.getPastContests(options,
				pattern);
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
