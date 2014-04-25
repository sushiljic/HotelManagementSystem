/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package systemdate;

import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecuteDateOpen {
    public ExecuteDateOpen(){
         SystemDateModel dateModel = new SystemDateModel();
//         System.out.println("wala");
            dateModel.openSystemDate();
    }
   
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                ExecuteDateOpen executeDateOpen = new ExecuteDateOpen();
            }
        });
            
    }
}
