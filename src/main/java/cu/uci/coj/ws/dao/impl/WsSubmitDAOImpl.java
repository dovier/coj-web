package cu.uci.coj.ws.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cu.uci.coj.dao.impl.BaseDAOImpl;
import cu.uci.coj.ws.dao.WsSubmitDAO;
import cu.uci.coj.ws.models.WsSubmit;

@Repository("wsSubmitDAO")
public class WsSubmitDAOImpl extends BaseDAOImpl implements WsSubmitDAO {

	@Override
	public List<WsSubmit> getSubmits(int from, int to) {
		if (to - from > 500)
			to = from + 500;
		return objects("ws.submit.range", WsSubmit.class, from, to);
	}
	
	@Override
	public List<WsSubmit> getSubmits(String user) {
		return objects("ws.submit.users", WsSubmit.class, user);
	}

	@Override
	public WsSubmit getSubmit(int sid) {
		return object("ws.submit.sid", WsSubmit.class, sid);
	}
}
