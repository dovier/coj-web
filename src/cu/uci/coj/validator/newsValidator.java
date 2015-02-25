/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cu.uci.coj.model.News;

@Component
public class newsValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return News.class.isAssignableFrom(clazz);
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "overview", "general.error.empty");
    }
}
