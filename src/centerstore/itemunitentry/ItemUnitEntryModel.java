/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.itemunitentry;

import database.DBConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;



/**
 *
 * @author SUSHIL
 */
public class ItemUnitEntryModel extends DBConnect {
    
    public String strQuery = null;
    private PreparedStatement stmtAddUnit;
    private PreparedStatement stmtDeleteUnit;
    private PreparedStatement stmtUpdateUnit;
    private PreparedStatement stmtUnitInfo;
    private PreparedStatement stmtDisplayJTable;
    private Statement stmt= null;
    ResultSet rsQuery;
   private ResultSet rsItemUnit = null;
   private DefaultTableModel tbModel;
    
    
    void addItemUnit(String TableName,String UnitName,int UnitRelativeQuantity,String UnitType) {
        
        strQuery = "INSERT INTO "+TableName+" (unit_name, unit_relative_quantity, unit_type) VALUES( ?, ?, ?)";
//       DBConnect add = new DBConnect();
      //  strQuery = "INSERT INTO "+TableName+" (unit_name, unit_relative_quantity, unit_type) VALUES("+UnitName+", "+UnitRelativeQuantity+" , "+UnitType+")";
                    try{
                         
                       initConnection();
                        stmtAddUnit = conn.prepareStatement(strQuery);

                     conn.setAutoCommit(false);
                      // stmtAddItemUnit.setString(1,TableName) ;
                       stmtAddUnit.setString(1, UnitName);
                        stmtAddUnit.setInt(2, UnitRelativeQuantity);
                        stmtAddUnit.setString(3, UnitType);
                        stmtAddUnit.executeUpdate();
                       conn.commit();
                        JOptionPane.showMessageDialog(null,"Item Unit Added Successfully");
                       conn.setAutoCommit(true);
                        
                    }
        
                    catch(SQLException se){
                       JOptionPane.showMessageDialog(null, se);
                       // System.err.println(se);
                    }
                                catch(Exception e){
                                    JOptionPane.showMessageDialog(null,e);
                                   // System.err.println(e);
                                }
                    finally{
                        try{
                        if(stmtAddUnit!=null){
                            stmtAddUnit.close();
                        }
                        closeConnection();
                        }
                        catch(SQLException sy){
                            JOptionPane.showMessageDialog(null,sy);
                        }
                    
                   }
    }
    /**
     * Setting the value to thier respective textfields after selecting jtable
     */
    
    /**
     * delete Unit Item with after seleting row in jtable
     * @param TableName
     * @param UnitID 
     */ 
    
