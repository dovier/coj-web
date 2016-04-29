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
public class InstitutionDescriptionRest {
    @ApiModelProperty(value = "Nombre de Institución", required = true)
    String name;
    @ApiModelProperty(value = "Código de Institución", required = true)
    String code;
    @ApiModelProperty(value = "Logotipo", required = true)
    String logo;
    @ApiModelProperty(value = "Sitio web", required = true)
    String website;
    @ApiModelProperty(value = "Código de país", required = true)
    String country_code;
    @ApiModelProperty(value = "País", required = true)
    String country_desc;
    
    @ApiModelProperty(value = "Usuarios", required = true)
    int users;
    @ApiModelProperty(value = "Puntuación", required = true)
    double score;
    @ApiModelProperty(value = "Posición", required = true)
    int rank;
    @ApiModelProperty(value = "Posición en el país", required = true)
    int rank_in_country;

    public InstitutionDescriptionRest(String name, String code, String logo, String website, String country_code, String country_desc, int users, double score, int rank, int rank_in_country) {
        this.name = name;
        this.code = code;
        this.logo = logo;
        this.website = website;
        this.country_code = country_code;
        this.country_desc = country_desc;
        this.users = users;
        this.score = score;
        this.rank = rank;
        this.rank_in_country = rank_in_country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public int getRank_in_country() {
        return rank_in_country;
    }

    public void setRank_in_country(int rank_in_country) {
        this.rank_in_country = rank_in_country;
    }
    
    

    
}
