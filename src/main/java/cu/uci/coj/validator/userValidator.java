/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.IpAddress;
import cu.uci.coj.model.Team;
import cu.uci.coj.model.User;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.impl.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class userValidator implements Validator {

	@Resource
	private UserDAO userDAO;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;
		user.setDob(new Date(user.getYear() - 1900, user.getMonth() - 1, user.getDay()));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nick", "judge.register.error.nick");

		// si el correo ha sido cambiado y esta en uso por otra persona en el
		// COJ
		if (!user.isTeam()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "errormsg.51");
			if (!StringUtils.isEmpty(user.getEmail()) && userDAO.bool("email.changed", user.getEmail(), user.getUid()) && userDAO.emailExistUpdate(user.getEmail().trim(), user.getUsername()))
				errors.rejectValue("email", "judge.register.error.emailexist");
		}

		if (user.isTeam()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_1", "errormsg.51");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_2", "errormsg.51");
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
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "judge.register.error.name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "judge.register.error.lastname");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "judge.register.error.name");

			if (user.getGender() == 0) {
				errors.rejectValue("gender", "judge.register.error.gender");
			}
		}
	}

	public void validateManageAdmin(Object o, Errors errors) {
		User user = (User) o;

		if (!user.isTeam())
			ValidationUtils.rejectIfEmpty(errors, "role", "mandatory.field");

		user.setDob(new Date(user.getYear() - 1900, user.getMonth() - 1, user.getDay()));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nick", "errormsg.10");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "access_rule", "errormsg.10");
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

		if (user.getAccess_rule() != null && !IpAddress.validate(user.getAccess_rule())) {
			errors.rejectValue("access_rule", "errormsg.11");
		}

		if (!user.isTeam()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "judge.register.error.name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "judge.register.error.lastname");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "judge.register.error.email");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail_quote", "general.error.empty");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "judge.register.error.name");
			EmailValidator a = new EmailValidator();
			if (!a.isValid(user.getEmail(), null)) {
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

		}

	}

	public void validateTeam(Object o, Errors errors) {
		Team team = (Team) o;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "judge.register.error.username_empty");
		String userName = team.getUsername();
		if ((userName.length()) > 50) {
			errors.rejectValue("username", "lengthOfUser.team.name", "Name must not more than 50 characters.");
		}
		boolean userExist = false;
		try {
			for (int i = 0; i < team.getTotal() && !userExist; i++) {
				userExist = userDAO.bool("exists.user.byusername", buildteamUsername(team.getUsername(), team.getTotal(), i + 1));
			}
		} catch (Exception e) {
		}
		if (userExist) {
			errors.rejectValue("username", "judge.register.error.username");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nick", "judge.register.error.nick");
		String nick = team.getNick();
		if ((userName.length()) > 25) {
			errors.rejectValue("nick", "lengthOfUser.team.nick", "Nick must not more than 25 characters.");
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
		if (!(StringUtils.isEmpty(team.getPassword()) && StringUtils.isEmpty(team.getConfirmPassword()))) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "judge.register.error.password");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "judge.register.error.password");
			if (team.getPassword().length() < 8) {
				errors.rejectValue("password", "errormsg.15");
			} else if (team.getPassword().length() > 100) {
				errors.rejectValue("password", "errormsg.14");
			} else if (!team.getPassword().equals(team.getConfirmPassword())) {
				errors.rejectValue("confirmPassword", "errormsg.16");
			}
		}

		if (team.getTotal() <= 0) {
			errors.rejectValue("total", "judge.createteams.error.total");
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
