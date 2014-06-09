/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.wastagereport;

import database.DBConnect;
import function.function;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SUSHIL
 */
public class WastageReportModel extends DBConnect {
    public Object MenuInfo[][] = null;
    
    
    
    
      //retreiving the itemname in the respective department
       public Object[][] getItemInfoForMenu(int storeid){
       String strQuery = "SELECT department_store_stock.department_item_id,centerstore_stock.item_name,department_store_stock.unit_id,item_unit.unit_name,item_unit.unit_relative_quantity,item_unit.unit_type FROM department_store_stock INNER JOIN centerstore_stock  ON department_store_stock.item_id = centerstore_stock.item_id INNER JOIN item_unit ON department_store_stock.unit_id = item_unit.unit_id  WHERE department_store_stock.department_id = ?";
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
   /*
    * this return the array of the itemname from the two dimensional array from get itemintoforissue
    */
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
   public  Date ChangeDate(Date dt,int changeday){
         Calendar cal = Calendar.getInstance();
       cal.setTime(dt);
       cal.add(Calendar.DATE,changeday);
      
       return cal.getTime();
   }
   public Date ChangeMonthBefore(int year,int month,int day){
       Calendar cal = Calendar.getInstance();
//      if(change>= 0){
//          month = month + change;
//      }
//      else{
//          month = month - Math.abs(change);
//      }
       cal.set(year, month-1, day);
       cal.add(Calendar.MONTH, 1);
       cal.set(Calendar.DAY_OF_MONTH, 1);
       cal.add(Calendar.DATE, -1);
       return cal.getTime();
      
   }
    public Date ChangeMonthAfther(int year,int month,int day){
       Calendar cal = Calendar.getInstance();
//      if(change>= 0){
//          month = month + change;
//      }
//      else{
//          month = month - Math.abs(change);
//      }
       cal.set(year, month+1, day);
       
       return cal.getTime();
      
   }
   public String[] returnItemBaseUnit(Object data[][]){
       String[] strName = new String[data.length];
      /*
       *
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
    public Object[][] getUnitInfo(String UnitId){
      PreparedStatement getrelqty;
      ResultSet getResultSet;
      //float Qty = 0;
      Object[] UnitName;
      String strgetUnitRelativeQuantity = "select unit_id,unit_name,unit_relative_quantity from item_unit where unit_type = (select unit_type from item_unit where unit_id = ?)";
       ArrayList<Object[]>  data= new ArrayList<Object[]>();
      DBConnect getUnit = new DBConnect();
      try{
          getUnit.initConnection();
          getrelqty = getUnit.conn.prepareStatement(strgetUnitRelativeQuantity);
         getrelqty.setString(1, UnitId);
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
    
     
       public DefaultTableModel getSalesList(Date[] date){
            PreparedStatement stmtIssueInfo;
          ResultSet rsResult;
          String ColumnNames[] = new String[]{"Bill No"," Item Amount","SVC","VAT","Discount"," Bill Total"," Received Amount","Payment Type"};
            String strQuery = "SELECT bill.bill_id,bill.item_total_amount,bill.service_charge,bill.vat,bill.bill_discount,bill_total,total_received,payment_type FROM bill WHERE  bill.void != 1 AND bill_datetime > ? AND bill_datetime < ? ";
       ArrayList<Object[]> data = new ArrayList<Object[]>();
    Object[][] finalData =null;
       DBConnect getissue = new DBConnect();
       try{
           getissue.initConnection();
           stmtIssueInfo = getissue.conn.prepareStatement(strQuery);
        //   System.out.println(new Timestamp(date[0].getTime())+"\n"+new Timestamp(date[1].getTime()));
           stmtIssueInfo.setTimestamp(1,new Timestamp(date[0].getTime()) );
            stmtIssueInfo.setTimestamp(2,new Timestamp(date[1].getTime()) );
          rsResult =  stmtIssueInfo.executeQuery();
            while(rsResult.next()){
               String st = rsResult.getBoolean("payment_type")== true?"Cash":"Credit";
           
                Object[] row = new Object[]{rsResult.getInt("bill_id"),rsResult.getBigDecimal("item_total_amount").setScale(2, RoundingMode.HALF_UP),rsResult.getBigDecimal("service_charge").setScale(2, RoundingMode.HALF_UP),rsResult.getBigDecimal("vat").setScale(2, RoundingMode.HALF_UP),rsResult.getBigDecimal("bill_discount").setScale(2, RoundingMode.HALF_UP),rsResult.getBigDecimal("bill_total").setScale(2, RoundingMode.HALF_UP),rsResult.getBigDecimal("total_received").setScale(2, RoundingMode.HALF_UP),st};
                data.add(row);
            }
             finalData = data.toArray(new Object[data.size()][]);
           
       }
       catch(SQLException se){
           JOptionPane.showMessageDialog(null, se+"From getSaleList");
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
                  
       };   
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
        //get menuid by menuname
         public int getMenuIdByMenuName(String menuname){
        PreparedStatement stmt = null;
        ResultSet rs;
        int menuid = 0;
      
//      ArrayList<Object[]> data = new ArrayList<>();
      try{
          initConnection();
          stmt = conn.prepareStatement("SELECT menu.menu_id FROM menu WHERE menu.menu_name = ?");
         stmt.setString(1, menuname);
          rs = stmt.executeQuery();
          rs.next();
          menuid = rs.getInt(1);
           
          
          
      }
      catch(SQLException se ){
          JOptionPane.showMessageDialog(null, se+"from getMenuIdByMenuName "+getClass().getName());
      }
      finally{
          closeConnection();
      }
      return menuid;
  }
         //get itemid by itemname
         public int getItemIdByItemName(String menuname){
        PreparedStatement stmt = null;
        ResultSet rs;
        int menuid = 0;
      
//      ArrayList<Object[]> data = new ArrayList<>();
      try{
          initConnection();
          stmt = conn.prepareStatement("SELECT centerstore_stock.item_id FROM centerstore_stock WHERE item_name =  ?");
         stmt.setString(1, menuname);
          rs = stmt.executeQuery();
          rs.next();
          menuid = rs.getInt(1);
           
          
          
      }
      catch(SQLException se ){
          JOptionPane.showMessageDialog(null, se+"from getItemIdByItemName "+getClass().getName());
      }
      finally{
          closeConnection();
      }
      return menuid;
  }
    
}
