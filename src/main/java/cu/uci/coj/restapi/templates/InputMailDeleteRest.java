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
public class InputMailDeleteRest {
    @ApiModelProperty(value = "Llave de desarrollador", required = true)
    String apikey;
    @ApiModelProperty(value = "Token de usuario", required = true)
    String token;
    @ApiModelProperty(value = "Bandeja donde se encuentra el correo", required = true, allowableValues = "inbox,outbox,draft")
    String where;

    public InputMailDeleteRest() {
    }

    public InputMailDeleteRest(String apikey, String token, String where) {
        this.apikey = apikey;
        this.token = token;
        this.where = where;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }
    
          
    
}
