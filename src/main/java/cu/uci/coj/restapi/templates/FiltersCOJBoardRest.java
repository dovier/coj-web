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
public class FiltersCOJBoardRest {
    @ApiModelProperty(value = "Identificador del Juez en Línea", required = true)
    int sid;
    @ApiModelProperty(value = "Dirección del sitio web", required = true)
    String site;

    public FiltersCOJBoardRest(int sid, String site) {
        this.sid = sid;
        this.site = site;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
    
    
}
