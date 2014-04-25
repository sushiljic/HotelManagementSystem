/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.receivablereport;

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
public class ReceiveableReportModel extends DBConnect {
     public  Object[][] CustomerInfo;
  
//    public ReceiveableReportModel(ReceiveableReportModel model,ComplimentaryReportView view){
//      
//    }
//   
     
      public DefaultTableModel getReceiveableList(String CustomerId,Date[] date,Boolean IncludeAll){
          PreparedStatement  stmtget;
          ResultSet rsGet;
          ArrayList<Object[]> data = new ArrayList<Object[]>();
           Object[][] finaldata = null;
           String[] ColumnNames = new String[]{"Customer Id","Customer Name","Bill Id","Total Amount","Amount Payable","Date "}; 
           String strQuery = "SELECT bill.customer_id,customer_info.customer_name,bill.bill_id,bill.bill_total,bill.total_received,bill.bill_datetime FROM bill INNER JOIN customer_info ON bill.customer_id = customer_info.customer_id WHERE (bill.total_received < bill.bill_total) AND bill.bill_datetime >= ? AND bill.bill_datetime <= ? ";
           if(!IncludeAll){
               strQuery += " AND bill.customer_id = ?";
           }
           DBConnect getPayableList = new DBConnect();
           
           try{
//               Timestamp tstamp = new Timestamp(00-00-00);
               getPayableList.initConnection();
               stmtget = getPayableList.conn.prepareStatement(strQuery);
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
                   BigDecimal rate = rsGet.getBigDecimal("bill_total").setScale(2, RoundingMode.HALF_UP).subtract(rsGet.getBigDecimal("total_received"));
                //   System.out.println(rate);
                   
                   Object[] row = new Object[]{rsGet.getString("customer_id"),rsGet.getString("customer_name"),rsGet.getString("bill_id"),rsGet.getBigDecimal("bill_total").setScale(2, RoundingMode.HALF_UP),rate.setScale(2, RoundingMode.HALF_UP),rsGet.getDate("bill_datetime")};
                   data.add(row);
           }
               finaldata = data.toArray(new Object[data.size()][]);
           }
           catch(SQLException se){
               JOptionPane.showMessageDialog(null, se+"From getReceiveableList");
           }
           finally{
               getPayableList.closeConnection();
           }
           return new DefaultTableModel(finaldata,ColumnNames){
             @Override
             public boolean isCellEditable(int row, int col){
                 return false;
             }  
           };
          
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
     public String[] returnCustomerName(Object[][] obj){
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
