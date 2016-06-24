/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.templates;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 *
 * @author cesar
 */
public class InputForgotPasswordRest {
    @ApiModelProperty(value = "Llave de desarrollador", required = true)
    String apikey;
    @ApiModelProperty(value = "Correo electrónico", required = true)
    String email;

    public InputForgotPasswordRest(String apikey, String email) {
        this.apikey = apikey;
        this.email = email;
    }

    public InputForgotPasswordRest() {
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
}
