/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.itemEnteryComponent;

import database.DBConnect;
//import reusableClass.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
//import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import reusableClass.DisplayMessages;
/**
 *
 * @author MinamRosh
 */
public class ItemEnteryModel extends DBConnect{
    private String sql;
    private ResultSet rs;
    private Statement stmt;
    private PreparedStatement preStmt;
    private DefaultTableModel tModel;
    
    private String[] distroList;
    private String[] categoryList;
    private String[] unitList;
    private String[] itemList;
   private int[] subUList;
    
    private String distroId;
    private String categoryId;
    private int[] unitIdAmt;
    private String subUId;
    private int[] itemEntry; //for purchase reference
    private Timestamp purchaseDate;
    
    //puchase element;
    String iName;
   // String itemId;
    String category; //= purchase[1];
    int qty; //= Integer.parseInt(purchase[2]);
    String unit;// = purchase[3];
    int bRate; // = Integer.parseInt(purchase[4]);
    int total; // = qty * bRate;
    int distorId; // = Integer.parseInt(purchase[5]);
    
    Object[][] entryTable; //for searching reference;
    
    public ItemEnteryModel(Component frm){
        //super(frm);
    }
    
    
    //distributor operation;
    public String[] getDistroList()throws NullPointerException{
        sql = "SELECT distributor_name FROM distributor";
        initConnection();
        try{
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            int row = getNumberOfRows(rs);
            distroList = new String[row];
            int i = 0;
            while(rs.next()){
                try{
                    distroList[i] = rs.getString("distributor_name");
                    i++;
                }
                catch(ArrayIndexOutOfBoundsException arr){
                    DisplayMessages.displayError(null, "ItemEntryModel.getDistroList()." +arr, "Database Error");
                }
            }
        }
        catch(SQLException ex){
                     DisplayMessages.displayError(null, "ItemEntryModel.getDistroList()." +ex, "Database Error");           //displayErrMsg(ex.getMessage());
        }
        finally{
            closeConnection();
        }
        return distroList;
    }
    
    //used for duplication detection in controller
    public String[] isItemDuplicate(){
        sql = "SELECT item_name FROM centerstore_stock";
        initConnection();
        try{
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            int row = getNumberOfRows(rs);
            itemList = new String[row];
            int i = 0;
            while(rs.next()){
                try{
                    itemList[i] = rs.getString("item_name");
                    i++;
                }
                catch(ArrayIndexOutOfBoundsException arr){
                    DisplayMessages.displayError(null, "ItemEntryModel.getItemList()." +arr, "Database Error");  
                }
            }
        }
        catch(SQLException ex){
            //displayErrMsg(ex.getMessage());
                    DisplayMessages.displayError(null, "ItemEntryModel.getItemList()." +ex, "Database Error");
        }
        finally{
            closeConnection();
        }
        return itemList;
    }
 
    //category list
    //combine two category table to produce category list;
    public String[] getCategoryList() throws NullPointerException{
        sql = "select sub_category_name as category from item_sub_category";
        initConnection();
        try{
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            int rows = getNumberOfRows(rs);
            
            categoryList = new String[rows];
            int i = 0;
            while(rs.next()){
                categoryList[i] = rs.getString("category");
                i++;
            }
        }
        catch(SQLException | ArrayIndexOutOfBoundsException  ex){
            DisplayMessages.displayError(null, "ItemEntryModel.getCategoryList()." +ex, "Database Error");
        }
        finally{
            closeConnection();
        }
        return categoryList;
    }
    
    public Object[][] categoryDetails(){
        ArrayList<Object[]> dbData = new ArrayList<Object[]>();
        sql = "SELECT sub_category_id, sub_category_name FROM item_sub_category";
        initConnection();
        try{
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            int rows = getNumberOfRows(rs);
            
            categoryList = new String[rows];
            //int i = 0;
            while(rs.next()){
                Object[] data = new Object[] { rs.getObject("sub_category_id"), rs.getObject("sub_category_name")};
                dbData.add(data);
              //  i++;
            }
        }
        catch(SQLException | ArrayIndexOutOfBoundsException  ex){
            DisplayMessages.displayError(null, "ItemEntryModel.categoryDetails()." +ex, "Database Error");
        }
        finally{
            closeConnection();
        }
        
        Object[][] category = dbData.toArray(new Object[dbData.size()][]);
        return category;
    }
 
