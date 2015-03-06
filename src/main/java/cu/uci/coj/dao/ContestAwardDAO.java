package cu.uci.coj.dao;

import cu.uci.coj.model.ContestAwardsFlags;


public interface ContestAwardDAO {
	
	public void insertContestAwardsFlags(ContestAwardsFlags contestAwardsFlags);
	public ContestAwardsFlags loadContestAwardsFlags(Integer cid);
}
