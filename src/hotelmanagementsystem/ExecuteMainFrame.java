/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

//import com.alee.laf.WebLookAndFeel;
import SystemInitTool.ExecuteSystemInitToolController;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecuteMainFrame {
   public MainFrameView MFView;
    MainFrameModel MFModel;
    SystemLogInView systemLogInView;
    
    public ExecuteMainFrame(){
        //checking of file exists or not
     
       Path path =    Paths.get("resources/system/database.dat");
          if(!Files.exists(path)){
        
              try{
              Files.createDirectories(Paths.get("resources/system"));
              }
              
              catch(IOException ioe){
                  JOptionPane.showMessageDialog(MFView, ioe+getClass().getName());
              }
             ExecuteSystemInitToolController executeSystemInitToolController = new ExecuteSystemInitToolController(MFView,true);  
//          System.out.println("wala1");
            
             return;
             
          }
      
        
        MFView = new MainFrameView();
        MFModel= new MainFrameModel();
        systemLogInView = new SystemLogInView(MFView, true);
      
          
       
        MainFrameController MFController = new MainFrameController(MFModel,MFView,systemLogInView);
        
        MFView.setVisible(true);
        // SystemLog inView to come
        
        SystemLogInController systemLogInController = new SystemLogInController(systemLogInView, MFModel,MFView);
        systemLogInView.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        // TODO code application logic here
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
//                WebLookAndFeel.install();
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           try{
                Thread.sleep(2500);//2.5 sec
               ExecuteMainFrame executeMainFrame = new ExecuteMainFrame();
           }
           catch(InterruptedException e){
               JOptionPane.showMessageDialog(null, e+"from ExecuteMainFrame");
           }
           }
           
            
        });
    }
}
