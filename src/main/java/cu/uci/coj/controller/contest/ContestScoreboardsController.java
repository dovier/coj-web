package cu.uci.coj.controller.contest;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;
import cu.uci.coj.controller.BaseController;
import cu.uci.coj.controller.interfaces.IACMScoreboard;
import cu.uci.coj.dao.ContestAwardDAO;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.ContestAwards;
import cu.uci.coj.model.ContestAwardsFlags;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.Roles;
import cu.uci.coj.model.SarisSubmission;
import cu.uci.coj.model.User;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

@Controller
public class ContestScoreboardsController extends BaseController {

	@Resource
	private ContestDAO contestDAO;
	@Resource
	private ContestAwardDAO contestAwardsDAO;
	@Resource
	private ProblemDAO problemDAO;
	@Resource
	private UserDAO userDAO;

	/*
    * RF98 Ver posiciones del concurso
    * */
	@RequestMapping(value = "/contest/contestsrank.xhtml", method = RequestMethod.GET)
	public String UsersRank(Model model, HttpServletRequest request, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern,
			@RequestParam(required = false, defaultValue = "false", value = "online") Boolean online) {
		model.addAttribute("pattern", pattern);
		model.addAttribute("online", online);
		return "/contest/contestsrank";
	}

	/*
    * RF98 Ver posiciones del concurso
    * */
	@RequestMapping(value = "/tables/contestsrank.xhtml", method = RequestMethod.GET)
	public String tablesUsersRank(Model model, HttpServletRequest request, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern,
			@RequestParam(required = false, defaultValue = "false", value = "online") Boolean online) {
		if (pattern!=null) {
			pattern = pattern.replace(" ", "");
		}
		int found = contestDAO.countContestGeneralScoreboard(pattern);
		if (found != 0) {
			IPaginatedList<User> users = contestDAO.getContestGeneralScoreboard(found, pattern, options);
			if (options.getPage() == 1) {
				assigMedals(users.getList());
			}
			model.addAttribute("users", users);
		}
		return "/tables/contestsrank";
	}

	@RequestMapping(value = "/tables/cscoreboard3.xhtml", method = RequestMethod.GET)
	public String ContestScoreboard(Locale locale, Model model, Principal principal, PagingOptions options,
			@RequestParam("cid") Integer cid) {

		Contest contest = contestDAO.loadContest(cid);
		IPaginatedList<User> users = contestDAO.getFreeContestRanking(cid, options);
		if (!users.getList().isEmpty() && users.getList().get(0).getRank() <= contest.getGold() + contest.getSilver() + contest.getBronze()) {
			setMedals(users.getList(), contest, 0);
		}
		model.addAttribute("contest",contest);
		model.addAttribute("users", users);
		return "/tables/cscoreboard3";
	}
	@RequestMapping(value = "/tables/cscoreboard4.xhtml", method = RequestMethod.GET)
	public String ContestScoreboardProgressive(Locale locale, Model model, Principal principal, PagingOptions options,
			@RequestParam("cid") Integer cid) {
		Contest contest = contestDAO.loadContest(cid);
		IPaginatedList<User> users = contestDAO.getFreeContestRanking(cid, options);
		if (!users.getList().isEmpty() && users.getList().get(0).getRank() <= contest.getGold() + contest.getSilver() + contest.getBronze()) {
			setMedals(users.getList(), contest, 0);
		}
		model.addAttribute("contest",contest);
		model.addAttribute("users", users);
		return "/tables/cscoreboard4";
	}

