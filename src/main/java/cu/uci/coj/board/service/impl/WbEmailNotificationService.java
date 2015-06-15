package cu.uci.coj.board.service.impl;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import cu.uci.coj.board.dao.WbContestDAO;
import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.board.service.WbNotificationService;
import cu.uci.coj.mail.MailService;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;
import cu.uci.coj.utils.RenderFTLTemplate;
import freemarker.template.Configuration;
import freemarker.template.SimpleDate;
import freemarker.template.Template;
import freemarker.template.TemplateException;



/**
*
* @author Eddy Roberto Morales Perez
*/

@Service("wbEmailNotificationService")
public class WbEmailNotificationService implements WbNotificationService {

	@Resource
	WbSiteDAO wbSiteDAO;
	@Resource
	WbContestDAO wbContestDAO;
	@Resource
	MailService mailService;
	@Resource
	RenderFTLTemplate renderFTLTemplate; 

	@Override
	public void sendNotifications(List<WbSite> newcontestNotificationSites, List<WbSite> changedNotificationSites, List<WbContest> nearContestNotification) {
		if(newcontestNotificationSites != null || changedNotificationSites != null || nearContestNotification != null) {	
			Map<String, Object> model = new HashMap<String, Object>();
			
			List<WbSite> list = wbSiteDAO.getSiteList();
			HashMap<String, WbSite> map =  new HashMap<String, WbSite>();
			
			for(int i = list.size()-1;i>=0;i--) {
				map.put(String.valueOf(list.get(i).getSid()), list.get(i));
			}
			
			model.put("mapsites", map);
			model.put("nearContests", nearContestNotification);
			model.put("newContestsSites", newcontestNotificationSites);
			model.put("changedSites", changedNotificationSites);
					
			String message = renderFTLTemplate.render("/email.ftl", model);
					
			List<String> mails = wbSiteDAO.strings("users.wboard.notifications");
			mailService.sendBulkMessage(mails.toArray(new String[0]), "COJ - COJboard Contest Notifications", message, true);
		}
	}
}
