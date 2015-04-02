package cu.uci.coj.model;

/**  
 * No me parece que se este usando" */
@Deprecated
public class Rule {

	private int rid;
	private String rule;

	public Rule() {
	}

	public Rule(int rid, String rule) {
		this.rid = rid;
		this.rule = rule;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

}
