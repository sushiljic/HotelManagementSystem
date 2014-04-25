/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserCrud;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import reusableClass.DisplayMessages;

/**
 *
 * @author SUSHIL
 */
public class ExecuteResetPassword {
    private ResetPasswordView resetPasswordView;
    private UserCrudModel userCrudModel;
    private ResetPasswordController resetPasswordController;
    
    public ExecuteResetPassword(final JFrame parent,final boolean modal,final int userid){
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
            try{
                    resetPasswordView = new ResetPasswordView(parent, modal);
                    userCrudModel = new UserCrudModel();
                    resetPasswordController = new ResetPasswordController(userCrudModel, resetPasswordView, userid);
                    resetPasswordView.setVisible(true);
                
            }
            catch(Exception se){
                DisplayMessages.displayError(parent, se.getMessage()+"from executeResetPassword","Error");
            }
            }
            
        });
       
    }
    public static void main(String[] arg){
        ExecuteResetPassword executeResetPassword = new ExecuteResetPassword(new JFrame(), true, 11);
    }
    
}
