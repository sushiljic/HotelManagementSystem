/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.menuentry;

import hotelmanagementsystem.MainFrameView;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecuteMenuEntry extends JDialog {
    MenuEntryView menuView;
     MenuEntryModel menuModel;
      MenuEntryController menucontroller;
public ExecuteMenuEntry(JFrame parent,boolean modal){
    super(parent,modal);
   menuView  = new MenuEntryView(parent,modal);
        MenuEntryModel menuModel = new MenuEntryModel();
        
         MenuEntryController menucontroller = new MenuEntryController( menuModel,menuView,(MainFrameView)parent);
         menuView.radioTrackable.setSelected(true);
         menuView.setVisible(true);
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       // Test HybridView = new Test();
       // MenuEntryHybridView HybridView = new MenuEntryHybridView();
       
        
//        
//        menuView.refreshJTable(menuModel.getMenuList());
//         menuView.setComboItemName(menuModel.returnItemName(menuModel.getItemInfoForMenu()));
        // HybridView.setVisible(true);
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            new ExecuteMenuEntry(new JFrame(),true);
            }
            
        });
       
    }
}
