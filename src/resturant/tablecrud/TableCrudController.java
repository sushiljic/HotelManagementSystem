/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.tablecrud;

import hotelmanagementsystem.MainFrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import reusableClass.DisplayMessages;

/**
 *
 * @author SUSHIL
 */
public class TableCrudController {
    TableCrudView tableview = new TableCrudView(new JFrame(),true);
    TableCrudModel tablemodel = new TableCrudModel();
    MainFrameView mainview ;
//    TableStatusView vw = new TableStatusView();
 //  static TableStatusView vw =  new  TableStatusView();
    private String InitialTableGroupName;
    private String InitialTableName;
      
    public TableCrudController(TableCrudModel tmodel,TableCrudView tview,MainFrameView mview){
        tablemodel = tmodel;
        tableview = tview;
        mainview = mview;
        /*
         * setting jtable
         */
        tableview.refreshGroupTableJTable(tablemodel.getTableGroupInfo());
        tableview.refreshTableJTable(tablemodel.getTableInfo());
        /*
         * this listen for the change of the tap in tabbed pane
         */
        tableview.addTabbedPaneChangeListener(new TabbedChangeListener());
        /*
         * adding the button listener for table group
         */
        tableview.addTableGroupAddListener(new TableGroupCrudListener());
        tableview.addTableGroupEditListener(new TableGroupCrudListener());
        tableview.addTableGroupCancelListener(new TableGroupCrudListener());
        tableview.addTableGroupDeleteListener(new TableGroupCrudListener());
        /*
         * for checkbox in  table group table
         */
        tableview.addCheckBoxRateListener(new TableGroupCheckBoxListener());
      //  tableview.addCheckBoxRateListener(new listselction());
        tableview.addTableGroupListSelectionListener(new returnTableGroupRowSelectedListener(tableview));
        
        /*
         * now for table listener
         */
        tableview.addTableAddListener(new TableCrudListener());
        tableview.addTableEditListener(new TableCrudListener());
        tableview.addTableDeleteListener(new TableCrudListener());
        tableview.addTableCancelListener(new TableCrudListener());
        tableview.addTableSearchListener(new TableCrudListener());
        // for text search
        tableview.addTextTableSearchListener(new TableSearchListener());
        
        tableview.addComboTableGroupListener(new ComboTableGroupListener());
        tableview.addTableListSelectionListener(new returnTableRowSelectedListener(tableview));
        tableview.addTableDocumentSearchListener(new TableDocumentSearchListener());
        
        /*
         * testing
         */
        
       //    vw.setVisible(true);
//           TableCrudModel model = new TableCrudModel();
// Object[][] data = tablemodel.getTableInfoObject();
//         vw.setPanel(/*vw,*/vw.drawPanel(data));
    //    
    }
    
