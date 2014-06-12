/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package registrator;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.NoSuchAlgorithmException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import license.Customer;
import reusableClass.DisplayMessages;
import reusableClass.Function;

/**
 *
 * @author SUSHIL
 */
public class ExecuteRegister {
    private RegisterPOSView registerPOSView;
    private RegisterPOSModel registerPOSModel;
    private RegisterPOSController registerPOSController;
    public ExecuteRegister(JFrame parent,boolean modal){
        registerPOSModel = new RegisterPOSModel();
        registerPOSView = new RegisterPOSView(parent, modal);
        registerPOSController = new RegisterPOSController(registerPOSModel, registerPOSView);
        registerPOSView.setVisible(true);
    }
    public ExecuteRegister(JFrame parent,boolean modal,boolean type){
          //load the run RegisterModule to register  for the user
                  //this will run only one time when company will be setup
                registerPOSModel = new RegisterPOSModel();
                registerPOSView = new RegisterPOSView(parent, modal);
                registerPOSView.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                registerPOSView.addWindowListener(new WindowAdapter(){
                      @Override
                      public void windowClosing(WindowEvent evt){
                          if(DisplayMessages.displayInputYesNo(registerPOSView, "Do you Want to Close the System?", " Software Registration")){
                              System.exit(0);
                          }
                      }
                  });
                
                registerPOSController = new RegisterPOSController(registerPOSModel, registerPOSView);
                
                registerPOSView.getBtnCancel().setVisible(false);
                 //generate the serial code load into the serial code
                 String cName = Function.getCompanyName();
                 String serialcode = new String();
                 Customer Client = new Customer(cName);
                 try{
                 serialcode = Client.GenerateCode();
                 }
                 catch(NoSuchAlgorithmException se){
                     DisplayMessages.displayError(registerPOSView, se.getMessage(), "from ExecuteRegister");
                 }
                 registerPOSView.setLblSerialCode(serialcode);
                 
                 registerPOSView.setVisible(true);
    }
    public RegisterPOSView getRegisterPosView(){
        return registerPOSView;
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ExecuteRegister executeRegister = new ExecuteRegister(new JFrame() , true);
    }
    
}
