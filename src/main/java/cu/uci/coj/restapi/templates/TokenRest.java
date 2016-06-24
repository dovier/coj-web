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
public class TokenRest {
    @ApiModelProperty(value = "Token de usuario", required = true, position = 2)
    String token;
    @ApiModelProperty(value = "Tiempo en que expira el token (ms)", position = 1)
    Long expiredin;

    public TokenRest(String token, Long expiredin) {
        this.token = token;
        this.expiredin = expiredin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpiredin() {
        return expiredin;
    }

    public void setExpiredin(Long expiredin) {
        this.expiredin = expiredin;
    }
    
    

    
    
    
    
}
