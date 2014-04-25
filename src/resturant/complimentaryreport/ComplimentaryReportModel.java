/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.complimentaryreport;

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
public class ComplimentaryReportModel extends DBConnect {
     public  Object[][] MenuInfo;
  
//    public ComplimentaryReportModel(ComplimentaryReportModel model,ComplimentaryReportView view){
//      
//    }
//   
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
      public DefaultTableModel getComplimentaryList(String MenuId,Date[] date,Boolean IncludeAll){
          PreparedStatement  stmtget;
          ResultSet rsGet;
          ArrayList<Object[]> data = new ArrayList<Object[]>();
           Object[][] finaldata = null;
           String[] ColumnNames = new String[]{"Menu Id","Menu Name","Bill Id","Quantity","Base Unit Name","Rate","Date","Total Amount"}; 
           String strQuery = "SELECT bill_item_info.menu_id,menu.menu_name,bill_item_info.bill_id,bill_item_info.quantity,item_unit.unit_name,bill.bill_datetime,menu.retail_price FROM bill_item_info INNER JOIN bill ON bill_item_info.bill_id = bill.bill_id INNER JOIN menu ON bill_item_info.menu_id = menu.menu_id INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id  WHERE bill_item_info.complimentary_type = 1 AND (bill.bill_datetime >= ?) AND (bill.bill_datetime <= ?)  ";
           if(!IncludeAll){
               strQuery += " AND bill_item_info.menu_id = ?";
           }
           DBConnect getComplimetaryList = new DBConnect();
           
           try{
//               Timestamp tstamp = new Timestamp(00-00-00);
               getComplimetaryList.initConnection();
               stmtget = getComplimetaryList.conn.prepareStatement(strQuery);
               stmtget.setTimestamp(1, new Timestamp(date[0].getTime()));
               stmtget.setTimestamp(2, new Timestamp(date[1].getTime()));
//               stmtget.setTimestamp(1,new Timestamp(date[0].getTime()) , Calendar.getInstance(TimeZone.getTimeZone("UTC")));
//                stmtget.setTimestamp(2,new Timestamp(date[1].getTime()) , Calendar.getInstance(TimeZone.getTimeZone("UTC")));
//             stmtget.setTimestamp(1, tstamp.valueOf(date[0].toString()));
//             stmtget.setTimestamp(2, tstamp.valueOf(date[1].toString()));
//               stmtget.setDate(1,(Date) date[0]);
               if(!IncludeAll){
                   stmtget.setString(3, MenuId);
               }
              rsGet =  stmtget.executeQuery();
               while(rsGet.next()){
                   BigDecimal rate = rsGet.getBigDecimal("retail_price").setScale(2, RoundingMode.HALF_UP).multiply(rsGet.getBigDecimal("quantity"));
                //   System.out.println(rate);
                   
                   Object[] row = new Object[]{rsGet.getString("menu_id"),rsGet.getString("menu_name"),rsGet.getString("bill_id"),rsGet.getBigDecimal("quantity"),rsGet.getString("unit_name"),rsGet.getBigDecimal("retail_price").setScale(2, RoundingMode.HALF_UP),rsGet.getDate("bill_datetime"),rate.setScale(2, RoundingMode.HALF_UP)};
                   data.add(row);
           }
               finaldata = data.toArray(new Object[data.size()][]);
           }
           catch(SQLException se){
               JOptionPane.showMessageDialog(null, se+"From getComplimentaryList");
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
   public String[] returnMenuName(Object[][] obj){
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
