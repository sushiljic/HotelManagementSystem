/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.itemCategoryComponent;

import database.DBConnect;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.tree.*;
import reusableClass.DisplayMessages;
//import reusableClass.DBOperation;
/**
 *
 * @author MinamRosh
 */
public class ItemCategoryModel extends DBConnect{
    
    private int info;
    //private Connection conn;
    private String sql;
    private String[] arrCategory = null;
    private ResultSet resultSet; 
    //boolean dupStatus;
    
    public ItemCategoryModel (){
        //super(frm);
    }
    
    public void displayError(String msg){
        JOptionPane.showMessageDialog(null, msg,"Database Connection Error",JOptionPane.ERROR_MESSAGE);
    }
    
    
    public void displayInfo(String msg){
        JOptionPane.showMessageDialog(null, msg,"Information", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    public void displayCofirm(String msg){
        JOptionPane.showMessageDialog(null, msg, "Conformation", JOptionPane.YES_NO_OPTION);
    }
    
  
//add root category    
    //add recodrs to item_cateogy table
    public boolean addItemCategory(String strCategory, int parent){
        sql = "INSERT into item_category (category_name, parent) VALUES(?,?)";
        
        initConnection();
        try{
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1,strCategory);
            preStatement.setInt(2, parent);
            preStatement.executeUpdate();
            displayInfo("Operation Successful !");
        }
        catch(SQLException sqlEx){
            displayError(sqlEx.getMessage());
            return false;
        }
        finally{
            closeConnection();
        }
        return true;
    }
   
//add sub category 
    public boolean addSubCategory(String cName, String parent){
        sql = "insert into item_sub_category (sub_category_name, category_id)\n" +
                "values (?,(select category_id from item_category where category_name = ?))";
        
        initConnection();
        try{
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1,cName);
            preStatement.setString(2,parent);
            preStatement.executeUpdate();
            displayInfo("Operation Successful !");
        }
        catch(SQLException sqlEx){
            displayError(sqlEx.getMessage());
            return false;
        }
        finally{
            closeConnection();
        }
        return true;
    }
    
