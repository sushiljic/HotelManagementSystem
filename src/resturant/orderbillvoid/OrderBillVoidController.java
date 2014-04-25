/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.orderbillvoid;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author SUSHIL
 */
public class OrderBillVoidController {
    private OrderBillVoidView VoidView ;
    private OrderBillVoidModel VoidModel;
//    private OrderBillVoidController VoidController;
    
    public OrderBillVoidController(OrderBillVoidModel model,OrderBillVoidView view){
        VoidView = view;
        VoidModel = model;
        /*
         * 
         */
        VoidView.addSearchListener(new SearchListener());
        VoidView.addCancelListener(new SearchListener());
        VoidView.addVoidListener(new SearchListener());
        
    }
    public class SearchListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            if(VoidView.getSearchBillId() == ""){
//                JOptionPane.showMessageDialog(VoidView, "Are You Crazy? Searching Blank bills");
//                return;
//            }
            try{
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getActionCommand().equalsIgnoreCase("Search")){
            int billid = VoidView.getSearchBillId();
            
            if(VoidModel.checkBillId(billid)){
                /*
                 * procced to  next stage
                 */
                VoidView.setBillId(String.valueOf(billid));
                VoidView.refreshJTableBillInfo(VoidModel.getBillItemList(billid));
                VoidView.refreshJTableOrderedList(VoidModel.getOrderList(billid));
                
                VoidView.setBillAmount(VoidModel.getSalesList(billid));
//                for(double db:VoidModel.getSalesList(billid)){
//                    System.out.println(db);
//                }
                VoidView.DialogOrderBillVoid.pack();
                VoidView.setVisible(false);
                VoidView.DialogOrderBillVoid.setModal(true);
                VoidView.DialogOrderBillVoid.setVisible(true);
                
            }
            else{
                JOptionPane.showMessageDialog(VoidView, "Bill Id Not Found.");
            }
        }
        if(e.getActionCommand().equalsIgnoreCase("Void")){
            //checking whethe it is legal to valid or not with system date
           Date SystemDate= VoidModel.returnSystemDate();
           Date BillDate = VoidModel.returnBillDate(Integer.parseInt(VoidView.getBillId()));
            if( !SystemDate.equals(BillDate)){
                JOptionPane.showMessageDialog( VoidView.DialogOrderBillVoid, "Bill Cannot be Void.\n Bill Date ("+BillDate+") is not equal to  current System Date ("+SystemDate+")");
                return;
            }
            VoidModel.voidOrderBill(VoidModel.convertDefaultTableModelToObject(VoidView.gettblBillInfo()), VoidModel.convertDefaultTableModelToObject(VoidView.gettblOrderList()),Integer.parseInt(VoidView.getBillId()));
              VoidView.setSearchBillId(0);
            VoidView.clearBillAmount();
            VoidView.gettblBillInfo().setRowCount(0);
            VoidView.DialogOrderBillVoid.setVisible(false);
            
            VoidView.setVisible(true);
        }
        if(e.getActionCommand().equalsIgnoreCase("Cancel")){
            VoidView.setSearchBillId(0);
            VoidView.clearBillAmount();
            VoidView.gettblBillInfo().setRowCount(0);
            VoidView.DialogOrderBillVoid.setVisible(false);
            
            VoidView.setVisible(true);
        }
        
        }
        catch(Exception se){
        JOptionPane.showMessageDialog(VoidView, se+"SearchListener");
             }
        }
        
    }
    
}
