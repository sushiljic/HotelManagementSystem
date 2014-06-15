/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;




import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import reusableClass.DisplayMessages;
/**
 *
 * @author SUSHIL
 */



public class DBConnect  extends readDatabase {

//    public static final String username = "sa";
//    public static final String username = "root";
//    public static final String password = "saadmin";
//    public static final String conn_string = "jdbc:sqlserver://localhost;databaseName=alepos";
     public static final String conn_string = "jdbc:mysql://";
//     public static final String conn_string = "jdbc:mysql://localhost:3307/alepos";
    public  Connection conn;
    public  ResultSet rs;
    public    Statement stmt;
    
    private String Username;
    private String Password;
    private String ServerLocation;
    private String Databasename;
    public  void initConnection() {
        
        try {
            String[] databasedata = readLine(Paths.get("database.dat"));
            if(databasedata.length >= 4){
            Username = databasedata[0];
            Password = databasedata[1];
            ServerLocation = databasedata[2];
            Databasename = databasedata[3];
            }
            else{
                JOptionPane.showMessageDialog(null, "Database.dat might be corruped");
                
//               System.exit(0);
            }
            String Url = conn_string +ServerLocation+"/"+Databasename;
//        conn = DriverManager.getConnection(Url,Username,Password);
//             System.out.println(Username);
         conn = DriverManager.getConnection(Url,Username,Password);
//         System.out.println(conn_string);
//        conn = DriverManager.
//         conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/alepos","root","saadmin");

            //conn = DriverManager.getConnection(conn_string,username,password);
      // System.out.println("Connected");
        }
       
        catch(SQLException se) {
            DisplayMessages.displayError(null,"Either Datbase Doesnot exists or password doesnot match\n", se.getMessage()+getClass().getName());
             System.exit(0);
            
            //System.err.println(se+"There is error conneting with database");
        }
    }
    
    public  ResultSet getResult(String sqlquery){
        try{
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sqlquery);
        }
        catch(SQLException se){
           // System.err.println(se+"There is error retrieving from  database");
            JOptionPane.showMessageDialog(null, se);
           }
      
        return rs;
    }
      public  void closeConnection(){
            if(conn != null){
                          try {
                              conn.close();
                          } catch (SQLException ex) {
                              Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
                          }
                      }          
                }  
    public Object[][] returnData(ResultSet rs){
        Object[][] finaldata = null;
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        int row = 0;
        int col = 0;
        ResultSetMetaData rsmd;
        try{
          rsmd  = rs.getMetaData();
        col = rsmd.getColumnCount();
      //  row = getNumberOfRows(rs);
      //  data = new Object[row][col];
        
       // rs.beforeFirst();
        while(rs.next()){
            
         //   for(int i =0;i<row;i++){
           //     for(int j =0;j<col;col++){
               //     data[i][j] = rs.getObject(j+1);
              //  }
               // rs.next();
            Object[] obj = new Object[col];
            for(int i =0;i<col;i++){
               obj[i] = rs.getObject(i+1);
            }
            data.add(obj);
            }
        finaldata = data.toArray(new Object[data.size()][]);
        }
        
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"form returnData function");
        }
        return finaldata;
    }
}
