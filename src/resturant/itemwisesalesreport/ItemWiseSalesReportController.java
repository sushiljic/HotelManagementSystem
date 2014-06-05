/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.itemwisesalesreport;

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
import report.terminalItemSalesReport.ItemSalesReport;
import reusableClass.DisplayMessages;
import reusableClass.Function;


/**
 *
 * @author SUSHIL
 */
public class ItemWiseSalesReportController {
    ItemWiseSalesReportModel IRModel;
    ItemWiseSalesReportView IRView;
    private MainFrameView mainview;
    public Date time;
    
    public ItemWiseSalesReportController(ItemWiseSalesReportModel model,ItemWiseSalesReportView view,MainFrameView main){
        IRModel= model;
        IRView = view;
        mainview = main;
      
        
     //   IRView.check
        IRView.addOkListener(new SalesReportListener());
        IRView.addCancelListener(new SalesReportListener());
        IRView.addCancelReportListener(new SalesReportListener());
        IRView.addPrintListener(new SalesReportListener());
        IRView.addSaveAsExcelListener(new SalesReportListener());
        /*
         * for comboitemname select
         */
        IRView.addComboMenuNameSelectListener(new ComboMenuNameSelectListener());
//        IRView.addComboBaseUnitListener(new ComboSelectListener());
        IRView.addComboReportTypeListener(new ComboReportTypeListener());
        /*
         * property change for the jdatechoser
         */
        IRView.addDayChooserListener(new DayPropertyListener());
        IRView.addMonthChooserListener(new MonthPropertyListener());
        /*
         * for include all
         */
        IRView.addCheckIncludeAllListener(new CheckIncludeAllListener());
         IRView.addComboDepartmentListener(new ComboDepartmentListener());
         //adding the trackable and nontrackable listener
         IRView.addRadioBothListener(new ItemTypeStatusListener());
         IRView.addRadioTrackableListener(new ItemTypeStatusListener());
         IRView.addRadioNonTrackableListener(new ItemTypeStatusListener());
         
         try{
         IRView.setComboDepartmentName(IRModel.returnMenuName(IRModel.getRespectiveDepartment(mainview.getUserId())));
         //set the both radio of that selected
         IRView.setBothStatus(true);
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
              if(IRView.getDepartmentId() == 0){
                   JOptionPane.showMessageDialog(IRView.DailogReport,"Please Select  Department Name. ");
                    return;
              }
            if(!IRView.getBooleanIncludeAll()){
                if(IRView.getMenuId().isEmpty()){
                      JOptionPane.showMessageDialog(IRView.DailogReport,"Please Select  Menu Name ");
                return;
                    }
                }
////                if(IRView.getUnitId().isEmpty()){
////                      JOptionPane.showMessageDialog(IRView.DailogReport,"Please Select The Item  Base Unit Name");
////                    return;
////                }
//                }
                if(IRView.getStartDate() == null){
                     JOptionPane.showMessageDialog(IRView.DailogReport,"Please Select  Start Date ");
                    return;
                }
                 if(IRView.getEndDate() == null){
                     JOptionPane.showMessageDialog(IRView.DailogReport,"Please Select  End Date ");
                    return;
                }
//                    Date[] date = IRView.getItemWiseSalesReportDate();
               
              
                    /*
                     * issue report should be fetche from database
                     */
                           
                            
//                      String title = " ItemWise Sales Report ";
//                        /*
//                         * when icnlude all is clicked then true is passed as parameter
//                         */
//                        if(IRView.getComboReportType().equalsIgnoreCase("Daily")){
//                           
//                          title  = "Daily "+ title;
//                         
//                          
//                        }
//                        else{
//                            title = "Monthly"+ title;
//                        }
                        /*
                         * if all menu list is to reported
                         */
                        //for viewing jasper report 
                        if(IRView.getComboReportType().equalsIgnoreCase("Daily") && IRView.getBooleanIncludeAll() ){
                             //DailyAllItemSalesReport report = new DailyAllItemSalesReport(IRView.getDailyAllParam());
                            //String report = "DailyAllItemSalesReport.jrxml";
                            //String title = "Daily Item Sales Report";
                            //ItemSalesReport sales = new ItemSalesReport(IRView.getDailyAllParam(),report,title);
                            if(IRView.getBothStatus()){
                                //display the report with both trackable and non trackable report
                                new ItemSalesReport(IRView.getDailyAllParam(),"DailyAllItemSalesReport.jrxml","Daily Sales Report");
                            }
                            else if(IRView.getTrackableStatus()){
                                //display the report with trackable menu sales
                                new ItemSalesReport(IRView.getDailyAllTrack(),"DailyAllTrackable.jrxml","Daily Sales Report Trackable");
                            }
                            else if(IRView.getNonTrackableStatus()){
                                //display the repor with nontrackable menu sales
                                new ItemSalesReport(IRView.getDailyAllUnTrack(),"DailyAllUntrackable.jrxml","Daily Sales Report Untrackable");
                            }
                         }
                        else if(IRView.getComboReportType().equalsIgnoreCase("Daily")){
                            //DailyItemSalesReport dReport = new DailyItemSalesReport(IRView.getDailyParam());
                            //String report = "DailyItemSalesReport.jrxml";
                            //String title = "Daily Item Sales Report";
                            //ItemSalesReport sales = new ItemSalesReport(IRView.getDailyParam(),report,title);
                            
                             if(IRView.getBothStatus()){
                                //display the report with both trackable and non trackable report
                                 new ItemSalesReport(IRView.getDailyParam(),"DailyItemSalesReport","Daily Item Sales Report");
                            }
                            else if(IRView.getTrackableStatus()){
                                //display the report with trackable menu sales
                                new ItemSalesReport(IRView.getDailyTrack(),"DailySalesTrackable.jrxml","Daily Item Sales Report Trackable");
                            }
                            else if(IRView.getNonTrackableStatus()){
                                //display the repor with nontrackable menu sales
                                new ItemSalesReport(IRView.getDailyTrack(),"DailySalesUntrackable.jrxml","Daily Item Sales Report Untrackable");
                            }
                        }              
                        
                        else if(IRView.getComboReportType().equalsIgnoreCase("Monthly") && IRView.getBooleanIncludeAll() ){
                            //String report = "MonthlyAllItemSalesReport.jrxml";
                            //String title = "Monthly Item Sales Report";
                            //ItemSalesReport sales = new ItemSalesReport(IRView.getMonthlyAllParam(),report,title);
// MonthlyAllSalesReport monthly = new MonthlyAllSalesReport(IRView.getMonthlyAllParam());
                             if(IRView.getBothStatus()){
                                //display the report with both trackable and non trackable report
                                 new ItemSalesReport(IRView.getMonthlyAllParam(),"MonthlyAllItemSalesReport.jrxml","Monthly Sales Report");
                            }
                            else if(IRView.getTrackableStatus()){
                                //display the report with trackable menu sales
                                new ItemSalesReport(IRView.getMonthlyAllTrack(),"MonthlyAllTrackable.jrxml","Monthly Sales Report Trackable");
                            }
                            else if(IRView.getNonTrackableStatus()){
                                //display the repor with nontrackable menu sales
                                new ItemSalesReport(IRView.getMonthlyAllUnTrack(),"MonthlyAllUntrackable.jrxml","Monthly Sales Report Untrackable");
                            }
                         }
                        else if(IRView.getComboReportType().equalsIgnoreCase("Monthly")){
                            //String report = "MonthlyItemSalesReport.jrxml";
                            //ItemSalesReport sales = new ItemSalesReport(IRView.getMonthlyParam(),report,"Montyly Item Sales Report");
//MonthlyItemSalesReport mReport = new MonthlyItemSalesReport(IRView.getMonthlyParam());
                             if(IRView.getBothStatus()){
                                //display the report with both trackable and non trackable report
                                 new ItemSalesReport(IRView.getMonthlyParam(),"MonthlyItemSalesReport.jrxml","Monthly Sales Report ");
                            }
                            else if(IRView.getTrackableStatus()){
                                //display the report with trackable menu sales
                                new ItemSalesReport(IRView.getMonthlyTrack(),"MonthlyTrackable.jrxml","Monthly Sales Report Trackable");
                            }
                            else if(IRView.getNonTrackableStatus()){
                                //display the repor with nontrackable menu sales
                                new ItemSalesReport(IRView.getMonthlyUnTrack(),"MonthlyUntrackable.jrxml","Monthly Sales Report Untrackable");
                            }
                        }
                         
//                        if(IRView.getBooleanIncludeAll()){
//                             IRView.setlblReportTitle(title);
//                              
//                             //String[] itemdata = IRView.getItemWiseSalesReport();
//                        IRView.refreshTableReport(IRModel.getSalesList(null,date,true));
//                            
//                        }
//                        else{
//                            /*
//                             * if certain menuname is selected
//                             */
//                            title += "\t of \t"+ IRView.getComboMenuName();
//                              IRView.setlblReportTitle(title);
//                             //String[] itemdata = IRView.getItemWiseSalesReport();
//                        IRView.refreshTableReport(IRModel.getSalesList(IRView.getMenuId(),date,false));
//                            
//                        }
//                          
//                            
//                            
//                         if(IRView.getTableReport().getRowCount() <= 0){
//                            JOptionPane.showMessageDialog(IRView.DailogReport, "Record Not Found");
//                            return;
//                        }
//                        DefaultTableModel ModelTableReport = (DefaultTableModel) IRView.tblReport.getModel();
//                        BigDecimal ItemTotal = BigDecimal.ZERO;
////                        
////                          
//////                        String UnitType = new String();
//                       for(int i=0;i<ModelTableReport.getRowCount();i++){
//                         ItemTotal = ItemTotal.add(new BigDecimal(ModelTableReport.getValueAt(i, 4).toString()));
////                       
//                        }
//////                     
//                        ModelTableReport.addRow(new Object[]{"Total",null,null,null,ItemTotal,null,null,null});
//                      
////                      
//                         DateFormat dt = DateFormat.getDateInstance(DateFormat.FULL);
//                        IRView.setlblStartDate(dt.format(IRView.getStartDate()));
//                        IRView.setlblEndDate(dt.format(IRView.getEndDate()));
//                        IRView.DailogReport.pack();
//                        IRView.DailogReport.setModal(true);
                         IRView.setVisible(false);
//                        IRView.DailogReport.setVisible(true);
               
              
               
               
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
        catch(HeadlessException ire){
            JOptionPane.showMessageDialog(IRView, "From IssueReportListener ");
        }
        }
        
    }
   
