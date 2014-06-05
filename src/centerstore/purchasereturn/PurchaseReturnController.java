/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.purchasereturn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author SUSHIL
 */
public class PurchaseReturnController {
    private PurchaseReturnModel prmodel;
    private PurchaseReturnView prview;
    private String UnitRelativeQuantity;
    
    PurchaseReturnController(PurchaseReturnModel model,PurchaseReturnView view){
        this.prmodel = model;
        this.prview = view;
        /*
         * adding the action listener for the view
         */
        prview.addReturnListener(new PurchaseReturnListener());
        prview.addCancelListener(new PurchaseReturnListener());
        prview.addSearchListener(new PurchaseReturnListener());
        /*
         * this is implement for the text entered in search
         */
        
        prview.addTextSearchListener(new TextPurchaseReturnListener());
        /*
         * adding key listner for the view
         */
//        prview.addKeyReturnListener(new PurchaseReturnKeyListener());
//        prview.addKeyCancelListener(new PurchaseReturnKeyListener());
//        prview.addKeySearchlistener(new PurchaseReturnKeyListener());
        /*
         * adding table click or key listner for jtable
         */
        prview.addRowSelectedListener(new ReturnRowListener(prview));
        /*
        adding the documetListn
        */
        prview.addSearchDocumentListener(new SearchDocumentListener());
//        prview.addSearchPropertyChangeListener(new SearchPropertyChangeListener());
        //refresh the jtable
        prview.refreshJTable(prmodel.getItemList());
    }
    
