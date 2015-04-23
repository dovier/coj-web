package cu.uci.coj.service;

import cu.uci.coj.dao.BaseDAO;

public interface CorrectionDAO extends BaseDAO {

	public void periodicCalculations();
	public void calculateUserStats24h();
	public void calculateProblemStats24h();
	public void calculateStats24h();
	public void calculateContestUserStats();
	public void calculateStatsContest();
	public void calculateAwards();
	public void calculatePoints();
	public void synchSharedFiles();
}
