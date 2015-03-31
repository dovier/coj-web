/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

import java.util.Date;

/**
 *
 * @author nenes
 */
public class Session {

    private Object principal;
    private String sessionid;
    private Date lastRequest;
    private boolean isExpired;

    public Session() {
    }

    public Session(Object principal, String sessionid, Date lastRequest, boolean isExpired) {
        this.principal = principal;
        this.sessionid = sessionid;
        this.lastRequest = lastRequest;
        this.isExpired = isExpired;
    }

    public boolean isIsExpired() {
        return isExpired;
    }

    public void setIsExpired(boolean isExpired) {
        this.isExpired = isExpired;
    }

    public Date getLastRequest() {
        return lastRequest;
    }

    public void setLastRequest(Date lastRequest) {
        this.lastRequest = lastRequest;
    }

    public Object getPrincipal() {
        return principal;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }
}
