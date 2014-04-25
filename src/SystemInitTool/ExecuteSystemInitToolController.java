/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SystemInitTool;

import hotelmanagementsystem.MainFrameView;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecuteSystemInitToolController {
    private SystemInitToolController systemInitToolController;
    private SystemInitToolModel systemInitToolModel;
    public SystemInitToolView systemInitToolView;
    public ExecuteSystemInitToolController(final MainFrameView mfview, final boolean parent){
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        systemInitToolModel = new SystemInitToolModel();
        systemInitToolView = new SystemInitToolView(mfview,parent);
        systemInitToolController = new SystemInitToolController(systemInitToolModel, systemInitToolView);
         systemInitToolView.setRadioLocalhostSelection(true);
        systemInitToolView.setVisible(true);
       
            }
            
        });
    
    }
            

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new ExecuteSystemInitToolController((MainFrameView)new JFrame(),true);
    }
    
}
