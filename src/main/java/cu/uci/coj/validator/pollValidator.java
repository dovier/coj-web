/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import cu.uci.coj.dao.PollDAO;
import cu.uci.coj.model.Poll;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 * @author Alison Muñoz Capote
 * @since 2010-09-01
 * @see http://coj.uci.cu
 */
@Component
public class pollValidator implements Validator {

    @Resource
    private PollDAO pollDAO;

    public PollDAO getPollDAO() {
        return pollDAO;
    }

    public void setPollDAO(PollDAO pollDAO) {
        this.pollDAO = pollDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Poll.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Poll poll = (Poll) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "question", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "answer1", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "answer2", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "answer3", "general.error.empty");         
    }
}
