package cu.uci.coj.jcompare;


import cu.uci.coj.jcompare.Caracter;
import java.util.LinkedList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author amado
 */
public class Linea implements Comparable<Linea>{
private String source;
private LinkedList<Caracter> characterlist;
    public Linea(String source) {
        this.source = source;
        characterlist = new LinkedList<Caracter>();
        
    }

    public int compareTo(Linea o) {
       return source.compareTo(o.source);
    }

    public String getSource() {
        return source;
    }

    public LinkedList<Caracter> getCharacterlist() {

        for (int i = 0; i < source.length(); i++) {
            characterlist.add(new Caracter(source.charAt(i)));
        }
        return characterlist;
    }

   
    


}
