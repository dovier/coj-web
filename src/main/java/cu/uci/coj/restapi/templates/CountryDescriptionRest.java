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
public class CountryDescriptionRest {
    @ApiModelProperty(value = "Nombre de país", required = true)
    String name;
    @ApiModelProperty(value = "Código de dos letras", required = true)
    String twocode;
    @ApiModelProperty(value = "Código de tres letras", required = true)
    String threecode;
    @ApiModelProperty(value = "Dirección de la iamgen de la bandera", required = true)
    String flag;
    @ApiModelProperty(value = "Sitio web", required = true)
    String website;
    
    @ApiModelProperty(value = "Instituciones", required = true)
    int institutions;
    @ApiModelProperty(value = "Usuarios", required = true)
    int users;
    @ApiModelProperty(value = "Puntuación", required = true)
    double score;
    @ApiModelProperty(value = "Posición", required = true)
    int rank;

    public CountryDescriptionRest(String name, String twocode, String threecode, String flag, String website, int institutions, int users, double score, int rank) {
        this.name = name;
        this.twocode = twocode;
        this.threecode = threecode;
        this.flag = flag;
        this.website = website;
        this.institutions = institutions;
        this.users = users;
        this.score = score;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    public String getTwocode() {
        return twocode;
    }

    public void setTwocode(String twocode) {
        this.twocode = twocode;
    }

    public String getThreecode() {
        return threecode;
    }

    public void setThreecode(String threecode) {
        this.threecode = threecode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getInstitutions() {
        return institutions;
    }

    public void setInstitutions(int institutions) {
        this.institutions = institutions;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    
    
}
