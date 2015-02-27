package cu.uci.coj.model;

import java.util.Date;

/**
*
* @author Eddy Roberto Morales Perez
*/


public class WbContest {
	private int id;
	private String url;
	private String name;
	private int sid;
	private Date startDate;
	private Date endDate;
	
	//Notifications
	boolean notifCreated;
	boolean notifChanged;
	boolean notifNearContest;
	
	public WbContest() {
		super();
	}

	public WbContest(String url, String name, int sid, Date startDate, Date endDate) {
		super();
		this.url = url;
		this.name = name;
		this.sid = sid;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public WbContest(int id, String url, String name, int sid, Date startDate, Date endDate, boolean notifCreated, boolean notifChanged) {
		super();
		this.id = id;
		this.url = url;
		this.name = name;
		this.sid = sid;
		this.startDate = startDate;
		this.endDate = endDate;
		this.notifCreated = notifCreated;
		this.notifChanged = notifChanged;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
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
	public boolean isNotifCreated() {
		return notifCreated;
	}
	public void setNotifCreated(boolean notifCreated) {
		this.notifCreated = notifCreated;
	}
	public boolean isNotifChanged() {
		return notifChanged;
	}
	public void setNotifChanged(boolean notifChanged) {
		this.notifChanged = notifChanged;
	}	
	public boolean isNotifNearContest() {
		return notifNearContest;
	}

	public void setNotifNearContest(boolean notifNearContest) {
		this.notifNearContest = notifNearContest;
	}

	public String getStartTimeAndDateUrl() {
		return "http://timeanddate.com/worldclock/fixedtime.html?day="+startDate.getDate()+"&month="+ (startDate.getMonth() + 1)+"&year="+(startDate.getYear() + 1900)+"&hour="+startDate.getHours()+"&min="+startDate.getMinutes()+"&sec="+startDate.getSeconds()+"&p1=";
	}
	public String getEndTimeAndDateUrl() {
		return "http://timeanddate.com/worldclock/fixedtime.html?day="+endDate.getDate()+"&month="+ (endDate.getMonth() + 1)+"&year="+(endDate.getYear() + 1900)+"&hour="+endDate.getHours()+"&min="+endDate.getMinutes()+"&sec="+endDate.getSeconds()+"&p1=";
	}
}
