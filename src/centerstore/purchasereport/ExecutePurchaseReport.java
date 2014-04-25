/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.purchasereport;

import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecutePurchaseReport  extends JDialog{
     public PurchaseReportView IssueReportView;
     public PuchaseReportModel IssueReportModel;
     public PurchaseReportController IssueReportController;
    
    public ExecutePurchaseReport(Frame parent,boolean modal){
      super(parent,modal);  
      IssueReportView = new PurchaseReportView(parent,modal);
      IssueReportModel = new PuchaseReportModel();
      IssueReportController = new PurchaseReportController(IssueReportModel,IssueReportView);
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
           new ExecutePurchaseReport(new javax.swing.JFrame(),true);
           }
           
       });
    }
}
