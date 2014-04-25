/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.servicechargereport;

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
public class ServiceChargeReportModel extends DBConnect {
     public DefaultTableModel getServiceChargeList(Date[] date){
          PreparedStatement  stmtget;
          ResultSet rsGet;
          ArrayList<Object[]> data = new ArrayList<Object[]>();
           Object[][] finaldata = null;
           String[] ColumnNames = new String[]{"Bill Id","Amount","SVC Amount","SVC Percentage(%)"}; 
           String strQuery = "SELECT bill.bill_id,bill.item_total_amount,bill.service_charge FROM bill WHERE service_charge >= 0 AND bill.bill_datetime >= ? AND bill.bill_datetime <= ?";
          
           DBConnect getComplimetaryList = new DBConnect();
           
           try{
               getComplimetaryList.initConnection();
               stmtget = getComplimetaryList.conn.prepareStatement(strQuery);
            // System.out.println(new Timestamp(date[0].getTime()) +"\n"+ new Timestamp(date[1].getTime()));
              stmtget.setTimestamp(1, new Timestamp(date[0].getTime()));
              stmtget.setTimestamp(2, new Timestamp(date[1].getTime()));
              
//               stmtget.setTimestamp(1, new Timestamp(date[0].getTime()));
//              stmtget.setTimestamp(2, new Timestamp(date[1].getTime()));
              rsGet =  stmtget.executeQuery();
               while(rsGet.next()){
                   BigDecimal SVCPer = rsGet.getBigDecimal("service_charge").divide(rsGet.getBigDecimal("item_total_amount")).multiply(BigDecimal.valueOf(100));
                 Object[] row = new Object[]{rsGet.getInt("bill_id"),rsGet.getBigDecimal("item_total_amount").setScale(2, RoundingMode.HALF_UP),rsGet.getBigDecimal("service_charge").setScale(2, RoundingMode.HALF_UP),SVCPer};
//                 for(Object rw:row){
//                     System.out.println(rw);
//                 }
                 data.add(row);
           }
               finaldata = data.toArray(new Object[data.size()][]);
           }
           catch(SQLException se){
               JOptionPane.showMessageDialog(null, se+"From getServiceChargeList");
           }
           finally{
               getComplimetaryList.closeConnection();
           }
           return new DefaultTableModel(finaldata,ColumnNames){
             @Override
             public boolean isCellEditable(int row, int col){
                 return false;
             }  
           };
          
      }
      public String[] returnMenuName(Object data[][]){
       String[] strName = new String[data.length];
      /*
       * SELECT centerstore_stock.item_id,centerstore_stock.item_name,centerstore_stock.unit_id,item_unit.unit_name,centerstore_stock.quantity,centerstore_stock.item_expiry_date 
       * FROM centerstore_stock,item_unit 
       * WHERE centerstore_stock.unit_id = item_unit.unit_id
       */
       //this give a string array of the itemname since itemname lies on 1 postion
       for(int i =0;i<data.length; i++){
         // System.out.println(data[i][1]);
           strName[i] = data[i][1].toString();
           
       }
       /*for(Object[] test:data)
       {
           for(Object te:test){
           System.out.print(te+"\t");
           }
           System.out.println("\n");
       }*/
       
       
       return strName;
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
