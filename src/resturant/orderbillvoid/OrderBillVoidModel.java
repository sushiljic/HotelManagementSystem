/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.orderbillvoid;

import database.DBConnect;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SUSHIL
 */
public class OrderBillVoidModel  extends DBConnect{
    
    public DefaultTableModel getBillItemList( int billid){
                    PreparedStatement stmtIssueInfo;
          ResultSet rsResult;
          String ColumnNames[] = new String[]{"Menu Id","Menu Name","Quantity","Rate","Total Amount","ComplimentaryType"};
            String strQuery = "SELECT bill_item_info.menu_id,menu.menu_name,bill_item_info.quantity,item_unit.unit_name,menu.retail_price,bill_item_info.complimentary_type FROM bill_item_info INNER JOIN menu ON bill_item_info.menu_id = menu.menu_id INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id WHERE bill_id = ? ";
          
       ArrayList<Object[]> data = new ArrayList<Object[]>();
    Object[][] finalData =null;
       DBConnect getissue = new DBConnect();
       try{
           getissue.initConnection();
           stmtIssueInfo = getissue.conn.prepareStatement(strQuery);
        //   System.out.println(new Timestamp(date[0].getTime())+"\n"+new Timestamp(date[1].getTime()));
         stmtIssueInfo.setInt(1, billid);
          rsResult =  stmtIssueInfo.executeQuery();
            while(rsResult.next()){
//               String st = rsResult.getBoolean("payment_type")== true?"Cash":"Credit";
                BigDecimal TotalAmount = rsResult.getBigDecimal("retail_price").setScale(2, RoundingMode.HALF_UP).multiply(rsResult.getBigDecimal("quantity").setScale(3, RoundingMode.HALF_UP));
                Object[] row = new Object[]{rsResult.getString("menu_id"),rsResult.getString("menu_name"),rsResult.getBigDecimal("quantity").setScale(3, RoundingMode.HALF_UP),rsResult.getBigDecimal("retail_price").setScale(2, RoundingMode.HALF_UP),TotalAmount.setScale(2, RoundingMode.HALF_UP),rsResult.getBoolean("complimentary_type")};
                data.add(row);
            }
             finalData = data.toArray(new Object[data.size()][]);
           
       }
       catch(SQLException se){
           JOptionPane.showMessageDialog(null, se+"From getBillItemList");
       }
       finally{
           getissue.closeConnection();
       }
         return new DefaultTableModel(finalData,ColumnNames){
          @Override
          public boolean isCellEditable(int rows,int columns){
          //all cell false
                  return false;    
          }
          @Override
          public Class<?> getColumnClass(int columnIndex){
              Class clazz = String.class;
              switch(columnIndex){
                  case 5:
                      
                      clazz = Boolean.class;
              }
              return clazz;
          }
                  
       };   

      }
     public Double[] getSalesList(int billid){
            PreparedStatement stmtIssueInfo;
          ResultSet rsResult;
//          String ColumnNames[] = new String[]{"Bill No"," Item Amount","SVC","VAT","Discount"," Bill Total"," Received Amount","Payment Type"};
            String strQuery = "SELECT bill.item_total_amount,bill.service_charge,bill.vat,bill.bill_discount,bill_total,total_received,payment_type FROM bill WHERE bill_id = ? ";
       ArrayList<Double> data = new ArrayList<Double>();
    Double[] finalData =null;
       DBConnect getissue = new DBConnect();
       try{
           getissue.initConnection();
           stmtIssueInfo = getissue.conn.prepareStatement(strQuery);
        //   System.out.println(new Timestamp(date[0].getTime())+"\n"+new Timestamp(date[1].getTime()));
         stmtIssueInfo.setInt(1, billid);
          rsResult =  stmtIssueInfo.executeQuery();
            while(rsResult.next()){
//               Double st = rsResult.getBoolean("payment_type")== true?"Cash":"Credit";
           
                Double[] row = new Double[]{rsResult.getDouble("item_total_amount"),rsResult.getDouble("service_charge"),rsResult.getDouble("vat"),rsResult.getDouble("bill_discount"),rsResult.getDouble("bill_total"),rsResult.getDouble("total_received"),rsResult.getDouble("payment_type")};
               // data.add(row);
                finalData = row;
            }
           //  finalData = data.toArray(new Double[data.size()]);
           
       }
       catch(SQLException se){
           JOptionPane.showMessageDialog(null, se+"From getSaleList");
       }
       finally{
           getissue.closeConnection();
       }
       return finalData;
       
       }
    
