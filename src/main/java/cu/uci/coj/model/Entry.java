package cu.uci.coj.model;

import java.util.Date;


public class Entry extends BaseBean {

    private int rate;
    private String text;
    private int uid;
    private String username;
    private Date date;
    private boolean adminEnabled = false;
    private boolean voted = false;
    private boolean reply;
    private boolean forward;
    private boolean hasLinks;
    private boolean hasUsers;
    
    
  
    public boolean isReply() {
		return reply;
	}




	public void setReply(boolean reply) {
		this.reply = reply;
	}




	public boolean isForward() {
		return forward;
	}




	public void setForward(boolean forward) {
		this.forward = forward;
	}




	public boolean isHasLinks() {
		return hasLinks;
	}




	public void setHasLinks(boolean hasLinks) {
		this.hasLinks = hasLinks;
	}




	public boolean isHasUsers() {
		return hasUsers;
	}




	public void setHasUsers(boolean hasUsers) {
		this.hasUsers = hasUsers;
	}




	public Entry() {
    }

    
    
    
    public Entry(String text, Date date) {
		super();
		this.text = text;
		this.date = date;
	}




	public boolean isVoted() {
		return voted;
	}



	public void setVoted(boolean voted) {
		this.voted = voted;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public boolean isAdminEnabled() {
        return adminEnabled;
    }

    public void setAdminEnabled(boolean adminEnabled) {
        this.adminEnabled = adminEnabled;
    }

    
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
    

    public String getText() {
		return text;
	}

	public void setText(String text) {
		String[] arr = text.split(" ");
		text.indexOf("http://");
		this.text = text;
	}

	public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
