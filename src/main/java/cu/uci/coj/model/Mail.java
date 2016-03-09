/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @version Caribbean Online Judge(COJ) v2.0
 * @see http://coj.uci.cu
 * @since 2010-09-01
 */
public class Mail {


    private int idmail;
    private int mail_quote;
    private int consumed_quote;
    private String title;
    private String content;
    private String id_from;
    private List<String> to;
    private Date date;
    private boolean isread;
    private int size;
    private double percent;
    private String cclass;
    private boolean send;
    private boolean inmail;
    private String usernameTo;
    private String general;
    private boolean redirectInbox;

    public Mail() {
    }

    public boolean isRedirectInbox() {
        return redirectInbox;
    }

    public void setRedirectInbox(boolean redirectInbox) {
        this.redirectInbox = redirectInbox;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public String getUsernameTo() {
        return usernameTo;
    }

    public void setUsernameTo(String usernameTo) {
        this.usernameTo = usernameTo;
    }

    public String getCclass() {
        return cclass;
    }

    public void setCclass(String cclass) {
        this.cclass = cclass;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public int getIdmail() {
        return idmail;
    }

    public void setIdmail(int idmail) {
        this.idmail = idmail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isIsread() {
        return isread;
    }

    public void setIsread(boolean isread) {

        this.isread = isread;
        initialize();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getConsumed_quote() {
        return consumed_quote;
    }

    public void setConsumed_quote(int consumed_quote) {
        this.consumed_quote = consumed_quote;
    }

    public int getMail_quote() {
        return mail_quote;
    }

    public void setMail_quote(int mail_quote) {
        this.mail_quote = mail_quote;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId_from() {
        return id_from;
    }

    public void setId_from(String id_from) {
        this.id_from = id_from;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTo() {
        return to;
    }

    public String getFirstTo() {
        return to.get(0);
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public void setTo(String to) {
        if (to != null)
            this.to = Arrays.asList(to.split(","));
    }

    public void percent() {
        this.percent = (double) (consumed_quote * 100) / mail_quote;
    }

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

    public void initialize() {
        if (!this.isread) {
            this.cclass = "unread";
        } else {
            this.cclass = "read";
        }
    }

    public boolean isInmail() {
        return inmail;
    }

    public void setInmail(boolean inmail) {
        this.inmail = inmail;
    }
}
