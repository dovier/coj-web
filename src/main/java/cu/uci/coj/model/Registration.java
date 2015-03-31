/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

import java.util.Date;
import java.util.LinkedList;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Registration {

    @NotEmpty
    private String name;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private String username;
    @NotEmpty
    @Size(min = 4, max = 20)
    private String password;
    @NotEmpty
    private String confirmPassword;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 4, max = 25)
    private String nick;
    @NotEmpty
    private String country;
    private int countryid;
    @NotEmpty
    private String institution;
    private int institutionid;
    @NotEmpty
    private boolean enabled;
    @NotEmpty
    private boolean contestNotifications;
    @NotEmpty
    private boolean submissionNotifications;
    @NotEmpty
    private boolean entriesNotifications;
    @NotEmpty
    private int planguage;
    private int locale;
    private boolean validateuser;
    private int gender;
    private boolean showemail;
    private boolean updateemail;
    private String captcha;
    private int mail_quote;
    private Date dob;
    private boolean showdob;
    private String access_rule;

    public Registration() {
    }

    public Registration(String name, String lastname, String username, String password, String confirmPassword, String email, String nick, String country, String institution, boolean enabled) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.nick = nick;
        this.country = country;
        this.institution = institution;
        this.enabled = enabled;
    }

    public boolean isValidateuser() {
        return validateuser;
    }

    public void setValidateuser(boolean validateuser) {
        this.validateuser = validateuser;
    }

    public Registration(String name, String lastname, String username, String password, String confirmPassword, String email, String nick, String country, String institution, boolean enabled, int locale) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.nick = nick;
        this.country = country;
        this.institution = institution;
        this.enabled = enabled;
        this.locale = locale;
    }

    public Registration(String name, String lastname, String username, String password, String confirmPassword, String email, String nick, String country, String institution, boolean enabled, int locale, int lid, int gender) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.nick = nick;
        this.country = country;
        this.institution = institution;
        this.enabled = enabled;
        this.locale = locale;
        this.planguage = lid;
        this.gender = gender;
    }

    public String getNick() {
        return nick;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    
    public boolean isContestNotifications() {
		return contestNotifications;
	}

	public void setContestNotifications(boolean contestNotifications) {
		this.contestNotifications = contestNotifications;
	}

	public boolean isSubmissionNotifications() {
		return submissionNotifications;
	}

	public void setSubmissionNotifications(boolean submissionNotifications) {
		this.submissionNotifications = submissionNotifications;
	}

	public boolean isEntriesNotifications() {
		return entriesNotifications;
	}

	public void setEntriesNotifications(boolean entriesNotifications) {
		this.entriesNotifications = entriesNotifications;
	}

	public void setNick(String nick) {
        this.nick = nick;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getLocale() {
        return locale;
    }

    public void setLocale(int locale) {
        this.locale = locale;
    }

    public int getPlanguage() {
        return planguage;
    }

    public void setPlanguage(int planguage) {
        this.planguage = planguage;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean isShowemail() {
        return showemail;
    }

    public void setShowemail(boolean showemail) {
        this.showemail = showemail;
    }

    public boolean isUpdateemail() {
        return updateemail;
    }

    public void setUpdateemail(boolean updateemail) {
        this.updateemail = updateemail;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public int getCountryid() {
        return countryid;
    }

    public void setCountryid(int countryid) {
        this.countryid = countryid;
    }

    public int getInstitutionid() {
        return institutionid;
    }

    public void setInstitutionid(int institutionid) {
        this.institutionid = institutionid;
    }

    public int getMail_quote() {
        return mail_quote;
    }

    public void setMail_quote(int mail_quote) {
        this.mail_quote = mail_quote;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean isShowdob() {
        return showdob;
    }

    public void setShowdob(boolean showdob) {
        this.showdob = showdob;
    }

    public String getAccess_rule() {
        return access_rule;
    }

    public void setAccess_rule(String access_rule) {
        this.access_rule = access_rule;
    }
}
