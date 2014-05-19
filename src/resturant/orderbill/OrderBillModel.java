/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.orderbill;

import database.DBConnect;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import reusableClass.Function;

/**
 *
 * @author SUSHIL
 */
public class OrderBillModel  extends DBConnect{
    PreparedStatement stmtorderbill;
    ResultSet rsordernill;
    String rsQuery = new String();
    Object[][] CustomerInfo;
            
    
    public void AddBill(Object[][] menudata,Double[] ratedata,String[] otherdata,String[] orderdata,String[][] complimentary,int userid,int departmentid){
        /*
         *     
         * this id for ratedata
        data[0] = getTotal();
          if(getBooleanSVCPercentage()){
            System.out.println(getSVCAmount());
          data[1] = getSVCAmount();
          }
          else{
          data[1] = getSVC();
          }
          
          data[2] = getVATAmount();
          data[3 ]= getSubTotal();
          if(getBooleanDiscountPercentage()){
          data[4] = getDiscountAmount();
          System.out.println(getDiscountAmount());
          }
          else{
           data[4] = getDiscount();
          }
        
          data[5] = getGrandTotal1();
         // data[6] = getOrderId();
          data[6] = getTenderedAmount();
          data[7] = getChangeAmount();
          * this is for bill otherdata
          *   data[0] = getBillId();
          data[1] = String.valueOf(getBooleanSVCPercentage());
          data[2] = String.valueOf(getBooleanDiscountPercentage());
          data[3] = getCustomerId();
          data[4] = String.valueOf(getPaymentType());
          data[5] = String.valueOf(getBooleanComplimentaryType());
          data[6] = getComplimentaryID();
         */
        PreparedStatement stmtbill;
        PreparedStatement stmtorder;
        PreparedStatement stmtbillitemlist;
        PreparedStatement stmttablereset;
        PreparedStatement stmtordertable;
        PreparedStatement stmtcomplimentary;
        String strBill = "INSERT INTO bill (item_total_amount,service_charge,vat,bill_discount,bill_total,total_received,customer_id,payment_type,bill_datetime,bill_id,user_id,department_id,complimentary_flag,complimentary_amount,com_bill_datetime) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String strBillItemInfo = "INSERT INTO bill_item_info (bill_id,menu_id,quantity,complimentary_type) VALUES(?,?,?,?) ";
        String strUpdateOrder = "UPDATE order_list SET paid = ?,bill_id = ? WHERE order_id = ?";
        String strtablereset = " UPDATE  table_info set table_status = 0 WHERE table_id = ?";
        String strordertable = "DELETE FROM temp_order_table WHERE order_id = ?";
        String strcomplimentarybill = "INSERT INTO bill_complimentary(bill_id,complimentary_id) VALUES(?,?)";
        Double ComplimentaryAmount = 0.0;
        /*
        maipulating and getting array of the table_id for updating the table unpack
        */
        ArrayList<Integer> tablelist = new ArrayList<>();
        for(String iod:orderdata){
            int tid = returnTableIdByOrderId(Integer.parseInt(iod));
            if(tid != 0){
                tablelist.add(tid);
            }
        }
        DBConnect dbconn = new DBConnect();
       
        
        try{
            dbconn.initConnection();
           
                /*
           * inserting data into bill_item_info
           */
          stmtbillitemlist = dbconn.conn.prepareStatement(strBillItemInfo);
            for (Object[] menudata1 : menudata) {
                stmtbillitemlist.setInt(1,Integer.parseInt(otherdata[0]));
                stmtbillitemlist.setString(2, menudata1[0].toString());
                stmtbillitemlist.setBigDecimal(3, new BigDecimal(menudata1[2].toString()));
                /*
                 * checking for the complimentary item
                 */
                //  System.out.println(complimentary.length);
                if (complimentary.length != 0) {
                    for (String[] complimentary1 : complimentary) {
                        if (complimentary1[0].equalsIgnoreCase(menudata1[0].toString())) {
                            // System.out.println("inside complimentary"+menudata[i][0]+"\n");
                            stmtbillitemlist.setInt(4, 1);
                            ComplimentaryAmount += Double.parseDouble(menudata1[5].toString()); 
                            break;
                        } else {
                            stmtbillitemlist.setInt(4, 0);
                            //  System.out.println("outside complimentary"+menudata[i][0]);
                        }
                    }
                } else {
                    //  System.out.println("outside loops"+menudata[i][0]);
                    stmtbillitemlist.setInt(4, 0);
                }
                stmtbillitemlist.executeUpdate();
//                System.out.println(ComplimentaryAmount);
            }
            /*
            for inserting into bill table
            */
            stmtbill = dbconn.conn.prepareStatement(strBill);
            dbconn.conn.setAutoCommit(false);
            stmtbill.setBigDecimal(1,new BigDecimal(ratedata[0]));
          
//            //checking for svc is percent or notif svc is in percentage convert in into number
//            if(otherdata[1].equalsIgnoreCase("false")){
//                
//           // stmtbill.setBigDecimal(2,new BigDecimal(ratedata[1]));
//             stmtbill.setDouble(2, ratedata[1]);
//            }
//            else{
//                 
//                //calcute svc value to stored in database
//              //  System.out.println(new BigDecimal(ratedata[1]).multiply(new BigDecimal(ratedata[0])));
//          //  stmtbill.setBigDecimal(2,new BigDecimal(ratedata[1]).multiply(new BigDecimal(ratedata[0])));
//               stmtbill.setDouble(2, ratedata[1]*ratedata[0]);
//                 
//            }
            //for svc
            stmtbill.setDouble(2, ratedata[1]);
            
          
//            stmtbill.setDouble(3, ratedata[2]*ratedata[0]);
          //for vat
            stmtbill.setDouble(3,ratedata[2]);
            
            
            //checking where discount is in percentage and calculating resultaant
//            if(otherdata[2].equalsIgnoreCase("false")){
//                 // stmtbill.setBigDecimal(4, new BigDecimal(ratedata[4]));
//                 stmtbill.setDouble(4, ratedata[4]);
//                 //  System.out.println(ratedata[4]);
//            }
//            else{
//              //  System.out.println(new BigDecimal(ratedata[4]).multiply(new BigDecimal(ratedata[3])));
//               
//              //  stmtbill.setBigDecimal(4, new BigDecimal(ratedata[4]).multiply(new BigDecimal(ratedata[3])));
//                stmtbill.setDouble(4, ratedata[4]*ratedata[3]);
//            }
          //for discount
           stmtbill.setDouble(4, ratedata[4]-ComplimentaryAmount);
           // stmtbill.setBigDecimal(5, new BigDecimal(ratedata[5]));
            stmtbill.setDouble(5, ratedata[5]);
            
            if(ratedata[6]>ratedata[5]){
            //  stmtbill.setBigDecimal(6, new BigDecimal(ratedata[5])); 
              stmtbill.setDouble(6, ratedata[5]);
              // System.out.println("wala");
            }
            else{
          //  stmtbill.setBigDecimal(6, new BigDecimal(ratedata[6]));
            stmtbill.setDouble(6, ratedata[6]);
           //  System.out.println("wala");
            }
            stmtbill.setInt(7, Integer.parseInt(otherdata[3]));
            
            
          if(otherdata[4].equalsIgnoreCase("true")){
              stmtbill.setInt(8, 1);
          }
          else{
              stmtbill.setInt(8, 0);
          }
          
//          stmtbill.setTimestamp(9, new Timestamp(new Date().getTime()));
         //inserting the system date into datbaase
          stmtbill.setTimestamp(9, new Timestamp(Function.returnSystemDate().getTime()));
          stmtbill.setInt(10, Integer.parseInt(otherdata[0]));
          stmtbill.setInt(11,userid);
          stmtbill.setInt(12, departmentid);
           if(otherdata[5].equalsIgnoreCase("true")){
           
               stmtbill.setInt(13, 1);
           }
           else{
              stmtbill.setInt(13, 0);  
           }
         stmtbill.setDouble(14, ComplimentaryAmount);
         //this get the date of the computer system
         stmtbill.setTimestamp(15, new Timestamp(new Date().getTime()));
          stmtbill.executeUpdate();
           
      
          /*
           * updating the data into order table
           */
          stmtorder = dbconn.conn.prepareStatement(strUpdateOrder);
            for (String orderdata1 : orderdata) {
                //System.out.println(orderdata[i]);
                stmtorder.setInt(1, 1);
                stmtorder.setInt(2,Integer.parseInt(otherdata[0]));
                stmtorder.setInt(3, Integer.parseInt(orderdata1));
                stmtorder.executeUpdate();
            }
            /*
            updating the table statuse in table_info
            */
            for(Integer tblid:tablelist){
                dbconn.conn.setAutoCommit(false);
                 stmttablereset = dbconn.conn.prepareStatement(strtablereset);
            stmttablereset.setInt(1, tblid);
            stmttablereset.executeUpdate();
                
            }
           /*
            deleting the temp_order_table
            */
            for(String tblid:orderdata){
                dbconn.conn.setAutoCommit(false);
                stmtordertable = dbconn.conn.prepareStatement(strordertable);
                stmtordertable.setInt(1, Integer.parseInt(tblid));
                stmtordertable.executeUpdate();
            }
            //for inserting the bill and complimentary reason if the complimentary is checked
            if(otherdata[5].equalsIgnoreCase("true")){
                dbconn.conn.setAutoCommit(false);
//                JOptionPane.showMessageDialog(null, "entering the data into complimentary");
                stmtcomplimentary = dbconn.conn.prepareStatement(strcomplimentarybill);
                stmtcomplimentary.setInt(1,Integer.parseInt(otherdata[0]));
                stmtcomplimentary.setInt(2,Integer.parseInt(otherdata[6]));
                stmtcomplimentary.executeUpdate();
            }
          /*
           * if al goes well commit the database
           */
          dbconn.conn.commit();
          JOptionPane.showMessageDialog(null, "Bill Recorded Succesfully");
            
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from AddBill");
          //  se.printStackTrace();
        }
        finally{
            dbconn.closeConnection();
        }
        
    }
    
