package cu.uci.coj.dao.impl;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cu.uci.coj.dao.AnnouncementDAO;
import cu.uci.coj.model.Announcement;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

import java.util.ArrayList;
import java.util.List;

@Repository("announcementDAO")
@Transactional
public class AnnouncementDAOImpl extends BaseDAOImpl implements AnnouncementDAO {

    @Transactional(readOnly = true)
    public IPaginatedList<Announcement> loadAnnouncements(String pattern, PagingOptions options) {

        if (pattern != null) {
            List<Announcement> announcements = objects("select.all.announcements", Announcement.class);
            List<Announcement> announcementsFiltred = new ArrayList();
            for (Announcement announcement : announcements) {
                String content = announcement.getContent();
                content = Jsoup.parse(content).text();
                if (content.contains(pattern)) {
                    announcementsFiltred.add(announcement);
                }
            }

            return getPaginatedList(options, announcementsFiltred, 30, announcementsFiltred == null ? 0 : announcementsFiltred.size());
        }
        return paginated("announcement", Announcement.class, 30, options);


//		if (pattern != null)
//			return paginated("announcement.pattern", Announcement.class,30,options, "%" + pattern + "%");
//		return paginated("announcement",Announcement.class, 30,options);
    }

    @Transactional(readOnly = true)
    public Integer countAnnouncements(String pattern) {

        if (pattern != null)
            return integer("count.announcement.pattern", "%" + pattern + "%");
        return integer("count.announcement");
    }
}
