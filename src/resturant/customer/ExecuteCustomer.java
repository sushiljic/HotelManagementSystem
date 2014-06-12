/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.customer;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author SUSHIL
 */
public class ExecuteCustomer extends JDialog {
   public  CustomerView CustomerView;
    public  CustomerModel CustomerModel;
         public      CustomerController CustomerController;
    
    public ExecuteCustomer(JFrame parent,boolean modal){
        super(parent,modal);
         CustomerView = new CustomerView(parent,modal);
         CustomerModel = new CustomerModel();
        
        CustomerController = new CustomerController(CustomerModel,CustomerView);
//         CustomerView.addWindowListener(new WindowAdapter(){
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
   
        
     /*   CustomerView CustomerView = new CustomerView();
        CustomerModel CustomerModel = new CustomerModel();
        
        CustomerController CustomerController = new CustomerController(CustomerModel,CustomerView);
        CustomerView.setVisible(true);
        * */
           /* Create and display the form */
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
              ExecuteCustomer dialog =  new ExecuteCustomer(new JFrame(),true);
//               dialog.CustomerView.addWindowListener(new WindowAdapter(){
//            @Override
//            public void windowClosing(WindowEvent e){
//                JOptionPane.showMessageDialog(null, "Jagad Guru Kripalu maharaj ki jaya ho");
//            }
//        });
             
                 
               
            }
        });
       }
}
