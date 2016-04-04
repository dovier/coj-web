/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.templates;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author lucy
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JudgmentsRest {
    int id;
    String date;
    String user;
    int prob;
    String judgment;
    Integer errortestcase;
    int time;
    String memory;
    String tam;
    String lang;

    public JudgmentsRest(int id, String date, String user, int prob, String judgment, int errortestcase, int time, String memory, String tam, String lang) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.prob = prob;
        this.judgment = judgment;
        this.errortestcase = errortestcase;
        this.time = time;
        this.memory = memory;
        this.tam = tam;
        this.lang = lang;
    }

    public int getErrortestcase() {
        return errortestcase;
    }

    public void setErrortestcase(int errortestcase) {
        this.errortestcase = errortestcase;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getProb() {
        return prob;
    }

    public void setProb(int prob) {
        this.prob = prob;
    }

    public String getJudgment() {
        return judgment;
    }

    public void setJudgment(String judgment) {
        this.judgment = judgment;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getTam() {
        return tam;
    }

    public void setTam(String tam) {
        this.tam = tam;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    
    
    
   
    
    
    
}
