package cu.uci.coj.teamanalyzer.validators;

import cu.uci.coj.teamanalyzer.models.analysis;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class analysisValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return analysis.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        analysis analysis = (analysis) o;
        if (analysis.getName().length() == 0) {
            errors.rejectValue("name", "errormsg.51");
        } else if (analysis.getName().length() < 3) {
            errors.rejectValue("name", "errormsg.63");
        } else if (analysis.getName().length() > 15) {
            errors.rejectValue("name", "errormsg.64");
        } else if (!analysis.getName().matches("[a-zA-Z0-9]+")) {
            errors.rejectValue("name", "errormsg.65");
        }

        if(analysis.getUsersids().length == 0){
            errors.rejectValue("usersids", "errormsg.51");
        } else if(analysis.getUsersids().length < 3){
            errors.rejectValue("usersids", "errormsg.66");
        }

        if(analysis.getContestsids().length == 0){
            errors.rejectValue("contestsids", "errormsg.51");
        }
    }
}
