/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package registrator;

import javax.swing.JFrame;

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
