/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

public class ProblemClassification implements Comparable<Object> {

    private String name;
    private int idClassification;
    private int estimatedCodeLength;
    private int complexity;
    private int aid;
    
    

    public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public ProblemClassification(String role, int idClassification) {
        this.name = role;
        this.idClassification = idClassification;

    }

    public ProblemClassification(String role, int idClassification, int estimatedCodeLength) {
        this(role, idClassification);
        this.estimatedCodeLength = estimatedCodeLength;
    }

    public ProblemClassification() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the idClassification
     */
    public int getIdClassification() {
        return idClassification;
    }

    /**
     * @param idClassification the idClassification to set
     */
    public void setIdClassification(int idClassification) {
        this.idClassification = idClassification;
    }

    /**
     * @return the estimatedCodeLength
     */
    public int getEstimatedCodeLength() {
        return estimatedCodeLength;
    }

    /**
     * @param estimatedCodeLength the estimatedCodeLength to set
     */
    public void setEstimatedCodeLength(int estimatedCodeLength) {
        this.estimatedCodeLength = estimatedCodeLength;
    }

    public int compareTo(Object o) {
        return idClassification - ((ProblemClassification) o).idClassification;
    }
    
    @Override
    public String toString(){
        return this.name;
    }

    /**
     * @return the complexity
     */
    public int getComplexity() {
        return complexity;
    }

    /**
     * @param complexity the complexity to set
     */
    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }
}