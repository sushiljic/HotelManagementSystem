/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserCrud;

import hotelmanagementsystem.MainFrameView;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecuteUserCrud {
    public UserCrudView userCrudView;
    public UserCrudModel userCrudModel;
    public ExecuteUserCrud( final JFrame parent,boolean modal){
        userCrudView = new UserCrudView(parent, modal);
        userCrudModel = new UserCrudModel();
        UserCrudController userCrudController = new UserCrudController(userCrudModel, userCrudView,(MainFrameView)parent);
        userCrudView.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            new ExecuteUserCrud(new MainFrameView(), true);
            }
            
        });
    }
    
}
