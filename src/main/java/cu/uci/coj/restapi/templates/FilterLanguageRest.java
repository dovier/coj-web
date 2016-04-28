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
public class FilterLanguageRest {
    @ApiModelProperty(value = "Identificador del Lenguaje", required = true)
    int id;
    @ApiModelProperty(value = "Nombre del Lenguaje", required = true)
    String language_name;
    @ApiModelProperty(value = "Descripción", required = true)
    String description;
    @ApiModelProperty(value = "Identificador en forma de texto", required = true)
    String key;

    public FilterLanguageRest(int id, String language_name, String description, String key) {
        this.id = id;
        this.language_name = language_name;
        this.description = description;
        this.key = key;
    }

    public FilterLanguageRest() {
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage_name() {
        return language_name;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
    
    
    

    
    
    
}
