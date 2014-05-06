/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package report.bill;

import database.DBConnect;
import java.awt.PrintJob;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import reusableClass.DisplayMessages;

/**
 *
 * @author MinamRosh
 */
public class PrintOrder extends DBConnect {
    
    //parameters need for bill printing borrowed from the source
    private Map param;
    //holds jasper design
    JasperDesign jDesign = null;
    //prepare report
    JasperReport jReport = null;
    //fill report
    JasperPrint jPrint = null; 
    String oPrinter;
    String dPrinter;
    public PrintOrder(Map param,String oPrinter, String dPrinter){
        this.param = param;
        this.oPrinter = oPrinter;
        this.dPrinter = dPrinter;
        //establish connection to the database;
        initConnection();
        try{
            
            InputStream file = BillPrint.class.getResource("thermalBill.jrxml").openStream();
            jDesign = JRXmlLoader.load(file);
            //compile
            jReport = JasperCompileManager.compileReport(jDesign);
            //fill report
            jPrint = JasperFillManager.fillReport(jReport, this.param, conn);
        }
        catch(JRException  | IOException e){
           JOptionPane.showMessageDialog(null,"report.bill.Billprint.contructor():"+e);
        } 
        finally{
            closeConnection();
        }
    }
    
    public void printOrder(){
        PrinterJob printJob = PrinterJob.getPrinterJob();
        try{
            JRPrintServiceExporter export = new JRPrintServiceExporter();
            PrintService[] printer = PrintServiceLookup.lookupPrintServices(null,null);
            int order = 0; //index for order printer
            int def = 0;  // index for default printer
            int selected = 0; //final printer index
            boolean o = false; // status for order
            boolean d = false; // status for default
            for(int i = 0; i < printer.length; i++){
                
                if(printer[i].getName().toUpperCase().contains(oPrinter)){
                  o = true;
                  order = i;  
                }
                if(printer[i].getName().toUpperCase().contains(dPrinter)){
                    d = true;
                    def = i;  
                }
            }

            if(order >= 0 && o){
                selected = order;
                printJob.setPrintService(printer[order]);
            }//if printer is not available
            else if( def >= 0 && d){
                selected = def;
                printJob.setPrintService(printer[def]);
            }
            else{
                JasperPrintManager.printReport(jPrint, false);
                return;
            }
            if(o || d){
                export.setParameter(JRExporterParameter.JASPER_PRINT, jPrint);
                export.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE,printer[selected]);
                export.exportReport();
            }
        }
        catch(JRException | PrinterException print){
            JOptionPane.showMessageDialog(null, "report.bill.BillPrint.printBill():"+print, "Opreation Failed!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
