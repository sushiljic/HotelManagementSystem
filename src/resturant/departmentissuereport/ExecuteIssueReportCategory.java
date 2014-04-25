/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.departmentissuereport;

import hotelmanagementsystem.MainFrameView;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecuteIssueReportCategory  extends JDialog{
     public IssueReportCategoryView IssueReportView;
     public IssueReportModel IssueReportModel;
     public IssueReportCategoryController IssueReportController;
     private MainFrameView mainview;
    
    public ExecuteIssueReportCategory(Frame parent,boolean modal){
      super(parent,modal);  
      IssueReportView = new IssueReportCategoryView(parent,modal);
      IssueReportModel = new IssueReportModel();
      IssueReportController = new IssueReportCategoryController(IssueReportModel,IssueReportView,(MainFrameView)parent);
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
           new ExecuteIssueReportCategory(new javax.swing.JFrame(),true);
           }
           
       });
    }
}
