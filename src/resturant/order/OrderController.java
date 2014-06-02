/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.order;

import hotelmanagementsystem.MainFrameView;
import java.awt.HeadlessException;
import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import report.bill.PrintOrder;
import resturant.customer.CustomerController;
import resturant.customer.CustomerModel;
import resturant.customer.CustomerView;
import resturant.orderbill.ExecuteOrderBill;
import resturant.orderbill.OrderBillView;
import resturant.tablecrud.ExecuteTableStatusView;
import resturant.tablecrud.TableCrudController;
import resturant.tablecrud.TableCrudModel;
import resturant.tablecrud.TableCrudView;
import resturant.waiter.WaiterController;
import resturant.waiter.WaiterModel;
import resturant.waiter.WaiterView;
import reusableClass.DisplayMessages;
import reusableClass.Function;
import systemdate.SystemDateModel;

/**
 *
 * @author SUSHIL
 */
public class OrderController  extends SystemDateModel{
    OrderView orderview;
    OrderModel ordermodel;
     public MainFrameView mainview;
     private  Object[][] DeleteData = null;
    BigDecimal TotalAmount;
    private  int LeadRow = 0;
    private Double[] TaxList = new Double[2];
    private int InitialTableId =0;
   
    
    public OrderController(OrderModel model,OrderView view,MainFrameView  mainframeview){
        ordermodel = model;
        orderview = view;
        mainview = mainframeview;
     
      
     
        
        /*
         * adding listener to respective model
         */
      //  orderview.addSearchListener(null);
        orderview.addAddTableListener(new AddModuleListener());
        orderview.addAddCustomerListener(new AddModuleListener());
        orderview.addAddWaiterListener(new AddModuleListener());
        /*
         * adding listener when combo box is selected
         */
        orderview.addComboTableNameListener(new ComboListener());
        orderview.addComboMenuNameListener(new ComboListener());
        orderview.addComboCustomerNameListener(new ComboListener());
        orderview.addComboWaiterNameListener(new ComboListener());
        orderview.addComboDepartmentListener(new ComboListener());
        /*
        adding  item listnener for combomenu name
        */
//        orderview.addComboMenuNameItemListener(new ComboItemListener());
        /*
         * adding crud function listener
         */
        orderview.addAdd(new CrudListener());
        orderview.addDelete(new CrudListener());
         orderview.addSearchMenuListener(new CrudListener());
         /*
          * this one os for text when quantiy is enter
          */
         orderview.addTextAdd(new TextCrudListener());
        
        /*
         * adding listselection listener
         */
       orderview.addOrderTableListSelection(new returnOrderTableListSelectionListener(orderview));
       orderview.addOrderedTableListSelection(new returnOrderedTableListSelectionListener(orderview));
       /*
        * this is all for search table listener when mouse oe key is pressed
        */
       createKeybindings(orderview.tblSearch);
       orderview.addDoubleClickListenerForTableSearch(new DoubleClickListener(mainview));
       /*
        * this listener is for odereed list when it is double click or enter on selected row
        */
       createKeybindings(orderview.tblOrderedList);
       orderview.addDoubleClickListenerForOrderedList(new DoubleClickListener(mainview));
       /*
        * adding main function
        */
      orderview.addAddOrder(new ItemCrudListener());
      orderview.addAddOrderAndPrint(new ItemCrudListener());
      orderview.addEditOrder(new ItemCrudListener());
      orderview.addDeleteOrder(new ItemCrudListener());
      orderview.addCancelOrder(new ItemCrudListener());
       orderview.addRefreshOrderedListListener(new ItemCrudListener());
       orderview.addSearchListener(new ItemCrudListener());
       /*
       listening for the shortcut
       */
       orderview.addShortcutForOrder(new OrderShortcutHandler());
       /*
        * listening for the close of window button
        */
     //  orderview.addCloseButtonListener(new DialogWindowListener());
      orderview.addInternalFrameListener(new InteralFrameCloseListener());
      
      //adding keyeventdispatcher for listening the shortcut of keyboard throug out the frame
      //adding abstractenter listener for the txtfield 
      orderview.addAbstractActionListener(new AsbtractActionListener());
      /*
      adding listener for the table edit when double click is on the ordertable which implemets tablecell listener class
      */
        TableCellListener tcl = new TableCellListener(orderview.tblOrderList, new listenActionForTableEdit());
        /*
        applying try and catch and all the relative dat
        */
        try{
               /*
         * seting order id
         */
        int temporderid = ordermodel.returnCurrentItentityId("order_list");
        //this is for displaying the value in the jframe
        orderview.setOrderId(String.valueOf(temporderid));
        orderview.setMainOrderId(temporderid);
           
        /*
         * adding columnname in table order
         */
        String[] orderColumnName =  new String[]{"Menu Code","Menu Name","Quantity","Item Base Unit","Rate","Total Amount"};
        DefaultTableModel orderTableModel = new DefaultTableModel(null,orderColumnName){
            @Override
            public boolean isCellEditable(int row,int columns){
                switch(columns){
                    case 2:
                        return true;
                   
                }
                return false;
            }
        };
        orderview.refreshOrderListJTable(orderTableModel);
//        orderview.refreshOrderedListJTable(ordermodel.getOrderInfo());
            orderview.setcomboDepartmentName(ordermodel.returnMenuName(Function.getRespectiveDepartment(mainview.getUserId())));
            //if it has only one element select it order wise add select into it
            int combosize = orderview.returnComboDepartmentName().getModel().getSize();
            if(combosize >1){
                orderview.AddSelectInCombo(orderview.returnComboDepartmentName());
            }
            else{
                if(combosize == 1){
                orderview.returnComboDepartmentName().setSelectedIndex(0);
                }
            }
                /*
         * retriving the value for combo box
         */
        orderview.setComboTableName(ordermodel.returnTableName(ordermodel.getTableInfoObject()));
        orderview.AddSelectInCombo(orderview.returnTableComboBox());
      orderview.setComboCustomerName(ordermodel.returnCustomerName(ordermodel.getCustomerInfoObject()));
      orderview.AddSelectInCombo(orderview.returnCustomerComboBox());
        orderview.setComboWaiterName(ordermodel.returnWaiterName(ordermodel.getWaiterInfoObject()));
        orderview.AddSelectInCombo(orderview.returnWaiterComboBox());
        //for refreshing the order list every 10 sec
        Timer refreshOrderedList = new Timer(10000,new RefreshOrderedListTimer());
        refreshOrderedList.start();
      
      
//           orderview.refreshOrderedListJTable(ordermodel.getOrderInfo(orderview.getDepartmentId()));
      
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(mainframeview, e+"From Constructor "+getClass().getName());
        }
    }
    
