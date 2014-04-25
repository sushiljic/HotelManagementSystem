/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.order;

import database.DBConnect;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import reusableClass.Function;

/**
 *
 * @author SUSHIL
 */
public class OrderModel  extends DBConnect{
            public Object TableInfo[][];
            public  Object[][] MenuInfo;
            public Object CustomerInfo[][];
            public Object WaiterInfo[][];
            
            public void AddOrder(Object[][] item,int orderid,int tableid,int waiterid,int customerid,int userid,int departmentid){
                // ordermodel.AddOrder(ordermodel.convertDefaultTableModelToObject(orderview.getTableOrderList()),orderview.getOrderId(),orderview.getTableId(),orderview.getWaiterId(),orderview.getCustomerId());
              PreparedStatement stmtadd;
              PreparedStatement stmtorderadd;
              PreparedStatement stmtSubtractResturantStore;
              PreparedStatement stmtSubtractHybridResturantStore;
              PreparedStatement stmtTableInfo;
              PreparedStatement stmtOrderTable;
              BigDecimal total_amount = BigDecimal.ZERO;
              String MenuId = new String();
              String stradd = "INSERT INTO order_item_list (order_id,menu_id,quantity) VALUES(?,?,?)";
//              String strSubtractSingleResturantStore = "UPDATE department_store_stock SET total_qty = department_store_stock.total_qty -(? * (select  menu.quantity*item_unit.unit_relative_quantity from menu INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id WHERE menu.menu_id = ?)) WHERE item_id = (select item_id from menu where menu_id = ?)";
               String strSubtractSingleResturantStore = "UPDATE department_store_stock SET total_qty = department_store_stock.total_qty -(? * (select  menu.quantity*item_unit.unit_relative_quantity from menu INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id WHERE menu.menu_id = ?)) WHERE department_item_id = (select department_item_id from menu where menu_id = ?)";
              String strSubtractHybridResturantStore = "UPDATE department_store_stock SET total_qty = department_store_stock.total_qty  - ?*? WHERE department_item_id = ?  ";
             String strorderadd = "INSERT INTO order_list(order_id,table_id,waiter_id,customer_id,total_amount,date,user_id,department_id) VALUES(?,?,?,?,?,?,?,?)";
             String strtablepack = " UPDATE table_info SET table_status = 1 WHERE table_id = ? ";
             String strordertable = "INSERT INTO temp_order_table(order_id,table_id) VALUES(?,?)";
              DBConnect addorder = new DBConnect();
              try{
                  addorder.initConnection();
                  /*
                   * for inserting into order_item_list
                   */
                   addorder.conn.setAutoCommit(false);
                  stmtadd = addorder.conn.prepareStatement(stradd);
                 
                  for (Object[] item1 : item) {
                      stmtadd.setInt(1,orderid);
                      stmtadd.setString(2, item1[0].toString());
                      stmtadd.setBigDecimal(3, new BigDecimal(item1[2].toString()));
                      total_amount = total_amount.add(new BigDecimal(item1[5].toString()));
                      stmtadd.executeUpdate();
                  }
                //  System.out.println(deltotal_amount);
                  /*
                   * for reducing resturant
                   */
                  //when tracable item
                //  if(checkTrackable(strdeladd))
                  /*
                   * separating the menu_id to thier type as singletracable and hybrid type
                   */
                  ArrayList<String[]> SingleTrackableItem = new ArrayList<>();
                  ArrayList<String[]> HybridTrackableItem = new ArrayList<>();
                  String[][] strSingleTrackableItem = null;
                  String[][] strHybridTrackableItem = null;
                  for (Object[] item1 : item) {
                      MenuId = item1[0].toString();
                      if (checkTrackable(Integer.parseInt(MenuId))) {
                          if (checkHybrid(Integer.parseInt(MenuId))) {
                              String[] row = new String[]{MenuId, item1[2].toString()};
                              HybridTrackableItem.add(row);
                          } else {
                              String[] row = new String[]{MenuId, item1[2].toString()};
                              SingleTrackableItem.add(row);
                          }
                      }
                  }
                  strSingleTrackableItem = SingleTrackableItem.toArray(new String[SingleTrackableItem.size()][]);
                  strHybridTrackableItem = HybridTrackableItem.toArray(new String[HybridTrackableItem.size()][]);
                  /*
                   * if only single trackable
                   */
                  stmtSubtractResturantStore = addorder.conn.prepareStatement(strSubtractSingleResturantStore);
                  addorder.conn.setAutoCommit(false);
                  for (String[] strSingleTrackableItem1 : strSingleTrackableItem) {
                      //  stmtdelSubtractResturantStore.setString(1, straddSingleTrackableItem[i][0]);
                      stmtSubtractResturantStore.setBigDecimal(1, new BigDecimal(strSingleTrackableItem1[1]));
                      stmtSubtractResturantStore.setString(2, strSingleTrackableItem1[0]);
                      stmtSubtractResturantStore.setString(3, strSingleTrackableItem1[0]);
                      stmtSubtractResturantStore.executeUpdate();
                  }
                  /*
                   * if only hybrid trackable
                   */
                  //return the item-unit in row
                  ArrayList<String[]> HybridItem = new ArrayList<>();
                  String[][] strHybridItem = null;
                  for (String[] strHybridTrackableItem1 : strHybridTrackableItem) {
                      String[][] data = getItemIdForHybrid(Integer.parseInt(strHybridTrackableItem1[0]));
                      for (String[] data1 : data) {
                          String[] row = new String[]{data1[0], data1[1], strHybridTrackableItem1[1]};
                          HybridItem.add(row);
                      }
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
                  
                  /*
                   * for order_list table
                   */
                  stmtorderadd = addorder.conn.prepareStatement(strorderadd);
                  addorder.conn.setAutoCommit(false);
                  stmtorderadd.setInt(1,orderid);
                    stmtorderadd.setInt(2,tableid);
//                      stmtorderadd.setString(3, waiterid);
//                      stmtorderadd.setString(4, customerid);
//                    System.out.println("wala1");
                     stmtorderadd.setInt(3, waiterid);
//                    System.out.println("wala2");
                     stmtorderadd.setInt(4, customerid);
                      stmtorderadd.setBigDecimal(5, total_amount);
//                      stmtorderadd.setTimestamp(6, new Timestamp(new Date().getTime()));
                     //saving the date as per the open date for system
                      stmtorderadd.setTimestamp(6, new Timestamp(Function.returnSystemDate().getTime()));
                      stmtorderadd.setInt(7, userid);
                      stmtorderadd.setInt(8, departmentid);
                      stmtorderadd.executeUpdate();
                  /*
                  * for table_info making table status pack    
                  */
                 addorder.conn.setAutoCommit(false);
                 stmtTableInfo = addorder.conn.prepareStatement(strtablepack);
                 stmtTableInfo.setInt(1, tableid);
                
                 stmtTableInfo.executeUpdate();
                 /*
                 inseting the order id and table info  so to track the department
                 */
                 addorder.conn.setAutoCommit(false);
                 stmtOrderTable = addorder.conn.prepareStatement(strordertable);
                 stmtOrderTable.setInt(1, orderid);
                 stmtOrderTable.setInt(2, tableid);
                 stmtOrderTable.executeUpdate();
                  //if everything goes weel commit
               addorder.conn.commit();
               JOptionPane.showMessageDialog(null, "Item Ordered Successfully");
                  
              }
              catch(SQLException e){
                  JOptionPane.showMessageDialog(null, e+"from  Add order model"+getClass().getName());
              }
              
            }
           
           
            public void EditOrder(Object[][] deletedata,Object[][] item,int orderid,int tableid,int waiterid,int customerid,int userid,int departmentid){
                PreparedStatement stmtdeladd;
              PreparedStatement stmtdelorderadd;
              PreparedStatement stmtdelSubtractResturantStore;
              PreparedStatement stmtdelSubtractHybridResturantStore;
              BigDecimal deltotal_amount = BigDecimal.ZERO;
               String MenuId = new String();
              
              /*
              here it is for adding the data
              */
                  PreparedStatement stmtadd;
              PreparedStatement stmtorderadd;
              PreparedStatement stmtSubtractResturantStore;
              PreparedStatement stmtSubtractHybridResturantStore;
              PreparedStatement stmtTableInfo;
              PreparedStatement stmtOrderTable;
              BigDecimal total_amount = BigDecimal.ZERO;
             
              String delMenuId = new String();
              /*
              this is all done for fist delete the data and then againg adding the data in the table
              */
              String strdeladd = "DELETE FROM order_item_list WHERE order_id IN (?) ";
              String strdelSubtractSingleResturantStore = "UPDATE department_store_stock SET total_qty = department_store_stock.total_qty +((select order_item_list.quantity from order_item_list where menu_id = ? and order_id = ?) * (select  menu.quantity*item_unit.unit_relative_quantity from menu INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id WHERE menu.menu_id = ?)) WHERE item_id = (select item_id from menu where menu_id = ?)";
              String strdelSubtractHybridResturantStore = "UPDATE department_store_stock SET total_qty = department_store_stock.total_qty + (select order_item_list.quantity from order_item_list where menu_id = ? and order_id = ?) *? WHERE item_id = ?  ";
//             String strdelorderadd = "UPDATE order_list  SET total_amount =  0 WHERE order_id = ?";
             String strtableunpack = "UPDATE table_info SET table_status = 0 WHERE table_id = ?";
            /*
             there is query for edit data
             */
                 String stradd = "INSERT INTO order_item_list (order_id,menu_id,quantity) VALUES(?,?,?)";
              String strSubtractSingleResturantStore = "UPDATE department_store_stock SET total_qty = department_store_stock.total_qty -(? * (select  menu.quantity*item_unit.unit_relative_quantity from menu INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id WHERE menu.menu_id = ?)) WHERE item_id = (select item_id from menu where menu_id = ?)";
              String strSubtractHybridResturantStore = "UPDATE department_store_stock SET total_qty = department_store_stock.total_qty  - ?*? WHERE item_id = ?  ";
             String strorderadd = "UPDATE  order_list SET table_id = ? ,waiter_id = ? ,customer_id = ? ,total_amount = ? ,date = ?,user_id= ? ,department_id = ? WHERE order_id = ?";
             String strtablepack = " UPDATE table_info SET table_status = 1 WHERE table_id = ? ";
              String strordertable = "UPDATE  temp_order_table SET table_id = ? WHERE order_id = ?";
             
              int temptableid = returnTableIdByOrderId(orderid);
             DBConnect addorder = new DBConnect();
              try{
                  
                  addorder.initConnection();
                  
                  /*
                   * for inserting into order_item_list
                   */
                //  System.out.println(orderid);
                
                //  System.out.println(deltotal_amount);
                  /*
                   * for reducing resturant
                   */
                  //when tracable item
                //  if(checkTrackable(strdeladd))
                  /*
                   * separating the menu_id to thier type as singletracable and hybrid type
                   */
                  ArrayList<String[]> SingleTrackableItem = new ArrayList<>();
                  ArrayList<String[]> HybridTrackableItem = new ArrayList<>();
                  String[][] strSingleTrackableItem = null;
                  String[][] strHybridTrackableItem = null;
                  for (Object[] item1 : deletedata) {
                      delMenuId = item1[0].toString();
                      if (checkTrackable(Integer.parseInt(delMenuId))) {
                          if (checkHybrid(Integer.parseInt(delMenuId))) {
                              String[] row = new String[]{delMenuId, item1[2].toString()};
                              HybridTrackableItem.add(row);
                          } else {
                              String[] row = new String[]{delMenuId, item1[2].toString()};
                              SingleTrackableItem.add(row);
                          }
                      }
                  }
                  strSingleTrackableItem = SingleTrackableItem.toArray(new String[SingleTrackableItem.size()][]);
                  strHybridTrackableItem = HybridTrackableItem.toArray(new String[HybridTrackableItem.size()][]);
                  /*
                   * if only single trackable
                   */
                  stmtdelSubtractResturantStore = addorder.conn.prepareStatement(strdelSubtractSingleResturantStore);
                  addorder.conn.setAutoCommit(false);
                  for (String[] strSingleTrackableItem1 : strSingleTrackableItem) {
                      // stmtdelSubtractResturantStore.setString(1, straddSingleTrackableItem[i][0]);
                      stmtdelSubtractResturantStore.setString(1, strSingleTrackableItem1[0]);
                      //  System.out.println(straddSingleTrackableItem[i][0]+orderid);
                      stmtdelSubtractResturantStore.setInt(2,orderid );
                      stmtdelSubtractResturantStore.setString(3, strSingleTrackableItem1[0]);
                      stmtdelSubtractResturantStore.setString(4, strSingleTrackableItem1[0]);
                      stmtdelSubtractResturantStore.executeUpdate();
                  }
                  /*
                   * if only hybrid trackable
                   */
                  //return the item-unit in row
                  ArrayList<String[]> HybridItem = new ArrayList<>();
                  String[][] strHybridItem = null;
                  for (String[] strHybridTrackableItem1 : strHybridTrackableItem) {
                      String[][] data = getItemIdForHybrid(Integer.parseInt(strHybridTrackableItem1[0]));
                      for (String[] data1 : data) {
                          String[] row = new String[]{data1[0], data1[1], strHybridTrackableItem1[1], strHybridTrackableItem1[0]};
                          //row0 = item_id row1 = total quantity defined as menu row2  = order quantity row3 = menu id
                          HybridItem.add(row);
                      }
                  }
                  strHybridItem = HybridItem.toArray(new String[HybridItem.size()][]);
                  /*
                   * start a query for hybrid
                   */
                  stmtdelSubtractHybridResturantStore = addorder.conn.prepareStatement(strdelSubtractHybridResturantStore);
                  for (String[] strHybridItem1 : strHybridItem) {
                      stmtdelSubtractHybridResturantStore.setString(1, strHybridItem1[3]); //menu_id
                      stmtdelSubtractHybridResturantStore.setInt(2, orderid);//order_id
                      stmtdelSubtractHybridResturantStore.setBigDecimal(3, new BigDecimal(strHybridItem1[1])); //total quantity
                      stmtdelSubtractHybridResturantStore.setString(4, strHybridItem1[0]); //Item_id
                      stmtdelSubtractHybridResturantStore.executeUpdate();
                  }
                  /*
                   * for order_item list table
                   */
                    stmtdeladd = addorder.conn.prepareStatement(strdeladd);
                  addorder.conn.setAutoCommit(false);
              /*    for(int i=0;i<item.length;i++){
                      stmtdeladd.setInt(1,Integer.parseInt(orderid));
                      stmtdeladd.setString(2, item[i][0].toString());
                      
                      stmtdeladd.setBigDecimal(3, new BigDecimal(item[i][2].toString()));
                      deltotal_amount = deltotal_amount.add(new BigDecimal(item[i][5].toString())); 
                    
                      stmtdeladd.executeUpdate();
                  }
                  */
                  stmtdeladd.setInt(1,orderid);
                  stmtdeladd.executeUpdate();
                  
                  /*
                   * for order_list table
                   */
//                  stmtdelorderadd = addorder.conn.prepareStatement(strdelorderadd);
//                  addorder.conn.setAutoCommit(false);
//                   stmtdelorderadd.setInt(1,orderid);
//                   //   stmtdelorderadd.setString(2, waiterid);
//                    //  stmtdelorderadd.setString(3, customerid);
//                    //  stmtdelorderadd.setBigDecimal(4, deltotal_amount);
//                    //  stmtdelorderadd.setTimestamp(5, new Timestamp(new Date().getTime()));
//                      stmtdelorderadd.executeUpdate();
                      /*
                      for unpacking the table 
                      */
                      
                     /*
                  * for table_info making table status uppack    
                  */
//                      System.out.println("wala");
                
                  addorder.conn.setAutoCommit(false);
                 stmtTableInfo = addorder.conn.prepareStatement(strtableunpack);
                 stmtTableInfo.setInt(1, temptableid);
                 
                 stmtTableInfo.executeUpdate(); 
                      /*
                      upto here we perform update for deleting the prevois order
                      */
                      /*
                      there is query for adding the value in new tabl
                      */
                      
                        /*
                   * for inserting into order_item_list
                   */
                  addorder.conn.setAutoCommit(false);
                  stmtadd = addorder.conn.prepareStatement(stradd);
                 
                    for (Object[] item1 : item) {
                        stmtadd.setInt(1,orderid);
                        stmtadd.setString(2, item1[0].toString());
                        stmtadd.setBigDecimal(3, new BigDecimal(item1[2].toString()));
                        total_amount = total_amount.add(new BigDecimal(item1[5].toString()));
                        stmtadd.executeUpdate();
                    }
//                  System.out.println(total_amount);
                  /*
                   * for reducing resturant
                   */
                  //when tracable item
                //  if(checkTrackable(strdeladd))
                  /*
                   * separating the menu_id to thier type as singletracable and hybrid type
                   */
                  ArrayList<String[]> addSingleTrackableItem = new ArrayList<String[]>();
                  ArrayList<String[]> addHybridTrackableItem = new ArrayList<String[]>();
                  String[][] straddSingleTrackableItem = null;
                  String[][] straddHybridTrackableItem = null;
                  for (Object[] item1 : item) {
                      MenuId = item1[0].toString();
                      if (checkTrackable(Integer.parseInt(MenuId))) {
                          if (checkHybrid(Integer.parseInt(MenuId))) {
                              String[] row = new String[]{MenuId, item1[2].toString()};
                              addHybridTrackableItem.add(row);
                          } else {
                              String[] row = new String[]{MenuId, item1[2].toString()};
                              addSingleTrackableItem.add(row);
                          }
                      }
                  }
                  straddSingleTrackableItem = addSingleTrackableItem.toArray(new String[addSingleTrackableItem.size()][]);
                  straddHybridTrackableItem = addHybridTrackableItem.toArray(new String[addHybridTrackableItem.size()][]);
                  /*
                   * if only single trackable
                   */
                  stmtSubtractResturantStore = addorder.conn.prepareStatement(strSubtractSingleResturantStore);
                  addorder.conn.setAutoCommit(false);
                  for (String[] strSingleTrackableItem1 : straddSingleTrackableItem) {
                      //  stmtdelSubtractResturantStore.setString(1, straddSingleTrackableItem[i][0]);
                      stmtSubtractResturantStore.setBigDecimal(1, new BigDecimal(strSingleTrackableItem1[1]));
                      stmtSubtractResturantStore.setString(2, strSingleTrackableItem1[0]);
                      stmtSubtractResturantStore.setString(3, strSingleTrackableItem1[0]);
                      stmtSubtractResturantStore.executeUpdate();
                  }
                  /*
                   * if only hybrid trackable
                   */
                  //return the item-unit in row
                  ArrayList<String[]> addHybridItem = new ArrayList<>();
                  String[][] straddHybridItem = null;
                  for (String[] strHybridTrackableItem1 : straddHybridTrackableItem) {
                      String[][] data = getItemIdForHybrid(Integer.parseInt(strHybridTrackableItem1[0]));
                      for (String[] data1 : data) {
                          String[] row = new String[]{data1[0], data1[1], strHybridTrackableItem1[1]};
                          addHybridItem.add(row);
                      }
                  }
                  straddHybridItem = addHybridItem.toArray(new String[addHybridItem.size()][]);
                  /*
                   * start a query for hybrid
                   */
                  stmtSubtractHybridResturantStore = addorder.conn.prepareStatement(strSubtractHybridResturantStore);
                  for (String[] strHybridItem1 : straddHybridItem) {
                      //  stmtdelSubtractHybridResturantStore.setString(1, straddHybridItem[i][0]);
                      stmtSubtractHybridResturantStore.setBigDecimal(1, new BigDecimal(strHybridItem1[2]));
                      stmtSubtractHybridResturantStore.setBigDecimal(2, new BigDecimal(strHybridItem1[1]));
                      stmtSubtractHybridResturantStore.setString(3, strHybridItem1[0]);
                      stmtSubtractHybridResturantStore.executeUpdate();
                  }
                  
                  /*
                   * for order_list table
                   */
                  stmtorderadd = addorder.conn.prepareStatement(strorderadd);
                  addorder.conn.setAutoCommit(false);
                    stmtorderadd.setInt(1,tableid);
                      stmtorderadd.setInt(2, waiterid);
                      stmtorderadd.setInt(3, customerid);
                      stmtorderadd.setBigDecimal(4, total_amount);
//                      stmtorderadd.setTimestamp(5, new Timestamp(new Date().getTime()));
                      //saving the system date into datbase
                      stmtorderadd.setTimestamp(5,  new Timestamp(Function.returnSystemDate().getTime()));
                      stmtorderadd.setInt(6, userid);
                      stmtorderadd.setInt(7, departmentid);
                      stmtorderadd.setInt(8, orderid);
                      stmtorderadd.executeUpdate();
                  /*
                  * for table_info making table status pack    
                  */
                 stmtTableInfo = addorder.conn.prepareStatement(strtablepack);
                 stmtTableInfo.setInt(1, tableid);
                 addorder.conn.setAutoCommit(false);
                 stmtTableInfo.executeUpdate();
                 /*
                 for changing the table id for the change table id
                 
                 */
                 addorder.conn.setAutoCommit(false);
                 stmtOrderTable = addorder.conn.prepareStatement(strordertable);
                 stmtOrderTable.setInt(1, tableid);
                 stmtOrderTable.setInt(2, orderid);
                 stmtOrderTable.executeUpdate();
                 /*
                 if everything goes well then commmit it
                 */
               addorder.conn.commit();
               JOptionPane.showMessageDialog(null, "Item Order Edited  Successfully");
                  
              }
              catch(SQLException e){
                  JOptionPane.showMessageDialog(null, e+"from edit order");
              }  
            }
            public void DeleteOrder(Object[][] item,int orderid,int tableid,int waiterid,int customerid){
                // ordermodel.AddOrder(ordermodel.convertDefaultTableModelToObject(orderview.getTableOrderList()),orderview.getOrderId(),orderview.getTableId(),orderview.getWaiterId(),orderview.getCustomerId());
              PreparedStatement stmtadd;
              PreparedStatement stmtorderadd;
              PreparedStatement stmtSubtractResturantStore;
              PreparedStatement stmtSubtractHybridResturantStore;
              PreparedStatement stmtOrdertable;
              BigDecimal total_amount = BigDecimal.ZERO;
              String MenuId = new String();
              String stradd = "DELETE  FROM order_item_list WHERE  order_id IN (?) ";
              String strSubtractSingleResturantStore = "UPDATE department_store_stock SET total_qty = department_store_stock.total_qty +((select order_item_list.quantity from order_item_list where menu_id = ? and order_id = ?) * (select  menu.quantity*item_unit.unit_relative_quantity from menu INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id WHERE menu.menu_id = ?)) WHERE item_id = (select item_id from menu where menu_id = ?)";
              String strSubtractHybridResturantStore = "UPDATE department_store_stock SET total_qty = department_store_stock.total_qty + (select order_item_list.quantity from order_item_list where menu_id = ? and order_id = ?) *? WHERE item_id = ?  ";
             String strorderadd = "DELETE  FROM order_list WHERE order_id = ?";
              String strtablepack = " UPDATE table_info SET table_status = 0 WHERE table_id = ? ";
               String strordertable = "DELETE FROM temp_order_table WHERE order_id = ?";
              DBConnect addorder = new DBConnect();
              try{
                  addorder.initConnection();
                  /*
                   * for inserting into order_item_list
                   */
                //  System.out.println(orderid);
                
                //  System.out.println(deltotal_amount);
                  /*
                   * for reducing resturant
                   */
                  //when tracable item
                //  if(checkTrackable(strdeladd))
                  /*
                   * separating the menu_id to thier type as singletracable and hybrid type
                   */
                  ArrayList<String[]> SingleTrackableItem = new ArrayList<>();
                  ArrayList<String[]> HybridTrackableItem = new ArrayList<>();
                  String[][] strSingleTrackableItem = null;
                  String[][] strHybridTrackableItem = null;
                  for (Object[] item1 : item) {
                      MenuId = item1[0].toString();
                      if (checkTrackable(Integer.parseInt(MenuId))) {
                          if (checkHybrid(Integer.parseInt(MenuId))) {
                              String[] row = new String[]{MenuId, item1[2].toString()};
                              HybridTrackableItem.add(row);
                          } else {
                              String[] row = new String[]{MenuId, item1[2].toString()};
                              SingleTrackableItem.add(row);
                          }
                      }
                  }
                  strSingleTrackableItem = SingleTrackableItem.toArray(new String[SingleTrackableItem.size()][]);
                  strHybridTrackableItem = HybridTrackableItem.toArray(new String[HybridTrackableItem.size()][]);
                  /*
                   * if only single trackable
                   */
                  stmtSubtractResturantStore = addorder.conn.prepareStatement(strSubtractSingleResturantStore);
                  addorder.conn.setAutoCommit(false);
                  for (String[] strSingleTrackableItem1 : strSingleTrackableItem) {
                      // stmtdelSubtractResturantStore.setString(1, straddSingleTrackableItem[i][0]);
                      stmtSubtractResturantStore.setString(1, strSingleTrackableItem1[0]);
                      //  System.out.println(straddSingleTrackableItem[i][0]+orderid);
                      stmtSubtractResturantStore.setInt(2,orderid );
                      stmtSubtractResturantStore.setString(3, strSingleTrackableItem1[0]);
                      stmtSubtractResturantStore.setString(4, strSingleTrackableItem1[0]);
                      stmtSubtractResturantStore.executeUpdate();
                  }
                  /*
                   * if only hybrid trackable
                   */
                  //return the item-unit in row
                  ArrayList<String[]> HybridItem = new ArrayList<>();
                  String[][] strHybridItem = null;
                  for (String[] strHybridTrackableItem1 : strHybridTrackableItem) {
                      String[][] data = getItemIdForHybrid(Integer.parseInt(strHybridTrackableItem1[0]));
                      for (String[] data1 : data) {
                          String[] row = new String[]{data1[0], data1[1], strHybridTrackableItem1[1], strHybridTrackableItem1[0]};
                          //row0 = item_id row1 = total quantity defined as menu row2  = order quantity row3 = menu id
                          HybridItem.add(row);
                      }
                  }
                  strHybridItem = HybridItem.toArray(new String[HybridItem.size()][]);
                  /*
                   * start a query for hybrid
                   */
                  stmtSubtractHybridResturantStore = addorder.conn.prepareStatement(strSubtractHybridResturantStore);
                  for (String[] strHybridItem1 : strHybridItem) {
                      stmtSubtractHybridResturantStore.setString(1, strHybridItem1[3]); //menu_id
                      stmtSubtractHybridResturantStore.setInt(2,orderid);//order_id
                      stmtSubtractHybridResturantStore.setBigDecimal(3, new BigDecimal(strHybridItem1[1])); //total quantity
                      stmtSubtractHybridResturantStore.setString(4, strHybridItem1[0]); //Item_id
                      stmtSubtractHybridResturantStore.executeUpdate();
                  }
                  /*
                   * for order_item list table
                   */
                    stmtadd = addorder.conn.prepareStatement(stradd);
                  addorder.conn.setAutoCommit(false);
              /*    for(int i=0;i<item.length;i++){
                      stmtdeladd.setInt(1,Integer.parseInt(orderid));
                      stmtdeladd.setString(2, item[i][0].toString());
                      
                      stmtdeladd.setBigDecimal(3, new BigDecimal(item[i][2].toString()));
                      deltotal_amount = deltotal_amount.add(new BigDecimal(item[i][5].toString())); 
                    
                      stmtdeladd.executeUpdate();
                  }
                  */
                  stmtadd.setInt(1,orderid);
                  stmtadd.executeUpdate();
                  
                  /*
                   * for order_list table
                   */
                  stmtorderadd = addorder.conn.prepareStatement(strorderadd);
                  addorder.conn.setAutoCommit(false);
                   stmtorderadd.setInt(1,orderid);
                   //   stmtdelorderadd.setString(2, waiterid);
                    //  stmtdelorderadd.setString(3, customerid);
                    //  stmtdelorderadd.setBigDecimal(4, deltotal_amount);
                    //  stmtdelorderadd.setTimestamp(5, new Timestamp(new Date().getTime()));
                      stmtorderadd.executeUpdate();
                    /*
                  * for table_info making table status pack    
                  */
                  addorder.conn.setAutoCommit(false);     
                 stmtadd = addorder.conn.prepareStatement(strtablepack);
                 stmtadd.setInt(1, tableid);
                  addorder.conn.setAutoCommit(false);
                 stmtadd.executeUpdate();    
                 /*
                 for deleting the temptable order
                 */
                 addorder.conn.setAutoCommit(false);
                 stmtOrdertable = addorder.conn.prepareStatement(strordertable);
                 stmtOrdertable.setInt(1,orderid);
                
                 stmtOrdertable.executeUpdate();
                 
                  //if everything goes weel commit
               addorder.conn.commit();
               JOptionPane.showMessageDialog(null, "Item Order Deleted  Successfully");
                  
              }
              catch(SQLException e){
                  JOptionPane.showMessageDialog(null, e+"from delete order "+getClass().getName());
              }
              
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
     public Object[][] getTableInfoObject(){
        PreparedStatement stmtget;
        ResultSet rsget ;
        DBConnect dbget  = new DBConnect();
        ArrayList<Object[]> data = new ArrayList<>();
        
        String strget = "SELECT table_id,table_name FROM table_info WHERE table_info.table_availability = 1";
        try{
            dbget.initConnection();
            stmtget = dbget.conn.prepareStatement(strget);
            rsget = stmtget.executeQuery();
            while(rsget.next()){
                Object[] row = new Object[]{rsget.getInt("table_id"),rsget.getString("table_name")};
                data.add(row);
            }
            TableInfo = data.toArray(new Object[data.size()][]);
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from gettableinfo");
        }
        finally{
            dbget.closeConnection();
        }
        return TableInfo;
    }
      public Object[][] getCustomerInfoObject(){
        PreparedStatement stmtget;
        ResultSet rsget ;
        DBConnect dbget  = new DBConnect();
        ArrayList<Object[]> data = new ArrayList<>();
        
        String strget = "SELECT customer_id,customer_name FROM customer_info ";
        try{
            dbget.initConnection();
            stmtget = dbget.conn.prepareStatement(strget);
            rsget = stmtget.executeQuery();
            while(rsget.next()){
                Object[] row = new Object[]{rsget.getString("customer_id"),rsget.getString("customer_name")};
                data.add(row);
            }
            CustomerInfo = data.toArray(new Object[data.size()][]);
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from getCustomerInfoObject");
        }
        finally{
            dbget.closeConnection();
        }
        return CustomerInfo;
    }
       public Object[][] getWaiterInfoObject(){
        PreparedStatement stmtget;
        ResultSet rsget ;
        DBConnect dbget  = new DBConnect();
        ArrayList<Object[]> data = new ArrayList<>();
        
        String strget = "SELECT waiter_id,waiter_name FROM waiter_info INNER JOIN designation_info ON designation_info.designation_id = waiter_info.designation_id WHERE designation_info.waiter_flag = 1 ";
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
     
      public String[] returnTableName(Object[][] obj){
        String[] Name = new String[obj.length];
        for(int i=0;i<obj.length;i++){
            Name[i] = obj[i][1].toString();
        }
        return Name;
    }
       public String[] returnMenuName(Object[][] obj){
        String[] Name = new String[obj.length];
        for(int i=0;i<obj.length;i++){
            Name[i] = obj[i][1].toString();
        }
        return Name;
    }
         public String[] returnCustomerName(Object[][] obj){
        String[] Name = new String[obj.length];
        for(int i=0;i<obj.length;i++){
            Name[i] = obj[i][1].toString();
        }
        return Name;
    }
              public String[] returnWaiterName(Object[][] obj){
        String[] Name = new String[obj.length];
        for(int i=0;i<obj.length;i++){
            Name[i] = obj[i][1].toString();
        }
        return Name;
    }
      public Object[][] getMenuInfo(int departmentid){
          PreparedStatement stmtget;
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
                  Object[] row = new Object[]{rsget.getString("menu_id"),rsget.getString("menu_name"),rsget.getString("unit_name"),rsget.getString("retail_price")};
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
       public BigDecimal checkSingleTrackableItem(int menu_id,Double quantity){
          PreparedStatement stmtget;
          ResultSet rsget;
          ArrayList<Object[]> data = new ArrayList<Object[]>();
          String strgetMenu = "SELECT department_store_stock.total_qty - (? * ( menu.quantity * item_unit.unit_relative_quantity )) as net_quantity from menu INNER JOIN department_store_stock on menu.department_item_id = department_store_stock.department_item_id INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id WHERE menu.menu_id = ? ";
          DBConnect getMenu = new DBConnect();
          BigDecimal net = BigDecimal.ZERO;
          try{
              getMenu.initConnection();
              stmtget = getMenu.conn.prepareStatement(strgetMenu);
              stmtget.setBigDecimal(1, new BigDecimal(quantity));
              stmtget.setInt(2, menu_id);
              rsget = stmtget.executeQuery();
              while(rsget.next()){
                //  Object[] row = new Object[]{rsget.getString("menu_id"),rsget.getString("menu_name"),rsget.getString("unit_name"),rsget.getString("retail_price")};
                  //data.add(row);
                  net = rsget.getBigDecimal("net_quantity");
              }
             // MenuInfo = data.toArray(new Object[data.size()][]);
          }
          catch(SQLException se){
              JOptionPane.showMessageDialog(null, se+"from checkSingleTrackableItem");
          }
          finally{
              getMenu.closeConnection();
          }
          return net;
              
      }
        public String[][] checkHybridTrackableItem(int menu_id,Double quantity){
          PreparedStatement stmtget;
          ResultSet rsget;
          ArrayList<Object[]> data = new ArrayList<Object[]>();
          //UPDATE resturant_store SET total_qty = (select total_qty from resturant_store where item_id = ?) - ?*? WHERE item_id = ?
          String strgetMenu = "SELECT department_store_stock.total_qty - (? * ?) as net_quantity,centerstore_stock.item_name FROM department_store_stock INNER JOIN centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id  WHERE department_store_stock.department_item_id = ? ";
         String[][] itemdata = getItemIdForHybrid(menu_id);
         String[][] netdata= null;
          DBConnect getMenu = new DBConnect();
          BigDecimal net = BigDecimal.ZERO;
          try{
              getMenu.initConnection();
              for (String[] itemdata1 : itemdata) {
                  stmtget = getMenu.conn.prepareStatement(strgetMenu);
                  stmtget.setBigDecimal(1, new BigDecimal(quantity));
                  stmtget.setBigDecimal(2, new BigDecimal(itemdata1[1]));
                  stmtget.setString(3, itemdata1[0]);
//              System.out.println(itemdata[i][0]+"\n"+itemdata[i][1]);
                  rsget = stmtget.executeQuery();
                  while(rsget.next()){
                      //  Object[] row = new Object[]{rsget.getString("menu_id"),rsget.getString("menu_name"),rsget.getString("unit_name"),rsget.getString("retail_price")};
                      //data.add(row);
                      
                      String[] row  = new String[]{rsget.getString("item_name"),rsget.getString("net_quantity")};
//                     System.out.println(row[1]);
                      data.add(row);
                      
                      //  net = rsget.getBigDecimal("net_quantity");
                  }
              }
               netdata= data.toArray(new String[data.size()][]);
               
             // MenuInfo = data.toArray(new Object[data.size()][]);
          }
          catch(SQLException se){
              JOptionPane.showMessageDialog(null, se+"from checkHybridTrackableItem");
           
          }
          finally{
              getMenu.closeConnection();
          }
          return netdata;
              
      }
       public BigDecimal checkSingleTrackableItemThreshold(int menu_id){
          PreparedStatement stmtget;
          ResultSet rsget;
          ArrayList<Object[]> data = new ArrayList<Object[]>();
//          String strgetMenu = "SELECT  menu_name,centerstore_stock.item_name,resturant_store.total_qty,(resturant_store.item_threshold * item_unit.unit_relative_quantity ) as threshold_quantity,item_unit.unit_type from menu INNER JOIN resturant_store on menu.department_item_id = resturant_store.storeitem_id INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id  INNER JOIN centerstore_stock on menu.item_id = centerstore_stock.item_id WHERE menu.menu_id = ? ";
           String strgetMenu = "SELECT  menu_name,centerstore_stock.item_name,department_store_stock.total_qty,department_store_stock.item_threshold  as threshold_quantity,item_unit.unit_type from menu INNER JOIN department_store_stock on menu.department_item_id = department_store_stock.department_item_id INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id  INNER JOIN centerstore_stock on department_store_stock.item_id = centerstore_stock.item_id WHERE menu.menu_id = ? ";
//          DBConnect getMenu = new DBConnect();
          BigDecimal thresholdQuantity = BigDecimal.ZERO;
          String menuname = null;
           String itemname = null;
           BigDecimal stock = BigDecimal.ZERO ;
           String BaseUnit = new String();
          
          try{
              initConnection();
              stmtget = conn.prepareStatement(strgetMenu);
              //stmtget.setBigDecimal(1, new BigDecimal(quantity));
              stmtget.setInt(1, menu_id);
              rsget = stmtget.executeQuery();
             
              while(rsget.next()){
                //  Object[] row = new Object[]{rsget.getString("menu_id"),rsget.getString("menu_name"),rsget.getString("unit_name"),rsget.getString("retail_price")};
                  //data.add(row);
                  thresholdQuantity = rsget.getBigDecimal("threshold_quantity")== null?BigDecimal.ZERO:rsget.getBigDecimal("threshold_quantity");
                
//                  System.out.println(thresholdQuantity);
                  menuname = rsget.getString("menu_name");
                 itemname = rsget.getString("item_name");
                 stock = rsget.getBigDecimal("total_qty");
                 BaseUnit = rsget.getString("unit_type");
//                   System.out.println(stock);
                  
              }
              int i = stock.compareTo(thresholdQuantity);
//               int i = stock.compareTo(new BigDecimal(th));
              if(i<0){
                  JOptionPane.showMessageDialog(null, menuname+"/  "+itemname +"  has reached below threshold value.\n Item Name:| \tCurrent Stock Quantity:\n "+itemname+"\t | "+stock+BaseUnit);
              }
              else if( i ==0){
              JOptionPane.showMessageDialog(null, menuname+"/  "+itemname+"  has reached threshold value.Please Issue the item");
          }
           /*   if(net.signum() < 0){*/
                 // JOptionPane.showMessageDialog(null, menuname+"/"+itemname +"has reached below threshold value.Current Stock Quantity:\t"+stock);
              //}
             // MenuInfo = data.toArray(new Object[data.size()][]);
          }
          catch(SQLException se){
              JOptionPane.showMessageDialog(null, se+"from checkSingleTrackableItemThreshold");
          }
          finally{
              closeConnection();
          }
          return thresholdQuantity;
              
      }
       public String[][] checkHybridTrackableItemthreshold(int menu_id){
          PreparedStatement stmtget;
          ResultSet rsget;
          ArrayList<Object[]> data = new ArrayList<Object[]>();
          //UPDATE resturant_store SET total_qty = (select total_qty from resturant_store where item_id = ?) - ?*? WHERE item_id = ?
//          String strgetMenu = "SELECT resturant_store.total_qty,centerstore_stock.item_name,(resturant_store.item_threshold * item_unit.unit_relative_quantity ) as threshold_quantity,item_unit.unit_type FROM resturant_store INNER JOIN centerstore_stock ON resturant_store.item_id = centerstore_stock.item_id INNER JOIN item_unit ON  resturant_store.unit_id = item_unit.unit_id  WHERE resturant_store.item_id = ? ";
           String strgetMenu = "SELECT department_store_stock.total_qty,centerstore_stock.item_name,(department_store_stock.item_threshold ) as threshold_quantity,item_unit.unit_type FROM department_store_stock INNER JOIN centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id INNER JOIN item_unit ON  department_store_stock.unit_id = item_unit.unit_id  WHERE department_store_stock.department_item_id = ? ";
         String[][] itemdata = getItemIdForHybrid(menu_id);
         String MenuName = returnMenuName(menu_id);
         String[][] netdata= null;
          DBConnect getMenu = new DBConnect();
          BigDecimal net = BigDecimal.ZERO;
           String status = new String();
              boolean flag = false;
          try{
              getMenu.initConnection();
              for (String[] itemdata1 : itemdata) {
                  stmtget = getMenu.conn.prepareStatement(strgetMenu);
                  stmtget.setString(1, itemdata1[0]);
                  //  stmtget.setBigDecimal(2, new BigDecimal(itemdata[i][1]));
                  //  stmtget.setString(3, itemdata[i][0]);
                  rsget = stmtget.executeQuery();
                  while(rsget.next()){
                      //  Object[] row = new Object[]{rsget.getString("menu_id"),rsget.getString("menu_name"),rsget.getString("unit_name"),rsget.getString("retail_price")};
                      //data.add(row);
                      
                      String[] row  = new String[]{rsget.getString("item_name"),rsget.getString("threshold_quantity")==null?"0":rsget.getString("threshold_quantity"),rsget.getString("total_qty")==null?"0":rsget.getString("total_qty"),rsget.getString("unit_type")};
                      data.add(row);
                      int j = new BigDecimal(row[2]).compareTo(new BigDecimal(row[1]));
                      if(j< 0 ){
                          status  += "\n Item Name:| \tCurrent Stock Quantity:\n "+row[0]+"| \t"+ row[2]+row[3]+"\n" ;
                          flag = true;
                      }
                      
                      //  net = rsget.getBigDecimal("net_quantity");
                  }
              }
               if(flag == true){
                 JOptionPane.showMessageDialog(null,MenuName +" has reached below threshold values \n"+ status);
               }
               netdata= data.toArray(new String[data.size()][]);
               
             // MenuInfo = data.toArray(new Object[data.size()][]);
          }
          catch(SQLException se){
              JOptionPane.showMessageDialog(null, se+"from checkHybridTrackableItem");
           
          }
          finally{
              getMenu.closeConnection();
          }
          return netdata;
              
      }
//     public int returnCurrentItentityId(String tablename){
//     //Boolean ExistingStatus = null; 
//        int id = 0;
//        String oid = tablename;
//    String strCheck = "SELECT IDENT_CURRENT(?)+1 AS id";
//    DBConnect check = new DBConnect();
//    PreparedStatement stmtcheck ;
//    try{
//        check.initConnection();
//        stmtcheck = check.conn.prepareStatement(strCheck);
//     stmtcheck.setString(1, oid);
//        ResultSet rs = stmtcheck.executeQuery();
//        while(rs.next()){
//        id = rs.getInt("id");
//        }
//       
//        
//        
//    }
//    catch(SQLException se){
//        JOptionPane.showMessageDialog(null, se+"from returncurrentIdentityid");
//    }
//    finally{
//        check.closeConnection();
//    }
//    return id;
//    
//}
      public int returnCurrentItentityId(String tablename){
     //Boolean ExistingStatus = null; 
        int id = 0;
        int em =0;
//        JOptionPane.showMessageDialog(null, "wala");
        String TableName = tablename;
        String strempty = "SELECT  autoinc_orderid as id FROM generate_orderid WHERE order_status = 0 LIMIT 1   ";
    String strCheck = "INSERT INTO  generate_orderid (order_status)   VALUES(1) ";
    String strGetId = " SELECT last_insert_id()";
    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
         
        check.initConnection();
       check.conn.setAutoCommit(false);
        stmtcheck = check.conn.prepareStatement(strempty);
        ResultSet rse = stmtcheck.executeQuery();
       while(rse.next()){
           em = rse.getInt(1);
//           System.out.println(em);
//           System.out.println(em);
//           break;
       }
       if(em != 0 ){
           id = em;
//           System.out.println("wala");
//            check.conn.setAutoCommit(false);
           stmtcheck = check.conn.prepareStatement("UPDATE generate_orderid SET order_status = 1 WHERE autoinc_orderid = ?");
           stmtcheck.setInt(1, id);
          stmtcheck.executeUpdate();
//          System.out.println(id);
       }
       else{
//          check.conn.setAutoCommit(false);  
        stmtcheck = check.conn.prepareStatement(strCheck);
        stmtcheck.executeUpdate();
//         check.conn.setAutoCommit(false);
        stmtcheck = check.conn.prepareStatement(strGetId);
//     stmtcheck.setString(1, oid);
        ResultSet rs = stmtcheck.executeQuery();
        while(rs.next()){
        id = rs.getInt(1);
//        System.out.println(id);
        }
       }
//       check.conn.setAutoCommit(true);
       check.conn.commit();
        
       
        
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from returncurrentIdentityid");
    }
    finally{
        check.closeConnection();
    }
    return id;
    
}
       public void MakeCurrentItentityIdFalse(int orderid){
     //Boolean ExistingStatus = null; 
        
        int oid = orderid;
        String strempty = "UPDATE  generate_orderid SET  order_status = 0 WHERE autoinc_orderid = ?   ";
   
    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
        check.initConnection();
      
        stmtcheck = check.conn.prepareStatement(strempty);
        stmtcheck.setInt(1, oid);
       stmtcheck.executeUpdate();
      
     
     
        
       
        
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from MakecurrentIdentityidFalse");
    }
    finally{
        check.closeConnection();
    }
   
    
}
      public String returnMenuName(int menuid){
     //Boolean ExistingStatus = null; 
       String id = null;
      //  String oid = tablename;
    String strCheck = "SELECT menu_name FROM menu WHERE menu_id = ?";
    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
        check.initConnection();
        stmtcheck = check.conn.prepareStatement(strCheck);
     stmtcheck.setInt(1,menuid);
        ResultSet rs = stmtcheck.executeQuery();
        while(rs.next()){
        id = rs.getString("menu_name");
        }
       
        
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from returnMenuName");
    }
    finally{
        check.closeConnection();
    }
    return id;
    
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
       public Object[][] getItemListByOrderId(String orderid){
        DBConnect gettg = new DBConnect();
        PreparedStatement stmtget ;
        ResultSet rs;
       // String search = menuid+"%";
        String strget = "SELECT order_item_list.menu_id,menu.menu_name,order_item_list.quantity,item_unit.unit_name,menu.retail_price,order_item_list.quantity*menu.retail_price as total_amount FROM order_item_list INNER JOIN menu ON order_item_list.menu_id = menu.menu_id INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id  where order_id  IN (?)";
       // String[] columnName = new String[]{"Menu Id","Menu Name"," Retail Rate","Wholesale Rate","Base Unit",};
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        Object[][] finaldata = null;
        try{
            gettg.initConnection();
            stmtget = gettg.conn.prepareStatement(strget);
            stmtget.setString(1, orderid);
            rs = stmtget.executeQuery();
            while(rs.next()){
                Object[] row = new Object[]{rs.getString("menu_id"),rs.getString("menu_name"),new BigDecimal(rs.getString("quantity")).setScale(3, RoundingMode.HALF_UP).toString(),rs.getString("unit_name"),new BigDecimal(rs.getString("retail_price")).setScale(2, RoundingMode.HALF_UP).toString(),new BigDecimal(rs.getString("total_amount")).setScale(2, RoundingMode.HALF_UP).toString()};
            data.add(row);
            }
            finaldata = data.toArray(new Object[data.size()][]);
            
        }
        
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e+"from getItemListByOrderId");
        }
        finally{
            gettg.closeConnection();
        }
        return finaldata;
    }
        public String[] getOtherInfoByOrderId(String orderid){
        DBConnect gettg = new DBConnect();
        PreparedStatement stmtget ;
        ResultSet rs;
       // String search = menuid+"%";
        String strget = "SELECT table_info.table_name,customer_info.customer_name,waiter_info.waiter_name FROM order_list LEFT JOIN table_info ON order_list.table_id = table_info.table_id LEFT JOIN customer_info ON order_list.customer_id = customer_info.customer_id LEFT JOIN waiter_info ON order_list.waiter_id = waiter_info.waiter_id WHERE order_id = ?";
       // String[] columnName = new String[]{"Menu Id","Menu Name"," Retail Rate","Wholesale Rate","Base Unit",};
      //  ArrayList<String[]> data = new ArrayList<String[]>();
        String[] finaldata = null;
        try{
            gettg.initConnection();
            stmtget = gettg.conn.prepareStatement(strget);
            stmtget.setString(1, orderid);
            rs = stmtget.executeQuery();
            while(rs.next()){
                finaldata = new String[]{rs.getString("table_name")!=null?rs.getString("table_name"):"",rs.getString("customer_name")!= null?rs.getString("customer_name"):"",rs.getString("waiter_name")!=null?rs.getString("waiter_name"):""};
           
            }
           
            
        }
        
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e+"from getOtherInfoByOrderId");
        }
        finally{
            gettg.closeConnection();
        }
        return finaldata;
    }
     public DefaultTableModel getMenuInfoLike(String src,int departmentid){
        DBConnect gettg = new DBConnect();
        PreparedStatement stmtget ;
        ResultSet rs;
        String search = src+"%";
        String strget = "SELECT menu.menu_id,menu.menu_name,menu.retail_price,menu.wholesale_price,item_unit.unit_name FROM menu INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id WHERE menu_name LIKE ? AND department_id = ?";
        String[] columnName = new String[]{"Menu Id","Menu Name"," Retail Rate","Wholesale Rate","Base Unit",};
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        Object[][] finaldata = null;
        try{
            gettg.initConnection();
            stmtget = gettg.conn.prepareStatement(strget);
            stmtget.setString(1, search);
            stmtget.setInt(2, departmentid);
            rs = stmtget.executeQuery();
            while(rs.next()){
                Object[] row = new Object[]{rs.getString("menu_id"),rs.getString("menu_name"),rs.getBigDecimal("retail_price"),rs.getBigDecimal("wholesale_price"),rs.getString("unit_name")};
            data.add(row);
            }
            finaldata = data.toArray(new Object[data.size()][]);
            
        }
        
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"from getmenuinfolike");
        }
        finally{
            gettg.closeConnection();
        }
        return new DefaultTableModel(finaldata,columnName){
                @Override     
            public boolean isCellEditable(int row, int col){
               return false;
           }
                
           
        };
    }
     public DefaultTableModel getMenuInfoAll(int departmentid){
        DBConnect gettg = new DBConnect();
        PreparedStatement stmtget ;
        ResultSet rs;
//        String search = src+"%";
        String strget = "SELECT menu.menu_id,menu.menu_name,menu.retail_price,menu.wholesale_price,item_unit.unit_name,department_info.department_name FROM menu INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id INNER JOIN department_info ON  menu.department_id = department_info.department_id WHERE menu.department_id = ?";
        String[] columnName = new String[]{"Menu Id","Menu Name"," Retail Rate","Wholesale Rate","Base Unit","Department Name"};
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        Object[][] finaldata = null;
        try{
            gettg.initConnection();
            stmtget = gettg.conn.prepareStatement(strget);
            stmtget.setInt(1, departmentid);
            rs = stmtget.executeQuery();
            while(rs.next()){
                Object[] row = new Object[]{rs.getString("menu_id"),rs.getString("menu_name"),rs.getBigDecimal("retail_price"),rs.getBigDecimal("wholesale_price"),rs.getString("unit_name"),rs.getString("department_name")};
            data.add(row);
            }
            finaldata = data.toArray(new Object[data.size()][]);
            
        }
        
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e+"from getmenuinfolike");
        }
        finally{
            gettg.closeConnection();
        }
        return new DefaultTableModel(finaldata,columnName){
                @Override     
            public boolean isCellEditable(int row, int col){
               return false;
           }
                
           
        };
    }
     public DefaultTableModel getOrderInfo(int dep){
        DBConnect gettg = new DBConnect();
        PreparedStatement stmtget ;
        ResultSet rs;
        Double[] TaxList = new Double[2];
        
       // String search = src+"%";
        String strget = "SELECT order_list.order_id,table_info.table_name,order_list.customer_id,order_list.total_amount FROM order_list LEFT JOIN  table_info ON order_list.table_id = table_info.table_id  WHERE order_list.paid = 0 and department_id = ? ORDER BY order_list.date desc ";
        String[] columnName = new String[]{"Order Id","Table Name","Total Amount "};
        ArrayList<Object[]> data = new ArrayList<Object[]>();
      
        Object[][] finaldata = null;
        /*
        retreiving the value for the tax to include into item total
        */
        TaxList = getChargeInfo();
        Double VAT = new Double(0.0);
        Double SVC = new Double(0.0);
        try{
//            System.out.println(dep);
            gettg.initConnection();
            stmtget = gettg.conn.prepareStatement(strget);
           // stmtget.setString(1,new String(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
            
          //  System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Timestamp(new Date().getTime())));
           stmtget.setInt(1, dep);
            rs = stmtget.executeQuery();
            while(rs.next()){
               /* if(rs.getString("table_id").isEmpty()){
                    data = rs.getString("Customer Name")
                }*/
               Double TotalAmount = rs.getDouble("total_amount");
//                System.out.println(TotalAmount);
//                VAT = TaxList[0]* TotalAmount/100;
//                SVC = TaxList[1]* TotalAmount/100;
//                TotalAmount = TotalAmount + VAT+SVC; 
                
                  SVC = TaxList[0]* TotalAmount/100;
                Double tt = TotalAmount +SVC;
                 VAT = (TaxList[1]* tt)/100;
                TotalAmount = tt + VAT; 
                
                Object[] row = new Object[]{rs.getInt("order_id"),rs.getString("table_name"),new BigDecimal(TotalAmount).setScale(2, RoundingMode.HALF_UP)};
            data.add(row);
            }
            finaldata = data.toArray(new Object[data.size()][]);
            
        }
        
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e+"from getOrderInfo"+getClass().getName());
        }
        finally{
            gettg.closeConnection();
        }
        return new DefaultTableModel(finaldata,columnName){
                @Override     
            public boolean isCellEditable(int row, int col){
               return false;
           }
                
           
        };
    }
       public int returnTableIdByOrderId(int  orderid){
     //Boolean ExistingStatus = null; 
        int id = 0;
        int oid = orderid;
    String strCheck = "SELECT table_id FROM order_list WHERE order_id = ?";
    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
        check.initConnection();
        stmtcheck = check.conn.prepareStatement(strCheck);
     stmtcheck.setInt(1, oid);
        ResultSet rs = stmtcheck.executeQuery();
        while(rs.next()){
        id = rs.getInt("table_id");
        }
       
        
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from returntableidBYorderid");
    }
    finally{
        check.closeConnection();
    }
    return id;
    
}
        public Double[] getChargeInfo(){
             String strQuery = new String();
             PreparedStatement stmtget ;
             ResultSet rsget;
        strQuery = "SELECT company_vat,company_svc FROM company_info";
        DBConnect getC = new DBConnect();
       Double cinfo[] = new Double[2];
        try{
            getC.initConnection();
            stmtget = getC.conn.prepareStatement(strQuery);
           rsget = stmtget.executeQuery();
//          getC.conn.commit();
          while(rsget.next()){
             
              cinfo[0] = rsget.getDouble("company_svc");
              cinfo[1] = rsget.getDouble("company_vat");
            
             
          }
          
        }
       
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"Sql error");
        }
        return cinfo;
    }
