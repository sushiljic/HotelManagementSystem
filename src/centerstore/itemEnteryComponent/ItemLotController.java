/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.itemEnteryComponent;

import centerstore.distributorComponent.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import reusableClass.DisplayMessages;

/**
 *
 * @author MinamRosh
 */
public class ItemLotController {
    private ItemLotView lotView;
    private ItemLotModel lotModel;
    
    private ListSelectionModel purchaseListModel;
    
    private String[] distroList;
    private String[] itemList;
    private String[] unitList;
    
    Object[][] unitDetails; // stores all unit details;
    Object[][] itemDetails;
    Object[][] distroDetails;
    
    //only used by the combo listener
    String unitId; //used at item listener
    String distroId;
    float itemQty;
    String itemId;
    String lotId; //purchase id
    String[] unitIdNR; //unit id and relative quantity;
    String[] distroIdN;
    String[] itemIdT; //id total_qty;
    String[] uIdNR;
    
    float preQty; // qty to be subtracted while updating the lot;
    int tblRow; //for deleting row purpose;
    
    public ItemLotController(ItemLotView v, ItemLotModel m){
        lotView = v;
        lotModel = m;
        
        distroList = lotModel.getDistroList();
        unitList = lotModel.getUnitList();
        itemList = lotModel.isItemDuplicate();
        //System.out.println(itemList[0]);
        
        lotView.setComboItemList(itemList);
        lotView.setComboBUnitList(unitList);
        lotView.setComboDistroList(distroList);
        lotView.setTblPurchare(lotModel.getPurchaseModel());
        
        unitDetails = lotModel.getUnitDetails();
        itemDetails = lotModel.getItemDetails();
        distroDetails = lotModel.getDistorDetails();
        
        
        
        lotView.addListenerToAdd(new AddListener());
        lotView.addListenerToCancel(new CancelListener());
        lotView.addListenerToUpdate(new UpdateListener());
        lotView.addListenerToDistro(new DistroListener());
        
        lotView.addListenerToComboItem(new ComboItemListener());
        lotView.addListenerToComboUnit(new ComboUnitListener());
        lotView.addListenerToComboDistro(new ComboDistroListener());
        lotView.addFoucsListenerToField(new AddFoucsListener());
        
        lotView.addListenerToPurchase(new PurchaseListener()); //listener for table;
        lotView.addKeyListenerToBtn(new KeyListenerToBtn());
        lotView.addKeyListenerToDistro(new KeyListenerToDistro());
        
        
        lotModel.getPurchaseModel();
        
    }
    
