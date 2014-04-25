/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package systemdate;

import javax.swing.JOptionPane;
import resturant.order.OrderView;
import resturant.orderbill.OrderBillView;

/**
 *
 * @author SUSHIL
 */
public class ExecuteDateClose {
    OrderView orderview;
    SystemDateModel dateModel;
//    OrderBillView billview;
    
    public ExecuteDateClose(){
//        this.orderview = orderview;
      
     dateModel = new SystemDateModel();
          
       ProcessCloseDate();
    }
    public void ProcessCloseDate(){
      int num =   dateModel.returnOrderedListNumber();
      
      if(num != 0){
      
          String st = num == 0?"is":"are";
          JOptionPane.showMessageDialog(orderview, "There "+st+" "+ num+" order pending.\nPlease Check Out all Those Order to Close Date");
          return;
      }
       dateModel.closeSystemDate();
      }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      new ExecuteDateClose();
    }
    
}
