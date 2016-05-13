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
 * @author lucy
 */
@ApiModel
public class UserRest {
    @ApiModelProperty(value = "Lugar", required = true)
    int rank;
    @ApiModelProperty(value = "Código de País", required = true)
    String country_code;
    @ApiModelProperty(value = "País", required = true)
    String country_desc;
    @ApiModelProperty(value = "Nombre de usuario", required = true)
    String user;
    @ApiModelProperty(value = "Estado", required = true)
    String status;
    @ApiModelProperty(value = "Envíos", required = true)
    int sub;
    @ApiModelProperty(value = "Aceptados", required = true)
    int ac;
    @ApiModelProperty(value = "Porciento de Aceptados", required = true)
    double acporciento;
    @ApiModelProperty(value = "Puntuación", required = true)
    double score;

    public UserRest(int rank, String country_code, String country_desc, String user, String status, int sub, int ac, double acporciento, double score) {
        this.rank = rank;
        this.country_code = country_code;
        this.country_desc = country_desc;
        this.user = user;
        this.status = status;
        this.sub = sub;
        this.ac = ac;
        this.acporciento = acporciento;
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry_desc() {
        return country_desc;
    }

    public void setCountry_desc(String country_desc) {
        this.country_desc = country_desc;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSub() {
        return sub;
    }

    public void setSub(int sub) {
        this.sub = sub;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public double getAcporciento() {
        return acporciento;
    }

    public void setAcporciento(double acporciento) {
        this.acporciento = acporciento;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    
    
    
    
    
    
}
