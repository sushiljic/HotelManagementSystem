/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.itemCategoryComponent;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author MinamRosh
 */

public class ExecuteItemCategoryComponent {
    
    public ExecuteItemCategoryComponent(){
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                ItemCategoryView itemView = new ItemCategoryView(new JFrame(), true);
        ItemCategoryModel itemModel = new ItemCategoryModel();
        ItemCategoryController itemController = new ItemCategoryController(itemView, itemModel);
        itemView.setVisible(true);
            }         
        });

    }
    
   public static void main(String[] args){
              new ExecuteItemCategoryComponent(); 
    }
}
