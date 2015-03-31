/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cu.uci.coj.model.Entry;

@Component
public class entryValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return Entry.class.isAssignableFrom(clazz);
    }

    public void validate(Object o, Errors errors) {
    	
    	
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "general.error.empty");
        
        Entry entry = (Entry)o;
        if ((entry.getText().length()) > 255) {
            errors.rejectValue("text",
                    "entry.text.length");
        }
    }
}




