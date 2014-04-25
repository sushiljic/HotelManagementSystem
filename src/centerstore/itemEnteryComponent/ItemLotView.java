/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package centerstore.itemEnteryComponent;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.*;

/**
 *
 * @author MinamRosh
 */
public class ItemLotView extends javax.swing.JDialog {

    private DefaultComboBoxModel distroModel;
    private DefaultComboBoxModel baseUModel;
    private DefaultComboBoxModel itemModel;
    private DefaultTableModel purchaseModel;
    private ListSelectionModel purchaseListModel;
    
    /**
     * Creates new form ItemLotView
     * @param parent
     * @param model
     */
    public ItemLotView(JFrame parent,boolean model) {
        super(parent,model);
        
        initComponents();
        //register key
        setButtonForEnter(btnAdd);
        setButtonForEnter(btnUpdate);
        setButtonForEnter(btnDistro);
        setButtonForEnter(btnCancel);
        
        
        txtExDate.setMinSelectableDate(new Date());
        disableUp();
        setLocationRelativeTo(null);
    }

    /* buttion listener*/
    public void addListenerToCancel(ActionListener lCn){
        btnCancel.addActionListener(lCn);
    }
    
    public void addListenerToAdd(ActionListener lAd){
        btnAdd.addActionListener(lAd);
    }
    
    public void addListenerToUpdate(ActionListener lUp){
        btnUpdate.addActionListener(lUp);
    }
    
    
    public void addListenerToDistro(ActionListener lDi){
        btnDistro.addActionListener(lDi);
    }
    
