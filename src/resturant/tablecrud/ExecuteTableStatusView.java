/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package resturant.tablecrud;

import hotelmanagementsystem.MainFrameView;

/**
 *
 * @author SUSHIL
 */
public class ExecuteTableStatusView  {
     public  TableStatusView view;
   public  TableCrudModel model;
  public   TableStatusController cont;
    public ExecuteTableStatusView(MainFrameView mview){
        view = new TableStatusView();
        model = new TableCrudModel();
        cont  = new TableStatusController(model,view,mview);
        view.setVisible(true);
        
    }
  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new ExecuteTableStatusView(new MainFrameView()); 
    }
    
}
