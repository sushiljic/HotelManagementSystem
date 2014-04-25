/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.departmentissuereport;

import hotelmanagementsystem.MainFrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import report.issueReport.category.IssueCategoryReport;
import report.issueReport.item.IssueItemReport;
import report.issueReturn.ReturnReport;
//import report.issueReport.category.*; //AllIssueReport;
/*
import report.terminalIRReport.AllIssueReturn;
import report.terminalIRReport.IssueReport;
import report.terminalIRReport.IssueReturn;
*/
/**
 *
 * @author SUSHIL
 */
public class IssueReportController {
    IssueReportModel IRModel;
    IssueReportView IRView;
    private MainFrameView mainview;
    
    public IssueReportController(IssueReportModel model,IssueReportView view,MainFrameView mainview){
        IRModel= model;
        IRView = view;
        this.mainview = mainview;
      
        
          /*
        adding for issue and return
        */
       
        IRView.addRadioIssueListener(new RadioIssueStatusListener());
        IRView.addRadioIssueReturnListener(new RadioIssueReturnStatusListener());
        IRView.checkRadioIssue();
        
     //   IRView.check
        IRView.addOkListener(new IssueReportListener());
        IRView.addCancelListener(new IssueReportListener());
        IRView.addCancelReportListener(new IssueReportListener());
        IRView.addPrintListener(new IssueReportListener());
        IRView.addSaveAsExcelListener(new IssueReportListener());
        /*
         * for comboitemname select
         */
        IRView.addComboItemNameSelectListener(new ComboSelectListener());
       //ading the store listener
        IRView.addComboStoreNameListener(new ComboSelectListener());
//        IRView.addComboStoreNameListener(new ComboSelectListener());
        /*
         * for include all
         */
        IRView.addCheckIncludeAllListener(new CheckIncludeAllListener());
        IRView.addCheckIncludeAllDepartmentListener(new CheckIncludeAllDepartmentListener());
        /*
         * fetching the  storedata into the combo box
         */
        try{
//       
        IRView.setComboStoreName(IRModel.returnItemName(IRModel.getRespectiveDepartment(this.mainview.getUserId())));
       if(IRView.returnComboStoreName().getModel().getSize() >1){
           IRView.AddSelectInCombo(IRView.returnComboStoreName()); 
           IRView.setCheckIncludeAllDepartmentVisible(true);
       }
       else{
           if(IRView.returnComboStoreName().getModel().getSize() ==1){
           IRView.returnComboStoreName().setSelectedIndex(0);
           }
       }
       //loading menu 
       IRView.setComboItemName(IRModel.returnItemName(IRModel.getItemInfo()));
       IRView.AddSelectInCombo(IRView.returnComboItemName());
//        IRView.AddSelectInCombo(IRView.returnComboStoreName());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(view, e+" from IssuereportController "+getClass().getName());
        }
    }
    public class IssueReportListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("Ok")){
                if(!IRView.getBooleanIncludeAllDepartment()){ 
                if(IRView.getStoreId() == 0){
                      JOptionPane.showMessageDialog(IRView.DailogReport,"Please Select The Department Name");
                    return;
                }
                }
                if(!IRView.getBooleanIncludeAllItemName())
                {
                    
                if(IRView.getItemId() == 0){
                    JOptionPane.showMessageDialog(IRView.DailogReport,"Please Select The Item Name");
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
                    Date[] date = IRView.getIssueReportDate();
               //is issue is checked
                if(IRView.getBooleanRadioButton()){
                    /*
                     * issue report should be fetche from database
                     */
                           
                //if all department report is to be showed
                    if((IRView.getBooleanIncludeAllDepartment()))
                    {
//                      String title = "Issue Report ";
                        /*
                         * when icnlude all is clicked then true is passed as parameter
                         */
                        if(!IRView.getBooleanIncludeAllItemName()){
                           
                            String report = "issueAllStoreItem.jrxml";
                           String title = "Itewise Issue Report";
                           IssueItemReport  r = new IssueItemReport(IRView.getIssueAllDepItem(),report, title);
                           // IssueReport issue = new IssueReport(IRView.getIssueParam());
                            
//                            title += "of "+ IRView.getComboItemName();
//                            
//                             IRView.setlblReportTitle(title);
//                             String[] itemdata = IRView.getIssueReport();
//                        IRView.refreshTableReport(IRModel.getIssueList(itemdata, date,false));
//                        DefaultTableModel ModelTableReport = (DefaultTableModel) IRView.tblReport.getModel();
//                        BigDecimal TotalQuantity = BigDecimal.ZERO;
//                        BigDecimal TotalIssue = BigDecimal.ZERO;
//                        String UnitType = new String();
//                        for(int i=0;i<ModelTableReport.getRowCount();i++){
//                           UnitType = ModelTableReport.getValueAt(i, 5).toString().replaceAll("[0-9,.]", "");
//                            TotalQuantity = TotalQuantity.add(new BigDecimal(ModelTableReport.getValueAt(i, 5).toString().replaceAll("[^0-9,.]", "")));
//                            TotalIssue = TotalIssue.add(new BigDecimal(ModelTableReport.getValueAt(i, 2).toString()));
//                        }
//                       // System.out.println(TotalQuantity+UnitType);
//                        //IRView.tblReport.getModel();
//                        ModelTableReport.addRow(new Object[]{"Total",null,TotalIssue,null,null,TotalQuantity+UnitType});
                        }
                        else{
                            
                           String report = "issueItemAll.jrxml";
                           String title = "Issue Item Report";
                            IssueItemReport issue = new IssueItemReport(IRView.getIssueItemAllParams(),report,title);
//                             IRView.setlblReportTitle(title);
//                            IRView.refreshTableReport(IRModel.getIssueList(null, date, true));
                            
                            
                        }
                }//end of all department report
                //when particulat department is selected
                else{
                               String title = "Issue Report ";
                        /*
                         * when icnlude all is clicked then true is passed as parameter
                         */
                        if(IRView.getBooleanIncludeAllItemName() == false){
                           
                            String report = "issueStoreItem.jrxml";
                            title = "Itemwise Issue Report";
                            IssueItemReport r = new IssueItemReport(IRView.getIssueDepItem(),report,title);
//                            title += "of "+ IRView.getComboItemName();
//                            
//                             IRView.setlblReportTitle(title);
//                             String[] itemdata = IRView.getIssueReport();
//                        IRView.refreshTableReport(IRModel.getIssueList(itemdata, date,false));
//                        DefaultTableModel ModelTableReport = (DefaultTableModel) IRView.tblReport.getModel();
//                        BigDecimal TotalQuantity = BigDecimal.ZERO;
//                        BigDecimal TotalIssue = BigDecimal.ZERO;
//                        String UnitType = new String();
//                        for(int i=0;i<ModelTableReport.getRowCount();i++){
//                           UnitType = ModelTableReport.getValueAt(i, 5).toString().replaceAll("[0-9,.]", "");
//                            TotalQuantity = TotalQuantity.add(new BigDecimal(ModelTableReport.getValueAt(i, 5).toString().replaceAll("[^0-9,.]", "")));
//                            TotalIssue = TotalIssue.add(new BigDecimal(ModelTableReport.getValueAt(i, 2).toString()));
//                        }
//                       // System.out.println(TotalQuantity+UnitType);
//                        //IRView.tblReport.getModel();
//                        ModelTableReport.addRow(new Object[]{"Total",null,TotalIssue,null,null,TotalQuantity+UnitType});
                        }
                        else{
                            
                            String report = "issueStoreAllItem.jrxml";
                            title = "Itemwise Issue Report";
                            IssueItemReport r = new IssueItemReport(IRView.getIssueDepAll(),report,title);
//                             IRView.setlblReportTitle(title);
//                            IRView.refreshTableReport(IRModel.getIssueList(null, date, true));
                            
                            
                        }
                }
//                        if(IRView.getTableReport().getRowCount() <= 0){
//                            JOptionPane.showMessageDialog(IRView.DailogReport, "Record Not Found");
//                            return;
//                        }
//                         DateFormat dt = DateFormat.getDateInstance(DateFormat.FULL);
//                        IRView.setlblStartDate(dt.format(IRView.getStartDate()));
//                        IRView.setlblEndDate(dt.format(IRView.getEndDate()));
//                        IRView.DailogReport.pack();
//                        IRView.DailogReport.setModal(true);
                         IRView.setVisible(false);
//                        IRView.DailogReport.setVisible(true);
                }
                //if issuereturn is clicked
                else{
//                    String ReturnTitle = "Issue Return Report";
                   
                    //  IRView.setlblReportTitle("Issue Report");
                      //if all department is to be shown in report      
                      if(IRView.getBooleanIncludeAllDepartment()) 
                      {
                        /*
                         * when icnlude all is clicked then true is passed as parameter
                         */
                        if(IRView.getBooleanIncludeAllItemName() == false){
                             String report = "returnAllStoreItem.jrxml";
                            String title = "Issue Return Report";
                            ReturnReport ret = new ReturnReport(IRView.getReturnAllDepItem(),report,title);
//                            ReturnTitle += "  of  "+ IRView.getComboItemName();
//                             IRView.setlblReportTitle(ReturnTitle);
//                             String[] itemdata = IRView.getIssueReport();
//                        IRView.refreshTableReport(IRModel.getIssueReturnList(itemdata, date,false));
                        }
                        else{
                             String report = "returnAllReport.jrxml";
                            String title = "Issue Return Report";

//AllIssueReturn ret = new AllIssueReturn(IRView.getReturnParams());
                            ReturnReport ret = new ReturnReport(IRView.getReturnItemAllParams(),report, title);
                            IRView.setlblReportTitle(title);
//                            IRView.refreshTableReport(IRModel.getIssueReturnList(null, date, true));
                        }
                }
                      //if partucular department is to be shown
                      else{
                         if(IRView.getBooleanIncludeAllItemName() == false){
                            String report ="returnStoreItem.jrxml";
                             String title = "Issue Return Report";
                            ReturnReport ret = new ReturnReport(IRView.getReturnDepItem(),report,title);
//                            ReturnTitle += "  of  "+ IRView.getComboItemName();
//                             IRView.setlblReportTitle(ReturnTitle);
//                             String[] itemdata = IRView.getIssueReport();
//                        IRView.refreshTableReport(IRModel.getIssueReturnList(itemdata, date,false));
                        }
                        else{
                            String report ="returnStoreAllItem.jrxml";
                             String title = "Issue Return Report";
                            ReturnReport ret = new ReturnReport(IRView.getReturnDepAll(),report,title); 
                            IRView.setlblReportTitle(title);
//                            IRView.refreshTableReport(IRModel.getIssueReturnList(null, date, true));
                        }
                          
                      }
//                        if(IRView.getTableReport().getRowCount() <= 0){
//                            JOptionPane.showMessageDialog(IRView.DailogReport, "Record Not Found");
//                            return;
//                        }
//                         DateFormat dt = DateFormat.getDateInstance(DateFormat.FULL);
//                        IRView.setlblStartDate(dt.format(IRView.getStartDate()));
//                        IRView.setlblEndDate(dt.format(IRView.getEndDate()));
//                        IRView.DailogReport.pack();
//                        IRView.DailogReport.setModal(true);
                         IRView.setVisible(false);
//                        IRView.DailogReport.setVisible(true);
                    
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
            JOptionPane.showMessageDialog(IRView, "From IssueReportListener ");
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
                if(cb.getSelectedIndex() == 0){
                    IRView.setItemId(0);
//                    IRView.setStoreId("");
                }
                else{
                for (Object[] Itemdat : IRModel.Itemdata) {
                    if (Itemdat[1].equals(cb.getSelectedItem())) {
                        item = Itemdat;
                        break;
                    }
                    // Systeim.out.println(issueModel.Itemdata[i][1]);
                }
            IRView.setOnComboItemNameSelect(item);
         
         //This will be commenting out since it has no use in the issue section  
//         ItemUnitInfo = IRModel.getUnitInfo(String.valueOf(IRView.getStoreId()));
//           //SetonComboItemNameSelect Doesnot selecet combo itemBaseUnit done mannually by another fucntion
//            
//        
//            /*
//             * this load all the relative unitname that can be used to issue the item
//             */
//         
//            IRView.setComboStoreName(IRModel.returnItemBaseUnit(ItemUnitInfo));
//            IRView.setComboStoreName(item[3].toString());
                }
            
            }
            if(cb == IRView.comboStoreName){
                
                   if(cb.getSelectedItem().equals("SELECT")){
                      IRView.setStoreId(0);
                  }
                  else{
                for (Object[] ItemUnitInfo1 :IRModel.getOtherStoreName()) {
                    if (ItemUnitInfo1[1].equals(cb.getSelectedItem())) {
                        Unit = ItemUnitInfo1;
                        break;
                    }
                }
               IRView.setStoreId(Integer.parseInt(Unit[0].toString()));
               //display respective  item store in resturant_store
//                IRView.setComboItemName(IRModel.returnItemName(IRModel.getResepectiveDepartmentItemInfo(IRView.getStoreId())));
//                IRView.AddSelectInCombo(IRView.returnComboItemName());
                  }
              // System.out.println(Unit[0]+"\n"+IRView.getStoreId());
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
     public class CheckIncludeAllDepartmentListener implements ItemListener{

      

        @Override
        public void itemStateChanged(ItemEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getStateChange() == 1){
            IRView.comboStoreName.setEnabled(false);
//            IRView.setComboItemName(itemName);
//            IRView.setComboItemName(IRModel.returnItemName(IRModel.getItemInfo()));
//            IRView.AddSelectInCombo(IRView.returnComboItemName());
        }
        else{
            IRView.comboStoreName.setEnabled(true);
        }
        }
    }
    public class RadioIssueStatusListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
           if(e.getStateChange() == 1){
               IRView.setlblStatus("Choose Department as Issue To");
             
           }
           else{
              IRView.setlblStatus("Choose Department as Issue Return From");  
           }
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(IRView, se+"from  RadioIssueStatusListener "+getClass().getName());
        }
        }
        
    }
     public class RadioIssueReturnStatusListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
           if(e.getStateChange() == 1){
               IRView.setlblStatus("Choose Department as Issue Return From");
              
           }
           else{
              IRView.setlblStatus("Choose Department as Issue  To");  
           }
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(IRView, se+"from  RadioIssueReturnStatusListener "+getClass().getName());
        }
        }
        
    }
}
