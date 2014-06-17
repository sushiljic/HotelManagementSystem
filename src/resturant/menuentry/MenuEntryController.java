/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.menuentry;

import hotelmanagementsystem.MainFrameView;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import reusableClass.DisplayMessages;
import reusableClass.Function;

/**
 *
 * @author SUSHIL
 */
public class MenuEntryController  {
    MenuEntryView MenuEntryView = new  MenuEntryView(new JFrame(),true);
    MenuEntryModel MenuEntryModel = new MenuEntryModel();
  private  MainFrameView mainFrameView;
  private Object[][] DeleteHybridData = null;
 
   private  String InitialMenuName ;
  // MenuEntryHybridDialogView HybridView = new MenuEntryHybridDialogView(MenuEntryView,true);
   // MenuEntryHybridDialogView

    public MenuEntryController(MenuEntryModel model,MenuEntryView view,MainFrameView mainFrameView) {
        MenuEntryModel =model;
        MenuEntryView = view;
        this.mainFrameView = mainFrameView;
      
        /*
         * for all user interface listnener
         */
       // MenuEntryView.addAddListener();
//        MenuEntryView.addTrackableListener(new TrackableListener());
        //ading trackableitemlistener
        MenuEntryView.addItemTrackableListener(new TrackableItemListener());
        MenuEntryView.addWholesaleListener(new WholesaleListener());
        //adding 
       // MenuEntryView.addWholesaleItemListener(new WholesaleItemListener());
        MenuEntryView.addComboItemNameSelectListener(new ComboListener());
        MenuEntryView.addComboItemBaseUnitSelectListener(new ComboListener());
        MenuEntryView.addComboItemCategoryListener(new ComboListener());
        MenuEntryView.addComboStoreNameListener(new ComboListener());
        /*
         * for crud operation listener
         */
        MenuEntryView.addAddListener(new MenuListener());
        MenuEntryView.addCancelListener(new MenuListener());
        MenuEntryView.addEditListener(new MenuListener());
        MenuEntryView.addDeleteListener(new MenuListener());
        /*
         * for row selected listener for jtable
         * 
         */
        MenuEntryView.addRowSelectionListener(new returnRowSelectedListener(MenuEntryView));
        MenuEntryView.addHybridRowSelectionListener(new HybridreturnRowSelectedListener(MenuEntryView));
      /*  StatusColumnCellRenderer cr = new StatusColumnCellRenderer();
        MenuEntryView.tblMenuItem.getColumnModel().getColumn(8).setCellRenderer(cr);
        * 
        * */
        MenuEntryView.addHybridMenuItemListener(new HybridListener());
        MenuEntryView.addComboHybridItemNameSelectListener(new ComboListener());
        MenuEntryView.addComboHybridItemBaseUnitSelectListener(new ComboListener());
        MenuEntryView.addHybridAddListener(new HybridMenuListener());
        MenuEntryView.addHybridCancelListener(new HybridMenuListener());
        MenuEntryView.addHybridFinishListener(new HybridMenuListener());
        MenuEntryView.addHybridDeleteListener(new HybridMenuListener());
        //MenuEntryView.addMenuNameListener(new MenuNameListener());
        //for image upload
       // UploadImage img = new UploadImage(MenuEntryView.ImageLabel);
        MenuEntryView.addImageUploadListener(new UploadImage(MenuEntryView.ImageLabel));
          /*
         * adding the combo
         */
           try{ 
//        MenuEntryView.refreshJTable(MenuEntryModel.getMenuList());
//         MenuEntryView.setComboItemName(MenuEntryModel.returnItemName(MenuEntryModel.getItemInfoForMenu()));
//         MenuEntryView.AddSelectInCombo(MenuEntryView.returnComboItemName());
         //adding for other item
         Function.AddSelectInCombo(MenuEntryView.returnComboItemBaseUnit());
         Function.AddSelectInCombo(MenuEntryView.returnComboItemCategory());
         Function.AddSelectInCombo(MenuEntryView.returnComboItemName());
         
         MenuEntryView.setComboDepartmentName(Function.returnSecondColumn(Function.getRespectiveDepartment(this.mainFrameView.getUserId())));
         //if it has only one element select it order wise add select into it
         
            int combosize = MenuEntryView.returnComboDepartmentName().getModel().getSize();
            if(combosize >1){
                Function.AddSelectInCombo(MenuEntryView.returnComboDepartmentName());
            }
            else{
                if(combosize == 1){
                MenuEntryView.returnComboDepartmentName().setSelectedIndex(0);
                }
            }
        
         
           }
           catch(Exception e){
               JOptionPane.showMessageDialog(MenuEntryView, e+"from constructor "+getClass().getName());
           }
        
       
    }
    /* doesnot works
    public class StatusColumnCellRenderer extends DefaultTableCellRenderer {
       
        @Override
        public Component getTableCellRendererComponent(  
JTable table, Object value, boolean isSelected, 
boolean hasFocus, int row, int col)
{
     Component comp = super.getTableCellRendererComponent(
                      table,  value, isSelected, hasFocus, row, col);
 
     Boolean s = (Boolean) table.getModel().getValueAt(row, 8 );
 
     if(s==false) 
     {
         comp.setBackground(Color.red);
     }
  
     return( comp );
 }
        
    }
*/
   
    public class HybridListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      //   HybridView.setVisible(true);
       //  MenuEntryView.setVisible(false);
            /*
             * load all the realative item name and item relative item
             * 
             */
             MenuEntryView.setComboHybridItemName(Function.returnSecondColumn(MenuEntryModel.getItemInfoForMenu(MenuEntryView.getDepartmentId())));
             Function.AddSelectInCombo(MenuEntryView.returnComboHybridItemName());
             Function.AddSelectInCombo(MenuEntryView.returnComboHybridItemBaseUnit());
           
