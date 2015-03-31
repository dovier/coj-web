/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import cu.uci.coj.model.Language;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class languageValidator implements Validator {

    /**
     *
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Language.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Language language = (Language) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "language", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name_bin", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "key", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descripcion", "general.error.empty");
    }
}
