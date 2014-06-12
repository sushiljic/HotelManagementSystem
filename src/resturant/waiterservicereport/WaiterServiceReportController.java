/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.waiterservicereport;

import hotelmanagementsystem.MainFrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
//import report.waiterServiceReport.AllWaiterServiceReport;
import report.waiterServiceReport.WaiterServiceReport;

/**
 *
 * @author SUSHIL
 */
public class WaiterServiceReportController {
      WaiterServiceReportModel WSRModel;
    WaiterServiceReportView WSRView;
    private MainFrameView mainview;
    public WaiterServiceReportController(WaiterServiceReportModel model,WaiterServiceReportView view,MainFrameView main){
          WSRModel  = model;
        WSRView= view;
        mainview = main;
        /*
         * loading menu name in combi list
         */
       // WSRView.setComboMenuName(WSRModel.returnMenuName(WSRModel.getCustomerInfo()));
      
        WSRView.addOkListener(new WaiterServiceListener());
        WSRView.addCancelListener(new WaiterServiceListener());
        WSRView.addPrintListener(new WaiterServiceListener());
        WSRView.addSaveAsExcelListener(new WaiterServiceListener());
        WSRView.addCancelReportListener(new WaiterServiceListener());
        /*
         * adding the menuitem listener
         */
        WSRView.addComboWaiterNameSelectListener(new ComboWaiterNameSelectListener());
        WSRView.addCheckIncludeAllListener(new CheckIncludeAllListener());
        WSRView.addComboDepartmentListener(new ComboDepartmentListener());
          try{
        WSRView.setComboDepartmentName(WSRModel.returnWaiterName(WSRModel.getRespectiveDepartment(mainview.getUserId())));
        //if it has only one element select it order wise add select into it
            int combosize = WSRView.returnComboDepartmentName().getModel().getSize();
            if(combosize >1){
                WSRView.AddSelectInCombo(WSRView.returnComboDepartmentName());
            }
            else{
                if(combosize == 1){
                WSRView.returnComboDepartmentName().setSelectedIndex(0);
                }
            }
        WSRView.setComboWaiterName(WSRModel.returnWaiterName(WSRModel.getWaiterInfoObject()));
        WSRView.AddSelectInCombo(WSRView.returnComboWaiterName());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(view, e+"From WaiterServiceReportControlller");
        }
    }
     public class WaiterServiceListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("Ok")){
                if(WSRView.getDepartmentId() == 0){
                   JOptionPane.showMessageDialog(WSRView.DailogReport,"Please Select  Department Name ");
                    return;  
                }
                if(!WSRView.getBooleanIncludeAll()){
                    if(WSRView.getWaiterId()==0){
                          JOptionPane.showMessageDialog(WSRView.DailogReport,"Please Select  Menu Name ");
                    return;
                        }
                    }
                
                if(WSRView.getStartDate() == null){
                     JOptionPane.showMessageDialog(WSRView.DailogReport,"Please Select  Start Date ");
                    return;
                    
                }
                 if(WSRView.getEndDate() == null){
                     JOptionPane.showMessageDialog(WSRView.DailogReport,"Please Select  End Date ");
                    return;
                }  
//                 Date[] date = WSRView.getWaiterServiceReportDate();
//                 String title = "Service Report";
//                 if(!WSRView.getBooleanIncludeAll()){
//                     title += " \t of \t"+WSRView.getComboWaiterName();
//                 }
//                 WSRView.setlblReportTitle(title);
//                 DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
//                 WSRView.setlblStartDate(df.format(date[0]));
//                 WSRView.setlblEndDate(df.format(date[1]));
                 /*
                  * two state whethe it is include all or particula
                  */
                 if(WSRView.getBooleanIncludeAll()){
//                      WSRView.refreshTableReport(WSRModel.getWaiterServiceList(null,date, Boolean.TRUE));
                      //AllWaiterServiceReport wr = new AllWaiterServiceReport(WSRView.getReportParams());
//                      if(WSRView.getTableReport().getRowCount()<=0){
//                          
//                          JOptionPane.showMessageDialog(WSRView, "Record Not Found");
//                          return;
//                      }
                     String report = "AllWaiterServiceReport.jrxml";
                     String title = "Waiter Service Report";
                     WaiterServiceReport wr = new WaiterServiceReport(WSRView.getReportParams(),report,title);
                    WSRView.setVisible(false);
//                    WSRView.DailogReport.pack();
//                     WSRView.DailogReport.setVisible(true);
                     
                 }
                 else{
//                  WSRView.refreshTableReport(WSRModel.getWaiterServiceList(WSRView.getWaiterId(),date, false));
//                    if(WSRView.getTableReport().getRowCount()<=0){
//                          JOptionPane.showMessageDialog(WSRView, "Record Not Found");
//                          return;
//                      }
                     String report = "WaiterServiceReport.jrxml";
                     String title = "Waiter Service Report";
                    WaiterServiceReport wr = new WaiterServiceReport(WSRView.getReportParam(),report,title);
                   WSRView.setVisible(false);
//                    WSRView.DailogReport.pack();
//                     WSRView.DailogReport.setVisible(true);
                 }
                 /*
                  * calculating the toal complimetary amount given
                  */
//                 BigDecimal TotalComplimentaryAmount = BigDecimal.ZERO;
//                 for(int i=0;i< WSRView.getTableReport().getRowCount();i++){
//                     TotalComplimentaryAmount  = TotalComplimentaryAmount.add(new BigDecimal(WSRView.getTableReport().getValueAt(i, 3).toString()));
//                 }
//                WSRView.getTableReport().addRow(new Object[]{"Total Amount Served",null,null,TotalComplimentaryAmount,null});
                 
            
            }
           if(e.getActionCommand().equalsIgnoreCase("ReportCancel")){
               WSRView.DailogReport.setVisible(false);
               WSRView.setVisible(true);
           }
           if(e.getActionCommand().equalsIgnoreCase("Cancel")){
               WSRView.setVisible(false);
           }
        }
        catch(Exception ce){
            JOptionPane.showMessageDialog(WSRView,ce+ "From WaiterServiceListener");
        }
        }
       
   } 
   public class ComboWaiterNameSelectListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       try{
           if(e.getActionCommand().equalsIgnoreCase("ComboWaiterName"))
           {
           Object[] item = null;
                JComboBox comboMenu = (JComboBox) e.getSource();
                if(comboMenu.getSelectedIndex() == 0){
                    WSRView.setWaiterId(0);
                }
                else{
               for (Object[] WaiterInfo : WSRModel.WaiterInfo) {
                   if (comboMenu.getSelectedItem().equals(WaiterInfo[1])) {
                       item = WaiterInfo;
                       break;
                   }
               }
                WSRView.setWaiterId(Integer.parseInt(item[0].toString()));
                }
               // System.out.println(CRView.getMenuId());
           }
       }
       catch(Exception cme){
           JOptionPane.showMessageDialog(WSRView, cme+"From ComboWaiter"
                   + "NameSelectListener");
       }
        }
       
   }
    public class CheckIncludeAllListener implements ItemListener{

      

        @Override
        public void itemStateChanged(ItemEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getStateChange() == 1){
            WSRView.comboWaiterName.setEnabled(false);
            
        }
        else{
            WSRView.comboWaiterName.setEnabled(true);
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
               WSRView.setDepartmentId(0);
             
              
           }
           else{
               for(Object[] data:WSRModel.getRespectiveDepartment(mainview.getUserId())){
                   if(data[1].equals(jc.getSelectedItem())){
                       WSRView.setDepartmentId(Integer.parseInt(data[0].toString()));
//                       IRView.refreshTableIssueStockReport(IRModel.getSalesList(IRView.getDepartmentId()));
                     
                   }
               }
           }
       }
       catch(Exception se){
           JOptionPane.showMessageDialog(WSRView , se+"from ComboDepartmentListener"+getClass().getName());
       }
        }
        
    }
}
