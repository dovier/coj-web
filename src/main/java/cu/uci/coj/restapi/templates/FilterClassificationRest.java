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
public class FilterClassificationRest {
    @ApiModelProperty(value = "Identificador de la clasificación", required = true)
    private int idClassification;
    @ApiModelProperty(value = "Clasificación", required = true)
    private String name;

    public FilterClassificationRest() {
    }

    public FilterClassificationRest(int idClassification, String name) {
        this.idClassification = idClassification;
        this.name = name;
    }

    public int getIdClassification() {
        return idClassification;
    }

    public void setIdClassification(int idClassification) {
        this.idClassification = idClassification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
     
     
    
}
