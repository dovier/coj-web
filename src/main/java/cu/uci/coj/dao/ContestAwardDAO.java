package cu.uci.coj.dao;

import java.util.List;
import java.util.Map;

import cu.uci.coj.model.ContestAwards;
import cu.uci.coj.model.ContestAwardsFlags;


public interface ContestAwardDAO {
	
	public void insertContestAwardsFlags(ContestAwardsFlags contestAwardsFlags);
	public ContestAwardsFlags loadContestAwardsFlags(Integer cid);
	public Map<Integer,ContestAwards> loadAwards(Integer cid);
}
