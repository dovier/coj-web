/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

import java.io.Serializable;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @since 2010-09-01
 * @see http://coj.uci.cu
 */
@Deprecated
public class Rejudge implements Serializable {

    private String cid;
    private String submitid;
    private String rg1;
    private String rg2;
    private String pid;
    private String status;

    public Rejudge() {
    }

    public Rejudge(String cid) {
        this.cid = cid;
    }

    public Rejudge(String cid, String submitid, String rg1, String rg2, String pid, String status) {
        this.cid = cid;
        this.submitid = submitid;
        this.rg1 = rg1;
        this.rg2 = rg2;
        this.pid = pid;
        this.status = status;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSubmitid() {
        return submitid;
    }

    public void setSubmitid(String submitid) {
        this.submitid = submitid;
    }

    public String getRg1() {
        return rg1;
    }

    public void setRg1(String rg1) {
        this.rg1 = rg1;
    }

    public String getRg2() {
        return rg2;
    }

    public void setRg2(String rg2) {
        this.rg2 = rg2;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
