/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.waiter;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecuteWaiter extends JDialog {
    
    public ExecuteWaiter(JFrame parent,boolean modal){
        super(parent,modal);
         final WaiterView WaiterView = new WaiterView(parent,modal);
         WaiterModel WaiterModel = new WaiterModel();
         
         WaiterController WaiterController = new WaiterController(WaiterModel,WaiterView);
//         WaiterView.addWindowListener(new WindowAdapter(){
//             public void windowClosing(WindowEvent e){
//                 JOptionPane.showMessageDialog(rootPane, "");
//             }
//         });
         
          SwingUtilities.invokeLater(new Runnable() {

           @Override
           public void run() {
             //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//          new ExecuteWaiter(new JFrame(),true);
          WaiterView.setVisible(true);
           }
       });
        
        
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//       SwingUtilities.invokeLater(new Runnable() {
//
//           @Override
//           public void run() {
//             //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//          new ExecuteWaiter(new JFrame(),true);
//           }
//       });
//    }
}
