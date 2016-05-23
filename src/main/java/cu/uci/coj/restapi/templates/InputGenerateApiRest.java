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
public class InputGenerateApiRest {

    @ApiModelProperty(value = "Usuario", required = true)
    String username;
    @ApiModelProperty(value = "Contraseña", required = true)
    String password;

    public InputGenerateApiRest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public InputGenerateApiRest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
