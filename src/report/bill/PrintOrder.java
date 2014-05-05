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
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
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
public class PrintOrder extends DBConnect {
    
    //parameters need for bill printing borrowed from the source
    private Map param;
    //holds jasper design
    JasperDesign jDesign = null;
    //prepare report
    JasperReport jReport = null;
    //fill report
    JasperPrint jPrint = null; 
    public PrintOrder(Map param){
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
            PrintService[] printer = PrintServiceLookup.lookupPrintServices(null,null);
            int selectedPrinter = 0;
            for(int i = 0; i < printer.length; i++){
                
            }
            JasperPrintManager.printReport(jPrint, false);
        }
        catch(JRException print){
            JOptionPane.showMessageDialog(null, "report.bill.BillPrint.printBill():"+print, "Opreation Failed!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
