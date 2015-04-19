package cu.uci.coj.controller.admin;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.mail.MailNotificationService;
import cu.uci.coj.service.CorrectionDAO;

@Controller("CorrectionController")
@RequestMapping(value = "/admin/correct")
public class CorrectionController extends BaseController {

	@Resource
	private CorrectionDAO correctionDAO;
	@Resource
	private UserDAO userDAO;
	@Resource
	private MailNotificationService mailNotificationService;

	@RequestMapping(value = "/managecorrections.xhtml", method = RequestMethod.GET)
	public String manageCorrections() {
		return "/admin/managecorrections";
	}

	@RequestMapping(value = "/remindactivations.xhtml", method = RequestMethod.GET)
	public String remindActivation(Model model) {
		mailNotificationService.sendBulkAccountVerificationReminder();
		return "manageactivations";
	}

	@RequestMapping(value = "/achievements.xhtml", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void awards(Model model) {
		correctionDAO.calculateAwards();
	}

	@RequestMapping(value = "/stats24h.xhtml", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void correctStats24h(Model model) {
		correctionDAO.calculateStats24h();
	}

	@RequestMapping(value = "/points.xhtml", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void correctPoints(Model model) {
		correctionDAO.calculatePoints();
	}

	@RequestMapping(value = "/userstats24h.xhtml", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void correctUserStats24h(Model model) throws Exception {
		correctionDAO.calculateUserStats24h();
	}
	
	@RequestMapping(value = "/shared.xhtml", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void correctSharedFiles(Model model) throws Exception {
		correctionDAO.synchSharedFiles();
	}

	@RequestMapping(value = "/problemstats24h.xhtml", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void correctProblemStats24h(Model model) throws Exception {
		correctionDAO.calculateProblemStats24h();
	}

	@RequestMapping(value = "/statsusercontest.xhtml", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void correctContestUserStats(Model model) throws Exception {
		correctionDAO.calculateContestUserStats();
	}

	@RequestMapping(value = "/statscontest.xhtml", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void correctStatsContest(Model model) {
		correctionDAO.calculateStatsContest();
	}
}
