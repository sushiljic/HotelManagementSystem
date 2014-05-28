/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.tablecrud;

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

public class TableCrudModel extends DBConnect {
   private  PreparedStatement stmtTableGroupAdd;
   private PreparedStatement stmtTableGroupEdit;
   private PreparedStatement stmtTableGroupDelete;
   private ResultSet rsdata;
   Object[][] TableGroupInfo = null ;
   Object[][] TableGroup = null;
    public void TableGroupAdd(String[] data){
        /*
          info[0] = String.valueOf(getTableGroupId());
         info[1] = getTableGroupName();
        if(RateStatus()){
             info[2] = "";
         }
         else{
        
         info[2] = getTableGroupRate();
                 }
         */
//        String strAddQuery = "INSERT INTO tablegroup (tablegroup_name,tablegroup_rate) VALUES(?,?)";
        String strAddQuery = "INSERT INTO tablegroup (tablegroup_name) VALUES(?)";
        DBConnect tbadd = new DBConnect();
        try{
            tbadd.initConnection();
            stmtTableGroupAdd = tbadd.conn.prepareStatement(strAddQuery);
            stmtTableGroupAdd.setString(1, data[1]);
//            if(data[2].isEmpty()){
//                stmtTableGroupAdd.setBigDecimal(2,null);
//            }
//            else {
//            stmtTableGroupAdd.setBigDecimal(2, new BigDecimal(data[2]).setScale(2, RoundingMode.HALF_UP));
//            }
            stmtTableGroupAdd.executeUpdate();
            JOptionPane.showMessageDialog(null, "Table Group Add Sucessfully");
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from TableGroupAdd");
        }
         finally{
            tbadd.closeConnection();
        }
        
    }
    public void TableGroupEdit(String[] data){
        /*
           info[0] = String.valueOf(getTableGroupId());
         info[1] = getTableGroupName();
        if(RateStatus()){
             info[2] = "";
         }
         else{
        
         info[2] = getTableGroupRate();
                 }
         */
//        String strAddQuery = "UPDATE  tablegroup  SET tablegroup_name = ?,tablegroup_rate =? WHERE tablegroup_id = ?";
        String strAddQuery = "UPDATE  tablegroup  SET tablegroup_name = ? WHERE tablegroup_id = ?";
        DBConnect tbadd = new DBConnect();
        try{
            tbadd.initConnection();
            stmtTableGroupEdit = tbadd.conn.prepareStatement(strAddQuery);
            stmtTableGroupEdit.setString(1, data[1]);
//            if(data[2].isEmpty()){
//                stmtTableGroupEdit.setBigDecimal(2,null);
//            }
//            else {
//            stmtTableGroupEdit.setBigDecimal(2, new BigDecimal(data[2]).setScale(2, RoundingMode.HALF_UP));
//            }
            stmtTableGroupEdit.setInt(2, Integer.parseInt(data[0]));
            stmtTableGroupEdit.executeUpdate();
            JOptionPane.showMessageDialog(null, "Table Group Edited Sucessfully");
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from TableGroupEdit");
        }
        finally{
            tbadd.closeConnection();
        }
        
    }
    public void TableGroupDelete(String[] data){
         /*
           info[0] = String.valueOf(getTableGroupId());
         info[1] = getTableGroupName();
        if(RateStatus()){
             info[2] = "";
         }
         else{
        
         info[2] = getTableGroupRate();
                 }
         */
        String strdel = "DELETE  FROM tablegroup WHERE tablegroup_id = ?";
        DBConnect del = new DBConnect();
        try{
            del.initConnection();
            stmtTableGroupDelete = del.conn.prepareStatement(strdel);
            stmtTableGroupDelete.setString(1, data[0]);
            stmtTableGroupDelete.executeUpdate();
            JOptionPane.showMessageDialog(null, "Table Group successfully Deleted");
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"form delete");
        }
         finally{
            del.closeConnection();
        }
    }
    public DefaultTableModel getTableGroupInfo(){
        DBConnect gettg = new DBConnect();
        PreparedStatement stmtget ;
        ResultSet rs;
        String[] columnName = new String[]{"TableGroup Id","TableGroup Name"/*,"TableGroup Rate"*/};
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        Object[][] finaldata = null;
        try{
            gettg.initConnection();
            stmtget = gettg.conn.prepareStatement("SELECT * FROM tablegroup");
            rs = stmtget.executeQuery();
            while(rs.next()){
                Object[] row = new Object[]{rs.getInt("tablegroup_id"),rs.getString("tablegroup_name")/*,rs.getBigDecimal("tablegroup_rate")*/};
            data.add(row);
            }
            finaldata = data.toArray(new Object[data.size()][]);
            
        }
        
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"from gettablegroupinfo");
        }
        finally{
            gettg.closeConnection();
        }
        return new DefaultTableModel(finaldata,columnName){
                @Override     
            public boolean isCellEditable(int row, int col){
               return false;
           }
                
           
        };
    }
    public DefaultTableModel getTableInfo(){
        DBConnect gettg = new DBConnect();
        PreparedStatement stmtget ;
        ResultSet rs;
        String[] columnName = new String[]{"Table Id","Table Name","TableGroup Name","Availability"};
        String strQuery = "SELECT table_info.table_id,table_info.table_name,tablegroup.tablegroup_name,table_info.table_availability FROM table_info,tablegroup WHERE table_info.parent_tablegroup_id = tablegroup.tablegroup_id ORDER BY table_id desc";
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        Object[][] finaldata = null;
        try{
            gettg.initConnection();
            stmtget = gettg.conn.prepareStatement(strQuery);
            rs = stmtget.executeQuery();
            while(rs.next()){
                Object[] row = new Object[]{rs.getInt("table_id"),rs.getString("table_name"),rs.getString("tablegroup_name"),rs.getBoolean("table_availability")};
            data.add(row);
            }
            finaldata = data.toArray(new Object[data.size()][]);
            
        }
        
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"from gettablegroupinfo");
        }
        finally{
            gettg.closeConnection();
        }
        return new DefaultTableModel(finaldata,columnName){
                @Override     
            public boolean isCellEditable(int row, int col){
               return false;
           }
                @Override
                        public Class<?> getColumnClass(int columnIndex) {
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
     public DefaultTableModel getTableInfoLike(String str){
        DBConnect gettg = new DBConnect();
        String st = str+"%";
        PreparedStatement stmtget ;
        ResultSet rs;
        String[] columnName = new String[]{"Table Id","Table Name","TableGroup Name","Availability"};
        String strQuery = "SELECT table_info.table_id,table_info.table_name,tablegroup.tablegroup_name,table_info.table_availability FROM table_info,tablegroup WHERE table_info.parent_tablegroup_id = tablegroup.tablegroup_id AND table_info.table_name LIKE ?  ORDER BY table_id desc";
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        Object[][] finaldata = null;
        try{
            gettg.initConnection();
            stmtget = gettg.conn.prepareStatement(strQuery);
            stmtget.setString(1, st);
            rs = stmtget.executeQuery();
            while(rs.next()){
                Object[] row = new Object[]{rs.getInt("table_id"),rs.getString("table_name"),rs.getString("tablegroup_name"),rs.getBoolean("table_availability")};
            data.add(row);
            }
            finaldata = data.toArray(new Object[data.size()][]);
            
        }
        
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"from gettablegroupinfo");
        }
        finally{
            gettg.closeConnection();
        }
        return new DefaultTableModel(finaldata,columnName){
                @Override     
            public boolean isCellEditable(int row, int col){
               return false;
           }
                @Override
                        public Class<?> getColumnClass(int columnIndex) {
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
    public Object[][] getTableGroupInfoObject(){
        PreparedStatement stmtget;
        ResultSet rsget ;
        DBConnect dbget  = new DBConnect();
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        
        String strget = "SELECT tablegroup_id,tablegroup_name FROM tablegroup ";
        try{
            dbget.initConnection();
            stmtget = dbget.conn.prepareStatement(strget);
            rsget = stmtget.executeQuery();
            while(rsget.next()){
                Object[] row = new Object[]{rsget.getInt("tablegroup_id"),rsget.getString("tablegroup_name")};
                data.add(row);
            }
            TableGroupInfo = data.toArray(new Object[data.size()][]);
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from gettablegroupinfo");
        }
        finally{
            dbget.closeConnection();
        }
        return TableGroupInfo;
    }
    public Object[][] getTableInfoObject(){
        PreparedStatement stmtget;
        ResultSet rsget ;
        DBConnect dbget  = new DBConnect();
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        
        String strget = "SELECT table_id,table_name,tablegroup.tablegroup_name,table_status FROM table_info INNER JOIN tablegroup ON  table_info.parent_tablegroup_id = tablegroup.tablegroup_id WHERE table_info.table_availability = 1";
        try{
            dbget.initConnection();
            stmtget = dbget.conn.prepareStatement(strget);
            rsget = stmtget.executeQuery();
            while(rsget.next()){
                Object[] row = new Object[]{rsget.getInt("table_id"),rsget.getString("table_name"),rsget.getString("tablegroup_name"),rsget.getBoolean("table_status")};
                data.add(row);
            }
            TableGroup = data.toArray(new Object[data.size()][]);
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from gettableinfo");
        }
        finally{
            dbget.closeConnection();
        }
        return TableGroup;
    }
    public void TableAdd(String[] data){
        /*
           info[0] = String.valueOf(getTableId());
         info[1] = getTableName();
         info[2] = String.valueOf(getTableGroupId());
         info[3] = getComboTableGroup();
         info[4] = String.valueOf(checkboxTableAvailability);
         */
        String strAddQuery = "INSERT INTO table_info (table_name,parent_tablegroup_id,table_availability) VALUES(?,?,?)";
        DBConnect tbadd = new DBConnect();
        try{
            tbadd.initConnection();
            stmtTableGroupAdd = tbadd.conn.prepareStatement(strAddQuery);
            stmtTableGroupAdd.setString(1, data[1]);
           stmtTableGroupAdd.setInt(2, Integer.parseInt(data[2]));
           if(data[4].equalsIgnoreCase("true")){
               stmtTableGroupAdd.setInt(3, 1);
           }
           else{
               stmtTableGroupAdd.setInt(3, 0);
           }
            stmtTableGroupAdd.executeUpdate();
            JOptionPane.showMessageDialog(null, "Table Added Sucessfully");
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from TableAdd");
        }
        finally{
            tbadd.closeConnection();
        }
        
    }
     public void TableEdit(String[] data){
        /*
           info[0] = String.valueOf(getTableId());
         info[1] = getTableName();
         info[2] = String.valueOf(getTableGroupId());
         info[3] = getComboTableGroup();
         info[4] = String.valueOf(checkboxTableAvailability);
         */
        String strAddQuery = "UPDATE  table_info SET table_name = ?,parent_tablegroup_id =?, table_availability = ? WHERE table_id = ?";
        DBConnect tbadd = new DBConnect();
        try{
            tbadd.initConnection();
            stmtTableGroupEdit = tbadd.conn.prepareStatement(strAddQuery);
            stmtTableGroupEdit.setString(1, data[1]);
           stmtTableGroupEdit.setInt(2, Integer.parseInt(data[2]));
           if(data[4].equalsIgnoreCase("false")){
               stmtTableGroupEdit.setInt(3, 0);
           }
           else{
               stmtTableGroupEdit.setInt(3, 1);
           }
            stmtTableGroupEdit.setInt(4, Integer.parseInt(data[0]));
            stmtTableGroupEdit.executeUpdate();
            JOptionPane.showMessageDialog(null, "Table Edited Sucessfully");
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from TableEdit");
        }
        finally{
            tbadd.closeConnection();
        }
        
    }
     public void TableDelete(String[] data){
         /*
           info[0] = String.valueOf(getTableId());
         info[1] = getTableName();
         info[2] = String.valueOf(getTableGroupId());
         info[3] = getComboTableGroup();
         */
        String strdel = "DELETE table_info WHERE table_id = ?";
        DBConnect del = new DBConnect();
        try{
            del.initConnection();
            stmtTableGroupDelete = del.conn.prepareStatement(strdel);
            stmtTableGroupDelete.setString(1, data[0]);
            stmtTableGroupDelete.executeUpdate();
            JOptionPane.showMessageDialog(null, "Table successfully Deleted");
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"form delete");
        }
        finally{
            del.closeConnection();
        }
    }
    public boolean checkExistingName(String menuname){
     Boolean ExistingStatus = null; 
    String strCheck = "SELECT tablegroup_name FROM tablegroup WHERE tablegroup_name = ? ";
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
        if(rows >= 1){
            //if there is another item id 
            ExistingStatus = true;
        }
        else{
            //if there is not same id
            ExistingStatus = false;
        }
        
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from checkExistingName");
    }
    finally{
        check.closeConnection();
    }
    return ExistingStatus;
    
}
     public boolean checkExistingTableName(String menuname){
     Boolean ExistingStatus = null; 
    String strCheck = "SELECT table_name FROM table_info WHERE table_name = ? ";
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
        if(rows >= 1){
            //if there is another item id 
            ExistingStatus = true;
        }
        else{
            //if there is not same id
            ExistingStatus = false;
        }
        
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from checkExistingTableName");
    }
    finally{
        check.closeConnection();
    }
    return ExistingStatus;
    
}
    public String[] returnTableGroupName(Object[][] obj){
        String[] Name = new String[obj.length];
        for(int i=0;i<obj.length;i++){
            Name[i] = obj[i][1].toString();
        }
        return Name;
    }
    public Object[] getRespectiveDepartmentFromTableId(int tableid){
        PreparedStatement stmt;
        ResultSet rs;
        String st = "SELECT order_list.department_id,department_info.department_name  FROM alepos.temp_order_table INNER JOIN order_list ON temp_order_table.order_id = order_list.order_id INNER JOIN department_infO ON order_list.department_id = department_info.department_id WHERE temp_order_table.table_id = ? ";
        int did = 0;
        Object[] data =  new Object[2];
        try{
         initConnection();
         stmt = conn.prepareStatement(st);
         stmt.setInt(1, tableid);
//         System.out.println(tableid);
         
        rs = stmt.executeQuery();
         while(rs.next()){
//             did = rs.getInt(1);
             data[0] = rs.getObject(1);
             data[1] = rs.getObject(2);
         }
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from getRespectiveDepartmentId"+getClass().getName());
        }
        finally{
            closeConnection();
        }
        return data;
    }
}
