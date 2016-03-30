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
public class InstitutionRest {
    
    int inst_id;
    int rank;
    String cname;
    String name;
    int users;
    int acc;
    double points;

    public InstitutionRest(int inst_id, int rank, String cname, String name, int users, int acc, double points) {
        this.inst_id = inst_id;
        this.rank = rank;
        this.cname = cname;
        this.name = name;
        this.users = users;
        this.acc = acc;
        this.points = points;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public int getAcc() {
        return acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }
    
    
}
