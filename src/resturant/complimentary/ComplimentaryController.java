/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.complimentary;

import resturant.customer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author SUSHIL
 */
public class ComplimentaryController {
    
    public ComplimentaryView cview = new ComplimentaryView(new JFrame(),true);
    private ComplimentaryModel cmodel = new ComplimentaryModel();
    private String InitialCustomerName = new String();
    
    public ComplimentaryController(ComplimentaryModel model,ComplimentaryView view){
        cview = view;
        cmodel = model;
        
       // cview.setVisible(true);
        cview.refreshJtableCustomerInfo(cmodel.getTableModelComplimentaryInfo());
        
        cview.addAddCustomerListener(new CustomerCrudListener());
        cview.addEditCustomerListener(new CustomerCrudListener());
        cview.addDeleteCustomerListener(new CustomerCrudListener());
        cview.addCancelCustomerListener(new CustomerCrudListener());
        cview.addCustomerListSelectionListener(new CustomerInfoListSelectionListener(cview));
      
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
                
                if(cview.getComplimentaryReason().isEmpty()){
                    JOptionPane.showMessageDialog(cview, "Complimentary Reason is Empty.");
                    return;
                }
               
               cmodel.AddComplimentary(cview.getCustomerInfo());
               cview.ClearAll();
               
               cview.refreshJtableCustomerInfo(cmodel.getTableModelComplimentaryInfo());
            }
            else if(e.getActionCommand().equalsIgnoreCase("Edit")){
//                System.out.println("wala");
                        if(cview.getComplimentaryId() == 0){
                        JOptionPane.showMessageDialog(cview, "Please Select From the list to Edit");
                        return;
                    }
//                        System.out.println(cview.getComplimentaryId());
//                        System.out.println("walao");
                    if(cview.getComplimentaryReason().isEmpty()){
                        JOptionPane.showMessageDialog(cview, "Complimentary Reason is Empty.");
                        return;
                    }
                 
                       int choice = JOptionPane.showConfirmDialog(cview, "DO You Want To Edit Complimentary", "Customer Add Window", JOptionPane.YES_NO_OPTION);
                    if(choice == JOptionPane.YES_OPTION ){
// System.out.println("wala1");
                    cmodel.EditComplimentary(cview.getCustomerInfo());
//                     System.out.println("wala2");
                     cview.ClearAll();
                     cview.refreshJtableCustomerInfo(cmodel.getTableModelComplimentaryInfo());
                    cview.setEditEditableFalse();
                    cview.setDeleteEditableFalse();
                    }
        }
            if(e.getActionCommand().equalsIgnoreCase("Delete")){
                    if(cview.getComplimentaryId() == 0){
                        JOptionPane.showMessageDialog(cview, "Please Select From the list to Delete");
                        return;
                    }
                    int choice = JOptionPane.showConfirmDialog(cview, "DO You Want To Delete Complimentary", "Complimentary Add Window", JOptionPane.YES_NO_OPTION);
                    if(choice == JOptionPane.YES_OPTION ){
                        cmodel.DeleteCustomer(cview.getCustomerInfo());
                        cview.ClearAll();
                        cview.refreshJtableCustomerInfo(cmodel.getTableModelComplimentaryInfo());
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
        ComplimentaryView theview = new ComplimentaryView(new JFrame(),true);
        JTable table;
            
        public CustomerInfoListSelectionListener(ComplimentaryView view){
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
                 InitialCustomerName = cview.getComplimentaryReason();
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
}
