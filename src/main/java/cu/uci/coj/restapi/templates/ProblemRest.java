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
public class ProblemRest {
    
    int pid;
    String title;
    int sub;
    int ac;
    double acporciento;
    double score;
    
    //Private Atributes of User
    boolean favorite;
    String status;
    
    public ProblemRest(int pid, String title, int sub, int ac, double acporciento, double score) {
        this.pid = pid;
        this.title = title;
        this.sub = sub;
        this.ac = ac;
        this.acporciento = acporciento;
        this.score = score;
        status = "not logged";
    }

    public ProblemRest(int pid, String title, int sub, int ac, double acporciento, double score, boolean favorite, String status) {
        this.pid = pid;
        this.title = title;
        this.sub = sub;
        this.ac = ac;
        this.acporciento = acporciento;
        this.score = score;
        this.favorite = favorite;
        this.status = status;
    }
    
    

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
}
