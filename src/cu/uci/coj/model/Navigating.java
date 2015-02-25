package cu.uci.coj.model;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @since 2010-09-01
 * @see http://coj.uci.cu
 */

public class Navigating {

    private int value;
    private boolean active;

    public Navigating(int value, boolean active) {
        this.value = value;
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
