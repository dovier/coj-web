package cu.uci.coj.model;

public class ProblemRichTitle implements Comparable<ProblemRichTitle>  {
	
	public ProblemRichTitle(Integer pid, String title, String richTitle) {
		super();
		this.pid = pid;
		this.title = title;
		this.richTitle = richTitle;
	}
	
	public ProblemRichTitle(Integer pid, String title) {
		super();
		this.pid = pid;
		this.title = title;
		this.richTitle = "";
	}
	
	public ProblemRichTitle(Integer pid) {
		super();
		this.pid = pid;
		this.title = "";
		this.richTitle = "";
	}
	
	public ProblemRichTitle() {
		super();
		this.pid = 0;
		this.title = "";
		this.richTitle = "";
	}			

	private Integer pid;
	private String title; 
	private String richTitle;
	
	/*
	 private List<Classification> classifications;
	 */
	
	@Override
	public int compareTo(ProblemRichTitle p) {
		return this.pid - p.pid;
	}

	/**
	 * @return the pid
	 */
	public Integer getPid() {
		return pid;
	}

	/**
	 * @param pid the pid to set
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the richTitle
	 */
	public String getRichTitle() {
		return title + richTitle;
	}

	/**
	 * @param richTitle the richTitle to set
	 */
	public void setRichTitle(String richTitle) {
		this.richTitle = richTitle;
	}
	
}
