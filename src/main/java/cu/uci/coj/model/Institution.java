/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.model;

import cu.uci.coj.controller.interfaces.CommonScoreboardInterface;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @since 2010-09-01
 * @see http://coj.uci.cu
 */

public class Institution implements CommonScoreboardInterface{

    private int id;
    private int country_id;
    private String zip;
    private String name;    
    private String czip;
    private String cname;
    private int users;
    private int acc;
    private double points;
    private String even;
    private boolean hasmedal;
    private String medal;
    private int rank;
    private int rankincountry;
    private int count;
    private boolean enabled;
    private String website;

    public Institution() {
    }

    public Institution(String zip, String name, String czip, String cname, int users, int acc, double points) {
        this.zip = zip;
        this.name = name;
        this.czip = czip;
        this.cname = cname;
        this.users = users;
        this.acc = acc;
        this.points = points;
    }

    public Institution(int id, String zip, String name) {
        this.id = id;
        this.zip = zip;
        this.name = name;
    }


    public Institution(int rank, String zip, String name, String czip, String cname, int users, int acc, double points, String even,int inst_id) {
        this.rank = rank;
        this.zip = zip;
        this.name = name;
        this.czip = czip;
        this.cname = cname;
        this.users = users;
        this.acc = acc;
        this.points = Math.round(points * 100);
        this.points /= 100;
        this.even = even;
        this.id = inst_id;
    }
    
    public Institution(int rank, String czip, String cname, int users, int acc, double points, String even, int id) {
        this.rank = rank;
        this.czip = czip;
        this.cname = cname;
        this.users = users;
        this.acc = acc;
        this.points = Math.round(points * 100);
        this.points /= 100;
        this.even = even;
        this.id = id;
    }

    public Institution(int id, int country_id, String name) {
        this.id = id;
        this.country_id = country_id;
        this.name = name;
    }



    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAcc() {
        return acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCzip() {
        return czip;
    }

    public void setCzip(String czip) {
        this.czip = czip;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public String getEven() {
        return even;
    }

    public void setEven(String even) {
        this.even = even;
    }

    public boolean isHasmedal() {
        return hasmedal;
    }

    public void setHasmedal(boolean hasmedal) {
        this.hasmedal = hasmedal;
    }

    public String getMedal() {
        return medal;
    }

    public void setMedal(String medal) {
        this.medal = medal;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Object[] toArray() {
        return new Object[]{name, zip, enabled, country_id, website, id,};
    }

    public String getWebsite() {
        return website;
}

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getRankincountry() {
        return rankincountry;
    }

    public void setRankincountry(int rankincountry) {
        this.rankincountry = rankincountry;
    }
}
