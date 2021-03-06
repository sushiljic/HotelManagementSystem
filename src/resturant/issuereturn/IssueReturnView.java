/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.issuereturn;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SUSHIL
 */
public class IssueReturnView extends javax.swing.JDialog {
    private String ItemId = "";
    private String UnitId ="";
    private String ReturnId = new String();
    private int IssueId;
    private int DepartmentId = 0;
    private String IssueDate;
    private ListSelectionModel selectionModel;
    private DefaultComboBoxModel modelItemBaseUnit;

    /**
     * Creates new form IssueReturnView
     */
    public IssueReturnView(JFrame parent,boolean modal) {
        super(parent,modal);
        initComponents();
        selectionModel = tblReturnList.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        disableReturnBtn();
        txtIssueDate.addFocusListener(new SetFocusListener(txtIssueDate));
        txtIssueQuantity.addFocusListener(new SetFocusListener(txtIssueQuantity));
        txtItemName.addFocusListener(new SetFocusListener(txtItemName));
        txtReturnQuantity.addFocusListener(new SetFocusListener(txtReturnQuantity));
        txtSearch.addFocusListener(new SetFocusListener(txtSearch));
        txtStockQuantity.addFocusListener(new SetFocusListener(txtStockQuantity));
        setButtonForEnter(btnCancel);
        setButtonForEnter(btnReturn);
        setButtonForEnter(btnSearch);
         /*
         * centering the frame
         */
        setLocationRelativeTo(null);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtReturnQuantity = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtReturnReason = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        txtStockQuantity = new javax.swing.JTextField();
        txtItemName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtIssueQuantity = new javax.swing.JTextField();
        comboItemBaseUnit = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        txtIssueDate = new javax.swing.JTextField();
        lblStoretitle = new javax.swing.JLabel();
        btnReturn = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblReturnList = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        comboDepartmentName = new javax.swing.JComboBox();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Issue Return ");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 153, 255)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Item Name:*");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Return Quantity:*");

        txtReturnQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReturnQuantityActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Item Base Unit:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Return Reason:");

        txtReturnReason.setColumns(20);
        txtReturnReason.setRows(5);
        jScrollPane1.setViewportView(txtReturnReason);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Stock Quantity:");

        txtStockQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockQuantityActionPerformed(evt);
            }
        });

        txtItemName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Issue Quantity:");

        comboItemBaseUnit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Issue Date:");

        txtIssueDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIssueDateActionPerformed(evt);
            }
        });

        lblStoretitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblStoretitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtReturnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtItemName, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtStockQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtIssueQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(37, 37, 37)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboItemBaseUnit, 0, 102, Short.MAX_VALUE)
                    .addComponent(txtIssueDate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtItemName, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStoretitle, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtStockQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(comboItemBaseUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtIssueDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIssueQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtReturnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnReturn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnReturn.setMnemonic('R');
        btnReturn.setText("Return");
        btnReturn.setToolTipText("ALT+R");

        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancel.setMnemonic('C');
        btnCancel.setText("Cancel");
        btnCancel.setToolTipText("ALT+C");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Issued Item List"));

        tblReturnList.setAutoCreateRowSorter(true);
        tblReturnList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblReturnList.getTableHeader().setReorderingAllowed(false);
        tblReturnList.setRowHeight(20);
        jScrollPane2.setViewportView(tblReturnList);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Department Name:");

        comboDepartmentName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboDepartmentName.setActionCommand("comboDepartmentName");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnSearch.setMnemonic('s');
        btnSearch.setText("Search");
        btnSearch.setToolTipText("ALT+S");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 255));
        jLabel2.setText("Search for the Item to Return");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboDepartmentName, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(txtSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboDepartmentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtStockQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockQuantityActionPerformed

    private void txtReturnQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReturnQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReturnQuantityActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtIssueDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIssueDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIssueDateActionPerformed
    public int getIssueId(){
        return IssueId;
    }
    public void setIssueId(int IssueId){
        //System.out.println("Issuetest");
        this.IssueId = IssueId;
    }
    public String getItemId(){
        return ItemId;
    }
    public void  setItemid(String itemid){
        ItemId = itemid;
    }
     public String getUnitId(){
        return UnitId;
    }
    public void  setUnitid(String unitid){
        ItemId = unitid;
    }
         public int getDepartmentId(){
        return DepartmentId;
    }
    public void setDepartmentId(int id){
        DepartmentId = id;
    }
    public String getItemName(){
        return txtItemName.toString();
    }
    public void setItemName(String itemname){
        txtItemName.setText(itemname);
    }
    public void setlblStoreTitle(String name){
        lblStoretitle.setText(name);
    }
    public String getlblStoreTitle(){
        return lblStoretitle.getText();
    }
  /*  public String getIssueQuantity(){
        return txtIssueQuantity.toString();
    }
    public void setIssueQuantity(String qty){
        txtIssueQuantity.setText(qty);
    }*/
    public String getIssueQuantity(){
        return txtIssueQuantity.getText().toString();
    }
    public void setIssueQuantity(String qty){
        txtIssueQuantity.setText(qty);
    }
    public String getStockQuantity(){
        return txtStockQuantity.getText().toString();
    }
    public void setStockQuantity(String qty){
        txtStockQuantity.setText(qty);
    }
    public String getReturnQuantity(){
        return txtReturnQuantity.getText().toString();
    }
    public void setReturnQuantity(String qty){
        txtReturnQuantity.setText(qty);
    }
    public String getItemBaseUnitName(){
        return comboItemBaseUnit.getSelectedItem().toString();
    }
    public void setcomboItemBaseUnitName(String baseunit){
       comboItemBaseUnit.setSelectedItem(baseunit);
       
    }
    public void  setcomboItemBaseUnitName(String[] ItemName){
    // comboItemName.setSelectedItem(ItemName);
     modelItemBaseUnit  = new DefaultComboBoxModel(ItemName) ;
     //comboItemName = setModel(modelItemName);
      comboItemBaseUnit.setModel(modelItemBaseUnit);
     
 }
 public void  setcomboItemBaseUnitName(Object[] ItemName){
    // comboItemName.setSelectedItem(ItemName);
     modelItemBaseUnit  = new DefaultComboBoxModel(ItemName) ;
     //comboItemName = setModel(modelItemName);
      comboItemBaseUnit.setModel(modelItemBaseUnit);
     
 }
     public void setcomboDepartmentName(Object[] data){
        DefaultComboBoxModel model = new DefaultComboBoxModel(data);
        comboDepartmentName.setModel(model);
    }
    public void setcomboDepartmentName(String st){
        comboDepartmentName.setSelectedItem(st);
    }
    public String getcomboDepartmentName(){
        return comboDepartmentName.getSelectedItem().toString();
    }
    public JComboBox returnComboDepartmentName(){
        return comboDepartmentName;
    }
    
    public String getReturnReason(){
        return txtReturnReason.getText().toString();
    }
    public void setReturnReason(String reason){
        txtReturnReason.setText(reason);
        
    }
    public String getSearch(){
        return txtSearch.getText().toString();
    }
    public void setSearch(String search){
        txtSearch.setText(search);
    }
    
    public String getReturnId(){
        return ReturnId;
    }
    public void enableReturnBtn(){
        btnReturn.setEnabled(true);
    }
    public void disableReturnBtn(){
        btnReturn.setEnabled(false);
    }
    public void setReturnId(String returnid){
    ReturnId= returnid;
    }

    public JButton getBtnReturn() {
        return btnReturn;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }
    
    public void setIssueDate(String data){
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        if(data.equals("")){
            txtIssueDate.setText("");
        }
        else{
        try {
            //System.out.println(data);
             //System.out.println(dateformat.parse(data));
            // System.out.println(dateformat.format(dateformat.parse(data)));
             txtIssueDate.setText(dateformat.format(dateformat.parse(data)));
        } catch (ParseException ex) {
           // Logger.getLogger(IssueReturnView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex+"from setissuedate");
        }
        }
           //IssueDate = dateformat.format(new date(data));
          // IssueDate = dateformat.
         //  IssueDate = dateformat.format(data);
          //  System.out.println(IssueDate);
        //    txtIssueDate.setText(data);
        
    }
    public String getIssueDate(){
        return txtIssueDate.getText();
    }
    public String[] getIssueReturnInfo(){
       
        String[] ReturnInfo = new String[8];
      //  ReturnInfo[0] = getItemId();
        ReturnInfo[0] = String.valueOf(getIssueId());
        ReturnInfo[1] = getItemName();
       // ReturnInfo[2]= getIssueQuantity();
         
        ReturnInfo[2]= getStockQuantity();
        ReturnInfo[3]= getIssueQuantity();
        ReturnInfo[4]= getReturnQuantity();
      //  ReturnInfo[4] = getUnitId();
        ReturnInfo[5] = getItemBaseUnitName();
        ReturnInfo[6]= getIssueDate();
        ReturnInfo[7] = getReturnReason();
       return ReturnInfo; 
    }
    public void setIssueReturnInfo(String[] returninfo){
         //Issue Id","Item Name","Issue Quantity","Item BaseUnit","Issue From","Issue To","Issue Date
      //  setItemid(returninfo[0]);
        setIssueId(Integer.parseInt(returninfo[0]));
        setItemName(returninfo[1]);
     //   setIssueQuantity(returninfo[2]);
        setIssueQuantity(returninfo[2]);
        //setStockQuantity(returninfo[3]);
       // setReturnQuantity(returninfo[4]);
        //setUnitid(returninfo[5]);
        setcomboItemBaseUnitName(new String[]{returninfo[3]});
        setIssueDate(returninfo[6]);
        
        setReturnReason("");
        
    }
    public void setFieldEditableFalse(){
       txtItemName.setEditable(false);
       txtStockQuantity.setEditable(false);
       txtIssueQuantity.setEditable(false);
       txtIssueDate.setEditable(false);
   }
   public void showFieldEditableTrue(){
       txtItemName.setEditable(true);
       txtStockQuantity.setEditable(true);
       txtIssueQuantity.setEditable(true);
       txtIssueDate.setEditable(true);
   }
    public  void clearAll(){
      //  setItemid("");
        setIssueId(0);
        setItemName("");
     //   setIssueQuantity("");
        setStockQuantity("");
        setReturnQuantity("");
        setIssueQuantity("");
        setIssueDate("");
        setReturnReason("");
        setlblStoreTitle("");
    }
     public void AddSelectInCombo(JComboBox jc){
      jc.insertItemAt("SELECT", 0);
      jc.setSelectedIndex(0);
    }
     public void addComboDepartmentNameListener(ActionListener Listen){
         comboDepartmentName.addActionListener(Listen);
     }
     public final void setButtonForEnter(JButton jb){
         jb.registerKeyboardAction(jb.getActionForKeyStroke(
                                      KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                                      KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                                      JComponent.WHEN_FOCUSED);
 
        jb.registerKeyboardAction(jb.getActionForKeyStroke(
                                      KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                                      KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                                      JComponent.WHEN_FOCUSED);
        
    }
    public void refreshJTable(DefaultTableModel dataModel){

     selectionModel  = tblReturnList.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    tblReturnList.setModel(dataModel);
    
}    

    public  void addReturnListener(ActionListener ListenForReturn){
        btnReturn.addActionListener(ListenForReturn);
    }
    public void addCancelListener(ActionListener ListenForCancel){
        btnCancel.addActionListener(ListenForCancel);
    }
    public void addSearchListnener(ActionListener ListenForSearch){
        btnSearch.addActionListener(ListenForSearch);
    }
    public void addRowSelectedListener(ListSelectionListener ListenForRowSelect){
        selectionModel.addListSelectionListener(ListenForRowSelect);
    }
    public void addTextSearchListener(ActionListener ListenForSearch){
        txtSearch.addActionListener(ListenForSearch);
    }
    public void  addKeyReturnListener(KeyListener ListenForReturn){
        btnReturn.addKeyListener(ListenForReturn);
    }
    public void addKeyCancelListener(KeyListener ListenForCancel){
        btnCancel.addKeyListener(ListenForCancel);
    }
    public void addKeySearchListener(KeyListener ListenForSearch){
        btnSearch.addKeyListener(ListenForSearch);
    }
    public void addTxtSearchDocumentListener(DocumentListener Listen){
        txtSearch.getDocument().addDocumentListener(Listen);
    }
    public final class SetFocusListener implements FocusListener{
      JTextField jf;
        public SetFocusListener(JTextField field){
         jf = field;   
        }

        @Override
        public void focusGained(FocusEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          jf.setBackground(new Color(136,249,168));
        }

        @Override
        public void focusLost(FocusEvent e) {
        //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       jf.setBackground(Color.white);
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IssueReturnView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueReturnView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueReturnView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueReturnView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueReturnView(new JFrame(),true).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox comboDepartmentName;
    private javax.swing.JComboBox comboItemBaseUnit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblStoretitle;
    public javax.swing.JTable tblReturnList;
    private javax.swing.JTextField txtIssueDate;
    private javax.swing.JTextField txtIssueQuantity;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtReturnQuantity;
    private javax.swing.JTextArea txtReturnReason;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStockQuantity;
    // End of variables declaration//GEN-END:variables
}
