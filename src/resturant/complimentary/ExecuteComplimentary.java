/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.complimentary;

import resturant.customer.*;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author SUSHIL
 */
public class ExecuteComplimentary extends JDialog {
   public  ComplimentaryView CustomerView;
    public  ComplimentaryModel CustomerModel;
         public      ComplimentaryController CustomerController;
    
    public ExecuteComplimentary(JFrame parent,boolean modal){
        super(parent,modal);
         CustomerView = new ComplimentaryView(parent,modal);
       CustomerModel = new ComplimentaryModel();
        
         CustomerController = new ComplimentaryController(CustomerModel,CustomerView);
//         ComplimentaryView.addWindowListener(new WindowAdapter(){
//             public void windowClosing(WindowEvent e){
//                 JOptionPane.showMessageDialog(rootPane, "");
//             }
//         });
         
          CustomerView.setVisible(true);
     
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
   
        
     /*   ComplimentaryView ComplimentaryView = new ComplimentaryView();
        ComplimentaryModel ComplimentaryModel = new ComplimentaryModel();
        
        ComplimentaryController ComplimentaryController = new ComplimentaryController(ComplimentaryModel,ComplimentaryView);
        ComplimentaryView.setVisible(true);
        * */
           /* Create and display the form */
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
              ExecuteComplimentary dialog =  new ExecuteComplimentary(new JFrame(),true);
//               dialog.ComplimentaryView.addWindowListener(new WindowAdapter(){
//            @Override
//            public void windowClosing(WindowEvent e){
//                JOptionPane.showMessageDialog(null, "Jagad Guru Kripalu maharaj ki jaya ho");
//            }
//        });
             
                 
               
            }
        });
       }
}
