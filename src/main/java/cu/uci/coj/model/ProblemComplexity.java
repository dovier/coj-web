//frankr addition start
//para encapsular datos en el comparador por etiquetas
package cu.uci.coj.model;

public class ProblemComplexity implements Comparable<ProblemComplexity> {
	@Override
	public String toString() {
		return "ProblemComplexity [pid=" + pid + ", complexity=" + complexity
				+ ", problemTitle=" + problemTitle + "]";
	}

	public ProblemComplexity(int pid, int complexity, String problemTitle) {
		this.pid = pid;
		this.complexity = complexity;
		this.problemTitle = problemTitle;
	}
	
	public ProblemComplexity(ProblemComplexity pc){
		this.pid = pc.pid;
		this.complexity = pc.complexity;
		this.problemTitle = pc.problemTitle;
	}
	
	public ProblemComplexity(){
		this.pid = this.complexity = -1;
		this.problemTitle = "";
	}

	private int pid;
	private int complexity;
	private String problemTitle;
	
	public int getPid() {
		return pid;
	}
	
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	public int getComplexity() {
		return complexity;
	}

	public void setComplexity(int complexity) {
		this.complexity = complexity;
	}

	public String getProblemTitle() {
		return problemTitle;
	}

	public void setProblemTitle(String problemTitle) {
		this.problemTitle = problemTitle;
	}

	@Override
	public int compareTo(ProblemComplexity pc) {
		return this.pid - pc.pid;
	}
}

//frankr addition end