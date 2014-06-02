/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.issue;

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
public class IssueModel extends DBConnect {
    private PreparedStatement stmtIssue ;
    private PreparedStatement stmtPrint;
    private PreparedStatement stmtEdit;
    private PreparedStatement stmtItemInfo;
    private PreparedStatement stmtIssueInfo;
    private PreparedStatement stmtUpdateCenterStore;
    private PreparedStatement stmtUpdateResturantStore;
    public Object Itemdata[][] = null;
     public  Boolean ExistingStatus;
   ResultSet rsResult;
   private DefaultTableModel issuemodel;
/*
 * this function execute the fucntion of the issue item for issuing the item to the resturant
 * if boolean is true then it understand item to issue already exist in resturant_store so
 * if update the table but when boolean is false it insert into resturant_store
 */
   void issueItem(String issueinfo[],boolean status){
       int StoreItemId = 0;
       /*
        *  
        *  info[0] = String.valueOf(getItemId());
     info[1] = getcomboItemName();
     info[2] = getCenterStockQuantity();
     info[3] = getIssueQuantity();
     info[4] = String.valueOf(getUnitId());
    // info[5] = getItemBaseUnit();
     info[5] = getcomboItemBaseUnit();
//     info[6] = getFrom();
//     info[7] = getTo();
      info[6] = String.valueOf(getFromStoreId());
     info[7] = String.valueOf(getToStoreId());
     info[8] = getExpiryDate().toString();
        */
       String strIssue = "INSERT INTO issue (department_item_id,quantity,unit_id,issue_from,issue_to,issue_date) VALUES(?,?,?,?,?,?)";
       String strUpdateCenterStore = "UPDATE centerstore_stock SET total_qty = ? WHERE item_id = ? ";
       String strResturant ;
       BigDecimal NetQuantity;
       BigDecimal  NetQuantityForResturant;
       BigDecimal  UnitRelativeQuantity ;
//       DBConnect issue = new DBConnect();
       try{
          initConnection();
         

           /*
            * for centerstore updating
            */
           /*
            * calculating thetotal quantity for center store to using unit_relative_quantity
            */
           UnitRelativeQuantity =new BigDecimal(getUnitRelativeQuantity(issueinfo[4])).setScale(3, RoundingMode.HALF_UP);
          
          
       
          // NetQuantity = (Float.parseFloat(issueinfo[2]) - (Float.parseFloat(issueinfo[3])))*UnitRelativeQuantity;
       NetQuantity = new BigDecimal(issueinfo[2]).setScale(3, RoundingMode.HALF_UP).subtract(new BigDecimal(issueinfo[3]).setScale(3, RoundingMode.HALF_UP)).multiply(UnitRelativeQuantity);
       conn.setAutoCommit(false);   
       stmtUpdateCenterStore = conn.prepareStatement(strUpdateCenterStore);
          
         //  System.out.println(NetQuantity);
          stmtUpdateCenterStore.setBigDecimal(1,NetQuantity.setScale(3, RoundingMode.HALF_UP));
          stmtUpdateCenterStore.setInt(2,Integer.parseInt(issueinfo[0]));
//          stmtUpdateCenterStore.setInt(3,Integer.parseInt( issueinfo[6]));
          stmtUpdateCenterStore.executeUpdate();

         // System.out.println("check3");
          //issue.conn.commit();
         //  issue.conn.setAutoCommit(true);
           /*
            * for updating the resturant_store
            */
           /*
    * this return true if name/id already exist and return false is not exist
    */
           //if true
           
    if(status){
         
                String strCheck = "SELECT department_item_id,total_qty FROM department_store_stock WHERE item_id = ? and department_id = ?";
   
    PreparedStatement stmtcheck ;
    BigDecimal quan = null;
    //obtaing the quantiy of the item to be issued if it is true
            //   System.out.println("check3");
                    
                    conn.setAutoCommit(false);
                    stmtcheck = conn.prepareStatement(strCheck);
                    
                    stmtcheck.setInt(1,Integer.parseInt(issueinfo[0]));
                     stmtcheck.setInt(2, Integer.parseInt(issueinfo[7]));
                    ResultSet rs = stmtcheck.executeQuery();
                   // System.out.println("check3");
                   while(rs.next()){
                         quan = rs.getBigDecimal("total_qty");
                         StoreItemId = rs.getInt("department_item_id");
//                   rs.close();
                  
                   }



                
                
               

               strResturant = "UPDATE department_store_stock  SET total_qty = ? WHERE item_id = ? and department_id = ? ";
                conn.setAutoCommit(false);
               stmtUpdateResturantStore  =conn.prepareStatement(strResturant);
            
              /*
               * calculating the totalquantiy of to stored in resturant_store
               */
             //  NetQuantityForResturant = (float)quan + (Float.parseFloat(issueinfo[3])*UnitRelativeQuantity);
           NetQuantityForResturant = quan.add(new BigDecimal(issueinfo[3]).setScale(3, RoundingMode.HALF_UP).multiply(UnitRelativeQuantity)).setScale(3, RoundingMode.HALF_UP);
              //   System.out.println(NetQuantityForResturant);
               stmtUpdateResturantStore.setBigDecimal(1,NetQuantityForResturant);
               stmtUpdateResturantStore.setInt(2,Integer.parseInt(issueinfo[0]));
               stmtUpdateResturantStore.setInt(3, Integer.parseInt(issueinfo[7]));
               stmtUpdateResturantStore.executeUpdate();
                   
//    JOptionPane.showMessageDialog(null, "o my radhe i love you");
           }
    //else if there doesnot exit same item 
    else {
      // System.out.println("check3");
        strResturant = "INSERT INTO department_store_stock (item_id,total_qty,unit_id,item_expiry_date,department_id) VALUES(?,?,?,?,?)";
        conn.setAutoCommit(false);       
        stmtUpdateResturantStore  = conn.prepareStatement(strResturant);
              
               /*
                * calculating totalquantity for resturantstore
                */
            //   NetQuantityForResturant = (float)Float.parseFloat(issueinfo[3])* UnitRelativeQuantity;
               NetQuantityForResturant = new BigDecimal(issueinfo[3]).setScale(3, RoundingMode.HALF_UP).multiply(UnitRelativeQuantity).setScale(3, RoundingMode.HALF_UP);
               stmtUpdateResturantStore.setInt(1,Integer.parseInt(issueinfo[0]));
               stmtUpdateResturantStore.setBigDecimal(2,NetQuantityForResturant);
               stmtUpdateResturantStore.setInt(3, Integer.parseInt(issueinfo[4]));
              // DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
      
      //   Date  ExpiryDate = dateformat.parse(date);
       // Date ExpiryDate = dataformat.
         //  System.out.println(issueinfo[8]);
     //  Date utildate =  new SimpleDateFormat("yyy-MM-dd").format(issueinfo[8]);
      // java.sql.Date sqldate = new  java.sql.Date(utildate.getTime());
       
               stmtUpdateResturantStore.setString(4,issueinfo[8]);
               stmtUpdateResturantStore.setInt(5, Integer.parseInt(issueinfo[7]));
               stmtUpdateResturantStore.executeUpdate();
               
               stmtUpdateResturantStore = conn.prepareStatement("SELECT LAST_INSERT_ID() ");
               ResultSet rs = stmtUpdateResturantStore.executeQuery();
               while(rs.next()){
                 StoreItemId =   rs.getInt(1);
               }
               
                                      
       }
//    System.out.println(StoreItemId);
    //for inserting into the issue table
       conn.setAutoCommit(false);
           stmtIssue = conn.prepareStatement(strIssue);
          
           stmtIssue.setInt(1,StoreItemId);
           stmtIssue.setBigDecimal(2,new BigDecimal(issueinfo[3]).setScale(3, RoundingMode.HALF_UP));
           stmtIssue.setInt(3, Integer.parseInt(issueinfo[4]));
           stmtIssue.setInt(4, Integer.parseInt(issueinfo[6]));
           stmtIssue.setInt(5,Integer.parseInt( issueinfo[7]));
           Timestamp date = new Timestamp(new Date().getTime()) ;
           stmtIssue.setTimestamp(6,date);
           stmtIssue.executeUpdate();
       
    //issue.conn.setAutoCommit(true);
    conn.commit();
           JOptionPane.showMessageDialog(null, "Item Issued Succesfully");
           
           
       }
       catch(SQLException se ){
           JOptionPane.showMessageDialog(null, se+"SqlException from issuemodel");
       }
      
       finally{
         
         closeConnection();
         
       }
       
    
}
    public float getStockQuantityfromCenterStoreByIssueId(int IssueId){
       String strQuery = "SELECT centerstore_stock.total_qty FROM centerstore_stock INNER JOIN department_store_stock ON centerstore_stock.item_id = department_store_stock.item_id INNER JOIN issue ON department_store_stock.department_item_id = issue.department_item_id WHERE issue.issue_id = ?";
//       DBConnect cn = new DBConnect();
       PreparedStatement stmtget;
      float quantity = 0 ;
       ResultSet rs;
       try{
           initConnection();
          stmtget =  conn.prepareStatement(strQuery);
           stmtget.setInt(1,IssueId);
          rs =  stmtget.executeQuery();
          while(rs.next()){
              quantity = rs.getFloat("total_qty");
          }
           
           
       }
       catch(SQLException se){
           JOptionPane.showMessageDialog(null, " from getitemidBYissueid");
       }
     
       finally{
          closeConnection();
       }
       return quantity;
   }
   public float getStockQuantityfromCenterStore(String itemname){
       String strQuery = "SELECT total_qty FROM centerstore_stock WHERE item_name = ?  ";
       DBConnect cn = new DBConnect();
       PreparedStatement stmtget = null ;
      float quantity = 0;
       ResultSet rs;
       try{
           cn.initConnection();
          stmtget =  cn.conn.prepareStatement(strQuery);
           stmtget.setString(1,itemname);
          rs =  stmtget.executeQuery();
          while(rs.next()){
              quantity = rs.getFloat("total_qty");
          }
           
           
       }
       catch(SQLException se){
           JOptionPane.showMessageDialog(null,se+ " from getStockQuantityfromDatabase");
       }
      
       finally{
           cn.closeConnection();
       }
       return quantity;
   }
   public float getStockQuantityfromResturantStore(int IssueId){
//       String strQuery = "SELECT total_qty FROM resturant_store WHERE item_id =(SELECT issue.item_id FROM issue WHERE issue_id = ?)   ";
        String strQuery = "select department_store_stock.total_qty from issue inner join department_store_stock on issue.department_item_id = department_store_stock.department_item_id and issue.issue_to = department_store_stock.department_id where issue_id = ?";
//       DBConnect cn = new DBConnect();
       PreparedStatement stmtget = null ;
      float quantity = 0;
       ResultSet rs;
       try{
          initConnection();
          stmtget =  conn.prepareStatement(strQuery);
           stmtget.setInt(1,IssueId);
          rs =  stmtget.executeQuery();
          while(rs.next()){
              quantity = rs.getFloat("total_qty");
          }
           
           
       }
       catch(SQLException se){
           JOptionPane.showMessageDialog(null, se+" from getStockQuantityfromresturant");
       }
      
       finally{
          closeConnection();
       }
       return quantity;
   }
   public void issueEdit(String  issueinfo[],float prevIssueQuantity, float UnitRelativeQuantity){
       /*
         
     info[0] = String.valueOf(getIssueId());
     info[1] = String.valueOf(getUnitId());
     info[2] =  getCenterStockQuantity();
     info[3] = getResturantStockQuantity();
     info[4] =  getIssueQuantity();
//     info[5] = getFrom();
//     info[6] = getTo();
      info[5] = String.valueOf(getFromStoreId());
     info[6] = String.valueOf(getToStoreId());
     info[7] = getExpiryDate();
     
        */
      
//       String strgetItemId = "SELECT issue.issue_to,resturant_store.item_id FROM issue WHERE issue_id = ?";
        String strgetItemId = "SELECT issue.issue_to,department_store_stock.item_id FROM issue INNER JOIN department_store_stock ON issue.department_item_id = department_store_stock.department_item_id WHERE issue_id = ?";
       
       
       String strIssueEdit = "UPDATE  issue SET quantity = ?,issue_from = ?,issue_to = ?,issue_date = ? WHERE issue_id = ? ";
       String strUpdateCenterStore = "UPDATE centerstore_stock SET total_qty = ? WHERE item_id = ?";
//       String strUpdateResuturantStore = "UPDATE resturant_store SET total_qty = ? WHERE item_id =?  and store_id = ?";
        String strUpdateResuturantStore = "UPDATE department_store_stock INNER JOIN issue ON department_store_stock.department_item_id = issue.department_item_id  SET total_qty = ?  WHERE issue.issue_id = ? ";
      
        
       BigDecimal NetQuantityForResturant;
       DBConnect edit = new DBConnect();
      BigDecimal NetQuantity;
   //   float UnitRelativeQuantity;
       int iItemId;
       int StoreId;
       PreparedStatement stmtitemid ;
       ResultSet rsitem;
      try{ 
           edit.initConnection();
           
           /*
        * obtaing the item_id from the issue_id
        */
           /*
            * setting the quantity into resepective Total_qty by using unit_id
            */
           
           stmtitemid = edit.conn.prepareStatement(strgetItemId);
           edit.conn.setAutoCommit(false);
           stmtitemid.setString(1, issueinfo[0]);
           rsitem = stmtitemid.executeQuery();
           rsitem.next();
            iItemId = rsitem.getInt("item_id");
            StoreId = rsitem.getInt("issue_to");
            rsitem.close();
            //System.out.println(iItemId);
           /*
            * operation fro updating issue table
            */
           stmtEdit = edit.conn.prepareStatement(strIssueEdit);
           edit.conn.setAutoCommit(false);
           stmtEdit.setBigDecimal(1,new BigDecimal(issueinfo[4]).setScale(3, RoundingMode.HALF_UP));
           stmtEdit.setString(2,issueinfo[5]);
           //stmtEdit.setString(2,"try");
           stmtEdit.setString(3,issueinfo[6]);
           stmtEdit.setTimestamp(4,new Timestamp(new Date().getTime()));
           stmtEdit.setString(5,issueinfo[0]);
           stmtEdit.executeUpdate();
           /*
            * operation for updating the centerstore_stock table
            */
       /*
           //obtaing the quantity from centerstore to update
             String strCheck = "SELECT total_qty FROM centerstore_stock WHERE item_id = ?";
   
    PreparedStatement stmtcheck ;
   BigDecimal quan;
    //obtaing the quantiy of the item to be issued if it is true
               
                   
                    edit.conn.setAutoCommit(false);
                    stmtcheck = edit.conn.prepareStatement(strCheck);
                    stmtcheck.setString(1, iItemId);
                    
                    ResultSet rs = stmtcheck.executeQuery();
                   rs.next();
                         quan = rs.getBigDecimal("total_qty");
                   rs.close();
                   * 
                   * */
           stmtUpdateCenterStore = edit.conn.prepareStatement(strUpdateCenterStore);
           edit.conn.setAutoCommit(false);
           /*
            * (Integer.parseInt(issueinfo[3])- prevIssueQuantity) is done to obtained the net quantity that need to update for centerstore
            */
           /*
            * calculating thetotal quantity for center store to using unit_relative_quantity
            */
        //  UnitRelativeQuantity =getUnitRelativeQuantity(issueinfo[0]);
         //  System.out.println(issueinfo[0]);
         //  System.out.println(issueinfo[0]);
        //  System.out.println(UnitRelativeQuantity);
       
          //NetQuantity = quan - ((Float.parseFloat(issueinfo[3])- prevIssueQuantity)*UnitRelativeQuantity);
          // System.out.println(NetQuantity);
           NetQuantity = new BigDecimal(issueinfo[2]).setScale(3, RoundingMode.HALF_UP).add(new BigDecimal(String.valueOf(prevIssueQuantity)).setScale(3
                   , RoundingMode.HALF_UP)).subtract(new BigDecimal(issueinfo[4]).setScale(3, RoundingMode.HALF_UP)).multiply(new BigDecimal(UnitRelativeQuantity));
          // System.out.println(NetQuantity);
          // System.out.println(NetQuantity);
          stmtUpdateCenterStore.setBigDecimal(1,NetQuantity.setScale(3, RoundingMode.HALF_UP));
          stmtUpdateCenterStore.setInt(2,iItemId);
          stmtUpdateCenterStore.executeUpdate();
          /*
           * operation fro updating the department_store_stock table
           */
         
                   
                   
                   stmtUpdateResturantStore  = edit.conn.prepareStatement(strUpdateResuturantStore);
              edit.conn.setAutoCommit(false);
               /*
            * (Integer.parseInt(issueinfo[3])- prevIssueQuantity) is done to obtained the net quantity that need to update for resturant
            */
           
         //   System.out.print((Float.parseFloat(issueinfo[3]) + (Float.parseFloat(issueinfo[4])- prevIssueQuantity))*UnitRelativeQuantity );
        // NetQuantityForResturant = new BigDecimal("50");
            NetQuantityForResturant = new BigDecimal(issueinfo[3]).add(new BigDecimal(issueinfo[4])).subtract(new BigDecimal(prevIssueQuantity)).multiply(new BigDecimal(UnitRelativeQuantity));
          //  System.out.println(NetQuantityForResturant);
           // stmtUpdateResturantStore.setBigDecimal(1,NetQuantityForResturant);
            stmtUpdateResturantStore.setBigDecimal(1, NetQuantityForResturant.setScale(3, RoundingMode.HALF_UP));
//            stmtUpdateResturantStore.setInt(2,iItemId);
//            stmtUpdateResturantStore.setInt(3, StoreId);
            stmtUpdateResturantStore.setInt(2,Integer.parseInt(issueinfo[0]));
               stmtUpdateResturantStore.executeUpdate();
           //System.out.println(NetQuantityForResturant);
           
           /*
            * it everything get well it permanently update the table
            */
           edit.conn.commit();
           //edit.conn.setAutoCommit(true);
           JOptionPane.showMessageDialog(null, "Issued item is edited");
          
           
       }
       catch(SQLException se){
           JOptionPane.showMessageDialog(null, se+"from issue edit "+getClass().getName());
       }
     
        finally{
         
           

           edit.closeConnection();
         
       }
       
       
   }
   
      

