/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.purchasereturn;

import database.DBConnect;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
//import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author SUSHIL
 */
public class PurchaseReturnModel {
    private PreparedStatement stmtReturn;
    private PreparedStatement stmtItemList;
    private PreparedStatement stmtItemQuery;
    private PreparedStatement stmtPurchase;
    ResultSet rsReturn;
    ResultSet rsItemList;
     Object[][] fullfinaldata = null;
    public void  executeReturn(String[] returnData,String UnitRelativeQuantity){
        String strQuery;
        String strItemCenterStore;
        String strPurchase;
      //  BigDecimal actualQuantity;
         String UnitId;
        BigDecimal  prevQuantity;
        BigDecimal  netQuantity = null;
        BigDecimal prevAmount;
        BigDecimal netAmount = null;
        String PurchaseId;
        DBConnect eReturn =  new DBConnect();
        /*
         * list of array
         *
          *  PurchaseReturnData[0] =getRItemId();
    PurchaseReturnData[1] = getRItemName();
    
   // PurchaseReturnData[2] = getRDistributorName();
    PurchaseReturnData[2] = getStockQuantity();
    PurchaseReturnData[3] = getReturnQuantity();
    PurchaseReturnData[4] = getBaseUnit();
    PurchaseReturnData[5] = getReturnReason();
    PurchaseReturnData[6] = getRDistributorId();
    PurchaseReturnData[7] = getAmount();
    PurchaseReturnData[8] = getPurchaseId();
    PurchaseReturnData[9] = getPurchaseQuantity();
    PurchaseReturnData[10] = getPurchaseAmount();
            * date is retreive from here 
         */
       
        UnitId = getUnitId(returnData[4]);
        BigDecimal UnitRelQuantity =  new BigDecimal(UnitRelativeQuantity);
      //  prevQuantity = new BigDecimal(getQuantityFromCenterStore(returnData[0]));
        
        strQuery = "INSERT INTO purchase_return(purchase_id,quantity,unit_id,return_reason,distributor_id,return_date,amount) VALUES(?,?,?,?,?,?,?)";
        strItemCenterStore = "UPDATE centerstore_stock SET total_qty = ? WHERE item_id = ?";
        strPurchase = "UPDATE PURCHASE SET  total_amount=? , quantity = ? WHERE purchase_id =?";
        try{
            //for calculating new value of qunatity in stock
          //  Date date = new Date();
            // actualQuantity =  Integer.parseInt(returnData[2]) - Integer.parseInt(returnData[3]);
            netQuantity = new BigDecimal(returnData[2]).setScale(3, RoundingMode.HALF_UP).subtract(new BigDecimal(returnData[3]).setScale(3, RoundingMode.HALF_UP)).multiply(UnitRelQuantity);
          /*
           * calculatin on amount
           */
            
            prevAmount = new BigDecimal(returnData[10]);
          if(returnData[7].isEmpty()){
              returnData[7] = "0";
              
          }
            netAmount = prevAmount.subtract(new BigDecimal(returnData[7]));
        
       
             eReturn.initConnection();
             /*
              * update in purchase_return
              */
            stmtReturn = eReturn.conn.prepareStatement(strQuery);
           eReturn.conn.setAutoCommit(false);
           stmtReturn.setString(1,returnData[8]);
           
           stmtReturn.setBigDecimal(2,new BigDecimal(returnData[3]).setScale(3, RoundingMode.HALF_UP));
          stmtReturn.setString(3, UnitId);
           stmtReturn.setString(4,returnData[5]);
           stmtReturn.setString(5,returnData[6]);
           
           
          // stmtReturn.setDate(6,(java.sql.Date) getdate());
           stmtReturn.setTimestamp(6, new Timestamp(new Date().getTime()));
           stmtReturn.setBigDecimal(7, new BigDecimal(returnData[7]).setScale(2, RoundingMode.HALF_UP));
           
           stmtReturn.executeUpdate();
          // eReturn.conn.commit();
          // eReturn.conn.setAutoCommit(true);
           //for updating into the centerstore-stock
           stmtItemQuery = eReturn.conn.prepareStatement(strItemCenterStore);
           eReturn.conn.setAutoCommit(false);
           stmtItemQuery.setBigDecimal(1,netQuantity);
         //  stmtItemQuery.setBigDecimal(2, netamount);
           stmtItemQuery.setString(2,returnData[0]);
           stmtItemQuery.executeUpdate();
           /*
            *  for updating the purchase table
            * 
            */
           BigDecimal netPurchaseQuantity = new BigDecimal(returnData[9]).setScale(3, RoundingMode.HALF_UP).subtract(new BigDecimal(returnData[3]).setScale(3, RoundingMode.HALF_UP));
           stmtPurchase = eReturn.conn.prepareStatement(strPurchase);
           eReturn.conn.setAutoCommit(false);
           stmtPurchase.setBigDecimal(1,netAmount);
           stmtPurchase.setBigDecimal(2, netPurchaseQuantity);
           stmtPurchase.setString(3, returnData[8]);
           stmtPurchase.executeUpdate();
           /*
            * if all goes well commit
            * */
            eReturn.conn.commit();
           eReturn.conn.setAutoCommit(true);
           JOptionPane.showMessageDialog(null, "Item Return Successfully");
        }
        catch( SQLException ce){
            JOptionPane.showMessageDialog(null, ce+" database error form pruchasereturnmoel");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"fromm purchaseReturnmodel");
        }
        finally{
            eReturn.closeConnection();
        }
    }
    public void cancelReturn(){
        
    }
    public DefaultTableModel  getItemList(){
        int colcount;
     /*   Vector<String> columnNames = new Vector();
        columnNames.add("Item Id");
        columnNames.add("Item Name");
        columnNames.add("Item Quantity");
        columnNames.add("Item Base Unit");
       columnNames.add("Distributor Id");
        columnNames.add("Distributor Name");
       
         Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        */
        String columnNames[] = {"Purchase Id","Item Name","Item Quantity","Item Base Unit","Distributor Name","Amount"};
      ArrayList<Object[]> data = new ArrayList<Object[]>();
      ArrayList<Object[]> fulldata = new ArrayList<Object[]>();
      Object[][] finaldata = null;
     
        String strQuery;
        int test;
        String[] arr;
      //  strQuery = "SELECT item_id,item_name,total_qty,(SELECT unit_name FROM item_unit WHERE item_unit.unit_id = centerstore_stock.unit_id),distributor_id FROM centerstore_stock";
      // strQuery = "SELECT purchase.purchase_id,(SELECT centerstore_stock.item_name FROM centerstore_stock WHERE purchase.item_id = centerstore_stock.item_id),purchase.quantity,(SELECT unit_name FROM item_unit WHERE item_unit.unit_id = purchase.unit_id),(SELECT distributor_name FROM distributor WHERE purchase.distributor_id = distributor.distributor_id),purchase.distributor_id,purchase.item_id  FROM purchase,distributor,centerstore_stock";
        strQuery = "SELECT purchase.purchase_id,centerstore_stock.item_name,purchase.quantity,item_unit.unit_name,distributor.distributor_name,distributor.distributor_id,purchase.item_id,purchase.total_amount FROM purchase,centerstore_stock,item_unit,distributor WHERE purchase.unit_id = item_unit.unit_id AND purchase.item_id = centerstore_stock.item_id AND purchase.distributor_id = distributor.distributor_id ";
        DBConnect preturn = new DBConnect();
        try{
        preturn.initConnection();
        stmtItemList = preturn.conn.prepareStatement(strQuery);
         rsItemList = stmtItemList.executeQuery();
                    //JOptionPane.showMessageDialog(null,"");
       //  ResultSetMetaData metadata = rsItemList.getMetaData();
         
        
       //  colcount = metadata.getColumnCount();
        
         // String arg = metadata.getColumnName(4);
         // System.out.println(arg);
         // return;
    /*
         while(rsItemList.next()){
             Vector<Object> temp = new Vector<Object>();
             Object strDistributorName= null;
            for(int i =1;i<=colcount;i++){
                temp.add(rsItemList.getObject(i));
                //for getting name of distributor from its id
                
               if(metadata.getColumnName(i).equalsIgnoreCase("distributor_id")){
                  //  System.out.println("distributoor detected");
                   try{
                       PreparedStatement stmtDistributorList;
                       ResultSet rsdist;
                       String query;
                       query= "SELECT distributor_name FROM distributor WHERE distributor_id = ? ";
                       
                       stmtDistributorList = preturn.conn.prepareStatement(query);
                       //stmtDistributorList.setString(1,String.valueOf(rsItemList.getInt("distributor_id")));
                       stmtDistributorList.setObject(1,rsItemList.getObject("distributor_id"));
                       rsdist = stmtDistributorList.executeQuery();
                       while(rsdist.next()){
                           strDistributorName = rsdist.getObject("distributor_name");
                       }
                       temp.add(strDistributorName);
                       
                   }
                   catch(SQLException se){
                       JOptionPane.showMessageDialog(null, se+"from disributor");
                   }
                   // temp.add("test");
                  //  continue;
                }
                
              //  test = rsItemList.getInt("distributor_id");
           //  test =  rsItemList.getRowId("distributor_id");
          // test =   Integer.parseInt(temp.elementAt(1).toString());
            //JOptionPane.showMessageDialog(null,temp);
            }
          
          
            data.add(temp);
        }
  */
         while(rsItemList.next()){
             Object[] row = new Object[]{rsItemList.getString("purchase_id"),rsItemList.getString("item_name"),rsItemList.getFloat("quantity"),rsItemList.getString("unit_name"),rsItemList.getString("distributor_name"),rsItemList.getString("total_amount")};
        Object[] full =  new Object[]{rsItemList.getString("purchase_id"),rsItemList.getString("item_name"),rsItemList.getFloat("quantity"),rsItemList.getString("unit_name"),rsItemList.getString("distributor_name"),rsItemList.getString("item_id"),rsItemList.getString("distributor_id")};
             data.add(row);
             fulldata.add(full);
         }
         finaldata = data.toArray(new Object[data.size()][]);
         //it is for retriving the data
         fullfinaldata = fulldata.toArray(new Object[fulldata.size()][]);
        }
        
        catch(SQLException se){
                        JOptionPane.showMessageDialog(null, se+"sql database error");

        }
        catch(Exception e){
                        JOptionPane.showMessageDialog(null, e+"Purchase Return");

        }
        finally{
            preturn.closeConnection();
        }
        return new DefaultTableModel(finaldata,columnNames){
            @Override
            public boolean isCellEditable(int row,int columns){
                //all cells false
                return false;
            }
        };
        
    }
    public DefaultTableModel  getItemListLike(String st){
        int colcount;
     /*   Vector<String> columnNames = new Vector();
        columnNames.add("Item Id");
        columnNames.add("Item Name");
        columnNames.add("Item Quantity");
        columnNames.add("Item Base Unit");
       columnNames.add("Distributor Id");
        columnNames.add("Distributor Name");
       
         Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        */
        String columnNames[] = {"Purchase Id","Item Name","Item Quantity","Item Base Unit","Distributor Name","Amount"};
      ArrayList<Object[]> data = new ArrayList<Object[]>();
      ArrayList<Object[]> fulldata = new ArrayList<Object[]>();
      Object[][] finaldata = null;
      String str = st+"%";
     
        String strQuery;
        int test;
        String[] arr;
      //  strQuery = "SELECT item_id,item_name,total_qty,(SELECT unit_name FROM item_unit WHERE item_unit.unit_id = centerstore_stock.unit_id),distributor_id FROM centerstore_stock";
      // strQuery = "SELECT purchase.purchase_id,(SELECT centerstore_stock.item_name FROM centerstore_stock WHERE purchase.item_id = centerstore_stock.item_id),purchase.quantity,(SELECT unit_name FROM item_unit WHERE item_unit.unit_id = purchase.unit_id),(SELECT distributor_name FROM distributor WHERE purchase.distributor_id = distributor.distributor_id),purchase.distributor_id,purchase.item_id  FROM purchase,distributor,centerstore_stock";
        strQuery = "SELECT purchase.purchase_id,centerstore_stock.item_name,purchase.quantity,item_unit.unit_name,distributor.distributor_name,distributor.distributor_id,purchase.item_id,purchase.total_amount FROM purchase INNER JOIN item_unit on  purchase.unit_id = item_unit.unit_id INNER JOIN centerstore_stock ON purchase.item_id = centerstore_stock.item_id INNER JOIN distributor ON  purchase.distributor_id = distributor.distributor_id WHERE centerstore_stock.item_name LIKE ? ";
        DBConnect preturn = new DBConnect();
        try{
        preturn.initConnection();
        stmtItemList = preturn.conn.prepareStatement(strQuery);
        stmtItemList.setString(1, str);
         rsItemList = stmtItemList.executeQuery();
                    //JOptionPane.showMessageDialog(null,"");
       //  ResultSetMetaData metadata = rsItemList.getMetaData();
         
        
       //  colcount = metadata.getColumnCount();
        
         // String arg = metadata.getColumnName(4);
         // System.out.println(arg);
         // return;
    /*
         while(rsItemList.next()){
             Vector<Object> temp = new Vector<Object>();
             Object strDistributorName= null;
            for(int i =1;i<=colcount;i++){
                temp.add(rsItemList.getObject(i));
                //for getting name of distributor from its id
                
               if(metadata.getColumnName(i).equalsIgnoreCase("distributor_id")){
                  //  System.out.println("distributoor detected");
                   try{
                       PreparedStatement stmtDistributorList;
                       ResultSet rsdist;
                       String query;
                       query= "SELECT distributor_name FROM distributor WHERE distributor_id = ? ";
                       
                       stmtDistributorList = preturn.conn.prepareStatement(query);
                       //stmtDistributorList.setString(1,String.valueOf(rsItemList.getInt("distributor_id")));
                       stmtDistributorList.setObject(1,rsItemList.getObject("distributor_id"));
                       rsdist = stmtDistributorList.executeQuery();
                       while(rsdist.next()){
                           strDistributorName = rsdist.getObject("distributor_name");
                       }
                       temp.add(strDistributorName);
                       
                   }
                   catch(SQLException se){
                       JOptionPane.showMessageDialog(null, se+"from disributor");
                   }
                   // temp.add("test");
                  //  continue;
                }
                
              //  test = rsItemList.getInt("distributor_id");
           //  test =  rsItemList.getRowId("distributor_id");
          // test =   Integer.parseInt(temp.elementAt(1).toString());
            //JOptionPane.showMessageDialog(null,temp);
            }
          
          
            data.add(temp);
        }
  */
         while(rsItemList.next()){
             Object[] row = new Object[]{rsItemList.getString("purchase_id"),rsItemList.getString("item_name"),rsItemList.getFloat("quantity"),rsItemList.getString("unit_name"),rsItemList.getString("distributor_name"),rsItemList.getString("total_amount")};
        Object[] full =  new Object[]{rsItemList.getString("purchase_id"),rsItemList.getString("item_name"),rsItemList.getFloat("quantity"),rsItemList.getString("unit_name"),rsItemList.getString("distributor_name"),rsItemList.getString("item_id"),rsItemList.getString("distributor_id")};
             data.add(row);
             fulldata.add(full);
         }
         finaldata = data.toArray(new Object[data.size()][]);
         //it is for retriving the data
         fullfinaldata = fulldata.toArray(new Object[fulldata.size()][]);
        }
        
        catch(SQLException se){
                        JOptionPane.showMessageDialog(null, se+"sql database error");

        }
        catch(Exception e){
                        JOptionPane.showMessageDialog(null, e+"Purchase Return");

        }
        finally{
            preturn.closeConnection();
        }
        return new DefaultTableModel(finaldata,columnNames){
            @Override
            public boolean isCellEditable(int row,int columns){
                //all cells false
                return false;
            }
        };
        
    }
    public String getUnitRelativeQuantity(String UnitId){
      PreparedStatement getrelqty;
      ResultSet getResultSet;
      String Qty = null;
      
      String strgetUnitRelativeQuantity = "SELECT unit_relative_quantity FROM item_unit WHERE unit_id=?";
      DBConnect getUnit = new DBConnect();
      try{
          getUnit.initConnection();
          getrelqty = getUnit.conn.prepareStatement(strgetUnitRelativeQuantity);
         getrelqty.setString(1, UnitId);
          getResultSet = getrelqty.executeQuery();
          getResultSet.next();
          Qty = getResultSet.getString("unit_relative_quantity");
          getResultSet.close();
         
      }
      catch(Exception e){
          JOptionPane.showMessageDialog(null, e+"form getUnitRelativeQuantity");
      }
      finally{
          getUnit.closeConnection();
      }
      return Qty;
  }
    public String getUnitId(String UnitName){
      PreparedStatement getunit_id;
      ResultSet getResultSet;
      String UnitId = null;
      
      String strgetUnitRelativeQuantity = "SELECT unit_id FROM item_unit WHERE unit_name= ?";
      DBConnect getUnit = new DBConnect();
      try{
          getUnit.initConnection();
          getunit_id = getUnit.conn.prepareStatement(strgetUnitRelativeQuantity);
         getunit_id.setString(1, UnitName);
          getResultSet = getunit_id.executeQuery();
          getResultSet.next();
          UnitId = getResultSet.getString("unit_id");
          getResultSet.close();
         
      }
      catch(Exception e){
          JOptionPane.showMessageDialog(null, e+"form getUnitid");
      }
      finally{
          getUnit.closeConnection();
      }
      return UnitId;
  }
    public String[] getItemIdAndDistrobutorId(String PurchaseID){
      PreparedStatement getunit_id;
      ResultSet getResultSet;
      String ItemIdAndDistId[] = new String[2];
    //  String DistId = null;
      
      String strgetUnitRelativeQuantity = "SELECT item_id,distributor_id FROM purchase WHERE purchase_id = ?";
      DBConnect getUnit = new DBConnect();
      try{
          getUnit.initConnection();
          getunit_id = getUnit.conn.prepareStatement(strgetUnitRelativeQuantity);
         getunit_id.setString(1, PurchaseID);
          getResultSet = getunit_id.executeQuery();
          getResultSet.next();
          ItemIdAndDistId[0] = getResultSet.getString("item_id");
          ItemIdAndDistId[1] = getResultSet.getString("distributor_id");
          getResultSet.close();
         
      }
      catch(Exception e){
          JOptionPane.showMessageDialog(null, e+"from getItemIdAndDistrobutorId");
      }
      finally{
          getUnit.closeConnection();
      }
      return ItemIdAndDistId;
  }
    public String getQuantityFromCenterStore(String ItemId){
      PreparedStatement getAmount;
      ResultSet getResultSet;
      String Amount = null;
      
      String strgetUnitRelativeQuantity = "SELECT total_qty FROM centerstore_stock WHERE item_id= ?";
      DBConnect getUnit = new DBConnect();
      try{
          getUnit.initConnection();
          getAmount = getUnit.conn.prepareStatement(strgetUnitRelativeQuantity);
         getAmount.setString(1, ItemId);
          getResultSet = getAmount.executeQuery();
          getResultSet.next();
          Amount = getResultSet.getString("total_qty");
          getResultSet.close();
         
      }
      catch(Exception e){
          JOptionPane.showMessageDialog(null, e+"from getQuantity");
      }
      finally{
          getUnit.closeConnection();
      }
      return Amount;
  }
    
    public Date getdate(){
        SimpleDateFormat  dateformat = new SimpleDateFormat("yyyy-MM-dd");
       // Calendar cal = Calendar.getInstance();
        Date date = new Date();
        return date;
    }
}
