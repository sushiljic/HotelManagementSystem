/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.menuentry;

import database.DBConnect;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import reusableClass.DisplayMessages;


/**
 *
 * @author SUSHIL
 */
public class MenuEntryModel extends DBConnect {
    public Object[][] Itemdata;
    private  PreparedStatement stmtItemInfo;
    private PreparedStatement stmtUnitInfo;
    private PreparedStatement stmtAddMenu;
    private PreparedStatement stmtEditMenu;
    private PreparedStatement stmtHybridAdd ; 
    private PreparedStatement stmtDeleteMenu;
    private ResultSet rsResult;
    private ResultSet rsEditMenu;
    
    
        
        public void AddMenu(String[] menuinfo,Object[][] hybriddata,File image){
            /*
             *
            menuInfo[0] = String.valueOf(checkTrackableUntrackable());
            menuInfo[1] = String.valueOf(getItemId());
            menuInfo[2] = getItemName();
            menuInfo[3] = String.valueOf(getMenuId());
            menuInfo[4] = getMenuName();
            menuInfo[5] = String.valueOf(getUnitId());
            menuInfo[6] = getItemBaseUnit();
            menuInfo[7] = getQuantity();
            menuInfo[8] = getRetailPrice();
           if(checkWholepriceChecked()){
            menuInfo[9] = ""; 
                    }
           else if(!checkWholepriceChecked()){
            menuInfo[9] = getWholesalePrice();
        }
         menuInfo[10] = String.valueOf(getCategoryId());
         menuInfo[11] = String.valueOf(getHybridFlag());
         menuInfo[12] = getImagePath();
         menuInfo[13]= String.valueOf(getStoreId());
             */
           boolean HybridFlag  = false; 
            int MenuId = 0;
            String strmenutrackable = "INSERT INTO menu (menu_name,item_type,department_item_id,quantity,unit_id,retail_price,wholesale_price,date,hybrid_type,category_id,image_path,department_id,menu_image) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            String strmenuuntrackable = "INSERT INTO menu(menu_name,item_type,quantity,unit_id,retail_price,wholesale_price,category_id,date,hybrid_type,image_path,department_id,menu_image) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            String strHybridAdd = "INSERT INTO hybrid_menu(department_item_id,quantity,unit_id,parent_menu_id) VALUES(?,?,?,?)";
            BigDecimal Quantity;
            DBConnect menu = new DBConnect();
            FileInputStream fis = null;
            try{
                menu.initConnection();
                if(image != null){
                    fis = new FileInputStream(image);
                }
                /*if 
                 * if menuinfo[0] equals true then it is trackable item else it is non trackable
                 */
                if(menuinfo[0].equalsIgnoreCase("true")){
                    /*
                     * if menuitem is single/non hybrid type use stmrmenutrackable others strmenuuntrackable
                     */
                                if(menuinfo[11].equalsIgnoreCase("false")){
                                      menu.conn.setAutoCommit(false);
                            stmtAddMenu = menu.conn.prepareStatement(strmenutrackable);
                          
                            stmtAddMenu.setString(1, menuinfo[4]);
                            stmtAddMenu.setInt(2, 1);
                          
                           
                            stmtAddMenu.setInt(3, Integer.parseInt(menuinfo[1]));
                           
                            stmtAddMenu.setBigDecimal(4,new BigDecimal(menuinfo[7]).setScale(3, RoundingMode.HALF_UP));
                            stmtAddMenu.setInt(5, Integer.parseInt(menuinfo[5]));
                            stmtAddMenu.setBigDecimal(6, new BigDecimal(menuinfo[8]).setScale(2, RoundingMode.HALF_UP));
                            if(!menuinfo[9].isEmpty()){
                               stmtAddMenu.setBigDecimal(7, new BigDecimal(menuinfo[9]).setScale(2, RoundingMode.HALF_UP));
                            }
                            else{
                                stmtAddMenu.setBigDecimal(7, null);
                            }
                          stmtAddMenu.setTimestamp(8, new Timestamp(new Date().getTime()));
                          // for hybrid menu item
                          stmtAddMenu.setInt(9, 0);
                          HybridFlag = false;
                          stmtAddMenu.setInt(10,Integer.parseInt(menuinfo[10]));
                          stmtAddMenu.setString(11, menuinfo[12]);
                          stmtAddMenu.setInt(12,Integer.parseInt(menuinfo[13]));
//                          if(image!= null){
                          stmtAddMenu.setBinaryStream(13, fis);
//                          }
//                          else{
//                              stmtAddMenu.setBinaryStream(13, null);
//                          }
                           stmtAddMenu.executeUpdate();
                          /*
                           * 
                           */
                                }
                                else{
                                      menu.conn.setAutoCommit(false);
                                    stmtAddMenu = menu.conn.prepareStatement(strmenuuntrackable);
                             stmtAddMenu.setString(1, menuinfo[4]);
                            stmtAddMenu.setInt(2, 1);
                           // stmtAddMenu.setString(3, menuinfo[1]);
                            stmtAddMenu.setBigDecimal(3,new BigDecimal(menuinfo[7]).setScale(3, RoundingMode.HALF_UP));
                            stmtAddMenu.setString(4, menuinfo[5]);
                            stmtAddMenu.setBigDecimal(5, new BigDecimal(menuinfo[8]).setScale(2, RoundingMode.HALF_UP));
                            if(!menuinfo[9].isEmpty()){
                               stmtAddMenu.setBigDecimal(6, new BigDecimal(menuinfo[9]).setScale(2, RoundingMode.HALF_UP));
                            }
                             else{
                                stmtAddMenu.setBigDecimal(6, null);
                            }
                            stmtAddMenu.setInt(7,Integer.parseInt(menuinfo[10]));
                            stmtAddMenu.setTimestamp(8, new Timestamp(new Date().getTime()));
                            //setting the hybrid flag for hybrid item
                            stmtAddMenu.setInt(9, 1);
                            HybridFlag = true;
                            stmtAddMenu.setString(10, menuinfo[12]);
                            stmtAddMenu.setInt(11,Integer.parseInt(menuinfo[13]));
//                            if(image!= null){
                                  stmtAddMenu.setBinaryStream(12, fis);
//                              }
//                            else{
//                              stmtAddMenu.setBinaryStream(12, null);
//                          }
                            stmtAddMenu.executeUpdate();
                            //retreiving the menu_id
                            menu.conn.setAutoCommit(false);
                             stmtAddMenu = menu.conn.prepareStatement("SELECT last_insert_id() as menuid ");
                             rs = stmtAddMenu.executeQuery();
                             while(rs.next()){
                                 MenuId = rs.getInt("menuid");
                             }

                                }
                        }
                else if(menuinfo[0].equalsIgnoreCase("false")){
                      menu.conn.setAutoCommit(false);
                stmtAddMenu = menu.conn.prepareStatement(strmenuuntrackable);
                stmtAddMenu.setString(1, menuinfo[4]);
                stmtAddMenu.setInt(2, 0);
               // stmtAddMenu.setString(3, menuinfo[1]);
                stmtAddMenu.setBigDecimal(3,new BigDecimal(menuinfo[7]).setScale(3, RoundingMode.HALF_UP));
                stmtAddMenu.setString(4, menuinfo[5]);
                stmtAddMenu.setBigDecimal(5, new BigDecimal(menuinfo[8]).setScale(2, RoundingMode.HALF_UP));
                if(!menuinfo[9].isEmpty()){
                   stmtAddMenu.setBigDecimal(6, new BigDecimal(menuinfo[9]).setScale(2, RoundingMode.HALF_UP));
                }
                 else{
                    stmtAddMenu.setBigDecimal(6, null);
                }
                stmtAddMenu.setInt(7,Integer.parseInt(menuinfo[10]));
                stmtAddMenu.setTimestamp(8, new Timestamp(new Date().getTime()));
               //setting the hybrid flag for hybrid item
                stmtAddMenu.setInt(9, 0);
                HybridFlag = false;
                 stmtAddMenu.setString(10, menuinfo[12]);
                 stmtAddMenu.setInt(11,Integer.parseInt(menuinfo[13]));
//                 if(image!= null){
                    stmtAddMenu.setBinaryStream(12, fis);
//                    }
//                 else{
//                              stmtAddMenu.setBinaryStream(12, null);
//                          }
                  stmtAddMenu.executeUpdate();
               
            }
             
               
                /*
                 * if hybrid flag is true then we have to inset  data also in the hybrid_menu table
                 */
                if(HybridFlag){
                   
//                    int MenuId = returnMenuId(menuinfo[4]);
                   // System.out.println("test"+MenuId);
                      menu.conn.setAutoCommit(false);
                     stmtHybridAdd = menu.conn.prepareStatement(strHybridAdd);
                    for (Object[] hybriddata1 : hybriddata) {
                        stmtHybridAdd.setString(1, hybriddata1[0].toString());
                        stmtHybridAdd.setBigDecimal(2, new BigDecimal(hybriddata1[2].toString()).setScale(3, RoundingMode.HALF_UP));
                        stmtHybridAdd.setString(3, hybriddata1[4].toString());
                        stmtHybridAdd.setInt(4, MenuId);
//                        System.out.println(MenuId);
//                        stmtHybridAdd.setInt(4, Integer.parseInt(menuinfo[3]));
                        
                        stmtHybridAdd.executeUpdate();
                    }
                    
                    
                }
                
                menu.conn.commit();
                 JOptionPane.showMessageDialog(null, "Item Successfully Added to Menu");
                 // String MenuId = returnMenuId(menuinfo[4]);
                  //  System.out.println("test"+MenuId);
                
                
                
            }
            catch(SQLException|FileNotFoundException se){
                JOptionPane.showMessageDialog(null, se+"from addmenu");
            }
            finally{
                try{
                    if(fis != null){
                        fis.close();
                        menu.closeConnection();
                    }
                }
                catch(IOException se){
                    JOptionPane.showMessageDialog(null, se.getMessage());
                }
            }
            
            
        }
        public void EditMenu(String[] menuinfo,Object[][] HybridData,Object[][] DeleteHybridData,File image){
            /*
             *   
           menuInfo[0] = String.valueOf(checkTrackableUntrackable());
            menuInfo[1] = String.valueOf(getItemId());
            menuInfo[2] = getItemName();
            menuInfo[3] = String.valueOf(getMenuId());
            menuInfo[4] = getMenuName();
            menuInfo[5] = String.valueOf(getUnitId());
            menuInfo[6] = getItemBaseUnit();
            menuInfo[7] = getQuantity();
            menuInfo[8] = getRetailPrice();
           if(checkWholepriceChecked()){
            menuInfo[9] = ""; 
                    }
           else if(!checkWholepriceChecked()){
            menuInfo[9] = getWholesalePrice();
        }
         menuInfo[10] = String.valueOf(getCategoryId());
         menuInfo[11] = String.valueOf(getHybridFlag());
         menuInfo[12] = getImagePath();
         menuInfo[13]= String.valueOf(getStoreId());
             */
            
            String strmenutrackable = "UPDATE  menu  SET menu_name = ?,item_type = ?,department_item_id = ?,quantity = ?,unit_id = ?,retail_price = ?,wholesale_price = ?,date = ? ,category_id = ? ";
            if(image != null){
                strmenutrackable += ", image_path= ?,menu_image = ? WHERE menu_id = ? ";
            }
            else{
                strmenutrackable += ", image_path= ? WHERE menu_id = ?"; 
            }
            String strmenuuntrackable = "UPDATE  menu  SET menu_name = ?,item_type = ?,quantity = ?,unit_id = ?,retail_price = ?,wholesale_price = ?, category_id = ?,date = ? ";
             if(image != null){
                strmenuuntrackable += ", image_path= ?,menu_image = ? WHERE menu_id = ? ";
            }
            else{
                strmenuuntrackable += ", image_path= ? WHERE menu_id = ?"; 
            }
            String strmenuHybridtrackable = "UPDATE  menu  SET menu_name = ?,quantity = ?,unit_id = ?,retail_price = ?,wholesale_price = ?, category_id = ?,date = ?  ";
            if(image != null){
                strmenuHybridtrackable += ", image_path= ?,menu_image = ? WHERE menu_id = ? ";
            }
            else{
                strmenuHybridtrackable += ", image_path= ? WHERE menu_id = ?"; 
            }
            //query for deleting the Hyrid Item Data
            String strmenuDelHybridtrackable = "DELETE  FROM hybrid_menu WHERE parent_menu_id = ?";
            //query to insert into the hybrid_menu
            String strmenuInsertHybridtrackable = "INSERT INTO hybrid_menu(department_item_id,quantity,unit_id,parent_menu_id) VALUES(?,?,?,?)";
            
            BigDecimal Quantity;
            DBConnect menuEdit = new DBConnect();
            FileInputStream fis = null;
            try{
                menuEdit.initConnection();
                if(image != null){
                    fis = new FileInputStream(image);
                }
                /*if 
                 * if menuinfo[0] equals true then it is trackable item else it is non trackable
                 */
                if(menuinfo[0].equalsIgnoreCase("true")){
                    //this is for single trackable
                    if(menuinfo[11].equalsIgnoreCase("false"))
                    {
                   menuEdit.conn.setAutoCommit(false);
                stmtEditMenu = menuEdit.conn.prepareStatement(strmenutrackable);
             
                stmtEditMenu.setString(1, menuinfo[4]);
                stmtEditMenu.setInt(2, 1);
                stmtEditMenu.setString(3, menuinfo[1]);
                stmtEditMenu.setBigDecimal(4,new BigDecimal(menuinfo[7]).setScale(3, RoundingMode.HALF_UP));
                stmtEditMenu.setString(5, menuinfo[5]);
                stmtEditMenu.setBigDecimal(6, new BigDecimal(menuinfo[8]).setScale(2, RoundingMode.HALF_UP));
                if(!menuinfo[9].isEmpty()){
                   stmtEditMenu.setBigDecimal(7, new BigDecimal(menuinfo[9]).setScale(2, RoundingMode.HALF_UP));
                }
                else{
                    stmtEditMenu.setBigDecimal(7, null);
                   
                }
              stmtEditMenu.setTimestamp(8, new Timestamp(new Date().getTime()));
              stmtEditMenu.setInt(9, Integer.parseInt(menuinfo[10]));
              //if image is not null insert image
              if(image != null){
                  
              stmtEditMenu.setString(10, menuinfo[12]);
              stmtEditMenu.setBinaryStream(11, fis);
              stmtEditMenu.setString(12, menuinfo[3]);    
              }
              else{
              stmtEditMenu.setString(10, menuinfo[12]);
              stmtEditMenu.setString(11, menuinfo[3]);
                  
              }
             
               stmtEditMenu.executeUpdate();
              
                    }
                    else{
                        //for hybrid trackable item
                           menuEdit.conn.setAutoCommit(false);
                          stmtEditMenu = menuEdit.conn.prepareStatement(strmenuHybridtrackable);
                 stmtEditMenu.setString(1, menuinfo[4]);
//                stmtEditMenu.setInt(2, 0);
               // stmtAddMenu.setString(3, menuinfo[1]);
                stmtEditMenu.setBigDecimal(2,new BigDecimal(menuinfo[7]).setScale(3, RoundingMode.HALF_UP));
                stmtEditMenu.setString(3, menuinfo[5]);
                stmtEditMenu.setBigDecimal(4, new BigDecimal(menuinfo[8]).setScale(2, RoundingMode.HALF_UP));
                if(!menuinfo[9].isEmpty()){
                   stmtEditMenu.setBigDecimal(5, new BigDecimal(menuinfo[9]).setScale(2, RoundingMode.HALF_UP));
                }
                 else{
                    stmtEditMenu.setBigDecimal(5, null);
                }
                stmtEditMenu.setInt(6,Integer.parseInt(menuinfo[10]));
                stmtEditMenu.setTimestamp(7, new Timestamp(new Date().getTime()));
//                 stmtEditMenu.setString(8, menuinfo[12]);
//                 stmtEditMenu.setString(9, menuinfo[3]);
                 if(image != null){
                  
              stmtEditMenu.setString(8, menuinfo[12]);
              stmtEditMenu.setBinaryStream(9, fis);
              stmtEditMenu.setString(10, menuinfo[3]);    
              }
              else{
              stmtEditMenu.setString(8, menuinfo[12]);
              stmtEditMenu.setString(9, menuinfo[3]);
                  
              }
                 
                  stmtEditMenu.executeUpdate();
                  //for hybrid entering into data
                  
                    menuEdit.conn.setAutoCommit(false);
                    //for deleting  from hybrid_menu
                    stmtEditMenu = menuEdit.conn.prepareStatement(strmenuDelHybridtrackable);
                    stmtEditMenu.setInt(1, Integer.parseInt(menuinfo[3]));
                    stmtEditMenu.executeUpdate();
                    //for inserting into hybrid_menu
                        
                     stmtEditMenu = menuEdit.conn.prepareStatement(strmenuInsertHybridtrackable);
                    for (Object[] hybriddata1 : HybridData) {
                        stmtEditMenu.setString(1, hybriddata1[0].toString());
                        stmtEditMenu.setBigDecimal(2, new BigDecimal(hybriddata1[2].toString()).setScale(3, RoundingMode.HALF_UP));
                        stmtEditMenu.setString(3, hybriddata1[4].toString());
                        stmtEditMenu.setInt(4, Integer.parseInt(menuinfo[3]));
//                        System.out.println(MenuId);
//                        stmtHybridAdd.setInt(4, Integer.parseInt(menuinfo[3]));
                        
                        stmtEditMenu.executeUpdate();
                    }
                    }
                    
            
                }
                
                else if(menuinfo[0].equalsIgnoreCase("false")){
                       menuEdit.conn.setAutoCommit(false);
                stmtEditMenu = menuEdit.conn.prepareStatement(strmenuuntrackable);
                 stmtEditMenu.setString(1, menuinfo[4]);
                stmtEditMenu.setInt(2, 0);
               // stmtAddMenu.setString(3, menuinfo[1]);
                stmtEditMenu.setBigDecimal(3,new BigDecimal(menuinfo[7]).setScale(3, RoundingMode.HALF_UP));
                stmtEditMenu.setString(4, menuinfo[5]);
                stmtEditMenu.setBigDecimal(5, new BigDecimal(menuinfo[8]).setScale(2, RoundingMode.HALF_UP));
                if(!menuinfo[9].isEmpty()){
                   stmtEditMenu.setBigDecimal(6, new BigDecimal(menuinfo[9]).setScale(2, RoundingMode.HALF_UP));
                }
                 else{
                    stmtEditMenu.setBigDecimal(6, null);
                }
                stmtEditMenu.setInt(7,Integer.parseInt(menuinfo[10]));
                stmtEditMenu.setTimestamp(8, new Timestamp(new Date().getTime()));
//                 stmtEditMenu.setString(9, menuinfo[12]);
//                 stmtEditMenu.setString(10, menuinfo[3]);
                 //if image is not null insert image
                 if(image != null){
                  
              stmtEditMenu.setString(9, menuinfo[12]);
              stmtEditMenu.setBinaryStream(10, fis);
              stmtEditMenu.setString(11, menuinfo[3]);    
              }
              else{
              stmtEditMenu.setString(9, menuinfo[12]);
              stmtEditMenu.setString(10, menuinfo[3]);
                  
              }
                  stmtEditMenu.executeUpdate();
            }
             
               
                menuEdit.conn.commit();
                 JOptionPane.showMessageDialog(null, "Item Successfully Edited to Menu");
                
                
                
            }
            catch(SQLException|FileNotFoundException se){
                JOptionPane.showMessageDialog(null, se+"from Editmenu");
                
            }
            finally{
                try{
                    if(fis != null){
                        fis.close();
                        menuEdit.closeConnection();
                    }
                }
                catch(IOException se){
                    JOptionPane.showMessageDialog(null, se.getMessage());
                }
            }
        }
        public void DeleteMenu(int info, boolean Hybridflag){
            //if flag is true delete all data from hybrid_menu table
            String strDelete = "DELETE FROM menu WHERE  menu_id = ?";
             String strHybridDelete = "DELETE FROM hybrid_menu WHERE  parent_menu_id = ?";
            
            DBConnect del = new DBConnect();
            try{
                del.initConnection();
                del.conn.setAutoCommit(false);
                stmtDeleteMenu = del.conn.prepareStatement(strDelete);
                stmtDeleteMenu.setInt(1, info);
                stmtDeleteMenu.executeUpdate();
                if(Hybridflag){
                  stmtDeleteMenu = del.conn.prepareStatement(strHybridDelete);
                stmtDeleteMenu.setInt(1, info);
                stmtDeleteMenu.executeUpdate();  
                }
                del.conn.commit();
                JOptionPane.showMessageDialog(null, "Menu Item Succesfully deleted");
            }
            catch(Exception de){
                JOptionPane.showMessageDialog(null, de+"from deleteMenu");
            }
            finally{
                del.closeConnection();
            }
        }
      /*  private static Map<Boolean, Integer> map = new HashMap<Boolean, Integer>() {{
    put(true, 1);
    put(false, 0);
}};*/
         /*
    * this return true if menu name/id already exist and return false is not exist
    */
   public boolean checkExistingName(String menuname){
     Boolean ExistingStatus = null; 
    String strCheck = "SELECT menu_name FROM menu WHERE menu_name = ? ";
    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
        check.initConnection();
        stmtcheck = check.conn.prepareStatement(strCheck,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        stmtcheck.setString(1, menuname);
        ResultSet rs = stmtcheck.executeQuery();
        rs.last();
        int rows = rs.getRow();
        rs.beforeFirst();
        ExistingStatus = rows >= 1;
        
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from checkExistingName");
    }
    finally{
        check.closeConnection();
    }
    return ExistingStatus;
    
}
   
