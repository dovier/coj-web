/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.model;

import cu.uci.coj.utils.enums.AchievementType;

/**
 *
 * @author jasoria
 */
public class Achievement extends BaseBean{
    
	
    private String legend;
    private String name;
    private String description;
    private String condition;
    private int level;
    private int count;
    
    public boolean getMaxedOut() {
    	return level == AchievementType.achievements[id].levels().length-1;
    }
    
    public int getMaxCount() {
    	return AchievementType.achievements[id].levels()[Math.min(level+1,AchievementType.achievements[id].levels().length-1)];
    }
    
    public int getProgress() {
    	return AchievementType.achievements[id].progress(count);
    }
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getLegend() {
		return legend;
	}

	public void setLegend(String legend) {
		this.legend = legend;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
