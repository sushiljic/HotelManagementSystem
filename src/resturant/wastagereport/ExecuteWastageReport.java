/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.wastagereport;

import resturant.salesreport.*;
import hotelmanagementsystem.MainFrameView;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecuteWastageReport  extends JDialog{
     public WastageReportView IssueReportView;
     public WastageReportModel IssueReportModel;
     public WastageReportController IssueReportController;
    
    public ExecuteWastageReport(JFrame parent,boolean modal){
      super(parent,modal);  
      IssueReportView = new WastageReportView(parent,modal);
      IssueReportModel = new WastageReportModel();
      IssueReportController = new WastageReportController(IssueReportModel,IssueReportView,(MainFrameView)parent);
      IssueReportView.setVisible(true);
      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       SwingUtilities.invokeLater(new Runnable(){

           @Override
           public void run() {
           //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           new ExecuteWastageReport(new javax.swing.JFrame(),true);
           }
           
       });
    }
}
