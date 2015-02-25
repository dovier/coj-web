package cu.uci.coj.mail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cu.uci.coj.config.Config;
import cu.uci.coj.dao.BaseDAO;
import cu.uci.coj.dao.SubmissionDAO;
import cu.uci.coj.model.AccountActivation;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Mail;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.model.User;
import cu.uci.coj.utils.RenderFTLTemplate;

@Service("mailNotificationService")
public class MailNotificationsServiceImpl extends MailServiceImpl implements MailNotificationService {

	private boolean disabledMailNotificationFlag;

	@Resource
	private BaseDAO baseDAO;
	@Resource
	private SubmissionDAO submissionDAO;
	@Resource
	RenderFTLTemplate renderFTLTemplate;

	@Override
	public void sendBulkAccountVerificationReminder() {
		if (disabledMailNotificationFlag)
			return;

		List<AccountActivation> activations = baseDAO.objects("select.unactivated.emails", AccountActivation.class);

		for (AccountActivation activation : activations) {
			sendAccountVerificationReminder(activation);
		}
	}

	@Override
	public void sendAccountVerificationReminder(AccountActivation activation) {
		if (disabledMailNotificationFlag)
			return;

		String subject = "COJ - User Account Validation.";
		String msg = Config.getProperty("register.msg.act.reminder");
		msg = msg.replaceFirst("<user>", activation.getUsername());
		msg = msg.replaceAll("<token>", activation.getToken());
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("text", msg);		
		msg = renderFTLTemplate.render("/normal-notification.ftl", model);
		
		sendMessage(activation.getEmail(), subject, msg, true);
	}

	@Override
	public void sendAccountVerification(User user, String token) {

		String to = user.getEmail();
		String subject = "COJ - User Account Validation.";
		String msg = Config.getProperty("register.msg.act");
		msg = msg.replaceFirst("<token>", token);
		msg = msg.replaceAll("<user>", user.getName() + " " + user.getLastname());
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("text", msg);		
		msg = renderFTLTemplate.render("/normal-notification.ftl", model);
		
		sendMessage(user.getEmail(), subject, msg, true);
	}

	@Override
	public void sendEmailChanged(User user, String token) {
		
		String subject = "COJ - Email Changed.";
		String msg = Config.getProperty("register.msg.email");
		msg = msg.replaceFirst("<token>",  token);
		msg = msg.replaceAll("<user>", user.getName() + " " + user.getLastname());
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("text", msg);		
		msg = renderFTLTemplate.render("/normal-notification.ftl", model);
		
		sendMessage(user.getEmail(), subject, msg, true);
	}

	@Override
	public void sendContestCreatedNotification(Integer cid) {

		if (disabledMailNotificationFlag)
			return;

		Contest contest = baseDAO.object("contest.for.notification", Contest.class, cid);
		List<String> mails = null;
		
		String subject = "COJ - New Contest";
		String msg = "Contest " + contest.getCid() + ": " + contest.getName() + " has been created and will start at " + contest.getInitdate()
				+ ". \n\nBest regards.";
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("text", msg);		
		msg = renderFTLTemplate.render("/normal-notification.ftl", model);
		

		int i = 0;

		do {
			mails = baseDAO.strings("users.contest.notifications", 50, i);
			i += 50;

			if (!CollectionUtils.isEmpty(mails))
				sendBulkMessage(mails.toArray(new String[0]), "COJ - New Contest", msg, true);
		} while (!CollectionUtils.isEmpty(mails));
	}

	@Override
	public void sendContestStartedNotification(Integer cid) {
		if (disabledMailNotificationFlag)
			return;

		Contest contest = baseDAO.object("contest.for.notification", Contest.class, cid);
		List<String> mails = null;

		String subject = "COJ - Contest Started";
		String msg = "Contest " + contest.getCid() + ": " + contest.getName() + " has started."
				+ "<br/><br/><br/>Best regards.";
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("text", msg);		
		msg = renderFTLTemplate.render("/normal-notification.ftl", model);
		
		int i = 0;

		do {
			mails = baseDAO.strings("users.contest.notifications", 50, i);
			i += 50;

			if (!CollectionUtils.isEmpty(mails))
				sendBulkMessage(mails.toArray(new String[0]), "COJ - Contest Started", msg, true);
		} while (!CollectionUtils.isEmpty(mails));
	}

	@Override
	public void sendSubmitionNotification(SubmissionJudge submit) {
		if (!disabledMailNotificationFlag)
			return;

		SubmissionJudge submission = submissionDAO.getSourceCode(submit.getSid());
		SubmissionJudge submitEmail = baseDAO.object("submition.for.notification", SubmissionJudge.class, submit.getSid());
		if (submission != null && submitEmail != null) {
			submission.setSid(submit.getSid());
			submission.setStatus(submit.getStatus());
			submission.setTitle(submitEmail.getTitle());
			submission.setEmail(submitEmail.getEmail());

			if (submission != null) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("submission", submission);
				String message = renderFTLTemplate.render("/submition-notification.ftl", model);
				String subject = "COJ - Submission " + submission.getSid() + " for the problem " + submission.getTitle() + " got a status of " + submission.getStatus();

				sendMessageWithAttachment(submission.getEmail(), subject, message, submission.getFilename(), submission.getCode(), true);
			}
		}
	}

	@Override
	public void sendEntryNotification(Integer eid) {

		if (disabledMailNotificationFlag)
			return;

		// TODO Auto-generated method stub

	}

	@Override
	public void sendProblemNotification(Integer pid) {

		if (disabledMailNotificationFlag)
			return;

		Problem problem = baseDAO.object("problem.for.notification", Problem.class, pid);
		List<String> mails = null;

		String subject = "COJ - New 24h Problem";
		String msg = "Problem " + problem.getPid() + ": " + problem.getTitle()
				+ " has been added.<br/><br/><br/>Best regards.";
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("text", msg);		
		msg = renderFTLTemplate.render("/normal-notification.ftl", model);
		
		int i = 0;

		do {
			mails = baseDAO.strings("users.problem.notifications", 50, i);
			i += 50;

			if (!CollectionUtils.isEmpty(mails))
				sendBulkMessage(mails.toArray(new String[0]), "COJ - New 24h Problem", msg, true);
		} while (!CollectionUtils.isEmpty(mails));

	}

	@Override
	public void setDisabledMailNotificationFlag(boolean disabled) {
		this.disabledMailNotificationFlag = disabled;
	}

	public void sendForgottenPasswordEmail(String to, String subject, String msg) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("text", msg);		
		msg = renderFTLTemplate.render("/normal-notification.ftl", model);
		sendMessage(to, subject, msg, true);
	}
	
	public void sendNewPrivateMessageNotification(Mail mail, String username){
		String to = baseDAO.string("email.by.username.if.newprivatemessagenotif", username);
		if(to == null) return;
		
		String subject = "COJ - New private message has arrived";
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("mail", mail);
		model.put("username", username);
		
		String msg = renderFTLTemplate.render("/newprivatemessage-notification.ftl", model);
		sendMessage(to, subject, msg, true);
	}
}
