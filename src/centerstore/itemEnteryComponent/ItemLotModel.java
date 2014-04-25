/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.itemEnteryComponent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
//import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import reusableClass.DisplayMessages;
/**
 *
 * @author MinamRosh
 */
public class ItemLotModel extends ItemEnteryModel {
     private String sql;
    private ResultSet rs;
    private Statement stmt;
    private PreparedStatement preStmt;
    private DefaultTableModel tModel;
    
    private String[] distroList;
    private String[] itemList;
    private String[] unitList;

    private int distroId;
    private int categoryId;
    private int unitId;
    private int subUId;
    
    public ItemLotModel(ItemLotView v){
        super(v);
    }
    
     /**
     *
     * @return
     */
    @Override
    public String[] isItemDuplicate(){
        sql = "SELECT item_name FROM centerstore_stock";
        initConnection();
        try{
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            int rows = getNumberOfRows(rs);
            
            itemList = new String[rows];
            int i = 0;
            while(rs.next()){
                itemList[i] = rs.getString("item_name");
                i++;
            }
        }
        catch(SQLException | ArrayIndexOutOfBoundsException ex){
                                DisplayMessages.displayError(null, "ItemLotModel.getItemList()."+ex, "Database Error");
        }
        return itemList;
    }
    
    /* @Override
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
                catch(ArrayIndexOutOfBoundsException | SQLException arr){
                    DisplayMessages.displayError(null, "ItemLotModel.getUnitDetails."+arr, "Form Error");
                }
            }
            
        }
        catch(SQLException | ArrayIndexOutOfBoundsException | NullPointerException ex){
                    DisplayMessages.displayError(null, "ItemLotModel.getUnitDetails."+ex, "Form Error");
        }
        finally{
            closeConnection();
        }
      
        Object[][] unitInfo = dbData.toArray(new Object[dbData.size()][]);
      
       return unitInfo;
    }
    */
    /*
    @Override
    public Object[][] getDistorDetails(){
        sql = "SELECT distributor_id, distributor_name FROM distributor";
        initConnection();
        ArrayList<Object[]> dbData = new ArrayList<>();
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
                    DisplayMessages.displayError(null, "ItemLotModel.getDistroDetails()."+ex, "SQL Error");
        }
        finally{
            closeConnection();
        }
        
        Object[][] distro = dbData.toArray( new Object[dbData.size()][]);
        
        return distro;
    }
    */
    public Object[][] getItemDetails(){
        sql = "SELECT item_id, item_name, unit_id, distributor_id, total_qty, item_expiry_date,item_threshold FROM centerstore_stock";
        ArrayList<Object[]> dbData = new ArrayList<Object[]>();
        initConnection();
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                Object[] row = new Object[]{rs.getObject("item_id"), rs.getObject("item_name"), 
                    rs.getObject("unit_id"),rs.getObject("distributor_id"), rs.getObject("total_qty"), 
                    rs.getObject("item_expiry_date"),rs.getObject("item_threshold")};
                dbData.add(row);
            }
            
        }
        catch(SQLException ex){
                    DisplayMessages.displayError(null, "ItemLotModel.getItemDetails."+ex, "Database Error");
        }
        finally{
            closeConnection();
        }
        Object[][] item = dbData.toArray(new Object[dbData.size()][]);
        
        return item;
    }
    
    /**
     *
     * @param item
     * @param txtField
     * @param unit
     * @param distro
     * @param status
     * @param lotId
     */
    public void updateToStoreAd(String[] item, String[] txtField, String[] unit, String[] distro){
     //item_expiry_date;
       sql = "UPDATE centerstore_stock SET   unit_id = ?, item_buy_rate = ?, item_threshold = ?, distributor_id = ?, total_qty = ?, item_expiry_date = ? WHERE item_id = ?";
        initConnection();
        try{
            int aQty; // quantity to be added
           aQty = Integer.parseInt(txtField[0]);
            float rQty = Float.parseFloat(unit[2]); // relative quantity;
           
            preStmt = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            preStmt.setInt(1, Integer.parseInt(unit[0])); //unitid
            preStmt.setBigDecimal(2, new BigDecimal(txtField[1]).setScale(2, RoundingMode.HALF_UP)); //buyrate
            //preStmt.setFloat(3, aQty); //qty
            preStmt.setInt(3,Integer.parseInt(txtField[3])); //threshold
            preStmt.setInt(4, Integer.parseInt(distro[0])); //distro id;
            preStmt.setBigDecimal(5, new BigDecimal( (float)(aQty * rQty) + Float.parseFloat(item[1])).setScale(3, RoundingMode.HALF_UP)); //total qty;
            preStmt.setObject(6,txtField[2]);
            
            preStmt.setInt(7, Integer.parseInt(item[0])); //id
            preStmt.executeUpdate();
            
            
            //true for add lot and false for update loot;
            
              boolean status = addToLot(item, txtField, unit, distro);
            if(status){
            
            
            conn.commit();
            conn.setAutoCommit(true);
                    DisplayMessages.displayInfo(null, "Item Successfully Added!", "Database Error");
            }
            else{
                DisplayMessages.displayError(null, "Execution Interrupted. Operation Failed", "Database Error");
            }
            
        }
        catch(SQLException | NumberFormatException ex){
                    DisplayMessages.displayError(null, "ItemLotModel.updateToStoreAd."+ex, "Database Error");
        }
        finally{
            closeConnection();
        }
    
    }
        //adding lot means update inventory
    /**
     *
     * @param item
     * @param txtField
     * @param unit
     * @param distro
     * @return
     */
    public boolean addToLot(String[] item, String[] txtField, String[] unit, String[] distro){
            //add to purchase lot
            sql = "INSERT INTO purchase (item_id,unit_id,buy_rate,quantity,distributor_id,purchase_date,total_amount) VALUES(?,?,?,?,?,?,?)";
           // initConnection();
            try{
                //itemid, category,qty,unit,buyrate,did
               PreparedStatement preStmtP = conn.prepareStatement(sql);
                conn.setAutoCommit(false);
                preStmtP.setInt(1, Integer.parseInt(item[0])); //item id
                preStmtP.setInt(2,Integer.parseInt(unit[0])); //unit id
                preStmtP.setBigDecimal(3,new BigDecimal(txtField[1]).setScale(2, RoundingMode.HALF_UP)); // buy rate
                preStmtP.setBigDecimal(4,new BigDecimal(Float.parseFloat(txtField[0])).setScale(2, RoundingMode.HALF_UP)); //quantity
                preStmtP.setInt(5, Integer.parseInt(distro[0])); //distro
                
                Timestamp date = getPurchaseDate();
                preStmtP.setTimestamp(6, date);
                preStmtP.setBigDecimal(7, new BigDecimal(Float.parseFloat(txtField[1]) * Float.parseFloat(txtField[0])).setScale(2, RoundingMode.HALF_UP));
                
                //preStmt.setInt(9, id);
                preStmtP.executeUpdate();
                conn.commit();
                //conn.setAutoCommit(true);
               // JOptionPane.showMessageDialog(null, "hahahha");
               
            }
            catch(SQLException | NumberFormatException  ex){
                                    DisplayMessages.displayError(null, "ItemLotModel.addToLot()."+ex, "Database Error");
                return false;
            }
            return true;
    }   
    
    /*
     * update stock;
     */
    
    /**
     *
     * @param lId ,lot id
     * @param item item id and total
     * @param txtField, text field data
     * @param unit, unit id and relative quantity
     * @param distro, distributor id
     * @param preQty, previous quantity for updating, get lot previous quantity;
     */
    public void updateToStoreUp(String lId,String[] item, String[] txtField, String[] unit, String[] distro, float preQty){
        //System.out.println(distro[0]);
        //System.out.println(distro[1]);
       sql = "UPDATE centerstore_stock SET   unit_id = ?, item_buy_rate = ?, item_threshold = ?, distributor_id = ?, total_qty = ?, item_expiry_date = ? WHERE item_id = ?";
       initConnection();
        try{
            float uQty = Float.parseFloat(txtField[0]); // update quantity
            float rUQty = Float.parseFloat(unit[2]); // realitve unit qty
            float result = (float) uQty * rUQty + Float.parseFloat(item[1]) - preQty;
            
            
            //System.out.println(uQty);
            /*
            System.out.println(item[0]);
            System.out.println(txtField[0]);
            System.out.println(txtField[2]);
            //System.out.println(result);
            */
            if( result < 0){
                    DisplayMessages.displayError(null, "Execution Interrupted, Operation Failed!", "Database Error");
               return;
            }

            preStmt = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            preStmt.setInt(1, Integer.parseInt(unit[0])); //unitid
            preStmt.setBigDecimal(2, new BigDecimal(txtField[1]).setScale(2, RoundingMode.HALF_UP)); //buyrate
            //preStmt.setInt(3,qty); //qty
            preStmt.setInt(3,Integer.parseInt(txtField[3])); //threshold
            preStmt.setInt(4, Integer.parseInt(distro[0])); //distro id;
            preStmt.setBigDecimal(5, new BigDecimal(result).setScale(3, RoundingMode.HALF_UP));
            preStmt.setObject(6, txtField[2]);
            preStmt.setInt(7, Integer.parseInt(item[0]));
           
           preStmt.executeUpdate();
           
           boolean status = updateToLot(lId,item,txtField,unit,distro);
           if(status){
               conn.commit();
               conn.setAutoCommit(true);
               DisplayMessages.displayInfo(null, "Item Successfully Updated!", "Success");
           }
           else{
            DisplayMessages.displayError(null, "Operation Failed!", "Error");
               return;
           }
        }
        catch(SQLException | NumberFormatException ex){
                    DisplayMessages.displayError(null, "ItemLotModel.updateToStoreUp()."+ex, "Database Error");
        }
        finally{
            closeConnection();
        }
    }
    /**
     *
     * @param lId
     * @param item
     * @param txtField
     * @param unit
     * @param distro
     * @return
     */
    public boolean updateToLot(String lId,String[] item, String[] txtField, String[] unit, String[] distro){
       
       // sql = "UPDATE centerstore_stock SET   unit_id = ?, item_buy_rate = ?, item_threshold = ?, distributor_id = ?, total_qty = ?, item_expiry_date = ? WHERE item_id = ?";
       // initConnection();
        //try{
            /*
             * int uQty = Integer.parseInt(txtField[0]); // update quantity
            float rUQty = Float.parseFloat(unit[2]); // realitve unit qty
            float result = (float) uQty * rUQty + Float.parseFloat(item[1]) - preQty;
            
            
            //System.out.println(item[0]);
            //System.out.println(txtField[2]);
            //System.out.println(result);
            if( result < 0){
               displayErrMsg(null, "Operation Failed !");
               return false;
            }
            */
           
            /*
            preStmt = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            preStmt.setString(1, unit[0]); //unitid
            preStmt.setBigDecimal(2, new BigDecimal(txtField[1]).setScale(2, RoundingMode.HALF_UP)); //buyrate
            //preStmt.setInt(3,qty); //qty
            preStmt.setInt(3,Integer.parseInt(txtField[3])); //threshold
            preStmt.setString(4, distro[0]); //distro id;
            preStmt.setBigDecimal(5, new BigDecimal(result).setScale(3, RoundingMode.HALF_UP));
            preStmt.setObject(6, txtField[2]);
            preStmt.setString(7, lId);
           
           preStmt.executeUpdate();
          */  
           //add to purchase lot
            sql = "UPDATE purchase SET item_id =?,unit_id =?, quantity = ?,buy_rate =?,total_amount=?,distributor_id=?  WHERE purchase_id = ?";
           // initConnection();
            try{
                //itemid, category,qty,unit,buyrate,did
                PreparedStatement preStmtP = conn.prepareStatement(sql);
                conn.setAutoCommit(false);
                preStmtP.setInt(1, Integer.parseInt(item[0])); //id
                preStmtP.setInt(2,Integer.parseInt(unit[0])); // unit name
                preStmtP.setBigDecimal(3,new BigDecimal(txtField[0]).setScale(2, RoundingMode.HALF_UP)); //qty;
                
                preStmtP.setBigDecimal(4, new BigDecimal(txtField[1]).setScale(2, RoundingMode.HALF_UP)); //buy rate;
                preStmtP.setBigDecimal(5, new BigDecimal(Float.parseFloat(txtField[1]) * Float.parseFloat(txtField[0])).setScale(2, RoundingMode.HALF_UP)); //total amount
                preStmtP.setInt(6,Integer.parseInt(distro[0]));
                
                preStmtP.setInt(7,Integer.parseInt(lId));
                
                //preStmt.setInt(9, id);
                preStmtP.executeUpdate();
                conn.commit();
                //conn.setAutoCommit(true);
               // JOptionPane.showMessageDialog(null, "hahahha");
            }
            catch(SQLException | NumberFormatException ex){
                                    DisplayMessages.displayError(null, "ItemLotModel.updateToLot()."+ex, "Database Error");
                return false;
            }
            
            //conn.commit();
            //conn.setAutoCommit(true);
            //displayInfoMsg(null, "Item Successfully Updated!");
         
          return true;
        //}
        /*
        
       */
    }
    
    public DefaultTableModel getPurchaseModel(){
        //lot_id, item_name, category, unit_name, buy_rate, quantity, stock, threshold, expiry_date, distributor;
        sql = "SELECT l.purchase_id, i.item_name, c.sub_category_name, u.unit_name, l.buy_rate, l.quantity, l.total_amount, i.item_threshold,\n" +
"                i.item_expiry_date, d.distributor_name, l.purchase_date FROM centerstore_stock as i, purchase as l, item_unit as u,\n" +
"                item_sub_category as c, distributor as d WHERE i.item_id = l.item_id AND u.unit_id = l.unit_id \n" +
"                AND c.sub_category_id = i.category_id AND l.distributor_id = d.distributor_id ORDER BY purchase_date DESC";           
        
        ArrayList<Object[]> data = new ArrayList<>();
        initConnection();
        
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                
                Object[] row = new Object[]{rs.getObject("purchase_id"),rs.getObject("item_name"), rs.getObject("sub_category_name"),
                    rs.getObject("unit_name"), rs.getObject("buy_rate"),rs.getObject("quantity"),rs.getObject("total_amount"), rs.getObject("item_threshold"),
                    rs.getObject("item_expiry_date"),rs.getObject("purchase_date"),rs.getObject("distributor_name")};
                data.add(row);
                
            }
        }
        catch(SQLException ex){
                                DisplayMessages.displayError(null, "ItemLotModel.getPurchaseModel."+ex, "Database Error");
        }
        catch(ArrayIndexOutOfBoundsException e){
            //fires this exception on edit so it must be mute
        }
        finally{
            closeConnection();
        }
        Object[][] fData = data.toArray(new Object[data.size()][]);
        String[] cols = new String[]{"Purchase Id", "Item", "Category", "Unit", "Buy Rate", "Quantity","Amount", "Threshold", "Expiry Date", "Purchase Date", "Distributor"}; 
        DefaultTableModel pModel = new DefaultTableModel(fData, cols){
          @Override
          public boolean isCellEditable(int rows,int columns){
               return false;    
         }
         };
        return pModel;
    }

   
    
}