    public int returnCurrentItentityId(String tablename){
     //Boolean ExistingStatus = null; 
        int id = 0;
    String strCheck = "SELECT IDENT_CURRENT('menu') AS id ";
    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
        check.initConnection();
        stmtcheck = check.conn.prepareStatement(strCheck);
      //  stmtcheck.setString(1, tablename);
        ResultSet rs = stmtcheck.executeQuery();
        id = rs.getInt(1);
       
        
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from returncurrentIdentityid");
    }
    finally{
        check.closeConnection();
    }
    return id;
    
}
    public int returnMenuId(String menuname){
        int MenuId = 0;
//        DBConnect menu = new DBConnect();
        PreparedStatement stmtmenu ;
        try{
         initConnection();
          stmtmenu = conn.prepareStatement("SELECT menu_id FROM menu WHERE menu_name = ? ");
          stmtmenu.setString(1, menuname);
          ResultSet rsmenu = stmtmenu.executeQuery();
        while(rsmenu.next()){
          MenuId = rsmenu.getInt("menu_id");
        System.out.println("radhe"+MenuId);
        }
          
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(null, "from returnMenuId");
        }
        finally{
           closeConnection();
        }
        return MenuId;
    }
        public boolean ConvertIntToBoolean(int a){
            boolean b;
            return b = (a!=0);
        }
        public String checkString(String st){
            if(st == null){
             //   System.out.print("wala");
                return "";
            }
            else{ 
              //  System.out.print("wala1");
                return st;
            }
        }
        public DefaultTableModel getMenuList(int storeid){
               
                          //  String strQuery = "SELECT menu.menu_id,menu.menu_name,centerstore_stock.item_name,menu.quantity,item_unit.unit_name,menu.retail_price,menu.wholesale_price,item_category.category_name,menu.item_type FROM menu INNER JOIN centerstore_stock ON menu.item_id = centerstore_stock.item_id INNER JOIN item_unit ON  menu.unit_id = item_unit.unit_id INNER JOIN  item_category ON centerstore_stock.category_id = item_category.category_id  WHERE menu.item_type = 1 AND menu.hybrid_type!= 1  ORDER BY date desc";
//          String strQuery = "SELECT menu.menu_id,menu.menu_name,centerstore_stock.item_name,menu.quantity,item_unit.unit_name,menu.retail_price,menu.wholesale_price,item_category.category_name,menu.item_type FROM menu INNER JOIN centerstore_stock ON menu.item_id = centerstore_stock.item_id INNER JOIN item_unit ON  menu.unit_id = item_unit.unit_id INNER JOIN  item_category ON menu.category_id = item_category.category_id  WHERE menu.item_type = 1 AND menu.hybrid_type!= 1  ORDER BY date desc";
           String strQuery = "SELECT menu.menu_id,menu.menu_name,centerstore_stock.item_name,menu.quantity,item_unit.unit_name,menu.retail_price,menu.wholesale_price,item_sub_category.sub_category_name,menu.item_type,department_info.department_name FROM menu INNER JOIN department_store_stock ON menu.department_item_id= department_store_stock.department_item_id INNER JOIN centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id  INNER JOIN item_unit ON  menu.unit_id = item_unit.unit_id INNER JOIN  item_sub_category ON menu.category_id = item_sub_category.sub_category_id INNER JOIN department_info ON menu.department_id = department_info.department_id  WHERE menu.item_type = 1 AND menu.hybrid_type!= 1 AND menu.department_id = ?  ORDER BY date desc";
            String strQueryUn = "SELECT menu.menu_id,menu.menu_name,menu.quantity,item_unit.unit_name,menu.retail_price,menu.wholesale_price,item_sub_category.sub_category_name,menu.item_type,department_info.department_name FROM menu INNER JOIN item_unit ON  menu.unit_id = item_unit.unit_id INNER JOIN  item_sub_category ON menu.category_id = item_sub_category.sub_category_id INNER JOIN department_info ON menu.department_id = department_info.department_id WHERE menu.item_type = 0 AND menu.department_id = ? ORDER BY date desc  ";    
           String strQueryHybrid = "SELECT menu.menu_id,menu.menu_name,menu.quantity,item_unit.unit_name,menu.retail_price,menu.wholesale_price,item_sub_category.sub_category_name,menu.item_type,department_info.department_name FROM menu  INNER JOIN item_unit ON  menu.unit_id = item_unit.unit_id INNER JOIN  item_sub_category ON menu.category_id = item_sub_category.sub_category_id INNER JOIN department_info ON menu.department_id = department_info.department_id WHERE menu.item_type = 1 AND menu.hybrid_type = 1 AND menu.department_id = ? ORDER BY date desc  ";    
     //       String strQuery = "select centerstore_stock.item_name from  centerstore_stock,menu WHERE centerstore_stock.item_id = menu.item_id";
            //   String strQuery = "SELECT menu_id,menu_name FROM menu";
                String[] columnNames = {"Menu Id","Menu Name","Item Name","Quantity","Item Base Unit","Retail Price","WholeSale Price","Category Name","Trackable","Hybrid Type","Store Name"};
                  ArrayList<Object[]> data = new ArrayList<Object[]>();
                  Object[][] finaldata = null;
                  PreparedStatement stmtMenu;
                  PreparedStatement stmtMenuUn;
                  PreparedStatement stmtMenuHybrid;
                  ResultSet rsresult;
                  ResultSet rsresultUn;
                  ResultSet rsresultHybrid;
                  DBConnect getMenu = new DBConnect();
                  try{
                      getMenu.initConnection();
                     stmtMenu = getMenu.conn.prepareStatement(strQuery);
                      stmtMenu.setInt(1, storeid);
                      rsresult = stmtMenu.executeQuery();
                       
                      while(rsresult.next()){
                        /*  Object[] row;
                    //     Object[] row = new Object[]{rsresult.getString("menu_id"),rsresult.getString("menu_name"),rsresult.getString("item_name"),rsresult.getFloat("Quantity"),rsresult.getString("unit_name"),rsresult.getBigDecimal("retail_price"),rsresult.getBigDecimal("wholesale_price")};
                          if(rsresult.getString("item_name").isEmpty()){
                           row = new Object[]{rsresult.getString("menu_id"),rsresult.getString("menu_name"),"",rsresult.getFloat("Quantity"),rsresult.getString("unit_name"),rsresult.getBigDecimal("retail_price"),rsresult.getBigDecimal("wholesale_price"),rsresult.getString("category_name"),true};  
                          }   
                          else{
                           row = new Object[]{rsresult.getString("menu_id"),rsresult.getString("menu_name"),rsresult.getObject("item_name"),rsresult.getFloat("Quantity"),rsresult.getString("unit_name"),rsresult.getBigDecimal("retail_price"),rsresult.getBigDecimal("wholesale_price"),rsresult.getString("category_name"),true};   
                          }
                          */
                         
                         Object[] row = new Object[]{rsresult.getString("menu_id"),rsresult.getString("menu_name"),rsresult.getString("item_name"),rsresult.getFloat("Quantity"),rsresult.getString("unit_name"),rsresult.getBigDecimal("retail_price").setScale(2, RoundingMode.HALF_UP)==null?BigDecimal.ZERO:rsresult.getBigDecimal("retail_price").setScale(2, RoundingMode.HALF_UP),rsresult.getBigDecimal("wholesale_price")/* == null?BigDecimal.ZERO:rsresult.getBigDecimal("wholesale_price").setScale(2, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP)*/,rsresult.getString("sub_category_name"),true,false,rsresult.getString("department_name")};

                     //  System.out.println(ConvertIntToBoolean(rsresult.getInt("item_type")));
                    //   System.out.println(row.toString());
                        //   JOptionPane.showMessageDiaog(null, data);
                        
                          
                      data.add(row);
                      }
                      /*
                       * for retrieving the data where item_type is 1 and hybrid_type is 1
                       */
                      stmtMenuHybrid = getMenu.conn.prepareStatement(strQueryHybrid);
                      stmtMenuHybrid.setInt(1, storeid);
                      rsresultHybrid = stmtMenuHybrid.executeQuery();
                      while(rsresultHybrid.next()){
                      Object[] row = new Object[]{rsresultHybrid.getString("menu_id"),rsresultHybrid.getString("menu_name"),"",rsresultHybrid.getFloat("Quantity"),rsresultHybrid.getString("unit_name"),rsresultHybrid.getBigDecimal("retail_price").setScale(2, RoundingMode.HALF_UP)==null?BigDecimal.ZERO:rsresultHybrid.getBigDecimal("retail_price").setScale(2, RoundingMode.HALF_UP),rsresultHybrid.getBigDecimal("wholesale_price") /*== null?BigDecimal.ZERO:rsresultHybrid.getBigDecimal("wholesale_price").setScale(2, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP)*/,rsresultHybrid.getString("sub_category_name"),/*ConvertIntToBoolean(rsresult.getInt("item_type"))*/true,true,rsresultHybrid.getString("department_name")};

                     //  System.out.println(ConvertIntToBoolean(rsresultUn.getInt("item_type")));
                       // System.out.println(data);
                        //   JOptionPane.showMessageDialog(null, data);
                      data.add(row);
                  }
                      /*
                       * for retreving the data where item_type is 0 or intrackable
                       */
                      stmtMenuUn = getMenu.conn.prepareStatement(strQueryUn);
                      stmtMenuUn.setInt(1, storeid);
                      rsresultUn = stmtMenuUn.executeQuery();
                      while(rsresultUn.next()){
                      Object[] row = new Object[]{rsresultUn.getString("menu_id"),rsresultUn.getString("menu_name"),"",rsresultUn.getFloat("Quantity"),rsresultUn.getString("unit_name"),rsresultUn.getBigDecimal("retail_price"),rsresultUn.getBigDecimal("wholesale_price"),rsresultUn.getString("sub_category_name"),/*ConvertIntToBoolean(rsresult.getInt("item_type"))*/false,false,rsresultUn.getString("department_name")};

                     //  System.out.println(ConvertIntToBoolean(rsresultUn.getInt("item_type")));
                       // System.out.println(data);
                        //   JOptionPane.showMessageDialog(null, data);
                      data.add(row);
                  }
                      
                    // System.out.println(data.size());
                      finaldata = data.toArray(new Object[data.size()][]);
                   //   JOptionPane.showMessageDialog(null, finaldata[0]);
                  }
                  catch(SQLException se){
                      JOptionPane.showMessageDialog(null, se+"from getmenulist");
                  }
                  finally{
                     getMenu.closeConnection();
                  }
                  return new DefaultTableModel(finaldata,columnNames){
                    
                      @Override
                      public boolean isCellEditable(int row,int col){
                          return false;
                      }
                      @Override
                        public Class<?> getColumnClass(int columnIndex) {
                          Class clazz = String.class;
                          switch (columnIndex) {
                           /* case 8:
                              clazz = Integer.class;
                              break;*/
                            case 8:
                              clazz = Boolean.class;
                              break;
                            case 9:
                                clazz = Boolean.class;
                                break;
                          }
                         
                          return clazz;
                        }
                                           
                  };
             /*     public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
                      Component c = super.prepareRenderer(renderer,row,column);
                      if(!isRowSelected(row)){
                          c.setBackground(getBackground());
                          int ModelRow = convertRowIndexToModel(row);
                          Boolean type = (Boolean)getModel().getValue(ModelRow,);
                          if(true == type ){
                              c.setBackground(Color.ORANGE);
                          }
                              if(false == type ){
                                c.setBackground(Color.cyan);
                            } 
                          
                          return c;
                      }
                  };
                  
*/
        }
        public Object[][] convertDefaultTableModelToObject(DefaultTableModel model){
            int rows = model.getRowCount();
           int cols = model.getColumnCount();
            Object[][] data = new Object[rows][cols]; 
            for(int i = 0;i<model.getRowCount();i++){
                for(int j =0;j<model.getColumnCount();j++){
                    data[i][j] = model.getValueAt(i, j);
                }
            }
            return data;
        }
     public Object[][] getItemInfoForMenu(int storeid){
       String strQuery = "SELECT department_store_stock.department_item_id,centerstore_stock.item_name,department_store_stock.unit_id,item_unit.unit_name,item_unit.unit_relative_quantity,item_unit.unit_type,centerstore_stock.category_id,item_sub_category.sub_category_name FROM department_store_stock INNER JOIN centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id INNER JOIN item_unit ON department_store_stock.unit_id = item_unit.unit_id LEFT JOIN item_sub_category ON centerstore_stock.category_id = item_sub_category.sub_category_id WHERE      department_store_stock.department_id = ?";
      
//       DBConnect getitem = new DBConnect();
       try{
           initConnection();
          
           stmtItemInfo = conn.prepareStatement(strQuery,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
          stmtItemInfo.setInt(1, storeid);
           rsResult = stmtItemInfo.executeQuery();
           /*
            * calling funtion from function package for returning the data value
            */
           Itemdata =returnData(rsResult);
      //   JOptionPane.showMessageDialog(null, Itemdata[2]);
         //  returnItemName(returnData(rsResult));
         //  JOptionPane.showMessageDialog(null,Itemdata);
         //  returnItemName(Itemdata);
          
       }
       catch(SQLException se){
           JOptionPane.showMessageDialog(null, se+"from getItemInfoForMenu ");
       }
       finally{
           closeConnection();
       }
       return Itemdata;
       
   }
      
     
     public Object[][] getUnitInfo(int UnitId,boolean flag){
      /*
       * flag check whether it for trackable or nontrackable
       * if flag is true then it is trackable otherwise 
        */
         PreparedStatement getrelqty;
      ResultSet getResultSet;
      //float Qty = 0;
      Object[] UnitName;
      String strgetUnitRelativeQuantity = "select unit_id,unit_name,unit_relative_quantity from item_unit" ;
      if(flag){
       strgetUnitRelativeQuantity += " where unit_type = (select unit_type from item_unit where unit_id = ?)";   
      }
      ArrayList<Object[]>  data= new ArrayList<Object[]>();
      DBConnect getUnit = new DBConnect();
      try{
          getUnit.initConnection();
          getrelqty = getUnit.conn.prepareStatement(strgetUnitRelativeQuantity);
        if(flag){
          getrelqty.setInt(1, UnitId);
        }
          getResultSet = getrelqty.executeQuery();
         
          while(getResultSet.next()){
           Object st[] = new Object[]{getResultSet.getObject("unit_id"),getResultSet.getObject("unit_name"),getResultSet.getObject("unit_relative_quantity")};
        data.add(st);
          }
         
      }
      catch(Exception e){
          JOptionPane.showMessageDialog(null, e+"form getUnitINfo");
      }
      finally{
          getUnit.closeConnection();
      }
      return data.toArray(new Object[data.size()][]);
  }
     public Object[][] getSubCategoryInfo(){
         PreparedStatement stmtcat;
         ResultSet rsQuery;
          ArrayList<Object[]> data = new ArrayList<Object[]>();
         DBConnect getcat = new DBConnect();
         try{
         String strcat = "SELECT * FROM item_sub_category";
        
         getcat.initConnection();
         stmtcat = getcat.conn.prepareStatement(strcat);
         rsQuery = stmtcat.executeQuery();
         while(rsQuery.next()){
         Object ct[] =new Object[]{ rsQuery.getObject("sub_category_id"),rsQuery.getObject("sub_category_name")};
         data.add(ct);
     }
         
         }
         catch(SQLException se){
             JOptionPane.showMessageDialog(null, se+"fromgetcategoryinfo");
         }
         return data.toArray(new Object[data.size()][]);
         
         
     }
  
   /**
     * This is implement by image upload button
     */

    /**
     * This is implement by image upload button
     * @param menuid
     * @return 
     */
    public String returnImagePath(int menuid){
       PreparedStatement stmtcat;
       ResultSet rsQuery;
       String path = null;
       DBConnect getcat = new DBConnect();
         String strcat = "SELECT image_path FROM menu WHERE menu_id = ?";
         try{
        
        
         getcat.initConnection();
         stmtcat = getcat.conn.prepareStatement(strcat);
         stmtcat.setInt(1, menuid);
         rsQuery = stmtcat.executeQuery();
         while(rsQuery.next()){
              String imagepath = (rsQuery.getObject("image_path") != null)?rsQuery.getString("image_path"):"";
//         path = rsQuery.getString("image_path");
              path = imagepath;
         }
         
         }
         catch(SQLException se){
             JOptionPane.showMessageDialog(null, se+"fromgetcategoryinfo");
         }
         finally{
            getcat.closeConnection();
         }
         return path;
       
   }
    public Object returnImage(int menuid){
       PreparedStatement stmtcat;
       ResultSet rsQuery;
       Object path = null;
       DBConnect getcat = new DBConnect();
         String strcat = "SELECT menu_image FROM menu WHERE menu_id = ?";
         try{
        
        
         getcat.initConnection();
         stmtcat = getcat.conn.prepareStatement(strcat);
         stmtcat.setInt(1, menuid);
         rsQuery = stmtcat.executeQuery();
         while(rsQuery.next()){
//              String imagepath = (rsQuery.getObject("image_path") != null)?rsQuery.getString("image_path"):"";
////         path = rsQuery.getString("image_path");
//              path = imagepath;
              path = rsQuery.getObject("menu_image");
         }
         
         }
         catch(SQLException se){
             JOptionPane.showMessageDialog(null, se+"fromgetcategoryinfo");
         }
         finally{
            getcat.closeConnection();
         }
         return path;
       
   }
   public void DeleteImage(Path path){
       Path relative = Paths.get("resources/images/"+path);
        try {
            Files.delete(relative);
        } catch (IOException ex) {
//            Logger.getLogger(MenuEntryModel.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, ex+"from Delete Image");
        }
   }
    public void SaveImage(File file,BufferedImage buffImage){
        /*
               * path of the image to save
               */
//        URL relative = getClass().getClassLoader().getResource("/src/images/");
//        System.out.println(relative);
//      File path = new File(relative+ file.getName()); 
//        URL pt = getClass().getResource("/images/"+file.getName());
//        File path = new File(getClass().getResource("/images/"+ file.getName()).toExternalForm());
//        File path = null;  
//        path = new File("resources/images/"+ file.getName());
        Path path = Paths.get(("resources/images/"+file.getName()));
      
//File path = new File(url.toURI());      
if(Files.exists(path)){
        //   int result = JOptionPane.showConfirmDialog(this, "DO you want to overswrite the existing file","File already exists",JOptionPane.YES_NO_CANCEL_OPTION);
    //int result = 0;
              int   result = JOptionPane.showConfirmDialog(null,"DO you want to overswrite the existing file","File already exits", JOptionPane.YES_NO_CANCEL_OPTION);
    switch(result){
                    case JOptionPane.YES_OPTION:
                       break;
                        
                    case JOptionPane.NO_OPTION:
                        
                        return;
                    case JOptionPane.CANCEL_OPTION:
                    case JOptionPane.CLOSED_OPTION:
                       
                        return;
                      }
      } 
try{
if(file.getName().endsWith(".png")){
              // System.out.println("png type"); 
    
              ImageIO.write(buffImage,"png",path.toFile());
}
else if(file.getName().endsWith(".jpg")){
   //   System.out.println("jpg type");
    ImageIO.write(buffImage, "jpg", path.toFile());
}
else if(file.getName().endsWith(".gif")){
   //   System.out.println("gif type");
    ImageIO.write(buffImage, "gif", path.toFile());
}
}
catch(IOException ioe){
    JOptionPane.showMessageDialog(null, ioe+"image not saved");
}

        
    }
     
    public DefaultTableModel getHybridItemData(int Hybridid){
        PreparedStatement stmt;
        ResultSet rs;
        ArrayList<Object[]> hybriditemdata = new ArrayList<>();
        String[] ColumnNames = {"Item Id","Item Name","Quantity","Item Base Unit","Unit Id"};
        
        String strget = "SELECT  department_store_stock.department_item_id,centerstore_stock.item_name,hybrid_menu.quantity,item_unit.unit_name,item_unit.unit_id  from hybrid_menu INNER JOIN item_unit ON hybrid_menu.unit_id = item_unit.unit_id INNER JOIN department_store_stock ON hybrid_menu.department_item_id = department_store_stock.department_item_id  INNER JOIN centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id  where parent_menu_id  in (?)";
        try{
            initConnection();
            stmt = conn.prepareStatement(strget);
            stmt.setInt(1, Hybridid);
            rs = stmt.executeQuery();
            while(rs.next()){
                Object[] data = new Object[]{rs.getObject(1),rs.getObject(2),rs.getObject(3),rs.getObject(4),rs.getObject(5)};
                hybriditemdata.add(data);
             }
            
        }
        catch(SQLException se){
            DisplayMessages.displayError(null, se.getMessage()+"from getHybridItemData "+getClass().getName(),"Error");
        }
        return new DefaultTableModel(hybriditemdata.toArray(new Object[hybriditemdata.size()][]),ColumnNames);
    }
   
    
}
