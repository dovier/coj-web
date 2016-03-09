/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import cu.uci.coj.dao.InstitutionDAO;
import cu.uci.coj.model.Institution;

import javax.annotation.Resource;


import org.apache.commons.validator.UrlValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

/**
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @version Caribbean Online Judge(COJ) v2.0
 * @see //coj.uci.cu
 * @since 2010-09-01
 */
@Component
public class institutionValidator implements Validator {

    @Resource
    private InstitutionDAO institutionDAO;

    private Pattern pattern;
    private Matcher matcher;

    private static final String STRING_PATTERN = "[a-zA-Záéíóú ]+";
    private String WEBSITE_PATTERN = "^(http(?:s)?\\:\\/\\/[a-zA-Z0-9]+(?:(?:\\.|\\-)[a-zA-Z0-9]+)+(?:\\:\\d+)?(?:\\/[\\w\\-]+)*(?:\\/?|\\/\\w+\\.[a-zA-Z]{2,4}(?:\\?[\\w]+\\=[\\w\\-]+)?)?(?:\\&[\\w]+\\=[\\w\\-]+)*)$";
    private String ZIP_PATTERN = "[0-9A-Z/-]+";

    //private String URLREGEX = "^(http(?:s)?\\:\\/\\/[a-zA-Z0-9]+(?:(?:\\.|\\-)[a-zA-Z0-9]+)+(?:\\:\\d+)?(?:\\/[\\w\\-]+)*(?:\\/?|\\/\\w+\\.[a-zA-Z]{2,4}(?:\\?[\\w]+\\=[\\w\\-]+)?)?(?:\\&[\\w]+\\=[\\w\\-]+)*)$";

            /*"\\b(https?|ftp|file|ldap)://"
            + "[-A-Za-z0-9+&@#/%?=~_|!:,.;]"
            + "*[-A-Za-z0-9+&@#/%=~_|]";*/

    public InstitutionDAO getInstitutionDAO() {
        return institutionDAO;
    }

    public void setInstitutionDAO(InstitutionDAO institutionDAO) {
        this.institutionDAO = institutionDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Institution.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Institution institution = (Institution) o;
        UrlValidator urlValidator = new UrlValidator(); //ver como inyectar este objeto

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "website", "general.error.empty");


        // input string contains valid website address
        if (!(institution.getWebsite() != null && institution.getWebsite().isEmpty())) {
            /*if (!urlValidator.isValid(institution.getWebsite())){
                errors.rejectValue("website", "website.containNonAddress");
            }*/

            if (!institution.getWebsite().matches(WEBSITE_PATTERN)){
                errors.rejectValue("website", "website.containNonAddress");
            }

            /*pattern = Pattern.compile(WEBSITE_PATTERN);
            matcher = pattern.matcher(institution.getWebsite());
            if (!matcher.matches()) {
                errors.rejectValue("website", "website.containNonAddress");
            }*/
        }

        // input string conatains characters only
        if (!errors.hasFieldErrors("name")) {
            pattern = Pattern.compile(STRING_PATTERN);
            matcher = pattern.matcher(institution.getName());
            if (!matcher.matches()) {
                errors.rejectValue("name", "name.containNonChar");
            }
        }

        if (!errors.hasFieldErrors("name") && institutionDAO.existInstitutionNameByCountry(institution.getName(), institution.getCountry_id())) {
            errors.rejectValue("name",
                    "general.error.exist",
                    "At must 40 characters.");
        }
        if (!errors.hasFieldErrors("zip") && institutionDAO.existInstitutionZip(institution.getZip())) {
            errors.rejectValue("zip",
                    "general.error.exist",
                    "At must 40 characters.");
        }

        if (institution.getCountry_id() == 0) {
            errors.rejectValue("country_id",
                    "judge.register.error.country",
                    "At must 40 characters.");
        }

        if (!errors.hasFieldErrors("zip") && institution.getZip().length() > 8) {
            errors.rejectValue("zip",
                    "general.error.invalid",
                    "At must 40 characters.");
        } else if (!errors.hasFieldErrors("zip")) {

            pattern = Pattern.compile(ZIP_PATTERN);
            matcher = pattern.matcher(institution.getZip());
            if (!matcher.matches()) {
                errors.rejectValue("zip",
                        "general.error.upper",
                        "Upper case characters only");
            }       
        }
    }

    public void validateUpdate(Object o, Errors errors) {
        Institution institution = (Institution) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "website", "general.error.empty");

        // input string conatains characters only
        if (!errors.hasFieldErrors("name")) {
            pattern = Pattern.compile(STRING_PATTERN);
            matcher = pattern.matcher(institution.getName());
            if (!matcher.matches()) {
                errors.rejectValue("name", "name.containNonChar");
            }
        }

        // input string conatains valid website address
        if (!errors.hasFieldErrors("website")) {
            pattern = Pattern.compile(WEBSITE_PATTERN);
            matcher = pattern.matcher(institution.getWebsite());
            if (!matcher.matches()) {
                errors.rejectValue("website", "website.containNonAddress");
            }
        }


        if (!errors.hasFieldErrors("name") && institutionDAO.bool("exist.inst.name.but", institution.getName(), institution.getId())) {
            errors.rejectValue("name",
                    "general.error.exist",
                    "At must 40 characters.");
        }
        if (!errors.hasFieldErrors("zip") && institutionDAO.existInstitutionZipBut(institution.getZip(), institution.getId())) {
            errors.rejectValue("zip",
                    "general.error.exist",
                    "At must 40 characters.");
        }

        if (!errors.hasFieldErrors("zip") && institution.getZip().length() > 8) {
            errors.rejectValue("zip",
                    "general.error.invalid",
                    "At must 40 characters.");
        } else if (!errors.hasFieldErrors("zip")) {
            pattern = Pattern.compile(ZIP_PATTERN);
            matcher = pattern.matcher(institution.getZip());
            if (!matcher.matches()) {
                errors.rejectValue("zip",
                        "general.error.upper",
                        "Upper case characters only");
            }           
        }
    }
}
