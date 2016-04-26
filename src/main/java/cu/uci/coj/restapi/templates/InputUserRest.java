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
public class InputUserRest {
    
    String apikey;
    
    String username;
    
    String password;

    public InputUserRest(String apikey, String username, String password) {
        this.apikey = apikey;
        this.username = username;
        this.password = password;
    }

    public InputUserRest() {
    }

    @ApiModelProperty(value = "LLave de desarrollador", required = true, position = 1)
    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    
    @ApiModelProperty(value = "Nombre de usuario",required = true, position = 2)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ApiModelProperty(value = "Contraseña",required = true, position = 3)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
