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
public class ProblemContestRest {
    @ApiModelProperty(value = "Identificador Númerico del Problema", required = true)
    Integer pid; 
    
    @ApiModelProperty(value = "Identificador Alfabético", required = false)
    String letter;
    
    @ApiModelProperty(value = "Color del globo", required = true, position = 2)
    String ballon;
    
    @ApiModelProperty(value = "Título del Problema", required = true, position = 3)
    String title;
    
    @ApiModelProperty(value = "Envíos aceptados al problema", required = true, position = 5)
    Integer ac;

    @ApiModelProperty(value = "Nivel del problema", position = 8)
    Integer level;
    
    @ApiModelProperty(value = "Puntuación del problema", required = true)
    Double score;
    
    
    public ProblemContestRest() {
    }
    
    //Construvtor para los problemas de competencia con pid
    public ProblemContestRest(Integer pid, String ballon, String title, int ac, Integer level) {
        this.pid = pid;
        this.ballon = ballon;
        this.title = title;
        this.ac = ac;
        this.level = level;
    }
    //Construvtor para los problemas de competencia con pid y letra
    public ProblemContestRest(Integer pid, String letter, String ballon, String title, Integer ac, Integer level) {
        this.pid = pid;
        this.letter = letter;
        this.ballon = ballon;
        this.title = title;
        this.ac = ac;
        this.level = level;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getBallon() {
        return ballon;
    }

    public void setBallon(String ballon) {
        this.ballon = ballon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAc() {
        return ac;
    }

    public void setAc(Integer ac) {
        this.ac = ac;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
    
    
    
    
    
    
    
    
}