	/*
   * RF20 Repuntear concurso
   * */
	@RequestMapping(value = "/contest/cscoreboard.xhtml", method = RequestMethod.GET)
	public String ContestScoreboard(Locale locale, SecurityContextHolderAwareRequestWrapper requestWrapper, Model model, Principal principal, PagingOptions options, HttpServletRequest request,
			@RequestParam("cid") Integer cid, @RequestParam(required = false, value = "selGroup") String selGroup, @RequestParam(required = false, value = "ungroup") String ungroup,
			@RequestParam(required = false, value = "grouponly") String groupOnly, @RequestParam(required = false, value = "refresh") Boolean refresh) {

		if (refresh == null)
			refresh = false;
		model.addAttribute("refresh", refresh);
		Contest contest = contestDAO.loadContest(cid);

		contestDAO.unfreezeIfNecessary(contest);

		if (contest.isComing()) {
			return "redirect:/contest/contestview.xhtml?cid=" + cid;
		}

		if ((contest.isRunning() || contest.isPast()) && !contest.isRepointing()) {
			if (contest.isShow_scoreboard() || (principal != null && requestWrapper.isUserInRole(Roles.ROLE_ADMIN))) {
				contest.setShow_status(false);
				if (contest.isShow_scoreboard_out() || (principal != null && requestWrapper.isUserInRole(Roles.ROLE_ADMIN))
						|| (!contest.isShow_scoreboard_out() && request.getUserPrincipal() != null && contestDAO.isInContest(cid, contestDAO.integer("users.uid", getUsername(principal))))) {
					contest.setShow_status(true);
                                        switch (contest.getStyle()) {
					case 1: {
						List<Problem> problems = problemDAO.getContestProblems(locale.getLanguage(), cid);
						int[] minimums = contestDAO.getRankingAcmMinimun(cid);
						int min = minimums[0];
						for (int i = 1; i < minimums.length; i++) {
							if (minimums[i] < min) {
								min = minimums[i];
							}
						}

						boolean group = contest.isGrouped() && ungroup == null;
						if (StringUtils.hasText(selGroup))
							group = true;
						IACMScoreboard iacmscoreboard = contestDAO.getRankingAcm(cid, selGroup, group, problems);
						for (int i = 0; i < problems.size(); i++) {
							problems.get(i).stats();
						}

						model.addAttribute("group", group);
						model.addAttribute("selGroup", selGroup);
						if (!group)
							model.addAttribute("users", iacmscoreboard.getUsers());

						if (!contest.isGrouped())
							ungroup = "ungroup";

						if (ungroup == null) {
							iacmscoreboard.groupSort();
							model.addAttribute("cbGroups", contestDAO.strings("ranking.acm.group.names", cid));
							model.addAttribute("groups", iacmscoreboard.getGroups());
						}

						model.addAttribute("problems", problems);
						break;
					}
					case 2: {
						break;
					}
					case 3: {
break;
					}
					case 4: {
						
						break;
					}

					}
				}
			}
		}

		model.addAttribute("contest", contest);
		return "/contest/cscoreboard";
	}

	@Deprecated
	@RequestMapping(value = "/contest/globalcontestscoreboard.xhtml", method = RequestMethod.GET)
	public String GlobalContestScoreboartd(Locale locale, SecurityContextHolderAwareRequestWrapper requestWrapper, Model model, Principal principal, PagingOptions options,
			@RequestParam("cid") Integer cid, @RequestParam(required = false, value = "ungroup") String ungroup) {

		Contest contest = contestDAO.loadContest(cid);
		if (contest.isComing()) {
			return "redirect:/contest/contestview.xhtml?cid=" + cid;
		}
		if (contest.getStyle() == 4) {
			contest.setStyle(3);
		}
		if ((contest.isRunning() || contest.isPast()) && !contest.isRepointing()) {
			if (contest.isShow_scoreboard() || (principal != null && requestWrapper.isUserInRole(Roles.ROLE_ADMIN))) {
				contest.setShow_status(false);
				if (contest.isShow_scoreboard_out() || (principal != null && requestWrapper.isUserInRole(Roles.ROLE_ADMIN))
						|| (!contest.isShow_scoreboard_out() && principal != null && contestDAO.isInContest(cid, contestDAO.integer("users.uid", getUsername(principal))))) {
					contest.setShow_status(true);
					switch (contest.getStyle()) {
					case 1: {
						List<Problem> problems = problemDAO.getContestProblems(locale.getLanguage(), cid);
						int[] ids = new int[] { 1213, 1200, 1199, 1192, 1196, 1214, 1212, 1204, 1193, 1178, 1206, 1202, 1211, 1195, 1208, 1187, 1209, 1198, 1210, 1205, 1191, 1207, 1194 };
						List<Integer> cids = new LinkedList<Integer>();
						for (int i = 0; i < ids.length; i++) {
							int j = ids[i];
							cids.add(j);
						}
						int[] minimums = contestDAO.getGlobalRankingAcmMinimun(cids);
						int min = minimums[0];
						for (int i = 1; i < minimums.length; i++) {
							if (minimums[i] < min) {
								min = minimums[i];
							}
						}

						IACMScoreboard iacmscoreboard = contestDAO.getGlobalRankingAcm(cids, ungroup != null, problems);
						for (int i = 0; i < problems.size(); i++) {
							problems.get(i).stats();
						}

						if (ungroup != null) {
							setMedals(iacmscoreboard.getUsers(), contest, 0);
							model.addAttribute("users", iacmscoreboard.getUsers());
						} else {
							model.addAttribute("group", true);
							for (int i = 0; i < iacmscoreboard.getGroups().size(); i++) {
								setMedals(iacmscoreboard.getGroups().get(i).getUsers(), contest, 0);
							}
							model.addAttribute("groups", iacmscoreboard.getGroups());
						}
						model.addAttribute("problems", problems);
						break;
					}
					case 3:
						IPaginatedList<User> users = contestDAO.getFreeContestRanking(cid, options);
						model.addAttribute("users", users);
						break;
					}
				}
			}
		}
		model.addAttribute("contest", contest);

		return "/contest/globalcontestscoreboard";
	}
	
