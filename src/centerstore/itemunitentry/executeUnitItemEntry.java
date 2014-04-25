/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.itemunitentry;

import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class executeUnitItemEntry extends JDialog {
     ItemUnitEntryView view;
      ItemUnitEntryModel model;
         ItemUnitEntryController controller;
    public executeUnitItemEntry(JFrame parent,boolean modal){
        super(parent,modal);   
        view = new ItemUnitEntryView(parent,modal);
       // view.displayJTable("item_unit");
       
       model = new ItemUnitEntryModel();
        //for loading content of database into table.
       
        controller = new ItemUnitEntryController(model,view);
         view.refreshJTable(model.getTableModelForJTable("item_unit"));
        view.setVisible(true);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
       
        // TODO code application logic here
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
            //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            new executeUnitItemEntry(new JFrame(),true);
            }
            
        });
      
    }
}
