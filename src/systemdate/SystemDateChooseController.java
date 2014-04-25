/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package systemdate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author SUSHIL
 */
public class SystemDateChooseController {
    private SystemDateChooseView systemDateChooseView = new SystemDateChooseView(new JFrame(), true);
    private SystemDateModel systemDateModel = new SystemDateModel();
    
    public SystemDateChooseController(SystemDateModel model,SystemDateChooseView view){
        systemDateChooseView = view;
        systemDateModel = model;
//        SystemDateChooseController systemDateChooseController = new SystemDateChooseController(model, view);
        systemDateChooseView.addSaveListener(new SaveCancelListener());
    }
    public class SaveCancelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       try{
           if(e.getActionCommand().equalsIgnoreCase("Save")){
               if(systemDateChooseView.getDateChooser() instanceof Date){
                   
               }
               else{
                   JOptionPane.showMessageDialog(systemDateChooseView, "Enter Right date Format");
                   return;
               }
               systemDateModel.addSystemDate(systemDateChooseView.getDateChooser());
               systemDateChooseView.setVisible(false);
               
           }
           if(e.getActionCommand().equalsIgnoreCase("Cancel")){
               
           }
       }
       catch(Exception se){
           JOptionPane.showMessageDialog(systemDateChooseView, se+"from SaveCancelListener");
       }
        }
        
    }
}



