package cu.uci.coj.board.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cu.uci.coj.board.dao.WbContestDAO;
import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.dao.impl.BaseDAOImpl;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;
import cu.uci.coj.query.Order;
import cu.uci.coj.query.Query;
import cu.uci.coj.query.Where;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

/**
*
* @author Eddy Roberto Morales Perez
*/

@Repository("wbContestDAO")
@Transactional
public class WbContestDAOImpl extends BaseDAOImpl implements WbContestDAO {

	@Resource
	WbSiteDAO wbSiteDAO;

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
		Query query = new Query("wboard_contest");
		sid = sid == 0?null:sid;

		query.where(Where.eq("sid", sid));

		if(options.getDirection() == null) {
			options.setDirection("asc");
			options.setSort("startDate");
		}

		if(options.getDirection().equals("asc")) {
			query.order(Order.asc(options.getSort()));
		} else {
			query.order(Order.desc(options.getSort()));
		}

		List<WbContest> list = wbSiteDAO.getContestsList();
		// objects(query.select("id", "url", "name", "sid", "startdate", "enddate"), WbContest.class, query.arguments());

		List<WbSite> sitelist = wbSiteDAO.getSiteList();
		HashMap<Integer, WbSite> map =  new HashMap<Integer, WbSite>();

		for(int i = 0;i<sitelist.size();i++) {
			map.put(sitelist.get(i).getSid(), sitelist.get(i));
		}

		List<WbContest> result = new ArrayList<WbContest>();
		long now = new Date().getTime();
		for(int i = 0;i<list.size();i++) {
			if(list.get(i).getEndDate().getTime()
					- map.get(list.get(i).getSid()).getOffset()
					>= now) {
				result.add(list.get(i));
			}
		}

		list.clear();

		int cant = 50;
		for(int i = 50 * (options.getPage() - 1);i<result.size() && cant > 0;i++, cant--) {
			list.add(result.get(i));
		}

		return getPaginatedList(options, list, 50, result.size());
	}

	@Override
	@Transactional(readOnly=true)
	public IPaginatedList<WbContest> getContestListFavorites(Integer sid, PagingOptions options, Integer uid) {
		Query query = new Query("wboard_contest join wboard_user_site using(sid)");
		sid = sid == 0?null:sid;

		query.where(Where.eq("sid", sid), Where.eq("uid", uid));

		if(options.getDirection() == null) {
			options.setDirection("asc");
			options.setSort("startDate");
		}

		if(options.getDirection().equals("asc")) {
			query.order(Order.asc(options.getSort()));
		} else {
			query.order(Order.desc(options.getSort()));
		}

		List<WbContest> list = objects(query.select("id", "url", "name", "sid", "startdate", "enddate"), WbContest.class, query.arguments());

		List<WbSite> sitelist = wbSiteDAO.getSiteList();
		HashMap<Integer, WbSite> map =  new HashMap<Integer, WbSite>();

		for(int i = 0;i<sitelist.size();i++) {
			map.put(sitelist.get(i).getSid(), sitelist.get(i));
		}

		List<WbContest> result = new ArrayList<WbContest>();
		long now = new Date().getTime();
		for(int i = 0;i<list.size();i++) {
            long date = list.get(i).getEndDate().getTime();
            int sidl = list.get(i).getSid();
            if(map.get(sidl)!=null){
                int offset = map.get(sidl).getOffset();
                if(date - offset >= now) {
                    result.add(list.get(i));
                }
            }
		}

		list.clear();

		int cant = 50;
		for(int i = 50 * (options.getPage() - 1);i<result.size() && cant > 0;i++, cant--) {
			list.add(result.get(i));
		}

		return getPaginatedList(options, list, 50, result.size());
	}

	@Override
	@Transactional(readOnly=true)
	public IPaginatedList<WbContest> getPaginatedContestList(PagingOptions options) {
		int found = integer("count.wbcontest.list");

		if(options.getDirection() == null) {
			options.setDirection("asc");
			options.setSort("startDate");
		}

		Query query = new Query("wboard_contest");
		if(options.getDirection().equals("asc")) {
			query.order(Order.asc(options.getSort()));
		} else {
			query.order(Order.desc(options.getSort()));
		}

		query.paginate(options, 50);

		List<WbContest> list = objects(query.select("id", "url", "name", "sid", "startdate", "enddate"), WbContest.class, query.arguments());

		return getPaginatedList(options, list, 50, found);
	}

	@Override
	@Transactional(readOnly=true)
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
