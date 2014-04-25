/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.itemEnteryComponent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author MinamRosh
*/

public class ExecuteItemEntry {
    public ExecuteItemEntry(){
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                ItemEnteryView itemEntry = new ItemEnteryView(new JFrame(), true);
       ItemEnteryModel iModel = new ItemEnteryModel(itemEntry);
       ItemEnteryController controller = new ItemEnteryController(itemEntry, iModel);
       itemEntry.setVisible(true);
            }
            
        });
    }
    public static void main(String[] args){
        new ExecuteItemEntry();
  }
}
