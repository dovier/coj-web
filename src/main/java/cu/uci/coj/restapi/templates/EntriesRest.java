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
public class EntriesRest {
    @ApiModelProperty(value = "Dirección de la iamgen de avatar", required = true)
    String avatar;
    @ApiModelProperty(value = "Nombre de Usuario", required = true)
    String user;
    @ApiModelProperty(value = "Fecha de creación", required = true)
    String date;
    @ApiModelProperty(value = "Contenido", required = true)
    String content;
    @ApiModelProperty(value = "Puntuación", required = true)
    int rate;

    public EntriesRest(String avatar, String user, String date, String content, int rate) {
        this.avatar = avatar;
        this.user = user;
        this.date = date;
        this.content = content;
        this.rate = rate;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
    
    
    
}
