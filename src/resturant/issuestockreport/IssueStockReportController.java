/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.issuestockreport;

import hotelmanagementsystem.MainFrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import report.issueStock.IssueStockReport;

/**
 *
 * @author SUSHIL
 */
public class IssueStockReportController {
    IssueStockReportModel IRModel;
    IssueStockReportView IRView;
    private MainFrameView mainview;
     public Date time;
    
    public IssueStockReportController(IssueStockReportModel model,IssueStockReportView view,MainFrameView main){
        IRModel= model;
        IRView = view;
        mainview = main; 
        /*
         * fetching the data into the combo box
         */
   
        IRView.addCancelReportListener(new SalesReportListener());
        IRView.addPrintListener(new SalesReportListener());
//        IRView.addSaveAsExcelListener(new SalesReportListener());
      
//        IRView.setComboRelativeUnitName(IRModel.returnUnitName(IRModel.getUnitInfo()));
        /*
         * listening for the listselection event
         */
        IRView.addListSelectionListener(new tblListSelectionListener(IRView));
        /*
         * adding combolistener for unitname
         */
        IRView.addComboUnitNameListener(new ComboUnitNameListener());
        IRView.addComboDepartmentNameListener(new ComboDepartmentListener());
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
            /*
         * loading the stock quantiy in the table
         */
//        IRView.refreshTableIssueStockReport(IRModel.getSalesList());  
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
           
            if(e.getActionCommand().equalsIgnoreCase("ReportCancel")){
              
                IRView.dispose();
               
            }
            if(e.getActionCommand().equalsIgnoreCase("Print")){
              
                //IRView.dispose();
                //IssueStockReport stock = new IssueStockReport(Map new Map(),"","");
                //TerminalIssueStockReport stock = new TerminalIssueStockReport();
               
            }
            
        }
        catch(Exception ire){
            JOptionPane.showMessageDialog(IRView, "From IssueReportListener ");
        }
        }
        
    }
    public class tblListSelectionListener implements ListSelectionListener{
         IssueStockReportView theview = new IssueStockReportView(new JFrame(), true);
         JTable table;
         public tblListSelectionListener(IssueStockReportView view){
             theview = view;
             table = theview.tblIssueStock;
         }
        

        @Override
        public void valueChanged(ListSelectionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       try{
           if(e.getValueIsAdjusting()){
               return;
           }
          ListSelectionModel lsm = table.getSelectionModel();
          if(lsm.isSelectionEmpty()){
              
          }
          else{
              int lead = lsm.getLeadSelectionIndex();
              String[] data = displayRowValues(lead);
              theview.setIssueStock(data);
              String unittype = theview.getTotalStockQuantity().replaceAll("[-,0-9,.]","");
//              System.out.println(unittype);
              theview.setComboRelativeUnitName(IRModel.returnUnitName(IRModel.getUnitInfo(unittype.trim())));
//               String unitname = data[3].toString().replaceAll("[0-9,.,-]", "");
//       theview.setComboRelativeUnitName(unitname);
              theview.AddSelectInCombo(theview.reutrnComboRelativeUnitName());
              theview.DialogIssueItem.pack();
               theview.DialogIssueItem.setModal(true);
              theview.DialogIssueItem.setVisible(true);
             
          }
           
       }
       catch(Exception le){
           JOptionPane.showMessageDialog(theview, le+"from tblListSelectionListener ");
       }
        
        }
         private String[] displayRowValues(int lead){
            int columns = table.getColumnCount();
            String[] st = new String[columns];
            for(int i=0;i<columns;i++){
                try{
                   Object o = table.getValueAt(lead,i);
                   st[i] = o.toString();
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(table, e+"for displayrowvalues");
                }
            }
            return st;
        }
        
   
        
        
    }
    public class ComboUnitNameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            Object[] item = null;
            if(e.getActionCommand().equalsIgnoreCase("ComboUnitNameListener")){
               JComboBox jc = (JComboBox)e.getSource();
               if(jc.getSelectedIndex() == 0){
                   IRView.setRelativeStockQuantity("");
                   return;
               }
                for (Object[] UnitInfo : IRModel.UnitInfo) {
                    if (UnitInfo[1].equals(jc.getSelectedItem())) {
                        item = UnitInfo;
                    }
                }
               /*
                * process the following data
                */
               String total = IRView.getTotalStockQuantity().replaceAll("[^0-9,.,-]","");
//               System.out.println(total);
               BigDecimal TotalQuantity = new BigDecimal(total);
//                System.out.println("wala");
               BigDecimal Relative = new BigDecimal(item[2].toString());
//               System.out.println(TotalQuantity +"\n"+ Relative);
//               System.out.println(TotalQuantity.divide(Relative));
               BigDecimal rt = TotalQuantity.divide(Relative).setScale(3, RoundingMode.HALF_UP);
//               System.out.println(TotalQuantity+"\n"+Relative+"\n"+rt);
               IRView.setRelativeStockQuantity(rt.toString());
            }
            
        }
        catch(Exception ce){
            JOptionPane.showMessageDialog(IRView, ce+"ComboUnitNameListener");
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
               IRView.refreshTableIssueStockReport(new DefaultTableModel());
           }
           else{
               for(Object[] data:IRModel.getRespectiveDepartment(mainview.getUserId())){
                   if(data[1].equals(jc.getSelectedItem())){
                       IRView.setDepartmentId(Integer.parseInt(data[0].toString()));
                       IRView.refreshTableIssueStockReport(IRModel.getSalesList(IRView.getDepartmentId()));
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