   public void issueCancel(){
       
   }
   /*
    * this function get all the itemname and info available from the table centorstorestock
    */
   public Object[][] getItemInfoForIssue(){
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
   public DefaultTableModel getIssueList(){
       int colcount;
       int rowcount ;
      // String[] ColumnNames = { "Issue Id", "Item Name", "Issue Quantity","item BaseUnit","Issue From", "Issue To","Issue Date"};
       String strQuery = "SELECT issue.issue_id,centerstore_stock.item_name,issue.quantity,issue.unit_id,item_unit.unit_name,center_store_info.store_name as issue_from,department_info.department_name as issue_to,issue.issue_date FROM issue INNER JOIN department_store_stock ON issue.department_item_id = department_store_stock.department_item_id INNER JOIN  item_unit ON issue.unit_id = item_unit.unit_id INNER JOIN centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id INNER JOIN department_info ON issue.issue_to = department_info.department_id "
               + " INNER JOIN center_store_info ON issue.issue_from = center_store_info.store_id   ORDER BY issue.issue_date desc";
       String ColumnNames[] = {"Issue Id","Item Name","Issue Quantity","Item BaseUnit","Issue From","Issue To","Issue Date"};
      //Object[][] data = null;
     //  List<Object[]> data = new ArrayList<Object[]>();
   ArrayList<Object[]> data = new ArrayList<Object[]>();
    Object[][] finalData =null;
       DBConnect getissue = new DBConnect();
       try{
           getissue.initConnection();
           stmtIssueInfo = getissue.conn.prepareStatement(strQuery);
           rsResult = stmtIssueInfo.executeQuery();
           
//           ResultSetMetaData metadata = rsResult.getMetaData();
//           colcount = metadata.getColumnCount();
            
           while(rsResult.next()){
              Object[] row = new Object[]{rsResult.getString("issue_id"),rsResult.getString("item_name"),rsResult.getFloat("quantity"),rsResult.getString("unit_name"),rsResult.getString("issue_from"),rsResult.getString("issue_to"),rsResult.getString("issue_date")};
              /* for(int i=0;i<colcount;i++){
                   row[i] = rsResult.getObject(i+1);
               }*/
                
               data.add(row);
               
              // data
              
           }
            finalData = data.toArray(new Object[data.size()][]);
           
       }
       catch(SQLException se){
           JOptionPane.showMessageDialog(null, se+"from getIssueList");
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
   //there it will be same as getissuelist but it used like 
   public DefaultTableModel getIssueListByWildSearch(String menuname){
       int colcount;
       int rowcount ;
       String src = menuname+"%";
      
      // String[] ColumnNames = { "Issue Id", "Item Name", "Issue Quantity","item BaseUnit","Issue From", "Issue To","Issue Date"};
       String strQuery = "SELECT issue.issue_id,centerstore_stock.item_name,issue.quantity,issue.unit_id,item_unit.unit_name,center_store_info.store_name as issue_from,department_info.department_name as issue_to,issue.issue_date FROM issue INNER JOIN department_store_stock ON issue.department_item_id = department_store_stock.department_item_id INNER JOIN  item_unit ON issue.unit_id = item_unit.unit_id INNER JOIN centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id INNER JOIN department_info ON issue.issue_to = department_info.department_id "
               + " INNER JOIN center_store_info ON issue.issue_from = center_store_info.store_id    WHERE centerstore_stock.item_name LIKE ? ORDER BY issue.issue_date desc";
       String ColumnNames[] = {"Issue Id","Item Name","Issue Quantity","Item BaseUnit","Issue From","Issue To","Issue Date"};
      //Object[][] data = null;
     //  List<Object[]> data = new ArrayList<Object[]>();
   ArrayList<Object[]> data = new ArrayList<Object[]>();
    Object[][] finalData =null;
       DBConnect getissue = new DBConnect();
       try{
           getissue.initConnection();
           stmtIssueInfo = getissue.conn.prepareStatement(strQuery);
           stmtIssueInfo.setString(1, src);
           rsResult = stmtIssueInfo.executeQuery();
           
//           ResultSetMetaData metadata = rsResult.getMetaData();
//           colcount = metadata.getColumnCount();
            
           while(rsResult.next()){
              Object[] row = new Object[]{rsResult.getString("issue_id"),rsResult.getString("item_name"),rsResult.getFloat("quantity"),rsResult.getString("unit_name"),rsResult.getString("issue_from"),rsResult.getString("issue_to"),rsResult.getString("issue_date")};
              /* for(int i=0;i<colcount;i++){
                   row[i] = rsResult.getObject(i+1);
               }*/
                
               data.add(row);
               
              // data
              
           }
            finalData = data.toArray(new Object[data.size()][]);
           
       }
       catch(SQLException se){
           JOptionPane.showMessageDialog(null, se+"from getIssueList");
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
   /*
    * this return true if name/id already exist and return false is not exist
    */
   public boolean checkExistingNameInStore(int itemid,int store_id){
      
    String strCheck = "SELECT item_id FROM department_store_stock WHERE item_id = ? and department_id = ? ";
   
    PreparedStatement stmtcheck ;
    try{
       initConnection();
        stmtcheck = conn.prepareStatement(strCheck,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        stmtcheck.setInt(1, itemid);
        stmtcheck.setInt(2, store_id);
        ResultSet rs = stmtcheck.executeQuery();
        rs.last();
        int rows = rs.getRow();
        rs.beforeFirst();
        ExistingStatus = rows >= 1;
        
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from checkExistingName");
    }
    finally{
       closeConnection();
    }
    return ExistingStatus;
    
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
  public Object[][] getUnitInfo(int UnitId){
      PreparedStatement getrelqty;
      ResultSet getResultSet;
      //float Qty = 0;
      Object[] UnitName;
      String strgetUnitRelativeQuantity = "select unit_id,unit_name,unit_relative_quantity from item_unit where unit_type = (select unit_type from item_unit where unit_id = ?)";
       ArrayList<Object[]>  data= new ArrayList<Object[]>();
//      DBConnect getUnit = new DBConnect();
      try{
         initConnection();
          getrelqty = conn.prepareStatement(strgetUnitRelativeQuantity);
         getrelqty.setInt(1, UnitId);
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
         closeConnection();
      }
      return data.toArray(new Object[data.size()][]);
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
  public String getUnitIdByIssueId(int IssueId){
      PreparedStatement getQuantity = null;
      ResultSet rsQuantity;
      String UnitId = null;
      String strQuantity = "SELECT unit_id FROM issue WHERE issue_id = ? ";
//      DBConnect  qty = new  DBConnect();
     try{
     initConnection();
     getQuantity = conn.prepareStatement(strQuantity);
      getQuantity.setInt(1,IssueId);
      rsQuantity = getQuantity.executeQuery();
     rsQuantity.next();
      UnitId = rsQuantity.getString("unit_id");
     }
     catch(SQLException se){
         JOptionPane.showMessageDialog(null, se+"gerUnitIdByIssueId");
     }
    
     
      finally{
         closeConnection();
     }
     return UnitId;
  }
  public Object[][] getCenterStoreName(){
    PreparedStatement stmt;
      ResultSet rs;
      ArrayList<Object[]> data = new ArrayList<>();
      try{
          initConnection();
          stmt = conn.prepareStatement("SELECT store_id,store_name from center_store_info ");
           rs = stmt.executeQuery();
          while(rs.next()){
          Object[] row = new Object[]{rs.getObject(1),rs.getObject(2)};
          data.add(row);
          }
          
      }
      catch(SQLException se ){
          JOptionPane.showMessageDialog(null, se+"from getCenterStoreName "+getClass().getName());
      }
      finally{
          closeConnection();
      }
      return data.toArray(new Object[data.size()][]);
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
  
}
