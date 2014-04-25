/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.issuestockreport;

import database.DBConnect;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SUSHIL
 */
public class IssueStockReportModel extends DBConnect {
    public Object[][] UnitInfo =  null;
    
    
    
    
     
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
    public Object[][] getUnitInfo(String unittype){
      PreparedStatement getrelqty;
      ResultSet getResultSet;
      //float Qty = 0;
      Object[] UnitName;
      String strgetUnitRelativeQuantity = "select unit_id,unit_name,unit_relative_quantity,unit_type from item_unit WHERE unit_type = ? ";
       ArrayList<Object[]>  data= new ArrayList<Object[]>();
      DBConnect getUnit = new DBConnect();
      try{
          getUnit.initConnection();
          getrelqty = getUnit.conn.prepareStatement(strgetUnitRelativeQuantity);
          getrelqty.setString(1,unittype);
//          System.out.println(unittype);
          getResultSet = getrelqty.executeQuery();
         
          while(getResultSet.next()){
             
           Object st[] = new Object[]{getResultSet.getObject("unit_id"),getResultSet.getObject("unit_name"),getResultSet.getObject("unit_relative_quantity"),getResultSet.getObject("unit_type")};
        data.add(st);
          }
          
          UnitInfo = data.toArray(new Object[data.size()][]);
//          for(Object[] da:UnitInfo){
//              for(Object d:da){
//                  System.out.print(d);
//              }
//              System.out.println();
//          }
          
         
      }
      catch(SQLException e){
          JOptionPane.showMessageDialog(null, e+"form getUnitINfo");
      }
      finally{
          getUnit.closeConnection();
      }
      return UnitInfo;
  }
    public String[] returnUnitName(Object[][] data){
       String[] unitname = new String[data.length];
        for(int row =0;row<data.length;row++){
           unitname[row] = data[row][1].toString();
        }
        return unitname;
    }
    
     
       public DefaultTableModel getSalesList(int departmentid){
            PreparedStatement stmtIssueInfo;
          ResultSet rsResult;
          String ColumnNames[] = new String[]{"Department Item Id","Item Name","Total Quantity","Relative Quantity"};
//            String strQuery = "SELECT resturant_store.item_id,centerstore_stock.item_name,resturant_store.total_qty,item_unit.unit_name,item_unit.unit_type,item_unit.unit_relative_quantity FROM resturant_store INNER JOIN centerstore_stock ON resturant_store.item_id = centerstore_stock.item_id INNER JOIN item_unit ON resturant_store.unit_id = item_unit.unit_id   ";
            String strQuery = "SELECT department_store_stock.department_item_id,centerstore_stock.item_name,department_store_stock.total_qty,item_unit.unit_name,item_unit.unit_type,item_unit.unit_relative_quantity FROM department_store_stock INNER JOIN centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id INNER JOIN item_unit ON department_store_stock.unit_id = item_unit.unit_id WHERE department_id = ?   ";
           
       ArrayList<Object[]> data = new ArrayList<Object[]>();
    Object[][] finalData =null;
       DBConnect getissue = new DBConnect();
       try{
           getissue.initConnection();
           
           stmtIssueInfo = getissue.conn.prepareStatement(strQuery);
        stmtIssueInfo.setInt(1, departmentid);
          rsResult =  stmtIssueInfo.executeQuery();
            while(rsResult.next()){
//               String st = rsResult.getBoolean("payment_type")== true?"Cash":"Credit";
                Object totalQuantity = rsResult.getBigDecimal("total_qty").setScale(3, RoundingMode.HALF_UP)+" "+rsResult.getString("unit_type");
                Object relativeQuantity = rsResult.getBigDecimal("total_qty").divide(rsResult.getBigDecimal("unit_relative_quantity"),3, RoundingMode.HALF_UP)+" "+rsResult.getString("unit_name");
                Object[] row = new Object[]{rsResult.getString("department_item_id"),rsResult.getString("item_name"),totalQuantity,relativeQuantity};
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
//          @Override
//          public Class<?> getColumnClass(int columnIndex){
//              Class clazz = String.class;
//              switch(columnIndex){
//                  case 6:
//                      
//                      clazz = Boolean.class;
//              }
//              return clazz;
//          }
                  
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
    
}
