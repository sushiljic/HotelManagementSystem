/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.directbill;

import hotelmanagementsystem.MainFrameView;
import java.awt.HeadlessException;
import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import report.bill.Bill;
import resturant.complimentary.ComplimentaryModel;
import resturant.customer.CustomerController;
import resturant.customer.CustomerModel;
import resturant.customer.CustomerView;
import resturant.order.OrderModel;
import resturant.orderbill.OrderBillModel;
import reusableClass.DisplayMessages;
import reusableClass.Function;
import reusableClass.Validator;
import systemdate.SystemDateModel;

/**
 *
 * @author SUSHIL
 */
public class DirectBillControllercopy  extends SystemDateModel{
    DirectBillView obview;
    OrderBillModel obmodel;
    OrderModel orderModel;
    public MainFrameView mainview;
    public ComplimentaryModel complimentaryModel;
     String[][] comp;
     BigDecimal TotalAmount;
     
     Double[] TaxList = new Double[2];
    
   public  DirectBillControllercopy(OrderBillModel model,DirectBillView view,MainFrameView mainview){
        
       obmodel = model;
       obview = view;
       this.mainview = mainview;
       orderModel = new OrderModel();
       complimentaryModel = new ComplimentaryModel();
  
//       obview.addComboCustomerNameForOrderListener(new ComboListener());
//       obview.addComboCustomerNameForBillListener(new BillComboCustomerListener());
       
       /*
       adding listener for menuname
       */
       obview.addComboMenuNameListener(new ComboListener());
       obview.addComboDepartmentNameListener(new ComboListener());
       obview.addAdd(new MenuCrudListener());
       obview.addTextQuantityListener(new TextQuantityListener());
       obview.addDoubliClickListenerForComplimentarySelect(new DoubleClickListener());
       obview.addSaveListener(new SimpleSaveCancelListener());
       obview.addCancelListener(new SimpleSaveCancelListener());
        // firing itemlistener for cash and credit
       obview.addItemListenerForCashCredit(new radioCashCreditListener());
       obview.addComboClickCustomerNameActionListener(new ComboCustomerNameListener());
        /*
         * adding the save and cancel for bill 
         not need since it is reduced fro main form of direct bill layour
       obview.addBillSaveListener(new BillInsertListener());
       obview.addBillCancelListener(new BillInsertListener());
       obview.addBillSaveAndPrintListener(new BillInsertListener());
       */
        /*
         * listening for the checkbox for complimentary
         */
       obview.addCheckBoxComplimentaryListener(new CheckBoxComplimentaryListener());
       obview.addCheckBoxComplimentaryItemListener(new CheckBoxComplimentaryItemListener());
        /*
        for adding the add customer fucntion in bill print window
        */
        obview.addCustomerWindow(new AddCustomerWindowListener());
        /*
         * adding windows adapter for clos action 
         */
//      obview.addWindowListener(new WindowCloseListener());
        obview.addInternalFrameListener(new InternalFrameCloseListener());
        ///adding the document listener for tracking teh rate
        obview.addSVCDocumentListener(new SVCDocumentListener());
        obview.addDiscountDocumentListener(new DiscountDocumentListener());
        obview.addTenderedAmountDocumentListener(new TenderedDocumentListener());
//      obview.addGrandTotalDocumentListener(new GrandTotalDocumentListener());//checking and it might not be needed.
        obview.addCheckSVCListener(new SVCCheckListener() );
        obview.addCheckDiscountListener(new DiscountCheckListener());
        //adding check listener
        obview.addCheckSVCListener(new SVCCheckListener() );
        obview.addCheckDiscountListener(new DiscountCheckListener());
        //adding enterlisten for tender amount
        obview.addTenderedAmountActionListener(new addEnterFocusListener());
        /*
        adding controller for deligate for keyboardmanager
        */
        obview.addShortcutForDirectBill(new ShortcutForDirectBill());
        obview.addAbstractActionListener(new AsbtractActionListener());
        try{
                 obview.setcomboDepartmentName(Function.returnSecondColumn(obmodel.getRespectiveDepartment(mainview.getUserId())));
            //if it has only one element select it order wise add select into it
            int combosize = obview.returnComboDepartmentName().getModel().getSize();
            if(combosize >1){
                obview.AddSelectInCombo(obview.returnComboDepartmentName());
            }
            else{
                if(combosize == 1){
                obview.returnComboDepartmentName().setSelectedIndex(0);
                }
            }
                 /*
        * getting the bill number
        */
       int billid = Function.returnCurrentItentityBillId("bill");
       obview.setBillId(String.valueOf(billid));
       obview.setMainBillId(billid);
       /*
        * listing the orderlist in jtable
        */
      
        /*
         * adding columnname in table order
         */
        String[] orderColumnName =  new String[]{"ID","Menu Name","Quantity","Rate","Total Amount","Complimentary"};
        DefaultTableModel orderTableModel = new DefaultTableModel(null,orderColumnName){
            @Override
            public boolean isCellEditable(int row,int columns){
                return false;
            }
            @Override
            public Class getColumnClass(int c){
               return getValueAt(0, c).getClass();
            }
        };
        obview.refreshJTableBillInfo(orderTableModel);
//       obview.refreshJTableOrderedList(obmodel.getOrderInfo());
       /*
        * loading customer name
        */
//         obview.setComboMenuName(obmodel.returnMenuName(obmodel.getMenuInfo(obview.getDepartmentId())));
//         obview.AddSelectInCombo(obview.returnComboMenuName());
//       obview.setComboCustomerName(obmodel.returnMenuName(obmodel.getCustomerInfoObject()));
       obview.setComboCustomerNameForBill(Function.returnSecondColumn(obmodel.getCustomerInfoObject()));
       obview.AddSelectInCombo(obview.returnComboCustomerNameForBill());
       //loading the  complimentary list in the combobox
       obview.setComboComplimentaryName(Function.returnSecondColumn(complimentaryModel.getComplimentaryInfo()));
       obview.AddSelectInCombo(obview.returnComboComplimentaryName());
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(mainview, se+"from constructor "+ getClass().getName());
        }
    }
   
