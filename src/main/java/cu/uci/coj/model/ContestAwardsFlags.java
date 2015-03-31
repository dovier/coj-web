package cu.uci.coj.model;

import java.util.List;

public class ContestAwardsFlags {

	private Integer cid;
	private boolean accurate;
	private boolean fast;
	private boolean exclusive;

	public static final int FAST_AWARD = 1;
	public static final int ACCURATE_AWARD = 2;
	public static final int EXCLUSIVE_AWARD = 3;

	public ContestAwardsFlags() {

	}

	public ContestAwardsFlags(Integer cid,List<Integer> aids) {
		this.cid = cid;
		for (Integer aid : aids) {
			switch (aid) {
			case EXCLUSIVE_AWARD:
				exclusive = true;
				break;
			case FAST_AWARD:
				fast = true;
				break;
			case ACCURATE_AWARD:
				accurate = true;
				break;
			}
		}
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public boolean isAccurate() {
		return accurate;
	}

	public void setAccurate(boolean accurate) {
		this.accurate = accurate;
	}

	public boolean isFast() {
		return fast;
	}

	public void setFast(boolean fast) {
		this.fast = fast;
	}

	public boolean isExclusive() {
		return exclusive;
	}

	public void setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
	}

}
