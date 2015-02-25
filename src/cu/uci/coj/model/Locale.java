/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.model;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @since 2010-09-01
 * @see http://coj.uci.cu
 */

public class Locale {
    private int lid;
    private String description;
    private String i18n;

    public Locale() {
    }

    public Locale(int lid, String description, String i18n) {
        this.lid = lid;
        this.description = description;
        this.i18n = i18n;
    }

    public Locale(int lid, String description) {
        this.lid = lid;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getI18n() {
        return i18n;
    }

    public void setI18n(String i18n) {
        this.i18n = i18n;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }



}
