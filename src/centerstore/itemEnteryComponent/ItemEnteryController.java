/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.itemEnteryComponent;

import centerstore.distributorComponent.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import reusableClass.DisplayMessages;
/**
 *
 * @author MinamRosh
 */
public class ItemEnteryController {
    private ItemEnteryView itemEView;
    private ItemEnteryModel itemEModel;
    //private String[] strInfo; //string intem entery info
    private String[] itemEInfo; //integer type entery info
    private ListSelectionModel listItemModel;
    private DefaultTableModel tblItemModel;
    private int lead; //used to abstract row of 
    private int tblRow; //used while deleting data;
    private String iName;
    private String[] purchaseInfo;
    private String[] itemList; //for checking duplication;
    
    private String oldItem = null;
    //details of unit, distro and category;
    private Object[][] distroDetails;
    private Object[][] unitDetails;
    private Object[][] categoryDetails;
    
    public ItemEnteryController(ItemEnteryView view, ItemEnteryModel model){
        itemEView = view;
        itemEModel = model;
 
        String[] distroList;
        String[] categoryList;
        String[] baseUList;
        //combobox
        try{
            distroDetails = itemEModel.getDistorDetails();
            unitDetails = itemEModel.getUnitDetails();
            categoryDetails = itemEModel.getCategoryDetails();
            loadItemList();
            
            distroList = itemEModel.getDistroList();
            categoryList = itemEModel.getCategoryList();
            baseUList = itemEModel.getUnitList();
            
            //show combo list
            itemEView.setComboCategoryList(categoryList);
            itemEView.setComboUnitList(baseUList);
            itemEView.setComboDistroList(distroList);
            
            itemEView.addListenerToDistro(new DistroListener());
            itemEView.addFocusToField(new AddFoucsListener());
            itemEView.setTableModel(itemEModel.getAllItemInfo(unitDetails));
            itemEView.addDocumentListenerToSearch(new SearchDocumentListener());;
            itemEView.addAListenerToSText(new SearchListener());
            
        }
        catch(NullPointerException ex){ 
            DisplayMessages.displayError(view, "ItemEntryController.constructor()" + ex, "Error");
        }
        
        itemEView.addListenerToListModel(new ListListener());
        itemEView.addListenerToUpdate(new UpdateListener());
        itemEView.addListenerToSearch(new SearchListener());
        itemEView.addListenerToDelete(new DeleteListener());
        itemEView.addListenerToCancel(new CancelListener());
        itemEView.addListenerToAdd(new AddListener());
        
        //itemEView.addKeyListenerToBtn(new KeyListenerToBtn());
        //itemEView.addKeyListenerToDistro(new KeyListenerToDistro());
    }
    
   class AddFoucsListener implements FocusListener{
        @Override
        public void focusGained(FocusEvent e) {
            JTextField cmp = (JTextField) e.getComponent();
            cmp.setBackground(new Color(136,249,168));
            cmp.selectAll();
        }

        @Override
        public void focusLost(FocusEvent e) {
            Component cmp = e.getComponent();
            cmp.setBackground(Color.white);
        }
        
    }
    
