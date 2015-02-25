package cu.uci.coj.jcompare;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author amado
 */
public class Caracter implements Comparable<Caracter> {

    private char caracter;

    public Caracter(char caracter) {
        this.caracter = caracter;
    }

    public int compareTo(Caracter o) {
        if (caracter == o.caracter) {
            return 0;
        }
        return -1;
    }

   
    
}
