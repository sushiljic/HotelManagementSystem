/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.distributorComponent;

import database.DBConnect;
import java.sql.*;
import javax.swing.table.*;
import java.util.*;
import reusableClass.DisplayMessages;
/**
 *
 * @author MinamRosh
 */
public class DistributerModel extends DBConnect {
   
    private String sql;
    private ResultSet rs;
    private Statement stmt;
    private PreparedStatement preStmt;
    private DefaultTableModel tModel;
    
    public DistributerModel(){
        
    }
    
    public void addDistroInfo(String[] dInfo){
        sql = "INSERT INTO distributor (distributor_name, distributor_address, distributor_phone, distributor_email)"
                + "VALUES (?, ?, ?,?)";
        initConnection();
        try{
            preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, dInfo[0]);
            preStmt.setString(2, dInfo[1]);
            preStmt.setString(3, dInfo[2]);
            preStmt.setString(4, dInfo[3]);
            preStmt.executeUpdate();;
            
            DisplayMessages.displayInfo(null,"Distributor Successfully Added!","Success");

          
        }
        catch(SQLException sqlEx){
            DisplayMessages.displayError(null, "DistributorModel.addDistroInfo()."+sqlEx, "Database Exception");
        }
        catch(NullPointerException nulEx){
            DisplayMessages.displayError(null, "DistributorModel.addDistorInfo(): " +nulEx,"Databse Exception");
        }
        finally{
            closeConnection();
        }
    }
    
    //delete 
    public void deleteDistroInfo(int id){
        sql = "DELETE FROM distributor WHERE distributor_id = ?";
        initConnection();
        try{
            preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, id);
            preStmt.executeUpdate();
            
            DisplayMessages.displayInfo(null, "Distributor Successfully Deleted !","Success");
        }
        catch(SQLException sqlEx){
            DisplayMessages.displayError(null, "DistributorModel.deleteDistroInfo: " +sqlEx,"Database Exception");
        }
        finally{
            closeConnection();
        }
    }
    
    //check for duplication while adding information
    public boolean isDistroDuplicate(String name){
        boolean dupStatus = true;
        initConnection();
        try{
            sql = "SELECT distributor_name FROM distributor WHERE distributor_name = ?";
            preStmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            preStmt.setString(1,name);
            rs = preStmt.executeQuery();
            int rows = getNumberOfRows(rs);
            if(rows >= 1)
               dupStatus = false;
            else
               dupStatus = true;
        }
        catch(SQLException | NullPointerException sqlEx){
            DisplayMessages.displayError(null,"DistributorModel.checkDuplicate(): " +sqlEx,"Database Exception");
        }
        finally{
            closeConnection();
        }
        return dupStatus;
    } 
    
    public void updateDistroInfo(int id, String[] info){
        sql = "UPDATE distributor SET distributor_name = ?, distributor_address = ?, distributor_phone = ?, distributor_email = ? WHERE distributor_id = ?";
        initConnection();
        try{
            preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, info[0]);
            preStmt.setString(2, info[1]);
            preStmt.setString(3, info[2]);
            preStmt.setString(4, info[3]);
            preStmt.setInt(5, id);
            preStmt.executeUpdate();
            
        }
        catch(SQLException sqlEx){
            DisplayMessages.displayError(null, "DistributorModel.updateDistorInfo(): " + sqlEx,"Database Exception");
        }
        finally{
            closeConnection();
        }
    }
    
    public DefaultTableModel getAllDistroInfo()throws NullPointerException{
        initConnection();
        sql = "SELECT distributor_id, distributor_name, distributor_address, distributor_phone, distributor_email FROM distributor ORDER BY distributor_id DESC";
        
        //for storing data from data as object
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        try{
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            int rows = getNumberOfRows(rs);
            int cols = rs.getMetaData().getColumnCount();
           // obj = new Object[rows][cols];
            //table models size;
            tModel = new DefaultTableModel(rows, cols);
            int i = 1; //for tracking rows;
            while(rs.next()){
                //store each record of row in object
                Object[] row = new Object[]{rs.getObject("distributor_id"),rs.getObject("distributor_name"),
                    rs.getObject("distributor_address"),rs.getObject("distributor_phone"),
                    rs.getObject("distributor_email")};
                data.add(row); // add each object to data to make array of array list
            }
            //convert array list to object of size data object
            Object[][] finalData = data.toArray(new Object[data.size()][]);
            tModel = new DefaultTableModel(finalData, new String[]{"Id","Name","Address","Phone","Email"}){
                 @Override
                 public boolean isCellEditable(int rows,int columns){
                       return false;    
                }
            };
            //System.out.println(tModel.toString());
        }
        catch(SQLException sqlEx){
            DisplayMessages.displayError(null, "DistributerModel.getAllDistroInfo()." +sqlEx,"Database Exception");
        }
        finally{
            closeConnection();
        }
        return tModel;
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
   
}
