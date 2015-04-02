package cu.uci.coj.model;

import java.util.Date;

public class Translation {
	private int id;
	private int pid;
	private String username;
	private Date date;
	private String title;
	private String description;
	private String input;
	private String output;
	private String comments;
	private String locale;
	
	
	public Translation() {
		super();
	}	
	public Translation(int id, int pid, String username, Date date, String title, String description, String input, String output, String locale) {
		super();
		this.id = id;
		this.pid = pid;
		this.username = username;
		this.date = date;
		this.title = title;
		this.description = description;
		this.input = input;
		this.output = output;
		this.locale = locale;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	
	
}
