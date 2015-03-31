package cu.uci.coj.jcompare;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.util.CollectionUtils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 */
public class Control {

    private List<Linea> buildLines(String text){
        List<Linea> lines = new LinkedList<Linea>();
//        while(!buffer.ready());
        String[] textlines = text.split("\n");
        for(String line: textlines) {
            lines.add(new Linea(line));
        }
        return lines;
    }
    
    public List<CeldaInfoAmpliada> editDistance(String userOutput, String modelOutput) {
       
        List<Linea> sourcelines = buildLines(userOutput);
        List<Linea> destinationlines = buildLines(modelOutput);

        EditDistance<Linea> edit = new EditDistance<Linea>(sourcelines, destinationlines);
        LinkedList<CeldaInformacion> tablaInformacion = edit.runEditDistance();
        
        LinkedList<CeldaInfoAmpliada> infolist = new LinkedList<CeldaInfoAmpliada>();
        for (CeldaInformacion celdaInformacion : tablaInformacion) {
            Resultado result = celdaInformacion.getResultado();
            int sourceIndex = celdaInformacion.getSourceindex();
            int destinationIndex = celdaInformacion.getDestinationindex();
            switch (result) {
                case Equal: {
                    Info<Linea> sourceInfo = new Info<Linea>(sourcelines.get(sourceIndex), result);
                    Info<Linea> destinationInfo = new Info<Linea>(destinationlines.get(destinationIndex), result);
                    CeldaInfoAmpliada cell = new CeldaInfoAmpliada(sourceInfo, destinationInfo, result);
                    infolist.add(cell);
                }
                break;
                case DestinationNull: {
                    Info<Linea> sourceInfo = new Info<Linea>(sourcelines.get(sourceIndex), result);
                    Info<Linea> destinationInfo = new Info<Linea>(null, result);
                    CeldaInfoAmpliada cell = new CeldaInfoAmpliada(sourceInfo, destinationInfo, result);
                    infolist.add(cell);
                }
                break;
                case SourceNull: {
                    Info<Linea> sourceInfo = new Info<Linea>(null, result);
                    Info<Linea> destinationInfo = new Info<Linea>(destinationlines.get(destinationIndex), result);
                    CeldaInfoAmpliada cell = new CeldaInfoAmpliada(sourceInfo, destinationInfo, result);
                    infolist.add(cell);
                }
                break;

                case Diferent: {


//                    LinkedList<Caracter> sourceCharlist = sourceLines.get(sourceIndex).getCharacterlist();
//                    LinkedList<Caracter> destinationCharList = destinationLines.get(destinationIndex).getCharacterlist();
//                    EditDistance<Caracter> edit = new EditDistance<Caracter>(sourceCharlist, destinationCharList);
//                    TablaInformacion tablaInformacion = edit.runEditDistance();
//                    processTableByChar(tablaInformacion, sourceCharlist, destinationCharList);
//                    Info<LinkedList<CeldaInfoAmpliada>> sourceinfo = new Info<LinkedList<CeldaInfoAmpliada>>(infolist, result);
                    Info<Linea> sourceInfo = new Info<Linea>(sourcelines.get(sourceIndex), result);
                    Info<Linea> destinationInfo = new Info<Linea>(destinationlines.get(destinationIndex), result);
                    CeldaInfoAmpliada cell = new CeldaInfoAmpliada(sourceInfo, destinationInfo, result);
                    infolist.add(cell);
                }
            }
        }
        return infolist;
        }
    

    public LinkedList<CeldaInfoAmpliada> processTableByChar(Linea sourceline, Linea destinationline) {


        LinkedList<CeldaInfoAmpliada> infolist = new LinkedList<CeldaInfoAmpliada>();

        LinkedList<Caracter> sourceCharlist = sourceline.getCharacterlist();
        LinkedList<Caracter> destinationCharList = destinationline.getCharacterlist();

        EditDistance<Caracter> edit = new EditDistance<Caracter>(sourceCharlist, destinationCharList);

        LinkedList<CeldaInformacion> cellsList = edit.runEditDistance();
        for (CeldaInformacion celdaInformacion : cellsList) {
            Resultado result = celdaInformacion.getResultado();
            int sourceIndex = celdaInformacion.getSourceindex();
            int destinationIndex = celdaInformacion.getDestinationindex();
            switch (result) {
                case Equal: {
                    Info<Caracter> sourceInfo = new Info<Caracter>(sourceCharlist.get(sourceIndex), result);
                    Info<Caracter> destinationInfo = new Info<Caracter>(destinationCharList.get(destinationIndex), result);
                    CeldaInfoAmpliada cell = new CeldaInfoAmpliada(sourceInfo, destinationInfo, result);
                    infolist.add(cell);
                }
                break;
                case DestinationNull: {
                    Info<Caracter> sourceInfo = new Info<Caracter>(sourceCharlist.get(sourceIndex), result);
                    Info<Caracter> destinationInfo = new Info<Caracter>(null, result);
                    CeldaInfoAmpliada cell = new CeldaInfoAmpliada(sourceInfo, destinationInfo, result);
                    infolist.add(cell);
                }
                break;
                case SourceNull: {
                    Info<Caracter> sourceInfo = new Info<Caracter>(null, result);
                    Info<Caracter> destinationInfo = new Info<Caracter>(destinationCharList.get(destinationIndex), result);
                    CeldaInfoAmpliada cell = new CeldaInfoAmpliada(sourceInfo, destinationInfo, result);
                    infolist.add(cell);
                }
                break;
                case Diferent: {
                    Info<Caracter> sourceInfo = new Info<Caracter>(sourceCharlist.get(sourceIndex), result);
                    Info<Caracter> destinationInfo = new Info<Caracter>(destinationCharList.get(destinationIndex), result);
                    CeldaInfoAmpliada cell = new CeldaInfoAmpliada(sourceInfo, destinationInfo, result);
                    infolist.add(cell);
                }
            }
        }
        return infolist;
    }
}