//        public Object[] returnSystemDateInfo(){
//    PreparedStatement stmtset;
//    ResultSet rs;
//    Date date = null;
//    boolean open = false;
//    boolean close = false;
//    int id = 0;
//    Object[] info = new Object[4];
//    String strget = "select system_date_id,date,open_status,close_status from system_date where  system_date_id = (select max(system_date_id) from system_date)";
////    String stropen = "UPDATE system_date SET open_status = 1 where system_date_id = ?";
//    try{
//        initConnection();
////        conn.setAutoCommit(false);
//        stmtset = conn.prepareStatement(strget);
//        rs = stmtset.executeQuery();
//        while(rs.next()){
//        
//           info[0] = rs.getObject("system_date_id");
//           info[1]=rs.getObject("date");
//           info[2] = rs.getObject("open_status");
//           info[3]  =rs.getObject("close_status");
////           System.out.println(id);
//           
//        }
//    
//      
//    }
//    catch(SQLException se){
//        JOptionPane.showMessageDialog(null, se+"from openSystemData"+getClass().getName());
//       
//    }
//    finally{
//        closeConnection();
//    }
//    return info;
//    
//}
       /*checking if is retreive by fucntion class in reusable class 
        public Date returnSystemDate(){
             PreparedStatement stmtset;
    ResultSet rs;
    Date date = null;
    boolean open = false;
    boolean close = false;
    int id = 0;
   
    String strget = "select system_date_id,date from system_date where  system_date_id = (select max(system_date_id) from system_date)";
//    String stropen = "UPDATE system_date SET open_status = 1 where system_date_id = ?";
    try{
        initConnection();
//        conn.setAutoCommit(false);
        stmtset = conn.prepareStatement(strget);
        rs = stmtset.executeQuery();
        while(rs.next()){
        
         date = rs.getDate("date");
//           System.out.println(id);
           
        }
    
      
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from returnSystemDate"+getClass().getName());
       
    }
    finally{
        closeConnection();
    }
    return date;
        }
        */ 
        public Object[][] getRespectiveDepartment(int userid){
    PreparedStatement stmt = null;
      ResultSet rs;
      ArrayList<Object[]> data = new ArrayList<>();
      try{
          initConnection();
          stmt = conn.prepareStatement("SELECT department_info.department_id,department_info.department_name from department_user INNER JOIN department_info ON department_user.department_id = department_info.department_id WHERE user_id = ?");
         stmt.setInt(1, userid);
          rs = stmt.executeQuery();
          while(rs.next()){
         Object[] row = new Object[]{rs.getObject(1),rs.getObject(2)};
         data.add(row);
           
          }
          
      }
      catch(SQLException se ){
          JOptionPane.showMessageDialog(null, se+"from getRespectiveDepartment "+getClass().getName());
      }
      finally{
          closeConnection();
      }
      return data.toArray(new Object[data.size()][]);
  }
          public boolean checkExistingTableid(int tableid){
     Boolean ExistingStatus = null; 
    String strCheck = "SELECT table_status FROM table_info WHERE table_id = ? ";
    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
        check.initConnection();
        stmtcheck = check.conn.prepareStatement(strCheck);
        stmtcheck.setInt(1, tableid);
        ResultSet rs = stmtcheck.executeQuery();
        
    while(rs.next()){
        ExistingStatus = rs.getBoolean("table_status");
    }
     
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from checkExistingTableid"+getClass().getName());
    }
    finally{
        check.closeConnection();
    }
    
    return ExistingStatus;
    
}
          public Object[] getRespectiveDepartmentForOrderid(int orderid){
        PreparedStatement stmt;
        ResultSet rs;
        String st = "SELECT order_list.department_id,department_info.department_name  FROM alepos.order_list  INNER JOIN department_info ON order_list.department_id = department_info.department_id WHERE order_list.order_id = ? ";
        int did = 0;
        Object[] data =  new Object[2];
        try{
         initConnection();
         stmt = conn.prepareStatement(st);
         stmt.setInt(1, orderid);
//         System.out.println(tableid);
         
        rs = stmt.executeQuery();
         while(rs.next()){
//             did = rs.getInt(1);
             data[0] = rs.getObject(1);
             data[1] = rs.getObject(2);
         }
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from getRespectiveDepartmentForOrderid"+getClass().getName());
        }
        finally{
            closeConnection();
        }
        return data;
    }
        
        
        //for adding select in alla combo
   
}