    //base unit
    public String[] getUnitList() throws NullPointerException{
        
        sql = "SELECT unit_name FROM item_unit";
          
        initConnection();
        try{
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            int rows = getNumberOfRows(rs);
            unitList = new String[rows];
            int i = 0;
            while(rs.next()){
                try{
                    unitList[i] = rs.getString("unit_name");
                    i++;
                }
                catch(ArrayIndexOutOfBoundsException arr){
                    DisplayMessages.displayError(null, "ItemEntryModel.getUnitList()." +arr, "Database Error");
                }
            }
        }
        catch(SQLException | ArrayIndexOutOfBoundsException ex){
            DisplayMessages.displayError(null, "ItemEntryModel.getUnitList()." +ex, "Database Error");// displayErrMsg(ex.getMessage());
        }
        finally{
            closeConnection();
        }
        return unitList;
    }

    public Object[][] getCategoryDetails(){
        ArrayList<Object[]> dbData = new ArrayList<Object[]>();
        sql = "select sub_category_id as id, sub_category_name as name from item_sub_category";
        initConnection();
        try{
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            int rows = getNumberOfRows(rs);
            
            categoryList = new String[rows];
           
            while(rs.next()){
               Object[] data = new Object[]{rs.getObject("id"), rs.getObject("name")};
                dbData.add(data);
            }
        }
        catch(SQLException | ArrayIndexOutOfBoundsException  ex){
            DisplayMessages.displayError(null, "ItemEntryModel.getCategoryDetails()." +ex, "Database Error");
        }
        finally{
            closeConnection();
        }
        Object[][] category = dbData.toArray(new Object [dbData.size()][]);
        return category;
    }
    
    public Object[][] getUnitDetails(){
         sql = "SELECT unit_id, unit_name, unit_relative_quantity FROM item_unit";
         
         ArrayList <Object[]> dbData = new ArrayList<Object[]>(); // = null;
        // Object[][] data;
        initConnection();
        try{
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            ResultSetMetaData meta = rs.getMetaData();
            //int cols = meta.getColumnCount();
            //int rows = getNumberOfRows(rs);
            //unitList = new String[rows];
            //int i = 0;
            while(rs.next()){
                try{
                    Object[] d = new Object[]{rs.getObject("unit_id"), rs.getObject("unit_name"),rs.getObject("unit_relative_quantity")};
                    //i++;
                    dbData.add(d);
                }
                catch(ArrayIndexOutOfBoundsException |SQLException ex){
                    DisplayMessages.displayError(null, "ItemEntryModel.getCategoryDetails()." +ex, "Database Error");
                }
            }
            
        }
        catch(SQLException | ArrayIndexOutOfBoundsException | NullPointerException ex){
            DisplayMessages.displayError(null, "ItemEntryModel.getCategoryDetails()." +ex, "Database Error");
        }
        finally{
            closeConnection();
        }
      
        Object[][] unitInfo = dbData.toArray(new Object[dbData.size()][]);
      
       return unitInfo;
    }
    
    public Object[][] getDistorDetails(){
        sql = "SELECT distributor_id, distributor_name FROM distributor";
        initConnection();
        ArrayList<Object[]> dbData = new ArrayList<Object[]>();
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            //ResultSetMetaData meta = rs.getMetaData();
            //int cols = meta.getColumnCount()
            while(rs.next()){
                Object[] data = new Object[] { rs.getObject("distributor_id"), rs.getObject("distributor_name")};
                dbData.add(data);
            }
        }
        catch(SQLException ex){
            DisplayMessages.displayError(null, "ItemEntryModel.getCategoryDetails()." +ex, "Database Error");
        }
        finally{
            closeConnection();
        }
        
        Object[][] distro = dbData.toArray( new Object[dbData.size()][]);
        
