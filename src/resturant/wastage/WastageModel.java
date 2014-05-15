/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package resturant.wastage;

import database.DBConnect;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import reusableClass.DisplayMessages;
import reusableClass.Function;

/**
 *
 * @author SUSHIL
 */
public class WastageModel  extends DBConnect{
      public void AddMenuWastage(Object[] wastageinfo,int userid,int departmentid){
                // ordermodel.AddMenuWastage(ordermodel.convertDefaultTableModelToObject(orderview.getTableOrderList()),orderview.getOrderId(),orderview.getTableId(),orderview.getWaiterId(),orderview.getCustomerId());
          /*
          wastage into
            obj[0] = getMenuID();
       obj[1] = getComboBoxMenuMenuName();
       obj[2] = getMenuQuantity();
       obj[3] = getMenuRate();
       obj[4] = getMenuAmount();
       obj[5] = getMenuReason();
       obj[6] = getMenuStaffId();
       obj[7] = getComboBoxMenuStaffName();
          */
              PreparedStatement stmtwastage;
              PreparedStatement stmtSubtractResturantStore;
              PreparedStatement stmtSubtractHybridResturantStore;
           
              BigDecimal total_amount = BigDecimal.ZERO;
              String MenuId = new String();
              String strwastage = "INSERT INTO wastage (id,quantity,amount,reason,staff_id,department_id,user_id,date,menu_type_flag) VALUES(?,?,?,?,?,?,?,?,?)";
//              String strSubtractSingleResturantStore = "UPDATE department_store_stock SET total_qty = department_store_stock.total_qty -(? * (select  menu.quantity*item_unit.unit_relative_quantity from menu INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id WHERE menu.menu_id = ?)) WHERE item_id = (select item_id from menu where menu_id = ?)";
              String strSubtractSingleResturantStore = "UPDATE department_store_stock SET total_qty = department_store_stock.total_qty -(? * (select  menu.quantity*item_unit.unit_relative_quantity from menu INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id WHERE menu.menu_id = ?)) WHERE department_item_id = (select department_item_id from menu where menu_id = ?)";
              String strSubtractHybridResturantStore = "UPDATE department_store_stock SET total_qty = department_store_stock.total_qty  - ?*? WHERE department_item_id = ?  ";
            
              DBConnect addorder = new DBConnect();
              try{
                  addorder.initConnection();
                  /*
                   * for inserting into wastage
                   */
                   addorder.conn.setAutoCommit(false);
                  stmtwastage = addorder.conn.prepareStatement(strwastage);
                  stmtwastage.setInt(1, Integer.parseInt(wastageinfo[0].toString()));
                  stmtwastage.setDouble(2, Double.parseDouble(wastageinfo[2].toString()));
                  stmtwastage.setDouble(3, Double.parseDouble(wastageinfo[4].toString()));
                  stmtwastage.setString(4, wastageinfo[5].toString());
                  stmtwastage.setInt(5, Integer.parseInt(wastageinfo[6].toString()));
                  stmtwastage.setInt(6,departmentid);
                  stmtwastage.setInt(7, userid);
                  stmtwastage.setDate(8,new java.sql.Date(Function.returnSystemDate().getTime()));
                  stmtwastage.setBoolean(9,Boolean.TRUE);
                  stmtwastage.executeUpdate();
                  
                 
               
               
                  /*
                   * for reducing resturant
                   */
                  //when tracable item
                //  if(checkTrackable(strdeladd))
                  /*
                   * separating the menu_id to thier type as singletracable and hybrid type
                   */
                
               
                  
                      MenuId = wastageinfo[0].toString();
                      if (checkTrackable(Integer.parseInt(MenuId))) {
                          if (checkHybrid(Integer.parseInt(MenuId))) {
                           //if it is hybrid  menu
                                                                /*
                                       * if only hybrid trackable
                                       */
                                      //return the item-unit in row
                                      ArrayList<String[]> HybridItem = new ArrayList<>();
                                      String[][] strHybridItem = null;

                                          String[][] data = getItemIdForHybrid(Integer.parseInt(wastageinfo[0].toString()));
                                          for (String[] data1 : data) {
                                              String[] row = new String[]{data1[0], data1[1],wastageinfo[2].toString()};
                                              HybridItem.add(row);
                                          }

                                      strHybridItem = HybridItem.toArray(new String[HybridItem.size()][]);
                                      /*
                                       * start a query for hybrid
                                       */
                                      stmtSubtractHybridResturantStore = addorder.conn.prepareStatement(strSubtractHybridResturantStore);
                                      for (String[] strHybridItem1 : strHybridItem) {
                                          //  stmtdelSubtractHybridResturantStore.setString(1, straddHybridItem[i][0]);
                                          stmtSubtractHybridResturantStore.setBigDecimal(1, new BigDecimal(strHybridItem1[2]));
                                          stmtSubtractHybridResturantStore.setBigDecimal(2, new BigDecimal(strHybridItem1[1]));
                                          stmtSubtractHybridResturantStore.setString(3, strHybridItem1[0]);
                                          stmtSubtractHybridResturantStore.executeUpdate();
                                      }
                              
                          } 
                          else {
                            //if it is single trackable menu
                                /*
                                * if only single trackable
                                */
                               stmtSubtractResturantStore = addorder.conn.prepareStatement(strSubtractSingleResturantStore);
                               addorder.conn.setAutoCommit(false);
                              
                                   //  stmtdelSubtractResturantStore.setString(1, straddSingleTrackableItem[i][0]);
                                   stmtSubtractResturantStore.setBigDecimal(1, new BigDecimal(wastageinfo[2].toString()));
                                   stmtSubtractResturantStore.setInt(2,Integer.parseInt(wastageinfo[0].toString()) );
                                   stmtSubtractResturantStore.setInt(3,Integer.parseInt(wastageinfo[0].toString()) );
                                   stmtSubtractResturantStore.executeUpdate();
                               
                            
                          }
                      }
                      //if everything goes weel commit
               addorder.conn.commit();
               JOptionPane.showMessageDialog(null, "Wastage Saved Successfully");
                  
              }
              catch(SQLException e){
                  JOptionPane.showMessageDialog(null, e+"from  Wastage Save"+getClass().getName());
              }
              
            }
      //for item wastage
     public void AddItemWastage(Object[] wastageinfo,int userid,int departmentid){
           /*
          wastage into
          obj[0] = getItemID();
       obj[1] = getComboBoxItemName();
       obj[2] = getItemQuantity();
       obj[3] = getItemAmount();
       obj[4] = getItemReason();
       obj[5] = getItemWaiterId();
       obj[6] = getComboBoxItemStaffName();
       obj[7] = getUnitID();
       obj[8] = getComboBoxItemBaseUnit();
          */
         PreparedStatement stmtwastage;
         PreparedStatement stmtSubtractResturantStore;
           String strwastage = "INSERT INTO wastage (id,quantity,amount,reason,staff_id,department_id,user_id,date,menu_type_flag,unit_id) VALUES(?,?,?,?,?,?,?,?,?,?)";
//              String strSubtractSingleResturantStore = "UPDATE department_store_stock SET total_qty = department_store_stock.total_qty -(? * (select  menu.quantity*item_unit.unit_relative_quantity from menu INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id WHERE menu.menu_id = ?)) WHERE item_id = (select item_id from menu where menu_id = ?)";
             
           String strSubtractHybridResturantStore = "UPDATE department_store_stock SET total_qty = department_store_stock.total_qty  - ?*(select unit_relative_quantity FROM item_unit WHERE unit_id = ?) WHERE department_item_id = ?  ";
           try{
               
               initConnection();
                 //for inserting into wastage table
                    conn.setAutoCommit(false);
                  stmtwastage = conn.prepareStatement(strwastage);
                  stmtwastage.setInt(1, Integer.parseInt(wastageinfo[0].toString()));
                  stmtwastage.setDouble(2, Double.parseDouble(wastageinfo[2].toString()));
                  stmtwastage.setDouble(3, Double.parseDouble(wastageinfo[3].toString()));
                  stmtwastage.setString(4, wastageinfo[4].toString());
                  stmtwastage.setInt(5, Integer.parseInt(wastageinfo[5].toString()));
                  stmtwastage.setInt(6,departmentid);
                  stmtwastage.setInt(7, userid);
                  stmtwastage.setDate(8,new java.sql.Date(Function.returnSystemDate().getTime()));
                   stmtwastage.setBoolean(9,Boolean.FALSE);
                   stmtwastage.setInt(10, Integer.parseInt(wastageinfo[7].toString()));
                  stmtwastage.executeUpdate();
                  
                  
                  
              
                   //for reducing the wastage quantity from the department_store_stock
                  stmtSubtractResturantStore = conn.prepareStatement(strSubtractHybridResturantStore);
                  stmtSubtractResturantStore.setDouble(1, Double.parseDouble(wastageinfo[2].toString()));
                  stmtSubtractResturantStore.setInt(2 , Integer.parseInt(wastageinfo[7].toString()));
                  stmtSubtractResturantStore.setInt(3, Integer.parseInt(wastageinfo[0].toString()));
                  stmtSubtractResturantStore.executeUpdate();
                  
                //if everthing goes well commit
                  conn.commit();
                  JOptionPane.showMessageDialog(null, "Item Wastage Saved Succesfully");
                  
               
           }   
           catch(SQLException se){
               JOptionPane.showMessageDialog(null, se+"from additemwastage"+getClass().getName());
           }
         
     }
       public boolean checkTrackable(int menuid){
     //Boolean ExistingStatus = null; 
       Boolean id = false;
      //  String oid = tablename;
    String strCheck = "SELECT item_type FROM menu WHERE menu_id = ?";
    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
        check.initConnection();
        stmtcheck = check.conn.prepareStatement(strCheck);
     stmtcheck.setInt(1,menuid);
        ResultSet rs = stmtcheck.executeQuery();
        while(rs.next()){
        id = rs.getBoolean("item_type");
        }
       
        
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from checkTrackable");
    }
    finally{
        check.closeConnection();
    }
    return id;
    
}
       public boolean checkHybrid(int menuid){
     //Boolean ExistingStatus = null; 
       Boolean id = false;
      //  String oid = tablename;
    String strCheck = "SELECT hybrid_type FROM menu WHERE menu_id = ?";
    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
        check.initConnection();
        stmtcheck = check.conn.prepareStatement(strCheck);
     stmtcheck.setInt(1,menuid);
        ResultSet rs = stmtcheck.executeQuery();
        while(rs.next()){
        id = rs.getBoolean("hybrid_type");
        }
       
        
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from checkHybrid");
    }
    finally{
        check.closeConnection();
    }
    return id;
    
}
       
       public String[][] getItemIdForHybrid(int menuid){
        DBConnect gettg = new DBConnect();
        PreparedStatement stmtget ;
        ResultSet rs;
       // String search = menuid+"%";
        String strget = "SELECT department_item_id,quantity*item_unit.unit_relative_quantity as total_qty  from hybrid_menu INNER JOIN item_unit ON hybrid_menu.unit_id = item_unit.unit_id where parent_menu_id  in (?)";
       // String[] columnName = new String[]{"Menu Id","Menu Name"," Retail Rate","Wholesale Rate","Base Unit",};
        ArrayList<String[]> data = new ArrayList<String[]>();
        String[][] finaldata = null;
        try{
            gettg.initConnection();
            stmtget = gettg.conn.prepareStatement(strget);
            stmtget.setInt(1, menuid);
            rs = stmtget.executeQuery();
            while(rs.next()){
                String[] row = new String[]{rs.getString("department_item_id"),rs.getString("total_qty")};
            data.add(row);
            }
            finaldata = data.toArray(new String[data.size()][]);
            
        }
        
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"from getmenuinfolike");
        }
        finally{
            gettg.closeConnection();
        }
        return finaldata;
    }
        //retreiving the itemname in the respective department
       public Object[][] getItemInfoForMenu(int storeid){
       String strQuery = "SELECT department_store_stock.department_item_id,centerstore_stock.item_name,department_store_stock.unit_id,item_unit.unit_name,item_unit.unit_relative_quantity,item_unit.unit_type,centerstore_stock.category_id,item_category.category_name FROM department_store_stock,centerstore_stock,item_unit,item_category WHERE department_store_stock.unit_id = item_unit.unit_id AND department_store_stock.item_id = centerstore_stock.item_id AND centerstore_stock.category_id = item_category.category_id and department_store_stock.department_id = ?";
      PreparedStatement stmtItemInfo;
      ResultSet rsResult;
      Object[][] Itemdata = null;
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
           JOptionPane.showMessageDialog(null, se+"from getItemInfoForMenu "+getClass().getName());
       }
       finally{
           closeConnection();
       }
       return Itemdata;
       
   }
        //retreiving menuname in respective department
       public Object[][] getMenuInfo(int departmentid){
          PreparedStatement stmtget;
          Object[][] MenuInfo = null;
          ResultSet rsget;
          ArrayList<Object[]> data = new ArrayList<Object[]>();
          String strgetMenu = "SELECT menu.menu_id,menu.menu_name,item_unit.unit_name,menu.retail_price FROM menu LEFT JOIN item_unit ON  menu.unit_id = item_unit.unit_id WHERE department_id = ?";
          DBConnect getMenu = new DBConnect();
          try{
              getMenu.initConnection();
              stmtget = getMenu.conn.prepareStatement(strgetMenu);
              stmtget.setInt(1, departmentid);
              rsget = stmtget.executeQuery();
              while(rsget.next()){
                  Object[] row = new Object[]{rsget.getString("menu_id"),rsget.getString("menu_name"),rsget.getString("unit_name"),rsget.getDouble("retail_price")};
                  data.add(row);
              }
              MenuInfo = data.toArray(new Object[data.size()][]);
          }
          catch(SQLException se){
              JOptionPane.showMessageDialog(null, se+"from getMenuInfo");
          }
          finally{
              getMenu.closeConnection();
          }
          return MenuInfo;
              
      }
        //retreining staff info
       public   Object[][] getWaiterInfoObject(){
        PreparedStatement stmtget;
        ResultSet rsget ;
        DBConnect dbget  = new DBConnect();
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        Object[][] WaiterInfo = null;
        
        String strget = "SELECT waiter_id,waiter_name FROM waiter_info ";
        try{
            dbget.initConnection();
            stmtget = dbget.conn.prepareStatement(strget);
            rsget = stmtget.executeQuery();
            while(rsget.next()){
                Object[] row = new Object[]{rsget.getString("waiter_id"),rsget.getString("waiter_name")};
                data.add(row);
            }
            WaiterInfo = data.toArray(new Object[data.size()][]);
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from getWaiterInfoObject");
        }
        finally{
            dbget.closeConnection();
        }
        return WaiterInfo;
    }
       public Object[][] getUnitInfo(int UnitId){
      PreparedStatement getrelqty;
      ResultSet getResultSet;
      //float Qty = 0;
      Object[] UnitName;
      String strgetUnitRelativeQuantity = "select unit_id,unit_name,unit_relative_quantity from item_unit where unit_type = (select unit_type from item_unit where unit_id = ?)";
       ArrayList<Object[]>  data= new ArrayList<Object[]>();
//      DBConnect getUnit = new DBConnect();
      try{
         initConnection();
          getrelqty = conn.prepareStatement(strgetUnitRelativeQuantity);
         getrelqty.setInt(1, UnitId);
          getResultSet = getrelqty.executeQuery();
         
          while(getResultSet.next()){
           Object st[] = new Object[]{getResultSet.getObject("unit_id"),getResultSet.getObject("unit_name"),getResultSet.getObject("unit_relative_quantity")};
        data.add(st);
          }
         
      }
      catch(Exception e){
          JOptionPane.showMessageDialog(null, e+"form getUnitINfo"+getClass().getName());
      }
      finally{
         closeConnection();
      }
      return data.toArray(new Object[data.size()][]);
  }
        public Object[][] getAllUnitInfo(){
      PreparedStatement getrelqty;
      ResultSet getResultSet;
      //float Qty = 0;
      Object[] UnitName;
      String strgetUnitRelativeQuantity = "select unit_id,unit_name,unit_relative_quantity from item_unit ";
       ArrayList<Object[]>  data= new ArrayList<Object[]>();
//      DBConnect getUnit = new DBConnect();
      try{
         initConnection();
          getrelqty = conn.prepareStatement(strgetUnitRelativeQuantity);
//         getrelqty.setInt(1, UnitId);
          getResultSet = getrelqty.executeQuery();
         
          while(getResultSet.next()){
           Object st[] = new Object[]{getResultSet.getObject("unit_id"),getResultSet.getObject("unit_name"),getResultSet.getObject("unit_relative_quantity")};
        data.add(st);
          }
         
      }
      catch(SQLException e){
          JOptionPane.showMessageDialog(null, e+"form getUnitINfo"+getClass().getName());
      }
      finally{
         closeConnection();
      }
      return data.toArray(new Object[data.size()][]);
  }
       public BigDecimal getHybridItemAvailable(int menuid){
        PreparedStatement stmtget;
        ResultSet rsget ;
        DBConnect dbget  = new DBConnect();
        BigDecimal Avaiable = BigDecimal.ZERO;
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        ArrayList<BigDecimal> ItemStockData = new ArrayList<>();
        BigDecimal MenuQuantity = BigDecimal.ZERO;
//        String ColName[] = new String[]{"ItemName","Stock Available"};
        
        String strget = "SELECT centerstore_stock.item_name,(department_store_stock.total_qty/(item_unit.unit_relative_quantity)) as total_qty,item_unit.unit_name,hybrid_menu.quantity as hybridquantity,menu.quantity as menuquantity  from hybrid_menu INNER JOIN department_store_stock ON hybrid_menu.department_item_id= department_store_stock.department_item_id INNER JOIN item_unit ON hybrid_menu.unit_id = item_unit.unit_id INNER JOIN centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id  INNER JOIN menu ON hybrid_menu.parent_menu_id = menu.menu_id where parent_menu_id  in (?)";
        try{
            dbget.initConnection();
            stmtget = dbget.conn.prepareStatement(strget);
            stmtget.setInt(1, menuid);
            rsget = stmtget.executeQuery();
            while(rsget.next()){
                BigDecimal TotalItem = rsget.getBigDecimal("total_qty").divide(rsget.getBigDecimal("hybridquantity"),3,RoundingMode.HALF_UP);
                ItemStockData.add(TotalItem);
//               System.out.println(TotalItem);
                MenuQuantity = rsget.getBigDecimal("menuquantity");
                Object[] row = new Object[]{rsget.getString("item_name"),rsget.getBigDecimal("total_qty")+rsget.getString("unit_name")};
                data.add(row);
            }
//            MenuInfo = data.toArray(new Object[data.size()][]);
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from getHybridItemAvailable "+getClass().getName());
        }
        finally{
            dbget.closeConnection();
        }
        Collections.sort(ItemStockData);
//        System.out.println(ItemStockData.get(0));
        MenuQuantity = ItemStockData.get(0).divide(MenuQuantity,3,RoundingMode.HALF_UP);
        
       return MenuQuantity ;
        
         
     }
       public BigDecimal getSingleItemAvailable(int menuid){
           PreparedStatement stmt;
           ResultSet rs;
           BigDecimal bg = null ;
           String str = "SELECT ((a.total_qty/u.unit_relative_quantity)/m.quantity)  FROM menu  m   INNER JOIN  department_store_stock a on  a.department_item_id = m.department_item_id INNER JOIN item_unit  u on m.unit_id = u.unit_id WHERE m.menu_id = ? ";
           try{
               initConnection();
               stmt = conn.prepareStatement(str);
               stmt.setInt(1, menuid);
               rs = stmt.executeQuery();
               while(rs.next()){
                   bg = rs.getBigDecimal(1).setScale(2, RoundingMode.HALF_UP);
               }
           }
           catch(SQLException se){
               DisplayMessages.displayError(null, se.getMessage()+"from "+getClass().getName(), "SQL Error");
           }
           finally{
               closeConnection();
           }
           return bg;
           
       } 
        public BigDecimal getItemStockAvailable(int itemid,int unitid){
           PreparedStatement stmt;
           ResultSet rs;
           BigDecimal bg = null ;
           String str = "SELECT (a.total_qty/u.unit_relative_quantity)  FROM  department_store_stock a,item_unit u   WHERE a.item_id = ? AND u.unit_id = ? ";
           try{
               initConnection();
               stmt = conn.prepareStatement(str);
               stmt.setInt(1, itemid);
//               System.out.println(unitid);
               stmt.setInt(2, unitid);
               rs = stmt.executeQuery();
               while(rs.next()){
                   bg = rs.getBigDecimal(1).setScale(2, RoundingMode.HALF_UP);
               }
           }
           catch(SQLException se){
               DisplayMessages.displayError(null, se.getMessage()+"from getItemStockAvailable "+getClass().getName(), "SQL Error");
           }
           finally{
               closeConnection();
           }
           return bg;
           
       } 
    
}
