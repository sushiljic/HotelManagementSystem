/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.servicechargereport;

import hotelmanagementsystem.MainFrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import report.terminalSVReport.SVCReport;

/**
 *
 * @author SUSHIL
 */
public class ServiceChargeReportController {
      ServiceChargeReportModel SCRModel;
    ServiceChargeReportView SCRView;
    private MainFrameView mainview;
    public ServiceChargeReportController(ServiceChargeReportModel model,ServiceChargeReportView view,MainFrameView main){
          SCRModel  = model;
        SCRView= view;
        mainview= main;
        /*
         * loading menu name in combi list
         */
       // SCRView.setComboMenuName(SCRModel.returnMenuName(SCRModel.getMenuInfo()));
        
        SCRView.addOkListener(new ComplimentaryListener());
        SCRView.addCancelListener(new ComplimentaryListener());
        SCRView.addPrintListener(new ComplimentaryListener());
        SCRView.addSaveAsExcelListener(new ComplimentaryListener());
        SCRView.addCancelReportListener(new ComplimentaryListener());
        /*
         * adding the menuitem listener
         */
//        SCRView.addComboMenuNameSelectListener(new ComboMenuNameSelectListener());
//        SCRView.addCheckIncludeAllListener(new CheckIncludeAllListener());
            SCRView.addComboDepartmentListener(new ComboDepartmentListener());
        /*
         * for include all
         */
//        IRView.addCheckIncludeAllListener(new CheckIncludeAllListener());
        try{
         SCRView.setComboDepartmentName(SCRModel.returnMenuName(SCRModel.getRespectiveDepartment(mainview.getUserId())));
        //if it has only one element select it order wise add select into it
            int combosize = SCRView.returnComboDepartmentName().getModel().getSize();
            if(combosize >1){
                SCRView.AddSelectInCombo(SCRView.returnComboDepartmentName());
            }
            else{
                if(combosize == 1){
                SCRView.returnComboDepartmentName().setSelectedIndex(0);
                }
            }
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(view, se+"from constructor "+getClass().getName());
        }
    }
     public class ComplimentaryListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("Ok")){
//                if(!SCRView.getBooleanIncludeAll()){
//                    if(SCRView.getMenuId().isEmpty()){
//                          JOptionPane.showMessageDialog(SCRView.DailogReport,"Please Select  Menu Name ");
//                    return;
//                        }
//                    }
               if(SCRView.getDepartmentId() == 0){
                     JOptionPane.showMessageDialog(SCRView.DailogReport,"Please Select  Department Name. ");
                    return;
               }
                if(SCRView.getStartDate() == null){
                     JOptionPane.showMessageDialog(SCRView.DailogReport,"Please Select  Start Date ");
                    return;
                    
                }
                 if(SCRView.getEndDate() == null){
                     JOptionPane.showMessageDialog(SCRView.DailogReport,"Please Select  End Date ");
                    return;
                }  
                 Date[] date = SCRView.getServiceChargeReportDate();
//                 String title = "Service Charge Report";
//                 if(!SCRView.getBooleanIncludeAll()){
//                     title += " \t of \t"+SCRView.getComboMenuName();
//                 }
//                 SCRView.setlblReportTitle(title);
                 DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
                 SCRView.setlblStartDate(df.format(date[0]));
                 SCRView.setlblEndDate(df.format(date[1]));
//                SCRView.refreshTableReport(SCRModel.getServiceChargeList(date));
//                 if(SCRView.getTableReport().getRowCount() <= 0){
//                 JOptionPane.showMessageDialog(SCRView, "Record Not Found");
//                 return;
//                 }
//                
                
                 /*
                  * calculating the toal complimetary amount given
//                  */
//                 BigDecimal TotalServiceChargeAmount = BigDecimal.ZERO;
//                  BigDecimal TotalAmount = BigDecimal.ZERO;
//                   BigDecimal TotalServiceChargePercentage = BigDecimal.ZERO;
//                 for(int i=0;i< SCRView.getTableReport().getRowCount();i++){
//                     TotalAmount  = TotalAmount.add(new BigDecimal(SCRView.getTableReport().getValueAt(i, 1).toString()));
//                     TotalServiceChargeAmount  = TotalServiceChargeAmount.add(new BigDecimal(SCRView.getTableReport().getValueAt(i, 2).toString()));
//                     TotalServiceChargePercentage = TotalServiceChargeAmount.divide(TotalAmount).multiply(BigDecimal.valueOf(100));
//                 }
//                SCRView.getTableReport().addRow(new Object[]{"Total Service Charge Amount",TotalAmount,TotalServiceChargeAmount,TotalServiceChargePercentage+"(Average %)"});
//                
//                SCRView.setVisible(false);
                //SCRView.DailogReport.pack();
                //SCRView.DailogReport.setVisible(true);
                 String report = "svcOnly.jrxml";
                 String title = "SVC Report";
                 SVCReport r = new SVCReport(SCRView.getReportParam(),report,title);
            
            }
           if(e.getActionCommand().equalsIgnoreCase("ReportCancel")){
               SCRView.DailogReport.setVisible(false);
               SCRView.setVisible(true);
           }
           if(e.getActionCommand().equalsIgnoreCase("Cancel")){
               SCRView.setVisible(false);
           }
        }
        catch(Exception ce){
            JOptionPane.showMessageDialog(SCRView,ce+ "From ServiceChargeListener");
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
               SCRView.setDepartmentId(0);
              
           }
           else{
               for(Object[] data:SCRModel.getRespectiveDepartment(mainview.getUserId())){
                   if(data[1].equals(jc.getSelectedItem())){
                       SCRView.setDepartmentId(Integer.parseInt(data[0].toString()));
//                       IRView.refreshTableIssueStockReport(IRModel.getSalesList(IRView.getDepartmentId()));
                   }
               }
           }
       }
       catch(Exception se){
           JOptionPane.showMessageDialog(SCRView , se+"from ComboDepartmentListener"+getClass().getName());
       }
        }
        
    }
    
}
