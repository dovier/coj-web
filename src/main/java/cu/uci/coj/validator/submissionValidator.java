package cu.uci.coj.validator;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.utils.Utils;

@Component
public class submissionValidator implements Validator {

	@Resource
	private ProblemDAO problemDAO;
	@Resource
	private Utils utils;

	@Override
	public boolean supports(Class<?> clazz) {
		return SubmissionJudge.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		SubmissionJudge submit = (SubmissionJudge) o;
		String empty = "errormsg.10";
		String invalid = "errormsg.9";
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "timeUsed", empty);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "memoryUsed", empty);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", empty);

		int problemSourceLimit = problemDAO.getSourceLimitByPid(submit.getPid());
		if (submit.getCode().length() == 0 && submit.getUploadfile().getSize() <= 0) {
			errors.rejectValue("code", "errormsg.27");
		} else if (submit.getCode().length() > problemSourceLimit || submit.getUploadfile().getSize() > problemSourceLimit) {
			errors.rejectValue("code", "errormsg.28");
		} else if (submit.getCode().length() == 0 && !utils.supportExtension(submit.getUploadfile().getOriginalFilename())) {
			errors.rejectValue("code", "errormsg.29");
		} else if (submit.getCode().length() == 0) {
			try {
				submit.setCode(new String(submit.getUploadfile().getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
				errors.rejectValue("code", "Exception uploading file");
			}
		}

		if (!errors.hasErrors()) {
			try {
				Integer.valueOf(submit.getTimeUsed());
			} catch (Exception e) {
				errors.rejectValue("timeUsed", invalid);
			}

			try {
				Long.valueOf(submit.getMemoryUsed());
			} catch (Exception e) {
				errors.rejectValue("memoryUsed", invalid);
			}

		}
	}
}
