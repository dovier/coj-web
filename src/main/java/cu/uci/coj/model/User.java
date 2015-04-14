/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

import cu.uci.coj.controller.interfaces.CommonScoreboardInterface;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class User implements CommonScoreboardInterface {

	private String banReason;
	private static final String ACTIVE = "active";
	private static final String DORMANT = "dormant";
	private static final String DISABLED = "disabled";
	private String status;
	
    private int uid;
    private int y;//para estadisticas
    public final static String GOLD = "gold";
    public final static String SILVER = "silver";
    public final static String BRONZE = "bronze";
    private String role;
    private String medal;
	private String username;
    private String nick;
    private String email;
    private String cemail;
    private String name;
    private String lastname;
    private String language;
    private String institution;
    private int institution_id;
    private String country;
    private int country_id;
    private String country_desc;
    private String institution_desc;
    private double points;
    private double percent;
    private int rank;
    private int acc;
    private int accu;
    private int wa;
    private int rte;
    private int tle;
    private int mle;
    private int ce;
    private int fle;
    private int pe;
    private int ole;
    private int uq;
    private int ivf;
    private int total;
    private String even;
    private boolean enabled;
    private int locale;
    private boolean team;
    private int solved;
    private int unsolved;
    private String last_accepted;
    private String last_submission;
    private int ranking;
    private int tot_ranking;
    private int rankingbycountry;
    private int tot_rankingbycountry;
    private int rankingbyinstitution;
    private int tot_rankingbyinstitution;
    private int lid;
    private int gender;
    private boolean contestNotifications;
    private boolean submissionNotifications;
    private boolean entriesNotifications;
    private boolean problemNotifications;
    private String planguage;
    private String glanguage;
    private boolean enableadveditor;
    private boolean agreementcojtos;
    private double score;
    private boolean online;
    private boolean showemail;
    private boolean virtual;
    private String lastip;
    private int mail_quote;
    private String group;
    private Date dob;
    private boolean showdob;
    private String access_rule;
    private int act_id;
    private int level;
    //ACM PARAMS
    private int penalty;
    private String lastacc;
    private String rgdate;
    private LinkedList<Problem> problems;
    private String password;
    private String confirmPassword;
    private int year;
    private int month;
    private int day;
    private String captcha;
    private HttpServletRequest request;
    private List<String> authorities;
    private boolean see_solutions;
    private Date update_time;
    private int contests;
    private Date dob1;
    private boolean update_nick;
    private String coach;
    private String user_1;
    private String user_2;
    private String user_3;
    private String tooltip;
    private boolean view_problem_info;
    private int[] preferedClassificationsIds;
    private Date join_date;
    private boolean banned;
    private List<Integer> sitesFollowing;   
    private boolean wboardNotifications;
    private boolean newprivatemessageNotifications;
    private boolean emailNotifications;
    
	public boolean isEmailNotifications() {
		return emailNotifications;
	}

	public void setEmailNotifications(boolean emailNotifications) {
		this.emailNotifications = emailNotifications;
	}

	public boolean isNewprivatemessageNotifications() {
		return newprivatemessageNotifications;
	}

	public void setNewprivatemessageNotifications(boolean newprivatemessageNotifications) {
		this.newprivatemessageNotifications = newprivatemessageNotifications;
	}

	public boolean isWboardNotifications() {
		return wboardNotifications;
	}

	public void setWboardNotifications(boolean wboardnotifications) {
		this.wboardNotifications = wboardnotifications;
	}

	public List<Integer> getSitesFollowing() {
		return sitesFollowing;
	}

	public void setSitesFollowing(List<Integer> sitesFollowing) {
		this.sitesFollowing = sitesFollowing;
	}

	public String getStatus() {
		return status;
	}
	
	

	public String getBanReason() {
		return banReason;
	}

	public void setBanReason(String banReason) {
		this.banReason = banReason;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public Date getJoin_date() {
        return join_date;
    }

    public void setJoin_date(Date join_date) {
        this.join_date = join_date;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public boolean isView_problem_info() {
        return view_problem_info;
    }

    public void setView_problem_info(boolean view_problem_info) {
        this.view_problem_info = view_problem_info;
    }

    public String getUser_1() {
        return user_1;
    }

    public void setUser_1(String user_1) {
        this.user_1 = user_1;
    }

    public String getUser_2() {
        return user_2;
    }

    public void setUser_2(String user_2) {
        this.user_2 = user_2;
    }

    public String getUser_3() {
        return user_3;
    }

    public void setUser_3(String user_3) {
        this.user_3 = user_3;
    }

    public boolean isUpdate_nick() {
        return update_nick;
    }

    public void setUpdate_nick(boolean update_nick) {
        this.update_nick = update_nick;
    }

    public Date getDob1() {
        return dob1;
    }

    public void setDob1(Date dob1) {
        this.dob1 = dob1;
    }

    public int getContests() {
        return contests;
    }

    public void setContests(int contests) {
        this.contests = contests;
    }

    public boolean isSee_solutions() {
        return see_solutions;
    }

    public void setSee_solutions(boolean see_solutions) {
        this.see_solutions = see_solutions;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public User() {
    }

    public User(String username, String nick, String email, String name, String lastname, String language, String institution, String country, String country_desc, String institution_desc, double points, double percent, int rank, int acc, int accu, int wa, int rte, int tle, int mle, int ce, int fle, int pe, int total, String even) {
        this.username = username;
        this.nick = nick;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.language = language;
        this.institution = institution;
        this.country = country;
        this.country_desc = country_desc;
        this.institution_desc = institution_desc;
        this.points = points;
        this.percent = percent;
        this.rank = rank;
        this.acc = acc;
        this.accu = accu;
        this.wa = wa;
        this.rte = rte;
        this.tle = tle;
        this.mle = mle;
        this.ce = ce;
        this.fle = fle;
        this.pe = pe;
        this.total = total;
        this.even = even;
    }

    public User(String username, String nick, String email, String name, String lastname, String language, String institution, String country, String country_desc, String institution_desc, double points, double percent, int rank, int acc, int accu, int wa, int rte, int tle, int mle, int ce, int fle, int pe, int ole, int uq, int ivf, int total) {
        this.username = username;
        this.nick = nick;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.language = language;
        this.institution = institution;
        this.country = country;
        this.country_desc = country_desc;
        this.institution_desc = institution_desc;
        this.points = points;
        this.percent = percent;
        this.rank = rank;
        this.acc = acc;
        this.accu = accu;
        this.wa = wa;
        this.rte = rte;
        this.tle = tle;
        this.mle = mle;
        this.ce = ce;
        this.fle = fle;
        this.pe = pe;
        this.ole = ole;
        this.uq = uq;
        this.ivf = ivf;
        this.total = total;
        this.total = acc + rte + tle + mle + ce + fle + pe + ole + uq + ivf;
    }

    public User(String username, String nick, String email, String name, String lastname, String language, String institution, String country, String country_desc, String institution_desc, double points, double percent, int rank, int acc, int accu, int wa, int rte, int tle, int mle, int ce, int fle, int pe, int ole, int uq, int total, String last_accepted, String last_submission) {
        this.username = username;
        this.nick = nick;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.language = language;
        this.institution = institution;
        this.country = country;
        this.country_desc = country_desc;
        this.institution_desc = institution_desc;
        this.points = points;
        this.percent = percent;
        this.rank = rank;
        this.acc = acc;
        this.accu = accu;
        this.wa = wa;
        this.rte = rte;
        this.tle = tle;
        this.mle = mle;
        this.ce = ce;
        this.fle = fle;
        this.pe = pe;
        this.ole = ole;
        this.uq = uq;
        this.total = total;
        this.last_accepted = last_accepted.substring(0, 19);
        this.last_submission = last_submission.substring(0, 19);
        this.total = acc + rte + tle + mle + ce + fle + pe + ole + uq;
    }

    public User(String username, String nick, String email, String name, String lastname, String language, String institution, String country, String country_desc, String institution_desc, double points, double percent, int rank, int acc, int accu, int wa, int rte, int tle, int mle, int ce, int fle, int pe, int ole, int uq, int total, String last_accepted, String last_submission, int country_id, int institution_id) {
        this.username = username;
        this.nick = nick;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.language = language;
        this.institution = institution;
        this.country = country;
        this.country_desc = country_desc;
        this.institution_desc = institution_desc;
        this.points = points;
        this.percent = percent;
        this.rank = rank;
        this.acc = acc;
        this.accu = accu;
        this.wa = wa;
        this.rte = rte;
        this.tle = tle;
        this.mle = mle;
        this.ce = ce;
        this.fle = fle;
        this.pe = pe;
        this.ole = ole;
        this.uq = uq;
        this.total = total;
        this.last_accepted = last_accepted.substring(0, 19);
        this.last_submission = last_submission.substring(0, 19);
        this.country_id = country_id;
        this.institution_id = institution_id;
        this.total = acc + rte + tle + mle + ce + fle + pe + ole + uq;
    }

    public User(String username, String nick, String email, String name, String lastname, String language, String institution, String country, String country_desc, String institution_desc, double points, double percent, int rank, int acc, int accu, int wa, int rte, int tle, int mle, int ce, int fle, int pe, int ole, int uq, int total, String last_accepted, String last_submission, int country_id, int institution_id, int gender, String planguage, String glanguage, double score) {
        this.username = username;
        this.nick = nick;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.language = language;
        this.institution = institution;
        this.country = country;
        this.country_desc = country_desc;
        this.institution_desc = institution_desc;
        this.points = points;
        this.percent = percent;
        this.rank = rank;
        this.acc = acc;
        this.accu = accu;
        this.wa = wa;
        this.rte = rte;
        this.tle = tle;
        this.mle = mle;
        this.ce = ce;
        this.fle = fle;
        this.pe = pe;
        this.ole = ole;
        this.uq = uq;
        this.total = total;
        this.last_accepted = last_accepted.substring(0, 19);
        this.last_submission = last_submission.substring(0, 19);
        this.country_id = country_id;
        this.institution_id = institution_id;
        this.total = acc + rte + tle + mle + ce + fle + pe + ole + uq;
        this.gender = gender;
        this.planguage = planguage;
        this.glanguage = glanguage;
        this.score = score;
    }

    public User(String username, String nick, String email, String name, String lastname, String language, String institution, String country, String country_desc, String institution_desc, double points, double percent, int rank, int acc, int accu, int wa, int rte, int tle, int mle, int ce, int fle, int pe, int ole, int uq, int total, String last_accepted, String last_submission, int country_id, int institution_id, String rgdate, int ivf) {
        this.username = username;
        this.nick = nick;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.language = language;
        this.institution = institution;
        this.country = country;
        this.country_desc = country_desc;
        this.institution_desc = institution_desc;
        this.points = points;
        this.percent = percent;
        this.rank = rank;
        this.acc = acc;
        this.accu = accu;
        this.wa = wa;
        this.rte = rte;
        this.tle = tle;
        this.mle = mle;
        this.ce = ce;
        this.fle = fle;
        this.pe = pe;
        this.ole = ole;
        this.uq = uq;
        this.ivf = ivf;
        this.total = total;
        this.last_accepted = last_accepted.substring(0, 19);
        this.last_submission = last_submission.substring(0, 19);
        this.country_id = country_id;
        this.institution_id = institution_id;
        this.total = acc + rte + tle + mle + ce + fle + pe + ole + uq + ivf;
        this.rgdate = rgdate.substring(0, 19);
    }

    public User(String username, String nick, String email, String name, String lastname, String language, String institution, String country, String country_desc, String institution_desc, double points, double percent, int rank, int acc, int accu, int wa, int rte, int tle, int mle, int ce, int fle, int pe, int ole, int uq, int total, String last_accepted, String last_submission, int country_id, int institution_id, String rgdate, int ivf, int gender, String glanguage, String planguage, double score) {
        this.username = username;
        this.nick = nick;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.language = language;
        this.institution = institution;
        this.country = country;
        this.country_desc = country_desc;
        this.institution_desc = institution_desc;
        this.points = points;
        this.percent = percent;
        this.rank = rank;
        this.acc = acc;
        this.accu = accu;
        this.wa = wa;
        this.rte = rte;
        this.tle = tle;
        this.mle = mle;
        this.ce = ce;
        this.fle = fle;
        this.pe = pe;
        this.ole = ole;
        this.uq = uq;
        this.ivf = ivf;
        this.total = total;
        this.last_accepted = last_accepted.substring(0, 19);
        this.last_submission = last_submission.substring(0, 19);
        this.country_id = country_id;
        this.institution_id = institution_id;
        this.total = acc + rte + tle + mle + ce + fle + pe + ole + uq + ivf;
        this.rgdate = rgdate.substring(0, 19);
        this.gender = gender;
        this.glanguage = glanguage;
        this.planguage = planguage;
        this.score = Math.round(score * 100);
        this.score /= 100;
    }

    //username,nick,email,"
    //  + "fullname,lastname,country.name,
    public User(String username, String nick, String email, String name, String lastname, String country, String institution, boolean enabled, int locale, int lid, int gender) {
        this.username = username;
        this.nick = nick;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.country = country;
        this.institution = institution;
        this.enabled = enabled;
        this.locale = locale;
        this.lid = lid;
        this.gender = gender;
    }

    public User(String username, String nick, String email, String name, String lastname, int country_id, int institution_id, boolean enabled, int locale, String password) {
        this.username = username;
        this.nick = nick;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.country_id = country_id;
        this.institution_id = institution_id;
        this.enabled = enabled;
        this.locale = locale;
        this.password = password;
    }

    public User(int rank, String country, String country_desc, String institution, String institution_desc, String username, String nick, int total, int accu, double percent, double points, String even) {
        this.rank = rank;
        this.country = country;
        this.country_desc = country_desc;
        this.institution = institution;
        this.institution_desc = institution_desc;
        this.username = username;
        this.nick = nick;
        this.total = total;
        this.accu = accu;
        this.percent = percent;
        this.points = points;
        this.even = even;
        try {
            this.percent = percent * 100 / total;
            this.percent = Math.round(this.percent * 100);
            this.percent /= 100;
        } catch (Exception e) {
            percent = 0;
        }
        this.points = Math.round(points * 100);
        this.points /= 100;
    }

    public User(int rank, String username, String nick, int total, int accu, double percent, double points, String even) {
        this.rank = rank;
        this.username = username;
        this.nick = nick;
        this.total = total;
        this.accu = accu;
        this.percent = percent;
        this.points = points;
        this.even = even;
        try {
            this.percent = percent * 100 / total;
            this.percent = Math.round(this.percent * 100);
            this.percent /= 100;
        } catch (Exception e) {
            percent = 0;
        }
        this.points = Math.round(points * 100);
        this.points /= 100;
    }

    //user_contest.uid,username,nick,accepted,penalty,"
    //"lastacc,country.zip,country.name,institution.zip,institution.name
    public User(String username, String nick, int accepted, int penalty, String lastcc, String country, String countryZip, String institution, String institutionZip, List<Problem> problems) {
        this.username = username;
        this.nick = nick;
        this.institution = institutionZip;
        this.country = countryZip;
        this.country_desc = country;
        this.institution_desc = institution;
        this.acc = accepted;
        this.penalty = penalty;
        this.lastacc = lastcc;
        this.problems = new LinkedList<Problem>();
        for (int i = 0; i < problems.size(); i++) {
            Problem problem = new Problem(problems.get(i).getPid());
            problem.initACM();
            this.problems.add(problem);
        }
    }

    public User(int uid, String username) {
        this.uid = uid;
        this.username = username;
    }

    public User(int uid) {
        this.uid = uid;
    }

    public boolean isTeam() {
        return team;
    }

    public void setTeam(boolean team) {
        this.team = team;
    }

    public LinkedList<Problem> getProblems() {
        return problems;
    }

    public void setProblems(LinkedList<Problem> problems) {
        this.problems = problems;
    }

    public String getLastacc() {
        return lastacc;
    }

    public void setLastacc(String lastacc) {
        this.lastacc = lastacc;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getPe() {
        return pe;
    }

    public void setPe(int pe) {
        this.pe = pe;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAccu() {
        return accu;
    }

    public void setAccu(int accu) {
        this.accu = accu;
    }

    public int getAcc() {
        return acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }

    public int getCe() {
        return ce;
    }

    public void setCe(int ce) {
        this.ce = ce;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_desc() {
        return country_desc;
    }

    public void setCountry_desc(String country_desc) {
        this.country_desc = country_desc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEven() {
        return even;
    }

    public void setEven(String even) {
        this.even = even;
    }

    public int getFle() {
        return fle;
    }

    public void setFle(int fle) {
        this.fle = fle;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getInstitution_desc() {
        return institution_desc;
    }

    public void setInstitution_desc(String institution_desc) {
        this.institution_desc = institution_desc;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getMle() {
        return mle;
    }

    public void setMle(int mle) {
        this.mle = mle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRte() {
        return rte;
    }

    public void setRte(int rte) {
        this.rte = rte;
    }

    public int getTle() {
        return tle;
    }

    public void setTle(int tle) {
        this.tle = tle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getWa() {
        return wa;
    }

    public void setWa(int wa) {
        this.wa = wa;
    }

    public int getOle() {
        return ole;
    }

    public void setOle(int ole) {
        this.ole = ole;
    }

    public int getUq() {
        return uq;
    }

    public void setUq(int uq) {
        this.uq = uq;
    }

    public int getLocale() {
        return locale;
    }

    public void setLocale(int locale) {
        this.locale = locale;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public int getInstitution_id() {
        return institution_id;
    }

    public void setInstitution_id(int institution_id) {
        this.institution_id = institution_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getSolved() {
        return solved;
    }

    public void setSolved(int solved) {
        this.solved = solved;
    }

    public int getUnsolved() {
        return unsolved;
    }

    public void setUnsolved(int unsolved) {
        this.unsolved = unsolved;
    }

    public String getLast_accepted() {
        return last_accepted;
    }

    public void setLast_accepted(String last_accepted) {
        this.last_accepted = last_accepted;
    }

    public String getLast_submission() {
        return last_submission;
    }

    public void setLast_submission(String last_submission) {
        this.last_submission = last_submission;
    }

    public int getProblemPosition(int pid) {
        for (int i = 0; i < this.problems.size(); i++) {
            if (this.problems.get(i).getPid() == pid) {
                return i;
            }
        }
        return -1;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getRankingbycountry() {
        return rankingbycountry;
    }

    public void setRankingbycountry(int rankingbycountry) {
        this.rankingbycountry = rankingbycountry;
    }

    public int getRankingbyinstitution() {
        return rankingbyinstitution;
    }

    public void setRankingbyinstitution(int rankingbyinstitution) {
        this.rankingbyinstitution = rankingbyinstitution;
    }

    public int getTot_ranking() {
        return tot_ranking;
    }

    public void setTot_ranking(int tot_ranking) {
        this.tot_ranking = tot_ranking;
    }

    public int getTot_rankingbycountry() {
        return tot_rankingbycountry;
    }

    public void setTot_rankingbycountry(int tot_rankingbycountry) {
        this.tot_rankingbycountry = tot_rankingbycountry;
    }

    public int getTot_rankingbyinstitution() {
        return tot_rankingbyinstitution;
    }

    public void setTot_rankingbyinstitution(int tot_rankingbyinstitution) {
        this.tot_rankingbyinstitution = tot_rankingbyinstitution;
    }

    public String getMedal() {
        return medal;
    }

    public void setMedal(String medal) {
        this.medal = medal;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getRgdate() {
        return rgdate;
    }

    public void setRgdate(String rgdate) {
        this.rgdate = rgdate;
    }

    public int getIvf() {
        return ivf;
    }

    public void setIvf(int ivf) {
        this.ivf = ivf;
    }

    public String getGlanguage() {
        return glanguage;
    }

    public void setGlanguage(String glanguage) {
        this.glanguage = glanguage;
    }

    public String getPlanguage() {
        return planguage;
    }

    public void setPlanguage(String planguage) {
        this.planguage = planguage;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isShowemail() {
        return showemail;
    }

    public void setShowemail(boolean showemail) {
        this.showemail = showemail;
    }

    public boolean isVirtual() {
        return virtual;
    }

    public void setVirtual(boolean virtual) {
        this.virtual = virtual;
    }

    public String getLastip() {
        return lastip;
    }

    public void setLastip(String lastip) {
        this.lastip = lastip;
    }

    public int getMail_quote() {
        return mail_quote;
    }

    public void setMail_quote(int mail_quote) {
        this.mail_quote = mail_quote;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
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

    public int getAct_id() {
        return act_id;
    }

    public void setAct_id(int act_id) {
        this.act_id = act_id;
    }

    public void builTotal() {
        this.total = acc + wa + pe + ce + rte + tle + fle + ole + mle;
    }

    public String getCemail() {
        return cemail;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail;
    }

    public boolean isEnableadveditor() {
        return enableadveditor;
    }

    public void setEnableadveditor(boolean enableadveditor) {
        this.enableadveditor = enableadveditor;
    }

    public boolean isAgreementcojtos() {
        return agreementcojtos;
    }

    public void setAgreementcojtos(boolean agreementcojtos) {
        this.agreementcojtos = agreementcojtos;
    }

    public void buildToolTip() {
        tooltip = username + "\n";
        if (user_1 != null && !"none".equals(user_1)) {
        	tooltip += " " + user_1 + "\n";
        }
        if (user_2 != null && !"none".equals(user_2)) {
            tooltip += " " + user_2 + "\n";
        }
        if (user_3 != null && !"none".equals(user_3)) {
            tooltip += " " + user_3 + "\n";
        }
        if (coach != null && !"none".equals(coach)) {
        	tooltip += " " + coach + " (C)\n";
        }
    }

    /**
     * @return the preferedClassificationsIds
     */
    public int[] getPreferedClassificationsIds() {
        return preferedClassificationsIds;
    }

    /**
     * @param preferedClassificationsIds the preferedClassificationsIds to set
     */
    public void setPreferedClassificationsIds(int[] preferedClassificationsIds) {
        this.preferedClassificationsIds = preferedClassificationsIds;
    }
    
    public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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

	public boolean isProblemNotifications() {
		return problemNotifications;
	}

	public void setProblemNotifications(boolean problemNotifications) {
		this.problemNotifications = problemNotifications;
	}

}
