/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cu.uci.coj.config.Config;

public class Status {

    private String status;
    private String key;
    
    public static Map<String,String> keyToStatus;
    public static Map<String,String> statusToKey;
    
    public Status() {
    }

    public Status(String status, String key) {
        this.status = status;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
