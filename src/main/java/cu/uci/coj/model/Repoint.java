/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

import java.io.Serializable;

/**
 *
 * @author nenes
 */
@Deprecated
public class Repoint implements Serializable{

    private int cid;
    private boolean frozen;

    public Repoint() {
    }

    public Repoint(int cid, boolean frozen) {
        this.cid = cid;
        this.frozen = frozen;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
}
