/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.User;

import java.util.Calendar;
import java.util.Date;
import javax.annotation.Resource;

import org.apache.commons.validator.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @version Caribbean Online Judge(COJ) v2.0
 * @see http://coj.uci.cu
 * @since 2010-09-01
 */
@Component
public class registrationValidator implements Validator {

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
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nick", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "general.error.empty");

        if (!errors.hasFieldErrors("username")) {
            if (user.getUsername().length() < 3) {
                errors.rejectValue("username", "errormsg.2");
            } else if (user.getUsername().length() > 15) {
                errors.rejectValue("username", "errormsg.1");
            } else if (!user.getUsername().matches("[a-zA-Z0-9]+")) {
                errors.rejectValue("username", "errormsg.3");
            } else if (userDAO.bool("exists.user.byusername", user.getUsername())) {
                errors.rejectValue("username", "errormsg.18");
            }
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(user.getYear(), user.getMonth() - 1, user.getDay());
        Date date = calendar.getTime();

        if (date.after(new Date())) {
            errors.rejectValue("year", "errormsg.61");
        }
        if (!errors.hasFieldErrors("nick")) {
            if (user.getNick().length() < 3) {
                errors.rejectValue("nick", "errormsg.5");
            } else if (user.getNick().length() > 15) {
                errors.rejectValue("nick", "errormsg.4");
            }
        }
        if (!errors.hasFieldErrors("name")) {
            if (user.getName().length() < 1) {
                errors.rejectValue("name", "errormsg.7");
            } else if (user.getName().length() > 30) {
                errors.rejectValue("name", "errormsg.6");
            } else if (!user.getName().matches("[a-zA-Z\\.\\-\\'\\s]+")) {
                errors.rejectValue("name", "errormsg.8");
            }
        }
        if (!errors.hasFieldErrors("lastname")) {
            if (user.getLastname().length() < 1) {
                errors.rejectValue("lastname", "errormsg.10");
            } else if (user.getLastname().length() > 50) {
                errors.rejectValue("lastname", "errormsg.9");
            } else if (!user.getLastname().matches("[a-zA-Z\\.\\-\\'\\s]+")) {
                errors.rejectValue("lastname", "errormsg.11");
            }
        }
        if (user.getCountry_id() == 0) {
            errors.rejectValue("country", "errormsg.19");
        }


        String email = user.getEmail();
        boolean valid = EmailValidator.getInstance().isValid(email);
        if (!errors.hasFieldErrors("email")) {
            if (!valid || email.length() == 0) {
                errors.rejectValue("email", "errormsg.12");
            } else if (userDAO.bool("exist.user.bymail", user.getEmail())) {
                errors.rejectValue("email", "errormsg.20");
            } else if (!user.getCemail().equals(email)) {
                errors.rejectValue("cemail", "errormsg.13");
            } else {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "errormsg.21");
            }
        }
        if (!errors.hasFieldErrors("password")) {
            if (user.getPassword().length() < 8) {
                errors.rejectValue("password", "errormsg.15");
            } else if (user.getPassword().length() > 100) {
                errors.rejectValue("password", "errormsg.14");
            } else if (!user.getPassword().equals(user.getConfirmPassword())) {
                errors.rejectValue("confirmPassword", "errormsg.16");
            }
        }
        if (!user.isAgreementcojtos()) {
            errors.rejectValue("agreementcojtos", "errormsg.17");
        }
    }
}
