package cu.uci.coj.jcompare;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author amado
 */
public class CeldaInformacion {
private int sourceindex;
private int destinationindex;
private Resultado resultado;

    public CeldaInformacion(int sourceindex, int destinationindex, Resultado resultado) {
        this.sourceindex = sourceindex;
        this.destinationindex = destinationindex;
        this.resultado = resultado;
     
    }

    public int getDestinationindex() {
        return destinationindex;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public int getSourceindex() {
        return sourceindex;
    }

    @Override
    public String toString() {
        return sourceindex+" "+destinationindex+" "+resultado;
    }
    

}
