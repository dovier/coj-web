/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.mail;

import cu.uci.coj.model.AccountActivation;
import cu.uci.coj.model.Mail;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.model.User;

public interface MailNotificationService extends MailService {
	
	public void setDisabledMailNotificationFlag(boolean disabled);
	public void sendContestCreatedNotification(Integer cid);

	void sendContestStartedNotification(Integer cid);
	public void sendSubmitionNotification(SubmissionJudge submit);

	public void sendEntryNotification(Integer eid);
	
	public void sendProblemNotification(Integer pid);

	public void sendAccountVerification(User user, String token);

	public void sendAccountVerificationReminder(AccountActivation activation);

	public void sendBulkAccountVerificationReminder();
	
	public void sendEmailChanged(User user, String token);
	
	public void sendForgottenPasswordEmail(String to, String subject, String msg);
	
	public void sendNewPrivateMessageNotification(Mail mail, String username);

}
