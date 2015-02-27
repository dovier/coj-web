/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

import cu.uci.coj.model.User;
import java.util.Date;

/**
 *
 * @author Leandro
 */
public class PlagiCOJRevision {

    private String comment;
    private String userId;
    private Date date;
    private int revisionId;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PlagiCOJRevision(String userId, String comment) {
        this.comment = comment;
        this.userId = userId;
    }

    public PlagiCOJRevision(String userId, Date date, String comment) {
        this.comment = comment;
        this.userId = userId;
        this.date = date;
    }

    public PlagiCOJRevision(int revisionId, String userId, Date date, String comment) {
        this.comment = comment;
        this.revisionId = revisionId;

        this.userId = userId;
        this.date = date;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the idRevision
     */
    public int getRevisionId() {
        return revisionId;
    }

    /**
     * @param idRevision the idRevision to set
     */
    public void setRevisionId(int revisionId) {
        this.revisionId = revisionId;
    }
}