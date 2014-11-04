/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.order;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import reusableClass.Function;
import reusableClass.Validator;

/**
 *
 * @author SUSHIL
 */
public  class OrderView extends javax.swing.JInternalFrame {
private int  TableId = 0;
private int MainOrderId = 0;
private int CustomMenuId = 0;
private int MenuId;
private int WaiterId;
private int CustomerId;
private int UnitId ;
private int DepartmentId=0;
private int ComplimentaryId = 0;
private String ItemBaseUnit = new String();
private Double DoubleSVC = 0.0;
private Double DoubleVAT = 0.0;


private BigDecimal rate;
private BigDecimal TotalAmount;
private BigDecimal NetAmount;
private BigDecimal ComplimentaryAmount;
private final ListSelectionModel selectionModelOrderTable;
private final ListSelectionModel selectionModelOrderedTable;
//this for listening the  keyboard key throug out the frame
private final KeyboardFocusManager kfmanager;
private final JComboBox comboColumnTableName = new JComboBox();

    /**
     * Creates new formOrderView
     */
    public OrderView() {
       super("Order Window",false,true,false,true);
        initComponents();
        
        selectionModelOrderTable = tblOrderList.getSelectionModel();
        selectionModelOrderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModelOrderedTable = tblOrderedList.getSelectionModel();
        selectionModelOrderedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setButtonForEnter(btnAdd);
        setButtonForEnter(btnDelete);
        setButtonForEnter(btnSearchMenu);
        setButtonForEnter(btnSearchOrder);
        setButtonForEnter(btnOrder);
        setButtonForEnter(btnOrderAndPrint);
        setButtonForEnter(btnOrderEdit);
        setButtonForEnter(btnOrderDelete);
        setButtonForEnter(btnRefresh);
        setButtonForEnter(btnCancelOrder);
        setButtonForEnter(btnAddCustomer);
        setButtonForEnter(btnAddTable);
        setButtonForEnter(btnAddWaiter);
        //for having combotype cell in orderedtable
        
         /*
          * setiing the quantity with jformatted text field
          */
         /*
         checking for enter
         */
         
         
         
         Validator.DecimalMaker(txtOrderQuantity);
         Validator.DecimalMaker(txtCustomQuantity);
         Validator.DecimalMaker(txtCustomRate);
         Validator.DecimalMaker(txtCustomTotalAmount);
         txtCustomMenuName.addFocusListener(new Function.SetTextFieldFocusListener(txtCustomMenuName));
         txtCustomRate.addFocusListener(new Function.SetJFormattedTextFieldFocusListener(txtCustomRate));
         txtCustomTotalAmount.addFocusListener(new Function.SetJFormattedTextFieldFocusListener(txtCustomTotalAmount));
         txtCustomQuantity.addFocusListener(new Function.SetJFormattedTextFieldFocusListener(txtCustomQuantity));
         txtOrderQuantity.addFocusListener(new Function.SetJFormattedTextFieldFocusListener(txtOrderQuantity));
         txtSearch.addFocusListener(new Function.SetTextFieldFocusListener(txtSearch));
         txtSearchMenu.addFocusListener(new Function.SetTextFieldFocusListener(txtSearchMenu));
         //getting the key acion
         kfmanager= KeyboardFocusManager.getCurrentKeyboardFocusManager();
         TotalAmount = BigDecimal.ZERO;
         TotalAmount.setScale(2, RoundingMode.HALF_EVEN);
         ComplimentaryAmount = BigDecimal.ZERO;
         ComplimentaryAmount.setScale(2,RoundingMode.HALF_EVEN);
         NetAmount = BigDecimal.ZERO;
         NetAmount.setScale(2, RoundingMode.HALF_EVEN);
        
         
//          setLocationRelativeTo(null);
       /*  DialogBox.addWindowListener(new WindowAdapter(){
             public void windowClosed(WindowEvent se){
                 JOptionPane.showMessageDialog(null, "wala closing");
             }
         });*/
         
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SearchDailog = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        tblSearch = new javax.swing.JTable();
        dialogComplimentary = new javax.swing.JDialog();
        comboComplimentary = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnInsertNewComplimentary = new javax.swing.JButton();
        btnComplimentarySave = new javax.swing.JButton();
        CustomMenuAddDialog = new javax.swing.JDialog();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtCustomMenuName = new javax.swing.JTextField();
        btnCustomMenuRealAdd = new javax.swing.JButton();
        btnCustomMenuRealCancel = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtCustomRate = new javax.swing.JFormattedTextField();
        txtCustomQuantity = new javax.swing.JFormattedTextField();
        txtCustomTotalAmount = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        comboCategoryName = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblOrderId = new javax.swing.JLabel();
        btnAddTable = new javax.swing.JButton();
        comboTableName = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        comboCustomerName = new javax.swing.JComboBox();
        btnAddCustomer = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        comboWaiterName = new javax.swing.JComboBox();
        btnAddWaiter = new javax.swing.JButton();
        btnOrder = new javax.swing.JButton();
        btnOrderAndPrint = new javax.swing.JButton();
        btnOrderEdit = new javax.swing.JButton();
        btnOrderDelete = new javax.swing.JButton();
        btnCancelOrder = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOrderedList = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        btnSearchOrder = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrderList = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        comboMenuName = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtOrderQuantity = new javax.swing.JFormattedTextField();
        btnAdd = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtSearchMenu = new javax.swing.JTextField();
        btnSearchMenu = new javax.swing.JButton();
        btnCustomMenuAdd = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        lblTotalAmount = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblComplimentaryAmount = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblNetAmount = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        comboDepartmentName = new javax.swing.JComboBox();

        SearchDailog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        SearchDailog.setTitle("Search Result");
        SearchDailog.setMinimumSize(new java.awt.Dimension(611, 437));
        SearchDailog.setModal(true);
        SearchDailog.setName("Search Result"); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Result"));

        scroll.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tblSearch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scroll.setViewportView(tblSearch);
        tblSearch.getTableHeader().setReorderingAllowed(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout SearchDailogLayout = new javax.swing.GroupLayout(SearchDailog.getContentPane());
        SearchDailog.getContentPane().setLayout(SearchDailogLayout);
        SearchDailogLayout.setHorizontalGroup(
            SearchDailogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchDailogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        SearchDailogLayout.setVerticalGroup(
            SearchDailogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchDailogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        dialogComplimentary.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        dialogComplimentary.setTitle("Complimentary Reason");

        comboComplimentary.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboComplimentary.setActionCommand("comboComplimentary");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Select ComplimentaryReason:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("or Set new Complimentary Reason:");

        btnInsertNewComplimentary.setText("New Complimentary");
        btnInsertNewComplimentary.setActionCommand("NewComplimentary");

        btnComplimentarySave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnComplimentarySave.setText("Save");
        btnComplimentarySave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComplimentarySaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dialogComplimentaryLayout = new javax.swing.GroupLayout(dialogComplimentary.getContentPane());
        dialogComplimentary.getContentPane().setLayout(dialogComplimentaryLayout);
        dialogComplimentaryLayout.setHorizontalGroup(
            dialogComplimentaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogComplimentaryLayout.createSequentialGroup()
                .addGroup(dialogComplimentaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogComplimentaryLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboComplimentary, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dialogComplimentaryLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnInsertNewComplimentary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(dialogComplimentaryLayout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(btnComplimentarySave)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dialogComplimentaryLayout.setVerticalGroup(
            dialogComplimentaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogComplimentaryLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(dialogComplimentaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboComplimentary, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(dialogComplimentaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(btnInsertNewComplimentary))
                .addGap(18, 18, 18)
                .addComponent(btnComplimentarySave)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        CustomMenuAddDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        CustomMenuAddDialog.setTitle("Custom Menu Add");
        CustomMenuAddDialog.setResizable(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Menu Name:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Rate:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Quantity:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Total Amount:");

        btnCustomMenuRealAdd.setText("Add");
        btnCustomMenuRealAdd.setActionCommand("CustomAdd");

        btnCustomMenuRealCancel.setText("Cancel");
        btnCustomMenuRealCancel.setActionCommand("CustomCancel");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Custom Menu Add");

        txtCustomTotalAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomTotalAmountActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Category:");

        javax.swing.GroupLayout CustomMenuAddDialogLayout = new javax.swing.GroupLayout(CustomMenuAddDialog.getContentPane());
        CustomMenuAddDialog.getContentPane().setLayout(CustomMenuAddDialogLayout);
        CustomMenuAddDialogLayout.setHorizontalGroup(
            CustomMenuAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomMenuAddDialogLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(btnCustomMenuRealAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(btnCustomMenuRealCancel)
                .addGap(100, 100, 100))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CustomMenuAddDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(CustomMenuAddDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CustomMenuAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CustomMenuAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCustomMenuName, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(txtCustomRate)
                    .addComponent(txtCustomQuantity)
                    .addComponent(txtCustomTotalAmount)
                    .addComponent(comboCategoryName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CustomMenuAddDialogLayout.setVerticalGroup(
            CustomMenuAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomMenuAddDialogLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel14)
                .addGap(20, 20, 20)
                .addGroup(CustomMenuAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtCustomMenuName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CustomMenuAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(comboCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(CustomMenuAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCustomRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CustomMenuAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(txtCustomQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CustomMenuAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtCustomTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CustomMenuAddDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCustomMenuRealAdd)
                    .addComponent(btnCustomMenuRealCancel))
                .addGap(24, 24, 24))
        );

        setNextFocusableComponent(comboMenuName);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Item Add"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Order Id:");

        lblOrderId.setBackground(new java.awt.Color(153, 255, 102));
        lblOrderId.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblOrderId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        lblOrderId.setOpaque(true);

        btnAddTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAddTable.setMnemonic('t');
        btnAddTable.setText("Add Table");
        btnAddTable.setActionCommand("AddTable");

        comboTableName.setActionCommand("ComboTableName");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Table Name:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOrderId, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboTableName, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddTable)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnAddTable, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(comboTableName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblOrderId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Order Table"));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Customer Name:");

        comboCustomerName.setActionCommand("ComboCustomerName");

        btnAddCustomer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAddCustomer.setMnemonic('u');
        btnAddCustomer.setText("ADD Customer");
        btnAddCustomer.setActionCommand("ADDCustomer");
        btnAddCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCustomerActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Waiter Name:");

        comboWaiterName.setActionCommand("ComboWaiterName");

        btnAddWaiter.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAddWaiter.setMnemonic('w');
        btnAddWaiter.setText("ADD Waiter");
        btnAddWaiter.setActionCommand("ADDWaiter");
        btnAddWaiter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddWaiterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboWaiterName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAddWaiter, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(btnAddWaiter, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(comboWaiterName)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {comboCustomerName, comboWaiterName});

        btnOrder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnOrder.setMnemonic('o');
        btnOrder.setText("Order");
        btnOrder.setEnabled(false);

        btnOrderAndPrint.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnOrderAndPrint.setMnemonic('p');
        btnOrderAndPrint.setText("Order&Print");
        btnOrderAndPrint.setEnabled(false);
        btnOrderAndPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderAndPrintActionPerformed(evt);
            }
        });

        btnOrderEdit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnOrderEdit.setMnemonic('e');
        btnOrderEdit.setText("Edit");
        btnOrderEdit.setActionCommand("OrderEdit");
        btnOrderEdit.setEnabled(false);
        btnOrderEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderEditActionPerformed(evt);
            }
        });

        btnOrderDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnOrderDelete.setMnemonic('d');
        btnOrderDelete.setText("Delete");
        btnOrderDelete.setActionCommand("OrderDelete");
        btnOrderDelete.setEnabled(false);
        btnOrderDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderDeleteActionPerformed(evt);
            }
        });

        btnCancelOrder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancelOrder.setMnemonic('c');
        btnCancelOrder.setText("Cancel");
        btnCancelOrder.setActionCommand("OrderCancel");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Ordered  Table List"));

        tblOrderedList.setAutoCreateRowSorter(true);
        tblOrderedList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblOrderedList.getTableHeader().setReorderingAllowed(false);
        tblOrderedList.setRowHeight(20);
        jScrollPane2.setViewportView(tblOrderedList);

        txtSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnSearchOrder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSearchOrder.setMnemonic('h');
        btnSearchOrder.setText("Search");
        btnSearchOrder.setActionCommand("OrderSearch");

        btnRefresh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRefresh.setMnemonic('r');
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearchOrder)
                        .addGap(42, 42, 42)
                        .addComponent(btnRefresh)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Order List"));

        tblOrderList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblOrderList.setRowHeight(20);
        tblOrderList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblOrderList);

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDelete.setMnemonic('l');
        btnDelete.setText("Delete");
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        comboMenuName.setActionCommand("ComboMenuName");
        comboMenuName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMenuNameActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Menu Name:*");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Qty:*");

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdd.setMnemonic('a');
        btnAdd.setText("Add");
        btnAdd.setMinimumSize(new java.awt.Dimension(87, 25));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 255));
        jLabel7.setText("Search for the Menu to Add");

        txtSearchMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSearchMenu.setHighlighter(null);

        btnSearchMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSearchMenu.setMnemonic('s');
        btnSearchMenu.setText("Search");
        btnSearchMenu.setActionCommand("SearchMenu");

        btnCustomMenuAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCustomMenuAdd.setText("Custom Menu Add");
        btnCustomMenuAdd.setActionCommand("CustomMenuAdd");

        jLabel17.setText("Total Amount:");

        lblTotalAmount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel18.setText("Comp* Amount:");

        lblComplimentaryAmount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel20.setText("Net Amount:");

        lblNetAmount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSearchMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboMenuName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSearchMenu)
                            .addComponent(txtOrderQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCustomMenuAdd))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblComplimentaryAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNetAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(btnCustomMenuAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMenuName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOrderQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(lblTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(lblComplimentaryAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(lblNetAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdd, btnSearchMenu});

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Department Name:");

        comboDepartmentName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboDepartmentName.setActionCommand("ComboDepartmentName");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnOrderAndPrint)
                                .addGap(18, 18, 18)
                                .addComponent(btnOrderEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnOrderDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboDepartmentName, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnOrder)
                            .addComponent(btnOrderAndPrint)
                            .addComponent(btnOrderEdit)
                            .addComponent(btnOrderDelete)
                            .addComponent(btnCancelOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboDepartmentName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancelOrder, btnOrder, btnOrderAndPrint, btnOrderDelete, btnOrderEdit});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboMenuNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMenuNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboMenuNameActionPerformed

    private void btnAddWaiterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddWaiterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddWaiterActionPerformed

    private void btnOrderEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOrderEditActionPerformed

    private void btnOrderAndPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderAndPrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOrderAndPrintActionPerformed

    private void btnOrderDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOrderDeleteActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnAddCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddCustomerActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtCustomTotalAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomTotalAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomTotalAmountActionPerformed

    private void btnComplimentarySaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComplimentarySaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnComplimentarySaveActionPerformed
    public String getOrderId(){
        return lblOrderId.getText().trim();
    }
    public void setOrderId(String id){
        lblOrderId.setText(id);
    }
     public int getMainOrderId(){
        return MainOrderId;
    }
    public void setMainOrderId(int id){
       MainOrderId = id;
    }
    public BigDecimal getRate(){
        return rate;
    }
    public void setRate(String rate){
        this.rate = new BigDecimal(rate).setScale(2, RoundingMode.HALF_UP);
    }
    public  BigDecimal getTotalAmount(){
        return TotalAmount;
    }
    public void setTotalAmount(BigDecimal  amount){
       lblTotalAmount.setText(amount.toString());
       TotalAmount = amount;
    }

    public BigDecimal getNetAmount() {
        return NetAmount;
    }

    public void setNetAmount(BigDecimal NetAmount) {
        lblNetAmount.setText(NetAmount.toString());
        this.NetAmount = NetAmount;
    }

    public BigDecimal getComplimentaryAmount() {
        return ComplimentaryAmount;
    }

    public void setComplimentaryAmount(BigDecimal ComplimentaryAmount) {
        lblComplimentaryAmount.setText(ComplimentaryAmount.toString());
        this.ComplimentaryAmount = ComplimentaryAmount;
    }
    
    public void setTableId(int id){
        TableId = id;
    }
    public int getTableId(){
        return TableId;
    }
    public void setCustomerId(int id){
        CustomerId = id;
    }
    public int getCustomerId(){
        return CustomerId;
    }
    public int getMenuId(){
        return MenuId;
    }
    public void setMenuId(int id){
        MenuId = id;
    }
     public int getUnitId(){
        return UnitId;
    }
    public void setUnitId(int id){
        UnitId = id;
    }
      public int getDepartmentId(){
        return DepartmentId;
    }
    public void setDepartmentId(int id){
        DepartmentId = id;
    }
    public void setItemBaseUnit(String  item){
        ItemBaseUnit = item;
    }
    public String getItemBaseUnit(){
        return ItemBaseUnit;
    }
    public void setWaiterId(int id){
        WaiterId = id;
    }
    public int getWaiterId(){
        return WaiterId;
    }

    public int getComplimentaryId() {
        return ComplimentaryId;
    }

    public void setComplimentaryId(int ComplimentaryId) {
        this.ComplimentaryId = ComplimentaryId;
    }
    
    public void setCustomMenuname(String menuname){
        txtCustomMenuName.setText(menuname);
    }
    public String getCustomMenuName(){
        return txtCustomMenuName.getText().trim();
    }
    public void setCustomRate(double rate){
        txtCustomRate.setValue(rate);
    }
    public Double getCustomRate(){
        return ((Number)txtCustomRate.getValue()).doubleValue();
    }
    public void setCustomTotalAmount(double amount){
        txtCustomTotalAmount.setValue(amount);
    }
    public Double getCustomTotalAmount(){
        return ((Number)txtCustomTotalAmount.getValue()).doubleValue();
    }
    public void setCustomQuantity(double qty){
        txtCustomQuantity.setValue(qty);
    }
    public Double getCustomQuantity(){
        return ((Number)txtCustomQuantity.getValue()).doubleValue();
    }
    public void setCustomMenuId(int id){
        CustomMenuId = id;
    }
    public int getCustomMenuId(){
        return CustomMenuId;
    }
    
    public void setFocusOnButtonOrder(){
        btnOrder.requestFocus();
    }
     public void setFocusOnButtonEdit(){
        btnOrderEdit.requestFocus();
    }
    public void setFocusOntxtOrderQuantity(){
        txtOrderQuantity.requestFocusInWindow();
    }
    public void setFocusOnAddOrder(){
        btnAdd.requestFocusInWindow();
    }
    public void  setComboTableName(String name){
        comboTableName.setSelectedItem(name);
    }
    public void setComboTableName(String[] name){
        DefaultComboBoxModel TableNameModel = new DefaultComboBoxModel(name);
        comboTableName.setModel(TableNameModel);
    }
    public String getComboTableName(){
        return comboTableName.getSelectedItem().toString();
    }
    public void setComboMenuName(String[] name){
        DefaultComboBoxModel MenuNameModel = new DefaultComboBoxModel(name);
        comboMenuName.setModel(MenuNameModel);
    }
    public void setComboMenuName(String name){
        comboMenuName.setSelectedItem(name);
    }
    public String getComboMenuName(){
         return comboMenuName.getSelectedItem().toString();
    }
    public void setComboWaiterName(String[] name){
        DefaultComboBoxModel WaiterNameModel = new DefaultComboBoxModel(name);
        comboWaiterName.setModel(WaiterNameModel);
    }
    public void setComboWaiterName(String name){
        comboWaiterName.setSelectedItem(name);
    }
    public String setComboWaiterName(){
        return comboWaiterName.getSelectedItem().toString();
    }
    public void setComboCustomerName(String[] name){
        DefaultComboBoxModel CustomerNameModel = new DefaultComboBoxModel(name);
        comboCustomerName.setModel(CustomerNameModel);
    }
    public void setComboCustomerName(String name){
        comboCustomerName.setSelectedItem(name);
    }
    public String getComboCustomerName(){
        return comboCustomerName.getSelectedItem().toString();
    }
    //for combocolumnTableName
    public void setComboColumnTableName(String[] tablename){
        DefaultComboBoxModel model = new DefaultComboBoxModel(tablename);
        comboColumnTableName.setModel(model);
    }
    public void setComboColumnTableName(String name){
        comboColumnTableName.setSelectedItem(name);
    }
    public String getComboColumnTableName(){
        return comboColumnTableName.getSelectedItem().toString();
    }
    //for complimentary combo box
    public void  setComboComplimentary(String name){
        comboComplimentary.setSelectedItem(name);
    }
    public void setComboComplimentary(String[] name){
        DefaultComboBoxModel TableNameModel = new DefaultComboBoxModel(name);
        comboComplimentary.setModel(TableNameModel);
    }
    public String getComboComplimentary(){
        return comboComplimentary.getSelectedItem().toString();
    }
    public void setQuantity(Double st){
        txtOrderQuantity.setValue(st);
    }
    public Double getQuantity(){
        return ((Number)txtOrderQuantity.getValue()).doubleValue();
    }
    public void setSearch(String search){
        txtSearch.setText(search);
    }
    public String getSearch(){
         return txtSearch.getText().trim();
    }
    
    public void setSearchMenu(String src){
        txtSearchMenu.setText(src);
    }
    public String getSearchMenu(){
        return txtSearchMenu.getText().trim();
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
     public void setComboCategoryName(String[] name){
        DefaultComboBoxModel MenuNameModel = new DefaultComboBoxModel(name);
        comboCategoryName.setModel(MenuNameModel);
    }
    public void setComboCategoryName(String name){
        comboCategoryName.setSelectedItem(name);
    }
    public String getComboCategoryName(){
         return comboCategoryName.getSelectedItem().toString();
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
     
     
     public void refreshOrderListJTable(DefaultTableModel model){
         tblOrderList.setModel(model);
     }
     public void refreshOrderedListJTable(DefaultTableModel model){
         tblOrderedList.setModel(model);
     }
      public void refreshSearchMenuJTable(DefaultTableModel model){
         tblSearch.setModel(model);
     }

    //return all the txtfield
   

    public JFormattedTextField getTxtOrderQuantity() {
        return txtOrderQuantity;
    }

    public JTextField getTxtSearch() {
        return txtSearch;
    }

    public JTextField getTxtSearchMenu() {
        return txtSearchMenu;
    }
//return combocomplimentary
    public JComboBox returnComboComplimentary(){
        return comboComplimentary;
    }
    public JComboBox returnComboCategory(){
        return comboCategoryName;
    }
    
   
    
    
      
     public void addSearchListener(ActionListener ListenForSearch){
         btnSearchOrder.addActionListener(ListenForSearch);
     }
      public void addSearchMenuListener(ActionListener ListenForSearch){
         btnSearchMenu.addActionListener(ListenForSearch);
     }
     public void addAddTableListener(ActionListener ListenForAddTable){
         btnAddTable.addActionListener(ListenForAddTable);
     }
     public void addAddCustomerListener(ActionListener ListenForAddCustomer){
         btnAddCustomer.addActionListener(ListenForAddCustomer);
     }
     public void addAddWaiterListener(ActionListener ListenForAddWaiter){
         btnAddWaiter.addActionListener(ListenForAddWaiter);
     }
    
     public void addAdd(ActionListener ListenForAddOrder){
         btnAdd.addActionListener(ListenForAddOrder);
     }
     public void addTextAdd(ActionListener ListenForAddOrder){
         txtOrderQuantity.addActionListener(ListenForAddOrder);
     }
     public void addDoubleClickListenerForTableSearch(MouseAdapter ListenForDoubleClick){
         tblSearch.addMouseListener(ListenForDoubleClick);
     }
       public void addDoubleClickListenerForOrderedList(MouseAdapter ListenForDoubleClick){
         tblOrderedList.addMouseListener(ListenForDoubleClick);
     }
    /* public void addEditOrder(ActionListener ListenForEditOrder){
         btnEditOrder.addActionListener(ListenForEditOrder);
     }*/
     public void addDelete(ActionListener ListenForDeleteOrder){
         btnDelete.addActionListener(ListenForDeleteOrder);
     }
     /*
      * order function
      */
     public void addAddOrder(ActionListener ListenForOrder){
         btnOrder.addActionListener(ListenForOrder);
     }
     public void addAddOrderAndPrint(ActionListener ListenForOrder){
         btnOrderAndPrint.addActionListener(ListenForOrder);
     }
      public void addEditOrder(ActionListener ListenForOrder){
         btnOrderEdit.addActionListener(ListenForOrder);
     }
      public void addDeleteOrder(ActionListener ListenForDelete){
          btnOrderDelete.addActionListener(ListenForDelete);
      }
     public void addCancelOrder(ActionListener ListenForCancel){
         btnCancelOrder.addActionListener(ListenForCancel);
     }
     public void addComboTableNameListener(ActionListener ListenForTableClick){
         comboTableName.addActionListener(ListenForTableClick);
     }
     public void addComboCustomerNameListener(ActionListener ListenForCustomerClick){
         comboCustomerName.addActionListener(ListenForCustomerClick);
     }
     public void addComboWaiterNameListener(ActionListener ListenForWaiterClick){
         comboWaiterName.addActionListener(ListenForWaiterClick);
     }
     public void addComboMenuNameListener(ActionListener ListenForMenuClick){
         comboMenuName.addActionListener(ListenForMenuClick);
     }
      public void addComboMenuNameItemListener(ItemListener ListenForMenuClick){
         comboMenuName.addItemListener(ListenForMenuClick);
     }
      public void addComboDepartmentListener(ActionListener Listen){
          comboDepartmentName.addActionListener(Listen);
      }
     public void addComboComplimentary(ActionListener action){
         comboComplimentary.addActionListener(action);
     }
     public void addComboCategory(ActionListener action){
         comboCategoryName.addActionListener(action);
     }
     public void addOrderTableListSelection(ListSelectionListener ListenForList){
         selectionModelOrderTable.addListSelectionListener(ListenForList);
     }
     public void addOrderedTableListSelection(ListSelectionListener ListenForOdered){
         selectionModelOrderedTable.addListSelectionListener(ListenForOdered);
     }
     public void addRefreshOrderedListListener(ActionListener ListenForRefresh){
         btnRefresh.addActionListener(ListenForRefresh);
     }
     //adding delaget for listening fore keyboard shortcut through out the frame
     public void addShortcutForOrder(KeyEventDispatcher Listen){
         kfmanager.addKeyEventDispatcher(Listen);
     }
     //adding delegate for the add custom menu
     public void addCustomMenuAddListenerListener(ActionListener Action){
         btnCustomMenuAdd.addActionListener(Action);
     }
     public void addCustomMenuRealAddListener(ActionListener action){
         btnCustomMenuRealAdd.addActionListener(action);
     }
     public void addCustomMenuRealCancelListener(ActionListener action){
         btnCustomMenuRealCancel.addActionListener(action);
     }
     public void addCustomQuantityDocumentListener(DocumentListener Listen){
         txtCustomQuantity.getDocument().addDocumentListener(Listen);
     }
     /*
     for new complimentary
     */
     public void addNewComplimentary(ActionListener action){
         btnInsertNewComplimentary.addActionListener(action);
     }
     public void addComplimentarySave(ActionListener action){
         btnComplimentarySave.addActionListener(action);
     }
    
     /*
      * window listenr for close action in add waiter and customenr 
      */
     public void addCloseButtonListener(WindowListener ListenForClose){
         dialogComplimentary.addWindowListener(ListenForClose);
     }
     /*
     adding enter listener in text field
     */
     public void addAbstractActionListener(AbstractAction action){
         //adding action for txtorderquantity
         txtOrderQuantity.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"check");
         txtOrderQuantity.getActionMap().put("check", action);
         //adding action for txtsearchmenu
         txtSearchMenu.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "ClickSearchMenu");
         txtSearchMenu.getActionMap().put("ClickSearchMenu",action);
         //addin action fo txt search
         txtSearch.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "ClickSearch");
         txtSearch.getActionMap().put("ClickSearch", action);
         //adding action for combomenu search box
         comboMenuName.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "ComboMenuName");
         comboMenuName.getActionMap().put("ComboMenuName", action);
     }
     public void addSearchDocumentListener(DocumentListener Listen){
         txtSearch.getDocument().addDocumentListener(Listen);
     }
     public void addTblOrderListTableModelListener(TableModelListener tme){
         tblOrderList.getModel().addTableModelListener(tme);
     }
     public void setAddEditableTrue(){
         btnAdd.setEnabled(true);
     }
     public void setAddEditableFalse(){
         btnAdd.setEnabled(false);
     }
    /* public void setEditOrderEditableFalse(){
         btnEditOrder.setEnabled(false);
     }
     public void setEditOrderEditableTrue(){
         btnEditOrder.setEnabled(true);
     }*/
     public void setDeleteEditableTrue(){
         btnDelete.setEnabled(true);
     }
     public void setDeleteEditableFalse(){
         btnDelete.setEnabled(false);
     }
     public void setAddOrderEditableTrue(){
         btnOrder.setEnabled(true);
     }
     public void setAddOrderEditableFalse(){
         btnOrder.setEnabled(false);
     }
     public void setAddOrderAndPrintEditableFalse(){
         btnOrderAndPrint.setEnabled(false);
     }
     public void setAddOrderAndPrintEditableTrue(){
         btnOrderAndPrint.setEnabled(true);
     }
      public void setEditOrderEditableTrue(){
         btnOrderEdit.setEnabled(true);
     }
     public void setEditOrderEditableFalse(){
         btnOrderEdit.setEnabled(false);
     }
      public void setDeleteOrderEditableTrue(){
         btnOrderDelete.setEnabled(true);
     }
     public void setDeleteOrderEditableFalse(){
         btnOrderDelete.setEnabled(false);
     }
    public DefaultTableModel getTableOrderList(){
        return (DefaultTableModel) tblOrderList.getModel();
    }
    public DefaultTableModel getTableOrderedList(){
        return (DefaultTableModel) tblOrderedList.getModel();
    }
    public DefaultTableModel getSearchTable(){
        return (DefaultTableModel) tblSearch.getModel();
    }
   
    public JComboBox returnCustomerComboBox(){
        return comboCustomerName;
    }
    public JComboBox returnWaiterComboBox(){
        return comboWaiterName;
    }
    public JComboBox returnTableComboBox(){
        return comboTableName;
    }
    public JComboBox returnMenuComboBox(){
        return comboMenuName;
    }
    public void clearAddData(){
       
        setMenuId(0);
        setQuantity(0.0);
        setRate("0");
        setItemBaseUnit("");
        setSearchMenu("");
        //setComboTableName(new String());
        
        
    }
    public void clearCustomData(){
        setCustomMenuId(0);
        setCustomMenuname("");
        setCustomQuantity(0.0);
        setCustomRate(0.0);
        setCustomTotalAmount(0.0);
    }
    public void  setOrderData(String[] data){
        if(data[0].equals("")){
            comboTableName.setSelectedIndex(0);
        }
        else{
        setComboTableName(data[0]);
        }
       // System.out.println(data[0]);
         if(data[1].equals("")){
            comboCustomerName.setSelectedIndex(0);
        }
         {
        setComboCustomerName(data[1]);
         }
       // System.out.println(data[1]);
          if(data[2].equals("")){
            comboWaiterName.setSelectedIndex(0);
        }
          {
        setComboWaiterName(data[2]);
          }
       // System.out.println(data[2]);
    }
    public  void clearOrderData(){
        if(comboCustomerName.getModel().getSize() != 0){
          comboCustomerName.setSelectedIndex(0);  
        }
        if(comboWaiterName.getModel().getSize() != 0){
            comboWaiterName.setSelectedIndex(0);
        }
        if(comboMenuName.getModel().getSize() != 0){
            comboMenuName.setSelectedIndex(0);
       }
        if(comboTableName.getModel().getSize() != 0){
            comboTableName.setSelectedIndex(0);
       }
        setOrderId("");
//        setMainOrderId(0);
        setMenuId(0);
        setQuantity(0.0);
       // setRate("0");
        setTableId(0);
        setCustomerId(0);
        setWaiterId(0);
        setItemBaseUnit("");
        setSearchMenu("");
        setSearch("");
        //clear netamount
        setTotalAmount(BigDecimal.ZERO);
        setComplimentaryAmount(BigDecimal.ZERO);
        setNetAmount(BigDecimal.ZERO);
       
    }
    public void initComboBox(){
         comboCustomerName.setSelectedIndex(0);
       comboWaiterName.setSelectedIndex(0);
     comboMenuName.setSelectedIndex(0);
     comboTableName.setSelectedIndex(0);
    }
    public void addcomboMenuNameFocus(){
//         comboMenuName.setFocusable(true);
        comboMenuName.requestFocusInWindow();
       
    }

    /*
    return all the button
     */
    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnAddCustomer() {
        return btnAddCustomer;
    }

    public JButton getBtnAddTable() {
        return btnAddTable;
    }

    public JButton getBtnAddWaiter() {
        return btnAddWaiter;
    }

    public JButton getBtnCancelOrder() {
        return btnCancelOrder;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }
    
    public   JButton getBtnOrder() {
       
        return btnOrder;
    }

    public JButton getBtnOrderAndPrint() {
        return btnOrderAndPrint;
    }

    public JButton getBtnOrderDelete() {
        return btnOrderDelete;
    }

    public JButton getBtnOrderEdit() {
        return btnOrderEdit;
    }

    public JButton getBtnRefresh() {
        return btnRefresh;
    }

    public JButton getBtnSearchMenu() {
        return btnSearchMenu;
    }

    public JButton getBtnSearchOrder() {
        return btnSearchOrder;
    }
   public void ClickAddOrderButton(){
       btnOrder.doClick();
   }
     public void setSVC(Double svc){
            DoubleSVC = svc;
        }
        public Double getSVC(){
            return DoubleSVC;
        }
     public void setVAT(Double Vat){
         DoubleVAT = Vat;
     }
     public Double getVAT(){
         return DoubleVAT;
     }
    
     public Map getOrderParam(){
         Map para = new HashMap<Object, Object>();
         para.put("orderId", Integer.parseInt(lblOrderId.getText().trim()));
         return para;
     }
     public class sAbstractListener extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("wala");
            
        }
         
     }
     
      //for setting the table type to accept the combo types
     public void setUpTableColumnForOrderedTable(JTable table,TableColumn column){
         JComboBox columnTableNo = new JComboBox();
         columnTableNo.addItem("table1");
         columnTableNo.addItem("table2");
         column.setCellEditor(new DefaultCellEditor(columnTableNo));
          //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        column.setCellRenderer(renderer);
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
            java.util.logging.Logger.getLogger(OrderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderView().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JDialog CustomMenuAddDialog;
    public javax.swing.JDialog SearchDailog;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddCustomer;
    private javax.swing.JButton btnAddTable;
    private javax.swing.JButton btnAddWaiter;
    private javax.swing.JButton btnCancelOrder;
    private javax.swing.JButton btnComplimentarySave;
    private javax.swing.JButton btnCustomMenuAdd;
    private javax.swing.JButton btnCustomMenuRealAdd;
    private javax.swing.JButton btnCustomMenuRealCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInsertNewComplimentary;
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnOrderAndPrint;
    private javax.swing.JButton btnOrderDelete;
    private javax.swing.JButton btnOrderEdit;
    public javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearchMenu;
    private javax.swing.JButton btnSearchOrder;
    private javax.swing.JComboBox comboCategoryName;
    private javax.swing.JComboBox comboComplimentary;
    private javax.swing.JComboBox comboCustomerName;
    private javax.swing.JComboBox comboDepartmentName;
    private javax.swing.JComboBox comboMenuName;
    private javax.swing.JComboBox comboTableName;
    private javax.swing.JComboBox comboWaiterName;
    public javax.swing.JDialog dialogComplimentary;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblComplimentaryAmount;
    private javax.swing.JLabel lblNetAmount;
    private javax.swing.JLabel lblOrderId;
    private javax.swing.JLabel lblTotalAmount;
    public javax.swing.JScrollPane scroll;
    public javax.swing.JTable tblOrderList;
    public javax.swing.JTable tblOrderedList;
    public javax.swing.JTable tblSearch;
    private javax.swing.JTextField txtCustomMenuName;
    private javax.swing.JFormattedTextField txtCustomQuantity;
    private javax.swing.JFormattedTextField txtCustomRate;
    private javax.swing.JFormattedTextField txtCustomTotalAmount;
    public javax.swing.JFormattedTextField txtOrderQuantity;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearchMenu;
    // End of variables declaration//GEN-END:variables
}
