/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package report.terminalSalesReport;

import database.DBConnect;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import report.ReportView;
import report.terminalItemSalesReport.ItemSalesReport;

/**
 *
 * @author SUSHIL
 */
public class SalesReport extends DBConnect {
       private Map reportParam;
    private JasperDesign jDesign;
    private JasperReport jReport;
    private JasperPrint jPrint;
    
    public SalesReport(Map m, String report, String title){
       reportParam = m;
       initConnection();
       try{
           InputStream file = SalesReport.class.getResource(report).openStream();   //new FileInputStream(new File("D:\\DreamSys\\HotelManagementSystem\\src\\report\\terminal\\dailySalesReport.jrxml"));
            jDesign = JRXmlLoader.load(file);
            //compile
            jReport = JasperCompileManager.compileReport(jDesign);
            //fill report
            jPrint = JasperFillManager.fillReport(jReport, this.reportParam, conn);
             
            //JasperPrintManager.printReport(jPrint, false);
           ReportView view = new ReportView(jPrint, title);
            //view.setVisible(true);
       }
       catch(JRException | IOException je){
           JOptionPane.showMessageDialog(null,"report.terminalSalesReport.MonthlySalesReport():"+je);
       }
    }
}
