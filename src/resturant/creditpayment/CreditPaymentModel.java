/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package resturant.creditpayment;

import database.DBConnect;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import reusableClass.DisplayMessages;
import reusableClass.Function;

/**
 *
 * @author SUSHIL
 */
public class CreditPaymentModel extends DBConnect {
    
    public void addCreditPayment(Object[] data,int userid){
       /*   Object[] data = new Object[5];
        data[0] = getDepartmentId();
        data[1] = getBillId();
        data[2] = getCustomerId();
        data[3] = getAmountReceivable();
        data[4] = getAmountPaid();
       */
        PreparedStatement stmt;
        ResultSet rs;
        String strpay ="INSERT INTO bill (item_total_amount,service_charge,vat,bill_discount,bill_total,total_received,customer_id,payment_type,bill_datetime,bill_id,user_id,department_id,complimentary_flag,complimentary_amount,repay,com_bill_datetime) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //connecting to database and inserting
        try{
        initConnection();
        conn.setAutoCommit(false);
        stmt = conn.prepareStatement(strpay);
        stmt.setDouble(1, 0.0);
        stmt.setDouble(2, 0.0);
        stmt.setDouble(3, 0.0);
        stmt.setDouble(4, 0.0);
        stmt.setDouble(5, 0.0);//initial bill amount contain all the amount
        stmt.setDouble(6,((Number)data[4]).doubleValue());//amount customer paid
        stmt.setInt(7, ((Number)data[2]).intValue());
        stmt.setInt(8, 1);//because it is cash type
        stmt.setDate(9,new java.sql.Date(Function.returnSystemDate().getTime()));
        stmt.setInt(10, ((Number)data[1]).intValue());
        stmt.setInt(11, userid);
        stmt.setInt(12, ((Number)data[0]).intValue());
        stmt.setInt(13, 0);
        stmt.setDouble(14, 0.0);
        stmt.setInt(15, 0);
        //this retreive the system date of th  computer
        stmt.setTimestamp(16, new Timestamp(new Date().getTime()));
        stmt.executeUpdate();
        
        conn.commit();
        DisplayMessages.displayInfo(null, "Payment Saved Succesfully", "Payment Window");
        
        }
        catch(SQLException se){
            DisplayMessages.displayError(null, se.getMessage()+"form addCreditPayment"+getClass().getName(),"SQLError");
        }
         finally{
           closeConnection();
        }
    }
     public Object[][] getCustomerInfoObject(){
        PreparedStatement stmtget;
        ResultSet rsget ;
       
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        Object[][] CustomerInfo = null;
        
        String strget = "SELECT customer_id,customer_name FROM customer_info ";
        try{
          initConnection();
            stmtget = conn.prepareStatement(strget);
            rsget = stmtget.executeQuery();
            while(rsget.next()){
                Object[] row = new Object[]{rsget.getInt("customer_id"),rsget.getString("customer_name")};
                data.add(row);
            }
            CustomerInfo = data.toArray(new Object[data.size()][]);
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from getCustomerInfoObject"+getClass().getName());
        }
        finally{
           closeConnection();
        }
        return CustomerInfo;
    }
     public BigDecimal getCustomerReceivableAmount(int customerid,int departmentid){
         PreparedStatement stmt;
         ResultSet rs;
         String strquery = "SELECT bill_total,total_received FROM bill WHERE customer_id = ? and department_id = ?";
//         ArrayList<BigDecimal> amounttotal = new ArrayList<>();
//         ArrayList<BigDecimal> amountpaid = new ArrayList<>();
         BigDecimal amttotal = BigDecimal.ZERO;
         BigDecimal amtpaid = BigDecimal.ZERO;
         
         try{
             initConnection();
             stmt = conn.prepareStatement(strquery);
//             System.out.println(customerid+"\n"+departmentid);
             stmt.setInt(1, customerid);
             stmt.setInt(2, departmentid);
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
