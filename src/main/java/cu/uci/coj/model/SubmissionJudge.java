package cu.uci.coj.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import cu.uci.coj.config.Config;

public class SubmissionJudge implements Serializable {

	private static final long serialVersionUID = -6506777204076530661L;

	private String status;
	private int cid;
	private int pid;
	private int uid;
	private Date date;
	private Verdicts verdict;

	private int sid;
	private int timeLimit;
	private int caseTimeLimit;
	private long memoryLimit;
	private String lang;
	private String source;
	private boolean specialJudge;

	// response attributes
	private int timeUsed;
	private int cpuTimeUsed;
	private String errMsg;
	private long memoryUsed;
	private int acTestCases;
	private int totalTestCases;
	private int firstWaCase;
	private int minTimeUsed;
	private int maxTimeUsed;
	private int avgTimeUsed;
	private boolean accepted;// esto es para facilitar el trabajo al no tener
								// que comparar textualmente con Accepted en
								// status.

	private boolean virtual;// solo se usa de este lado, lo mando para evitar
							// tener que volver a consultar el submit cuando
							// regresa.Refactorizar.

	private boolean frozen;

	private String font;
	private String statusClass;
	private String statusName;
	private String username;
	private boolean authorize;
	private boolean ontest;
	private String email;
	private String code;
	private String title;
	private String userNick;
	private String problemTitle;
	private boolean enabled;
	private int rank;
	private boolean see_solution;
	private String groupd;
	private String color;
	private String compileInfo;
	private Date ddate;
	private String statusId;
	private String error;
	private int testcase;
	private int rejudge_status;
	private int lid;
	private String key;
	private Character letter;

	// xtats
	private int y;// contador para envios en el coj
	private int yp;// contador para envios por problemas
	private int yu;// contador para envios por usuarios
	private String sdate;
	
	private List<Language> languages;
	private int ppid;
	private MultipartFile uploadfile;
	private Contest contest;
	private boolean haspriviligeForProblem;
	private int course_id = 0;
	
	//FIXME:parche debido a que submit_id(bd) no es igual a sid(objeto). Los select * no sirven.
	//La base de datos esta mal, debemos cambiarla.
	public void setSubmitId(int sid) {
		this.sid = sid; 
	}
	
