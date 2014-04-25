/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.threshold;

import database.DBConnect;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
public class ThresholdModel extends DBConnect {
    Object[][] FullThresholdData =null;
    Object[] ItemName = null;
    PreparedStatement stmtUpdateThreshold;
    
    
            public DefaultTableModel  getItemList(int storeid){
                String strQuery = "SELECT department_store_stock.department_item_id,centerstore_stock.item_name,department_store_stock.item_threshold,item_unit.unit_type FROM department_store_stock INNER JOIN centerstore_stock ON department_store_stock.item_id = centerstore_stock.item_id INNER JOIN item_unit ON centerstore_stock.unit_id = item_unit.unit_id WHERE  department_id = ? ";
                String ColumnNames[] = {"Item Name", "Threshold","Unit Type"};
                ArrayList<Object[]> data = new ArrayList<>();
                ArrayList<Object[]> fulldata = new ArrayList<>();
                ArrayList<Object> ItemName = new ArrayList<>();

                Object[][] finaldata = null;
//                DBConnect getItem = new  DBConnect();
                ResultSet rsResult;
                PreparedStatement stmtGetItem;
                try{
                    initConnection();
                    stmtGetItem = conn.prepareCall(strQuery);
                    stmtGetItem.setInt(1, storeid);
                    rsResult = stmtGetItem.executeQuery();
                    while(rsResult.next()){
                        Object[] row = new Object[]{/*rsResult.getString("item_id"),*/rsResult.getString("item_name"),rsResult.getFloat("item_threshold"),rsResult.getString("unit_type")};
                         Object[] fullrow = new Object[]{rsResult.getString("department_item_id"),rsResult.getString("item_name"),rsResult.getFloat("item_threshold")};
                         Object Name = rsResult.getString("item_name");
                         data.add(row);
                    fulldata.add(fullrow);
                    ItemName.add(Name);
                    }
                    finaldata= data.toArray(new Object[data.size()][]);
                    FullThresholdData = fulldata.toArray(new Object[data.size()][]);
                    this.ItemName = ItemName.toArray(new Object[ItemName.size()]);

                }
                catch(SQLException se){
                    JOptionPane.showMessageDialog(null, se+"from thresholdmodel "+getClass().getName() );
                }
               
                finally{
                    closeConnection();
                }
                return new DefaultTableModel(finaldata,ColumnNames){
                  @Override
                  public boolean isCellEditable(int rows,int columns){
                  //all cell false
                      switch(columns){
                          case 1:
                              return true;
                             
                      }
                      return false;
                  }

               };
            }
            public String[][] returnItemIdplusThreshold(String[][] data){
                int row = FullThresholdData.length;
                for(int i=0;i<row;i++){
                   // for(Object st:FullThresholdData[i]){
                   // for(int) 
                    if(FullThresholdData[i][1].equals(data[i][0])){
                            data[i][0] =FullThresholdData[i][0].toString();
                        }
                    //}

                }
                return data;
            }
            
            public void  EntryThreshold(String[][] thresholddata){
                String strQuery = "UPDATE department_store_stock SET item_threshold = ? WHERE  department_item_id =?";
//                DBConnect updThreshold = new DBConnect();
                try{
                   initConnection();
                  
                    for (String[] thresholddata1 : thresholddata) {
                         conn.setAutoCommit(false);
                        stmtUpdateThreshold = conn.prepareStatement(strQuery);
                        stmtUpdateThreshold.setBigDecimal(1, new BigDecimal(thresholddata1[1]).setScale(3, RoundingMode.HALF_UP));
                        stmtUpdateThreshold.setInt(2, Integer.parseInt(thresholddata1[0]));
//                        System.out.println(thresholddata1[0]);
                        stmtUpdateThreshold.executeUpdate();
                    }
//                  conn.setAutoCommit(true);
                   conn.commit();
                    JOptionPane.showMessageDialog(null, "Threshold Update Successfully");
                   
                }
                catch(SQLException se){
                    JOptionPane.showMessageDialog(null, se+"from entrythreshold");
                }
               
                finally{
                    closeConnection();
                }
                
            }
            public int returnItemId(String itemname){
                int row = FullThresholdData.length;
                int ItemId = 0;
                for(int i=0;i<row;i++){
                   // for(Object st:FullThresholdData[i]){
                   // for(int) 
                    if(FullThresholdData[i][1].equals(itemname)){
                           ItemId  =Integer.parseInt(FullThresholdData[i][0].toString());
                           break;
                    }
                    //}

                }
                return ItemId;
                
            }
            public void EntrySingleThreshold(int itemid,String threshold){
                 String strQuery = "UPDATE department_store_stock SET item_threshold = ? WHERE  department_item_id =?";
//                DBConnect updThreshold = new DBConnect();
                try{
                    initConnection();
                    conn.setAutoCommit(false);
                  
                    stmtUpdateThreshold = conn.prepareStatement(strQuery);
                    stmtUpdateThreshold.setBigDecimal(1, new BigDecimal(threshold).setScale(3, RoundingMode.HALF_UP));
                    stmtUpdateThreshold.setInt(2,itemid);
                    stmtUpdateThreshold.executeUpdate();
                   
//                   updThreshold.conn.setAutoCommit(true);
                   conn.commit();
                   
                }
                catch(SQLException se){
                    JOptionPane.showMessageDialog(null, se+"from entrythreshold");
                }
              
               
                finally{
                   closeConnection();
                }
                
            }
             public Object[][] getRespectiveDepartment(int userid){
    PreparedStatement stmt = null;
      ResultSet rs;
      ArrayList<Object[]> data = new ArrayList<>();
      try{
          initConnection();
          stmt = conn.prepareStatement("SELECT department_info.department_id,department_info.department_name from department_user INNER JOIN department_info ON department_user.department_id = department_info.department_id WHERE user_id = ?");
         stmt.setInt(1, userid);
          rs = stmt.executeQuery();
          while(rs.next()){
         Object[] row = new Object[]{rs.getObject(1),rs.getObject(2)};
         data.add(row);
           
          }
          
      }
      catch(SQLException se ){
          JOptionPane.showMessageDialog(null, se+"from getotherStoreName "+getClass().getName());
      }
      finally{
          closeConnection();
      }
      return data.toArray(new Object[data.size()][]);
  }
             public Object[] returnSecondColumn(Object[][] data){
                 ArrayList<Object> obj = new ArrayList<>();
                 for( Object data1[]:data){
                     obj.add(data1[1]);
                 }
                 return obj.toArray(new Object[obj.size()]);
             }
  /*  public void getItemName(){
        
    }
    * */
}
