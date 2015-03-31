/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import cu.uci.coj.model.Problem;

/**
 *
 * @author jorge
 */
public class PDFExportProblem {

    private String[] images;
    private String logo; 
    private static String FILE = cu.uci.coj.config.Config.getProperty("problems.export");
    private Problem problem;
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    private Font textFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL);

    public PDFExportProblem(String[] images, String logo, Problem problem) {
        this.images = images;
        this.logo = logo;
        this.problem = problem;

            }

    public PDFExportProblem() {
    }

    public Paragraph addParagraph(String header, String content, boolean images) {
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Paragraph(header, catFont));
        paragraph.add(new Paragraph(" "));
        Paragraph paragraph1 = new Paragraph(content,textFont);
        paragraph.add(paragraph1);
        paragraph.add(new Paragraph(" "));
        return paragraph;
    }

    public Document getPDF() throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(FILE + problem.getPid() + ".pdf"));
        Image image = Image.getInstance(this.logo);
        document.open();
        document.add(image);
        document.addCreationDate();
        document.add(new Paragraph("Title: "+problem.getTitle()));
        document.add(new Paragraph("Code: "+problem.getPid()));
        document.add(new Paragraph(" "));
        document.add(addParagraph("Description",problem.getDescription(), true));
        document.add(addParagraph("Input",problem.getInput(), true));
        document.add(addParagraph("Output",problem.getOutput(), true));
        document.add(addParagraph("Input Example",problem.getInputex().replaceAll("<br/>", ""), true));
        document.add(addParagraph("Output Example",problem.getOutputex(), true));
        document.add(new Paragraph("Time(ms): "+problem.getTime()));
        document.add(new Paragraph("Memory(kb): "+problem.getMemory()));
        document.add(new Paragraph("Source(kb): "+problem.getFontsize()));
        document.addTitle("Challenger Online Judge");
        document.addAuthor("Chjudge");
        document.close();
        return document;
    }
}