	public Verdicts getVerdict() {
		return verdict;
	}
	public void setVerdict(Verdicts verdict) {
		this.verdict = verdict;
	}
	@JsonIgnore
	public List<Language> getLanguages() {
		return languages;
	}
	@JsonIgnore
	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}
	@JsonIgnore
	public int getPpid() {
		return ppid;
	}
	@JsonIgnore
	public void setPpid(int ppid) {
		this.ppid = ppid;
	}

	@JsonIgnore
	public MultipartFile getUploadfile() {
		return uploadfile;
	}
	@JsonIgnore
	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}
	@JsonIgnore
	public Contest getContest() {
		return contest;
	}
	@JsonIgnore
	public void setContest(Contest contest) {
		this.contest = contest;
	}
	@JsonIgnore
	public boolean isHaspriviligeForProblem() {
		return haspriviligeForProblem;
	}
	@JsonIgnore
	public void setHaspriviligeForProblem(boolean haspriviligeForProblem) {
		this.haspriviligeForProblem = haspriviligeForProblem;
	}
	@JsonIgnore
	public int getCourse_id() {
		return course_id;
	}
	@JsonIgnore
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public String getGroupd() {
		return groupd;
	}

	public void setGroupd(String groupd) {
		this.groupd = groupd;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCompileInfo() {
		return compileInfo;
	}

	public void setCompileInfo(String compileInfo) {
		this.compileInfo = compileInfo;
	}

	public Date getDdate() {
		return ddate;
	}

	public void setDdate(Date ddate) {
		this.ddate = ddate;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getTestcase() {
		return testcase;
	}

	public void setTestcase(int testcase) {
		this.testcase = testcase;
	}

	public int getRejudge_status() {
		return rejudge_status;
	}

	public void setRejudge_status(int rejudge_status) {
		this.rejudge_status = rejudge_status;
	}

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Character getLetter() {
		return letter;
	}

	public void setLetter(Character letter) {
		this.letter = letter;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getYp() {
		return yp;
	}

	public void setYp(int yp) {
		this.yp = yp;
	}

	public int getYu() {
		return yu;
	}

	public void setYu(int yu) {
		this.yu = yu;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public boolean isSee_solution() {
		return see_solution;
	}

	public void setSee_solution(boolean see_solution) {
		this.see_solution = see_solution;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getProblemTitle() {
		return problemTitle;
	}

	public void setProblemTitle(String problemTitle) {
		this.problemTitle = problemTitle;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAuthorize() {
		return authorize;
	}

	public void setAuthorize(boolean authorize) {
		this.authorize = authorize;
	}

	public boolean isOntest() {
		return ontest;
	}

	public void setOntest(boolean ontest) {
		this.ontest = ontest;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStatusClass() {
		return statusClass;
	}

	public void setStatusClass(String statusClass) {
		this.statusClass = statusClass;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public boolean isSpecialJudge() {
		return specialJudge;
	}

	public void setSpecialJudge(boolean isSpecialJudge) {
		this.specialJudge = isSpecialJudge;
	}

	public boolean isFrozen() {
		return frozen;
	}

	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}

	public SubmissionJudge() {

	}

	public SubmissionJudge(int sid, int uid, String code, int pid, int time, int caseTimeLimit, int memory, String lang) {
		this.sid = sid;
		this.uid = uid;
		this.source = code;
		this.pid = pid;
		this.timeLimit = time;
		this.caseTimeLimit = caseTimeLimit;
		this.memoryLimit = memory;
		this.lang = lang;
	}

	public SubmissionJudge(int uid, int pid, int sid, int cid) {
		this.uid = uid;
		this.pid = pid;
		this.sid = sid;
		this.cid = cid;
	}

	public SubmissionJudge(int uid, int pid, int sid, int cid, String lang) {
		this.uid = uid;
		this.pid = pid;
		this.sid = sid;
		this.cid = cid;
		this.lang = lang;
	}

	public SubmissionJudge(int sid, int uid, String code, int pid, int timeLimit, int caseTimeLimit, int memoryLimit, String lang, int cid) {
		this.sid = sid;
		this.uid = uid;
		this.source = code;
		this.pid = pid;
		this.timeLimit = timeLimit;
		this.caseTimeLimit = caseTimeLimit;
		this.memoryLimit = memoryLimit;
		this.lang = lang;
		this.cid = cid;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

	public long getMemoryLimit() {
		return memoryLimit;
	}

	public void setMemoryLimit(long memoryLimit) {
		this.memoryLimit = memoryLimit;
	}

	public int getTimeUsed() {
		return timeUsed;
	}

	public void setTimeUsed(int timeUsed) {
		this.timeUsed = timeUsed;
	}

	public int getCpuTimeUsed() {
		return cpuTimeUsed;
	}

	public void setCpuTimeUsed(int cpuTimeUsed) {
		this.cpuTimeUsed = cpuTimeUsed;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public long getMemoryUsed() {
		return memoryUsed;
	}

	public void setMemoryUsed(long memoryUsed) {
		this.memoryUsed = memoryUsed;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getAcTestCases() {
		return acTestCases;
	}

	public void setAcTestCases(int acTestCases) {
		this.acTestCases = acTestCases;
	}

	public int getTotalTestCases() {
		return totalTestCases;
	}

	public void setTotalTestCases(int totalTestCases) {
		this.totalTestCases = totalTestCases;
	}

	public int getFirstWaCase() {
		return firstWaCase;
	}

	public void setFirstWaCase(int firstWaCase) {
		this.firstWaCase = firstWaCase;
	}

	public int getMinTimeUsed() {
		return minTimeUsed;
	}

	public void setMinTimeUsed(int minTimeUsed) {
		this.minTimeUsed = minTimeUsed;
	}

	public int getMaxTimeUsed() {
		return maxTimeUsed;
	}

	public void setMaxTimeUsed(int maxTimeUsed) {
		this.maxTimeUsed = maxTimeUsed;
	}

	public int getAvgTimeUsed() {
		return avgTimeUsed;
	}

	public void setAvgTimeUsed(int avgTimeUsed) {
		this.avgTimeUsed = avgTimeUsed;
	}

	public void setCaseTimeLimit(int caseTimeLimit) {
		this.caseTimeLimit = caseTimeLimit;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getCaseTimeLimit() {
		return caseTimeLimit;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public boolean isVirtual() {
		return virtual;
	}

	public void setVirtual(boolean virtual) {
		this.virtual = virtual;
	}

	public String getMemoryMB() {
		if (memoryUsed == -1)
			return "...";
		return FileUtils.byteCountToDisplaySize(memoryUsed);
	}

	public String getFontMB() {
		if (NumberUtils.isNumber(font))
			return FileUtils.byteCountToDisplaySize(Long.valueOf(font));
		return "...";
	}

	public void froze() {
		this.status = "Pending";
		this.timeUsed = -1;
		this.memoryUsed = -1;
		this.font = "...";
		this.lang = "...";
		this.statusClass = "PEN";
		this.statusName = null;
	}

	public void initialize() {
		
		if (this.font == null || this.font.equals("-1")) {
			this.font = "...";
		}
		
		if (status.equals("Accepted")) {
			this.statusClass = "AC";
		} else {
			this.statusClass = "WA";
		}

		if (status.equals("Compilation Error")) {
			this.statusName = "CE";
		}

		if (status.equals("Runtime Error")) {
			this.statusName = "RTE";
		}

		if (status.equals("Compilation Error") || status.equals("Runtime Error") || status.equals("Invalid Function")) {
			this.memoryUsed = -1;
			this.timeUsed = -1;
		}
		if (status.equals("Judging") || status.equals("Unqualified") || status.equals("Pending")) {
			this.memoryUsed = -1;
			this.timeUsed = -1;
			this.font = "...";

		}

		if (status.equals("Pending")) {
			this.statusClass = "PEN";
		}

		if (status.equals("Unqualified") || status.equals("Judging")) {
			this.statusClass = "UQ";
		}
		if (status.equals("Time Limit Exceeded") || status.equals("Output Limit Exceeded")) {
			this.timeUsed = -1;
			this.memoryUsed = -1;	
		}

		if (status.equals("Memory Limit Exceeded")) {
			this.timeUsed = -1;
		}

		if (!status.equals("Accepted") && !status.equals("Compilation Error") && !status.equals("Unqualified") && !status.equals("Judging") && !status.equals("Pending")) {
			this.ontest = true;
		}
	}
	@JsonIgnore
	public String getFilename() {
		if (getUsername() != null && getStatus() != null && getLang()!= null)
		return this.getUsername() + "-p" + this.getPid() + "-" + this.getStatus().replace(" ", "_") + "-s" + this.getSid() + "." + Config.getProperty("language.filext." + this.getLang());
		return null;
	}
	@JsonIgnore
	public String getLanguageByLid() {
		if (languages != null) {
			for (Iterator<Language> it = languages.iterator(); it.hasNext();) {
				Language lang = it.next();
				if (lang.getLid() == lid) {
					return lang.getLanguage();
				}
			}
		}
		return "C";
	}

	@JsonIgnore
	public void getLanguageIdByKey() {
		if (languages != null) {
			for (Iterator<Language> it = languages.iterator(); it.hasNext();) {
				Language lang = it.next();
				if (lang.getKey().equals(key)) {
					lid = lang.getLid();
					return;
				}
			}
			lid = 1;
		}
	}
}
