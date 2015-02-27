package cu.uci.coj.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cu.uci.coj.dao.AnnouncementDAO;
import cu.uci.coj.model.Announcement;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

@Repository("announcementDAO")
@Transactional
public class AnnouncementDAOImpl extends BaseDAOImpl implements AnnouncementDAO {

	@Transactional(readOnly = true)
	public IPaginatedList<Announcement> loadAnnouncements(String pattern, PagingOptions options) {

		if (pattern != null)
			return paginated("announcement.pattern", Announcement.class,30,options, "%" + pattern + "%");
		return paginated("announcement",Announcement.class, 30,options);
	}
	
	@Transactional(readOnly = true)
	public Integer countAnnouncements(String pattern) {

		if (pattern != null)
			return integer("count.announcement.pattern",  "%" + pattern + "%");
		return integer("count.announcement");
	}
}