    /* main action listener to buttons*/
    class AddListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ad){
            addFunc();
        }
       
    }
    
    class UpdateListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ad){
            editFunc();
            
        }
    }
    
    class DeleteListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ad){
            deleteFunc();
            
        }
    }
    
    class CancelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ad){
            cancleFunc();
           
        }
    }
    
    class SearchListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ad){
            String item = itemEView.getSearchItem();
            if(item.isEmpty()){
                DisplayMessages.displayError(itemEView, "Select Item First !", "Form Error !");
                return;
            }
            searchByName(item);
        }
    }
    class DistroListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent d){
            addDistro();
          
        }
    }
    
    /*
    class KeyListenerToDistro implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
                addDistro();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    */
    
    class ListListener implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent lEvt){
            //System.out.println("list Listener");
            if(!lEvt.getValueIsAdjusting()){
               try{ itemEView.disableAdd();
                itemEView.enableUpdate();
                itemEView.disableField();
               // System.out.println("list Listener");
               listItemModel = itemEView.getListModel();
               if(listItemModel.isSelectionEmpty()){
                   return;
               }
               lead = listItemModel.getLeadSelectionIndex();
               JTable tbl = itemEView.getTable();
               tblRow = tbl.getSelectedRow();//for deleting row of table
               //System.out.println("list selection"+tblRow);
               int cols = tbl.getColumnCount(); //get number of column in table;
               String[] rowList = new String[cols];
               
               for(int i = 0 ; i < cols; i++){
                   Object obj = tbl.getValueAt(lead, i);
                   rowList[i] = obj.toString();
               }
               
               oldItem = rowList[1];
              //String[] rowList = itemEView.getRowList(lead);
              itemEView.showSelectedRowList(rowList);
               }
               catch(ArrayIndexOutOfBoundsException ex){
               //throws while updating and deleting so wee need to mute this exceptions
               }
           }
        }
    }
    
    class KeyListenerToBtn implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                
                if(e.getSource().toString().contains("Add")){
                    addFunc();
                }
                
                if(e.getSource().toString().contains("Edit")){
                    editFunc();
                    oldItem = null;
                }
                
                if(e.getSource().toString().contains("Delete")){
                    deleteFunc();
                    oldItem = null;
                }
                
                if(e.getSource().toString().contains("Cancle")){
                    cancleFunc();
                    oldItem = null;
                }
                
                if(e.getSource().toString().contains("Search")){
                    searchFunc();
                    //oldItem = null;
                }
                
                
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    /* SearchDocumentListener
    * listen for each key press and get data from database to show likely result in table
    *
    */
    class SearchDocumentListener implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
            getSearchResult(e);//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            getSearchResult(e);//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            getSearchResult(e);//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        public void getSearchResult(DocumentEvent evt){
            try{
                SwingUtilities.invokeLater(new Runnable(){

                    @Override
                    public void run() {
                            itemEView.refreshItemTable(itemEModel.getSearchItemInfo(itemEView.getSearchItem(), unitDetails));
                    }    
                });
            }
            catch(ExceptionInInitializerError ex){
                DisplayMessages.displayError(itemEView, ex.getMessage(), "Error in Search Document Listener");
            }
        }
    }
    
    /**
     * used to initiate click event on search button
     * when enter is hit at the search text field
     */
    class SearchActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //itemEView.getSearchBtn().doClick();
            searchFunc();
        }
        
    }
    
    private void loadItemList(){
        itemList = itemEModel.isItemDuplicate();
       
    }
    
    public boolean checkItemDuplicationAd(String name){
        for (String item : itemList) {
            if (item.equalsIgnoreCase(name)) {
                return false;
            }
        }
        /*
        for(int i = 0; i < itemList.length; i++){
            if(itemList[i].equalsIgnoreCase(name)){
                return false;
            }
        }
                */
        return true;
    }
    
    public boolean checkItemDuplicationUp(String name){
        for (String item : itemList) {
            if (item.equals(name)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * search item by name and display item,
     * search on the basis of like
     * @param name , string 
     */
    public void searchByName(String name){
        //Object[][] entryTable = itemEModel.getEntryTable();
        JTable tbl = itemEView.getTable();
        String[] searchList = new String[tbl.getModel().getColumnCount()];
        for(int r = 0; r < tbl.getModel().getRowCount(); r++){
            
            if(tbl.getValueAt(r, 1).toString().toLowerCase().startsWith(name.toLowerCase())){
                for(int c = 0; c < tbl.getModel().getColumnCount(); c++){
                    searchList[c] = tbl.getValueAt(r,c).toString();
                }
            }
            itemEView.showSelectedRowList(searchList);
            itemEView.setSItemTable(r, r);
            
            break;
        }
        //itemEView.showSelectedRowList(ob);
        /*int cols = tbl.getColumnCount();
        
        String[] ob = new String[cols];
        for (Object[] entryTable1 : entryTable) {
            if (entryTable1[1].equals(name)) {
                for (int j = 0; j < cols; j++) {
                    ob[j] = entryTable1[j].toString();
                }
            }
        }
       */
        
        
    }
    
    public void addFunc(){
        String[] txtItem = itemEView.txtItem();
            if(txtItem[0] == null || txtItem[0].isEmpty() )
            if(!checkItemDuplicationAd(txtItem[0])){
                DisplayMessages.displayError(null, "Item Already Exists!", "Form Error");
                return;
            }
            
            if(!checkComboSelection())
                return;
            
            String[] ids = new String[4];
            String category = null;// category id and name since due to the change of the schema
            
            
            for (Object[] categoryDetail : categoryDetails) {
                if (categoryDetail[1].equals(itemEView.getSelectedCategory())) {
                    ids[0] = categoryDetail[0].toString();
                    category = categoryDetail[1].toString();
                
                    break;
                }
            }
            /*            for(int i = 0; i < categoryDetails.length; i++){
                if(categoryDetails[i][1].equals(itemEView.getSelectedCategory())){ 
                    ids[0] = categoryDetails[i][0].toString();
                    break;
                }
            } */
            
            for(int i = 0; i < unitDetails.length; i++){
                if(unitDetails[i][1].equals(itemEView.getSelectedUnit())){
                    ids[1] = unitDetails[i][0].toString();
                    ids[2] = unitDetails[i][2].toString();
                    break;
                }
            }

            for(int i = 0; i < distroDetails.length; i++){
                if(distroDetails[i][1].equals(itemEView.getSelectedDistro())){
                    ids[3] = distroDetails[i][0].toString();
                }
            }
            
            itemEModel.addItemEntery(txtItem, ids,category);
            itemEView.setTableModel(itemEModel.getAllItemInfo(unitDetails));
            itemEView.clearAllField();
            
            loadItemList();
            itemEView.clearComboList();
            
    }
    
    public void editFunc(){
        String[] txtItem = itemEView.txtItem();
            
        if(!oldItem.equalsIgnoreCase(txtItem[0])){
            if(!checkItemDuplicationUp(txtItem[0])){
                DisplayMessages.displayError(null, "Item Already Exists!", "Form Error");
                return;
            }
        }
        
        if(!checkComboSelection())
            return;
            
            // item id;
            int item = Integer.parseInt(itemEView.getItemId());
             if(item <= 0){
                DisplayMessages.displayError(null, "Please Select an Item First !", "Form Error");
                 return;
             }
            
            //get text field item;
            //0-item, 1-qty, 2-buy, 3-extate, 4-threshold;
          
            
            //category id'
             int cId  = 0;
        for (Object[] categoryDetail : categoryDetails) {
            if (categoryDetail[1].equals(itemEView.getSelectedCategory())) {
                cId = Integer.parseInt(categoryDetail[0].toString());
                break;
            }
        }
            
            
            itemEModel.updateItemEntery(item, txtItem, cId);
            
            itemEView.setTableModel(itemEModel.getAllItemInfo(unitDetails));
            itemEView.clearAllField();
            
            loadItemList();
            
            itemEView.enableAdd();
            itemEView.disableUpdate();
            itemEView.enableField();
            itemEView.clearComboList();
    }
    
    public void deleteFunc(){
        String id = itemEView.getItemId();
            if(id == null || id.isEmpty()){
                DisplayMessages.displayError(null, "Please Select an Item First!", "Form Error");
                return;
            }
            int status = JOptionPane.showConfirmDialog(itemEView, "Are You Sure You Want to Delete?", null, JOptionPane.YES_NO_OPTION);
            if(status == JOptionPane.YES_OPTION){
                //itemEView.deleteRowTable(tblRow);
                itemEModel.deleteItemEntery(id);
                itemEView.setTableModel(itemEModel.getAllItemInfo(unitDetails));
                itemEView.clearAllField();
                
                itemEView.enableAdd();
                itemEView.disableUpdate();
                itemEView.enableField();
            }
            else{
                return;
            }
            loadItemList();
            itemEView.clearComboList();
    }
    
    public void addDistro(){
         DistributerView dView = new DistributerView(new JFrame(), true);
            
            DistributerModel dModel = new DistributerModel();
            DistributerController dController = new DistributerController(dView, dModel); 
            dView.setVisible(true);
            if(!dView.isVisible()){
                distroDetails = itemEModel.getDistorDetails();
                String[] distroList = itemEModel.getDistroList();
                itemEView.setComboDistroList(distroList);
            }
    }
    
    public void searchFunc(){
        String source = itemEView.getSearchItem();
        if(source.isEmpty()){
            DisplayMessages.displayError(itemEView, "Enter Search Item Name", "Form Error");
            return;
        }
            searchByName(source);
            //itemEView.clearAllField();
    }
    
    public void cancleFunc(){
         itemEView.clearAllField();
            itemEView.enableAdd();
            itemEView.disableUpdate();
            itemEView.enableField();
            itemEView.clearComboList();
    }
    
    public boolean checkComboSelection(){
        String selectedCategory = itemEView.getSelectedCategory();
            //System.out.println(selectedCategory);
            if(selectedCategory.equalsIgnoreCase("No Category List")|| selectedCategory.equalsIgnoreCase("Select Category")){
                DisplayMessages.displayError(itemEView, "Select Category!", "Form Error");
                return false;
            }
            
                        String selectedDistro = itemEView.getSelectedDistro();
            if(selectedDistro.equalsIgnoreCase("No Distributor List")||selectedDistro.equalsIgnoreCase("Select Distriobutor")){
                DisplayMessages.displayError(itemEView, "Select Distributor!", "Form Error");
                return false;
            }
            
            
            String selectedUnit = itemEView.getSelectedUnit();
            if(selectedUnit.equalsIgnoreCase("No Unit List") || selectedUnit.equalsIgnoreCase("Select Unit")){
                DisplayMessages.displayError(itemEView, "Select Unit First!", "Form Error");
                return false;
            }
        return true;
    }
    
}
