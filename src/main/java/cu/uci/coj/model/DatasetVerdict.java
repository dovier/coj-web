/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.model;

/**
 *
 * @author lan
 */
public class DatasetVerdict {

	//frankr ioi start
	public DatasetVerdict(Integer sid, Integer testnum, String status,
			String message, Long userTime, Long cpuTime, Long memory,
			Verdicts verdict) {
		super();
		this.sid = sid;
		this.testnum = testnum;
		this.status = status;
		this.message = message;
		this.userTime = userTime;
		this.cpuTime = cpuTime;
		this.memory = memory;
		this.verdict = verdict;
	}
	
	public DatasetVerdict(Integer testnum, String status, Long userTime, Long memory) {
		super();
		this.testnum = testnum;
		this.status = status;
		this.userTime = userTime;
		this.memory = memory;
	}
	
	
	public DatasetVerdict() {
		super();
	}	

	private Integer sid;
	private Integer testnum;
	private String status;
	//frankr ioi end
    private String message;
    private Long userTime;
    private Long cpuTime;
    private Long memory;

    private Verdicts verdict;
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the userTime
     */
    public Long getUserTime() {
        return userTime;
    }

    /**
     * @param userTime the userTime to set
     */
    public void setUserTime(Long userTime) {
        this.userTime = userTime;
    }

    /**
     * @return the cpuTime
     */
    public Long getCpuTime() {
        return cpuTime;
    }

    /**
     * @param cpuTime the cpuTime to set
     */
    public void setCpuTime(Long cpuTime) {
        this.cpuTime = cpuTime;
    }

    /**
     * @return the memory
     */
    public Long getMemory() {
        return memory;
    }

    /**
     * @param memory the memory to set
     */
    public void setMemory(Long memory) {
        this.memory = memory;
    }

    /**
     * @return the verdict
     */
    public Verdicts getVerdict() {
        return verdict;
    }

    /**
     * @param verdicts the verdict to set
     */
    public void setVerdict(Verdicts verdicts) {
        this.verdict = verdicts;
    }

	//frankr ioi start
	/**
	 * @return the testnum
	 */
	public Integer getTestnum() {
		return testnum;
	}

	/**
	 * @param testnum the testnum to set
	 */
	public void setTestnum(Integer testnum) {
		this.testnum = testnum;
	}

	/**
	 * @return the sid
	 */
	public Integer getSid() {
		return sid;
	}

	/**
	 * @param sid the sid to set
	 */
	public void setSid(Integer sid) {
		this.sid = sid;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	//frankr ioi end
}
