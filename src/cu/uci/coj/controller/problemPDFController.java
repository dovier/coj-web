/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.controller;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import cu.uci.coj.config.Config;

public class problemPDFController  extends BaseController implements Controller {

    JasperReport jasperReport;
    JasperPrint jasperPrint;
    DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = null;

        Integer pid = -1;
        try {
            pid = Integer.parseInt(request.getParameter("pid"));
        } catch (Exception ex) {
            pid = -1;
        }
        if (pid.equals(-1)) {
            return new ModelAndView("/error/wproblem");
        }

        try {

            jasperReport = JasperCompileManager.compileReport(Config.getProperty("problems.report.path") + "Problem.jrxml");

            Map<String, String> parametros = new HashMap<String, String>();

            parametros.put("PID", String.valueOf(pid));
            /*
             * try { jasperPrint = JasperFillManager.fillReport(jasperReport,
             * parametros, ds.getConnection()); } catch (SQLException ex) {
             * logger.error("Error generando Reporte."); ex.printStackTrace(); }
             *
             *
             * JasperExportManager.exportReportToPdfFile(jasperPrint,
             * "reporte.pdf");
             */

            //
            byte[] reporte = null;
            Connection conn = dataSource.getConnection();
            reporte = JasperRunManager.runReportToPdf(jasperReport, parametros, conn);

            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline; filename=" + pid + ".pdf");
            response.setHeader("Cache-Control", "max-age=30");
            response.setHeader("Pragma", "No-cache");
            response.setDateHeader("Expires", 0);
            response.setContentLength(reporte.length);

            ServletOutputStream out = response.getOutputStream();
            out.write(reporte, 0, reporte.length);
            out.flush();
            out.close();
            //
            conn.close();
            return new ModelAndView("index");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mv;
    }
}
