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
public class Limits {

    private long maxCaseExecutionTime;
    private long maxTotalExecutionTime;
    private long maxMemory;
    private long maxSourceCodeLenght;

    private int languageId;
    private int problemId;
    
    public Limits(){
        this.maxCaseExecutionTime = 1000;
        this.maxTotalExecutionTime = 10000; 
        this.maxSourceCodeLenght = 10000;
        this.maxMemory = 1024*1024*512;
        
    }
    
    public Limits(int languageId,int problemId) {
        this();
        this.languageId = languageId;
        this.problemId = problemId;
    }

    /**
     * @return the maxCaseExecutionTime
     */
    public long getMaxCaseExecutionTime() {
        return maxCaseExecutionTime;
    }

    /**
     * @param maxCaseExecutionTime the maxCaseExecutionTime to set
     */
    public void setMaxCaseExecutionTime(long maxCaseExecutionTime) {
        this.maxCaseExecutionTime = maxCaseExecutionTime;
    }

    /**
     * @return the maxTotalExecutionTime
     */
    public long getMaxTotalExecutionTime() {
        return maxTotalExecutionTime;
    }

    /**
     * @param maxTotalExecutionTime the maxTotalExecutionTime to set
     */
    public void setMaxTotalExecutionTime(long maxTotalExecutionTime) {
        this.maxTotalExecutionTime = maxTotalExecutionTime;
    }

    /**
     * @return the maxMemory
     */
    public long getMaxMemory() {
        return maxMemory;
    }

    /**
     * @param maxMemory the maxMemory to set
     */
    public void setMaxMemory(long maxMemory) {
        this.maxMemory = maxMemory;
    }

    /**
     * @return the maxSourceCodeLenght
     */
    public long getMaxSourceCodeLenght() {
        return maxSourceCodeLenght;
    }

    /**
     * @param maxSourceCodeLenght the maxSourceCodeLenght to set
     */
    public void setMaxSourceCodeLenght(long maxSourceCodeLenght) {
        this.maxSourceCodeLenght = maxSourceCodeLenght;
    }

    /**
     * @return the languageId
     */
    public int getLanguageId() {
        return languageId;
    }

    /**
     * @param languageId the languageId to set
     */
    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    /**
     * @return the problemId
     */
    public int getProblemId() {
        return problemId;
    }

    /**
     * @param problemId the problemId to set
     */
    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

}