      public Object[][] convertDefaultTableModelToObject(DefaultTableModel model){
            int rows = model.getRowCount();
           int cols = model.getColumnCount();
            Object[][] data = new Object[rows][cols]; 
            for(int i = 0;i<model.getRowCount();i++){
                for(int j =0;j<model.getColumnCount();j++){
                    data[i][j] = model.getValueAt(i, j);
                }
            }
            return data;
        }
      
       public Object[][] getCustomerInfoObject(){
        PreparedStatement stmtget;
        ResultSet rsget ;
        DBConnect dbget  = new DBConnect();
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        
        String strget = "SELECT customer_id,customer_name FROM customer_info ";
        try{
            dbget.initConnection();
            stmtget = dbget.conn.prepareStatement(strget);
            rsget = stmtget.executeQuery();
            while(rsget.next()){
                Object[] row = new Object[]{rsget.getString("customer_id"),rsget.getString("customer_name")};
                data.add(row);
            }
            CustomerInfo = data.toArray(new Object[data.size()][]);
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from getCustomerInfoObject");
        }
        finally{
            dbget.closeConnection();
        }
        return CustomerInfo;
    }
       public String[] returnMenuName(Object[][] obj){
        String[] Name = new String[obj.length];
        for(int i=0;i<obj.length;i++){
            Name[i] = obj[i][1].toString();
        }
        return Name;
    }   

