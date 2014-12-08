/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package admin;

import database.DBConnect;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author SUSHIL
 */
public class AdminPanelModel  extends DBConnect{
    PreparedStatement stmtAdd;
    String strQuery;
    public void saveSystemDateStatus(Boolean status) throws SQLException{
        strQuery = "UPDATE system_config SET system_date_enable = ?";
        initConnection();
        conn.setAutoCommit(false);
        stmtAdd = conn.prepareStatement(strQuery);
        stmtAdd.setBoolean(1,status);
        stmtAdd.executeUpdate();
        conn.commit();
        closeConnection();
        
    }
    
}
