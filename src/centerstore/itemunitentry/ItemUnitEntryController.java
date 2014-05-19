/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.itemunitentry;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import reusableClass.DisplayMessages;

/**
 *
 * @author SUSHIL
 */
public class ItemUnitEntryController {
    private ItemUnitEntryModel themodel;
    private ItemUnitEntryView theview;
    //for the object into row listener
   // public ItemUnitEntryView viewtry = new ItemUnitEntryView();
    
    public ItemUnitEntryController(ItemUnitEntryModel model, ItemUnitEntryView view){
        this.themodel = model;
        theview = view;
        /**
         * adding mouse hit listener when add is click.
         */
        theview.addAddListener(new ItemUnitEntryListener());
        
        //for rowselected in jtable
        theview.addRowSelectedListener(new rowListener(theview));
        /**
         * adding mouse hit listener when delete is click.
         */
        theview.addDeleteListener(new ItemUnitEntryListener());
        /**
         * adding mouse hit listener when Update is click.
         */
        theview.addUpdateListener(new ItemUnitEntryListener());
         /**
         * adding mouse hit listener when cancel is click.
         */
        theview.addCancelListener(new ItemUnitEntryListener());
       
       
         /**
         * adding key pressed listener when add button is enter from keyboard.
         */
//        this.theview.addKeyAddListener(new ItemUnitEntryKeyListener());
        /**
         * adding key pressed listener when update button is enter from keyboard.
         */
//        this.theview.addKeyUpdateListener(new ItemUnitEntryKeyListener());
        /**
         * adding key pressed listener when delete button is enter from keyboard.
         */
//        this.theview.addKeyDeleteListener(new ItemUnitEntryKeyListener());
        /**
         * adding key pressed listener when cancel button is enter from keyboard.
         */
//        this.theview.addKeyCancelListener(new ItemUnitEntryKeyListener());
    }

