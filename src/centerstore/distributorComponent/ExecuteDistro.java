/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.distributorComponent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author MinamRosh
 */

public class ExecuteDistro {
    
    public ExecuteDistro(){
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
        
            DistributerView dV = new DistributerView(new JFrame(), true);
                DistributerModel dM = new DistributerModel();
                DistributerController dC = new DistributerController(dV,dM);
                dV.setVisible(true);        
              
            }         
        });  
        
        
    }
    
   public static void main(String[] args){
       new ExecuteDistro();
       
    }
}
