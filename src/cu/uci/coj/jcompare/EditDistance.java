package cu.uci.coj.jcompare;

import java.util.LinkedList;
import java.util.List;

public class EditDistance<T extends Comparable> {

    private List<T> source;
    private List<T> destination;

    public EditDistance(List<T> source, List<T> destination) {
        this.source = source;
        this.destination = destination;
    }

    public LinkedList<CeldaInformacion> runEditDistance() {



        int[][] matriz = matriz();

        int rowindex = source.size() + 1;
        int colindex = destination.size() + 1;
        int sourceSize = source.size();
        int destinationSize = destination.size();
        int sourceIndex = sourceSize - 1;
        int destinationIndex = destinationSize - 1;


        for (int i = 0; i < sourceSize + 2; i++) {
            for (int j = 0; j < destinationSize + 2; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");

        }


        LinkedList<CeldaInformacion> cellsList = new LinkedList<CeldaInformacion>();
        for (int i = 1; i < sourceSize; i++) {
            matriz[i][0] = Integer.MAX_VALUE;
        }
        for (int i = 1; i < destinationSize; i++) {
            matriz[0][i] = Integer.MAX_VALUE;
        }

        while (rowindex > 1 && colindex > 1) {
            T sourceElement = source.get(sourceIndex);
            T destinationElement = destination.get(destinationIndex);
            int result = sourceElement.compareTo(destinationElement);
            int current = matriz[rowindex][colindex];
            int diagonal = matriz[rowindex - 1][colindex - 1];
            int left = matriz[rowindex][colindex - 1];
            int up = matriz[rowindex - 1][colindex];

            if (result == 0) {
                if (current == diagonal) {
                    CeldaInformacion cell = new CeldaInformacion(sourceIndex, destinationIndex, Resultado.Equal);
                    cellsList.addFirst(cell);
                    sourceIndex--;
                    destinationIndex--;
                    rowindex--;
                    colindex--;
                } else if (sourceIndex > destinationIndex) {
                    if (up <= left) {
                        CeldaInformacion cell = new CeldaInformacion(sourceIndex, destinationIndex, Resultado.DestinationNull);
                        cellsList.addFirst(cell);
                        sourceIndex--;
                        rowindex--;

                    } else {
                        CeldaInformacion cell = new CeldaInformacion(sourceIndex, destinationIndex, Resultado.SourceNull);
                        cellsList.addFirst(cell);
                        destinationIndex--;
                        colindex--;
                    }
                } else {
                    if (left <= up) {
                        CeldaInformacion cell = new CeldaInformacion(sourceIndex, destinationIndex, Resultado.SourceNull);
                        cellsList.addFirst(cell);
                        destinationIndex--;
                        colindex--;
                    } else {
                        CeldaInformacion cell = new CeldaInformacion(sourceIndex, destinationIndex, Resultado.DestinationNull);
                        cellsList.addFirst(cell);
                        sourceIndex--;
                        rowindex--;
                    }

                }
            } else {
                if (current == diagonal + 1) {
                    CeldaInformacion cell = new CeldaInformacion(sourceIndex, destinationIndex, Resultado.Diferent);
                    cellsList.addFirst(cell);
                    sourceIndex--;
                    destinationIndex--;
                    rowindex--;
                    colindex--;
                } else if (sourceIndex > destinationIndex) {
                    if (up <= left) {
                        CeldaInformacion cell = new CeldaInformacion(sourceIndex, destinationIndex, Resultado.DestinationNull);
                        cellsList.addFirst(cell);
                        sourceIndex--;
                        rowindex--;
                    } else {
                        CeldaInformacion cell = new CeldaInformacion(sourceIndex, destinationIndex, Resultado.SourceNull);
                        cellsList.addFirst(cell);
                        destinationIndex--;
                        colindex--;
                    }
                } else {
                    if (left <= up) {
                        CeldaInformacion cell = new CeldaInformacion(sourceIndex, destinationIndex, Resultado.SourceNull);
                        cellsList.addFirst(cell);
                        destinationIndex--;
                        colindex--;
                    } else {
                        CeldaInformacion cell = new CeldaInformacion(sourceIndex, destinationIndex, Resultado.DestinationNull);
                        cellsList.addFirst(cell);
                        sourceIndex--;
                        rowindex--;
                    }

                }
            }

        }
        while (rowindex-- > 1) {
            CeldaInformacion cell = new CeldaInformacion(sourceIndex--, -1, Resultado.DestinationNull);
            cellsList.addFirst(cell);
        }
        while (colindex-- > 1) {
            CeldaInformacion cell = new CeldaInformacion(-1, destinationIndex--, Resultado.SourceNull);
            cellsList.addFirst(cell);
        }
        for (CeldaInformacion celdaInformacion : cellsList) {
            System.out.println(celdaInformacion.toString());
        }
        LinkedList<CeldaInformacion> tabla = new LinkedList<CeldaInformacion>(cellsList);
        return tabla;
    }

    private int[][] matriz() {
        int sourceSize = source.size();
        int destinationSize = destination.size();

        int[][] editArray = new int[sourceSize + 2][destinationSize + 2];


        for (int i = 2; i <= sourceSize + 1; i++) {
            editArray[i][1] = i - 1;
        }
        for (int i = 2; i <= destinationSize + 1; i++) {
            editArray[1][i] = i - 1;
        }

        for (int sourceindex = 0, rowindex = 2; sourceindex < sourceSize; sourceindex++) {
            T sourceElement = source.get(sourceindex);

            for (int destinationindex = 0, colindex = 2; destinationindex < destinationSize; destinationindex++) {
                T destinationElement = destination.get(destinationindex);
                int result = sourceElement.compareTo(destinationElement);

                int value2 = editArray[rowindex][colindex - 1] + 1;//numero de operaciones ralizadas hasta el momento mas eliminar en el destino
                int value3 = editArray[rowindex - 1][colindex] + 1;//numero de operaciones ralizadas hasta el momento mas eliminar en la fuente
                int value1 = -1;

                if (result == 0) {
                    value1 = editArray[rowindex - 1][colindex - 1];//numero de operaciones realizadas hasta el momento
                } else {
                    value1 = editArray[rowindex - 1][colindex - 1] + 1;//numero de operaciones realizadas hasta el momento mas una sustitucion en la fuente o en el destino
                }

                int minvalue = Math.min(value1, value2);
                minvalue = Math.min(value3, minvalue);

                editArray[rowindex][colindex] = minvalue;

                colindex++;
            }
            rowindex++;

        }

        return editArray;
    }
}
