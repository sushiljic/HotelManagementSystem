/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.voidreport;

import hotelmanagementsystem.MainFrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import report.voidBill.VoidBill;

/**
 *
 * @author SUSHIL
 */
public class VoidController {
      VoidModel TRModel;
    VoidView TRView;
    private MainFrameView mainview;
    public VoidController(VoidModel model,VoidView view,MainFrameView main){
          TRModel  = model;
        TRView= view;
        mainview = main;
        /*
         * loading menu name in combi list
         */
       // TRView.setComboMenuName(TRModel.returnMenuName(TRModel.getMenuInfo()));
        
        TRView.addOkListener(new TAXListener());
        TRView.addCancelListener(new TAXListener());
        TRView.addPrintListener(new TAXListener());
        TRView.addSaveAsExcelListener(new TAXListener());
        TRView.addCancelReportListener(new TAXListener());
        /*
         * adding the menuitem listener
         */
//        TRView.addComboMenuNameSelectListener(new ComboMenuNameSelectListener());
//        TRView.addCheckIncludeAllListener(new CheckIncludeAllListener());
           TRView.addComboDepartmentListener(new ComboDepartmentListener());
        /*
         * for include all
         */
//        IRView.addCheckIncludeAllListener(new CheckIncludeAllListener());
        try{
         TRView.setComboDepartmentName(TRModel.returnMenuName(TRModel.getRespectiveDepartment(mainview.getUserId())));
        //if it has only one element select it order wise add select into it
            int combosize = TRView.returnComboDepartmentName().getModel().getSize();
            if(combosize >1){
                TRView.AddSelectInCombo(TRView.returnComboDepartmentName());
            }
            else{
                if(combosize == 1){
                TRView.returnComboDepartmentName().setSelectedIndex(0);
                }
            }
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(view, se+"from constructor "+getClass().getName());
        }
    }
     public class TAXListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("Ok")){
//                if(!TRView.getBooleanIncludeAll()){
//                    if(TRView.getMenuId().isEmpty()){
//                          JOptionPane.showMessageDialog(TRView.DailogReport,"Please Select  Menu Name ");
//                    return;
//                        }
//                    }
              if(TRView.getDepartmentId() == 0){
                    JOptionPane.showMessageDialog(TRView.DailogReport,"Please Select Department Name. ");
                    return;  
              }  
                if(TRView.getStartDate() == null){
                     JOptionPane.showMessageDialog(TRView.DailogReport,"Please Select  Start Date ");
                    return;
                    
                }
                 if(TRView.getEndDate() == null){
                     JOptionPane.showMessageDialog(TRView.DailogReport,"Please Select  End Date ");
                    return;
                }  
                 Date[] date = TRView.getTAXReportDate();
                 String title = "TAX Report";
//                 if(!TRView.getBooleanIncludeAll()){
//                     title += " \t of \t"+TRView.getComboMenuName();
//                 }
//                 TRView.setlblReportTitle(title);
//                 DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
//                 /*
//                 testing for the ale 
//                 */
//                 SimpleDateFormat dfs =  new SimpleDateFormat("yyyy-mm-hh");
//                SimpleDateFormat dfe =  new SimpleDateFormat("yyyy-mm-hh");
//                Date dt = null;
//                try {
//                    System.out.println(dfs.format(date[0]));
//                    
//                    dt = dfe.parse(dfs.format(date[0]));
//                   
//                    JOptionPane.showMessageDialog(TRView,dt);
//                } catch (ParseException ex) {
//                    Logger.getLogger(VoidController.class.getName()).log(Level.SEVERE, null, ex);
//                }
////                Timestamp tp = new Timestamp(date[0].getTime());
////                if(dt instanceof Date ){
////                   JOptionPane.showMessageDialog(null,dt);
////                }
//                 TRView.setlblStartDate(df.format(date[0]));
//                 TRView.setlblEndDate(df.format(date[1]));
//                TRView.refreshTableReport(TRModel.getTAXList(date));
//                 if(TRView.getTableReport().getRowCount() <= 0){
//                 JOptionPane.showMessageDialog(TRView, "Record Not Found");
//                 return;
//                 }
                
                String report = "voidBill.jrxml";
                title = "Void Bill Report";
                VoidBill bill = new VoidBill(TRView.getReportParam(),report, title);
                 /*
                  * calculating the toal complimetary amount given
                  
                 BigDecimal TotalServiceChargeAmount = BigDecimal.ZERO;
                  BigDecimal TotalAmount = BigDecimal.ZERO;
                   BigDecimal TotalServiceChargePercentage = BigDecimal.ZERO;
                 for(int i=0;i< TRView.getTableReport().getRowCount();i++){
                     TotalAmount  = TotalAmount.add(new BigDecimal(TRView.getTableReport().getValueAt(i, 1).toString()));
                     TotalServiceChargeAmount  = TotalServiceChargeAmount.add(new BigDecimal(TRView.getTableReport().getValueAt(i, 2).toString()));
                     TotalServiceChargePercentage = TotalServiceChargeAmount.divide(TotalAmount).multiply(BigDecimal.valueOf(100));
                 }
                TRView.getTableReport().addRow(new Object[]{"Total",TotalAmount,TotalServiceChargeAmount,TotalServiceChargePercentage+"(Average %)"});
                
                TRView.setVisible(false);
                //TRView.DailogReport.pack();
                //TRView.DailogReport.setVisible(true);
                VoidBill r = new VoidBill(TRView.getReportParam(),null,null);
                 
                         */
            }
           if(e.getActionCommand().equalsIgnoreCase("ReportCancel")){
               TRView.DailogReport.setVisible(false);
               TRView.setVisible(true);
           }
           if(e.getActionCommand().equalsIgnoreCase("Cancel")){
               TRView.setVisible(false);
           }
        }
        catch(Exception ce){
            JOptionPane.showMessageDialog(TRView,ce+ "From TAXListener");
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
               TRView.setDepartmentId(0);
              
           }
           else{
               for(Object[] data:TRModel.getRespectiveDepartment(mainview.getUserId())){
                   if(data[1].equals(jc.getSelectedItem())){
                       TRView.setDepartmentId(Integer.parseInt(data[0].toString()));
//                       IRView.refreshTableIssueStockReport(IRModel.getSalesList(IRView.getDepartmentId()));
                   }
               }
           }
       }
       catch(Exception se){
           JOptionPane.showMessageDialog(TRView , se+"from ComboDepartmentListener"+getClass().getName());
       }
        }
        
    }
 
    
}
