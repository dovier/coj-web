/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.exceptions;

/**
 *
 * @author Leandro
 */
public class PlagiCOJUnprocessedSubmissionException extends Exception {
        
    
    @Override
    public String getMessage(){
        return "The Submissions have not been processed yet";
    }
}