                private void createKeybindings(JTable table) {
           table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "Enter");
               table.getActionMap().put("Enter",new SearchTableEnterListener(mainview));
            }
     /*
      * this  happen when enter is click on selected
      */
    public class DoubleClickListener extends MouseAdapter{
         MainFrameView mainview;
        public DoubleClickListener(MainFrameView mview){
             mainview = mview;
        }
         @Override
         public void mouseClicked(MouseEvent me){
            JTable tb = (JTable)me.getSource();
           
             if(me.getClickCount() == 2){
       if(tb == orderview.tblSearch){
         ListSelectionModel listmodel = tb.getSelectionModel();
               int Lead = listmodel.getLeadSelectionIndex();
               orderview.setComboMenuName(tb.getValueAt(Lead, 1).toString());
               orderview.SearchDailog.setVisible(false);
               orderview.txtOrderQuantity.requestFocus();
             }
         if(tb == orderview.tblOrderedList){
               /*
                * load the  the object for  billing ExecuteOrderBill
                */
               ListSelectionModel listmodel = tb.getSelectionModel();
               int Lead = listmodel.getLeadSelectionIndex();
               /*
                * adding the ExcecuteOrderbill in desktop
                */
//         
//           }
           /*
            * implementing the orderbillview only one time
            */
           
             
                if(mainview.getCountForPay() == 0){
             
                /*
                 * expering menting with  jinternal fames
                 */
//              
                 
               
                   try {
                       //                 MainFrameView.desktop.add(scrollpane);
                        
                 ExecuteOrderBill billpay = new ExecuteOrderBill(mainview);
                 //checking whether the user is authorised to set bill for that order
                  //loading the data of respective department in whick order id was order
                    Object[] dep = ordermodel.getRespectiveDepartmentForOrderid(Integer.parseInt(tb.getValueAt(Lead, 0).toString()));
                 ComboBoxModel departmentCombo = billpay.OrderBillView.returnComboDepartmentName().getModel();
                 boolean authoriseflag = false;
                 for(int i=0;i<departmentCombo.getSize();i++){
                     if(departmentCombo.getElementAt(i).equals(dep[1])){
                         
                             authoriseflag = true; 
                               billpay.OrderBillView.setcomboDepartmentName(dep[1].toString());
//                               System.out.println(dep[1]);
                               break;
                     }
                 }
                  if(!authoriseflag){
                           JOptionPane.showMessageDialog(mainview, "This Order has been ordered by another Department.");
                           return;
                       }
                  //checking the customer if selected and if selected making is select
                  
                  billpay.OrderBillView.setComboCustomerName(orderview.getComboCustomerName());
                        mainview.desktop.add(billpay.OrderBillView);
                        billpay.OrderBillView.setSelected(true);
                        billpay.OrderBillView.setSearch(tb.getValueAt(Lead, 0).toString());
                        billpay.OrderBillView.btnSearchOrder.doClick();
                         mainview.incrementCountForPay();
              
               
                   } catch (PropertyVetoException ex) {
//                       Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
                       JOptionPane.showMessageDialog(mainview, ex+"from doubleclick listener");
                   }
                }
                else{
                    
                  JInternalFrame[] frames=  mainview.desktop.getAllFrames();
                   for (JInternalFrame frame : frames) {
                       if (frame.getTitle().equalsIgnoreCase("Order Bill Pay Window")) {
                           try {
                            
                              //checking authorization for order 
                             Object[] dep = ordermodel.getRespectiveDepartmentForOrderid(Integer.parseInt(tb.getValueAt(Lead, 0).toString()));   
                               
                                  OrderBillView view = (OrderBillView)frame;
                                  //checking for authorize  user
                               ComboBoxModel departmentCombo = view.returnComboDepartmentName().getModel();
                               boolean authoriseflag = false;
                                for(int i=0;i<departmentCombo.getSize();i++){
                           if(departmentCombo.getElementAt(i).equals(dep[1])){
                               authoriseflag = true;
//                                mainview.desktop.add(billpay.OrderBillView);
//                               billpay.OrderBillView.setSelected(true);
                               view.setcomboDepartmentName(dep[1].toString());
//                               System.out.println(dep[1]);
                               break;
                           }
                       }
                       if(!authoriseflag){
                           JOptionPane.showMessageDialog(mainview, "This Order has been ordered by another Department.");
                           return;
                       }
                       //if authorise set selected
//                       if(!view.isMaximum()){
//                           view.setMaximum(true);
//                       }
                           
                               view.setSelected(true);
                               view.setSearch(tb.getValueAt(Lead, 0).toString());
                               view.btnSearchOrder.doClick();
                               
//                    frames[i].setSearch(tb.getValueAt(Lead, 0).toString());
//                    frames[i].OrderBillView.btnSearchOrder.doClick();
                           }catch (PropertyVetoException ex) {
//                             Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
                               JOptionPane.showMessageDialog(mainview, ex+"from doubleclick listener");
                           }
                       }
                   }
                    
                }
//                System.out.println("wala");
//                  
                
            
               
           }
            }
         
         }
    }
   
     public  class SearchTableEnterListener extends AbstractAction  {
          MainFrameView mainview;
         
         public SearchTableEnterListener(MainFrameView mview){
             mainview = mview;
             
         }
         @Override
         public void actionPerformed(ActionEvent ae){
           //  System.out.println("wala");
             JTable table = (JTable)ae.getSource();
             //int row = table.getSelectedRow();
            if(table == orderview.tblSearch){
              ListSelectionModel listmodel = table.getSelectionModel();
               int Lead = listmodel.getLeadSelectionIndex();
               orderview.setComboMenuName(table.getValueAt(Lead, 1).toString());
               orderview.SearchDailog.setVisible(false);
               orderview.txtOrderQuantity.requestFocus();
            }
            if(table == orderview.tblOrderedList){
               /*
                * load the  the object for  billing ExecuteOrderBill
                */
               ListSelectionModel listmodel = table.getSelectionModel();
               int Lead = listmodel.getLeadSelectionIndex();
//               ExecuteOrderBill OrderBillByOrder = new ExecuteOrderBill(mainview);
//               OrderBillByOrder.OrderBillView.setSearch(table.getValueAt(Lead, 0).toString());
//               OrderBillByOrder.OrderBillView.btnSearchOrder.doClick();
                    /*
            * implementing the orderbillview only one time
            */
           
              
                if(mainview.getCountForPay() == 0){
             
                /*
                 * expering menting with  jinternal fames
                 */
//                JScrollPane scrollpane = new JScrollPane(300,300);
//                scrollpane.add(billpay.OrderBillView);
//                    System.out.println(mainview.getCountForPay());
               
                   try {
                       //                 MainFrameView.desktop.add(scrollpane);
                            ExecuteOrderBill billpay = new ExecuteOrderBill(mainview);
                 //checking whether the user is authorised to set bill for that order
                  //loading the data of respective department in whick order id was order
                 Object[] dep = ordermodel.getRespectiveDepartmentForOrderid(Integer.parseInt(table.getValueAt(Lead, 0).toString()));
                 ComboBoxModel departmentCombo = billpay.OrderBillView.returnComboDepartmentName().getModel();
                 boolean authoriseflag = false;
                 for(int i=0;i<departmentCombo.getSize();i++){
                     if(departmentCombo.getElementAt(i).equals(dep[1])){
                         
                             authoriseflag = true; 
                               billpay.OrderBillView.setcomboDepartmentName(dep[1].toString());
//                               System.out.println(dep[1]);
                               break;
                     }
                 }
                  if(!authoriseflag){
                           JOptionPane.showMessageDialog(mainview, "This Order has been ordered by another Department.");
                           return;
                       }
                  //checking the customer if selected and if selected making is select
                  
                  billpay.OrderBillView.setComboCustomerName(orderview.getComboCustomerName());
                        mainview.desktop.add(billpay.OrderBillView);
                        billpay.OrderBillView.setSelected(true);
                        billpay.OrderBillView.setSearch(table.getValueAt(Lead, 0).toString());
                        billpay.OrderBillView.btnSearchOrder.doClick();
                         mainview.incrementCountForPay();
              
                   } catch (PropertyVetoException ex) {
//                       Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
                       JOptionPane.showMessageDialog(mainview, ex+"from doubleclick listener");
                   }
                }
                else{
                    
                  JInternalFrame[] frames=  mainview.desktop.getAllFrames();
                   for (JInternalFrame frame : frames) {
                       if (frame.getTitle().equalsIgnoreCase("Order Bill Pay Window")) {
                           try {
                              //checking authorization for order 
                             Object[] dep = ordermodel.getRespectiveDepartmentForOrderid(Integer.parseInt(table.getValueAt(Lead, 0).toString()));   
                               
                                  OrderBillView view = (OrderBillView)frame;
                                  //checking for authorize  user
                               ComboBoxModel departmentCombo = view.returnComboDepartmentName().getModel();
                               boolean authoriseflag = false;
                                for(int i=0;i<departmentCombo.getSize();i++){
                           if(departmentCombo.getElementAt(i).equals(dep[1])){
                               authoriseflag = true;
//                                mainview.desktop.add(billpay.OrderBillView);
//                               billpay.OrderBillView.setSelected(true);
                               view.setcomboDepartmentName(dep[1].toString());
//                               System.out.println(dep[1]);
                               break;
                           }
                       }
                       if(!authoriseflag){
                           JOptionPane.showMessageDialog(mainview, "This Order has been ordered by another Department.");
                           return;
                       }
                       //if authorise set selected
//                       if(!view.isMaximum()){
//                           view.setMaximum(true);
//                       }
                           
                               view.setSelected(true);
                               view.setSearch(table.getValueAt(Lead, 0).toString());
                               view.btnSearchOrder.doClick();
                               
                           }catch (PropertyVetoException ex) {
//                             Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
                               JOptionPane.showMessageDialog(mainview, ex+"from doubleclick listener");
                           }
                       }
                   }
                    
                }
               
           }
         }
     }
     /*
      * mainly by order and cancel
      */
     public class ItemCrudListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("Order")){
              //  String[] info = new String[]{orderview.getOrderId(),orderview.getTableId(),orderview.getWaiterId(),orderview.getCustomerId()};
              //fetch default and order printer for particlar department
//               final String dPrinter = ordermodel.getDefaultPrinter(orderview.getDepartmentId());
//               final String oPrinter = ordermodel.getOrderPrinter(orderview.getDepartmentId());
                //System.out.print(orderview.getDepartmentId());
                
//checking whether  the date has been closed by the admin
                
                
                Object[] dateinfo = Function.returnSystemDateInfo();
                
                //this is given first before of shotcut key
                 orderview.setAddOrderEditableFalse();
                 orderview.setAddOrderAndPrintEditableFalse();
                   if(dateinfo[2] == Boolean.TRUE && dateinfo[3] == Boolean.FALSE){
                       
                   }
                   else{
                      JOptionPane.showMessageDialog(orderview, "Please First Open the Date to Perform Order Transaction.");
                    return;
                   }
                if(orderview.getTableOrderList().getRowCount() <=0){
                   JOptionPane.showMessageDialog(orderview, "Please Add Aleast one item to order");
                   return;
               }
               /*
               checking for the same table  and return if same table order
               */
//               for(int i=0;i<orderview.getTableOrderedList().getRowCount();i++){
//                   if(orderview.getTableOrderedList().getValueAt(i, 1) != null ){
//                   if(orderview.getTableOrderedList().getValueAt(i, 1).equals(orderview.getComboTableName())){
//                      JOptionPane.showMessageDialog(orderview, "Table "+orderview.getComboTableName()+" is already packed");
//                      return;
//                   }
//                   }
//               }
               if(orderview.getTableId() != 0 ){ 
               if(ordermodel.checkExistingTableid(orderview.getTableId())){
                  JOptionPane.showMessageDialog(orderview, "Table "+orderview.getComboTableName()+" is already packed");
                      return;  
               }
               }
//                System.out.println("wala");
               if(DisplayMessages.displayInputYesNo(orderview, "Do you Want to Order ","Order  Window"))
                {
//                    System.out.println(orderview.getOrderId());
                ordermodel.AddOrder(ordermodel.convertDefaultTableModelToObject(orderview.getTableOrderList()),Integer.parseInt(orderview.getOrderId()),orderview.getTableId(),orderview.getWaiterId(),orderview.getCustomerId(),mainview.getUserId(),orderview.getDepartmentId());
                //set order to printer not needed
//                Map para = new HashMap<>();
//                para.put("orderId",Integer.parseInt(orderview.getOrderId()));
//                PrintOrder order = new PrintOrder(para,oPrinter,dPrinter);
//                order.printOrder();
                
                orderview.clearOrderData();
                orderview.setMainOrderId(0);
                orderview.getTableOrderList().setRowCount(0);
               int neworderid = ordermodel.returnCurrentItentityId("order_list");
                 orderview.setOrderId(String.valueOf(neworderid));
                 orderview.setMainOrderId(neworderid);
                 orderview.setDeleteEditableFalse();
                
                
                  orderview.txtOrderQuantity.setEnabled(true);
                   orderview.refreshOrderedListJTable(ordermodel.getOrderInfo(orderview.getDepartmentId()));
                 
                   /*
                    * here is manipulation for refreshing the data in order pay if it is open
                    */
                 JInternalFrame[] iframes =   mainview.desktop.getAllFrames();
                   for (JInternalFrame iframe : iframes) {
                       if (iframe.getTitle().equalsIgnoreCase("Order Bill Pay Window")) {
                           /*
                            * refreshing the billorder view the ordered table data
                            */
//                           iframe.dispose();
//                           ExecuteOrderBill billpay = new ExecuteOrderBill(mainview);
//                           mainview.desktop.add(billpay.OrderBillView);
//                           System.out.println("wala");
//                           OrderBillController BillControll = null;
                           OrderBillView BillView = (OrderBillView)iframe;
//                           BillView.setSelected(true);
//                           BillView.refreshJTableOrderedList(BillControll.obmodel.getOrderInfo());
                          BillView.btnRefresh.doClick();
//                       
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
                
                  
                }
                else{
                    //perform thing to make it to previos condtion
                   orderview.setAddOrderEditableTrue();
                   orderview.setAddOrderAndPrintEditableTrue();
                }
            }
            else if(e.getActionCommand().equalsIgnoreCase("Order&Print")){
                 //  String[] info = new String[]{orderview.getOrderId(),orderview.getTableId(),orderview.getWaiterId(),orderview.getCustomerId()};
              //fetch default and order printer for particlar department
                final String dPrinter = ordermodel.getDefaultPrinter(orderview.getDepartmentId());
                final String oPrinter = ordermodel.getOrderPrinter(orderview.getDepartmentId());
                //System.out.print(orderview.getDepartmentId());
                
//checking whether  the date has been closed by the admin
                
                
                Object[] dateinfo = Function.returnSystemDateInfo();
                
                //this is given first before of shotcut key
                 orderview.setAddOrderEditableFalse();
                 orderview.setAddOrderAndPrintEditableFalse();
                   if(dateinfo[2] == Boolean.TRUE && dateinfo[3] == Boolean.FALSE){
                       
                   }
                   else{
                      JOptionPane.showMessageDialog(orderview, "Please First Open the Date to Perform Order Transaction.");
                    return;
                   }
                if(orderview.getTableOrderList().getRowCount() <=0){
                   JOptionPane.showMessageDialog(orderview, "Please Add Aleast one item to order");
                   return;
               }
               /*
               checking for the same table  and return if same table order
               */
//               for(int i=0;i<orderview.getTableOrderedList().getRowCount();i++){
//                   if(orderview.getTableOrderedList().getValueAt(i, 1) != null ){
//                   if(orderview.getTableOrderedList().getValueAt(i, 1).equals(orderview.getComboTableName())){
//                      JOptionPane.showMessageDialog(orderview, "Table "+orderview.getComboTableName()+" is already packed");
//                      return;
//                   }
//                   }
//               }
               if(orderview.getTableId() != 0 ){ 
               if(ordermodel.checkExistingTableid(orderview.getTableId())){
                  JOptionPane.showMessageDialog(orderview, "Table "+orderview.getComboTableName()+" is already packed");
                      return;  
               }
               }
//                System.out.println("wala");
               if(DisplayMessages.displayInputYesNo(orderview, "Do you Want to Order and Print the Bill ","Order and Print Window"))
                {
//                    System.out.println(orderview.getOrderId());
                ordermodel.AddOrder(ordermodel.convertDefaultTableModelToObject(orderview.getTableOrderList()),Integer.parseInt(orderview.getOrderId()),orderview.getTableId(),orderview.getWaiterId(),orderview.getCustomerId(),mainview.getUserId(),orderview.getDepartmentId());
                final int orderid = Integer.parseInt(orderview.getOrderId());
                //set order to printer
                //do this in a thread so that it doesnot affect another system
                    SwingUtilities.invokeLater(new Runnable(){

                    @Override
                    public void run() {
                        try{
                        Map para = new HashMap<>();
                        para.put("orderId",orderid);
                        PrintOrder order = new PrintOrder(para,oPrinter,dPrinter);
                        order.printOrder();
                        }
                        catch(Exception se){
                            DisplayMessages.displayError(mainview, "Bill Not Printed."+se.getMessage(),"Print Error");
                        }
                    }
                        
                    });
                
                
                orderview.clearOrderData();
                orderview.setMainOrderId(0);
                orderview.getTableOrderList().setRowCount(0);
                int neworderid = ordermodel.returnCurrentItentityId("order_list");
                orderview.setOrderId(String.valueOf(neworderid));
                orderview.setMainOrderId(neworderid);
                orderview.setDeleteEditableFalse();
                
                
                  orderview.txtOrderQuantity.setEnabled(true);
                  orderview.refreshOrderedListJTable(ordermodel.getOrderInfo(orderview.getDepartmentId()));
                 
                   /*
                    * here is manipulation for refreshing the data in order pay if it is open
                    */
                 JInternalFrame[] iframes =   mainview.desktop.getAllFrames();
                   for (JInternalFrame iframe : iframes) {
                       if (iframe.getTitle().equalsIgnoreCase("Order Bill Pay Window")) {
                           /*
                            * refreshing the billorder view the ordered table data
                            */
//                           iframe.dispose();
//                           ExecuteOrderBill billpay = new ExecuteOrderBill(mainview);
//                           mainview.desktop.add(billpay.OrderBillView);
//                           System.out.println("wala");
//                           OrderBillController BillControll = null;
                           OrderBillView BillView = (OrderBillView)iframe;
//                           BillView.setSelected(true);
//                           BillView.refreshJTableOrderedList(BillControll.obmodel.getOrderInfo());
                          BillView.btnRefresh.doClick();
//                       
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
                
                  
                }
                else{
                    //perform thing to make it to previos condtion
                   orderview.setAddOrderEditableTrue();
                   orderview.setAddOrderAndPrintEditableTrue();
                }
            }
            else if(e.getActionCommand().equalsIgnoreCase("OrderCancel")) {
                if(DisplayMessages.displayInputYesNo(orderview, "Do you Want to Cancel Order ","Order Cancel Window"))
                {
             
                   
               
                
                 orderview.getTableOrderList().setRowCount(0);
//                 orderview.setAddOrderEditableFalse();
//                 orderview.setAddOrderAndPrintEditableFalse();
                 orderview.setEditOrderEditableFalse();
                 orderview.setDeleteOrderEditableFalse();
                 orderview.setAddEditableTrue();
                  orderview.txtOrderQuantity.setEnabled(true);
//                     ordermodel.MakeCurrentItentityIdFalse(Integer.parseInt(orderview.getOrderId()));
//                 System.out.println("wala");
                
                   orderview.clearOrderData();
//                    orderview.setOrderId(String.valueOf(ordermodel.returnCurrentItentityId("order_list")));
                   orderview.setOrderId(String.valueOf(orderview.getMainOrderId()));
                  
                 
                }
                
            }
            else if (e.getActionCommand().equalsIgnoreCase("OrderEdit")){
                 //checking whether  the date has been closed by the admin
                   Object[] dateinfo = Function.returnSystemDateInfo();
                   //Make The Edit Button False so That it will not occur twice in shortcut
                    orderview.setEditOrderEditableFalse();
                    orderview.setDeleteOrderEditableFalse();
                   if(dateinfo[2] == Boolean.TRUE && dateinfo[3] == Boolean.FALSE){
                     
                   }
                   else{
                      JOptionPane.showMessageDialog(orderview, "Please First Open the Date to Perform Order Transaction.");
                    return;
                   }
                  if(InitialTableId != orderview.getTableId()){
                     if(ordermodel.checkExistingTableid(orderview.getTableId())){
                            JOptionPane.showMessageDialog(orderview, "Table "+orderview.getComboTableName()+" is already packed");
                      return;  
                        }
                  }
                     
                if(DisplayMessages.displayInputYesNo(orderview, "Do you Want to Edit This Order.","Order Edit Window"))
                {
//                    ordermodel.EditOrder(ordermodel.convertDefaultTableModelToObject(orderview.getTableOrderList()),orderview.getOrderId(),orderview.getTableId(),orderview.getWaiterId(),orderview.getCustomerId());
                     ordermodel.EditOrder(DeleteData,ordermodel.convertDefaultTableModelToObject(orderview.getTableOrderList()),Integer.parseInt(orderview.getOrderId()),orderview.getTableId(),orderview.getWaiterId(),orderview.getCustomerId(),mainview.getUserId(),orderview.getDepartmentId());
                    orderview.clearOrderData();
//                    orderview.setOrderId(String.valueOf(ordermodel.returnCurrentItentityId("order_list")));
                    orderview.setOrderId(String.valueOf(orderview.getMainOrderId()));
                    
                 orderview.getTableOrderList().setRowCount(0);
                  orderview.refreshOrderedListJTable(ordermodel.getOrderInfo(orderview.getDepartmentId()));
//                  JOptionPane.showMessageDialog(mainview, "radhe");
                 orderview.setAddEditableTrue();
                  orderview.txtOrderQuantity.setEnabled(true);
                  orderview.setDeleteEditableFalse();
                    /*
                    * here is manipulation for refreshing the data in order pay if it is open
                    */
                 JInternalFrame[] iframes =   mainview.desktop.getAllFrames();
                   for (JInternalFrame iframe : iframes) {
                       if (iframe.getTitle().equalsIgnoreCase("Order Bill Pay Window")) {
                           /*
                            * refreshing the billorder view the ordered table data
                            */
//                           iframe.dispose();
//                           ExecuteOrderBill billpay = new ExecuteOrderBill(mainview);
//                           mainview.desktop.add(billpay.OrderBillView);
                            OrderBillView BillView = (OrderBillView)iframe;
//                           BillView.setSelected(true);
//                           BillView.refreshJTableOrderedList(BillControll.obmodel.getOrderInfo());
                          BillView.btnRefresh.doClick();   
//                       
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
                }
                else{
                    //perform to undo the afther Clicking Edit Button
                    orderview.setEditOrderEditableTrue();
                    orderview.setDeleteOrderEditableTrue();
                }
                
            }
            else if (e.getActionCommand().equalsIgnoreCase("OrderDelete")){
                 //checking whether  the date has been closed by the admin
                   Object[] dateinfo = Function.returnSystemDateInfo();
                   //making the  Delete disable so that it doesnot occue twice
                    orderview.setDeleteEditableFalse(); 
                    orderview.setEditOrderEditableFalse();
                   if(dateinfo[2] == Boolean.TRUE && dateinfo[3] == Boolean.FALSE){
                       
                   }
                   else{
                      JOptionPane.showMessageDialog(orderview, "Please First Open the Date to Perform Order Transaction.");
                    return;
                   }
                if(DisplayMessages.displayInputYesNo(orderview, "Do you Want to Delete This Order.","Order Delete Window"))
                {
                     ordermodel.DeleteOrder(ordermodel.convertDefaultTableModelToObject(orderview.getTableOrderList()),Integer.parseInt(orderview.getOrderId()),orderview.getTableId(),orderview.getWaiterId(),orderview.getCustomerId());
                    orderview.clearOrderData();
//                     orderview.setOrderId(String.valueOf(ordermodel.returnCurrentItentityId("order_list")));
                  orderview.setOrderId(String.valueOf(orderview.getMainOrderId()));  
                  orderview.getTableOrderList().setRowCount(0);
                  orderview.refreshOrderedListJTable(ordermodel.getOrderInfo(orderview.getDepartmentId()));
                  orderview.setAddEditableTrue();
                  orderview.txtOrderQuantity.setEnabled(true);
                 
                 
                  orderview.setDeleteOrderEditableFalse();
                  
                      /*
                    * here is manipulation for refreshing the data in order pay if it is open
                    */
                 JInternalFrame[] iframes =   mainview.desktop.getAllFrames();
                   for (JInternalFrame iframe : iframes) {
                       if (iframe.getTitle().equalsIgnoreCase("Order Bill Pay Window")) {
                           /*
                            * refreshing the billorder view the ordered table data
                            */
//                           iframe.dispose();
//                           ExecuteOrderBill billpay = new ExecuteOrderBill(mainview);
//                           mainview.desktop.add(billpay.OrderBillView);
                            OrderBillView BillView = (OrderBillView)iframe;
//                           BillView.setSelected(true);
//                           BillView.refreshJTableOrderedList(BillControll.obmodel.getOrderInfo());
                          BillView.btnRefresh.doClick();   
//                       
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
                  
                  
                }
                   else{
                    //perform to undo the afther Clicking Edit Button
                    orderview.setEditOrderEditableTrue();
                    orderview.setDeleteOrderEditableTrue();
                }
            }
            else if (e.getActionCommand().equalsIgnoreCase("Refresh")){
                 orderview.refreshOrderedListJTable(ordermodel.getOrderInfo(orderview.getDepartmentId()));
            }
            else if (e.getActionCommand().equalsIgnoreCase("OrderSearch")){
                   String strsearch;
           // String[] SearchBox = new String[7];
            boolean flag = false;
            int col =0;
             try{
                 strsearch = orderview.getSearch();
                 for(int row=0;row<orderview.getTableOrderedList().getRowCount();row++){
                     if(strsearch.equalsIgnoreCase(orderview.getTableOrderedList().getValueAt(row, col).toString())){
                          //inorer to scrooll over the tbale
                     orderview.tblOrderedList.scrollRectToVisible(orderview.tblOrderedList.getCellRect(row, 0, true));
                     // for focus
                     orderview.tblOrderedList.setRowSelectionInterval(row, row);
                     orderview.setSearch("");
                     flag = true;
                     
                     }
                 }
                 if(flag== false){
                     JOptionPane.showMessageDialog(orderview, "Order Not Found");
                 }
             }   
             catch(HeadlessException se){
                 JOptionPane.showMessageDialog(orderview, se+"from Search Listener");
//                 return;
             }
            }
            
        }
        catch(HeadlessException | NumberFormatException ie){
            JOptionPane.showMessageDialog(orderview, ie+"from ItemCrudListener");
        }
        }
         
     }
    public  class  AddModuleListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("AddTable")){
               //System.out.println("wala");
//               ExecuteTableCrud tablecrud =  new ExecuteTableCrud(orderview,true);
               /*
                * forming class for add table
                */
               class Table extends JDialog{
//                   Table(JFrame parent,boolean modal){
                       Table(){
//                         super(parent,modal);
                        TableCrudModel TableCrudModel = new TableCrudModel();
//                TableCrudView TableCrudView = new TableCrudView(parent,modal);
                 TableCrudView TableCrudView = new TableCrudView(new JFrame(),true);


                TableCrudController TableCrudController = new TableCrudController(TableCrudModel,TableCrudView,mainview);
                TableCrudView.addWindowListener(new WindowAdapter(){
                    @Override
                    public void windowClosing(WindowEvent e){
                        orderview.setComboTableName(ordermodel.returnTableName(ordermodel.getTableInfoObject()));
                         orderview.AddSelectInCombo(orderview.returnTableComboBox());
     
                        
                       // System.out.println("wala");
                    }
                });

                 TableCrudView.setVisible(true);

                   }
               }
//               Table table = new Table(orderview,true);
               Table table = new Table();
         
       
        }
           else if(e.getActionCommand().equalsIgnoreCase("AddCustomer")){
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
                               orderview.setComboCustomerName(ordermodel.returnCustomerName(ordermodel.getCustomerInfoObject()));
                               orderview.AddSelectInCombo(orderview.returnCustomerComboBox());
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
           else if(e.getActionCommand().equalsIgnoreCase("AddWaiter")){
            
              // ExecuteWaiter Waiter = new ExecuteWaiter(orderview,true);
               /*
                * class for window closng and refresing the waiter combo box
                */
               class Waiter extends JDialog{
                  private  WaiterView WaiterView;
                   private  WaiterModel WaiterModel;
                   private   WaiterController WaiterController;
                            
                   
//                   Waiter(JFrame parent,boolean modal){
                        Waiter(){
//                                    super(parent,modal);
//                                    WaiterView = new WaiterView(parent,modal);
                                     WaiterView = new WaiterView(new JFrame(),true);
                                    WaiterModel = new WaiterModel();

                                     WaiterController = new WaiterController(WaiterModel,WaiterView);
                                    WaiterView.addWindowListener(new WindowAdapter(){
                                        public void windowClosing(WindowEvent e){
                                            orderview.setComboWaiterName(ordermodel.returnWaiterName(ordermodel.getWaiterInfoObject()));
                                            orderview.AddSelectInCombo(orderview.returnWaiterComboBox());
                                       //       System.out.println("wala");
                                        }
                                    });
                                    WaiterView.setVisible(true);
                       
                   }
               }
               /*
                * calling class
                */
//               Waiter waiter = new Waiter(orderview,true);
                Waiter waiter = new Waiter();
               
           }
            
        }
        catch(Exception ate){
            JOptionPane.showMessageDialog(orderview, ate+"from AddModuleListener");
        }
        }
        
    }
    public class ComboListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("ComboTableName")){
               Object[] item = null;
               boolean flag = false;
                JComboBox combotable = (JComboBox) e.getSource();
                if(combotable.getSelectedIndex() == 0){
                  orderview.setTableId(0);   
                  
                }
                else{
                for (Object[] TableInfo : ordermodel.TableInfo) {
                    if (combotable.getSelectedItem().equals(TableInfo[1])) {
                        item = TableInfo;
                        // flag = true;
                        break;
                    }
                }
              //  if(flag== true){
                orderview.setTableId(Integer.parseInt(item[0].toString()));
                }
             //   }
              //  System.out.println(orderview.getTableId());
                
             }
            if(e.getActionCommand().equalsIgnoreCase("ComboMenuName")){
               //  System.out.println("wala");
                Object[] item = null;
                Object[][] MenuInfo = ordermodel.getMenuInfo(orderview.getDepartmentId());
                JComboBox comboMenu = (JComboBox) e.getSource();
//                System.out.println("wala");
                if(comboMenu.getSelectedIndex() == 0){
                      orderview.setMenuId(0);
//                 System.out.println(orderview.getMenuId());
                 orderview.setRate("0");
//                 System.out.println(orderview.getRate());
                 orderview.setItemBaseUnit("");
//                 System.out.println("walafrommenu");
                }
                else{
                    
               
                for (Object[] MenuInfo1 : MenuInfo) {
                    if (comboMenu.getSelectedItem().equals(MenuInfo1[1])) {
                        item = MenuInfo1;
                        
                        break;
                    }
                }
                /*
                 * perfom some operation
                 */
//                System.out.println(item[0]);
                 orderview.setMenuId(Integer.parseInt(item[0].toString()));
//                 System.out.println(orderview.getMenuId());
                 orderview.setRate(item[3].toString());
//                 System.out.println(orderview.getRate());
                 orderview.setItemBaseUnit(item[2].toString());
//                 System.out.println(orderview.getItemBaseUnit());
                
//                orderview.setFocusOntxtOrderQuantity();
                 }
                 
               //System.out.println(orderview.getMenuId() + orderview.getRate());
            }
             if(e.getActionCommand().equalsIgnoreCase("ComboCustomerName")){
               Object[] item = null;
               boolean flag = false;
                JComboBox combotable = (JComboBox) e.getSource();
                if(combotable.getSelectedIndex() == 0){
                    orderview.setCustomerId(0);
                }
                else{
                for (Object[] CustomerInfo : ordermodel.CustomerInfo) {
                    if (combotable.getSelectedItem().equals(CustomerInfo[1])) {
                        item = CustomerInfo;
                        // flag = true;
                        break;
                    }
                }
              //  if(flag== true){
               orderview.setCustomerId(Integer.parseInt(item[0].toString()));
                }
             //   }
             //   System.out.println(orderview.getCustomerId());
                
             }
              if(e.getActionCommand().equalsIgnoreCase("ComboWaiterName")){
               Object[] item = null;
               boolean flag = false;
                JComboBox combotable = (JComboBox) e.getSource();
                if(combotable.getSelectedIndex() == 0){
                  orderview.setWaiterId(0);   
                }
                else{
                for (Object[] WaiterInfo : ordermodel.WaiterInfo) {
                    if (combotable.getSelectedItem().equals(WaiterInfo[1])) {
                        item = WaiterInfo;
                        // flag = true;
                        break;
                    }
                }
              //  if(flag== true){
             orderview.setWaiterId(Integer.parseInt(item[0].toString()));
             //   }
              // System.out.println(orderview.getWaiterId());
                
             }
              }
              //for department id
              if(e.getActionCommand().equalsIgnoreCase("comboDepartmentName")){
                 Object[][] departmentdata= ordermodel.getRespectiveDepartment(mainview.getUserId());
                JComboBox dep = (JComboBox)e.getSource();
                 if(dep.getSelectedItem().equals("SELECT")){
                     orderview.setDepartmentId(0);
                     orderview.refreshOrderedListJTable(new DefaultTableModel());
                     orderview.setComboMenuName(new String[]{});
                     
                 }
                 else{
                     for(Object[] dat:departmentdata){
                         if(dat[1].equals(dep.getSelectedItem())){
                             orderview.setDepartmentId(Integer.parseInt(dat[0].toString()));
                             //loading the repective item according to department  
                             orderview.setComboMenuName(ordermodel.returnMenuName(ordermodel.getMenuInfo(orderview.getDepartmentId())));
                             orderview.AddSelectInCombo(orderview.returnMenuComboBox());
                             orderview.refreshOrderedListJTable(ordermodel.getOrderInfo(orderview.getDepartmentId()));
                             break;
                             
                         }
                     }
                 }
              }
              }
        catch(Exception ce){
            JOptionPane.showMessageDialog(orderview, ce+"from comboListener");
        }
      
        
        }
    }
