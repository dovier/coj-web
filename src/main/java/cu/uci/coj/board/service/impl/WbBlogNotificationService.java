package cu.uci.coj.board.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cu.uci.coj.board.service.WbNotificationService;
import cu.uci.coj.dao.EntryDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Entry;
import cu.uci.coj.model.User;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;
import cu.uci.coj.utils.EntryHelper;

/**
*
* @author Eddy Roberto Morales Perez
*/

@Service("wbBlogNotificationService")
public class WbBlogNotificationService implements WbNotificationService {

	@Resource
	UserDAO userDAO;
	@Resource
	EntryDAO entryDAO;
	@Resource
	EntryHelper entryHelper;
	
	@PostConstruct
	void init() {
		if(userDAO.idByUsername("COJbot") == null) {
			User user = new User();
			user.setUsername("COJbot");
			user.setNick("COJbot");
			user.setEmail("coj@uci.cu");
			user.setEnabled(true);
			user.setLid(1);
			user.setLocale(1);
			user.setJoin_date(new Date());
			user.setDob(new Date());
			userDAO.InsertUser(user);
		}
	}
	
	private Entry createEntry(String text) {
		Entry entry = new Entry(text, new Date());
		entryHelper.preProcessEntry(entry);
		return entry;
	}
	
	@Override
	public void sendNotifications(List<WbSite> newcontestNotificationSites, List<WbSite> changedNotificationSites, List<WbContest> nearContestNotification) {
		String text = "";		
		WbContest contest;
		
		if(newcontestNotificationSites != null) {
			for(int i = 0;i<newcontestNotificationSites.size();i++) {
				WbSite site = newcontestNotificationSites.get(i);
				for(int j = 0;j<site.getContests().size();j++) {			
					contest = site.getContests().get(j);
					text = "New contest: " + contest.getName() + ". More info: http://coj.uci.cu/wboard/contests.xhtml";
					entryDAO.addEntry(createEntry(text), true, "COJbot");		
				}
			}
		}
		
		if(nearContestNotification != null) {
			for(int i = 0;i<nearContestNotification.size();i++) {
				contest = nearContestNotification.get(i);
				text = "The contest " + contest.getName() + " is near. More info: http://coj.uci.cu/wboard/contests.xhtml";
				entryDAO.addEntry(createEntry(text), true, "COJbot");	
			}
		}		
		
		if(changedNotificationSites != null) {
			for(int i = 0;i<changedNotificationSites.size();i++) {
				WbSite site = changedNotificationSites.get(i);
				for(int j = 0;j<site.getContests().size();j++) {			
					contest = site.getContests().get(j);
					text = "The contest " + contest.getName() + " has suffered changes. More info: http://coj.uci.cu/wboard/contests.xhtml";
					entryDAO.addEntry(createEntry(text), true, "COJbot");	
				}
			}
		}		
	}
}
