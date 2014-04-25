/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.issue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class executeIssue extends JDialog {
     IssueModel model; 
              IssueView view;
               IssueController icontrol;
    public executeIssue(JFrame parent,boolean modal){
       super(parent,modal);
        model = new IssueModel();
        view = new IssueView(parent,modal);
         
        
       // model.getItemInfoForIssue();
        
      icontrol  = new IssueController(model,view);
       view.setVisible(true);
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
       /*
         * thid give the list of the itemname into the combo
         */
       
//        view.setcomboItemName(model.returnItemName(model.getItemInfoForIssue()));
//        /*
//         * this by default display the issue table 
//         */
//        view.refreshJTable(model.getIssueList());
      SwingUtilities.invokeLater(new Runnable(){

          @Override
          public void run() {
            //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          new executeIssue(new JFrame(),true);
          }
          
      });
        
    }
}
