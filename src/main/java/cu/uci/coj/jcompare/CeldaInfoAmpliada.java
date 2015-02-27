/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.jcompare;

/**
 *
 * @author amado
 */
public class CeldaInfoAmpliada {
private Info<?> source;
private Info<?> destination;
private Resultado result;

    public CeldaInfoAmpliada(Info<?> source, Info<?> destination, Resultado result) {
        this.source = source;
        this.destination = destination;
        this.result = result;
    }

    public Resultado getResult() {
        return result;
    }
    

    public Info<?> getDestination() {
        return destination;
    }

    public Info<?> getSource() {
        return source;
    }

}
