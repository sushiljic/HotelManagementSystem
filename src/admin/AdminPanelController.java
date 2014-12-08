/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package admin;

import hotelmanagementsystem.MainFrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import reusableClass.DisplayMessages;
import reusableClass.Function;

/**
 *
 * @author SUSHIL
 */
public class AdminPanelController {
    AdminPanelView adminPanelView;
    AdminPanelModel adminPanelModel;
    MainFrameView mainFrameView;

    public AdminPanelController(AdminPanelView adminPanelView,AdminPanelModel adminPanelModel,MainFrameView mainFrameView) {
        this.adminPanelView = adminPanelView;
        this.adminPanelModel= adminPanelModel;
        this.mainFrameView = mainFrameView;
        
        this.adminPanelView.addbtnSystemDateSaveListener(new SystemDateListener());
        this.adminPanelView.addbtnSystemDateCancelListener(new SystemDateListener());
        try{
        Boolean flag = Function.getSystemDateEnable();
        this.adminPanelView.setSystemDateSwitch(flag);
        }
        catch(Exception se){
            se.printStackTrace();
            DisplayMessages.displayError(mainFrameView, "Cannot get Message from SystemDateEnable", "Error");
        }
    }
    public class SystemDateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                if(e.getActionCommand().equalsIgnoreCase("Save"))
                {
                
                if(DisplayMessages.displayInputYesNo(mainFrameView, "Do you want to save?", "Save System Date Setting")){
                    adminPanelModel.saveSystemDateStatus(adminPanelView.getSystemDateSwitch());
                    DisplayMessages.displayInfo(mainFrameView, "SystemDate Configuration was saved Successfully.\nSoftware System will be restarted.", "Successful Transaction");
                    Function.restartApplication(null);
                    
                }
                }
                if(e.getActionCommand().equalsIgnoreCase("Cancel")){
                    adminPanelView.setVisible(false);
                }
            }
            catch(Exception se){
                se.printStackTrace();
                DisplayMessages.displayError(adminPanelView, se.getMessage(), "Error from SystemDateListener");
            }
        }
        
    }
    
    
}
