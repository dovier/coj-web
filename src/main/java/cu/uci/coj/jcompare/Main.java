package cu.uci.coj.jcompare;

import java.util.List;

/**
 *
 * @author amado
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        Control ctr = new Control();
        List<CeldaInfoAmpliada> cells = ctr.editDistance("D:/source.txt","D:/destination.txt");
        
        for (CeldaInfoAmpliada cell: cells){
            System.out.print("---");
            System.out.print(cell.getSource().getResult().toString());
        System.out.print(cell.getResult().toString());
        System.out.println(cell.getDestination().getInfo().toString());
        }
    }
}
