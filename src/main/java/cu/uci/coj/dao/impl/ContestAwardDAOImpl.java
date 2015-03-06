package cu.uci.coj.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cu.uci.coj.dao.ContestAwardDAO;
import cu.uci.coj.model.ContestAwardsFlags;

@Repository("contestAwardDAO")
@Transactional
public class ContestAwardDAOImpl extends BaseDAOImpl implements ContestAwardDAO {

	public void insertContestAwardsFlags(ContestAwardsFlags contestAwardsFlags) {

		int cid = contestAwardsFlags.getCid();
		dml("delete.contest.awards", cid);
		if (contestAwardsFlags.isAccurate())
			dml("insert.contest.award", ContestAwardsFlags.ACCURATE_AWARD, cid);
		if (contestAwardsFlags.isFast())
			dml("insert.contest.award", ContestAwardsFlags.FAST_AWARD, cid);
		if (contestAwardsFlags.isExclusive())
			dml("insert.contest.award", ContestAwardsFlags.EXCLUSIVE_AWARD, cid);

	}

	public ContestAwardsFlags loadContestAwardsFlags(Integer cid) {
		List<Integer> aids = integers("load.contest.awards.id", cid);
		ContestAwardsFlags flags = new ContestAwardsFlags(cid, aids);
		return flags;
	}

}
