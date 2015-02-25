package cu.uci.coj.board.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cu.uci.coj.board.dao.WbContestDAO;
import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.board.service.WbContestService;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

/**
*
* @author Eddy Roberto Morales Perez
*/

@Service("wbContestService")
public class WbContestServiceImpl implements WbContestService {
	
	@Resource
	WbContestDAO WbContestDAO;
	@Resource
	WbSiteDAO wbSiteDAO;

	@Override
	public void addToDatabaseAndCreateNotification(WbContest contest) {
		WbContest old = WbContestDAO.getContestByName(contest.getName());
		
		if(old == null) {
			contest.setNotifCreated(true);
			contest.setNotifChanged(false);
			WbContestDAO.insertContest(contest);
		} else {			
			if(old.getStartDate().getTime() != contest.getStartDate().getTime()) {
				if(old.isNotifCreated()) {
					contest.setNotifChanged(false);
				} else {
					contest.setNotifChanged(true);
				}
				contest.setId(old.getId());				
				WbContestDAO.updateContest(contest);
			}
		}
	}

	@Override
	public IPaginatedList<WbContest> getContestList(Integer site, PagingOptions options, Integer followed, Integer uid){
		if(followed != 0) {
			return WbContestDAO.getContestListFavorites(site, options, uid);
		} else {
			return WbContestDAO.getContestList(site, options);
		}		
	}
}
