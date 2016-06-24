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
public class InstitutionRest {
    @ApiModelProperty(value = "Identificador de Institución", required = true)
    int inst_id;
    @ApiModelProperty(value = "Posición", required = true)
    int rank;
    @ApiModelProperty(value = "Código del país", required = true)
    String country_code;
    @ApiModelProperty(value = "País", required = true)
    String country_desc;
    @ApiModelProperty(value = "Nombre de Institución", required = true)
    String name;
    @ApiModelProperty(value = "Usuarios", required = true)
    int users;
    @ApiModelProperty(value = "Envíos", required = true)
    int acc;
    @ApiModelProperty(value = "Puntuación", required = true)
    double points;

    public InstitutionRest(int inst_id, int rank, String country_code, String country_desc, String name, int users, int acc, double points) {
        this.inst_id = inst_id;
        this.rank = rank;
        this.country_code = country_code;
        this.country_desc = country_desc;
        this.name = name;
        this.users = users;
        this.acc = acc;
        this.points = points;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public int getAcc() {
        return acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }
    
    

}
