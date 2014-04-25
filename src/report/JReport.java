/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package report;

import java.io.File;
import java.sql.*;
//import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import report.bill.BillPrint;
/**
 *
 * @author MinamRosh
 */
public class JReport {
    
    //holds compiled jrxml file;
    final String db_url ="jdbc:sqlserver://localhost;databasename=hotel";// "jdbc:mysql://localhost/pofil";
    Connection connet = null;
    JasperDesign jDesign = null;
    JasperReport jReport = null;
    JasperPrint jPrint = null; //holds report after filling for printing;
    
    public void dbConnect(){
        try{
            connet = DriverManager.getConnection(db_url, "sa", "minamrosh");
            System.out.println("Connected");
        }
        catch(SQLException s){
            System.out.println(s);
        }
    }
    
    public void dbDisconnect(){
        try{
            connet.close();
            System.out.println("Disconnected");
        }
        catch(SQLException se){
            System.out.println(se);
        }
    }
    public void generate(){
        
        @SuppressWarnings("Convert2Diamond")
                       Map param = new HashMap<Object, Object>();
             try{
                
             //putting parameter 
             param.put("billNo","11");
             param.put("total","1555");
             param.put("svc", "10");
             param.put("vat", "13%");
             param.put("subtotal","2893");
             param.put("discount","20");
             param.put("grandtotal","3333");
             param.put("amountTen","33333");
             param.put("amountRetn","50");
             param.put("terminal",1);
             param.put("orderId",52);
             //calling BillPrint function
             BillPrint p = new BillPrint(param);
             //p.printBill();
             
             }catch(ExceptionInInitializerError e){
                 JOptionPane.showMessageDialog(null,"ksjdf;jasf");
             }
             System.out.println(this.getClass().getResource("JReport.java"));
             /*
        Map jParam = new HashMap<Object, Object>();
        jParam.put("order_id", 44);
        jParam.put("total", new BigDecimal(100));
             
        try{
            
            InputStream file = new FileInputStream(new File("D:\\DreamSys\\HotelManagementSystem\\src\\report\\bill\\invoice.jrxml"));
            jDesign = JRXmlLoader.load(file);
            //compile
            jReport = JasperCompileManager.compileReport(jDesign);
            //fill report
            jPrint = JasperFillManager.fillReport(jReport, jParam, connet);
            JasperPrintManager.printReport(jPrint, false);
           // JasperViewer view = new JasperViewer(jPrint);
            //view.setVisible(true);
           JFrame frm =  new JFrame();
           frm.getContentPane().add(new JRViewer(jPrint));
           frm.pack();
           frm.setVisible(true);
           frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          /* 
            JRXlsExporter export = new JRXlsExporter();
            export.setParameter(JRExporterParameter.JASPER_PRINT,jPrint);
            export.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "file.xls");
            export.exportReport();
                 
        }
        catch(JRException | FileNotFoundException e){
           System.out.println(e); 
        }
        */
    }
    
    public static void main(String[] args){
      /*JReport r =  new JReport();
      r.dbConnect();
      r.generate();
      r.dbDisconnect();
              */
      
    }
}
