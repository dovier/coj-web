package cu.uci.coj.ws.dao;

import java.util.List;

import cu.uci.coj.ws.models.WsSubmit;

public interface WsSubmitDAO {
	WsSubmit getSubmit(int sid);
	List<WsSubmit> getSubmits(int from, int to);
}
