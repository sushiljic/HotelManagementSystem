/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.issuereturn;

import hotelmanagementsystem.MainFrameView;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class executeIssueReturn extends JDialog {
     IssueReturnModel model;
      IssueReturnView view;
       IssueReturnController controller;
      
    public executeIssueReturn(JFrame parent,boolean modal){
        super(parent,modal);  
        model = new IssueReturnModel();
         view = new IssueReturnView(parent,modal);
        
         controller = new IssueReturnController(model,view,(MainFrameView) parent);
        
//        view.refreshJTable(model.getResturantItemList());
        view.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            new executeIssueReturn(new JFrame(),true);
            }
            
        });
      
    }
}
