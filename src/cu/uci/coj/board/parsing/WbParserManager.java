package cu.uci.coj.board.parsing;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cu.uci.coj.board.dao.WbContestDAO;
import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.board.parsing.impl.WbCojParser;
import cu.uci.coj.board.parsing.impl.WbCodeforcesParser;
import cu.uci.coj.board.parsing.impl.WbUvaParser;
import cu.uci.coj.board.parsing.impl.WbCodechefParser;
import cu.uci.coj.board.service.WbContestService;
import cu.uci.coj.board.service.WbNotificationService;
import cu.uci.coj.board.util.ConnectionErrorException;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;

/**
*
* @author Eddy Roberto Morales Perez
*/

@Component("wbParserManager")
public class WbParserManager {
	@Value("false")
	boolean notified;
	
	@Resource
	WbSiteDAO wbSiteDAO;
	@Resource
	WbContestDAO wbContestDAO; 

	@Resource
	WbContestService wbContestService;
	
	@Autowired
	List<WbParser> parserList;
	@Autowired
	List<WbNotificationService> notificationList;
	
	
	public void doWork() {		
		List<WbContest> contests;
		wbSiteDAO.dml("insert.log", "Parsing sites for the WebBoard", "No user");		

		
		for(int i = 0;i<parserList.size();i++) {
			if(wbSiteDAO.isSiteEnabled(parserList.get(i).getSite().getSid())) {
				if(!parserList.get(i).getSite().isCompleted()) {
					try {
						contests = parserList.get(i).parse();				
						
						parserList.get(i).getSite().setCompleted(true);
						wbSiteDAO.setCompleted(parserList.get(i).getSite().getSid(), true);
						
						for(int j = 0;j<contests.size();j++) {
							try {
								wbContestService.addToDatabaseAndCreateNotification(contests.get(j));
							} catch(Exception e) {}
						}
					} catch(ConnectionErrorException e) {
						//e.printStackTrace();
					}				
				}
			}
		}
		
		if(isNotificationTime()) {
			List<WbSite> newcontestNotificationSites = wbSiteDAO.getListSitesWithContestsNewContestNotification();
			List<WbSite> changedNotificationSites = wbSiteDAO.getListSitesWithContestsScheduleChangedNotification();		
			
			List<WbContest> nearContestNotification = wbContestDAO.getNearContestsNotification();

			
			for(int i = 0;i<notificationList.size();i++) {
				try {
					notificationList.get(i).sendNotifications(newcontestNotificationSites, changedNotificationSites, nearContestNotification);			
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			wbContestDAO.cleanNotifications();
			
			for(int i = 0;i<parserList.size();i++) {
				parserList.get(i).getSite().setCompleted(false);
				wbSiteDAO.setCompleted(parserList.get(i).getSite().getSid(), false);
			}
		}
	}	
	 
	public boolean parseSite(Integer sid) {
		for(int i = 0;i<parserList.size();i++) {
			if(parserList.get(i).getSite().getSid() == sid) {
				try {
					List<WbContest> contests = parserList.get(i).parse();				
					
					parserList.get(i).getSite().setCompleted(true);
					wbSiteDAO.setCompleted(parserList.get(i).getSite().getSid(), true);
					
					for(int j = 0;j<contests.size();j++) {
						wbContestService.addToDatabaseAndCreateNotification(contests.get(j));
					}
					return true;
				} catch(ConnectionErrorException e) {
					//e.printStackTrace();	
				}
				break;
			}
		}
		return false;
	}

	// Notifications first time after 9:00
	private boolean isNotificationTime() {
		Date now = new Date();		
				
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 9);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		if(now.after( calendar.getTime() )) {
			if(!notified) {
				notified = true;
				return true;
			}
		} else {
			notified = false;
		}		
		return false;
	}
}