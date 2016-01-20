package cu.uci.coj.validator;

import cu.uci.coj.model.ProblemSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.annotation.Resource;

/**
 * Created by alison on 14/01/16.
 */

@Component
public class ProblemSourceValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProblemSource.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProblemSource problemSource = (ProblemSource) o;
        if (problemSource.getAuthor().isEmpty() && problemSource.getName().isEmpty()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "general.error.empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "general.error.empty");
        }
    }
}