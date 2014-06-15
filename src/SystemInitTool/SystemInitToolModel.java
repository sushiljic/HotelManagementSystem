/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SystemInitTool;

import database.DBConnect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author SUSHIL
 */
public class SystemInitToolModel extends DBConnect {
    PreparedStatement stmt;
    ResultSet rs;
    Connection connect;
    String Username;
    String Password;
    String Url;
    
    
    public boolean checkConnection(String location,String username,String password) throws SQLException{
        boolean flag = false;
//        try{
//            String url = "jdbc:sqlserver://"+location+";";
             String url = "jdbc:mysql://"+location;
        Url = url;
        Username = username;
        Password = password;
        connect = DriverManager.getConnection(url, username, password);
        flag = true; 
//       System.out.println("wala");
//                }
//        catch(SQLException se){
//            JOptionPane.showMessageDialog(null, se);
////             System.out.println("wala");
//            flag = false;
//        }
//        System.err.println(flag);
        return flag;
//   
    }
    public Object[] returnAllDatabase(){
        ArrayList<String> name = new ArrayList<>();
        Object[] databaseNames = null ;
          try{
//            String url = "jdbc:sqlserver://"+location+";";
        
        connect = DriverManager.getConnection(Url, Username, Password);
//       flag = true;    
//        stmt = connect.prepareStatement("Select name from master.sys.databases d where d.database_id > 6");
         stmt = connect.prepareStatement("SHOW DATABASES ");
        rs = stmt.executeQuery();
        while(rs.next()){
            name.add(rs.getString(1));
        }
        databaseNames = name.toArray(new Object[name.size()]);
                }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+" From returnAllDatabases");
          
        }
       return databaseNames;
    }
    
}
