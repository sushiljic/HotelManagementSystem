/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package report.waiterServiceReport;

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
public class WaiterServiceReport extends DBConnect{
   private Map param;
    //holds jasper design
    JasperDesign jDesign = null;
    //prepare report
    JasperReport jReport = null;
    //fill report
    JasperPrint jPrint = null; 
    
    public WaiterServiceReport(Map m, String report, String title){
        param = m;
        initConnection();
        try{
            InputStream file = WaiterServiceReport.class.getResource(report).openStream();//new FileInputStream(new File("D:\\DreamSys\\HotelManagementSystem\\src\\report\\complementary\\AllComplementaryReport.jrxml"));
            jDesign = JRXmlLoader.load(file);
            //compile
            jReport = JasperCompileManager.compileReport(jDesign);
            //fill report
            jPrint = JasperFillManager.fillReport(jReport, this.param, conn);
             
            ReportView displayReport = new ReportView(jPrint, title);
        }
        catch(JRException | IOException ex){
            JOptionPane.showMessageDialog(null,"report.complementary.AllComplementary.contructor():"+ex);
        }
        finally{
            closeConnection();
        }
    }
}