       //is copied to funtion class because it is resuable component
//      public int returnCurrentItentityBillId(String tablename){
//     //Boolean ExistingStatus = null; 
//        int id = 0;
//        int em =0;
//        String TableName = tablename;
//        String strempty = "SELECT  autoinc_billid as id FROM generate_billid WHERE bill_status = 0  LIMIT 1  ";
//    String strCheck = "INSERT INTO  generate_billid (bill_status)   VALUES(1) ";
////    String strGetId = " SELECT @@IDENTITY as id";
//    String strGetId = " SELECT last_insert_id()";
//    DBConnect check = new DBConnect();
//    PreparedStatement stmtcheck ;
//    try{
//        check.initConnection();
//       check.conn.setAutoCommit(false);
//        stmtcheck = check.conn.prepareStatement(strempty);
//        ResultSet rse = stmtcheck.executeQuery();
//       while(rse.next()){
//           em = rse.getInt("id");
////           System.out.println(em);
////           break;
//       }
//       if(em != 0 ){
//           id = em;
////           System.out.println("wala");
//            check.conn.setAutoCommit(false);
//           stmtcheck = check.conn.prepareStatement("UPDATE generate_billid SET bill_status = 1 WHERE autoinc_billid = ?");
//           stmtcheck.setInt(1, id);
//          stmtcheck.executeUpdate();
////          System.out.println(id);
//       }
//       else{
//            check.conn.setAutoCommit(false);
//        stmtcheck = check.conn.prepareStatement(strCheck);
//        stmtcheck.executeUpdate();
//         check.conn.setAutoCommit(false);
//        stmtcheck = check.conn.prepareStatement(strGetId);
////     stmtcheck.setString(1, oid);
//        ResultSet rs = stmtcheck.executeQuery();
//        while(rs.next()){
//        id = rs.getInt(1);
////        System.out.println("wala"+id);
//        }
//       }
//       check.conn.commit();
//        
//       
//        
//        
//    }
//    catch(SQLException se){
//        JOptionPane.showMessageDialog(null, se+"from returncurrentIdentityid");
//    }
//   
//    finally{
//        check.closeConnection();
//    }
//    return id;
//    
//}
//       public void MakeCurrentItentityIdFalse(int orderid){
//     //Boolean ExistingStatus = null; 
//        
//        int oid = orderid;
//        String strempty = "UPDATE  generate_billid SET  bill_status = 0 WHERE autoinc_billid = ?   ";
//   
//    DBConnect check = new DBConnect();
//    PreparedStatement stmtcheck ;
//    try{
//        check.initConnection();
//      
//        stmtcheck = check.conn.prepareStatement(strempty);
//        stmtcheck.setInt(1, oid);
//       stmtcheck.executeUpdate();
//      
//     
//     
//        
//       
//        
//        
//    }
//    catch(SQLException se){
//        JOptionPane.showMessageDialog(null, se+"from MakecurrentIdentityidFalse");
//    }
//    finally{
//        check.closeConnection();
//    }
//   
//    
//}
    
