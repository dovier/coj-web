/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.utils;

/**
 *
 * @author cesar
 */
public class InputProblem {
    String token;
    String apikey;
    String pattern;
    Integer filterby;
    Integer classification;
    Integer complexity;

    public InputProblem() {
    }

    public InputProblem(String token, String apikey, String pattern, Integer filterby, Integer classification, Integer complexity) {
        this.token = token;
        this.apikey = apikey;
        this.pattern = pattern;
        this.filterby = filterby;
        this.classification = classification;
        this.complexity = complexity;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Integer getFilterby() {
        return filterby;
    }

    public void setFilterby(Integer filterby) {
        this.filterby = filterby;
    }

    public Integer getClassification() {
        return classification;
    }

    public void setClassification(Integer classification) {
        this.classification = classification;
    }

    public Integer getComplexity() {
        return complexity;
    }

    public void setComplexity(Integer complexity) {
        this.complexity = complexity;
    }
    
    
}