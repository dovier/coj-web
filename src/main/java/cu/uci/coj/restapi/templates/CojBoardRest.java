/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.templates;

/**
 *
 * @author cesar
 */
public class CojBoardRest {
    String name;
    String site;
    String status;
    String start;
    String end;

    public CojBoardRest(String name, String site, String status, String start, String end) {
        this.name = name;
        this.site = site;
        this.status = status;
        this.start = start;
        this.end = end;
    }
    
    
}
