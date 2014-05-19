/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package resturant.menulist;

import hotelmanagementsystem.MainFrameView;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public  class ExecuteMenuDetailList {
  public  MenuListView view;
  public  MenuListModel model;
  public    MenuListDetailController cont;
  
  
  
  
  public ExecuteMenuDetailList(MainFrameView mview, boolean modal){
        
        view = new MenuListView(mview,modal);
        
        
        
        model = new MenuListModel();
        cont  = new MenuListDetailController(model,view,mview);
        view.setTitle("Menu Detail View");
        
        view.setVisible(true);
  }
  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               ExecuteMenuDetailList  executeMenuDetailList = new ExecuteMenuDetailList(new MainFrameView(),false);        
            }
            
        });
    
    }
    
}
