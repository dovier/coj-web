/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import cu.uci.coj.model.Faq;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 */
@Component
public class FaqValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Faq.class.isAssignableFrom(clazz);
    }
    
    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "answer", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "question", "general.error.empty");
    }

}
