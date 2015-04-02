package cu.uci.coj.ws.models;

import java.util.Date;

public class WsContest {
	int cid;
	String name;
	int registration;
	Date enddate;
	Date initdate;

	public WsContest() {
		// TODO Auto-generated constructor stub
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public int getRegistration() {
		return registration;
	}

	public void setRegistration(int registration) {
		this.registration = registration;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Date getInitdate() {
		return initdate;
	}

	public void setInitdate(Date initdate) {
		this.initdate = initdate;
	}	
}
