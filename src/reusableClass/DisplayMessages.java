





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reusableClass;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author MinamRosh
 */
public class DisplayMessages {
    
    public static void displayInfo(Component cmp,String msg, String title){
        JOptionPane.showMessageDialog(cmp, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void displayError(Component cmp,String msg, String title){
        JOptionPane.showMessageDialog(cmp, msg, title, JOptionPane.ERROR_MESSAGE);
    }
    
    public static void displayWarning(Component cmp,String msg, String title){
        JOptionPane.showMessageDialog(cmp, msg, title, JOptionPane.WARNING_MESSAGE);
    }
    public static boolean displayInputYesNo(Component cmp,String msg,String title){
//        int choice = JOptionPane.showConfirmDialog(cmp, msg, title,JOptionPane.YES_NO_OPTION);
         int choice = JOptionPane.showConfirmDialog(cmp, msg, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return choice == JOptionPane.YES_OPTION;
    }
}
