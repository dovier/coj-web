/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.templates;

/**
 *
 * @author cesar
 */
public class UserPerfilRest {
    String firstname;
    String lastname;
    String username;
    String email;
    String gender;
    String country;
    String institution;
    String favLanguage;
    String registration;
    String lastSubmission;
    String lastAccepted;
    String score;
    String rankByUser;
    String rankByInstotution;
    String rankByCountry;
    String lastEntry;
    String lastEntryDate;
    String followers;
    String following;

    public UserPerfilRest(String firstname, String lastname, String username, String email, String gender, String country, String institution, String favLanguage, String registration, String lastSubmission, String lastAccepted, String score, String rankByUser, String rankByInstotution, String rankByCountry, String lastEntry, String lastEntryDate, String followers, String following) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.country = country;
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
    
    
    
    
    
    
    
}
