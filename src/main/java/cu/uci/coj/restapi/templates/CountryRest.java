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
public class CountryRest {
    
     int country_id;
     int rank;
     String name;
     int institutions;
     int users;
     int acc;     
     double points;

    public CountryRest(int country_id, int rank, String name, int institutions, int users, int acc, double points) {
        this.country_id = country_id;
        this.rank = rank;
        this.name = name;
        this.institutions = institutions;
        this.users = users;
        this.acc = acc;
        this.points = points;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
