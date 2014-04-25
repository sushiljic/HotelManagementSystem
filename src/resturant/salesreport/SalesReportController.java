/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.salesreport;

import hotelmanagementsystem.MainFrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import report.terminalSalesReport.SalesReport;
//import report.terminalSalesReport.MonthlySalesReport;

/**
 *
 * @author SUSHIL
 */
public class SalesReportController {
    SalesReportModel IRModel;
    SalesReportView IRView;
    MainFrameView mainview;
     public Date time;
    
    public SalesReportController(SalesReportModel model,SalesReportView view,MainFrameView main){
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
        /*
         * for comboitemname select
         */
//        IRView.addComboItemNameSelectListener(new ComboSelectListener());
//        IRView.addComboBaseUnitListener(new ComboSelectListener());
        IRView.addComboReportTypeListener(new ComboReportTypeListener());
        /*
         * property change for the jdatechoser
         */
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
                if(IRView.getStartDate() == null){
                     JOptionPane.showMessageDialog(IRView.DailogReport,"Please Select  Start Date ");
                    return;
                }
                 if(IRView.getEndDate() == null){
                     JOptionPane.showMessageDialog(IRView.DailogReport,"Please Select  End Date ");
                    return;
                }
                    Date[] date = IRView.getIssueReportDate();
               
              
                    /*
                     * issue report should be fetche from database
                     */
                           
                            
                      String title = " Sales Report ";
                        /*
                         * when icnlude all is clicked then true is passed as parameter
                         */
                        if(IRView.getComboReportType().equalsIgnoreCase("Daily")){
                           
//                          title  = "Daily "+ title;
                            String report = "DailySalesReport.jrxml";
                            title = "Daily Sales Report";
                          SalesReport dView = new SalesReport(IRView.getReportPara(),report,title);
                        }
                        else{
//                            title = "Monthly"+ title;
                            String report = "MonthlySalesReport.jrxml";
                            title = "Monthly Sales Report";
                            SalesReport mReport = new SalesReport(IRView.getReportParams(),report,title);
                        }
                          
                            
//                             IRView.setlblReportTitle(title);
                             //String[] itemdata = IRView.getIssueReport();
//                        IRView.refreshTableReport(IRModel.getSalesList(date));
//                         if(IRView.getTableReport().getRowCount() <= 0){
//                            JOptionPane.showMessageDialog(IRView.DailogReport, "Record Not Found");
//                            return;
//                        }
//                        DefaultTableModel ModelTableReport = (DefaultTableModel) IRView.tblReport.getModel();
//                        BigDecimal ItemTotal = BigDecimal.ZERO;
//                         BigDecimal TotalSVC = BigDecimal.ZERO;
//                          BigDecimal TotalVAT = BigDecimal.ZERO;
//                           BigDecimal TotalDiscount = BigDecimal.ZERO;
//                         BigDecimal TotalBillAmount = BigDecimal.ZERO;
//                          BigDecimal TotalReceivedAmount = BigDecimal.ZERO;
                          
//                        String UnitType = new String();
//                       for(int i=0;i<ModelTableReport.getRowCount();i++){
//                         ItemTotal = ItemTotal.add(new BigDecimal(ModelTableReport.getValueAt(i, 1).toString()));
//                          TotalSVC = TotalSVC.add(new BigDecimal(ModelTableReport.getValueAt(i, 2).toString()));
//                          TotalVAT = TotalVAT.add(new BigDecimal(ModelTableReport.getValueAt(i, 3).toString()));
//                          TotalDiscount = TotalDiscount.add(new BigDecimal(ModelTableReport.getValueAt(i, 4).toString()));
//                          TotalBillAmount = TotalBillAmount.add(new BigDecimal(ModelTableReport.getValueAt(i, 5).toString()));
//                          TotalReceivedAmount = TotalReceivedAmount.add(new BigDecimal(ModelTableReport.getValueAt(i, 6).toString()));
//                        }
//                       // System.out.println(TotalQuantity+UnitType);
//                        //IRView.tblReport.getModel();
//                        ModelTableReport.addRow(new Object[]{"Total",ItemTotal,TotalSVC,TotalVAT,TotalDiscount,TotalBillAmount,TotalReceivedAmount});
                      
//                        else{
//                               title  = "Monthly "+ title;
//                             IRView.setlblReportTitle(title);
//                         //   IRView.refreshTableReport(IRModel.getIssueList(null, date, true));
//                        }
                     
//                         DateFormat dt = DateFormat.getDateInstance(DateFormat.FULL);
//                        IRView.setlblStartDate(dt.format(IRView.getStartDate()));
//                        IRView.setlblEndDate(dt.format(IRView.getEndDate()));
//                        IRView.DailogReport.pack();
//                        IRView.DailogReport.setModal(true);
                         IRView.setVisible(false);
//                        IRView.DailogReport.setVisible(true);
               //for viewing report
                        if(IRView.getComboReportType().equalsIgnoreCase("Daily")){
                           
                         // title  = "Daily "+ title;
                            
                        }
                        else{
                            //title = "Monthly"+ title;
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
}
