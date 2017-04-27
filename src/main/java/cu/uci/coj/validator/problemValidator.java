/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.model.Problem;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @since 2010-09-01
 * @see http://coj.uci.cu
 */
@Component
public class problemValidator implements Validator {

    @Resource
    private ProblemDAO problemDAO;

    public ProblemDAO getproblemDAO() {
        return problemDAO;
    }

    public void setproblemDAO(ProblemDAO problemDAO) {
        this.problemDAO = problemDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Problem.class.isAssignableFrom(clazz);
    }

    /**
     *
     * @param o
     * @param errors
     */
    @Override
    public void validate(Object o, Errors errors) {
        Problem problem = (Problem) o;
        String empty = "general.error.empty";
        String invalid = "errormsg.9";
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", empty);        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "time", empty);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "memory", empty);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fontsize", empty);
       // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "output", empty);
        if (problem.getTime() < 0) {
            errors.rejectValue("time",
                    invalid,
                    "At must 40 characters.");
        }

      /*  if (problem.isMultidata() && problem.getCasetimelimit() <= 0) {
            errors.rejectValue("casetimelimit",
                    invalid,
                    "At must 40 characters.");
        }*/

        if (problem.getMemory() < 0) {
            errors.rejectValue("memory",
                    invalid,
                    "At must 40 characters.");
        }

        if (problem.getFontsize() < 0) {
            errors.rejectValue("fontsize",
                    invalid,
                    "At must 40 characters.");
        }

        if ((problem.getTitle().length()) > 100) {
            errors.rejectValue("Title",
                    "lengthtitle.addproblem.name",
                    "At must 100 characters.");
        }        


        if (problem.getPid() == 0) {
            if (problemDAO.existProblemByTitle(problem.getTitle())) {
                errors.rejectValue("title",
                        "page.manageproblem.pidexist",
                        "Already Exist.");
            }
        } else {
            if (problemDAO.existProblemTitleOffPid(problem.getPid(), problem.getTitle())) {
                errors.rejectValue("title",
                        "page.manageproblem.pidexist",
                        "Already Exist.");
            }
        }        

        if (problem.getLanguageids() == null || problem.getLanguageids().length == 0) {
            errors.rejectValue("languages",
                    "languages.addproblem.required",
                    "At least one language should be select");
        }
    }
}
