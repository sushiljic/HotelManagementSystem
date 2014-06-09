/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hotelmanagementsystem;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import reusableClass.DisplayMessages;
import reusableClass.Register;

/**
 *
 * @author SUSHIL
 */
public class RegisterPOSController {
    
    private RegisterPOSView registerPOSView;
    private RegisterPOSModel registerPOSModel;
    public RegisterPOSController(RegisterPOSModel model,RegisterPOSView view){
        registerPOSModel  = model;
        registerPOSView = view;
        
        registerPOSView.addRegisterListener(new RegisterListener());
        registerPOSView.addCancelListener(new RegisterListener());
    }
 public class RegisterListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
               if(e.getActionCommand().equalsIgnoreCase("Register")){
                   String registercode = registerPOSView.gettxtRegisterCode();
                   String serialcode = registerPOSView.getLblSerialCode();
                   
                   String truncateserialcode = serialcode.substring(0,5);
                   String truncateregistercode = registercode.substring(0,5);
                   if(Register.DecodeSerialCharCodeByIndex(truncateserialcode).equals(truncateregistercode)){
                       registerPOSModel.setRegisterCode(registercode);
                       registerPOSView.dispose();
                   }
                   else{
                       JOptionPane.showMessageDialog(registerPOSView,"Please Enter the Valid Register Code");
                   }
                   
                   
               }
               if(e.getActionCommand().equalsIgnoreCase("Cancel")){
                   registerPOSView.setVisible(false);
               }
            }
            catch(HeadlessException se){
                DisplayMessages.displayError(registerPOSView, se.getMessage()+"From RegisterListener "+getClass().getName(), "Error");
            }
        }
     
 }   
 
}
