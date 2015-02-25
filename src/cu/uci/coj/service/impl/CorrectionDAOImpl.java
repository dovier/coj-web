package cu.uci.coj.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cu.uci.coj.config.Config;
import cu.uci.coj.dao.impl.BaseDAOImpl;
import cu.uci.coj.model.Language;
import cu.uci.coj.service.CorrectionDAO;
import cu.uci.coj.utils.enums.AchievementType;

@Repository("correctionDAO")
@Transactional
public class CorrectionDAOImpl extends BaseDAOImpl implements CorrectionDAO {

	public void calculateAwards() {

		List<Language> languages = objects("master.achievement.by.language", Language.class);
		for (Language lang : languages) {
			dml("delete.achievements", lang.getAid());
			dml("calculate.language.master", lang.getAid(), lang.getLanguage());
		}
		dml("delete.achievements", AchievementType.SNIPER.id());
		dml("calculate.sniper", AchievementType.SNIPER.id());

		dml("delete.achievements", AchievementType.POLYGLOT.id());
		dml("calculate.polyglot", AchievementType.POLYGLOT.id());

		List<Integer> classifications = integers("select aid from classifications");
		for (Integer classif : classifications)
			dml("delete.achievements", classif);
		dml("calculate.classification.master");

		dml("insert.log", "Awards correction", "No user");
	}

	public void periodicCalculations() {
		calculateStats24h();
		calculateStatsContest();
		calculateUserStats24h();
		calculateProblemStats24h();
		calculatePoints();
		calculateContestUserStats();
		dml("insert.log", "Statistics periodic calculation", "No user");
	}

	public void calculateUserStats24h() {
		dml("delete.user.stats24h");
		dml("calculate.user.stats24h");
		dml("update.user.stats24h");
	}

	public void calculateProblemStats24h() {
		dml("delete.problem.stats24h");
		dml("calculate.problem.stats24h");
		dml("update.problem.stats24h");

		dml("delete.user.problem.tmp");
		dml("insert.user.problem.tmp");
		dml("delete.user.problem");
		dml("insert.user.problem.all");
		dml("update.user.problem.lock");

	}

	public void calculateContestUserStats() {
		dml("delete.user.contest.stats");
		dml("calculate.user.contest.stats");
		dml("update.user.contest.stats");
	}

	public void calculateStats24h() {
		dml("delete.stats24h");
		dml("calculate.stats24h");
	}

	public void calculatePoints() {
		dml("calculate.user.points", (double) Integer.valueOf(Config.getProperty("formula.value")));
	}

	public void calculateStatsContest() {
		dml("delete.stats.contest");
		dml("calculate.stats.contest");
	}
}
