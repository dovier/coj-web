/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.api.json.model;

import cu.uci.coj.config.Config;
import cu.uci.coj.model.Language;
import java.util.List;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jasoria
 */
public class JsonProblem extends JsonBaseBean{
    
    private String titleEN;
    private String titleEs;
    private String titlePt;
    private String descriptionEN;
    private String descriptionEs;
    private String descriptionPt;
    private String inputEN;
    private String inputEs;
    private String inputPt;
    private String outputEN;
    private String outputEs;
    private String outputPt;
    private String commentsEN;
    private String commentsEs;
    private String commentsPt;
    
    private String inputex;
    private String outputex;
    private String author;
    
    private int submitions;
    private int ac;
    private int accu;
    private double accp;
    
    private int time;
    private int memory;
    private String date;
    private boolean enabled;
    private boolean special_judge;
    
    private List<String> languages;
    
    private String country;
    private String institution;
    private String username;
    
    //statistics
    private int y;
    private String classification;

   
    public String getTitleEN() {
        return titleEN;
    }

    public void setTitleEN(String titleEN) {
        this.titleEN = titleEN;
    }

    public String getTitleEs() {
        return titleEs;
    }

    public void setTitleEs(String titleEs) {
        this.titleEs = titleEs;
    }

    public String getTitlePt() {
        return titlePt;
    }

    public void setTitlePt(String titlePt) {
        this.titlePt = titlePt;
    }

    public String getDescriptionEN() {
        return descriptionEN;
    }

    public void setDescriptionEN(String descriptionEN) {
        this.descriptionEN = descriptionEN;
    }

    public String getDescriptionEs() {
        return descriptionEs;
    }

    public void setDescriptionEs(String descriptionEs) {
        this.descriptionEs = descriptionEs;
    }

    public String getDescriptionPt() {
        return descriptionPt;
    }

    public void setDescriptionPt(String descriptionPt) {
        this.descriptionPt = descriptionPt;
    }

    public String getInputEN() {
        return inputEN;
    }

    public void setInputEN(String inputEN) {
        this.inputEN = inputEN;
    }

    public String getInputEs() {
        return inputEs;
    }

    public void setInputEs(String inputEs) {
        this.inputEs = inputEs;
    }

    public String getInputPt() {
        return inputPt;
    }

    public void setInputPt(String inputPt) {
        this.inputPt = inputPt;
    }

    public String getOutputEN() {
        return outputEN;
    }

    public void setOutputEN(String outputEN) {
        this.outputEN = outputEN;
    }

    public String getOutputEs() {
        return outputEs;
    }

    public void setOutputEs(String outputEs) {
        this.outputEs = outputEs;
    }

    public String getOutputPt() {
        return outputPt;
    }

    public void setOutputPt(String outputPt) {
        this.outputPt = outputPt;
    }

    public String getCommentsEN() {
        return commentsEN;
    }

    public void setCommentsEN(String commentsEN) {
        this.commentsEN = commentsEN;
    }

    public String getCommentsEs() {
        return commentsEs;
    }

    public void setCommentsEs(String commentsEs) {
        this.commentsEs = commentsEs;
    }

    public String getCommentsPt() {
        return commentsPt;
    }

    public void setCommentsPt(String commentsPt) {
        this.commentsPt = commentsPt;
    }

    public String getInputex() {
        return inputex;
    }

    public void setInputex(String inputex) {
        this.inputex = inputex;
    }

    public String getOutputex() {
        return outputex;
    }

    public void setOutputex(String outputex) {
        this.outputex = outputex;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getSubmitions() {
        return submitions;
    }

    public void setSubmitions(int submitions) {
        this.submitions = submitions;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public int getAccu() {
        return accu;
    }

    public void setAccu(int accu) {
        this.accu = accu;
    }

    public double getAccp() {
        return accp;
    }

    public void setAccp(double accp) {
        this.accp = accp;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isSpecial_judge() {
        return special_judge;
    }

    public void setSpecial_judge(boolean special_judge) {
        this.special_judge = special_judge;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

   
    
    
    public double getPoints(){
        return (double) Integer.valueOf(Config.getProperty("formula.value")) / (double) (40 + accu);
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the institution
     */
    public String getInstitution() {
        return institution;
    }

    /**
     * @param institution the institution to set
     */
    public void setInstitution(String institution) {
        this.institution = institution;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the classification
     */
    public String getClassification() {
        return classification;
    }

    /**
     * @param classification the classification to set
     */
    public void setClassification(String classification) {
        this.classification = classification;
    }
}
