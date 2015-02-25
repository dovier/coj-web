/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.pdf;

import cu.uci.coj.config.Config;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Problem;
import java.io.File;
import java.util.Iterator;

/**
 *
 * @author nenes
 */
public class ProblemPdf extends BasePdf {

    public ProblemPdf() {
    }

    public void generate(Problem problem) {
        File file = new File(Config.getProperty("pdfs.dir") + "/" + problem.getPid() + ".pdf");
        if (!file.exists()) {
            String contenido = "";
            contenido += "<h3><center><label style=\"color: #595959;font-size: 18px;\"><b>" + problem.getPid() + " - " + problem.getTitle() + "</b></label></center></h3><br>";
            contenido += "<h4 style=\"color: #616161;font-size: 16px;\">Description</h4><br>";
            contenido += "<p style=\"font-size: 15px;line-height: 21px;text-align: justify;\">" + problem.getDescription() + "</p><br>";

            contenido += "<h4 style=\"color: #616161;font-size: 16px;\">Input specification</h4><br>";
            contenido += "<p style=\"font-size: 15px;line-height: 21px;text-align: justify;\">" + problem.getInput() + "</p><br>";

            contenido += "<h4 style=\"color: #616161;font-size: 16px;\">Output specification</h4><br>";
            contenido += "<p style=\"font-size: 15px;line-height: 21px;text-align: justify;\">" + problem.getOutput() + "</p><br>";

            contenido += "<h4 style=\"color: #616161;font-size: 16px;\">Sample input</h4><br>";
            contenido += "<pre style=\"background-color: #FFFFEE;border: 1px solid #C0C0C0;overflow: auto; padding: 0.1em;\">" + problem.getInputex() + "</pre><br>";

            contenido += "<h4 style=\"color: #616161;font-size: 16px;\">Sample output</h4><br>";
            contenido += "<pre style=\"background-color: #FFFFEE;border: 1px solid #C0C0C0;overflow: auto; padding: 0.1em;\">" + problem.getOutputex() + "</pre><br>";

            contenido += "<h4 style=\"color: #616161;font-size: 16px;\">Hint(s)</h4><br>";
            contenido += "<p>" + problem.getComments() + "</p><br>";
            contenido += "<table style=\" border: 1px solid #C0C0C0;border-collapse: collapse;color: #003333;font: 14px/1.2em Times;text-align: left;width: 100%;\">";
            contenido += "<tbody>";
            contenido += "<tr><td>Source</td>";
            contenido += "<td>" + problem.getAuthor() + "</td></tr>";

            contenido += "<tr><td>Added by</td>";
            contenido += "<td><b>" + problem.getUsername() + "</b></td></tr>";

            contenido += "<tr><td>Addition date</td>";
            contenido += "<td>" + problem.getDate() + "</td></tr>";

            contenido += "<tr><td>Time limit (ms)</td>";
            contenido += "<td>" + problem.getTime() + "</td></tr>";

            contenido += "<tr><td><font color=\"red\">Test limit (ms)</font></td>";
            contenido += "<td>" + problem.getCasetimelimit() + "</td></tr>";

            contenido += "<tr><td>Memory limit (kb)</td>";
            contenido += "<td>" + problem.getMemory() + "</td></tr>";

            contenido += "<tr><td>Output limit (mb)</td>";
            contenido += "<td>64</td></tr>";

            contenido += "<tr><td>Size limit (bytes)</td>";
            contenido += "<td>" + problem.getFontsize() + "</td></tr>";

            contenido += "<tr><td>Enabled languages</td>";

            contenido += "<td>";
            for (Iterator<Language> it = problem.getLanguages().iterator(); it.hasNext();) {
                Language lang = it.next();
                contenido += (lang.getLanguage() + "  ");
            }
            contenido += "</td></tr>";
            contenido += "</tbody></table>";
            contenido = contenido.replaceAll("/downloads/", "/home/Judge/uploads/");            
            file = generarPdf("Caribbean Online Judge", "coj@coj.uci.cu", "Problem " + problem.getPid() + " description", String.valueOf(problem.getPid()), contenido, "/home/Judge/uploads/problems/pdfs/" + problem.getPid() + ".pdf", false);
        }
    }
}
