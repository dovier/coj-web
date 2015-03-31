package cu.uci.coj.board.service;


import cu.uci.coj.model.WbContest;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

public interface WbContestService {
	void addToDatabaseAndCreateNotification(WbContest contest);
	 IPaginatedList<WbContest> getContestList(Integer site, PagingOptions options, Integer followed, Integer uid);
}
