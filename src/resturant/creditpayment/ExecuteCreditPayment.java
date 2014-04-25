/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package resturant.creditpayment;

import hotelmanagementsystem.MainFrameView;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecuteCreditPayment {
    
    private CreditPaymentView creditPaymentView;
    private CreditPaymentModel creditPaymentModel;
    private CreditPaymentController creditPaymentController;
    public  ExecuteCreditPayment(final JFrame parent,final boolean modal){
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
        creditPaymentView = new CreditPaymentView(parent, modal);
        creditPaymentModel = new CreditPaymentModel();
        creditPaymentController = new CreditPaymentController(creditPaymentModel, creditPaymentView, (MainFrameView)parent);
        creditPaymentView.setVisible(true);
            }
        });
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
     ExecuteCreditPayment creditPayment = new ExecuteCreditPayment(new JFrame(), true);
    }
    
}