	@Deprecated
	@RequestMapping(value = "/tables/globalcontestscoreboard.xhtml", method = RequestMethod.GET)
	public String tablesGlobalContestScoreboartd(Locale locale, SecurityContextHolderAwareRequestWrapper requestWrapper, Model model, Principal principal, PagingOptions options,
			@RequestParam("cid") Integer cid) {

		Contest contest = contestDAO.loadContest(cid);
		if (contest.getStyle() == 4) {
			contest.setStyle(3);
		}
		if ((contest.isRunning() || contest.isPast()) && !contest.isRepointing()) {
			if (contest.isShow_scoreboard() || (principal != null && requestWrapper.isUserInRole(Roles.ROLE_ADMIN))) {
				contest.setShow_status(false);
				if (contest.isShow_scoreboard_out() || (principal != null && requestWrapper.isUserInRole(Roles.ROLE_ADMIN))
						|| (!contest.isShow_scoreboard_out() && principal != null && contestDAO.isInContest(cid, contestDAO.integer("users.uid", getUsername(principal))))) {
					contest.setShow_status(true);
					switch (contest.getStyle()) {
					case 1: {
						break;
					}
					case 3:
						IPaginatedList<User> users = contestDAO.getFreeContestRanking(cid, options);
						model.addAttribute("users", users);
						break;
					}
				}
			}
		}
		model.addAttribute("contest", contest);

		return "/tables/globalcontestscoreboard";
	}

