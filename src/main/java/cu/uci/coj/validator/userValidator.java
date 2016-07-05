/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.IpAddress;
import cu.uci.coj.model.Team;
import cu.uci.coj.model.User;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class userValidator implements Validator {

    @Resource
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        user.setDob(new Date(user.getYear() - 1900, user.getMonth() - 1, user.getDay()));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nick", "judge.register.error.nick");
        if (!errors.hasFieldErrors("nick")) {
            if ((user.getNick().length()) > 15) {
                errors.rejectValue("nick", "judge.register.error.long25charact");
            } else if (user.getNick().length() < 3) {
                errors.rejectValue("nick", "judge.register.error.less3charact");
            }


        }
        if (!user.isTeam()) {
            if (user.getName().length() < 1) {
                errors.rejectValue("name", "errormsg.7");
            } else if (user.getName().length() > 30) {
                errors.rejectValue("name", "errormsg.6");
            } else if (!user.getName().matches("[a-zA-Z\\.\\-\\'\\s]+")) {
                errors.rejectValue("name", "errormsg.8");
            }

            if (user.getLastname().length() < 1) {
                errors.rejectValue("lastname", "errormsg.10");
            } else if (user.getLastname().length() > 50) {
                errors.rejectValue("lastname", "errormsg.9");
            } else if (!user.getLastname().matches("[a-zA-Z\\.\\-\\'\\s]+")) {
                errors.rejectValue("lastname", "errormsg.11");
            }
            // si el correo ha sido cambiado y esta en uso por otra persona en el
            // COJ

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "errormsg.51");
            if (!StringUtils.isEmpty(user.getEmail()) && userDAO.bool("email.changed", user.getEmail(), user.getUid()) && userDAO.emailExistUpdate(user.getEmail().trim(), user.getUsername())) {
                errors.rejectValue("email", "judge.register.error.emailexist");
            }
            if (!errors.hasFieldErrors("email")) {
                EmailValidator emailValidator = EmailValidator.getInstance(); //ver como inyectar este objeto
                if (!emailValidator.isValid(user.getEmail())) {
                    errors.rejectValue("email", "judge.register.error.bademail");
                }
            }
        }

        if (user.isTeam()) {
            // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_1", "errormsg.51");
            // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_2", "errormsg.51");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_3", "errormsg.51");
        }
        if (user.getCountry_id() == 0) {
            errors.rejectValue("country", "judge.register.error.country");
        }
        if (user.getInstitution_id() == 0) {
            errors.rejectValue("institution", "judge.register.error.institution");
        }

        if (user.getLid() == 0) {
            errors.rejectValue("planguage", "judge.register.error.planguage");
        }

        if (user.getLocale() == 0) {
            errors.rejectValue("locale", "judge.register.error.locale");
        }

        if (!(StringUtils.isEmpty(user.getPassword()) && StringUtils.isEmpty(user.getConfirmPassword()))) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "judge.register.error.password");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "judge.register.error.password");
            if (user.getPassword().length() < 8) {
                errors.rejectValue("password", "errormsg.15");
            } else if (user.getPassword().length() > 100) {
                errors.rejectValue("password", "errormsg.14");
            } else if (!user.getPassword().equals(user.getConfirmPassword())) {
                errors.rejectValue("confirmPassword", "errormsg.16");
            }
        }
        if (!user.isTeam()) {
            //  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "judge.register.error.name");
            //  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "judge.register.error.lastname");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "judge.register.error.name");

            if (user.getGender() == 0) {
                errors.rejectValue("gender", "judge.register.error.gender");
            }
        }
    }

    public void validateManageAdmin(Object o, Errors errors) {
        User user = (User) o;

        if (!user.isTeam()) {
            ValidationUtils.rejectIfEmpty(errors, "authorities", "mandatory.field");
        }

        user.setDob(new Date(user.getYear() - 1900, user.getMonth() - 1, user.getDay()));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nick", "mandatory.field");

        if (!errors.hasFieldErrors("nick")) {
            if ((user.getNick().length()) > 15) {
                errors.rejectValue("nick", "judge.register.error.long25charact");
            } else if (user.getNick().length() < 3) {
                errors.rejectValue("nick", "judge.register.error.less3charact");
            }


        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "access_rule", "mandatory.field");
        if (user.getCountry_id() == 0) {
            errors.rejectValue("country", "judge.register.error.country");
        }
        if (user.getInstitution_id() == 0) {
            errors.rejectValue("institution", "judge.register.error.institution");
        }

        if (user.getLid() == 0) {
            errors.rejectValue("planguage", "judge.register.error.planguage");
        }

        if (user.getLocale() == 0) {
            errors.rejectValue("locale", "judge.register.error.locale");
        }

        if (!(StringUtils.isEmpty(user.getPassword()) && StringUtils.isEmpty(user.getConfirmPassword()))) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "judge.register.error.password");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "judge.register.error.password");
            if (user.getPassword().length() < 8) {
                errors.rejectValue("password", "errormsg.15");
            } else if (user.getPassword().length() > 100) {
                errors.rejectValue("password", "errormsg.14");
            } else if (!user.getPassword().equals(user.getConfirmPassword())) {
                errors.rejectValue("confirmPassword", "errormsg.16");
            }
        }

        if (!errors.hasFieldErrors("access_rule")) {
            if (user.getAccess_rule() != null && !IpAddress.validate(user.getAccess_rule())) {
                errors.rejectValue("access_rule", "errormsg.11.1");
            }
        }
        if (!user.isTeam()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "judge.register.error.name");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "judge.register.error.lastname");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "judge.register.error.email");
            //  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail_quote", "general.error.empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "judge.register.error.name");
            EmailValidator a = EmailValidator.getInstance();
            if (!a.isValid(user.getEmail())) {
                errors.rejectValue("email", "judge.register.error.bademail");
            }

            if (userDAO.existUserByMailOffUid(user.getEmail(), user.getUid())) {
                errors.rejectValue("email", "judge.register.error.emailexist");
            }

            if (user.getMail_quote() > 1000000) {
                errors.rejectValue("mail_quote", "general.error.mail.limit");
            }

            if (user.getGender() == 0) {
                errors.rejectValue("gender", "judge.register.error.gender");
            }

            Calendar calendar = Calendar.getInstance();
            calendar.set(user.getYear(), user.getMonth() - 1, user.getDay());
            Date date = calendar.getTime();

            if (date.after(new Date())) {
                errors.rejectValue("dob", "errormsg.61");
            }

        }

    }

    public void validateTeam(Object o, Errors errors) {
        Team team = (Team) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "judge.register.error.username_empty");

        if (!errors.hasFieldErrors("username")) {
            String userName = team.getUsername();
            if ((userName.length()) > 50) {
                errors.rejectValue("username", "judge.register.error.long50charact");
            } else if (!userName.matches("[0-9a-zA-Z_-]+")) {
                errors.rejectValue("username", "general.error.onlylettersnumbers");
            } else {
                boolean userExist = false;
                try {
                    for (int i = 0; i < team.getTotal() + 1 && !userExist; i++) {
                        userExist = userDAO.bool("exists.user.byusername", buildteamUsername(team.getUsername(), team.getTotal(), i + 1));
                    }
                } catch (Exception e) {
                }
                if (userExist) {
                    errors.rejectValue("username", "judge.register.error.username");
                }
            }
        }


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nick", "judge.register.error.nick");

        if (!errors.hasFieldErrors("nick")) {
            if (!team.getNick().isEmpty()) {
                if (!team.getNick().matches("[0-9a-zA-Z _-]+")) {
                    errors.rejectValue("nick", "general.error.onlylettersnumbers");
                }
            } else if ((team.getNick().length()) > 15) {
                errors.rejectValue("nick", "judge.register.error.long25charact");
            } else if (team.getNick().length() < 3) {
                errors.rejectValue("nick", "judge.register.error.less3charact");
            }
        }


        int country = team.getCountry();
        if (country == 0) {
            errors.rejectValue("country", "judge.register.error.country");
        }
        int institution = team.getInstitution();
        if (institution == 0) {
            errors.rejectValue("institution", "judge.register.error.institution");
        }

        if (team.getLocale() == 0) {
            errors.rejectValue("locale", "judge.register.error.locale");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "judge.register.error.password");

        if (!errors.hasFieldErrors("password")) {
            if (team.getPassword().length() < 8) {
                errors.rejectValue("password", "errormsg.15");
            } else if (team.getPassword().length() > 100) {
                errors.rejectValue("password", "errormsg.14");
            } else if (!team.getPassword().equals(team.getConfirmPassword())) {
                errors.rejectValue("confirmPassword", "errormsg.16");
            }
        }


        if (!errors.hasFieldErrors("total")) {
            int total = team.getTotal();
            if (total <= 0) {
                errors.rejectValue("total", "judge.createteams.error.total");
            } else if (total > 999) {
                errors.rejectValue("total", "judge.createteams.error.total.max");
            }
        }
    }

    private String buildteamUsername(String username, int total, int real) {
        if (total > 1) {
            int add = (total + "").length() - (real + "").length();
            for (int i = 0; i < add; i++) {
                username += "0";
            }
            username += real;
        }
        return username;
    }
}
