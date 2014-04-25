/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package systemdate;

import database.DBConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author SUSHIL
 */
public class SystemDateModel extends DBConnect {
    DateFormat dateformat = DateFormat.getDateInstance(DateFormat.FULL);
    
public void addSystemDate(Date date){
    PreparedStatement stmtdate;
    ResultSet rs;
    String strdate = "INSERT INTO system_date (date) VALUES(?) ";
    try{
       initConnection();
       stmtdate = conn.prepareStatement(strdate);
//       stmtdate.setTimestamp(1,new Timestamp());
       stmtdate.setDate(1,new java.sql.Date(date.getTime()));
       stmtdate.executeUpdate();
       JOptionPane.showMessageDialog(null, "Date "+date+"("+dateformat.format(date)+")"+" SuccuessFully Inserted.");
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from addSystemDate");
    }
}
public boolean checkSystemDateExist(){
    PreparedStatement stmtdate;
    ResultSet rsdate;
     int rows =0;
    try{
        initConnection();
        stmtdate = conn.prepareStatement("SELECT system_date_id FROM system_date",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rsdate= stmtdate.executeQuery();
        rsdate.last();
       rows = rsdate.getRow();
        rsdate.beforeFirst();
       
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, "From checkSystemDateExist:"+getClass().getName());
    }
    finally{
        closeConnection();
    }
     return rows != 0;
}
public void openSystemDate(){
    PreparedStatement stmtset;
    ResultSet rs;
    Date date = null;
    boolean open = false;
    boolean close = false;
    int id = 0;
    String strget = "select system_date_id,date,open_status,close_status from system_date where  system_date_id = (select max(system_date_id) from system_date)";
    String stropen = "UPDATE system_date SET open_status = 1 where system_date_id = ?";
    try{
        initConnection();
        conn.setAutoCommit(false);
        stmtset = conn.prepareStatement(strget);
        rs = stmtset.executeQuery();
        while(rs.next()){
           date = rs.getDate("date");
           open = rs.getBoolean("open_status");
           close = rs.getBoolean("close_status");
//           System.out.println(close);
           id = rs.getInt("system_date_id");
//           System.out.println(id);
           
        }
        if(date != null){
            if(close == true){
                JOptionPane.showMessageDialog(null, "Cannot Open The System since it is Already closed for"+date);
            }
            else{
                if(open == true){
                    JOptionPane.showMessageDialog(null, "System is Already Open for date "+date+" ("+dateformat.format(date)+")");
                }
                else{
                    int choice =   JOptionPane.showConfirmDialog(null, "Do you Want To Open System  for "+date+" ("+dateformat.format(date)+")"+"?", "Open System Windows", JOptionPane.YES_NO_CANCEL_OPTION);
                   if(choice == JOptionPane.YES_OPTION){
                       conn.setAutoCommit(false);
                       stmtset = conn.prepareStatement(stropen);
                       stmtset.setInt(1, id);
                       stmtset.executeUpdate();
                       JOptionPane.showMessageDialog(null, "Successfully  Day Opened for "+date+" ("+dateformat.format(date)+")");
                   }
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Please First Set The Date For the System");
        }
        
       conn.commit();
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from openSystemData"+getClass().getName());
    }
}
public void closeSystemDate(){
    PreparedStatement stmtset;
    ResultSet rs;
    Date date = null;
    Calendar Nextdate;
    boolean open = false;
    boolean close = false;
    int id = 0;
    String strget = "select system_date_id,date,open_status,close_status from system_date where  system_date_id = (select max(system_date_id) from system_date)";
    String strclose = "UPDATE system_date SET close_status = 1 where system_date_id = ?";
    String strdate = "INSERT INTO system_date (date) VALUES(?) ";
    try{
        initConnection();
        conn.setAutoCommit(false);
        stmtset = conn.prepareStatement(strget);
        rs = stmtset.executeQuery();
        while(rs.next()){
           date = rs.getDate("date");
           open = rs.getBoolean("open_status");
           close = rs.getBoolean("close_status");
//           System.out.println(close);
           id = rs.getInt("system_date_id");
           
        }
        if(date != null){
            if(open == true && close != true){
//                JOptionPane.showMessageDialog(null, "Cannot Open The System since it is Allready closed for"+date);
              int choice =   JOptionPane.showConfirmDialog(null, "Do you Want To Close System  for "+date+"("+dateformat.format(date)+")"+"?", "Close System Windows", JOptionPane.YES_NO_CANCEL_OPTION);
                   if(choice == JOptionPane.YES_OPTION){
                       conn.setAutoCommit(false);
                       stmtset = conn.prepareStatement(strclose);
                       stmtset.setInt(1, id);
                       stmtset.executeUpdate();
                       //adding the next day in next row of system_date
                       Nextdate = Calendar.getInstance();
                       Nextdate.setTime(date);
                       Nextdate.add(Calendar.DATE, 1);
//                       System.out.println(Nextdate);
                       conn.setAutoCommit(false);
                       stmtset = conn.prepareStatement(strdate);
                       //calculating the next day
                       stmtset.setDate(1,new java.sql.Date(Nextdate.getTime().getTime()));
                       stmtset.executeUpdate();
                       JOptionPane.showMessageDialog(null, "Successfully  Day Closed for "+date+" ("+dateformat.format(date)+")");
                   }
            }
            else{
               JOptionPane.showMessageDialog(null, "First Open the Date");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Please First Set The Date For The System.");
        }
        
       conn.commit();
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from openSystemData"+getClass().getName());
    }
}
public  Object[] returnSystemDateInfo(){
    PreparedStatement stmtset;
    ResultSet rs;
    Date date = null;
    boolean open = false;
    boolean close = false;
    int id = 0;
    Object[] info = new Object[4];
    String strget = "select system_date_id,date,open_status,close_status from system_date where  system_date_id = (select max(system_date_id) from system_date)";
//    String stropen = "UPDATE system_date SET open_status = 1 where system_date_id = ?";
    try{
        initConnection();
//        conn.setAutoCommit(false);
        stmtset = conn.prepareStatement(strget);
        rs = stmtset.executeQuery();
        while(rs.next()){
        
           info[0] = rs.getObject("system_date_id");
           info[1]=rs.getObject("date");
           info[2] = rs.getObject("open_status");
           info[3]  =rs.getObject("close_status");
//           System.out.println(id);
           
        }
    
      
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from openSystemData"+getClass().getName());
    }
    finally{
        closeConnection();
    }
    return info;
    
}
public int  returnOrderedListNumber(){
    PreparedStatement stmt;
    ResultSet rs;
    String st = "SELECT count(order_id) as num FROM order_list WHERE paid = 0";
    int num = 0;
    try{
        initConnection();
        stmt = conn.prepareStatement(st);
        rs  = stmt.executeQuery();
        while(rs.next()){
           num = rs.getInt(1);
           
        }
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from returnOrderedListNumber "+getClass().getName());
        
    }
    finally{
        closeConnection();
    }
    return num;
}

}