    public class ComboReportTypeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
           JComboBox jreport = (JComboBox)e.getSource();
           if(jreport.getSelectedItem().equals("Daily")){
             //  JOptionPane.showMessageDialog(jreport, "wala daiay");
             //  IRView.DialogPeriodType.pack();
             //  IRView.DialogPeriodType.setModal(true);
                 IRView.hideMonthPanel();
                 IRView.showDayChooserPanel();
                IRView.DialogPeriodType.pack();
                 IRView.DialogPeriodType.setModal(true);
              //  IRView.DayChooser.setVisible(true);
               IRView.DialogPeriodType.setVisible(true);
             
               
           }
           else if(jreport.getSelectedItem().equals("Monthly")){
               // JOptionPane.showMessageDialog(jreport, "wala daiay");
                IRView.hideDayChooserPanel();
                IRView.showMonthPanel();
                  IRView.DialogPeriodType.pack();
                 IRView.DialogPeriodType.setModal(true);
                  IRView.DialogPeriodType.setVisible(true);
           }
        }
        catch(Exception ce){
            JOptionPane.showMessageDialog(IRView, "From ComboReportTypeListener");
        }
        }
        
    }
    public class DayPropertyListener implements PropertyChangeListener{

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Date date = IRView.DayChooser.getDate();
        IRView.setDailyDate(date);
//       Calendar cal = Calendar.getInstance();
//       cal.setTime(date);
//       cal.add(Calendar.DATE,1);
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
    public class ComboMenuNameSelectListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       try{
           if(e.getActionCommand().equalsIgnoreCase("ComboMenuName"))
           {
           Object[] item = null;
                JComboBox comboMenu = (JComboBox) e.getSource();
                if(comboMenu.getSelectedIndex() == 0){
                    IRView.setMenuId("");
                }
                else{
               for (Object[] MenuInfo : IRModel.MenuInfo) {
                   if (comboMenu.getSelectedItem().equals(MenuInfo[1])) {
                       item = MenuInfo;
                       break;
                   }
               }
                IRView.setMenuId(item[0].toString());
                }
               // System.out.println(CRView.getMenuId());
           }
       }
       catch(Exception cme){
           JOptionPane.showMessageDialog(IRView, cme+"From ComboMenuNameSelectListener");
       }
        }
       
   }
    public class CheckIncludeAllListener implements ItemListener{

      

        @Override
        public void itemStateChanged(ItemEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getStateChange() == 1){
            IRView.comboMenuName.setEnabled(false);
            
        }
        else{
            IRView.comboMenuName.setEnabled(true);
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
               IRView.setComboMenuName(new String[]{});
              
           }
           else{
               for(Object[] data:IRModel.getRespectiveDepartment(mainview.getUserId())){
                   if(data[1].equals(jc.getSelectedItem())){
                       IRView.setDepartmentId(Integer.parseInt(data[0].toString()));
//                       IRView.refreshTableIssueStockReport(IRModel.getSalesList(IRView.getDepartmentId()));
                        IRView.setComboMenuName(IRModel.returnMenuName(IRModel.getMenuInfo(IRView.getDepartmentId(),false,false)));
                        IRView.AddSelectInCombo(IRView.returnMenuName());
                   }
               }
           }
       }
       catch(NumberFormatException se){
           JOptionPane.showMessageDialog(IRView , se+"from ComboDepartmentListener"+getClass().getName());
       }
        }
        
    }
    public class ItemTypeStatusListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        try{
            JRadioButton jradio = (JRadioButton)e.getSource();
            if(jradio == IRView.getRadioBoth()){
                IRView.setComboMenuName(Function.returnSecondColumn(IRModel.getMenuInfo(IRView.getDepartmentId(), false, false)));
                Function.AddSelectInCombo(IRView.returnMenuName());
                
            }
            else if(jradio ==IRView.getRadioTrackable()){
                IRView.setComboMenuName(Function.returnSecondColumn(IRModel.getMenuInfo(IRView.getDepartmentId(), true, false)));
                Function.AddSelectInCombo(IRView.returnMenuName());
                
            }
            else if(jradio == IRView.getRadioNontrackable()){
                IRView.setComboMenuName(Function.returnSecondColumn(IRModel.getMenuInfo(IRView.getDepartmentId(), false, true)));
                Function.AddSelectInCombo(IRView.returnMenuName());
                
            }
        }
        catch(Exception se){
            DisplayMessages.displayError(IRView, se.getMessage() +" from "+ getClass().getName(),"ItemTypeStatusListener Error");
        }
        }
        
    }
   
}
