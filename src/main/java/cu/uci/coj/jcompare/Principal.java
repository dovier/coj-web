/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.jcompare;

import static cu.uci.coj.jcompare.Resultado.DestinationNull;
import static cu.uci.coj.jcompare.Resultado.Diferent;
import static cu.uci.coj.jcompare.Resultado.Equal;
import static cu.uci.coj.jcompare.Resultado.SourceNull;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author adrian
 */
public class Principal {

    public String getTemplate(String source,String destination) throws IOException {
        
        Control control = new Control();
        
        StringBuffer buffer = new StringBuffer();
        List<CeldaInfoAmpliada> tbInfo = control.editDistance(source,destination);
        for (CeldaInfoAmpliada celdaInfoAmpliada : tbInfo) {
            Resultado result = celdaInfoAmpliada.getResult();
            switch (result) {
                case Diferent: {
                    LinkedList<CeldaInfoAmpliada> listaInfoTemp = control.processTableByChar((Linea) celdaInfoAmpliada.getSource().getInfo(), (Linea) celdaInfoAmpliada.getDestination().getInfo());
                    buildByChar(buffer, listaInfoTemp);
                }
                break;
                case Equal: {
                    Linea sourceLine = (Linea) celdaInfoAmpliada.getSource().getInfo();
                    Linea destinationLine = (Linea) celdaInfoAmpliada.getDestination().getInfo();
                }
                break;
                case DestinationNull: {
                    Linea sourceLine = (Linea) celdaInfoAmpliada.getSource().getInfo();
                }
                break;
                case SourceNull: {
                    Linea destinationLine = (Linea) celdaInfoAmpliada.getDestination().getInfo();
                }
                break;
            }
        }
        return buffer.toString();
    }

    private void buildByChar(StringBuffer buffer, LinkedList<CeldaInfoAmpliada> listaInfoTemp) {
        for (CeldaInfoAmpliada celdaInfoAmpliada : listaInfoTemp) {
            Resultado result = celdaInfoAmpliada.getResult();
            switch (result) {
                case Diferent: {
                    Caracter sourceCaracter = (Caracter) celdaInfoAmpliada.getSource().getInfo();
                    Caracter destinationCaracter = (Caracter) celdaInfoAmpliada.getDestination().getInfo();
                }
                break;
                case Equal: {
                    Caracter sourceCaracter = (Caracter) celdaInfoAmpliada.getSource().getInfo();
                    Caracter destinationCaracter = (Caracter) celdaInfoAmpliada.getDestination().getInfo();
                }
                break;
                case DestinationNull: {
                    Caracter sourceCaracter = (Caracter) celdaInfoAmpliada.getSource().getInfo();
                }
                break;
                case SourceNull: {
                    Caracter destinationCaracter = (Caracter) celdaInfoAmpliada.getDestination().getInfo();
                }
                break;
            }
        }
    }
}
