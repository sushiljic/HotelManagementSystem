/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.salesreport;

import hotelmanagementsystem.MainFrameView;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecuteSalesReport  extends JDialog{
     public SalesReportView IssueReportView;
     public SalesReportModel IssueReportModel;
     public SalesReportController IssueReportController;
    
    public ExecuteSalesReport(JFrame parent,boolean modal){
      super(parent,modal);  
      IssueReportView = new SalesReportView(parent,modal);
      IssueReportModel = new SalesReportModel();
      IssueReportController = new SalesReportController(IssueReportModel,IssueReportView,(MainFrameView)parent);
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
           new ExecuteSalesReport(new javax.swing.JFrame(),true);
           }
           
       });
    }
}
