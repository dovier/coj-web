package cu.uci.coj.validator;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.model.ProblemSource;

/**
 * Created by alison on 14/01/16.
 */

@Component
public class ProblemSourceValidator implements Validator {


    @Resource
    private ProblemDAO problemDAO;

    public ProblemDAO getproblemDAO() {
        return problemDAO;
    }

    public void setPollDAO(ProblemDAO problemDAO) {
        this.problemDAO = problemDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ProblemSource.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProblemSource problemSource = (ProblemSource) o;

        String author = ((ProblemSource) o).getAuthor().replace(" ", "");
        String name = ((ProblemSource) o).getName().replace(" ", "");
        if (author.isEmpty() && name.isEmpty()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "general.error.oneempty");

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "general.error.oneempty");
        }

    }

    public void validateUpdate(Object o, Errors errors) {
        ProblemSource problemSource = (ProblemSource) o;

        String author = ((ProblemSource) o).getAuthor().replaceAll(" ", "");
        String name = ((ProblemSource) o).getName().replaceAll(" ", "");


        if (author.isEmpty() && name.isEmpty()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "general.error.oneempty");

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "general.error.oneempty");
        }

    }
}