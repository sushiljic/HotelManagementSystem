/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserCrud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import reusableClass.CyptoAES;
import reusableClass.DisplayMessages;

/**
 *
 * @author SUSHIL
 */
public class ResetPasswordController {
    private final ResetPasswordView resetPasswordView;
    private final UserCrudModel userCrudModel;
    private final int userid;
    
    public ResetPasswordController(UserCrudModel model,ResetPasswordView view, int userid){
        resetPasswordView = view;
        userCrudModel = model;
        this.userid = userid;
        //adding handler 
        resetPasswordView.addSaveListener(new ChangePasswordListener());
        resetPasswordView.addCancelListener(new ChangePasswordListener());
        
    }
    
    public class ChangePasswordListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        try{
            if(e.getActionCommand().equalsIgnoreCase("Save")){
                if(resetPasswordView.getNewPassword().isEmpty()){
                    DisplayMessages.displayWarning(resetPasswordView, "Password Changenot be Blank", "Password Window");
                    return;
                }
                if(!resetPasswordView.getNewPassword().equals(resetPasswordView.getComfirmPassword())){
                     DisplayMessages.displayWarning(resetPasswordView, "Password Changenot be Blank", "Password Window");
                    return;
                }
                //encpyt the password
                String encyptValue = CyptoAES.encrypt(resetPasswordView.getNewPassword());
                if(DisplayMessages.displayInputYesNo(resetPasswordView, "Do You Want to Change The Password", "Change Password")){
                    userCrudModel.ChangePassword(encyptValue, userid);
                    resetPasswordView.dispose();
                }
            }
            if(e.getActionCommand().equalsIgnoreCase("Cancel")){
                resetPasswordView.dispose();
            }
            
        }
        catch(Exception se){
            DisplayMessages.displayError(resetPasswordView, se.getMessage()+"from ChangePasswordListener "+getClass().getName(), null);
        }
        }
        
    }
}
