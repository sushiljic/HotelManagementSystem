/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package report.departmentItem;

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
 * @author SUSHIL
 */
public class DepartmentItem extends DBConnect {
     private Map param;
    //holds jasper design
    JasperDesign jDesign = null;
    //prepare report
    JasperReport jReport = null;
    //fill report
    JasperPrint jPrint = null;

    /**
     *
     * @param m
     * @param report
     */
    public DepartmentItem(Map m, String report){
        param = m;
        initConnection();
        try{
            InputStream file = DepartmentItem.class.getResource(report).openStream();
            jDesign = JRXmlLoader.load(file);
            //compi
            jReport = JasperCompileManager.compileReport(jDesign);
            //fill report
            jPrint = JasperFillManager.fillReport(jReport, this.param, conn);
             
            new ReportView(jPrint,"Complementary Report");
        }
        catch(JRException | IOException ex){
            JOptionPane.showMessageDialog(null,"report.complementary.Complementary.contructor():"+ex);
        }
        finally{
            closeConnection();
        }
    }
}