     public boolean checkBillId(int billid){
     Boolean ExistingStatus = null; 
    String strCheck = "SELECT bill_id FROM bill WHERE bill_id = ?  AND void !=1 ";
    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
        check.initConnection();
        stmtcheck = check.conn.prepareStatement(strCheck,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        stmtcheck.setInt(1, billid);
        ResultSet rs = stmtcheck.executeQuery();
        rs.last();
        int rows = rs.getRow();
        rs.beforeFirst();
        ExistingStatus = rows >= 1;
        
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from checkBillId");
    }
    finally{
        check.closeConnection();
    }
    return ExistingStatus;
    
}
      public boolean checkBillExist(int billid){
     Boolean ExistingStatus = null; 
    String strCheck = "SELECT bill_id FROM bill WHERE bill_id = ?   ";
    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
        check.initConnection();
        stmtcheck = check.conn.prepareStatement(strCheck,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        stmtcheck.setInt(1, billid);
        ResultSet rs = stmtcheck.executeQuery();
        rs.last();
        int rows = rs.getRow();
        rs.beforeFirst();
        ExistingStatus = rows >= 1;
        
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from checkBillExist");
    }
    finally{
        check.closeConnection();
    }
    return ExistingStatus;
    
}
      public Date returnBillDate(int billid){
     Date date = null;
    String strCheck = "SELECT bill_datetime FROM bill WHERE bill_id = ?  ";
    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
        check.initConnection();
        stmtcheck = check.conn.prepareStatement(strCheck,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        stmtcheck.setInt(1, billid);
        ResultSet rs = stmtcheck.executeQuery();
        while(rs.next()){
            date = rs.getDate("bill_datetime");
        }
        
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from checkBillId");
    }
    finally{
        check.closeConnection();
    }
    return date;
    
}
     public DefaultTableModel getOrderList(int billid){
          ArrayList<Object[]> orderlist= new ArrayList<Object[]>();
          Object[][] list= null;
          
          
    String strCheck = "SELECT order_id  FROM order_list WHERE bill_id = ? ";
    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    String colname[] = new String[]{"order_id"};
    try{
        check.initConnection();
        stmtcheck = check.conn.prepareStatement(strCheck);
        stmtcheck.setInt(1, billid);
        ResultSet rs = stmtcheck.executeQuery();
        
        while(rs.next()){
          Object[] row = new Object[]{rs.getObject("order_id")};
                  orderlist.add(row);
          }
         list = orderlist.toArray( new Object[orderlist.size()][]);
        }
     
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from getOrderList");
    }
    finally{
        check.closeConnection();
    }
    return new DefaultTableModel(list,colname){
        @Override
        public boolean isCellEditable(int row,int col){
            return false;
        }
        
    };
         
     }
     public void voidOrderBill(Object[][] itemlist,Object[][] orderlist,int billid){
           PreparedStatement stmtadd;
              PreparedStatement stmtorderadd;
              PreparedStatement stmtvoidbill;
              PreparedStatement stmtSubtractResturantStore;
              PreparedStatement stmtSubtractHybridResturantStore;
              BigDecimal total_amount = BigDecimal.ZERO;
              String MenuId = new String();
              String strvoidbill = "UPDATE  bill SET void = 1 WHERE bill_id =?";
              String stradd = "DELETE FROM order_item_list WHERE order_id IN (?) ";
              String strSubtractSingleResturantStore = "UPDATE department_store_stock SET total_qty = department_store_stock.total_qty +(? * (select  menu.quantity*item_unit.unit_relative_quantity from menu INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id WHERE menu.menu_id = ?)) WHERE item_id = (select item_id from menu where menu_id = ?)";
              String strSubtractHybridResturantStore = "UPDATE department_store_stock SET total_qty = department_store_stock.total_qty + (? *?) WHERE item_id = ?  ";
             String strorderadd = "DELETE  FROM order_list WHERE order_id = ?";
              DBConnect addorder = new DBConnect();
              try{
                  addorder.initConnection();
                  /*
                   * for voiding the biil
                   */
                  stmtvoidbill = addorder.conn.prepareStatement(strvoidbill);
                  addorder.conn.setAutoCommit(false);
                  stmtvoidbill.setInt(1, billid);
                  stmtvoidbill.executeUpdate();
                  
                  /*
                   * for inserting into order_item_list
                   */
              
                  //when tracable item
                //  if(checkTrackable(stradd))
                  /*
                   * separating the menu_id to thier type as singletracable and hybrid type
                   */
                  ArrayList<String[]> SingleTrackableItem = new ArrayList<>();
                  ArrayList<String[]> HybridTrackableItem = new ArrayList<>();
                  String[][] strSingleTrackableItem = null;
                  String[][] strHybridTrackableItem = null;
               for (Object[] itemlist1 : itemlist) {
                   MenuId = itemlist1[0].toString();
                   if (checkTrackable(MenuId)) {
                       if (checkHybrid(MenuId)) {
                           String[] row = new String[]{MenuId, itemlist1[2].toString()};
                           HybridTrackableItem.add(row);
                       } else {
                           String[] row = new String[]{MenuId, itemlist1[2].toString()};
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
                   // stmtSubtractResturantStore.setString(1, strSingleTrackableItem[i][0]);
//                       stmtSubtractResturantStore.setString(1, strSingleTrackableItem[i][0]);
                   //  System.out.println(strSingleTrackableItem[i][0]+orderid);
                   stmtSubtractResturantStore.setBigDecimal(1, new BigDecimal(strSingleTrackableItem1[1]));
                   stmtSubtractResturantStore.setString(2, strSingleTrackableItem1[0]);
                   stmtSubtractResturantStore.setString(3, strSingleTrackableItem1[0]);
//                      stmtSubtractResturantStore.setString(4, strSingleTrackableItem[i][0]);
                   stmtSubtractResturantStore.executeUpdate();
               }
                  /*
                   * if only hybrid trackable
                   */
                  //return the item-unit in row
                  ArrayList<String[]> HybridItem = new ArrayList<>();
                  String[][] strHybridItem = null;
               for (String[] strHybridTrackableItem1 : strHybridTrackableItem) {
                   String[][] data = getItemIdForHybrid(strHybridTrackableItem1[0]);
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
                   stmtSubtractHybridResturantStore.setString(1, strHybridItem1[2]); //order quantity
//                      stmtSubtractHybridResturantStore.setInt(2, Integer.parseInt(orderid));//order_id
                   stmtSubtractHybridResturantStore.setBigDecimal(2, new BigDecimal(strHybridItem1[1])); //total quantity
                   stmtSubtractHybridResturantStore.setString(3, strHybridItem1[0]); //Item_id
                   stmtSubtractHybridResturantStore.executeUpdate();
               }
                  /*
                   * for order_item list table
                   */
                    stmtadd = addorder.conn.prepareStatement(stradd);
                  addorder.conn.setAutoCommit(false);
               for (Object[] orderlist1 : orderlist) {
                   stmtadd.setInt(1, Integer.parseInt(orderlist1[0].toString()));
                   stmtadd.executeUpdate();
               }
                  
                  /*
                   * for order_list table
                   */
                  stmtorderadd = addorder.conn.prepareStatement(strorderadd);
                  addorder.conn.setAutoCommit(false);
               for (Object[] orderlist1 : orderlist) {
                   stmtorderadd.setInt(1, Integer.parseInt(orderlist1[0].toString()));
                   //   stmtorderadd.setString(2, waiterid);
                   //  stmtorderadd.setString(3, customerid);
                   //  stmtorderadd.setBigDecimal(4, total_amount);
                   //  stmtorderadd.setTimestamp(5, new Timestamp(new Date().getTime()));
                   stmtorderadd.executeUpdate();
               }
                  //if everything goes weel commit
               addorder.conn.commit();
               JOptionPane.showMessageDialog(null, "Bill Void   Successfully");
                  
              }
              catch(SQLException e){
                  JOptionPane.showMessageDialog(null, e+"from Order Bill Void");
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
         public boolean checkTrackable(String menuid){
     //Boolean ExistingStatus = null; 
       Boolean id = false;
      //  String TableName = tablename;
    String strCheck = "SELECT item_type FROM menu WHERE menu_id = ?";
    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
        check.initConnection();
        stmtcheck = check.conn.prepareStatement(strCheck);
     stmtcheck.setString(1,menuid);
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
       public boolean checkHybrid(String menuid){
     //Boolean ExistingStatus = null; 
       Boolean id = false;
      //  String TableName = tablename;
    String strCheck = "SELECT hybrid_type FROM menu WHERE menu_id = ?";
    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
        check.initConnection();
        stmtcheck = check.conn.prepareStatement(strCheck);
     stmtcheck.setString(1,menuid);
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
        public String[][] getItemIdForHybrid(String menuid){
        DBConnect gettg = new DBConnect();
        PreparedStatement stmtget ;
        ResultSet rs;
       // String search = menuid+"%";
        String strget = "SELECT item_id,quantity*item_unit.unit_relative_quantity as total_qty  from hybrid_menu INNER JOIN item_unit ON hybrid_menu.unit_id = item_unit.unit_id where parent_menu_id  in (?)";
       // String[] columnName = new String[]{"Menu Id","Menu Name"," Retail Rate","Wholesale Rate","Base Unit",};
        ArrayList<String[]> data = new ArrayList<String[]>();
        String[][] finaldata = null;
        try{
            gettg.initConnection();
            stmtget = gettg.conn.prepareStatement(strget);
            stmtget.setString(1, menuid);
            rs = stmtget.executeQuery();
            while(rs.next()){
                String[] row = new String[]{rs.getString("item_id"),rs.getString("total_qty")};
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
    
    
}