        return distro;
    }


    public DefaultTableModel getAllItemInfo(Object[][] unitDetails){
        initConnection();
        sql = "SELECT item_id,item_name,sub_category_name,unit_name,total_qty,item_buy_rate,item_expiry_date,item_threshold,\n" +
"                distributor_name,item_entry_date FROM centerstore_stock, item_sub_category, distributor, item_unit \n" +
"               WHERE centerstore_stock.category_id = item_sub_category.sub_category_id and centerstore_stock.unit_id = item_unit.unit_id and centerstore_stock.distributor_id = distributor.distributor_id ORDER BY centerstore_stock.item_entry_date DESC";
        
        //for storing data from data as object
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        try{
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            int rows = getNumberOfRows(rs);
            int cols = rs.getMetaData().getColumnCount();
           // obj = new Object[rows][cols];
            //table models size;
            tModel = new DefaultTableModel(rows, cols);
            //int i = 1; //for tracking rows;
            while(rs.next()){
                Object uni = rs.getObject("unit_name");
                float tQty = rs.getFloat("total_qty");
                float rQty = 0;
                for(int i = 0; i < unitDetails.length; i++){
                    if(unitDetails[i][1].equals(uni)){
                        rQty = Float.parseFloat(unitDetails[i][2].toString());
                        break;
                    }
                }
                
                float stock = (float) tQty/rQty;
                
                Object[] row = new Object[]{rs.getObject("item_id"),rs.getObject("item_name"),rs.getObject("sub_category_name"),
                    rs.getObject("unit_name"), stock, rs.getObject("item_buy_rate"),rs.getObject("item_threshold"),
                    rs.getObject("distributor_name"), rs.getObject("item_expiry_date"),rs.getObject("item_entry_date")     
                };//,
                    
                data.add(row); // add each object to data to make array of array list
            }
            //convert arrlist to array of size data object
            entryTable = data.toArray(new Object[data.size()][]);
            
            //System.out.println(finalData);
            tModel = new DefaultTableModel(entryTable,
                    new String[]{"Id","Name","Category","Unit","Stock","Buy Rate", "Threshold", "Distributor", "Expiry Date", "Entry Date"}){
                         @Override
                         public boolean isCellEditable(int rows,int columns){
                             return false;    
                        }
                    };
            //System.out.println(tModel.toString());
        }
        catch(SQLException ex){
            DisplayMessages.displayError(null, "ItemEntryModel.getAllItemInfo()." +ex, "Database Error");
        }
        finally{
            closeConnection();
        }
        return tModel;
    }
    
    /*
    
    */
    public DefaultTableModel getSearchItemInfo(String search, Object[][] unitDetails){
        String key = search+"%";
        initConnection();
        sql = "SELECT item_id,item_name,sub_category_name,unit_name,total_qty,item_buy_rate,item_expiry_date,item_threshold,\n" +
"                distributor_name,item_entry_date FROM centerstore_stock, item_sub_category, distributor, item_unit \n" +
"               WHERE centerstore_stock.category_id = item_sub_category.sub_category_id and centerstore_stock.unit_id = item_unit.unit_id and centerstore_stock.distributor_id = distributor.distributor_id and item_name LIKE ? ORDER BY centerstore_stock.item_entry_date DESC";
        
        //for storing data from data as object
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        try{
            preStmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //preStmt = conn.createStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            preStmt.setString(1, key);
            rs = preStmt.executeQuery();
            int rows = getNumberOfRows(rs);
            int cols = preStmt.getMetaData().getColumnCount();
           // obj = new Object[rows][cols];
            //table models size;
            tModel = new DefaultTableModel(rows, cols);
            //int i = 1; //for tracking rows;
            while(rs.next()){
                Object uni = rs.getObject("unit_name");
                float tQty = rs.getFloat("total_qty");
                float rQty = 0;
                for(int i = 0; i < unitDetails.length; i++){
                    if(unitDetails[i][1].equals(uni)){
                        rQty = Float.parseFloat(unitDetails[i][2].toString());
                        break;
                    }
                }
                
                float stock = (float) tQty/rQty;
                
                Object[] row = new Object[]{rs.getObject("item_id"),rs.getObject("item_name"),rs.getObject("sub_category_name"),
                    rs.getObject("unit_name"), stock, rs.getObject("item_buy_rate"),rs.getObject("item_threshold"),
                    rs.getObject("distributor_name"), rs.getObject("item_expiry_date"),rs.getObject("item_entry_date")     
                };//,
                    
                data.add(row); // add each object to data to make array of array list
            }
            //convert arrlist to array of size data object
            entryTable = data.toArray(new Object[data.size()][]);
            
            //System.out.println(finalData);
            tModel = new DefaultTableModel(entryTable,
                    new String[]{"Id","Name","Category","Unit","Stock","Buy Rate", "Threshold", "Distributor", "Expiry Date", "Entry Date"}){
                         @Override
                         public boolean isCellEditable(int rows,int columns){
                             return false;    
                        }
                    };
            //System.out.println(tModel.toString());
        }
        catch(SQLException ex){
            DisplayMessages.displayError(null, "ItemEntryModel.getSearchItemInfo()." +ex, "Database Error");
        }
        finally{
            closeConnection();
        }
        return tModel;
    }
    
       //database opertion main
    public void addItemEntery(String[] txtItem, String[] ids, String category){
        //System.out.println(itemName);
        //itemEntry = intInfo;
        /*
         * txtItem
         * 0 name, 1 qty, 2 buy, 3 ex-date, 4 threshodl
         * ids
         * 0 category, 1 unit, 2 unit relative qty,3 distro
         */
        float tQty = Float.parseFloat(txtItem[1]) * Float.parseFloat(ids[2]);
        sql= "INSERT INTO centerstore_stock (item_name, category_id, unit_id, total_qty, item_buy_rate, item_expiry_date,"
                + "item_threshold, distributor_id, item_entry_date)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        initConnection();
        try{
            /*
             * 1 item name, 2 cateogry, 3 unit, 4 total qty, 5 buy rate, 6 expiry date, 6 threshold, 7 distributor, 8 entry date;
             */
            preStmt = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            preStmt.setString(1, txtItem[0]); //name
            preStmt.setInt(2, Integer.parseInt(ids[0])); //category id
            preStmt.setInt(3, Integer.parseInt(ids[1])); //unit id
            preStmt.setFloat(4, tQty); // total quantity
            preStmt.setFloat(5, Float.parseFloat(txtItem[2]));
            purchaseDate = getPurchaseDate();
            
            preStmt.setString(6,txtItem[3]);
           
            preStmt.setInt(7, Integer.parseInt(txtItem[4])); //threshold
            preStmt.setInt(8, Integer.parseInt(ids[3])); //distributor
            purchaseDate = getPurchaseDate();
            preStmt.setTimestamp(9,purchaseDate); //new Timestamp(new Date().getTime()));
           
            
            
            preStmt.executeUpdate();
            
            //int[] purchase = {intInfo[0], intInfo[1],intInfo[6]};
            boolean  check = addToPurchase(txtItem, ids);
            if(check == false){
                DisplayMessages.displayError(null, "Execution Interrupted, Operation Failed!", "Database Error");
            }
            else{
                conn.commit();
                conn.setAutoCommit(true);
                DisplayMessages.displayInfo(null, "Item Added Successfully!", "Success");
            }
         
        }
        catch(SQLException ex){
            DisplayMessages.displayError(null, "ItemEntryModel.addItemEntery()." +ex, "Database Error");
        }
        finally{
            closeConnection();
            
        }
    }
    
    public void updateItemEntery(int id, String[] txtItem, int cId){
        //float tQty = Float.parseFloat(txtItem[1]) * Float.parseFloat(ids[2]);
       sql = "Update centerstore_stock SET item_name = ?, category_id = ? WHERE item_id = ?";
        initConnection();
        try{
        /*
         * txtItem
         * 0 name, 1 qty, 2 buy, 3 ex-date, 4 threshodl
         * ids
         * 0 category, 1 unit, 2 unit relative qty,3 distro
         */
            preStmt = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            preStmt.setString(1, txtItem[0]); //name
            preStmt.setInt(2, cId);
            preStmt.setInt(3, id);
           /*
            preStmt.setString(2, ids[0]); //category Id
            preStmt.setString(3, ids[1]); //unitId
            preStmt.setBigDecimal(4, new BigDecimal(tQty).setScale(3, RoundingMode.HALF_UP)); //total qty
            preStmt.setBigDecimal(5, new BigDecimal(txtItem[2]).setScale(2, RoundingMode.HALF_UP)); //buy rate
            preStmt.setTimestamp(6, new Timestamp(new Date().getTime())); //expiry date
            preStmt.setInt(7, Integer.parseInt(txtItem[4])); //threshold
            preStmt.setString(8, ids[3]); // distributor id
           // preStmt.setTimestamp(9, new Timestamp(new Date().getTime()));
            preStmt.setString(9, id); //item id
            //int[] purchase = {intInfo[6],intInfo[]
          
          */
            preStmt.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
            DisplayMessages.displayInfo(null, "Item Successfully Updated!" , "Success");
            //updateToPurchase();
           /* boolean ok = updateToPurchase(id);
            if(ok){
                conn.commit();
                conn.setAutoCommit(true);
            }
            else{
                displayErrMsg(null, "Operation Failed !");
                conn.setAutoCommit(true);
            }
            * */
        }
        catch(SQLException ex){
            DisplayMessages.displayError(null, "ItemEntryModel.updateItem." +ex, "Database Error");
            return;
            
        }
        finally{
            closeConnection();
        }
      
    }
    
    public void deleteItemEntery(String id){
        sql = "DELETE FROM centerstore_stock WHERE item_id = ?";
        initConnection();
        try{
            preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, Integer.parseInt(id));
            preStmt.executeUpdate();
            DisplayMessages.displayInfo(null, "Item Successfully Deleted !", "Success");
        }
        catch(SQLException ex){
            DisplayMessages.displayError(null, "ItemEntryModel.deleteItemEntry()." +ex, "Database Error");
        }
        finally{
            closeConnection();
        }
        
    }
    public boolean addToPurchase(String[] txtItem, String[] ids){
         /*
         * txtItem
         * 0 name, 1 qty, 2 buy, 3 ex-date, 4 threshodl
         * ids
         * 0 category, 1 unit, 2 unit relative qty,3 distro
         */
        String itemId = getItemId(txtItem[0]);
        if(itemId.isEmpty())
               return false;
        
            sql = "INSERT INTO purchase (item_id, unit_id, buy_rate, quantity, total_amount,purchase_date,distributor_id) VALUES(?,?,?,?,?,?,?)";
           // initConnection();
            try{
                //1 itemid, 2 unit id, 3 buy rate, 4 quantity, 5 total amt, 6 date, 7 distributor
                int  qty = Integer.parseInt(txtItem[1]);
                float buy = Float.parseFloat(txtItem[2]) ;
                float tAmount = (float)qty * buy;
                
                PreparedStatement preStmtP = conn.prepareStatement(sql);
                conn.setAutoCommit(false);
                preStmtP.setInt(1, Integer.parseInt(itemId)); // item id;
                preStmtP.setInt(2,Integer.parseInt(ids[1])); //unid id;
                preStmtP.setFloat(3, buy);
                preStmtP.setInt(4, qty);
                preStmtP.setFloat(5,tAmount);
                preStmtP.setTimestamp(6, purchaseDate);
                preStmtP.setInt(7, Integer.parseInt(ids[3]));
               
                //preStmt.setInt(9, id);
                preStmtP.executeUpdate();
                //conn.setAutoCommit(true);
               // JOptionPane.showMessageDialog(null, "hahahha");
            }
            catch(SQLException ex){
                DisplayMessages.displayError(null, "ItemEntryModel.addToPurchase()." +ex, "Database Error");
                return false;
            }
            return true;
    }
    
   public boolean updateToPurchase(String id){
       sql= "UPDATE purchase SET item_category = ?, quantity = ?,unit_name = ?,buy_rate = ?,total_qty = ? ,distributor_id = ? WHERE item_id = ?";
       try{
           preStmt = conn.prepareStatement(sql);
           conn.setAutoCommit(false);
           preStmt.setString(1, category);
           preStmt.setInt(2, qty);
           preStmt.setString(3, unit);
           preStmt.setInt(4, bRate);
           preStmt.setInt(5, bRate * qty);
           preStmt.setString(6, distroId);
           preStmt.setInt(7, Integer.parseInt(id));
           preStmt.executeUpdate();
       }
       catch(SQLException sql){
DisplayMessages.displayError(null, "ItemEntryModel.updateToPurchase()." +sql, "Database Error");
           return false;
       }
       return true;
   }
   
   public Timestamp getPurchaseDate(){
       return new Timestamp(new Date().getTime());
   }
  
   /*
   public void setItemInfo(String[] purchase){
       iName = purchase[0];
       itemId = purchase[0]; //for update case;
        category = purchase[1];
         qty = Integer.parseInt(purchase[2]);
         unit = purchase[3];
         bRate = Integer.parseInt(purchase[4]);
         total = qty * bRate;
         distorId = Integer.parseInt(purchase[5]);
   }
  */ 
   public String getItemId(String iName){
          sql = "SELECT item_id FROM centerstore_stock WHERE item_name=?";
        String id = new String();
        //initConnection();
        //System.out.println("getItemId");
        try{
           PreparedStatement preStmtId = conn.prepareStatement(sql);
            preStmtId.setString(1,iName);
            rs = preStmtId.executeQuery();
            while(rs.next()){
               id = rs.getString("item_id");
            }
        }
        catch(SQLException sql){
DisplayMessages.displayError(null, "ItemEntryModel.getItemId()." +sql, "Database Error");
        }
        
        return id;
   }
   
   public int getNumberOfRows(ResultSet rs){
       int n = 0;
        try{ rs.last();
          n = rs.getRow();
            rs.beforeFirst();
        }
        catch(SQLException ex){
            DisplayMessages.displayError(null, "ItemEntryModel.getNumberOfRows()." +ex, "Database Error");
        }
        return n;
    }
   
   public Object[][] getEntryTable(){
       return entryTable;
   }
}
