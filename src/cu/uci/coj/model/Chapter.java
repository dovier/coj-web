/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author nenes
 */
public class Chapter {

    String name;
    Date initdate;
    List<Problem> problems;
    List<ChapterContent> materials;
    boolean enabled;
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Chapter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getInitdate() {
        return initdate;
    }

    public void setInitdate(Date initdate) {
        this.initdate = initdate;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public List<ChapterContent> getMaterials() {
        return materials;
    }

    public void setMaterials(List<ChapterContent> materials) {
        this.materials = materials;
    }
}
