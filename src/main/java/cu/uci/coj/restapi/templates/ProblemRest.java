/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.templates;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 *
 * @author lucy
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel
public class ProblemRest {
    @ApiModelProperty(value = "Identificador del Problema", required = true)
    Integer pid; 
    
    @ApiModelProperty(value = "Título del Problema", required = true)
    String title;
    
    @ApiModelProperty(value = "Envíos realizados al problema", required = true)
    Integer sub;
    
    @ApiModelProperty(value = "Envíos aceptados al problema", required = true)
    Integer ac;
    
    @ApiModelProperty(value = "Porciento de aceptados", required = true)
    Double acporciento;
    
    @ApiModelProperty(value = "Puntuación del problema", required = true)
    Double score;    
    
    //Private Atributes of User
    @ApiModelProperty(value = "Solo es usado cuando se envía un token de un usuario, devuelve si el problema está marcado como favorito", position = 9)
    Boolean favorite;
    
    @ApiModelProperty(value = "Solo es usado cuando se envía un token de un usuario, devuelve es estado del problema", allowableValues = "not send, solved, unsolved, not logged", position = 10)
    String status;
    
    

    public ProblemRest() {
    }
    
    //Constructor de visualizar los problemas publicos
    public ProblemRest(Integer pid, String title, int sub, int ac, double acporciento, double score) {
        this.pid = pid;
        this.title = title;
        this.sub = sub;
        this.ac = ac;
        this.acporciento = acporciento;
        this.score = score;
    }

    //Constructor de visualizar los problemas privados
    public ProblemRest(Integer pid, String title, int sub, int ac, double acporciento, double score, boolean favorite, String status) {
        this.pid = pid;
        this.title = title;
        this.sub = sub;
        this.ac = ac;
        this.acporciento = acporciento;
        this.score = score;
        this.favorite = favorite;
        this.status = status;
    }

  

   
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSub() {
        return sub;
    }

    public void setSub(Integer sub) {
        this.sub = sub;
    }

    public Integer getAc() {
        return ac;
    }

    public void setAc(Integer ac) {
        this.ac = ac;
    }

    public Double getAcporciento() {
        return acporciento;
    }

    public void setAcporciento(Double acporciento) {
        this.acporciento = acporciento;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
    
    
}