    public class TabbedChangeListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            JTabbedPane pane  = (JTabbedPane) e.getSource();
           //if table crud is selected
            if(pane.getSelectedIndex() == 1){
                //JOptionPane.showMessageDialog(pane, "tale is selected");
                //perform which action to be loaded
                tableview.setComboTableGroup(tablemodel.returnTableGroupName(tablemodel.getTableGroupInfoObject()));
                tableview.AddSelectInCombo(tableview.returnComboTableGroup());
            }
        }
        
    }
    public class TableSearchListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                tableview.getBtnTableSearch().doClick();
            }
            catch(Exception se){
                JOptionPane.showMessageDialog(tableview, se+"From tablesearch Listener");
            }
        }
        
    }
    public class TableGroupCrudListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("TableGroupAdd")){
               
                if(tableview.getTableGroupName().isEmpty()){
                    JOptionPane.showMessageDialog(tableview, "Blank Field in Table Group Name");
                    return;
                }
                if(tablemodel.checkExistingName(tableview.getTableGroupName())){
                    JOptionPane.showMessageDialog(tableview, "Duplicate Name.Try With Another Name");
                    return;
                }
//                if(!tableview.RateStatus()){
//                    if(tableview.getTableGroupRate().isEmpty()){
//                    JOptionPane.showMessageDialog(tableview, "Blank Field in Rate");
//                    return;
//                    }
//                    try{
//                        Float.parseFloat(tableview.getTableGroupRate());
//                    }
//                    catch(NumberFormatException ee){
//                        JOptionPane.showMessageDialog(tableview, "Please Enter Number only in rate");
//                        return;
//                    }
//                    
//                }
               
               /* try{
                    String st = new String();
                   // st = null;
                    if(st.isEmpty()){
                        st = "";
                    }
                System.out.println(new BigDecimal(st)+"wala");
                }
                catch(Exception se){
                    System.out.println(se+"from big");
                    return;
                }
                * */
                tablemodel.TableGroupAdd(tableview.getTableGroup());
                tableview.refreshGroupTableJTable(tablemodel.getTableGroupInfo());
                tableview.clearGroupTable();
                tableview.disableTableGroupEdit();
            }
            else if (e.getActionCommand().equalsIgnoreCase("TableGroupEdit")){
                if(tableview.getTableGroupId() == 0){
                    JOptionPane.showMessageDialog(tableview, "Please Select the Table Group from table");
                    return;
                }
                if(tableview.getTableGroupName().isEmpty()){
                    JOptionPane.showMessageDialog(tableview, "BLank Field Detected in Name.");
                    return;
                }
                if(!InitialTableGroupName.equalsIgnoreCase(tableview.getTableGroupName())){
                    if(tablemodel.checkExistingName(tableview.getTableGroupName())){
                        JOptionPane.showMessageDialog(tableview, "Duplicate Name.Try With Another Name");
                        return;
                    }
                }
//                if(!tableview.RateStatus()){
//                    if(tableview.getTableGroupRate().isEmpty()){
//                    JOptionPane.showMessageDialog(tableview, "Blank Field in Rate");
//                    return;
//                    }
//                     try{
//                        Float.parseFloat(tableview.getTableGroupRate());
//                    }
//                    catch(NumberFormatException ee){
//                        JOptionPane.showMessageDialog(tableview, "Please Enter Number only in rate");
//                        return;
//                    }
//                }
                
                int choice = JOptionPane.showConfirmDialog(tableview, "Do You Want to Edit?","Edit Window",JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION){
                tablemodel.TableGroupEdit(tableview.getTableGroup());
                tableview.refreshGroupTableJTable(tablemodel.getTableGroupInfo());
                tableview.clearGroupTable();
                tableview.disableTableEdit();
                //refresh the indivuduall table
                tableview.refreshTableJTable(tablemodel.getTableInfo());
                }
                
            }
            else if (e.getActionCommand().equalsIgnoreCase("TableGroupDelete")){
                if(tableview.getTableGroupId() == 0){
                    JOptionPane.showMessageDialog(tableview, "Please select the TableGroup to delete");
                    return;
                }
                if(DisplayMessages.displayInputYesNo(tableview, "All Tables Under this Category will also be  Deleted.\nDo You Want to Delete?","Delete Window"))
                {
                tablemodel.TableGroupDelete(tableview.getTableGroup());
                tableview.refreshGroupTableJTable(tablemodel.getTableGroupInfo());
                tableview.clearGroupTable();
                tableview.disableTableGroupDelete();
                //refresh the table from the individuall
                tableview.refreshTableJTable(tablemodel.getTableInfo());
                }
            }
            else if (e.getActionCommand().equalsIgnoreCase("TableGroupCancel")){
                tableview.clearGroupTable();
                tableview.enableTableGroupAdd();
                tableview.disableTableGroupEdit();
                tableview.disableTableGroupDelete();
            }
        }
        catch(Exception te){
            JOptionPane.showMessageDialog(tableview, te+"from tablegroupcrudListener");
        }
        }
    
}
  /*  public class listselction implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        JCheckBox jc = (JCheckBox)e.getSource();
        if(jc.isSelected()){
            tableview.setTableGroupRateEditableTrue();
            
        }
        }
        
    }
    * */////
    public class TableCrudListener implements ActionListener{
       
       
        @Override
        public void actionPerformed(ActionEvent e) {
          
            //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       try{
           if(e.getActionCommand().equalsIgnoreCase("TableAdd")){
               if(tableview.getTableName().isEmpty()){
                   JOptionPane.showMessageDialog(tableview, "Blank Field Detected in Table Name.");
                   return;
               }
               if(tablemodel.checkExistingTableName(tableview.getTableName())){
                   JOptionPane.showMessageDialog(tableview, "Duplicate Name found.Please Try Another Name");
                   return;
               }
               if(tableview.getTableGroupId() == 0){
                   JOptionPane.showMessageDialog(tableview, "Please Select the Table Group Name");
                   return;
               }
               tablemodel.TableAdd(tableview.getTable());
           //  System.out.println(tableview.getTableGroupId());  
               tableview.refreshTableJTable(tablemodel.getTableInfo());
               tableview.clearTable();
               tableview.enableTableAdd();
               tableview.checkedTableAvailability();
               /*
               for refrehing the the vie of table
               */
//                Object[][] data = tablemodel.getTableInfoObject();
//           vw.setPanel(/*vw,*/vw.drawPanel(data));
                 /*
                    * here is manipulation for refreshing the data in order pay if it is open
                    */
               JInternalFrame[] iframes = mainview.desktop.getAllFrames();
               for (JInternalFrame iframe : iframes) {
                   if (iframe.getTitle().equalsIgnoreCase("Table Status")) {
                       ExecuteTableStatusView tablestatus = new ExecuteTableStatusView(mainview);
                       iframe.dispose();
                       mainview.desktop.add(tablestatus.view);
                   }
               }
//              
               
           }
           else if(e.getActionCommand().equalsIgnoreCase("TableEdit")){
              if(tableview.getTableId() == 0){
                  JOptionPane.showMessageDialog(tableview, "Please Select the Table From Table Info");
                  return;
              }
               if(tableview.getTableName().isEmpty()){
                   JOptionPane.showMessageDialog(tableview, "Blank Field Detected in Table Name.");
                   return;
               }
               if(!InitialTableName.equalsIgnoreCase(tableview.getTableName())){
                    if(tablemodel.checkExistingTableName(tableview.getTableName())){
                   JOptionPane.showMessageDialog(tableview, "Duplicate Name found.Please Try Another Name");
                   return;
               }
                   
               }
                if(tableview.getTableGroupId() == 0){
                   JOptionPane.showMessageDialog(tableview, "Please Select the Table Group Name");
                   return;
               }
                int choice = JOptionPane.showConfirmDialog(tableview, "Do You Want to Edit?","Edit Window",JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION){
                tablemodel.TableEdit(tableview.getTable());
                tableview.refreshTableJTable(tablemodel.getTableInfo());
                tableview.clearTable();
                tableview.disableTableEdit();
                 tableview.disableTableDelete();
                tableview.checkedTableAvailability();
                  /*
               for refrehing the the vie of table
               */
//                Object[][] data = tablemodel.getTableInfoObject();
//           vw.setPanel(/*vw,*/vw.drawPanel(data));
                 /*
                    * here is manipulation for refreshing the data in order pay if it is open
                    */
               JInternalFrame[] iframes = mainview.desktop.getAllFrames();
               for (JInternalFrame iframe : iframes) {
                   if (iframe.getTitle().equalsIgnoreCase("Table Status")) {
                       ExecuteTableStatusView tablestatus = new ExecuteTableStatusView(mainview);
                       iframe.dispose();
                       mainview.desktop.add(tablestatus.view);
                   }
               }
                }
           }
           else if (e.getActionCommand().equalsIgnoreCase("TableDelete")){
               if(tableview.getTableId() == 0){
                   JOptionPane.showMessageDialog(tableview, "Please Select  the Table from tableInfo");
                   return;
               }
               int choice = JOptionPane.showConfirmDialog(tableview, "Do You Want to Delete?","Delete Window",JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION){
               tablemodel.TableDelete(tableview.getTable());
               tableview.refreshTableJTable(tablemodel.getTableInfo());
                tableview.clearTable();
                tableview.disableTableDelete();
                tableview.disableTableEdit();
                tableview.checkedTableAvailability();
                  /*
               for refrehing the the vie of table
               */
//                Object[][] data = tablemodel.getTableInfoObject();
//           vw.setPanel(/*vw,*/vw.drawPanel(data));
                 /*
                    * here is manipulation for refreshing the data in order pay if it is open
                    */
               JInternalFrame[] iframes = mainview.desktop.getAllFrames();
               for (JInternalFrame iframe : iframes) {
                   if (iframe.getTitle().equalsIgnoreCase("Table Status")) {
                       ExecuteTableStatusView tablestatus = new ExecuteTableStatusView(mainview);
                       iframe.dispose();
                       mainview.desktop.add(tablestatus.view);
                   }
               }
                }
              
           }
           else if(e.getActionCommand().equalsIgnoreCase("TableCancel")){
               tableview.clearTable();
               tableview.disableTableEdit();
                tableview.disableTableDelete();
                tableview.enableTableAdd();
                tableview.checkedTableAvailability();
           
       }
           else if (e.getActionCommand().equalsIgnoreCase("TableSearch")){
                 String strsearch;
            String[] SearchBox = new String[7];
            boolean flag = false;
            int col =1;
            try{
                strsearch = tableview.getTableSearch().trim();
                for(int row=0;row<tableview.tblTable.getRowCount();row++){
                    if(tableview.tblTable.getValueAt(row, col).toString().toLowerCase().startsWith(strsearch.toLowerCase())){
                        for(int j = 0;j<tableview.tblTable.getColumnCount();j++){
                            SearchBox[j] = tableview.tblTable.getValueAt(row, j).toString();
                        }
                       tableview.tblTable.scrollRectToVisible(tableview.tblTable.getCellRect(row, 0, true));
                       tableview.tblTable.setRowSelectionInterval(row, row);
//                       tableview.setTable(SearchBox);
//                       tableview.enableTableEdit();
//                       tableview.enableTableDelete();
//                       tableview.setTableSearch("");
//                       flag = true;
                    }
                }
//                if(flag == false){
//                    JOptionPane.showMessageDialog(tableview, "Table Not Found.");
//                }
                
            }
            catch(Exception se){
                JOptionPane.showMessageDialog(tableview, se+"From tablesearch Listener");
            }
           }
           
       }
       catch(Exception se){
           JOptionPane.showMessageDialog(tableview, se+"from TableCrud Listener");
       }
        }
    
        
    }
    public class TableGroupCheckBoxListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getActionCommand().equalsIgnoreCase("checkboxRate")){
           if(tableview.RateStatus()){
               tableview.setTableGroupRate("");
               tableview.setTableGroupRateEditableFalse();
           }
           else{
               tableview.setTableGroupRateEditableTrue();
           }
        }
        }
        
    }
    public class ComboTableGroupListener implements ActionListener{
        Object item[];
        @Override
        public void actionPerformed(ActionEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getActionCommand().equalsIgnoreCase("comboTableGroupName")){
            try{
            JComboBox combo = (JComboBox) e.getSource();
            if(combo.getSelectedIndex() == 0){
                tableview.setTableGroupId(0);
                
            }
            else{
                for (Object[] TableGroupInfo : tablemodel.TableGroupInfo) {
                    if (combo.getSelectedItem().equals(TableGroupInfo[1])) {
                        item = TableGroupInfo;
                        break;
                    }
                }
            tableview.setTableGroupId(Integer.parseInt(item[0].toString()));
           // System.out.println(item[0].toString());
            }
            }
             catch(Exception ex){
               JOptionPane.showMessageDialog(tableview,ex+"from combotableGroup Listener");
           }
        }
        }
        
    }
    public class returnTableGroupRowSelectedListener implements ListSelectionListener{
        TableCrudView theview;
        JTable jtable;
        public returnTableGroupRowSelectedListener(TableCrudView view){
            theview = view;
            jtable = theview.tblGroupTable;
        }
        

        @Override
        public void valueChanged(ListSelectionEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getValueIsAdjusting()){
                return;
            }
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            if(lsm.isSelectionEmpty()){
                
            }
            else{
                ListSelectionModel listModel = jtable.getSelectionModel();
                 int lead = listModel.getLeadSelectionIndex();
                 
                 theview.setTableGroup(displayRowValues(lead));
                 InitialTableGroupName = theview.getTableGroupName();
                // theview.checkedTableGroupRate();
                 theview.disableTableGroupAdd();
                 theview.enableTableGroupEdit();
                 theview.enableTableGroupDelete();
            }
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(theview, se+"from return tablrgroup row slected listener");
        }
        }
        private String[] displayRowValues(int lead){
            int columns = jtable.getColumnCount();
            String[] st = new String[columns];
            for(int i = 0;i<columns;i++){
                
                Object o = jtable.getValueAt(lead, i);
         
                if(o!= null){
                st[i] = o.toString();
                
             }
             else{
                 st[i] = "";
              //  System.out.println("wala");
             }
           
            }
            return st;
            
        }
        
    }
    public class returnTableRowSelectedListener implements ListSelectionListener{
        TableCrudView theview;
        JTable jtable;
        public returnTableRowSelectedListener(TableCrudView view){
            theview = view;
            jtable = theview.tblTable;
        }
        

        @Override
        public void valueChanged(ListSelectionEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getValueIsAdjusting()){
                return;
            }
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            if(lsm.isSelectionEmpty()){
                
            }
            else{
                ListSelectionModel listModel = jtable.getSelectionModel();
                 int lead = listModel.getLeadSelectionIndex();
                 
                 theview.setTable(displayRowValues(lead));
                 InitialTableName = theview.getTableName();
                // theview.checkedTableGroupRate();
                 theview.disableTableAdd();
                 theview.enableTableEdit();
                 theview.enableTableDelete();
            }
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(theview, se+"from return tablerow slected listener");
        }
        }
        private String[] displayRowValues(int lead){
            int columns = jtable.getColumnCount();
            String[] st = new String[columns];
            for(int i = 0;i<columns;i++){
                
                Object o = jtable.getValueAt(lead, i);
         
                if(o!= null){
                st[i] = o.toString();
                
             }
             else{
                 st[i] = "";
              //  System.out.println("wala");
             }
           
            }
            return st;
            
        }
        
    }
    
    //for search mechanish
    public class TableDocumentSearchListener implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
        ReloadTableModel();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            ReloadTableModel();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            ReloadTableModel();
        }
        private void ReloadTableModel(){
            try{
                tableview.refreshTableJTable(tablemodel.getTableInfoLike(tableview.getTableSearch()));
                
            }
            catch(Exception se){
                DisplayMessages.displayError(mainview, se.getMessage()+" from"+getClass().getName(), "Error From TableDocumentSearchListener");
            }
        }
    }
}
