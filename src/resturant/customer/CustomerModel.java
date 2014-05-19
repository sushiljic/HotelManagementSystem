/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.customer;

import database.DBConnect;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import reusableClass.DisplayMessages;

/**
 *
 * @author SUSHIL
 */
public class CustomerModel extends DBConnect{
    PreparedStatement stmtAdd;
    String strQuery;
            
        public void AddCustomer(String[] data){
        /*
        info[0] = getCustomerId();
        info[1] = getCustomerName();
        info[2] = getCustomerPhone();
        info[3] = getCustomerAddress();
        info[4] = getCustomerType();
         */
        strQuery = "INSERT INTO customer_info (customer_name,customer_phone,customer_address,customer_type) VALUES(?,?,?,?)";
        DBConnect waiterAdd = new DBConnect();
        try{
            waiterAdd.initConnection();
            waiterAdd.conn.setAutoCommit(false);
            stmtAdd = waiterAdd.conn.prepareStatement(strQuery);
            stmtAdd.setString(1, data[1]);
            stmtAdd.setString(2, data[2]);
            stmtAdd.setString(3, data[3]);
            stmtAdd.setString(4, data[4]);
            stmtAdd.executeUpdate();
            
            waiterAdd.conn.commit();
            JOptionPane.showMessageDialog(null, "Customer Added Successfully");
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null , se+"From AddCustomer");
        }
        finally{
            waiterAdd.closeConnection();
        }
    
    
        }
          public void EditCustomer(String[] data){
        /*
          info[0] = getCustomerId();
        info[1] = getCustomerName();
        info[2] = getCustomerPhone();
        info[3] = getCustomerAddress();
        info[4] = getCustomerType();
         */
        strQuery = "UPDATE  customer_info  SET customer_name = ?,customer_phone = ?,customer_address = ?, customer_type =? WHERE customer_id = ?";
//        DBConnect waiterAdd = new DBConnect();
        try{
            initConnection();
           conn.setAutoCommit(false);
            stmtAdd = conn.prepareStatement(strQuery);
            stmtAdd.setString(1, data[1]);
            stmtAdd.setString(2, data[2]);
            stmtAdd.setString(3, data[3]);
            stmtAdd.setString(4, data[4]);
            stmtAdd.setInt(5, Integer.parseInt(data[0]));
            stmtAdd.executeUpdate();
            
           conn.commit();
            JOptionPane.showMessageDialog(null, "Customer Edited Successfully");
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null , se+"From EditCustomer");
        }
        finally{
            closeConnection();
        }
    
    
        }
          public void DeleteCustomer(String[] data){
        /*
      info[0] = getCustomerId();
        info[1] = getCustomerName();
        info[2] = getCustomerPhone();
        info[3] = getCustomerAddress();
        info[4] = getCustomerType();
         */
        strQuery = "DELETE FROM    customer_info   WHERE customer_id= ?";
//        DBConnect waiterAdd = new DBConnect();
        try{
           initConnection();
           conn.setAutoCommit(false);
            stmtAdd = conn.prepareStatement(strQuery);
            stmtAdd.setInt(1, Integer.parseInt(data[0]));
            stmtAdd.executeUpdate();
            
            conn.commit();
            JOptionPane.showMessageDialog(null, "Customer Deleted Successfully");
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null , se+"From DeleteCustomer");
        }
        finally{
            closeConnection();
        }
    
    
        }
          public DefaultTableModel getTableModelCustomerInfo(){
      //   int colcount;
     //  int rowcount ;
      // String[] ColumnNames = { "Issue Id", "Item Name", "Issue Quantity","item BaseUnit","Issue From", "Issue To","Issue Date"};
       String strQuery = "SELECT customer_id,customer_name,customer_phone,customer_address,customer_type FROM customer_info ORDER BY customer_id desc ";
       String ColumnNames[] = {"Customer Id","Customer Name","Customer Phone","Customer Address","Customer Type"};
      //Object[][] data = null;
     //  List<Object[]> data = new ArrayList<Object[]>();
   ArrayList<Object[]> data = new ArrayList<Object[]>();
    Object[][] finalData =null;
//       DBConnect getWaiter = new DBConnect();
       ResultSet rsResult;
       PreparedStatement stmtIssueInfo;
       try{
          initConnection();
           stmtIssueInfo = conn.prepareStatement(strQuery);
           rsResult = stmtIssueInfo.executeQuery();
           
          // ResultSetMetaData metadata = rsResult.getMetaData();
         //  colcount = metadata.getColumnCount();
            
           while(rsResult.next()){
              Object[] row = new Object[]{rsResult.getString("customer_id"),rsResult.getString("customer_name"),rsResult.getString("customer_phone"),rsResult.getString("customer_address"),rsResult.getString("customer_type")};
              /* for(int i=0;i<colcount;i++){
                   row[i] = rsResult.getObject(i+1);
               }*/
              
               data.add(row);
               
              // data
              
           }
            finalData = data.toArray(new Object[data.size()][]);
           
       }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"form getTableModelCustomerInfo");
        }
         finally{
           closeConnection();
       }
       return new DefaultTableModel(finalData,ColumnNames){
          @Override
          public boolean isCellEditable(int rows,int columns){
          //all cell false
                  return false;    
          }
                  
       };
    }
    public boolean checkExistingName(String menuname){
     Boolean ExistingStatus = null; 
    String strCheck = "SELECT waiter_name FROM waiter_info WHERE waiter_name = ? ";
//    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
       initConnection();
        stmtcheck = conn.prepareStatement(strCheck,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        stmtcheck.setString(1, menuname);
        ResultSet rs = stmtcheck.executeQuery();
        rs.last();
        int rows = rs.getRow();
        rs.beforeFirst();
        ExistingStatus = rows >= 1;
        
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from checkExistingName");
    }
    finally{
       closeConnection();
    }
    return ExistingStatus;
    
}
    
    //check whether customer have relation with the bill or has to pay
    
    public BigDecimal getCustomerReceivableAmount(int customerid){
         PreparedStatement stmt;
         ResultSet rs;
         String strquery = "SELECT bill_total,total_received FROM bill WHERE customer_id = ?";
//         ArrayList<BigDecimal> amounttotal = new ArrayList<>();
//         ArrayList<BigDecimal> amountpaid = new ArrayList<>();
         BigDecimal amttotal = BigDecimal.ZERO;
         BigDecimal amtpaid = BigDecimal.ZERO;
         
         try{
             initConnection();
             stmt = conn.prepareStatement(strquery);
//             System.out.println(customerid+"\n"+departmentid);
             stmt.setInt(1, customerid);
//             stmt.setInt(2, departmentid);
             rs= stmt.executeQuery();
             while(rs.next()){
//               amounttotal.add(rs.getBigDecimal("bill_total"));
//               amountpaid.add(rs.getBigDecimal("total_received"));
//                 System.out.println(rs.getBigDecimal("bill_total"));
              amttotal =  amttotal.add(rs.getBigDecimal("bill_total"));
             amtpaid =   amtpaid.add(rs.getBigDecimal("total_received"));
               
             }
//           System.out.println(amttotal + " \n"+ amtpaid);
         }
         catch(SQLException se){
             DisplayMessages.displayError(null, se.getMessage()+" from getCustomerReceivableAmount "+getClass().getName(), "Error");
         }
         finally{
             closeConnection();
         }
         return amttotal.subtract(amtpaid);
     }
    
}
