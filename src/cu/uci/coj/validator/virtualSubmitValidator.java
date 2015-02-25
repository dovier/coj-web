/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.SubmissionJudge;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @since 2010-09-01
 * @see http://coj.uci.cu
 */
@Component
public class virtualSubmitValidator implements Validator {

    @Resource
    private ContestDAO contestDAO;
    @Resource
    private ProblemDAO problemDAO;
    @Resource
    private UserDAO userDAO;

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

    public ProblemDAO getproblemDAO() {
        return problemDAO;
    }

    public void setproblemDAO(ProblemDAO problemDAO) {
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

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pid", "errormsg.24");
        boolean test1 = !(submit.getCid()>=10000 && contestDAO.isProblemInVirtualContest(submit.getPid()));
        boolean test2 = !contestDAO.isProblemInContest(submit.getPid(), submit.getCid(), problemDAO.getCurrentLevel(userDAO.integer("user.uid", submit.getUsername()), submit.getCid()));
        boolean test =  test1 && test2;
        if (!submit.getContest().isRunning()) {
            errors.rejectValue("cid", "errormsg.31");
        } else if (!errors.hasFieldErrors("pid") && test) {
            errors.rejectValue("pid", "errormsg.25");
        }

        if (!(submit.getContest().getRegistration() == 0 && submit.getContest().isAllow_registration()) && !contestDAO.isInContest(submit.getCid(), userDAO.integer("user.uid", submit.getUsername()))) {
            errors.rejectValue("username", "errormsg.30");
        }
        if (submit.getCode().length() == 0 && submit.getUploadfile().getSize() <= 0) {
            errors.rejectValue("code", "errormsg.27");
        } else if (submit.getCode().length() > 100 * 1024 || submit.getUploadfile().getSize() > 100 * 1024) {
            errors.rejectValue("code", "errormsg.28");
        } else if (submit.getCode().length() == 0 && !supportExtension(submit.getUploadfile().getOriginalFilename())) {
            errors.rejectValue("code", "errormsg.29");
        } else {
            if (submit.getCode().length() == 0) {
                try {
                    submit.setCode(new String(submit.getUploadfile().getBytes()));
                } catch (IOException ex) {
                    Logger.getLogger(virtualSubmitValidator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private boolean supportExtension(String filename) {
        String[] extensions = ".c,.cpp,.java,.py,.cs,.pl,.pas,.php,.rb,.txt".split(",");
        for (int i = 0; i < extensions.length; i++) {
            String string = extensions[i];
            if (filename.endsWith(string)) {
                return true;
            }
        }
        return false;
    }
}
