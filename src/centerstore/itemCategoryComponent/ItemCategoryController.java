/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.itemCategoryComponent;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.tree.*;
import javax.swing.event.*;
//listener and event for new advance swing element;
import javax.swing.event.TreeSelectionEvent;
import reusableClass.DisplayMessages;
//import database.*;
/**
 *
 * @author MinamRosh
 */
public class ItemCategoryController {
    private final ItemCategoryView itemView;
    private final ItemCategoryModel itemModel;
    private String[] category_list;
    //private Map<String, String> treeCategory;
    private DefaultTreeModel treeModel;
    
    public ItemCategoryController(ItemCategoryView view, ItemCategoryModel model){
        itemView = view;
        itemModel = model;
        itemView.addListenerToButton(new AddButtonListener());
        //itemView.addKeyListenerToBtn(new BtnKeyListener());
        itemView.addFoucsListenerToField(new AddFoucsListener());
        
        updateComboTree();
        itemView.addListenerToTree(new AddTreeListener());
        
    }
    
    //listener configuration and operation for button
    class AddButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            String command;      
            command = event.getActionCommand();
                
            //handle adding category
            if(command.equals("Add")){
                addFunc();
            }
                
                //update
                if(command.equals("Update")){
                    //boolean status = itemModel.checkDupItemCategory(categoryName);
                    
                    updateFunc();
                }
                
                //delete
                if(command.equals("Delete")){
                       deleteFunc();
                }
                