 class  ItemUnitEntryListener implements ActionListener {
      String strUnitName;
         int strUnitRelativeQuantity;
         String strUnitType;
     @Override
     public void actionPerformed(ActionEvent ae){
         if(ae.getActionCommand().equalsIgnoreCase("Add"))
         {
        
                try{
                    strUnitName = theview.getUnitName().toLowerCase();
                    strUnitRelativeQuantity = theview.getUnitRelativeQuantity();
                    strUnitType = theview.getUnitType().toLowerCase();
                    if(strUnitName.equals("")||strUnitRelativeQuantity == 0||strUnitType.equals("")){
                   JOptionPane.showMessageDialog(theview, "There is Blank Data in form.recheck it");
                 }
                    if(theview.returnComboUnitType().getSelectedIndex() == 0){
                        JOptionPane.showMessageDialog(theview, "Please Select the Unit Type");
                        return;
                    }
                    themodel.addItemUnit("item_unit", strUnitName, strUnitRelativeQuantity, strUnitType);
                    theview.clearAllText();
                   // theview.displayJTable("item_unit");
                    //for retriving the Jtable from database
                    theview.refreshJTable(themodel.getTableModelForJTable("item_unit"));
//                     theview.disableAddBtn();
                    theview.disableDeleteBtn();
                    theview.disableUpdateBtn();
                }
                catch(NumberFormatException ne){
                    JOptionPane.showMessageDialog(theview, "Please enter right data");
                }
                catch(HeadlessException e){
                    JOptionPane.showMessageDialog(theview,e);
                   // e.printStackTrace();
                }
         }
         if(ae.getActionCommand().equalsIgnoreCase("delete")){
            int choice;
            int iUnitID;
            
            try{
             //strUnitName = theview.getUnitName();
             iUnitID = theview.getUnitID();
            // if(strUnitName.contains(null)||strUnitName.contains("")){
              //   throw new  NullPointerException();
             //}
                 if(iUnitID == 0){
                   JOptionPane.showMessageDialog(theview, "Please select the Data");
                        return;
                    }
                  if(theview.returnComboUnitType().getSelectedIndex() == 0){
                        JOptionPane.showMessageDialog(theview, "Please Select the Unit Type");
                        return;
                    }
                
             choice = JOptionPane.showConfirmDialog(theview, "Do you Want to Delete item Unit","Delete ItemUnit",JOptionPane.YES_NO_OPTION);
            // System.out.println(choice);
             if(choice == JOptionPane.YES_OPTION){
               
                    //JOptionPane.showMessageDialog(theview, themodel.getUnitID("item_unit", strUnitName));
                   
                    themodel.deleteUnitItem("item_unit",iUnitID );
                    theview.clearAllText();
                   theview.enableAddBtn();
                    theview.disableDeleteBtn();
                    theview.disableUpdateBtn();
                   /*
                    * refreshjtable wiil throw array index out of exceception in
                    * first and second time update the 
                    * table
                    */
                     
                    theview.refreshJTable(themodel.getTableModelForJTable("item_unit"));
                   //  theview.refreshJTable(themodel.getTableModelForJTable("item_unit"));
//                    theview.tblUnitJTable.repaint();
                   
                     
                
             }
            }
            catch(NumberFormatException nfe){
                 JOptionPane.showConfirmDialog(theview,nfe,"NumberFormat ",JOptionPane.CLOSED_OPTION); 
                 
             }
            catch(NullPointerException e){
                JOptionPane.showConfirmDialog(theview,e,"Null value",JOptionPane.CLOSED_OPTION);
                
            }
             
         }
         if(ae.getActionCommand().equalsIgnoreCase("Update")){
             int iUnitID;
             int choice;
            // boolean check = true;
             try {
//                iUnitID = theview.getUnitID();
                   iUnitID = theview.getUnitID();
                 strUnitName = theview.getUnitName();
                 
                 strUnitRelativeQuantity = theview.getUnitRelativeQuantity();
                 strUnitType = theview.getUnitType();
                if("".equals(iUnitID)){
                   JOptionPane.showMessageDialog(theview, "Please select the Data");
                        return;
                    }
                 if(strUnitName.equals("")||strUnitRelativeQuantity == 0||strUnitType.equals("")){
                   JOptionPane.showMessageDialog(theview, "Blank Field Not Allowed.Recheck it");
                   return;
                 }
                  if(theview.returnComboUnitType().getSelectedIndex() == 0){
                        JOptionPane.showMessageDialog(theview, "Please Select the Unit Type");
                        return;
                    }
                if(DisplayMessages.displayInputYesNo(theview, "Do you Want to Update item Unit","Update ItemUnit")){

                 //if true there is duplicate vlaue
               // boolean check = themodel.checkforDuplicate("item_unit", "unit_name", strUnitName);
               /*  if(check == true){
                     JOptionPane.showMessageDialog(theview, "Duplicate value Detected.");
                   //  themodel.checkforDuplicate("item_unit", strUnitName, strUnitName);
                     System.out.println(check);
                     return;
                 }
                 * */
                 
               //  System.out.println(strUnitName);
                // System.out.println(themodel.getUnitID("item_unit", strUnitName));
                 themodel.updateUnitItem("item_unit",iUnitID, strUnitName, strUnitRelativeQuantity, strUnitType);
                 theview.clearAllText();
                 //theview.displayJTable("item_unit");
                 theview.refreshJTable(themodel.getTableModelForJTable("item_unit"));
                   theview.enableAddBtn();
                    theview.disableDeleteBtn();
                    theview.disableUpdateBtn();
             }
             } 
             catch(NumberFormatException nfe){
                 JOptionPane.showMessageDialog(theview,"Number Format Misplaced.Integer only");
                 
             }
             catch(NullPointerException ne){
                JOptionPane.showMessageDialog(theview, "NUll Pointer from UpdateControl");
             }
         
         }
         if(ae.getActionCommand().equalsIgnoreCase("Cancel")){
             theview.clearAllText();
             theview.enableAddBtn();
             theview.disableDeleteBtn();
             theview.disableUpdateBtn();
         }
         
     }

       

    }
 /**
  * class/KeyListener for button enter in the corresponding jbutton
  */
 class ItemUnitEntryKeyListener implements KeyListener{
      String strUnitName;
         int UnitRelativeQuantity;
         String strUnitType;
      @Override
         public void keyReleased(KeyEvent ke){
           //  System.out.println(ke.getKeyLocation());
             
             
         }
      @Override
         public void keyPressed(KeyEvent ke){
          int code=0;
             //this check which button generated which events
           /*
            * when keyevent pressed is enter it occurs only
            */ 
          code=ke.getKeyCode();
          if(code == KeyEvent.VK_ENTER){
          /*
            * when enter is entered  on the add button it get executed
            */
              
             if( ke.getSource().toString().contains("Add")){
              //  System.out.println("add entered");
                 try{
                    strUnitName = theview.getUnitName().toLowerCase();
                    UnitRelativeQuantity = theview.getUnitRelativeQuantity();
                    strUnitType = theview.getUnitType().toLowerCase();
                    if(strUnitName.equals("")||UnitRelativeQuantity == 0||strUnitType.equals("")){
                   JOptionPane.showMessageDialog(theview, "There is Blank Data in form.recheck it");
                 }
                    themodel.addItemUnit("item_unit", strUnitName, UnitRelativeQuantity, strUnitType);
                    theview.clearAllText();
                   // theview.displayJTable("item_unit");
                    //for retriving the Jtable from database
                    theview.refreshJTable(themodel.getTableModelForJTable("item_unit"));
                     theview.disableAddBtn();
                    theview.disableDeleteBtn();
                    theview.disableUpdateBtn();
                }
                catch(NumberFormatException ne){
                    JOptionPane.showMessageDialog(theview, "Please enter right data");
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(theview,e);
                   // e.printStackTrace();
                }
                 
                 
            }
             /**
              * when enter is pressed when focus is on update button,it is excueted
              */
            if(ke.getSource().toString().contains("Update")){
                
              //  System.out.println("update entered");
                int iUnitId;
            // boolean check = true;
             try {
                iUnitId = theview.getUnitID();
                
                 strUnitName = theview.getUnitName();
                 
                 UnitRelativeQuantity = theview.getUnitRelativeQuantity();
                 strUnitType = theview.getUnitType();
                if(iUnitId == 0){
                   JOptionPane.showMessageDialog(theview, "Please select the Data");
                        return;
                    }
                 if(strUnitName.equals("")||UnitRelativeQuantity == 0||strUnitType.equals("")){
                   JOptionPane.showMessageDialog(theview, "Blank Field Not Allowed.Recheck it");
                   return;
                 }
                 //if true there is duplicate vlaue
               // boolean check = themodel.checkforDuplicate("item_unit", "unit_name", strUnitName);
               /*  if(check == true){
                     JOptionPane.showMessageDialog(theview, "Duplicate value Detected.");
                   //  themodel.checkforDuplicate("item_unit", strUnitName, strUnitName);
                     System.out.println(check);
                     return;
                 }
                 * */
                 
               //  System.out.println(strUnitName);
                // System.out.println(themodel.getUnitID("item_unit", strUnitName));
                 themodel.updateUnitItem("item_unit",iUnitId, strUnitName, UnitRelativeQuantity, strUnitType);
                 theview.clearAllText();
                 //theview.displayJTable("item_unit");
                 theview.refreshJTable(themodel.getTableModelForJTable("item_unit"));
                   theview.enableAddBtn();
                    theview.disableDeleteBtn();
                    theview.disableUpdateBtn();
             } 
             catch(NumberFormatException nfe){
                 JOptionPane.showMessageDialog(theview,"Number Format Misplaced.Integer only");
                 
             }
             catch(NullPointerException ne){
                JOptionPane.showMessageDialog(theview, "NUll Pointer from UpdateControl");
             }
                
            }
            /*
             * when enter is press when focus is on the jbutton delete ,it is executed
             */
            if(ke.getSource().toString().contains("Delete")){
                 int choice;
           int iUnitId;
        
            
            try{
             //strUnitName = theview.getUnitName();
             iUnitId = theview.getUnitID();
            // if(strUnitName.contains(null)||strUnitName.contains("")){
              //   throw new  NullPointerException();
             //}
                 if(iUnitId == 0){
                   JOptionPane.showMessageDialog(theview, "Please select the Data");
                        return;
                    }
                
             choice = JOptionPane.showConfirmDialog(theview, "Do you Want to Delete item Unit","Delete ItemUnit",JOptionPane.YES_NO_OPTION);
            // System.out.println(choice);
             if(choice == JOptionPane.YES_OPTION){
               
                    //JOptionPane.showMessageDialog(theview, themodel.getUnitID("item_unit", strUnitName));
                   
                    themodel.deleteUnitItem("item_unit",iUnitId );
                    theview.clearAllText();
                   /*
                    * refreshjtable wiil throw array index out of exceception in
                    * first and second time update the 
                    * table
                    */
                    theview.refreshJTable(themodel.getTableModelForJTable("item_unit"));
                     theview.refreshJTable(themodel.getTableModelForJTable("item_unit"));
                      theview.enableAddBtn();
                    theview.disableDeleteBtn();
                    theview.disableUpdateBtn();
                     //theview.refreshJTable(themodel.getTableModelForJTable("item_unit"));
                   
                    //theview.displayJTable("item_unit");
                //    theview.displayJTable("item_unit");
                
             }
            }
            catch(NumberFormatException nfe){
                 JOptionPane.showConfirmDialog(theview,nfe,"NumberFormat ",JOptionPane.CLOSED_OPTION); 
                 
             }
            catch(NullPointerException e){
                JOptionPane.showConfirmDialog(theview,e,"Null value",JOptionPane.CLOSED_OPTION);
                
            }
                
            }
            /*
             * when enter is press when it is focus on the jbutton cance, it is excuted
             */
            if(ke.getSource().toString().contains("Cancel")){
                theview.clearAllText();
                 theview.enableAddBtn();
                    theview.disableDeleteBtn();
                    theview.disableUpdateBtn();
                
            }
          }
            
         }
         public void keyTyped(KeyEvent ke){
             
         }
     
 }
 /**
  * Class/EventListener for RowSelection in Jtable
  */
 class rowListener implements ListSelectionListener {
    ItemUnitEntryView view;
    JTable jtable;
    
