/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Clarification;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @since 2010-09-01
 * @see http://coj.uci.cu
 */

@Component
public class clarificationValidator implements Validator {

    @Resource
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Clarification.class.isAssignableFrom(clazz);
    }  

    @Override
    public void validate(Object o, Errors errors) {
        Clarification clarification = (Clarification) o;
        try {            
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "general.error.empty");
            if (!userDAO.isInContest(userDAO.integer("select.uid.by.username",clarification.getUsername()), clarification.getCid()) && !userDAO.isJudgeInContest(userDAO.integer("select.uid.by.username",clarification.getUsername()), clarification.getCid())) {
                errors.rejectValue("general", "errormsg.30");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void validateSend(Object o, Errors errors) {
        Clarification clarification = (Clarification) o;
        try {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "general.error.empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "general.error.empty");
            if (clarification.getUsername() != null && clarification.getUsername().length() > 0) {
                String[] list = clarification.getUsername().split(";");
                if (list.length > 10) {
                    errors.rejectValue("username", "general.error.users.exceed");
                }
                for (int i = 0; i < list.length && !errors.hasErrors(); i++) {
                    String string = list[i];
                    if (!clarification.isForall() && !userDAO.isIn(string)) {
                        errors.rejectValue("username", "general.error.user.notexist");
                    }
                    if (!errors.hasErrors() && !clarification.isForall()) {
                        int uid = userDAO.integer("select.uid.by.username",string);
                        if (!userDAO.isInContest(uid, clarification.getCid()) && !userDAO.isJudgeInContest(uid, clarification.getCid())) {
                            errors.rejectValue("username", "clarification.error.notin");
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void validateSendInternal(Object o, Errors errors) {
        Clarification clarification = (Clarification) o;
        try {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "mail.msg.error.subject.empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usernameto", "mail.msg.error.to.empty");
            if (!errors.hasFieldErrors("usernameto")) {
                String[] to = clarification.getUsernameto().split(";");
                if (to.length <= 10) {
                    for (int i = 0; i < to.length; i++) {
                        String string = to[i].replaceAll(" ", "");
                        if (!userDAO.isIn(string)) {
                            errors.rejectValue("usernameto", "general.error.users.invalid");
                            break;
                        }
                    }
                } else {
                    errors.rejectValue("usernameto", "general.error.users.exceed");
                }
            }
            if (!errors.hasErrors()) {
                Map<String,Object> mapa = userDAO.getMailValues(clarification.getUsernamefrom());
                    Integer max = (Integer)mapa.get("mail_quote");
                    Integer consumed = (Integer)mapa.get("consume_quote");
                    int msgSize = clarification.getDescription().getBytes().length + clarification.getTitle().getBytes().length;
                    if (consumed + msgSize > max) {
                        errors.rejectValue("general", "general.error.mail.full");
                    }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void validateSendPublicNotification(Object o, Errors errors) {
        Clarification clarification = (Clarification) o;
        try {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "general.error.empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "general.error.empty");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