	@RequestMapping(value = "/contest/saris.xhtml", method = RequestMethod.GET)
	public String sarisGenerator(Principal principal, SecurityContextHolderAwareRequestWrapper requestWrapper, Locale locale, Model model, HttpServletResponse response,
			@RequestParam("cid") Integer cid, @RequestParam(required = false, value = "group") String group) throws JSONException, IOException {
		if (!(requestWrapper.isUserInRole(Roles.ROLE_ADMIN) || ( contestDAO.isJudgeInContest(cid, userDAO.idByUsername(getUsername(principal)))))) {
			return "/error/accessdenied";
		}
		Contest contest = contestDAO.object("get.contest.forsaris", Contest.class, cid);

		JSONObject json = new JSONObject();
		contest.setProblems(problemDAO.getContestProblems(locale.getLanguage(), cid));
		List<SarisSubmission> contestruns = null;
		if (group != null) {
			contest.setUsers(contestDAO.objects("get.contest.users.forsaris.group", User.class, cid, group));

			List<Map<String, Object>> maps = contestDAO.maps("get.contest.runs.forsaris.group", cid, cid, group);
			contestruns = new ArrayList<>();
			if (maps == null)
				maps = Collections.emptyList();
			for (Map<String, Object> map : maps) {
				SarisSubmission ss = new SarisSubmission(map.get("nick").toString(), map.get("pid").toString(), map.get("from_start").toString(), Boolean.parseBoolean(map.get("success").toString()));
				contestruns.add(ss);
			}
		} else {
			contest.setUsers(contestDAO.objects("get.contest.users.forsaris", User.class, cid));

			List<Map<String, Object>> maps = contestDAO.maps("get.contest.runs.forsaris", cid, cid);
			contestruns = new ArrayList<>();
			if (maps == null)
				maps = Collections.emptyList();
			for (Map<String, Object> map : maps) {
				SarisSubmission ss = new SarisSubmission(map.get("nick").toString(), map.get("pid").toString(), map.get("from_start").toString(), Boolean.parseBoolean(map.get("success").toString()));
				contestruns.add(ss);
			}
		}
		json.accumulate("contestName", contest.getName());
		json.accumulate("freezeTimeMinutesFromStart", contest.getFrom_start());
		json.accumulate("problemLetters", new JSONArray(contest.getLetters()));
		JSONArray users = new JSONArray();
		for (Iterator<?> it = contest.getUsers().iterator(); it.hasNext();) {
			User contestant = (User) it.next();
			users.add(contestant.getNick());
		}
		json.accumulate("contestants", users);
		JSONArray runs = new JSONArray();
		for (Iterator<SarisSubmission> it = contestruns.iterator(); it.hasNext();) {
			SarisSubmission submission = (SarisSubmission) it.next();
			JSONObject run = new JSONObject();
			run.accumulate("contestant", submission.getNick());
			run.accumulate("problemLetter", contest.getProblemLetter(new Integer(submission.getPid())));
			run.accumulate("timeMinutesFromStart", Integer.parseInt(submission.getFrom_start()));
			run.accumulate("success", submission.isSuccess());
			runs.add(run);
		}
		json.accumulate("runs", runs);
		String contestLog = json.toString(5);
		model.addAttribute("contest", contest);
		model.addAttribute("contestLog", contestLog);
		System.out.println(contestLog);
		model.addAttribute("group", group);
		return "/contest/saris";
	}

	@RequestMapping(value = "/contest/cawards.xhtml", method = RequestMethod.GET)
	public String contestAwards(Model model, SecurityContextHolderAwareRequestWrapper requestWrapper, Locale locale, @RequestParam("cid") Integer cid) {

		Contest contest = contestDAO.loadContest(cid);
		List<Problem> problems = problemDAO.getContestProblems(locale.getLanguage(), cid);
		IACMScoreboard iacmscoreboard = contestDAO.getRankingAcm(cid, null, false, problems);
		List<User> users = iacmscoreboard.assignMedals(contest);

		model.addAttribute("cid", cid);
		model.addAttribute("users", users);

		if (!contest.isPast()) {
			return "/error/accessdenied";
		}
		model.addAttribute("contest", contest);
		awards(model, cid);
		return "/contest/cawards";
	}

	public void awards(Model model,Integer cid) {
		List<Integer> aids = baseDAO.integers("load.contest.awards.id", cid);
		for (Integer aid : aids) {
			switch (aid) {
			case ContestAwardsFlags.FAST_AWARD:
				model.addAttribute("fastAward",baseDAO.object("fast.award", ContestAwards.class, cid));
				break;
			case ContestAwardsFlags.EXCLUSIVE_AWARD:
				model.addAttribute("exclusiveAward",baseDAO.object("exclusive.award", ContestAwards.class, cid,cid));
				break;
			case ContestAwardsFlags.ACCURATE_AWARD:
				model.addAttribute("accurateAward",baseDAO.object("accurate.award", ContestAwards.class, cid,cid));
				break;
			}
		}
	}
	
	public void setMedals(List<User> users, Contest contest, int i) {
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
	}

	private void assigMedals(List<User> medals) {
		for (int i = 0; i < 3 && i < medals.size(); i++) {
			if ((medals.get(i)).getAccu() == 0) {
				break;
			}
			switch (i) {
			case 0: {
				(medals.get(i)).setMedal(User.GOLD);
				break;
			}
			case 1: {
				(medals.get(i)).setMedal(User.SILVER);
				break;
			}
			case 2: {
				(medals.get(i)).setMedal(User.BRONZE);
				break;
			}
			}
		}
	}
}
