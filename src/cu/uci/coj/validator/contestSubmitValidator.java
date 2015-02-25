package cu.uci.coj.validator;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.utils.Utils;

@Component
public class contestSubmitValidator implements Validator {

	@Resource
	private Utils utils;
    @Resource
    private ContestDAO contestDAO;
    @Resource
    private UserDAO userDAO;
    @Resource
    private ProblemDAO problemDAO;

    public ProblemDAO getproblemDAO() {
        return problemDAO;
    }

    public void setproblemDAO(ProblemDAO problemDAO) {
        this.problemDAO = problemDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public ContestDAO getContestDAO() {
        return contestDAO;
    }

    public void setContestDAO(ContestDAO contestDAO) {
        this.contestDAO = contestDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SubmissionJudge.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {SubmissionJudge submit = (SubmissionJudge) o;
        try {
            if (!submit.getContest().isRunning()) {
                errors.rejectValue("cid", "errormsg.14");
            }
            submit.getLanguageIdByKey();
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pid", "errormsg.24");
            
            
            boolean isteam = userDAO.bool("is.team",submit.getUsername());            
            if (!((submit.getContest().getContestant() == 3 || (isteam && submit.getContest().getContestant() == 1) || (!isteam && submit.getContest().getContestant() == 2) ) && submit.getContest().getRegistration() == 0 && submit.getContest().isAllow_registration()) && !contestDAO.isInContest(submit.getCid(), userDAO.integer("user.uid",submit.getUsername()))) {
                errors.rejectValue("username", "errormsg.30");
            }

            int problemSourceLimit = problemDAO.getSourceLimitByPid(submit.getPid());
            if (submit.getCode().length() == 0 && submit.getUploadfile().getSize() <= 0) {
                errors.rejectValue("code", "errormsg.27");
            } else if (submit.getCode().length() > problemSourceLimit || submit.getUploadfile().getSize() > problemSourceLimit) {
                errors.rejectValue("code", "errormsg.28");
            } else if (submit.getCode().length() == 0 && !utils.supportExtension(submit.getUploadfile().getOriginalFilename())) {
                errors.rejectValue("code", "errormsg.29");
            } else if (submit.getCode().length() == 0) {
                submit.setCode(new String(submit.getUploadfile().getBytes()));
            }
            
            if (!errors.hasFieldErrors("pid") && !contestDAO.isProblemInContest(submit.getPid(), submit.getCid(), problemDAO.getCurrentLevel(userDAO.integer("user.uid",submit.getUsername()), submit.getCid()))) {
                errors.rejectValue("pid", "errormsg.25");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
}
