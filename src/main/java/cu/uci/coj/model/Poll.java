package cu.uci.coj.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Poll {
	private Integer pid;
	private String question;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String answer5;
	private boolean enabled;
	
	private boolean voted;
	private int votes1;
	private int votes2;
	private int votes3;
	private int votes4;
	private int votes5;
	private DecimalFormat df = new DecimalFormat("##.##");
	
	private String format(int vote) {
		return df.format(vote*100/Math.max(getVoteTotal(),1));
	}
	
	public String getVote1PC() {
		return format(votes1);
	}
	public String getVote2PC() {
		return format(votes2);
	}
	public String getVote3PC() {
		return format(votes3);
	}
	public String getVote4PC() {
		return format(votes4);
	}
	public String getVote5PC() {
		return format(votes5);
	}
	
	public int getVoteTotal() {
		return votes1+votes2+votes3+votes4+votes5;
	}
	
	
	public boolean isVoted() {
		return voted;
	}
	public void setVoted(boolean voted) {
		this.voted = voted;
	}
	public int getVotes1() {
		return votes1;
	}
	public void setVotes1(int votes1) {
		this.votes1 = votes1;
	}
	public int getVotes2() {
		return votes2;
	}
	public void setVotes2(int votes2) {
		this.votes2 = votes2;
	}
	public int getVotes3() {
		return votes3;
	}
	public void setVotes3(int votes3) {
		this.votes3 = votes3;
	}
	public int getVotes4() {
		return votes4;
	}
	public void setVotes4(int votes4) {
		this.votes4 = votes4;
	}
	public int getVotes5() {
		return votes5;
	}
	public void setVotes5(int votes5) {
		this.votes5 = votes5;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	public String getAnswer4() {
		return answer4;
	}
	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}
	public String getAnswer5() {
		return answer5;
	}
	public void setAnswer5(String answer5) {
		this.answer5 = answer5;
	}
	
}
