/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.issuestockreport;

import hotelmanagementsystem.MainFrameView;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecuteIssueStockReport  extends JDialog{
     public IssueStockReportView IssueReportView;
     public IssueStockReportModel IssueReportModel;
     public IssueStockReportController IssueReportController;
    
    public ExecuteIssueStockReport(Frame parent,boolean modal){
        super(parent,modal);  
       
        try{
      IssueReportView = new IssueStockReportView(parent,modal);
      IssueReportModel = new IssueStockReportModel();
      IssueReportController = new IssueStockReportController(IssueReportModel,IssueReportView,(MainFrameView)parent);
     
      IssueReportView.setVisible(true);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(parent,e+" From ExecuteIssueStock");
        }
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
           new ExecuteIssueStockReport(new javax.swing.JFrame(),true);
           }
           
       });
    }
}
