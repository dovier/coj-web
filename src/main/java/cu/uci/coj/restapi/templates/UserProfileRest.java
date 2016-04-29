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
public class UserProfileRest {
    @ApiModelProperty(value = "Dirección de la imagen", required = true)
    String avatar;
    @ApiModelProperty(value = "Nombre", required = true)
    String firstname;
    @ApiModelProperty(value = "Apellido", required = true)
    String lastname;
    @ApiModelProperty(value = "Nombre de usuario", required = true)
    String username;
    @ApiModelProperty(value = "Apodo", required = true)
    String nickname;
    @ApiModelProperty(value = "Género", required = true)
    String gender;
    @ApiModelProperty(value = "Código del País", required = true)
    String country_code;
    @ApiModelProperty(value = "País", required = true)
    String country_desc;
    @ApiModelProperty(value = "Nombre de la Institución", required = true)
    String institution;
    @ApiModelProperty(value = "Lenguaje Favorito", required = true)
    String favLanguage;
    @ApiModelProperty(value = "Fecha de Registro", required = true)
    String registration;
    @ApiModelProperty(value = "Fecha del último envío", required = true)
    String lastSubmission;
    @ApiModelProperty(value = "Fecha del último envío aceptado", required = true)
    String lastAccepted;
    @ApiModelProperty(value = "Puntuación", required = true)
    double score;
    @ApiModelProperty(value = "Posición en usuarios", required = true)
    int rankByUser;
    @ApiModelProperty(value = "Posición en la institución", required = true)
    int rankByInstotution;
    @ApiModelProperty(value = "Posición en el país", required = true)
    int rankByCountry;
    @ApiModelProperty(value = "Última Entrada", required = true)
    String lastEntry;
    @ApiModelProperty(value = "Fecha de la última entrada", required = true)
    String lastEntryDate;
    @ApiModelProperty(value = "Seguidores", required = true)
    int followers;
    @ApiModelProperty(value = "Siguiendo", required = true)
    int following;
    

    public UserProfileRest(String avatar, String firstname, String lastname, String username, String gender, String country_code,String country_desc, String institution, String favLanguage, String registration, String lastSubmission, String lastAccepted, double score, int rankByUser, int rankByInstotution, int rankByCountry, String lastEntry, String lastEntryDate, int followers, int following) {
        this.avatar = avatar;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.gender = gender;
        this.country_code = country_code;
        this.country_desc = country_desc;
        this.institution = institution;
        this.favLanguage = favLanguage;
        this.registration = registration;
        this.lastSubmission = lastSubmission;
        this.lastAccepted = lastAccepted;
        this.score = score;
        this.rankByUser = rankByUser;
        this.rankByInstotution = rankByInstotution;
        this.rankByCountry = rankByCountry;
        this.lastEntry = lastEntry;
        this.lastEntryDate = lastEntryDate;
        this.followers = followers;
        this.following = following;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country_code;
    }

    public void setCountry(String country) {
        this.country_code = country;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getFavLanguage() {
        return favLanguage;
    }

    public void setFavLanguage(String favLanguage) {
        this.favLanguage = favLanguage;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getLastSubmission() {
        return lastSubmission;
    }

    public void setLastSubmission(String lastSubmission) {
        this.lastSubmission = lastSubmission;
    }

    public String getLastAccepted() {
        return lastAccepted;
    }

    public void setLastAccepted(String lastAccepted) {
        this.lastAccepted = lastAccepted;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getRankByUser() {
        return rankByUser;
    }

    public void setRankByUser(int rankByUser) {
        this.rankByUser = rankByUser;
    }

    public int getRankByInstotution() {
        return rankByInstotution;
    }

    public void setRankByInstotution(int rankByInstotution) {
        this.rankByInstotution = rankByInstotution;
    }

    public int getRankByCountry() {
        return rankByCountry;
    }

    public void setRankByCountry(int rankByCountry) {
        this.rankByCountry = rankByCountry;
    }

    public String getLastEntry() {
        return lastEntry;
    }

    public void setLastEntry(String lastEntry) {
        this.lastEntry = lastEntry;
    }

    public String getLastEntryDate() {
        return lastEntryDate;
    }

    public void setLastEntryDate(String lastEntryDate) {
        this.lastEntryDate = lastEntryDate;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    
   
    
    
    
    
    
    
}
