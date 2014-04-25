/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserCreditial;

import hotelmanagementsystem.MainFrameView;
import hotelmanagementsystem.MainFrameModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author SUSHIL
 */
public class UserCreditialController extends  UserCreditialModel {
    private UserCreditialView creditialView ;
    private UserCreditialModel creditialModel;
    private MainFrameView mainview;
    private MainFrameModel model = new MainFrameModel();
    private  int UserId = 0;
    public UserCreditialController(UserCreditialModel model,UserCreditialView view,MainFrameView mainview, int UserId){
        creditialModel = model;
        creditialView = view;
        this.mainview = mainview;
        this.UserId = UserId;
//        creditialView.addCheckBoxListener(new CheckListener());
        creditialView.addSaveListener(new SaveCancelListener());
        creditialView.addCancelListener(new SaveCancelListener());
        
        //for checking the allow if it is alreay addinges
//        System.out.println(this.mainview.getUserId()+"wala");
        creditialModel.DisplayMenuItemAsCreditial(creditialModel.getUserCreditial(/*this.mainview.getUserId()*/this.UserId), creditialView.getAllMenuItemText());
    }
    
//    public class CheckListener implements ActionListener{
//
//        
//       
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
////            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        try{
//            if(e.getActionCommand().equalsIgnoreCase("AllowCompanySetup")){
//                JCheckBoxMenuItem item = (JCheckBoxMenuItem)e.getSource();
//                if(item.isSelected()){
//                creditialView.setcheckCompanySetup(Boolean.TRUE);
////                System.out.println(creditialView.getcheckCompanySetup());
//                }
//            }
//            //centerstoresetup
//             if(e.getActionCommand().equalsIgnoreCase("AllowCenterStoreSetup")){
//                JCheckBoxMenuItem item = (JCheckBoxMenuItem)e.getSource();
//                if(item.isSelected()){
//                creditialView.setcheckCenterStoreSetup(Boolean.TRUE);
//                }
//            }
//             //centerstore
//            if(e.getActionCommand().equalsIgnoreCase("AllowCenterStore")){
//                JCheckBoxMenuItem item = (JCheckBoxMenuItem)e.getSource();
//                if(item.isSelected()){
//                creditialView.setcheckCenterStore(Boolean.TRUE);
//                }
//            }
//            //terminalsetup
//            if(e.getActionCommand().equalsIgnoreCase("AllowTerminalSetup")){
//                JCheckBoxMenuItem item = (JCheckBoxMenuItem)e.getSource();
//                if(item.isSelected()){
//                creditialView.setcheckTerminalSetup(Boolean.TRUE);
//                }
//            }
//            //terminal
//            if(e.getActionCommand().equalsIgnoreCase("AllowTerminal")){
//                JCheckBoxMenuItem item = (JCheckBoxMenuItem)e.getSource();
//                if(item.isSelected()){
//                creditialView.setcheckTerminal(Boolean.TRUE);
//                }
//            }
//            //centerstoresetup
//            if(e.getActionCommand().equalsIgnoreCase("AllowCenterStoreReport")){
//                JCheckBoxMenuItem item = (JCheckBoxMenuItem)e.getSource();
//                if(item.isSelected()){
//                creditialView.setcheckCenterStoreReport(Boolean.TRUE);
//                }
//            }
//            //terminalsetup
//            if(e.getActionCommand().equalsIgnoreCase("AllowTerminalReport")){
//                JCheckBoxMenuItem item = (JCheckBoxMenuItem)e.getSource();
//                if(item.isSelected()){
//                creditialView.setcheckTerminalReport(Boolean.TRUE);
//                }
//            }
//            if(e.getActionCommand().equalsIgnoreCase("AllowSystemConfig")){
//                JCheckBoxMenuItem item = (JCheckBoxMenuItem)e.getSource();
//                if(item.isSelected()){
//                    creditialView.setcheckSystemConfig(Boolean.TRUE);
//                }
//            }
//        }
//        catch(Exception ce){
//            JOptionPane.showMessageDialog(creditialView, ce+"from CheckListener");
//        }
//        
//        }
//    }
    public class SaveCancelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("Save")){
//                if(creditialView.getUserId() == 0){
//                    return;
//                }
               creditialModel.InsertUserCreditial(/*mainview.getUserId()*/UserId, creditialView.getcheckCompanySetup(), creditialView.getcheckCenterStoreSetup(), creditialView.getcheckCenterStore(), creditialView.getcheckTerminalSetup(), creditialView.getcheckTerminal(), creditialView.getcheckCenterReport(), creditialView.getcheckTerminalReport(),creditialView.getcheckSystemDate(),creditialView.getcheckSystemConfig());
              
                model.DisplayMenuItemAsCreditial( getUserCreditial(mainview.getUserId()),mainview.getAllMenuItemText(),mainview.getAllToolBar());
               creditialView.setVisible(false);
               
            }
            if(e.getActionCommand().equalsIgnoreCase("Cancel")){
                 creditialView.setVisible(false);
                
            }
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(creditialView, se+"From SaveCancelListener");
        }
        }
        
    }
}
