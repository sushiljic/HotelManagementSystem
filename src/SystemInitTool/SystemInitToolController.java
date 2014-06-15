/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SystemInitTool;

import database.readDatabase;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.nio.file.Paths;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import reusableClass.DisplayMessages;
import reusableClass.Function;

/**
 *
 * @author SUSHIL
 */
public class SystemInitToolController {
    private SystemInitToolView theview;
    private SystemInitToolModel themodel;
    public SystemInitToolController(SystemInitToolModel model,SystemInitToolView view){
        theview  = view;
        themodel= model;
        theview.addTestListener(new TestListener());
        theview.addSaveListener(new TestListener());
        theview.addCancelListener(new TestListener());
        theview.addRadioLocalhostListener(new RadioLocalhostListener());
    }
    
    public class TestListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       try{
           if(e.getActionCommand().equalsIgnoreCase("Test")){
               if(theview.getServerLocation().isEmpty()){
                   JOptionPane.showMessageDialog(theview, "ServerLocation Cannot be Empty");
                  return; 
               }
              if(theview.getUserName().isEmpty()){
                  JOptionPane.showMessageDialog(theview, "UserName Cannot be Empty");
                  return;
              }
               if(theview.getUserName().isEmpty()){
                  JOptionPane.showMessageDialog(theview, "UserName Cannot be Empty");
                  return;
              }
               //procedd to test
             try{
           if(themodel.checkConnection(theview.getServerLocation(),theview.getUserName(),new String(theview.getPassword()))){
            JOptionPane.showMessageDialog(theview, "Connection SucessFull.\n Now Select The Database From ComboBox For Your System");
            //load all the datbase name in the combobox
            theview.setComboboxDatabaseName(themodel.returnAllDatabase());
            theview.AddSelectInCombo(theview.returnComboboxDatabaseName());
            theview.setBtnSaveEditable(true);
           }
           else{
              JOptionPane.showMessageDialog(theview, "Connection Failed.\n Please Ensure UserName and Password provided are Correct");
          theview.setBtnSaveEditable(false);
           }
             }
             catch(HeadlessException | SQLException se){
                 DisplayMessages.displayError(theview,"Connection Failed.\n Please Ensure UserName and Password provided are Correct", "");
             }
           }
           if(e.getActionCommand().equalsIgnoreCase("Save")){
               if(theview.returnComboboxDatabaseName().getSelectedIndex() == 0){
                   JOptionPane.showMessageDialog(theview, "Please Select The Database");
                   return;
               }
               readDatabase rdDatabase  = new readDatabase();
               rdDatabase.writeData(Paths.get("database.dat"),theview.getUserName(),new String(theview.getPassword()),theview.getServerLocation(),theview.getComboboxDatabaseName());
               JOptionPane.showMessageDialog(theview, "Database Setting  Saved Successively");
               theview.setVisible(false);
               
               //restart the application
               DisplayMessages.displayInfo(theview, "System Now Will Restart.", "Restart Window");
               Function.restartApplication(null);
           }
           if(e.getActionCommand().equalsIgnoreCase("Cancel")){
               theview.ClearAll();
           }
       }
       catch(Exception ce){
           JOptionPane.showMessageDialog(theview, ce+" from TestListener");
       }
        }
        
    }
    public class RadioLocalhostListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       try{
           if(e.getStateChange() == 1){
           theview.setServerLocation("localhost");
           theview.setTxtServerLocationEditable(false);
           theview.setFocusOnTxtUserName();
                   }
           else{
               theview.setServerLocation("");
           theview.setTxtServerLocationEditable(true);
           theview.setFocusOnTxtServerLocation();
           }
       }
       catch(Exception ie){
           JOptionPane.showMessageDialog(theview, ie+" From RadioLocalhostListener");
       }
        }
        
    }
    
}
