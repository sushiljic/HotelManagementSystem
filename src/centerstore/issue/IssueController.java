/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.issue;
import java.awt.HeadlessException;
import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import reusableClass.DisplayMessages;

/**
 *
 * @author SUSHIL
 */
public class IssueController {
    
    private  IssueModel issueModel;
    private IssueView issueView ;
    private Float prevIssueQuantity;
    
    IssueController(IssueModel model,IssueView view){
     issueModel = model;
     issueView = view;
   
     /*
      * adding the listener for the mouse click and key listener
      */
     issueView.addIssueListener(new IssueListener());
//     issueView.addKeyIssueListener(new IssueKeyListener());
     
     issueView.addEditListener(new IssueListener());
//     issueView.addKeyEditListener(new IssueKeyListener());
     
     issueView.addCancelListener(new IssueListener());
//     issueView.addKeyCancelListener(new IssueKeyListener());
     
     issueView.addPrintListener(new IssueListener());
//     issueView.addKeyPrintListener(new IssueKeyListener());
     
     issueView.addSearchListener(new IssueListener());
//     issueView.addKeySearchListener(new IssueKeyListener());
     /*
      * adding listner for text search
      */
     issueView.addTextSearchListener(new TextIssueListener());
     /*
      * adding the listener for the selecting the row in the table 
      */
    issueView.addRowSelectedListener(new returnRowSelectedListener(issueView));
    /*
     * this is when itemname is selected on the combo
     */
     issueView.addComboItemNameSelectListener(new ComboIssueListener());
     /*
      * this is when itembaseunit is  trigered 
      */
      issueView.addComboItemBaseUnitSelectListener(new ComboItemBaseUnitListener());
      //setting action when combo issue from and to are trigered
      issueView.addComboStoreFromListener(new ComboStoreFromListener());
      issueView.addComboStoreToListener(new ComboStoreToListener());
      issueView.addShortcutForIssue(new ShortcutForIssue());
      //add the document listener for  the search
//      issueView.addtxtSearchDocumentListener(new SearchDocumentListener());
        /*
      * setting 
      */
     try{
      issueView.setcomboItemName(model.returnItemName(model.getItemInfoForIssue()));
      issueView.AddSelectInCombo(issueView.returnComboItemName());
      //for ComboStore from and to
      issueView.setcomboIssueFrom(model.returnItemName(model.getCenterStoreName()));
      issueView.AddSelectInCombo(issueView.returnComboStoreFrom());
      issueView.setcomboIssueTo(model.returnItemName(model.getOtherStoreName()));
      issueView.AddSelectInCombo(issueView.returnComboStoreTo());
      
//      for(String st:model.returnItemName(model.getItemInfoForIssue())){
//          System.out.println(st);
//      }
        /*
         * this by default display the issue table 
         */
        issueView.refreshJTable(model.getIssueList());
     }
     catch(Exception e){
         JOptionPane.showMessageDialog(issueView, e+" from IssueController Constructor "+getClass().getName());
     }
     
    }
    
    class IssueListener implements ActionListener{
        float UnitRelativeQuantity;

