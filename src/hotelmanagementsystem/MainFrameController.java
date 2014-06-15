/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

import CompanyInfo.CompanyInfoView;
import UserCreditial.ExecuteUserCreditial;
import UserCrud.ExecuteResetPassword;
import UserCrud.ExecuteUserCrud;
import centerstore.departmentissuereport.ExecuteCenterostoreIssueReport;
import centerstore.departmentissuereport.ExecuteCenterstoreIssueReportCategory;
import centerstore.distributorComponent.ExecuteDistro;
import centerstore.issue.executeIssue;
import centerstore.itemCategoryComponent.ExecuteItemCategoryComponent;
import centerstore.itemEnteryComponent.ExecuteItemEntry;
import centerstore.itemEnteryComponent.ExecuteItemLot;
import resturant.issuereturn.executeIssueReturn;
import centerstore.itemunitentry.executeUnitItemEntry;
import centerstore.purchasereport.ExecutePurchaseReport;
import centerstore.purchasereturn.executePurchaseReturn;
import resturant.threshold.ExecuteThreshold;
import companysetup.executeCompanySetup;
import department.ExecuteDepartment;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import registrator.ExecuteRegister;
import report.distributorReport.DistributorReport;
import report.issueStock.IssueStockReport;
import resturant.complimentary.ExecuteComplimentary;
import resturant.orderbillvoid.ExecuteOrderBillVoid;
import resturant.complimentaryreport.ExecuteComplimentaryReport;
import resturant.creditpayment.ExecuteCreditPayment;
import resturant.customer.ExecuteCustomer;
import resturant.directbill.ExecuteDirectBill;
import resturant.discountreport.ExecuteDiscountReport;
import resturant.departmentissuereport.ExecuteIssueReport;
import resturant.departmentissuereport.ExecuteIssueReportCategory;
import resturant.issuestockreport.ExecuteIssueStockReport;
import resturant.itemwisesalesreport.ExecuteItemWiseSalesReport;
import resturant.menuentry.ExecuteMenuEntry;
import resturant.menulist.ExecuteMenuDetailList;
import resturant.menulist.ExecuteMenuList;
import resturant.menulistreport.ExecuteMenuListReport;
import resturant.order.ExecuteOrder;
import resturant.orderbill.ExecuteOrderBill;
import resturant.receivablereport.ExecuteReceiveableReport;
import resturant.servicechargereport.ExecuteServiceChargeReport;
import resturant.tablecrud.ExecuteTableCrud;
import resturant.taxreport.ExecuteTAXReport;
import resturant.waiter.ExecuteWaiter;
import resturant.waiterservicereport.ExecuteWaiterServiceReport;
import resturant.salesreport.ExecuteSalesReport;
import resturant.tablecrud.ExecuteTableStatusView;
import resturant.voidreport.ExecuteVoidReport;
import resturant.wastage.ExecuteWastage;
import resturant.wastagereport.ExecuteWastageReport;
import reusableClass.DisplayMessages;
import reusableClass.Function;
import systemdate.ExecuteDateClose;
import systemdate.ExecuteDateOpen;
import systemdate.ExecuteSystemDateChoose;

/**
 *
 * @author SUSHIL
 */
public final class MainFrameController {
    MainFrameModel MainFrameModel;
    MainFrameView MainFrameView;
    SystemLogInView sysview;
    ExecuteOrder order;
    
