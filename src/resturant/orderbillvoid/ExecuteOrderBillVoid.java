/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.orderbillvoid;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import reusableClass.DisplayMessages;
import reusableClass.Function;

/**
 *
 * @author SUSHIL
 */
public class ExecuteOrderBillVoid extends JDialog {
    public ExecuteOrderBillVoid(JFrame parent,boolean modal){
        super(parent,modal);
        try{
        if(Function.getSystemDateEnable()){
            Object[] dateinfo = Function.returnSystemDateInfo();
            if(dateinfo[2] == Boolean.TRUE && dateinfo[3] == Boolean.FALSE){
            OrderBillVoidView orderBillVoidView = new OrderBillVoidView(parent, modal);
            OrderBillVoidModel orderBillVoidModel = new OrderBillVoidModel();

            OrderBillVoidController orderBillVoidController = new OrderBillVoidController(orderBillVoidModel, orderBillVoidView);
            orderBillVoidView.setVisible(true);
            }
            else{
                DisplayMessages.displayError(parent, "Please First Open the Date to Perform Transaction.","Error");
            }
        
        }
        else{
            OrderBillVoidView orderBillVoidView = new OrderBillVoidView(parent, modal);
            OrderBillVoidModel orderBillVoidModel = new OrderBillVoidModel();

            OrderBillVoidController orderBillVoidController = new OrderBillVoidController(orderBillVoidModel, orderBillVoidView);
            orderBillVoidView.setVisible(true);
        }
        }
        catch(Exception se){
            se.printStackTrace();
            DisplayMessages.displayError(this,"Error from orderbillVoid" , "Error");
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
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            new ExecuteOrderBillVoid(new JFrame(),true);
            }
            
        });
    }
}
