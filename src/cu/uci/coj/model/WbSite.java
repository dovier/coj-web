package cu.uci.coj.model;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
*
* @author Eddy Roberto Morales Perez
*/

public class WbSite {
	private int sid;
	private String site;
	private String url;
	private String code;
	private List<WbContest> contests;
	
	private boolean completed;
	private boolean enabled;
	
	int timeanddateid;
	String timezone;

	public WbSite() {
		super();
	}

	public WbSite(int sid, String site, String url, String code, boolean completed, boolean enabled, int timeanddateid, String timezone) {
		super();
		this.sid = sid;
		this.site = site;
		this.url = url;
		this.code = code;
		this.completed = completed;
		this.enabled = enabled;
		this.timeanddateid = timeanddateid;
		this.timezone = timezone;
	}

	public WbSite(int sid, String site, String url, String code, List<WbContest> contests) {
		super();
		this.sid = sid;
		this.site = site;
		this.url = url;
		this.code = code;
		this.contests = contests;
	}	
	
	
	public WbSite(int sid, String site, String url, String code, List<WbContest> contests, boolean completed) {
		super();
		this.sid = sid;
		this.site = site;
		this.url = url;
		this.code = code;
		this.contests = contests;
		this.completed = completed;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<WbContest> getContests() {
		return contests;
	}

	public void setContests(List<WbContest> contests) {
		this.contests = contests;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}	
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public int getTimeanddateid() {
		return timeanddateid;
	}

	public void setTimeanddateid(int timeanddateid) {
		this.timeanddateid = timeanddateid;
	}
	
	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	
	
	public int getOffset() {
		int offset = TimeZone.getTimeZone(timezone).getOffset(new Date().getTime()) -
				TimeZone.getTimeZone("America/Havana").getOffset(new Date().getTime());
		return offset; 		  
	}
}