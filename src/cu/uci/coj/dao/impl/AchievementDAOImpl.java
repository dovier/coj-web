package cu.uci.coj.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cu.uci.coj.dao.AchievementDAO;
import cu.uci.coj.mail.MailNotificationService;
import cu.uci.coj.model.Rejudge;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.utils.enums.AchievementType;

@Repository("achievementDAO")
@Transactional
public class AchievementDAOImpl extends BaseDAOImpl implements AchievementDAO {

	@Resource
	private MailNotificationService mailNotificationService;

	@Async
	public void checkSubmits(SubmissionJudge submit) {

		if (submit != null) {
			switch (submit.getStatus()) {
			case "Accepted":
				int aid = integer(0, "select.aid.language", submit.getLang());
				if (aid != 0)
					checkAndAwardByUserLanguage("check.language.master.achievement", submit, AchievementType.achievements[aid]);

				checkAndAwardByUser("check.sniper.achievement", submit, AchievementType.SNIPER);
				checkAndAwardByUser("check.polyglot.achievement", submit, AchievementType.POLYGLOT);

				List<Integer> aids = integers("achievements.by.classification.pid", submit.getPid());
				for (Integer id : aids)
					checkAndAwardByUserClassif("check.classification.achievement", submit, AchievementType.achievements[id]);
				break;
			default:
				break;
			}
		}
	}

	private void checkAndCreateachievement(int uid, int aid) {
		boolean exists = bool("achievement.exists", uid, aid);
		if (!exists)
			dml("achievement.insert", aid, uid);
	}

	private void checkAndAwardByUserLanguage(String sql, SubmissionJudge submission, AchievementType achievement) {

		int uid = submission.getUid();
		int aid = achievement.id();

		// si el logro no existe, se crea pero no se procesa (se acaba de
		// crear). process es true si el logro ya existe y no ha sido
		// otorgado aun
		checkAndCreateachievement(uid, aid);

		int currentCount = integer(sql, uid, submission.getLang());
		dml("achievement.update", achievement.currentLevel(currentCount), currentCount, uid, aid);
	}
	
	private void checkAndAwardByUser(String sql, SubmissionJudge submission, AchievementType achievement) {

		int uid = submission.getUid();
		int aid = achievement.id();

		// si el logro no existe, se crea pero no se procesa (se acaba de
		// crear). process es true si el logro ya existe y no ha sido
		// otorgado aun
		checkAndCreateachievement(uid, aid);

		int currentCount = integer(sql, uid);
		dml("achievement.update", achievement.currentLevel(currentCount), currentCount, uid, aid);
	}
	
	private void checkAndAwardByUserClassif(String sql, SubmissionJudge submission, AchievementType achievement) {

		int uid = submission.getUid();
		int aid = achievement.id();

		// si el logro no existe, se crea pero no se procesa (se acaba de
		// crear). process es true si el logro ya existe y no ha sido
		// otorgado aun
		checkAndCreateachievement(uid, aid);

		int currentCount = integer(sql, uid,aid);
		dml("achievement.update", achievement.currentLevel(currentCount), currentCount, uid, aid);
	}

}
