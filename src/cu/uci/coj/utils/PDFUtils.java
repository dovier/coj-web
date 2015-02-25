/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.utils;

import cu.uci.coj.model.Problem;
import cu.uci.coj.pdf.ProblemPdf;

public class PDFUtils {

    static ProblemPdf problem = new ProblemPdf();

    public static void generatePdf(Problem p) {
        problem.generate(p);
    }
}