    class AddListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent aEvt){
            addFunc();
            
        }
    }
    
    class UpdateListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent aEvt){
            updateFunc();
            
            
        }
    }
    
    class CancelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent cEvt){
            cancleFunc();
            
        }
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
    
    class PurchaseListener implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent lEv){
           
            if(!lEv.getValueIsAdjusting()){
                try{
                    lotView.disableComboItem();
                    lotView.disableAd();
                    lotView.enableUp();
                    
                    purchaseListModel = lotView.getPurchaseListModel();
                    //deal with exception occured before
                    if(purchaseListModel.isSelectionEmpty()){
                        return;
                    }
                    int lead = purchaseListModel.getLeadSelectionIndex();
                    JTable tbl = lotView.getPurchaseTable();
                    tblRow = tbl.getSelectedRow();
                    int cols = tbl.getColumnCount();
                    String[] rowList = new String[cols];

                    for(int i = 0 ; i < cols; i++){
                        //System.out.println(i);

                           //after update throws arrayindexoutofbouds excetpion;
                           Object obj = tbl.getValueAt(lead, i);
                           rowList[i] = obj.toString();
                    }

                        lotId = rowList[0];
                       
                        //0-purchaseId,1-item,2-category,3-unit,4-buyRate,5-qty,6-threshold,7-expirydate,8-total amount, 9-disto
                        getItemIdTotal(rowList[1]); //set itemIdT = id and total quantity;
                        lotView.setItem(rowList[1]);
                        lotView.setUnit(rowList[2]);
                        unitIdNR = getUnitIdNR(rowList[3]);

                       preQty = Float.parseFloat(rowList[5]) * Float.parseFloat(unitIdNR[2]);
                      // System.out.println(preQty);
                       distroIdN = getDistroIdN(rowList[10]);
                   
                    //System.out.println(distroIdN[0]);                  // System.out.println(uIdNR[2]);
                      lotView.setDistro(rowList[10]);

                      lotView.showTxtData(rowList);
                }
                catch(ArrayIndexOutOfBoundsException E){
                    //must mute this exception
                }

            }
            
        }
    }
    class DistroListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent d){
           distroFunc();
        }
    }
    
    class ComboItemListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent itm){
            JComboBox cItem = (JComboBox) itm.getSource();
            if(cItem.getSelectedIndex()== 0){
                lotView.clearAll();
                return;
            }
            String itemName = cItem.getSelectedItem().toString();

            for(int i = 0; i < itemDetails.length; i++){
               if(itemDetails[i][1].equals(itemName)){
                    unitId = itemDetails[i][2].toString(); //unit id
                    //DisplayMessages.displayInfo(cItem, unitId, unitId);
                    itemIdT = new String[2];
                    itemIdT[0] = itemDetails[i][0].toString();
                    itemIdT[1] = itemDetails[i][4].toString();
                    distroId = itemDetails[i][3].toString(); //distributor id
                    itemQty = Float.parseFloat(itemDetails[i][4].toString()); //item total quantiy
                    
                    itemId = itemDetails[i][0].toString(); //item id
                    lotView.setThreshold(itemDetails[i][6].toString());
                    break;
                }
                
            }
          //prepare data for unit and distributor
            
            try{
                String[] unit = getUnitNameR(unitId);
                //DisplayMessages.displayInfo(cItem, unit[1], "");
                lotView.setUnit(unit[1]);
                //System.out.println(unit[1]);
                distroIdN = getDistroName(distroId);
                lotView.setDistro(distroIdN[1]);
                lotView.showStock(itemQty/Float.parseFloat(unit[2]));
                
            }
            catch(ArrayIndexOutOfBoundsException ex){
                    DisplayMessages.displayError(null, "ItemLotModel.ComboItemListener."+ex, "Error");
            }
            
        }
    }
    
    class ComboUnitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent u){
            JComboBox unit = (JComboBox) u.getSource();
            String unitName = unit.getSelectedItem().toString();
            if(unitName.equalsIgnoreCase("Select Unit")||unitName.equalsIgnoreCase("No Unit List")){
                //DisplayMessages.displayError(unit, "Select Unit!", "Form Error");
                //return;
            }
            else{
            
            
                unitIdNR = getUnitIdNR(unitName);
                lotView.showStock(itemQty/Float.parseFloat(unitIdNR[2]));
            }
        }
    }
    
    class ComboDistroListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent u){
            JComboBox distro = (JComboBox) u.getSource();
            String distroName = distro.getSelectedItem().toString();     
            distroIdN = getDistroIdN(distroName);

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
                
                if(e.getSource().toString().contains("Update")){
                    updateFunc();
                }
                
                if(e.getSource().toString().contains("Cancle")){
                    cancleFunc();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    class KeyListenerToDistro implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
                distroFunc();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    public String[] getUnitNameR(String id){
        //System.out.println(id);
        String[] unit = new String[3];
        //System.out.println(id);
        for(int i = 0; i < unitDetails.length; i++){
            if(Integer.parseInt(unitDetails[i][0].toString()) == Integer.parseInt(id)){
                
                try{
                    unit[0] = id;
                    unit[1] = unitDetails[i][1].toString();
                    //System.out.println(name[0]);
                    unit[2] = unitDetails[i][2].toString();
                    break;
                }
                catch(NullPointerException ex){
                    DisplayMessages.displayError(null, "ItemLotModel.getUnitNameR()"+ex, "Error");
                }
                        
            }    
        }
        return unit;
    }
    
    //called from comboUnit;
    public String[] getUnitIdNR(String name){
        String[] unit = new String[3];
        for(int i = 0; i < unitDetails.length; i++){
            if(unitDetails[i][1].equals(name)){
                unit[0] = unitDetails[i][0].toString();
                unit[1] = unitDetails[i][1].toString();
                unit[2] = unitDetails[i][2].toString();
                 //System.out.println(i + " : " +unit[1] +" | "+ name);
                break;
            }
        }
      
       
        return unit;
    }
    
    //called from combo Item;
    public String[] getDistroName(String id){
        String[] distro = new String[2];
        for(int i = 0; i < distroDetails.length; i++){
            //System.out.println(distroDetails[i][0].toString());
            if(Integer.parseInt(distroDetails[i][0].toString()) == Integer.parseInt(id)){
                distro[0] = id;
                distro[1] = distroDetails[i][1].toString();
                //System.out.println(name);
                break;
            }
        }
        return distro;
    }
    //called from combo distro
    public String[] getDistroIdN(String name){
        
        String[] distro = new String[2];
       
        
       // System.out.println(distroDetails.length);
       for(int i = 0; i < distroDetails.length; i++){
            //System.out.println(distroDetails[i][0] + " | " +distroDetails[i][1]);
               if(distroDetails[i][1].equals(name)){
                   
                  distro[0] = distroDetails[i][0].toString();
                   //System.out.println(distro[0]);
                   distro[1] = name;
                   break;
                }
            }
  
        return distro;
    }
    
    //get item id and total quantiyt;
    public void getItemIdTotal(String name){
        //System.out.println(name);
        for(int i = 0; i < itemDetails.length; i++){
            if(itemDetails[i][1].equals(name)){
                try{
                itemIdT[0] = itemDetails[i][0].toString(); //item id;
                itemIdT[1] = itemDetails[i][4].toString(); //total quantity;
                
                break;
                }
                catch(NullPointerException e){}
            }
        }
        
    }
    
    public void addFunc(){
        if(!checkComboSelection())
            return;
        //qty, brate, eDate, threshold
        String[] txtFieldData = lotView.getLotData();
        
        //validate
        String msg = new String();
        //System.out.println(msg);
        if(txtFieldData[0].isEmpty()){
            DisplayMessages.displayError(lotView, "Enter Quantity!", "Form Error");
            return;
        }
        

//        System.out.println(itemIdT[0]);
//        System.out.println(unitIdNR[0]);
//        System.out.println(distroIdN[0]);
        
        
        lotModel.updateToStoreAd(itemIdT, txtFieldData, unitIdNR, distroIdN);
        lotView.clearAll();
            
        itemDetails = lotModel.getItemDetails();
        lotView.setTblPurchare(lotModel.getPurchaseModel());
    }
    
    public void updateFunc(){
        String[] txtFieldData = lotView.getLotData();
           
        if(!checkComboSelection()){
            return;
        }
            
            lotModel.updateToStoreUp(lotId,itemIdT, txtFieldData, unitIdNR, distroIdN, preQty);
            lotView.clearAll();
            
            unitDetails = lotModel.getUnitDetails();
            itemDetails = lotModel.getItemDetails();
            distroDetails = lotModel.getDistorDetails();
            
            lotView.setTblPurchare(lotModel.getPurchaseModel());
    }
    
    public void cancleFunc(){
            lotView.clearAll();
            lotView.enableAd();
            lotView.enableComoboItem();
            lotView.disableUp();
            lotView.clearComboList();
    }
    
    public void distroFunc(){
        DistributerView dView = new DistributerView(new JFrame(), true);
            
            DistributerModel dModel = new DistributerModel();
            DistributerController dController = new DistributerController(dView, dModel); 
            dView.setVisible(true);
            if(!dView.isVisible()){
                distroDetails = lotModel.getDistorDetails();
                String[] distro = lotModel.getDistroList();
                lotView.setComboDistroList(distro);
            }
    }
    
    public boolean checkComboSelection(){
        String selectedItem = lotView.getSelectedItem();
            //System.out.println(selectedCategory);
            if(selectedItem.equalsIgnoreCase("No Item List")|| selectedItem.equalsIgnoreCase("Select Item")){
                DisplayMessages.displayError(lotView, "Select Category!", "Form Error");
                return false;
            }
           
            String selectedDistro = lotView.getSelectedDistro();
            if(selectedDistro.equalsIgnoreCase("No Distributor List")||selectedDistro.equalsIgnoreCase("Select Distriobutor")){
                DisplayMessages.displayError(lotView, "Select Distributor!", "Form Error");
                return false;
            }
            
            
            String selectedUnit = lotView.getSelectedBUnit();
            if(selectedUnit.equalsIgnoreCase("No Unit List") || selectedUnit.equalsIgnoreCase("Select Unit")){
                DisplayMessages.displayError(lotView, "Select Unit First!", "Form Error");
                return false;
            }
        return true;
    }
}
