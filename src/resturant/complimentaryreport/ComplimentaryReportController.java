/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.complimentaryreport;

import hotelmanagementsystem.MainFrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
//import report.complementary.AllComplementaryReport;
import report.complementary.ComplementaryReport;

/**
 *
 * @author SUSHIL
 */
public class ComplimentaryReportController {
      ComplimentaryReportModel CRModel;
    ComplimentaryReportView CRView;
    private MainFrameView mainview;
    public ComplimentaryReportController(ComplimentaryReportModel model,ComplimentaryReportView view,MainFrameView main){
          CRModel  = model;
        CRView= view;
        mainview = main;
    
        CRView.addOkListener(new ComplimentaryListener());
        CRView.addCancelListener(new ComplimentaryListener());
        CRView.addPrintListener(new ComplimentaryListener());
        CRView.addSaveAsExcelListener(new ComplimentaryListener());
        CRView.addCancelReportListener(new ComplimentaryListener());
        /*
         * adding the menuitem listener
         */
        CRView.addComboMenuNameSelectListener(new ComboMenuNameSelectListener());
        CRView.addCheckIncludeAllListener(new CheckIncludeAllListener());
            CRView.addComboDepartmentListener(new ComboDepartmentListener());
         try{
         CRView.setComboDepartmentName(CRModel.returnMenuName(CRModel.getRespectiveDepartment(mainview.getUserId())));
         //if it has only one element select it order wise add select into it
            int combosize = CRView.returnComboDepartmentName().getModel().getSize();
            if(combosize >1){
                CRView.AddSelectInCombo(CRView.returnComboDepartmentName());
            }
            else{
                if(combosize == 1){
                CRView.returnComboDepartmentName().setSelectedIndex(0);
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
                if(CRView.getDepartmentId() == 0){
                    JOptionPane.showMessageDialog(CRView.DailogReport,"Please Select  Department Name. ");
                    return;   
                }
                if(!CRView.getBooleanIncludeAll()){
                    if(CRView.getMenuId() == 0){
                          JOptionPane.showMessageDialog(CRView.DailogReport,"Please Select  Menu Name ");
                    return;
                        }
                    }
                
                if(CRView.getStartDate() == null){
                     JOptionPane.showMessageDialog(CRView.DailogReport,"Please Select  Start Date ");
                    return;
                    
                }
                 if(CRView.getEndDate() == null){
                     JOptionPane.showMessageDialog(CRView.DailogReport,"Please Select  End Date ");
                    return;
                }  
//                 Date[] date = CRView.getComplimentaryReportDate();
//                 String title = "Complimentary Report";
//                 if(!CRView.getBooleanIncludeAll()){
//                     title += " \t of \t"+CRView.getComboMenuName();
//                 }
//                 CRView.setlblReportTitle(title);
//                 DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
//                 CRView.setlblStartDate(df.format(date[0]));
//                 CRView.setlblEndDate(df.format(date[1]));
//                 /*
//                  * two state whethe it is include all or particula
//                  */
                 if(CRView.getBooleanIncludeAll()){
//                      CRView.refreshTableReport(CRModel.getComplimentaryList(null,date, Boolean.TRUE));
//                      if(CRView.getTableReport().getRowCount()<=0){
//                          JOptionPane.showMessageDialog(CRView, "Record Not Found");
//                          return;
//                      }
                    CRView.setVisible(false);
                    //CRView.DailogReport.pack();
                     //CRView.DailogReport.setVisible(true);
                     
                     //display all complementary report
                    String report = "AllComplementaryReport.jrxml";
                    String title = "All ComplementaryReport";
                     ComplementaryReport r = new ComplementaryReport(CRView.getReportParams(),report, title);
                     
                 }
                 else{
//                  CRView.refreshTableReport(CRModel.getComplimentaryList(CRView.getMenuId(),date, false));
//                    if(CRView.getTableReport().getRowCount()<=0){
//                          JOptionPane.showMessageDialog(CRView, "Record Not Found");
//                          return;
//                      }
                   CRView.setVisible(false);
                   // CRView.DailogReport.pack();
                    // CRView.DailogReport.setVisible(true);
                   String report = "ComplementaryReport.jrxml";
                   String title = "Complementary Report";
                     ComplementaryReport r = new ComplementaryReport(CRView.getReportParam(),report,title);
                     
                 }
                 /*
                  * calculating the toal complimetary amount given
                  */
//                 BigDecimal TotalComplimentaryAmount = BigDecimal.ZERO;
//                 for(int i=0;i< CRView.getTableReport().getRowCount();i++){
//                     TotalComplimentaryAmount  = TotalComplimentaryAmount.add(new BigDecimal(CRView.getTableReport().getValueAt(i, 7).toString()));
//                 }
//                CRView.getTableReport().addRow(new Object[]{"Total Amount",null,null,null,null,null,null,TotalComplimentaryAmount});
//                 
            
            }
           if(e.getActionCommand().equalsIgnoreCase("ReportCancel")){
               CRView.DailogReport.setVisible(false);
               CRView.setVisible(true);
           }
           if(e.getActionCommand().equalsIgnoreCase("Cancel")){
               CRView.setVisible(false);
           }
        }
        catch(Exception ce){
            JOptionPane.showMessageDialog(CRView,ce+ "From ComplimentaryListener");
        }
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
                    CRView.setMenuId(0);
                }
                else{
               for (Object[] MenuInfo : CRModel.MenuInfo) {
                   if (comboMenu.getSelectedItem().equals(MenuInfo[1])) {
                       item = MenuInfo;
                       break;
                   }
               }
                CRView.setMenuId(Integer.parseInt(item[0].toString()));
                }
               // System.out.println(CRView.getMenuId());
           }
       }
       catch(Exception cme){
           JOptionPane.showMessageDialog(CRView, cme+"From ComboMenuNameSelectListener");
       }
        }
       
   }
    public class CheckIncludeAllListener implements ItemListener{

      

        @Override
        public void itemStateChanged(ItemEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getStateChange() == 1){
            CRView.comboMenuName.setEnabled(false);
            
        }
        else{
            CRView.comboMenuName.setEnabled(true);
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
               CRView.setDepartmentId(0);
               CRView.setComboMenuName(new String[]{});
              
           }
           else{
               for(Object[] data:CRModel.getRespectiveDepartment(mainview.getUserId())){
                   if(data[1].equals(jc.getSelectedItem())){
                       CRView.setDepartmentId(Integer.parseInt(data[0].toString()));
//                       IRView.refreshTableIssueStockReport(IRModel.getSalesList(IRView.getDepartmentId()));
                        CRView.setComboMenuName(CRModel.returnMenuName(CRModel.getMenuInfo(CRView.getDepartmentId())));
                        CRView.AddSelectInCombo(CRView.returnMenuName());
                   }
               }
           }
       }
       catch(Exception se){
           JOptionPane.showMessageDialog(CRView , se+"from ComboDepartmentListener"+getClass().getName());
       }
        }
        
    }
}
