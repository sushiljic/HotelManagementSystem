/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.orderbill;

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
import resturant.order.OrderModel;
import reusableClass.Function;

/**
 *
 * @author SUSHIL
 */
public class OrderBillModel  extends DBConnect{
    PreparedStatement stmtorderbill;
    ResultSet rsordernill;
    String rsQuery = new String();
    public Object[][] CustomerInfo;
    private OrderModel orderModel = new OrderModel();
    public void AddBill(Object[][] menudata,Double[] ratedata,String[] otherdata,String[] orderdata,int userid,int departmentid){
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
         */
        PreparedStatement stmtbill;
        PreparedStatement stmtorder;
        PreparedStatement stmtbillitemlist;
        PreparedStatement stmttablereset;
        PreparedStatement stmtordertable;
        PreparedStatement stmtcomplimentary;
        String strBill = "INSERT INTO bill (item_total_amount,service_charge,vat,bill_discount,bill_total,total_received,customer_id,payment_type,bill_datetime,bill_id,user_id,department_id,complimentary_flag,complimentary_amount,com_bill_datetime) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String strBillItemInfo = "INSERT INTO bill_item_info (bill_id,menu_id,quantity,complimentary_type) VALUES(?,?,?,?) ";
        String strUpdateOrder = "UPDATE order_list SET paid = ?,bill_id = ? WHERE order_id = ?";
        String strtablereset = " UPDATE  table_info set table_status = 0 WHERE table_id = ?";
        String strordertable = "DELETE FROM temp_order_table WHERE order_id = ?";
        String strcomplimentarybill = "INSERT INTO bill_complimentary(bill_id,complimentary_id) VALUES(?,?)";
        Double ComplimentaryAmount = 0.0;
        Double SubTotalAmount = 0.0;
        /*
        calculating complimentaryAmount
        */
        ComplimentaryAmount = Function.manageAmount(menudata)[1].doubleValue();
        SubTotalAmount = Function.manageAmount(menudata)[0].doubleValue();
        
        /*
        maipulating and getting array of the table_id for updating the table unpack
        */
        ArrayList<Integer> tablelist = new ArrayList<>();
        for(String iod:orderdata){
            int tid = returnTableIdByOrderId(Integer.parseInt(iod));
            if(tid != 0){
                tablelist.add(tid);
            }
        }
        DBConnect dbconn = new DBConnect();
       
        
        try{
            dbconn.initConnection();
           
                /*
           * inserting data into bill_item_info
           */
          stmtbillitemlist = dbconn.conn.prepareStatement(strBillItemInfo);
            for (Object[] menudata1 : menudata) {
                stmtbillitemlist.setInt(1,Integer.parseInt(otherdata[0]));
                stmtbillitemlist.setString(2, menudata1[0].toString());
                stmtbillitemlist.setBigDecimal(3, new BigDecimal(menudata1[2].toString()));
                stmtbillitemlist.setBoolean(4,(Boolean)menudata1[5]);
                stmtbillitemlist.executeUpdate();
//                System.out.println(ComplimentaryAmount);
            }
            /*
            for inserting into bill table
            */
            stmtbill = dbconn.conn.prepareStatement(strBill);
            dbconn.conn.setAutoCommit(false);
            stmtbill.setDouble(1,SubTotalAmount);

            //for svc
            stmtbill.setDouble(2, ratedata[1]);
          //for vat
            stmtbill.setDouble(3,ratedata[2]);
          //for discount
           stmtbill.setDouble(4, ratedata[4]);
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
          if(Function.getSystemDateEnable()){
              stmtbill.setTimestamp(9, new Timestamp(Function.returnSystemDate().getTime()));
          }
          else{
              stmtbill.setTimestamp(9,null);
          }
          
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
         //this get the date of the computer system
         stmtbill.setTimestamp(15, new Timestamp(new Date().getTime()));
         stmtbill.executeUpdate();
           
      
          /*
           * updating the data into order table
           */
          stmtorder = dbconn.conn.prepareStatement(strUpdateOrder);
            for (String orderdata1 : orderdata) {
                //System.out.println(orderdata[i]);
                stmtorder.setInt(1, 1);
                stmtorder.setInt(2,Integer.parseInt(otherdata[0]));
                stmtorder.setInt(3, Integer.parseInt(orderdata1));
                stmtorder.executeUpdate();
            }
            /*
            updating the table statuse in table_info
            */
            for(Integer tblid:tablelist){
                dbconn.conn.setAutoCommit(false);
                 stmttablereset = dbconn.conn.prepareStatement(strtablereset);
            stmttablereset.setInt(1, tblid);
            stmttablereset.executeUpdate();
                
            }
           /*
            deleting the temp_order_table
            */
            for(String tblid:orderdata){
                dbconn.conn.setAutoCommit(false);
                stmtordertable = dbconn.conn.prepareStatement(strordertable);
                stmtordertable.setInt(1, Integer.parseInt(tblid));
                stmtordertable.executeUpdate();
            }
            //for inserting the bill and complimentary reason if the complimentary is checked
            if(otherdata[5].equalsIgnoreCase("true")){
                dbconn.conn.setAutoCommit(false);
//                JOptionPane.showMessageDialog(null, "entering the data into complimentary");
                stmtcomplimentary = dbconn.conn.prepareStatement(strcomplimentarybill);
                stmtcomplimentary.setInt(1,Integer.parseInt(otherdata[0]));
                stmtcomplimentary.setInt(2,Integer.parseInt(otherdata[6]));
                stmtcomplimentary.executeUpdate();
            }
          /*
           * if al goes well commit the database
           */
          dbconn.conn.commit();
          JOptionPane.showMessageDialog(null, "Bill Recorded Succesfully");
            
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(null, se+"from AddBill");
          //  se.printStackTrace();
        }
        finally{
            dbconn.closeConnection();
        }
        
    }
    public void AddDirectBill(Object[][] menudata,Double[] ratedata,String[] otherdata,int customerid,int userid,int departmentid){
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
        int orderid = Function.returnCurrentOrderItentityId("generate_orderid");
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
        String stradd = "INSERT INTO order_item_list (order_id,menu_id,quantity,complimentary) VALUES(?,?,?,?)";
//              String strSubtractSingleResturantStore = "UPDATE resturant_store SET total_qty = resturant_store.total_qty -(? * (select  menu.quantity*item_unit.unit_relative_quantity from menu INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id WHERE menu.menu_id = ?)) WHERE item_id = (select item_id from menu where menu_id = ?)";
//              String strSubtractHybridResturantStore = "UPDATE resturant_store SET total_qty = resturant_store.total_qty  - ?*? WHERE item_id = ?  ";
        String strSubtractSingleResturantStore = "UPDATE department_store_stock SET total_qty = department_store_stock.total_qty -(? * (select  menu.quantity*item_unit.unit_relative_quantity from menu INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id WHERE menu.menu_id = ?)) WHERE department_item_id = (select department_item_id from menu where menu_id = ?)";
        String strSubtractHybridResturantStore = "UPDATE department_store_stock SET total_qty = department_store_stock.total_qty  - ?*? WHERE department_item_id = ?  ";
        String strorderadd = "INSERT INTO order_list(customer_id,total_amount,date,order_id,bill_id,paid,user_id,department_id,com_date,complimentary_amount) VALUES(?,?,?,?,?,?,?,?,?,?)";
            
             /*
             qeury for addbil
             */
//        String strBill = "INSERT INTO bill (item_total_amount,service_charge,vat,bill_discount,bill_total,total_received,customer_id,payment_type,bill_datetime,bill_id) VALUES(?,?,?,?,?,?,?,?,?,?)";
        String strBill = "INSERT INTO bill (item_total_amount,service_charge,vat,bill_discount,bill_total,total_received,customer_id,payment_type,bill_datetime,bill_id,user_id,department_id,complimentary_flag,complimentary_amount,com_bill_datetime) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String strBillItemInfo = "INSERT INTO bill_item_info (bill_id,menu_id,quantity,complimentary_type) VALUES(?,?,?,?) ";
        String strUpdateOrder = "UPDATE order_list SET paid = ?,bill_id = ? WHERE order_id = ?";
//        String strtablereset = " UPDATE  table_info set table_status = 0 WHERE table_id = ?";
        
        Double SubTotalAmount = 0.0;
        /*
        calculating complimentaryAmount
        */
        ComplimentaryAmount = Function.manageAmount(menudata)[1].doubleValue();
        SubTotalAmount = Function.manageAmount(menudata)[0].doubleValue();
        DBConnect dbconn = new DBConnect();
       
        
        try{
            dbconn.initConnection();
               /*
                   * for order_list table
                   */
             dbconn.conn.setAutoCommit(false);
                  stmtorderadd = dbconn.conn.prepareStatement(strorderadd);
                 
                    stmtorderadd.setInt(1,customerid);
                    
                      stmtorderadd.setDouble(2,SubTotalAmount);
//                     inserting systemdate into datbaase
                      if(Function.getSystemDateEnable()){
                          stmtorderadd.setTimestamp(3, new Timestamp(Function.returnSystemDate().getTime()));
                      }
                      else{
                          stmtorderadd.setTimestamp(3,null);
                      }
                      
                      stmtorderadd.setInt(4, orderid);
                      stmtorderadd.setInt(5, Integer.parseInt(otherdata[0]));
                      stmtorderadd.setInt(6, 1);
                      stmtorderadd.setInt(7, userid);
                      stmtorderadd.setInt(8, departmentid);
                      //this retrieve the system time of cimputer
                      stmtorderadd.setTimestamp(9, new Timestamp(new Date().getTime()));
                      stmtorderadd.setDouble(10, ComplimentaryAmount);
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
                  stmtadd.setBoolean(4, (Boolean)item1[5]);
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
                      if (orderModel.checkTrackable(Integer.parseInt(MenuId))) {
                          if (orderModel.checkHybrid(Integer.parseInt(MenuId))) {
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
                      String[][] data = orderModel.getItemIdForHybrid(Integer.parseInt(strHybridTrackableItem1[0]));
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
          stmtbillitemlist = dbconn.conn.prepareStatement(strBillItemInfo);
            for (Object[] menudata1 : menudata) {
                stmtbillitemlist.setInt(1,Integer.parseInt(otherdata[0]));
                stmtbillitemlist.setString(2, menudata1[0].toString());
                stmtbillitemlist.setBigDecimal(3, new BigDecimal(menudata1[2].toString()));
                stmtbillitemlist.setBoolean(4, (Boolean)menudata1[5]);
                stmtbillitemlist.executeUpdate();
            }
            /*
             * inserting in bill table
             */
            stmtbill = dbconn.conn.prepareStatement(strBill);
            dbconn.conn.setAutoCommit(false);
            stmtbill.setBigDecimal(1,new BigDecimal(ratedata[0]));
            //for svc
            stmtbill.setDouble(2, ratedata[1]);
          //for vat
            stmtbill.setDouble(3,ratedata[2]);
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
          if(Function.getSystemDateEnable()){
              stmtbill.setTimestamp(9, new Timestamp(Function.returnSystemDate().getTime()));
          }
          else{
              stmtbill.setTimestamp(9,null);
          }
          
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
        catch(Exception se){
            se.printStackTrace();
            JOptionPane.showMessageDialog(null, se+"from AddBill"+getClass().getName());
          //  se.printStackTrace();
        }
        finally{
            dbconn.closeConnection();
        }
        
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
    
    public DefaultTableModel getOrderInfoForAddOrder(ArrayList<String> st,int did){
        DBConnect gettg = new DBConnect();
        PreparedStatement stmtget ;
        ResultSet rs;
        String cond = " AND order_id !=";
        String tt = new String();
       // String search = src+"%";
        String strget = "SELECT order_list.order_id,table_info.table_name,order_list.customer_id,order_list.total_amount FROM order_list LEFT JOIN  table_info ON order_list.table_id = table_info.table_id  WHERE order_list.paid = 0 and order_list.department_id = ?";
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
            stmtget.setInt(1, did);
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
        
        catch(SQLException e){
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
            for (String[] menuid1 : menuid) {
                if (menuid1[0].equalsIgnoreCase(model.getValueAt(i, 0).toString()) && menuid1[1].equalsIgnoreCase(model.getValueAt(i, 5).toString())) {
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
        strQuery = "SELECT company_svc,company_vat FROM company_info";
        DBConnect getC = new DBConnect();
       Double cinfo[] = new Double[2];
        try{
            getC.initConnection();
            stmtget = getC.conn.prepareStatement(strQuery);
           rsget = stmtget.executeQuery();
       
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
//          getC.conn.commit();
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
    public DefaultTableModel getOrderInfoLike(int dep,String search){
        DBConnect gettg = new DBConnect();
        PreparedStatement stmtget ;
        ResultSet rs;
        Double[] TaxList = new Double[2];
        String st = search +"%";
        
       // String search = src+"%";
        String strget = "SELECT order_list.order_id,table_info.table_name,order_list.customer_id,order_list.total_amount FROM order_list LEFT JOIN  table_info ON order_list.table_id = table_info.table_id  WHERE order_list.paid = 0 and department_id = ? and order_list.order_id LIKE ? ORDER BY order_list.date desc ";
        String[] columnName = new String[]{"Order Id","Table Name","Total Amount "};
        ArrayList<Object[]> data = new ArrayList<>();
      
        Object[][] finaldata = null;
        /*
        retreiving the value for the tax to include into item total
        */
        TaxList = getChargeInfo();
        Double VAT = 0.0;
        Double SVC = 0.0;
        try{
//            System.out.println(dep);
            gettg.initConnection();
            stmtget = gettg.conn.prepareStatement(strget);
           // stmtget.setString(1,new String(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
            
          //  System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Timestamp(new Date().getTime())));
           stmtget.setInt(1, dep);
           stmtget.setString(2, st);
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
    
}
