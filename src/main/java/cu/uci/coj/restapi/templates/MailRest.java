/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.templates;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;
import org.junit.Ignore;

/**
 *
 * @author cesar
 */

@ApiModel
public class MailRest {
    
    @ApiModelProperty(value = "Idenificador del correo", required = true)
    int idmail;
    @ApiModelProperty(value = "Asunto del correo", required = true)
    private String title;
    @ApiModelProperty(value = "Contenido del correo", required = true)
    private String content;
    @ApiModelProperty(value = "Enviado por", required = true)
    private String id_from;
    @ApiModelProperty(value = "Lista de receptores", required = true)
    private List<String> to;
    @ApiModelProperty(value = "Fecha en que se recibió", required = true)
    private Date date;
    @ApiModelProperty(value = "Leído o no el correo", required = true)
    private boolean isread;
    
    @JsonIgnore
    private String cclass;

    public MailRest(int idmail, String title, String content, String id_from, List<String> to, Date date, boolean isread, String cclass) {
        this.idmail = idmail;
        this.title = title;
        this.content = content;
        this.id_from = id_from;
        this.to = to;
        this.date = date;
        this.isread = isread;
        this.cclass = cclass;
    }

    public int getIdmail() {
        return idmail;
    }

    public void setIdmail(int idmail) {
        this.idmail = idmail;
    }

    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId_from() {
        return id_from;
    }

    public void setId_from(String id_from) {
        this.id_from = id_from;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isIsread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }

    public String getCclass() {
        return cclass;
    }

    public void setCclass(String cclass) {
        this.cclass = cclass;
    }
    
    
    
    
}
