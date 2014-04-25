/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.waiter;

import database.DBConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SUSHIL
 */
public class WaiterModel extends DBConnect {
    private PreparedStatement stmtAdd;
    private PreparedStatement stmtEdit;
    private PreparedStatement stmtDelete;
    private String strQuery = new String();
    private ResultSet rsSet;
    
    
    public void AddWaiter(String[] data){
        /*
         *  info[0] = getWaiterId();
        info[1] = getWaiterName();
        info[2] = getWaiterPhone();
        info[3] = getWaiterAddress();
         */
        strQuery = "INSERT INTO waiter_info (waiter_name,waiter_phone,waiter_address,designation_id) VALUES(?,?,?,?)";
//        DBConnect waiterAdd = new DBConnect();
        try{
            initConnection();
          conn.setAutoCommit(false);
            stmtAdd = conn.prepareStatement(strQuery);
            stmtAdd.setString(1, data[1]);
            stmtAdd.setString(2, data[2]);
            stmtAdd.setString(3, data[3]);
            stmtAdd.setInt(4, Integer.parseInt(data[4]));
            stmtAdd.executeUpdate();
            
            conn.commit();
            JOptionPane.showMessageDialog(null, "Waiter Added Successfully");
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null , se+"From AddWaiter");
        }
        finally{
            closeConnection();
        }
    
    
        }
    
    
    public void EditWaiter(String[] data){
        /*
         *  info[0] = getWaiterId();
        info[1] = getWaiterName();
        info[2] = getWaiterPhone();
        info[3] = getWaiterAddress();
         */
        strQuery = "UPDATE  waiter_info  SET waiter_name = ?,waiter_phone = ?,waiter_address = ?,designation_id = ? WHERE waiter_id = ?";
//        DBConnect waiterAdd = new DBConnect();
        try{
           initConnection();
           conn.setAutoCommit(false);
            stmtAdd = conn.prepareStatement(strQuery);
            stmtAdd.setString(1, data[1]);
            stmtAdd.setString(2, data[2]);
            stmtAdd.setString(3, data[3]);
            stmtAdd.setInt(4, Integer.parseInt(data[4]));
            stmtAdd.setInt(5,Integer.parseInt(data[0]));
            stmtAdd.executeUpdate();
            
           conn.commit();
            JOptionPane.showMessageDialog(null, "Waiter Edited Successfully");
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null , se+"From EditWaiter");
        }
        finally{
           closeConnection();
        }
    
    
        }
     public void DeleteWaiter(String[] data){
        /*
         *  info[0] = getWaiterId();
        info[1] = getWaiterName();
        info[2] = getWaiterPhone();
        info[3] = getWaiterAddress();
         */
        strQuery = "DELETE FROM  waiter_info   WHERE waiter_id = ?";
//        DBConnect waiterAdd = new DBConnect();
        try{
           initConnection();
           conn.setAutoCommit(false);
            stmtAdd = conn.prepareStatement(strQuery);
            stmtAdd.setInt(1,Integer.parseInt(data[0]));
            stmtAdd.executeUpdate();
            
            conn.commit();
            JOptionPane.showMessageDialog(null, "Waiter Deleted Successfully");
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null , se+"From DeleteWaiter");
        }
        finally{
           closeConnection();
        }
    
    
        }
    public DefaultTableModel getTableModelWaiterInfo(){
      //   int colcount;
     //  int rowcount ;
      // String[] ColumnNames = { "Issue Id", "Item Name", "Issue Quantity","item BaseUnit","Issue From", "Issue To","Issue Date"};
       String strQuery = "SELECT waiter_id,waiter_name,waiter_phone,waiter_address,designation_info.designation_title FROM waiter_info INNER JOIN designation_info ON designation_info.designation_id = waiter_info.designation_id ORDER BY waiter_id ";
       String ColumnNames[] = {" Id"," Name","Phone"," Address","Post"};
      //Object[][] data = null;
     //  List<Object[]> data = new ArrayList<Object[]>();
   ArrayList<Object[]> data = new ArrayList<Object[]>();
    Object[][] finalData =null;
//       DBConnect getWaiter = new DBConnect();
       ResultSet rsResult;
       PreparedStatement stmtIssueInfo;
       try{
           initConnection();
           stmtIssueInfo = conn.prepareStatement(strQuery);
           rsResult = stmtIssueInfo.executeQuery();
           
          // ResultSetMetaData metadata = rsResult.getMetaData();
         //  colcount = metadata.getColumnCount();
            
           while(rsResult.next()){
              Object[] row = new Object[]{rsResult.getString("waiter_id"),rsResult.getString("waiter_name"),rsResult.getString("waiter_phone"),rsResult.getString("waiter_address"),rsResult.getString("designation_title")};
              /* for(int i=0;i<colcount;i++){
                   row[i] = rsResult.getObject(i+1);
               }*/
              
               data.add(row);
               
              // data
              
           }
            finalData = data.toArray(new Object[data.size()][]);
           
       }
        catch(Exception se){
            JOptionPane.showMessageDialog(null, se+"form getTableModelWaiterInfo");
        }
         finally{
          closeConnection();
       }
       return new DefaultTableModel(finalData,ColumnNames){
          @Override
          public boolean isCellEditable(int rows,int columns){
          //all cell false
                  return false;    
          }
                  
       };
    }
    public Object[][] getDesignationInfo(){
      //   int colcount;
     //  int rowcount ;
      // String[] ColumnNames = { "Issue Id", "Item Name", "Issue Quantity","item BaseUnit","Issue From", "Issue To","Issue Date"};
       String strQuery = "SELECT designation_id,designation_title FROM designation_info ORDER BY designation_id ";
//      ArrayList<Object[]> list = new ArrayList<>();
      
      //Object[][] data = null;
     //  List<Object[]> data = new ArrayList<Object[]>();
   ArrayList<Object[]> data = new ArrayList<Object[]>();
    Object[][] finalData =null;
//       DBConnect getWaiter = new DBConnect();
       ResultSet rsResult;
       PreparedStatement stmtIssueInfo;
       try{
           initConnection();
           stmtIssueInfo = conn.prepareStatement(strQuery);
           rsResult = stmtIssueInfo.executeQuery();
           
          // ResultSetMetaData metadata = rsResult.getMetaData();
         //  colcount = metadata.getColumnCount();
            
           while(rsResult.next()){
              Object[] row = new Object[]{rsResult.getInt("designation_id"),rsResult.getString("designation_title")};
              /* for(int i=0;i<colcount;i++){
                   row[i] = rsResult.getObject(i+1);
               }*/
              
               data.add(row);
               
              // data
              
           }
            finalData = data.toArray(new Object[data.size()][]);
           
       }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"form getDesignationInfo"+getClass().getName());
        }
         finally{
          closeConnection();
       }
       return finalData;
     
      }  
    public boolean checkExistingName(String menuname){
     Boolean ExistingStatus = null; 
    String strCheck = "SELECT waiter_name FROM waiter_info WHERE waiter_name = ? ";
//    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
      initConnection();
        stmtcheck = conn.prepareStatement(strCheck,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        stmtcheck.setString(1, menuname);
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
     public boolean checkExistingDesignation(String menuname){
     Boolean ExistingStatus = null; 
    String strCheck = "SELECT designation_title FROM designation_info WHERE designation_title = ? ";
//    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
      initConnection();
        stmtcheck = conn.prepareStatement(strCheck,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        stmtcheck.setString(1, menuname);
        ResultSet rs = stmtcheck.executeQuery();
        rs.last();
        int rows = rs.getRow();
        rs.beforeFirst();
        ExistingStatus = rows >= 1;
        
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from checkExistingDesignation");
    }
    finally{
       closeConnection();
    }
    return ExistingStatus;
    
}
    //designation model
     public void AddDesignation(String[] data){
        /*
         *   info[0] = String.valueOf(getDesignationId());
        info[1] = getDesignationTitle();
        info[2] = getDescription();
         */
        strQuery = "INSERT INTO designation_info (designation_title,designation_description,waiter_flag) VALUES(?,?,?)";
//        DBConnect waiterAdd = new DBConnect();
        try{
            initConnection();
          conn.setAutoCommit(false);
            stmtAdd = conn.prepareStatement(strQuery);
            stmtAdd.setString(1, data[1]);
            stmtAdd.setString(2, data[2]);
            stmtAdd.setBoolean(3, Boolean.parseBoolean(data[3]));
            stmtAdd.executeUpdate();
            
            conn.commit();
            JOptionPane.showMessageDialog(null, "Designation Added Successfully");
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null , se+"From designation_add"+getClass().getName());
        }
        finally{
            closeConnection();
        }
    
    
        }
      public void EditDesignation(String[] data){
        /*
         * info[0] = String.valueOf(getDesignationId());
        info[1] = getDesignationTitle();
        info[2] = getDescription();
         */
        strQuery = "UPDATE  designation_info  SET designation_title = ?,designation_description = ?,waiter_flag = ? WHERE designation_id = ?";
//        DBConnect waiterAdd = new DBConnect();
        try{
           initConnection();
           conn.setAutoCommit(false);
            stmtAdd = conn.prepareStatement(strQuery);
            stmtAdd.setString(1, data[1]);
            stmtAdd.setString(2, data[2]);
            stmtAdd.setBoolean(3, Boolean.parseBoolean(data[3]));
            stmtAdd.setInt(4,Integer.parseInt(data[0]));
            stmtAdd.executeUpdate();
            
           conn.commit();
            JOptionPane.showMessageDialog(null, "Designation Edited Successfully");
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null , se+"From DesignationEdit"+getClass().getName());
        }
        finally{
           closeConnection();
        }
    
    
        }
      public void DeleteDesignation(String[] data){
        /*
         *  info[0] = getWaiterId();
        info[1] = getWaiterName();
        info[2] = getWaiterPhone();
        info[3] = getWaiterAddress();
         */
        strQuery = "DELETE FROM  designation_info   WHERE designation_id = ?";
//        DBConnect waiterAdd = new DBConnect();
        try{
           initConnection();
           conn.setAutoCommit(false);
            stmtAdd = conn.prepareStatement(strQuery);
            stmtAdd.setInt(1,Integer.parseInt(data[0]));
            stmtAdd.executeUpdate();
            
            conn.commit();
            JOptionPane.showMessageDialog(null, "Designation Deleted Successfully");
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null , se+"From DeleteDesignation"+getClass().getName());
        }
        finally{
           closeConnection();
        }
    
    
        }
       public DefaultTableModel getTableModelDesignationInfo(){
      //   int colcount;
     //  int rowcount ;
      // String[] ColumnNames = { "Issue Id", "Item Name", "Issue Quantity","item BaseUnit","Issue From", "Issue To","Issue Date"};
       String strQuery = "SELECT * FROM designation_info ORDER BY designation_id ";
       String ColumnNames[] = {"Designation Id","Designation Title","Designation Desc","Waiter Flag"};
      //Object[][] data = null;
     //  List<Object[]> data = new ArrayList<Object[]>();
   ArrayList<Object[]> data = new ArrayList<Object[]>();
    Object[][] finalData =null;
//       DBConnect getWaiter = new DBConnect();
       ResultSet rsResult;
       PreparedStatement stmtIssueInfo;
       try{
           initConnection();
           stmtIssueInfo = conn.prepareStatement(strQuery);
           rsResult = stmtIssueInfo.executeQuery();
           
          // ResultSetMetaData metadata = rsResult.getMetaData();
         //  colcount = metadata.getColumnCount();
            
           while(rsResult.next()){
              Object[] row = new Object[]{rsResult.getString("designation_id"),rsResult.getString("designation_title"),rsResult.getString("designation_description"),rsResult.getBoolean("waiter_flag")};
              /* for(int i=0;i<colcount;i++){
                   row[i] = rsResult.getObject(i+1);
               }*/
              
               data.add(row);
               
              // data
              
           }
            finalData = data.toArray(new Object[data.size()][]);
           
       }
        catch(Exception se){
            JOptionPane.showMessageDialog(null, se+"form getTableModelDesignationInfo"+getClass().getName());
        }
         finally{
          closeConnection();
       }
       return new DefaultTableModel(finalData,ColumnNames){
          @Override
          public boolean isCellEditable(int rows,int columns){
          //all cell false
                  return false;    
          }
          @Override
           public Class<?>  getColumnClass(int columnIndex){
           Class clazz = String.class;
           switch(columnIndex){
               case 3:
                   clazz = Boolean.class;
                   break;
                    }
           return clazz;
                }
             
                  
       };
       
      }  
}       
