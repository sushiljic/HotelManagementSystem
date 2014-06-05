/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package resturant.menulist;

import database.DBConnect;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SUSHIL
 */
public class MenuListModel extends DBConnect {
    Object[][] MenuInfo;
    
     public Object[][] getMenuInfoObject(int user_id){
        PreparedStatement stmtget;
        ResultSet rsget ;
        DBConnect dbget  = new DBConnect();
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        
//        String strget = "SELECT menu_id,menu_name,menu.department_item_id,item_type,centerstore_stock.item_name,menu.unit_id,item_unit.unit_name,quantity,retail_price,wholesale_price,image_path,hybrid_type,department_info.department_name FROM menu LEFT JOIN department_store_stock ON menu.department_item_id = department_store_stock.department_item_id LEFT JOIN centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id  LEFT JOIN item_unit ON menu.unit_id = item_unit.unit_id  LEFT JOIN department_info ON menu.department_id= department_info.department_id   WHERE menu.department_id = ? ORDER BY date desc";
          String strget = "SELECT menu_id,menu_name,menu.department_item_id,item_type,centerstore_stock.item_name,menu.unit_id,item_unit.unit_name,quantity,retail_price,wholesale_price,image_path,hybrid_type,department_info.department_name,menu_image FROM menu LEFT JOIN department_store_stock ON menu.department_item_id = department_store_stock.department_item_id LEFT JOIN centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id  LEFT JOIN item_unit ON menu.unit_id = item_unit.unit_id  LEFT JOIN department_info ON menu.department_id= department_info.department_id LEFT JOIN department_user ON menu.department_id = department_user.department_id   WHERE department_user.user_id = ? ORDER BY date desc";
        try{
            dbget.initConnection();
            stmtget = dbget.conn.prepareStatement(strget);
            stmtget.setInt(1, user_id);
            rsget = stmtget.executeQuery();
            while(rsget.next()){
               Object imagepath = (rsget.getObject("image_path") != null )?rsget.getObject("image_path"):"";
                Object[] row = new Object[]{rsget.getString("menu_id"),rsget.getString("menu_name"),rsget.getBoolean("item_type"),rsget.getString("department_item_id"),rsget.getString("item_name"),rsget.getString("unit_id"),rsget.getString("unit_name"),rsget.getBigDecimal("quantity"),rsget.getBigDecimal("retail_price"),rsget.getBigDecimal("wholesale_price"),imagepath,rsget.getBoolean("hybrid_type"),rsget.getString("department_name"),rsget.getObject("menu_image")};
                data.add(row);
                        
            }
            MenuInfo = data.toArray(new Object[data.size()][]);
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from getmenuinfo "+getClass().getName());
        }
        finally{
            dbget.closeConnection();
        }
        return MenuInfo;
    }
     //this function return the data as per the user search
     public Object[][] getMenuInfoObjectBySearchLike(int user_id,String menuname){
        PreparedStatement stmtget;
        ResultSet rsget ;
        DBConnect dbget  = new DBConnect();
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        String menu = menuname+"%";
        
//        String strget = "SELECT menu_id,menu_name,menu.department_item_id,item_type,centerstore_stock.item_name,menu.unit_id,item_unit.unit_name,quantity,retail_price,wholesale_price,image_path,hybrid_type,department_info.department_name FROM menu LEFT JOIN department_store_stock ON menu.department_item_id = department_store_stock.department_item_id LEFT JOIN centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id  LEFT JOIN item_unit ON menu.unit_id = item_unit.unit_id  LEFT JOIN department_info ON menu.department_id= department_info.department_id   WHERE menu.department_id = ? ORDER BY date desc";
          String strget = "SELECT menu_id,menu_name,menu.department_item_id,item_type,centerstore_stock.item_name,menu.unit_id,item_unit.unit_name,quantity,retail_price,wholesale_price,image_path,hybrid_type,department_info.department_name,menu_image FROM menu LEFT JOIN department_store_stock ON menu.department_item_id = department_store_stock.department_item_id LEFT JOIN centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id  LEFT JOIN item_unit ON menu.unit_id = item_unit.unit_id  LEFT JOIN department_info ON menu.department_id= department_info.department_id LEFT JOIN department_user ON menu.department_id = department_user.department_id   WHERE department_user.user_id = ? AND menu.menu_name LIKE ? ORDER BY date desc";
        try{
            dbget.initConnection();
            stmtget = dbget.conn.prepareStatement(strget);
            stmtget.setInt(1, user_id);
            stmtget.setString(2, menu);
            rsget = stmtget.executeQuery();
            while(rsget.next()){
               Object imagepath = (rsget.getObject("image_path") != null )?rsget.getObject("image_path"):"";
                Object[] row = new Object[]{rsget.getString("menu_id"),rsget.getString("menu_name"),rsget.getBoolean("item_type"),rsget.getString("department_item_id"),rsget.getString("item_name"),rsget.getString("unit_id"),rsget.getString("unit_name"),rsget.getBigDecimal("quantity"),rsget.getBigDecimal("retail_price"),rsget.getBigDecimal("wholesale_price"),imagepath,rsget.getBoolean("hybrid_type"),rsget.getString("department_name"),rsget.getObject("menu_image")};
                data.add(row);
                        
            }
            MenuInfo = data.toArray(new Object[data.size()][]);
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from getMenuInfoObjectBySearchLike "+getClass().getName());
        }
        finally{
            dbget.closeConnection();
        }
        return MenuInfo;
    }
     public DefaultTableModel getSingleTrackableItem(String menuid){
           PreparedStatement stmtget;
        ResultSet rsget ;
        DBConnect dbget  = new DBConnect();
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        String ColName[] = new String[]{"ItemName","Stock Available"};
        
        String strget = "SELECT menu.department_item_id,centerstore_stock.item_name,department_store_stock.total_qty,item_unit.unit_relative_quantity,item_unit.unit_name FROM menu INNER JOIN department_store_stock ON menu.department_item_id= department_store_stock.department_item_id INNER JOIN centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id  INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id  WHERE menu_id = ? ";
        try{
            dbget.initConnection();
            stmtget = dbget.conn.prepareStatement(strget);
            stmtget.setString(1, menuid);
            rsget = stmtget.executeQuery();
            while(rsget.next()){
                BigDecimal TotalItem = rsget.getBigDecimal("total_qty").divide(rsget.getBigDecimal("unit_relative_quantity"),3,RoundingMode.HALF_UP);
//               System.out.println(TotalItem);
                Object[] row = new Object[]{rsget.getString("item_name"),TotalItem+rsget.getString("unit_name")};
                data.add(row);
            }
            MenuInfo = data.toArray(new Object[data.size()][]);
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from getmenuinfo");
        }
        finally{
            dbget.closeConnection();
        }
        return new DefaultTableModel(MenuInfo,ColName){
            
            @Override
            public boolean isCellEditable(int row,int col){
                return false;
            }
        };
        
         
     }
      public DefaultTableModel getHybridTrackableItem(String menuid){
           PreparedStatement stmtget;
        ResultSet rsget ;
        DBConnect dbget  = new DBConnect();
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        String ColName[] = new String[]{"ItemName","Stock Available"};
        
//        String strget = "SELECT centerstore_stock.item_name,(resturant_store.total_qty/(item_unit.unit_relative_quantity)) as total_qty,item_unit.unit_name  from hybrid_menu INNER JOIN item_unit ON hybrid_menu.unit_id = item_unit.unit_id INNER JOIN centerstore_stock ON hybrid_menu.item_id = centerstore_stock.item_id INNER JOIN resturant_store ON hybrid_menu.item_id= resturant_store.item_id where parent_menu_id  in (?)";
        String strget = "SELECT centerstore_stock.item_name,(department_store_stock.total_qty/(item_unit.unit_relative_quantity)) as total_qty,item_unit.unit_name  from hybrid_menu INNER JOIN item_unit ON hybrid_menu.unit_id = item_unit.unit_id INNER JOIN department_store_stock ON hybrid_menu.department_item_id = department_store_stock.department_item_id  INNER JOIN centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id  where parent_menu_id  in (?)";
        try{
            dbget.initConnection();
            stmtget = dbget.conn.prepareStatement(strget);
            stmtget.setString(1, menuid);
            rsget = stmtget.executeQuery();
            while(rsget.next()){
//                BigDecimal TotalItem = rsget.getBigDecimal("total_qty").divide(rsget.getBigDecimal("unit_relative_quantity"));
//               System.out.println(TotalItem);
                Object[] row = new Object[]{rsget.getString("item_name"),rsget.getBigDecimal("total_qty").setScale(3, RoundingMode.HALF_UP)+rsget.getString("unit_name")};
                data.add(row);
            }
            MenuInfo = data.toArray(new Object[data.size()][]);
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from getHybridTrackableItem");
        }
        finally{
            dbget.closeConnection();
        }
        return new DefaultTableModel(MenuInfo,ColName){
            
            @Override
            public boolean isCellEditable(int row,int col){
                return false;
            }
        };
        
         
     }
       public BigDecimal getHybridItemAvailable(String menuid){
           PreparedStatement stmtget;
        ResultSet rsget ;
        DBConnect dbget  = new DBConnect();
        BigDecimal Avaiable = BigDecimal.ZERO;
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        ArrayList<BigDecimal> ItemStockData = new ArrayList<>();
        BigDecimal MenuQuantity = BigDecimal.ZERO;
//        String ColName[] = new String[]{"ItemName","Stock Available"};
        
        String strget = "SELECT centerstore_stock.item_name,(department_store_stock.total_qty/(item_unit.unit_relative_quantity)) as total_qty,item_unit.unit_name,hybrid_menu.quantity as hybridquantity,menu.quantity as menuquantity  from hybrid_menu INNER JOIN department_store_stock ON hybrid_menu.department_item_id= department_store_stock.department_item_id INNER JOIN item_unit ON hybrid_menu.unit_id = item_unit.unit_id INNER JOIN centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id  INNER JOIN menu ON hybrid_menu.parent_menu_id = menu.menu_id where parent_menu_id  in (?)";
        try{
            dbget.initConnection();
            stmtget = dbget.conn.prepareStatement(strget);
            stmtget.setString(1, menuid);
            rsget = stmtget.executeQuery();
            while(rsget.next()){
                BigDecimal TotalItem = rsget.getBigDecimal("total_qty").divide(rsget.getBigDecimal("hybridquantity"),3,RoundingMode.HALF_UP);
                ItemStockData.add(TotalItem);
//               System.out.println(TotalItem);
                MenuQuantity = rsget.getBigDecimal("menuquantity");
                Object[] row = new Object[]{rsget.getString("item_name"),rsget.getBigDecimal("total_qty")+rsget.getString("unit_name")};
                data.add(row);
            }
            MenuInfo = data.toArray(new Object[data.size()][]);
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from getHybridItemAvailable "+getClass().getName());
        }
        finally{
            dbget.closeConnection();
        }
        Collections.sort(ItemStockData);
//        System.out.println(ItemStockData.get(0));
        MenuQuantity = ItemStockData.get(0).divide(MenuQuantity,3,RoundingMode.HALF_UP);
        
       return MenuQuantity ;
        
         
     }
       public Object[] getRespectiveDepartmentFromMenuId(int menuid){
        PreparedStatement stmt;
        ResultSet rs;
        String st = "SELECT menu.department_id,department_info.department_name  FROM menu  INNER JOIN department_infO ON menu.department_id = department_info.department_id WHERE menu_id = ? ";
        int did = 0;
        Object[] data =  new Object[2];
        try{
         initConnection();
         stmt = conn.prepareStatement(st);
         stmt.setInt(1, menuid);
//         System.out.println(menuid);
         
        rs = stmt.executeQuery();
         while(rs.next()){
//             did = rs.getInt(1);
             data[0] = rs.getObject(1);
             data[1] = rs.getObject(2);
         }
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from getRespectiveDepartmentFromMenuId"+getClass().getName());
        }
        finally{
            closeConnection();
        }
        return data;
    }
    
}
