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
public class FilterClassificationRest {
    
     private int idClassification;
     private String name;

    public FilterClassificationRest() {
    }

    public FilterClassificationRest(int idClassification, String name) {
        this.idClassification = idClassification;
        this.name = name;
    }

    public int getIdClassification() {
        return idClassification;
    }

    public void setIdClassification(int idClassification) {
        this.idClassification = idClassification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
     
     
    
}
