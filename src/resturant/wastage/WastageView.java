/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package resturant.wastage;

import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.event.DocumentListener;
import reusableClass.Function;
import reusableClass.Validator;

/**
 *
 * @author SUSHIL
 */
public class WastageView extends javax.swing.JDialog {
    private int DepartmentID;
    private int MenuID;
    private int ItemID;
    private int UnitID;
    private int MenuStaffId;
    private int ItemStaffId;
    

    /**
     * Creates new form WastageView
     * @param parent
     * @param modal
     */
    public WastageView(JFrame parent,boolean modal) {
        super(parent,modal);
        initComponents();
        Validator.DecimalMaker(txtMenuQuantity);
        Validator.DecimalMaker(txtMenuAmount);
        Validator.DecimalMaker(txtItemAmount);
        Validator.DecimalMaker(txtItemQuantity);
        Validator.DecimalMaker(txtMenuRate);
        //ading focus listener
        txtMenuAmount.addFocusListener(new Function.SetJFormattedTextFieldFocusListener(txtMenuAmount));
        txtMenuQuantity.addFocusListener(new Function.SetJFormattedTextFieldFocusListener(txtMenuQuantity));
        txtMenuRate.addFocusListener(new Function.SetJFormattedTextFieldFocusListener(txtMenuRate));
        txtMenuReason.addFocusListener(new Function.SetTextFieldFocusListener(txtMenuReason));
        //adding focus listener for item
        txtItemAmount.addFocusListener(new Function.SetJFormattedTextFieldFocusListener(txtItemAmount));
        txtItemQuantity.addFocusListener(new Function.SetJFormattedTextFieldFocusListener(txtItemQuantity));
        txtItemReason.addFocusListener(new Function.SetTextFieldFocusListener(txtItemReason));
        //adding key to enter
        Function.setButtonForEnter(btnMenuSave);
        Function.setButtonForEnter(btnMenuCancel);
        Function.setButtonForEnter(btnItemSave);
        Function.setButtonForEnter(btnItemCancel);
    }

    public int getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(int DepartmentID) {
        this.DepartmentID = DepartmentID;
    }

    public int getMenuID() {
        return MenuID;
    }

    public void setMenuID(int MenuID) {
        this.MenuID = MenuID;
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int ItemID) {
        this.ItemID = ItemID;
    }

    public int getUnitID() {
        return UnitID;
    }

    public void setUnitID(int UnitID) {
        this.UnitID = UnitID;
    }

    public int getMenuStaffId() {
        return MenuStaffId;
    }

    public void setMenuStaffId(int MenuStaffId) {
        this.MenuStaffId = MenuStaffId;
    }

    public int getItemWaiterId() {
        return ItemStaffId;
    }

    public void setItemWaiterId(int ItemWaiterId) {
        this.ItemStaffId = ItemWaiterId;
    }

   public Double getMenuQuantity(){
       return ((Number)txtMenuQuantity.getValue()).doubleValue();
   }
   public void setMenuQuantity(Double d){
       txtMenuQuantity.setValue(new Double(d));
   }
   public Double getMenuRate(){
       return ((Number)txtMenuRate.getValue()).doubleValue();
   }
   public void setMenuRate(Double d){
       txtMenuRate.setValue(new Double(d));
   }
   public Double getMenuAmount(){
       return ((Number)txtMenuAmount.getValue()).doubleValue();
   }
   public void setMenuAmount(Double d){
       txtMenuAmount.setValue(new Double(d));
   }
   public void setMenuReason(String sr){
       txtMenuReason.setText(sr);
   }
   public String getMenuReason(){
       return txtMenuReason.getText();
   }
   public void setlblMenuStock(String st){
       lblMenuStock.setText(st);
   }
   public String getlblMenuStock(){
       return lblMenuStock.getText();
   }
   public void setlblItemStock(String st){
       lblItemStock.setText(st);
   }
   public String getlblItemStock(){
       return lblItemStock.getText();
   }
   
