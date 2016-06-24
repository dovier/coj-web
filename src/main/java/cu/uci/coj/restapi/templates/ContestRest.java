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
public class ContestRest {
    @ApiModelProperty(value = "Identificador de la competencia", required = true)
    int id;
    @ApiModelProperty(value = "Tipo de competencia", required = true, allowableValues = "private, public")
    String access;
    @ApiModelProperty(value = "Nombre de la competencia", required = true)
    String name;
    @ApiModelProperty(value = "Fecha de inicio", required = true)
    String start;
    @ApiModelProperty(value = "Fecha de fin", required = true)
    String end;

    public ContestRest(int id, String access, String name, String start, String end) {
        this.id = id;
        this.access = access;
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public ContestRest() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
