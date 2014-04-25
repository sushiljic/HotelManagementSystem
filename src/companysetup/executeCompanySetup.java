/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package companysetup;

import hotelmanagementsystem.MainFrameView;
import java.awt.Dialog;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class executeCompanySetup extends JDialog {
   public   CompanySetupModel cmodel ;
     public  CompanySetupView cview ;
      CompanySetupController ccontrol;
     
    public executeCompanySetup(JFrame parent,boolean modal){
        super(parent,modal);  
        cmodel = new CompanySetupModel();
     cview = new CompanySetupView(parent,modal);
       ccontrol = new CompanySetupController(cview,cmodel,(MainFrameView)parent);
        cview.setVisible(true);
//        setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
        
      //  ModalityType(true);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
   // CompanySetupModel cmodel = new CompanySetupModel();
   // CompanySetupView cview = new CompanySetupView();
    //CompanySetupController ccontrol = new CompanySetupController(cview,cmodel);
   // cview.setVisible(true);
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
             //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         executeCompanySetup cp =   new executeCompanySetup(new JFrame(),true);
//            executeCompanySetup cp =   new executeCompanySetup(new MainFrameView(),true);
          // cp.setModal(true);
           
            }
            
        });
        
        
    
    }
}
