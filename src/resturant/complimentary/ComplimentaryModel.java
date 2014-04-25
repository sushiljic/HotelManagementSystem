/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.complimentary;

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
public class ComplimentaryModel extends DBConnect{
    PreparedStatement stmtAdd;
    String strQuery;
            
        public void AddComplimentary(String[] data){
        /*
        info[0] = getCustomerId();
        info[1] = getCustomerName();
        info[2] = getCustomerPhone();
        info[3] = getCustomerAddress();
        info[4] = getCustomerType();
         */
        strQuery = "INSERT INTO complimentary_info (complimentary_reason) VALUES(?)";
        DBConnect waiterAdd = new DBConnect();
        try{
            waiterAdd.initConnection();
            waiterAdd.conn.setAutoCommit(false);
            stmtAdd = waiterAdd.conn.prepareStatement(strQuery);
            stmtAdd.setString(1, data[1]);
          
            stmtAdd.executeUpdate();
            
            waiterAdd.conn.commit();
            JOptionPane.showMessageDialog(null, "Complimentary Added Successfully");
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null , se+"From ADDCOMPLIMETARY"+getClass().getName());
        }
        finally{
            waiterAdd.closeConnection();
        }
    
    
        }
          public void EditComplimentary(String[] data){
        /*
          info[0] = getCustomerId();
        info[1] = getCustomerName();
        info[2] = getCustomerPhone();
        info[3] = getCustomerAddress();
        info[4] = getCustomerType();
         */
        strQuery = "UPDATE  complimentary_info  SET complimentary_reason = ? WHERE complimentary_id = ?";
//        DBConnect waiterAdd = new DBConnect();
        try{
            initConnection();
           conn.setAutoCommit(false);
            stmtAdd = conn.prepareStatement(strQuery);
            stmtAdd.setString(1, data[1]);
            stmtAdd.setInt(2, Integer.parseInt(data[2]));
          
            stmtAdd.executeUpdate();
            
           conn.commit();
            JOptionPane.showMessageDialog(null, "Complimentary Edited Successfully");
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null , se+"From EditComplimentary"+getClass().getName());
        }
        finally{
            closeConnection();
        }
    
    
        }
          public void DeleteCustomer(String[] data){
        /*
      info[0] = getCustomerId();
        info[1] = getCustomerName();
        info[2] = getCustomerPhone();
        info[3] = getCustomerAddress();
        info[4] = getCustomerType();
         */
        strQuery = "DELETE  FROM   complimentary_info   WHERE complimentary_id= ?";
//        DBConnect waiterAdd = new DBConnect();
        try{
           initConnection();
           conn.setAutoCommit(false);
            stmtAdd = conn.prepareStatement(strQuery);
            stmtAdd.setInt(1, Integer.parseInt(data[0]));
            stmtAdd.executeUpdate();
            
            conn.commit();
            JOptionPane.showMessageDialog(null, "Complimentary Deleted Successfully");
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null , se+"From DeleteCustomer");
        }
        finally{
            closeConnection();
        }
    
    
        }
          public DefaultTableModel getTableModelComplimentaryInfo(){
      //   int colcount;
     //  int rowcount ;
      // String[] ColumnNames = { "Issue Id", "Item Name", "Issue Quantity","item BaseUnit","Issue From", "Issue To","Issue Date"};
       String strQuery = "SELECT * FROM complimentary_info ORDER BY complimentary_id desc ";
       String ColumnNames[] = {"Complimentary Id","Complimentary Reason"};
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
              Object[] row = new Object[]{rsResult.getInt("complimentary_id"),rsResult.getString("complimentary_reason")};
              /* for(int i=0;i<colcount;i++){
                   row[i] = rsResult.getObject(i+1);
               }*/
              
               data.add(row);
               
              // data
              
           }
            finalData = data.toArray(new Object[data.size()][]);
           
       }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"form getTableModelComplimentaryInfo"+getClass().getName());
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
    
}