        @Override
        public void actionPerformed(ActionEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       if(e.getActionCommand().equalsIgnoreCase("Issue")){
           try{
               if( issueView.getItemId() == 0){
                   JOptionPane.showMessageDialog(issueView, "Please Select the Item name to Issue");
                   return;
               }
             
              
               if(  issueView.getIssueQuantity().equals("")|| issueView.getIssueQuantity() == 0){
                 JOptionPane.showMessageDialog(issueView, "Are You Crazy.Issue quantity is Empty");
                   return;   
               }
               if(issueView.getIssueQuantity() > Float.parseFloat(issueView.getCenterStockQuantity())){
                   JOptionPane.showMessageDialog(issueView, "Issue Quantity can`t be greater than Stock Quantity");
                   return;
               }
               if(issueView.getFromStoreId()==0){
                    JOptionPane.showMessageDialog(issueView, "Please Select the Store From which Item is to be Issued");
                   return;
               }
                if(issueView.getToStoreId()==0){
                    JOptionPane.showMessageDialog(issueView, "Please Select the Store to which Item is to be Issued.");
                   return;
               }
              //obtaing the status is true is item exist in the certain stored_id wwhere store_id is provided as parameter and if not present false
               boolean status = issueModel.checkExistingNameInStore(issueView.getItemId(),issueView.getToStoreId());
//               System.out.println(status);
               issueModel.issueItem(issueView.getIssueInfo(),status);
               
              // JOptionPane.showMessageDialog(issueView, "Item Issued Succesfully");
               /*
                * this reset the the issueform
                */
             //  System.out.println(issueView.getcomboItemBaseUnit());
               issueView.setSearch("");
               issueView.setCenterStockQuantity("");
               issueView.setIssueId(0);
               issueView.setItemId(0);
               issueView.setIssueQuantity(0);
               issueView.returnComboItemName().setSelectedIndex(0);
               //for combobox to and from
                 //for ComboStore from and to
      issueView.setcomboIssueFrom(issueModel.returnItemName(issueModel.getCenterStoreName()));
      issueView.AddSelectInCombo(issueView.returnComboStoreFrom());
      issueView.setcomboIssueTo(issueModel.returnItemName(issueModel.getOtherStoreName()));
      issueView.AddSelectInCombo(issueView.returnComboStoreTo());
               
             //  issueView.setItemBaseUnit("");
               
               
              
               /*
                * refreshing the combo and the object[][]
                */
               issueView.setcomboItemName(issueModel.returnItemName(issueModel.getItemInfoForIssue()));
              issueView.AddSelectInCombo(issueView.returnComboItemName());
               //issueView.showPrintbtn();
               //issueView.clearAll();
                /*
                * this refreash the jtable
                */
               issueView.refreshJTable(issueModel.getIssueList());
               
           }
           catch(HeadlessException | NumberFormatException ee){
               JOptionPane.showMessageDialog(issueView, ee+"Error detected in the issue "+getClass().getName());
           }
       
       }
       if(e.getActionCommand().equalsIgnoreCase("print")){
           JOptionPane.showMessageDialog(issueView, "MOdule still to be added");
       }
       
       if(e.getActionCommand().equalsIgnoreCase("Edit")){
          try{ 
            if("".equals(issueView.getIssueId())){
                   JOptionPane.showMessageDialog(issueView, "Please Select the Item name from the table below to edit");
                   return;
               }
          /*  System.out.println((Float.parseFloat(issueView.getCenterStockQuantity())));
             System.out.println(issueView.getResturantStockQuantity());
             System.out.println(prevIssueQuantity);
              System.out.println();*/
               //for edit if the quantity is greater than the prevvois issue+ stock quantity which mean actual quantity
              if(issueView.getIssueQuantity() > (Float.parseFloat(issueView.getCenterStockQuantity())+prevIssueQuantity)){
                   JOptionPane.showMessageDialog(issueView, "Issue Quantity can`t be greater than CenterStore Stock Quantity");
                   return;
               }
             /* if(!(Float.parseFloat(issueView.getIssueQuantity())<(Float.parseFloat(issueView.getResturantStockQuantity())-prevIssueQuantity))){
               JOptionPane.showMessageDialog(issueView, "Issue Quantity is not suficient to be edited");
               return;
           }
           * */
             if((Float.parseFloat(issueView.getResturantStockQuantity())-prevIssueQuantity+issueView.getIssueQuantity())< 0){
                JOptionPane.showMessageDialog(issueView, "Operation for Edit Quantiiy is not allowed.");
               return;  
             }
               if(DisplayMessages.displayInputYesNo(issueView,"Do you Want to Edit the issued item?","Edit Issued Item"))
               //{"Issue Id","Item Name","Issue Quantity","Item BaseUnit","Issue From","Issue To","Issue Date"};
                {
               /*
                * here previssueQuantity is send as parameter inorder to obtaing the autual quantity that
                * need to be update inorder for correct result
                */
               issueModel.issueEdit(issueView.getIssueEditInfo(),prevIssueQuantity,issueView.getUnitRelativeQuantity());
              // issueView.refreshJTable(issueModel.getIssueList());
                  /*
                * this reset the the issueform
                */
             //  System.out.println(issueView.getIssueEditInfo()//);
               issueView.setSearch("");
               issueView.setCenterStockQuantity("");
               issueView.setIssueId(0);
               issueView.setItemId(0);
               issueView.setIssueQuantity(0);
//               issueView.setItemBaseUnit("");
               
               
               /*
                * this refreash the jtable
                */
               issueView.refreshJTable(issueModel.getIssueList());
               /*
                * refreshing the combo and the object[][]
                */
               issueView.setcomboItemName(issueModel.returnItemName(issueModel.getItemInfoForIssue()));
                issueView.AddSelectInCombo(issueView.returnComboItemName());
//issueView.clearAll();
               issueView.hideResturant();
               issueView.enableIssueBtn();
            
               
           }
            }
          catch(HeadlessException | NumberFormatException se){
               JOptionPane.showMessageDialog(issueView, se+"From edit");
           }
         
       }
       if(e.getActionCommand().equalsIgnoreCase("cancel")){
           //issueModel.getItemInfoForIssue();
          
           issueView.setcomboItemName(issueModel.returnItemName(issueModel.getItemInfoForIssue()));
           issueView.AddSelectInCombo(issueView.returnComboItemName());
            issueView.clearAll();
           issueView.hideResturant();
           //issueView.enableIssueBtn();
           issueView.disableEditBtn();
           issueView.disableIssueBtn();
             //for ComboStore from and to
      issueView.setcomboIssueFrom(issueModel.returnItemName(issueModel.getCenterStoreName()));
      issueView.AddSelectInCombo(issueView.returnComboStoreFrom());
      issueView.setcomboIssueTo(issueModel.returnItemName(issueModel.getOtherStoreName()));
      issueView.AddSelectInCombo(issueView.returnComboStoreTo());
       }
       if(e.getActionCommand().equalsIgnoreCase("Search")){
           String strSearch = null;
           boolean flag = false;
           try{
               //issueView.clearAll();
               /*
                * clearing all data
                */ 
               issueView.setCenterStockQuantity("");
               
//               issueView.setcomboItemName(issueModel.returnItemName(issueModel.getItemInfoForIssue()));
               strSearch = issueView.getSearch();
               for (String returnItemName : issueModel.returnItemName(issueModel.getItemInfoForIssue())) {
                   if (strSearch.equalsIgnoreCase(returnItemName)) {
                       issueView.comboItemName.setSelectedItem(returnItemName);
                       // JOptionPane.showMessageDialog(issueView, "item found");
                       issueView.setIssueQuantity(0);
                       issueView.disableEditBtn();
                       flag = true;
                       break;
                   }
               }
               if(flag==false)
                   JOptionPane.showMessageDialog(issueView, "Item Not found");
               
           }
           catch(HeadlessException srce){
               JOptionPane.showMessageDialog(issueView, srce+"from Search");
           }
           finally{
               flag=false;
           }
       }
       
        }
       
        
    }
    class TextIssueListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
          String strSearch = null;
           boolean flag = false;
           try{
              issueView.setCenterStockQuantity("");
              issueView.setcomboItemName(issueModel.returnItemName(issueModel.getItemInfoForIssue()));
               
               strSearch = issueView.getSearch();
               for(int col =0;col<issueView.comboItemName.getItemCount();col++){
                   if(strSearch.equalsIgnoreCase(issueModel.returnItemName(issueModel.getItemInfoForIssue())[col])){
                       issueView.comboItemName.setSelectedItem(issueModel.returnItemName(issueModel.getItemInfoForIssue())[col]);
                       issueView.enableIssueBtn();
                       issueView.disableEditBtn();
                       flag = true;
                       break;
                   }
                   
               }
               if(flag==false)
                   JOptionPane.showMessageDialog(issueView, "Item Not found");
               
           }
           catch(Exception srce){
               JOptionPane.showMessageDialog(issueView, srce+"from Search");
           }
        
        }
        
    }
    
    class ComboIssueListener implements ActionListener{
        int row;
      
    private    Object item[] =null;
    private Object[][] ItemUnitInfo;
        @Override
        public void actionPerformed(ActionEvent e) {
          /*
           * SELECT centerstore_stock.item_id,centerstore_stock.item_name,centerstore_stock.unit_id,item_unit.unit_name,item_unit.unit_relative_quantity,item_unit.unit_type,centerstore_stock.total_qty,centerstore_stock.item_expiry_date 
           * FROM centerstore_stock,item_unit 
           * WHERE centerstore_stock.unit_id = item_unit.unit_id
           */
        try{
            
            JComboBox cb = (JComboBox)e.getSource();
            
            if(cb.getSelectedItem().equals("SELECT")){
                issueView.setItemId(0);
            }
            else{
            for (Object[] Itemdata : issueModel.Itemdata) {
                if (Itemdata[1].equals(cb.getSelectedItem())) {
                    item = Itemdata;
                    break;
                }
                // Systeim.out.println(issueModel.Itemdata[i][1]);
            }
            issueView.setOnComboItemNameSelect(item);
         ItemUnitInfo = issueModel.getUnitInfo(issueView.getUnitId());
           //SetonComboItemNameSelect Doesnot selecet combo itemBaseUnit done mannually by another fucntion
            
        
            /*
             * this load all the relative unitname that can be used to issue the item
             */
           // JOptionPane.showMessageDialog(issueView, ItemUnitInfo);
            issueView.setcomboItemBaseUnit(issueModel.returnItemBaseUnit(ItemUnitInfo));
         // System.out.println(issueView.getItemBaseUnit());
            /*
             * this is used to select the required basunit
             */
            issueView.setComboItemBaseUnitSelected(issueView.getItemBaseUnit());
            
          // System.out.println(issueView.getcomboItemBaseUnit());
            issueView.setTextEditableFalse();
            issueView.setIssueQuantity(0);
            //JOptionPane.showMessageDialog(issueView, issueView);
            issueView.enableIssueBtn();
            }
        }
        catch(Exception ex){
           JOptionPane.showMessageDialog(issueView, ex+"From ComdoIssueListener");
        }
        finally{
          //  item = null;
        }
        }

      
   /*
    * inner class of comboissueListener
    */        
     
        
    }
    /*
     * listener of the comboitembaseunitlistener
     */
      class ComboItemBaseUnitListener implements ActionListener{
    private Object[] items = null;
    private Object[][] ItemUnitInfo;
      float TotalQuantity =0;
   /* float totalquantity;
        ComboItemBaseUnitListener(float qty){
          totalquantity =qty;  
        }*/
    
     
            @Override
            public void actionPerformed(ActionEvent ee) {
                try{
          ItemUnitInfo =    issueModel.getUnitInfo(issueView.getUnitId());
                    JComboBox cbj = (JComboBox) ee.getSource();
                    
                    for (Object[] ItemUnitInfo1 : ItemUnitInfo) {
                        //issueModel.Itemdata[i][3] represent unitname
                        if (ItemUnitInfo1[1].equals(cbj.getSelectedItem())) {
                            items = ItemUnitInfo1;
                            break;
                        }
                    }
                    /*
                     * this setthe stockquantity to its respective unit
                     */
                       TotalQuantity =issueView.getTotalQty();
                // JOptionPane.showMessageDialog(issueView, items);
                    issueView.setCenterStockQuantity(String.valueOf(TotalQuantity/Float.parseFloat(items[2].toString())));
                   issueView.setComboItemBaseUnitSelected(items[1].toString());
                   issueView.setUnitId(Integer.parseInt(items[0].toString()));
               //   System.out.println(items[0]);
                    // issueView.setcom
                }
                catch(Exception see){
                    JOptionPane.showMessageDialog(issueView, see+"for comboItemBaseUnitListener");
                }
            }
           
       }
      class ComboStoreFromListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object[][] centstoreinfo = null;
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
         JComboBox combo = (JComboBox) e.getSource();
         if(combo.getSelectedItem().equals("SELECT")){
             issueView.setFromStoreId(0);
             
            
         }
         else{
             centstoreinfo = issueModel.getCenterStoreName();
             for(Object[] cent:centstoreinfo){
                 if(cent[1].equals(combo.getSelectedItem())){
                     issueView.setFromStoreId(Integer.parseInt(cent[0].toString()));
//                     JOptionPane.showMessageDialog(null, "wala");
                     break;
                 }
             }
         }
         
                
         
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(issueView, e+"from ComboStoreListener "+getClass().getName());
        }
        }
          
      }
       class ComboStoreToListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          Object[][] storeinfo = null;
            try{
              JComboBox combo = (JComboBox) e.getSource();
         if(combo.getSelectedItem().equals("SELECT")){
             issueView.setToStoreId(0);
//             System.out.println("wala");
            
         }
         else{
             storeinfo = issueModel.getOtherStoreName();
             for(Object[] cent:storeinfo){
                 if(cent[1].equals(combo.getSelectedItem())){
                     issueView.setToStoreId(Integer.parseInt(cent[0].toString()));
//                     System.out.println(issueView.getToStoreId());
                     break;
                 }
             }
         }
         
          
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(issueView, e+"from StoreToListener "+getClass().getName());
        }
        }
          
      }
