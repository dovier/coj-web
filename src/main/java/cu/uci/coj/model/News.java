/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @since 2010-09-01
 * @see http://coj.uci.cu
 */

public class News {

    private int nid;
    private String title;
    private String overview;
    private String content;
    private String username;
    private String date;
    private int rate;
    private boolean enabled;
    private String even;
    private int contest;

    public News() {
    }

    public News(int nid, String title, String overview, String content, String username, String date, int rate, boolean enabled) {
        this.nid = nid;
        this.title = title;
        this.overview = overview;
        this.content = content;
        this.username = username;
        this.date = date;
        this.rate = rate;
        this.enabled = enabled;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEven() {
        return even;
    }

    public void setEven(String even) {
        this.even = even;
    }

    public int getContest() {
        return contest;
    }

    public void setContest(int contest) {
        this.contest = contest;
    }

    public Object[] toArray() {
        return new Object[]{title, overview, content, username, rate, enabled};
    }
}
