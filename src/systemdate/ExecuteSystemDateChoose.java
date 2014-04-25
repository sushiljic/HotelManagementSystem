/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package systemdate;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecuteSystemDateChoose extends JDialog {
   
    public ExecuteSystemDateChoose(JFrame parent,boolean modal){
        super(parent,modal);
//     System.out.println("walaradheradhe");
       SystemDateChooseView systemDateChooseView = new SystemDateChooseView((JFrame)parent, modal);
//    System.out.println("walaradheradhe");
       SystemDateModel systemDateModel = new SystemDateModel();
//     System.out.println("walaradheradhe");
       SystemDateChooseController systemDateChooseController = new SystemDateChooseController(systemDateModel, systemDateChooseView);
//     System.out.println("walaradheradhe");
       if(!systemDateModel.checkSystemDateExist()){
       systemDateChooseView.setVisible(true);
       }
       else{
           JOptionPane.showMessageDialog(systemDateChooseView, "System Date has been Already Set.");
       }
        
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              new ExecuteSystemDateChoose(new JFrame(), true);
           
            }
        });
    }
}
