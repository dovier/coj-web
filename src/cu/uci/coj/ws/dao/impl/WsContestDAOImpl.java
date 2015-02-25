package cu.uci.coj.ws.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cu.uci.coj.dao.impl.BaseDAOImpl;
import cu.uci.coj.model.Contest;
import cu.uci.coj.ws.dao.WsContestDAO;
import cu.uci.coj.ws.models.WsContest;

@Repository("wsContestDAO")
public class WsContestDAOImpl extends BaseDAOImpl implements WsContestDAO {

	@Override
	public List<WsContest> getComingContests() {
		return objects("ws.coming.contests", WsContest.class);
	}
}
