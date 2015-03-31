package cu.uci.coj.board.dao;

import java.util.List;

import cu.uci.coj.dao.BaseDAO;
import cu.uci.coj.model.WbSite;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

/**
*
* @author Eddy Roberto Morales Perez
*/

public interface WbSiteDAO extends BaseDAO {	
	WbSite getSiteByUrl(String url);
	
	void setCompleted(int sid, boolean completed);
	
	Integer getSiteId(String site);
	
	List<WbSite> getSiteList();
	
	List<WbSite> getSiteListFollowed(Integer uid);
	
	List<Integer> getIdSiteListFollowed(Integer uid);
	
	void updateSiteListFollowed(Integer uid, List<Integer> followed);
	
	void followSite(Integer uid, Integer sid);
	
	void unfollowSite(Integer uid, Integer sid);
	
	boolean getIfFollow(Integer uid, Integer sid);
	
	List<WbSite> getListSitesWithContestsNewContestNotification();
	
	List<WbSite> getListSitesWithContestsScheduleChangedNotification();
	
	int insertSite(WbSite site);
	
	void updateSite(WbSite site);
	
	void deleteSite(Integer sid);
	
	boolean isSiteEnabled(Integer sid);
	
	void setSiteEnabled(Integer sid, Boolean enable);
	
	WbSite getSiteById(Integer sid);
	
	IPaginatedList<WbSite> getPaginatedSiteList(PagingOptions options);
}