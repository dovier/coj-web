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
public class UserProfileRest {
    String avatar;
    String firstname;
    String lastname;
    String username;
    String gender;
    String country;
    String institution;
    String favLanguage;
    String registration;
    String lastSubmission;
    String lastAccepted;
    double score;
    int rankByUser;
    int rankByInstotution;
    int rankByCountry;
    String lastEntry;
    String lastEntryDate;
    int followers;
    int following;

    public UserProfileRest(String avatar, String firstname, String lastname, String username, String gender, String country, String institution, String favLanguage, String registration, String lastSubmission, String lastAccepted, double score, int rankByUser, int rankByInstotution, int rankByCountry, String lastEntry, String lastEntryDate, int followers, int following) {
        this.avatar = avatar;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
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

    public String getAvatar() {
        return avatar;
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
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
