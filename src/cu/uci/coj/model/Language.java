/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 * @see http://coj.uci.cu
 */

public class Language {

    private String language;
    private String name_bin;
    private boolean active_contest;
    private boolean installed;
    private boolean enabled;
    private String descripcion;
    private String key;
    private String ext;
    private int aid;
    private int acc;
    private int wa;
    private int rte;
    private int tle;
    private int mle;
    private int ce;
    private int fle;
    private int pe;
    private int ole;
    private int uq;
    private int ivf;
    private float acc_percent;
    private float wa_percent;
    private float rte_percent;
    private float tle_percent;
    private float mle_percent;
    private float ce_percent;
    private float fle_percent;
    private float pe_percent;
    private float ole_percent;
    private float uq_percent;
    private float ivf_percent;
    private int total;
    private int lid;
    private String even;

    public Language() {
    }
    
    public Language(boolean active_contest, int lid) {
        this.active_contest = active_contest;
        this.lid = lid;
    }

    public Language(String language, int acc, int wa, int pe, int tle, int mle, int fle, int ole, int ce, int rte, int uq, int ivf) {
        this.language = language;
        this.acc = acc;
        this.wa = wa;
        this.rte = rte;
        this.tle = tle;
        this.mle = mle;
        this.ce = ce;
        this.fle = fle;
        this.pe = pe;
        this.ole = ole;
        this.uq = uq;
        this.ivf = ivf;
        percentTotal();
    }
    
    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
    
    public void percentTotal(){
        this.total = acc + wa + rte + tle + mle + ce + fle + pe + ole + uq + ivf;
        if (total != 0) {
            this.acc_percent = acc * 100 / total;
            this.wa_percent = wa * 100 / total;
            this.rte_percent = rte * 100 / total;
            this.tle_percent = tle * 100 / total;
            this.mle_percent = mle * 100 / total;
            this.ce_percent = ce * 100 / total;
            this.fle_percent = fle * 100 / total;
            this.pe_percent = pe * 100 / total;
            this.ole_percent = ole * 100 / total;
            this.uq_percent = uq * 100 / total;
            this.ivf_percent = ivf * 100 / total;
        } else {
            this.acc_percent = 0;
            this.wa_percent = 0;
            this.rte_percent = 0;
            this.tle_percent = 0;
            this.mle_percent = 0;
            this.ce_percent = 0;
            this.fle_percent = 0;
            this.pe_percent = 0;
            this.ole_percent = 0;
            this.uq_percent = 0;
            this.ivf_percent = 0;
        }
    }

    public Language(String language, String key) {
        this.language = language;
        this.key = key;
    }

    public Language(String language, String key, int lid) {
        this.language = language;
        this.key = key;
        this.lid = lid;
    }

    public Language(String language, String descripcion, String key) {
        this.language = language;
        this.descripcion = descripcion;
        this.key = key;
    }

    public Language(String language, boolean active_contest, String descripcion) {
        this.language = language;
        this.active_contest = active_contest;
        this.installed = installed;
        this.descripcion = descripcion;
    }

    public Language(String descripcion, int lid) {
        this.descripcion = descripcion;
        this.lid = lid;
    }

    
    
    public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getAcc() {
        return acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }

    public float getAcc_percent() {
        return acc_percent;
    }

    public void setAcc_percent(float acc_percent) {
        this.acc_percent = acc_percent;
    }

    public int getCe() {
        return ce;
    }

    public void setCe(int ce) {
        this.ce = ce;
    }

    public float getCe_percent() {
        return ce_percent;
    }

    public void setCe_percent(float ce_percent) {
        this.ce_percent = ce_percent;
    }

    public int getFle() {
        return fle;
    }

    public void setFle(int fle) {
        this.fle = fle;
    }

    public float getFle_percent() {
        return fle_percent;
    }

    public void setFle_percent(float fle_percent) {
        this.fle_percent = fle_percent;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getMle() {
        return mle;
    }

    public void setMle(int mle) {
        this.mle = mle;
    }

    public float getMle_percent() {
        return mle_percent;
    }

    public void setMle_percent(float mle_percent) {
        this.mle_percent = mle_percent;
    }

    public int getOle() {
        return ole;
    }

    public void setOle(int ole) {
        this.ole = ole;
    }

    public float getOle_percent() {
        return ole_percent;
    }

    public void setOle_percent(float ole_percent) {
        this.ole_percent = ole_percent;
    }

    public int getPe() {
        return pe;
    }

    public void setPe(int pe) {
        this.pe = pe;
    }

    public float getPe_percent() {
        return pe_percent;
    }

    public void setPe_percent(float pe_percent) {
        this.pe_percent = pe_percent;
    }

    public int getRte() {
        return rte;
    }

    public void setRte(int rte) {
        this.rte = rte;
    }

    public float getRte_percent() {
        return rte_percent;
    }

    public void setRte_percent(float rte_percent) {
        this.rte_percent = rte_percent;
    }

    public int getTle() {
        return tle;
    }

    public void setTle(int tle) {
        this.tle = tle;
    }

    public float getTle_percent() {
        return tle_percent;
    }

    public void setTle_percent(float tle_percent) {
        this.tle_percent = tle_percent;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getUq() {
        return uq;
    }

    public void setUq(int uq) {
        this.uq = uq;
    }

    public float getUq_percent() {
        return uq_percent;
    }

    public void setUq_percent(float uq_percent) {
        this.uq_percent = uq_percent;
    }

    public int getWa() {
        return wa;
    }

    public void setWa(int wa) {
        this.wa = wa;
    }

    public float getWa_percent() {
        return wa_percent;
    }

    public void setWa_percent(float wa_percent) {
        this.wa_percent = wa_percent;
    }

    public String getName_bin() {
        return name_bin;
    }

    public void setName_bin(String name_bin) {
        this.name_bin = name_bin;
    }

    public boolean isActive_contest() {
        return active_contest;
    }

    public void setActive_contest(boolean active_contest) {
        this.active_contest = active_contest;
    }

    public boolean isInstalled() {
        return installed;
    }

    public void setInstalled(boolean installed) {
        this.installed = installed;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public int getIvf() {
        return ivf;
    }

    public void setIvf(int ivf) {
        this.ivf = ivf;
    }

    public float getIvf_percent() {
        return ivf_percent;
    }

    public void setIvf_percent(float ivf_percent) {
        this.ivf_percent = ivf_percent;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEven() {
        return even;
    }

    public void setEven(String even) {
        this.even = even;
    }

}
