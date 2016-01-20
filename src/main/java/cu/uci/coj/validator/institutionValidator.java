/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import cu.uci.coj.dao.InstitutionDAO;
import cu.uci.coj.model.Institution;

import javax.annotation.Resource;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @version Caribbean Online Judge(COJ) v2.0
 * @see http://coj.uci.cu
 * @since 2010-09-01
 */
@Component
public class institutionValidator implements Validator {

    @Resource
    private InstitutionDAO institutionDAO;

    private Pattern pattern;
    private Matcher matcher;

    private static final String STRING_PATTERN = "[a-zA-Z ]+";
    private String WEBSITE_PATTERN = "^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\?=.-]*)*\\/?$";
    private String ZIP_PATTERN = "[0-9A-Z/-]+";

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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "website", "general.error.empty");


        // input string conatains valid website address
        if (!(institution.getWebsite() != null && institution.getWebsite().isEmpty())) {
            pattern = Pattern.compile(WEBSITE_PATTERN);
            matcher = pattern.matcher(institution.getWebsite());
            if (!matcher.matches()) {
                errors.rejectValue("website", "website.containNonAddress");
            }
        }

        // input string conatains characters only
        if (!(institution.getName() != null && institution.getName().isEmpty())) {
            pattern = Pattern.compile(STRING_PATTERN);
            matcher = pattern.matcher(institution.getName());
            if (!matcher.matches()) {
                errors.rejectValue("name", "name.containNonChar");
            }
        }

        if (institution.getName() != null && institutionDAO.existInstitutionNameByCountry(institution.getName(), institution.getCountry_id())) {
            errors.rejectValue("name",
                    "general.error.exist",
                    "At must 40 characters.");
        }
        if (institution.getZip() != null && institutionDAO.existInstitutionZip(institution.getZip())) {
            errors.rejectValue("zip",
                    "general.error.exist",
                    "At must 40 characters.");
        }

        if (institution.getCountry_id() == 0) {
            errors.rejectValue("country_id",
                    "judge.register.error.country",
                    "At must 40 characters.");
        }

        if (institution.getZip() != null && institution.getZip().length() > 8) {
            errors.rejectValue("zip",
                    "general.error.invalid",
                    "At must 40 characters.");
        } else if (institution.getZip() != null) {
            pattern = Pattern.compile(ZIP_PATTERN);
            matcher = pattern.matcher(institution.getZip());
            if (!matcher.matches()) {
                errors.rejectValue("zip",
                        "general.error.upper",
                        "Upper case characters only");
            }
           /* for (int i = 0; i < institution.getZip().length(); i++) {
                char c = institution.getZip().charAt(i);
                if ((c < 'A' || c > 'Z') && c != '-') {
                    errors.rejectValue("zip",
                            "general.error.upper",
                            "Upper case characters only");
                    break;
                }*/
//            }
        }
    }

    public void validateUpdate(Object o, Errors errors) {
        Institution institution = (Institution) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "website", "general.error.empty");

        // input string conatains characters only
        if (!(institution.getName() != null && institution.getName().isEmpty())) {
            pattern = Pattern.compile(STRING_PATTERN);
            matcher = pattern.matcher(institution.getName());
            if (!matcher.matches()) {
                errors.rejectValue("name", "name.containNonChar");
            }
        }

        // input string conatains valid website address
        if (!(institution.getWebsite() != null && institution.getWebsite().isEmpty())) {
            pattern = Pattern.compile(WEBSITE_PATTERN);
            matcher = pattern.matcher(institution.getWebsite());
            if (!matcher.matches()) {
                errors.rejectValue("website", "website.containNonAddress");
            }
        }


        if (institution.getName() != null && institutionDAO.bool("exist.inst.name.but", institution.getName(), institution.getId())) {
            errors.rejectValue("name",
                    "general.error.exist",
                    "At must 40 characters.");
        }
        if (institution.getZip() != null && institutionDAO.existInstitutionZipBut(institution.getZip(), institution.getId())) {
            errors.rejectValue("zip",
                    "general.error.exist",
                    "At must 40 characters.");
        }

        if (institution.getZip() != null && institution.getZip().length() > 8) {
            errors.rejectValue("zip",
                    "general.error.invalid",
                    "At must 40 characters.");
        } else if (institution.getZip() != null) {
            pattern = Pattern.compile(ZIP_PATTERN);
            matcher = pattern.matcher(institution.getZip());
            if (!matcher.matches()) {
                errors.rejectValue("zip",
                        "general.error.upper",
                        "Upper case characters only");
            }
           /* for (int i = 0; i < institution.getZip().length(); i++) {
                char c = institution.getZip().charAt(i);
                if ((c < 'A' || c > 'Z') && c != '-') {
                    errors.rejectValue("zip",
                            "general.error.upper",
                            "Upper case characters only");
                    break;
                }
            }*/
        }
    }
}
