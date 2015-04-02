/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.User;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.impl.EmailValidator;
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
public class forgottenValidator implements Validator {

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

        try {
            
            
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "judge.register.error.email");
            String email = user.getEmail();
            EmailValidator a = new EmailValidator();
            if (!a.isValid(email, null)) {
                errors.rejectValue("email", "judge.register.error.bademail");
            }
            if (!errors.hasErrors()) {
                try {
                    boolean emailExist = userDAO.bool("exist.user.bymail", email);
                    if (!emailExist) {
                        errors.rejectValue("email", "judge.forgotten.emailexist");
                    }
                } catch (Exception e) {
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