    public rowListener(ItemUnitEntryView view){
        this.view = view;
        jtable= this.view.tblUnitJTable;
    }
    @Override
    public void  valueChanged(ListSelectionEvent e){
        //ListSelectionModel model = e.getSource();
        try{
        if(e.getValueIsAdjusting()){
            return;
        }
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        
        if(lsm.isSelectionEmpty()){
            //nothing is selected
        }
        else{
             ListSelectionModel model = jtable.getSelectionModel();
            int lead = model.getLeadSelectionIndex();
            //displayRowValues(lead);
           
            //JOptionPane.showMessageDialog(null,displayRowValues(lead));
         //  for(String test:displayRowValues(lead)){
             //   System.out.println(test);
           // }
            this.view.setUnitItem(displayRowValues(lead));
           // this.view.SetUnitType(this.view.getUnitType());
           view.disableAddBtn();
           view.enableDeleteBtn();
           view.enableUpdateBtn();
            
        }
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(view, e+"from returnrowlistener");
        }
        /*
        if(!e.getValueIsAdjusting()){
            ListSelectionModel model = jtable.getSelectionModel();
            int lead = model.getLeadSelectionIndex();
            //displayRowValues(lead);
           
            //JOptionPane.showMessageDialog(null,displayRowValues(lead));
         //  for(String test:displayRowValues(lead)){
             //   System.out.println(test);
           // }
            this.view.setUnitItem(displayRowValues(lead));
           // this.view.SetUnitType(this.view.getUnitType());
           view.disableAddBtn();
           view.enableDeleteBtn();
           view.enableUpdateBtn();
          
            }
            * */
            
    }
    private String[] displayRowValues(int rowIndex){
        int columns = jtable.getColumnCount();
       // String s = "";
        
        String st[] = new String[columns];
        
        for(int col=0;col<columns;col++){
            Object o =jtable.getValueAt(rowIndex, col);
          
           try{
                st[col] = o.toString(); 
               // System.out.println(st[col]);
                }
            catch(NullPointerException e){
                //System.err.println(e.getMessage());
                JOptionPane.showMessageDialog(view, e);
                }
           catch(ArrayIndexOutOfBoundsException ee){
               System.out.println(ee+"from controller");
           }
        }
        
        return st;
    }
}
 
 
}