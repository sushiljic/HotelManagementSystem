/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.departmentissuereport;

import hotelmanagementsystem.MainFrameView;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecuteCenterostoreIssueReport  extends JDialog{
     public IssueReportView IssueReportView;
     public IssueReportModel IssueReportModel;
     public IssueReportController IssueReportController;
    
    public ExecuteCenterostoreIssueReport(Frame parent,boolean modal){
      super(parent,modal);  
      IssueReportView = new IssueReportView(parent,modal);
      IssueReportModel = new IssueReportModel();
      IssueReportController = new IssueReportController(IssueReportModel,IssueReportView,(MainFrameView)parent);
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
           new ExecuteCenterostoreIssueReport(new javax.swing.JFrame(),true);
           }
           
       });
    }
}
