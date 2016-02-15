package cu.uci.coj.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alison on 2/02/16.
 */
@Component
public class filesValidator implements Validator{

    private String STRING_PATTERN_CL = "[a-zA-Z0-9\\s]+";
    private Pattern pattern;
    private Matcher matcher;


    @Override
    public boolean supports(Class<?> clazz) {
        return File.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        File file = (File) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "question", "general.error.empty");
    }
}
