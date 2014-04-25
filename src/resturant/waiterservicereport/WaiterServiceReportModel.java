/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.waiterservicereport;

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

/**
 *
 * @author SUSHIL
 */
public class WaiterServiceReportModel extends DBConnect {
     public  Object[][] WaiterInfo;
  
//    public WaiterServiceReportModel(WaiterServiceReportModel model,ComplimentaryReportView view){
//      
//    }
//   
     
      public DefaultTableModel getWaiterServiceList(String CustomerId,Date[] date,Boolean IncludeAll){
          PreparedStatement  stmtget;
          ResultSet rsGet;
          ArrayList<Object[]> data = new ArrayList<Object[]>();
           Object[][] finaldata = null;
           String[] ColumnNames = new String[]{"Waiter Id","Waiter Name","Bill Id","Amount Served","Date "}; 
           String strQuery = "SELECT order_list.waiter_id,waiter_info.waiter_name,order_list.bill_id,order_list.total_amount,order_list.date FROM order_list INNER JOIN waiter_info ON order_list.waiter_id = waiter_info.waiter_id LEFT JOIN bill ON order_list.bill_id = bill.bill_id WHERE order_list.date >= ? AND order_list.date <= ? ";
           if(!IncludeAll){
               strQuery += " AND order_list.waiter_id = ?";
           }
           DBConnect getWaiterServiceList = new DBConnect();
           
           try{
//               Timestamp tstamp = new Timestamp(00-00-00);
               getWaiterServiceList.initConnection();
               stmtget = getWaiterServiceList.conn.prepareStatement(strQuery);
               stmtget.setTimestamp(1, new Timestamp(date[0].getTime()));
               stmtget.setTimestamp(2, new Timestamp(date[1].getTime()));
//               stmtget.setTimestamp(1,new Timestamp(date[0].getTime()) , Calendar.getInstance(TimeZone.getTimeZone("UTC")));
//                stmtget.setTimestamp(2,new Timestamp(date[1].getTime()) , Calendar.getInstance(TimeZone.getTimeZone("UTC")));
//             stmtget.setTimestamp(1, tstamp.valueOf(date[0].toString()));
//             stmtget.setTimestamp(2, tstamp.valueOf(date[1].toString()));
//               stmtget.setDate(1,(Date) date[0]);
               if(!IncludeAll){
                   stmtget.setString(3, CustomerId);
               }
              rsGet =  stmtget.executeQuery();
               while(rsGet.next()){
                 //  BigDecimal rate = rsGet.getBigDecimal("bill_total").setScale(2, RoundingMode.HALF_UP).subtract(rsGet.getBigDecimal("total_received"));
                //   System.out.println(rate);
                   
                   Object[] row = new Object[]{rsGet.getString("waiter_id"),rsGet.getString("waiter_name"),rsGet.getString("bill_id"),rsGet.getBigDecimal("total_amount").setScale(2, RoundingMode.HALF_UP),rsGet.getDate("date")};
                   data.add(row);
           }
               finaldata = data.toArray(new Object[data.size()][]);
           }
           catch(SQLException se){
               JOptionPane.showMessageDialog(null, se+"From getWaiterServiceList");
           }
           finally{
               getWaiterServiceList.closeConnection();
           }
           return new DefaultTableModel(finaldata,ColumnNames){
             @Override
             public boolean isCellEditable(int row, int col){
                 return false;
             }  
           };
          
      }
      
        public   Object[][] getWaiterInfoObject(){
        PreparedStatement stmtget;
        ResultSet rsget ;
        DBConnect dbget  = new DBConnect();
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        
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
     public String[] returnWaiterName(Object[][] obj){
        String[] Name = new String[obj.length];
        for(int i=0;i<obj.length;i++){
            Name[i] = obj[i][1].toString();
        }
        return Name;
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
}
