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
public class InputMailSend {
    @ApiModelProperty(value = "Llave de desarrollador", required = true)
    String apikey;
    @ApiModelProperty(value = "Token de usuario", required = true)
    String token;
    @ApiModelProperty(value = "Remitente", required = true)
    String to;
    @ApiModelProperty(value = "Asunto", required = true)
    String subject;
    @ApiModelProperty(value = "Contenido", required = true)
    String content;

    public InputMailSend() {
    }

    public InputMailSend(String apikey, String token, String to, String subject, String content) {
        this.apikey = apikey;
        this.token = token;
        this.to = to;
        this.subject = subject;
        this.content = content;
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

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
    
    
            
}
