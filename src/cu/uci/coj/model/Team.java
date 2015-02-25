package cu.uci.coj.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class Team {

    private int uid;
    @NotEmpty
    private String username;
    @NotEmpty
    @Size(min = 4, max = 20)
    private String password;
    @NotEmpty
    private String confirmPassword;
    @NotEmpty
    @Size(min = 4, max = 25)
    private String nick;
    @NotEmpty
    private int country;
    @NotEmpty
    private int institution;
    @NotEmpty
    private boolean enabled;
    private int locale;
    private int total;
    private int contest;
    private String group;
    private MultipartFile uploadfile;
    private boolean update_nick;

    public boolean isUpdate_nick() {
        return update_nick;
    }

    public void setUpdate_nick(boolean update_nick) {
        this.update_nick = update_nick;
    }

    public MultipartFile getUploadfile() {
        return uploadfile;
    }

    public void setUploadfile(MultipartFile uploadfile) {
        this.uploadfile = uploadfile;
    }

    public Team() {
    }

    public Team(String username, String password, String confirmPassword, String nick, int country, int institution, boolean enabled, int locale) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.nick = nick;
        this.country = country;
        this.institution = institution;
        this.enabled = enabled;
        this.locale = locale;
    }

    public Team(String username, String password, String nick, int country, int institution, int locale) {
        this.username = username;
        this.password = password;
        this.nick = nick;
        this.country = country;
        this.institution = institution;
        this.locale = locale;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getInstitution() {
        return institution;
    }

    public void setInstitution(int institution) {
        this.institution = institution;
    }

    public int getLocale() {
        return locale;
    }

    public void setLocale(int locale) {
        this.locale = locale;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getContest() {
        return contest;
    }

    public void setContest(int contest) {
        this.contest = contest;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
