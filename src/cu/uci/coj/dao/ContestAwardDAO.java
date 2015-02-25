package cu.uci.coj.dao;

import java.util.List;

import cu.uci.coj.model.ContestAwards;
import cu.uci.coj.model.ContestAwardsFlags;


public interface ContestAwardDAO {
	
	public void issueAwards(Integer cid);
	public void insertContestAwardsFlags(ContestAwardsFlags contestAwardsFlags);
	public ContestAwardsFlags loadContestAwardsFlags(Integer cid);
	public List<ContestAwards> loadAwards(Integer cid);
}
