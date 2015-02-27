package cu.uci.coj.controller.practice;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONObject;
import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Filter;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.Roles;
import cu.uci.coj.model.User;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.virtualcontestValidator;

@Controller
public class VirtualContestController extends BaseController {

	@Resource
	private ContestDAO contestDAO;
	@Resource
	private UserDAO userDAO;
	@Resource
	private ProblemDAO problemDAO;
	@Resource
	private virtualcontestValidator virtualContestValidator;

	
	
	@RequestMapping(value = "/practice/vcontestview.xhtml", method = RequestMethod.GET)
	public String ContestView(Model model, Principal principal) {
		Integer cid = contestDAO.integer("virtual.running", userDAO.idByUsername(getUsername(principal)));
		if (cid != null) {
			Contest contest = contestDAO.loadVirtualContest(cid);
			model.addAttribute("planguages", contestDAO.getContestLanguagesVirtual(cid));
			model.addAttribute("styles", contestDAO.loadEnabledScoringStyles());
			model.addAttribute("contest", contest);
		}
		return "/practice/vcontestview";
	}

	@RequestMapping(value = "/practice/delete.xhtml", method = RequestMethod.GET)
	public String DeleteVirtual(Model model, Principal principal, Integer cid) {
		contestDAO.deleteVirtualContestCid(cid);
		return "redirect:/practice/mylist.xhtml";
	}

	@RequestMapping(value = "/practice/joinvirtual.xhtml", method = RequestMethod.GET)
	public String JoinVirtualContest(HttpServletRequest request, Model model, Principal principal, @RequestParam("vid") Integer vid) {
		contestDAO.joinVirtualContest(vid, userDAO.idByUsername(getUsername(principal)), request.isUserInRole(Roles.ROLE_ADMIN));
		return "redirect:/practice/mylist.xhtml";
	}

	@RequestMapping(value = "/practice/mylist.xhtml", method = RequestMethod.GET)
	public String MyList(Model model, Principal principal, PagingOptions options) {
		return "/practice/mylist";
	}
	
	@RequestMapping(value = "/tables/mylist.xhtml", method = RequestMethod.GET)
	public String tablesMyList(Model model, Principal principal, PagingOptions options) {
		model.addAttribute("contests", contestDAO.loadUserVirtualContests(userDAO.idByUsername(getUsername(principal)), options));
		return "/tables/mylist";
	}

	@RequestMapping(value = "/practice/globallist.xhtml", method = RequestMethod.GET)
	public String GlobalList(Model model, HttpServletRequest request, PagingOptions options, @RequestParam(required = false, value = "cid") String cid,
			@RequestParam(required = false, value = "creator") String username, @RequestParam(required = false, defaultValue = "0", value = "access") Integer access,
			@RequestParam(required = false, defaultValue = "0", value = "status") Integer status) {
		Filter filter = new Filter();
		filter.setContest_id(cid);
		filter.setUsername(username);
		filter.setAccess(access);
		filter.setVstatus(status);
		model.addAttribute("filter", filter);
		model.addAttribute("isadmin", request.isUserInRole(Roles.ROLE_ADMIN));
		return "/practice/globallist";
	}
	
	@RequestMapping(value = "/tables/globallist.xhtml", method = RequestMethod.GET)
	public String tableGlobalList(Model model, HttpServletRequest request, PagingOptions options, @RequestParam(required = false, value = "cid") String cid,
			@RequestParam(required = false, value = "creator") String username, @RequestParam(required = false, defaultValue = "0", value = "access") Integer access,
			@RequestParam(required = false, defaultValue = "0", value = "status") Integer status) {
		IPaginatedList<Contest> contests = contestDAO.loadGlobalVirtualContests(options, cid, username, access, status);
		model.addAttribute("contests", contests);
		model.addAttribute("isadmin", request.isUserInRole(Roles.ROLE_ADMIN));
		return "/tables/globallist";
	}

	@RequestMapping(value = "/practice/createvc.xhtml", method = RequestMethod.GET)
	public String CreateVC(Locale locale, Model model, Principal principal) {
		Contest contest = new Contest();
		Date date = new Date();
		contest.setIyear(date.getYear() + 1900);
		contest.setIday(date.getDate());
		contest.setImonth(date.getMonth() + 1);
		contest.setIhour(date.getHours());
		contest.setIminutes(date.getMinutes());
		contest.setIseconds(date.getSeconds());
		contest.setStyle(1);
		model.addAttribute("contest", contest);
		model.addAttribute("contests", contestDAO.loadVirtualTemplates());
		model.addAttribute("allusers", userDAO.loadUsersButOne(getUsername(principal)));
		model.addAttribute("problems", problemDAO.getProblemsOffContestVirtual(locale.getLanguage()));
		return "/practice/createvc";
	}

