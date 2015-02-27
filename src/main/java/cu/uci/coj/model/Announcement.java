package cu.uci.coj.model;

import javax.persistence.Table;

public class Announcement {

    private int aid;
    private String content;
    private String username;
    private String date;
    private boolean enabled;
    private String even;
    private int contest;

    public Announcement() {
    }

    public Announcement(int aid, String content, String username, String date, boolean enabled, String even, int contest) {
        this.aid = aid;
        this.content = content;
        this.username = username;
        this.date = date;
        this.enabled = enabled;
        this.even = even;
        this.contest = contest;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getContest() {
        return contest;
    }

    public void setContest(int contest) {
        this.contest = contest;
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

    public String getEven() {
        return even;
    }

    public void setEven(String even) {
        this.even = even;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object[] toArray() {
        return new Object[]{content, enabled, username, contest};
    }

    public Object[] toArrayUpdate() {
        return new Object[]{content, enabled, username, contest, aid};
    }
}