//    public class ComboItemListener implements ItemListener{
//
//        @Override
//        public void itemStateChanged(ItemEvent e) {
////            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        try{
//               Object[] item = null;
//                Object[][] MenuInfo = ordermodel.getMenuInfo();
//                JComboBox comboMenu = (JComboBox) e.getSource();
//               
//                if(comboMenu.getSelectedIndex() == 0){
//                      orderview.setMenuId(0);
////                 System.out.println(orderview.getMenuId());
//                 orderview.setRate("0");
////                 System.out.println(orderview.getRate());
//                 orderview.setItemBaseUnit("");
////                 System.out.println("walafrommenu");
//                }
//                else{
//                    
//               
//                for (Object[] MenuInfo1 : MenuInfo) {
//                    if (comboMenu.getSelectedItem().equals(MenuInfo1[1])) {
//                        item = MenuInfo1;
//                        break;
//                    }
//                }
//                /*
//                 * perfom some operation
//                 */
////                System.out.println(item[0]);
//                 orderview.setMenuId(Integer.parseInt(item[0].toString()));
////                 System.out.println(orderview.getMenuId());
//                 orderview.setRate(item[3].toString());
////                 System.out.println(orderview.getRate());
//                 orderview.setItemBaseUnit(item[2].toString());
////                 System.out.println(orderview.getItemBaseUnit());
//                
//                orderview.setFocusOntxtOrderQuantity();
//                 } 
//        }
//        catch(Exception se){
//            JOptionPane.showMessageDialog(mainview, se+"from ComboItemListener " +getClass().getName());
//        }
//        }
//        
//    }
    public class CrudListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            try{
               
                if(e.getActionCommand().equalsIgnoreCase("Add")){
                  
                if(orderview.getMenuId() == 0){
//                    System.out.println(orderview.getMenuId());
                JOptionPane.showMessageDialog(orderview, "Please Select  any Item");
                orderview.addcomboMenuNameFocus();//add focus of cobomenu
                
                    return;    
                }
              
                if(orderview.getQuantity() == 0.0){
                    JOptionPane.showMessageDialog(orderview, "Empty Field detected in Quantity");
                    orderview.setFocusOntxtOrderQuantity();
                    return;
                }
                 
//                try {
////                    System.out.println(orderview.getRate());
//                   Float.parseFloat(orderview.getQuantity());
////                  
//                }
//                catch(NumberFormatException ne){
//                    JOptionPane.showMessageDialog(orderview, "Quantity must be a number.");
//                    return;
//                }
               
               TotalAmount = new BigDecimal(orderview.getQuantity()).setScale(3, RoundingMode.HALF_UP).multiply(orderview.getRate()).setScale(2, RoundingMode.HALF_UP);
                 
              
               /*
                * checking it availability
                */
               if(ordermodel.checkTrackable(orderview.getMenuId())){
                   if(ordermodel.checkHybrid(orderview.getMenuId())){
                       String status = new String();
//                        System.out.println(orderview.getQuantity());
                       boolean flag = false;
                       //if it hybrid type
                       String[][] hybriditem = ordermodel.checkHybridTrackableItem(orderview.getMenuId(), orderview.getQuantity());
                       for (String[] hybriditem1 : hybriditem) {
                           int x = new BigDecimal(hybriditem1[1]).signum();
                           if (x<0) {
                               status += "MenuItem is Insufficient  to add due to insufficient amount in " + hybriditem1[0] + "\n";
                               flag =true;
                           }
                       }
                       if(flag == true){
                           JOptionPane.showMessageDialog(orderview, status);
                           return;
                       }
                       ordermodel.checkHybridTrackableItemthreshold(orderview.getMenuId());
//                        System.out.println("wala");
                   }
                   else{
                        
                     
                       BigDecimal net = ordermodel.checkSingleTrackableItem(orderview.getMenuId(), orderview.getQuantity());
                   
                       int i = net.signum();
                       if(i < 0){
                           JOptionPane.showMessageDialog(orderview, "Item is Insufficient to Add for order.First Issue the item");
                           return;
                       }
                       ordermodel.checkSingleTrackableItemThreshold(orderview.getMenuId());
//                       System.out.println(orderview.getQuantity());
                   }
                  // return;
               }
             Object[] row = new Object[]{orderview.getMenuId(),orderview.getComboMenuName().toString(),new BigDecimal(orderview.getQuantity()).setScale(3, RoundingMode.HALF_UP),orderview.getItemBaseUnit(),orderview.getRate(),TotalAmount};
                orderview.getTableOrderList().addRow(row);
//                if(ordermodel.returnCurrentItentityId("order_list") == Integer.parseInt(orderview.getOrderId())){
                    if(Integer.parseInt(orderview.getOrderId()) == orderview.getMainOrderId()){
                     orderview.setAddOrderAndPrintEditableTrue();
                     orderview.setAddOrderEditableTrue(); 
                     //checking 
//                      orderview.setFocusOnButtonOrder();
                     orderview.addcomboMenuNameFocus();//add focus of cobomenu
                        }
                    else{
                        
//                        orderview.setFocusOnButtonEdit();
                         orderview.addcomboMenuNameFocus();//add focus of cobomenu
                    }
                   orderview.setDeleteEditableTrue();
//               
//                }
                   orderview.clearAddData();
     
                }
                else  if(e.getActionCommand().equalsIgnoreCase("Delete")){
                    if(LeadRow < 0){
                        JOptionPane.showMessageDialog(orderview, "Plese Select the row to delete");
                        return;
                    }
                    orderview.getTableOrderList().removeRow(LeadRow);
                    orderview.setDeleteEditableFalse();
                }
                else if (e.getActionCommand().equalsIgnoreCase("SearchMenu")){
                    if(orderview.getSearchMenu().isEmpty()){
//                       JOptionPane.showMessageDialog(orderview, "Searching blanks.");
                        orderview.refreshSearchMenuJTable(ordermodel.getMenuInfoAll(orderview.getDepartmentId()));
                         orderview.SearchDailog.setVisible(true);
                       return;
//                        return;
                    }
                    
                  orderview.refreshSearchMenuJTable(ordermodel.getMenuInfoLike(orderview.getSearchMenu(),orderview.getDepartmentId()));
                  if(orderview.getSearchTable().getRowCount() <= 0 ){
                      JOptionPane.showMessageDialog(orderview, "Item Not Found");
                      
                      return;
                  }
                  orderview.SearchDailog.setVisible(true);
                  
           /*  orderview.tblSearch.addMouseListener(new MouseAdapter() {
                  public void mouseClicked(MouseEvent me){
                      if(me.getClickCount() == 2){
                         // System.out.println("wala");
                          
                      }
                  }  
                  });*/
               
           
                  
                }
                
               
            }
            catch(Exception ce){
                JOptionPane.showMessageDialog(orderview, ce+"from CrudListener");
            }
            
        }
          
        
    }
    public class listenActionForTableEdit extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
              TableCellListener tcl = (TableCellListener)e.getSource();
                         /*   System.out.println("Row   : " + tcl.getRow());
                            System.out.println("Column: " + tcl.getColumn());
                            System.out.println("Old   : " + tcl.getOldValue());
                            System.out.println("New   : " + tcl.getNewValue());
                            * */
                            int row = tcl.getRow();
                            int col = tcl.getColumn();
                            Object oQuantity = tcl.getTable().getValueAt(row, col);

                          try{
                              Float.parseFloat(oQuantity.toString());
                          }
                          catch(NumberFormatException se){
                                JOptionPane.showMessageDialog(orderview, "Please Enter Numbers only");
                                tcl.getTable().setValueAt(tcl.getOldValue(),row,col);
                                return;
                          }


                                BigDecimal rate = new BigDecimal(tcl.getTable().getValueAt(row, 4).toString());
                                    BigDecimal TotalAmount;
                                    BigDecimal Quantity = new BigDecimal(tcl.getTable().getValueAt(row, col).toString());
                                    /*
                * checking it availability
                */
               if(ordermodel.checkTrackable(Integer.parseInt(tcl.getTable().getValueAt(row, 0).toString()))){
                   if(ordermodel.checkHybrid(Integer.parseInt(tcl.getTable().getValueAt(row, 0).toString()))){
                       //for hybrid
                        String status = new String();
                       boolean flag = false;
                       //if it hybrid type
                       String[][] hybriditem = ordermodel.checkHybridTrackableItem(Integer.parseInt(tcl.getTable().getValueAt(row, 0).toString()),new Double(Quantity.doubleValue()));
                       for (String[] hybriditem1 : hybriditem) {
                           int x = new BigDecimal(hybriditem1[1]).signum();
                           if (x<0) {
                               status += "MenuItem is Insufficient  to add due to insufficient amount in " + hybriditem1[0] + "\n";
                               flag =true;
                           }
                       }
                       if(flag == true){
                           JOptionPane.showMessageDialog(orderview, status);
                           tcl.getTable().setValueAt(tcl.getOldValue(),row,col);
                           return;
                       }
                       
                   }
                   else{
                     
                       BigDecimal net = ordermodel.checkSingleTrackableItem(Integer.parseInt(tcl.getTable().getValueAt(row, 0).toString()),new Double(Quantity.doubleValue()));
                     
                       int i = net.signum();
                       if(i < 0){
                           JOptionPane.showMessageDialog(orderview, "Item is Insufficient to Edit for order.First Issue the item");
                          tcl.getTable().setValueAt(tcl.getOldValue(),row,col);
                           return;
                       }
                       ordermodel.checkSingleTrackableItemThreshold(Integer.parseInt(tcl.getTable().getValueAt(row, 0).toString()));
                   }
                  // return;
               }
                                    
                                    TotalAmount = Quantity.multiply(rate);
                                  //  System.out.println(TotalAmount);
                                  //  System.out.println(Quantity);
                                  //  System.out.println(rate);
                                    tcl.getTable().setValueAt(TotalAmount, row, 5);
                                   if(Integer.parseInt(orderview.getOrderId())== orderview.getMainOrderId()){
                                   orderview.setFocusOnButtonOrder();
                                   }
                                   else{
                                    orderview.setFocusOnButtonEdit(); 
                                   }
                                   
            
        }
        catch(Exception le){
            JOptionPane.showMessageDialog(orderview, le+"from listenActionForTableEdit ");
        }
        }
        
    }
    public class returnOrderTableListSelectionListener implements ListSelectionListener{
        OrderView returnview = new OrderView();
        JTable table = new JTable()/*{
            public void changeSelection(final int row, final int column, boolean toggle, boolean extend)
            {
                super.changeSelection(row, column, toggle, extend);
                table.editCellAt(row, column);
                table.transferFocus();
            }
        }*/;
        public returnOrderTableListSelectionListener(OrderView view){
            returnview = view;
            table = returnview.tblOrderList;
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
                LeadRow = Lead;
//               
//                 if(ordermodel.returnCurrentItentityId("order_list") == Integer.parseInt(returnview.getOrderId())){
//              returnview.setDeleteEditableTrue();
//                 }
//                 else{
                     returnview.setDeleteEditableTrue();
//                 }
               
            }
        
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(returnview, se+"from returnOrderTableListSelectionListener");
        }
        }
        
        
    }
    public class returnOrderedTableListSelectionListener implements ListSelectionListener{
        OrderView returnview = new OrderView();
        JTable table = new JTable()/*{
            public void changeSelection(final int row, final int column, boolean toggle, boolean extend)
            {
                super.changeSelection(row, column, toggle, extend);
                table.editCellAt(row, column);
                table.transferFocus();
            }
        }*/;
        public returnOrderedTableListSelectionListener(OrderView view){
            returnview = view;
            table = returnview.tblOrderedList;
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
                orderview.setOrderId(table.getValueAt(Lead, 0).toString());
              
                DeleteData = ordermodel.getItemListByOrderId(orderview.getOrderId());
               
                
                orderview.getTableOrderList().setRowCount(0);
                for (Object[] itemrow1 : DeleteData) {
                    orderview.getTableOrderList().addRow(itemrow1);
                }
                orderview.setOrderData(ordermodel.getOtherInfoByOrderId(orderview.getOrderId()));
//                orderview.setAddEditableFalse();
//                orderview.txtOrderQuantity.setEnabled(false);
               InitialTableId = orderview.getTableId();
               orderview.setEditOrderEditableTrue();
               orderview.setDeleteOrderEditableTrue();
               orderview.setAddOrderEditableFalse();
               orderview.setAddOrderAndPrintEditableFalse();
//                orderview.setDeleteEditableFalse();
            }
        
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(returnview, se+"from returnedOrderTableListSelectionListener");
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
   public class TextCrudListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
             orderview.setFocusOnAddOrder();
//            
        }
        catch(Exception te){
            JOptionPane.showMessageDialog(orderview, te+"TextCrudListener");
        }
        }
       
   }
   public class InteralFrameCloseListener extends InternalFrameAdapter{
     @Override
     public void  internalFrameClosing(InternalFrameEvent e){
//      System.out.println("wala");
         mainview.setCountForOrder(0);
           ordermodel.MakeCurrentItentityIdFalse(orderview.getMainOrderId());
         e.getInternalFrame().dispose();
//         orderview.setVisible(false);
      
     }
   }
   /*
   handler for the shortcut key
   */
   public  class OrderShortcutHandler implements KeyEventDispatcher{

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
           //which shortcut to perform
            try{
            if(orderview.isSelected()){
            if(e.getID() == KeyEvent.KEY_PRESSED){
            //to order 
                //not needed since key mnenomics does this work
                /*
                if(e.getKeyCode() == KeyEvent.VK_S && e.isControlDown()){
                   if(Integer.parseInt(orderview.getOrderId()) == orderview.getMainOrderId()){
                    orderview.getBtnOrder().doClick();
                   }
                   //if it is selected for edit
                   else{
                       orderview.getBtnOrderEdit().doClick();
                   }
                  }
                        
                if(e.getKeyCode() ==KeyEvent.VK_S && e.isControlDown() && e.isAltDown()){
                    orderview.getBtnOrderAndPrint().doClick();
                }
                //to delete
                if(e.getKeyCode() == KeyEvent.VK_D && e.isControlDown()){
                    orderview.getBtnDelete().doClick();
                }
                        */
                //to exit the frame
                if(e.getKeyCode() == KeyEvent.VK_X && e.isControlDown()){
                     try {
                        orderview.setClosed(true);
//                        orderview.setVisible(false);
                    } catch (PropertyVetoException ex) {
                       DisplayMessages.displayError(mainview, ex.getMessage()+" from OrderShortcutHandler "+getClass().getName(), "Error window");
                       
                    } 
                }
                //to get to focus to table order
                if(e.getKeyCode() == KeyEvent.VK_HOME){
                    orderview.tblOrderedList.requestFocusInWindow();
                }
                //to set focus on menu
                if(e.getKeyCode() == KeyEvent.VK_INSERT){
                    orderview.addcomboMenuNameFocus();
                }
                //to cancel
                //not needed
                /*
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    orderview.getBtnCancelOrder().doClick();
                  
                }
                        */
            
            }
            }
            }
            catch(NumberFormatException se){
                DisplayMessages.displayError(mainview, se.getMessage()+" from ShorcutForOrder "+getClass().getName(), "Error");
            }
            return false;
            
        }
       
   }
   public class AsbtractActionListener extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                //it enter is pressed at orderquantity
                if(e.getSource() == orderview.getTxtOrderQuantity()){
                    orderview.getBtnAdd().doClick();
                        
                }
                //if enter is pressed at menusearch
                if(e.getSource() == orderview.getTxtSearchMenu()){
                    orderview.getBtnSearchMenu().doClick();
                }
                //if enter is pressed at search order
                if(e.getSource() == orderview.getTxtSearch()){
                    orderview.getBtnSearchOrder().doClick();
                }
                if(e.getSource() == orderview.returnMenuComboBox()){
                    orderview.setFocusOntxtOrderQuantity();
                }
                
                
            }
            catch(Exception se){
                DisplayMessages.displayError(mainview, se.getMessage()+" from AsbtractActionListener "+getClass().getName(), "Error");
            }
        }
       
   }
   public class RefreshOrderedListTimer implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
       try{
//           orderview.btnRefresh.doClick();
            orderview.refreshOrderedListJTable(ordermodel.getOrderInfo(orderview.getDepartmentId()));
       }
       catch(Exception se){
           DisplayMessages.displayError(orderview, se.getMessage()+" from RefreshOrderedListTimer "+getClass().getName(), "Error");
       }
        }
       
   }
}