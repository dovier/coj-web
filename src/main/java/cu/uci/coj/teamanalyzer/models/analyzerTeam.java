/*
* analyzerTeam.java
*
* v1.0
*
* 14/05/2016
*/

package cu.uci.coj.teamanalyzer.models;

public class analyzerTeam {

    private int id;
    private int uid1;
    private int uid2;
    private int uid3;

    private String username1;
    private String username2;
    private String username3;

    private int taid;

    public analyzerTeam() {
    }

    public analyzerTeam(int id, int uid1, int uid2, int uid3, int taid) {
        this.id = id;
        this.uid1 = uid1;
        this.uid2 = uid2;
        this.uid3 = uid3;
        this.taid = taid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid1() {
        return uid1;
    }

    public void setUid1(int uid1) {
        this.uid1 = uid1;
    }

    public int getUid2() {
        return uid2;
    }

    public void setUid2(int uid2) {
        this.uid2 = uid2;
    }

    public int getUid3() {
        return uid3;
    }

    public void setUid3(int uid3) {
        this.uid3 = uid3;
    }

    public int getTaid() {
        return taid;
    }

    public void setTaid(int taid) {
        this.taid = taid;
    }

    public String getUsername3() {
        return username3;
    }

    public void setUsername3(String username3) {
        this.username3 = username3;
    }

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }
}