           String[] columnName = new String[]{"Item Id","Item Name","Quantity","Item Base Unit","Unit Id"};
               DefaultTableModel tablemodel = new DefaultTableModel(null,columnName);
               MenuEntryView.tblHybridMenuItem.setModel(tablemodel);
               MenuEntryView.MenuEntryHybridDialog.setVisible(true);
               MenuEntryView.disableHybridAddBtn();
               MenuEntryView.disableHybridFinishBtn();
        }
        
    }
     public class UploadImage implements ActionListener{
        JLabel ImageLabel;
        public UploadImage(JLabel jlabel){
            ImageLabel = jlabel;
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      JFileChooser chooser = new JFileChooser(){
         /*    public void approveSelection(){
                  File f = getSelectedFile();
            if(f.exists()){
                int result = JOptionPane.showConfirmDialog(this, "DO you want to overswrite the existing file","File already exists",JOptionPane.YES_NO_CANCEL_OPTION);
                switch(result){
                    case JOptionPane.YES_OPTION:
                        super.approveSelection();
                        return;
                    case JOptionPane.NO_OPTION:
                        return;
                    case JOptionPane.CANCEL_OPTION:
                    case JOptionPane.CLOSED_OPTION:
                        cancelSelection();
                        return;
            }
        }
           // super.approveSelection();
        }*/
        };
             
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
      /*  chooser.setFileFilter(new FileFilter(){
@override
            public String getDescription(){
                return "All Supported Image Formats";
            }
            @Override
            public boolean accept(File f) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(f.isDirectory()){
                return true;
            }
            else{
                return f.getName().toLowerCase().endsWith(".png")||f.getName().toLowerCase().endsWith("jpg")||f.getName().toLowerCase().endsWith(".gif");
            }
            }
            
        });
        * */
 
        chooser.setFileFilter(new FileFilter() {

            
            @Override
            public boolean accept(File p) {
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(p.isDirectory()){
                return true;
            }
            else{
                return p.getName().toLowerCase().endsWith(".png")||p.getName().toLowerCase().endsWith(".jpg")||p.getName().toLowerCase().endsWith(".gif");
            }
            }

            @Override
            public String getDescription() {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return "png and jpeg image formats only";
            }
        });
        int res = chooser.showSaveDialog(null);
        if(res == JFileChooser.APPROVE_OPTION){
             MenuEntryView.setImageFile(chooser.getSelectedFile());
             if(!chooser.getFileFilter().accept(MenuEntryView.getImageFile())){
                  MenuEntryView.setImageFile(new File(MenuEntryView.getImageFile().getAbsoluteFile()+".png"));
              }
            
                    
            ImageIcon icon = new ImageIcon(MenuEntryView.getImageFile().getAbsolutePath());
            // ImageIcon imgicon = new ImageIcon("images/Welcome Scan.jpg");
           
         //  ImageLabel.setIcon(imgicon);
           
          //  jLabel1.setIcon(icon);
            Rectangle rect = ImageLabel.getBounds();
           Image scaledimage = icon.getImage().getScaledInstance(rect.width, rect.height, Image.SCALE_DEFAULT);
         //  System.out.println(rect.width+"\n"+rect.height);
            icon = new ImageIcon(scaledimage);
            
            //fro saving image into test directory
         
              /*  String fileName = File.getCanonicalPath();
            if (!fileName.endsWith(".png")) {
                File = new File(fileName + ".png");
            }*/
           //  BufferedImage  img = null;
              //covertin image into buffered image
            // img = ImageIO.read(file)
             
              MenuEntryView.setBufferedImage(new BufferedImage(rect.width,rect.height,BufferedImage.TYPE_INT_ARGB));
              MenuEntryView.getBufferedImage().getGraphics().drawImage(scaledimage, 0, 0, null);
             /*  Graphics2D bGr = buffImage.createGraphics();
    bGr.drawImage(scaledimage, 0, 0, null);
    bGr.dispose();
    */
          //   System.out.println(file.getName()); 
            ////  ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
////URL url = classLoader.getResource("");
//System.out.println(url.toURI());
//System.out.println(getClass().getClassLoader().getResource("")+"images");
//File file = new File(url.toURI());
              
            //  ImageIO.write
            //  ClassLoader cl = this.getClass().getClassLoader();
            //  String st = cl.getResource("")+"images/image.jpg";
              //URL url = getClass().getClassLoader().getResource("");
             // MenuEntryView.ImageLabel.setIcon(new ImageIcon(st));
       // System.out.println(cl.getResource(".")+"images/image.jpg");
            ImageLabel.setIcon(icon);
            MenuEntryView.setImagePath(MenuEntryView.getImageFile().getName());
            
            
        }
        else{
            JOptionPane.showMessageDialog(null, "cancelled by user");
        }
       
        }
    
}
    public class MenuNameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      //   HybridView.setVisible(true);
       //  MenuEntryView.setVisible(false);
            /*
             * load all the realative item name and item relative item
             * 
             */
            MenuEntryView.enableHybridBtn();
        }
        
    }
    
    public class MenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("Add")){
               
                  if(MenuEntryView.getDepartmentId() == 0){ 
                       JOptionPane.showMessageDialog(MenuEntryView, "Please Select the  Respective Store To Which Menu Is to be Made.");
                       return;
                       }
                  if(MenuEntryView.getMenuName().isEmpty()){
                    JOptionPane.showMessageDialog(MenuEntryView, "Menu Name is Empty");
                    return;
                }
                  if(MenuEntryModel.checkExistingName(MenuEntryView.getMenuName())){
                      JOptionPane.showMessageDialog(MenuEntryView, "Duplicate Menu name.Try some thing else");
                      return;
                  }
                if(MenuEntryView.checkTrackableUntrackable()){
                    if(!MenuEntryView.getHybridFlag()){
                     
                       if(MenuEntryView.getItemId() == 0){
                                JOptionPane.showMessageDialog(MenuEntryView, "Please select the Item from itemname");
                                return;
                             }
                           
                    }
                }
                
                if(MenuEntryView.getUnitId() == 0){
                    JOptionPane.showMessageDialog(MenuEntryView, "Please select the Item Base Unit");
                        return; 
                }
                
                if(MenuEntryView.getCategoryId() == 0){
                    JOptionPane.showMessageDialog(MenuEntryView, "Please select the Category");
                        return; 
                    
                }
                
                if(MenuEntryView.getQuantity() == 0.0){
                    JOptionPane.showMessageDialog(MenuEntryView, "Blank values are not Allowed in Quantity");
                    return;
                }
                 try{
                if(MenuEntryView.getRetailPrice() == 0.0){
                    JOptionPane.showMessageDialog(MenuEntryView, "Blank values are not Allowed in Retail price");
                    return;
                }
                }
                catch(Exception se){
                    JOptionPane.showMessageDialog(mainFrameView, se);
                }
                if(!MenuEntryView.checkWholepriceChecked()){
                    if(MenuEntryView.getWholesalePrice() == 0.0){
                    JOptionPane.showMessageDialog(MenuEntryView, "Blank values are not Allowed in Wholesale price");
                    return;
                    }
                }
                
                /*
                 * for saving image into images directory
                 */
//                 if(MenuEntryView.getBufferedImage()!= null){
                        //checking it images folder exists or not if not create
                
                           Path imagepath = Paths.get("resources/images");
                           if(!Files.exists(imagepath)){
                               Files.createDirectories(imagepath);
                           }
                
                
//                MenuEntryModel.SaveImage(MenuEntryView.getImageFile(), MenuEntryView.getBufferedImage());
//                }
                /*
                 * return null in second paremeter if hybrid menu is not selected
                 */
                           
                if(!MenuEntryView.getHybridFlag()){
                   
                MenuEntryModel.AddMenu(MenuEntryView.getMenuEntry(),null,Function.returnFileFromBufferedImage(MenuEntryView.getImageFile(),MenuEntryView.getBufferedImage()));
                    
                //  System.out.println("wala1");
                }
                else{
                   
                    MenuEntryModel.AddMenu(MenuEntryView.getMenuEntry(),MenuEntryView.getHybridData(),Function.returnFileFromBufferedImage(MenuEntryView.getImageFile(),MenuEntryView.getBufferedImage()) );
                }
                
              // System.out.println("wala2");
                MenuEntryView.refreshJTable(MenuEntryModel.getMenuList(MenuEntryView.getDepartmentId()));
              // System.out.println(MenuEntryView.getMenuName());
                
               
                // String MenuId = MenuEntryModel.returnMenuId(MenuEntryView.getMenuName());
                 //   System.out.println("test"+MenuId);
                     MenuEntryView.clearAll();
              //  System.out.println(MenuEntryModel.returnCurrentItentityId("menu"));
                
               
            }
            else if (e.getActionCommand().equalsIgnoreCase("Edit")){
                try{
                     if(MenuEntryView.getMenuName().isEmpty()){
                    JOptionPane.showMessageDialog(MenuEntryView, "Menu Name is Empty");
                    return;
                }
                    // System.out.println(MenuEntryView.getMenuName());
                     //if initial value is change check for new name to be unigew
                     if(!InitialMenuName.equalsIgnoreCase(MenuEntryView.getMenuName())){
                  if(MenuEntryModel.checkExistingName(MenuEntryView.getMenuName())){
                      JOptionPane.showMessageDialog(MenuEntryView, "Duplicate Menu name.Try some thing else");
                      return;
                  }
                     }
                    if(MenuEntryView.getMenuId()==0){
                        JOptionPane.showMessageDialog(MenuEntryView, "Please select from the list to edit");
                        return;
                    }
                     if(MenuEntryView.checkTrackableUntrackable()){
                         if(!MenuEntryView.getHybridFlag()){
                    if(MenuEntryView.getItemId()==0){
                        JOptionPane.showMessageDialog(MenuEntryView, "Please select the Item from itemname");
                        return;
                    }
                    }
                }
                if(MenuEntryView.getUnitId()==0){
                    JOptionPane.showMessageDialog(MenuEntryView, "Please select the Item Base Unit");
                        return; 
                }
                if(MenuEntryView.getQuantity()==0.0){
                    JOptionPane.showMessageDialog(MenuEntryView, "Blank values are not Allowed in Quantity");
                    return;
                }
                if(MenuEntryView.getRetailPrice() == 0.0){
                    JOptionPane.showMessageDialog(MenuEntryView, "Blank values are not Allowed in Retail price");
                    return;
                }
                if(!MenuEntryView.checkWholepriceChecked()){
                    if(MenuEntryView.getWholesalePrice() == 0.0){
                    JOptionPane.showMessageDialog(MenuEntryView, "Blank values are not Allowed in Wholesale price");
                    return;
                    }
                }
               
                int choice;
                choice = JOptionPane.showConfirmDialog(MenuEntryView, "Do you want to edit the Menu Item?","Edit Window",JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION)
                {
                     /*
                 * for saving image into images directory
                 */
//                 if(MenuEntryView.getBufferedImage()!= null){
////                MenuEntryModel.SaveImage(MenuEntryView.getImageFile(), MenuEntryView.getBufferedImage());
//                }
                   //checking it images folder exists or not if not create
                           Path imagepath = Paths.get("resources/images");
                           if(!Files.exists(imagepath)){
                               Files.createDirectories(imagepath);
                           } 
                 MenuEntryModel.EditMenu(MenuEntryView.getMenuEntry(),MenuEntryView.getHybridData(),DeleteHybridData,Function.returnFileFromBufferedImage(MenuEntryView.getImageFile(), MenuEntryView.getBufferedImage()));
              // MenuEntryView.clearJTable();
                 MenuEntryView.refreshJTable(MenuEntryModel.getMenuList(MenuEntryView.getDepartmentId()));
               //  System.out.println("wow");
//                 MenuEntryView.clearAll();
//                 MenuEntryView.disableDeleteBtn();
//                 MenuEntryView.disableEditBtn();
                  MenuEntryView.ClickCancelBtn();
                  //unset the edit flag
                  MenuEntryView.setEdit_Flag(false);
                }
                }
                catch(HeadlessException ee){
                    JOptionPane.showMessageDialog(MenuEntryView, ee+"from edit");
                }
            }
            else if (e.getActionCommand().equalsIgnoreCase("Delete")){
                try{
                     int choice;
                      // System.out.println(MenuEntryView.getMenuId());
                 //  System.out.println(MenuEntryModel.returnImagePath(MenuEntryView.getMenuId()));
                choice = JOptionPane.showConfirmDialog(MenuEntryView, "Do you want to Delete the Menu Item?","Delete Window",JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION){
                  
              // Files.deleteIfExists(MenuEntryView.getMenuId());
                 //  MenuEntryModel.DeleteImage(MenuEntryModel.returnImagePath(MenuEntryView.getMenuId())));
               // MenuEntryView.clearAll();
                 //  System.out.println(MenuEntryView.getMenuId());
                 //  System.out.println(MenuEntryModel.returnImagePath(MenuEntryView.getMenuId()));
//                    if(!MenuEntryModel.returnImagePath(MenuEntryView.getMenuId()).isEmpty()){
//                   Path path = Paths.get(MenuEntryModel.returnImagePath(MenuEntryView.getMenuId()));
//                   MenuEntryModel.DeleteImage(path);
//                    }
                    MenuEntryModel.DeleteMenu(MenuEntryView.getMenuId(),MenuEntryView.getHybridFlag());
//                 MenuEntryView.disableDeleteBtn();
//                 MenuEntryView.disableEditBtn();
//                MenuEntryView.clearAll();
                    MenuEntryView.ClickCancelBtn();
                     //unset the edit flag
                  MenuEntryView.setEdit_Flag(false);
                  MenuEntryView.refreshJTable(MenuEntryModel.getMenuList(MenuEntryView.getDepartmentId()));
                }
                
                 }
                catch(Exception de){
                    JOptionPane.showMessageDialog(MenuEntryView, de+"from delete");
                }
            }
            else if (e.getActionCommand().equalsIgnoreCase("Cancel")){
                try{
                 //   MenuEntryView.refreshJTable(MenuEntryModel.getMenuList());
                    MenuEntryView.clearAll();
                    MenuEntryView.enableAddBtn();
                    MenuEntryView.disableDeleteBtn();
                    MenuEntryView.disableEditBtn();
                    MenuEntryView.enableHybridBtn();
                    MenuEntryView.setComboDepartmentEnable(true);
//                    MenuEntryView.returnComboDepartmentName().setSelectedIndex(0);
                    MenuEntryView.setComboDepartmentName(Function.returnSecondColumn(Function.getRespectiveDepartment(mainFrameView.getUserId())));
//                    MenuEntryView.AddSelectInCombo(MenuEntryView.returnComboDepartmentName());
                    //code for selecting if there is only one combo box  
                    if(MenuEntryView.returnComboDepartmentName().getModel().getSize() > 1){
                          Function.AddSelectInCombo(MenuEntryView.returnComboDepartmentName());
                        }
                        else{
                            MenuEntryView.returnComboDepartmentName().setSelectedIndex(0);
                        }
                    //added code
                    MenuEntryView.radioNonTrackable.setEnabled(true);
                    MenuEntryView.radioTrackable.setEnabled(true);
                    MenuEntryView.radioTrackable.setSelected(true);
//                    MenuEntryView.radioWholesaleClick.setSelected(true);
                }
                catch(Exception ce){
                    JOptionPane.showMessageDialog(MenuEntryView, ce+"from cancel");
                }
            }
            
        }
        catch(HeadlessException | IOException me){
            JOptionPane.showMessageDialog(MenuEntryView, me+"from Menulistener");
        }
        }
        
    }
     public class TrackableListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
            if(e.getActionCommand().equalsIgnoreCase("Trackable")){
                //JOptionPane.showMessageDialog(MenuEntryView, "");
                MenuEntryView.setTrackable(true);
                MenuEntryView.showItemName();
                MenuEntryView.enableHybridBtn();
               // MenuEntryView.setComboItemBaseUnit(null);
             //  MenuEntryView.clearItemBaseUnit();
             //  MenuEntryView.clearItemCategory();
                
                
               
            }
            else if (e.getActionCommand().equalsIgnoreCase("NonTrackable")){
                MenuEntryView.setTrackable(false);
                MenuEntryView.hideItemName();
                MenuEntryView.disableHybridBtn();
              Object[][]  ItemUnitInfo = MenuEntryModel.getUnitInfo(MenuEntryView.getUnitId(),false);
          
            /*
             * this load all the relative unitname that can be used to issue the item
             */
           // JOptionPane.showMessageDialog(issueView, ItemUnitInfo);
            MenuEntryView.setComboItemBaseUnit(Function.returnSecondColumn(ItemUnitInfo));
            Function.AddSelectInCombo(MenuEntryView.returnComboItemBaseUnit());
        
            Object[][] ItemCategory = MenuEntryModel.getSubCategoryInfo();
            MenuEntryView.setComboItemCategory(Function.returnSecondColumn(ItemCategory));
            Function.AddSelectInCombo(MenuEntryView.returnComboItemCategory());
                
            }
           
            
        }
        
        catch(Exception te){
         JOptionPane.showMessageDialog(MenuEntryView, te+"from trackablelistener");
     }
        }
         
     }
     public class TrackableItemListener implements ItemListener{
      

        @Override
        public void itemStateChanged(ItemEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           try{
         if(e.getStateChange() == 1){
                MenuEntryView.setTrackable(true);
                MenuEntryView.showItemName();
//                MenuEntryView.showStoreName();
                MenuEntryView.enableHybridBtn();
                 
              
         }
         else{
               MenuEntryView.setTrackable(false);
                MenuEntryView.hideItemName();
//                MenuEntryView.hideStoreName();
                MenuEntryView.disableHybridBtn();
              Object[][]  ItemUnitInfo = MenuEntryModel.getUnitInfo(MenuEntryView.getUnitId(),false);
          
            /*
             * this load all the relative unitname that can be used to issue the item
             */
           // JOptionPane.showMessageDialog(issueView, ItemUnitInfo);
            MenuEntryView.setComboItemBaseUnit(Function.returnSecondColumn(ItemUnitInfo));
            Function.AddSelectInCombo(MenuEntryView.returnComboItemBaseUnit());
        
            Object[][] ItemCategory = MenuEntryModel.getSubCategoryInfo();
            MenuEntryView.setComboItemCategory(Function.returnSecondColumn(ItemCategory));
            Function.AddSelectInCombo(MenuEntryView.returnComboItemCategory());
         }
     }
         catch(Exception ie){
         JOptionPane.showMessageDialog(MenuEntryView, ie+"from trackableItemListener");
     }
        }
     }
      public class WholesaleItemListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(e.getStateChange()==ItemEvent.SELECTED){
                MenuEntryView.setEditableWholesalePricefalse(); 
            }
            else{
                MenuEntryView.setEditableWholesalePricetrue();
            }
        }
         
     }
    
     public class WholesaleListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
             if(e.getActionCommand().equalsIgnoreCase("Wholesale")){
            JRadioButton track = (JRadioButton) e.getSource();
            if(track.isSelected()){
                MenuEntryView.setEditableWholesalePricefalse();
                MenuEntryView.setWholesalePrice(0.0);
            }
            else/* if(!track.isSelected())*/{
                MenuEntryView.setEditableWholesalePricetrue();
                 MenuEntryView.setWholesalePrice(0.0);
            }
        }
            
        }
         
     }
     public class ComboListener implements ActionListener{
          private    Object item[];
    private Object[][] ItemCategoryInfo;
        @Override
        public void actionPerformed(ActionEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       if(e.getActionCommand().equalsIgnoreCase("ItemName")){
         //   JOptionPane.showMessageDialog(MenuEntryView, "wala");
          try{
              // System.out.println(MenuEntryModel.Itemdata.length);
               JComboBox jcomboname =(JComboBox) e.getSource();
               if(jcomboname.getSelectedIndex() == 0){
                  MenuEntryView.setItemId(0);
//                  MenuEntryView.setItemBaseUnit("");
//                  MenuEntryView.setCategoryId(0);
                  MenuEntryView.returnComboItemCategory().setSelectedIndex(0);
                  MenuEntryView.returnComboItemBaseUnit().setSelectedIndex(0);
               }
               else{
              for (Object[] Itemdata : MenuEntryModel.Itemdata) {
                  if (Itemdata[1].equals(jcomboname.getSelectedItem())) {
                      //   System.out.println("check1");
                      item = Itemdata;
                      break;
                  }
              }
              //now retreiving
              //   MenuEntryView.setOnComboItemNameSelect(item);
           
                ItemCategoryInfo = MenuEntryModel.getUnitInfo(Integer.parseInt(item[2].toString()),true);
                
           //SetonComboItemNameSelect Doesnot selecet combo itemBaseUnit done mannually by another fucntion
            
        
            /*
             * this load all the relative unitname that can be used to issue the item
             */
           // JOptionPane.showMessageDialog(issueView, ItemUnitInfo);
            MenuEntryView.setComboItemBaseUnit(Function.returnSecondColumn(ItemCategoryInfo));
            Function.AddSelectInCombo(MenuEntryView.returnComboItemBaseUnit());
             Object[][] ItemCategory = MenuEntryModel.getSubCategoryInfo();
            MenuEntryView.setComboItemCategory(Function.returnSecondColumn(ItemCategory));
            Function.AddSelectInCombo(MenuEntryView.returnComboItemCategory());
            //MenuEntryView.setComboItemCategory(category);
            //loading only after loading alla comboitembaseunit
            MenuEntryView.setOnComboItemNameSelect(item);
               }
            
            
           }
           catch(NumberFormatException ex){
               JOptionPane.showMessageDialog(MenuEntryView,ex+"from combolistener");
           }
           
           
        }
       else if (e.getActionCommand().equalsIgnoreCase("ItemBaseUnit")){
        Object[] units = null;
           //JOptionPane.showMessageDialog(MenuEntryView,"ea");
        try{
           JComboBox jBaseUnit = (JComboBox) e.getSource();
         //i beleive this is useless upto now
           /*  if(MenuEntryView.getTrackable()){
               System.out.println(MenuEntryView.getUnitId());
          ItemUnitInfo = MenuEntryModel.getUnitInfo(MenuEntryView.getUnitId(),true);
           }
           else { */
            ItemCategoryInfo = MenuEntryModel.getUnitInfo(0,false);    
            if(jBaseUnit.getSelectedIndex() == 0){
                MenuEntryView.setUnitId(0);
            }
            else{
            for (Object[] ItemCategoryInfo1 : ItemCategoryInfo) {
                if (ItemCategoryInfo1[1].equals(jBaseUnit.getSelectedItem())) {
                    units = ItemCategoryInfo1;
                    break;
                }
            }
           MenuEntryView.setUnitId(Integer.parseInt(units[0].toString()));   
            }
       // System.out.println(units[0]);
        }
        catch(NumberFormatException see){
            JOptionPane.showMessageDialog(MenuEntryView, see+"from itembaseunit");
        }
       }
       else if (e.getActionCommand().equalsIgnoreCase("ItemCategory")){
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
            ItemCategoryInfo = MenuEntryModel.getSubCategoryInfo();
            if(jCat.getSelectedIndex() == 0){
                MenuEntryView.setCategoryId(0);
            }
            else{
            for (Object[] ItemCategoryInfo1 : ItemCategoryInfo) {
                if (ItemCategoryInfo1[1].equals(jCat.getSelectedItem())) {
                    cat_id = ItemCategoryInfo1;
                    break;
                }
            }
           MenuEntryView.setCategoryId(Integer.parseInt(cat_id[0].toString()));     
        //System.out.println(cat_id[0]);
        }
            
            
        }
        catch(NumberFormatException see){
            JOptionPane.showMessageDialog(MenuEntryView, see+"from itembaseunit");
        }
       }
       /*
        * this is for jdailogbox comboboxlistener
        */
       else if(e.getActionCommand().equalsIgnoreCase("HybridItemName")){
         //   JOptionPane.showMessageDialog(MenuEntryView, "wala");
          try{
              // System.out.println(MenuEntryModel.Itemdata.length);
               JComboBox jcomboname =(JComboBox) e.getSource();
               if(jcomboname.getSelectedIndex() == 0){
                   MenuEntryView.setHybridItemId(0);
                   MenuEntryView.setHybridUnitId(0);
                   
               }
               else{
              for (Object[] Itemdata : MenuEntryModel.Itemdata) {
                  if (Itemdata[1].equals(jcomboname.getSelectedItem())) {
                      //   System.out.println("check1");
                      item = Itemdata;
                      break;
                  }
              }
               //now retreiving 
           //   MenuEntryView.setOnComboItemNameSelect(item);
          // JOptionPane.showMessageDialog(null, "wala");
                ItemCategoryInfo = MenuEntryModel.getUnitInfo(Integer.parseInt(item[2].toString()),true);
                
           //SetonComboItemNameSelect Doesnot selecet combo itemBaseUnit done mannually by another fucntion
            
        
            /*
             * this load all the relative unitname that can be used to issue the item
             */
           // JOptionPane.showMessageDialog(issueView, ItemUnitInfo);
            MenuEntryView.setComboHybridItemBaseUnit(Function.returnSecondColumn(ItemCategoryInfo));
            Function.AddSelectInCombo(MenuEntryView.returnComboHybridItemBaseUnit());
            //MenuEntryView.setComboItemCategory(category);
            //loading only after loading alla comboitembaseunit
           MenuEntryView.setOnComboHybridItemNameSelect(item);
           MenuEntryView.enableHybridAddBtn();
           MenuEntryView.enableHybridFinishBtn();
               }
            
            
           }
           catch(NumberFormatException ex){
               JOptionPane.showMessageDialog(MenuEntryView,ex+"from hybriditemnamecombolistener");
           }
           
           
        }
       else if (e.getActionCommand().equalsIgnoreCase("HybridItemBaseUnit")){
        Object[] units = null;
           //JOptionPane.showMessageDialog(MenuEntryView,"ea");
        try{
           JComboBox jBaseUnit = (JComboBox) e.getSource();
           if(MenuEntryView.getTrackable()){
               
          ItemCategoryInfo = MenuEntryModel.getUnitInfo(MenuEntryView.getHybridUnitId(),true);
         
           }
           else if(!MenuEntryView.getTrackable()){
            ItemCategoryInfo = MenuEntryModel.getUnitInfo(0,false);    
           }
           if(jBaseUnit.getSelectedIndex() == 0){
               MenuEntryView.setHybridUnitId(0);
           }
           else{
            for (Object[] ItemCategoryInfo1 : ItemCategoryInfo) {
                if (ItemCategoryInfo1[1].equals(jBaseUnit.getSelectedItem())) {
                    units = ItemCategoryInfo1;
                    break;
                }
            }
        //   JOptionPane.showMessageDialog(null, "walaa");
           MenuEntryView.setHybridUnitId(Integer.parseInt(units[0].toString()));    
           }
        // System.out.println(MenuEntryView.getUnitId());
        }
        catch(NumberFormatException see){
            JOptionPane.showMessageDialog(MenuEntryView, see+"from hybriditembaseunit");
        }
       }
       
       else if(e.getActionCommand().equalsIgnoreCase("ItemCategory")){
           Object[] cat = null;  
           try{
              // JOptionPane.showMessageDialog(MenuEntryView, "wala");
               JComboBox jCat = (JComboBox) e.getSource();
               Object[][] catInfo = MenuEntryModel.getSubCategoryInfo();
               if(jCat.getSelectedIndex() == 0){
                   MenuEntryView.setCategoryId(0);
//                   JOptionPane.showMessageDialog(null, "walais");
               }
               else{
               for (Object[] catInfo1 : catInfo) {
                   if (catInfo1[1].equals(jCat.getSelectedItem())) {
                       cat = catInfo1;
                       break;
                   }
               }
               MenuEntryView.setCategoryId(Integer.parseInt(cat[0].toString()));
             //  System.out.println(MenuEntryView.getCategoryId());
               }
}
           catch(NumberFormatException ce){
               JOptionPane.showMessageDialog(MenuEntryView, ce+"from itemcatrgory");
           }
       }
       //this is for store name select
         else if (e.getActionCommand().equalsIgnoreCase("ComboStoreName")){
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
          Object[][]  StoreInfo = Function.getRespectiveDepartment(mainFrameView.getUserId());
            if(jCat.getSelectedItem().equals("SELECT")){
                MenuEntryView.setDepartmentId(0);
                MenuEntryView.returnTableModelMenuItem().setRowCount(0);
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
           MenuEntryView.setDepartmentId(Integer.parseInt(cat_id[0].toString()));
           // loading repective item
           MenuEntryView.setComboItemName(Function.returnSecondColumn(MenuEntryModel.getItemInfoForMenu(MenuEntryView.getDepartmentId())));
           Function.AddSelectInCombo(MenuEntryView.returnComboItemName());
           MenuEntryView.refreshJTable(MenuEntryModel.getMenuList(MenuEntryView.getDepartmentId()));
        //System.out.println(cat_id[0]);
        }
            
            
        }
        catch(Exception see){
            JOptionPane.showMessageDialog(MenuEntryView, see+"from itembaseunit"+getClass().getName());
        }
       }
       
     }
    
    
    
}
     
     public  class returnRowSelectedListener implements ListSelectionListener{
         MenuEntryView mview;
         JTable mtable;
         public returnRowSelectedListener(MenuEntryView view){
             mview = view;
             mtable = mview.tblMenuItem;
         }
        @Override
        public void valueChanged(ListSelectionEvent e) {
        //   int i =1;
             //Ignore extra messages.
            try{
        if (e.getValueIsAdjusting()) return;

        ListSelectionModel lsm =
            (ListSelectionModel)e.getSource();
        if (lsm.isSelectionEmpty()) {
            //no rows are selected
        } else {
           // int selectedRow = lsm.getMinSelectionIndex();
           //selectedRow is selected
            ListSelectionModel listmodel = mtable.getSelectionModel();
           int lead = listmodel.getLeadSelectionIndex();
           String[] data = Function.displayRowValues(lead,mtable);
           //set  check box for trackable and non trackable
           mview.setHybridFlag(Boolean.parseBoolean(data[9]));
           mview.setTrackableUntrackable(Boolean.parseBoolean(data[8]));
           mview.setTrackable(Boolean.parseBoolean(data[8]));
           /*
           loading the info about item unit and category type
           */
            if(MenuEntryView.getHybridFlag()){
//            JOptionPane.showMessageDialog(mainFrameView, "wala");
            Object[][]  ItemUnitInfo = MenuEntryModel.getUnitInfo(MenuEntryView.getUnitId(),false);
          
            /*
             * this load all the relative unitname that can be used to issue the item
             */
           // JOptionPane.showMessageDialog(issueView, ItemUnitInfo);
            MenuEntryView.setComboItemBaseUnit(Function.returnSecondColumn(ItemUnitInfo));
            Function.AddSelectInCombo(MenuEntryView.returnComboItemBaseUnit());
        
            Object[][] ItemCategory = MenuEntryModel.getSubCategoryInfo();
            MenuEntryView.setComboItemCategory(Function.returnSecondColumn(ItemCategory));
            Function.AddSelectInCombo(MenuEntryView.returnComboItemCategory());
                }
          
           mview.setMenuEntry(data);
           
          //for image path
           mview.setImagePath(MenuEntryModel.returnImagePath(mview.getMenuId()));
          // System.out.println(mview.getImagePath());
           //for drawing the image 
           mview.loadImage(MenuEntryModel.returnImage(mview.getMenuId()));
           //setting initail name at the time of editing
           InitialMenuName = mview.getMenuName();
           
           //System.out.println(i++);
          
          
           //for indicating for hydrid data edit
            mview.setEdit_Flag(true);
         
            if(mview.getHybridFlag()){
                //if the item type is hybrid
//                mview.disableEditBtn();
                mview.hideItemName();
                
                mview.refreshHybridTable(MenuEntryModel.getHybridItemData(mview.getMenuId()));
                //copying the default table model to object[][]
                DeleteHybridData = MenuEntryModel.convertDefaultTableModelToObject((DefaultTableModel)mview.tblHybridMenuItem.getModel());
                 /*
             * load all the realative item name and item relative item
             * 
             */
               
             MenuEntryView.setComboHybridItemName(Function.returnSecondColumn(MenuEntryModel.getItemInfoForMenu(MenuEntryView.getDepartmentId())));
             Function.AddSelectInCombo(MenuEntryView.returnComboHybridItemName());
             Function.AddSelectInCombo(MenuEntryView.returnComboHybridItemBaseUnit());
                mview.MenuEntryHybridDialog.setVisible(true);
               
                
           }
            mview.disableAddBtn();
            mview.enableDeleteBtn();
            mview.enableEditBtn();
            mview.disableHybridBtn();
            mview.setComboDepartmentEnable(false);
            
            //added code
          
            
            
        }
         }
        catch(Exception se){
            JOptionPane.showMessageDialog(mview, se+"from returnrowlistener");
        }
            
        
        }
        
    
}
     public class HybridreturnRowSelectedListener implements ListSelectionListener{
         MenuEntryView mview;
         JTable mtable;
         public HybridreturnRowSelectedListener(MenuEntryView m){
             mview = m;
             mtable = mview.tblHybridMenuItem;
         }
        @Override
        public void valueChanged(ListSelectionEvent e) {
            try{
                if(e.getValueIsAdjusting()) return;
                ListSelectionModel lsm = mtable.getSelectionModel();
                if(lsm.isSelectionEmpty()){
                    
                }
                else{
                   //code to write
                    int lead = lsm.getLeadSelectionIndex();
                    //wrtie the row of table to delete
                    mview.setHybridLead(lead);
                    mview.setHybridDeleteBtnEnable(true);
                    
                }
            }
            catch(Exception se){
                DisplayMessages.displayError(mview, se.getMessage()+"from HybridreturnRowSelectedListener "+getClass().getName(), "Error");
            }
        }
         
     }
     public class HybridMenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("HybridAdd")){
              //  System.err.println("wala");
            //   System.out.println(MenuEntryView.getMenuName());
            //   System.out.println(MenuEntryView.getMenuId());
                /*
               System.out.println(MenuEntryView.getUnitId());
               System.out.println(MenuEntryView.getItemId());
               System.out.println(MenuEntryView.getHybridItemName());
               System.out.println(MenuEntryView.getHybridQuantity());
               */
                  if(MenuEntryView.getHybridItemId()==0){
                    JOptionPane.showMessageDialog(MenuEntryView.MenuEntryHybridDialog, "Please select the Item Name.");
                    return; 
                }
                if(MenuEntryView.getHybridQuantity().isEmpty()){
                    JOptionPane.showMessageDialog(MenuEntryView.MenuEntryHybridDialog, "Blank field detected in Quantity.");
                    return;
                }
                if(MenuEntryView.getHybridUnitId()==0){
                     JOptionPane.showMessageDialog(MenuEntryView.MenuEntryHybridDialog, "Please Select item category");
                    return;
                }
                try{
                   Float.parseFloat(MenuEntryView.getHybridQuantity());
                }
                catch(NumberFormatException ne){
                    JOptionPane.showMessageDialog(MenuEntryView.MenuEntryHybridDialog,"Please enter Only Numbers");
                    return;
                }
              
               Object row[] = new Object[]{MenuEntryView.getHybridItemId(),MenuEntryView.getHybridItemName(),MenuEntryView.getHybridQuantity(),MenuEntryView.getHybridItemBaseUnit(),MenuEntryView.getHybridUnitId()};
               
               DefaultTableModel tbmodel = (DefaultTableModel) MenuEntryView.tblHybridMenuItem.getModel();
               tbmodel.addRow(row);
               MenuEntryView.clearAllHybrid();
                
            }
            else if(e.getActionCommand().equalsIgnoreCase("HybridDelete")){
//                if(MenuEntryView.getHybridLead() == 0){
//                    DisplayMessages.displayInfo(mainFrameView, "Please Select the Item To Delete from table", "Hybrid Window");
//                    return;
//                }
                if(DisplayMessages.displayInputYesNo(MenuEntryView.tblHybridMenuItem, "Do You Want To Delete the Item", "Hybrid Delete Window")){
                   DefaultTableModel tbmodel = (DefaultTableModel) MenuEntryView.tblHybridMenuItem.getModel();
                   tbmodel.removeRow(MenuEntryView.getHybridLead());
                   MenuEntryView.setHybridLead(0);
                   MenuEntryView.setHybridDeleteBtnEnable(false);
                }
        }
            else if(e.getActionCommand().equalsIgnoreCase("HybridCancel")){
               //  System.err.println("walas");
                MenuEntryView.clearAllHybrid();
                MenuEntryView.setHybridFlag(false);
               // MenuEntryView.MenuEntryHybridDialog.setVisible(false);
              DefaultTableModel model =   (DefaultTableModel) MenuEntryView.tblHybridMenuItem.getModel();
              model.setRowCount(0);
            //   MenuEntryView.MenuEntryHybridDialog.setVisible(false);
            }
            else if(e.getActionCommand().equalsIgnoreCase("HybridFinish")){
                
                MenuEntryView.setHybridFlag(true);
                DefaultTableModel model =   (DefaultTableModel) MenuEntryView.tblHybridMenuItem.getModel();
                if(model.getRowCount()<1){
                    JOptionPane.showMessageDialog(MenuEntryView.MenuEntryHybridDialog, "Please Add atleast One Item");
                    return;
                }
             
               MenuEntryView.setHybridData(MenuEntryModel.convertDefaultTableModelToObject(model));
                MenuEntryView.MenuEntryHybridDialog.setVisible(false);
               
                /*
                 * set the view as of nontracable
                 * hiding itemname and loading unitid and category
                 */
                 MenuEntryView.hideItemName();
                 //donot refresh the  unit_type and category if it is not edit type
                 if(!MenuEntryView.isEdit_Flag()){
              Object[][]  ItemUnitInfo = MenuEntryModel.getUnitInfo(MenuEntryView.getUnitId(),false);
//          JOptionPane.showMessageDialog(mainFrameView, "Wala");
            /*
             * this load all the relative unitname that can be used to issue the item
             */
           // JOptionPane.showMessageDialog(issueView, ItemUnitInfo);
            MenuEntryView.setComboItemBaseUnit(Function.returnSecondColumn(ItemUnitInfo));
            Function.AddSelectInCombo(MenuEntryView.returnComboItemBaseUnit());
            Object[][] ItemCategory = MenuEntryModel.getSubCategoryInfo();
            MenuEntryView.setComboItemCategory(Function.returnSecondColumn(ItemCategory));
            Function.AddSelectInCombo(MenuEntryView.returnComboItemCategory());
                 }  
            }
            
        }
        catch(HeadlessException he){
            JOptionPane.showMessageDialog(MenuEntryView, he+"from HybridMenuListener");
        }
        }
         
     }
}