     public Object[][] getItemListByOrderId(int orderid){
        DBConnect gettg = new DBConnect();
        PreparedStatement stmtget ;
        ResultSet rs;
       // String search = menuid+"%";
        String strget = "SELECT order_item_list.menu_id,menu.menu_name,order_item_list.quantity,item_unit.unit_name,menu.retail_price,order_item_list.quantity*menu.retail_price as total_amount FROM order_item_list INNER JOIN menu ON order_item_list.menu_id = menu.menu_id INNER JOIN item_unit ON menu.unit_id = item_unit.unit_id  where order_id  IN (?)";
       // String[] columnName = new String[]{"Menu Id","Menu Name"," Retail Rate","Wholesale Rate","Base Unit",};
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        Object[][] finaldata = null;
        try{
            gettg.initConnection();
            stmtget = gettg.conn.prepareStatement(strget);
            stmtget.setInt(1, orderid);
            rs = stmtget.executeQuery();
            while(rs.next()){
                Object[] row = new Object[]{rs.getObject("menu_id"),rs.getObject("menu_name"),new BigDecimal(rs.getString("quantity")).setScale(3, RoundingMode.HALF_UP),rs.getObject("unit_name"),new BigDecimal(rs.getString("retail_price")).setScale(2, RoundingMode.HALF_UP),new BigDecimal(rs.getString("total_amount")).setScale(2, RoundingMode.HALF_UP)};
            data.add(row);
            }
            finaldata = data.toArray(new Object[data.size()][]);
            
        }
        
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"from getItemListByOrderId");
        }
        finally{
            gettg.closeConnection();
        }
        return finaldata;
    }
            
