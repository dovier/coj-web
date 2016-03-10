/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.templates;

import java.util.List;

/**
 *
 * @author lucy
 */
public class CompareUserRest {
    List<Integer> solvedOnlyByUser1;
    List<Integer> solvedOnlyByBoth;
    List<Integer> solvedOnlyByUser2;
    
    List<Integer> triedOnlyByUser1;
    List<Integer> triedOnlyByBoth;
    List<Integer> triedOnlyByUser2;

    public CompareUserRest(List<Integer> solvedOnlyByUser1, List<Integer> solvedOnlyByBoth, List<Integer> solvedOnlyByUser2, List<Integer> triedOnlyByUser1, List<Integer> triedOnlyByBoth, List<Integer> triedOnlyByUser2) {
        this.solvedOnlyByUser1 = solvedOnlyByUser1;
        this.solvedOnlyByBoth = solvedOnlyByBoth;
        this.solvedOnlyByUser2 = solvedOnlyByUser2;
        this.triedOnlyByUser1 = triedOnlyByUser1;
        this.triedOnlyByBoth = triedOnlyByBoth;
        this.triedOnlyByUser2 = triedOnlyByUser2;
    }

    public List<Integer> getSolvedOnlyByUser1() {
        return solvedOnlyByUser1;
    }

    public void setSolvedOnlyByUser1(List<Integer> solvedOnlyByUser1) {
        this.solvedOnlyByUser1 = solvedOnlyByUser1;
    }

    public List<Integer> getSolvedOnlyByBoth() {
        return solvedOnlyByBoth;
    }

    public void setSolvedOnlyByBoth(List<Integer> solvedOnlyByBoth) {
        this.solvedOnlyByBoth = solvedOnlyByBoth;
    }

    public List<Integer> getSolvedOnlyByUser2() {
        return solvedOnlyByUser2;
    }

    public void setSolvedOnlyByUser2(List<Integer> solvedOnlyByUser2) {
        this.solvedOnlyByUser2 = solvedOnlyByUser2;
    }

    public List<Integer> getTriedOnlyByUser1() {
        return triedOnlyByUser1;
    }

    public void setTriedOnlyByUser1(List<Integer> triedOnlyByUser1) {
        this.triedOnlyByUser1 = triedOnlyByUser1;
    }

    public List<Integer> getTriedOnlyByBoth() {
        return triedOnlyByBoth;
    }

    public void setTriedOnlyByBoth(List<Integer> triedOnlyByBoth) {
        this.triedOnlyByBoth = triedOnlyByBoth;
    }

    public List<Integer> getTriedOnlyByUser2() {
        return triedOnlyByUser2;
    }

    public void setTriedOnlyByUser2(List<Integer> triedOnlyByUser2) {
        this.triedOnlyByUser2 = triedOnlyByUser2;
    }
    
    
    
    
}
