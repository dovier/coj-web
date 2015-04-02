/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

/**
 *
 * @author Jose Carlos
 */
public class VotesStats {
    
    int pid;
    double vote1_sum;
    double vote1_cant;
    double vote2_sum;
    double vote2_cant;
    double vote3_sum;
    double vote3_cant;
    double vote4_sum;
    double vote4_cant;
    double vote5_sum;
    double vote5_cant;
    
    public VotesStats() {
    }
    
    public VotesStats(int pid,double vote1_sum,double vote1_cant,double vote2_sum,double vote2_cant,double vote3_sum,double vote3_cant,double vote4_sum,double vote4_cant,double vote5_sum,double vote5_cant){
        this.pid = pid;
        this.vote1_sum = vote1_sum;
        this.vote1_cant = vote1_cant;
        this.vote2_sum = vote2_sum;
        this.vote2_cant = vote2_cant;
        this.vote3_sum = vote3_sum;
        this.vote3_cant = vote3_cant;
        this.vote4_sum = vote4_sum;
        this.vote4_cant = vote4_cant;
        this.vote5_sum = vote5_sum;
        this.vote5_cant = vote5_cant;
    }
    
    
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public double getVote1_sum() {
        return vote1_sum;
    }

    public void setVote1_sum(double vote1_sum) {
        this.vote1_sum = vote1_sum;
    }

    public double getVote1_cant() {
        return vote1_cant;
    }

    public void setVote1_cant(double vote1_cant) {
        this.vote1_cant = vote1_cant;
    }

    public double getVote2_sum() {
        return vote2_sum;
    }

    public void setVote2_sum(double vote2_sum) {
        this.vote2_sum = vote2_sum;
    }

    public double getVote2_cant() {
        return vote2_cant;
    }

    public void setVote2_cant(double vote2_cant) {
        this.vote2_cant = vote2_cant;
    }

    public double getVote3_sum() {
        return vote3_sum;
    }

    public void setVote3_sum(double vote3_sum) {
        this.vote3_sum = vote3_sum;
    }

    public double getVote3_cant() {
        return vote3_cant;
    }

    public void setVote3_cant(double vote3_cant) {
        this.vote3_cant = vote3_cant;
    }

    public double getVote4_sum() {
        return vote4_sum;
    }

    public void setVote4_sum(double vote4_sum) {
        this.vote4_sum = vote4_sum;
    }

    public double getVote4_cant() {
        return vote4_cant;
    }

    public void setVote4_cant(double vote4_cant) {
        this.vote4_cant = vote4_cant;
    }

    public double getVote5_sum() {
        return vote5_sum;
    }

    public void setVote5_sum(double vote5_sum) {
        this.vote5_sum = vote5_sum;
    }

    public double getVote5_cant() {
        return vote5_cant;
    }

    public void setVote5_cant(double vote5_cant) {
        this.vote5_cant = vote5_cant;
    }
    
}
