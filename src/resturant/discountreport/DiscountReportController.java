/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.discountreport;

import hotelmanagementsystem.MainFrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import report.discount.DiscountReport;

/**
 *
 * @author SUSHIL
 */
public class DiscountReportController {
      DiscountReportModel DRModel;
    DiscountReportView DRView;
    private MainFrameView mainview;
    public DiscountReportController(DiscountReportModel model,DiscountReportView view,MainFrameView main){
          DRModel  = model;
        DRView= view;
        mainview= main;
        /*
         * loading menu name in combi list
         */
       // DRView.setComboMenuName(DRModel.returnMenuName(DRModel.getMenuInfo()));
        
        DRView.addOkListener(new ComplimentaryListener());
        DRView.addCancelListener(new ComplimentaryListener());
        DRView.addPrintListener(new ComplimentaryListener());
        DRView.addSaveAsExcelListener(new ComplimentaryListener());
        DRView.addCancelReportListener(new ComplimentaryListener());
        /*
         * adding the menuitem listener
         */
//        DRView.addComboMenuNameSelectListener(new ComboMenuNameSelectListener());
//        DRView.addCheckIncludeAllListener(new CheckIncludeAllListener());
           DRView.addComboDepartmentListener(new ComboDepartmentListener());
        /*
         * for include all
         */
//        IRView.addCheckIncludeAllListener(new CheckIncludeAllListener());
        try{
         DRView.setComboDepartmentName(DRModel.returnMenuName(DRModel.getRespectiveDepartment(mainview.getUserId())));
         //if it has only one element select it order wise add select into it
            int combosize = DRView.returnComboDepartmentName().getModel().getSize();
            if(combosize >1){
                DRView.AddSelectInCombo(DRView.returnComboDepartmentName());
            }
            else{
                if(combosize == 1){
                DRView.returnComboDepartmentName().setSelectedIndex(0);
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
//                if(!DRView.getBooleanIncludeAll()){
//                    if(DRView.getMenuId().isEmpty()){
//                          JOptionPane.showMessageDialog(DRView.DailogReport,"Please Select  Menu Name ");
//                    return;
//                        }
//                    }
//                
                if(DRView.getStartDate() == null){
                     JOptionPane.showMessageDialog(DRView.DailogReport,"Please Select  Start Date ");
                    return;
                    
                }
                 if(DRView.getEndDate() == null){
                     JOptionPane.showMessageDialog(DRView.DailogReport,"Please Select  End Date ");
                    return;
                }  
//                 Date[] date = DRView.getComplimentaryReportDate();
//                 String title = "Discount Report";
////                 if(!DRView.getBooleanIncludeAll()){
////                     title += " \t of \t"+DRView.getComboMenuName();
////                 }
//                 DRView.setlblReportTitle(title);
//                 DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
//                 DRView.setlblStartDate(df.format(date[0]));
//                 DRView.setlblEndDate(df.format(date[1]));
//                DRView.refreshTableReport(DRModel.getDiscountList(date));
//                 if(DRView.getTableReport().getRowCount() <= 0){
//                 JOptionPane.showMessageDialog(DRView, "Record Not Found");
//                 return;
//                 }
//                
//                
//                 /*
//                  * calculating the toal complimetary amount given
//                  */
//                 BigDecimal TotalDiscountAmount = BigDecimal.ZERO;
//                 for(int i=0;i< DRView.getTableReport().getRowCount();i++){
//                     TotalDiscountAmount  = TotalDiscountAmount.add(new BigDecimal(DRView.getTableReport().getValueAt(i, 3).toString()));
//                 }
//                DRView.getTableReport().addRow(new Object[]{"Total Discount Amount",null,null,TotalDiscountAmount});
//               
                 String report = "DiscountReport.jrxml";
                 String title = "Discount Report";
                 DiscountReport discount = new DiscountReport(DRView.getReportParam(),report,title);
                DRView.setVisible(false);
//                DRView.DailogReport.pack();
//                DRView.DailogReport.setVisible(true);
                 
            
            }
           if(e.getActionCommand().equalsIgnoreCase("ReportCancel")){
               DRView.DailogReport.setVisible(false);
               DRView.setVisible(true);
           }
           if(e.getActionCommand().equalsIgnoreCase("Cancel")){
               DRView.setVisible(false);
           }
        }
        catch(Exception ce){
            JOptionPane.showMessageDialog(DRView,ce+ "From DiscountListener");
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
               DRView.setDepartmentId(0);
             
              
           }
           else{
               for(Object[] data:DRModel.getRespectiveDepartment(mainview.getUserId())){
                   if(data[1].equals(jc.getSelectedItem())){
                       DRView.setDepartmentId(Integer.parseInt(data[0].toString()));
//                       IRView.refreshTableIssueStockReport(IRModel.getSalesList(IRView.getDepartmentId()));
                     
                   }
               }
           }
       }
       catch(Exception se){
           JOptionPane.showMessageDialog(DRView , se+"from ComboDepartmentListener"+getClass().getName());
       }
        }
        
    }
}
