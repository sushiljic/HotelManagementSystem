/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package companysetup;

import database.DBConnect;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import license.Customer;
import reusableClass.CyptoAES;


/**
 *
 * @author SUSHIL
 */
public class CompanySetupModel {
    
    private String strQuery = null;
    private PreparedStatement stmtEntry;
    private PreparedStatement stmtCheck;
    private PreparedStatement stmtEdit;
    private PreparedStatement stmtget;
    private ResultSet rsCompany;
    private ResultSet rsCheck;
    private ResultSet rsget;
    //private ResultSet 
    public boolean registerCompany(String[] CompanyInfo,File image) throws NoSuchAlgorithmException,FileNotFoundException,SQLException {
        boolean status= false;
        strQuery = "INSERT INTO company_info (company_name,company_address,bill_greet,phone,fax,website,email,pan_no,company_logo,company_slogan,company_logo_image,serial_code) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
//         if(image != null){    
//          strQuery += ",  company_logo = ?,company_logo_image = ? ";
//                    }
        DBConnect entry = new DBConnect();
         FileInputStream fis = null;
         
        
           
//        InputStream fis = getClass().getResource("/images/imageorder.png").openStream();    
        entry.initConnection();
         entry.conn.setAutoCommit(false);
        stmtEntry = entry.conn.prepareStatement(strQuery);
       
        stmtEntry.setString(1,CompanyInfo[0]);
        stmtEntry.setString(2,CompanyInfo[1]);
        stmtEntry.setString(3, CompanyInfo[2]);
        stmtEntry.setString(4,CompanyInfo[3]);
        stmtEntry.setString(5,CompanyInfo[4]);
        stmtEntry.setString(6, CompanyInfo[5]);
        stmtEntry.setString(7,CompanyInfo[6]);
        stmtEntry.setLong(8,Long.parseLong(CompanyInfo[7]));
        stmtEntry.setString(9, CompanyInfo[8]);
//        stmtEntry.setShort(10,Short.parseShort("1"));
        stmtEntry.setString(10, CompanyInfo[9]);
        if(image != null){
        fis= new FileInputStream(image); 
//        File fs= new File(getClass().getResource("\images\imageorder.png"));
        stmtEntry.setBinaryStream(11, fis,(int)image.length());
//        stmtEntry.setBlob(12, fis, (int)image.length());
        }
        else{
            stmtEntry.setBinaryStream(11, null);
        }
        //enter serialcode
        //calculate serial code and append the 0 at last before encypting
        Customer customer = new Customer(CompanyInfo[0]);
        String customerSerailNo = customer.GenerateCode();
        String encytSerialNo= CyptoAES.encrypt(customerSerailNo+"0");
        stmtEntry.setString(12,encytSerialNo);
        stmtEntry.executeUpdate();
        entry.conn.commit();
        status = true;
        entry.closeConnection();
//        JOptionPane.showMessageDialog(null, "Company Register Successfully");
        return status;
     
        
        
    }
    /*
     * there is code for some update
     */
    public void updateCompany(String[] CompanyInfo,File image){
//        strQuery = "UPDATE company_info SET company_name= ?,company_address = ?,bill_greet =?,phone = ?,fax = ?,website = ?,email = ?,pan_no= ?, company_slogan = ? ";
         strQuery = "UPDATE company_info SET company_name= ?,company_address = ?,bill_greet =?,phone = ?,fax = ?,website = ?,email = ?,pan_no= ?, company_slogan = ?  ";
//        if(!CompanyInfo[8].isEmpty()){
         if(image != null){    
          strQuery += ",  company_logo = ?,company_logo_image = ? ";
                    }
            DBConnect entry = new DBConnect();
            FileInputStream fis = null;
            
        try{
        entry.initConnection();
        
         entry.conn.setAutoCommit(false);
        stmtEdit = entry.conn.prepareStatement(strQuery);
       
        stmtEdit.setString(1,CompanyInfo[0]);
        stmtEdit.setString(2,CompanyInfo[1]);
       stmtEdit.setString(3, CompanyInfo[2]);
        stmtEdit.setString(4,CompanyInfo[3]);
        stmtEdit.setString(5,CompanyInfo[4]);
        stmtEdit.setString(6, CompanyInfo[5]);
        stmtEdit.setString(7,CompanyInfo[6]);
        //stmtEdit.setString(8,CompanyInfo[7]);
       stmtEdit.setLong(8,Long.parseLong(CompanyInfo[7]));
       stmtEdit.setString(9, CompanyInfo[9]);
//         if(!CompanyInfo[8].isEmpty()){
      
       if(image != null){
        stmtEdit.setString(10, CompanyInfo[8]);    
       fis = new FileInputStream(image);
       stmtEdit.setBinaryStream(11, fis, (int)image.length());
       }
      
//         }
       // stmtEntry.setShort(9,Short.parseShort("1"));
        stmtEdit.executeUpdate();
        entry.conn.commit();
        JOptionPane.showMessageDialog(null, "Company Updated Successfully.");
        
        }
        catch(SQLException|FileNotFoundException se){
            JOptionPane.showMessageDialog(null, se+"Update error!!");
        } 
        finally{
           try {
               if(fis != null){
                fis.close();
               }
                entry.closeConnection();
            } catch (IOException ex) {
               JOptionPane.showMessageDialog(null, ex+"image error!!");
            }  
        }
        
        
    }
    public void updateChargeCompany(Double SVC,Double VAT){
        strQuery = "UPDATE company_info SET company_svc= ?,company_vat = ? ";
        DBConnect entry = new DBConnect();
        try{
        entry.initConnection();
        stmtEdit = entry.conn.prepareStatement(strQuery);
        entry.conn.setAutoCommit(false);
        stmtEdit.setDouble(1,SVC);
        stmtEdit.setDouble(2,VAT);
     
        stmtEdit.executeUpdate();
        entry.conn.commit();
        JOptionPane.showMessageDialog(null, "Company  Charge Setup Updated Successfully");
        
        }
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"Update error!!");
        } 
        
        
    }
    public Object[] getCompanyInfo(){
        strQuery = "SELECT * FROM company_info";
        DBConnect getC = new DBConnect();
        Object cinfo[] = new Object[11];
        try{
            getC.initConnection();
            stmtget = getC.conn.prepareStatement(strQuery);
           rsget = stmtget.executeQuery();
//          getC.conn.commit();
          while(rsget.next()){
              int i=0;
              cinfo[0] = rsget.getString("company_name");
              cinfo[1] = rsget.getString("company_address");
             cinfo[2] = rsget.getString("bill_greet");
              cinfo[3] = rsget.getString("phone");
              cinfo[4] = rsget.getString("fax");
              cinfo[5] = rsget.getString("website");
              cinfo[6] = rsget.getString("email");
              cinfo[7] = rsget.getString("pan_no");
              cinfo[8] = rsget.getString("company_logo");
              cinfo[9] = rsget.getString("company_slogan");
//              Blob bb =  rsget.getBlob("company_logo_image");
//              byte[] ip = bb.getBytes(1, (int) bb.length());
//                cinfo[10] =ip;
              cinfo[10] = rsget.getObject("company_logo_image");
          }
          
        }
       
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"Sql error");
        }
        return cinfo;
    }
     public Double[] getChargeInfo(){
        strQuery = "SELECT company_vat,company_svc FROM company_info";
        DBConnect getC = new DBConnect();
       Double cinfo[] = new Double[2];
        try{
            getC.initConnection();
            stmtget = getC.conn.prepareStatement(strQuery);
           rsget = stmtget.executeQuery();
//          getC.conn.commit();
          while(rsget.next()){
              int i=0;
              cinfo[0] = rsget.getDouble("company_svc");
              cinfo[1] = rsget.getDouble("company_vat");
            
             
          }
          
        }
       
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"Sql error");
        }
        return cinfo;
    }
    public short checkRegister(){
        short register = 0;
        strQuery = "SELECT register FROM company_info ";
       DBConnect check = new DBConnect();
       try{
           check.initConnection();
           stmtCheck   = check.conn.prepareStatement(strQuery);
           rsCheck = stmtCheck.executeQuery();
//           check.conn.commit();
           while(rsCheck.next()){
               register = rsCheck.getShort("register");
           }
           
           
       }
       
       catch(SQLException se){
           JOptionPane.showMessageDialog(null, se+"Sql server format error");
       }
       finally{
           check.closeConnection();
       }
       return register;
        
        
    }
     
     public void SaveImage(File file,BufferedImage buffImage){
        /*
               * path of the image to save
               */
//        URL relative = getClass().getClassLoader().getResource("/src/images/");
//        System.out.println(relative);
//      File path = new File(relative+ file.getName());  
//        File path = new File("/images/"+ file.getName());  
//        System.out.println(path);
//        System.out.println(path);
//File path = new File("images/"+file.getName());
Path path = Paths.get(("resources/images/"+file.getName()));
//System.out.println(path);
//System.out.println(getClass().getClassLoader().getResource("")+"images/"+file.getName());
      
//File path = new File(url.toURI());      
if(Files.exists(path)){
        //   int result = JOptionPane.showConfirmDialog(this, "DO you want to overswrite the existing file","File already exists",JOptionPane.YES_NO_CANCEL_OPTION);
    //int result = 0;
              int   result = JOptionPane.showConfirmDialog(null,"DO you want to overswrite the existing file","File already exits", JOptionPane.YES_NO_CANCEL_OPTION);
    switch(result){
                    case JOptionPane.YES_OPTION:
                       break;
                        
                    case JOptionPane.NO_OPTION:
                        
                        return;
                    case JOptionPane.CANCEL_OPTION:
                    case JOptionPane.CLOSED_OPTION:
                       
                        return;
                      }
      } 
try{
if(file.getName().endsWith(".png")){
              // System.out.println("png type"); 
   
              ImageIO.write(buffImage,"png",path.toFile());
}
else if(file.getName().endsWith(".jpg")){
   //   System.out.println("jpg type");
    ImageIO.write(buffImage, "jpg", path.toFile());
}
else if(file.getName().endsWith(".gif")){
   //   System.out.println("gif type");
    ImageIO.write(buffImage, "gif", path.toFile());
}
}
catch(IOException ioe){
    JOptionPane.showMessageDialog(null, ioe+"image not saved");
}
        
    }
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
    
}
