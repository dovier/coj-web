package cu.uci.coj.utils;

import cu.uci.coj.adapters.VerdictDTOToSubmissionJudgeAdapter;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.SubmissionDAO;
import cu.uci.coj.mail.MailNotificationService;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.model.Verdicts;
import cu.uci.coj.model.dto.VerdictDTO;
import javax.annotation.Resource;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.stereotype.Component;

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
			VerdictDTO verdict = (VerdictDTO) jsonMessageConverter.fromMessage(message);
			SubmissionJudge submit = new VerdictDTOToSubmissionJudgeAdapter(verdict);
			
			if (submit.getSid() < 0) {
				testSubmit.add(submit);
				return;
			}

			// FIXME Esto es un parche para evitar que el motor sobreescriba la
			// fecha del submit. No sabemos porque ni como, pero lo hace, y eso
			// provoca problemas si los dos servers tienen horas diferentes
			if (!submit.isTest())
				if (submit.getCid() == 0)
					submissionDAO.updateDate(submit);
				else
					contestDAO.updateDate(submit);

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
