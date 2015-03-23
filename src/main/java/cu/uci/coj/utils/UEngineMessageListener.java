package cu.uci.coj.utils;

import javax.annotation.Resource;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.stereotype.Component;

import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.SubmissionDAO;
import cu.uci.coj.mail.MailNotificationService;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.model.Verdicts;

@Component
public class UEngineMessageListener implements MessageListener {

	@Resource
	private JsonMessageConverter jsonMessageConverter;
	@Resource
	private SubmissionDAO submissionDAO;

	@Resource
	private ContestDAO contestDAO;
	@Resource
	private MailNotificationService mailNotificationService;
	
	@Resource
	private TestSubmitContainer testSubmit;

	@Override
	public void onMessage(Message message) {

		try {
			SubmissionJudge submit = (SubmissionJudge) jsonMessageConverter
					.fromMessage(message);
			
			if (submit.getSid() < 0) {
				testSubmit.add(submit);
				return;
			}
			
			if (submit.getVerdict() == Verdicts.CTLE)
				submit.setStatus("Time Limit Exceeded");

			if (submit.getCid() != 0) {
				boolean virtual = contestDAO
						.bool("select virtual from contest_submition where submit_id=?",
								submit.getSid());

				Contest contest = contestDAO
						.loadContest(!virtual ? submit.getCid()
								: contestDAO
										.integer(
												"select cid from individual_virtual_contest where vid=?",
												submit.getCid()));

				submit.setVirtual(virtual);
				if (virtual) {
					contestDAO.updateAfterSubmitionContestVirtual(submit);
				}

				contestDAO.applyEffects(submit, contest);
			} else {
				submissionDAO.applyEffects(submit);
				mailNotificationService.sendSubmitionNotification(submit);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
