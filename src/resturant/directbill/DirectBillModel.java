/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.directbill;

import database.DBConnect;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import reusableClass.Function;

/**
 *
 * @author SUSHIL
 */
public class DirectBillModel extends DBConnect {
    PreparedStatement stmtorderbill;
    ResultSet rsordernill;
    String rsQuery = new String();
    Object[][] CustomerInfo;
    Object[][] MenuInfo;
            
     public void AddBill(Object[][] menudata,Double[] ratedata,String[] otherdata,String[][] complimentary,int customerid,int userid,int departmentid){
         /*
         *     
         * this id for ratedata
        data[0] = getTotal();
          if(getBooleanSVCPercentage()){
            System.out.println(getSVCAmount());
          data[1] = getSVCAmount();
          }
          else{
          data[1] = getSVC();
          }
          
          data[2] = getVATAmount();
          data[3 ]= getSubTotal();
          if(getBooleanDiscountPercentage()){
          data[4] = getDiscountAmount();
          System.out.println(getDiscountAmount());
          }
          else{
           data[4] = getDiscount();
          }
        
          data[5] = getGrandTotal1();
         // data[6] = getOrderId();
          data[6] = getTenderedAmount();
          data[7] = getChangeAmount();
          * this is for bill otherdata
          *   data[0] = getBillId();
          data[1] = String.valueOf(getBooleanSVCPercentage());
          data[2] = String.valueOf(getBooleanDiscountPercentage());
          data[3] = getCustomerId();
          data[4] = String.valueOf(getPaymentType());
          data[5] = String.valueOf(getBooleanComplimentaryType());
          data[6] = getComplimentaryID();
         *
        /*
        preparestatement for adding order in orderdata
        */
          PreparedStatement stmtadd;
        PreparedStatement stmtorderadd;
        PreparedStatement stmtSubtractResturantStore;
        PreparedStatement stmtSubtractHybridResturantStore;
        PreparedStatement stmtTableInfo;
        BigDecimal total_amount = BigDecimal.ZERO;
//        Retrreiving the last value inserted into the database
        int orderid = returnCurrentOrderItentityId("generate_orderid");
//        System.out.println("wala"+orderid);
        /*
        for addbill
        */
        PreparedStatement stmtbill;
        PreparedStatement stmtorder;
        PreparedStatement stmtbillitemlist;
        PreparedStatement stmttablereset;
        /*
        query for order
        */
          String MenuId = new String();
          Double ComplimentaryAmount = 0.0;
              String stradd = "INSERT INTO order_item_list (order_id,menu_id,quantity) VALUES(?,?,?)";
//              String strSubtractSingleResturantStore = "UPDATE resturant_store SET total_qty = resturant_store.total_qty -(? * (select  menu.quantity*item_unit.unit_relative_quantity from menu INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id WHERE menu.menu_id = ?)) WHERE item_id = (select item_id from menu where menu_id = ?)";
//              String strSubtractHybridResturantStore = "UPDATE resturant_store SET total_qty = resturant_store.total_qty  - ?*? WHERE item_id = ?  ";
              String strSubtractSingleResturantStore = "UPDATE department_store_stock SET total_qty = department_store_stock.total_qty -(? * (select  menu.quantity*item_unit.unit_relative_quantity from menu INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id WHERE menu.menu_id = ?)) WHERE department_item_id = (select department_item_id from menu where menu_id = ?)";
              String strSubtractHybridResturantStore = "UPDATE department_store_stock SET total_qty = department_store_stock.total_qty  - ?*? WHERE department_item_id = ?  ";
              String strorderadd = "INSERT INTO order_list(customer_id,total_amount,date,order_id,bill_id,paid,user_id,department_id,com_date) VALUES(?,?,?,?,?,?,?,?,?)";
            
             /*
             qeury for addbil
             */
//        String strBill = "INSERT INTO bill (item_total_amount,service_charge,vat,bill_discount,bill_total,total_received,customer_id,payment_type,bill_datetime,bill_id) VALUES(?,?,?,?,?,?,?,?,?,?)";
         String strBill = "INSERT INTO bill (item_total_amount,service_charge,vat,bill_discount,bill_total,total_received,customer_id,payment_type,bill_datetime,bill_id,user_id,department_id,complimentary_flag,complimentary_amount,com_bill_datetime) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String strBillItemInfo = "INSERT INTO bill_item_info (bill_id,menu_id,quantity,complimentary_type) VALUES(?,?,?,?) ";
        String strUpdateOrder = "UPDATE order_list SET paid = ?,bill_id = ? WHERE order_id = ?";
//        String strtablereset = " UPDATE  table_info set table_status = 0 WHERE table_id = ?";
        DBConnect dbconn = new DBConnect();
       
        
        try{
            dbconn.initConnection();
               /*
                   * for order_list table
                   */
             dbconn.conn.setAutoCommit(false);
                  stmtorderadd = dbconn.conn.prepareStatement(strorderadd);
                 
                    stmtorderadd.setInt(1,customerid);
                    
                      stmtorderadd.setBigDecimal(2, new BigDecimal(ratedata[0]));
//                      stmtorderadd.setTimestamp(3, new Timestamp(new Date().getTime()));
//                     inserting systemdate into datbaase
                      stmtorderadd.setTimestamp(3, new Timestamp(Function.returnSystemDate().getTime()));
                      stmtorderadd.setInt(4, orderid);
                      stmtorderadd.setInt(5, Integer.parseInt(otherdata[0]));
                      stmtorderadd.setInt(6, 1);
                      stmtorderadd.setInt(7, userid);
                      stmtorderadd.setInt(8, departmentid);
                      //this retrieve the system time of cimputer
                      stmtorderadd.setTimestamp(9, new Timestamp(new Date().getTime()));
                      stmtorderadd.executeUpdate();
               /*
                   * for inserting into order_item_list
                   */
                      //
                    
//                      System.out.println(orderid);
                  stmtadd = dbconn.conn.prepareStatement(stradd);
                  dbconn.conn.setAutoCommit(false);
              for (Object[] item1 : menudata) {
                  stmtadd.setInt(1,orderid);
                  stmtadd.setString(2, item1[0].toString());
                  stmtadd.setBigDecimal(3, new BigDecimal(item1[2].toString()));
//                  total_amount = total_amount.add(new BigDecimal(item1[5].toString()));
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
                  for (Object[] item1 : menudata) {
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
                  stmtSubtractResturantStore = dbconn.conn.prepareStatement(strSubtractSingleResturantStore);
                  dbconn.conn.setAutoCommit(false);
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
                  stmtSubtractHybridResturantStore = dbconn.conn.prepareStatement(strSubtractHybridResturantStore);
                  for (String[] strHybridItem1 : strHybridItem) {
                      //  stmtdelSubtractHybridResturantStore.setString(1, straddHybridItem[i][0]);
                      stmtSubtractHybridResturantStore.setBigDecimal(1, new BigDecimal(strHybridItem1[2]));
                      stmtSubtractHybridResturantStore.setBigDecimal(2, new BigDecimal(strHybridItem1[1]));
                      stmtSubtractHybridResturantStore.setString(3, strHybridItem1[0]);
                      stmtSubtractHybridResturantStore.executeUpdate();
                  }
                  
                 /*
           * inserting data into bill_item_info
           */
//          int billid = returnCurrentOrderId();
//          System.out.println(billid);
          stmtbillitemlist = dbconn.conn.prepareStatement(strBillItemInfo);
            for (Object[] menudata1 : menudata) {
                stmtbillitemlist.setInt(1,Integer.parseInt(otherdata[0]));
                stmtbillitemlist.setString(2, menudata1[0].toString());
                stmtbillitemlist.setBigDecimal(3, new BigDecimal(menudata1[2].toString()));
                /*
                 * checking for the complimentary item
                 */
//                  System.out.println(complimentary.length);
                if (complimentary.length != 0) {
                    for (String[] complimentary1 : complimentary) {
                        if (complimentary1[0].equalsIgnoreCase(menudata1[0].toString())) {
                            // System.out.println("inside complimentary"+menudata[i][0]+"\n");
                            stmtbillitemlist.setInt(4, 1);
                            ComplimentaryAmount += Double.parseDouble(menudata1[5].toString());
                            break;
                        } else {
                            stmtbillitemlist.setInt(4, 0);
                            //  System.out.println("outside complimentary"+menudata[i][0]);
                        }
                    }
                } else {
                    //  System.out.println("outside loops"+menudata[i][0]);
                    stmtbillitemlist.setInt(4, 0);
                }
                stmtbillitemlist.executeUpdate();
            }   
               
            /*
             * inserting in bill table
             */

           
            stmtbill = dbconn.conn.prepareStatement(strBill);
            dbconn.conn.setAutoCommit(false);
             stmtbill.setBigDecimal(1,new BigDecimal(ratedata[0]));
          
//            //checking for svc is percent or notif svc is in percentage convert in into number
//            if(otherdata[1].equalsIgnoreCase("false")){
//                
//           // stmtbill.setBigDecimal(2,new BigDecimal(ratedata[1]));
//             stmtbill.setDouble(2, ratedata[1]);
//            }
//            else{
//                 
//                //calcute svc value to stored in database
//              //  System.out.println(new BigDecimal(ratedata[1]).multiply(new BigDecimal(ratedata[0])));
//          //  stmtbill.setBigDecimal(2,new BigDecimal(ratedata[1]).multiply(new BigDecimal(ratedata[0])));
//               stmtbill.setDouble(2, ratedata[1]*ratedata[0]);
//                 
//            }
            //for svc
            stmtbill.setDouble(2, ratedata[1]);
            
          
//            stmtbill.setDouble(3, ratedata[2]*ratedata[0]);
          //for vat
            stmtbill.setDouble(3,ratedata[2]);
            
            
            //checking where discount is in percentage and calculating resultaant
//            if(otherdata[2].equalsIgnoreCase("false")){
//                 // stmtbill.setBigDecimal(4, new BigDecimal(ratedata[4]));
//                 stmtbill.setDouble(4, ratedata[4]);
//                 //  System.out.println(ratedata[4]);
//            }
//            else{
//              //  System.out.println(new BigDecimal(ratedata[4]).multiply(new BigDecimal(ratedata[3])));
//               
//              //  stmtbill.setBigDecimal(4, new BigDecimal(ratedata[4]).multiply(new BigDecimal(ratedata[3])));
//                stmtbill.setDouble(4, ratedata[4]*ratedata[3]);
//            }
          //for discount
           stmtbill.setDouble(4, ratedata[4]-ComplimentaryAmount);
           // stmtbill.setBigDecimal(5, new BigDecimal(ratedata[5]));
            stmtbill.setDouble(5, ratedata[5]);
            
            if(ratedata[6]>ratedata[5]){
            //  stmtbill.setBigDecimal(6, new BigDecimal(ratedata[5])); 
              stmtbill.setDouble(6, ratedata[5]);
              // System.out.println("wala");
            }
            else{
          //  stmtbill.setBigDecimal(6, new BigDecimal(ratedata[6]));
            stmtbill.setDouble(6, ratedata[6]);
           //  System.out.println("wala");
            }
            stmtbill.setInt(7, Integer.parseInt(otherdata[3]));
            
            
          if(otherdata[4].equalsIgnoreCase("true")){
              stmtbill.setInt(8, 1);
          }
          else{
              stmtbill.setInt(8, 0);
          }
          
//          stmtbill.setTimestamp(9, new Timestamp(new Date().getTime()));
         //inserting the system date into datbaase
          stmtbill.setTimestamp(9, new Timestamp(Function.returnSystemDate().getTime()));
          stmtbill.setInt(10, Integer.parseInt(otherdata[0]));
          
          stmtbill.setInt(11,userid);
          stmtbill.setInt(12, departmentid);
           if(otherdata[5].equalsIgnoreCase("true")){
           
               stmtbill.setInt(13, 1);
           }
           else{
              stmtbill.setInt(13, 0);  
           }
         stmtbill.setDouble(14, ComplimentaryAmount);
         //this retieve the system date of computer system
         stmtbill.setTimestamp(15, new Timestamp(new Date().getTime()));
          stmtbill.executeUpdate();
           
     
          /*
           * updating the data into order table
           */
//          stmtorder = dbconn.conn.prepareStatement(strUpdateOrder);
////            for (String orderdata1 : orderdata) {
//                //System.out.println(orderdata[i]);
//                stmtorder.setInt(1, 1);
//                stmtorder.setInt(2,Integer.parseInt(otherdata[0]));
//                stmtorder.setInt(3, Integer.parseInt(orderdata1));
//                stmtorder.executeUpdate();
//            }
            /*
            updating the table statuse in table_info
             */
//            stmttablereset = dbconn.conn.prepareStatement(strtablereset);
//            stmttablereset.setInt(1, tableid);
//            stmttablereset.executeUpdate();
            
          /*
           * if al goes well commit the database
           */
          dbconn.conn.commit();
          JOptionPane.showMessageDialog(null, "Bill Recorded Succesfully");
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from AddBill"+getClass().getName());
          //  se.printStackTrace();
        }
        finally{
            dbconn.closeConnection();
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
      
       public Object[][] getCustomerInfoObject(){
        PreparedStatement stmtget;
        ResultSet rsget ;
        DBConnect dbget  = new DBConnect();
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        
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
       public String[] returnMenuName(Object[][] obj){
        String[] Name = new String[obj.length];
        for(int i=0;i<obj.length;i++){
            Name[i] = obj[i][1].toString();
        }
        return Name;
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
//     public int returnCurrentItentityId(String tablename){
//     //Boolean ExistingStatus = null; 
//        int id = 0;
//        String TableName = tablename;
//    String strCheck = "SELECT IDENT_CURRENT(?)+1 AS id";
//    DBConnect check = new DBConnect();
//    PreparedStatement stmtcheck ;
//    try{
//        check.initConnection();
//        stmtcheck = check.conn.prepareStatement(strCheck);
//     stmtcheck.setString(1, TableName);
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
        String TableName = tablename;
        String strempty = "SELECT  autoinc_billid as id FROM generate_billid WHERE bill_status = 0 LIMIT 1  ";
    String strCheck = "INSERT INTO  generate_billid (bill_status)   VALUES(1) ";
//    String strGetId = " SELECT @@IDENTITY as id";
     String strGetId = " SELECT last_insert_id() ";
//    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
       initConnection();
      conn.setAutoCommit(false);
        stmtcheck = conn.prepareStatement(strempty);
        ResultSet rse = stmtcheck.executeQuery();
       while(rse.next()){
           em = rse.getInt("id");
//           System.out.println(em);
//           break;
       }
       if(em != 0 ){
           id = em;
//           System.out.println("wala");
            conn.setAutoCommit(false);
           stmtcheck = conn.prepareStatement("UPDATE generate_billid SET bill_status = 1 WHERE autoinc_billid = ?");
           stmtcheck.setInt(1, id);
          stmtcheck.executeUpdate();
//          System.out.println(id);
       }
       else{
            conn.setAutoCommit(false);
        stmtcheck = conn.prepareStatement(strCheck);
        stmtcheck.executeUpdate();
        conn.setAutoCommit(false);
        stmtcheck = conn.prepareStatement(strGetId);
//     stmtcheck.setString(1, oid);
        ResultSet rs = stmtcheck.executeQuery();
        while(rs.next()){
        id = rs.getInt(1);
//        System.out.println(id);
        }
       }
       conn.commit();
        
       
        
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from returncurrentIdentityid");
    }
    finally{
       closeConnection();
    }
    return id;
    
}
       public void MakeCurrentItentityIdFalse(int orderid){
     //Boolean ExistingStatus = null; 
        
        int oid = orderid;
        String strempty = "UPDATE  generate_billid SET  bill_status = 0 WHERE autoinc_billid = ?   ";
   
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
//      public int returnCurrentOrderId(){
//     //Boolean ExistingStatus = null; 
//        int id = 0;
////        String TableName = tablename;
//    String strCheck = "SELECT  SCOPE_IDENTITY() AS id ";
//    DBConnect check = new DBConnect();
//    PreparedStatement stmtcheck ;
//    try{
//        check.initConnection();
//        stmtcheck = check.conn.prepareStatement(strCheck);
////     stmtcheck.setString(1, TableName);
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
        public int returnCurrentOrderItentityId(String tablename){
     //Boolean ExistingStatus = null; 
        int id = 0;
        int em =0;
        String TableName = tablename;
        String strempty = "SELECT  autoinc_orderid as id FROM generate_orderid WHERE order_status = 0 LIMIT 1  ";
    String strCheck = "INSERT INTO  generate_orderid (order_status)   VALUES(1) ";
//    String strGetId = " SELECT @@IDENTITY as id";
    String strGetId = " SELECT last_insert_id()";
//    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
       initConnection();
      conn.setAutoCommit(false);
        stmtcheck = conn.prepareStatement(strempty);
        ResultSet rse = stmtcheck.executeQuery();
       while(rse.next()){
           em = rse.getInt("id");
//           System.out.println(em);
//           break;
       }
       if(em != 0 ){
           id = em;
//           System.out.println("wala");
             conn.setAutoCommit(false);
           stmtcheck = conn.prepareStatement("UPDATE generate_orderid SET order_status = 1 WHERE autoinc_orderid = ?");
           stmtcheck.setInt(1, id);
          stmtcheck.executeUpdate();
//          System.out.println(id);
       }
       else{
             conn.setAutoCommit(false);
        stmtcheck = conn.prepareStatement(strCheck);
        stmtcheck.executeUpdate();
        conn.setAutoCommit(false);
        stmtcheck = conn.prepareStatement(strGetId);
//     stmtcheck.setString(1, oid);
        ResultSet rs = stmtcheck.executeQuery();
        while(rs.next()){
        id = rs.getInt(1);
//        System.out.println(id);
        }
       }
      conn.commit();
        
       
        
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from returnCurrentOrderItentityId");
    }
    finally{
       closeConnection();
    }
    return id;
    
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
                Object[] row = new Object[]{rs.getObject("menu_id"),rs.getObject("menu_name"),new BigDecimal(rs.getString("quantity")).setScale(3, RoundingMode.HALF_UP),rs.getObject("unit_name"),new BigDecimal(rs.getString("retail_price")).setScale(2, RoundingMode.HALF_UP),new BigDecimal(rs.getString("total_amount")).setScale(2, RoundingMode.HALF_UP)};
            data.add(row);
            }
            finaldata = data.toArray(new Object[data.size()][]);
            
        }
        
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"from getItemListByOrderId");
        }
        finally{
            gettg.closeConnection();
        }
        return finaldata;
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
            JOptionPane.showMessageDialog(null, e+"from getItemIdForHybrid");
        }
        finally{
            gettg.closeConnection();
        }
        return finaldata;
    }
            public boolean checkTrackable(int menuid){
     //Boolean ExistingStatus = null; 
       Boolean id = false;
      //  String TableName = tablename;
    String strCheck = "SELECT item_type FROM menu WHERE menu_id = ?";
//    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
        initConnection();
        stmtcheck = conn.prepareStatement(strCheck);
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
       closeConnection();
    }
    return id;
    
}
       public boolean checkHybrid(int menuid){
     //Boolean ExistingStatus = null; 
       Boolean id = false;
      //  String TableName = tablename;
    String strCheck = "SELECT hybrid_type FROM menu WHERE menu_id = ?";
//    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
        initConnection();
        stmtcheck = conn.prepareStatement(strCheck);
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
        closeConnection();
    }
    return id;
    
}
            
      public DefaultTableModel getOrderInfo(){
        DBConnect gettg = new DBConnect();
        PreparedStatement stmtget ;
        ResultSet rs;
       // String search = src+"%";
        String strget = "SELECT order_list.order_id,table_info.table_name,order_list.customer_id,order_list.total_amount FROM order_list LEFT JOIN  table_info ON order_list.table_id = table_info.table_id  WHERE order_list.paid = 0 ORDER BY order_list.date desc ";
        String[] columnName = new String[]{"Order Id","Table Name","Total Amount "};
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        
        Object[][] finaldata = null;
        try{
            gettg.initConnection();
            stmtget = gettg.conn.prepareStatement(strget);
           // stmtget.setString(1,new String(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
            
          //  System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Timestamp(new Date().getTime())));
            rs = stmtget.executeQuery();
            while(rs.next()){
               /* if(rs.getString("table_id").isEmpty()){
                    data = rs.getString("Customer Name")
                }*/
                Object[] row = new Object[]{rs.getInt("order_id"),rs.getString("table_name"),rs.getBigDecimal("total_amount").setScale(2, RoundingMode.HALF_UP)};
            data.add(row);
            }
            finaldata = data.toArray(new Object[data.size()][]);
            
        }
        
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"from getOrderInfo");
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
       public Object[][] getMenuInfo(int departmentid){
          PreparedStatement stmtget;
          ResultSet rsget;
          ArrayList<Object[]> data = new ArrayList<Object[]>();
          String strgetMenu = "SELECT menu.menu_id,menu.menu_name,item_unit.unit_name,menu.retail_price FROM menu INNER JOIN item_unit ON  menu.unit_id = item_unit.unit_id WHERE department_id = ?";
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
      
       public DefaultTableModel getOrderInfoForAddOrder(ArrayList<String> st){
        DBConnect gettg = new DBConnect();
        PreparedStatement stmtget ;
        ResultSet rs;
        String cond = " AND order_id !=";
        String tt = new String();
       // String search = src+"%";
        String strget = "SELECT order_list.order_id,table_info.table_name,order_list.customer_id,order_list.total_amount FROM order_list LEFT JOIN  table_info ON order_list.table_id = table_info.table_id  WHERE order_list.paid = 0";
       for(int i=0;i<st.size();i++){
          // cond += st.get(i)+" ";
           tt += cond + st.get(i)+" ";
       }
        String appendget = "ORDER BY order_list.date desc ";
       // System.out.println(strget+tt+appendget);
        String query = strget+tt+appendget;
       // System.out.println(query);
        String[] columnName = new String[]{"Order Id","Table Name","Total Amount "};
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        
        Object[][] finaldata = null;
        try{
            gettg.initConnection();
            stmtget = gettg.conn.prepareStatement(query);
           // stmtget.setString(1,new String(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
            
          //  System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Timestamp(new Date().getTime())));
            rs = stmtget.executeQuery();
            while(rs.next()){
               /* if(rs.getString("table_id").isEmpty()){
                    data = rs.getString("Customer Name")
                }*/
                Object[] row = new Object[]{rs.getInt("order_id"),rs.getString("table_name"),rs.getBigDecimal("total_amount").setScale(2, RoundingMode.HALF_UP)};
            data.add(row);
            }
            finaldata = data.toArray(new Object[data.size()][]);
            
        }
        
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"from getOrderInfoForAddOrder");
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
       public DefaultTableModel getComplimentaryTable(DefaultTableModel dtm,String[][] menuid){
           DefaultTableModel model = dtm;
           
           for(int i=0;i<model.getRowCount();i++){
               for(int j=0;j<menuid.length;j++){
                if(menuid[j][0].equalsIgnoreCase(model.getValueAt(i, 0).toString()) && menuid[j][1].equalsIgnoreCase(model.getValueAt(i, 5).toString())){
                 model.removeRow(i);
                }   
               }
           }
       return model;
       }
         public Double[] getChargeInfo(){
             String strQuery = new String();
             PreparedStatement stmtget ;
             ResultSet rsget;
        strQuery = "SELECT company_vat,company_svc FROM company_info";
//        DBConnect getC = new DBConnect();
       Double cinfo[] = new Double[2];
        try{
           initConnection();
            stmtget =conn.prepareStatement(strQuery);
           rsget = stmtget.executeQuery();
         
          while(rsget.next()){
             
              cinfo[0] = rsget.getDouble("company_svc");
              cinfo[1] = rsget.getDouble("company_vat");
            
             
          }
          
        }
       
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"Sql error");
        }
        finally{
            closeConnection();
        }
        return cinfo;
    }
           public int getTableIdInfoTableName(String tablename){
             int tableid = 0;
             String strQuery = new String();
             PreparedStatement stmtget ;
             ResultSet rsget;
        strQuery = "SELECT table_id FROM table_info WHERE table_name = ? ";
        DBConnect getC = new DBConnect();
        
//       Double cinfo[] = new Double[2];
        try{
            getC.initConnection();
            stmtget = getC.conn.prepareStatement(strQuery);
            stmtget.setString(1, tablename);
            rsget = stmtget.executeQuery();
          getC.conn.commit();
          while(rsget.next()){
             
             tableid  = rsget.getInt("table_id");
            
             
          }
          
        }
       
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"getTableIdInfoTableName");
        }
        finally{
            getC.closeConnection();
        }
        return tableid;
    }
             public String returnMenuNameFromId(int menuid){
     //Boolean ExistingStatus = null; 
       String id = null;
      //  String TableName = tablename;
    String strCheck = "SELECT menu_name FROM menu WHERE menu_id = ?";
//    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
        initConnection();
        stmtcheck = conn.prepareStatement(strCheck);
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
       closeConnection();
    }
    return id;
    
}
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
          JOptionPane.showMessageDialog(null, se+"from getotherStoreName "+getClass().getName());
      }
      finally{
          closeConnection();
      }
      return data.toArray(new Object[data.size()][]);
  }
     public  Object[][] getComplimentaryInfo(){
         PreparedStatement stmtget;
        ResultSet rsget ;
        DBConnect dbget  = new DBConnect();
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        
        String strget = "SELECT complimentary_id,complimentary_reason FROM complimentary_info ";
        try{
            dbget.initConnection();
            stmtget = dbget.conn.prepareStatement(strget);
            rsget = stmtget.executeQuery();
            while(rsget.next()){
                Object[] row = new Object[]{rsget.getInt("complimentary_id"),rsget.getString("complimentary_reason")};
                data.add(row);
            }
           
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from getComplimentaryInfo");
        }
        finally{
            dbget.closeConnection();
        }
        return data.toArray(new Object[data.size()][]);
     }
}
