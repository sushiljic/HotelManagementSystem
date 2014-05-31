/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.orderbill;

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
import java.util.Map;
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
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import report.bill.BillPrint;
import resturant.customer.CustomerController;
import resturant.customer.CustomerModel;
import resturant.customer.CustomerView;
import resturant.order.OrderView;
import resturant.tablecrud.ExecuteTableStatusView;
import reusableClass.DisplayMessages;
import reusableClass.Function;
import reusableClass.Validator;
import systemdate.SystemDateModel;

/**
 *
 * @author SUSHIL
 */
public class OrderBillController extends SystemDateModel {
  public  OrderBillView obview;
   public  OrderBillModel obmodel;
    public MainFrameView mainview;
     String[][] comp;
     Double[] TaxList = new Double[2];
    
   public  OrderBillController(OrderBillModel model,OrderBillView view,MainFrameView mainview){
        
       obmodel = model;
       obview = view;
       this.mainview = mainview;
     
       obview.addListSelectionListenerForOrderedList(new OrderedListSelectionListener(obview));
       //adding action listener for save
        obview.addSaveListener(new SimpleSaveCancelListener());
        obview.addCancelListener(new SimpleSaveCancelListener());
        // firing itemlistener for cash and credit
        obview.addItemListenerForCashCredit(new radioCashCreditListener());
        obview.addComboClickCustomerNameActionListener(new ComboCustomerNameListener());
        obview.addComboDepartmentNameListener(new ComboDepartmentNameListener());
        /*
         * adding the save and cancel for bill 
         */
        obview.addBillSaveListener(new BillInsertListener());
        obview.addBillCancelListener(new BillInsertListener());
        obview.addBillSaveAndPrintListener(new BillInsertListener());
        
        obview.addSearchListener(new OrderSearchListener());
        obview.addTextSearchListener(new  OrderSearchListener());
        obview.addRefreshListener(new RefreshOrderTableListener());
        /*
        addd key handler for keyshortcut
        */
        obview.addShortcutForOrderBill(new ShortcutForOrderBill());
        /*
         * this further addes order into the billinfo table
         */
        obview.addAddOrderListener(new AddOrderListener());
        /*
         * this id the listener for the jtable enter or double clicl listener
         */
        createKeybindings(obview.tblAddOrder);
        obview.addDoubliClickListenerForAddOrder(new DoubleClickListener());
        createKeybindings(obview.tblComplimentarySelect);
        obview.addDoubliClickListenerForComplimentarySelect(new DoubleClickListener());
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
//        obview.addWindowListener(new WindowCloseListener());
        obview.addInternalFrameListener(new InternalFrameCloseListener());
       
        //adding  document and checklistener
        obview.addTotalDocumentListener(new TotalDocumentListener());
        obview.addSVCDocumentListener(new SVCDocumentListener());
        obview.addDiscountDocumentListener(new DiscountDocumentListener());
        obview.addTenderedAmountDocumentListener(new TenderedDocumentListener());
//        obview.addGrandTotalDocumentListener(new GrandTotalDocumentListener());
        obview.addCheckSVCListener(new SVCCheckListener() );
        obview.addCheckDiscountListener(new DiscountCheckListener());
        obview.addCheckBoxComplimentaryItemListener(new CheckBoxComplimentaryItemListener());
        //adding enterlisten for tender amount
//        obview.addTenderedAmountActionListener(new addEnterFocusListener());
        obview.addAbstractActionListener(new AbstractActionListener());
        //adding complimentary listener
        obview.addComboComplimentaryReason(new ComboComplimentaryListener());
        try{
              obview.setcomboDepartmentName(obmodel.returnMenuName(obmodel.getRespectiveDepartment(mainview.getUserId())));
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
        String[] orderColumnName =  new String[]{"Menu Code","Menu Name","Quantity","Item Base Unit","Rate","Total Amount"};
        DefaultTableModel orderTableModel = new DefaultTableModel(null,orderColumnName){
            @Override
            public boolean isCellEditable(int row,int columns){
              
                return false;
            }
        };
        obview.refreshJTableBillInfo(orderTableModel);
//       obview.refreshJTableOrderedList(obmodel.getOrderInfo());
       /*
        * loading customer name
        */
       obview.setComboCustomerName(obmodel.returnMenuName(obmodel.getCustomerInfoObject()));
       obview.AddSelectInCombo(obview.returnComboBoxCustomer());
       //check the svc button
            
       obview.setSVCCheck(true);
       obview.setDiscountCheck(true);
       obview.setDiscountCheck(false);
       obview.setComboComplimentaryName(obmodel.returnMenuName(obmodel.getComplimentaryInfo()));
       //adding refresh button on every ten second
       obview.AddSelectInCombo(obview.returnComboComplimentaryName());
       Timer refreshOrderedList = new Timer(10000,new RefreshOrderedListTimer());
        refreshOrderedList.start();
        }
         catch(Exception se){
            JOptionPane.showMessageDialog(mainview, se+"from constructor "+ getClass().getName());
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
                if(obview.getBooleanComplimentaryType()){
                    if(obview.getComplimentaryId() == 0){
                         JOptionPane.showMessageDialog(obview.JDialogBillPayment, "Reason Must be Specified for Complimentary");
                        return;
                    }
                }
                String[] orderarray = obview.OrderArray.toArray(new String[obview.OrderArray.size()]);
                String[][] complilist = obview.ComplimentaryList.toArray(new String[obview.ComplimentaryList.size()][]);
//               System.out.println(comp.length);
//                System.out.println(obview.getMainBillId());
                obmodel.AddBill(obmodel.convertDefaultTableModelToObject(obview.gettblBillInfo()),obview.getBillRate(), obview.getBillOtherInfo(),orderarray,complilist,mainview.getUserId(),obview.getDepartmentId());
//               System.out.println(obview.getMainBillId());
//                System.out.println("wala");
               
                obview.refreshJTableOrderedList(obmodel.getOrderInfo(obview.getDepartmentId()));
               
               
//                System.out.println("walal");
                obview.JDialogBillPayment.setVisible(false);
                   
               
//              
               
//                 /*alejob
                if(DisplayMessages.displayInputYesNo(obview, "Do You Want To Print the Bill","Print Bill")){
                    final Map print = obview.getBillParam();
                    SwingUtilities.invokeLater(new Runnable(){

                        @Override
                        public void run() {
                        BillPrint bill = new BillPrint(print);
                    bill.printBill();
                        }
                        
                    });
                    
                }
                 obview.clearOrderBill();
//                 System.out.println("walal");
                obview.gettblBillInfo().setRowCount(0);
//                 */
              
                int newbillid ;
                newbillid = Function.returnCurrentItentityBillId("bill");
//                 System.out.println(newbillid+"wl");
                obview.setBillId(String.valueOf(newbillid));    
//                 System.out.println("wala2");
                obview.setMainBillId(newbillid);
                         /*
                    * here is manipulation for refreshing the data in order window if it is open
                    */
                 JInternalFrame[] iframes =   mainview.desktop.getAllFrames();
                   for (JInternalFrame iframe : iframes) {
                     if(iframe.getTitle().equalsIgnoreCase("Order Window")){
//                         iframe.dispose();
//                         ExecuteOrder order = new ExecuteOrder(mainview);
//                         mainview.desktop.add(order.OrderView);
                         OrderView view = (OrderView)iframe;
                         view.btnRefresh.doClick();
                     }
                          /*
                   here is manipulation and update of tabl
                   */
                       if(iframe.getTitle().equalsIgnoreCase("Table Status")){
                           iframe.dispose();
                           ExecuteTableStatusView tablestatus = new ExecuteTableStatusView(mainview);
                           mainview.desktop.add(tablestatus.view);
                       }
                   }
                    obview.setSaveEditableFalse();
                      //print bill
//           
                   
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
                    obview.setBillId(String.valueOf(obmodel.returnCurrentItentityBillId("bill")));
                    * */
                JOptionPane.showMessageDialog(obview.JDialogBillPayment, "Module to be added");
            }
            else  if(e.getActionCommand().equalsIgnoreCase("BillCancel")){
                if(DisplayMessages.displayInputYesNo(obview.JDialogBillPayment, "Do you Want to Cancel Billing","Bill Cancel Window"))
                {
                    obview.JDialogBillPayment.setVisible(false);
                    obview.requestFocusOnTenderedAmount();
                   obview.ComplimentaryList.clear();
                   obview.returnComboComplimentaryName().setSelectedIndex(0);
                    
                }
            }
        }
        catch(HeadlessException se){
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
                if(obview.getOrderId() == 0){
                    JOptionPane.showMessageDialog(obview, "Select the Order From the Ordered table");
                    return;
                }
            if( obview.getTotal() == 0.0){
                JOptionPane.showMessageDialog(obview, "Select the Order From the Ordered table");
                return;
            }
           if(obview.getTenderedAmount() < obview.getGrandTotal1()){
               obview.setPaymentTypeForCredit(Boolean.TRUE);
              obview.radioCash.setEnabled(false);
               obview.radioCredit.setEnabled(true);
               //disable the save  button
               obview.btnBillSave.setEnabled(false);
              
           }
           else{
               obview.setPaymentTypeForCash(Boolean.TRUE);
               obview.radioCredit.setEnabled(false);
               obview.radioCash.setEnabled(true);
               obview.btnBillSave.setEnabled(true);
               
           }
           obview.getBillRate();
            obview.JDialogBillPayment.setTitle("Bill Window For Bill No:"+obview.getMainBillId());
            obview.setComplimentary(false);
           obview.JDialogBillPayment.pack();
            obview.requestFocusOnButtonBillSave();
             obview.JDialogBillPayment.setVisible(true);
             obview.setCheckBoxComplimentaryFalse();
//             JOptionPane.showMessageDialog(mainview, "wala");
             
             
             // obview.btnBillSave.requestFocus();
             // setting check box
              
            }
            else if (e.getActionCommand().equalsIgnoreCase("Cancel")){
                if(DisplayMessages.displayInputYesNo(obview, "Do you Want to Cancel Billing","Bill Cancel Window"))
                {
                   
                    obview.gettblBillInfo().setRowCount(0);
                    obview.setSaveEditableFalse();
                    obview.setAddOrderEditableFalse();
//                    obmodel.MakeCurrentItentityIdFalse(Integer.parseInt(obview.getBillId()));
                   
                     obview.clearOrderBill();
//                      obview.setBillId(String.valueOf(obmodel.returnCurrentItentityBillId("bill")));
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
        public class OrderSearchListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               // if(e.getActionCommand().equalsIgnoreCase("OrderSearch")){
                    Boolean flag = false;
                    if(obview.getSearch().isEmpty()){
                        JOptionPane.showMessageDialog(obview, "Search is Blanks");
                        return;
                    }
                   for(int i=0;i<obview.gettblOrderList().getRowCount();i++){
                       if(obview.getSearch().equalsIgnoreCase(obview.gettblOrderList().getValueAt(i, 0).toString())){
                           obview.tblOrderedList.scrollRectToVisible(obview.tblOrderedList.getCellRect(i, 0, true));
                           obview.tblOrderedList.setRowSelectionInterval(i, i);
                           obview.setSearch("");
                           flag = true;
                       }
                   }
                   if(flag == false){
                       JOptionPane.showMessageDialog(obview, "Order Id Not Found");
                   }
                }
       // }
            
        }
        public class RefreshOrderTableListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getActionCommand().equalsIgnoreCase("Refresh")){
            obview.refreshJTableOrderedList(obmodel.getOrderInfo(obview.getDepartmentId()));
        }
        }
            
        }
        public class AddOrderListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getActionCommand().equalsIgnoreCase("AddOrder")){
            obview.refreshJTableAddOrder(obmodel.getOrderInfoForAddOrder(obview.OrderArray,obview.getDepartmentId()));
            obview.JDialogAddOrder.setLocationRelativeTo(null);
            obview.JDialogAddOrder.setTitle("Add Order Window For Bill No:"+obview.getBillId());
            obview.JDialogAddOrder.setVisible(true);
        }
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

                            DefaultTableModel BillModel =new DefaultTableModel((obmodel.convertDefaultTableModelToObject(obview.gettblBillInfo())),Header){
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
                JComboBox cb = (JComboBox)e.getSource();
                if(cb.getSelectedIndex() == 0){
                       obview.setCustomerId(0);
                       if(obview.getTenderedAmount() >= obview.getGrandTotal1()){
                       obview.btnBillSave.setEnabled(true);
                       obview.requestFocusOnButtonBillSave();
                       }
                       else{
                           obview.btnBillSave.setEnabled(false);
                       }
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
                
                       
                       obview.btnBillSave.setEnabled(true);
                       obview.requestFocusOnButtonBillSave();
                      
                }
               // System.out.println(obview.getCustomerId());
            }
        }
        catch(NumberFormatException se){
            JOptionPane.showMessageDialog(obview, se+"From ComboCustomerNameListener");
        }
        }
                
            }
            public class ComboDepartmentNameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
                 //for department id
              if(e.getActionCommand().equalsIgnoreCase("comboDepartmentName")){
                 Object[][] departmentdata= obmodel.getRespectiveDepartment(mainview.getUserId());
                JComboBox dep = (JComboBox)e.getSource();
                 if(dep.getSelectedItem().equals("SELECT")){
                     obview.setDepartmentId(0);
                  
                     obview.refreshJTableOrderedList(new DefaultTableModel());
                     
                 }
                 else{
                     for(Object[] dat:departmentdata){
                         if(dat[1].equals(dep.getSelectedItem())){
                             obview.setDepartmentId(Integer.parseInt(dat[0].toString()));
                             //loading the repective item according to department  
//                              JOptionPane.showMessageDialog(null, "wala");
                            obview.refreshJTableOrderedList(obmodel.getOrderInfo(obview.getDepartmentId()));
                           
                            
                             break;
                             
                         }
                     }
                 }
              }
        }
        catch(NumberFormatException se){
            JOptionPane.showMessageDialog(mainview, se+"from ComboDepartmentNameListener "+getClass().getName());
        }
        }
                
            }
            public class ComboComplimentaryListener implements ActionListener{

                @Override
                public void actionPerformed(ActionEvent e) {
        //            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    try{
                     JComboBox jc = (JComboBox)e.getSource();
                     if(jc.getSelectedIndex() == 0){
                         obview.setComplimentaryId(0);
                     }
                     else{
                         for(Object[] data:obmodel.getComplimentaryInfo()){
                             if(data[1].equals(jc.getSelectedItem())){
                                 obview.setComplimentaryId(Integer.parseInt(data[0].toString()));
                                 break;
                             }
                         }
                     }
                    }
                    catch(NumberFormatException se){
                        JOptionPane.showMessageDialog(mainview, se+"from ComboComplimentaryListener"+getClass().getName());
                    }
                }

                    }
            /*
             * this is listener and event for enter and double click in addorder
             */
              private void createKeybindings(JTable table) {
           table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "Enter");
               table.getActionMap().put("Enter",new SearchAddOrderTableEnterListener());
            }
     /*
      * this  happen when enter is click on selected
      */
    public class DoubleClickListener extends MouseAdapter{
         @Override
         public void mouseClicked(MouseEvent me){
             if(me.getClickCount() == 2){
        JTable table = (JTable)me.getSource();
        if(table == obview.tblAddOrder){
         ListSelectionModel listmodel = table.getSelectionModel();
               int Lead = listmodel.getLeadSelectionIndex();
                  Double total_amount = new Double(0.0);
               // System.out.println(billview.getOrderId());
                    obview.setOrderId(Integer.parseInt(table.getValueAt(Lead, 0).toString()));
                obview.OrderArray.add(table.getValueAt(Lead, 0).toString());
               Object[][] itemrow = obmodel.getItemListByOrderId(obview.getOrderId());
            for (Object[] itemrow1 : itemrow) {
                obview.gettblBillInfo().addRow(itemrow1);
            }
                for(int i=0;i<obview.gettblBillInfo().getRowCount();i++){
                   // System.out.println(billview.gettblBillInfo().getValueAt(i, 5));
                    total_amount += ((Number)obview.gettblBillInfo().getValueAt(i, 5)).doubleValue();
                }
             //   System.out.println(total_amount);
                
                obview.setTotal(total_amount);
                obview.setSaveEditableTrue();
                obview.setAddOrderEditableTrue();
                obview.setTenderedAmount(0.0);
                obview.JDialogAddOrder.setVisible(false);
                obview.requestFocusOnTenderedAmount();
               
            }
        if(table == obview.tblComplimentarySelect){
             ListSelectionModel listmodel = table.getSelectionModel();
               int Lead = listmodel.getLeadSelectionIndex();
             //  System.out.println("wala");
               String[] row = new String[]{table.getValueAt(Lead,0).toString(),table.getValueAt(Lead, 5).toString()};
               Double ComplimentaryDiscount = new Double(Double.parseDouble(table.getValueAt(Lead, 5).toString()));
              Object cmenuid = table.getValueAt(Lead, 0);
               
              //   String[][] comp = obview.ComplimentaryList.toArray(new String[obview.ComplimentaryList.size()][]);
               //blocking if there in two same item in list
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
               
               }
////            
               obview.setDiscount(obview.getDiscount()+ComplimentaryDiscount);
               //reducing the total amount in 
             
//              Double gt = obview.getGrandTotal1();
//              obview.setGrandTotal1(gt-ComplimentaryDiscount);
//              obview.setGrandTotal2(gt-ComplimentaryDiscount);
               obview.JDialogComplimentary.setVisible(false);
            //   obview.JDialogBillPayment.setModal(false);
              // obview.JDialogComplimentary.setModal(false);
        }
             }
         }
    }
    public class SearchAddOrderTableEnterListener extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          JTable table = (JTable)e.getSource();
             //int row = table.getSelectedRow();
             if(table == obview.tblAddOrder){
              ListSelectionModel listmodel = table.getSelectionModel();
               int Lead = listmodel.getLeadSelectionIndex();
                 Double total_amount = new Double(0.0);
               // System.out.println(billview.getOrderId());
                    obview.setOrderId(Integer.parseInt(table.getValueAt(Lead, 0).toString()));
                obview.OrderArray.add(table.getValueAt(Lead, 0).toString());
               Object[][] itemrow = obmodel.getItemListByOrderId(obview.getOrderId());
              for (Object[] itemrow1 : itemrow) {
                  obview.gettblBillInfo().addRow(itemrow1);
              }
                for(int i=0;i<obview.gettblBillInfo().getRowCount();i++){
                   // System.out.println(billview.gettblBillInfo().getValueAt(i, 5));
                    total_amount += ((Number)obview.gettblBillInfo().getValueAt(i, 5)).doubleValue();
                }
             //   System.out.println(total_amount);
                
                obview.setTotal(total_amount);
                obview.setSaveEditableTrue();
                obview.setAddOrderEditableTrue();
                obview.setTenderedAmount(0.0);
                obview.JDialogAddOrder.setVisible(false);
                obview.requestFocusOnTenderedAmount();
             }
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
             //  obview.ComplimentaryList.add(row);
              //  System.out.println(obview.getDiscount()+"\n"+ComplimentaryDiscount);
               obview.setDiscount(obview.getDiscount()+ComplimentaryDiscount);
              obview.JDialogComplimentary.setVisible(false);
              // obview.JDialogBillPayment.setModal(false);
             }
        }
        
    }
           
           public class OrderedListSelectionListener implements ListSelectionListener{
        OrderBillView billview = new OrderBillView();
        JTable table = new JTable()/*{
            public void changeSelection(final int row, final int column, boolean toggle, boolean extend)
            {
                super.changeSelection(row, column, toggle, extend);
                table.editCellAt(row, column);
                table.transferFocus();
            }
        }*/;
        public OrderedListSelectionListener(OrderBillView view){
            billview = view;
            table = billview.tblOrderedList;
        }
        @Override
        public void valueChanged(ListSelectionEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getValueIsAdjusting()) return;
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            if(lsm.isSelectionEmpty()){
                //doesnot nothing
            }
            else{
                ListSelectionModel listmodel = table.getSelectionModel();
                int Lead = listmodel.getLeadSelectionIndex();
                  billview.OrderArray.clear();
//                  System.out.println(table.getValueAt(Lead, 0));
                billview.setOrderId(Integer.parseInt(table.getValueAt(Lead, 0).toString()));
              billview.OrderArray.add(table.getValueAt(Lead, 0).toString());
                //System.out.println(billview.getOrderId());
                //resetting the value of order list 
               /*
                 * adding service charge and vat 
                 */
                TaxList = obmodel.getChargeInfo();
                billview.setCheckBoxSVCTrue();
              
                billview.setSVC(TaxList[0]);
                billview.setVAT(TaxList[1]);
//             JOptionPane.showMessageDialog(mainview, "wala");
//                  obview.setDiscount(0.0);
               //setting the dicount not higesht than total
             
                /*
                 * setting the data according to order prevoisly
                 */
                Double total_amount = new Double(0.0);
               // System.out.println(billview.getOrderId());
               Object[][] itemrow = obmodel.getItemListByOrderId(billview.getOrderId());
//               System.out.println(billview.getOrderId());
               
                billview.gettblBillInfo().setRowCount(0);
                for (Object[] itemrow1 : itemrow) {
                    billview.gettblBillInfo().addRow(itemrow1);
                }
                for(int i=0;i<billview.gettblBillInfo().getRowCount();i++){
                   // System.out.println(billview.gettblBillInfo().getValueAt(i, 5));
                    total_amount += ((Number)billview.gettblBillInfo().getValueAt(i, 5)).doubleValue();
                }
             //   System.out.println(total_amount);
                 
                
                billview.setTotal(total_amount);
                
                /*
                adding table id
                */
                if(billview.gettblOrderList().getValueAt(Lead, 1) != null){
                    
                
                billview.setTableId(obmodel.getTableIdInfoTableName(billview.gettblOrderList().getValueAt(Lead, 1).toString()));
               }
                 //setting the dicount not higesht than total
//               Validator.DecimalMaker(billview.returnDiscount(),total_amount);
                obview.setSVCCheck(true);
                obview.setDiscountCheck(true);
                obview.setDiscountCheck(false);
                obview.setSaveEditableTrue();
                obview.setAddOrderEditableTrue();
                obview.setTenderedAmount(0.0);
                obview.requestFocusOnTenderedAmount();
//                obview.requestFocusOnTenderedAmount();
               /* billview.setOrderData(obmodel.getOtherInfoByOrderId(billview.getOrderId()));
                orderview.setAddEditableFalse();
               orderview.setEditOrderEditableTrue();
               orderview.setDeleteOrderEditableTrue();
               orderview.setAddOrderEditableFalse();
               orderview.setAddOrderAndPrintEditableFalse();
               */
            }
        
        }
        catch(NumberFormatException se){
            JOptionPane.showMessageDialog(billview, se+"from OrderedListSelectionListener");
        }
        }
         private String[] checkArrayForNull(String data[]){
          //  int columns = table.getColumnCount();
             int size = data.length;
            String[] st = new String[size];
            for(int i = 0;i<size;i++){
                
              //  Object o = table.getValueAt(lead, i);
                String o = data[i];
         
                if(o!= null){
                   
                st[i] = o.toString();
                
             }
             else{
                 st[i] = "";
              //  System.out.println("wala");
             }
           
            }
            return st;
            
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
                               
                        obview.setComboCustomerName(obmodel.returnMenuName(obmodel.getCustomerInfoObject()));
                        obview.AddSelectInCombo(obview.returnComboBoxCustomer());
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
           public class WindowCloseListener extends WindowAdapter{
           @Override
           public void windowClosing(WindowEvent e){
                 JFrame billwindow = (JFrame)e.getSource();
          //  JOptionPane.showMessageDialog(obview, "DO You Want to close it");
           if(obview.gettblBillInfo().getRowCount() >0){
                if(DisplayMessages.displayInputYesNo(obview, "You Are in Middle of Check Out.\n Do You Still Want To Close this Window?", "Bill Alert Window"))
          {
            
              billwindow.dispose();
          }
               
           }
           else if(obview.gettblOrderList().getRowCount()>0){
               int ordercount = obview.gettblOrderList().getRowCount();
               String gm = ordercount==1?"is":"are";
              if(DisplayMessages.displayInputYesNo(obview, "There "+gm+ " Still "+ordercount+" order to be bill in the ordered List.\n Do You Still Want To Close this Window?", "Bill Alert Window"))
          {
            
              billwindow.dispose();
          }
           }
          
           else{
              billwindow.dispose();
           }
           }    
           }
           public class InternalFrameCloseListener extends InternalFrameAdapter{
               
               @Override
               public void internalFrameClosing(InternalFrameEvent e){
                   try{
                            JInternalFrame billwindow = (JInternalFrame)e.getSource();
          //  JOptionPane.showMessageDialog(obview, "DO You Want to close it");
           if(obview.gettblBillInfo().getRowCount() >0){
           if(DisplayMessages.displayInputYesNo(obview, "You Are in Middle of Check Out.\n Do You Still Want To Close this Window?", "Bill Alert Window"))
            {
              mainview.setCountForPay(0);
             
//              billwindow.setClosed(true);
               Function.MakeCurrentItentityIdFalse(obview.getMainBillId());
                billwindow.dispose();
          }
          
               
           }
           else if(obview.gettblOrderList().getRowCount()>0){
               int ordercount = obview.gettblOrderList().getRowCount();
               String gm = ordercount==1?"is":"are";
              if(DisplayMessages.displayInputYesNo(obview, "There "+gm+ " Still "+ordercount+" order to be bill in the ordered List.\n Do You Still Want To Close this Window?", "Bill Alert Window"))
            {
             mainview.setCountForPay(0);
             
//                billwindow.setClosed(true);
              Function.MakeCurrentItentityIdFalse(obview.getMainBillId());
               billwindow.dispose();
          }
          
           }
          
           else{
                mainview.setCountForPay(0);
             
              Function.MakeCurrentItentityIdFalse(obview.getMainBillId());
               billwindow.dispose();
           }
                   
               }
                     catch(Exception se){
                         JOptionPane.showMessageDialog(mainview, se+"from InternalFrameCloseListener "+getClass().getName());
            }
               
           }
           }
     
           //old one 
           public class SVCDocumentListener extends DocumentFunction implements DocumentListener{

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
       

          //not use totaldocumentlistener
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
           //old code for discount
            public class DiscountDocumentListener extends DocumentFunction implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            DiscountTrigger(e);
           //   setDiscount(0.0);
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
            public class GrandTotalDocumentListener implements DocumentListener{

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
      TenderAmountChange(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      TenderAmountChange(e);
        }
          
      }
              //Document Function is exetended by Documentlistener
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
            obview.setDiscountAmount(dsc);
//            System.out.println(dsc+" "+obview.getTotal());
       
            obview.setTotalAfterDiscount(obview.getTotal()-dsc);
//         
           //start for svc
           
               SVCTrigger(e);
        }
        else{
              Double dsc = (obview.getDiscount()/obview.getTotal())*100;
              if(dsc.isInfinite()|| dsc.isNaN()){
//                  JOptionPane.showMessageDialog(mainview, "wala");
                  dsc = 0.0;
              }
              obview.setDiscountAmount(dsc);
                 obview.setTotalAfterDiscount(obview.getTotal()-obview.getDiscount());
//         
           //start for svc
              SVCTrigger(e);
        
        }
          }
                
            });  
                   }
                   //this is used by tendered amount
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
          
          public class ShortcutForOrderBill implements KeyEventDispatcher{

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
                    //to focus on order table
                    if(e.getKeyCode() == KeyEvent.VK_HOME){
                       obview.tblOrderedList.requestFocusInWindow();
                    }
                    //to focus on tenderamount
                    if(e.getKeyCode() == KeyEvent.VK_INSERT){
                        obview.requestFocusOnTenderedAmount();
                    }
                    //to close
                    if(e.getKeyCode() == KeyEvent.VK_X && e.isControlDown()){
                        obview.setClosed(true);
                    }
                }
                }
            }
            catch(PropertyVetoException se){
                DisplayMessages.displayError(obview,se.getMessage()+" from ShortcutForOrderBill"+getClass().getName(), "Error");
            }
            return false;
        }
              
          }
          public class AbstractActionListener extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
        try{
           
            obview.getBtnSave().doClick();
        }
        catch(Exception se){
            DisplayMessages.displayError(obview, se.getMessage()+" from abstractActionListener "+getClass().getName(), "Error");
        }
        }
              
          }
           public class RefreshOrderedListTimer implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
       try{
//         obview.btnRefresh.doClick();
         obview.refreshJTableOrderedList(obmodel.getOrderInfo(obview.getDepartmentId()));
       }
       catch(Exception se){
           DisplayMessages.displayError(obview, se.getMessage()+" from RefreshOrderedListTimer "+getClass().getName(), "Error");
       }
        }
       
   }
          
}

