package cu.uci.coj.model;

import java.util.Date;
import java.util.List;

public class Filter {

	private Date startDate;
	private Date endDate;
	private String username;
	private String current_status;
	private String language;
	private Integer pid;
	private List<Language> languages;
	private String status;
	private String fparam;
	private String sparam;
	private Integer cid;
	private String contest_id;
	private int access;
	private int vstatus;
	private int classification;
	private int complexity;
	private Integer startSid;
	private Integer endSid;
	

	public Integer getStartSid() {
		return startSid;
	}

	public void setStartSid(Integer startSid) {
		this.startSid = startSid;
	}

	public Integer getEndSid() {
		return endSid;
	}

	public void setEndSid(Integer endSid) {
		this.endSid = endSid;
	}

	public int getVstatus() {
		return vstatus;
	}

	public void setVstatus(int vstatus) {
		this.vstatus = vstatus;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAccess() {
		return access;
	}

	public void setAccess(int access) {
		this.access = access;
	}

	public String getContest_id() {
		return contest_id;
	}

	public void setContest_id(String contest_id) {
		this.contest_id = contest_id;
	}

	public Filter() {
	}

	public Filter(Date startDate, Date endDate, String username, Integer cid, String current_status, String language, Integer pid, List<Language> languages, String status) {
		this.cid = cid;
		this.startDate = startDate;
		this.endDate = endDate;
		this.username = username;
		this.current_status = current_status;
		this.language = language;
		this.pid = pid;
		this.languages = languages;
		this.status = status;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCurrent_status() {
		return current_status;
	}

	public void setCurrent_status(String current_status) {
		this.current_status = current_status;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFparam() {
		return fparam;
	}

	public void setFparam(String fparam) {
		this.fparam = fparam;
	}

	public String getSparam() {
		return sparam;
	}

	public void setSparam(String sparam) {
		this.sparam = sparam;
	}

	public void fillFirstParam() {
		fparam = "";
		boolean is = false;
		if (username != null) {
			fparam += "?username=" + username;
			is = true;
		}

		if (pid != null) {
			if (is) {
				fparam += "?pid=" + pid;
			} else {
				is = true;
				fparam += "?pid=" + pid;
			}
		}

		if (current_status != null) {
			if (is) {
				fparam += "&status=" + current_status;
			} else {
				is = true;
				fparam += "?status=" + current_status;
			}
		}


	
		if (language != null) {
			if (is) {
				fparam += "&planguage=" + language;
			} else {
				is = true;
				fparam += "?planguage=" + language;
			}
		}

		if (cid != null) {
			if (is) {
				fparam += "&cid=" + cid;
			} else {
				is = true;
				fparam += "?cid=" + cid;
			}
		}

	}

	public void fillSecondParam() {
		sparam = "";
		if (username != null) {
			sparam += "&username=" + username;
		}

		if (pid != null) {
			sparam += "?pid=" + pid;
		}

		if (current_status != null) {
			sparam += "&status=" + current_status;
		}

		if (language != null) {
			sparam += "&planguage=" + language;
		}

		if (cid != null) {
			sparam += "&cid=" + cid;
		}

	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	/**
	 * @return the classification
	 */
	public int getClassification() {
		return classification;
	}

	/**
	 * @param classification
	 *            the classification to set
	 */
	public void setClassification(int classification) {
		this.classification = classification;
	}

	/**
	 * @return the complexity
	 */
	public int getComplexity() {
		return complexity;
	}

	/**
	 * @param complexity
	 *            the complexity to set
	 */
	public void setComplexity(int complexity) {
		this.complexity = complexity;
	}
}