   public void setComboBoxDepartmentName(Object[] data){
       DefaultComboBoxModel model = new DefaultComboBoxModel(data);
       ComboBoxDepartmentName.setModel(model);
   }
   public void setComboBoxDepartmentName(String st){
       ComboBoxDepartmentName.setSelectedItem(st);
   }
   public String getComboBoxDepartmentName(){
       return ComboBoxDepartmentName.getSelectedItem().toString();
   }
   public JComboBox returnComboBoxDepartmentName(){
       return ComboBoxDepartmentName;
   }
    public void setComboBoxMenuMenuName(Object[] data){
       DefaultComboBoxModel model = new DefaultComboBoxModel(data);
       ComboBoxMenuMenuName.setModel(model);
   }
   public void setComboBoxMenuMenuName(String st){
       ComboBoxMenuMenuName.setSelectedItem(st);
   }
   public String getComboBoxMenuMenuName(){
       return ComboBoxMenuMenuName.getSelectedItem().toString();
   }
   public JComboBox returnComboBoxMenuMenuName(){
       return ComboBoxMenuMenuName;
   }
    public void setComboBoxMenuStaffName(Object[] data){
       DefaultComboBoxModel model = new DefaultComboBoxModel(data);
       ComboBoxMenuStaffName.setModel(model);
   }
   public void setComboBoxMenuStaffName(String st){
       ComboBoxMenuStaffName.setSelectedItem(st);
   }
   public String getComboBoxMenuStaffName(){
       return ComboBoxMenuStaffName.getSelectedItem().toString();
   }
   public JComboBox returnComboBoxMenuStaffName(){
       return ComboBoxMenuStaffName;
   }
   
   //for item getter and setter
    public Double getItemQuantity(){
       return ((Number)txtItemQuantity.getValue()).doubleValue();
   }
   public void setItemQuantity(Double d){
       txtItemQuantity.setValue(new Double(d));
   }
  
   public Double getItemAmount(){
       return ((Number)txtItemAmount.getValue()).doubleValue();
   }
   public void setItemAmount(Double d){
       txtItemAmount.setValue(new Double(d));
   }
   public void setItemReason(String sr){
       txtItemReason.setText(sr);
   }
   public String getItemReason(){
       return txtItemReason.getText();
   }
  
  
    public void setComboBoxItemName(Object[] data){
       DefaultComboBoxModel model = new DefaultComboBoxModel(data);
       ComboBoxItemName.setModel(model);
   }
   public void setComboBoxItemName(String st){
       ComboBoxItemName.setSelectedItem(st);
   }
   public String getComboBoxItemName(){
       return ComboBoxItemName.getSelectedItem().toString();
   }
   public JComboBox returnComboBoxItemName(){
       return ComboBoxItemName;
   }
    public void setComboBoxItemStaffName(Object[] data){
       DefaultComboBoxModel model = new DefaultComboBoxModel(data);
       ComboBoxItemStaffName.setModel(model);
   }
   public void setComboBoxItemStaffName(String st){
       ComboBoxItemStaffName.setSelectedItem(st);
   }
   public String getComboBoxItemStaffName(){
       return ComboBoxItemStaffName.getSelectedItem().toString();
   }
   public JComboBox returnComboBoxItemStaffName(){
       return ComboBoxItemStaffName;
   }
   public void setComboBoxItemBaseUnit(Object[] data){
       DefaultComboBoxModel model = new DefaultComboBoxModel(data);
       ComboBoxItemBaseUnit.setModel(model);
   }
   public void setComboBoxItemBaseUnit(String st){
       ComboBoxItemBaseUnit.setSelectedItem(st);
   }
   public String getComboBoxItemBaseUnit(){
       return ComboBoxItemBaseUnit.getSelectedItem().toString();
   }
   public JComboBox returnComboBoxItemBaseUnit(){
       return ComboBoxItemBaseUnit;
   }
   //adding delegate for menu
   public void addMenuSaveListener(ActionListener Listen){
       btnMenuSave.addActionListener(Listen);
   }
   public void addMenuCancelListener(ActionListener LIsten){
       btnMenuCancel.addActionListener(LIsten);
   }
   public void addDepartmentListener(ActionListener Listen){
       ComboBoxDepartmentName.addActionListener(Listen);
   }
   public void addMenuNameListener(ActionListener Listen){
       ComboBoxMenuMenuName.addActionListener(Listen);
   }
   public void addMenuStaffNameListener(ActionListener Listen){
       ComboBoxMenuStaffName.addActionListener(Listen);
   }
   
