/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

import java.util.List;

/**
 *
 * @author leandro
 */
public class UserProfile {
    private String username = null;
    private int uid;
	private Double pearson = 0D;

	private boolean[] tagsSelected;

	public boolean[] getTagsSelected() {
		return tagsSelected;
	}

	public void setTagsSelected(boolean[] tagsSelected) {
		this.tagsSelected = tagsSelected;
	}

	// en realidad son enteros, pero van a ser usados par computaciones de
	// numeros con precision, y es mejor que sean cargados directamente como
	// doubles sin necesidad de convertir
	private List<Double> tags = null;

	public Double getPearson() {
		return pearson;
	}

	public void setPearson(Double pearson) {
		this.pearson = pearson;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userId) {
		this.username = userId;
	}

	public List<Double> getTags() {
		return tags;
	}

	public void setTags(List<Double> tags) {
		this.tags = tags;
	}

    /**
     * @return the uid
     */
    public int getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(int uid) {
        this.uid = uid;
    }
}
