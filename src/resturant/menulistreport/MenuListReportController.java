/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.menulistreport;

import hotelmanagementsystem.MainFrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import report.menu.MenuReport;

/**
 *
 * @author SUSHIL
 */
public class MenuListReportController {
    MenuListReportModel IRModel;
    MenuListReportView IRView;
     private MainFrameView mainview;
    
    public MenuListReportController(MenuListReportModel model,MenuListReportView view,MainFrameView main){
        IRModel= model;
        IRView = view;
        mainview = main;
       
     //   IRView.check
        IRView.addOkListener(new IssueReportListener());
        IRView.addCancelListener(new IssueReportListener());
        IRView.addCancelReportListener(new IssueReportListener());
        IRView.addPrintListener(new IssueReportListener());
        IRView.addSaveAsExcelListener(new IssueReportListener());
        /*
         * for comboitemname select
         */
        IRView.addComboCategoryNameSelectListener(new ComboSelectListener());
        IRView.addComboSubCategoryListener(new ComboSelectListener());
        /*
         * for include all
         */
        IRView.addCheckIncludeAllListener(new CheckIncludeAllListener());
        IRView.addCheckIncludeAllSubCategoryListener(new CheckIncludeAllSubCategoryListener());
        IRView.addComboDepartmentListener(new ComboDepartmentListener());
         /*
         * fetching the data into the combo box
         */
        try{
        IRView.setComboDepartmentName(IRModel.returnItemName(IRModel.getRespectiveDepartment(mainview.getUserId())));
       //if it has only one element select it order wise add select into it
            int combosize = IRView.returnComboDepartmentName().getModel().getSize();
            if(combosize >1){
                IRView.AddSelectInCombo(IRView.returnComboDepartmentName());
            }
            else{
                if(combosize == 1){
                IRView.returnComboDepartmentName().setSelectedIndex(0);
                }
            }
        IRView.setComboGroupName(IRModel.returnItemName(IRModel.getCategoryInfo()));
        IRView.AddSelectInCombo(IRView.returnComboItemName());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(view, e+" from IssuereportController");
        }
    }
    public class IssueReportListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("Ok")){
               if(IRView.getDepartmentId() == 0){
                    JOptionPane.showMessageDialog(IRView.DailogReport,"Please Select The Department Name");
                    return; 
               } 
                if(!IRView.getBooleanIncludeAllCategory())
                {
                if(IRView.getGroupId()==0){
                    JOptionPane.showMessageDialog(IRView.DailogReport,"Please Select The Category Name");
                    return;
                }
                if(!IRView.getBooleanIncludeAllSubCategory()){
                if(IRView.getCategoryId()==0){
                      JOptionPane.showMessageDialog(IRView.DailogReport,"Please Select The SubCategory Name");
                    return;
                }
                }
                }
              
//                    Date[] date = IRView.getIssueReportDate();
               
                if(IRView.getBooleanAllButton()){
                    
                    /*
                     * issue report should be fetche from database
                     */
                           
                      
                    
//                     
                      
                        if(!IRView.getBooleanIncludeAllCategory()){
                            //if checkboxincludeall category is not check
                          //  print the menu with selected category item
                            if(IRView.getBooleanIncludeAllSubCategory()){
                                //print report with all subcategory
                                String report = "allParent.jrxml";
                                String title = "Menu Report";
                                MenuReport menu = new MenuReport(IRView.getAllParent(), report, title);
                            }
                            else{
                                //print will selected subcategory it
                                String report = "allParentCategory.jrxml";
                                String title = "Menu Report";
                                MenuReport menu = new MenuReport(IRView.getAllParentCategory(), report, title);
                            }
                           
                         
                        }
                        else{
                            //print with all category including all sub category
                            String report = "all.jrxml";
                            String title = "Menu Report";
                            MenuReport menu = new MenuReport(IRView.getAllParam(),report, title);
                            //AllIssueReport issue = new AllIssueReport(IRView.getIssueParams());
//                          
                        }
//                      
                         IRView.setVisible(false);
//                  
                }
                //if retail price is selected
                else if(IRView.getBooleanRetailPriceButton()){
                  
                        if(!IRView.getBooleanIncludeAllCategory()){
                            //if checkboxincludeall category is not check
                          //  print the menu with selected category item
                            if(IRView.getBooleanIncludeAllSubCategory()){
                                //print report with all subcategory
                                String report = "retailParent.jrxml";
                                String title = "Menu Report";
                                MenuReport menu = new MenuReport(IRView.getRetailParent(), report, title);
                            }
                            else{
                                //print will selected subcategory it
                                String report = "retailParentCategory.jrxml";
                                String title = "Menu Report";
                                MenuReport menu = new MenuReport(IRView.getRetailParentCategory(), report, title);
                                
                            }
                           
                         
                        }
                        else{
                            //print with all category including all sub category
                            //AllIssueReport issue = new AllIssueReport(IRView.getIssueParams());
                            String report = "retail.jrxml";
                                String title = "Menu Report";
                                MenuReport menu = new MenuReport(IRView.getRetailParam(), report, title);
//                           
                        }
                       
          
                }
                //if wholesae if checked
                else if(IRView.getBooleanWholesalePriceButton()){
                     
                        if(!IRView.getBooleanIncludeAllCategory()){
                            //if checkboxincludeall category is not check
                          //  print the menu with selected category item
                            if(IRView.getBooleanIncludeAllSubCategory()){
                                //print report with all subcategory
                                String report = "wholesaleParent.jrxml";
                                String title = "Menu Report";
                                MenuReport menu = new MenuReport(IRView.getWholesaleParent(), report, title);
                            }
                            else{
                                //print will selected subcategory it
                                String report = "wholesaleParentCategory.jrxml";
                                String title = "Menu Report";
                                MenuReport menu = new MenuReport(IRView.getWholesaleParentCategory(),report, title);
                                
                            }
                           
                         
                        }
                        else{
                            //print with all category including all sub category
                            
                             String report = "wholesale.jrxml";
                                String title = "Menu Report";
                                MenuReport menu = new MenuReport(IRView.getWholesaleParam(), report, title);
                            //AllIssueReport issue = new AllIssueReport(IRView.getIssueParams());
//                           
                        }
            
            }
               
               
            }
            if(e.getActionCommand().equalsIgnoreCase("Cancel")){
//                IRView.setVisible(false);
//                System.out.println("wala1");
                IRView.clearAll();
//                 System.out.println("wala1");
            }
            if(e.getActionCommand().equalsIgnoreCase("ReportCancel")){
                IRView.DailogReport.setVisible(false);
                IRView.setVisible(true);
                IRView.clearAll();
            }
            
        }
        catch(Exception ire){
            JOptionPane.showMessageDialog(IRView,ire+ "From IssueReportListener ");
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
            if(cb == IRView.comboGroupName)
            {
                if(cb.getSelectedIndex() == 0){
                    IRView.setGroupId(0);
                    return;
//                    IRView.setCategoryId("");
                }
                else{
                for (Object[] Itemdata : IRModel.getCategoryInfo()) {
                    if (Itemdata[1].equals(cb.getSelectedItem())) {
                        item = Itemdata;
                          IRView.setGroupId(Integer.parseInt(item[0].toString()));
                          System.out.println(IRView.getGroupId());
           //loading repective subcategory
            IRView.setComboCategoryName(IRModel.returnItemBaseUnit(IRModel.getSubCategoryForCategory(IRView.getGroupId())));
            IRView.AddSelectInCombo(IRView.returnComboBaseUnitName());
                        break;
                    }
                    // Systeim.out.println(issueModel.Itemdata[i][1]);
                }
//            IRView.setOnComboItemNameSelect(item);
           // System.out.println(IRView.getCategoryId());
//         ItemUnitInfo = IRModel.getUnitInfo(IRView.getCategoryId());
           //SetonComboItemNameSelect Doesnot selecet combo itemBaseUnit done mannually by another fucntion
          
//            System.out.println(item[0]);
        
            /*
             * this load all the relative unitname that can be used to issue the item
             */
         
//            IRView.setComboCategoryName(IRModel.returnItemBaseUnit(ItemUnitInfo));
//            IRView.setComboCategoryName(item[3].toString());
                }
            
            }
            if(cb == IRView.comboSubCategoryName){
                 Object[][] SubCategoryid = IRModel.getSubCategoryForCategory(IRView.getGroupId());
                  if(cb.getSelectedIndex() == 0){
                      IRView.setCategoryId(0);
                    
                  }
                  else{
                for (Object[] ItemUnitInfo1 : SubCategoryid) {
                    if (ItemUnitInfo1[1].equals(cb.getSelectedItem())) {
                        Unit = ItemUnitInfo1;
                        break;
                    }
                }
               IRView.setCategoryId(Integer.parseInt(Unit[0].toString()));
//               System.out.println(IRView.getCategoryId());
                  }
//              // System.out.println(Unit[0]+"\n"+IRView.getCategoryId());
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
            IRView.comboGroupName.setEnabled(false);
            IRView.SubCategoryPanel.setVisible(false);
            
        }
        else{
            IRView.comboGroupName.setEnabled(true);
            IRView.SubCategoryPanel.setVisible(true);
        }
        }
    }
     public class CheckIncludeAllSubCategoryListener implements ItemListener{

      

        @Override
        public void itemStateChanged(ItemEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getStateChange() == 1){
            IRView.comboSubCategoryName.setEnabled(false);
//            IRView.SubCategoryPanel.setVisible(false);
            
        }
        else{
            IRView.comboSubCategoryName.setEnabled(true);
//            IRView.SubCategoryPanel.setVisible(true);
        }
        }
    }
      public class ComboDepartmentListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       try{
           JComboBox jc =  (JComboBox)e.getSource();
           
           if(jc.getSelectedItem().equals("SELECT")){
               IRView.setDepartmentId(0);
              
           }
           else{
               for(Object[] data:IRModel.getRespectiveDepartment(mainview.getUserId())){
                   if(data[1].equals(jc.getSelectedItem())){
                       IRView.setDepartmentId(Integer.parseInt(data[0].toString()));
//                       IRView.refreshTableIssueStockReport(IRModel.getSalesList(IRView.getDepartmentId()));
                     
                   }
               }
           }
       }
       catch(Exception se){
           JOptionPane.showMessageDialog(IRView , se+"from ComboDepartmentListener"+getClass().getName());
       }
        }
        
    }
}
