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

public class Level {

    private int level;
    private LinkedList<Problem> problems;

    public Level() {
    }

    public Level(int level, LinkedList<Problem> problems) {
        this.level = level;
        this.problems = problems;
    }

    public Level(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public LinkedList<Problem> getProblems() {
        return problems;
    }

    public void setProblems(LinkedList<Problem> problems) {
        this.problems = problems;
    }

}