//    class IssueKeyListener implements KeyListener{
//
//        @Override
//        public void keyTyped(KeyEvent e) {
//          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void keyPressed(KeyEvent e) {
//          int code =0;
//          code = e.getKeyCode();
//          if(code == KeyEvent.VK_ENTER){
//                        if(e.getSource().toString().contains("Issue")){
//                          try{
//               if( issueView.getItemId().equals("")){
//                   JOptionPane.showMessageDialog(issueView, "Please Select the Item name to Issue");
//                   return;
//               }
//               /*
//                * temporary solution for finding the uNitRelative Quantity
//                */
//             //  UnitRelativeQuantity =issueModel.getUnitRelativeQuantity(issueView.getIssueQuantity());
//               
//               //
//               if(Float.parseFloat(issueView.getIssueQuantity()) == 0 || issueView.getIssueQuantity().equals(" ")){
//                 JOptionPane.showMessageDialog(issueView, "Are You Crazy.Issue quantity is zero");
//                   return;   
//               }
//               if(Float.parseFloat(issueView.getIssueQuantity()) > Float.parseFloat(issueView.getCenterStockQuantity())){
//                   JOptionPane.showMessageDialog(issueView, "Issue Quantity can be greater than Stock Quantity");
//                   return;
//               }
//              //obtaing the status is true is item exist in the resturant_store and else false
//               boolean status = issueModel.checkExistingNameInStore(issueView.getItemId());
//               issueModel.issueItem(issueView.getIssueInfo(),status);
//               
//              // JOptionPane.showMessageDialog(issueView, "Item Issued Succesfully");
//               /*
//                * this reset the the issueform
//                */
//             //  System.out.println(issueView.getcomboItemBaseUnit());
//               issueView.setSearch("");
//               issueView.setCenterStockQuantity("");
//               issueView.setIssueId("");
//               issueView.setItemId("");
//               issueView.setIssueQuantity("");
//               
//             //  issueView.setItemBaseUnit("");
//               
//               
//               /*
//                * this refreash the jtable
//                */
//               issueView.refreshJTable(issueModel.getIssueList());
//               /*
//                * refreshing the combo and the object[][]
//                */
//               issueView.setcomboItemName(issueModel.returnItemName(issueModel.getItemInfoForIssue()));
//               //issueView.showPrintbtn();
//               //issueView.clearAll();
//               
//           }
//           catch(Exception ee){
//               JOptionPane.showMessageDialog(issueView, ee+"Error detected in the issue");
//           }
//                  
//              }
//              if(e.getSource().toString().contains("Print")){
//                  JOptionPane.showMessageDialog(issueView, "Module still to come");
//              }
//              if(e.getSource().toString().contains("Edit")){
//                               try{ 
//            if("".equals(issueView.getIssueId())){
//                   JOptionPane.showMessageDialog(issueView, "Please Select the Item name from the table below to edit");
//                   return;
//               }
//          /*  System.out.println((Float.parseFloat(issueView.getCenterStockQuantity())));
//             System.out.println(issueView.getResturantStockQuantity());
//             System.out.println(prevIssueQuantity);
//              System.out.println();*/
//               //for edit if the quantity is greater than the prevvois issue+ stock quantity which mean actual quantity
//              if(Float.parseFloat(issueView.getIssueQuantity()) > (Float.parseFloat(issueView.getCenterStockQuantity())+prevIssueQuantity)){
//                   JOptionPane.showMessageDialog(issueView, "Issue Quantity can be greater than CenterStore Stock Quantity");
//                   return;
//               }
//             /* if(!(Float.parseFloat(issueView.getIssueQuantity())<(Float.parseFloat(issueView.getResturantStockQuantity())-prevIssueQuantity))){
//               JOptionPane.showMessageDialog(issueView, "Issue Quantity is not suficient to be edited");
//               return;
//           }
//           * */
//             if((Float.parseFloat(issueView.getResturantStockQuantity())-prevIssueQuantity+Float.parseFloat(issueView.getIssueQuantity()))< 0){
//                JOptionPane.showMessageDialog(issueView, "Operation for Edit Quantiiy is not allowed.");
//               return;  
//             }
//               int choice = 0;
//               choice = JOptionPane.showConfirmDialog(issueView,"Do you Want to Edit the issued item?","Edit Issued Item",JOptionPane.YES_NO_OPTION);
//               //{"Issue Id","Item Name","Issue Quantity","Item BaseUnit","Issue From","Issue To","Issue Date"};
//           if(choice == JOptionPane.YES_OPTION){
//               /*
//                * here previssueQuantity is send as parameter inorder to obtaing the autual quantity that
//                * need to be update inorder for correct result
//                */
//               issueModel.issueEdit(issueView.getIssueEditInfo(),prevIssueQuantity,issueView.getUnitRelativeQuantity());
//              // issueView.refreshJTable(issueModel.getIssueList());
//                  /*
//                * this reset the the issueform
//                */
//             //  System.out.println(issueView.getIssueEditInfo()//);
//               issueView.setSearch("");
//               issueView.setCenterStockQuantity("");
//               issueView.setIssueId("");
//               issueView.setItemId("");
//               issueView.setIssueQuantity("");
////               issueView.setItemBaseUnit("");
//               
//               
//               /*
//                * this refreash the jtable
//                */
//               issueView.refreshJTable(issueModel.getIssueList());
//               /*
//                * refreshing the combo and the object[][]
//                */
//               issueView.setcomboItemName(issueModel.returnItemName(issueModel.getItemInfoForIssue()));
//               //issueView.clearAll();
//               issueView.hideResturant();
//               issueView.enableIssueBtn();
//            
//               
//           }
//            }
//          catch(Exception se){
//               JOptionPane.showMessageDialog(issueView, se+"From edit");
//           }
//                  
//              }
//              if(e.getSource().toString().contains("Cancel")){
//                   //issueModel.getItemInfoForIssue();
//           issueView.clearAll();
//           issueView.setcomboItemName(issueModel.returnItemName(issueModel.getItemInfoForIssue()));
//           issueView.hideResturant();
//           //issueView.enableIssueBtn();
//           issueView.disableEditBtn();
//           issueView.disableIssueBtn();
//                  
//              }
//              if(e.getSource().toString().contains("Search")){
//                   String strSearch = null;
//           boolean flag = false;
//           try{
//               //issueView.clearAll();
//               /*
//                * clearing all data
//                */ 
//               issueView.setCenterStockQuantity("");
//               
//               issueView.setcomboItemName(issueModel.returnItemName(issueModel.getItemInfoForIssue()));
//               strSearch = issueView.getSearch();
//               for(int col =0;col<issueModel.returnItemName(issueModel.getItemInfoForIssue()).length;col++){
//                   if(strSearch.equalsIgnoreCase(issueModel.returnItemName(issueModel.getItemInfoForIssue())[col])){
//                       issueView.comboItemName.setSelectedItem(issueModel.returnItemName(issueModel.getItemInfoForIssue())[col]);
//                      // JOptionPane.showMessageDialog(issueView, "item found");
//                        issueView.setIssueQuantity("");
//                        issueView.disableEditBtn();
//                       flag = true;
//                       break;
//                   }
//                   
//               }
//               if(flag==false)
//                   JOptionPane.showMessageDialog(issueView, "Item Not found");
//               
//           }
//           catch(Exception srce){
//               JOptionPane.showMessageDialog(issueView, srce+"from Search");
//           }
//           finally{
//               flag=false;
//           }
//                  
//              }
//          }
//            
//        }
//
//        @Override
//        public void keyReleased(KeyEvent e) {
//        //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//        
//    }
    class returnRowSelectedListener implements ListSelectionListener{
        IssueView iview;
        JTable jtable;
       public returnRowSelectedListener(IssueView view){
            iview = view;
            jtable = iview.tblIssueList;
        }
        
        @Override
        public void valueChanged(ListSelectionEvent e) {
        //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         try{
        if(e.getValueIsAdjusting()){
            return;
        }
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        
        if(lsm.isSelectionEmpty()){
            //nothing is selected
        }
        else{
             ListSelectionModel rowmodel = jtable.getSelectionModel();
            int lead = rowmodel.getLeadSelectionIndex();
          
            iview.setIssueInfo(displayRowValues(lead));
          // System.out.print(issueModel.getStockQuantityfromDatabase(iview.getcomboItemName()));
            /*
             * this set the curent stock quantiy remain in the centerstore+stock 
             */
            //this private int prevIssueQuantity is stored when the userr click on the table
            prevIssueQuantity = iview.getIssueQuantity();
            /*
             * retreiving the unitrelativeQuantity to get actual quantity of item
             */
            Float UnitRelativeQuantity = issueModel.getUnitRelativeQuantity(issueModel.getUnitIdByIssueId(iview.getIssueId()));
           // System.out.println(iview.getIssueId());
           // System.out.println(UnitRelativeQuantity);
            iview.setUnitRelativeQuantity(UnitRelativeQuantity);
            iview.setResturantStockQuantity(String.valueOf(issueModel.getStockQuantityfromResturantStore(iview.getIssueId())/UnitRelativeQuantity));
            iview.setCenterStockQuantity(String.valueOf(issueModel.getStockQuantityfromCenterStoreByIssueId(iview.getIssueId())/UnitRelativeQuantity));
            iview.setTextEditableFalse();
            iview.showResturant();
           
            iview.disableIssueBtn();
//            iview.enableEditBtn();
            
        }
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(iview, e+"from returnrowlistener");
        }
         
         /*   if(!e.getValueIsAdjusting()){
            ListSelectionModel rowmodel = jtable.getSelectionModel();
            int lead = rowmodel.getLeadSelectionIndex();
          
            iview.setIssueInfo(displayRowValues(lead));
         
            prevIssueQuantity = Float.parseFloat(iview.getIssueQuantity());
          
            Float UnitRelativeQuantity = issueModel.getUnitRelativeQuantity(issueModel.getUnitIdByIssueId(iview.getIssueId()));
           // System.out.println(iview.getIssueId());
           // System.out.println(UnitRelativeQuantity);
            iview.setUnitRelativeQuantity(UnitRelativeQuantity);
            iview.setResturantStockQuantity(String.valueOf(issueModel.getStockQuantityfromResturantStore(iview.getIssueId())/UnitRelativeQuantity));
            iview.setCenterStockQuantity(String.valueOf(issueModel.getStockQuantityfromCenterStoreByIssueId(iview.getIssueId())/UnitRelativeQuantity));
            iview.setTextEditableFalse();
            iview.showResturant();
            iview.disableIssueBtn();
            iview.enableEditBtn();
       
        }
        */
       
        }
         private String[] displayRowValues(int lead){
            int columns = jtable.getColumnCount();
            String[] st = new String[columns];
            for(int i = 0;i<columns;i++){
                Object o = jtable.getValueAt(lead, i);
                st[i] = o.toString();
                        
            }
            return st;
            
        }
    }
    //class to hanlde shortcut for insert and home key
    public class ShortcutForIssue implements KeyEventDispatcher{

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
        try{
            if(issueView.isActive()){
                if(e.getKeyCode() == KeyEvent.VK_INSERT){
                    //setfocus on txtcustomername
                    issueView.getComboItemName().requestFocusInWindow();
                }
                if(e.getKeyCode() == KeyEvent.VK_HOME){
                    issueView.getTblIssueList().requestFocusInWindow();
                }
            }
            
        }
        catch(Exception se){
            DisplayMessages.displayError(issueView, se.getMessage()+"from shortcutforissue"+getClass().getName(), "Error");
        }
        return false;
        }
        
    }
    //class to handle like search
    public class SearchDocumentListener implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
            retreiveSearch(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            retreiveSearch(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            retreiveSearch(e);
        }
        //function to handle the search
        private void retreiveSearch(DocumentEvent se){
            try{
                SwingUtilities.invokeLater(new Runnable(){

                    @Override
                    public void run() {
                    //retreive the update the database
                        issueView.refreshJTable(issueModel.getIssueListByWildSearch(issueView.getSearch()));
                    }
                    
                });
                
            }
            catch(Exception ee){
                DisplayMessages.displayError(issueView, ee.getMessage() +" from " + getClass().getName(),"Error");
            }
        }
    }
}