    static ExecuteMenuDetailList MenuDetailList = null ;
   
    
    public MainFrameController(MainFrameModel mfmodel,MainFrameView mfview,SystemLogInView systemview){
        MainFrameModel = mfmodel;
        MainFrameView = mfview;
        sysview = systemview;
        
        MainFrameView.addCompanySetupListener(new MenuItemListener());
        MainFrameView.addCustomerSetupListener(new MenuItemListener());
        MainFrameView.addDistributorSetupListener(new MenuItemListener());
        MainFrameView.addWaiterSetupListener(new MenuItemListener());
        MainFrameView.addSoftwareRegistration(new MenuItemListener());
        MainFrameView.addUnitSetupListener(new MenuItemListener());
        MainFrameView.addGroupSetupListener(new MenuItemListener());
        MainFrameView.addItemEntryListener(new MenuItemListener());
        MainFrameView.addItemLotListener(new MenuItemListener());
        /*
        terminal setup
        */
        MainFrameView.addIssueListener(new MenuItemListener());
        MainFrameView.addIssueReturnListener(new MenuItemListener());
        MainFrameView.addPurchaseReturnListener(new MenuItemListener());
        MainFrameView.addMenuEntryListener(new MenuItemListener());
        MainFrameView.addTableSetupListener(new MenuItemListener());
        MainFrameView.addThresholdSetupListener(new MenuItemListener());
        MainFrameView.addComplimentaryReasonSetupListenr(new MenuItemListener());
        //for terminal
        MainFrameView.addOrderListener(new MenuItemListener());
        MainFrameView.addPayListener(new MenuItemListener());
        MainFrameView.addMenuViewListener(new MenuItemListener());
        MainFrameView.addMenuDetailViewListener(new MenuItemListener());
        MainFrameView.addDirectBillPayListener(new MenuItemListener());
        MainFrameView.addTableStatusListener(new MenuItemListener());
        MainFrameView.addVOidBillListener(new MenuItemListener());
        MainFrameView.addWastageListener(new MenuItemListener());
        MainFrameView.addCreditPaymentListener(new MenuItemListener());
        //MainFrameView.
        /*
         * centerstore report
         */
        MainFrameView.addItemReportListener(new MenuItemListener());
        MainFrameView.addPurchaseReportListener(new MenuItemListener());
        MainFrameView.addPurchaseReturnReportListener(new MenuItemListener());
        MainFrameView.addCenterstoreItemwiseIssueReportListener(new MenuItemListener());
        MainFrameView.addCenterstoreCategorywiseIssueReportListener(new MenuItemListener());
        
//        MainFrameView.addIssueturnReportlistener(new MenuItemListener());
        MainFrameView.addDistributorReportListener(new MenuItemListener());
        /*
         * terminal report
         */
        MainFrameView.addIssueStockReportListener(new MenuItemListener());
        MainFrameView.addSalesReportListener(new MenuItemListener());
        MainFrameView.addItemWiseSaleReportListener(new MenuItemListener());
        MainFrameView.addRecievableReportListener(new MenuItemListener());
        MainFrameView.addTerminalItemWiseIssueReportListener(new MenuItemListener());
        MainFrameView.addTerminalCategoryWiseIssueReportListener(new MenuItemListener());
//        MainFrameView.addTerminalIssueReturnReportListener(new MenuItemListener());
        MainFrameView.addSVCReportListener(new MenuItemListener());
        MainFrameView.addVATReportListener(new MenuItemListener());
        MainFrameView.addDiscountReportListener(new MenuItemListener());
        MainFrameView.addComplimentaryReportListener(new MenuItemListener());
        MainFrameView.addWaiterServiceReportListener(new MenuItemListener());
        MainFrameView.addMenuListListener(new MenuItemListener());
        MainFrameView.addVoidReportListener(new MenuItemListener());
        MainFrameView.addWastageReportListener(new MenuItemListener());
       /*
        for system date
        */
        MainFrameView.addSystemDate(new MenuItemListener());
        /*
        for system config
        */
        MainFrameView.addUserCreditialListener(new MenuItemListener());
        MainFrameView.addManageUserListener(new MenuItemListener());
        MainFrameView.addDepartmentSetupListeener(new MenuItemListener());
        /*
         * adding mouse listener for popup menu
         */
        MainFrameView.addPopUpMenuListener(new PopUpMenuMouseListener());
        /*
        * adding the url to the dreamsys
        */
        MainFrameView.addDreamsysListener(new DreamsysListener());
        MainFrameView.addAboutUsListener(new MenuItemListener());
        /*
        loading the companu detail for the 
        */
//        try{
//        MainFrameView.setCompany(MainFrameModel.getCompanyDetail());
//        }
//        catch(NullPointerException ne){
//            JOptionPane.showMessageDialog(MainFrameView, ne+"From contructor");
//        }
        
        /*
        adapter for closing
        */
        MainFrameView.addWindowListener(new MainFrameCloseListener());
//       for custom display of menu as per the user
//        MainFrameModel.DisplayMenuItemAsCreditial( MainFrameModel.getUserCreditial(1),MainFrameView.getAllMenuItemText());
        //ading the actionlisten for log out
        MainFrameView.addLogOutListener(new LogOutListener());
        MainFrameView.addchangePasswordListener(new ChangePasswordListener());
          //running autodegenarateinvalidbillid algorithms
//        MainFrameModel.returnInvalidBillId(MainFrameModel.getAllGeneratedBill_id(), MainFrameModel.getValidBill_id());
   
        //Loading as JMENU AS PER THE DATBASE VALUE
        try{
             //FOR MAKING ALL INVALID GENERATE BILLID FALSE FOR NEW RENEWAL
        MainFrameModel.MakeAllInvalidBillIdFalse();
        ChangeMenuBarName(MainFrameView.getAllMenuItemText(), MainFrameModel.getJMenuName());
       
        //setting the timer for  update of the time
        Timer RefreshDate = new Timer(5000,new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
//                    DateFormat dateformat = DateFormat.getDateInstance(DateFormat.LONG);
                    DateFormat dateformat = new SimpleDateFormat("EEE, MMM  d ,Y");
                    if(Function.checkSystemDateExist()){
                    MainFrameView.setLblDate(dateformat.format(Function.returnSystemDate()));
                    //manage the day status
                    ManageDayStatus();
                    }
                }
                catch(Exception se){
                    DisplayMessages.displayError(sysview, se.getMessage()+"from refreshDate "+getClass().getName(), "Error");
                }
            }
            
        });
       
         //inserting the date according to the  point of sale system date
        DateFormat dateformat = new SimpleDateFormat("EEE, MMM  d ,Y");
        //check it there is date then only  update the date again the point of sale system date
        if(Function.checkSystemDateExist()){
        MainFrameView.setLblDate(dateformat.format(Function.returnSystemDate()));
        ManageDayStatus();
//        //check the date of the computer and pointofsale system and state status both doesnot coincide
//        Date posDate = Function.returnSystemDate();
//        Date SystemDate = new Date();
//        if(posDate != SystemDate){
//            DisplayMessages.displayInfo(mfview, "Computer Date( "+dateformat.format(SystemDate)+" ) and Our SOftware System Date( "+dateformat.format(posDate)+" )  Doesnot Match.\n Please Make Sure You are Running of the Current Date", " Date Info");
//        }
        
      
        }
          //also refresh the date
        RefreshDate.start();
       
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(MainFrameView, "from mainFrameContructor\n"+e.getMessage()+getClass().getName());
            System.exit(0);
        }
    }
    //this change the mainframe image for showing status of the day
     private void ManageDayStatus(){
             //for changing image according to the day status
                    Object[] dateinfo = Function.returnSystemDateInfo();
                    //check it is open
                    if(dateinfo[2].equals(Boolean.TRUE) && dateinfo[3].equals(Boolean.FALSE)){
//                        System.out.println("wala started");
                        MainFrameView.LoadlblDayStatus("daystarted.png");
                    }
                    else if(dateinfo[2].equals(Boolean.TRUE) && dateinfo[3].equals(Boolean.TRUE) ){
                        MainFrameView.LoadlblDayStatus("dayclosed.png");
//                        System.out.println("wala closed");
                    }
                    else{
//                        System.out.println("wala nothing happends");
                    }
        }
    
    public void ChangeMenuBarName(ArrayList MenuBar,String[] title){
        try{
//            System.out.println(title.length+"menubar"+MenuBar.size());
            for(int i=0;i<MenuBar.size();i++){
              JMenu menu = (JMenu)MenuBar.get(i);
//              menu.getText();
              menu.setText(title[i]);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(MainFrameView, e+"from ChangeMenuBarName"+getClass().getName());
        }
    }
   
    public class  MenuItemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            /*
             * company setup
             */
            if(e.getActionCommand().equalsIgnoreCase("CompanySetup")){
              
//               CompanySetup.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
//               CompanySetup.requestFocusInWindow();
//               CompanySetup.setModal(true);
//             //   JOptionPane.showConfirmDialog(MainFrameView, );
             executeCompanySetup exe=   new executeCompanySetup(MainFrameView,true);
                
        }
           if(e.getActionCommand().equalsIgnoreCase("CustomerSetup")){
//              JDialog CustomerSetup = new JDialog(new ExecuteCustomer(),true);
             ExecuteCustomer Customer = new ExecuteCustomer(MainFrameView,true);
           }
            if(e.getActionCommand().equalsIgnoreCase("DistributorSetup")){
               // JDialog DistributorSetup = new JDialog(new ExecuteDistributor(),true);
            }
            if(e.getActionCommand().equalsIgnoreCase("WaiterSetup")){
              //  MainFrameView.jDialog1 = new JDialog(new ExecuteWaiter(),"",Dialog.ModalityType.DOCUMENT_MODAL);
               // MainFrameView.jDialog1.setVisible(true);
//                JDialog WaiterSetup = new JDialog(new ExecuteWaiter(),"",Dialog.ModalityType.DOCUMENT_MODAL);
                ExecuteWaiter Waiter = new ExecuteWaiter(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("SoftwareRegistration")){
                ExecuteRegister  executeRegister = new ExecuteRegister(MainFrameView, true);
            }
            
            /*
             * centerstore setup
             */
            if(e.getActionCommand().equalsIgnoreCase("UnitSetup")){
//                JDialog UnitSetup = new JDialog(new executeUnitItemEntry(),"",Dialog.ModalityType.DOCUMENT_MODAL);
            executeUnitItemEntry UnitItemEntry =new executeUnitItemEntry(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("Group&CategorySetup")){
               //  JDialog GroupSetup = new JDialog(new executeUnitItemEntry(),"",Dialog.ModalityType.DOCUMENT_MODAL);
            ExecuteItemCategoryComponent ItemCategory = new ExecuteItemCategoryComponent();
            }
            if(e.getActionCommand().equalsIgnoreCase("DistributorSetup")){
//                System.out.println("wala");
                ExecuteDistro Distro = new ExecuteDistro();
            }
            /*
             * centerstore
             */
            if(e.getActionCommand().equalsIgnoreCase("ItemEntry")){
               // JDialog ItemEntry = new JDialog(new Item)
                ExecuteItemEntry ItemEntry = new ExecuteItemEntry();
            }
            if(e.getActionCommand().equalsIgnoreCase("ItemAdd/ItemLot")){
                ExecuteItemLot ItemLot = new ExecuteItemLot();
            }
          
            if(e.getActionCommand().equalsIgnoreCase("Issue")){
//                JDialog Issue = new JDialog(new executeIssue(),"",Dialog.ModalityType.DOCUMENT_MODAL);
                executeIssue Issue = new executeIssue(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("PurchaseReturn")){
//                JDialog PurchaseReturn = new JDialog(new executePurchaseReturn(),"",Dialog.ModalityType.DOCUMENT_MODAL );
            executePurchaseReturn PurchaseReturn = new executePurchaseReturn(MainFrameView, true);
            }
            /*
             * centerstore report
             */
            if(e.getActionCommand().equalsIgnoreCase("PurchaseReport")){
                 ExecutePurchaseReport PurchaseReort = new ExecutePurchaseReport(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("PurchaseReturnReport")){
                ExecutePurchaseReport PurchaseReort = new ExecutePurchaseReport(MainFrameView, true);
              //  E
            }
            if(e.getActionCommand().equalsIgnoreCase("ItemReport")){
                //ExecutePurchaseReport PurchaseReort = new ExecutePurchaseReport(MainFrameView, true);
                Map m = new HashMap<>();
                m.put("title", "Item Stock Report");
                IssueStockReport r = new IssueStockReport(m,"stock.jrxml", "Item Stock Report");
              //  E
            }
            if(e.getActionCommand().equalsIgnoreCase("CenterstoreItemwiseIssue/ReturnReport")){
                ExecuteCenterostoreIssueReport centerostoreIssueReport = new ExecuteCenterostoreIssueReport(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("CenterstoreCategorywiseIssue/ReturnReport")){
                ExecuteCenterstoreIssueReportCategory centerstoreIssueReportCategory = new ExecuteCenterstoreIssueReportCategory(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("DistributorReport")){
               // JDialog DistributorSetup = new JDialog(new ExecuteDistributor(),true);
               @SuppressWarnings("Convert2Diamond")
               Map para = new HashMap<Object, Object>();
               para.put("title","Distributor Report");
                DistributorReport r = new DistributorReport(para);
            }
            /*
             * terminal setup
             */
            if(e.getActionCommand().equalsIgnoreCase("MenuEntry")){
//                JDialog MenuEntry = new JDialog(new ExecuteMenuEntry(),"",Dialog.ModalityType.DOCUMENT_MODAL);
            ExecuteMenuEntry MenuEntry = new ExecuteMenuEntry(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("TableSetup")){
//                JDialog TableSetup = new JDialog(new ExecuteTableCrud(),"",Dialog.ModalityType.DOCUMENT_MODAL);
            ExecuteTableCrud TableCrud = new ExecuteTableCrud(MainFrameView, true);
                    
            
            }
            if(e.getActionCommand().equalsIgnoreCase("ThresholdSetup")){
//                JDialog ThresholdSetup = new JDialog(new ExecuteThreshold(),"",Dialog.ModalityType.DOCUMENT_MODAL);
            ExecuteThreshold Threshold = new ExecuteThreshold(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("ComplimentaryReasonSetup")){
                ExecuteComplimentary executeComplimentary = new ExecuteComplimentary(MainFrameView, true);
            }
            
            /*
             * terminal
             */
            if(e.getActionCommand().equalsIgnoreCase("Order")){
//                JDialog Order= new JDialog(new ExecuteOrder(),"",Dialog.ModalityType.DOCUMENT_MODAL);
                /*
                 * experinmenting with the internal frame
                 */
                
                if(MainFrameView.getCountForOrder() == 0){
              
               ExecuteOrder order = new ExecuteOrder(MainFrameView);
               if(order.OrderView!= null){
                MainFrameView.desktop.add(order.OrderView);
             
                order.OrderView.setSelected(true);
               }
//                MainFrameView.incrementCountForOrder();
                
                }
                else{
                      JInternalFrame[] frames=  MainFrameView.desktop.getAllFrames();
                    for (JInternalFrame frame : frames) {
                        if (frame.getTitle().equalsIgnoreCase("Order Window")) {
//                         System.out.println("wala");
                            frame.setSelected(true);
                        }
                    }
                    
                }
            }
            if(e.getActionCommand().equalsIgnoreCase("Pay")){
//                JDialog Pay= new JDialog(new ExecuteOrderBill(),"",Dialog.ModalityType.DOCUMENT_MODAL);
            
              
                if(MainFrameView.getCountForPay() == 0){
               ExecuteOrderBill billpay = new ExecuteOrderBill(MainFrameView);
                /*
                 * expering menting with  jinternal fames
                 */
//                JScrollPane scrollpane = new JScrollPane(300,300);
//                scrollpane.add(billpay.OrderBillView);
               if(billpay.OrderBillView!= null){
                MainFrameView.desktop.add(billpay.OrderBillView);
//                 MainFrameView.desktop.add(scrollpane);
                billpay.OrderBillView.setSelected(true);
               }
               //increment will be in the contructor the executeorderbill
//                 MainFrameView.incrementCountForPay();
                }
                else{
                  JInternalFrame[] frames=  MainFrameView.desktop.getAllFrames();
                   for (JInternalFrame frame : frames) {
                       if (frame.getTitle().equalsIgnoreCase("Order Bill Pay Window")) {
//                         System.out.println("wala");
                           frame.setSelected(true);
                       }
                   }
                    
                }
                    
                
            }
            if(e.getActionCommand().equalsIgnoreCase("DirectBillPay")){
                    
                if(MainFrameView.getCountForDirectPay() == 0){
               ExecuteDirectBill billpay = new ExecuteDirectBill(MainFrameView);
                /*
                 * expering menting with  jinternal fames
                 */
//                JScrollPane scrollpane = new JScrollPane(300,300);
//                scrollpane.add(billpay.OrderBillView);
               if(billpay.OrderBillView!= null){
                MainFrameView.desktop.add(billpay.OrderBillView);
//                 MainFrameView.desktop.add(scrollpane);
                billpay.OrderBillView.setSelected(true);
               }
                 MainFrameView.incrementCountForDirectPay();
                }
                else{
                  JInternalFrame[] frames=  MainFrameView.desktop.getAllFrames();
                         for (JInternalFrame frame : frames) {
                             if (frame.getTitle().equalsIgnoreCase("Direct Bill Pay Window")) {
//                         System.out.println("wala");
                                 frame.setSelected(true);
                             }
                         }
                    
                }
            }
             if(e.getActionCommand().equalsIgnoreCase("TableStatus")){
//                JDialog Order= new JDialog(new ExecuteOrder(),"",Dialog.ModalityType.DOCUMENT_MODAL);
                /*
                 * experinmenting with the internal frame
                 */
               
                if(MainFrameView.getCountForTableStatus()== 0){
              
               ExecuteTableStatusView tablestatus = new ExecuteTableStatusView(MainFrameView);
               
                MainFrameView.desktop.add(tablestatus.view);
             
                tablestatus.view.setSelected(true);
                 MainFrameView.incrementCountForTableStatus();
//                tablestatus.view.setLocation();
//                tablestatus.view.setLocation(MainFrameView.desktop.getSize().width- tablestatus.view.getSize().width-1,1);
              
                
                }
                else{
                      JInternalFrame[] frames=  MainFrameView.desktop.getAllFrames();
                    for (JInternalFrame frame : frames) {
                        if (frame.getTitle().equalsIgnoreCase("Table Status")) {
//                         System.out.println("wala");
                            frame.setSelected(true);
                        }
                    }
                    
                }
            }
            if(e.getActionCommand().equalsIgnoreCase("IssueReturn")){
//                JDialog IssueReturn = new JDialog(new executeIssueReturn(),"",Dialog.ModalityType.DOCUMENT_MODAL);
            executeIssueReturn IssueReturn = new executeIssueReturn(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("MenuView")){
               ExecuteMenuList MenuList = new ExecuteMenuList(MainFrameView,false);
            }
            if(e.getActionCommand().equalsIgnoreCase("MenuDetailView")){
                int ctn;
                if(MenuDetailList == null){
                  MenuDetailList = new ExecuteMenuDetailList(MainFrameView,false);
//                  JOptionPane.showMessageDialog(null, "wala");
                }
                else{
                  MenuDetailList.view.setVisible(true);
                }
            }
            if(e.getActionCommand().equalsIgnoreCase("VoidBill")){
                ExecuteOrderBillVoid OrderBillVoid = new ExecuteOrderBillVoid(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("Wastage")){
                ExecuteWastage executeWastage = new ExecuteWastage(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("CreditPayment")){
                ExecuteCreditPayment creditPayment = new ExecuteCreditPayment(MainFrameView, true);
            }
            /*
             * terminal report
             */
            if(e.getActionCommand().equalsIgnoreCase("IssueStockReport")){
                ExecuteIssueStockReport IssueStockReport = new ExecuteIssueStockReport(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("SalesReport")){
                ExecuteSalesReport SaleReport = new ExecuteSalesReport(MainFrameView,true);
            }
            if(e.getActionCommand().equalsIgnoreCase("ItemWiseSalesReport")){
                ExecuteItemWiseSalesReport ItemWiseReport = new ExecuteItemWiseSalesReport(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("ReceivableReport")){
                ExecuteReceiveableReport ReceivableReport = new ExecuteReceiveableReport(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("TerminalItemwiseIssue/ReturnReport")){
                ExecuteIssueReport IssueReport = new ExecuteIssueReport(MainFrameView,true);
            }
             if(e.getActionCommand().equalsIgnoreCase("TerminalCategorywiseIssue/ReturnReport")){
                ExecuteIssueReportCategory IssueReportCategory = new ExecuteIssueReportCategory(MainFrameView,true);
            }
            if(e.getActionCommand().equalsIgnoreCase("TerminalIssueReturnReport")){
                
            }
            if(e.getActionCommand().equalsIgnoreCase("SVCReport")){
                ExecuteServiceChargeReport ServiceChargeReport = new ExecuteServiceChargeReport(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("VATReport")){
                ExecuteTAXReport TAXReport = new ExecuteTAXReport(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("DiscountReport")){
                ExecuteDiscountReport DiscountReport = new ExecuteDiscountReport(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("ComplimentaryReport")){
                ExecuteComplimentaryReport ComplimentaryReport = new ExecuteComplimentaryReport(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("WaiterServiceReport")){
                ExecuteWaiterServiceReport WaiterServiceReport = new ExecuteWaiterServiceReport(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("MenuList")){
                //
//                MenuList menu = new MenuList();
                ExecuteMenuListReport  MenuListReport = new ExecuteMenuListReport(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("VoidReport")){
                ExecuteVoidReport executeVoidReport = new ExecuteVoidReport(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("WastageReport")){
                ExecuteWastageReport executeWastageReport = new ExecuteWastageReport(MainFrameView, true);
            }
            /*
            system date
            */
            if(e.getActionCommand().equalsIgnoreCase("SystemDateConfig")){
                ExecuteSystemDateChoose  executeSystemDateChoose = new ExecuteSystemDateChoose(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("SystemDateOpen")){
//                JOptionPane.showMessageDialog(MainFrameView, "");
                ExecuteDateOpen  executeDateOpen = new ExecuteDateOpen();
            }
            if(e.getActionCommand().equalsIgnoreCase("SystemDateClose")){
                ExecuteDateClose executeDateClose = new ExecuteDateClose();
            }
            /*
            system config
            */
            if(e.getActionCommand().equalsIgnoreCase("UserCreditial")){
                ExecuteUserCreditial UserCreditial = new ExecuteUserCreditial(MainFrameView, true,MainFrameView.getUserId());
            }
            if(e.getActionCommand().equalsIgnoreCase("ManageUser")){
                ExecuteUserCrud UserCrud = new ExecuteUserCrud(MainFrameView,true);
            }
            if(e.getActionCommand().equalsIgnoreCase("DepartmentSetup")){
                ExecuteDepartment department = new ExecuteDepartment(MainFrameView, true);
            }
            if(e.getActionCommand().equalsIgnoreCase("AboutUs")){
                CompanyInfoView CompanyInfo = new CompanyInfoView();
                CompanyInfo.setVisible(true);
//                System.out.println("wala");
            }
        }
        catch(PropertyVetoException me){
            JOptionPane.showMessageDialog(MainFrameView, me+"From MenuItemListener");
        }
        }
        
    }
    public class PopUpMenuMouseListener extends MouseAdapter{
        @Override
        public void mouseReleased(MouseEvent me){
            MainFrameView.ReportPopUpMenu.show(me.getComponent(), me.getX(), me.getY());
        }
    }
    public class DreamsysListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       try{
            final URI uri = new URI("http://www.dreamsys.com.np");
            if(Desktop.isDesktopSupported()){
                Desktop.getDesktop().browse(uri);
            }
            else{
                JOptionPane.showMessageDialog(MainFrameView, "Website Not Supported in your System");
            }
       }
       catch(IOException ioe){
           JOptionPane.showMessageDialog(MainFrameView, ioe);
       }
       catch(Exception de){
           JOptionPane.showMessageDialog(MainFrameView, de+"From dreamsyslistener");
       }
        }
        
    }
    public class LogOutListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       try{
           
          
          
           MainFrameView.setLogOutVisibleFalse();
           MainFrameView.setChangePasswordVisible(false);
           MainFrameView.setUserName("");
           MainFrameView.setUserId(0);
           
            //close all internal frame
           JInternalFrame[] frames = MainFrameView.desktop.getAllFrames();
           for(JInternalFrame frame:frames){
               frame.setClosed(true);
           }
            sysview.setUserName("");
           sysview.setPassWord(null);
            sysview.setVisible(true);
//            sysview.pack();
       }
       catch(Exception se){
           JOptionPane.showMessageDialog(MainFrameView, e+"from LogOutListener");
       }
        }
        
    }
    public class ChangePasswordListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       try{
            ExecuteResetPassword changePassword = new ExecuteResetPassword(MainFrameView, true, MainFrameView.getUserId());
       }
       catch(Exception se){
           JOptionPane.showMessageDialog(MainFrameView, e+"from ChangePasswordListener");
       }
        }
        
    }
    
//    public class CommonDialog extends JDialog{
//        CommonDialog(Object obj){
//            
//        }
//    }
    public class MainFrameCloseListener extends WindowAdapter{

       
        @Override
//        public void windowClosing(WindowEvent e){
//            boolean billpaystatusclose = true;
//            boolean directbillpaystatusclose = true;
//            String Status = null;
//           
////           JOptionPane.showMessageDialog(MainFrameView, "wala");
//                JInternalFrame[] frames=  MainFrameView.desktop.getAllFrames();
//                   for (JInternalFrame frame : frames) {
//                     if(frame.getTitle().equalsIgnoreCase("Direct Bill Pay Window")){
//                        int choice = JOptionPane.showConfirmDialog(MainFrameView, "Direct Bill Pay Window is Still Opened.Do You Still Want To Close?","DreamSYS Point Of Sales Window Close Alert",JOptionPane.YES_NO_CANCEL_OPTION);
//                        if(choice == JOptionPane.YES_OPTION){
//                         DirectBillView directBillView = (DirectBillView)frame;
//                            try {
//                                directBillView.setClosed(true);
////                                System.exit(0);
//                            } catch (PropertyVetoException ex) {
////                                Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
//                            JOptionPane.showMessageDialog(MainFrameView, ex+"from MainFrameCloseListener");
//                            }
//                        }
//                        else{
//                           directbillpaystatusclose = false;
//                        }
//                         
//                        
//                     }
//                           if(frame.getTitle().equalsIgnoreCase("Order Bill Pay Window")){
//                        int choice = JOptionPane.showConfirmDialog(MainFrameView, "Order Bill Pay Window is Still Opened.Do You Still Want To Close?","DreamSYS Point Of Sales Window Close Alert",JOptionPane.YES_NO_CANCEL_OPTION);
//                        if(choice == JOptionPane.YES_OPTION){
//                         OrderBillView directBillView = (OrderBillView)frame;
//                            try {
//                                directBillView.setClosed(true);
////                                 System.exit(0);
//                            } catch (PropertyVetoException ex) {
////                                Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
//                            JOptionPane.showMessageDialog(MainFrameView, ex+"from MainFrameCloseListener");
//                            }
//                        }
//                        else{
//                           billpaystatusclose  = false;
//                        }
//                         
//                        
//                     }
//                           if(frame.getTitle().equalsIgnoreCase("Order Window")){
//                               OrderView orderBillView = (OrderView) frame;
//                         try {
//                             orderBillView.setClosed(true);
////                              System.exit(0);
//                         } catch (PropertyVetoException ex) {
////                             Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
//                         JOptionPane.showMessageDialog(MainFrameView, ex+"from MainFrameCloseListener");
//                         }
//                           }
//                   }
////                   if(billpaystatusclose == true && directbillpaystatusclose == true ){
////                   System.exit(0);
////                   }
//                   if(directbillpaystatusclose == true || directbillpaystatusclose == false){
//                       if(billpaystatusclose == true){
//                         System.exit(0);
//                       }
//                   }
//       } 
        public void windowClosing(WindowEvent e){
            String WindowStatus = "";
             boolean billpaystatus = false;
            boolean directbillpaystatus = false;
          JInternalFrame[] frames=  MainFrameView.desktop.getAllFrames();
                   for (JInternalFrame frame : frames) {
                       if(frame.getTitle().equalsIgnoreCase("Direct Bill Pay Window")){
                           WindowStatus += "Direct Bill Pay Window is Still Opened";
                           directbillpaystatus = true;
//                            OrderBillView directBillView = (OrderBillView)frame;
                       } 
                       if(frame.getTitle().equalsIgnoreCase("Order Bill Pay Window")){
                            WindowStatus += "\n Order Bill Pay Window is Still Opened \n";
                            billpaystatus = true;
//                            OrderBillView BillView = (OrderBillView)frame;
                             
                       }
                     
                       
                   } 
                     if(DisplayMessages.displayInputYesNo(MainFrameView,WindowStatus+"Do You Want to Close The Window", "DreamSYS Close Window")){
                           System.exit(0);
                       }
        }
    }
    
    
    
}
