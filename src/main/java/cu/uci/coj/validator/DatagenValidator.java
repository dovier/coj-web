/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.model.DatagenDataset;
import cu.uci.coj.utils.FileUtils;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class DatagenValidator implements Validator {

    @Resource
    private UtilDAO utilDAO;
    @Resource
    private ProblemDAO problemDAO;

    @Override
    public boolean supports(Class<?> clazz) {
        return DatagenDataset.class.isAssignableFrom(clazz);
    }

    /**
     *
     * @param o
     * @param errors
     */
    @Override
    public void validate(Object o, Errors errors) {

        DatagenDataset datagen = (DatagenDataset) o;
        if ("inputgen".equals(datagen.getMode())) {
            validateInputGen(datagen, errors);
        } else if ("modelsol".equals(datagen.getMode())) {
            validateModelSol(datagen, errors);
        } else {
            validateCustomSol(datagen, errors);
        }
        //   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content",                 "");
    }

    private void validateCustomSol(DatagenDataset datagen, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "errormsg.51");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "key", "errormsg.51");
        boolean langError = utilDAO.bool("language.exists", datagen.getKey());
        if (!langError) {
            errors.rejectValue("language", "errormsg.51");
        }
    }

    private void validateModelSol(DatagenDataset datagen, Errors errors) {
        //si la solucion no esta en los archivos ni se puede generar una de la base de datos
        if (!FileUtils.modelSolutionExists(datagen)) {
            Map<String, Object> map = problemDAO.map("problem.accepted.solutions", datagen.getProblemId());
            boolean exists = (map != null && !map.isEmpty());
            if (!exists) {
                errors.reject("mode", "errormsg.37");
            }
        }
        ValidationUtils.rejectIfEmpty(errors, "input", "errormsg.51");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "problemId", "errormsg.51");
    }

    private void validateInputGen(DatagenDataset datagen, Errors errors) {
        if (!FileUtils.inputGenExists(datagen)) {
            errors.reject("mode", "errormsg.38");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "problemId", "errormsg.51");
    }
}