    //update category
    public void updateItemCategory(String newName, int intId){
        sql = "UPDATE item_category SET category_name = ? WHERE category_id = ?";
        initConnection();
        try{
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, newName);
            preStatement.setInt(2, intId);
            //preStatement.setInt(3, intId);
            preStatement.executeUpdate();
            //conn.commit();
        }
        catch(SQLException sqlEx){
            displayError("updateItemCategory()."+sqlEx.getMessage());
            
        }
        finally{
            closeConnection();
        }
    }
    
    //check for root's subnode while updating it
    //look for sub category if has any sub node
    //if contains subnode then don't allow it to be subnode of another;
    public boolean checkNodeSubNode(String categoryName){
       boolean status = true;
        sql = "SELECT count(sub_category_id) as s FROM item_sub_category WHERE category_id = (SELECT category_id FROM item_category WHERE category_name = ?)";
        initConnection();
        try{
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, categoryName);
           
            ResultSet rows = preStmt.executeQuery();
            //int nRows = getNumberOfRows(rows);
            int num = 0;
            while(rows.next())
               num = rows.getInt("s");
            if(num >= 1)
                status = false;
            else
                status = true;
            //System.out.println(nRows);
            //ResultSet status = preStmt.executeQuery().;
        }
        catch(SQLException sqlEx){
             displayError("checkNodeSubNode"+sqlEx.getMessage());
        }
        finally{
            closeConnection();
        }
        return status;
    }
    //update and adjust subnode
    public void updateItemCategorySubNode(String newName, String currentParent, int id){
        //String parent = parentCategory; 
        sql = "UPDATE item_sub_category SET sub_category_name = ?, category_id = (SELECT category_id FROM item_category WHERE category_name = ? ) WHERE sub_category_id = ?";
        initConnection();
        try{
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1,newName);
            preStatement.setString(2,currentParent);
            preStatement.setInt(3,id);
            preStatement.executeUpdate();
        }
        catch(SQLException sqlEx){
            displayError(sqlEx.getMessage());
        }
        finally{
            closeConnection();
        }
    }
    //delete category
    public boolean deleteItemCategory(int id){
       
        sql = "DELETE FROM item_category WHERE category_id = ?";
        
        initConnection();
        
        try{
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setInt(1, id);
            preStatement.executeUpdate();
            //connet.commit();
            //displayInfo("Category Deleted Successfully !");
        }
        catch(SQLException sqlEx){
            displayError(sqlEx.getMessage());
            return false;
        }
        finally{
            closeConnection();
        }
        return true;
    }
    
    //check duplication while adding
    //look for both item_sub_category and item_category table
    public boolean checkDupItemCategory(String category){
        boolean dupStatus = true;
        sql = "select count(b.category_name) as row from (select category_name from item_category \n" +
"union\n" +
"select sub_category_name from item_sub_category) b where b.category_name = ?";
        //boolean status = true;
        initConnection();
        
        try{
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, category);
            ResultSet result = preStatement.executeQuery();
            
            while (result.next()){
                if(result.getInt("row") > 0)
                    dupStatus = true;
                else
                    dupStatus = false;
            }
        }
        catch(SQLException sqlEx){
            displayError(sqlEx.getMessage());
        }
        return dupStatus;
    }
    
    /*
    *check duplicate item on category table
    *@param category string holds category name
    *@return boolean
    */
    public boolean checkDupItemOnParent(String category){
        boolean dupStatus = true;
        sql = "select count(category_name) as row from item_category where category_name = ?";
        //boolean status = true;
        initConnection();
        
        try{
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, category);
            ResultSet result = preStatement.executeQuery();
            
            while (result.next()){
                if(result.getInt("row") > 0)
                    dupStatus = true;
                else
                    dupStatus = false;
            }
        }
        catch(SQLException sqlEx){
            displayError(sqlEx.getMessage());
        }
        return dupStatus;
    }
    
    /*
    *check deuplicate category on child
    *@param category string, holds category name
    *@param return boolean
    */
    public boolean checkDupItemOnChild(String category){
        boolean dupStatus = true;
        sql = "select count(sub_category_name) as row item_sub_category where sub_category_name = ?";
        //boolean status = true;
        initConnection();
        
        try{
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, category);
            ResultSet result = preStatement.executeQuery();
            
            while (result.next()){
                if(result.getInt("row") > 0)
                    dupStatus = true;
                else
                    dupStatus = false;
            }
        }
        catch(SQLException sqlEx){
            displayError(sqlEx.getMessage());
        }
        return dupStatus;
    }
    //retrive category for combobox;
   public String[] getItemCategoryCombo(){
         sql = "SELECT category_name FROM item_category WHERE parent = 1";
        //sql = "SELeCT category_name FROM item_category";
        initConnection();
        try{
           Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
           resultSet = stmt.executeQuery(sql);
           
           //get number of rows in table
           int size = getNumberOfRows(resultSet);
           /*
           try{
               resultSet.last();
               arrCategory = new String[resultSet.getRow()];
               resultSet.beforeFirst();
           }
           catch(SQLException sqlEx){
               displayError(sqlEx.getMessage());
           }
           */
           arrCategory = new String[size];
           for( int i = 0; resultSet.next(); i++){
               arrCategory[i] = resultSet.getString(1);
           }
           return arrCategory;
           //JOptionPane.showMessageDialog(null,arrCategory);
        }
        catch(SQLException sqlEx){
            displayError(sqlEx.getMessage());
        }
        finally{
            closeConnection();
        
        }
        
        return null;
    }
    
    //retrive tree model for tree
    public DefaultTreeModel getItemCategoryForTree(){
        
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Category List");
        //DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
        sql = "SELECT category_id, category_name FROM item_category WHERE parent= 1";
        initConnection();
        try{
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql); //retuns true;
            
            //System.out.println(resultSet);
            
            while(resultSet.next()){
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(resultSet.getString(2));
                sql = "SELECT sub_category_name, category_id FROM item_sub_category";
                stmt = conn.createStatement();
                ResultSet resultSet2 = stmt.executeQuery(sql);
                while(resultSet2.next()){
                   /* if(resultSet2.getString(2).equals(resultSet.getString(1))){
                        DefaultMutableTreeNode subNode = new DefaultMutableTreeNode(resultSet2.getString(1));
                        node.add(subNode);
                    }
                    */
                    if(resultSet2.getInt(2) == resultSet.getInt(1)) {
                        DefaultMutableTreeNode subNode = new DefaultMutableTreeNode(resultSet2.getString("sub_category_name"));
                        node.add(subNode);
                    }
                    else {
                    }
                }
                rootNode.add(node);
            }
            
        }
        catch(SQLException sqlEx){
            displayError(sqlEx.getMessage());
        }
        finally{
            closeConnection();
        }
        return treeModel;
    }
    
    //for update and delete, show selected item in the frame;
    public String[] getItemSelectedCategory(String category){
        sql = "SELECT category_id, category_name, parent FROM item_category WHERE category_name = ?";
        initConnection();
        String[] list = new String[3];
        try{
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, category);
            ResultSet resultSet = preStatement.executeQuery();
            while(resultSet.next()){
                list[0] = Integer.toString(resultSet.getInt("category_id"));
                list[1] = resultSet.getString("category_name");
                list[2] = "root";//resultSet.getString("parent");
            }
        }
        catch(SQLException sqlEx){
            //displayError(sqlEx.getMessage());
            System.out.println(sqlEx);
        }
        finally{
            closeConnection();
        }
        //JOptionPane.showMessageDialog(null,list);
        return list;
    }
    
    public String[] getItemSelectedSubCategory(String category){
        sql = "SELECT sub_category_id, sub_category_name, category_name as parent FROM item_sub_category child, item_category parent  WHERE sub_category_name = ? and parent.category_id = child.category_id";
        initConnection();
        String[] list = new String[3];
        try{
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, category);
            ResultSet resultSet = preStatement.executeQuery();
            while(resultSet.next()){
                list[0] = Integer.toString(resultSet.getInt("sub_category_id"));
                list[1] = resultSet.getString("sub_category_name");
                list[2] = resultSet.getString("parent");
            }
        }
        catch(SQLException sqlEx){
            //displayError(sqlEx.getMessage());
            DisplayMessages.displayError(null, sqlEx.toString(), "Database Error");
        }
        finally{
            closeConnection();
        }
        //JOptionPane.showMessageDialog(null,list);
        return list;
    }
    
    public boolean changeParentToChild(String name, String parent, int id){
         sql = "insert into item_sub_category (sub_category_name, category_id)" +
                "values (?,(select category_id from item_category where category_name = ?))";
        
        initConnection();
        try{
            PreparedStatement preStatement = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            preStatement.setString(1,name);
            preStatement.setString(2,parent);
            preStatement.executeUpdate();
            
            sql = "DELETE FROM item_category WHERE category_id = ?";
            try{
                PreparedStatement preStatement2 = conn.prepareStatement(sql);
                preStatement2.setInt(1, id);
                preStatement2.executeUpdate();
                //connet.commit();
                //displayInfo("Category Deleted Successfully !");
            }
            catch(SQLException sqlEx){
                displayError("changeParentToChild"+sqlEx.getMessage());
                return false;
            }
            conn.commit();
        }
        catch(SQLException sqlEx){
            displayError("changeParentToChild.outer." +sqlEx.getMessage());
            
            return false;
        }
        finally{
            try{
                conn.setAutoCommit(true);
            }
            catch(SQLException ee){
            }
            closeConnection();
            
        }
        return true;
    }
    
    public boolean changeChildToParent(String name, String parent, int id){
         sql = "insert into item_category (category_name,parent)values (?, ?)";
        
        initConnection();
        try{
            PreparedStatement preStatement = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            preStatement.setString(1,name);
            preStatement.setObject(2,1);
            preStatement.executeUpdate();
            
            sql = "DELETE FROM item_sub_category WHERE sub_category_id = ?";
            try{
                PreparedStatement preStatement2 = conn.prepareStatement(sql);
                preStatement2.setInt(1, id);
                preStatement2.executeUpdate();
                //connet.commit();
                //displayInfo("Category Deleted Successfully !");
            }
            catch(SQLException sqlEx){
                displayError("changeChildToParent.innerSql"+sqlEx.getMessage());
                return false;
            }
            conn.commit();
        }
        catch(SQLException sqlEx){
            displayError("changeeChildToParent" + sqlEx.getMessage());
            return false;
        }
        finally{
            try{
                conn.setAutoCommit(true);
            }
            catch(SQLException ee){
            }
            closeConnection();
            
        }
        return true;
    }
    
    public boolean deleteSubCategory(int id){
        sql = "DELETE FROM item_sub_category WHERE sub_category_id = ?";
        initConnection();
            try{
                PreparedStatement preStatement2 = conn.prepareStatement(sql);
                preStatement2.setInt(1, id);
                preStatement2.executeUpdate();
                conn.commit();
                //connet.commit();
                //displayInfo("Category Deleted Successfully !");
            }
            catch(SQLException sqlEx){
                displayError(sqlEx.getMessage());
                return false;
            }
            finally{
                closeConnection();
            }
            return true;
    }
    
    //check for item entry ;
    public boolean isContainsItem(int id){
        int num = 0;
        sql= "SELECT count(item_id) as no from centerstore_stock where category_id = ?";
        initConnection();
        try{
            PreparedStatement preStatement2 = conn.prepareStatement(sql);
                preStatement2.setInt(1, id);
                resultSet = preStatement2.executeQuery();
                
                //int num = 0;
                while(resultSet.next()){
                    //num = resultSet.getInt("no");
                    num = resultSet.getInt(1);
                    //System.out.println(num);
                }
                
                //if(num < 1)
                //connet.commit();
                //displayInfo("Category Deleted Successfully !");
            }
            catch(SQLException sqlEx){
                displayError(sqlEx.getMessage());
                return true;
            }
            finally{
                closeConnection();
            }
        if(num >= 1){
            return true;
        }
        else{
            return false;
        }
        
    }
    
    //get number of rows

    /**
     *
     * @param rs
     * @return
     */
   
    public int getNumberOfRows(ResultSet rs){
       int n = 0;
        try{ rs.last();
          n = rs.getRow();
        rs.beforeFirst();
        }
        catch(SQLException sqlEx){
            displayError(sqlEx.getMessage());
        }
        return n;
    }
}