                //cancle
                if(command.equals("Cancel")){
                    //itemView.clearErrorTextField();
                    itemView.disableBtns();
                    itemView.clearFrame();
                }
            
        }
    }
    // end of class listener for buttons
    
    //listener for jtree
    class AddTreeListener implements TreeSelectionListener{
        @Override
        public void valueChanged(TreeSelectionEvent treeEvent){
           // String[] cDetails; //= new String[2];
            try{
              TreePath path = treeEvent.getPath();
              if(path == null) return;
              DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
              //System.out.println(selectedNode.getIndex(selectedNode));
              itemView.enableBtns();
              //check for root of the tree
              if(!selectedNode.isRoot()){
                  ///JOptionPane.showMessageDialog(itemView, "This Category Is Reserved By The System!", "Information", JOptionPane.INFORMATION_MESSAGE);
                   // DisplayMessages.displayWarning(itemView,"This Category is reserved by the System!", "Warning");
 
                  //if selected category is not the root of the tree then
                  //process the category
                  //childNo is greater than 0 for root node in our category;
                  //parent stores the parent node name of selected node category;
                  
                  int childNo = selectedNode.getDepth();
                  String parent = selectedNode.getParent().toString();
                  
                  String node = selectedNode.toString();
                  boolean parentStatus = true; // for parent
                  //parent node;
                  if(childNo > 0 || parent.equalsIgnoreCase("category list")){
                      //get selected category details;
                      //System.out.println(selectedNode.toString());
                      String[] cDetails = itemModel.getItemSelectedCategory(node);
                      //display in the view
                      //System.out.println(cDetails[0]);
                      if(cDetails[1].toString() == null)
                           return;
                      itemView.setCategoryDetails(cDetails);
                  }else{ //child node;
                      String[] cDetails = itemModel.getItemSelectedSubCategory(node);
                      if(cDetails[1].toString() == null)
                           return;
                      itemView.setCategoryDetails(cDetails);
                  }
              }

            }
            catch(NullPointerException ex){
                //JOptionPane.showMessageDialog(itemView,"controller.treeListener"+ex);
                //DisplayMessages.displayError(itemView, "TreeListener:"+ex, "Operation Error");
                itemView.clearFrame();
                //return;
            }
            catch(ArrayIndexOutOfBoundsException | NumberFormatException index){
                //System.out.println(index);
                 DisplayMessages.displayError(itemView, "TreeListener:"+index.getStackTrace(), "Exception Found");
                 index.printStackTrace();
                itemView.clearFrame();
            }
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
    /*
    class BtnKeyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyPressed(KeyEvent e) {
            
            //if entered is pressed then
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                if(e.getSource().toString().contains("Add")){
                    addFunc();
                }
                
                if(e.getSource().toString().contains("Update")){
                    updateFunc();
                }
                
                if(e.getSource().toString().contains("Delete")){
                    deleteFunc();
                }
                
                if(e.getSource().toString().contains("Cancle")){
                    itemView.clearErrorTextField();
                    itemView.clearFrame();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    */
    private void updateComboTree(){
        treeModel = itemModel.getItemCategoryForTree();
        itemView.setTree(treeModel);
        category_list = itemModel.getItemCategoryCombo();
        itemView.setCategory(category_list);
    }
   
    public void addFunc(){
        String categoryName = itemView.getCategory();
        //System.out.println(categoryName);
        String parentCategory;
        int id;   
       /*
        if(categoryName.isEmpty()){
            DisplayMessages.displayError(itemView, "Enter Category Name", "Form Error");
           return;
       }
        */
         parentCategory = itemView.getParentCategory();
       boolean status = itemModel.checkDupItemCategory(categoryName);
       if(status){
           DisplayMessages.displayError(itemView, "Category Already Exist !", "Form Error");
           return;
       }
       /*
       if(categoryName.equalsIgnoreCase("ROOT")){ 
           DisplayMessages.displayError(itemView, "Category Name cannot be \"root\"!", "Form Error");
        }
       else if(!status){
            DisplayMessages.displayError(itemView, "Category Already Exist!", "Form Error");
        }
       */
        if(checkCategoryName(categoryName)){
        //    itemView.errorTextField();
                if(parentCategory.equalsIgnoreCase("root"))
                    itemModel.addItemCategory(categoryName, 1);
                else
                    itemModel.addSubCategory(categoryName, parentCategory);
                    
                updateComboTree();
                
                itemView.clearFrame();
                         
            }  
    }
    
    public void updateFunc(){
        String categoryName = itemView.getCategory();
        
        
        String parentCategory = itemView.getParentCategory();
        
        if(categoryName.isEmpty()){
            JOptionPane.showMessageDialog(itemView, "Please select an item first !",
                "Notification",JOptionPane.ERROR_MESSAGE);
                return;
        }
       
       /*boolean status = itemModel.checkDupItemCategory(categoryName);
       if(status){
           DisplayMessages.displayError(itemView, "Category Already Exist !", "Form Error");
           return;
       }*/
       int id = itemView.getCategoryId();
       if(itemModel.isContainsItem(id)){
           DisplayMessages.displayError(itemView, "This operation can not be proceed. \n Category contains item list.", "Error");
           itemView.clearFrame();
           return;
       }
        String oldCategoryName = itemView.getOldCategoryName();
        //System.out.println(oldCategoryName);
        String oldParent = itemView.getOldParent();
               
        if(oldParent.equalsIgnoreCase("root")){
            if(oldCategoryName.equalsIgnoreCase(parentCategory)){
                DisplayMessages.displayError(itemView, "Category can not be the sub cateogry of same Category !", "Form Error");
                return;
            }
            //determine subnode of root node, check for sub category
            boolean status = itemModel.checkNodeSubNode(oldCategoryName);
            //System.out.println(status);
            if(status){
                //if current parent is not root then insert to sub cateogry
                //delete the main category
                if(!parentCategory.equalsIgnoreCase("root")){
                    boolean dup = itemModel.checkDupItemOnChild(categoryName);
                    /*if(dup){
                        DisplayMessages.displayInfo(itemView, "Operation Failed ! Category Already Exists!", "Information");
                        return;
                    }
                    */
                    boolean opStatus = itemModel.changeParentToChild(categoryName, parentCategory, id);  
                    if(opStatus){
                        DisplayMessages.displayInfo(itemView, "Update Successful !", "Information");
                    }
                    else{
                        DisplayMessages.displayInfo(itemView, "Operation Failed!", "Information");
                    }
                }
                else{
                    //rename current parent category
                    itemModel.updateItemCategory(categoryName, id);
                }
                updateComboTree();
            }
            else{
                itemModel.displayError("Root category having subcatgory can't \n"
                    + "be the sub category of another category !");
            }
        }
        else{ 
        //sub node are free to move everywhere;
         //check for current parent;
         //if parent delete current and insert to main category
         //else update
            if(parentCategory.equalsIgnoreCase("root")){
                
                //itemModel.updateItemCategorySubNode(categoryName, parentCategory, id);
                  boolean dup = itemModel.checkDupItemOnParent(categoryName);
                    /*if(dup){
                        DisplayMessages.displayInfo(itemView, "Operation Failed !Category Already Exists!", "Information");
                        return;
                    }*/
                itemModel.changeChildToParent(categoryName, parentCategory, id);
            }
            else{
                //update only child category/item_sub_category
                itemModel.updateItemCategorySubNode(categoryName, parentCategory, id);
                
            }
            updateComboTree();
            //itemView.clearErrorTextField();
        }
        itemView.disableBtns();
    }
    
    public void deleteFunc(){
        String categoryName = itemView.getCategory();
        String parentCategory;
        int id;
        parentCategory = itemView.getParentCategory();
        
        if(categoryName.isEmpty()){
            JOptionPane.showMessageDialog(itemView, "Please select an item to delete !",
                "Notification",JOptionPane.YES_OPTION );
            return;
       }
       int status = JOptionPane.showConfirmDialog(null, "Are you sure you want do delete " + itemView.getOldCategoryName() + " category ?", "Conformation",JOptionPane.YES_NO_OPTION);
       //yes = 0; no = 1;
       if(status == 0){
            id = itemView.getCategoryId();
            //int id = itemView.getCategoryId();
       if(itemModel.isContainsItem(id)){
           DisplayMessages.displayError(itemView, "This operation can not be proceed. \n Category contains item list.", "Error");
           itemView.clearFrame();
           return;
       }
            
            //adjust subnode;
            if(parentCategory.equalsIgnoreCase("root")){
                //if cureent node is root then make subnode root;
                //itemModel.updateItemCategorySubNode(categoryName,"root");
            }
            else{
                //if current node is not root then make subnode it's parent's node;
                itemModel.deleteSubCategory(id);
           }
           itemModel.deleteItemCategory(id);
            updateComboTree();
           }
       else{
       }
       itemView.disableBtns();
    }
    
    private boolean checkCategoryName(String name){
        if(name.isEmpty()){
            DisplayMessages.displayError(itemView, "Enter Category Name", "Form Error");
            return false;
        }
        if(name.equalsIgnoreCase("root")){
            DisplayMessages.displayError(itemView, "Category Name cannot be \"root\"!", "Form Error");
            return false;
        }
        return true;
    }
}
