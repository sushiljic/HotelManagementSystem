/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.purchasereport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import report.purchaseReport.PurchaseReport;
import report.purchaseReturnReport.PurchaseReturnReport;
//import report.purchaseReport.*;
/*
import report.purchaseReport.PurchaseReportAll;
import report.purchaseReturnReport.PurchaseReturnReport;
import report.purchaseReturnReport.PurchaseReturnReportAll;
*/
/**
 *
 * @author SUSHIL
 */
public class PurchaseReportController {
    PuchaseReportModel IRModel;
    PurchaseReportView IRView;
    
    public PurchaseReportController(PuchaseReportModel model,PurchaseReportView view){
        IRModel= model;
        IRView = view;
        /*
         * fetching the data into the combo box
         */
        IRView.setComboItemName(IRModel.returnItemName(IRModel.getItemInfoForIssue()));
        
     //   IRView.check
        IRView.addOkListener(new PurchaseReportListener());
        IRView.addCancelListener(new PurchaseReportListener());
        IRView.addCancelReportListener(new PurchaseReportListener());
        IRView.addPrintListener(new PurchaseReportListener());
        IRView.addSaveAsExcelListener(new PurchaseReportListener());
        /*
         * for comboitemname select
         */
        IRView.addComboItemNameSelectListener(new ComboSelectListener());
        IRView.addComboBaseUnitListener(new ComboSelectListener());
        /*
         * for include all
         */
        IRView.addCheckIncludeAllListener(new CheckIncludeAllListener());
    }
    public class PurchaseReportListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("Ok")){
                if(!IRView.getBooleanIncludeAll())
                {
                if(IRView.getItemId().isEmpty()){
                    JOptionPane.showMessageDialog(IRView.DailogReport,"Please Select The Item Name");
                    return;
                }
                if(IRView.getUnitId().isEmpty()){
                      JOptionPane.showMessageDialog(IRView.DailogReport,"Please Select The Item  Base Unit Name");
                    return;
                }
                }
                if(IRView.getStartDate() == null){
                     JOptionPane.showMessageDialog(IRView.DailogReport,"Please Select  Start Date ");
                    return;
                }
                 if(IRView.getEndDate() == null){
                     JOptionPane.showMessageDialog(IRView.DailogReport,"Please Select  End Date ");
                    return;
                }
//                    Date[] date = IRView.getPurchaseReportDate();
               
                if(IRView.getBooleanRadioButton() == true){
                    /*
                     * issue report should be fetche from database
                     */
                           
                      if(!IRView.getBooleanIncludeAll()){
                            //System.out.println("hello all");
                          String title = "Purchase Report";
                          String report = "MonthlyPurchase.jrxml";
                          PurchaseReport purchase = new PurchaseReport(IRView.getPurchaseParam(),report,title);  
                          //PurchaseReport pr = new PurchaseReport(IRView.getPurchaseParam());
                         }
                         else{
                            String title = "Purchase Report";
                            String report = "MonthlyAllPurchase.jrxml";
                            PurchaseReport purchase = new PurchaseReport(IRView.getPurchaseParams(),report,title);
                             //PurchaseReportAll pr = new PurchaseReportAll(IRView.getPurchaseParams());
                         } 
                      
//                      String title = "Purchase Report ";
                        /*
                         * when icnlude all is clicked then true is passed as parameter
                         */
                        if(IRView.getBooleanIncludeAll() == false){
//                           
//                           title += "of "+ IRView.getComboItemName();
//                            
//                             IRView.setlblReportTitle(title);
//                             String[] itemdata = IRView.getPurchaseReport();
//                       IRView.refreshTableReport(IRModel.getPurchaseList(itemdata, date,false));
//                        DefaultTableModel ModelTableReport = (DefaultTableModel) IRView.tblReport.getModel();
//                        BigDecimal TotalQuantity = BigDecimal.ZERO;
//                         BigDecimal TotalAmount = BigDecimal.ZERO;
//                        String UnitType = new String();
//                         if(IRView.getTableReport().getRowCount() <= 0){
//                            JOptionPane.showMessageDialog(IRView.DailogReport, "Record Not Found");
//                            return;
//                      }
//                        
//                         
//                          
//                         
//                         
//                        for(int i=0;i<ModelTableReport.getRowCount();i++){
//                           UnitType = ModelTableReport.getValueAt(i, 8).toString().replaceAll("[0-9,.]", "");
//                            TotalQuantity = TotalQuantity.add(new BigDecimal(ModelTableReport.getValueAt(i, 8).toString().replaceAll("[^0-9,.]", "")));
//                            TotalAmount = TotalAmount.add(new BigDecimal(ModelTableReport.getValueAt(i, 5).toString()));
//                        }
                        
                        /*if(IRView.getBooleanIncludeAll() == false){
                            System.out.println("hello all");
                             PurchaseReport pr = new PurchaseReport(IRView.getPurchaseParam());
                         }
                         else if(IRView.getBooleanIncludeAll() == true){
                             PurchaseReportAll pr = new PurchaseReportAll(IRView.getPurchaseParams());
                         }
                        */
                        //PurchaseReportAll pr = new PurchaseReportAll(IRView.getPurchaseParams());
                        //System.out.println(TotalQuantity+UnitType);
                        //IRView.tblReport.getModel();
//                        ModelTableReport.addRow(new Object[]{"Total",null,null,null,null,TotalAmount,null,null,TotalQuantity+UnitType});
                        }
                        else{
//                             IRView.setlblReportTitle(title);
//                            IRView.refreshTableReport(IRModel.getPurchaseList(null, date, true));
                            /*
                            for calculating total
                            */
//                             DefaultTableModel ModelTableReport = (DefaultTableModel) IRView.tblReport.getModel();
////                        BigDecimal TotalQuantity = BigDecimal.ZERO;
//                         BigDecimal TotalAmount = BigDecimal.ZERO;
//                        String UnitType = new String();
//                         if(IRView.getTableReport().getRowCount() <= 0){
//                            JOptionPane.showMessageDialog(IRView.DailogReport, "Record Not Found");
//                            return;
//                        }
//                        for(int i=0;i<ModelTableReport.getRowCount();i++){
////                           UnitType = ModelTableReport.getValueAt(i, 8).toString().replaceAll("[0-9,.]", "");
////                            TotalQuantity = TotalQuantity.add(new BigDecimal(ModelTableReport.getValueAt(i, 8).toString().replaceAll("[^0-9,.]", "")));
//                            TotalAmount = TotalAmount.add(new BigDecimal(ModelTableReport.getValueAt(i, 5).toString()));
//                        }
//                       // System.out.println(TotalQuantity+UnitType);
//                        //IRView.tblReport.getModel();
//                        ModelTableReport.addRow(new Object[]{"Total",null,null,null,null,TotalAmount,null,null});
                        }
                       
//                         DateFormat dt = DateFormat.getDateInstance(DateFormat.FULL);
//                        IRView.setlblStartDate(dt.format(IRView.getStartDate()));
//                        IRView.setlblEndDate(dt.format(IRView.getEndDate()));
//                        IRView.DailogReport.pack();
//                        IRView.DailogReport.setModal(true);
//                         IRView.setVisible(false);
//                        IRView.DailogReport.setVisible(true);
                }
                else{
                    String ReturnTitle = "Purchase Return Report";
                   
                    //  IRView.setlblReportTitle("Issue Report");
                           if(IRView.getBooleanIncludeAll() == false){
                               String title = "Purchase Return Report";
                               String report = "PurchaseReturn.jrxml";
                               PurchaseReturnReport rtr = new PurchaseReturnReport(IRView.getPurchaseReturnParam(),report,title);
                            //System.out.println("hello all");
                             //PurchaseReturnReport pr = new PurchaseReturnReport(IRView.getPurchaseReturnParam());
                         }
                         else{
                               String title = "Purchase Return Report";
                               String report = "MonthlyPurchaseReturn.jrxml";
                              PurchaseReturnReport rtr = new PurchaseReturnReport(IRView.getPurchaseReturnParams(),report,title);
                             //PurchaseReturnReportAll pr = new PurchaseReturnReportAll(IRView.getPurchaseReturnParams());
                         }   
                       
                        /*
                         * when icnlude all is clicked then true is passed as parameter
                         */
                       
                           /*if(IRView.getBooleanIncludeAll() == false){
                            ReturnTitle += "  of  "+ IRView.getComboItemName();
                             IRView.setlblReportTitle(ReturnTitle);
                             String[] itemdata = IRView.getPurchaseReport();
                        IRView.refreshTableReport(IRModel.getPurchaseReturnList(itemdata, date,false));
                        }
                        else{
                             IRView.setlblReportTitle(ReturnTitle);
                            IRView.refreshTableReport(IRModel.getPurchaseReturnList(null, date, true));
                        }
                        if(IRView.getTableReport().getRowCount() <= 0){
                            JOptionPane.showMessageDialog(IRView.DailogReport, "Record Not Found");
                            return;
                        }
                         DateFormat dt = DateFormat.getDateInstance(DateFormat.FULL);
                        IRView.setlblStartDate(dt.format(IRView.getStartDate()));
                        IRView.setlblEndDate(dt.format(IRView.getEndDate()));
                        IRView.DailogReport.pack();
                        IRView.DailogReport.setModal(true);
                         IRView.setVisible(false);
                        IRView.DailogReport.setVisible(true);
                    */
                }
               
               
            }
            if(e.getActionCommand().equalsIgnoreCase("Cancel")){
                IRView.setVisible(false);
                IRView.clearAll();
            }
            if(e.getActionCommand().equalsIgnoreCase("ReportCancel")){
                IRView.DailogReport.setVisible(false);
                IRView.setVisible(true);
                IRView.clearAll();
            }
            
        }
        catch(Exception ire){
            JOptionPane.showMessageDialog(IRView, ire+"From PurchaseReportListener ");
        }
        }
        
    }
    public class ComboSelectListener implements ActionListener{
 private    Object item[] =null;
 private Object Unit[] = null;
    private Object[][] ItemUnitInfo;
        @Override
        public void actionPerformed(ActionEvent e) {
          /*
           * SELECT centerstore_stock.item_id,centerstore_stock.item_name,centerstore_stock.unit_id,item_unit.unit_name,item_unit.unit_relative_quantity,item_unit.unit_type,centerstore_stock.total_qty,centerstore_stock.item_expiry_date 
           * FROM centerstore_stock,item_unit 
           * WHERE centerstore_stock.unit_id = item_unit.unit_id
           */
        try{
            
            JComboBox cb = (JComboBox)e.getSource();
            if(cb == IRView.comboItemName)
            {
            //System.out.println(cb.getSelectedItem());
            for(int i=0;i<IRModel.Itemdata.length;i++){
                if(IRModel.Itemdata[i][1].equals(cb.getSelectedItem())){
              
                    item = IRModel.Itemdata[i];
                    break;
                }
               // Systeim.out.println(issueModel.Itemdata[i][1]);
            }
            IRView.setOnComboItemNameSelect(item);
           // System.out.println(IRView.getUnitId());
         ItemUnitInfo = IRModel.getUnitInfo(IRView.getUnitId());
           //SetonComboItemNameSelect Doesnot selecet combo itemBaseUnit done mannually by another fucntion
            
        
            /*
             * this load all the relative unitname that can be used to issue the item
             */
         
            IRView.setComboBaseUnitName(IRModel.returnItemBaseUnit(ItemUnitInfo));
            IRView.setComboBaseUnitName(item[3].toString());
            
            }
            if(cb == IRView.comboBaseUnitName){
                  ItemUnitInfo = IRModel.getUnitInfo(IRView.getUnitId());
                
               for(int i=0;i<ItemUnitInfo.length;i++){
                   if(ItemUnitInfo[i][1].equals(cb.getSelectedItem())){
                     Unit= ItemUnitInfo[i];
                     break;
                   }
               }
               IRView.setUnitId(Unit[0].toString());
              // System.out.println(Unit[0]+"\n"+IRView.getUnitId());
            }
        }
        catch(Exception ex){
           JOptionPane.showMessageDialog(IRView, ex+"From ComdoIssueListener");
        }
        finally{
          //  item = null;
        }
        }
        
    }
    public class CheckIncludeAllListener implements ItemListener{

      

        @Override
        public void itemStateChanged(ItemEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getStateChange() == 1){
            IRView.comboItemName.setEnabled(false);
            
        }
        else{
            IRView.comboItemName.setEnabled(true);
        }
        }
    }
}
