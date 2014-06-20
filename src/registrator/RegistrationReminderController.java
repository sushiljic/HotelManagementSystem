/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package registrator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import reusableClass.CyptoAES;
import reusableClass.DisplayMessages;
import reusableClass.Function;

/**
 *
 * @author SUSHIL
 */
public class RegistrationReminderController {
    private RegistrationReminderView registrationReminderView;
    private RegisterPOSModel registerPOSModel;
    private String strRegisterCode;
    private String strdecRegisterCode;
    private String strSerialCode;
    private String strdecSerialCode;
    private  int workingdays= 0;
    private int status ;
    public RegistrationReminderController(JFrame parent,boolean modal){
//        registrationReminderView = view;
//        registerPOSModel = model;
        registrationReminderView = new RegistrationReminderView(parent, modal);
        registerPOSModel = new RegisterPOSModel();

        
//        registrationReminderController = new RegistrationReminderController(registerPOSModel, registrationReminderView);
        registrationReminderView.addRegisterListener(new Registrationlistener());
        registrationReminderView.addContinueListener(new Registrationlistener());
//         workingdays = Function.getNumberofSystemDateInserted();
//                System.out.println(workingdays)
        try{
        status = Function.getCompanyStatus();
        if(status == 0){
            
        }
        else if (status == 1){
            strSerialCode = registerPOSModel.getSerialCode();
 
            if(strSerialCode != null){
            
            strdecSerialCode = CyptoAES.decypt(strSerialCode);
            //check whether it have 0 flag at last or not
            if(strdecSerialCode.toCharArray().length >8){
//                System.out.println(strdecSerialCode.substring(8, adm9));
                //display the view class
                if(strdecSerialCode.substring(8, 9).equalsIgnoreCase("0")){
//                    System.out.println("wala");
                //set the number of day in progress bar
                workingdays = Function.getNumberofSystemDateInserted();
//                System.out.println(();
                if(workingdays <= 35){
                float workingpercent;
                workingpercent = 35-workingdays;
//                System.out.println(workingpercent);
                workingpercent = workingpercent/35;
//                System.out.println(workingpercent);
                workingpercent = workingpercent * 100;
//                System.out.println(workingpercent);
                
                registrationReminderView.setProgressbarValue((int)workingpercent);
                registrationReminderView.setProgressbarString(35 - workingdays+"days remaining");
                registrationReminderView.setProgressbarStringPainted(true);
                registrationReminderView.setVisible(true);
                }
                else{
//                 System.out.println(workingdays); 
                DisplayMessages.displayInfo(parent, "Your Demo Period have been expired.\nPlease Contact your Software Provider for License Key","Registration Remminder");
                registrationReminderView.getBtnRegister().doClick();
                }
                }
            }
        }
        }
        
        }
        catch(SQLException se){
            DisplayMessages.displayError(parent, se.getMessage(), "from RegistrationReminderController ");
            System.exit(0);
        }
        
      
        
        
    }
    public  class Registrationlistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                if(e.getActionCommand().equalsIgnoreCase("Register")){
                     registrationReminderView.setVisible(false);
                    ExecuteRegister executeRegister = new ExecuteRegister(null,true);
                   
                    
                }
                if(e.getActionCommand().equalsIgnoreCase("Continue")){
                    registrationReminderView.setVisible(false);
                }
                
            }
            catch(Exception se){
                DisplayMessages.displayError(registrationReminderView, se.getMessage()+"from "+getClass().getName(), "From Registration Listener");
            }
        }
       
        
    }
    public static void main(String[] arg){
        RegistrationReminderController registrationReminderController = new registrator.RegistrationReminderController(null, true);
    }
    
}