      /*
             * this is listener and event for enter and double click in addorder
             */
              private void createKeybindings(JTable table) {
           table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "Enter");
               table.getActionMap().put("Enter",new SearchAddOrderTableEnterListener());
            }
   
        public class MenuCrudListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("Add")){
             if(obview.getMenuId() == 0){
//                    System.out.println(orderview.getMenuId());
                JOptionPane.showMessageDialog(obview, "Please Select  any Item");
                    return;    
                }
              
//                if(obview.getQuantity() == 0.0){
//                    JOptionPane.showMessageDialog(obview, "Empty Field detected in Quantity");
//                    return;
//                }
                 if(obview.getQuantity() == 0.0){
                    JOptionPane.showMessageDialog(obview, "Quantiy cannot be Zero");
                    return;
                }
                BigDecimal TotalAmount;
               
               TotalAmount = new BigDecimal(obview.getQuantity()).setScale(3, RoundingMode.HALF_UP).multiply(obview.getRate()).setScale(2, RoundingMode.HALF_UP);
               /*
                * checking it availability
                */
               if(orderModel.checkTrackable(obview.getMenuId())){
                   if(orderModel.checkHybrid(obview.getMenuId())){
                       String status = new String();
//                        System.out.println(orderview.getQuantity());
                       boolean flag = false;
                       //if it hybrid type
                       String[][] hybriditem = orderModel.checkHybridTrackableItem(obview.getMenuId(), obview.getQuantity());
                       for (String[] hybriditem1 : hybriditem) {
                           int x = new BigDecimal(hybriditem1[1]).signum();
                           if (x<0) {
                               status += "MenuItem is Insufficient  to add due to insufficient amount in " + hybriditem1[0] + "\n";
                               flag =true;
                           }
                       }
                       if(flag == true){
                           JOptionPane.showMessageDialog(obview, status);
                           return;
                       }
                       orderModel.checkHybridTrackableItemthreshold(obview.getMenuId());
//                        System.out.println("wala");
                   }
                   else{
                        
                     
                       BigDecimal net = orderModel.checkSingleTrackableItem(obview.getMenuId(), obview.getQuantity());
                   
                       int i = net.signum();
                       if(i < 0){
                           JOptionPane.showMessageDialog(obview, "Item is Insufficient to Add for order.First Issue the item");
                           return;
                       }
                       orderModel.checkSingleTrackableItemThreshold(obview.getMenuId());
//                       System.out.println(orderview.getQuantity());
                   }
                  // return;
               }
             Object[] row = new Object[]{obview.getMenuId(),obview.getComboMenuName(),new BigDecimal(obview.getQuantity()).setScale(3, RoundingMode.HALF_UP),obview.getRate(),TotalAmount,false};
//                obview.gettblBillInfo().addRow(row);
                 //check whether same menu is in the row if there is add it into the row
             boolean SameMenuIdentifier = false;
             for( int i=0;i<obview.gettblBillInfo().getRowCount();i++){
                 if((Integer.parseInt(obview.gettblBillInfo().getValueAt(i, 0).toString()))==obview.getMenuId()){
//                     JOptionPane.showMessageDialog(mainview,orderview.getTableOrderList().getValueAt(i, 2));
                      BigDecimal quantity = new BigDecimal(obview.gettblBillInfo().getValueAt(i, 2).toString());
                     
                     quantity = quantity.add(BigDecimal.valueOf(obview.getQuantity()));
                     final BigDecimal qty = quantity;
                     final BigDecimal amount = obview.getRate().multiply(quantity).setScale(2, BigDecimal.ROUND_HALF_UP);
                     final int j = i;
//                      System.err.print(quantity);
//                     SwingUtilities.invokeLater(new Runnable(){
//
//                         @Override
//                         public void run() {
                         obview.gettblBillInfo().setValueAt(qty, j, 2);
                         obview.gettblBillInfo().setValueAt(amount, j, 4);
//                         }
//                         
//                     });
                     
                     SameMenuIdentifier = true;
                     break;
                 }
             }
             if(!SameMenuIdentifier){
             obview.gettblBillInfo().addRow(row);
             }
                
               
                obview.clearAddData();
                obview.setSaveEditableTrue();
                TaxList = obmodel.getChargeInfo();
                obview.setCheckBoxSVCTrue();
                obview.setSVC(TaxList[0]);
                obview.setVAT(TaxList[1]);
                obview.setDiscount(0.0);
                Double TotalAmountBill =0.0;
                for(int i=0; i<obview.gettblBillInfo().getRowCount();i++){
                    TotalAmountBill += ((Number)obview.gettblBillInfo().getValueAt(i, 4)).doubleValue();
                }
               obview.setTotal(TotalAmountBill);
                //not a permanent solution
               obview.setSVCCheck(true);
               obview.setDiscountCheck(true);
               obview.setDiscountCheck(false);
//             obview.requestFocusOnTenderedAmount();
                 
            }   
        }
        catch(HeadlessException | NumberFormatException me){
            JOptionPane.showMessageDialog(mainview, me.getMessage()+"from MenuCrudListener");
        }
        }
            
        }
        public class TextQuantityListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            obview.addfocusonAddButton();
           
            
        }
        catch(Exception te){
            JOptionPane.showMessageDialog(obview, te+"from textQuantityListener");
        }
        }
            
        }
        public class ComboListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
              if(e.getActionCommand().equalsIgnoreCase("ComboMenuName")){
               //  System.out.println("wala");
                Object[] item = null;
                Object[][] MenuInfo = orderModel.getMenuInfo(obview.getDepartmentId());
                JComboBox comboMenu = (JComboBox) e.getSource();
                if(comboMenu.getSelectedIndex() == 0){
                     obview.setMenuId(0);
//                 System.out.println(orderview.getMenuId());
                 obview.setRate("0");
//                 System.out.println(orderview.getRate());
//                 obview.setItemBaseUnit("");
                    
                }
                else
                {
                for (Object[] MenuInfo1 : MenuInfo) {
                    if (comboMenu.getSelectedItem().equals(MenuInfo1[1])) {
                        item = MenuInfo1;
                        break;
                    }
                }
                /*
                 * perfom some operation
                 */
                 obview.setMenuId(Integer.parseInt(item[0].toString()));
                 obview.setRate(item[2].toString());
//                 obview.setItemBaseUnit(item[2].toString());
                }
            }
              if(e.getActionCommand().equalsIgnoreCase("ComboCustomerName")){
                     Object item[] = null;
                    JComboBox comboMenu = (JComboBox) e.getSource();
            if(comboMenu.getSelectedIndex() == 0){
                 obview.setOrderCustomerId(0);
            }        
            else{
            for (Object[] customerInfoObject : obmodel.getCustomerInfoObject()) {
                if (customerInfoObject[1].equals(obview.getComboCustomerNameForBill())) {
                    item = customerInfoObject;
                    break;
                }
            }
            obview.setOrderCustomerId(Integer.parseInt(item[0].toString()));
              }
              }
             //for department id
              if(e.getActionCommand().equalsIgnoreCase("comboDepartmentName")){
                 Object[][] departmentdata= obmodel.getRespectiveDepartment(mainview.getUserId());
                JComboBox dep = (JComboBox)e.getSource();
                 if(dep.getSelectedItem().equals("SELECT")){
                     obview.setDepartmentId(0);
                  
                     obview.setComboMenuName(new String[]{});
                     
                 }
                 else{
                     for(Object[] dat:departmentdata){
                         if(dat[1].equals(dep.getSelectedItem())){
                             obview.setDepartmentId(Integer.parseInt(dat[0].toString()));
                             //loading the repective item according to department  
                             obview.setComboMenuName(Function.returnSecondColumn(orderModel.getMenuInfo(obview.getDepartmentId())));
                             obview.AddSelectInCombo(obview.returnComboMenuName());
                            
                             break;
                             
                         }
                     }
                 }
              }
        }
        catch(Exception ce){
            ce.printStackTrace();
            JOptionPane.showMessageDialog(obview, ce+"from comboListener");
        }
        }
            
        }
        public class BillInsertListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("BillSave")){
                if(!obview.getPaymentType()){
                    if(obview.getCustomerId() == 0){
                        JOptionPane.showMessageDialog(obview.JDialogBillPayment, "For Credit Transaction, Customer must be Choosed.");
                        return;
                    }
                }
                
                
                String[] orderarray = obview.OrderArray.toArray(new String[obview.OrderArray.size()]);
                String[][] complilist = obview.ComplimentaryList.toArray(new String[obview.ComplimentaryList.size()][]);
//               System.out.println(comp.length);
//                JOptionPane.showMessageDialog(mainview, mainview.getUserId());
                obmodel.AddDirectBill(Function.convertDefaultTableModelToObject(obview.gettblBillInfo()),obview.getBillRate(), obview.getBillOtherInfo(),obview.getOrderCustomerId(),mainview.getUserId(),obview.getDepartmentId());
                obview.JDialogBillPayment.setVisible(false);
                 if(DisplayMessages.displayInputYesNo(obview, "Do you Want To Print The Bill", "Direct Bill Print"))
                {
//                 SwingUtilities.invokeLater(new Runnable(){
//
//                     @Override
//                     public void run() {
                    try{
                          Bill direct = new Bill(obview.getBillParam(),"directBill.jrxml");
                          direct.printBill(); 
                    }
                    catch(Exception se){
                        DisplayMessages.displayError(obview, "Printing Error", "From DirectBill");
                    }
//                     }
//                     
//                 });
                  
                }
                  obview.clearOrderBill();
                    obview.gettblBillInfo().setRowCount(0);
                    /*
                    * here is manipulation for refreshing the data in order window if it is open
                    */
//                 JInternalFrame[] iframes =   mainview.desktop.getAllFrames();
//                   for (JInternalFrame iframe : iframes) {
//                     if(iframe.getTitle().equalsIgnoreCase("Order Window")){
//                         iframe.dispose();
//                         ExecuteOrder order = new ExecuteOrder(mainview);
//                         mainview.desktop.add(order.OrderView);
//                     }
//                          /*
//                   here is manipulation and update of tabl
//                   */
////                       if(iframe.getTitle().equalsIgnoreCase("Table Status")){
////                           iframe.dispose();
////                           ExecuteTableStatusView tablestatus = new ExecuteTableStatusView(mainview);
////                           mainview.desktop.add(tablestatus.view);
////                       }
//                   }
                    obview.setSaveEditableFalse();
                    //print the bill
                    
                    
                   
                    int newbillid = Function.returnCurrentItentityBillId("bill");
                    obview.setBillId(String.valueOf(newbillid));
                    obview.setMainBillId(newbillid);
                     
                    
            }
            else if(e.getActionCommand().equalsIgnoreCase("BillSaveAndPrint")){
             /*     if(!obview.getPaymentType()){
                    if(obview.getCustomerId().isEmpty()){
                        JOptionPane.showMessageDialog(obview, "For Credit Transaction, Customer must be Choosed.");
                        return;
                    }
                }
                int[] orderarray = new int[]{Integer.parseInt(obview.getOrderId())};
                obmodel.AddBill(obmodel.convertDefaultTableModelToObject(obview.gettblBillInfo()),obview.getBillRate(), obview.getBillOtherInfo(),orderarray);
                obview.refreshJTableOrderedList(obmodel.getOrderInfo());
                obview.JDialogBillPayment.setVisible(false);
                  obview.clearOrderBill();
                    obview.gettblBillInfo().setRowCount(0);
                    obview.setSaveEditableFalse();
                    obview.setBillId(String.valueOf(obmodel.returnCurrentItentityId("bill")));
                    * */
                JOptionPane.showMessageDialog(obview.JDialogBillPayment, "Module to be added");
            }
            else  if(e.getActionCommand().equalsIgnoreCase("BillCancel")){
                if(DisplayMessages.displayInputYesNo(obview.JDialogBillPayment, "Do you Want to Cancel Billing","Bill Cancel Window"))
                {
                    obview.JDialogBillPayment.setVisible(false);
                    obview.requestFocusOnTenderedAmount();
                   obview.ComplimentaryList.clear();
                   
                    
                }
            }
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(obview, se+" from BillInsertListener");
        }
        }
            
        }
        public class SimpleSaveCancelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("Save")){
              //checking whether  the date has been closed by the admin
                Object[] dateinfo = Function.returnSystemDateInfo();
                   if(dateinfo[2] == Boolean.TRUE && dateinfo[3] == Boolean.FALSE){
                       
                   }
                   else{
                      JOptionPane.showMessageDialog(obview, "Please First Open the Date to Perform Order Transaction.");
                    return;
                   }
              
            if(obview.getGrandTotal1().isNaN() || obview.getTotal() == 0.0){
                JOptionPane.showMessageDialog(obview, "Please Select any Item");
                return;
            }
           if(obview.getTenderedAmount() < obview.getGrandTotal1()){
               obview.setPaymentTypeForCredit(Boolean.TRUE);
              obview.radioCash.setEnabled(false);
               obview.radioCredit.setEnabled(true);
              
           }
           else{
               obview.setPaymentTypeForCash(Boolean.TRUE);
               obview.radioCredit.setEnabled(false);
               obview.radioCash.setEnabled(true);
               
           }
           /* not needed in first save
            obview.JDialogBillPayment.setTitle("Bill Window For Bill No:"+obview.getBillId());
            obview.JDialogBillPayment.pack();
            obview.requestFocusOnButtonBillSave();
            obview.JDialogBillPayment.setVisible(true);
            obview.setCheckBoxComplimentaryFalse();
            */
             if(!obview.getPaymentType()){
                    if(obview.getCustomerId() == 0){
                        JOptionPane.showMessageDialog(obview.JDialogBillPayment, "For Credit Transaction, Customer must be Choosed.");
                        return;
                    }
                }
                
                
                String[] orderarray = obview.OrderArray.toArray(new String[obview.OrderArray.size()]);
                String[][] complilist = obview.ComplimentaryList.toArray(new String[obview.ComplimentaryList.size()][]);
//               System.out.println(comp.length);
//                JOptionPane.showMessageDialog(mainview, mainview.getUserId());
                obmodel.AddDirectBill(Function.convertDefaultTableModelToObject(obview.gettblBillInfo()),obview.getBillRate(), obview.getBillOtherInfo(),obview.getOrderCustomerId(),mainview.getUserId(),obview.getDepartmentId());
                obview.JDialogBillPayment.setVisible(false);
                 if(DisplayMessages.displayInputYesNo(obview, "Do you Want To Print The Bill", "Direct Bill Print"))
                {
//                 SwingUtilities.invokeLater(new Runnable(){
//
//                     @Override
//                     public void run() {
                    try{
                          Bill direct = new Bill(obview.getBillParam(),"directBill.jrxml");
                          direct.printBill(); 
                    }
                    catch(Exception se){
                        se.printStackTrace();
                        DisplayMessages.displayError(obview, "Printing Error", "From DirectBill");
                    }
//                     }
//                     
//                 });
                  
                }
                  obview.clearOrderBill();
                  obview.gettblBillInfo().setRowCount(0);
                  obview.setSaveEditableFalse();
                  int newbillid = Function.returnCurrentItentityBillId("bill");
                  obview.setBillId(String.valueOf(newbillid));
                  obview.setMainBillId(newbillid);
                     //print the bill
             
              
            }
            else if (e.getActionCommand().equalsIgnoreCase("Cancel")){
                if(DisplayMessages.displayInputYesNo(obview, "Do you Want to Cancel Billing","Bill Cancel Window"))
                {
                    obview.clearOrderBill();
                    obview.gettblBillInfo().setRowCount(0);
                    obview.setSaveEditableFalse();
//                    obview.setAddOrderEditableFalse();
                    obview.setBillId(String.valueOf(obview.getMainBillId()));    
                }
                
            }
        }
        catch(HeadlessException se){
            JOptionPane.showMessageDialog(obview, se+"for SimpleSaveCancelListener");
        }
        }
                
            }
        public class radioCashCreditListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        JRadioButton jb = (JRadioButton) e.getSource();
      /*  if(jb == obview.radioCash){
            if(obview.getTenderedAmount()<obview.getGrandTotal1()){
              //  JOptionPane.showMessageDialog(obview.JDialogBillPayment, "Since Tendered Amount is less than total amount \n So It will be credit transaction");
              // obview.setPaymentTypeForCredit(Boolean.TRUE);
               obview.setPaymentTypeForCash(Boolean.FALSE);
               //obview.radioCash.setEnabled(false);
               
            }
            else{
                obview.setPaymentTypeForCash(true);
                
                
                 
            }
        }
        if(jb == obview.radioCredit){
            if(obview.getTenderedAmount()>obview.getGrandTotal1()){
              //  JOptionPane.showMessageDialog(obview.JDialogBillPayment, "Since Tendered Amount is less than total amount \n So It will be credit transaction");
              // obview.setPaymentTypeForCredit(Boolean.TRUE);
               obview.setPaymentTypeForCredit(Boolean.TRUE);
        }
            else{
                obview.setPaymentTypeForCredit(false);
                
            }
        
        }*/
        }
            }
       
      
       
        public class CheckBoxComplimentaryListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getActionCommand().equalsIgnoreCase("Complimentary")){
            JCheckBox jb = (JCheckBox)e.getSource();
      
       if(jb.isSelected()){
                            comp = obview.ComplimentaryList.toArray(new String[obview.ComplimentaryList.size()][]);
//                            System.out.println(comp.length);
//                            for(String[] st:comp){
//                                for(String s:st){
//                                    System.out.println(s);
//                                }
//                            }
                           /*
                            * getting new deafult tabel because it hamper the source
                            */
                            int size=obview.tblBillInfo.getColumnCount();
                            String[] Header = new String[size] ;

                            for(int i=0;i<size;i++){
                                Header[i] = obview.tblBillInfo.getColumnName(i);
                            }

                            DefaultTableModel BillModel =new DefaultTableModel((Function.convertDefaultTableModelToObject(obview.gettblBillInfo())),Header){
                                 @Override
                                 public boolean isCellEditable(int row, int col){
                                return false;
                            }
                            };
           obview.refreshJTableComplimentarySelect(obmodel.getComplimentaryTable(BillModel, comp));
           obview.JDialogComplimentary.requestFocus();
           obview.JDialogComplimentary.setModal(true);
           obview.JDialogComplimentary.setTitle("Complimentary Window For Bill No:"+obview.getBillId());
           obview.JDialogComplimentary.setVisible(true);
                    }
             }
        }
            
        }
       public class CheckBoxComplimentaryItemListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      try{
            if(e.getStateChange() == 1){
//                   comp = obview.ComplimentaryList.toArray(new String[obview.ComplimentaryList.size()][]);
////                            System.out.println(comp.length);
////                            for(String[] st:comp){
////                                for(String s:st){
////                                    System.out.println(s);
////                                }
////                            }
//                           /*
//                            * getting new deafult tabel because it hamper the source
//                            */
//                            int size=obview.tblBillInfo.getColumnCount();
//                            String[] Header = new String[size] ;
//
//                            for(int i=0;i<size;i++){
//                                Header[i] = obview.tblBillInfo.getColumnName(i);
//                            }
//
//                            DefaultTableModel BillModel =new DefaultTableModel((obmodel.convertDefaultTableModelToObject(obview.gettblBillInfo())),Header){
//                                 @Override
//                                 public boolean isCellEditable(int row, int col){
//                                return false;
//                            }
//                            };
//           obview.refreshJTableComplimentarySelect(obmodel.getComplimentaryTable(BillModel, comp));
//           obview.JDialogComplimentary.requestFocus();
//           obview.JDialogComplimentary.setModal(true);
//           obview.JDialogComplimentary.setTitle("Complimentary Window For Bill No:"+obview.getBillId());
//           obview.JDialogComplimentary.setVisible(true);
                obview.setComboComplimentaryEnable(true);
            
        }
            else{
//                obview.setTextAreaComplimentaryVisibleFalse();
                if(obview.ComplimentaryList.size() >= 1 ){
                   ((JCheckBox)e.getSource()).setSelected(true);
//                   System.out.println("wala");
                   
                   return;
                }
                obview.setComboComplimentaryEnable(false);
            }
      }
      catch(Exception ie){
          JOptionPane.showMessageDialog(obview.JDialogBillPayment, ie+"from itemlistener");
      }
        }
            
        }
            public class ComboCustomerNameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("comboCustomerName")){
                Object[] item = null;
                JComboBox jc = (JComboBox)e.getSource();
                if(jc.getSelectedIndex() == 0){
                     obview.setCustomerId(0);
                }
                else{
                for (Object[] CustomerInfo : obmodel.CustomerInfo) {
                    if (obview.getcomboCustomerName().equalsIgnoreCase(CustomerInfo[1].toString())) {
                        item = CustomerInfo;
                        // flag = true;
                        break;
                    }
                }
                obview.setCustomerId(Integer.parseInt(item[0].toString()));
                obview.requestFocusOnButtonBillSave();
                }
               // System.out.println(obview.getCustomerId());
            }
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(obview, se+"From ComboCustomerNameListener");
        }
        }
                
            }
             public class DoubleClickListener extends MouseAdapter{
         @Override
         public void mouseClicked(MouseEvent me){
             if(me.getClickCount() == 2){
        JTable table = (JTable)me.getSource();
   
        if(table == obview.tblComplimentarySelect){
             ListSelectionModel listmodel = table.getSelectionModel();
               int Lead = listmodel.getLeadSelectionIndex();
             //  System.out.println("wala");
               String[] row = new String[]{table.getValueAt(Lead,0).toString(),table.getValueAt(Lead, 5).toString()};
               Double ComplimentaryDiscount = new Double(Double.parseDouble(table.getValueAt(Lead, 5).toString()));
              Object cmenuid = table.getValueAt(Lead, 0);
              
              //   String[][] comp = obview.ComplimentaryList.toArray(new String[obview.ComplimentaryList.size()][]);
               int ctn =0;
               for(int i=0;i<table.getRowCount();i++){
                //   System.out.println("from table:"+table.getValueAt(Lead, 0)+"| \n");
                
                  if(table.getValueAt(i, 0).equals(cmenuid)){
                      ctn++;
                  }
                  if(ctn >= 2){
                      JOptionPane.showMessageDialog(obview.JDialogComplimentary, "The Item:|"+table.getValueAt(Lead, 1)+"| Selected is Found more than One Time In the Bill List.So It Cannot be Added as Complimentary.");
                      return;
                  }
              }
                 obview.ComplimentaryList.add(row);
               if(obview.getBooleanDiscountPercentage()){
                   obview.setCheckBoxDiscountFalse();
                   obview.setDiscount(obview.getDiscountAmount());
                  // JOptionPane.showMessageDialog(table, "discount percentage is available");
                   //Double Total = new Double(obview.getTotal());
                // percentage;
                   
                   //return;
               }
//              for(String rw:row){
//                  System.out.println(rw);
//              }
              
//               System.out.println(obview.ComplimentaryList.size());
              //  System.out.println(obview.getDiscount()+"\n"+ComplimentaryDiscount);
               obview.setDiscount(obview.getDiscount()+ComplimentaryDiscount);
              obview.JDialogComplimentary.setVisible(false);
            //   obview.JDialogBillPayment.setModal(false);
              // obview.JDialogComplimentary.setModal(false);
        }
             }
         }
    }
          