   //adding delegate for item
   public void addItemSaveListener(ActionListener Listen){
       btnItemSave.addActionListener(Listen);
   }
   public void addItemCancelListener(ActionListener LIsten){
       btnItemCancel.addActionListener(LIsten);
   }
    public void addItemNameListener(ActionListener Listen){
       ComboBoxItemName.addActionListener(Listen);
   }
   public void addItemStaffNameListener(ActionListener Listen){
       ComboBoxItemStaffName.addActionListener(Listen);
   }
   public void addItemBaseUnitListener(ActionListener Listen){
       ComboBoxItemBaseUnit.addActionListener(Listen);
   }
   public void addMenuQuantityDocumentListener(DocumentListener Listen){
       txtMenuQuantity.getDocument().addDocumentListener(Listen);
   }
   public void clearAllMenu(){
       setMenuQuantity(0.0);
       setMenuRate(0.0);
       setMenuAmount(0.0);
       setMenuReason("");
       setlblMenuStock("");
       ComboBoxMenuMenuName.setSelectedIndex(0);
       ComboBoxMenuStaffName.setSelectedIndex(0);
   }
   public Object[] getAllMenu(){
       Object[] obj = new Object[8];
       obj[0] = getMenuID();
       obj[1] = getComboBoxMenuMenuName();
       obj[2] = getMenuQuantity();
       obj[3] = getMenuRate();
       obj[4] = getMenuAmount();
       obj[5] = getMenuReason();
       obj[6] = getMenuStaffId();
       obj[7] = getComboBoxMenuStaffName();
       return obj;
   }
//for item 
   public void clearAllItem(){
       setItemQuantity(0.0);
       setItemAmount(0.0);
       setItemReason("");
       setlblItemStock("");
       ComboBoxItemName.setSelectedIndex(0);
       ComboBoxItemBaseUnit.setSelectedIndex(0);
       ComboBoxItemStaffName.setSelectedIndex(0);
   }
   public Object[] getAllItem(){
       Object[] obj = new Object[9];
       obj[0] = getItemID();
       obj[1] = getComboBoxItemName();
       obj[2] = getItemQuantity();
       obj[3] = getItemAmount();
       obj[4] = getItemReason();
       obj[5] = getItemWaiterId();
       obj[6] = getComboBoxItemStaffName();
       obj[7] = getUnitID();
       obj[8] = getComboBoxItemBaseUnit();
       return obj;
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ComboBoxMenuMenuName = new javax.swing.JComboBox();
        ComboBoxDepartmentName = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtMenuQuantity = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMenuReason = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ComboBoxMenuStaffName = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtMenuRate = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMenuAmount = new javax.swing.JFormattedTextField();
        btnMenuSave = new javax.swing.JButton();
        btnMenuCancel = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblMenuStock = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        ComboBoxItemName = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtItemQuantity = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        txtItemAmount = new javax.swing.JFormattedTextField();
        txtItemReason = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ComboBoxItemStaffName = new javax.swing.JComboBox();
        btnItemSave = new javax.swing.JButton();
        btnItemCancel = new javax.swing.JButton();
        ComboBoxItemBaseUnit = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblItemStock = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Menu Name:");

        ComboBoxMenuMenuName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        ComboBoxDepartmentName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Quantity:");

        txtMenuQuantity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Reason:");

        txtMenuReason.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Assign:");

        ComboBoxMenuStaffName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Rate:");

        txtMenuRate.setEnabled(false);
        txtMenuRate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Amount:");

        txtMenuAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnMenuSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnMenuSave.setMnemonic('s');
        btnMenuSave.setText("Save");
        btnMenuSave.setToolTipText("ALT+S");
        btnMenuSave.setActionCommand("MenuSave");

        btnMenuCancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnMenuCancel.setMnemonic('C');
        btnMenuCancel.setText("Cancel");
        btnMenuCancel.setToolTipText("ALT+C");
        btnMenuCancel.setActionCommand("MenuCancel");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Department Name:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Stock:");

        lblMenuStock.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtMenuQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtMenuRate, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ComboBoxDepartmentName, 0, 135, Short.MAX_VALUE)
                                .addComponent(ComboBoxMenuMenuName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMenuAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(lblMenuStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(txtMenuReason, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxMenuStaffName, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(btnMenuSave, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMenuCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel13});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxDepartmentName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxMenuMenuName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(lblMenuStock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMenuQuantity)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMenuRate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMenuAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMenuReason, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxMenuStaffName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMenuSave, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMenuCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ComboBoxDepartmentName, ComboBoxMenuMenuName});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel13});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtMenuQuantity, txtMenuRate});

        jTabbedPane1.addTab("Menu Wastage", jPanel1);

        ComboBoxItemName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Item Name:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Quantity:");

        txtItemQuantity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setText("Amount:");

        txtItemReason.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Reason:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Assign:");

        ComboBoxItemStaffName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnItemSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnItemSave.setMnemonic('S');
        btnItemSave.setText("Save");
        btnItemSave.setToolTipText("ALT+S");
        btnItemSave.setActionCommand("ItemSave");

        btnItemCancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnItemCancel.setMnemonic('c');
        btnItemCancel.setText("Cancel");
        btnItemCancel.setToolTipText("ALT+C");
        btnItemCancel.setActionCommand("ItemCancel");

        ComboBoxItemBaseUnit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Unit:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Stock:");

        lblItemStock.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtItemReason, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ComboBoxItemStaffName, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtItemAmount, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ComboBoxItemName, javax.swing.GroupLayout.Alignment.LEADING, 0, 100, Short.MAX_VALUE)
                                            .addComponent(txtItemQuantity, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel15))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ComboBoxItemBaseUnit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblItemStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(btnItemSave, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 227, Short.MAX_VALUE)
                                .addComponent(btnItemCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(67, 67, 67))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(lblItemStock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtItemQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxItemBaseUnit)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtItemAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtItemReason, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxItemStaffName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnItemSave, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnItemCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );

        jTabbedPane1.addTab("Item Wastage", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboBoxDepartmentName;
    private javax.swing.JComboBox ComboBoxItemBaseUnit;
    private javax.swing.JComboBox ComboBoxItemName;
    private javax.swing.JComboBox ComboBoxItemStaffName;
    private javax.swing.JComboBox ComboBoxMenuMenuName;
    private javax.swing.JComboBox ComboBoxMenuStaffName;
    private javax.swing.JButton btnItemCancel;
    private javax.swing.JButton btnItemSave;
    private javax.swing.JButton btnMenuCancel;
    private javax.swing.JButton btnMenuSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblItemStock;
    private javax.swing.JLabel lblMenuStock;
    private javax.swing.JFormattedTextField txtItemAmount;
    private javax.swing.JFormattedTextField txtItemQuantity;
    private javax.swing.JTextField txtItemReason;
    private javax.swing.JFormattedTextField txtMenuAmount;
    private javax.swing.JFormattedTextField txtMenuQuantity;
    private javax.swing.JFormattedTextField txtMenuRate;
    private javax.swing.JTextField txtMenuReason;
    // End of variables declaration//GEN-END:variables
}
