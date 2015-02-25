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

public class ContestStyle {
    private int sid;
    private String name;
    private boolean enabled;

    public ContestStyle() {
    }

    public ContestStyle(int sid, String name, boolean enabled) {
        this.sid = sid;
        this.name = name;
        this.enabled = enabled;
    }

    public ContestStyle(int sid, String name) {
        this.sid = sid;
        this.name = name;
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

}