//    }
    public class SearchAddOrderTableEnterListener extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          JTable table = (JTable)e.getSource();
             //int row = table.getSelectedRow();
           
             if(table == obview.tblComplimentarySelect){
                   ListSelectionModel listmodel = table.getSelectionModel();
               int Lead = listmodel.getLeadSelectionIndex();
             //  System.out.println("wala");
               String[] row = new String[]{table.getValueAt(Lead,0).toString(),table.getValueAt(Lead, 5).toString()};
               Double ComplimentaryDiscount = new Double(Double.parseDouble(table.getValueAt(Lead, 5).toString()));
               
                Object cmenuid = table.getValueAt(Lead, 0);
                obview.ComplimentaryList.add(row);
              //   String[][] comp = obview.ComplimentaryList.toArray(new String[obview.ComplimentaryList.size()][]);
               int ctn =0;
               for(int i=0;i<table.getRowCount();i++){
                //   System.out.println("from table:"+table.getValueAt(Lead, 0)+"| \n");
                
                  if(table.getValueAt(i, 0).equals(cmenuid)){
                      ctn++;
                  }
                  if(ctn >= 2){
                      JOptionPane.showMessageDialog(obview.JDialogComplimentary, "The Item:|"+table.getValueAt(Lead, 1)+"| Selected is Found more than One Time In the Bill List.So It Cannot be Added as Complimentary.");
                      return;
                  }
              }
               if(obview.getBooleanDiscountPercentage()){
                   obview.setCheckBoxDiscountFalse();
                   obview.setDiscount(obview.getDiscountAmount());
                  // JOptionPane.showMessageDialog(table, "discount percentage is available");
                   //Double Total = new Double(obview.getTotal());
                // percentage;
                   
                   //return;
               }
             //  obview.ComplimentaryList.add(row);
              //  System.out.println(obview.getDiscount()+"\n"+ComplimentaryDiscount);
               obview.setDiscount(obview.getDiscount()+ComplimentaryDiscount);
              obview.JDialogComplimentary.setVisible(false);
              // obview.JDialogBillPayment.setModal(false);
             }
        }
        
    }
    public class BillComboCustomerListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            
            Object item[] = null;
            JComboBox jc = (JComboBox) e.getSource();
            if(jc.getSelectedIndex() == 0){
                 obview.setCustomerId(0);
            }
            else{
            for (Object[] customerInfoObject : obmodel.getCustomerInfoObject()) {
                if (customerInfoObject[1].equals(obview.getComboCustomerNameForBill())) {
                    item = customerInfoObject;
                    break;
                }
            }
            obview.setCustomerId(Integer.parseInt(item[0].toString()));
            }
            
        }
        catch(Exception be){
            JOptionPane.showMessageDialog(obview, be+"form BillComboCustomerListener");
        }
        }
        
    }
    public class AddCustomerWindowListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       try{
                      /*
             * this is done just to refresh the customer name at time of closing
             */
               class Customer extends JDialog{
                    private  CustomerView CustomerView;
                    private CustomerModel CustomerModel;
                    private     CustomerController CustomerController;
//                Customer(JFrame parent,boolean modal){
                      Customer(){
                    
//                    super(parent,modal);
//                       CustomerView = new CustomerView(parent,modal);
                         CustomerView = new CustomerView(new JFrame(),true);
                       CustomerModel = new CustomerModel();
        
                       CustomerController = new CustomerController(CustomerModel,CustomerView);
                       CustomerView.addWindowListener(new WindowAdapter(){
                                @Override
                               public void windowClosing(WindowEvent e){
                                 // JOptionPane.showMessageDialog(null, "Jagad Guru Kripalu maharaj ki jaya ho");
                              obview.setComboCustomerNameForBill(Function.returnSecondColumn(obmodel.getCustomerInfoObject()));
                               obview.AddSelectInCombo(obview.returnComboCustomerNameForBill());
                           //   System.out.println("wala");
                                }
                           });

                        CustomerView.setVisible(true);
                    
                }
              
                 // ExecuteCustomer dialog =  new ExecuteCustomer(new JFrame(),true);
                
               }
//              Customer customer= new Customer(orderview,true);
               Customer customer= new Customer();
           
       }
       catch(Exception ae){
           JOptionPane.showMessageDialog(obview.JDialogBillPayment, ae+"from AddCustomerWindowListener");
       }
        }
        
    }
           
         
          
           public class InternalFrameCloseListener extends InternalFrameAdapter{
               
               @Override
               public void internalFrameClosing(InternalFrameEvent e){
                            JInternalFrame billwindow = (JInternalFrame)e.getSource();
          //  JOptionPane.showMessageDialog(obview, "DO You Want to close it");
           if(obview.gettblBillInfo().getRowCount() >0){
               if(DisplayMessages.displayInputYesNo(obview, "You Are in Middle of Check Out.\n Do You Still Want To Close this Window?", "Bill Alert Window"))
          {
              mainview.setCountForDirectPay(0);
              billwindow.dispose();
              Function.MakeCurrentItentityIdFalse(obview.getMainBillId());
          }
               
           }
          
           else{
                mainview.setCountForDirectPay(0);
                billwindow.dispose();
                Function.MakeCurrentItentityIdFalse(obview.getMainBillId());
           }
                   
               }
               
           }
           //all the method for the document listener
             public class SVCDocumentListener extends DocumentFunction implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            SVCTrigger(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
     
            SVCTrigger(e);
              
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            SVCTrigger(e);
        }
       
           
      }
       

          
           public class TotalDocumentListener implements DocumentListener{
              

        @Override
        public void insertUpdate(DocumentEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            SwingUtilities.invokeLater(new Runnable(){

                @Override
                public void run() {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
              
        if(obview.getBooleanDiscountPercentage()){
          //  when the percentage is clicked
            Double dsc = (obview.getDiscount()*obview.getTotal())/100;
            obview.setDiscountAmount(dsc);
       
            obview.setTotalAfterDiscount(obview.getTotal()-dsc);
//         
           //start for svc
               if(obview.getBooleanSVCPercentage()){
                Double SVCAmount = (obview.getSVC()*obview.getTotalAfterDiscount())/100;
                obview.setSVCAmount(SVCAmount);
                obview.setSubTotal(obview.getTotalAfterDiscount()+SVCAmount);
                 }
                else{
                      //   System.out.print(getBooleanSVCPercentage());
                Double svc = (obview.getSVC()/obview.getTotalAfterDiscount())*100;
                obview.setSubTotal(obview.getTotalAfterDiscount()+obview.getSVC());

                    }
            Double Vatamount = (obview.getSubTotal()*obview.getVAT())/100;
            obview.setGrandTotal1(obview.getSubTotal()+Vatamount);
             obview.setGrandTotal2(obview.getSubTotal()+Vatamount);
             obview.setChangeAmount(obview.getTenderedAmount()-obview.getGrandTotal1());
        }
        else{
              Double dsc = (obview.getDiscount()/obview.getTotal())*100;
              obview.setDiscountAmount(dsc);
                 obview.setTotalAfterDiscount(obview.getTotal()-obview.getDiscount());
//         
           //start for svc
               if(obview.getBooleanSVCPercentage()){
                Double SVCAmount = (obview.getSVC()*obview.getTotalAfterDiscount())/100;
                obview.setSVCAmount(SVCAmount);
                obview.setSubTotal(obview.getTotalAfterDiscount()+SVCAmount);
                 }
                else{
                      //   System.out.print(getBooleanSVCPercentage());
                Double svc = (obview.getSVC()/obview.getTotalAfterDiscount())*100;
                obview.setSubTotal(obview.getTotalAfterDiscount()+obview.getSVC());

                    }
            Double Vatamount = (obview.getSubTotal()*obview.getVAT())/100;
            obview.setGrandTotal1(obview.getSubTotal()+Vatamount);
             obview.setGrandTotal2(obview.getSubTotal()+Vatamount);
             obview.setChangeAmount(obview.getTenderedAmount()-obview.getGrandTotal1());
        
        }
          }
                
            });
           //   setDiscount(0.0);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
         
                   SwingUtilities.invokeLater(new Runnable(){

                @Override
                public void run() {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
              
        if(obview.getBooleanDiscountPercentage()){
          //  when the percentage is clicked
            Double dsc = (obview.getDiscount()*obview.getTotal())/100;
            obview.setDiscountAmount(dsc);
       
            obview.setTotalAfterDiscount(obview.getTotal()-dsc);
//         
           //start for svc
               if(obview.getBooleanSVCPercentage()){
                Double SVCAmount = (obview.getSVC()*obview.getTotalAfterDiscount())/100;
                obview.setSVCAmount(SVCAmount);
                obview.setSubTotal(obview.getTotalAfterDiscount()+SVCAmount);
                 }
                else{
                      //   System.out.print(getBooleanSVCPercentage());
                Double svc = (obview.getSVC()/obview.getTotalAfterDiscount())*100;
                obview.setSubTotal(obview.getTotalAfterDiscount()+obview.getSVC());

                    }
            Double Vatamount = (obview.getSubTotal()*obview.getVAT())/100;
            obview.setGrandTotal1(obview.getSubTotal()+Vatamount);
             obview.setGrandTotal2(obview.getSubTotal()+Vatamount);
             obview.setChangeAmount(obview.getTenderedAmount()-obview.getGrandTotal1());
        }
        else{
              Double dsc = (obview.getDiscount()/obview.getTotal())*100;
              obview.setDiscountAmount(dsc);
                 obview.setTotalAfterDiscount(obview.getTotal()-obview.getDiscount());
//         
           //start for svc
               if(obview.getBooleanSVCPercentage()){
                Double SVCAmount = (obview.getSVC()*obview.getTotalAfterDiscount())/100;
                obview.setSVCAmount(SVCAmount);
                obview.setSubTotal(obview.getTotalAfterDiscount()+SVCAmount);
                 }
                else{
                      //   System.out.print(getBooleanSVCPercentage());
                Double svc = (obview.getSVC()/obview.getTotalAfterDiscount())*100;
                obview.setSubTotal(obview.getTotalAfterDiscount()+obview.getSVC());

                    }
            Double Vatamount = (obview.getSubTotal()*obview.getVAT())/100;
            obview.setGrandTotal1(obview.getSubTotal()+Vatamount);
             obview.setGrandTotal2(obview.getSubTotal()+Vatamount);
             obview.setChangeAmount(obview.getTenderedAmount()-obview.getGrandTotal1());
        
        }
          }
                
            });
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
                    SwingUtilities.invokeLater(new Runnable(){

                @Override
                public void run() {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
              
        if(obview.getBooleanDiscountPercentage()){
          //  when the percentage is clicked
            Double dsc = (obview.getDiscount()*obview.getTotal())/100;
            obview.setDiscountAmount(dsc);
       
            obview.setTotalAfterDiscount(obview.getTotal()-dsc);
//         
           //start for svc
               if(obview.getBooleanSVCPercentage()){
                Double SVCAmount = (obview.getSVC()*obview.getTotalAfterDiscount())/100;
                obview.setSVCAmount(SVCAmount);
                obview.setSubTotal(obview.getTotalAfterDiscount()+SVCAmount);
                 }
                else{
                      //   System.out.print(getBooleanSVCPercentage());
                Double svc = (obview.getSVC()/obview.getTotalAfterDiscount())*100;
                obview.setSubTotal(obview.getTotalAfterDiscount()+obview.getSVC());

                    }
            Double Vatamount = (obview.getSubTotal()*obview.getVAT())/100;
            obview.setGrandTotal1(obview.getSubTotal()+Vatamount);
             obview.setGrandTotal2(obview.getSubTotal()+Vatamount);
             obview.setChangeAmount(obview.getTenderedAmount()-obview.getGrandTotal1());
        }
        else{
              Double dsc = (obview.getDiscount()/obview.getTotal())*100;
              obview.setDiscountAmount(dsc);
                 obview.setTotalAfterDiscount(obview.getTotal()-obview.getDiscount());
//         
           //start for svc
               if(obview.getBooleanSVCPercentage()){
                Double SVCAmount = (obview.getSVC()*obview.getTotalAfterDiscount())/100;
                obview.setSVCAmount(SVCAmount);
                obview.setSubTotal(obview.getTotalAfterDiscount()+SVCAmount);
                 }
                else{
                      //   System.out.print(getBooleanSVCPercentage());
                Double svc = (obview.getSVC()/obview.getTotalAfterDiscount())*100;
                obview.setSubTotal(obview.getTotalAfterDiscount()+obview.getSVC());

                    }
            Double Vatamount = (obview.getSubTotal()*obview.getVAT())/100;
            obview.setGrandTotal1(obview.getSubTotal()+Vatamount);
             obview.setGrandTotal2(obview.getSubTotal()+Vatamount);
             obview.setChangeAmount(obview.getTenderedAmount()-obview.getGrandTotal1());
        
        }
          }
                
            });
        }
          
      
      }
           // code for discount
            public class DiscountDocumentListener  extends DocumentFunction implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
          
           //invoke the document triiger
            DiscountTrigger(e);
            
        
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
         
                   DiscountTrigger(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
                    DiscountTrigger(e);
        }
          
      } 
            public class GrandTotalDocumentListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              
                obview.setChangeAmount(obview.getTenderedAmount()-obview.getGrandTotal1());
                obview.setGrandTotal2(obview.getGrandTotal1());
        // System.out.println(getTenderedAmount());
              
                }
            
        });
       
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       obview.setChangeAmount(obview.getTenderedAmount()-obview.getGrandTotal1());
        obview.setGrandTotal2(obview.getGrandTotal1());
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       obview.setChangeAmount(obview.getTenderedAmount()-obview.getGrandTotal1());
        obview.setGrandTotal2(obview.getGrandTotal1());
        }
        
          
      }
              public class TenderedDocumentListener extends DocumentFunction implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            TenderAmountChange(e);
       
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//       obview.setChangeAmount(obview.getTenderedAmount()-obview.getGrandTotal1());
            TenderAmountChange(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//       obview.setChangeAmount(obview.getTenderedAmount()-obview.getGrandTotal1());
            TenderAmountChange(e);
        }
       
      }
              public class DocumentFunction{
                  //this should be trigger in case of each time when svc is changed
                   public void SVCTrigger(DocumentEvent e){
             SwingUtilities.invokeLater(new Runnable(){

                @Override
                public void run() {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
              
             if(obview.getBooleanSVCPercentage()){
                Double SVCAmount = (obview.getSVC()*obview.getTotalAfterDiscount())/100;
                obview.setSVCAmount(SVCAmount);
            obview.setSubTotal(obview.getTotalAfterDiscount()+SVCAmount);
          
            
             
       
        }
        else{
          //   System.out.print(getBooleanSVCPercentage());
                Double svc = (obview.getSVC()/obview.getTotalAfterDiscount())*100;
                  obview.setSVCAmount(svc);
            obview.setSubTotal(obview.getTotalAfterDiscount()+obview.getSVC());
   
        }
              Double Vatamount = (obview.getSubTotal()*obview.getVAT())/100;
              obview.setVATAmount(Vatamount);
            obview.setGrandTotal1(obview.getSubTotal()+Vatamount);
             obview.setGrandTotal2(obview.getSubTotal()+Vatamount);
             obview.setChangeAmount(obview.getTenderedAmount()-obview.getGrandTotal1());
            
             }
                
            }); 
        }
                   //this should be trigger in case of each time when discount is changed
                   public void DiscountTrigger(final DocumentEvent e){
                      SwingUtilities.invokeLater(new Runnable(){

                @Override
                public void run() {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
              
        if(obview.getBooleanDiscountPercentage()){
          //  when the percentage is clicked
            Double dsc = (obview.getDiscount()*obview.getTotal())/100;
             if(dsc.isInfinite()|| dsc.isNaN()){
//                  JOptionPane.showMessageDialog(mainview, "wala");
                  dsc = 0.0;
              }
            obview.setDiscountAmount(dsc);
//            System.out.println(dsc+" "+obview.getTotal());
       
            obview.setTotalAfterDiscount(obview.getTotal()-dsc);
//         
           //start for svc
           
               SVCTrigger(e);
        }
        else{
              Double dsc = (obview.getDiscount()/obview.getTotal())*100;
              obview.setDiscountAmount(dsc);
                 obview.setTotalAfterDiscount(obview.getTotal()-obview.getDiscount());
//         
           //start for svc
              SVCTrigger(e);
        
        }
          }
                
            });  
                   }
                   //this is used by Tenderamount
                    public void TenderAmountChange(DocumentEvent e){
             SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              
                obview.setChangeAmount(obview.getTenderedAmount()-obview.getGrandTotal1());
                
        // System.out.println(getTenderedAmount());
              
                }
            
        });
            
        }  
              }
         public class SVCCheckListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        JCheckBox dsc = (JCheckBox) e.getSource();
