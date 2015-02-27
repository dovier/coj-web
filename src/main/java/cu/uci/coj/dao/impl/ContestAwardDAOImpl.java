package cu.uci.coj.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import cu.uci.coj.dao.ContestAwardDAO;
import cu.uci.coj.model.ContestAwards;
import cu.uci.coj.model.ContestAwardsFlags;

@Repository("contestAwardDAO")
@Transactional
public class ContestAwardDAOImpl extends BaseDAOImpl implements ContestAwardDAO {

	private String issueAward(String sql, Integer cid, Integer aid) {
		String username = string(sql, cid);
		if (!StringUtils.isEmpty(username)) {
			Integer uid = integer("select.uid.by.username", username);
			dml("set.contest.award", uid, cid, aid);
			return username;
		} else
			return "None";
	}

	private String issueFastAward(String sql, Integer cid, Integer aid) {
		String username = string(sql, cid, cid, cid, cid, cid, cid, cid, cid, cid, cid, cid, cid);
		if (!StringUtils.isEmpty(username)) {
			Integer uid = integer("select.uid.by.username", username);
			dml("set.contest.award", uid, cid, aid);
			return username;
		} else
			return "None";
	}

	private String issueExclusiveAward(String username, Integer cid) {
		if (!StringUtils.isEmpty(username)) {
			Integer uid = integer("select.uid.by.username", username);
			dml("set.contest.award", uid, cid, ContestAwardsFlags.EXCLUSIVE_AWARD);
			return username;
		} else
			return "None";
	}

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

	public Map<Integer, ContestAwards> loadAwards(Integer cid) {
		Map<Integer, ContestAwards> map = new HashMap<>();
		List<Integer> aids = integers("load.contest.awards.id", cid);
		ContestAwards award = null; 
		for (Integer aid : aids) {
			switch (aid) {
			case ContestAwardsFlags.FAST_AWARD:
				award = object("fast.award", ContestAwards.class, cid);
				break;
			case ContestAwardsFlags.EXCLUSIVE_AWARD:
				award = object("exclusive.award", ContestAwards.class, cid);
				break;
			case ContestAwardsFlags.ACCURATE_AWARD:
				award = object("accurate.award", ContestAwards.class, cid);
				break;
			}
			map.put(aid, award);
		}
		return map;
	}

}
