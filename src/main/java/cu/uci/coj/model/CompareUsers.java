/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

import java.util.LinkedList;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @since 2010-09-01
 * @see http://coj.uci.cu
 */

public class CompareUsers {

    private String user1;
    private String user2;
    LinkedList<Problem> facc;
    LinkedList<Problem> sacc;
    LinkedList<Problem> bacc;
    LinkedList<Problem> ff;
    LinkedList<Problem> bf;
    LinkedList<Problem> sf;
    LinkedList<Problem> faccs;
    LinkedList<Problem> saccf;
    private int ft;
    private int bt;
    private int st;
    private int fft;
    private int bft;
    private int sft;
    private int fos;
    private int sof;

    public CompareUsers() {
    }

    public CompareUsers(String user1, String user2, LinkedList<Problem> facc, LinkedList<Problem> sacc, LinkedList<Problem> bacc) {
        this.user1 = user1;
        this.user2 = user2;
        this.facc = facc;
        this.sacc = sacc;
        this.bacc = bacc;
    }

    public CompareUsers(String user1, String user2, LinkedList<Problem> facc, LinkedList<Problem> sacc, LinkedList<Problem> bacc, LinkedList<Problem> ff, LinkedList<Problem> bf, LinkedList<Problem> sf) {
        this.user1 = user1;
        this.user2 = user2;
        this.facc = facc;
        this.sacc = sacc;
        this.bacc = bacc;
        this.ff = ff;
        this.bf = bf;
        this.sf = sf;
    }

    public CompareUsers(String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.facc = new LinkedList<Problem>();
        this.sacc = new LinkedList<Problem>();
        this.bacc = new LinkedList<Problem>();
        this.ff = new LinkedList<Problem>();
        this.bf = new LinkedList<Problem>();
        this.sf = new LinkedList<Problem>();
        this.faccs = new LinkedList<Problem>();
        this.saccf = new LinkedList<Problem>();
    }

    public LinkedList<Problem> getBacc() {
        return bacc;
    }

    public void setBacc(LinkedList<Problem> bacc) {
        this.bacc = bacc;
    }

    public LinkedList<Problem> getFacc() {
        return facc;
    }

    public void setFacc(LinkedList<Problem> facc) {
        this.facc = facc;
    }

    public LinkedList<Problem> getSacc() {
        return sacc;
    }

    public void setSacc(LinkedList<Problem> sacc) {
        this.sacc = sacc;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public int getBt() {
        return bt;
    }

    public void setBt(int bt) {
        this.bt = bt;
    }

    public int getFt() {
        return ft;
    }

    public void setFt(int ft) {
        this.ft = ft;
    }

    public int getSt() {
        return st;
    }

    public void setSt(int st) {
        this.st = st;
    }

    public LinkedList<Problem> getBf() {
        return bf;
    }

    public void setBf(LinkedList<Problem> bf) {
        this.bf = bf;
    }

    public LinkedList<Problem> getFf() {
        return ff;
    }

    public void setFf(LinkedList<Problem> ff) {
        this.ff = ff;
    }

    public LinkedList<Problem> getSf() {
        return sf;
    }

    public void setSf(LinkedList<Problem> sf) {
        this.sf = sf;
    }

    public int getBft() {
        return bft;
    }

    public void setBft(int bft) {
        this.bft = bft;
    }

    public int getFft() {
        return fft;
    }

    public void setFft(int fft) {
        this.fft = fft;
    }

    public int getSft() {
        return sft;
    }

    public void setSft(int sft) {
        this.sft = sft;
    }

    public LinkedList<Problem> getFaccs() {
        return faccs;
    }

    public void setFaccs(LinkedList<Problem> faccs) {
        this.faccs = faccs;
    }

    public int getFos() {
        return fos;
    }

    public void setFos(int fos) {
        this.fos = fos;
    }

    public LinkedList<Problem> getSaccf() {
        return saccf;
    }

    public void setSaccf(LinkedList<Problem> saccf) {
        this.saccf = saccf;
    }

    public int getSof() {
        return sof;
    }

    public void setSof(int sof) {
        this.sof = sof;
    }

    public void putCants() {
        ft = facc.size();
        bt = bacc.size();
        st = sacc.size();
        fft = ff.size();
        bft = bf.size();
        sft = sf.size();
        fos = faccs.size();
        sof = saccf.size();
    }

}
