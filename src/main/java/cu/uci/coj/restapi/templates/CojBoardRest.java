/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.templates;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 *
 * @author cesar
 */
@ApiModel
public class CojBoardRest {
    
    @ApiModelProperty(value = "Nombre del sitio web", required = true)
    String name;
    @ApiModelProperty(value = "Sitio web", required = true)
    String site;
    @ApiModelProperty(value = "Dirección de la competencia", required = true)
    String urlcontest;
    @ApiModelProperty(value = "Fecha de inicio", required = true)
    String start;
    @ApiModelProperty(value = "Fecha de fin", required = true)
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
