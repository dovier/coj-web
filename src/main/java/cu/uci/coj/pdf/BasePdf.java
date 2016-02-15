/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;

public abstract class BasePdf {

    public static String DIRECCION_PDFS = "";
    public static String CARACTER_DELIMITADOR = "";
    public static String RUTA_IMAGENES = "";
    public static String ADMIN_SITIO = "";

    //author, en caso del pdf del problema, es el problemsetter, los demas pdfs es el admin del sitio.
    //el creator siempre es el admin del sitio
    protected File generarPdf(String author, String creator, String subject, String title, String contenido, String ruta, boolean concat) {


        Document document = new Document(PageSize.A4, 35, 30, 70, 50);
        FileOutputStream fileO;
        File file = new File(ruta);
        if (!file.exists()) {
            try {
                if (concat) {
                    fileO = new FileOutputStream(new File(ruta));
                } else {

                    fileO = new FileOutputStream(ruta);
                }
                PdfWriter writer = PdfWriter.getInstance(document, fileO);

                writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));
                HeaderFooter event = new HeaderFooter();
                writer.setPageEvent(event);

                document.open();
                if (!concat) {
                    document.addAuthor(author);
                    document.addCreator(creator);
                    document.addSubject(subject);
                    document.addCreationDate();
                    document.addTitle(title);
                }

                contenido = procesarHtml(contenido);

                HTMLWorker htmlWorker = new HTMLWorker(document);
                if (concat) {
                    htmlWorker.newPage();
                }

                htmlWorker.parse(new StringReader(contenido));
                document.close();

                File file1 = new File(ruta);
                return file1;

            } catch (Exception e) {

                return null;
            }
        }

        return file;
    }

    static class HeaderFooter extends PdfPageEventHelper {

        public void onEndPage(PdfWriter writer, Document document) {
            Rectangle rect = writer.getBoxSize("art");

            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER, new Phrase("Caribbean Online Judge"),
                    (rect.getLeft() + rect.getRight()) / 2, rect.getTop() + 10, 0);

            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER, new Phrase(String.format("-%d-", writer.getPageNumber())),
                    (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);
        }
    }

    private String procesarHtml(String html) {

        html = html.replace("< ", "&lt; ");
        html = html.replace("<<", "&lt;&lt;");
        html = html.replace("<=", "&lt;=");
        html = html.replace("<1", "&lt;1");
        html = html.replace("<2", "&lt;2");
        html = html.replace("<3", "&lt;3");
        html = html.replace("<4", "&lt;4");
        html = html.replace("<5", "&lt;5");
        html = html.replace("<6", "&lt;6");
        html = html.replace("<7", "&lt;7");
        html = html.replace("<8", "&lt;8");
        html = html.replace("<9", "&lt;9");
        html = html.replace("<0", "&lt;0");
        html = html.replace("<-", "&lt;-");
        html = html.replace("<\"", "&lt;\"");
        html = html.replace("<\n", "&lt;\n");
        html = html.replace("<\r", "&lt;\r");

        html = html.replace("&#8804;", "&lt;=");

        html = procesarHtmlImage(html);

        String buffer = "";
        while (html.contains("\n")) {
            buffer += html.substring(0, html.indexOf("\n") + 1);
            html = html.substring(html.indexOf("\n") + 1);

            int pos = 0;
            while (html.charAt(pos++) == ' ') {
                buffer += "&nbsp;";
            }
            html = html.substring(pos - 1);
        }
        buffer += html;
        // buffer = buffer.replace("\n", "<br>");
        buffer = buffer.replace("<table", "<table width=80%");

        return buffer;
    }

    private String procesarHtmlImage(String html) {
        System.out.println(html);
        String server = "http://coj.uci.cu";
        int idxFImg = 0;
        String tagImg = "<img src = \"";
        int idxIImg = html.indexOf(tagImg, 0);
        while (idxIImg != -1) {
            boolean fin = false;
            int idxISrc = idxIImg + (tagImg.length());
            int idxFSrc = idxIImg + (tagImg.length() + 1);
            for (int i = idxISrc; !fin && i < html.length(); i++) {
                if (html.charAt(i) == '"') {
                    idxFSrc = i;
                }

                if (html.substring(i, i + 3).equals("\"/>")) {
                    fin = true;
                    idxFImg = i + 3;
                }
            }

            String srcVal = html.substring(idxISrc, idxFSrc);
            html = html.replace(srcVal, server + srcVal);
            System.out.println(html);
            idxFImg += server.length();

            idxIImg = html.indexOf(tagImg, idxFImg);
        }

        return html;
    }
}
