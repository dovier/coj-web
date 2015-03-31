/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

/**
 *
 * @author nenes
 */
@Deprecated
//unificar con SubmissionJudge
public class SarisSubmission {

    private String nick;
    private String pid;
    private String from_start;
    private boolean success;

    public SarisSubmission() {
    }

    public SarisSubmission(String nick, String pid, String from_start, boolean success) {
        this.nick = nick;
        this.pid = pid;
        this.from_start = from_start;
        this.success = success;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getFrom_start() {
        return from_start;
    }

    public void setFrom_start(String from_start) {
        this.from_start = from_start;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
