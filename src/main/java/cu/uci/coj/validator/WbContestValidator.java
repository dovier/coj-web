package cu.uci.coj.validator;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cu.uci.coj.board.dao.WbContestDAO;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;

@Component
public class WbContestValidator implements Validator {

    @Resource
    WbContestDAO wbContestDAO;

    private String URLREGEX = "^(http(?:s)?\\:\\/\\/[a-zA-Z0-9]+(?:(?:\\.|\\-)[a-zA-Z0-9]+)+(?:\\:\\d+)?(?:\\/[\\w\\-]+)*(?:\\/?|\\/\\w+\\.[a-zA-Z]{2,5}(?:\\?[\\w]+\\=[\\w\\-]+)?)?(?:\\&[\\w]+\\=[\\w\\-]+)*)$";

    @Override
    public boolean supports(Class<?> clazz) {
        return WbContest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "wbsite.error.empty", "wboard.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "wbsite.error.empty", "Field empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", "wbsite.error.empty", "Field empty");

        if (((WbContest) o).getStartDate() != null && ((WbContest) o).getEndDate() != null) {
            if (((WbContest) o).getStartDate().getTime() >= (((WbContest) o).getEndDate().getTime())) {
                errors.rejectValue("startDate", "wboard.error.startdate.after.endate", "Start date before the end date.");
            }
        }
        if (wbContestDAO.getContestById(((WbContest) o).getId()) != null) {
            errors.rejectValue("name", "wboard.error.contest.exist", "This contest already exists.");
        }

        if (((WbContest) o).getSid() == 0) {
            errors.rejectValue("sid", "wboard.error.nosite", "Select a site.");
        }

        if (!((WbContest) o).getUrl().matches(URLREGEX)) {
            errors.rejectValue("url", "wboard.error.url.invalid");
        }
    }

    public void validateUpdate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "wboard.error.empty", "Field empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "wboard.error.empty", "Field empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", "wboard.error.empty", "Field empty");


        if (((WbContest) o).getSid() == 0) {
            errors.rejectValue("sid", "wboard.error.nosite", "Select a site.");
        }


        if (!((WbContest) o).getUrl().matches(URLREGEX)) {
            errors.rejectValue("url", "wboard.error.url.invalid");
        }
    }
}
