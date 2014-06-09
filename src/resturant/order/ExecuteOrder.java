/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.order;

import hotelmanagementsystem.MainFrameView;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import reusableClass.Function;
import systemdate.SystemDateModel;

/**
 *
 * @author SUSHIL
 */
public class ExecuteOrder extends SystemDateModel {
   public   OrderModel OrderModel;
   public    OrderView OrderView;
    public  OrderController OrderController;
//    public MainFrameView mainview;
    public ExecuteOrder( MainFrameView view){
         
      try{   
      Object[] dateinfo = Function.returnSystemDateInfo();
      if(dateinfo[2] == Boolean.TRUE && dateinfo[3] == Boolean.FALSE){
           OrderModel = new OrderModel();
        OrderView = new OrderView();
        OrderController = new OrderController(OrderModel,OrderView,view);
         
        OrderView.setVisible(true);
        /*
        increase the counter for the mainview order
        */
        view.incrementCountForOrder();
      }
      else{
          
         JOptionPane.showMessageDialog(OrderView, "Please First Open the Date to Perform Order Transaction.");
//         return;
         
       
      }
      
       
      }
      catch(Exception e){
          JOptionPane.showMessageDialog(OrderView, e+"from constructor "+getClass().getName());
      }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      
        
       // ExecuteTableCrud crud = new ExecuteTableCrud();
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            new ExecuteOrder(new MainFrameView());
            }
            
        });
    }
}
