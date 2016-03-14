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
    String urlcontest;
    String start;
    String end;

    public CojBoardRest(String name, String site, String urlcontest, String start, String end) {
        this.name = name;
        this.site = site;
        this.urlcontest = urlcontest;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUrlcontest() {
        return urlcontest;
    }

    public void setUrlcontest(String urlcontest) {
        this.urlcontest = urlcontest;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
    
    

    
    
    
}
