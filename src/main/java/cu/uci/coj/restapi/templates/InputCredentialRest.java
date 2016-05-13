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
public class InputCredentialRest {
    @ApiModelProperty(value = "Llave de desarrollador", required = true)
    String apikey;
    @ApiModelProperty(value = "token de usuario", required = true)
    String token;

    public InputCredentialRest() {
    }

    public InputCredentialRest(String apikey, String token) {
        this.apikey = apikey;
        this.token = token;
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
    
    
}
