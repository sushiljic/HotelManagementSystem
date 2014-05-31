/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.orderbill;

import hotelmanagementsystem.MainFrameView;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import reusableClass.Function;
import systemdate.SystemDateModel;

/**
 *
 * @author SUSHIL
 */
public class ExecuteOrderBill extends SystemDateModel{
   public OrderBillModel OrderBillModel;
    public  OrderBillView OrderBillView;
     public  OrderBillController OrderBillController;
        public ExecuteOrderBill(MainFrameView mainview) {
          
            Object[] dateinfo = Function.returnSystemDateInfo();
       if(dateinfo[2] == Boolean.TRUE && dateinfo[3] == Boolean.FALSE){
            OrderBillModel = new OrderBillModel();
             OrderBillView = new OrderBillView();
            
            OrderBillController = new OrderBillController(OrderBillModel,OrderBillView,mainview);
           OrderBillView.setVisible(true);
           /*
           increses the counter for in mainframeview
           */
           mainview.incrementCountForPay();
       }
       else{
           JOptionPane.showMessageDialog(OrderBillView, "Please First Open the Date to Perform Billing Transaction.");
//        OrderBillView.setClosed(true);
       }
        }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
             //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           new ExecuteOrderBill(new MainFrameView());
            }
        });
    }
}
