/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package resturant.wastage;

import hotelmanagementsystem.MainFrameView;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecuteWastage {
//    private WastageView wastageView;
//    private WastageModel wastageModel;
//    private WastageController wastageController;
   
    
    public ExecuteWastage(JFrame parent,boolean modal){
        
       WastageView wastageView = new WastageView(parent,modal);
       WastageModel wastageModel = new WastageModel();
       WastageController wastageController= new WastageController(wastageModel, wastageView, (MainFrameView)parent);
       wastageView.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
               ExecuteWastage wast = new ExecuteWastage(new JFrame(), true);
            }
        });
    }
    
}
