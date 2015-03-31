package cu.uci.coj.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.validation.constraints.Size;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class Problem {

	private String forumLink;
	private boolean editingAllowed;
	private DecimalFormat decimal;
	private String even;
	private String balloonColor;
	private Integer pid = 0;
	@NotEmpty
	@Size(min = 4, max = 40)
	private String title;
	private String slvimg;
	private String description;
	private String input;
	@NotEmpty
	private String output;
	private String inputex;
	@NotEmpty
	private String outputex;
	@NotEmpty
	private String author;
	private String comments;
	@NotEmpty
	private long time;
	private long casetimelimit;
	@NotEmpty
	private long memory;
	@NotEmpty
	private int fontsize;
	private String date;
	@NotEmpty
	private String username;
	private int uid;
	private int submitions;
	private int ac;
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
	private double accp;
	private boolean jump;
	private boolean solved;
	private boolean locked;
	private boolean unsolved;
	private boolean enabled;
	private boolean multidata;
	private boolean special_judge;
	private int contest;
	private int contest_flag;
	private double points;
	private String orderby;
	private boolean favorite;
        private List<Limits> limits;
	// ACM RANK PARAMS
	private String ac_time;
	private int submissions;
	private int beforeac;
	private int afterac;
	private int pending;
	private boolean accepted;
	private char letter;
	private String acmin;
	private String scoreClass;
	private boolean fsolved;
	private int teamsolved;
	private int teamstry;
	private double avgs;
	private List<Language> languages;
	private List<User> psetters;
	private int level;
	private String rdate;
	private boolean see;
	private MultipartFile[] files;
	private int[] languageids;
	private int[] psettersids;
	private boolean disable_24h;
	private int accu;
	// I18N
	private String userLanguage;
	private String titleEs;
	private String descriptionEs;
	private String inputEs;
	private String outputEs;
	private String commentsEs;
	private String titlePt;
	private String descriptionPt;
	private String inputPt;
	private String outputPt;
	private String commentsPt;

	public String getMemoryMB() {
		return FileUtils.byteCountToDisplaySize(memory);
	}

	public String getFontMB() {
		return FileUtils.byteCountToDisplaySize(fontsize);
	}

	public String getForumLink() {
		return forumLink;
	}

	public void setForumLink(String forumLink) {
		this.forumLink = forumLink;
	}

	public String getBalloonColor() {
		return balloonColor;
	}

	public void setBalloonColor(String balloonColor) {
		this.balloonColor = balloonColor;
	}

	public String getUserLanguage() {
		return userLanguage;
	}

	public void setUserLanguage(String userLanguage) {
		this.userLanguage = userLanguage;
	}

	public String getTitleEs() {

		return titleEs;
	}

	public String getDefaultTitleEs() {

		return defaultIfEmpty(titleEs, title);
	}

	private String defaultIfEmpty(String lang, String defaultOpt) {
		return StringUtils.isEmpty(lang) ? defaultOpt : lang;
	}

	public void setTitleEs(String titleEs) {
		this.titleEs = titleEs;
	}

	public String getDescriptionEs() {
		return descriptionEs;
	}

	public String getDefaultDescriptionEs() {
		return defaultIfEmpty(descriptionEs, description);
	}

	public void setDescriptionEs(String descriptionEs) {
		this.descriptionEs = descriptionEs;
	}

	public String getDefaultInputEs() {
		return defaultIfEmpty(inputEs, input);
	}

	public String getInputEs() {
		return inputEs;
	}

	public void setInputEs(String inputEs) {
		this.inputEs = inputEs;
	}

	public String getDefaultOutputEs() {
		return defaultIfEmpty(outputEs, output);
	}

	public String getOutputEs() {
		return outputEs;
	}

	public void setOutputEs(String outputEs) {
		this.outputEs = outputEs;
	}

	public String getDefaultCommentsEs() {
		return defaultIfEmpty(commentsEs, comments);
	}

	public String getCommentsEs() {
		return commentsEs;
	}

	public void setCommentsEs(String commentsEs) {
		this.commentsEs = commentsEs;
	}

	public String getTitleEN() {
		return title;
	}

	public void setTitleEN(String titleEN) {
		this.title = titleEN;
	}

	public String getDescriptionEN() {
		return description;
	}

	public void setDescriptionEN(String descriptionEN) {
		this.description = descriptionEN;
	}

	public String getInputEN() {
		return input;
	}

	public void setInputEN(String inputEN) {
		this.input = inputEN;
	}

	public String getOutputEN() {
		return output;
	}

	public void setOutputEN(String outputEN) {
		this.output = outputEN;
	}

	public String getCommentsEN() {
		return comments;
	}

	public void setCommentsEN(String commentsEN) {
		this.comments = commentsEN;
	}

	public String getDefaultTitlePt() {
		return defaultIfEmpty(titlePt, title);
	}

	public String getTitlePt() {
		return titlePt;
	}

	public void setTitlePt(String titlePt) {
		this.titlePt = titlePt;
	}

	public String getDescriptionPt() {
		return descriptionPt;
	}

	public String getDefaultDescriptionPt() {
		return defaultIfEmpty(descriptionPt, description);
	}

	public void setDescriptionPt(String descriptionPt) {
		this.descriptionPt = descriptionPt;
	}

	public String getDefaultInputPt() {
		return defaultIfEmpty(inputPt, input);
	}

	public String getInputPt() {
		return inputPt;
	}

	public void setInputPt(String inputPt) {
		this.inputPt = inputPt;
	}

	public String getOutputPt() {
		return outputPt;
	}

	public String getDefaultOutputPt() {
		return defaultIfEmpty(outputPt, input);
	}

	public void setOutputPt(String outputPt) {
		this.outputPt = outputPt;
	}

	public String getCommentsPt() {

		return commentsPt;
	}

	public String getDefaultCommentsPt() {

		return defaultIfEmpty(commentsPt, comments);
	}

	public void setCommentsPt(String commentsPt) {
		this.commentsPt = commentsPt;
	}

	public int getAccu() {
		return accu;
	}

	public void setAccu(int accu) {
		this.accu = accu;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLetter(Integer num) {
		this.letter = (char) ('A' + num);
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isDisable_24h() {
		return disable_24h;
	}

	public void setDisable_24h(boolean disable_24h) {
		this.disable_24h = disable_24h;
	}

	public int[] getLanguageids() {
		return languageids;
	}

	public void setLanguageids(int[] languageids) {
		this.languageids = languageids;
	}

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	private void initDecimal() {
		this.decimal = new DecimalFormat();
		this.decimal.setMaximumFractionDigits(2);
		this.decimal.setMinimumFractionDigits(2);
	}

	public Problem() {
		initDecimal();
	}

	public Problem(Integer pid) {
		initDecimal();
		this.pid = pid;
	}

	public Problem(int pid, String title) {
		initDecimal();
		this.pid = pid;
		this.title = title;
	}

	public Problem(int pid, String title, String even, char letter) {
		initDecimal();
		this.pid = pid;
		this.title = title;
		this.even = even;
		this.letter = letter;
	}

	public Problem(int pid, String title, char letter) {
		initDecimal();
		this.pid = pid;
		this.title = title;
		this.letter = letter;
	}

	public Problem(int pid, String title, int ac, int wa, int pe, int tle, int mle, int fle, int ole, int ce, int rte, int uq, int ivf) {
		initDecimal();
		this.pid = pid;
		this.title = title;
		this.ac = ac;
		this.wa = wa;
		this.pe = pe;
		this.tle = tle;
		this.mle = mle;
		this.fle = fle;
		this.ole = ole;
		this.ce = ce;
		this.rte = rte;
		this.uq = uq;
		this.ivf = ivf;
		this.submitions = (ac + wa + pe + tle + mle + fle + ole + ce + rte + uq + ivf);
	}

	public Problem(Integer pid, String title, int ac, int wa, int pe, int tle, int mle, int fle, int ole, int ce, int rte, int uq, int ivf) {
		initDecimal();
		this.title = title;
		this.ac = ac;
		this.wa = wa;
		this.pe = pe;
		this.tle = tle;
		this.mle = mle;
		this.fle = fle;
		this.ole = ole;
		this.ce = ce;
		this.rte = rte;
		this.uq = uq;
		this.ivf = ivf;
		this.submitions = (ac + wa + pe + tle + mle + fle + ole + ce + rte + uq + ivf);
	}

	public Problem(Integer pid, String title, boolean solved, boolean unsolved, String even) {
		initDecimal();
		this.pid = pid;
		this.title = title;
		this.solved = solved;
		this.unsolved = unsolved;
		this.even = even;
	}

	public Problem(Integer pid, String title, boolean jump) {
		this.pid = pid;
		this.title = title;
		this.jump = jump;
	}

	public Problem(Integer pid, String title, int submitions, int accepted, double accp, String even, String slvimg) {
		initDecimal();
		this.pid = pid;
		this.title = title;
		this.submitions = submitions;
		this.ac = accepted;
		this.accp = accp;
		this.even = even;
		this.slvimg = slvimg;
	}

	public Problem(Integer pid, String title, int submitions, int accepted, double accp, String even, boolean solved, boolean unsolved) {
		initDecimal();
		this.pid = pid;
		this.title = title;
		this.submitions = submitions;
		this.ac = accepted;
		this.accp = accp;
		this.even = even;
		this.solved = solved;
		this.unsolved = unsolved;
	}

	public Problem(Integer pid, String title, String description, String input, String output, String inputex, String outputex, String author, String comments, int time, int memory, int fontsize,
			String date, int uid, String username) {
		initDecimal();
		this.pid = pid;
		this.title = title;
		this.description = description;
		this.input = input;
		this.output = output;
		this.inputex = inputex;
		this.outputex = outputex;
		this.author = author;
		this.comments = comments;
		this.time = time;
		this.memory = memory;
		this.fontsize = fontsize;
		this.date = date;
		this.uid = uid;
		this.username = username;
		this.outputex = this.outputex.replaceAll("\n", "<br/>");
		this.inputex = this.inputex.replaceAll("\n", "<br/>");
	}

	public Problem(Integer pid, String title, String description, String input, String output, String inputex, String outputex, String author, String comments, int time, int casetime, int memory,
			int fontsize, String date, int uid, String username, boolean multidata) {
		initDecimal();
		this.pid = pid;
		this.title = title;
		this.description = description;
		this.input = input;
		this.output = output;
		this.inputex = inputex;
		this.outputex = outputex;
		this.author = author;
		this.comments = comments;
		this.time = time;
		this.casetimelimit = casetime;
		this.memory = memory;
		this.fontsize = fontsize;
		this.date = date;
		this.uid = uid;
		this.username = username;
		this.outputex = this.outputex.replaceAll("\n", "<br/>");
		this.inputex = this.inputex.replaceAll("\n", "<br/>");
		this.multidata = multidata;
	}

	public Problem(Integer pid, String title, String description, String input, String output, String inputex, String outputex, String author, String comments, int time, int memory, int fontsize,
			boolean enabled) {
		initDecimal();
		this.pid = pid;
		this.title = title;
		this.description = description;
		this.input = input;
		this.output = output;
		this.inputex = inputex;
		this.outputex = outputex;
		this.author = author;
		this.comments = comments;
		this.time = time;
		this.memory = memory;
		this.fontsize = fontsize;
		this.outputex = this.outputex.replaceAll("\n", "<br/>");
		this.inputex = this.inputex.replaceAll("\n", "<br/>");
		this.enabled = enabled;
	}

	public Problem(Integer pid, String title, String description, String input, String output, String inputex, String outputex, String author, String comments, int time, int casetime, int memory,
			int fontsize, boolean enabled, boolean multidata) {
		initDecimal();
		this.pid = pid;
		this.title = title;
		this.description = description;
		this.input = input;
		this.output = output;
		this.inputex = inputex;
		this.outputex = outputex;
		this.author = author;
		this.comments = comments;
		this.time = time;
		this.casetimelimit = casetime;
		this.memory = memory;
		this.fontsize = fontsize;
		this.outputex = this.outputex.replaceAll("\n", "<br/>");
		this.inputex = this.inputex.replaceAll("\n", "<br/>");
		this.enabled = enabled;
		this.multidata = multidata;
	}

	public Problem(Integer pid, String title, String description, String input, String output, String inputex, String outputex, String author, String comments, int time, int casetime, int memory,
			int fontsize, boolean enabled, boolean multidata, int contest) {
		initDecimal();
		this.pid = pid;
		this.title = title;
		this.description = description;
		this.input = input;
		this.output = output;
		this.inputex = inputex;
		this.outputex = outputex;
		this.author = author;
		this.comments = comments;
		this.time = time;
		this.casetimelimit = casetime;
		this.memory = memory;
		this.fontsize = fontsize;
		this.outputex = this.outputex.replaceAll("\n", "<br/>");
		this.inputex = this.inputex.replaceAll("\n", "<br/>");
		this.enabled = enabled;
		this.multidata = multidata;
		this.contest = contest;
	}

	public Problem(Integer pid, String title, String description, String input, String output, String inputex, String outputex, String author, String comments, int time, int casetime, int memory,
			int fontsize, boolean enabled, boolean multidata, int contest, int contest_flag) {
		initDecimal();
		this.pid = pid;
		this.title = title;
		this.description = description;
		this.input = input;
		this.output = output;
		this.inputex = inputex;
		this.outputex = outputex;
		this.author = author;
		this.comments = comments;
		this.time = time;
		this.casetimelimit = casetime;
		this.memory = memory;
		this.fontsize = fontsize;
		this.outputex = this.outputex.replaceAll("\n", "<br/>");
		this.inputex = this.inputex.replaceAll("\n", "<br/>");
		this.enabled = enabled;
		this.multidata = multidata;
		this.contest = contest;
		this.contest_flag = contest_flag;
	}

	public Problem(String ac_time, int submissions, int beforeac, int afterac, int pending, boolean accepted) {
		initDecimal();
		this.ac_time = ac_time;
		this.submissions = submissions;
		this.beforeac = beforeac;
		this.afterac = afterac;
		this.pending = pending;
		this.accepted = accepted;
	}

	public int getAccepted() {
		return ac;
	}

	public void setAccepted(int accepted) {
		this.ac = accepted;
	}

	public double getAccp() {
		return accp;
	}

	public void setAccp(double accp) {
		this.accp = accp;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public int getSubmitions() {
		return submitions;
	}

	public void setSubmitions(int submitions) {
		this.submitions = submitions;
	}

	public String getTitle() {
		title = selectByLanguage(titleEs, title, titlePt, userLanguage);
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setEven(String even) {
		this.even = even;
	}

	public String getEven() {
		return even;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getComments() {
		comments = selectByLanguage(commentsEs, comments, commentsPt, userLanguage);
		return comments;
	}

	protected String selectByLanguage(String opcionEs, String opcionEN, String opcionPt, String lang) {
		if (!StringUtils.isEmpty(opcionEs) && "es".equalsIgnoreCase(lang)) {
			return opcionEs;
		} else if (!StringUtils.isEmpty(opcionPt) && "pt".equalsIgnoreCase(lang)) {
			return opcionPt;
		}
		// FIXME esto esta comentado porque la opcion EN es la opcion por
		// defecto.
		// cuando se haga una mejor solucion para esto, hay que hacerlo
		// diferente
		// else if ("EN".equals(lang))
		return opcionEN;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		description = selectByLanguage(descriptionEs, description, descriptionPt, userLanguage);
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFontsize() {
		return fontsize;
	}

	public void setFontsize(int fontsize) {
		this.fontsize = fontsize;
	}

	public String getInput() {
		input = selectByLanguage(inputEs, input, inputPt, userLanguage);
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getInputex() {
		return inputex;
	}

	public void setInputex(String inputex) {
		this.inputex = inputex;
	}

	public long getMemory() {
		return memory;
	}

	public void setMemory(long memory) {
		this.memory = memory;
	}

	public String getOutput() {
		output = selectByLanguage(outputEs, output, outputPt, userLanguage);
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getOutputex() {
		return outputex;
	}

	public void setOutputex(String outputex) {
		this.outputex = outputex;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSlvimg() {
		return slvimg;
	}

	public void setSlvimg(String slvimg) {
		this.slvimg = slvimg;
	}

	public boolean isJump() {
		return jump;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	public boolean isUnsolved() {
		return unsolved;
	}

	public void setUnsolved(boolean unsolved) {
		this.unsolved = unsolved;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public long getCasetimelimit() {
		return casetimelimit;
	}

	public void setCasetimelimit(long casetimelimit) {
		this.casetimelimit = casetimelimit;
	}

	public boolean isMultidata() {
		return multidata;
	}

	public void setMultidata(boolean multidata) {
		this.multidata = multidata;
	}

	public int getAc() {
		return ac;
	}

	public void setAc(int ac) {
		this.ac = ac;
	}

	public int getCe() {
		return ce;
	}

	public void setCe(int ce) {
		this.ce = ce;
	}

	public int getFle() {
		return fle;
	}

	public void setFle(int fle) {
		this.fle = fle;
	}

	public int getMle() {
		return mle;
	}

	public void setMle(int mle) {
		this.mle = mle;
	}

	public int getOle() {
		return ole;
	}

	public void setOle(int ole) {
		this.ole = ole;
	}

	public int getPe() {
		return pe;
	}

	public void setPe(int pe) {
		this.pe = pe;
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

	public int getUq() {
		return uq;
	}

	public void setUq(int uq) {
		this.uq = uq;
	}

	public int getWa() {
		return wa;
	}

	public void setWa(int wa) {
		this.wa = wa;
	}

	public int getContest() {
		return contest;
	}

	public void setContest(int contest) {
		this.contest = contest;
	}

	public int getContest_flag() {
		return contest_flag;
	}

	public void setContest_flag(int contest_flag) {
		this.contest_flag = contest_flag;
	}

	public String getAc_time() {
		return ac_time;
	}

	public void setAc_time(String ac_time) {
		this.ac_time = ac_time;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public int getAfterac() {
		return afterac;
	}

	public void setAfterac(int afterac) {
		this.afterac = afterac;
	}

	public int getBeforeac() {
		return beforeac;
	}

	public void setBeforeac(int beforeac) {
		this.beforeac = beforeac;
	}

	public int getPending() {
		return pending;
	}

	public void setPending(int pending) {
		this.pending = pending;
	}

	public int getSubmissions() {
		return submissions;
	}

	public void setSubmissions(int submissions) {
		this.submissions = submissions;
	}

	public void initACM() {
		this.pending = 0;
		this.accepted = false;
		this.beforeac = 0;
		this.afterac = 0;
		this.ac_time = "";
		this.acmin = "";
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public void increaseAfterAc(int cant) {
		this.afterac += cant;
	}

	public void increaseBeforeAc(int cant) {
		this.beforeac += cant;
	}

	public void increasePending(int cant) {
		this.pending += cant;
	}

	public String getAcmin() {
		return acmin;
	}

	public void setAcmin(String acmin) {
		this.acmin = acmin;
	}

	public String getScoreClass() {
		return scoreClass;
	}

	public void setScoreClass(String scoreClass) {
		this.scoreClass = scoreClass;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public boolean isFsolved() {
		return fsolved;
	}

	public void setFsolved(boolean fsolved) {
		this.fsolved = fsolved;
	}

	public int getTeamsolved() {
		return teamsolved;
	}

	public void setTeamsolved(int teamsolved) {
		this.teamsolved = teamsolved;
	}

	public int getTeamstry() {
		return teamstry;
	}

	public void setTeamstry(int teamstry) {
		this.teamstry = teamstry;
	}

	public double getAvgs() {
		return avgs;
	}

	public void setAvgs(double avgs) {
		this.avgs = avgs;
	}

	public void incrementteamSolved() {
		this.teamsolved++;
	}

	public void incrementSubmissions() {
		this.submissions++;
	}

	public void incrementTeamTry() {
		this.teamstry++;
	}

	public String getRoundedPoints() {
		return this.decimal.format(points);
	}

	public String getRoundedAccp() {
		return this.decimal.format(accp);
	}

	public void stats() {
		if (this.submissions > 0) {
			this.accp = (double) ((double) this.teamsolved / (double) this.submissions) * 100;
			this.accp = Math.round(this.accp * 100);
			this.accp /= 100;
		}

		if (this.teamstry > 0) {
			this.points = (double) ((double) this.submissions / (double) this.teamstry);
			this.points = Math.round(this.points * 100);
			this.points /= 100;
		}

		if (this.teamsolved > 0) {
			this.avgs = (double) ((double) this.beforeac / (double) this.teamsolved);
			this.avgs = Math.round(this.avgs * 100);
			this.avgs /= 100;
		}

	}

	public int getIvf() {
		return ivf;
	}

	public void setIvf(int ivf) {
		this.ivf = ivf;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public boolean isSee() {
		return see;
	}

	public void setSee(boolean see) {
		this.see = see;
	}

	public boolean isSpecial_judge() {
		return special_judge;
	}

	public void setSpecial_judge(boolean special_judge) {
		this.special_judge = special_judge;
	}

	public int[] getPsettersids() {
		return psettersids;
	}

	public void setPsettersids(int[] psettersids) {
		this.psettersids = psettersids;
	}

	public List<User> getPsetters() {
		return psetters;
	}

	public void setPsetters(List<User> psetters) {
		this.psetters = psetters;
	}

	public boolean isEditingAllowed() {
		return editingAllowed;
	}

	public void setEditingAllowed(boolean editingAllowed) {
		this.editingAllowed = editingAllowed;
	}

	public void initProblemsetters() {
		psettersids = new int[psetters.size()];
		int i = 0;
		for (Iterator<User> it = psetters.iterator(); it.hasNext();) {
			User user = it.next();
			psettersids[i++] = user.getUid();
		}
	}

	public void initLanguages() {
		languageids = new int[languages.size()];
		int i = 0;
		for (Iterator<Language> it = languages.iterator(); it.hasNext();) {
			Language language = it.next();
			languageids[i++] = language.getLid();
		}
	}

	public boolean isAvailableInEn() {
		return !(StringUtils.isEmpty(title) || StringUtils.isEmpty(description) || StringUtils.isEmpty(input) || StringUtils.isEmpty(output));
	}

	public boolean isAvailableInEs() {
		return !(StringUtils.isEmpty(titleEs) || StringUtils.isEmpty(descriptionEs) || StringUtils.isEmpty(inputEs) || StringUtils.isEmpty(outputEs));
	}

	public boolean isAvailableInPt() {
		return !(StringUtils.isEmpty(titlePt) || StringUtils.isEmpty(descriptionPt) || StringUtils.isEmpty(inputPt) || StringUtils.isEmpty(outputPt));
	}
        
    /**
     * @return the limits
     */
    public List<Limits> getLimits() {
        return limits;
    }

    /**
     * @param limits the limits to set
     */
    public void setLimits(List<Limits> limits) {
        this.limits = limits;
    }

    public void initLimits(List<Language> languages) {
        this.limits = new ArrayList<>();

        for (Language language : languages) {
            limits.add(new Limits(language.getLid(), this.getPid()));
        }
    }
}
