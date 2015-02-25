/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Clarification;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class sendClarificationValidator implements Validator {

    @Resource
    private UserDAO userDAO;

    public boolean supports(Class<?> type) {
        return Clarification.class.isAssignableFrom(type);
    }

    public void validate(Object o, Errors errors) {
        Clarification clarification = (Clarification) o;
        try {
            //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "general.error.empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "general.error.empty");
            if (clarification.getUsername() != null && clarification.getUsername().length() > 0) {
                String[] list = clarification.getUsername().split(";");
                if (list.length > 10) {
                    errors.rejectValue("username", "general.error.users.exceed");
                }
                for (int i = 0; (i < (list.length) && (!errors.hasErrors())); i++) {
                    String string = list[i];
                    if (!clarification.isForall() && !userDAO.isUser(string)) {
                        errors.rejectValue("username", "general.error.user.notexist");
                    }
                    if (!errors.hasErrors() && !clarification.isForall()) {
                        int uid = userDAO.integer("user.uid", clarification.getUsername());
                        if (!userDAO.isInContest(uid, clarification.getCid()) && !userDAO.isJudgeInContest(uid, clarification.getCid())) {
                            errors.rejectValue("username", "clarification.error.notin");
                        }
                    }
                }
            }
            if (!userDAO.isJudgeInContest(userDAO.integer("user.uid", clarification.getUsername()), clarification.getCid())) {
                errors.rejectValue("general", "general.error.clarification.judges");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
