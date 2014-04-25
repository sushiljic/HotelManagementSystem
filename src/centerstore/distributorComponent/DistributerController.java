/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.distributorComponent;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import reusableClass.DisplayMessages;


/**
 *
 * @author MinamRosh
 */
public class DistributerController {
    private final DistributerView distroView;
    private final DistributerModel distroModel;
    private String strDName;
    private String strDAdrs;
    private String strDPhone;
    private String strDEmail;
    private DefaultTableModel tModel;
    private ListSelectionModel listModel;
    private int lead;
    private int tblRow;
    
    public DistributerController(DistributerView view, DistributerModel model){
        
        //System.out.println("hahha");
        distroView = view;
        distroModel = model;
        //adding action listener to each button
        distroView.addListenerToAdd(new AddButtonListener());
        distroView.addListernerToUpdate(new UpdateButtonListener());
        distroView.addListenerToDelete(new DeleteButtonListener());
        distroView.addListenerToCancle(new CancelButtonListener());
        //distroView.addFocustListenerToName(new NameFocusOutListener());
        //distroView.addFocusListenerToPhone(new PhoneFocusListener());
        
        //distroView.addKeyListenerToBtn(new KeyListenerToBtn());
        distroView.addFocusToField(new AddFoucsListener());
        try{
            tModel = distroModel.getAllDistroInfo();
        }
        catch(NullPointerException nulEx){
            System.out.println(nulEx.getStackTrace());
        }
        distroView.setTable(tModel);
        
        distroView.addListenerToListModel(new RowListListener());
    }
    
    class AddFoucsListener implements FocusListener{
        @Override
        public void focusGained(FocusEvent e) {
            JTextField cmp = (JTextField) e.getComponent();
            cmp.setBackground(new Color(136,249,168));
            cmp.selectAll();
        }

        @Override
        public void focusLost(FocusEvent e) {
            Component cmp = e.getComponent();
            cmp.setBackground(Color.white);
        }
        
    }
    
    //event listeners for buttons
    class AddButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent aE){
            addFunc();
            
        }
    }
    
    class UpdateButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent aE){
            updateFunc();
        }
    }
    
    class DeleteButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent aE){
           //String[] info = distroView.getDistributorInfo();
            deleteFunc();
        }
    }
    
    class CancelButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent aE){
            //distroView.clearErr();
            distroView.clearTxtField();
            distroView.disableUpdate();
            distroView.enableAdd();
        }
    }
    
    /*focus listener for text fields
    class NameFocusOutListener extends FocusAdapter{
        public void focusLost(FocusEvent lEvt){
           //distroView.addFocustListenerToName(d);
            //String[] info = distroView.getDistributorInfo();
            //if(info[0].isEmpty()){
              //  distroView.setErrName();
            }
           
        }
        
        public void focusGained(FocusEvent iEvnt){
            
        }
        
   
    
    class PhoneFocusListener extends FocusAdapter{
        public void focusLost(FocusEvent iEvt){
            String[] info = distroView.getDistributorInfo();
        }   
    }
    */
    //listener for retriving rows only
    class RowListListener implements ListSelectionListener{
       @Override
        public void valueChanged(ListSelectionEvent lEvt) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           if(!lEvt.getValueIsAdjusting()){
               listModel = distroView.getListModel();
               if(listModel.isSelectionEmpty()){
                   return;
               }
               lead = listModel.getLeadSelectionIndex();
               JTable tbl = distroView.getTable();
               tblRow = tbl.getSelectedRow();
              // System.out.println("list selection"+tblRow);
              String[] rowList = distroView.getRowList(lead);
              distroView.showSelectedRow(rowList);
              
              distroView.enableUpdate();
              distroView.disableAdd();
           }
       }
        
    }
    
    /*
    class KeyListenerToBtn implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                if(e.getSource().toString().contains("Add")){
                    addFunc();
                }
                
                if(e.getSource().toString().contains("Update")){
                    updateFunc();
                }
                
                if(e.getSource().toString().contains("Delete")){
                    deleteFunc();
                }
                
                if(e.getSource().toString().contains("Cancle")){
                    distroView.clearTxtField();
                    distroView.disableUpdate();
                    distroView.enableAdd();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    */
    public void addFunc(){
        String[] dInfo = distroView.getDistributorInfo();
            if(dInfo[0].isEmpty()){
               // distroView.setErrName();
                JOptionPane.showMessageDialog(distroView,"Name Required !", null,JOptionPane.YES_OPTION);
                return;
            }
           if(dInfo[1].isEmpty()){
               //distroView.setErrAdrs();
               JOptionPane.showMessageDialog(distroView,"Address Required !", null,JOptionPane.ERROR_MESSAGE);
               return;
           }
           if(dInfo[2].isEmpty()){
               //distroView.setErrPhone();
               JOptionPane.showMessageDialog(distroView, "Phone Required !", null, JOptionPane.ERROR_MESSAGE);
               return;
           }
           if(dInfo[3].isEmpty()){
              // distroView.setErrEmail();
               dInfo[3] = null;
           }
           
           
           distroModel.addDistroInfo(dInfo);
                distroView.clearTxtField();
                
                tModel = distroModel.getAllDistroInfo();
                distroView.setTable(tModel);
                /*
           //check for duplication
           boolean status = distroModel.isDistroDuplicate(dInfo[0]);
           //System.out.println(status);
           if(status == true){
                distroModel.addDistroInfo(dInfo);
                distroView.clearTxtField();
                
                tModel = distroModel.getAllDistroInfo();
                distroView.setTable(tModel);
           }
           else{
               DisplayMessages.displayError(distroView, "Duplcate Distributer Name !","Error");
           }     
                        */
    }
    
    public void updateFunc(){
        String[] info = distroView.getDistributorInfo();
            
            int id = distroView.getDistroId();
           if(id == 0 || info[0].isEmpty() || info[1].isEmpty() || info[2].isEmpty()){
                JOptionPane.showMessageDialog(distroView, "Please Select An Item First !");
                return;
            }
            
            int status = JOptionPane.showConfirmDialog(distroView, "Are You Sure You Want to Update This Information ?", null, JOptionPane.YES_NO_OPTION);
            if(status == JOptionPane.YES_OPTION){
                distroModel.updateDistroInfo(id, info);
                tModel = distroModel.getAllDistroInfo();
                distroView.setTable(tModel);
                distroView.clearTxtField();
            }
            else{
            }
    }
    public void deleteFunc(){
            int id = distroView.getDistroId();
            if(id == 0){
                JOptionPane.showMessageDialog(distroView, "Please Select An Item First !");
                return;
            }
            
            int status = JOptionPane.showConfirmDialog(distroView, "Are You Sure You Want to Delete?", null, JOptionPane.YES_NO_OPTION);
            if(status == JOptionPane.YES_OPTION){
                distroView.deleteRowTable(tblRow);
                distroModel.deleteDistroInfo(id);
                distroView.clearTxtField();
                try{
                    //tModel = distroModel.getAllDistroInfo();
                    //distroView.setTable(tModel);
                }
                catch(Exception ex){
                    
                }
            }
            else{
                distroView.clearTxtField();
            }
    }
}
