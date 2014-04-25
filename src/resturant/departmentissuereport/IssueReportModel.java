/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.departmentissuereport;

import database.DBConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
public class IssueReportModel extends DBConnect {
    public Object Itemdata[][] = null;
    
    
    
     public Object[][] getResepectiveDepartmentItemInfo(int departmentid){
          PreparedStatement stmtItemInfo;
          ResultSet rsResult;
//       String strQuery = "SELECT centerstore_stock.item_id,centerstore_stock.item_name,centerstore_stock.unit_id,item_unit.unit_name,item_unit.unit_relative_quantity,item_unit.unit_type,centerstore_stock.total_qty,centerstore_stock.item_expiry_date FROM centerstore_stock,item_unit WHERE centerstore_stock.unit_id = item_unit.unit_id";

         String strQuery = "SELECT department_store_stock.department_item_id,centerstore_stock.item_name FROM department_store_stock  INNER JOIN  centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id  WHERE department_id = ?";
       DBConnect getitem = new DBConnect();
       try{
           getitem.initConnection();
          
           stmtItemInfo = getitem.conn.prepareStatement(strQuery,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
          stmtItemInfo.setInt(1, departmentid);
           rsResult = stmtItemInfo.executeQuery();
           /*
            * calling funtion from function package for returning the data value
            */
           Itemdata =returnData(rsResult);
         //  returnItemName(returnData(rsResult));
         //  JOptionPane.showMessageDialog(null,Itemdata);
         //  returnItemName(Itemdata);
          
       }
       catch(SQLException se){
           JOptionPane.showMessageDialog(null, se+"from getItemInfoForIssue ");
       }
       return Itemdata;
       
   }
      public Object[][] getItemInfo(){
          PreparedStatement stmtItemInfo;
          ResultSet rsResult;
          ArrayList<Object[]> data = new ArrayList<>();
//       String strQuery = "SELECT centerstore_stock.item_id,centerstore_stock.item_name,centerstore_stock.unit_id,item_unit.unit_name,item_unit.unit_relative_quantity,item_unit.unit_type,centerstore_stock.total_qty,centerstore_stock.item_expiry_date FROM centerstore_stock,item_unit WHERE centerstore_stock.unit_id = item_unit.unit_id";
      String strQuery = "SELECT department_store_stock.department_item_id,centerstore_stock.item_name,department_info.department_name FROM department_store_stock  INNER JOIN  centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id  INNER JOIN department_info ON department_store_stock.department_id = department_info.department_id ";
       DBConnect getitem = new DBConnect();
       try{
           getitem.initConnection();
          
           stmtItemInfo = getitem.conn.prepareStatement(strQuery,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
           rsResult = stmtItemInfo.executeQuery();
           /*
            * calling funtion from function package for returning the data value
            */
//           Itemdata =returnData(rsResult);
           while(rsResult.next()){
//                 System.out.println("wala");
               String da = rsResult.getString("item_name")+"("+rsResult.getString("department_name")+")";
               Object[] row = new Object[]{ rsResult.getInt("department_item_id"),da};
               data.add(row);
//               System.out.println("wala");
           }
           Itemdata =data.toArray(new Object[data.size()][]);
         //  returnItemName(returnData(rsResult));
         //  JOptionPane.showMessageDialog(null,Itemdata);
         //  returnItemName(Itemdata);
//           for(Object[]item:Itemdata){
//               System.out.println(item[1]);
//           }
          
       }
       catch(SQLException se){
           JOptionPane.showMessageDialog(null, se+"from getItemInfo ");
       }
       catch(Exception e){
         JOptionPane.showMessageDialog(null, e+"from getItemInfo ");  
       } 
       return Itemdata;
       
   }
       public Object[][] getCategoryInfoForIssue(){
          PreparedStatement stmtItemInfo;
          ResultSet rsResult;
       String strQuery = "SELECT centerstore_stock.category_id,item_sub_category.sub_category_name FROM centerstore_stock,item_sub_category WHERE centerstore_stock.category_id = item_sub_category.sub_category_id";
      
       DBConnect getitem = new DBConnect();
       try{
           getitem.initConnection();
          
           stmtItemInfo = getitem.conn.prepareStatement(strQuery,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
           rsResult = stmtItemInfo.executeQuery();
           /*
            * calling funtion from function package for returning the data value
            */
           Itemdata =returnData(rsResult);
         //  returnItemName(returnData(rsResult));
         //  JOptionPane.showMessageDialog(null,Itemdata);
         //  returnItemName(Itemdata);
          
       }
       catch(SQLException se){
           JOptionPane.showMessageDialog(null, se+"from getCategoryInfoForIssue ");
       }
       return Itemdata;
       
   }
   /*
    * this return the array of the itemname from the two dimensional array from get itemintoforissue
    */
   public String[] returnItemName(Object data[][]){
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
      public DefaultTableModel getIssueList(String[] itemdata,Date[] date,boolean allstatus){
          PreparedStatement stmtIssueInfo;
          ResultSet rsResult;
          String ColumnNames[] = null;
       int colcount;
       int rowcount ;
      // String[] ColumnNames = { "Issue Id", "Item Name", "Issue Quantity","item BaseUnit","Issue From", "Issue To","Issue Date"};
       String strQuery = "SELECT issue.issue_id,issue.item_id,centerstore_stock.item_name,issue.quantity,issue.unit_id,item_unit.unit_name,issue.issue_from,issue.issue_to,issue.issue_date,item_unit.unit_relative_quantity,item_unit.unit_type FROM issue  INNER JOIN centerstore_stock ON issue.item_id = centerstore_stock.item_id INNER JOIN  item_unit ON  issue.unit_id = item_unit.unit_id  WHERE ";
       if(!allstatus){
       strQuery += "issue.item_id = ? AND ";
       }
       strQuery += " issue.issue_date >= ? And issue.issue_date <= ? ORDER BY issue.issue_date desc";
       if(!allstatus){
            String ColNames[] = {"Item Id","Item Name","Issue Quantity","Item BaseUnit"/*,"Issue From","Issue To"*/,"Issue Date","Total Qty"};
            ColumnNames = ColNames;
    
       }
       else{
             String ColNames[] =  new String[]{"Item Id","Item Name","Issue Quantity","Item BaseUnit"/*,"Issue From","Issue To",*/,"Issue Date"};
      ColumnNames = ColNames;
       }
       
      //Object[][] data = null;
     //  List<Object[]> data = new ArrayList<Object[]>();
   ArrayList<Object[]> data = new ArrayList<Object[]>();
    Object[][] finalData =null;
       DBConnect getissue = new DBConnect();
       try{
           getissue.initConnection();
           stmtIssueInfo = getissue.conn.prepareStatement(strQuery);
           if(!allstatus){
           stmtIssueInfo.setString(1, itemdata[0]);
           stmtIssueInfo.setTimestamp(2,new Timestamp(date[0].getTime()) );
           stmtIssueInfo.setTimestamp(3,new Timestamp(date[1].getTime()));
           }
           else{
             stmtIssueInfo.setTimestamp(1,new Timestamp(date[0].getTime()) );
           stmtIssueInfo.setTimestamp(2,new Timestamp(date[1].getTime()));   
           }
           rsResult = stmtIssueInfo.executeQuery();
           
           ResultSetMetaData metadata = rsResult.getMetaData();
           colcount = metadata.getColumnCount();
            
           while(rsResult.next()){
               if(!allstatus){
                     Object qt = rsResult.getBigDecimal("unit_relative_quantity").multiply(rsResult.getBigDecimal("quantity")) + rsResult.getString("unit_type");
                   //  System.out.println(qt.toString().replaceAll("[^0-9,.]",""));
                    Object[] row = new Object[]{rsResult.getString("item_id"),rsResult.getString("item_name"),rsResult.getFloat("quantity"),rsResult.getString("unit_name"),/*rsResult.getString("issue_from"),rsResult.getString("issue_to"),*/rsResult.getDate("issue_date"),qt};
                     data.add(row);
             
               }
               else{
                 
                     
                      Object[] row = new Object[]{rsResult.getString("item_id"),rsResult.getString("item_name"),rsResult.getFloat("quantity"),rsResult.getString("unit_name")/*,rsResult.getString("issue_from"),rsResult.getString("issue_to")*/,rsResult.getDate("issue_date")};
              /* for(int i=0;i<colcount;i++){
                   row[i] = rsResult.getObject(i+1);
               }*/
                
               data.add(row);
               }
               
              // data,rsResult.getBigDecimal("unit_relative_quantity").multiply(rsResult.getBigDecimal("quantity")
              
           }
            finalData = data.toArray(new Object[data.size()][]);
           
       }
       catch(SQLException se){
           JOptionPane.showMessageDialog(null, se+"from get IssueList");
       }
//       catch(Exception e){
//           JOptionPane.showMessageDialog(null, e+"form get IssueList");
//       }
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
       public DefaultTableModel getIssueReturnList(String[] itemdata,Date[] date,boolean allstatus){
          PreparedStatement stmtIssueInfo;
          ResultSet rsResult;
       int colcount;
       int rowcount ;
      // String[] ColumnNames = { "Issue Id", "Item Name", "Issue Quantity","item BaseUnit","Issue From", "Issue To","Issue Date"};
       String strQuery = "SELECT issue_return.issue_return_id,issue.issue_id,centerstore_stock.item_id,centerstore_stock.item_name,issue_return.quantity,issue_return.unit_id,item_unit.unit_name,issue_return.return_reason,issue_return.return_date FROM issue_return INNER JOIN issue ON issue_return.issue_id = issue.issue_id  INNER JOIN centerstore_stock ON issue.item_id = centerstore_stock.item_id INNER JOIN  item_unit ON  issue.unit_id = item_unit.unit_id  WHERE ";
       if(!allstatus){
       strQuery += "issue.item_id = ? AND ";
       }
       strQuery += " issue_return.return_date >= ? And issue_return.return_date <= ? ORDER BY issue_return.return_date desc";
       String ColumnNames[] = {"Item Id","Item Name","Issue Quantity","Item BaseUnit","Return Reason","Issue Date"};
      //Object[][] data = null;
     //  List<Object[]> data = new ArrayList<Object[]>();
   ArrayList<Object[]> data = new ArrayList<Object[]>();
    Object[][] finalData =null;
       DBConnect getissue = new DBConnect();
       try{
           getissue.initConnection();
           stmtIssueInfo = getissue.conn.prepareStatement(strQuery);
           if(!allstatus){
           stmtIssueInfo.setString(1, itemdata[0]);
           stmtIssueInfo.setTimestamp(2,new Timestamp(date[0].getTime()) );
           stmtIssueInfo.setTimestamp(3,new Timestamp(date[1].getTime()));
           }
           else{
             stmtIssueInfo.setTimestamp(1,new Timestamp(date[0].getTime()) );
           stmtIssueInfo.setTimestamp(2,new Timestamp(date[1].getTime()));   
           }
           rsResult = stmtIssueInfo.executeQuery();
           
           ResultSetMetaData metadata = rsResult.getMetaData();
           colcount = metadata.getColumnCount();
            
           while(rsResult.next()){
              Object[] row = new Object[]{rsResult.getString("item_id"),rsResult.getString("item_name"),rsResult.getFloat("quantity"),rsResult.getString("unit_name"),rsResult.getString("return_reason"),rsResult.getDate("return_date")};
              /* for(int i=0;i<colcount;i++){
                   row[i] = rsResult.getObject(i+1);
               }*/
                
               data.add(row);
               
              // data
              
           }
            finalData = data.toArray(new Object[data.size()][]);
           
       }
       catch(SQLException se){
           JOptionPane.showMessageDialog(null, se+"from get IssueReturnList");
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null, e+"form get IssueReturnList");
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
         public Object[][] getOtherStoreName(){
    PreparedStatement stmt = null;
      ResultSet rs;
      ArrayList<Object[]> data = new ArrayList<>();
      try{
          initConnection();
          stmt = conn.prepareStatement("SELECT department_id,department_name from department_info");
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
