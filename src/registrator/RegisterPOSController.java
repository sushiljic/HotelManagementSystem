/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package registrator;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import javax.swing.JDialog;
import license.Validation;
import reusableClass.CyptoAES;
import reusableClass.DisplayMessages;
import reusableClass.Function;

/**
 *
 * @author SUSHIL
 */
public class RegisterPOSController {
    
    private RegisterPOSView registerPOSView;
    private RegisterPOSModel registerPOSModel;
    String strSerialCode;
    String strLicenseCode;
    String decyptSerialCode;
    String decyptRegisterCode;
    public RegisterPOSController(RegisterPOSModel model,RegisterPOSView view){
        registerPOSModel  = model;
        registerPOSView = view;
        
        registerPOSView.addRegisterListener(new RegisterListener());
        registerPOSView.addCancelListener(new RegisterListener());
//        registerPOSView.addTryTrialListener(new RegisterListener());
        try{
        //check wther it is trial version of registerversion
             strSerialCode = registerPOSModel.getSerialCode();
             //        System.out.println(strSerialCode);
            if(strSerialCode!= null){
            decyptSerialCode = CyptoAES.decypt(strSerialCode);
            }
            //set the original serail code
            registerPOSView.setorgSerailCode(decyptSerialCode);
           
            int status = Function.getCompanyStatus();
            if(status == 0){
                //if company is not inserted in the company
            }
            else if(status == 1){
               //if company is not registerd nut inserted
                
                if(decyptSerialCode != null){

//                registerPOSView.setbtnTryTrialVisible(false);

                  //check whether it have how man days
                if(decyptSerialCode.toCharArray().length >8){
                    //System.out.println(decyptSerialCode);
                      //display the view class
                    
                    if(decyptSerialCode.substring(8, 9).equalsIgnoreCase("0")){
      //                    System.out.println("wala");
                      //set the number of day in progress bar
                        
                        int  workingdays = Function.getNumberofSystemDateInserted();
      //                System.out.println(workingdays);
                        
                        registerPOSView.setLblSerialCode(decyptSerialCode.substring(0, 8));
                        if(workingdays > 35){
                        registerPOSView.getBtnCancel().setVisible(false);
                        //try code
                        registerPOSView.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                        registerPOSView.addWindowListener(new WindowAdapter(){
                              @Override
                              public void windowClosing(WindowEvent evt){
                                  if(DisplayMessages.displayInputYesNo(registerPOSView, "Do you Want to Close the System?", " Software Registration")){
                                      System.exit(0);
                                  }
                              }
                          });
                        }
                        else{
        //                 System.out.println(workingdays);    
                       registerPOSView.getBtnCancel().setVisible(true);
                        }
                      }
                  }
              }
                
            }
            else{
                //if conpany is registered
                 //get license codeif there is
//                JOptionPane.showMessageDialog(view, "wala");
                strLicenseCode = registerPOSModel.getRegisterCode();
                if(strLicenseCode != null){
                decyptRegisterCode = CyptoAES.decypt(strLicenseCode);
                }
                //set serial and license key
                registerPOSView.setLblSerialCode(decyptSerialCode);
//                System.out.println(decyptRegisterCode);
                registerPOSView.settxtRegisterCode(decyptRegisterCode);
                registerPOSView.getBtnRegister().setVisible(false);
                
            }
        }
        catch(SQLException se){
            DisplayMessages.displayError(view, se.getMessage(),"from RegisterPOSController");
            registerPOSView.setVisible(false);
            
        }
    }
 public class RegisterListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
               if(e.getActionCommand().equalsIgnoreCase("Register")){
                  
//                   String registercode = registerPOSView.gettxtRegisterCode();
                   String serialcode = registerPOSView.getLblSerialCode();
//                   System.out.println(serialcode);
                   String regcode = registerPOSView.gettxtRegisterCode();
                   String encregcode = null;
//                   System.out.println(regcode +"wala");
//                   DisplayMessages.displayInfo(registerPOSView, "Please Enter the License Key", "License Register");
//                   System.out.println(regcode.toCharArray().length);
                   if(regcode.isEmpty()){
                       DisplayMessages.displayInfo(registerPOSView, "Please Enter the License Key", "License Register");
                       return;
                   }
//                   System.err.println(registerPOSView.gettxtRegisterCode());
                   //create the validation object to validate the class
                   Validation validate = new Validation();
                   
                   if(validate.Validate(serialcode, regcode.toUpperCase())){
                       try{
                       if(regcode != null){
                       encregcode = CyptoAES.encrypt(regcode);
                       }
                      
                           //this function register and truncate the indicator in serial key
                       registerPOSModel.setRegisterCode(encregcode);
                       
                       DisplayMessages.displayInfo(registerPOSView, "Thank You For Registration.", "Registration Window");
                       registerPOSView.setVisible(false );
                       }
                       catch(SQLException se){
                          DisplayMessages.displayError(registerPOSView, "Can`t Insert the register code into Database"+se.getMessage(), "Register Error"); 
                       }
                   }
                   else{
                      DisplayMessages.displayInfo(registerPOSView, "Invalid License Number.\nPlease Contact your Software Provider.", "License Register"); 
                   }
                   
                   
                   
               }
//               if(e.getActionCommand().equalsIgnoreCase("TryTrial")){
//                   String decyptserial = null;
//                   String serialcode = registerPOSView.getLblSerialCode();
//                   serialcode += "0";
////                   System.out.println(serialcode);
//                   decyptserial = CyptoAES.encrypt(serialcode);
////                   System.out.println(decyptserial);
//                   try {
//                   registerPOSModel.setSerialCode(decyptserial);
//                   }
//                   catch(SQLException se){
//                   DisplayMessages.displayError(registerPOSView, "Can`t Insert the serial code into Database"+se.getMessage(), "Try Trial Error");
//                   }
//                   registerPOSView.setVisible(false);
//                   
//               }
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
