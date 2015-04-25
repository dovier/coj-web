package cu.uci.coj.controller;

import cu.uci.coj.config.Config;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.RecommenderDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.ProblemClassification;
import cu.uci.coj.model.Roles;
import cu.uci.coj.model.VotesStats;
import cu.uci.coj.recommender.Recommender;
import cu.uci.coj.utils.FileUtils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProblemController extends BaseController {

	private static final long serialVersionUID = -4368385200281070644L;
	@Resource
	private ProblemDAO problemDAO;
	@Resource
	private UserDAO userDAO;
	@Resource
	private RecommenderDAO recommenderDAO;

	@RequestMapping(value = "/24h/problem.xhtml", method = RequestMethod.GET)
	public String Problem(HttpServletRequest request, Locale locale,
			Model model, Principal principal, @RequestParam Integer pid) {
		Integer uid = problemDAO.integer("select.uid.by.username",
				getUsername(principal));
		Problem problem = problemDAO
				.getProblemByCode(
						locale.getLanguage(),
						pid,
						request.isUserInRole(Roles.ROLE_ADMIN)
								|| request
										.isUserInRole(Roles.ROLE_SUPER_PSETTER)
								|| (problemDAO.bool("is.psetter.problem", uid,
										pid) && request
										.isUserInRole(Roles.ROLE_PSETTER)));
		if (getUsername(principal) != null) {
			problem.setSolved(problemDAO.bool("issolved.byuser", uid,
					problem.getPid()));
		}
		problem.setDate(problem.getDate().split(" ")[0]);
		problemDAO.fillProblemLanguages(problem);
		problemDAO.fillProblemLimits(problem);
		String username = getUsername(principal);
		Language language = null;
		if (username != null)
			language = userDAO.getProgrammingLanguageByUsername(username);
		model.addAttribute("userLanguage", language);
		model.addAttribute("problem", problem);
		Recommender recommender = new Recommender(userDAO, problemDAO,
				recommenderDAO);
		List<Problem> recommendations;
		if (request.isUserInRole(Roles.ROLE_USER)) {
			boolean view_pinfo = problemDAO.bool("get.pinfo.byuser",
					getUsername(principal));
			model.addAttribute("view_pinfo", view_pinfo);
			if (view_pinfo) {
				model.addAttribute("classifications", problemDAO.objects(
						"get.problem.classifications",
						ProblemClassification.class, problem.getPid()));
			}
			recommendations = recommender.findSimilarProblems(
					getUsername(principal), problem);
		} else {
			recommendations = recommender.findSimilarProblems(problem);
		}

		if (recommendations.isEmpty() == false) {
			model.addAttribute("hasRecommend", true);
			model.addAttribute("recommend", recommendations);
		} else {
			model.addAttribute("hasRecommend", false);
		}
		model.addAttribute("locale", locale.getLanguage());
		return "/24h/problem";
	}

	@RequestMapping(value = "/24h/problems.xhtml", method = RequestMethod.GET)
	public String problems(
			Model model,
			Locale locale,
			Principal principal,
			PagingOptions options,
			@RequestParam(required = false, value = "pattern") String pattern,
			@RequestParam(required = false, defaultValue = "0", value = "filterby") Integer filterby,
			@RequestParam(required = false, defaultValue = "-1", value = "classification") Integer idClassification,
			@RequestParam(required = false, defaultValue = "-1", value = "complexity") Integer complexity) {

		String username = getUsername(principal);
		if (pattern == null)
			pattern = "";
		else
			pattern = pattern.trim();
		model.addAttribute("pattern", pattern);

		model.addAttribute("classifications", problemDAO.objects(
				"problem.classifications", ProblemClassification.class));
		model.addAttribute("filterby", filterby);
		model.addAttribute("complexity", complexity);
		model.addAttribute("classification", idClassification);
		return "/24h/problems";
	}

	@RequestMapping(value = "/tables/problems.xhtml", method = RequestMethod.GET)
	public String tablesProblems(
			Model model,
			Locale locale,
			Principal principal,
			PagingOptions options,
			@RequestParam(required = false, value = "pattern") String pattern,
			@RequestParam(required = false, defaultValue = "0", value = "filterby") Integer filterby,
			@RequestParam(required = false, defaultValue = "-1", value = "classification") Integer idClassification,
			@RequestParam(required = false, defaultValue = "-1", value = "complexity") Integer complexity) {

		String username = getUsername(principal);
		if (filterby == 5) {
			Recommender recommender = new Recommender(userDAO, problemDAO,
					recommenderDAO);
			List<Problem> recommendations = recommender.recommendations(
					username,
					username == null ? 0 : problemDAO.integer(
							"select.uid.by.username", username), options
							.getSort(), options.getDirection());
			int found = recommendations.size();
			if (found != 0) {
				model.addAttribute("problems", recommendations);
			}
		} else {
			int found = problemDAO.countProblem(pattern, filterby, username,
					idClassification, complexity);
			if (found != 0) {
				IPaginatedList<Problem> problems = problemDAO.findAllProblems(
						locale.getLanguage(),
						pattern,
						found,
						options,
						username,
						username == null ? 0 : problemDAO.integer(
								"select.uid.by.username", username), filterby,
						idClassification, complexity);
				model.addAttribute("problems", problems);
			}
		}
		return "/tables/problems";
	}

	@RequestMapping(value = "/24h/lockproblem.xhtml", method = RequestMethod.POST)
	public String LockProblem(Model model, Principal principal,
			@RequestParam("pid") Integer pid) {
		String username = getUsername(principal);
		try {
			int uid = problemDAO.integer("select.uid.by.username", username);
			if (problemDAO.bool("issolved.byuser", uid, pid)) {
				problemDAO.dml("lock.problem", uid, pid);
			}
		} catch (EmptyResultDataAccessException e) {
			return "/error/wproblem";
		}
		return "redirect:/24h/bestsolutions.xhtml?pid=" + pid;
	}

	@RequestMapping(value = "/24h/markasfavorite.xhtml", method = RequestMethod.GET)
	public String MarkFavorite(Model model, Principal principal,
			@RequestParam("pid") Integer pid) {
		try {
			int uid = problemDAO.integer("select.uid.by.username",
					getUsername(principal));
			if (!problemDAO.bool("problem.ismark.asfavorite.byuser", uid, pid)
					&& problemDAO.bool("exist.problem.bypid", pid)) {
				problemDAO.dml("problem.mark.asfavorite.byuser", uid, pid);
			}
		} catch (EmptyResultDataAccessException e) {
			return "/error/wproblem";
		}
		return "redirect:/24h/problems.xhtml?filterby=4";
	}

	@RequestMapping(value = "/24h/unmarkfavorite.xhtml", method = RequestMethod.GET)
	public String UnMarkFavorite(Model model, Principal principal,
			@RequestParam("pid") Integer pid) {
		try {
			int uid = problemDAO.integer("select.uid.by.username",
					getUsername(principal));
			if (problemDAO.bool("problem.ismark.asfavorite.byuser", uid, pid)) {
				problemDAO.dml("problem.unmark.favorite.byuser", uid, pid);
			}
		} catch (EmptyResultDataAccessException e) {
			return "/error/wproblem";
		}
		return "redirect:/24h/problems.xhtml";
	}

	@RequestMapping(value = "/24h/problempdf.xhtml", method = RequestMethod.GET)
	public String ProblemPdf(Locale locale, HttpServletRequest request,
			HttpServletResponse response, OutputStream os, Model model,
			Principal principal, @RequestParam Integer pid) {
		Integer uid = problemDAO.integer("select.uid.by.username",
				getUsername(principal));
		Problem problem = problemDAO
				.getProblemByCode(
						locale.getLanguage(),
						pid,
						request.isUserInRole(Roles.ROLE_ADMIN)
								|| request
										.isUserInRole(Roles.ROLE_SUPER_PSETTER)
								|| (problemDAO.bool("is.psetter.problem", uid,
										pid) && request
										.isUserInRole(Roles.ROLE_PSETTER)));
		try {
			problemDAO.fillProblemLanguages(problem);
			FileUtils.generatePdf(problem);
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline; filename="
					+ problem.getPid() + ".pdf");
			FileInputStream in = new FileInputStream(
					Config.getProperty("pdfs.dir") + "/" + problem.getPid()
							+ ".pdf");
			FileUtils.redirectStreams(in, os);
		} catch (FileNotFoundException ex) {
			Logger.getLogger(ProblemController.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return null;
	}

	@RequestMapping(value = "/24h/votes.xhtml", method = RequestMethod.GET)
	public String Votes(Locale locale, Principal principal,
			SecurityContextHolderAwareRequestWrapper requestWrapper,
			Model model, @RequestParam Integer pid) {
		Problem problem = problemDAO.getStatistics(locale.getLanguage(), pid);
		if (getUsername(principal) == null) {
			return "/error/wproblem";
		}

		int uid = problemDAO.integer("select.uid.by.username",
				getUsername(principal));

		if (!problemDAO.bool("issolved.byuser", uid, problem.getPid())) {
			return "/error/wproblem";
		}

		if (!problemDAO.bool("problem.voted.byuser", uid, problem.getPid())) {
			model.addAttribute("voted", false);
		} else {
			model.addAttribute("voted", true);

			VotesStats datos = problemDAO.object("problem.load.votes",
					VotesStats.class, problem.getPid());
			double[] V = new double[6];
			V[1] = datos.getVote1_sum() / Math.max(1, datos.getVote1_cant());
			V[2] = datos.getVote2_sum() / Math.max(1, datos.getVote2_cant());
			V[3] = datos.getVote3_sum() / Math.max(1, datos.getVote3_cant());
			V[4] = datos.getVote4_sum() / Math.max(1, datos.getVote4_cant());
			V[5] = datos.getVote5_sum() / Math.max(1, datos.getVote5_cant());
			model.addAttribute("vote1", V[1]);
			model.addAttribute("vote2", V[2]);
			model.addAttribute("vote3", V[3]);
			model.addAttribute("vote4", V[4]);
			model.addAttribute("vote5", V[5]);

			model.addAttribute("str_vote1", String.format("%.2f", V[1]));
			model.addAttribute("str_vote2", String.format("%.2f", V[2]));
			model.addAttribute("str_vote3", String.format("%.2f", V[3]));
			model.addAttribute("str_vote4", String.format("%.2f", V[4]));
			model.addAttribute("str_vote5", String.format("%.2f", V[5]));
		}

		model.addAttribute("problem", problem);
		return "/24h/votes";
	}

	@RequestMapping(value = "/24h/voting.xhtml", method = RequestMethod.POST)
	public String VoteProblem(Model model, Principal principal,
			@RequestParam Integer pid,
			@RequestParam(required = true, value = "vote1") String vote1,
			@RequestParam(required = true, value = "vote2") String vote2,
			@RequestParam(required = true, value = "vote3") String vote3,
			@RequestParam(required = true, value = "vote4") String vote4,
			@RequestParam(required = true, value = "vote5") String vote5) {
		System.out.println("Voto = " + vote1 + " " + vote2);
		try {
			int uid = problemDAO.integer("select.uid.by.username",
					getUsername(principal));

			if (!problemDAO.bool("problem.has_voted", pid)) {
				problemDAO.dml("problem.create.votes", pid);
			}
			double[] V = { Double.parseDouble(vote1), 0,
					Double.parseDouble(vote2), 0, Double.parseDouble(vote3), 0,
					Double.parseDouble(vote4), 0, Double.parseDouble(vote5), 0 };

			double vote = 0, cant = 0;
			for (int i = 0; i < 5; i++)
				if (V[2 * i] > 0) {
					V[2 * i + 1] = 1;
					cant += 1.0;
					;
					vote += V[2 * i];
				}
			problemDAO.dml("problem.update.votes", V[0], V[1], V[2], V[3],
					V[4], V[5], V[6], V[7], V[8], V[9], pid);

			vote /= cant;
			if (!problemDAO.bool("problem.voted.byuser", uid, pid)) {
				problemDAO.dml("problem.register.vote", uid, pid, vote);
			} else {
				problemDAO.dml("problem.update.vote", vote, uid, pid);
			}
		} catch (EmptyResultDataAccessException e) {
			return "/error/wproblem";
		}
		return "redirect:/24h/votes.xhtml?pid=" + pid;
	}
}
