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
public class InstitutionDescriptionRest {
    
    String name;
    String code;
    String logo;
    String website;
    String country;
    
    int users;
    double score;
    int rank;
    int rank_in_country;

    public InstitutionDescriptionRest(String name, String code, String logo, String website, String country, int users, double score, int rank, int rank_in_country) {
        this.name = name;
        this.code = code;
        this.logo = logo;
        this.website = website;
        this.country = country;
        this.users = users;
        this.score = score;
        this.rank = rank;
        this.rank_in_country = rank_in_country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public int getRank_in_country() {
        return rank_in_country;
    }

    public void setRank_in_country(int rank_in_country) {
        this.rank_in_country = rank_in_country;
    }
    
    
    
}
