/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.purchasereturn;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class executePurchaseReturn  extends JDialog {
     PurchaseReturnView prview ;
     PurchaseReturnModel prmodel;
      PurchaseReturnController prcontrol;
     
    public executePurchaseReturn(JFrame parent,boolean modal){
        super(parent,modal);     
        prview = new PurchaseReturnView(parent,modal);
        prmodel = new PurchaseReturnModel();
       // prm.getItemList();
         prcontrol = new PurchaseReturnController(prmodel, prview);
        
       
        prview.setVisible(true);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(new Runnable(){
           

            @Override
            public void run() {
                //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                executePurchaseReturn executePurchaseReturn = new executePurchaseReturn(new JFrame(),true);
            }
        });
       
        
    }
}
