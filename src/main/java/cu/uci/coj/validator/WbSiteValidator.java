package cu.uci.coj.validator;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Resource;

import org.apache.commons.validator.EmailValidator;
import org.apache.commons.validator.UrlValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;

@Component
public class WbSiteValidator implements Validator {

    private String URLREGEX = "^(http(?:s)?\\:\\/\\/[a-zA-Z0-9]+(?:(?:\\.|\\-)[a-zA-Z0-9]+)+(?:\\:\\d+)?(?:\\/[\\w\\-]+)*(?:\\/?|\\/\\w+\\.[a-zA-Z]{2,4}(?:\\?[\\w]+\\=[\\w\\-]+)?)?(?:\\&[\\w]+\\=[\\w\\-]+)*)$";
//    private String URLREGEX = "^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$";
    //   private String URLREGEX = "(http(s)?://)?([\\w-]+\\.)+[\\w-]+(/[\\w- ;,./?%&=]*)?";


    String CODEREGEX = "[0-9A-z/-]+";

    @Resource
    WbSiteDAO wbSiteDAO;

    @Override
    public boolean supports(Class<?> clazz) {
        return WbSite.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "site", "wbsite.error.empty");
        if (!errors.hasFieldErrors("timeanddateid")) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "timeanddateid", "wbsite.error.empty");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "wbsite.error.empty");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "url", "wbsite.error.empty");


        if (((WbSite) o).getTimeanddateid() < 1 && !errors.hasFieldErrors("timeanddateid")) {
            errors.rejectValue("timeanddateid", "wboard.error.timeanddateid.invalid", "Select a valid value greater than 0.");
        }

        if (wbSiteDAO.getSiteId(((WbSite) o).getSite()) != null) {
            errors.rejectValue("site", "wboard.error.site.exists");
        }

        if (((WbSite) o).getTimezone().equals("0")) {
            errors.rejectValue("timezone", "wboard.error.notimezone");
        }

        if (!((WbSite) o).getUrl().matches(URLREGEX)) {
            errors.rejectValue("url", "website.containNonAddress");
        }

        if (!errors.hasFieldErrors("code")) {
            if (!((WbSite) o).getCode().matches(CODEREGEX)) {
                errors.rejectValue("code", "website.containInvalidCode");
            }
        }

		/*try {
            new URL(((WbSite) o).getUrl());
		} catch (MalformedURLException e) {
			errors.rejectValue("url", "wboard.error.url.invalid");
		}*/
    }

    public void validateUpdate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "site", "wboard.error.empty", "Field is empty.");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "timeanddateid", "wboard.error.empty", "Field is empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "wboard.error.empty", "Field is empty.");


        if (((WbSite) o).getTimeanddateid() < 1 && !errors.hasFieldErrors("timeanddateid")) {
            errors.rejectValue("timeanddateid", "wboard.error.timeanddateid.invalid", "Select a valid value greater than 0.");
        }

        if (((WbSite) o).getTimezone().equals("0")) {
            errors.rejectValue("timezone", "wboard.error.notimezone", "Select a timezone.");
        }

        if (!((WbSite) o).getUrl().matches(URLREGEX)) {
            errors.rejectValue("url", "website.containNonAddress");
        }

        if (!((WbSite) o).getCode().matches(CODEREGEX)) {
            errors.rejectValue("code", "website.containInvalidCode");
        }

//        try {
//            new URL(((WbSite) o).getUrl());
//        } catch (MalformedURLException e) {
//            errors.rejectValue("url", "wboard.error.url.invalid", "Invalid url.");
//        }
    }

}
