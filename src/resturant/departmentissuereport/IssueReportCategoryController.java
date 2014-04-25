/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.departmentissuereport;

import hotelmanagementsystem.MainFrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import report.issueReport.category.IssueCategoryReport;
import report.issueReport.IssueReturn;

/**
 *
 * @author SUSHIL
 */
public class IssueReportCategoryController {
    IssueReportModel IRModel;
    IssueReportCategoryView IRView;
    private MainFrameView mainview;
    
    public IssueReportCategoryController(IssueReportModel model,IssueReportCategoryView view,MainFrameView main){
        IRModel= model;
        IRView = view;
        mainview = main;
     
        
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
        IRView.setComboStoreName(IRModel.returnItemName(IRModel.getOtherStoreName()));
        if(IRView.returnComboStoreName().getModel().getSize() >1){
           IRView.AddSelectInCombo(IRView.returnComboStoreName()); 
           IRView.setCheckIncludeAllDepartmentVisible(true);
       }
       else{
           if(IRView.returnComboStoreName().getModel().getSize() == 1){
           IRView.returnComboStoreName().setSelectedIndex(0);
           }
       }
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
                if(!IRView.getBooleanIncludeAllDepartment())  
                {
                    if(IRView.getStoreId() == 0){
                      JOptionPane.showMessageDialog(IRView.DailogReport,"Please Select Department Name ");
                    return;
                }
                }
                if(!IRView.getBooleanIncludeAllCategoryName())
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
               //if  issue is selected
                if(IRView.getBooleanRadioButton() == true){
                    /*
                     * issue report should be fetche from database
                     */
                           //if all department is to be showm in report
                    if(IRView.getBooleanIncludeAllDepartment())
                    {   
//                      String title = "Issue Report ";
                        /*
                         * when icnlude all is clicked then true is passed as parameter
                         */
                        if(!IRView.getBooleanIncludeAllCategoryName()){
                           
                            //IssueReport issue = new IssueReport(IRView.getIssueParam());
                            String report = "issueAllStoreCategory.jrxml";
                            String title = "Categorywise Issue Report";
                            IssueCategoryReport issue = new IssueCategoryReport(IRView.getIssueAllDepCategory(),report, title);
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
                            String report = "issueAllReport.jrxml";
                            String title = "Categorywise Issue Report";
                            IssueCategoryReport issue = new IssueCategoryReport(IRView.getIssueCategoryAllParams(),report, title);
                            //AllIssueReport issue = new AllIssueReport(IRView.getIssueParams());
//                             IRView.setlblReportTitle(title);
//                            IRView.refreshTableReport(IRModel.getIssueList(null, date, true));
                            
                            
                        }
                }
                //if particular department is to be shown
                else
                {
                       String title = "Issue Report ";
                        /*
                         * when icnlude all is clicked then true is passed as parameter
                         */
                        if(!IRView.getBooleanIncludeAllCategoryName()){
                           
                            String report = "issueStoreCategory.jrxml";
                            title = "Categorywise Issue Report";
                            //String title = "";
                            IssueCategoryReport issue = new IssueCategoryReport(IRView.getIssueDepCategory(),report, title);
                            //IssueReport issue = new IssueReport(IRView.getIssueParam());
                            
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
                            String report = "issueStoreAllCategory.jrxml";
                            title = "";
                            IssueCategoryReport issue = new IssueCategoryReport(IRView.getIssueDepAll(),report, title);
                            //AllIssueReport issue = new AllIssueReport(IRView.getIssueParams());
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
                  // if issuereturn is selected
                else{
//                    String ReturnTitle = "Issue Return Report";
                   
                    //  IRView.setlblReportTitle("Issue Report");
                            
                  //if all department is to be shown
                    if(IRView.getBooleanIncludeAllDepartment())
                    {
                        /*
                         * when icnlude all is clicked then true is passed as parameter
                         */
                  if(!IRView.getBooleanIncludeAllCategoryName() ){
                      String report = "returnAllStoreCategory.jrxml";
                      String title = "Issue Return Report";
                            IssueReturn ret = new IssueReturn(IRView.getReturnAllDepCategory(),report,title);
                            
//                            ReturnTitle += "  of  "+ IRView.getComboItemName();
//                             IRView.setlblReportTitle(ReturnTitle);
//                             String[] itemdata = IRView.getIssueReport();
//                        IRView.refreshTableReport(IRModel.getIssueReturnList(itemdata, date,false));
                        }
                        else{
                        String report = "returnAllReport.jrxml";
                      String title = "Issue Return Report";
                            IssueReturn ret = new IssueReturn(IRView.getReturnParams(),report,title);
                            //AllIssueReturn ret = new AllIssueReturn(IRView.getReturnParams());
//                             IRView.setlblReportTitle(ReturnTitle);
//                            IRView.refreshTableReport(IRModel.getIssueReturnList(null, date, true));
                        }
                }
                //if particular department is to be shown
                else
                {
                      if(!IRView.getBooleanIncludeAllCategoryName() ){
                          String report = "returnStoreCategory.jrxml";
                      String title = "Issue Return Report";
                            IssueReturn ret = new IssueReturn(IRView.getReturnDepCategory(),report,title);
                            //IssueReturn ret = new IssueReturn(IRView.getReturnParam());
                            
//                            ReturnTitle += "  of  "+ IRView.getComboItemName();
//                             IRView.setlblReportTitle(ReturnTitle);
//                             String[] itemdata = IRView.getIssueReport();
//                        IRView.refreshTableReport(IRModel.getIssueReturnList(itemdata, date,false));
                        }
                        else{
                          String report = "returnStoreAllItem.jrxml";
                      String title = "Issue Return Report";
                            IssueReturn ret = new IssueReturn(IRView.getReturnDepAll(),report,title);
                           // AllIssueReturn ret = new AllIssueReturn(IRView.getReturnParams());
//                             IRView.setlblReportTitle(ReturnTitle);
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
            if(cb == IRView.comboCategoryName)
            {
                if(cb.getSelectedIndex() == 0){
                    IRView.setItemId(0);
//                    IRView.setStoreId("");
                }
                else{
                for (Object[] Itemdata : IRModel.Itemdata) {
                    if (Itemdata[1].equals(cb.getSelectedItem())) {
                        item = Itemdata;
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
                      //reseting
                      IRView.setComboCategoryName(new String[]{});
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
                IRView.setComboCategoryName(IRModel.returnItemName(IRModel.getCategoryInfoForIssue()));
                IRView.AddSelectInCombo(IRView.returnComboCategoryName());
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
            IRView.comboCategoryName.setEnabled(false);
            
        }
        else{
            IRView.comboCategoryName.setEnabled(true);
        }
        }
    }
     public class CheckIncludeAllDepartmentListener implements ItemListener{

      

        @Override
        public void itemStateChanged(ItemEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getStateChange() == 1){
            IRView.comboStoreName.setEnabled(false);
           
              IRView.setComboCategoryName(IRModel.returnItemName(IRModel.getCategoryInfoForIssue()));
                IRView.AddSelectInCombo(IRView.returnComboCategoryName());
            
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
