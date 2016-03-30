/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.templates;

/**
 *
 * @author cesar
 */
public class CountryDescriptionRest {
    
    String name;
    String twocode;
    String threecode;
    String flag;
    String website;
    
    int institutions;
    int users;
    double score;
    int rank;

    public CountryDescriptionRest(String name, String twocode, String threecode, String flag, String website, int institutions, int users, double score, int rank) {
        this.name = name;
        this.twocode = twocode;
        this.threecode = threecode;
        this.flag = flag;
        this.website = website;
        this.institutions = institutions;
        this.users = users;
        this.score = score;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    public String getTwocode() {
        return twocode;
    }

    public void setTwocode(String twocode) {
        this.twocode = twocode;
    }

    public String getThreecode() {
        return threecode;
    }

    public void setThreecode(String threecode) {
        this.threecode = threecode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getInstitutions() {
        return institutions;
    }

    public void setInstitutions(int institutions) {
        this.institutions = institutions;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    
    
}