//      public DefaultTableModel getOrderInfo(){
//        DBConnect gettg = new DBConnect();
//        PreparedStatement stmtget ;
//        ResultSet rs;
//       // String search = src+"%";
//        String strget = "SELECT order_list.order_id,table_info.table_name,order_list.customer_id,order_list.total_amount FROM order_list LEFT JOIN  table_info ON order_list.table_id = table_info.table_id  WHERE order_list.paid = 0 ORDER BY order_list.date desc ";
//        String[] columnName = new String[]{"Order Id","Table Name","Total Amount "};
//        ArrayList<Object[]> data = new ArrayList<Object[]>();
//        
//        Object[][] finaldata = null;
//        try{
//            gettg.initConnection();
//            stmtget = gettg.conn.prepareStatement(strget);
//           // stmtget.setString(1,new String(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
//            
//          //  System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Timestamp(new Date().getTime())));
//            rs = stmtget.executeQuery();
//            while(rs.next()){
//               /* if(rs.getString("table_id").isEmpty()){
//                    data = rs.getString("Customer Name")
//                }*/
//                Object[] row = new Object[]{rs.getInt("order_id"),rs.getString("table_name"),rs.getBigDecimal("total_amount").setScale(2, RoundingMode.HALF_UP)};
//            data.add(row);
//            }
//            finaldata = data.toArray(new Object[data.size()][]);
//            
//        }
//        
//        catch(Exception e){
//            JOptionPane.showMessageDialog(null, e+"from getOrderInfo");
//        }
//        finally{
//            gettg.closeConnection();
//        }
//        return new DefaultTableModel(finaldata,columnName){
//                @Override     
//            public boolean isCellEditable(int row, int col){
//               return false;
//           }
//                
//           
//        };
//    }
      public DefaultTableModel getOrderInfo(int dep){
        DBConnect gettg = new DBConnect();
        PreparedStatement stmtget ;
        ResultSet rs;
        Double[] TaxList = new Double[2];
        
       // String search = src+"%";
        String strget = "SELECT order_list.order_id,table_info.table_name,order_list.customer_id,order_list.total_amount FROM order_list LEFT JOIN  table_info ON order_list.table_id = table_info.table_id  WHERE order_list.paid = 0 and department_id = ? ORDER BY order_list.date desc ";
        String[] columnName = new String[]{"Order Id","Table Name","Total Amount "};
        ArrayList<Object[]> data = new ArrayList<Object[]>();
      
        Object[][] finaldata = null;
        /*
        retreiving the value for the tax to include into item total
        */
        TaxList = getChargeInfo();
        Double VAT = new Double(0.0);
        Double SVC = new Double(0.0);
        try{
//            System.out.println(dep);
            gettg.initConnection();
            stmtget = gettg.conn.prepareStatement(strget);
           // stmtget.setString(1,new String(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
            
          //  System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Timestamp(new Date().getTime())));
           stmtget.setInt(1, dep);
            rs = stmtget.executeQuery();
            while(rs.next()){
               /* if(rs.getString("table_id").isEmpty()){
                    data = rs.getString("Customer Name")
                }*/
               Double TotalAmount = rs.getDouble("total_amount");
//                System.out.println(TotalAmount);
               
                SVC = TaxList[0]* TotalAmount/100;
                Double tt = TotalAmount +SVC;
                 VAT = (TaxList[1]* tt)/100;
                TotalAmount = tt + VAT; 
                
                Object[] row = new Object[]{rs.getInt("order_id"),rs.getString("table_name"),new BigDecimal(TotalAmount).setScale(2, RoundingMode.HALF_UP)};
            data.add(row);
            }
            finaldata = data.toArray(new Object[data.size()][]);
            
        }
        
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e+"from getOrderInfo"+getClass().getName());
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
       public DefaultTableModel getOrderInfoForAddOrder(ArrayList<String> st,int did){
        DBConnect gettg = new DBConnect();
        PreparedStatement stmtget ;
        ResultSet rs;
        String cond = " AND order_id !=";
        String tt = new String();
       // String search = src+"%";
        String strget = "SELECT order_list.order_id,table_info.table_name,order_list.customer_id,order_list.total_amount FROM order_list LEFT JOIN  table_info ON order_list.table_id = table_info.table_id  WHERE order_list.paid = 0 and order_list.department_id = ?";
       for(int i=0;i<st.size();i++){
          // cond += st.get(i)+" ";
           tt += cond + st.get(i)+" ";
       }
        String appendget = "ORDER BY order_list.date desc ";
       // System.out.println(strget+tt+appendget);
        String query = strget+tt+appendget;
       // System.out.println(query);
        String[] columnName = new String[]{"Order Id","Table Name","Total Amount "};
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        
        Object[][] finaldata = null;
        try{
            gettg.initConnection();
            stmtget = gettg.conn.prepareStatement(query);
            stmtget.setInt(1, did);
           // stmtget.setString(1,new String(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
            
          //  System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Timestamp(new Date().getTime())));
            rs = stmtget.executeQuery();
            while(rs.next()){
               /* if(rs.getString("table_id").isEmpty()){
                    data = rs.getString("Customer Name")
                }*/
                Object[] row = new Object[]{rs.getInt("order_id"),rs.getString("table_name"),rs.getBigDecimal("total_amount").setScale(2, RoundingMode.HALF_UP)};
            data.add(row);
            }
            finaldata = data.toArray(new Object[data.size()][]);
            
        }
        
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e+"from getOrderInfoForAddOrder");
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
       public DefaultTableModel getComplimentaryTable(DefaultTableModel dtm,String[][] menuid){
           DefaultTableModel model = dtm;
           
           for(int i=0;i<model.getRowCount();i++){
               for (String[] menuid1 : menuid) {
                   if (menuid1[0].equalsIgnoreCase(model.getValueAt(i, 0).toString()) && menuid1[1].equalsIgnoreCase(model.getValueAt(i, 5).toString())) {
                       model.removeRow(i);
                   }   
               }
           }
       return model;
       }
         public Double[] getChargeInfo(){
             String strQuery = new String();
             PreparedStatement stmtget ;
             ResultSet rsget;
        strQuery = "SELECT company_svc,company_vat FROM company_info";
        DBConnect getC = new DBConnect();
       Double cinfo[] = new Double[2];
        try{
            getC.initConnection();
            stmtget = getC.conn.prepareStatement(strQuery);
           rsget = stmtget.executeQuery();
       
          while(rsget.next()){
             
              cinfo[0] = rsget.getDouble("company_svc");
              cinfo[1] = rsget.getDouble("company_vat");
            
             
          }
          
        }
       
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"Sql error");
        }
        return cinfo;
    }
           public int getTableIdInfoTableName(String tablename){
             int tableid = 0;
             String strQuery = new String();
             PreparedStatement stmtget ;
             ResultSet rsget;
        strQuery = "SELECT table_id FROM table_info WHERE table_name = ? ";
        DBConnect getC = new DBConnect();
        
//       Double cinfo[] = new Double[2];
        try{
            getC.initConnection();
            stmtget = getC.conn.prepareStatement(strQuery);
            stmtget.setString(1, tablename);
            rsget = stmtget.executeQuery();
//          getC.conn.commit();
          while(rsget.next()){
             
             tableid  = rsget.getInt("table_id");
            
             
          }
          
        }
       
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"getTableIdInfoTableName");
        }
        finally{
            getC.closeConnection();
        }
        return tableid;
    }
            public int returnTableIdByOrderId(int  orderid){
     //Boolean ExistingStatus = null; 
        int id = 0;
        int oid = orderid;
    String strCheck = "SELECT table_id FROM order_list WHERE order_id = ?";
    DBConnect check = new DBConnect();
    PreparedStatement stmtcheck ;
    try{
        check.initConnection();
        stmtcheck = check.conn.prepareStatement(strCheck);
     stmtcheck.setInt(1, oid);
        ResultSet rs = stmtcheck.executeQuery();
        while(rs.next()){
        id = rs.getInt("table_id");
        }
       
        
        
    }
    catch(SQLException se){
        JOptionPane.showMessageDialog(null, se+"from returntableidBYorderid");
    }
    finally{
        check.closeConnection();
    }
    return id;
    
}
//            public Object[] returnSystemDateInfo(){
//    PreparedStatement stmtset;
//    ResultSet rs;
//    Date date = null;
//    boolean open = false;
//    boolean close = false;
//    int id = 0;
//    Object[] info = new Object[4];
//    String strget = "select system_date_id,date,open_status,close_status from system_date where  system_date_id = (select max(system_date_id) from system_date)";
////    String stropen = "UPDATE system_date SET open_status = 1 where system_date_id = ?";
//    try{
//        initConnection();
////        conn.setAutoCommit(false);
//        stmtset = conn.prepareStatement(strget);
//        rs = stmtset.executeQuery();
//        while(rs.next()){
//        
//           info[0] = rs.getObject("system_date_id");
//           info[1]=rs.getObject("date");
//           info[2] = rs.getObject("open_status");
//           info[3]  =rs.getObject("close_status");
////           System.out.println(id);
//           
//        }
//    
//      
//    }
//    catch(SQLException se){
//        JOptionPane.showMessageDialog(null, se+"from openSystemData"+getClass().getName());
//       
//    }
//    finally{
//        closeConnection();
//    }
//    return info;
//    
//}
            
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
     public  Object[][] getComplimentaryInfo(){
         PreparedStatement stmtget;
        ResultSet rsget ;
        DBConnect dbget  = new DBConnect();
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        
        String strget = "SELECT complimentary_id,complimentary_reason FROM complimentary_info ";
        try{
            dbget.initConnection();
            stmtget = dbget.conn.prepareStatement(strget);
            rsget = stmtget.executeQuery();
            while(rsget.next()){
                Object[] row = new Object[]{rsget.getInt("complimentary_id"),rsget.getString("complimentary_reason")};
                data.add(row);
            }
           
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"from getComplimentaryInfo");
        }
        finally{
            dbget.closeConnection();
        }
        return data.toArray(new Object[data.size()][]);
     }
    
}
