/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import cu.uci.coj.model.MailNotification;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 */
@Component
public class MailNotificationValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return MailNotification.class.isAssignableFrom(clazz);
    }
    
    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "general.error.empty");
    }

}
