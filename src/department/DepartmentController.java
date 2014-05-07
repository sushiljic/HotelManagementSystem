/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package department;

import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import reusableClass.DisplayMessages;
import reusableClass.Function;

/**
 *
 * @author SUSHIL
 */
public class DepartmentController {
    DepartmentView departmentView;
    DepartmentModel departmentModel;
    private  String InitialDepartmentName = new String();
    public  DepartmentController(DepartmentModel dm , DepartmentView dv){
        departmentModel = dm;
        departmentView = dv;
        try{
         departmentView.refreshDepartmentTable(departmentModel.getDepartmentTableModel());
         String[] PrinterStack = getAllPrinterName();
         departmentView.setComboDefaultPrinter(PrinterStack);
         Function.AddSelectInCombo(departmentView.returnComboDefaultPrinter());
         departmentView.setComboOrderPrinter(PrinterStack);
         Function.AddSelectInCombo(departmentView.returnComboOrderPrinter());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(departmentView, e+"from costructor "+ getClass().getName());
           
        }
        departmentView.addAddListener(new DepartmentCrudListener());
        departmentView.addEditListener(new DepartmentCrudListener());
        departmentView.addDeleteListener(new DepartmentCrudListener());
        departmentView.addCancelListener(new DepartmentCrudListener());
        departmentView.addRowSelectionListener(new TableListSelectionListener(departmentView));
        departmentView.addShortcutForDepartment(new ShortcutForDepartment());
        
    }
    //for retreiving all the printer name
    public String[] getAllPrinterName(){
         
         PrintService[] MetaPrinter = PrintServiceLookup.lookupPrintServices(null, null);
         ArrayList<String> PrinterNameStack = new ArrayList<>();
         //PrintService DefaultPrinter = PrintServiceLookup.lookupDefaultPrintService();
         for(PrintService printer:MetaPrinter){
             String printname = printer.getName();
             PrinterNameStack.add(printname);
         }
     return PrinterNameStack.toArray(new String[PrinterNameStack.size()]);
     } 
     public class DepartmentCrudListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("Add")){
                if(departmentView.getDepartmentName().isEmpty()){
                    JOptionPane.showMessageDialog(departmentView, "Department Name Cannot be Empty");
                    return;
                }
                if(departmentModel.checkExistingName(departmentView.getDepartmentName())){
                   JOptionPane.showMessageDialog(departmentView, "Department Name Cannot be Same.");
                    return;  
                }
                departmentModel.addDepartment(departmentView.getDepartmentInfo());
                departmentView.clearDeparmentInfo();
                departmentView.refreshDepartmentTable(departmentModel.getDepartmentTableModel());
            }
            if(e.getActionCommand().equalsIgnoreCase("Edit")){
               if(departmentView.getDepartmentid() == 0){
                   JOptionPane.showMessageDialog(departmentView, "Select the List from Table to Edit Department");
                    return;   
               }
                if(departmentView.getDepartmentName().isEmpty()){
                    JOptionPane.showMessageDialog(departmentView, "Department Name Cannot be Empty");
                    return;
                }
               if(!InitialDepartmentName.equalsIgnoreCase(departmentView.getDepartmentName())){
                   if(departmentModel.checkExistingName(departmentView.getDepartmentName())){
                       JOptionPane.showMessageDialog(departmentView, "Department Name Cannot be Same.");
                    return;    
                   }
               }
               int choice = JOptionPane.showConfirmDialog(departmentView, "Do You want to Edit Department?","Edit Department Window",JOptionPane.YES_NO_CANCEL_OPTION);
               if(choice == JOptionPane.YES_OPTION){
               departmentModel.editDepartment(departmentView.getDepartmentInfo());
               departmentView.refreshDepartmentTable(departmentModel.getDepartmentTableModel());
               departmentView.clearDeparmentInfo();
                departmentView.setBtnEditEnable(false);
               departmentView.setBtnDeleteEnable(false);
               }
            }
               if(e.getActionCommand().equalsIgnoreCase("Delete")){
                    if(departmentView.getDepartmentid() == 0){
                   JOptionPane.showMessageDialog(departmentView, "Select the List from Table to Delete Department");
                    return;   
               }
                     int choice = JOptionPane.showConfirmDialog(departmentView, "Do You want to Delete Department?\n This will Result on Deleting all the transaction related to the Department.","Delete Department Window",JOptionPane.YES_NO_CANCEL_OPTION);
               if(choice == JOptionPane.YES_OPTION){
               departmentModel.deleteDepartment(departmentView.getDepartmentInfo());
               departmentView.refreshDepartmentTable(departmentModel.getDepartmentTableModel());
               departmentView.clearDeparmentInfo();
                departmentView.setBtnEditEnable(false);
               departmentView.setBtnDeleteEnable(false);
               }
            
                    
               }
                  if(e.getActionCommand().equalsIgnoreCase("Cancel")){
               departmentView.refreshDepartmentTable(departmentModel.getDepartmentTableModel());
               departmentView.clearDeparmentInfo(); 
               departmentView.setBtnAddEnable(true);
               departmentView.setBtnEditEnable(false);
               departmentView.setBtnDeleteEnable(false);
               }
            
            
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(departmentView, se+"from DepartmentCrudListener "+getClass().getName());
        }
        }
    
}
     public class TableListSelectionListener implements ListSelectionListener{
         DepartmentView view ;
         JTable table;
         public TableListSelectionListener(DepartmentView view1){
             view= view1;
             table = view.tblDepartment;
         }

        @Override
        public void valueChanged(ListSelectionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            
          if(e.getValueIsAdjusting()){
              return;
          }  
          ListSelectionModel lsm = (ListSelectionModel)e.getSource();
          if(lsm.isSelectionEmpty()){
              
          }
          else{
              int lead = lsm.getLeadSelectionIndex();
              view.setDepartmentInfo(displayRowValues(lead));
              
              view.setBtnEditEnable(true);
              view.setBtnDeleteEnable(true);
              view.setBtnAddEnable(false);
          }
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(departmentView, se+"from ListSelectionListener "+getClass().getName());
        }
        }
          private String[] displayRowValues(int lead){
            int columns = table.getColumnCount();
            String[] st = new String[columns];
            for(int i = 0;i<columns;i++){
                
                Object o = table.getValueAt(lead, i);
         
                if(o!= null){
                st[i] = o.toString();
                
             }
             else{
                 st[i] = "";
              //  System.out.println("wala");
             }
           
            }
            return st;
            
        }
         
     }
      //class to hanlde shortcut for insert and home key
    public class ShortcutForDepartment implements KeyEventDispatcher{

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
        try{
            if(departmentView.isActive()){
                if(e.getKeyCode() == KeyEvent.VK_INSERT){
                    //setfocus on txtcustomername
                    departmentView.getTxtDepartmentName().requestFocusInWindow();
                }
                if(e.getKeyCode() == KeyEvent.VK_HOME){
                    departmentView.getTblDepartment().requestFocusInWindow();
                }
            }
            
        }
        catch(Exception se){
            DisplayMessages.displayError(departmentView, se.getMessage()+"from shortcutforDepartment"+getClass().getName(), "Error");
        }
        return false;
        }
        
    }
}
