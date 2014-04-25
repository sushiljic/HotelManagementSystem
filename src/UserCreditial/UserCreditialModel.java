/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserCreditial;

import database.DBConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

/**
 *
 * @author SUSHIL
 */
public class UserCreditialModel extends DBConnect {
    
    public void InsertUserCreditial(int userid,boolean companysetup,boolean centerstoresetup,boolean centerstore,boolean terminalsetup,boolean terminal,boolean centerstorereport,boolean terminalreport,boolean systemdate,boolean systemconfig){
        PreparedStatement stmtinsert ;
        ResultSet rsget;
        String DelQuery  = "DELETE from sys_menubar_setup WHERE UserId = ?";
        
//        String Query = "INSERT INTO sys_menubar_setup (UserId,[Company Setup],[CenterStore Setup],[CenterStore],[Terminal Setup],[Terminal],[CenterStore Report],[Terminal Report],[System Config]) VALUES(?,?,?,?,?,?,?,?,?)";
         String Query = "INSERT INTO sys_menubar_setup (UserId,`1`,`2`,`3`,`4`,`5`,`6`,`7`,`8`,`9`) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try{
            initConnection();
            conn.setAutoCommit(false);
            stmtinsert = conn.prepareStatement(DelQuery);
            stmtinsert.setInt(1, userid);
            stmtinsert.executeUpdate();
            
            stmtinsert = conn.prepareStatement(Query);
            stmtinsert.setInt(1, userid);
            stmtinsert.setBoolean(2, companysetup);
            stmtinsert.setBoolean(3, centerstoresetup);
            stmtinsert.setBoolean(4, centerstore);
            stmtinsert.setBoolean(5, terminalsetup);
            stmtinsert.setBoolean(6, terminal);
            stmtinsert.setBoolean(7, centerstorereport);
            stmtinsert.setBoolean(8, terminalreport);
            stmtinsert.setBoolean(9, systemdate);
            stmtinsert.setBoolean(10, systemconfig);
            stmtinsert.executeUpdate();
            conn.commit();
            JOptionPane.showMessageDialog(null, "MenuBar is  Being Set Succesfully");
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"From UserCreditialModel");
        }
            
    }
    public Object[][] getUserCreditial(int userid){
        PreparedStatement stmtget ;
        ResultSet get;
        String Query =  " SELECT * FROM sys_menubar_setup WHERE userid = ?";
        ArrayList<Object[]> data = new ArrayList<>();
        ResultSetMetaData metaData ;
//        Map<String,Boolean> usercred = new HashMap<String,Boolean>();
        int Col = 0;
        
        try{
            initConnection();
            stmtget = conn.prepareStatement(Query);
            stmtget.setInt(1, userid);
            get = stmtget.executeQuery();
            metaData = get.getMetaData();
            Col = metaData.getColumnCount();
            while(get.next()){
                for(int i=0;i<Col;i++){
//                    data.add(get.getObject(i));
//                  usercred.put(metaData.getColumnName(Col),get.getBoolean(Col));
                    //first will be the admin
                    String st;
                   if(metaData.getColumnName(i+1).equalsIgnoreCase("UserId")){
                       st = "UserId";
                   }
                   else{
                       st = returnMenuNameByMenuId(metaData.getColumnName(i+1));
                       
                   }
//                   System.out.println(st);
//                data.add(new Object[]{metaData.getColumnName(i+1),get.getBoolean(i+1)});
                data.add(new Object[]{st,get.getBoolean(i+1)});
//               JOptionPane.showMessageDialog(null,new Object[]{metaData.getColumnName(i+1),get.getObject(i+1)});
                }
            }
//            Collections.sort(usercred);
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se);
        }
        finally{
            closeConnection();
        }
        return data.toArray(new Object[data.size()][]) ;
        
        
    }
    public String returnMenuNameByMenuId(String menuid){
       PreparedStatement stmt;
       ResultSet rs;
       String name = null;
       try{
           initConnection();
           stmt = conn.prepareStatement("SELECT jmenu_name FROM sys_menu_info WHERE jmenu_id = ?");
           stmt.setString(1, menuid);
         rs=  stmt.executeQuery();
         while(rs.next()){
            name = rs.getString(1);
         }
       }
       catch(SQLException e){
           JOptionPane.showMessageDialog(null, e+"from returnMenuNameByMenuId");
       }
       finally{
           closeConnection();
       }
        return name;
    }
    public void DisplayMenuItemAsCreditial(Object[][] data,ArrayList menu){
       //separting the false value in one arraylist
//        ArrayList<Object[]> FalseMenuItem = new ArrayList<>();
        for (Object[] data1 : data) {
            if (data1[1].equals(false)) {
//                FalseMenuItem.add(data);
//                
                for(int i=0;i<menu.size();i++){
             JMenu mn = (JMenu)menu.get(i);
             if(mn.getText().equalsIgnoreCase(data1[0].toString())){
//                 mn.setVisible(false);
              JCheckBoxMenuItem menuitem =  (JCheckBoxMenuItem) mn.getMenuComponent(0);
              menuitem.setSelected(false);
             }
                
            }   
            }
            else if(data1[1].equals(true)){
              for(int i=0;i<menu.size();i++){
             JMenu mn = (JMenu)menu.get(i);
             if(mn.getText().equalsIgnoreCase(data1[0].toString())){
//                 mn.setVisible(false);
              JCheckBoxMenuItem menuitem =  (JCheckBoxMenuItem) mn.getMenuComponent(0);
              menuitem.setSelected(true);
             }
                
            }   
            }
        }
       
        
//        for(Object[] FalseMenuItem1:FalseMenuItem){
//            for(int i=0;i<menu.size();i++){
//               
//                
//            }
////             System.out.println(FalseMenuItem1[0].toString());
//           
////            System.out.println(FalseMenuItem1[0]);
//        }
//        for(int i=0;i<FalseMenuItem.size();i++){
////         System.out.println(  FalseMenuItem.get(i).length);
//        }
//        
    }
    
}
