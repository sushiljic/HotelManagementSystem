/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package resturant.creditpayment;

import hotelmanagementsystem.MainFrameView;
import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import reusableClass.DisplayMessages;
import reusableClass.Function;

/**
 *
 * @author SUSHIL
 */
public class CreditPaymentController {
    private CreditPaymentModel creditPaymentModel ;
    private CreditPaymentView creditPaymentView;
    private MainFrameView mainview;
    
    public CreditPaymentController(CreditPaymentModel model,CreditPaymentView view,MainFrameView mview){
        creditPaymentModel = model;
        creditPaymentView = view;
        mainview = mview;
        //adding the handler
        creditPaymentView.addComboBoxDepartmentNameListener(new ComboBoxPaymentListener());
        creditPaymentView.addComboBoxCustomerNameListener(new ComboBoxPaymentListener());
        creditPaymentView.addBtnSaveListener(new CreditPaymentSaveCancelListener());
        creditPaymentView.addBtnCancelListener(new CreditPaymentSaveCancelListener());
        creditPaymentView.addShortcutForCreditPayment(new ShortcutForCreditPayment());
        //to  make the bill id false when close is listener
        creditPaymentView.addWindowListener(new CreditPaymentWindowListener());
        try{
            //for combodepartment box
         creditPaymentView.setComboBoxDepartmentName(Function.returnSecondColumn(Function.getRespectiveDepartment(mainview.getUserId())));
         //if it has only one element select it order wise add select into it
            int combosize = creditPaymentView.returnComboBoxDepartmentName().getModel().getSize();
            if(combosize >1){
                Function.AddSelectInCombo(creditPaymentView.returnComboBoxDepartmentName());
            }
            else{
                if(combosize == 1){
                creditPaymentView.returnComboBoxDepartmentName().setSelectedIndex(0);
                }
            }
          //loading the bill number
          int billid = Function.returnCurrentItentityBillId("notneeded but it retrieve from generate_bill");
          creditPaymentView.setBillId(billid);
         // for loading customer combobox
          creditPaymentView.setComboBoxCustomerName(Function.returnSecondColumn(creditPaymentModel.getCustomerInfoObject()));
          Function.AddSelectInCombo(creditPaymentView.returnComboBoxCustomerName());
          
        }
        catch(Exception se){
            DisplayMessages.displayError(view, se.getMessage()+" "+getClass().getName() ,"CreditPaymentController");
        }
        
        
        
    }
    public class ComboBoxPaymentListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        try{
            JComboBox jc = (JComboBox)e.getSource();
            if(jc == creditPaymentView.returnComboBoxDepartmentName()){
                 Object[] depinfo = null;
               Object[][] fulldep = Function.getRespectiveDepartment(mainview.getUserId());
               if(jc.getSelectedItem().equals("SELECT")){
                   creditPaymentView.setDepartmentId(0);
                 
               } 
               else{
                  for(Object[] de:fulldep){
                      if(jc.getSelectedItem().equals(de[1])){
                          //set deparmtnet id
                          creditPaymentView.setDepartmentId(((Number)de[0]).intValue());
//                          System.out.println(creditPaymentView.getDepartmentId());
                          break;
                      }
                  } 
               }
            }
            //if event is triggered from customer
            if(jc == creditPaymentView.returnComboBoxCustomerName()){
               Object[][] fullcustomerinfo = creditPaymentModel.getCustomerInfoObject();
               if(jc.getSelectedIndex() == 0){
                   creditPaymentView.setCustomerId(0);
                   creditPaymentView.setAmountReceivable(0.0);
               }
               else{
                   for(Object[] data:fullcustomerinfo){
                       if(jc.getSelectedItem().equals(data[1])){
                           //calculate the  reaming amount to be paid
                           int customerid = ((Number)data[0]).intValue();
                           creditPaymentView.setCustomerId(customerid);
                           BigDecimal total = creditPaymentModel.getCustomerReceivableAmount(customerid, creditPaymentView.getDepartmentId()).setScale(2, RoundingMode.HALF_UP);
                           creditPaymentView.setAmountReceivable(total.doubleValue());
                           break;
                       }
                   }
               }
            }
        }
        catch(Exception se){
            DisplayMessages.displayError(mainview, se.getMessage()+"from ComboBoxPaymentListener "+getClass().getName(), "Error Window");
        }
        
        }
        
    }
    public class CreditPaymentSaveCancelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        try{
         if(e.getActionCommand().equalsIgnoreCase("Save")){
             //checking 
             if(creditPaymentView.getDepartmentId() == 0){
                 JOptionPane.showMessageDialog(mainview, "Select the Department");
                 return;
             }
             if(creditPaymentView.getCustomerId() == 0){
                 JOptionPane.showMessageDialog(mainview, "Select the Customer");
                 return;
             }
             //asking with yes or not
             if(DisplayMessages.displayInputYesNo(mainview, "Do You Want to Save The Payment", "Payment Window")){
             creditPaymentModel.addCreditPayment(creditPaymentView.getCreditPayment(),mainview.getUserId());
             creditPaymentView.clearAll();
             //load new bill
             int billid = Function.returnCurrentItentityBillId("");
             creditPaymentView.setBillId(billid);
             }
             
         }   
         if(e.getActionCommand().equalsIgnoreCase("Cancel")){
             if(DisplayMessages.displayInputYesNo(mainview, "Do You Want to Cancel the Payment", "Payment Window")){
                 creditPaymentView.clearAll();
             }
         }
        }
        catch(Exception se){
            DisplayMessages.displayError(creditPaymentView, se.getMessage()+" from CreditPaymentSaveCancelListener "+getClass().getName(),"Error");
        }
        }
        
    }
    public class CreditPaymentWindowListener extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e){
            //making the current bill id status false
            Function.MakeCurrentItentityIdFalse(creditPaymentView.getBillId());
            creditPaymentView.dispose();
        }
    }
    public class ShortcutForCreditPayment implements KeyEventDispatcher{

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
          
            try{
                if(creditPaymentView.isVisible()){
              if(e.getID() == KeyEvent.KEY_PRESSED){  
                  if(e.getKeyCode() == KeyEvent.VK_S && e.isControlDown()){
                     creditPaymentView.getBtnSave().doClick();
                  }
                  if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                      
                      creditPaymentView.getBtnCancel().doClick();
                  }
                  if(e.getKeyCode() == KeyEvent.VK_X && e.isControlDown()){
                      Function.MakeCurrentItentityIdFalse(creditPaymentView.getBillId());
                        creditPaymentView.dispose();
                  }
              }   
            }
            }
            catch(Exception se){
                DisplayMessages.displayError(mainview, se.getMessage()+"from  ShortcutForCreditPayment "+getClass().getName(), "Error");
            }
            return false;
        }
        
    }
    
}