    public void addFoucsListenerToField(FocusListener f){
        txtBRate.addFocusListener(f);
        txtExDate.addFocusListener(f);
        txtQty.addFocusListener(f);
        txtThreshold.addFocusListener(f);
        txtItemStock.addFocusListener(f);
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
    
    /*set combo list and add listener*/
    public void setComboDistroList(String[] distroList){
        String[] distro = null;
        if(distroList.length <= 0){
            distro = new String[1];
            distro[0] = "No Distributor List";
        }
        else{
            distro = new String[distroList.length+1];
            distro[0] = "Select Distributor";
            for(int i = 0; i < distroList.length; i++){
                distro[i+1] = distroList[i];
        }
        }
        distroModel = new DefaultComboBoxModel(distro);
        comboDistributor.setModel(distroModel);
    }
    
    public String getSelectedDistro(){
        return comboDistributor.getSelectedItem().toString();
    }
    
     //set base unit list
    public void setComboBUnitList(String[] unitList){
        String[] unit = null;
        if(unitList.length <= 0){
            unit = new String[1];
            unit[0] = "No Unit List";
        }
        else{
            unit = new String[unitList.length+1];
            unit[0] = "Select Unit";
            for(int i = 0; i < unitList.length; i++){
                unit[i+1] = unitList[i];
            }
        }
        baseUModel = new DefaultComboBoxModel(unit);
        comboItemBUnit.setModel(baseUModel);
    }
    //get selected base unit
    public String getSelectedBUnit(){
        return comboItemBUnit.getSelectedItem().toString();
    }
    
    public void setComboItemList(String[] itemList){
        
        String[] item = null; //new String[itemList.length+1];
        if(itemList.length <= 0){
            item = new String[1];
            item[0] = "No Item List";
        }
        else{
            item = new String[itemList.length+1];
            item[0] = "Select Item";
            for(int i = 0; i < itemList.length; i++){
                item[i+1] = itemList[i];
            }
        }
        
        itemModel = new DefaultComboBoxModel(item);
        comboItemList.setModel(itemModel);
    }
    
    /* purchase table */
    public void setTblPurchare(DefaultTableModel m){
        purchaseModel = m;
        tblPurchase.setModel(purchaseModel);
    }
    
    public void addListenerToPurchase(ListSelectionListener lP){
        purchaseListModel = tblPurchase.getSelectionModel();
        purchaseListModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        purchaseListModel.addListSelectionListener(lP);
    }
    
    public void addKeyListenerToBtn(KeyListener k){
        btnAdd.addKeyListener(k);
        btnUpdate.addKeyListener(k);
        btnCancel.addKeyListener(k);
        
    }
    
    public void addKeyListenerToDistro(KeyListener k){
        btnDistro.addKeyListener(k);
    }
    //return list selection model
    public ListSelectionModel getPurchaseListModel(){
        return purchaseListModel;
    }
    
    //return table
    public JTable getPurchaseTable(){
        return tblPurchase;
    }
    //get selected base unit
    public String getSelectedItem(){
        return comboItemBUnit.getSelectedItem().toString();
    }
    
    public void addListenerToComboItem(ActionListener l){
        comboItemList.addActionListener(l);
    }
    public void addListenerToComboUnit(ActionListener u){
        comboItemBUnit.addActionListener(u);
    }
    public void addListenerToComboDistro(ActionListener d){
        comboDistributor.addActionListener(d);
    }
    /* set selection on the basisc of item anme */
    public void setUnit(String name){
        comboItemBUnit.setSelectedItem(name);
    }
    public void setDistro(String name){
        comboDistributor.setSelectedItem(name);
       
    }
    
    public void setItem(String name){
        comboItemList.setSelectedItem(name);
    }
    public void showStock(double stock){
        txtItemStock.setText(Double.toString(stock));
    }
    
    public void disableComboItem(){
        comboItemList.setEnabled(false);
    }
    public void enableComoboItem(){
        comboItemList.setEnabled(true);
    }
    public void enableUp(){
        btnUpdate.setEnabled(true);
    }
    public final void disableUp(){
        btnUpdate.setEnabled(false);
    }
    public void enableAd(){
        btnAdd.setEnabled(true);
    }
    public void disableAd(){
        btnAdd.setEnabled(false);
    }
    
    public void clearAll(){
        comboDistributor.setSelectedIndex(0);
        comboItemBUnit.setSelectedIndex(0);
        comboItemList.setSelectedIndex(0);
        
         txtItemStock.setText("0");
        //txtItemStock.setText("0");
        txtBRate.setText("");
        txtExDate.setDate(null);
        txtQty.setText("");
        txtThreshold.setText("0");
    }
    
    public void setThreshold(String threshold){
        txtThreshold.setText(threshold);
    }
    //get all lot data from the frame;
    //only text field;
    public String[] getLotData(){
        SimpleDateFormat n = new SimpleDateFormat("yyyy-MM-dd");
        String exDate = n.format(txtExDate.getJCalendar().getDate()).toString().trim();
        String qty = txtQty.getText().trim();
        String bRate = txtBRate.getText().trim();
        
        
        String[] txtLotData = new String[]{qty, bRate, exDate, txtThreshold.getText().trim()};
        return txtLotData;
    }
     public void clearComboList(){
        comboItemBUnit.setSelectedIndex(0);
        comboItemList.setSelectedIndex(0);
        comboDistributor.setSelectedIndex(0);
    }
    
    //show text filed data 
    public void showTxtData(String[] data ){
        //System.out.println(data[4]);
        txtBRate.setText(data[4]);
        txtQty.setText(data[5]);
        txtThreshold.setText(data[7]);
        try {
            txtExDate.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(data[8]));
        } catch (ParseException ex) {
            Logger.getLogger(ItemLotView.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        jPanel4 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPurchase = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txtBRate = new javax.swing.JTextField();
        lblBRate = new javax.swing.JLabel();
        txtThreshold = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        comboDistributor = new javax.swing.JComboBox();
        btnDistro = new javax.swing.JButton();
        comboItemList = new javax.swing.JComboBox();
        txtItemStock = new javax.swing.JTextField();
        lblExDate = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblIName = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        txtExDate = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        lblQty = new javax.swing.JLabel();
        comboItemBUnit = new javax.swing.JComboBox();
        lblBaseUnit = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Item Lot");
        setResizable(false);

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdd.setMnemonic('A');
        btnAdd.setText("Add");

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancel.setMnemonic('C');
        btnCancel.setText("Cancle");

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUpdate.setMnemonic('U');
        btnUpdate.setText("Update");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(btnUpdate)
                .addGap(18, 18, 18)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate))
                .addContainerGap())
        );

        tblPurchase.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblPurchase.getTableHeader().setReorderingAllowed(false);
        tblPurchase.setFillsViewportHeight(true);
        tblPurchase.setRowHeight(20);
        jScrollPane1.setViewportView(tblPurchase);

        txtBRate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblBRate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblBRate.setText("Buy Rate :*");

        txtThreshold.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Item Stock :");

        comboDistributor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboDistributor.setModel(new javax.swing.DefaultComboBoxModel());

        btnDistro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDistro.setText("Add Distributor");
        btnDistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDistroActionPerformed(evt);
            }
        });

        comboItemList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboItemList.setModel(new javax.swing.DefaultComboBoxModel());

        txtItemStock.setEditable(false);
        txtItemStock.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtItemStock.setText("0");

        lblExDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblExDate.setText("Expire Date :");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Threshold: *");

        lblIName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblIName.setText("Item Name :*");

        txtQty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQtyActionPerformed(evt);
            }
        });

        txtExDate.setDateFormatString("yyyy-MM-dd");
        txtExDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Distributor :*");

        lblQty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblQty.setText("Quantity :*");

        comboItemBUnit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboItemBUnit.setModel(new javax.swing.DefaultComboBoxModel());

        lblBaseUnit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblBaseUnit.setText("Base Unit :*");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblBRate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblExDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblIName, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtThreshold)
                            .addComponent(comboItemBUnit, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboItemList, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtItemStock, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBRate, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboDistributor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtExDate, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDistro)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblQty)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lblBaseUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboItemList, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBaseUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboItemBUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtItemStock, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblQty)
                        .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblExDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtExDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtThreshold)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboDistributor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDistro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtyActionPerformed

    private void btnDistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDistroActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ItemLotView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new ItemLotView().setVisible(true);
            }
        });
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDistro;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox comboDistributor;
    private javax.swing.JComboBox comboItemBUnit;
    private javax.swing.JComboBox comboItemList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBRate;
    private javax.swing.JLabel lblBaseUnit;
    private javax.swing.JLabel lblExDate;
    private javax.swing.JLabel lblIName;
    private javax.swing.JLabel lblQty;
    private javax.swing.JTable tblPurchase;
    private javax.swing.JTextField txtBRate;
    private com.toedter.calendar.JDateChooser txtExDate;
    private javax.swing.JTextField txtItemStock;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtThreshold;
    // End of variables declaration//GEN-END:variables
}
