package cu.uci.coj.board.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import cu.uci.coj.board.dao.WbContestDAO;
import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.dao.impl.BaseDAOImpl;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

/**
 *
 * @author Eddy Roberto Morales Perez
 */

@Repository("wbSiteDAO")
@Transactional
public class WbSiteDAOImpl extends BaseDAOImpl implements WbSiteDAO {

	@Resource
	WbContestDAO wbContestDAO;

	@Override
	@Transactional(readOnly = true)
	public WbSite getSiteByUrl(String url) {
		return object("wbsite.by.url", WbSite.class, url);
	}

	@Override
	public void setCompleted(int sid, boolean completed) {
		dml("wbsite.update.completed", completed, sid);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getSiteId(String site) {
		return integer("wbsite.sid", site);
	}

	@Override
	@Transactional(readOnly = true)
	public List<WbSite> getSiteList() {
		return objects("wbsite.list", WbSite.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<WbSite> getSiteListFollowed(Integer uid) {
		return objects("wbsite.list.followed", WbSite.class, uid);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Integer> getIdSiteListFollowed(Integer uid) {
		return integers("wbsite.list.id.followed", uid);
	}

	@Override
	public void followSite(Integer uid, Integer sid) {
		dml("wbsite.follow", uid, sid);
	}

	@Override
	public void unfollowSite(Integer uid, Integer sid) {
		dml("wbsite.unfollow", uid, sid);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean getIfFollow(Integer uid, Integer sid) {
		return (integer(0, "wbsite.getiffollow", uid, sid) != 0);
	}

	@Override
	public void updateSiteListFollowed(Integer uid, List<Integer> followed) {
		dml("wbsite.delete.sites.followed", uid);
		if(followed != null) {
			for(int i = 0;i<followed.size();i++) {
				dml("wbsite.follow", uid, followed.get(i));
			}
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<WbSite> getListSitesWithContestsNewContestNotification() {
		List<WbSite> sites = getSiteList();
		List<WbSite> result = new ArrayList<WbSite>();
		boolean exist = false;		
		if(sites != null) {
			for(int i = 0;i<sites.size();i++) {
				List<WbContest> contests =  wbContestDAO.getContestsWithNewContestNotificationBySite(sites.get(i).getSid());
				sites.get(i).setContests(contests);			
				if(contests.size() != 0) {
					exist = true;
					result.add(sites.get(i));
				}
			}
		}

		if (!exist) {
			return null;
		}

		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<WbSite> getListSitesWithContestsScheduleChangedNotification() {
		List<WbSite> sites = getSiteList();
		List<WbSite> res = new ArrayList<WbSite>();
		boolean exist = false;
		
		if(sites != null) {
			for(int i = 0;i<sites.size();i++) {
				List<WbContest> contests = wbContestDAO.getContestsWithScheduleChangeNotificationBySite(sites.get(i).getSid());
				sites.get(i).setContests(contests);
				if(contests.size() != 0) {
					exist = true;
					res.add(sites.get(i));
				}
			}
		}

		if (!exist) {
			return null;
		}

		return res;
	}

	@Override
	public int insertSite(WbSite site) {
		return jdbcTemplate.queryForInt(getSql("wbsite.insert"), site.getSite(), site.getUrl(), site.getCode(), site.isCompleted(), site.isEnabled(), site.getTimeanddateid(), site.getTimezone());
	}

	@Override
	public void updateSite(WbSite site) {
		dml("wbsite.update", site.getSite(), site.getUrl(), site.getCode(), site.isCompleted(), site.isEnabled(), site.getTimeanddateid(), site.getTimezone(), site.getSid());
	}

	@Override
	public void deleteSite(Integer sid) {
		dml("wbsite.delete", sid);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isSiteEnabled(Integer sid) {
		return bool("wbsite.enable", sid);
	}

	@Override
	public void setSiteEnabled(Integer sid, Boolean enable) {
		dml("wbsite.enable.set", enable, sid);
	}

	@Override
	public IPaginatedList<WbSite> getPaginatedSiteList(PagingOptions options) {
		String sql = getSql("wbsite.list");
		int found = integer("count.wbsite.list");
		List<WbSite> list = new ArrayList<WbSite>();
		if (found > 0) {
			sql += " ORDER BY " + options.getSort() + " " + options.getDirection();
			list = objects(sql, WbSite.class);
		}
		return getPaginatedList(options, list, 25, found);
	}

	@Override
	public WbSite getSiteById(Integer sid) {
		return object("wbsite.by.sid", WbSite.class, sid);
	}

}
