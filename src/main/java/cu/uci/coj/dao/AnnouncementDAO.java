package cu.uci.coj.dao;

import cu.uci.coj.model.Announcement;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

public interface AnnouncementDAO extends BaseDAO {

	public Integer countAnnouncements(String pattern);
    public IPaginatedList<Announcement> loadAnnouncements(String pattern, PagingOptions options);
}
