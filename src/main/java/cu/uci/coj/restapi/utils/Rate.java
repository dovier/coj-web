/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cesar
 */
@Service
public class Rate {
    
    private static Rate rate;
    
    private Map<String, Integer> host_contador;
    private Map<String, Long> host_time;
    
    public Rate() {
        host_contador = new HashMap();
        host_time = new HashMap();
    }
    
    public static Rate getIsntance(){
        if(rate == null)
            rate = new Rate();
        
        return rate;
    }

    public Map<String, Integer> getHost_contador() {
        return host_contador;
    }

    public void setHost_contador(Map<String, Integer> host_contador) {
        this.host_contador = host_contador;
    }

    public Map<String, Long> getHost_time() {
        return host_time;
    }

    public void setHost_time(Map<String, Long> host_time) {
        this.host_time = host_time;
    }

    
    
}
