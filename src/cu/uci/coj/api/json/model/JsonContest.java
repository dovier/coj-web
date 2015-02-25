/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.api.json.model;

import cu.uci.coj.model.BaseBean;
import java.util.Date;
import net.sf.json.JSONObject;

/**
 *
 * @author jasoria
 */
public class JsonContest extends JsonBaseBean{
    private String name;
    private String user;
    private int cstats;
    private int pid; 
    private String state;
    private String date;
    private String startDate;
    private String endDate;
    private boolean access;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the cstats
     */
    public int getCstats() {
        return cstats;
    }

    /**
     * @param cstats the cstats to set
     */
    public void setCstats(int cstats) {
        this.cstats = cstats;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the pid
     */
    public int getPid() {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(int pid) {
        this.pid = pid;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }
   
    
}
