/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package department;

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
public class DepartmentModel extends DBConnect {
    public void addDepartment(String[] info){
        PreparedStatement stmtadd;
        ResultSet rs;
        int department_id = 0;
        String strQuery = "INSERT INTO department_info(department_name,default_printer,order_printer) VALUES(?,?,?)";
        String str = "SELECT last_insert_id()";
        String stri = "INSERT INTO department_user (department_id,user_id) VALUES(?,?)";
        try{
             initConnection();
          conn.setAutoCommit(false);
          stmtadd = conn.prepareStatement(strQuery);
          stmtadd.setString(1, info[1]);
          stmtadd.setString(2, info[2]);
          stmtadd.setString(3, info[3]);
          stmtadd.executeUpdate();
          stmtadd = conn.prepareStatement(str);
          rs = stmtadd.executeQuery();
          rs.next();
          department_id = rs.getInt(1);
          stmtadd = conn.prepareStatement(stri);
          stmtadd.setInt(1, department_id);
          stmtadd.setInt(2, 1);
          stmtadd.executeUpdate();
          conn.commit();
          
          JOptionPane.showMessageDialog(null, "Department Added Successfully");
          
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+" from addDepartment "+getClass().getName());
            
        }
        finally{
            closeConnection();
        }
      
    }
     public void editDepartment(String[] info){
        PreparedStatement stmtadd;
        ResultSet rs;
        String strQuery = "UPDATE department_info SET department_name= ?,default_printer = ?,order_printer = ? WHERE department_id = ?";
        
        try{
            initConnection();
          stmtadd = conn.prepareStatement(strQuery);
          stmtadd.setString(1, info[1]);
          stmtadd.setString(2, info[2]);
          stmtadd.setString(3, info[3]);
          stmtadd.setInt(4, Integer.parseInt(info[0]));
          
          stmtadd.executeUpdate();
          
          JOptionPane.showMessageDialog(null, "Department Edited Successfully");
          
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+" from editDepartment "+getClass().getName());
            
        }
         finally{
            closeConnection();
        }
      
    }
     public void deleteDepartment(String[] info){
        PreparedStatement stmtadd;
        ResultSet rs;
        String strQuery = "DELETE * FROM  department_info  WHERE department_id = ?";
        try{
             initConnection();
          stmtadd = conn.prepareStatement(strQuery);
        
          stmtadd.setInt(1, Integer.parseInt(info[0]));
          stmtadd.executeUpdate();
          JOptionPane.showMessageDialog(null, "Department Deleted Successfully");
          
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+" from deleteDepartment "+getClass().getName());
            
        }
         finally{
            closeConnection();
        }
      
    }
    
     public DefaultTableModel getDepartmentTableModel(){
      //   int colcount;
     //  int rowcount ;
      // String[] ColumnNames = { "Issue Id", "Item Name", "Issue Quantity","item BaseUnit","Issue From", "Issue To","Issue Date"};
       String strQuery = "SELECT * from department_info ";
       String ColumnNames[] = {"Department Id","Department Name","Default Printer","Order Printer"};
      //Object[][] data = null;
     //  List<Object[]> data = new ArrayList<Object[]>();
   ArrayList<Object[]> data = new ArrayList<>();
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
              Object[] row = new Object[]{rsResult.getInt(1),rsResult.getString(2),rsResult.getObject(3),rsResult.getObject(4)};
              /* for(int i=0;i<colcount;i++){
                   row[i] = rsResult.getObject(i+1);
               }*/
              
               data.add(row);
               
              // data
              
           }
            finalData = data.toArray(new Object[data.size()][]);
           
       }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"form getDepartmentTableModel "+getClass().getName());
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