     void deleteUnitItem(String TableName,int UnitID) {
        
       // strQuery = "INSERT INTO "+TableName+" (unit_name, unit_relative_quantity, unit_type) VALUES( ?, ?, ?)";
//       DBConnect ad = new DBConnect();
        strQuery = " DELETE FROM "+TableName+" WHERE unit_id = ?";
                    try{
                         
                       initConnection();
                        stmtDeleteUnit = conn.prepareStatement(strQuery);

                      conn.setAutoCommit(false);
                       stmtDeleteUnit.setInt(1,UnitID);
                       
                        stmtDeleteUnit.executeUpdate();
                       conn.commit();
                        JOptionPane.showMessageDialog(null,"Item Unit Deleted Succesfully");
                        
                        //ad.conn.setAutoCommit(true);
                        
                        
                    }
                    
                    catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException je){
                        JOptionPane.showMessageDialog(null, "There Exists a Item Which contain this Unit.So Deletion of this Item is not Allowed.");
                       
                    }
                    catch(SQLException | NumberFormatException se){
                       JOptionPane.showMessageDialog(null, se);
                       // System.err.println(se);
                    }
                                    finally{
                        try {
                            if(stmtDeleteUnit!=null){
                        stmtDeleteUnit.close();
                        
                        }
                            
                       closeConnection();
                        }
                        catch(SQLException si){
                         JOptionPane.showMessageDialog(null, si);
                        }
                        
                    }
    }
   public  void updateUnitItem(String TableName,int UnitID,String UnitName,int UnitRelativeQuantity, String UnitType) {
//       DBConnect upd = new DBConnect();  
       strQuery = "UPDATE "+TableName+" SET  unit_name = ?, unit_relative_quantity = ?, unit_type = ? WHERE unit_id = ?";
    try{
        
        initConnection();
        stmtUpdateUnit = conn.prepareStatement(strQuery);
        conn.setAutoCommit(false);
        stmtUpdateUnit.setString(1,UnitName);
        stmtUpdateUnit.setInt(2,UnitRelativeQuantity);
        stmtUpdateUnit.setString(3, UnitType);
        stmtUpdateUnit.setInt(4, UnitID);
        stmtUpdateUnit.executeUpdate();
       conn.commit();
        JOptionPane.showMessageDialog(null, "Item Unit was Updated.");
       conn.setAutoCommit(true);
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se);
    }
    catch(Exception e) {
        JOptionPane.showMessageDialog(null,e+"from updateUnitItem");
    }
    finally{
        try{
        if(stmtUpdateUnit!=null){
            stmtUpdateUnit.close();
        }
       closeConnection();
        }
        catch(SQLException see){
            JOptionPane.showMessageDialog(null,see);
        }
    }
     }
   //retrive data fro mitem unit
   public TableModel getTableModelForJTable(String TableName) {
      
       
       
       int colcount ;
        // String[] columnNames = { "ItemID", "Item Unit", "Item Relative Quantity", "Item Type" };
        // Vector<String> columnNames = new Vector();
         String columnNames[] = {"ItemID","Item Unit","Item Relative Quantity","Item Type"};
       /*  columnNames.add("ItemID");
         columnNames.add("Item Unit");
         columnNames.add("Item Relative Quantity");
         columnNames.add("Item Type");
         Vector<Vector<Object>> data = new Vector<Vector<Object>>();
         * */
         
         ArrayList<Object[]> data = new ArrayList<Object[]>();
         Object[][] finaldata = null ;
            strQuery = "SELECT unit_id,unit_name,unit_relative_quantity,unit_type FROM item_unit";//+TableName;
            
                    
//            DBConnect test = new DBConnect(); 
            try{
                        
                       initConnection();
                        stmtDisplayJTable = conn.prepareStatement(strQuery,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

                       //test.conn.setAutoCommit(false);
                        
                       rsQuery =  stmtDisplayJTable.executeQuery();
                      /* rsQuery.last();
                       int row = rsQuery.getRow();
                       rsQuery.beforeFirst();
                       if(row < 0)
                       System.out.println(row);    //test.conn.commit();
                       // tblUnitJTable.setModel(DbUtils.resultSetToTableModel(rsQuery));
                       * */
                       ResultSetMetaData metaData = rsQuery.getMetaData();
                       colcount = metaData.getColumnCount();
                        
                       while(rsQuery.next()){
                          // Object[] row ;
                       /*  Vector<Object> vector = new Vector<Object>();
                         for(int i =1;i<=colcount;i++){
                             vector.add(rsQuery.getObject(i));
                        }
*/
                           Object[] row = new Object[]{rsQuery.getInt("unit_id"),rsQuery.getString("unit_name"),rsQuery.getInt("unit_relative_quantity"),rsQuery.getString("unit_type")};
                        data.add(row);
                       }
                       
                        
                finaldata = data.toArray(new Object[data.size()][]);
           // tbModel = new DefaultTableModel(data,columnNames);
                     
                    }
        
                    catch(SQLException se){
                       JOptionPane.showMessageDialog(null, se);
                       // System.err.println(se);
                    }
                    
                                catch(Exception e){
                                    JOptionPane.showMessageDialog(null,e);
                                   // System.err.println(e);
                                }
            
                    finally{
                closeConnection();
                    /*    if(stmtDisplayJTable!=null){
                            try {
                               // stmtDisplayJTable.close();
                            } catch (SQLException ex) {
                                //Logger.getLogger(ItemUnitEntryModel.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(null, ex);
                            }
                        } 
                        test.closeConnection();
                      */
                    }
                   return new DefaultTableModel(finaldata,columnNames){
            @Override
            public boolean isCellEditable(int row,int columns){
                //all cells false
                return false;
            }
        };
    }

     /**
     *
     * @param TableName
     * @param UnitName
     * @return
     */
    public int getUnitIDByQuery(String TableName,String UnitName) {
        int retrieve = 0 ;
        //String strretrieve;
         strQuery = "SELECT unit_id FROM "+TableName+" WHERE unit_name = ?";
//          DBConnect  test = new DBConnect();
       // System.out.println(strQuery);
         try{
            initConnection();
             stmtUnitInfo =conn.prepareStatement(strQuery);
             conn.setAutoCommit(false);
             stmtUnitInfo.setString(1,UnitName);
            // stmtUnitInfo.setString(2,UnitName);
             rsItemUnit = stmtUnitInfo.executeQuery();
             conn.commit();
             while(rsItemUnit.next()){
                retrieve =  rsItemUnit.getInt("unit_id");
             }
            conn.setAutoCommit(true);
//           conn.close();
               // return retrieve;  
            // JOptionPane.showMessageDialog(null, UnitName);
             
         }
         catch(SQLException se){
             JOptionPane.showMessageDialog(null,se);
             
         }
         catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
         finally{
            
           closeConnection();
             
         }
        return retrieve;
     }
    /**
     * return true if there is duplicate value in database otherwise
     * return false
     * @param TableName
     * @param ColumnName
     * @param Value
     * @return 
     */
    public void checkforDuplicate(String TableName,String ColumnName,String Value){
//        DBConnect check = new DBConnect();
        boolean flag = false;
        PreparedStatement stmtcheck = null;
        ResultSet rscheck;
        String strcheck;
        try{
            strcheck = "SELECT unit_name FROM "+TableName+" WHERE unit_name= 'boras'";
            stmtcheck = conn.prepareStatement(strcheck);
            //check.conn.setAutoCommit(false);
           // stmtcheck.setString(1, ColumnName);
           // stmtcheck.setString(2, ColumnName);
          //  stmtcheck.setString(3, Value);
            rscheck = stmtcheck.executeQuery();
//            conn.commit();
          //  check.conn.setAutoCommit(true);
           // System.out.println(rscheck.getRow());
           /* if(rscheck.getRow()==0){
                flag= true;
            }
            else{
                flag=false;
            }
            * */
            while(rscheck.next()){
                System.out.println(rscheck.getObject("unit_name"));
            }
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from check dulpicate");
        }
        finally{
            try{
            if(stmtcheck!=null){
                stmtcheck.close();
            }
            closeConnection();
            }
             catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from check dulpicate");
        }
           
        }
      //  return flag;
        
    }
    
}

