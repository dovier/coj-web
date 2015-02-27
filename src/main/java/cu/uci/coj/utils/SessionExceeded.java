/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.utils;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @since 2010-09-01
 * @see http://coj.uci.cu
 */
public class SessionExceeded extends Exception {

    public SessionExceeded() {
    }

    @Override
    public String getMessage() {
        return "Session Limit Reach";
    }

}
