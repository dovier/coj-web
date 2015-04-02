/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Team;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class teamValidator implements Validator {

    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean supports(Class<?> clazz) {
        return Team.class.isAssignableFrom(clazz);
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

    public void validate(Object o, Errors errors) {
        Team team = (Team) o;
        try {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "judge.register.error.username_empty");
            String userName = team.getUsername();
            if ((userName.length()) > 50) {
                errors.rejectValue("username",
                        "lengthOfUser.team.name",
                        "Name must not more than 50 characters.");
            }
            boolean userExist = false;
            try {

                for (int i = 0; i < team.getTotal() && !userExist; i++) {
                    userExist = userDAO.bool("exists.user.byusername",buildteamUsername(team.getUsername(), team.getTotal(), i + 1));
                }
            } catch (Exception e) {
            }
            if (userExist) {
                errors.rejectValue("username", "judge.register.error.username");
            }
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nick", "judge.register.error.nick");
            String nick = team.getNick();
            if ((userName.length()) > 25) {
                errors.rejectValue("nick",
                        "lengthOfUser.team.nick",
                        "Nick must not more than 25 characters.");
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
            if (!(team.getPassword()).equals(team.getConfirmPassword())) {
                errors.rejectValue("confirmPassword", "judge.register.error.confirmpassword");
            }

            if (team.getTotal() <= 0) {
                errors.rejectValue("total", "judge.createteams.error.total");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
