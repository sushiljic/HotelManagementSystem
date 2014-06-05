/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.issuereturn;

import database.DBConnect;
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
public class IssueReturnModel extends DBConnect {
    private PreparedStatement  stmtIssueReturn;
     private PreparedStatement  stmtUpdateCenterStore;
      private PreparedStatement  stmtUpdateResturantStore;
    private PreparedStatement stmtIssueReturnCancel;
    private PreparedStatement stmtIssueReturnSearch;
    private PreparedStatement stmtResturantItemList;
    private PreparedStatement stmtIssue;

    private ResultSet rsResultSet;
    
    
    
    public void IssueReturn(String[] ReturnInfo){
        /*
        //  ReturnInfo[0] = getItemId();
        ReturnInfo[0] = getIssueId();
        ReturnInfo[1] = getItemName();
      
         
        ReturnInfo[2]= getStockQuantity();
        ReturnInfo[3]= getIssueQuantity();
        ReturnInfo[4]= getReturnQuantity();
    
        ReturnInfo[5] = getItemBaseUnitName();
        ReturnInfo[6]= getIssueDate();
        ReturnInfo[7] = getReturnReason();
         */
       BigDecimal NetQuantity;
       BigDecimal NetQuantityForResturant ;
        
        String strIssueReturn = "INSERT INTO issue_return(issue_id,quantity,unit_id,return_reason,return_date) VALUES(?,?,?,?,?)";
//        String strUpdateCenterStore = "UPDATE centerstore_stock SET total_qty = ? WHERE item_id =?";
        String strUpdateCenterStore = "UPDATE centerstore_stock SET total_qty = ? WHERE item_id =?";
        String strResturantStock = "UPDATE department_store_stock SET total_qty = ? WHERE department_item_id =?";
        String strIssue = "UPDATE issue SET quantity = ? WHERE issue_id = ?";
        
        DBConnect issueReturn = new DBConnect();
        try{
            /*
             * for inserting into issue_return
             */
            /*
             *finding the uni_id by issue_id 
             */
            String UnitId = getUnitIdByIssueId(Integer.parseInt(ReturnInfo[0]));
          //  String ItemId =
            int ItemId = getItemIdByIssueId(Integer.parseInt(ReturnInfo[0]));
            int DepartmentItemId = getDepartmentItemIdByIssueId(Integer.parseInt(ReturnInfo[0]));
            issueReturn.initConnection();
            stmtIssueReturn = issueReturn.conn.prepareStatement(strIssueReturn);
            issueReturn.conn.setAutoCommit(false);
            stmtIssueReturn.setString(1, ReturnInfo[0]);
            stmtIssueReturn.setBigDecimal(2, new BigDecimal(ReturnInfo[4]).setScale(3, RoundingMode.HALF_UP));
            stmtIssueReturn.setString(3,UnitId);
            stmtIssueReturn.setString(4, ReturnInfo[7]);
            stmtIssueReturn.setTimestamp(5,new Timestamp(returnSystemDate().getTime()));
            stmtIssueReturn.executeUpdate();
            /*
             * for updating the centerstore_stock
             */
            //for finding the quantity in cneterstore_stock
            BigDecimal quan;
            PreparedStatement stmtcheck;
    //obtaing the quantiy of the item to be issued if it is true
            String strCheck = "SELECT total_qty FROM centerstore_stock WHERE item_id = ?";
                   
                    issueReturn.conn.setAutoCommit(false);
                    stmtcheck = issueReturn.conn.prepareStatement(strCheck);
                    
                    stmtcheck.setInt(1,ItemId);
                     
                    ResultSet rs = stmtcheck.executeQuery();
                    
                   rs.next();
                         quan = rs.getBigDecimal("total_qty");
                   rs.close();
            
            BigDecimal UnitRelativeQuantity;
            UnitRelativeQuantity = new BigDecimal(getUnitRelativeQuantity(UnitId)).setScale(3, RoundingMode.HALF_UP);
            NetQuantity = quan.add(new BigDecimal(ReturnInfo[4]).setScale(3, RoundingMode.HALF_UP).multiply(UnitRelativeQuantity));
            stmtUpdateCenterStore = issueReturn.conn.prepareStatement(strUpdateCenterStore);
            issueReturn.conn.setAutoCommit(false);
            stmtUpdateCenterStore.setBigDecimal(1,NetQuantity);
            stmtUpdateCenterStore.setInt(2, ItemId);
            stmtUpdateCenterStore.executeUpdate();
            /*
             * for updating the resturantstore
             */
      //      NetQuantityForResturant = Integer.parseInt(strIssueReturn)
           /*  int quan = 0;
             PreparedStatement stmtcheck;
            //obtaing the quantiy of the item to be issued if it is true
            String strCheck = "SELECT quantity FROM department_store_stock WHERE item_id = ?";
                    
                    issueReturn.conn.setAutoCommit(false);
                    stmtcheck = issueReturn.conn.prepareStatement(strCheck);
                    
                    stmtcheck.setString(1,ReturnInfo[0]);
                     
                    ResultSet rs = stmtcheck.executeQuery();
                    
                   rs.next();
                         quan = rs.getInt("quantity");
                   rs.close();
                   */
            /*
             * getting resturnat store 
             */
             BigDecimal ResStore = new BigDecimal(getStockQuantityfromResturantStore(Integer.parseInt(ReturnInfo[0]))).setScale(3, RoundingMode.HALF_UP);
             NetQuantityForResturant = ResStore.subtract(new BigDecimal(ReturnInfo[4]).setScale(3, RoundingMode.HALF_UP).multiply(UnitRelativeQuantity));
             stmtUpdateResturantStore  = issueReturn.conn.prepareStatement(strResturantStock);
              issueReturn.conn.setAutoCommit(false);
             //  NetQuantityForResturant = Integer.parseInt(ReturnInfo[2]) - Integer.parseInt(ReturnInfo[3]);
           
              //   System.out.println(NetQuantityForResturant);
               stmtUpdateResturantStore.setBigDecimal(1, NetQuantityForResturant);
               stmtUpdateResturantStore.setInt(2,DepartmentItemId);
               stmtUpdateResturantStore.executeUpdate();
               /*
                * for updating the issue table
                */
               BigDecimal NetIssueQuantity = new BigDecimal(ReturnInfo[3]).setScale(3, RoundingMode.HALF_UP).subtract(new BigDecimal(ReturnInfo[4]).setScale(3, RoundingMode.HALF_UP));
               stmtIssue = issueReturn.conn.prepareStatement(strIssue);
               stmtIssue.setBigDecimal(1, NetIssueQuantity);
               stmtIssue.setString(2,ReturnInfo[0]);
               stmtIssue.executeUpdate();
               //commit if everything went right
              issueReturn.conn.commit();
              JOptionPane.showMessageDialog(null, "Issued Item Returned Successively");
                      
           
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null,se+"from issueReturn");
        }
        catch(Exception e){
          JOptionPane.showMessageDialog(null,e+"from issueReturn");  
        }
        finally{
             try{
            issueReturn.conn.setAutoCommit(true);

           issueReturn.closeConnection();
            }
           catch(SQLException se){
               JOptionPane.showMessageDialog(null, se+"from issuereturn");
           }
        }
    }
    public DefaultTableModel getResturantItemList( int departmentid){
         int colcount;
       int rowcount ;
      // String[] ColumnNames = { "Issue Id", "Item Name", "Issue Quantity","item BaseUnit","Issue From", "Issue To","Issue Date"};
       String strQuery = "SELECT issue.issue_id,issue.department_item_id,centerstore_stock.item_name,issue.quantity,issue.unit_id,item_unit.unit_name,center_store_info.store_name,department_info.department_name,issue.issue_date FROM issue INNER JOIN item_unit ON issue.unit_id = item_unit.unit_id INNER JOIN  department_store_stock ON issue.department_item_id = department_store_stock.department_item_id INNER JOIN centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id INNER JOIN center_store_info ON issue.issue_from = center_store_info.store_id INNER JOIN department_info ON issue.issue_to = department_info.department_id WHERE department_store_stock.department_id = ? ORDER BY issue.issue_date desc";
       String ColumnNames[] = {"Issue Id","Item Name","Issue Quantity","Item BaseUnit","Issue From","Issue To","Issue Date"};
      //Object[][] data = null;
     //  List<Object[]> data = new ArrayList<Object[]>();
   ArrayList<Object[]> data = new ArrayList<Object[]>();
    Object[][] finalData =null;
       DBConnect getissue = new DBConnect();
       ResultSet rsResult;
       PreparedStatement stmtIssueInfo;
       try{
           getissue.initConnection();
           stmtIssueInfo = getissue.conn.prepareStatement(strQuery);
           stmtIssueInfo.setInt(1, departmentid);
           rsResult = stmtIssueInfo.executeQuery();
           
           ResultSetMetaData metadata = rsResult.getMetaData();
           colcount = metadata.getColumnCount();
            
           while(rsResult.next()){
              Object[] row = new Object[]{rsResult.getString("issue_id"),rsResult.getString("item_name"),rsResult.getFloat("quantity"),rsResult.getString("unit_name"),rsResult.getString("center_store_info.store_name"),rsResult.getString("department_info.department_name"),rsResult.getString("issue_date")};
              /* for(int i=0;i<colcount;i++){
                   row[i] = rsResult.getObject(i+1);
               }*/
              
               data.add(row);
               
              // data
              
           }
            finalData = data.toArray(new Object[data.size()][]);
           
       }
        catch(Exception se){
            JOptionPane.showMessageDialog(null, se+"form getResturantItemList");
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
    public DefaultTableModel getResturantItemListLikeSearch( int departmentid,String src){
         int colcount;
       int rowcount ;
       String search = src +"%";
      // String[] ColumnNames = { "Issue Id", "Item Name", "Issue Quantity","item BaseUnit","Issue From", "Issue To","Issue Date"};
       String strQuery = "SELECT issue.issue_id,issue.department_item_id,centerstore_stock.item_name,issue.quantity,issue.unit_id,item_unit.unit_name,center_store_info.store_name,department_info.department_name,issue.issue_date FROM issue INNER JOIN item_unit ON issue.unit_id = item_unit.unit_id INNER JOIN  department_store_stock ON issue.department_item_id = department_store_stock.department_item_id INNER JOIN centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id INNER JOIN center_store_info ON issue.issue_from = center_store_info.store_id INNER JOIN department_info ON issue.issue_to = department_info.department_id WHERE department_store_stock.department_id = ? AND centerstore_stock.item_name LIKE  ? ORDER BY issue.issue_date desc";
       String ColumnNames[] = {"Issue Id","Item Name","Issue Quantity","Item BaseUnit","Issue From","Issue To","Issue Date"};
      //Object[][] data = null;
     //  List<Object[]> data = new ArrayList<Object[]>();
   ArrayList<Object[]> data = new ArrayList<Object[]>();
    Object[][] finalData =null;
       DBConnect getissue = new DBConnect();
       ResultSet rsResult;
       PreparedStatement stmtIssueInfo;
       try{
           getissue.initConnection();
           stmtIssueInfo = getissue.conn.prepareStatement(strQuery);
           stmtIssueInfo.setInt(1, departmentid);
           stmtIssueInfo.setString(2, search);
           rsResult = stmtIssueInfo.executeQuery();
           
           ResultSetMetaData metadata = rsResult.getMetaData();
           colcount = metadata.getColumnCount();
            
           while(rsResult.next()){
              Object[] row = new Object[]{rsResult.getString("issue_id"),rsResult.getString("item_name"),rsResult.getFloat("quantity"),rsResult.getString("unit_name"),rsResult.getString("center_store_info.store_name"),rsResult.getString("department_info.department_name"),rsResult.getString("issue_date")};
              /* for(int i=0;i<colcount;i++){
                   row[i] = rsResult.getObject(i+1);
               }*/
              
               data.add(row);
               
              // data
              
           }
            finalData = data.toArray(new Object[data.size()][]);
           
       }
        catch(Exception se){
            JOptionPane.showMessageDialog(null, se+"form getResturantItemList");
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
    /* borrowed form issueModel
     * 
     */
    public String getUnitIdByIssueId(int IssueId){
      PreparedStatement getQuantity;
      ResultSet rsQuantity;
      String UnitId = null;
      String strQuantity = "SELECT unit_id FROM issue WHERE issue_id = ? ";
      DBConnect  qty = new  DBConnect();
     try{
      qty.initConnection();
     getQuantity = qty.conn.prepareStatement(strQuantity);
      getQuantity.setInt(1,IssueId);
      rsQuantity = getQuantity.executeQuery();
     while(rsQuantity.next()){
      UnitId = rsQuantity.getString("unit_id");
     }
     }
     catch(SQLException se){
         JOptionPane.showMessageDialog(null, se+"gerUnitIdByIssueId");
     }
    
     
      finally{
         qty.closeConnection();
     }
     return UnitId;
  }
    public int getDepartmentItemIdByIssueId(int IssueId){
      PreparedStatement getQuantity = null;
      ResultSet rsQuantity;
      int ItemId = 0;
      String strQuantity = "SELECT department_item_id FROM issue WHERE issue_id = ? ";
      DBConnect  qty = new  DBConnect();
     try{
      qty.initConnection();
     getQuantity = qty.conn.prepareStatement(strQuantity);
      getQuantity.setInt(1,IssueId);
      rsQuantity = getQuantity.executeQuery();
     rsQuantity.next();
      ItemId = rsQuantity.getInt("department_item_id");
     }
     catch(SQLException se){
         JOptionPane.showMessageDialog(null, se+"gerItemIdByIssueId");
     }
    
     
      finally{
         qty.closeConnection();
     }
     return ItemId;
  }
    public int getItemIdByIssueId(int IssueId){
      PreparedStatement getQuantity = null;
      ResultSet rsQuantity;
      int ItemId = 0;
      String strQuantity = "SELECT department_store_stock.item_id FROM issue INNER JOIN  department_store_stock ON  issue.department_item_id = department_store_stock.department_item_id WHERE issue.issue_id = ? ";
      DBConnect  qty = new  DBConnect();
     try{
      qty.initConnection();
     getQuantity = qty.conn.prepareStatement(strQuantity);
      getQuantity.setInt(1,IssueId);
      rsQuantity = getQuantity.executeQuery();
     rsQuantity.next();
      ItemId = rsQuantity.getInt("item_id");
     }
     catch(SQLException se){
         JOptionPane.showMessageDialog(null, se+"gerItemIdByIssueId");
     }
    
     
      finally{
         qty.closeConnection();
     }
     return ItemId;
  }
    public float getUnitRelativeQuantity(String UnitId){
      PreparedStatement getrelqty;
      ResultSet getResultSet;
      float Qty = 0;
      
      String strgetUnitRelativeQuantity = "SELECT unit_relative_quantity FROM item_unit WHERE unit_id=?";
      DBConnect getUnit = new DBConnect();
      try{
          getUnit.initConnection();
          getrelqty = getUnit.conn.prepareStatement(strgetUnitRelativeQuantity);
         getrelqty.setString(1, UnitId);
          getResultSet = getrelqty.executeQuery();
          getResultSet.next();
          Qty = getResultSet.getFloat("unit_relative_quantity");
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
     public float getStockQuantityfromResturantStore(int IssueId){
       String strQuery = "SELECT total_qty FROM department_store_stock WHERE department_item_id =(SELECT issue.department_item_id FROM issue WHERE issue_id = ?)   ";
       DBConnect cn = new DBConnect();
       PreparedStatement stmtget = null ;
      BigDecimal quantity = null;
       ResultSet rs;
       try{
           cn.initConnection();
          stmtget =  cn.conn.prepareStatement(strQuery);
           stmtget.setInt(1,IssueId);
          rs =  stmtget.executeQuery();
          while(rs.next()){
              quantity = rs.getBigDecimal("total_qty");
//              System.out.println(quantity);
          }
           
           
       }
       catch(SQLException se){
           JOptionPane.showMessageDialog(null, se+" from getStockQuantityfromresturant");
       }
     
       finally{
           cn.closeConnection();
       }
       return quantity.floatValue();
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
        public Object[] returnSecondColumn(Object[][]da){
            Object[] data = new Object[da.length];
            for(int i=0;i<da.length;i++){
                data[i]= da[i][1];
            }
            return data;
        }
}
