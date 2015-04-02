/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

public class Stats {

	private int lid;
	private int cid;
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

    public Stats() {
    }


    public Stats(int acc, int wa, int pe, int tle, int mle, int fle, int ole, int ce, int rte, int ivf,int uq) {
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
        this.total = acc + wa + rte + tle + mle + ce + fle + pe + ole + uq + ivf;
        if (total != 0) {
            this.acc_percent = percent(acc,total);
            this.wa_percent = percent(wa,total);
            this.rte_percent = percent(rte,total);
            this.tle_percent = percent(tle,total);
            this.mle_percent = percent(mle,total);
            this.ce_percent = percent(ce,total);
            this.fle_percent = percent(fle,total);
            this.pe_percent = percent(pe,total);
            this.ole_percent = percent(ole,total);
            this.uq_percent = percent(uq,total);
            this.ivf_percent = percent(ivf,total);
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

    public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getLid() {
		return lid;
	}


	public void setLid(int lid) {
		this.lid = lid;
	}


	private float percent(float value, float total) {
        float rs = value * 100 / total;
        rs *= 100;
        rs = Math.round(rs);
        rs /= 100;
        return rs;
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

}
