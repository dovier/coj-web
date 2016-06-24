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
public class InputSubmitRest {
    
    @ApiModelProperty(value = "Llave de desarrollador", required = true)
    String apikey;
    @ApiModelProperty(value = "Token de usuario", required = true)
    String token;
    @ApiModelProperty(value = "Identificador del problema", required = true)
    Integer pid;
    @ApiModelProperty(value = "Identificador en forma de cadena de texto del lenguaje (key)", required = true)
    String keylanguage;
    @ApiModelProperty(value = "Código fuente de la solución", required = true)
    String source;

    public InputSubmitRest() {
    }

    public InputSubmitRest(String apikey, String token, Integer pid, String keylanguage, String source) {
        this.apikey = apikey;
        this.token = token;
        this.pid = pid;
        this.keylanguage = keylanguage;
        this.source = source;
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getKeylanguage() {
        return keylanguage;
    }

    public void setKeylanguage(String keylanguage) {
        this.keylanguage = keylanguage;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    
    
}
