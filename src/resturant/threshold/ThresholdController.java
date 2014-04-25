/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.threshold;

import hotelmanagementsystem.MainFrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SUSHIL
 */
public class ThresholdController {
    private ThresholdModel thModel;
    private ThresholdView thView;
    private MainFrameView mainview;
    
    public ThresholdController(ThresholdModel model,ThresholdView view,MainFrameView mainview){
        thModel = model;
        thView = view;
        this.mainview = mainview;
        /*
         * 
         */
       
//           thView.refreshJTable(thModel.getItemList(thView.getStoreId()));
//       //forloading itename in combo box
//       thView.setComboItemName(thModel.ItemName);
//       thView.AddSelectInCombo(thView.returnItemName());
        /*
         * adding the listener for the event registration
         */
        thView.addSaveListener(new ThresholdListener());
        thView.addCancelListener(new ThresholdListener());
        thView.addSingleSaveListener(new singleThresholdListener());
        /*
         * adding keylistener for event registration
         */
//        thView.addKeySaveListener(new KeyThresholdListener());
//        thView.addKeyCancelListener(new KeyThresholdListener());
//        thView.addKeySingleSaveListener(new KeysingleThresholdListener());
        thView.addComboStoreNameListener(new ComboListener());
          try{
            thView.setComboStoreName(thModel.returnSecondColumn(thModel.getRespectiveDepartment(this.mainview.getUserId())));
          //if it has only one element select it order wise add select into it
            int combosize = thView.returnComboStoreName().getModel().getSize();
            if(combosize >1){
                thView.AddSelectInCombo(thView.returnComboStoreName());
            }
            else{
                if(combosize == 1){
                thView.returnComboStoreName().setSelectedIndex(0);
                }
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(thView, e+"from constructor "+getClass().getName());
        }
    }
    
    public class ThresholdListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getActionCommand().equalsIgnoreCase("Save")){
            try{
               // JOptionPane.showMessageDialog(thView,thModel.returnItemIdplusThreshold(thView.getValueFromTable()) ); 
            thModel.EntryThreshold(thModel.returnItemIdplusThreshold(thView.getValueFromTable()));
//           thView.refreshJTable(thModel.getItemList(thView.getStoreId()));
           thView.clear();
           
            }
            catch(Exception te){
                JOptionPane.showMessageDialog(thView, te+"from save");
            }
        }
        if(e.getActionCommand().equalsIgnoreCase("cancel")){
            thView.returnComboStoreName().setSelectedIndex(0);
//            thView.refreshJTable(thModel.getItemList(thView.getStoreId()));
            thView.clear();
           
            
        }
        }
        
    }
    public class singleThresholdListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getActionCommand().equalsIgnoreCase("Save")){
            try{
                if(thView.returnItemName().getSelectedIndex() == 0){
                     JOptionPane.showMessageDialog(thView, "Please Select the ItemName.");
                    return; 
                }
                if(thView.getThreshold().trim().isEmpty()){
                    JOptionPane.showMessageDialog(thView, "Threshold is zero");
                    return;
                }
              
                thModel.EntrySingleThreshold(thModel.returnItemId(thView.getComboItemName()),thView.getThreshold());
                
              
                thView.refreshJTable(thModel.getItemList(thView.getStoreId()));
                 thView.clear();
            JOptionPane.showMessageDialog(thView, "Threshold Update Successfully");
            }
            catch(Exception the){
                JOptionPane.showMessageDialog(thView, the+"from singlethresholdLIstener");
            }
        }
        }
        
    }
    public class ComboListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         //this is for store name select
          if (e.getActionCommand().equalsIgnoreCase("comboStoreName")){
        Object[] cat_id = null;
           //JOptionPane.showMessageDialog(MenuEntryView,"ea");
        try{
           JComboBox jCat = (JComboBox) e.getSource();
         //i beleive this is useless upto now
           /*  if(MenuEntryView.getTrackable()){
               System.out.println(MenuEntryView.getUnitId());
          ItemUnitInfo = MenuEntryModel.getUnitInfo(MenuEntryView.getUnitId(),true);
           }
           else { */
          Object[][]  StoreInfo = thModel.getRespectiveDepartment(mainview.getUserId());
            if(jCat.getSelectedItem().equals("SELECT")){
                thView.setStoreId(0);
                thView.setComboItemName(new Object[]{});
                thView.refreshJTable(new DefaultTableModel());
              
//                thView.refreshJTable(null);
//                MenuEntryView.setTrackableUntrackable(Boolean.TRUE);
//                MenuEntryView.setRadioEnableTrackable(false);
//                MenuEntryView.setRadioEnableUnTrackable(false);
//                JOptionPane.showMessageDialog(null, "wala");
               
                
            }
            else{
            for (Object[] ItemCategoryInfo1 : StoreInfo) {
                if (ItemCategoryInfo1[1].equals(jCat.getSelectedItem())) {
                    cat_id = ItemCategoryInfo1;
                    break;
                }
            }
           thView.setStoreId(Integer.parseInt(cat_id[0].toString()));
          
           thView.refreshJTable(thModel.getItemList(thView.getStoreId()));
            thView.setComboItemName(thModel.ItemName);
           thView.AddSelectInCombo(thView.returnItemName());
           // loading repective item
        
        //System.out.println(cat_id[0]);
        }
            
            
        }
        catch(Exception see){
            JOptionPane.showMessageDialog(thView, see+"from comboListener "+getClass().getName());
        }
       }
        }
        
    }
//    public class KeyThresholdListener implements KeyListener{
//
//        @Override
//        public void keyTyped(KeyEvent e) {
//           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void keyPressed(KeyEvent e) {
//          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        int key = e.getKeyCode();
//        if(key == KeyEvent.VK_ENTER){
//            if(e.getSource().toString().contains("Save")){
//                 try{
//               // JOptionPane.showMessageDialog(thView,thModel.returnItemIdplusThreshold(thView.getValueFromTable()) ); 
//            thModel.EntryThreshold(thModel.returnItemIdplusThreshold(thView.getValueFromTable()));
//           thView.refreshJTable(thModel.getItemList());
//           thView.clear();
//            JOptionPane.showMessageDialog(thView, "Threshold Update Successfully");
//            }
//            catch(Exception te){
//                JOptionPane.showMessageDialog(thView, te+"from save");
//            }
//            }
//            if(e.getSource().toString().contains("Cancel")){
//                thView.refreshJTable(thModel.getItemList());
//            thView.clear();
//            }
//        }
//        }
//
//        @Override
//        public void keyReleased(KeyEvent e) {
//           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//        
//    }
//    public class KeysingleThresholdListener implements KeyListener{
//
//        @Override
//        public void keyTyped(KeyEvent e) {
//         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void keyPressed(KeyEvent e) {
//         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        int key = e.getKeyCode();
//        if(key == KeyEvent.VK_ENTER){
//            if(e.getSource().toString().contains("Save")){
//                try{
//                if(thView.getThreshold().trim().isEmpty()){
//                    JOptionPane.showMessageDialog(thView, "Threshold is zero");
//                    return;
//                }
//                thModel.EntrySingleThreshold(thModel.returnItemId(thView.getComboItemName()),thView.getThreshold());
//                 thView.refreshJTable(thModel.getItemList());
//                 thView.clear();
//            JOptionPane.showMessageDialog(thView, "Threshold Update Successfully");
//            }
//            catch(Exception the){
//                JOptionPane.showMessageDialog(thView, the+"from singlethresholdLIstener");
//            }
//            }
//          
//        }
//        }
//
//        @Override
//        public void keyReleased(KeyEvent e) {
//          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//        
//    }
}
