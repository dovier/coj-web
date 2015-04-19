package cu.uci.coj.service.impl;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import cu.uci.coj.board.parsing.WbParserManager;
import cu.uci.coj.dao.AccountActivationDAO;
import cu.uci.coj.dao.AchievementDAO;
import cu.uci.coj.dao.BaseDAO;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.mail.MailNotificationService;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.service.CorrectionDAO;
import cu.uci.coj.service.ScheduleService;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

	@Resource
	private BaseDAO baseDAO;
	@Resource
	private UserDAO userDAO;
	@Resource
	private ProblemDAO problemDAO;
	@Resource
	private AchievementDAO achievementDAO;
	@Resource
	private ContestDAO contestDAO;
	@Resource
	private CorrectionDAO correctionDAO;
	@Resource
	private AccountActivationDAO accountActivationDAO;
	@Resource
	private MailNotificationService mailNotificationService;
	@Resource
	private WbParserManager wbParserManager;

	// cada segundo
	private Integer CONTEST_INTERVAL = 1;
	private Integer currentContestInterval = 0;
	// cada segundo
	private Integer PROBLEM_INTERVAL = 1;
	private Integer currentProblemInterval = 0;
	// cada medio dia
	private Integer STATISTICS_INTERVAL = 3600 * 4;
	private Integer currentStatisticsInterval = 0;
	// cada dia
	private Integer ACTIVATION_INTERVAL = 3600 * 8;
	private Integer currentActivationInterval = 0;
	// cada dia
	private Integer WBOARD_INTERVAL = 3600 * 2;
	private Integer currentWBoardInterval = 0;
	//cada dia
	private Integer USER_STATUS_INTERVAL = 3600 * 2;
	private Integer currentUserStatusInterval = 0;

	public void problemEvents() {
		if (((currentProblemInterval = (currentProblemInterval < PROBLEM_INTERVAL) ? currentProblemInterval + 1 : 0) != 0))
			return;

		problemDAO.checkProblemCreated();
	}

	public void contestEvents() {
		if (((currentContestInterval = (currentContestInterval < CONTEST_INTERVAL) ? currentContestInterval + 1 : 0) != 0))
			return;

		contestDAO.checkContestsCreated();
		// FIXME cuando el contest se crea se crea con la hora actual. Eso
		// implica que cuando se crea el contest al usuario se le mandan dos
		// notificaciones, la de creacion y la de comienzo, como si el contest
		// hubiera empezado en ese momento.
		// contestDAO.checkContestsStarted();
		contestDAO.checkContestsEnded();
	}

	public void activationEvents() {

		if (((currentActivationInterval = (currentActivationInterval < ACTIVATION_INTERVAL) ? currentActivationInterval + 1 : 0) != 0))
			return;

		accountActivationDAO.purgeOldActivations();

	}

	public void statisticsEvents() {
		if (((currentStatisticsInterval = (currentStatisticsInterval < STATISTICS_INTERVAL) ? currentStatisticsInterval + 1 : 0) != 0))
			return;

		correctionDAO.periodicCalculations();
	}
	
	public void wBoardEvents() {
		if (((currentWBoardInterval = (currentWBoardInterval < WBOARD_INTERVAL) ? currentWBoardInterval + 1 : 0) != 0))
			return;

		
		wbParserManager.doWork();
	}

	public void userStatusEvents() {
		if (((currentUserStatusInterval = (currentUserStatusInterval < USER_STATUS_INTERVAL) ? currentUserStatusInterval + 1 : 0) != 0))
			return;
		
		
		userDAO.checkUserStatus();
	}

	@Scheduled(fixedDelay = 1000)
	public void mainScheduler() {
		activationEvents();
		statisticsEvents();
		wBoardEvents();
		userStatusEvents();
	}
}
