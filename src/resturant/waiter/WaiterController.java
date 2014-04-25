/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.waiter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import reusableClass.Function;

/**
 *
 * @author SUSHIL
 */
public class WaiterController {
    private WaiterView wview = new WaiterView(new JFrame(),true);
    private WaiterModel wmodel = new WaiterModel();
    private String InitialWaiterName = new String();
     private String InitialDesignationName = new String();
    public WaiterController(WaiterModel model, WaiterView view){
        wview  = view;
        wmodel = model;
        
        wview.addAddWaiterListener(new WaiterCrudListener());
        wview.addEditWaiterListener(new WaiterCrudListener());
        wview.addDeleteWaiterListener(new WaiterCrudListener());
        wview.addCancelWaiterListener(new WaiterCrudListener());
        wview.addComboDesignationListener(new ComboDesignationListener());
        wview.addListSelectionListener(new ReturnListSelectionListener(wview));
        //designation
       wview.addAddDesignationListener(new DesignationCrudListener());
       wview.addEditDesignationListener(new DesignationCrudListener() );
       wview.addDeleteDesignationListener(new DesignationCrudListener());
       wview.addCancelDesignationListener(new DesignationCrudListener());
       wview.addDesignationListSelectionListener(new ReturnDesignationListSelectionListener(wview));
       wview.addTabbedPaneListener(new TabbedChangeListener());
       try{
         wview.refreshJTableWaiterInfo(wmodel.getTableModelWaiterInfo());
         wview.refreshJTableDesignationInfo(wmodel.getTableModelDesignationInfo());
         
       }
       catch(Exception se){
           JOptionPane.showMessageDialog(view, se+"from constructor"+getClass().getName());
       }
    }
    public class WaiterCrudListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("Add")){
               /* if(wview.getWaiterId().isEmpty()){
                    JOptionPane.showMessageDialog(wview, "Pl");
                }*/
                if(wview.getWaiterName().isEmpty()){
                     JOptionPane.showMessageDialog(wview, "Waiter Name is Empty");
                     return;
                }
                  if(wmodel.checkExistingName(wview.getWaiterName())){
                        JOptionPane.showMessageDialog(wview, "Duplicate Name.Try With Another Name");
                        return;
                    }
               if(wview.getStaffDesignationId() == 0){
                  JOptionPane.showMessageDialog(wview, "Please Select the Designation");
                        return; 
               }
               wmodel.AddWaiter(wview.getTableInfo());
              wview.refreshJTableWaiterInfo(wmodel.getTableModelWaiterInfo());
               wview.clearAllWaiterInfo();
               wview.setAddVisibleTrue();
                 wview.setEditVisibleFalse();
                    wview.setDeleteVisibleFalse();
               
            }
            if(e.getActionCommand().equalsIgnoreCase("Edit")){
                if(wview.getWaiterId() == 0){
                   JOptionPane.showMessageDialog(wview, "Select From Table First");
                        return;     
                }
               if(wview.getWaiterName().isEmpty()){
                JOptionPane.showMessageDialog(wview, "WaiterName is Empty");
                        return;   
               }
                if(!InitialWaiterName.equalsIgnoreCase(wview.getWaiterName())){
                    if(wmodel.checkExistingName(wview.getWaiterName())){
                        JOptionPane.showMessageDialog(wview, "Duplicate Name.Try With Another Name");
                        return;
                    }
                }
                if(wview.getStaffDesignationId() == 0){
                  JOptionPane.showMessageDialog(wview, "Please Select the Designation");
                        return; 
               }
                int choice = JOptionPane.showConfirmDialog(wview, "Do You Want to Edit?","Edit Window",JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION){
                    wmodel.EditWaiter(wview.getTableInfo());
                   // JOptionPane.showMessageDialog(wview, "Waiter Edited Successfully");
                    wview.refreshJTableWaiterInfo(wmodel.getTableModelWaiterInfo());
                    wview.clearAllWaiterInfo();
                    wview.setEditVisibleFalse();
                    wview.setDeleteVisibleFalse();
                     wview.setAddVisibleTrue();
                }
                
            }
            if(e.getActionCommand().equalsIgnoreCase("Delete")){
                  if(wview.getWaiterId() == 0){
                   JOptionPane.showMessageDialog(wview, "Select From Table First");
                        return;     
                }
                  int choice = JOptionPane.showConfirmDialog(wview, "Do You Want to Delete", "Delete Window", JOptionPane.YES_NO_OPTION);
                  if(choice == JOptionPane.YES_OPTION){
                 wmodel.DeleteWaiter(wview.getTableInfo());
                 wview.refreshJTableWaiterInfo(wmodel.getTableModelWaiterInfo());
                 wview.clearAllWaiterInfo();
                 wview.setEditVisibleFalse();
                 wview.setDeleteVisibleFalse();
                  wview.setAddVisibleTrue();
                  }
                
            }
            if(e.getActionCommand().equalsIgnoreCase("Cancel")){
                 wview.clearAllWaiterInfo();
                 wview.setAddVisibleTrue();
                 wview.setEditVisibleFalse();
                 wview.setDeleteVisibleFalse();
                
            }
                
        }
        catch(Exception ce){
            JOptionPane.showMessageDialog(wview, "From WaiterCrudListener");
        }
        }
        
    }
    public class ComboDesignationListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            JComboBox jc = (JComboBox)e.getSource();
            if(jc.getSelectedIndex() == 0){
                wview.setStaffDesignationId(0);
            }
            else{
                for(Object[] dt:wmodel.getDesignationInfo()){
                    if(dt[1].equals(jc.getSelectedItem())){
                        wview.setStaffDesignationId(Integer.parseInt(dt[0].toString()));
                        break;
                    }
                }
            }
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(wview, se+"from ComboDesignationListener"+getClass().getName());
        }
        }
        
    }
     public class DesignationCrudListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("Add")){
               /* if(wview.getWaiterId().isEmpty()){
                    JOptionPane.showMessageDialog(wview, "Pl");
                }*/
                if(wview.getDesignationTitle().isEmpty()){
                     JOptionPane.showMessageDialog(wview, "Designation Name is Empty");
                     return;
                }
                  if(wmodel.checkExistingDesignation(wview.getDesignationTitle())){
                        JOptionPane.showMessageDialog(wview, "Duplicate Name.Try With Another Name");
                        return;
                    }
               wmodel.AddDesignation(wview.getDesignationInfo());
              wview.refreshJTableDesignationInfo(wmodel.getTableModelDesignationInfo());
               wview.clearAllDesignationInfo();
               
            }
            if(e.getActionCommand().equalsIgnoreCase("Edit")){
                if(wview.getDesignationId() == 0){
                   JOptionPane.showMessageDialog(wview, "Select From Table First");
                        return;     
                }
               if(wview.getDesignationTitle().isEmpty()){
                JOptionPane.showMessageDialog(wview, "Designation Title is Empty");
                        return;   
               }
                if(!InitialDesignationName.equalsIgnoreCase(wview.getDesignationTitle())){
                    if(wmodel.checkExistingDesignation(wview.getDesignationTitle())){
                        JOptionPane.showMessageDialog(wview, "Duplicate Name.Try With Another Name");
                        return;
                    }
                }
                int choice = JOptionPane.showConfirmDialog(wview, "Do You Want to Edit?","Edit Window",JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION){
                    wmodel.EditDesignation(wview.getDesignationInfo());
                   // JOptionPane.showMessageDialog(wview, "Waiter Edited Successfully");
                    wview.refreshJTableDesignationInfo(wmodel.getTableModelDesignationInfo());
                    wview.clearAllDesignationInfo();
                    wview.setDesignationEdit(false);
                    wview.setDesignationDelete(false);
                     wview.setDesignationAdd(true);
                }
                
            }
            if(e.getActionCommand().equalsIgnoreCase("Delete")){
                  if(wview.getDesignationId() == 0){
                   JOptionPane.showMessageDialog(wview, "Select From Table First");
                        return;     
                }
                  int choice = JOptionPane.showConfirmDialog(wview, "Do You Want to Delete", "Delete Window", JOptionPane.YES_NO_OPTION);
                  if(choice == JOptionPane.YES_OPTION){
                 wmodel.DeleteDesignation(wview.getDesignationInfo());
                  wview.refreshJTableDesignationInfo(wmodel.getTableModelDesignationInfo());
                    wview.clearAllDesignationInfo();
                    wview.setDesignationEdit(false);
                    wview.setDesignationDelete(false);
                     wview.setDesignationAdd(true);
                  }
                
            }
            if(e.getActionCommand().equalsIgnoreCase("Cancel")){
                 wview.clearAllDesignationInfo();
                 wview.setDesignationAdd(true);
                 wview.setDesignationEdit(false);
                    wview.setDesignationDelete(false);
                
            }
                
        }
        catch(Exception ce){
            JOptionPane.showMessageDialog(wview, "From DesignationCrudListener");
        }
        }
        
    }
    public class ReturnListSelectionListener implements ListSelectionListener{
        WaiterView view = new WaiterView(new JFrame(),true);
        JTable table;
            public ReturnListSelectionListener(WaiterView view){
                this.view = view;
                table = this.view.tblWaiterInfo;
            }

        @Override
        public void valueChanged(ListSelectionEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getValueIsAdjusting()){
                return;
            }
            ListSelectionModel listModel = (ListSelectionModel)e.getSource();
            if(listModel.isSelectionEmpty()){
                
            }
            else{
                 ListSelectionModel lsm = table.getSelectionModel();
                 int lead = lsm.getLeadSelectionIndex();
                 wview.setTableInfo(displayRowValues(lead));
                 InitialWaiterName = wview.getWaiterName();
                 wview.setEditVisibleTrue();
                 wview.setDeleteVisibleTrue();
                 wview.setAddVisibleFalse();
            }
          //  if()
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(wview, se+"ReturnListSelectionListener");
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
     public class ReturnDesignationListSelectionListener implements ListSelectionListener{
        WaiterView view = new WaiterView(new JFrame(),true);
        JTable table;
            public ReturnDesignationListSelectionListener(WaiterView view){
                this.view = view;
                table = this.view.tblDesignation;
            }

        @Override
        public void valueChanged(ListSelectionEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getValueIsAdjusting()){
                return;
            }
            ListSelectionModel listModel = (ListSelectionModel)e.getSource();
            if(listModel.isSelectionEmpty()){
                
            }
            else{
                 ListSelectionModel lsm = table.getSelectionModel();
                 int lead = lsm.getLeadSelectionIndex();
                 wview.setDesignationInfo(displayRowValues(lead));
                 InitialDesignationName = wview.getDesignationTitle();
                 wview.setDesignationEdit(true);
                 wview.setDesignationDelete(true);
                 wview.setDesignationAdd(false);
            }
          //  if()
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(wview, se+"ReturnDesignationListSelectionListener"+getClass().getName());
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
    public class TabbedChangeListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        JTabbedPane tp = (JTabbedPane)e.getSource();
        if(tp.getSelectedIndex() == 1){
            wview.setComboDepartmentTitle(Function.returnSecondColumn(wmodel.getDesignationInfo()));
            Function.AddSelectInCombo(wview.returnComboDepartmentTitle());
        }
        }
        
    }
}
