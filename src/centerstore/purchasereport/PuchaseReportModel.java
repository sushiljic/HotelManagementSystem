/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.purchasereport;

import database.DBConnect;
import function.function;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
public class PuchaseReportModel extends function {
    public Object Itemdata[][] = null;
    
    
    
     public Object[][] getItemInfoForIssue(){
          PreparedStatement stmtItemInfo;
          ResultSet rsResult;
       String strQuery = "SELECT centerstore_stock.item_id,centerstore_stock.item_name,centerstore_stock.unit_id,item_unit.unit_name,item_unit.unit_relative_quantity,item_unit.unit_type,centerstore_stock.total_qty,centerstore_stock.item_expiry_date FROM centerstore_stock,item_unit WHERE centerstore_stock.unit_id = item_unit.unit_id";
      
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
           JOptionPane.showMessageDialog(null, se+"from getItemInfoForIssue ");
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
      public DefaultTableModel getPurchaseList(String[] itemdata,Date[] date,boolean allstatus){
          PreparedStatement stmtIssueInfo;
          ResultSet rsResult;
          String ColumnNames[] = null;
       int colcount;
       int rowcount ;
      // String[] ColumnNames = { "Issue Id", "Item Name", "Issue Quantity","item BaseUnit","Issue From", "Issue To","Issue Date"};
       String strQuery = "SELECT  purchase.item_id,centerstore_stock.item_name,purchase.quantity,purchase.unit_id,item_unit.unit_name,item_unit.unit_relative_quantity,item_unit.unit_type,purchase.buy_rate,purchase.distributor_id,distributor.distributor_name,purchase.purchase_date FROM purchase INNER JOIN item_unit ON purchase.unit_id = item_unit.unit_id INNER JOIN  distributor ON purchase.distributor_id = distributor.distributor_id INNER JOIN  centerstore_stock ON purchase.item_id = centerstore_stock.item_id WHERE  ";
       if(!allstatus){
       strQuery += " purchase.item_id = ? AND ";
       }
       strQuery += " purchase.purchase_date >= ? And purchase.purchase_date <= ? ORDER BY purchase.purchase_date desc";
       if(!allstatus){
            String ColNames[] = {"Item Id","Item Name","Purchase Quantity","Item BaseUnit","Rate","Total Amount","Distributor Name","Purchase Date","Total Qty"};
            ColumnNames = ColNames;
    
       }
       else{
             String ColNames[] =  new String[]{"Item Id","Item Name","Purchase Quantity","Item BaseUnit","Rate","Total Amount","Distributor Name","Purchase Date"};
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
                      BigDecimal total = rsResult.getBigDecimal("buy_rate").multiply(rsResult.getBigDecimal("quantity"));
                   //  System.out.println(qt.toString().replaceAll("[^0-9,.]",""));
                    Object[] row = new Object[]{rsResult.getString("item_id"),rsResult.getString("item_name"),rsResult.getBigDecimal("quantity").setScale(3, RoundingMode.HALF_UP),rsResult.getString("unit_name"),rsResult.getBigDecimal("buy_rate").setScale(2, RoundingMode.HALF_UP),total.setScale(2, RoundingMode.HALF_UP),rsResult.getString("distributor_name"),rsResult.getDate("purchase_date"),qt};
                     data.add(row);
             
               }
               else{
                 
                      BigDecimal total = rsResult.getBigDecimal("buy_rate").multiply(rsResult.getBigDecimal("quantity"));
                      Object[] row = new Object[]{rsResult.getString("item_id"),rsResult.getString("item_name"),rsResult.getBigDecimal("quantity").setScale(3, RoundingMode.HALF_UP),rsResult.getString("unit_name"),rsResult.getBigDecimal("buy_rate").setScale(2, RoundingMode.HALF_UP),total.setScale(2, RoundingMode.HALF_UP),rsResult.getString("distributor_name"),rsResult.getDate("purchase_date")};
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
           JOptionPane.showMessageDialog(null, se+"from get PurchaseList");
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
       public DefaultTableModel getPurchaseReturnList(String[] itemdata,Date[] date,boolean allstatus){
          PreparedStatement stmtIssueInfo;
          ResultSet rsResult;
       int colcount;
       int rowcount ;
      // String[] ColumnNames = { "Issue Id", "Item Name", "Issue Quantity","item BaseUnit","Issue From", "Issue To","Issue Date"};
       String strQuery = "SELECT purchase_return.purchase_return_id,purchase.purchase_id,centerstore_stock.item_id,centerstore_stock.item_name,purchase_return.quantity,purchase_return.unit_id,item_unit.unit_name,purchase_return.return_reason,purchase_return.return_date,distributor.distributor_name FROM purchase_return INNER JOIN purchase ON purchase_return.purchase_id = purchase.purchase_id  INNER JOIN centerstore_stock ON purchase.item_id = centerstore_stock.item_id INNER JOIN  item_unit ON  purchase.unit_id = item_unit.unit_id INNER JOIN distributor ON purchase_return.distributor_id = distributor.distributor_id  WHERE ";
       if(!allstatus){
       strQuery += "purchase.item_id = ? AND ";
       }
       strQuery += " purchase_return.return_date >= ? And purchase_return.return_date <= ? ORDER BY purchase_return.return_date desc";
       String ColumnNames[] = {"Item Id","Item Name","Purchase Quantity","Item BaseUnit","Return Reason","Issue Date","Distributor Name"};
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
              Object[] row = new Object[]{rsResult.getString("item_id"),rsResult.getString("item_name"),rsResult.getFloat("quantity"),rsResult.getString("unit_name"),rsResult.getString("return_reason"),rsResult.getDate("return_date"),rsResult.getString("distributor_name")};
              /* for(int i=0;i<colcount;i++){
                   row[i] = rsResult.getObject(i+1);
               }*/
                
               data.add(row);
               
              // data
              
           }
            finalData = data.toArray(new Object[data.size()][]);
           
       }
       catch(SQLException se){
           JOptionPane.showMessageDialog(null, se+"from get PurchaseReturnList");
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
    
}