//       JFormattedTextField svc = obview.returnSVC();
        Double[] tax = obmodel.getChargeInfo();
        Double SVCpercent =tax[0];
        Double SVC = tax[0]*obview.getTotalAfterDiscount()/100;
        if(dsc.isSelected()){
//            JOptionPane.showMessageDialog(mainview, "walapercent");
            Validator.PercentageMakerWithDefaultValue(obview.returnSVC(),SVCpercent);
        }
        else{
//            JOptionPane.showMessageDialog(mainview, "walaamount");
        Validator.DecimalMakerWithDefaultValue(obview.returnSVC(),SVC);
        }
        
        }
            
        }
          public class DiscountCheckListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        JCheckBox dsc = (JCheckBox) e.getSource();
//        JFormattedTextField discount = obview.returnDiscount();
        if(dsc.isSelected()){
//             JOptionPane.showMessageDialog(mainview, "walapercent");
        Validator.PercentageMaker(obview.returnDiscount());
        }
        else{
         Validator.DecimalMaker(obview.returnDiscount(),obview.getTotal());
        }
        
        }
            
        }
          
//       
          public class addEnterFocusListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//       JFormattedTextField jf = (JFormattedTextField)e.getSource();
      // System.out.println("walaoutside");
            
//                System.out.println("wala");
              obview.requestFocusOnButtonSimpleBillSave();
            
        }
            
    
        }
          public class ShortcutForDirectBill implements KeyEventDispatcher{

        @Override
       public boolean dispatchKeyEvent(KeyEvent e) {
            try{
                if(obview.isSelected()){
                if(e.getID() == KeyEvent.KEY_PRESSED){
                    //for save 
                    /*
                    this is not need this mnemonics does work for me
                    
                    
                    if(e.getKeyCode() == KeyEvent.VK_S && e.isControlDown()){
                        if(!obview.JDialogBillPayment.isVisible()){
                        obview.getBtnSave().doClick();
                        }
                        else{
                            //when dialog box is opend save the bill
                            obview.getBtnBillSave().doClick();
                        }
                    }
                    //for cancel
                    if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                         if(!obview.JDialogBillPayment.isVisible()){
                        obview.getBtnCancel().doClick();
                         }
                         else{
                             //when dialog box is opened clost that window
                             obview.getBtnBillCancel().doClick();
                         }
                    }
                            */
                   
                    //to focus on tenderamount
                    if(e.getKeyCode() == KeyEvent.VK_INSERT){
                        obview.requestFocusOnTenderedAmount();
                    }
                    //to close
                    if(e.getKeyCode() == KeyEvent.VK_X && e.isControlDown()){
                        obview.setClosed(true);
                    }
                    if(e.getKeyCode() == KeyEvent.VK_HOME){
                        obview.returnComboMenuName().requestFocusInWindow();
                    }
                }
                }
            }
            catch(PropertyVetoException se){
                DisplayMessages.displayError(obview,se.getMessage()+" from ShortcutForDirectBill"+getClass().getName(), "Error");
            }
            return false;
        }
              
          }
           public class AsbtractActionListener extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                //it enter is pressed at orderquantity
                if(e.getSource() == obview.getTxtOrderQuantity()){
                    obview.getBtnAdd().doClick();
                        
                }
                //if enter is pressed at menusearch
              
                if(e.getSource() == obview.returnComboMenuName()){
                    obview.txtOrderQuantity.requestFocusInWindow();
                }
                if(e.getSource() == obview.returnTenderedAmount()){
                    obview.getBtnSave().doClick();
                }
                
            }
            catch(Exception se){
                DisplayMessages.displayError(mainview, se.getMessage()+" from AsbtractActionListener "+getClass().getName(), "Error");
            }
        }
       
   }

    
}
