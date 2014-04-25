/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.tablecrud;

import hotelmanagementsystem.MainFrameView;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecuteTableCrud  extends JDialog {
    public ExecuteTableCrud(JFrame parent,boolean modal){
        super(parent,modal);
            TableCrudModel TableCrudModel = new TableCrudModel();
    TableCrudView TableCrudView = new TableCrudView(parent,modal);
    
   
    TableCrudController TableCrudController = new TableCrudController(TableCrudModel,TableCrudView,(MainFrameView)parent);
   
     TableCrudView.setVisible(true);
   //  TableCrudView.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
     SwingUtilities.invokeLater(new Runnable() {

         @Override
         public void run() {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        new ExecuteTableCrud(new JFrame(),false);
         }
     });
     
    }
}