	@RequestMapping(value = "/practice/createvc.xhtml", method = RequestMethod.POST)
	public String CreateVC(Locale locale, Model model, Principal principal, Contest contest, BindingResult result) {
		virtualContestValidator.validate(contest, result);
		if (result.hasErrors()) {
			model.addAttribute("contest", contest);
			model.addAttribute("contests", contestDAO.loadVirtualTemplates());
			model.addAttribute("allusers", userDAO.loadUsersButOne(getUsername(principal)));
			model.addAttribute("problems", problemDAO.getProblemsOffContestVirtual(locale.getLanguage()));
			return "/practice/createvc";
		}
		contestDAO.deleteVirtualContest(userDAO.idByUsername(getUsername(principal)));
		int cid = contestDAO.createVirtualContest(contest, userDAO.idByUsername(getUsername(principal)), getUsername(principal));
		contest.setCid(cid);
		for (int i = 0; i < contest.getUsersids().length; i++) {
			int uid = userDAO.idByUsername((String) contest.getUsersids()[i]);
			contestDAO.joinVirtualContest(cid, uid, true);
		}
		
		List<Problem> problems;
		if (!ArrayUtils.isEmpty(contest.getProblemids())) {
			problems = new LinkedList<Problem>();
			for (int i = 0; i < contest.getProblemids().length; i++) {
				problems.add(new Problem(contest.getProblemids()[i]));
			}
		} else {
			problems = contestDAO.loadContestProblems(contest.getTemplate());
		}
		for (Iterator<Problem> it = problems.iterator(); it.hasNext();) {
			Problem problem = it.next();
			contestDAO.dml("insert.virtual.contest.problem", cid, problem.getPid());
		}
		return "redirect:/practice/mylist.xhtml";
	}

	@RequestMapping(value = "/practice/vscoreboard.xhtml", method = RequestMethod.GET)
	public String VirtualScoreboard(Locale locale, Model model, Principal principal, @RequestParam(required = false, value = "start", defaultValue = "1") Integer number) {
		if (number <= 0) {
			number = 1;
		}

		int cid = contestDAO.getVirtualRunning(getUsername(principal));
		String interval1 = "", interval2 = "";
		Contest contest = contestDAO.loadVirtualContest(cid);
		int father = contestDAO.integer("load.virtual.contest.father", getUsername(principal));
		contest.setVid(father);
		model.addAttribute("contest", contest);
		if (cid > 0) {
			switch (contest.getStyle()) {
			case 1: {
				List<Problem> problems = problemDAO.getVirtualContestProblems(locale.getLanguage(), getUsername(principal));
				List<User> users = contestDAO.getRankingACMVirtual(cid, getUsername(principal), problems);
				Map<Integer, Integer> uidPos = new HashMap<Integer, Integer>();
				for (Iterator<User> it = users.iterator(); it.hasNext();) {
					User user = it.next();
					uidPos.put(user.getUid(), user.getRank() - 1);
				}
				contestDAO.buildRankingACMVirtual(problems, users, uidPos, contest, getUsername(principal), userDAO.integer("user.uid", getUsername(principal)));

				for (int i = 0; i < problems.size(); i++) {
					problems.get(i).stats();
				}
				int i = 0;
				while (i < users.size() && i < contest.getGold()) {
					if (users.get(i).getAcc() == 0) {
						break;
					}
					users.get(i).setMedal(User.GOLD);
					i++;
				}

				while (i < users.size() && (i - contest.getGold()) < contest.getSilver()) {
					if (users.get(i).getAcc() == 0) {
						break;
					}
					users.get(i).setMedal(User.SILVER);
					i++;
				}

				while (i < users.size() && (i - contest.getGold() - contest.getSilver()) < contest.getBronze()) {
					if (users.get(i).getAcc() == 0) {
						break;
					}
					users.get(i).setMedal(User.BRONZE);
					i++;
				}
				// Collections.so
				shellSortByAcceptedAndPenalty(users);
				model.addAttribute("users", users);
				model.addAttribute("problems", problems);
				break;
			}
			case 2: {
				break;
			}
			case 3: {
				break;
			}

			}
		}
		return "/practice/vscoreboard";
	}

	@RequestMapping(value = "/practice/getvirtualtemplates.xhtml", method = RequestMethod.GET)
	public String getInstitutions(Model model, @RequestParam("style") Integer style) throws Exception {
		List<Contest> contests = contestDAO.loadVirtualTemplates(style);
		JSONArray result = new JSONArray();
		for (Iterator<Contest> it = contests.iterator(); it.hasNext();) {
			Contest object = (Contest) it.next();
			JSONObject obj = new JSONObject();
			obj.accumulate("cid", object.getCid());
			obj.accumulate("name", object.getName());
			result.add(obj);
		}
		model.addAttribute("contests", result.toString());
		return "/practice/getvirtualtemplates";
	}

	private void shellSortByAcceptedAndPenalty(List<User> a) {
		for (int increment = a.size() / 2; increment > 0; increment = (increment == 2 ? 1 : (int) Math.round(increment / 2.2))) {
			for (int i = increment; i < a.size(); i++) {
				for (int j = i; j >= increment
						&& ((a.get(j - increment).getAcc() < a.get(j).getAcc()) || (a.get(j - increment).getAcc() == a.get(j).getAcc() && a.get(j - increment).getPenalty() > a.get(j).getPenalty())); j -= increment) {
					User temp = a.get(j);
					temp.setRank(j - increment + 1);
					User temp1 = a.get(j - increment);
					temp1.setRank(j + 1);
					a.set(j, temp1);
					a.set(j - increment, temp);
				}
			}
		}
	}
}
