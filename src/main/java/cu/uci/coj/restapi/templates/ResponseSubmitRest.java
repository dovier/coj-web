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
public class ResponseSubmitRest {
    @ApiModelProperty(value = "Identificador del envío", required = true)
    Integer idSubmission;
    @ApiModelProperty(value = "Identificador del problema", required = true)
    Integer pid;
    @ApiModelProperty(value = "Usuario que realizó el envío", required = true)
    String username;
    @ApiModelProperty(value = "Identificador del lenguaje", required = true)
    String keylanguage;

    public ResponseSubmitRest() {
    }

    public ResponseSubmitRest(Integer idSubmission, Integer pid, String username, String keylanguage) {
        this.idSubmission = idSubmission;
        this.pid = pid;
        this.username = username;
        this.keylanguage = keylanguage;
    }

    public Integer getIdSubmission() {
        return idSubmission;
    }

    public void setIdSubmission(Integer idSubmission) {
        this.idSubmission = idSubmission;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKeylanguage() {
        return keylanguage;
    }

    public void setKeylanguage(String keylanguage) {
        this.keylanguage = keylanguage;
    }
    
    
}
