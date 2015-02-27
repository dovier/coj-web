package cu.uci.coj.board.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import cu.uci.coj.board.service.WbNotificationService;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;
import cu.uci.coj.utils.SocialIntegration;

/**
*
* @author Eddy Roberto Morales Perez
*/

@Service("wbTwitterNotificationService")
public class WbTwitterNotificationService implements WbNotificationService {

	@Resource
	SocialIntegration socialIntegration;

	@Override
	public void sendNotifications(List<WbSite> newcontestNotificationSites, List<WbSite> changedNotificationSites, List<WbContest> nearContestNotification) {
		String text = "";		
		WbContest contest;
		
		if(nearContestNotification != null) {
			for(int i = 0;i<nearContestNotification.size();i++) {
				contest = nearContestNotification.get(i);
				text = "The contest " + contest.getName() + " is near. More info: http://coj.uci.cu/wboard/contests.xhtml.";
				try {
					socialIntegration.getTwitterTemplate().timelineOperations().updateStatus(text);	
				} catch(Exception e) {
					System.out.println("Update on twitter failed.");
				}
			}
		}		

		if(newcontestNotificationSites != null) {
			for(int i = 0;i<newcontestNotificationSites.size();i++) {
				WbSite site = newcontestNotificationSites.get(i);
				for(int j = 0;j<site.getContests().size();j++) {			
					contest = site.getContests().get(j);
					text = "New contest: " + contest.getName() + ". More info: http://coj.uci.cu/wboard/contests.xhtml.";
					try {
						socialIntegration.getTwitterTemplate().timelineOperations().updateStatus(text);	
					} catch(Exception e) {
						System.out.println("Update on twitter failed.");
					}		
				}
			}
		}
		
		if(changedNotificationSites != null) {
			for(int i = 0;i<changedNotificationSites.size();i++) {
				WbSite site = changedNotificationSites.get(i);
				for(int j = 0;j<site.getContests().size();j++) {			
					contest = site.getContests().get(j);
					text = "The contest " + contest.getName() + " has suffered changes. More info: http://coj.uci.cu/wboard/contests.xhtml.";
					try {
						socialIntegration.getTwitterTemplate().timelineOperations().updateStatus(text);	
					} catch(Exception e) {
						System.out.println("Update on twitter failed.");
					}		
				}
			}
		}
	}	
}
