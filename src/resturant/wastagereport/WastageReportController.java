/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.wastagereport;

import hotelmanagementsystem.MainFrameView;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import reusableClass.DisplayMessages;
import reusableClass.Function;
import report.wastage.WastageReport;
//import report.terminalSalesReport.MonthlySalesReport;

/**
 *
 * @author SUSHIL
 */
public class WastageReportController {
    WastageReportModel IRModel;
    WastageReportView IRView;
    MainFrameView mainview;
     public Date time;
    
    public WastageReportController(WastageReportModel model,WastageReportView view,MainFrameView main){
        IRModel= model;
        IRView = view;
        mainview = main;
        /*
         * fetching the data into the combo box
         */
//        IRView.setComboItemName(IRModel.returnMenuName(IRModel.getMenuInfo()));
        
     //   IRView.check
        IRView.addOkListener(new SalesReportListener());
        IRView.addCancelListener(new SalesReportListener());
        IRView.addCancelReportListener(new SalesReportListener());
        IRView.addPrintListener(new SalesReportListener());
        IRView.addSaveAsExcelListener(new SalesReportListener());
       //Add Menu type or Item type Listener
        IRView.addRadioMenuTypeListener(new WastageTypeListener());
        IRView.addRadioItemTypeListener(new WastageTypeListener());
        //add include all Listener
        IRView.addCheckIncludeAllListener(new CheckIncludeAllListener());
        //add itemcobo type listener
        IRView.addComboItemNameSelectListener(new ComboItemNameSelectListener());
        IRView.addDayChooserListener(new DayPropertyListener());
        IRView.addMonthChooserListener(new MonthPropertyListener());
        IRView.addComboDepartmentListener(new ComboDepartmentListener());
        /*
         * for include all
         */
//        IRView.addCheckIncludeAllListener(new CheckIncludeAllListener());
        try{
         IRView.setComboDepartmentName(IRModel.returnMenuName(IRModel.getRespectiveDepartment(mainview.getUserId())));
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
          IRView.getCheckboxIncludeAll().setSelected(true);
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(view, se+"from constructor "+getClass().getName());
        }
    }
    public class SalesReportListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("Ok")){
//                if(!IRView.getBooleanIncludeAll())
//                {
//                if(IRView.getItemId().isEmpty()){
//                    JOptionPane.showMessageDialog(IRView.DailogReport,"Please Select The Item Name");
//                    return;
//                }
////                if(IRView.getUnitId().isEmpty()){
////                      JOptionPane.showMessageDialog(IRView.DailogReport,"Please Select The Item  Base Unit Name");
////                    return;
////                }
//                }
                if(IRView.getDepartmentId() == 0){
                   JOptionPane.showMessageDialog(IRView.DailogReport,"Please Select  Department Name ");
                    return;  
                }
                if(!IRView.getBooleanIncludeAllItemName()){
                if(IRView.getItemId() == 0){
                    DisplayMessages.displayInfo(IRView.DailogReport, "Please Select the  Name", "Select Name");
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
                 //display the menuwise wastage report
                 if(IRView.getRadioMenu()){
                     if(IRView.getBooleanIncludeAllItemName()){
                         WastageReport wastageReport = new WastageReport(IRView.getMWastageParms(),"allMenuWastage.jrxml","Wasteage Report");
                     }
                     else{
                         WastageReport wastageReport = new WastageReport(IRView.getMWastageParam(),"menuWastage.jrxml","Wasteage Report");
                     }
                 }
                 //display the itemwise wastage report
                 else{
                      if(IRView.getBooleanIncludeAllItemName()){
                          WastageReport wastageReport = new WastageReport(IRView.getIWastageParms(),"allItemWastage.jrxml","Wasteage Report");
                     }
                     else{
                          WastageReport wastageReport = new WastageReport(IRView.getIWastageParam(),"itemWastage.jrxml","Wasteage Report");
                     }
                     
                 }
                 
                   
                 IRView.setVisible(false);
//                        IRView.DailogReport.setVisible(true);
              
              
               
               
            }
            if(e.getActionCommand().equalsIgnoreCase("Cancel")){
                IRView.clearAll();
                IRView.setVisible(false);
                
            }
            if(e.getActionCommand().equalsIgnoreCase("ReportCancel")){
                IRView.clearAll();
                IRView.DailogReport.setVisible(false);
                IRView.setVisible(true);
                
            }
            
        }
        catch(HeadlessException ire){
            JOptionPane.showMessageDialog(IRView, "From IssueReportListener ");
        }
        }
        
    }
   
    

   public class DayPropertyListener implements PropertyChangeListener{

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Date date = IRView.DayChooser.getDate();
//       Calendar cal = Calendar.getInstance();
//       cal.setTime(date);
//       cal.add(Calendar.DATE,1);
        IRView.setDailyDate(date);
       IRView.setStartDate(IRModel.ChangeDate(date, -1));
       IRView.setEndDate(IRModel.ChangeDate(date, 1));
       IRView.DialogPeriodType.setVisible(false);
     //  IRView.setEditableEndDateFalse();
     //  IRView.setEditableStartDateFalse();
       
        }
        
    }
   public class MonthPropertyListener implements PropertyChangeListener{

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int Month = IRView.MonthChooser.getMonth();
        int Year = IRView.YearChooser.getYear();
        //System.out.println(Month+"\n"+Year);
//         Calendar cal = Calendar.getInstance();
//         cal.set(Year, Month, 0);
           IRView.setStartDate(IRModel.ChangeMonthBefore(Year, Month, 1));
       IRView.setEndDate(IRModel.ChangeMonthAfther(Year, Month, 1));
      IRView.DialogPeriodType.setVisible(false);
         
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
   
   public class WastageTypeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        try{
            JRadioButton jradio = (JRadioButton)e.getSource();
            if(jradio == IRView.getRadioMenuType()){
                //display the list of the menu item
                IRView.setComboItemName(Function.returnSecondColumn(IRModel.getMenuInfo(IRView.getDepartmentId())));
                Function.AddSelectInCombo(IRView.returnComboItemName());
            }
            else{
                IRView.setComboItemName(Function.returnSecondColumn(IRModel.getItemInfoForMenu(IRView.getDepartmentId())));
                Function.AddSelectInCombo(IRView.returnComboItemName());
            }
            
        }
        catch(Exception se){
            DisplayMessages.displayError(IRView, se.getMessage()+"from "+ getClass().getName(), "WastageTypeListener Error");
        }
        }
       
   }
   public class CheckIncludeAllListener implements ItemListener{

        @Override
        public void itemStateChanged (ItemEvent e) {
        try{
            if(e.getStateChange() == 1)
            {
                IRView.returnComboItemName().setEnabled(false);
            }
            else{
                IRView.returnComboItemName().setEnabled(true);
            }
        }
        catch(Exception se){
            DisplayMessages.displayError(IRView, se.getMessage()+"from CheckIncludeAllListener()" +getClass().getName(), " CheckIncludeAllListener Error");
        }
        }

       

       
       
   }
   public class ComboItemNameSelectListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                JComboBox jc = (JComboBox)e.getSource();
                if(jc.getSelectedIndex() == 0){
                    IRView.setItemId(0);
                    return;
                }
                //update menuid into the itemid
                if(IRView.getRadioMenu()){
                    IRView.setItemId(IRModel.getMenuIdByMenuName(IRView.getComboItemName()));
//                    System.out.println(IRView.getItemId());
                }
                else{
                    IRView.setItemId(IRModel.getItemIdByItemName(IRView.getComboItemName()));
//                    System.out.println(IRView.getItemId());
                }
            }
            catch(Exception se){
                DisplayMessages.displayError(IRView, se.getMessage()+"from" +getClass().getName(), "ComboItemNameSelectListener");
            }
        }
       
   }
}

