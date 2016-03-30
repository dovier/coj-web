/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.templates;

/**
 *
 * @author lucy
 */
public class UserRest {
    
    int rank;
    String country;
    String user;
    String status;
    int sub;
    int ac;
    double acporciento;
    double score;

    public UserRest(int rank, String country, String user, String status, int sub, int ac, double acporciento, double score) {
        this.rank = rank;
        this.country = country;
        this.user = user;
        this.status = status;
        this.sub = sub;
        this.ac = ac;
        this.acporciento = acporciento;
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSub() {
        return sub;
    }

    public void setSub(int sub) {
        this.sub = sub;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public double getAcporciento() {
        return acporciento;
    }

    public void setAcporciento(double acporciento) {
        this.acporciento = acporciento;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    
    
    
    
    
    
}
