/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.threshold;

import hotelmanagementsystem.MainFrameView;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecuteThreshold extends JDialog {
     ThresholdModel themodel;
     ThresholdView theview;
      ThresholdController thecontroller;
     public ExecuteThreshold(JFrame parent,boolean modal){
         super(parent,modal);
          themodel = new ThresholdModel();
       theview = new ThresholdView(parent,modal);
       
       thecontroller = new ThresholdController(themodel,theview,(MainFrameView)parent);
         theview.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
     
       //for loading itemname and threshold in table
    
     
  // JOptionPane.showMessageDialog(theview,themodel.returnItemIdplusThreshold(theview.getValueFromTable()) ); 
    //JOptionPane.showMessageDialog(theview, theview.getValueFromTable());
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             new ExecuteThreshold(new JFrame(),true);
            }
            
        });
    }
}
