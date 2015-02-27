/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;

/**
 *
 * @author jorge
 */
public class RepointUser implements Serializable {

    int cid;
    boolean frozen;
    int uid;

    public RepointUser(int cid, int uid) {
        this.cid = cid;
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public RepointUser(int cid, boolean frozen, int uid) {
        this.cid = cid;
        this.frozen = frozen;
        this.uid = uid;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public RepointUser() {
    }

    public RepointUser(int cid) {
        this.cid = cid;
    }

    public RepointUser(int cid, boolean frozen) {
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
}
