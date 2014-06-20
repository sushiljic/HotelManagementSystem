/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

import database.DBConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import reusableClass.DisplayMessages;

/**
 *
 * @author SUSHIL
 */
public class MainFrameModel extends DBConnect {
    
    public Object[] getCompanyDetail(){
        PreparedStatement getc;
        ResultSet get;
//        ArrayList<String> cinfo  = new ArrayList<String>();
        Object[] com = new Object[5];
        String Query = "SELECT company_name,company_address,phone,company_logo,company_logo_image FROM company_info  ";
       DBConnect dbget = new DBConnect();
       try{
           dbget.initConnection();
           getc = dbget.conn.prepareStatement(Query);
          get= getc.executeQuery();
           while(get.next()){
               com[0] = get.getString("company_name");
               com[1] = get.getString("company_address");
                       com[2] = get.getString("phone");
                       com[3] = get.getString("company_logo");
//                       Blob bb  = get.getBlob("company_logo_image");
//                       byte[] bt = bb.getBytes(1, (int)bb.length());
//                       com[4] = bt;
                       com[4] = get.getObject("company_logo_image");
           }
           
           
           
           
       }
       
       catch(SQLException se){
           JOptionPane.showMessageDialog(null, se+"from getCompanyDetail");
       }
       return com;
    }
     public boolean getCompanyRegister(){
        PreparedStatement getc;
        ResultSet get;
//        ArrayList<String> cinfo  = new ArrayList<String>();
        String[] com = new String[4];
        boolean registerstatus = false;
        String Query = "SELECT register FROM company_info  ";
       DBConnect dbget = new DBConnect();
       try{
           dbget.initConnection();
           getc = dbget.conn.prepareStatement(Query);
          get= getc.executeQuery();
           while(get.next()){
            registerstatus = get.getBoolean("register");
           }
           
           
           
           
       }
       
       catch(SQLException se){
           JOptionPane.showMessageDialog(null, se+"from getCompanyDetail");
       }
       return registerstatus;
    }
    
//    public Object[][] getUserCreditial(int userid){
//        PreparedStatement stmtget ;
//        ResultSet get;
//        String Query =  " SELECT * FROM sys_menubar_setup WHERE userid = ?";
//        ArrayList<Object[]> data = new ArrayList<>();
//        ResultSetMetaData metaData ;
////        Map<String,Boolean> usercred = new HashMap<String,Boolean>();
//        int Col = 0;
//        
//        try{
//            initConnection();
//            stmtget = conn.prepareStatement(Query);
//            stmtget.setInt(1, userid);
//            get = stmtget.executeQuery();
//            metaData = get.getMetaData();
//            Col = metaData.getColumnCount();
//            while(get.next()){
//                for(int i=0;i<Col;i++){
////                    data.add(get.getObject(i));
////                  usercred.put(metaData.getColumnName(Col),get.getBoolean(Col));
//                    //first will be the admin
//                data.add(new Object[]{metaData.getColumnName(i+1),get.getBoolean(i+1)});
////               JOptionPane.showMessageDialog(null,new Object[]{metaData.getColumnName(i+1),get.getObject(i+1)});
//                }
//            }
////            Collections.sort(usercred);
//        }
//        catch(SQLException se){
//            JOptionPane.showMessageDialog(null, se);
//        }
//        finally{
//            closeConnection();
//        }
//        return data.toArray(new Object[data.size()][]) ;
//        
//        
//    }
    public void DisplayMenuItemAsCreditial(Object[][] data,ArrayList menu,ArrayList button){
       //separting the false value in one arraylist
//        ArrayList<Object[]> FalseMenuItem = new ArrayList<>();
        for (Object[] data1 : data) {
            if(data1[0].equals("Terminal")){
                if(data1[1].equals(false)){
                    
                  for(int i=0;i<button.size();i++){
                       if(button.get(i) instanceof  JButton){
                      JButton btn = (JButton)button.get(i);
//                     System.out.println( btn.getActionCommand());
                    
                     String actioncommand[] =  new String[]{"Order","Pay","TableStatus","MenuDetailView"};
                     for(String ac:actioncommand){
                           if(btn.getActionCommand().equalsIgnoreCase(ac)){
                          btn.setVisible(false);
                       JSeparator js =   (JSeparator) button.get(i+1);
                       js.setVisible(false);
                          
                          
                      }
                     }
                  }
                  }
                }
                else{
                   for(int i=0;i<button.size();i++){
                       if(button.get(i) instanceof  JButton){
                      JButton btn = (JButton)button.get(i);
//                     System.out.println( btn.getActionCommand());
                    
                     String actioncommand[] =  new String[]{"Order","Pay","TableStatus","MenuDetailView"};
                     for(String ac:actioncommand){
                           if(btn.getActionCommand().equalsIgnoreCase(ac)){
                          btn.setVisible(true);
                            JSeparator js =   (JSeparator) button.get(i+1);
                       js.setVisible(true);
                          
                      }
                     }
                       }
                  }
                }
            }
            //for the toolbar for terminal 
                  if(data1[0].equals("Terminal Report")){
                if(data1[1].equals(false)){
                    
                  for(int i=0;i<button.size();i++){
                       if(button.get(i) instanceof  JButton){
                      JButton btn = (JButton)button.get(i);
//                     System.out.println( btn.getActionCommand());
                    
                     String actioncommand[] =  new String[]{"Report"};
                     for(String ac:actioncommand){
                           if(btn.getActionCommand().equalsIgnoreCase(ac)){
                          btn.setVisible(false);
                       JSeparator js =   (JSeparator) button.get(i+1);
                       js.setVisible(false);
                          
                          
                      }
                     }
                  }
                  }
                }
                else{
                   for(int i=0;i<button.size();i++){
                       if(button.get(i) instanceof  JButton){
                      JButton btn = (JButton)button.get(i);
//                     System.out.println( btn.getActionCommand());
                    
                     String actioncommand[] =  new String[]{"Report"};
                     for(String ac:actioncommand){
                           if(btn.getActionCommand().equalsIgnoreCase(ac)){
                          btn.setVisible(true);
                            JSeparator js =   (JSeparator) button.get(i+1);
                       js.setVisible(true);
                          
                      }
                     }
                       }
                  }
                }
            }
            if (data1[1].equals(false)) {
//                FalseMenuItem.add(data);
//                System.out.println(data1[0]);
//                for(Object dt:data1){
//                    System.out.println(dt);
//                }
//           
                for(int i=0;i<menu.size();i++){
             JMenu mn = (JMenu)menu.get(i);
             if(mn.getText().equalsIgnoreCase(data1[0].toString())){
                 mn.setVisible(false);
             }
                
            }   
            }
            else{
                 for(int i=0;i<menu.size();i++){
             JMenu mn = (JMenu)menu.get(i);
             if(mn.getText().equalsIgnoreCase(data1[0].toString())){
                 mn.setVisible(true);
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
    public void ReturnMenuBar(){
        PreparedStatement stmtget ;
        ResultSet get;
        try{
           initConnection();
           stmtget = conn.prepareStatement("");
           
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public boolean ValidateUser(String username,String password){
        Boolean ValidateUser = null;
        PreparedStatement stmt;
        ResultSet rs;
        String Query = "SELECT username FROM sys_user_info WHERE username = ? AND password = ? ";
        try{
            initConnection();
            stmt = conn.prepareStatement(Query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            rs.last();
            int rows = rs.getRow();
            rs.beforeFirst();
            ValidateUser = rows >= 1;
            
        }
        catch(SQLException re){
            JOptionPane.showMessageDialog(null, re+"From returnUserInfo");
        }
        finally{
            closeConnection();
        }
        return ValidateUser;
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
    //for making all the bil_id boolean false for whose being missed out
    //no need obsolute methods
    public ArrayList getValidBill_id(){
        PreparedStatement stmtget;
        ResultSet rsget;
        ArrayList<Integer> BillList = new ArrayList<>();
        try{
            initConnection();
            stmtget = conn.prepareStatement("SELECT bill_id FROM bill");
           rsget = stmtget.executeQuery();
           while(rsget.next()){
               BillList.add(rsget.getInt("bill_id"));
           }
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from getValidBill_id");
        }
        return BillList;
    }
     public ArrayList getAllGeneratedBill_id(){
        PreparedStatement stmtget;
        ResultSet rsget;
        ArrayList<Integer> BillList = new ArrayList<>();
        try{
            initConnection();
            stmtget = conn.prepareStatement("SELECT autoinc_billid FROM generate_billid");
           rsget = stmtget.executeQuery();
           while(rsget.next()){
               BillList.add(rsget.getInt("autoinc_billid"));
           }
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from getAllGeneratedBill_id");
        }
        return BillList;
    }
    
    public ArrayList returnInvalidBillId(ArrayList AllGenerateBillId,ArrayList ValidBillId){
      System.out.println(AllGenerateBillId.size());
        AllGenerateBillId.removeAll(ValidBillId);
         System.out.println(AllGenerateBillId.size());
//       for(int i =0;i<AllGenerateBillId.size();i++){
////           System.out.println(AllGenerateBillId.get(i));
//       }
         return AllGenerateBillId;
    }
    //right method
    public void MakeAllInvalidBillIdFalse()throws SQLException{
         PreparedStatement stmtget;
        ResultSet rsget;
         String strInvalid = "update  generate_billid set bill_status = 0 where autoinc_billid  not in (select bill_id from  bill)";
       
//        try{
            initConnection();
           
           
          
            stmtget = conn.prepareStatement(strInvalid);
           stmtget.executeUpdate();
         
            
//        }
//        catch(SQLException se){
//            JOptionPane.showMessageDialog(null, se+"from getAllGeneratedBill_id");
//        }
//        finally{
//            closeConnection();
//        }
    }
     public String[] getJMenuName(){
        PreparedStatement getc;
        ResultSet get;
//        ArrayList<String> cinfo  = new ArrayList<String>();
//        String[] com = new String[8];
        ArrayList<String> name = new ArrayList<>();
        String Query = "SELECT jmenu_name FROM sys_menu_info ";
       DBConnect dbget = new DBConnect();
       try{
           dbget.initConnection();
           getc = dbget.conn.prepareStatement(Query);
          get= getc.executeQuery();
          int i=0;
           while(get.next()){
              name.add(get.getString("jmenu_name"));
//             com[i++] = get.getString("jmenu_name");
//             System.out.println(com[i]+i);
           }
           
           
           
           
       }
       
       catch(SQLException se){
           JOptionPane.showMessageDialog(null, se+"from getCompanyDetail");
       }
       return name.toArray(new String[name.size()]);
    }
     public void SynchronisePosDate(Calendar posdate,Calendar comdate,Object[] dateinfo){
         PreparedStatement syndate;
         ResultSet rsdate;
         String qryClosedDate = "UPDATE system_date set close_status = ? WHERE system_date_id = ?";
         String qryInsertDate = "INSERT INTO system_date (date,open_status,close_status) VALUES(?,1,1)";
         try{
            initConnection();
            conn.setAutoCommit(false);
         //check if it not closed the closed the date
         if(dateinfo[2].equals(Boolean.TRUE) && dateinfo[3].equals(Boolean.FALSE)){
           
            syndate = conn.prepareStatement(qryClosedDate);
            syndate.setInt(1, 1);
            syndate.setObject(2, dateinfo[0]);
            syndate.executeUpdate();
         }
         //now insert the date with open and close flag true till it reach to comdate
         while(posdate.before(comdate) ){
//             System.out.println("wala");
             posdate.add(Calendar.DATE,1);
//             System.err.println(posdate.getTime());
             //this restrict the last date so that it will also not be inserted
             if(!posdate.equals(comdate)){
             syndate = conn.prepareStatement(qryInsertDate);
             syndate.setDate(1,new java.sql.Date(posdate.getTime().getTime()));
             syndate.executeUpdate();
             }
         }
         conn.commit();
         }
         catch(SQLException se){
             DisplayMessages.displayError(null, se.getMessage(), "SynchronisePosDate Error");
         }
     }
}
