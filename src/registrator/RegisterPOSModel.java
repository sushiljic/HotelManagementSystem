/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package registrator;

import database.DBConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import reusableClass.DisplayMessages;

/**
 *
 * @author SUSHIL
 */
public class RegisterPOSModel extends DBConnect {
    
    public String getRegisterCode(){
        PreparedStatement stmt;
        ResultSet rs;
        String code = null;
        String strQry = "SELECT register_code from company_info";
        try{
            initConnection();
            stmt = conn.prepareStatement(strQry);
            rs = stmt.executeQuery();
            rs.next();
            code = rs.getString(1);
        }
        catch(SQLException se){
            DisplayMessages.displayError(null, se.getMessage()+"fron getRegisterCode"+getClass().getName(),"Error");
        }
        finally{
            closeConnection();
        }
        return code;
    }
    public void setRegisterCode(String st) throws SQLException{
        PreparedStatement stmt;
        ResultSet rs;
        String code = null;
        String strQry = "UPDATE company_info  SET register_code = ?  ";
//        try{
            initConnection();
            stmt = conn.prepareStatement(strQry);
            stmt.setString(1, st);
            stmt.executeUpdate();
            
//        }
//        catch(SQLException se){
//            DisplayMessages.displayError(null, se.getMessage()+"fron setRegisterCode"+getClass().getName(),"Error");
//        }
//        finally{
//            closeConnection();
//        }
       
    }
     public String getSerialCode() throws SQLException{
        PreparedStatement stmt;
        ResultSet rs;
        String code = null;
        String strQry = "SELECT serial_code from company_info";
//        try{
            initConnection();
            stmt = conn.prepareStatement(strQry);
            rs = stmt.executeQuery();
            while(rs.next()){
            code = rs.getString(1);
            }
//            JOptionPane.showMessageDialog(null, "Product Registered Successfully.");
//        }
//        catch(SQLException se){
//            DisplayMessages.displayError(null, se.getMessage()+"fron getserialCode"+getClass().getName(),"Error");
//        }
//        finally{
//            closeConnection();
//        }
        return code;
    }
    public void setSerialCode(String st) throws SQLException{
        PreparedStatement stmt;
        ResultSet rs;
        String code = null;
        String strQry = "UPDATE company_info  SET serial_code = ?  ";
//        try{
            initConnection();
            stmt = conn.prepareStatement(strQry);
            stmt.setString(1, st);
           stmt.executeUpdate();
            
//        }
//        catch(SQLException se){
//            DisplayMessages.displayError(null, se.getMessage()+"fron setRegisterCode"+getClass().getName(),"Error");
//        }
//        finally{
//            closeConnection();
//        }
       
    }
   
    
    
}
