/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserCrud;

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
public class UserCrudModel extends DBConnect {
    PreparedStatement stmtAdd;
    String strQuery;

     String strQuery1;
        public void AddUser(String username,String padd,int departmentid){
      
        strQuery = "INSERT INTO sys_user_info (username,password) VALUES(?,?)";
      String strMenuQuery = "INSERT INTO sys_menubar_setup (userid) VALUES(?)";
      String strDepartmentUser = "INSERT INTO department_user (department_id,user_id) VALUES(?,?)";
      String strIndentity = "SELECT last_insert_id() AS id";
       
        try{
           initConnection();
          conn.setAutoCommit(false);
            stmtAdd = conn.prepareStatement(strQuery);
            stmtAdd.setString(1, username);
            stmtAdd.setString(2, padd);
           
            stmtAdd.executeUpdate();
            conn.setAutoCommit(false);
            stmtAdd = conn.prepareStatement(strIndentity);
            ResultSet rse = stmtAdd.executeQuery();
            int userid = 0;
            while(rse.next()){
                userid = rse.getInt("id");
            }
//            System.out.println(userid);
            stmtAdd = conn.prepareStatement(strMenuQuery);
            stmtAdd.setInt(1, userid);
            stmtAdd.executeUpdate();
            //update the departmentuser relation so that it get only one department
            conn.setAutoCommit(false);
            stmtAdd = conn.prepareStatement(strDepartmentUser);
            stmtAdd.setInt(1, departmentid);
            stmtAdd.setInt(2, userid);
            stmtAdd.executeUpdate();
            
            
           conn.commit();
            JOptionPane.showMessageDialog(null, "User Created Successfully");
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null , se+"From Adduser "+getClass().getName());
        }
        finally{
           closeConnection();
        }
    
    
        }
          public void EditCustomer(String username,int userid,int departmentid){
        /*
          info[0] = getCustomerId();
        info[1] = getCustomerName();
        info[2] = getCustomerPhone();
        info[3] = getCustomerAddress();
        info[4] = getCustomerType();
         */
        strQuery = "UPDATE  sys_user_info  SET username = ? WHERE userid = ?";
        strQuery1  = "UPDATE department_user SET department_id = ? WHERE user_id = ? LIMIT 1";
//        DBConnect waiterAdd = new DBConnect();
        try{
           initConnection();
            conn.setAutoCommit(false);
            stmtAdd = conn.prepareStatement(strQuery);
            stmtAdd.setString(1, username);
//            stmtAdd.setString(2, password);
            stmtAdd.setInt(2, userid);
          
            stmtAdd.executeUpdate();
            //updating department_user  table for department assignment
            conn.setAutoCommit(false);
            stmtAdd = conn.prepareStatement(strQuery1);
            stmtAdd.setInt(1, departmentid);
            stmtAdd.setInt(2, userid);
            stmtAdd.executeUpdate();
           conn.commit();
            JOptionPane.showMessageDialog(null, "User Edited Successfully");
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null , se+"From UserCustomer");
        }
        finally{
            closeConnection();
        }
    
    
        }
          public void DeleteCustomer(int userid){
        /*
      info[0] = getCustomerId();
        info[1] = getCustomerName();
        info[2] = getCustomerPhone();
        info[3] = getCustomerAddress();
        info[4] = getCustomerType();
         */
        strQuery = "DELETE  FROM sys_user_info   WHERE userid = ?";
       String strMenuBar = "DELETE FROM  sys_menubar_setup WHERE userid = ?";
       String strDepartmentUser = " DELETE  FROM department_user WHERE user_id = ?";
//        DBConnect waiterAdd = new DBConnect();
        try{
          initConnection();
            conn.setAutoCommit(false);
            stmtAdd = conn.prepareStatement(strQuery);
            stmtAdd.setInt(1, userid);
            stmtAdd.executeUpdate();
             conn.setAutoCommit(false);
            stmtAdd = conn.prepareStatement(strMenuBar);
            stmtAdd.setInt(1, userid);
            stmtAdd.executeUpdate();
            //for deleing in department_user
            conn.setAutoCommit(false);
            stmtAdd = conn.prepareStatement(strDepartmentUser);
            stmtAdd.setInt(1, userid);
            stmtAdd.executeUpdate();
           conn.commit();
            JOptionPane.showMessageDialog(null, "User Deleted Successfully");
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null , se+"From DeleteUser");
        }
        finally{
           closeConnection();
        }
    
    
        }
          public DefaultTableModel getTableModelCustomerInfo(){
      //   int colcount;
     //  int rowcount ;
      // String[] ColumnNames = { "Issue Id", "Item Name", "Issue Quantity","item BaseUnit","Issue From", "Issue To","Issue Date"};
       String strQuery = "SELECT userid,username,department_info.department_name FROM sys_user_info LEFT JOIN department_user ON sys_user_info.userid = department_user.user_id LEFT JOIN department_info ON department_user.department_id = department_info.department_id  WHERE admin != 1 ORDER BY sys_user_info.userid desc ";
       String ColumnNames[] = {"User Id","User Name","Department Name"};
      //Object[][] data = null;
     //  List<Object[]> data = new ArrayList<Object[]>();
   ArrayList<Object[]> data = new ArrayList<Object[]>();
    Object[][] finalData =null;
       DBConnect getWaiter = new DBConnect();
       ResultSet rsResult;
       PreparedStatement stmtIssueInfo;
       try{
           getWaiter.initConnection();
           stmtIssueInfo = getWaiter.conn.prepareStatement(strQuery);
           rsResult = stmtIssueInfo.executeQuery();
           
          // ResultSetMetaData metadata = rsResult.getMetaData();
         //  colcount = metadata.getColumnCount();
            
           while(rsResult.next()){
              Object[] row = new Object[]{rsResult.getInt("userid"),rsResult.getString("username"),rsResult.getString("department_name")};
              /* for(int i=0;i<colcount;i++){
                   row[i] = rsResult.getObject(i+1);
               }*/
              
               data.add(row);
               
              // data
              
           }
            finalData = data.toArray(new Object[data.size()][]);
           
       }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"form getTableModelUserInfo");
        }
         finally{
           getWaiter.closeConnection();
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
    String strCheck = "SELECT username  FROM sys_user_info WHERE username = ? ";
    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
        check.initConnection();
        stmtcheck = check.conn.prepareStatement(strCheck,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
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
        check.closeConnection();
    }
    return ExistingStatus;
    
}
     public int getUserIdFromUserName(String name){
        PreparedStatement stmt;
        ResultSet rs;
        int UserId = 0;
        String Query = "SELECT userid FROM sys_user_info WHERE username = ?";
        try{
            initConnection();
           
            stmt = conn.prepareStatement(Query);
            
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            while(rs.next()){
                UserId = rs.getInt("userid");
            }
        }
        catch(SQLException ge){
            JOptionPane.showMessageDialog(null, ge+"from getUserIdFromUserName");
        }
        finally{
            closeConnection();
        }
        return UserId;
    }
     public Object[][] getDepartmentInfo(){
         PreparedStatement stmt;
         ResultSet rs;
//         String strquery = "SELECT  department_info.department_id,department_info.department_name FROM department_user INNER JOIN department_info ON department_user.department_id = department_info.department_id order by department_user.department_id desc";
         String strquery = "SELECT department_id,department_name FROM department_info ";
         ArrayList<Object[]> data = new ArrayList<>();
         
         
         try{
            initConnection();
            stmt = conn.prepareStatement(strquery);
            rs = stmt.executeQuery();
            while(rs.next()){
                Object[] row = new Object[]{rs.getInt(1),rs.getString(2)};
                data.add(row);
                
            }
         }
         catch(SQLException se){
             JOptionPane.showMessageDialog(null, se+"from getDepartmentInfo "+getClass().getName());
         }
         finally{
             closeConnection();
         }
         return data.toArray(new Object[data.size()][]);
     }
    public Object[] returnSecondColumn(Object[][] data){
        Object[] st = new Object[data.length];
       for(int i=0;i<data.length;i++){
           st[i] = data[i][1];
       }
       return st;
    }
     public void ChangePassword(String password,int userid){
        /*
          info[0] = getCustomerId();
        info[1] = getCustomerName();
        info[2] = getCustomerPhone();
        info[3] = getCustomerAddress();
        info[4] = getCustomerType();
         */
        strQuery = "UPDATE  sys_user_info  SET password = ? WHERE userid = ?";
       
//        DBConnect waiterAdd = new DBConnect();
        try{
           initConnection();
          
            stmtAdd = conn.prepareStatement(strQuery);
            stmtAdd.setString(1, password);
            stmtAdd.setInt(2, userid);
          
          
            stmtAdd.executeUpdate();
          
            JOptionPane.showMessageDialog(null, "Password Change Successfully");
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null , se+"From ChangePassword");
        }
        finally{
            closeConnection();
        }
    
    
        }
     public String returnPasswordByUserId(int userid){
      
     String pass = null;
    String strCheck = "SELECT password  FROM sys_user_info WHERE userid = ? ";
    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
        check.initConnection();
        stmtcheck = check.conn.prepareStatement(strCheck);
        stmtcheck.setInt(1, userid);
        ResultSet rs = stmtcheck.executeQuery();
        rs.next();
        pass = rs.getString("password");
//        System.out.println(pass);
        
        
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from checkExistingName");
    }
    finally{
        check.closeConnection();
    }
    return pass;
    
}
}
