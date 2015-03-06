package cu.uci.coj.dao.impl;

import org.springframework.stereotype.Repository;

import cu.uci.coj.dao.ContributionDAO;

@Repository("contributionDAO")
public class ContributionDAOImpl extends BaseDAOImpl implements ContributionDAO {

	@Override
	public void insertContribution(String username, Integer cid) {
		dml("contribution.insert", username, cid);
	}
}
