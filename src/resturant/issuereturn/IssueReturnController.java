/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.issuereturn;

import hotelmanagementsystem.MainFrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import reusableClass.DisplayMessages;

/**
 *
 * @author SUSHIL
 */
public class IssueReturnController {
   private  IssueReturnModel issueReturnModel;
   private IssueReturnView issueReturnView;
   private MainFrameView mainview;
   
   
   IssueReturnController(IssueReturnModel model,IssueReturnView view,MainFrameView main){
       issueReturnModel = model;
       issueReturnView = view;
       mainview = main;
       
       /*
        * implementing the actionlistener for the buttons in view
        */
       issueReturnView.addSearchListnener(new IssueReturnListener());
//       issueReturnView.addKeySearchListener(new IssueReturnKeyListener());
       
       issueReturnView.addReturnListener(new IssueReturnListener());
//       issueReturnView.addKeyReturnListener(new IssueReturnKeyListener());
       
       issueReturnView.addCancelListener(new IssueReturnListener());
//       issueReturnView.addKeyCancelListener(new IssueReturnKeyListener());
       
       issueReturnView.addTextSearchListener(new TextIssueReturnListener());
       issueReturnView.addComboDepartmentNameListener(new ComboListener());
       
       issueReturnView.addRowSelectedListener(new returnRowSelectedListener(issueReturnView));
       issueReturnView.addTxtSearchDocumentListener(new TxtSearchDocumentListener());
       try{
              issueReturnView.setcomboDepartmentName(issueReturnModel.returnSecondColumn(issueReturnModel.getRespectiveDepartment(mainview.getUserId())));
            //if it has only one element select it order wise add select into it
           //if it has only one element select it order wise add select into it
            int combosize = issueReturnView.returnComboDepartmentName().getModel().getSize();
            if(combosize >1){
                issueReturnView.AddSelectInCombo(issueReturnView.returnComboDepartmentName());
            }
            else{
                if(combosize == 1){
                issueReturnView.returnComboDepartmentName().setSelectedIndex(0);
                }
            }
//             view.refreshJTable(model.getResturantItemList(issueReturnView.getDepartmentId()));
       }
       catch(Exception se){
           JOptionPane.showMessageDialog(view, se+"from constructor "+getClass().getName());
       }
   }
    class IssueReturnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getActionCommand().equalsIgnoreCase("Return")){
            try{
             //   System.out.println("walal");
                if("".equals(issueReturnView.getIssueId())){
                    JOptionPane.showMessageDialog(issueReturnView, "Please Select the  Issued Item to be Return");
              return;
                }
              // System.out.println("walal"); 
                if(issueReturnView.getReturnQuantity().trim().isEmpty()){
                    JOptionPane.showMessageDialog(issueReturnView, "Return Quantity Can be Empty.");
                    return;
                }
                if(issueReturnView.getReturnReason().trim().isEmpty()){
                     JOptionPane.showMessageDialog(issueReturnView, "Return Reason Can be Empty.");
                    return;
                }
               // System.out.println(issueReturnView.getReturnQuantity());
              //  System.out.println("walal");
                
                if(Float.parseFloat(issueReturnView.getReturnQuantity())> Float.parseFloat(issueReturnView.getIssueQuantity())){
                   JOptionPane.showMessageDialog(issueReturnView, "Return Quantity is greater than  Issued Quantity");
                   return;
                }
                if(Float.parseFloat(issueReturnView.getReturnQuantity())> Float.parseFloat(issueReturnView.getStockQuantity())){
                   JOptionPane.showMessageDialog(issueReturnView, "There is not Sufficient Amount  to return.");
                   return;
                }
               // System.out.println("walal");
                int choice;
                choice = JOptionPane.showConfirmDialog(issueReturnView, "Do You Want to Return the Item","Return Item", JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION){
                    issueReturnModel.IssueReturn(issueReturnView.getIssueReturnInfo());
                    issueReturnView.clearAll();
                    issueReturnView.enableReturnBtn();
                    issueReturnView.refreshJTable(issueReturnModel.getResturantItemList(issueReturnView.getDepartmentId()));
                }
                }
                
           catch(Exception ee){
                JOptionPane.showMessageDialog(issueReturnView, ee+"form IssueReturnListener");
            }
            
        }
        if(e.getActionCommand().equalsIgnoreCase("Cancel")){
            issueReturnView.clearAll();
             issueReturnView.disableReturnBtn();
        }
        if(e.getActionCommand().equalsIgnoreCase("Search")){
            String strsearch;
            String[] SearchBox = new String[7];
            boolean flag = false;
            int col =1;
            try{
                strsearch = issueReturnView.getSearch();
                for(int row = 0;row<issueReturnView.tblReturnList.getModel().getRowCount();row++){
                    if(strsearch.equalsIgnoreCase(issueReturnView.tblReturnList.getValueAt(row, col).toString())||strsearch.equalsIgnoreCase(issueReturnView.tblReturnList.getValueAt(row, col-1).toString())){
                     for(int scol = 0;scol<issueReturnView.tblReturnList.getModel().getColumnCount();scol++){
                        SearchBox[scol] = issueReturnView.tblReturnList.getValueAt(row, scol).toString();
                     }
                     //inorer to scrooll over the tbale
                     issueReturnView.tblReturnList.scrollRectToVisible(issueReturnView.tblReturnList.getCellRect(row, 0, true));
                     // for focus
                     issueReturnView.tblReturnList.setRowSelectionInterval(row, row);
                      issueReturnView.setIssueReturnInfo(SearchBox);
        issueReturnView.setFieldEditableFalse();
        Float UnitRelativeQuantity = issueReturnModel.getUnitRelativeQuantity(issueReturnModel.getUnitIdByIssueId(issueReturnView.getIssueId()));
        issueReturnView.setStockQuantity(String.valueOf(issueReturnModel.getStockQuantityfromResturantStore(issueReturnView.getIssueId())/UnitRelativeQuantity));
          issueReturnView.enableReturnBtn();
        flag =true;
        break;
                    }
                 }
                if(flag == false){
                    JOptionPane.showMessageDialog(issueReturnView, "Item Not Found.");
                }
                
            }
            catch(Exception sere){
                JOptionPane.showMessageDialog(issueReturnView, sere+"from search");
            }
        }
        }
        
    }
    class TextIssueReturnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String strsearch;
            String[] SearchBox = new String[7];
            boolean flag = false;
            int col =1;
            try{
                strsearch = issueReturnView.getSearch();
                for(int row = 0;row<issueReturnView.tblReturnList.getModel().getRowCount();row++){
                    if(strsearch.equalsIgnoreCase(issueReturnView.tblReturnList.getValueAt(row, col).toString())||strsearch.equalsIgnoreCase(issueReturnView.tblReturnList.getValueAt(row, col-1).toString())){
                     for(int scol = 0;scol<issueReturnView.tblReturnList.getModel().getColumnCount();scol++){
                        SearchBox[scol] = issueReturnView.tblReturnList.getValueAt(row, scol).toString();
                     }
                     //inorer to scrooll over the tbale
                     issueReturnView.tblReturnList.scrollRectToVisible(issueReturnView.tblReturnList.getCellRect(row, 0, true));
                     // for focus
                     issueReturnView.tblReturnList.setRowSelectionInterval(row, row);
                      issueReturnView.setIssueReturnInfo(SearchBox);
        issueReturnView.setFieldEditableFalse();
        Float UnitRelativeQuantity = issueReturnModel.getUnitRelativeQuantity(issueReturnModel.getUnitIdByIssueId(issueReturnView.getIssueId()));
        issueReturnView.setStockQuantity(String.valueOf(issueReturnModel.getStockQuantityfromResturantStore(issueReturnView.getIssueId())/UnitRelativeQuantity));
        issueReturnView.enableReturnBtn();
        flag =true;
        break;
                    }
                 }
                if(flag == false){
                    JOptionPane.showMessageDialog(issueReturnView, "Item Not Found.");
                }
                
            }
            catch(Exception sere){
                JOptionPane.showMessageDialog(issueReturnView, sere+"from search");
            }
           
        }
        
    }
    class IssueReturnKeyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyPressed(KeyEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      int key;
      key = e.getKeyCode();
      if(key == KeyEvent.VK_ENTER){
          if(e.getSource().toString().contains("Return")){
               try{
             //   System.out.println("walal");
                if("".equals(issueReturnView.getIssueId())){
                    JOptionPane.showMessageDialog(issueReturnView, "Please Select the  Issued Item to be Return");
              return;
                }
              // System.out.println("walal"); 
                if(issueReturnView.getReturnQuantity().trim().isEmpty()){
                    JOptionPane.showMessageDialog(issueReturnView, "Return Quantity Can be Zero.");
                    return;
                }
               // System.out.println(issueReturnView.getReturnQuantity());
              //  System.out.println("walal");
                if(Float.parseFloat(issueReturnView.getReturnQuantity())> Float.parseFloat(issueReturnView.getIssueQuantity())){
                   JOptionPane.showMessageDialog(issueReturnView, "Return Quantity is greater than  Issued Quantity");
                   return;
                }
               // System.out.println("walal");
                int choice;
                choice = JOptionPane.showConfirmDialog(issueReturnView, "Do You Want to Return the Item","Return Item", JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION){
                    issueReturnModel.IssueReturn(issueReturnView.getIssueReturnInfo());
                    issueReturnView.clearAll();
                    issueReturnView.enableReturnBtn();
                }
                }
                
           catch(Exception ee){
                JOptionPane.showMessageDialog(issueReturnView, ee+"form IssueReturnListener");
            }
              
          }
          if(e.getSource().toString().contains("Cancel")){
            issueReturnView.clearAll(); 
           issueReturnView.disableReturnBtn();
          }
          if(e.getSource().toString().contains("Search")){
               String strsearch;
            String[] SearchBox = new String[7];
            boolean flag = false;
            int col =1;
            try{
                strsearch = issueReturnView.getSearch();
                for(int row = 0;row<issueReturnView.tblReturnList.getModel().getRowCount();row++){
                    if(strsearch.equalsIgnoreCase(issueReturnView.tblReturnList.getValueAt(row, col).toString())||strsearch.equalsIgnoreCase(issueReturnView.tblReturnList.getValueAt(row, col-1).toString())){
                     for(int scol = 0;scol<issueReturnView.tblReturnList.getModel().getColumnCount();scol++){
                         SearchBox[scol] = issueReturnView.tblReturnList.getValueAt(row, scol).toString();
                     }
                     //inorer to scrooll over the tbale
                     issueReturnView.tblReturnList.scrollRectToVisible(issueReturnView.tblReturnList.getCellRect(row, 0, true));
                     // for focus
                     issueReturnView.tblReturnList.setRowSelectionInterval(row, row);
//                      issueReturnView.setIssueReturnInfo(SearchBox);
//        issueReturnView.setFieldEditableFalse();
//        Float UnitRelativeQuantity = issueReturnModel.getUnitRelativeQuantity(issueReturnModel.getUnitIdByIssueId(issueReturnView.getIssueId()));
//        issueReturnView.setStockQuantity(String.valueOf(issueReturnModel.getStockQuantityfromResturantStore(issueReturnView.getIssueId())/UnitRelativeQuantity));
//        issueReturnView.enableReturnBtn();
        flag = true;
        break;
        
                    }
                 }
                
                 if(flag == false){
                    JOptionPane.showMessageDialog(issueReturnView, "Item Not Found.");
                }
                
            }
            catch(Exception sere){
                JOptionPane.showMessageDialog(issueReturnView, sere+"from search");
            }
          }
      }
        }

        @Override
        public void keyReleased(KeyEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    class ComboListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       try{
            if(e.getActionCommand().equalsIgnoreCase("comboDepartmentName")){
                 Object[][] departmentdata= issueReturnModel.getRespectiveDepartment(mainview.getUserId());
                JComboBox dep = (JComboBox)e.getSource();
                 if(dep.getSelectedItem().equals("SELECT")){
                     issueReturnView.setDepartmentId(0);
                     issueReturnView.refreshJTable(new DefaultTableModel());
                     
                 }
                 else{
                     for(Object[] dat:departmentdata){
                         if(dat[1].equals(dep.getSelectedItem())){
                             issueReturnView.setDepartmentId(Integer.parseInt(dat[0].toString()));
                             //loading the repective item according to department  
                             issueReturnView.refreshJTable(issueReturnModel.getResturantItemList(issueReturnView.getDepartmentId()));
                             break;
                             
                         }
                     }
                 }
              }
       }
       catch(Exception se){
           JOptionPane.showMessageDialog(issueReturnView, se+"from ComboListener"+getClass().getName());
       }
        }
        
    }
    class returnRowSelectedListener implements ListSelectionListener{
        IssueReturnView IRView;
        JTable jtable;
        
        returnRowSelectedListener(IssueReturnView view){
            IRView = view;
            jtable = IRView.tblReturnList;
        }
        
        

        @Override
        public void valueChanged(ListSelectionEvent e) {
          
             try{
        if(e.getValueIsAdjusting()){
            return;
        }
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        
        if(lsm.isSelectionEmpty()){
            //nothing is selected
        }
        else{
              ListSelectionModel lsmodel = jtable.getSelectionModel();
        int lead = lsmodel.getLeadSelectionIndex();
       // IRView.
        IRView.setIssueReturnInfo(displayRowValues(lead));
        IRView.setFieldEditableFalse();
        int issueid = IRView.getIssueId();
        Float UnitRelativeQuantity = issueReturnModel.getUnitRelativeQuantity(issueReturnModel.getUnitIdByIssueId(issueid));
//        System.out.println(UnitRelativeQuantity);
//        System.out.println(String.valueOf(issueReturnModel.getStockQuantityfromResturantStore(issueid))+"wala"+issueid);
        IRView.setStockQuantity(String.valueOf(issueReturnModel.getStockQuantityfromResturantStore(IRView.getIssueId())/UnitRelativeQuantity));
        IRView.enableReturnBtn();
        IRView.setlblStoreTitle(displayRowValues(lead)[5]);
            
        }
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(IRView, e+"from returnrowlistener");
        }
            /*if(!e.getValueIsAdjusting()){
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ListSelectionModel lsmodel = jtable.getSelectionModel();
        int lead = lsmodel.getLeadSelectionIndex();
       // IRView.
        IRView.setIssueReturnInfo(displayRowValues(lead));
        IRView.setFieldEditableFalse();
        Float UnitRelativeQuantity = issueReturnModel.getUnitRelativeQuantity(issueReturnModel.getUnitIdByIssueId(IRView.getIssueId()));
        IRView.setStockQuantity(String.valueOf(issueReturnModel.getStockQuantityfromResturantStore(IRView.getIssueId())/UnitRelativeQuantity));
        IRView.enableReturnBtn();
            }*/
        }
        private String[] displayRowValues(int lead){
            int columns = jtable.getColumnCount();
            String[] st = new String[columns];
            for(int i=0;i<columns;i++){
                try{
                   Object o = jtable.getValueAt(lead,i);
                   st[i] = o.toString();
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(jtable, e+"for displayrowvalues");
                }
            }
            return st;
        }
    }
    class TxtSearchDocumentListener implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
            ReloadTable();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            ReloadTable();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            ReloadTable();
        }
        private void ReloadTable(){
            try{
                issueReturnView.refreshJTable(issueReturnModel.getResturantItemListLikeSearch(issueReturnView.getDepartmentId(), issueReturnView.getSearch()));
                
            }
            catch(Exception se){
                DisplayMessages.displayError(mainview, se.getMessage()+" from "+ getClass().getName(), "Search Document Listener Error");
            }
        }
        
    }
}
