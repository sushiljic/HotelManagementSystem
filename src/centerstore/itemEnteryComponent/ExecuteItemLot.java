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

public class ExecuteItemLot {
    public ExecuteItemLot(){
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                ItemLotView itemLot = new ItemLotView(new JFrame(), true);
       ItemLotModel iModel = new ItemLotModel(itemLot);
       ItemLotController controller = new ItemLotController(itemLot, iModel);
       itemLot.setVisible(true);
            }
            
        });
    }
    public static void main(String[] args){
        new ExecuteItemLot();
  }
}
