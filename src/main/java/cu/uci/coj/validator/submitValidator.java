package cu.uci.coj.validator;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cu.uci.coj.dao.CourseDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.utils.Utils;

@Component
public class submitValidator implements Validator {

	@Resource
	private Utils utils;
    @Resource
    private ProblemDAO problemDAO;
    @Resource
    private UtilDAO utilDAO;
    @Resource
    private CourseDAO courseDAO;

    public UtilDAO getUtilDAO() {
        return utilDAO;
    }

    public void setUtilDAO(UtilDAO utilDAO) {
        this.utilDAO = utilDAO;
    }

    public ProblemDAO getProblemDAO() {
        return problemDAO;
    }

    public void setProblemDAO(ProblemDAO problemDAO) {
        this.problemDAO = problemDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SubmissionJudge.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	SubmissionJudge submit = (SubmissionJudge) o;
        submit.getLanguageIdByKey();
        if (utilDAO.bool("submit.enabled")) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pid", "errormsg.25");
            if (errors.hasFieldErrors("pid") || (!problemDAO.exists(submit.getPid()) || !problemDAO.isEnabled(submit.getPid())) || (submit.getCourse_id() != 0 && !courseDAO.isProblemAvailableInCourse(submit.getCourse_id(), submit.getPid()))) {
                if(!errors.hasFieldErrors("pid"))
                errors.rejectValue("pid", "errormsg.25");
            } else {
                if (problemDAO.isDisable24h(submit.getPid()) && !submit.isHaspriviligeForProblem()) {
                    errors.rejectValue("pid", "errormsg.25");
                } else {
                    if (!errors.hasErrors() && !problemDAO.hasLanguageAvailable(submit.getPid(), submit.getLid())) {
                        errors.rejectValue("key", "errormsg.26");
                    }
                    int problemSourceLimit = problemDAO.getSourceLimitByPid(submit.getPid(),submit.getLid());
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
                }
            }
        } else {
            errors.rejectValue("pid", "errormsg.43");
        }

    }

    private boolean supportExtension(String filename) {
        String[] extensions = ".c,.cpp,.java,.py,.cs,.pl,.pas,.php,.rb,.txt,.sh".split(",");
        for (int i = 0; i < extensions.length; i++) {
            String string = extensions[i];
            if (filename.endsWith(string)) {
                return true;
            }
        }
        return false;
    }
}
