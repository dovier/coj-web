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
public class StadisticsRest {
    
    @ApiModelProperty(value = "Lenguaje", required = true)
    String pattern;
    @ApiModelProperty(value = "Accepted", required = true)
    int ac;
    @ApiModelProperty(value = "Compilation Error", required = true)
    int ce;
    @ApiModelProperty(value = "Invalid Function", required = true)
    int ivf;
    @ApiModelProperty(value = "Memory Limit Exceeded", required = true)
    int mle;
    @ApiModelProperty(value = "Output Limit Exceeded", required = true)
    int ole;
    @ApiModelProperty(value = "Presentation Error", required = true)
    int pe;
    @ApiModelProperty(value = "Runtime Error", required = true)
    int rte;
    @ApiModelProperty(value = "Time Limit Exceeded", required = true)
    int tle;
    @ApiModelProperty(value = "Wrong Answer", required = true)
    int wa;
    @ApiModelProperty(value = "Total de envíos", required = true)
    int total;

    public StadisticsRest(String pattern, int ac, int ce, int ivf, int mle, int ole, int pe, int rte, int tle, int wa, int total) {
        this.pattern = pattern;
        this.ac = ac;
        this.ce = ce;
        this.ivf = ivf;
        this.mle = mle;
        this.ole = ole;
        this.pe = pe;
        this.rte = rte;
        this.tle = tle;
        this.wa = wa;
        this.total = total;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public int getCe() {
        return ce;
    }

    public void setCe(int ce) {
        this.ce = ce;
    }

    public int getIvf() {
        return ivf;
    }

    public void setIvf(int ivf) {
        this.ivf = ivf;
    }

    public int getMle() {
        return mle;
    }

    public void setMle(int mle) {
        this.mle = mle;
    }

    public int getOle() {
        return ole;
    }

    public void setOle(int ole) {
        this.ole = ole;
    }

    public int getPe() {
        return pe;
    }

    public void setPe(int pe) {
        this.pe = pe;
    }

    public int getRte() {
        return rte;
    }

    public void setRte(int rte) {
        this.rte = rte;
    }

    public int getTle() {
        return tle;
    }

    public void setTle(int tle) {
        this.tle = tle;
    }

    public int getWa() {
        return wa;
    }

    public void setWa(int wa) {
        this.wa = wa;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}