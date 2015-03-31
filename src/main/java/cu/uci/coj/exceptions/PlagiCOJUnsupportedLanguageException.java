/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.exceptions;

/**
 *
 * @author Leandro
 */
public class PlagiCOJUnsupportedLanguageException extends Exception {
        
    
    @Override
    public String getMessage(){
        return "The Language is not supported yet";
    }
}
