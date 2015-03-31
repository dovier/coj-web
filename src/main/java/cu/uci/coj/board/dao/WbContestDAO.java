package cu.uci.coj.board.dao;


import java.util.List;

import cu.uci.coj.model.WbContest;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

/**
*
* @author Eddy Roberto Morales Perez
*/

public interface WbContestDAO {
	WbContest getContestById(Integer id);
	
	WbContest getContestByName(String name);
	
	WbContest getContestByUrl(String url);
	
	void insertContest(WbContest contest);
	
	void updateContest(WbContest contest);
	
	void deleteContest(Integer id);
	
	void cleanNotifications();
	
	IPaginatedList<WbContest> getContestList(Integer sid, PagingOptions options);
	
	IPaginatedList<WbContest> getContestListFavorites(Integer sid, PagingOptions option, Integer uid);
	
	IPaginatedList<WbContest> getPaginatedContestList(PagingOptions options);
	
	List<WbContest> getNearContestsNotification();
	
	List<WbContest> getContestsWithNewContestNotificationBySite(Integer sid);
	
	List<WbContest> getContestsWithScheduleChangeNotificationBySite(Integer sid);
}
