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
public class ApiRest {
    @ApiModelProperty(value = "LLave de desarrollador", required = true, position = 2)
    String apikey;
    @ApiModelProperty(value = "Tiempo en que expira la apikey (ms)", position = 1)
    Long expiredin;

    public ApiRest(String apikey, Long expiredin) {
        this.apikey = apikey;
        this.expiredin = expiredin;
    }

    public ApiRest() {
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public Long getExpiredin() {
        return expiredin;
    }

    public void setExpiredin(Long expiredin) {
        this.expiredin = expiredin;
    }

   
    

    
    
    
    
}
