/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package report.terminalItemSalesReport;

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

/**
 *
 * @author MinamRosh
 */
public class ItemSalesReport extends DBConnect{
    private Map reportParam;
    private JasperDesign jDesign;
    private JasperReport jReport;
    private JasperPrint jPrint;
    
    public ItemSalesReport(Map m, String report, String title){
       reportParam = m;
       initConnection();
       try{
           InputStream file = ItemSalesReport.class.getResource(report).openStream();   //new FileInputStream(new File("D:\\DreamSys\\HotelManagementSystem\\src\\report\\terminal\\dailySalesReport.jrxml"));
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

/*
public class DailySalesReport extends DBConnect {
    private Map param;
    //holds jasper design
    JasperDesign jDesign = null;
    //prepare report
    JasperReport jReport = null;
    //fill report
    JasperPrint jPrint = null; 
    
    @SuppressWarnings("Convert2Diamond")
    public DailySalesReport(){//Map para){
        
        param = new HashMap<Object, Object>();
        
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        //Timestamp t;
        
        
        //establish connection to the database;
        //
       // param = para;
        initConnection();
        try{
            
            d = f.parse("2013-12-3");
            
            param.put("pDay",d);
            
            InputStream file = new FileInputStream(new File("D:\\DreamSys\\HotelManagementSystem\\src\\report\\terminal\\dailySalesReport.jrxml"));
            jDesign = JRXmlLoader.load(file);
            //compile
            jReport = JasperCompileManager.compileReport(jDesign);
            //fill report
            jPrint = JasperFillManager.fillReport(jReport, this.param, conn);
             
            //JasperPrintManager.printReport(jPrint, false);
           JasperViewer view = new JasperViewer(jPrint);
            view.setVisible(true);
        
            
           //JDialog reportWindow = new JDialog(new JFrame(),false);
           
           //JRViewer rView = new JRViewer(jPrint);
           //rView.setVisible(true);
          /* reportWindow.getContentPane().add(new JRViewer(jPrint));
           
           reportWindow.setVisible(true);
           reportWindow.pack();
           reportWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
           
           /* JRXlsExporter export = new JRXlsExporter();
            export.setParameter(JRExporterParameter.JASPER_PRINT,jPrint);
            export.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "file.xls");
            export.exportReport();
             */  
    /*        
        }
        catch(JRException | FileNotFoundException | ParseException  e){
           JOptionPane.showMessageDialog(null,"report.bill.Billprint.contructor():"+e);
        }
        finally{
            closeConnection();
        }
    }
    public void printReport(){
        try{
            JasperPrintManager.printReport(jPrint, false);
        }
        catch(JRException print){
            JOptionPane.showMessageDialog(null, "report.bill.BillPrint.printBill():"+print, "Opreation Failed!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args){
        //new DailySalesReport();
    }
}
*/