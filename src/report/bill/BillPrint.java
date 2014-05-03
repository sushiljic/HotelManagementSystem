/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package report.bill;

import database.DBConnect;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author MinamRosh
 */
public class BillPrint extends DBConnect {
    
    //parameters need for bill printing borrowed from the source
    private Map param;
    //holds jasper design
    JasperDesign jDesign = null;
    //prepare report
    JasperReport jReport = null;
    //fill report
    JasperPrint jPrint = null; 
    public BillPrint(Map param){
        this.param = param;
        //establish connection to the database;
        initConnection();
        try{
            
            InputStream file = BillPrint.class.getResource("thermalBill.jrxml").openStream();
            jDesign = JRXmlLoader.load(file);
            //compile
            jReport = JasperCompileManager.compileReport(jDesign);
            //fill report
            jPrint = JasperFillManager.fillReport(jReport, this.param, conn);
            //JasperPrintManager.printReport(jPrint, false);
           // JasperViewer view = new JasperViewer(jPrint);
            //view.setVisible(true);
            
           /*JFrame frm =  new JFrame();
           frm.getContentPane().add(new JRViewer(jPrint));
           frm.pack();
           frm.setVisible(true);
           frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          /* 
            JRXlsExporter export = new JRXlsExporter();
            export.setParameter(JRExporterParameter.JASPER_PRINT,jPrint);
            export.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "file.xls");
            export.exportReport();
             */  
            
        }
        catch(JRException  | IOException e){
           JOptionPane.showMessageDialog(null,"report.bill.Billprint.contructor():"+e);
        } 
        finally{
            closeConnection();
        }
    }
    
    public void printBill(){
        try{
            JasperPrintManager.printReport(jPrint, false);
        }
        catch(JRException print){
            JOptionPane.showMessageDialog(null, "report.bill.BillPrint.printBill():"+print, "Opreation Failed!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
