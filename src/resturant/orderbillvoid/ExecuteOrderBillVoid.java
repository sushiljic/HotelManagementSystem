/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.orderbillvoid;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecuteOrderBillVoid extends JDialog {
    public ExecuteOrderBillVoid(JFrame parent,boolean modal){
        super(parent,modal);
        OrderBillVoidView orderBillVoidView = new OrderBillVoidView(parent, modal);
        OrderBillVoidModel orderBillVoidModel = new OrderBillVoidModel();
        
        OrderBillVoidController orderBillVoidController = new OrderBillVoidController(orderBillVoidModel, orderBillVoidView);
        orderBillVoidView.setVisible(true);
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
            new ExecuteOrderBillVoid(new JFrame(),true);
            }
            
        });
    }
}
