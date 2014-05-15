/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package resturant.wastage;

import hotelmanagementsystem.MainFrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import reusableClass.DisplayMessages;
import reusableClass.Function;

/**
 *
 * @author SUSHIL
 */
public class WastageController {
    private WastageView wastageView;
    private WastageModel wastageModel;
    private MainFrameView mainview;
//    private Object[][] unitinfo;
    public  WastageController(WastageModel model,WastageView view,MainFrameView mview){
        wastageModel = model;
        wastageView = view;
        mainview = mview;
        //adding the controller model 
        wastageView.addMenuSaveListener(new MenuSaveCancelListener());
        wastageView.addMenuCancelListener(new MenuSaveCancelListener());
        wastageView.addItemSaveListener(new ItemSaveCancelListener());
        wastageView.addDepartmentListener(new ComboMenuListener());
        wastageView.addMenuNameListener(new ComboMenuListener());
        wastageView.addItemNameListener(new ComboMenuListener());
        wastageView.addItemBaseUnitListener(new ComboMenuListener());
        wastageView.addMenuQuantityDocumentListener(new MenuDocumentListener());
        try{
        wastageView.setComboBoxDepartmentName(Function.returnSecondColumn(Function.getRespectiveDepartment(mainview.getUserId())));
        //if it has only one element select it order wise add select into it
            int combosize = wastageView.returnComboBoxDepartmentName().getModel().getSize();
            if(combosize >1){
                Function.AddSelectInCombo(wastageView.returnComboBoxDepartmentName());
            }
            else{
                if(combosize == 1){
                wastageView.returnComboBoxDepartmentName().setSelectedIndex(0);
                }
            }
         //for stafff info in  menu tab
        wastageView.setComboBoxMenuStaffName(Function.returnSecondColumn(wastageModel.getWaiterInfoObject()));
        Function.AddSelectInCombo(wastageView.returnComboBoxMenuStaffName());
        //for staff info in item tab
        wastageView.setComboBoxItemStaffName(Function.returnSecondColumn(wastageModel.getWaiterInfoObject()));
        Function.AddSelectInCombo(wastageView.returnComboBoxItemStaffName());
        }
        catch(Exception se){
        DisplayMessages.displayError(view,se.getMessage(),"Error Window");
        }
       
    }
    public class MenuSaveCancelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("MenuSave")){
            //validating the imputs\
                int menuid = wastageView.getMenuID();
            if(wastageView.getMenuID() == 0){
                DisplayMessages.displayWarning(wastageView, "Please Select the Menu Item","Validation Window");
                return;
            }
            if(wastageView.getMenuQuantity() == 0.0){
                DisplayMessages.displayWarning(wastageView, "Quantiy is Zero","Validation Window");
                return; 
            }
            if(wastageView.getMenuReason().isEmpty()){
                 DisplayMessages.displayWarning(wastageView, "Reason is Compulsary for Wastage Entry","Validation Window");
                return;
            }
            //check if there is sufficient stock os that it is wasted
            if(wastageModel.checkTrackable(menuid)){
                //if it is trackable
                if(wastageModel.checkHybrid(menuid)){
                    //if it is hybrid type
                    Float availablestock = wastageModel.getHybridItemAvailable(menuid).floatValue();
                    if(availablestock < wastageView.getMenuQuantity()){
                        DisplayMessages.displayInfo(mainview, "Menu Is Not Sufficent for wastage", "Info");
                        return;
                    }
                }
                else{
                    //if it is single trackable
                    Float availablestock = wastageModel.getSingleItemAvailable(menuid).floatValue();
                     if(availablestock < wastageView.getMenuQuantity()){
                        DisplayMessages.displayInfo(mainview, "Menu Is Not Sufficent for wastage", "Info");
                        return;
                    }
                }
            }
            
           
            if(DisplayMessages.displayInputYesNo(mainview, "Staff Name is not Selected.\n Are You Sure You Want to Save", "Staff Window"))
            {
                 //if user say yes then it procedd
                if(DisplayMessages.displayInputYesNo(mainview, "Do You Want To Save?", "Wastage  Yes/No Window")){
                wastageModel.AddMenuWastage(wastageView.getAllMenu(), mainview.getUserId(),wastageView.getDepartmentID());
                wastageView.clearAllMenu();
                }
            }
        }
            if(e.getActionCommand().equalsIgnoreCase("MenuCancel")){
                wastageView.clearAllMenu();
            }
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(wastageView, se+"from MenuSaveCancelListener "+getClass().getName());
        }
        }
        
    }
    public class ItemSaveCancelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        try{
            if(e.getActionCommand().equalsIgnoreCase("ItemSave")){
                //validating the item information
                if(wastageView.getItemID() == 0){
                    DisplayMessages.displayInfo(wastageView, "Please Select the Item Name", "Item Window");
                    return;
                }
                if(wastageView.getUnitID() == 0){
                    DisplayMessages.displayInfo(wastageView, "Please Select the Unit  Name", "Item Window");
                    return; 
                }
               
                if(wastageView.getItemQuantity() == 0.0){
                    DisplayMessages.displayInfo(wastageView, "Quantity is Zero", "Item Wastage Window");
                    return;
                }
                
                if(wastageView.getItemReason().isEmpty()){
                     DisplayMessages.displayInfo(wastageView, "Reason is Compulsary For Item Wastage Entry ", "Item Wastage Window");
                    return;
                }
                if(wastageView.getItemQuantity() > wastageModel.getItemStockAvailable(wastageView.getItemID(), wastageView.getUnitID()).floatValue()){
                    DisplayMessages.displayInfo(mainview, "Item is not Sufficient for wastage", "Wastage Window");
                    return;
                }
                 if(DisplayMessages.displayInputYesNo(mainview, "Staff Name is not Selected.\n Are You Sure You Want to Save", "Staff Window"))
                {
                    if(DisplayMessages.displayInputYesNo(wastageView, "Do you Want To Save", " Item Save Window ")){
                    
                        wastageModel.AddItemWastage(wastageView.getAllItem(), mainview.getUserId(),wastageView.getDepartmentID());
                        wastageView.clearAllItem();
                    }
                }
            }
            if(e.getActionCommand().equalsIgnoreCase("ItemCancel")){
                wastageView.clearAllItem();
            }
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(wastageView, se+"fron ItemSaveCancelListener"+getClass().getName());
        }
        }
        
    }
    public class ComboMenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        try{
           JComboBox jb = (JComboBox) e.getSource();
           if(jb == wastageView.returnComboBoxDepartmentName()){
               Object[] depinfo = null;
               Object[][] fulldep = Function.getRespectiveDepartment(mainview.getUserId());
              if(jb.getSelectedItem().equals("SELECT")){
                  wastageView.setDepartmentID(0);
                  return;
              } 
              else{
                  for(Object[] data:fulldep){
                   if(data[1].equals(jb.getSelectedItem())){
                       depinfo = data;
                       break;
                   }   
                  }
              }
              wastageView.setDepartmentID(Integer.parseInt(depinfo[0].toString()));
              wastageView.setComboBoxMenuMenuName(Function.returnSecondColumn(wastageModel.getMenuInfo(wastageView.getDepartmentID())));
              Function.AddSelectInCombo(wastageView.returnComboBoxMenuMenuName());
              //for filling dat for item
              wastageView.setComboBoxItemName(Function.returnSecondColumn(wastageModel.getItemInfoForMenu(wastageView.getDepartmentID())));
              Function.AddSelectInCombo(wastageView.returnComboBoxItemName());
           }
           //if event is generated from menumenuname
           if(jb == wastageView.returnComboBoxMenuMenuName()){
               Object[] menuinfo = null;
               Object[][] menu = wastageModel.getMenuInfo(wastageView.getDepartmentID());
               if(jb.getSelectedIndex() == 0){
                   wastageView.setMenuID(0);
                   return;
               }
               else{
                   for(Object[] data:menu){
                      if(data[1].equals(jb.getSelectedItem())){
                         menuinfo = data;
                         break;
                      } 
                   }
               }
               wastageView.setMenuID(Integer.parseInt(menuinfo[0].toString()));
               wastageView.setMenuRate(Double.parseDouble(menuinfo[3].toString()));
               //display the stock item according to the type
               int menuid = wastageView.getMenuID();
               if(wastageModel.checkTrackable(menuid)){
                   if(wastageModel.checkHybrid(menuid)){
                       wastageView.setlblMenuStock(wastageModel.getHybridItemAvailable(menuid).toString());
                   }
                   else{
                       wastageView.setlblMenuStock(wastageModel.getSingleItemAvailable(menuid).toString());
                   }
               }
               else{
                   wastageView.setlblMenuStock("");
               }
               
           }
           //if event is generate from itemname combo
           if(jb == wastageView.returnComboBoxItemName()){
               Object[] iteminfo = null;
               Object[][] item = wastageModel.getItemInfoForMenu(wastageView.getDepartmentID());
               if(jb.getSelectedIndex() ==0){
                   wastageView.setItemID(0);
                   return;
               }
               else{
                   for(Object[] data:item ){
                       if(data[1].equals(jb.getSelectedItem())){
                           iteminfo = data;
                           break;
                       }
                   }
               }
               wastageView.setUnitID(Integer.parseInt(iteminfo[2].toString()));
//               //this unitinfo the data of relative unit of the item selected so that it can be used
//               unitinfo = wastageModel.getUnitInfo(wastageView.getUnitID());
               wastageView.setComboBoxItemBaseUnit(Function.returnSecondColumn(wastageModel.getUnitInfo(wastageView.getUnitID())));
               Function.AddSelectInCombo(wastageView.returnComboBoxItemBaseUnit());
                wastageView.setItemID(Integer.parseInt(iteminfo[0].toString()));
           }   
           //if event is generated from tien unit combo
           if(jb == wastageView.returnComboBoxItemBaseUnit()){
//              JOptionPane.showMessageDialog(wastageView, "ala");
               
               Object[][] unitinfo = wastageModel.getAllUnitInfo();
              
//                  JOptionPane.showMessageDialog(wastageView, jb.getSelectedItem()+""+wastageView.getUnitID());
                if(jb.getSelectedItem().equals("SELECT")){
//                    System.out.println("wala");
                    wastageView.setUnitID(0);
                    wastageView.setlblItemStock("");
                }
                else{
                  for(Object[] data:unitinfo){
                      
                     
                      if(data[1].equals(jb.getSelectedItem())){
                          
                          wastageView.setUnitID(Integer.parseInt(data[0].toString()));
//                          System.out.println(wastageView.getUnitID());
//                          JOptionPane.showMessageDialog(wastageView, jb.getSelectedItem()+"unit"+wastageView.getUnitID());
                          //update  the item stock
                          
                          
                          wastageView.setlblItemStock(wastageModel.getItemStockAvailable(wastageView.getItemID(), wastageView.getUnitID()).toString());
                          break;
                      }
                  }
//                 
                }
               }
           
        }
        catch(NumberFormatException se){
            DisplayMessages.displayError(mainview, se.getMessage()+getClass().getName(), "Error Window");
        }
        }
        
    }
    public class MenuDocumentListener implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
            changeAmount(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            changeAmount(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            changeAmount(e);
        }
        //action to be perfomed while change is litener
        private  void changeAmount(DocumentEvent e){
             try{
                 SwingUtilities.invokeLater(new Runnable() {

                     @Override
                     public void run() {
                      Double  rate = wastageView.getMenuRate();
                 Double quantity = wastageView.getMenuQuantity();
                 wastageView.setMenuAmount(rate*quantity);
                     }
                 });
                
                }
                catch(Exception se){
                    JOptionPane.showMessageDialog(wastageView, se+"from MenuDocumentListener"+getClass().getName());
                }
        } 
        
    }
   
    
}
