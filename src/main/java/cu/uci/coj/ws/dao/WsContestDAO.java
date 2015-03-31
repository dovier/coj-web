package cu.uci.coj.ws.dao;

import java.util.List;

import cu.uci.coj.ws.models.WsContest;

public interface WsContestDAO {
	List<WsContest> getComingContests();
}
