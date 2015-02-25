/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import cu.uci.coj.model.Announcement;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author juanky
 */
public class manageannValidator implements Validator {

    public boolean supports(Class<?> type) {
        return Announcement.class.isAssignableFrom(type);
    }

    public void validate(Object o, Errors errors) {
        Announcement announcement = (Announcement) o;        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "general.error.empty");
    }
}
