/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.directbill;

import hotelmanagementsystem.MainFrameView;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import resturant.order.OrderModel;
import resturant.orderbill.OrderBillModel;
import reusableClass.Function;
import systemdate.SystemDateModel;

/**
 *
 * @author SUSHIL
 */
public class ExecuteDirectBill extends SystemDateModel{
//   public DirectBillModel OrderBillModel;
   public OrderBillModel OrderBillModel;
    public  DirectBillView OrderBillView;
//    public  DirectBillController OrderBillController;
    public  DirectBillControllercopy OrderBillController;
    public ExecuteDirectBill(MainFrameView mainview) {
           
          
     try{   
      Object[] dateinfo = Function.returnSystemDateInfo();
      if(dateinfo[2] == Boolean.TRUE && dateinfo[3] == Boolean.FALSE){
//          OrderBillModel = new DirectBillModel();
          OrderBillModel = new OrderBillModel();
          OrderBillView = new DirectBillView();
//          OrderBillController = new DirectBillController(OrderBillModel,OrderBillView,mainview);
          OrderBillController = new DirectBillControllercopy(OrderBillModel,OrderBillView,mainview);
          OrderBillView.setVisible(true);
          /*
          increase the counter for directbill
          */
          mainview.incrementCountForDirectPay();
      }
      else{
         JOptionPane.showMessageDialog(OrderBillView, "Please First Open the Date to Perform Order Transaction.");
//        OrderBillView.setClosed(true);
      }
      }
      catch(HeadlessException e){
          e.printStackTrace();
          JOptionPane.showMessageDialog(OrderBillView, e+"from constructor "+getClass().getName());
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
           new ExecuteDirectBill(new MainFrameView());
            }
        });
    }
}
