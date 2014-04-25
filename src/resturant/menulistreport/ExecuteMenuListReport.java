/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.menulistreport;

import hotelmanagementsystem.MainFrameView;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecuteMenuListReport  extends JDialog{
     public MenuListReportView IssueReportView;
     public MenuListReportModel IssueReportModel;
     public MenuListReportController IssueReportController;
    
    public ExecuteMenuListReport(Frame parent,boolean modal){
      super(parent,modal);  
      IssueReportView = new MenuListReportView(parent,modal);
      IssueReportModel = new MenuListReportModel();
      IssueReportController = new MenuListReportController(IssueReportModel,IssueReportView,(MainFrameView)parent);
      IssueReportView.setBooleanIncludeAllCategory(true);
      IssueReportView.setBooleanIncludeAllSubCategory(true);
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
           new ExecuteMenuListReport(new javax.swing.JFrame(),true);
           }
           
       });
    }
}
