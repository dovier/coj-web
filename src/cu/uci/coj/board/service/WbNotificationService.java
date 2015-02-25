package cu.uci.coj.board.service;

import java.util.List;

import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;

public interface WbNotificationService {
	public void sendNotifications(List<WbSite> newcontestNotificationSites, List<WbSite> changedNotificationSites, List<WbContest> nearContestNotification);
}
