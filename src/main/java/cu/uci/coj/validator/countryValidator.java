/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import cu.uci.coj.dao.CountryDAO;
import cu.uci.coj.model.Country;
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
public class countryValidator implements Validator {

    @Resource
    private CountryDAO countryDAO;

    public CountryDAO getCountryDAO() {
        return countryDAO;
    }

    public void setCountryDAO(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Country.class.isAssignableFrom(clazz);
    }

    /**
     *
     * @param o
     * @param errors
     */
    @Override
    public void validate(Object o, Errors errors) {
        Country country = (Country) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip_two", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "website", "general.error.empty");
        if (country.getName() != null && countryDAO.bool("exist.country.name", country.getName())) {
            errors.rejectValue("name",
                    "general.error.exist",
                    "At must 40 characters.");
        }
        if (country.getZip() != null && countryDAO.bool("exist.country.zip", country.getZip())) {
            errors.rejectValue("zip",
                    "general.error.exist",
                    "At must 40 characters.");
        }

        if (country.getZip() != null && country.getZip().length() > 8) {
            errors.rejectValue("zip",
                    "general.error.invalid",
                    "At must 40 characters.");
        } else if (country.getZip() != null) {
            for (int i = 0; i < country.getZip().length(); i++) {
                char c = country.getZip().charAt(i);
                if (c < 'A' || c > 'Z') {
                    errors.rejectValue("zip",
                            "general.error.upper",
                            "Upper case characters only");
                    break;
                }
            }
        }
    }

    public void validateUpdate(Object o, Errors errors) {
        Country country = (Country) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "general.error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "general.error.empty");
        if (!errors.hasFieldErrors("zip") && !errors.hasFieldErrors("name")) {
            Country cr2 = countryDAO.object("country.by.id.enabled", Country.class, country.getId());
            if (!cr2.getZip().equals(country.getZip()) && countryDAO.bool("exist.country.zip", country.getZip())) {
                errors.rejectValue("zip",
                        "general.error.exist",
                        "Already Exists");
            }

            if (!cr2.getName().equals(country.getName()) && countryDAO.bool("exist.country.name", country.getName())) {
                errors.rejectValue("name",
                        "general.error.exist",
                        "Already Exists");
            }
        }

        if (!errors.hasFieldErrors("zip") && country.getZip().length() > 8) {
            errors.rejectValue("zip",
                    "general.error.invalid",
                    "At must 40 characters.");
        } else if (!errors.hasFieldErrors("zip")) {
            for (int i = 0; i < country.getZip().length(); i++) {
                char c = country.getZip().charAt(i);
                if (c < 'A' || c > 'Z') {
                    errors.rejectValue("zip",
                            "general.error.upper",
                            "Upper case characters only");
                    break;
                }
            }
        }
    }
}
