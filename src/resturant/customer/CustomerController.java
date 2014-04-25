/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.customer;

import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import reusableClass.DisplayMessages;

/**
 *
 * @author SUSHIL
 */
public class CustomerController {
    
    public CustomerView cview = new CustomerView(new JFrame(),true);
    private CustomerModel cmodel = new CustomerModel();
    private String InitialCustomerName = new String();
    
    public CustomerController(CustomerModel model,CustomerView view){
        cview = view;
        cmodel = model;
        
       // cview.setVisible(true);
        cview.refreshJtableCustomerInfo(cmodel.getTableModelCustomerInfo());
        
        cview.addAddCustomerListener(new CustomerCrudListener());
        cview.addEditCustomerListener(new CustomerCrudListener());
        cview.addDeleteCustomerListener(new CustomerCrudListener());
        cview.addCancelCustomerListener(new CustomerCrudListener());
        cview.addCustomerListSelectionListener(new CustomerInfoListSelectionListener(cview));
        cview.addShortcutForCustomer(new ShortcutForCustomer());
      
//        cview.addWindowListener(new WindowAdapter(){
//            @Override
//            public void windowClosing(WindowEvent e){
//                JOptionPane.showMessageDialog(cview, "Jagad Guru Kripalu maharaj ki jaya ho");
//            }
//        });
        
    }
    
    public class CustomerCrudListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("Add")){
                
                if(cview.getCustomerName().isEmpty()){
                    JOptionPane.showMessageDialog(cview, "Customer Name is Empty.");
                    return;
                }
                if(cmodel.checkExistingName(cview.getCustomerName())){
                    JOptionPane.showMessageDialog(cview, "Duplicate Entry.Try Another Name.");
                    return;
                }
               cmodel.AddCustomer(cview.getCustomerInfo());
               cview.ClearAll();
               
               cview.refreshJtableCustomerInfo(cmodel.getTableModelCustomerInfo());
            }
            else if(e.getActionCommand().equalsIgnoreCase("Edit")){
//                System.out.println("wala");
                        if(cview.getCustomerId() == 0){
                        JOptionPane.showMessageDialog(cview, "Please Select From the list to Edit");
                        return;
                    }
//                        System.out.println(cview.getCustomerId());
//                        System.out.println("walao");
                    if(cview.getCustomerName().isEmpty()){
                        JOptionPane.showMessageDialog(cview, "Customer Name is Empty.");
                        return;
                    }
                    if(!InitialCustomerName.equalsIgnoreCase(cview.getCustomerName())){
                          if(cmodel.checkExistingName(cview.getCustomerName())){
                            JOptionPane.showMessageDialog(cview, "Duplicate Entry.Try Another Name.");
                            return;
                        }
                    }
                       int choice = JOptionPane.showConfirmDialog(cview, "DO You Want To Edit Customer", "Customer Add Window", JOptionPane.YES_NO_OPTION);
                    if(choice == JOptionPane.YES_OPTION ){
// System.out.println("wala1");
                    cmodel.EditCustomer(cview.getCustomerInfo());
//                     System.out.println("wala2");
                     cview.ClearAll();
                     cview.refreshJtableCustomerInfo(cmodel.getTableModelCustomerInfo());
                    cview.setEditEditableFalse();
                    cview.setDeleteEditableFalse();
                    }
        }
            if(e.getActionCommand().equalsIgnoreCase("Delete")){
                    if(cview.getCustomerId() == 0){
                        JOptionPane.showMessageDialog(cview, "Please Select From the list to Delete");
                        return;
                    }
                    int choice = JOptionPane.showConfirmDialog(cview, "DO You Want To Add Customer", "Customer Add Window", JOptionPane.YES_NO_OPTION);
                    if(choice == JOptionPane.YES_OPTION ){
                        cmodel.DeleteCustomer(cview.getCustomerInfo());
                        cview.ClearAll();
                        cview.refreshJtableCustomerInfo(cmodel.getTableModelCustomerInfo());
                          cview.setEditEditableFalse();
            cview.setDeleteEditableFalse();
                    }
            }
            else if (e.getActionCommand().equalsIgnoreCase("Cancel")){
                 cview.ClearAll();
                 cview.setAddEditableTrue();
                 cview.setEditEditableFalse();
                 cview.setDeleteEditableFalse();
            }
        }
        catch(Exception cce){
            JOptionPane.showMessageDialog(cview, cce+"From CustomerCrudListener");
        }
        }
        
    }
    
    public class CustomerInfoListSelectionListener implements ListSelectionListener{
        CustomerView theview = new CustomerView(new JFrame(),true);
        JTable table;
            
        public CustomerInfoListSelectionListener(CustomerView view){
            theview = view;
            table = theview.tblCustomerInfo;
        }
        @Override
        public void valueChanged(ListSelectionEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getValueIsAdjusting()){
                return;
            }
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            if(lsm.isSelectionEmpty()){
               
                
            }
            else{
          ListSelectionModel      listmodel = cview.tblCustomerInfo.getSelectionModel();
                int lead = listmodel.getLeadSelectionIndex();
                 cview.setCustomerInfo(displayRowValues(lead));
                 InitialCustomerName = cview.getCustomerName();
                 cview.setEditEditableTrue();
                 cview.setDeleteEditableTrue();
                 cview.setAddEditableFalse();
                 
                
            }
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(cview, se+"from CustomerInfoListSelectionListener");
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
    public class ShortcutForCustomer implements KeyEventDispatcher{

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
        try{
            if(cview.isActive()){
                if(e.getKeyCode() == KeyEvent.VK_INSERT){
                    //setfocus on txtcustomername
                    cview.getTxtCustomerName().requestFocusInWindow();
                }
                if(e.getKeyCode() == KeyEvent.VK_HOME){
                    cview.getTblCustomerInfo().requestFocusInWindow();
                }
            }
            
        }
        catch(Exception se){
            DisplayMessages.displayError(cview, se.getMessage()+"from shortcutforcustomer"+getClass().getName(), InitialCustomerName);
        }
        return false;
        }
        
    }
}
