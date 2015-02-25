package cu.uci.coj.board.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cu.uci.coj.board.dao.WbContestDAO;
import cu.uci.coj.dao.impl.BaseDAOImpl;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

/**
*
* @author Eddy Roberto Morales Perez
*/

@Repository("wbContestDAO")
@Transactional
public class WbContestDAOImpl extends BaseDAOImpl implements WbContestDAO {

	@Override
	@Transactional(readOnly=true)
	public WbContest getContestById(Integer id) {
		return object("wbcontest.by.id", WbContest.class, id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public WbContest getContestByName(String name) {
		return object("wbcontest.by.name", WbContest.class, name);
	}
	
	@Override
	@Transactional(readOnly=true)
	public WbContest getContestByUrl(String url) {
		return object("wbcontest.by.url", WbContest.class, url);
	}

	@Override
	public void insertContest(WbContest contest) {
		dml("wbcontest.insert", contest.getUrl(), contest.getName(), contest.getSid(), contest.getStartDate(), contest.getEndDate(), contest.isNotifCreated(), contest.isNotifChanged());
	}

	@Override
	public void updateContest(WbContest contest) {
		dml("wbcontest.update", contest.getUrl(), contest.getName(), contest.getSid(), contest.getStartDate(), contest.getEndDate(), contest.isNotifCreated(), contest.isNotifChanged(), contest.getId());		
	}
	

	@Override
	public void deleteContest(Integer id) {
		dml("wbcontest.delete", id);		
	}

	@Override
	public void cleanNotifications() {
		dml("wbcontest.clean.notifications");
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR, 48);
		Date date = calendar.getTime();
		
		dml("wbcontest.clean.notifications.nearcontest", date);
	}

	@Override
	@Transactional(readOnly=true)
	public IPaginatedList<WbContest> getContestList(Integer sid, PagingOptions options) {
		if(sid == 0) {
			return paginated("wbcontest.list.without.site." + options.getDirection(), WbContest.class, 50, options);				
		} else {			
			return paginated("wbcontest.list.with.site." + options.getDirection(), WbContest.class, 50, options, sid);	
		}	
	}

	@Override
	@Transactional(readOnly=true)
	public IPaginatedList<WbContest> getContestListFavorites(Integer sid, PagingOptions options, Integer uid) {
		if(sid == 0) {
			return paginated("wbcontest.list.followed.without.site." + options.getDirection(), WbContest.class, 50,  options, uid);				
		} else {
			return paginated("wbcontest.list.followed.with.site." + options.getDirection(), WbContest.class, 50, options, uid ,sid);	
		}	
	}

	@Override
	public IPaginatedList<WbContest> getPaginatedContestList(PagingOptions options) {
		String sql = getSql("wbcontest.list");
		int found = integer("count.wbcontest.list");
		List<WbContest> list = new ArrayList<WbContest>();
		if(found > 0) {			
			sql += " ORDER BY " + options.getSort() + " " + options.getDirection();
			list = objects(sql, WbContest.class);
		}
		return getPaginatedList(options, list, 50, found);
	}
	
	@Override
	public List<WbContest> getNearContestsNotification() {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR, 48);
		Date date = calendar.getTime();
		
		List<WbContest> contests = objects("wbcontest.list.nearcontestsnotification", WbContest.class, date); 
		
		return contests.size() > 0 ? contests : null;
	}	

	@Override
	@Transactional(readOnly=true)
	public List<WbContest> getContestsWithNewContestNotificationBySite(Integer sid) {
		return objects("wbcontest.list.newcontestnotification.by.sid", WbContest.class, sid);
	}

	@Override
	@Transactional(readOnly=true)
	public List<WbContest> getContestsWithScheduleChangeNotificationBySite(Integer sid) {
		return objects("wbcontest.list.schedulechangenotification.by.sid", WbContest.class, sid);
	}
}