    class PurchaseReturnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae){
            if(ae.getActionCommand().equalsIgnoreCase("Return")){
               try{
                   if(prview.getRItemId().isEmpty()){
                       JOptionPane.showMessageDialog(prview, "Please,First Select from the table.");
                       return;
                   }
                   if(prview.getReturnQuantity().isEmpty()){
                       JOptionPane.showMessageDialog(prview, "Blank Feilds not allowed in Return Quantity");
                       return;
                   }
                 /*  if(prview.getReturnAmount().isEmpty()){
                      JOptionPane.showMessageDialog(prview, "Blank Feilds not allowed.Recheck it.");
                       return; 
                   }*/
                   if(Float.parseFloat(prview.getReturnQuantity())>Float.parseFloat(prview.getStockQuantity())){
                      
                   if(Float.parseFloat(prview.getReturnQuantity())>Float.parseFloat(prview.getPurchaseQuantity())){
                     JOptionPane.showMessageDialog(prview, "Return Quantity is greater than Purchase Quantity");
                       return;  
                   }
                    JOptionPane.showMessageDialog(prview, "Return Quantity is greater than Stock Quantity");
                       return;
                   }
                   if(!prview.getReturnAmount().isEmpty()){
                   if(Float.parseFloat(prview.getReturnAmount())>Float.parseFloat(prview.getPurchaseAmount())){
                      JOptionPane.showMessageDialog(prview, "Return Amount is greater than Purchase Amount");
                      return;
                   }
                   }
                /*   try{
                       Float.parseFloat(prview.getReturnAmount());
                   }
                   catch(NumberFormatException ne){
                       JOptionPane.showMessageDialog(prview,"Enter right format in Amount");
                       return;
                   }*/
                     int choice;
                choice = JOptionPane.showConfirmDialog(prview, "Do You Want to Return the Item","Return Item", JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION){
                  prmodel.executeReturn(prview.getPurchaseReturn(),UnitRelativeQuantity);
                  prview.clearall();
                  prview.refreshJTable(prmodel.getItemList());
                  prview.disableReturnBtn();
               }
               }
               catch(Exception e){
                   JOptionPane.showMessageDialog(prview, e+"From PurchaseReutrn Listner");
               }
            }
            if(ae.getActionCommand().equalsIgnoreCase("cancel")){
                try{
                    prview.clearall();
                    prview.disableReturnBtn();
                    
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(prview, e+"form getcontroller");
                }
            }
            if(ae.getActionCommand().equalsIgnoreCase("Search")){
                 String  strSearch =null;
                 int col =1;
                 String[] SearchBox = new String[6];
                
                 
                try{
                    strSearch = prview.getSearch();
                    for(int row=0; row<prview.tblItemList.getModel().getRowCount();row++){
                       if(prview.tblItemList.getValueAt(row,col).toString().toLowerCase().startsWith(strSearch.toLowerCase())){
                        // JOptionPane.showMessageDialog(prview, "name foound");
                        //prview.setPurchaseReturn(displayRowValues());
                           //this collect the data of the item search in array
                         for(int scol = 0;scol<prview.tblItemList.getModel().getColumnCount();scol++){
                             SearchBox[scol] = prview.tblItemList.getValueAt(row, scol).toString();
//                             break;
                            
                             
                         }
                         //this will automatically set the view of scroll in the location of the value
                         prview.tblItemList.scrollRectToVisible(prview.tblItemList.getCellRect(row, 0, true));
                        //this will set the focus of th searched file in table
                        prview.tblItemList.setRowSelectionInterval(row, row);
                        
//                         prview.setTextEditableFalse();
//                          prview.setPurchaseReturn(SearchBox);
//                           prview.setReturnQuantity("");
//                    prview.setReturnReason("");
//                    prview.enableReturnBtn();
                       //  JOptionPane.showMessageDialog(prview, SearchBox);
//                          flag =true;
                          break;
                       } 
                    }
                   
                    
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(prview, e+"form search");
                }
            }
            /*
             * this is foraction to be performed when enter is entered in the text feolds 
             */
           
            
        }
    }
    class TextPurchaseReturnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae){
            prview.getBtnSearch().doClick();
        }
    }
    
    /*
     * start of keyPurchaseReturnListner
     */
   class PurchaseReturnKeyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyPressed(KeyEvent e) {
        //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       int code = 0;
       code = e.getKeyCode();
       if(code == KeyEvent.VK_ENTER){
           if(e.getSource().toString().contains("Return")){
               try{
                   if(prview.getRItemId().isEmpty()){
                       JOptionPane.showMessageDialog(prview, "Please,First Select from the table.");
                       return;
                   }
                   if(prview.getReturnQuantity().isEmpty()){
                       JOptionPane.showMessageDialog(prview, "Blank Feilds not allowed.Recheck it.");
                       return;
                   }
                   if(prview.getReturnAmount().isEmpty()){
                      JOptionPane.showMessageDialog(prview, "Blank Feilds not allowed.Recheck it.");
                       return; 
                   }
                   if(Float.parseFloat(prview.getReturnQuantity())>Float.parseFloat(prview.getStockQuantity())){
                     JOptionPane.showMessageDialog(prview, "Return Quantity is greater than Stock Quantity");
                       return;  
                   }
                   if(Float.parseFloat(prview.getReturnAmount())>Float.parseFloat(prview.getPurchaseAmount())){
                      JOptionPane.showMessageDialog(prview, "Return Amount is greater than Purchase Amount");
                      return;
                   }
                     int choice;
                choice = JOptionPane.showConfirmDialog(prview, "Do You Want to Return the Item","Return Item", JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION){
                  prmodel.executeReturn(prview.getPurchaseReturn(),UnitRelativeQuantity);
                  prview.clearall();
                  prview.refreshJTable(prmodel.getItemList());
                  prview.disableReturnBtn();
               }
               }
               catch(Exception se){
                   JOptionPane.showMessageDialog(prview, se+"From PurchaseReutrn Listner");
               }
           }
           if(e.getSource().toString().contains("Cancel")){
                try{
                    prview.clearall();
                    prview.disableReturnBtn();
                    
                }
                catch(Exception se){
                    JOptionPane.showMessageDialog(prview, se+"form getcontroller");
                }
               
           }
           if(e.getSource().toString().contains("Search")){
                String  strSearch =null;
                 int col =1;
                 String[] SearchBox = new String[6];
                 boolean flag = false;
                 
                try{
                    strSearch = prview.getSearch();
                    for(int row=0; row<prview.tblItemList.getModel().getRowCount();row++){
                       if(strSearch.equalsIgnoreCase(prview.tblItemList.getValueAt(row,col).toString())){
                        // JOptionPane.showMessageDialog(prview, "name foound");
                        //prview.setPurchaseReturn(displayRowValues());
                           //this collect the data of the item search in array
                         for(int scol = 0;scol<prview.tblItemList.getModel().getColumnCount();scol++){
                             SearchBox[scol] = prview.tblItemList.getValueAt(row, scol).toString();
                             break;
                            
                             
                         }
                         //this will automatically set the view of scroll in the location of the value
                         prview.tblItemList.scrollRectToVisible(prview.tblItemList.getCellRect(row, 0, true));
                        //this will set the focus of th searched file in table
                        prview.tblItemList.setRowSelectionInterval(row, row);
                         prview.setTextEditableFalse();
                          prview.setPurchaseReturn(SearchBox);
                          prview.setReturnQuantity("");
                prview.setReturnReason("");
                prview.enableReturnBtn();
                       //  JOptionPane.showMessageDialog(prview, SearchBox);
                          flag =true;
                       } 
                    }
                    if(flag==false){
                        JOptionPane.showMessageDialog(prview, "The Item Doesnot Exists.");
                    }
                    
                }
                catch(Exception se){
                    JOptionPane.showMessageDialog(prview, se+"froburgerbm search");
                }
               
           }
       }
        }

        @Override
        public void keyReleased(KeyEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
       
   }
    class ReturnRowListener implements ListSelectionListener{
        PurchaseReturnView rview = new PurchaseReturnView(new JFrame(),true);
        JTable jtable;
        public ReturnRowListener(PurchaseReturnView view){
            this.rview = view;
            this.jtable = this.rview.tblItemList;
        }
        @Override
        public void valueChanged(ListSelectionEvent le){
            try{
        if(le.getValueIsAdjusting()){
            return;
        }
        ListSelectionModel lsm = (ListSelectionModel) le.getSource();
        
        if(lsm.isSelectionEmpty()){
            //nothing is selected
        }
        else{
             ListSelectionModel lsmodel = jtable.getSelectionModel();
                int lead = lsmodel.getLeadSelectionIndex();
              
                
                rview.setPurchaseReturn(displayRowValues(lead));
                rview.setRItemId(prmodel.getItemIdAndDistrobutorId(rview.getPurchaseId())[0]);
                rview.setRDistributorId(prmodel.getItemIdAndDistrobutorId(rview.getPurchaseId())[1]);
                UnitRelativeQuantity = prmodel.getUnitRelativeQuantity(prmodel.getUnitId(rview.getBaseUnit()));
                rview.setStockQuantity(String.valueOf(Float.parseFloat(prmodel.getQuantityFromCenterStore(prview.getRItemId()))/Float.parseFloat(UnitRelativeQuantity)));
                //rview.setStockQuantity(String.valueOf(Float.parseFloat(rview.getStockQuantity())/UnitRelativeQuantity));
                rview.setTextEditableFalse();
                rview.setReturnQuantity("");
                rview.setReturnReason("");
                rview.setReturnAmount("");
                rview.enableReturnBtn();
            
        }
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(rview, se+"from returnrowlistener");
        }
            /*if(!le.getValueIsAdjusting()){
                ListSelectionModel lsmodel = jtable.getSelectionModel();
                int lead = lsmodel.getLeadSelectionIndex();
              
                
                rview.setPurchaseReturn(displayRowValues(lead));
                rview.setRItemId(prmodel.getItemIdAndDistrobutorId(rview.getPurchaseId())[0]);
                rview.setRDistributorId(prmodel.getItemIdAndDistrobutorId(rview.getPurchaseId())[1]);
                UnitRelativeQuantity = prmodel.getUnitRelativeQuantity(prmodel.getUnitId(rview.getBaseUnit()));
                rview.setStockQuantity(String.valueOf(Float.parseFloat(prmodel.getQuantityFromCenterStore(prview.getRItemId()))/Float.parseFloat(UnitRelativeQuantity)));
                //rview.setStockQuantity(String.valueOf(Float.parseFloat(rview.getStockQuantity())/UnitRelativeQuantity));
                rview.setTextEditableFalse();
                rview.setReturnQuantity("");
                rview.setReturnReason("");
                rview.enableReturnBtn();
                
                
            }
            * */
            
            
        }
        private String[] displayRowValues(int lead){
            int columns = jtable.getColumnCount();
            String[] st = new String[columns];
            for(int i = 0;i<columns;i++){
                Object o = jtable.getValueAt(lead, i);
                        try{
                            st[i] = o.toString();
                        }
                        catch(Exception e){
                            JOptionPane.showMessageDialog(prview, e+"from display rowvalues");
                        }
                       
            }
          //  JOptionPane.showMessageDialog(prview, st);
             return st;
        }
    }
    //doesnot work
    //for property chagne listener
    class SearchPropertyChangeListener implements PropertyChangeListener{

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            System.out.println(prview.getSearch()+"wala");
        }
        
    }
    
    class SearchDocumentListener implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
            ReloadTableModel();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            ReloadTableModel();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            ReloadTableModel();
        }
        private void ReloadTableModel(){
            try{
                prview.refreshJTable(prmodel.getItemListLike(prview.getSearch()));
                
            }
            catch(Exception se){
                
            }
        }
    }
    
     
}
