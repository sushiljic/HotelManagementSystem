/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.itemwisesalesreport;

import hotelmanagementsystem.MainFrameView;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecuteItemWiseSalesReport  extends JDialog{
     public ItemWiseSalesReportView IssueReportView;
     public ItemWiseSalesReportModel IssueReportModel;
     public ItemWiseSalesReportController IssueReportController;
    
    public ExecuteItemWiseSalesReport(Frame parent,boolean modal){
      super(parent,modal);  
      IssueReportView = new ItemWiseSalesReportView(parent,modal);
      IssueReportModel = new ItemWiseSalesReportModel();
      IssueReportController = new ItemWiseSalesReportController(IssueReportModel,IssueReportView,(MainFrameView)parent);
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
           new ExecuteItemWiseSalesReport(new javax.swing.JFrame(),true);
           }
           
       });
    }
}
