/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.receivablereport;

import hotelmanagementsystem.MainFrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
//import report.receivableReport.AllReceivableReport;
import report.receivableReport.ReceivableReport;

/**
 *
 * @author SUSHIL
 */
public class ReceiveableReportController {
      ReceiveableReportModel RRModel;
    ReceivableReportView RRView;
    private MainFrameView mainview;
    public ReceiveableReportController(ReceiveableReportModel model,ReceivableReportView view,MainFrameView main){
          RRModel  = model;
        RRView= view;
        mainview = main;
        /*
         * loading menu name in combi list
         */
       // RRView.setComboMenuName(RRModel.returnMenuName(RRModel.getCustomerInfo()));
     
        RRView.addOkListener(new ReceiveableListener());
        RRView.addCancelListener(new ReceiveableListener());
        RRView.addPrintListener(new ReceiveableListener());
        RRView.addSaveAsExcelListener(new ReceiveableListener());
        RRView.addCancelReportListener(new ReceiveableListener());
        /*
         * adding the menuitem listener
         */
        RRView.addComboCustomerNameSelectListener(new ComboCustomerNameSelectListener());
        RRView.addCheckIncludeAllListener(new CheckIncludeAllListener());
        RRView.addComboDepartmentListener(new ComboDepartmentListener());
           try{
        RRView.setComboDepartmentName(RRModel.returnCustomerName(RRModel.getRespectiveDepartment(mainview.getUserId())));
       //if it has only one element select it order wise add select into it
            int combosize = RRView.returnComboDepartmentName().getModel().getSize();
            if(combosize >1){
                RRView.AddSelectInCombo(RRView.returnComboDepartmentName());
            }
            else{
                if(combosize == 1){
                RRView.returnComboDepartmentName().setSelectedIndex(0);
                }
            }
        RRView.setComboCustomerName(RRModel.returnCustomerName(RRModel.getCustomerInfoObject()));
        RRView.AddSelectInCombo(RRView.returnComboCustomer());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(view, e+" From  ReceiveablereportConstructor ");
        }
    }
     public class ReceiveableListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("Ok")){
                if(RRView.getDepartmentId() == 0){
                   JOptionPane.showMessageDialog(RRView.DailogReport,"Please Select  Department Name ");
                    return;  
                }
                if(!RRView.getBooleanIncludeAll()){
                    if(RRView.getCustomerId()==0){
                          JOptionPane.showMessageDialog(RRView.DailogReport,"Please Select  Menu Name ");
                    return;
                        }
                    }
                
                if(RRView.getStartDate() == null){
                     JOptionPane.showMessageDialog(RRView.DailogReport,"Please Select  Start Date ");
                    return;
                    
                }
                 if(RRView.getEndDate() == null){
                     JOptionPane.showMessageDialog(RRView.DailogReport,"Please Select  End Date ");
                    return;
                }  
//                 Date[] date = RRView.getComplimentaryReportDate();
//                 String title = "Receiveable Report";
//                 if(!RRView.getBooleanIncludeAll()){
//                     title += " \t of \t"+RRView.getComboCustomerName();
//                 }
//                 RRView.setlblReportTitle(title);
//                 DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
//                 RRView.setlblStartDate(df.format(date[0]));
//                 RRView.setlblEndDate(df.format(date[1]));
//                 /*
//                  * two state whethe it is include all or particula
//                  */
                 if(RRView.getBooleanIncludeAll()){
//                      RRView.refreshTableReport(RRModel.getReceiveableList(null,date, Boolean.TRUE));
//                      if(RRView.getTableReport().getRowCount()<=0){
//                          JOptionPane.showMessageDialog(RRView, "Record Not Found");
//                          return;
//                      }
                    RRView.setVisible(false);
                    //RRView.DailogReport.pack();
                     //RRView.DailogReport.setVisible(true);
                    ReceivableReport r = new ReceivableReport(RRView.getReportParams(),null,null);
                    
                    
                     
                 }
                 else{
//                  RRView.refreshTableReport(RRModel.getReceiveableList(RRView.getCustomerId(),date, false));
//                    if(RRView.getTableReport().getRowCount()<=0){
//                          JOptionPane.showMessageDialog(RRView, "Record Not Found");
//                          return;
//                      }
                   RRView.setVisible(false);
                   // RRView.DailogReport.pack();
                     //RRView.DailogReport.setVisible(true);
                   ReceivableReport r = new ReceivableReport(RRView.getReportParam(),null,null);
                   
                 }
                 /*
                  * calculating the toal complimetary amount given
                  */
//                 BigDecimal TotalReceiableAmount = BigDecimal.ZERO;
//                  BigDecimal TotalAmount = BigDecimal.ZERO;
//                 for(int i=0;i< RRView.getTableReport().getRowCount();i++){
//                     TotalReceiableAmount  = TotalReceiableAmount.add(new BigDecimal(RRView.getTableReport().getValueAt(i, 4).toString()));
//                      TotalAmount  = TotalAmount.add(new BigDecimal(RRView.getTableReport().getValueAt(i, 3).toString()));
//                 }
//                RRView.getTableReport().addRow(new Object[]{"Total Amount Receiveable",null,null,TotalAmount.setScale(2, RoundingMode.HALF_UP),TotalReceiableAmount,null});
                 
            
            }
           if(e.getActionCommand().equalsIgnoreCase("ReportCancel")){
               RRView.DailogReport.setVisible(false);
               RRView.setVisible(true);
           }
           if(e.getActionCommand().equalsIgnoreCase("Cancel")){
               RRView.setVisible(false);
           }
        }
        catch(Exception ce){
            JOptionPane.showMessageDialog(RRView,ce+ "From ReceiveableListener");
        }
        }

        private void ortParam() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
       
   } 
   public class ComboCustomerNameSelectListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       try{
           if(e.getActionCommand().equalsIgnoreCase("ComboCustomerName"))
           {
           Object[] item = null;
                JComboBox comboMenu = (JComboBox) e.getSource();
                if(comboMenu.getSelectedIndex() == 0){
                    RRView.setCustomerId(0);
                }
                else{
               for (Object[] CustomerInfo : RRModel.CustomerInfo) {
                   if (comboMenu.getSelectedItem().equals(CustomerInfo[1])) {
                       item = CustomerInfo;
                       break;
                   }
               }
                RRView.setCustomerId(Integer.parseInt(item[0].toString()));
                }
               // System.out.println(CRView.getMenuId());
           }
       }
       catch(Exception cme){
           JOptionPane.showMessageDialog(RRView, cme+"From ComboCustomerNameSelectListener");
       }
        }
       
   }
    public class CheckIncludeAllListener implements ItemListener{

      

        @Override
        public void itemStateChanged(ItemEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getStateChange() == 1){
            RRView.comboCustomerName.setEnabled(false);
            
        }
        else{
            RRView.comboCustomerName.setEnabled(true);
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
               RRView.setDepartmentId(0);
             
              
           }
           else{
               for(Object[] data:RRModel.getRespectiveDepartment(mainview.getUserId())){
                   if(data[1].equals(jc.getSelectedItem())){
                       RRView.setDepartmentId(Integer.parseInt(data[0].toString()));
//                       IRView.refreshTableIssueStockReport(IRModel.getSalesList(IRView.getDepartmentId()));
                     
                   }
               }
           }
       }
       catch(Exception se){
           JOptionPane.showMessageDialog(RRView , se+"from ComboDepartmentListener"+getClass().getName());
       }
        }
        
    } 
}
