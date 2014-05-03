/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserCrud;

import UserCreditial.ExecuteUserCreditial;
import hotelmanagementsystem.MainFrameView;
import java.awt.HeadlessException;
import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import reusableClass.CyptoAES;
import reusableClass.DisplayMessages;

/**
 *
 * @author SUSHIL
 */
public class UserCrudController {
   UserCrudView userCrudView;
   UserCrudModel userCrudModel;
   MainFrameView mainview;
   private String InitialUserName;
   public UserCrudController(UserCrudModel model,UserCrudView view,MainFrameView mainview){
       userCrudModel = model;
       userCrudView = view;
       this.mainview = mainview;
       userCrudView.addAddListener(new UserCrudListener());
       userCrudView.addEditListener(new UserCrudListener());
       userCrudView.addDeleteListener(new UserCrudListener());
       userCrudView.addCancelListener(new UserCrudListener());
       userCrudView.addChangePasswordListener(new UserCrudListener());
       userCrudView.addViewPasswordListener(new UserCrudListener());
       userCrudView.addShortcutForUserCrud(new ShortcutForUserCrud());
       try{
       userCrudView.refreshJTableUserInfo(userCrudModel.getTableModelCustomerInfo());
       userCrudView.setcomboDepartment(userCrudModel.returnSecondColumn(userCrudModel.getDepartmentInfo()));
       userCrudView.AddSelectInCombo(userCrudView.returncomboDepartment());
       //hide the change and view password button
//       userCrudView.setBtnChangePasswordVisible(false);
//       userCrudView.setBtnViewPasswordVisible(false);
       userCrudView.setPanelPasswordButtonVisible(false);
       }
       catch(Exception se){
           JOptionPane.showMessageDialog(userCrudView, se+" from Constructor "+ getClass().getName());
       }
       userCrudView.addUserListSelectionListener(new UserListSelectionListener(userCrudView));
      userCrudView.addcomboDepartmentListener(new DepartmentComboListener());
       
   }
   public class UserCrudListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      try{
          if(e.getActionCommand().equalsIgnoreCase("Add")){
            
              if(userCrudView.getUserName().isEmpty()){
                  JOptionPane.showMessageDialog(userCrudView, "UserName Can Not be Blank");
                  return;
              }
              if(userCrudModel.checkExistingName(userCrudView.getUserName())){
                  JOptionPane.showMessageDialog(userCrudView, "UserName Already Taken. Pleas Choose Another Name");
                  return;
              }
               if(userCrudView.getPassWord().length == 0){
                  JOptionPane.showMessageDialog(userCrudView, "Password Can Not be Blank");
                  return;
              }
               if(!Arrays.equals(userCrudView.getPassWord(), userCrudView.getConfirmPassword())){
                   JOptionPane.showMessageDialog(userCrudView, "Please Match the Password to Proced");
                   return;
               }
               if(userCrudView.returncomboDepartment().getSelectedIndex() == 0){
//                  int choice =  JOptionPane.showConfirmDialog(userCrudView,"Department Is Not Setup for this user.\n Do You Want to Continue?","Add User Window",JOptionPane.YES_NO_CANCEL_OPTION);
//                  if(choice != JOptionPane.YES_OPTION){
//                      return;
//                  }
                 JOptionPane.showMessageDialog(userCrudView, "Department Is Not Setup for this user.");
                 return;
               }
                 //setting the encrypted passowrd
               String ecyptedValue = CyptoAES.encrypt(new String(userCrudView.getPassWord()));
               userCrudView.setEncryptedPassword(ecyptedValue);
               
               userCrudModel.AddUser(userCrudView.getUserName(),userCrudView.getEncryptedPassword(),userCrudView.getDepartmentId());
                userCrudView.refreshJTableUserInfo(userCrudModel.getTableModelCustomerInfo());
//                mainview.setUserId(userCrudModel.getUserIdFromUserName(userCrudView.getUserName()));
//              int userid = userCrudModel.getUserIdFromUserName(userCrudView.getUserName());
                //l oading userpreferences for the user
                ExecuteUserCreditial executeUserCreditial = new ExecuteUserCreditial(mainview, true, userCrudModel.getUserIdFromUserName(userCrudView.getUserName()));
//                executeUserCreditial.userCreditialView.setbtnCancelEnableFalse();
                
                userCrudView.btnCancel.doClick();
               
               
          }
          if(e.getActionCommand().equalsIgnoreCase("Edit")){
                 if(userCrudView.getUserName().isEmpty()){
                  JOptionPane.showMessageDialog(userCrudView, "UserName Can Not be Blank");
                  return;
              }
                 if(!InitialUserName.equals(userCrudView.getUserName())){
                    if(userCrudModel.checkExistingName(userCrudView.getUserName())){
                        JOptionPane.showMessageDialog(userCrudView, "UserName Already Taken. Pleas Choose Another Name");
                        return;
                    }
                 }
//               if(userCrudView.getPassWord().length == 0){
//                  JOptionPane.showMessageDialog(userCrudView, "Password Can Not be Blank");
//                  return;
//              }
                 if(userCrudView.returncomboDepartment().getSelectedIndex() == 0){
                 
                  if(!DisplayMessages.displayInputYesNo(userCrudView,"Department Is Not Setup for this user.\n Do You Want to Continue?","Add User Window"))
                  {
                      
                      return;
                  }
                  
               }
                   if(DisplayMessages.displayInputYesNo(userCrudView,"Do You Want To edit?"," Edit Window"))
                    {
                        userCrudModel.EditCustomer(userCrudView.getUserName(), userCrudView.getUserId(),userCrudView.getDepartmentId());
                        userCrudView.setbtnEditEnableFalse();
                        userCrudView.setbtnDeleteEnableFalse();
                        userCrudView.refreshJTableUserInfo(userCrudModel.getTableModelCustomerInfo());
                        userCrudView.btnCancel.doClick();
                     }   
          }
          if(e.getActionCommand().equalsIgnoreCase("Delete")){
               int choice =  JOptionPane.showConfirmDialog(userCrudView,"Do You Want To Delete?"," Delete Window",JOptionPane.YES_NO_CANCEL_OPTION);
              if(choice == JOptionPane.YES_OPTION){
              userCrudModel.DeleteCustomer(userCrudView.getUserId());
               userCrudView.setbtnEditEnableFalse();
              userCrudView.setbtnDeleteEnableFalse();
               userCrudView.refreshJTableUserInfo(userCrudModel.getTableModelCustomerInfo());
              userCrudView.btnCancel.doClick();
              }
              
          }
          if(e.getActionCommand().equalsIgnoreCase("Cancel")){
              userCrudView.setUserName("");
              userCrudView.setPassWord("");
              userCrudView.setConfirmPassword("");
              userCrudView.setUserId(0);
              userCrudView.returncomboDepartment().setSelectedIndex(0);
              userCrudView.setbtnEditEnableFalse();
              userCrudView.setbtnDeleteEnableFalse();
              userCrudView.setbtnAddEnableTrue();
              userCrudView.setPanelPasswordButtonVisible(false);
              userCrudView.setPanelPasswordFieldsVisible(true);
          
            }
          if(e.getActionCommand().equalsIgnoreCase("ChangePassword")){
              ExecuteResetPassword executeResetPassword = new ExecuteResetPassword(new JFrame(), true,userCrudView.getUserId());
          }
          if(e.getActionCommand().equalsIgnoreCase("ViewPassword")){
              //now Show the decypted password
              String ecnytpassword = userCrudModel.returnPasswordByUserId(userCrudView.getUserId());
              String decpytpassword = CyptoAES.decypt(ecnytpassword);
              DisplayMessages.displayInfo(mainview,"The Passord of "+userCrudView.getUserName() +" is "+ "'"+ decpytpassword +"'" , "Password");
              
          }
      }
      catch(HeadlessException ue){
          JOptionPane.showMessageDialog(userCrudView, ue+"From UserCrudListener");
      }
        }
       
   }
   public class UserListSelectionListener implements ListSelectionListener{
       UserCrudView userview;
       JTable table;
       public UserListSelectionListener(UserCrudView view){
           userview = view;
           table = userview.tblUserInfo;
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
              String[] userinfo = displayRowValues(lead);
             int id =Integer.parseInt(userinfo[0]);
              userview.setUserId(id);
              userview.setUserName(userinfo[1]);
//              userview.setPassWord(userinfo[2]);
              InitialUserName = userinfo[1];
              userview.setcomboDepartment(userinfo[2]);
              //for setting in mainview
//              mainview.setUserId(id);
                userCrudView.setbtnEditEnableTrue();
              userCrudView.setbtnDeleteEnableTrue();
              userCrudView.setbtnAddEnableFalse();
              //hide the password field and label
//              userCrudView.setPasswordFieldsVisible(false);
//              userCrudView.setBtnChangePasswordVisible(true);
//              userCrudView.setBtnViewPasswordVisible(true);
              userCrudView.setPanelPasswordButtonVisible(true);
              userCrudView.setPanelPasswordFieldsVisible(false);
               ExecuteUserCreditial executeUserCreditial = new ExecuteUserCreditial(mainview, true, id);
              
          }
           
       }
       catch(NumberFormatException ue){
           JOptionPane.showMessageDialog(userCrudView, ue+"from ListSelectionListener");
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
   public class DepartmentComboListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            try{
               JComboBox jc = (JComboBox)e.getSource();
               if(jc.getSelectedIndex() == 0){
                  userCrudView.setDepartmentId(0);
               }
               else{
                   for(Object[] data:userCrudModel.getDepartmentInfo()){
                       if(jc.getSelectedItem().equals(data[1])){
                           userCrudView.setDepartmentId(Integer.parseInt(data[0].toString()));
                       }
                   }
               }
            }
            catch(NumberFormatException se){
                JOptionPane.showMessageDialog(userCrudView, se+"from DepartmentComboListener "+getClass().getName());
            }
        }
       
   } 
   //class to hanlde shortcut for insert and home key
    public class ShortcutForUserCrud implements KeyEventDispatcher{

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
        try{
            if(userCrudView.isActive()){
                if(e.getKeyCode() == KeyEvent.VK_INSERT){
                    //setfocus on txtcustomername
                    userCrudView.getTxtUserName().requestFocusInWindow();
                }
                if(e.getKeyCode() == KeyEvent.VK_HOME){
                    userCrudView.getTblUserInfo().requestFocusInWindow();
                }
            }
            
        }
        catch(Exception se){
            DisplayMessages.displayError(userCrudView, se.getMessage()+"from shortcutforuserCrud"+getClass().getName(), "Error");
        }
        return false;
        }
        
    }
}
