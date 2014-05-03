/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.directbill;

import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import reusableClass.Function;
import reusableClass.Validator;

/**
 *
 * @author SUSHIL
 */
public class DirectBillView extends javax.swing.JInternalFrame {
    
    private final ListSelectionModel ListSelectionModelBillInfo;
   
     private final ListSelectionModel ListSelectionModelAddOrderInfo;
      private final ListSelectionModel ListSelectionModelComplimentaryInfo;
    private int CustomerId ;
    private int orderCustomerId ;
    private int OrderId ;
    private int MenuId ;
    private Double SVCpercentage = new Double(0.0) ;
    private Double VATPercentage = new Double(0.0);
    private final Double DiscountAmount = new Double(0.0);
     private Double TotalAfterDiscount = 0.0;
    private int TableId = 0;
    private int MainBillId = 0;
    private int DepartmentId = 0;
    private int ComplimentaryId = 0;
    private String ItemBaseUnit = new String();
private BigDecimal rate;
    public ArrayList<String> OrderArray = new ArrayList<>();
    public ArrayList<String[]> ComplimentaryList = new ArrayList<>();
    //for listening to the key
    private KeyboardFocusManager directbillKeyboardManager;
    
    

    /**
     * Creates new form OrderBillView
     */
    public DirectBillView() {
          super("Direct Bill Pay Window",false,true,false,true);
//          setDefaultCloseOperation(EXIT_ON_CLOSE);
          
        initComponents();
//        addInternalFrameListener(null)
        jPanel6.requestFocusInWindow();
         comboMenuName.setFocusable(true);
        comboMenuName.requestFocusInWindow();
       
      
        ListSelectionModelBillInfo = tblBillInfo.getSelectionModel();
        ListSelectionModelBillInfo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       
         ListSelectionModelAddOrderInfo = tblAddOrder.getSelectionModel();
        ListSelectionModelAddOrderInfo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModelComplimentaryInfo = tblComplimentarySelect.getSelectionModel();
        ListSelectionModelComplimentaryInfo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        setButtonForEnter(btnSave);
        setButtonForEnter(btnCancel);
        setButtonForEnter(btnBillSave);
        setButtonForEnter(btnBillSaveAndPrint);
        setButtonForEnter(btnBillCancel);
        setButtonForEnter(btnAdd);
        setButtonForEnter(btnAddCustomer);
        
        jPanel4.setVisible(false);
      
       // txtReturnAmount.addFocusListener(new SetFocusListener(txtReturnAmount));
        txtSVCPercent.addFocusListener(new SetFocusListener(txtSVCPercent));
//        txtSearch.addFocusListener(new SetFocusListener(txtSearch));
        
       Validator.DecimalMaker(txtTenderedAmount);
       Validator.DecimalMaker(txtOrderQuantity);
       Validator.PercentageMaker(txtVATPercent);
       Validator.DecimalMaker(txtDiscountPercent);
       
       txtTenderedAmount.addFocusListener(new Function.SetJFormattedTextFieldFocusListener(txtTenderedAmount));
       txtOrderQuantity.addFocusListener(new Function.SetJFormattedTextFieldFocusListener(txtOrderQuantity));
         //SetFocusListener(txtDiscountAmount);
        txtDiscountPercent.addFocusListener(new Function.SetJFormattedTextFieldFocusListener(txtDiscountPercent));
        /*
         * center in the middle of the screen
         */
        JDialogBillPayment.setLocationRelativeTo(null);
        /*
         * for adding textwindos
         */
        txtTenderedAmount.addActionListener(new addEnterFocusListener());
        /*
         * adding clos listener
         */
        /*
        receing the key input
        */
        directbillKeyboardManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        btnBillSaveAndPrint.setVisible(false);
       
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JDialogBillPayment = new javax.swing.JDialog();
        btnBillSave = new javax.swing.JButton();
        btnBillSaveAndPrint = new javax.swing.JButton();
        btnBillCancel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        comboBoxCustomerName = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        checkBoxComplimentary = new javax.swing.JCheckBox();
        radioCash = new javax.swing.JRadioButton();
        radioCredit = new javax.swing.JRadioButton();
        btnAddCustomer = new javax.swing.JButton();
        comboboxComplimentaryName = new javax.swing.JComboBox();
        buttonGroupPaymentType = new javax.swing.ButtonGroup();
        JDialogAddOrder = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblAddOrder = new javax.swing.JTable();
        JDialogComplimentary = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblComplimentarySelect = new javax.swing.JTable();
        lblBillNo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtGrandTotal2 = new javax.swing.JFormattedTextField();
        jPanel6 = new javax.swing.JPanel();
        comboMenuName = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        txtOrderQuantity = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        comboCustomerNameForOrder = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        comboDepartmentName = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBillInfo = new javax.swing.JTable();
        lblAmountReturn = new javax.swing.JLabel();
        btnAddOrder = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        txtTotal = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        checkBoxSVCByPercentage = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenderedAmount = new javax.swing.JFormattedTextField();
        txtGrandTotal1 = new javax.swing.JFormattedTextField();
        txtSubTotalAmount = new javax.swing.JFormattedTextField();
        checkBoxDiscountByPercentage = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        checkBoxVATByPercentage = new javax.swing.JCheckBox();
        txtDiscountPercent = new javax.swing.JFormattedTextField();
        txtSVCPercent = new javax.swing.JFormattedTextField();
        txtVATPercent = new javax.swing.JFormattedTextField();
        //NumberFormatter numberformatter = new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00;(#,##0.00)"));
        //NumberFormatter numberformatter = new javax.swing.text.NumberFormatter(NumberFormat.getCurrencyInstance(Locale.US));
        //NumberFormatter numberformatter = new javax.swing.text.NumberFormatter(DecimalFormat.getInstance());
        //NumberFormat format = DecimalFormat.getInstance();
        //format.setMinimumFractionDigits(2);
        //                format.setMaximumFractionDigits(2);
        // InternationalFormatter numberformatter = new InternationalFormatter(format);
        //numberformatter.setAllowsInvalid(false);
        //numberformatter.setCommitsOnValidEdit(true);
        //numberformatter.setOverwriteMode(true);
        txtSVCAmount = new javax.swing.JFormattedTextField();
        txtDiscountAmount = new javax.swing.JFormattedTextField();
        txtVATAmount = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        txtAmountReturn = new javax.swing.JFormattedTextField();

        JDialogBillPayment.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        JDialogBillPayment.setMinimumSize(new java.awt.Dimension(500, 300));
        JDialogBillPayment.setModal(true);
        JDialogBillPayment.setResizable(false);

        btnBillSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBillSave.setMnemonic('S');
        btnBillSave.setText("Save");
        btnBillSave.setToolTipText("ALT+S");
        btnBillSave.setActionCommand("BillSave");

        btnBillSaveAndPrint.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBillSaveAndPrint.setText("Save&Print");
        btnBillSaveAndPrint.setActionCommand("BillSaveAndPrint");

        btnBillCancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBillCancel.setMnemonic('C');
        btnBillCancel.setText("Cancel");
        btnBillCancel.setToolTipText("ALT+C");
        btnBillCancel.setActionCommand("BillCancel");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        comboBoxCustomerName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBoxCustomerName.setActionCommand("comboCustomerName");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Customer:");

        checkBoxComplimentary.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        checkBoxComplimentary.setMnemonic('o');
        checkBoxComplimentary.setText("Complimentary");
        checkBoxComplimentary.setToolTipText("ALT+O");
        checkBoxComplimentary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxComplimentaryActionPerformed(evt);
            }
        });

        buttonGroupPaymentType.add(radioCash);
        radioCash.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioCash.setMnemonic('a');
        radioCash.setSelected(true);
        radioCash.setText("Cash");
        radioCash.setToolTipText("ALT+A");

        buttonGroupPaymentType.add(radioCredit);
        radioCredit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioCredit.setMnemonic('r');
        radioCredit.setText("Credit");
        radioCredit.setToolTipText("ALT+R");
        radioCredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCreditActionPerformed(evt);
            }
        });

        btnAddCustomer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAddCustomer.setMnemonic('U');
        btnAddCustomer.setText("ADD Customer");
        btnAddCustomer.setToolTipText("ALT+U");
        btnAddCustomer.setActionCommand("ADDCustomer");
        btnAddCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCustomerActionPerformed(evt);
            }
        });

        comboboxComplimentaryName.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(radioCash)
                .addGap(62, 62, 62)
                .addComponent(radioCredit)
                .addContainerGap(132, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(checkBoxComplimentary)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboboxComplimentaryName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboBoxCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btnAddCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioCash)
                    .addComponent(radioCredit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboBoxCustomerName)
                        .addComponent(btnAddCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxComplimentary)
                    .addComponent(comboboxComplimentaryName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {comboBoxCustomerName, jLabel4});

        javax.swing.GroupLayout JDialogBillPaymentLayout = new javax.swing.GroupLayout(JDialogBillPayment.getContentPane());
        JDialogBillPayment.getContentPane().setLayout(JDialogBillPaymentLayout);
        JDialogBillPaymentLayout.setHorizontalGroup(
            JDialogBillPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDialogBillPaymentLayout.createSequentialGroup()
                .addGroup(JDialogBillPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JDialogBillPaymentLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btnBillSave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBillSaveAndPrint)
                        .addGap(21, 21, 21)
                        .addComponent(btnBillCancel))
                    .addGroup(JDialogBillPaymentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        JDialogBillPaymentLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBillCancel, btnBillSave, btnBillSaveAndPrint});

        JDialogBillPaymentLayout.setVerticalGroup(
            JDialogBillPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDialogBillPaymentLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(JDialogBillPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBillSave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBillSaveAndPrint)
                    .addComponent(btnBillCancel))
                .addGap(34, 34, 34))
        );

        JDialogBillPaymentLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBillCancel, btnBillSave, btnBillSaveAndPrint});

        JDialogAddOrder.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        JDialogAddOrder.setMinimumSize(new java.awt.Dimension(520, 500));
        JDialogAddOrder.setModalityType(java.awt.Dialog.ModalityType.DOCUMENT_MODAL);
        JDialogAddOrder.setResizable(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Order"));

        tblAddOrder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblAddOrder.setModel(new javax.swing.table.DefaultTableModel(
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
        tblAddOrder.setRowHeight(20);
        jScrollPane4.setViewportView(tblAddOrder);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout JDialogAddOrderLayout = new javax.swing.GroupLayout(JDialogAddOrder.getContentPane());
        JDialogAddOrder.getContentPane().setLayout(JDialogAddOrderLayout);
        JDialogAddOrderLayout.setHorizontalGroup(
            JDialogAddOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDialogAddOrderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JDialogAddOrderLayout.setVerticalGroup(
            JDialogAddOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDialogAddOrderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JDialogComplimentary.setMinimumSize(new java.awt.Dimension(450, 400));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Complimentary Select Table"));

        tblComplimentarySelect.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(tblComplimentarySelect);
        tblComplimentarySelect.getTableHeader().setReorderingAllowed(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout JDialogComplimentaryLayout = new javax.swing.GroupLayout(JDialogComplimentary.getContentPane());
        JDialogComplimentary.getContentPane().setLayout(JDialogComplimentaryLayout);
        JDialogComplimentaryLayout.setHorizontalGroup(
            JDialogComplimentaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDialogComplimentaryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JDialogComplimentaryLayout.setVerticalGroup(
            JDialogComplimentaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDialogComplimentaryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        lblBillNo.setBackground(new java.awt.Color(153, 255, 102));
        lblBillNo.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblBillNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        lblBillNo.setOpaque(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Bill No:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText(" Grand Total :");

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSave.setMnemonic('S');
        btnSave.setText("Save");
        btnSave.setToolTipText("ALT+S");
        btnSave.setEnabled(false);

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancel.setMnemonic('C');
        btnCancel.setText("Cancel");
        btnCancel.setToolTipText("ALT+C");

        txtGrandTotal2.setEditable(false);
        txtGrandTotal2.setBackground(new java.awt.Color(153, 255, 102));
        txtGrandTotal2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txtGrandTotal2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtGrandTotal2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        txtGrandTotal2.setValue(0.0);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Item Add"));

        comboMenuName.setActionCommand("ComboMenuName");
        comboMenuName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMenuNameActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Qty:");

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdd.setMnemonic('a');
        btnAdd.setText("Add");
        btnAdd.setToolTipText("ALT+A");
        btnAdd.setMinimumSize(new java.awt.Dimension(87, 25));

        txtOrderQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrderQuantityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboMenuName, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(txtOrderQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboMenuName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOrderQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdd, txtOrderQuantity});

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Order Table"));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Customer Name:");

        comboCustomerNameForOrder.setActionCommand("ComboCustomerName");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(comboCustomerNameForOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboCustomerNameForOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Department Name:");

        comboDepartmentName.setActionCommand("comboDepartmentName");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setFocusCycleRoot(true);

        tblBillInfo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblBillInfo.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBillInfo.setEnabled(false);
        tblBillInfo.setRowHeight(20);
        jScrollPane1.setViewportView(tblBillInfo);

        btnAddOrder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAddOrder.setText("Add Order");
        btnAddOrder.setActionCommand("AddOrder");
        btnAddOrder.setEnabled(false);

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTotal.setValue(0.0);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Discount:");

        checkBoxSVCByPercentage.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        checkBoxSVCByPercentage.setText("By %");
        checkBoxSVCByPercentage.setActionCommand("SVC");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Amount Tendered:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Total:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("VAT:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("SVC:");

        txtTenderedAmount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtTenderedAmount.setValue(0.0);

        txtGrandTotal1.setEditable(false);
        txtGrandTotal1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtGrandTotal1.setValue(0.0);

        txtSubTotalAmount.setEditable(false);
        txtSubTotalAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSubTotalAmount.setValue(0.0
        );

        checkBoxDiscountByPercentage.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        checkBoxDiscountByPercentage.setText("By %");
        checkBoxDiscountByPercentage.setActionCommand("Discount");
        checkBoxDiscountByPercentage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxDiscountByPercentageActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Sub Total:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Grand Total :");

        checkBoxVATByPercentage.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        checkBoxVATByPercentage.setSelected(true);
        checkBoxVATByPercentage.setText("By %");
        checkBoxVATByPercentage.setActionCommand("VAT");
        checkBoxVATByPercentage.setEnabled(false);

        txtDiscountPercent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDiscountPercent.setValue(0.0);
        txtDiscountPercent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiscountPercentActionPerformed(evt);
            }
        });

        txtSVCPercent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSVCPercent.setValue(0.0);

        txtVATPercent.setEditable(false);
        txtVATPercent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtVATPercent.setValue(0.0
        );

        txtSVCAmount.setValue(new Double(0.0));
        txtSVCAmount.setEditable(false);
        txtSVCAmount.setBackground(new java.awt.Color(153, 153, 153));
        txtSVCAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtDiscountAmount.setEditable(false);
        txtDiscountAmount.setBackground(new java.awt.Color(153, 153, 153));
        txtDiscountAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtVATAmount.setEditable(false);
        txtVATAmount.setBackground(new java.awt.Color(153, 153, 153));
        txtVATAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(checkBoxSVCByPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkBoxDiscountByPercentage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDiscountPercent, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(txtSVCPercent))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiscountAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSVCAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(checkBoxVATByPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotal)
                            .addComponent(txtSubTotalAmount, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtGrandTotal1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenderedAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(txtVATPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtVATAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiscountPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(txtDiscountAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSVCPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSVCAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkBoxSVCByPercentage)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(checkBoxDiscountByPercentage)
                        .addComponent(jLabel10)))
                .addGap(4, 4, 4)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSubTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtVATAmount, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtVATPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(checkBoxVATByPercentage)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGrandTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenderedAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Amount Return:");

        txtAmountReturn.setEditable(false);
        txtAmountReturn.setForeground(new java.awt.Color(255, 0, 0));
        txtAmountReturn.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtAmountReturn.setValue(0.0);
        txtAmountReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAmountReturnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(btnAddOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblAmountReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtAmountReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)))
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btnAddOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtAmountReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addComponent(lblAmountReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblBillNo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtGrandTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(32, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comboDepartmentName, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(112, 112, 112)
                                .addComponent(btnCancel)))
                        .addGap(46, 46, 46))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancel, btnSave});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblBillNo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtGrandTotal2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboDepartmentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(112, 112, 112))))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancel, btnSave});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {comboDepartmentName, jLabel9});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkBoxComplimentaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxComplimentaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxComplimentaryActionPerformed

    private void radioCreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCreditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioCreditActionPerformed

    private void comboMenuNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMenuNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboMenuNameActionPerformed

    private void btnAddCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddCustomerActionPerformed

    private void checkBoxDiscountByPercentageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxDiscountByPercentageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxDiscountByPercentageActionPerformed

    private void txtDiscountPercentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiscountPercentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiscountPercentActionPerformed

    private void txtAmountReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAmountReturnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAmountReturnActionPerformed

    private void txtOrderQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrderQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOrderQuantityActionPerformed
        public void setSVCPercentage(Double d){
            SVCpercentage = d;
        }
        public Double getSVCPercentage(){
            return SVCpercentage;
        }
         public void setVATPercentage(Double d){
            VATPercentage = d;
        }
        public Double getVATPercentage(){
            return VATPercentage;
        }
         //this is when discount percantage is chlick
      
        
        public String getBillId(){
             return lblBillNo.getText();
        }
        public void setBillId(String id){
            lblBillNo.setText(id);
        }
         public Double getVAT(){
             return ((Number)txtVATPercent.getValue()).doubleValue();
        }
        public void setVAT(Double id){
            txtVATPercent.setValue(id);
        }
//         public String getDate(){
//             return lblDate.getText();
//        }
//        public void setDate(String id){
//            lblDate.setText(id);
//        }
         public int getCustomerId(){
             return CustomerId;
        }
        public void setCustomerId(int id){
            CustomerId = id;
        }
         public int getOrderCustomerId(){
             return orderCustomerId;
        }
        public void setOrderCustomerId(int id){
            orderCustomerId = id;
        }
          public void setMainBillId(int i){
            MainBillId = i;
        }
        public int getMainBillId(){
            return MainBillId;
        }

        public int getOrderId(){
             return OrderId;
        }
        public void setOrderId(int id){
            OrderId = id;
        }
            public int getDepartmentId(){
        return DepartmentId;
    }
    public void setDepartmentId(int id){
        DepartmentId = id;
    }
        
        public Double getGrandTotal1(){
            return ((Number)txtGrandTotal1.getValue()).doubleValue();
        }
        public void setGrandTotal1(Double total){
            txtGrandTotal1.setValue(total);
        }
        public Double getGrandTotal2(){
            return ((Number)txtGrandTotal2.getValue()).doubleValue();
        }
        public void setGrandTotal2(Double total){
            txtGrandTotal2.setValue(total);
        }
        public void setTotal(Double total){
            txtTotal.setValue(total);
        }
        public Double getTotal(){
             return ((Number)txtTotal.getValue()).doubleValue();
        }
        public void setSVC(Double svc){
            txtSVCPercent.setValue(svc);
        }
        public Double getSVC(){
            return ((Number)txtSVCPercent.getValue()).doubleValue();
        }
//          public void setSearch(String svc){
//            txtSearch.setText(svc);
//        }
//        public String getSearch(){
//            return txtSearch.getText().trim();
//        }
        public void setSubTotal(Double amount){
            txtSubTotalAmount.setValue(amount);
        }
        public Double getSubTotal(){
            return ((Number)txtSubTotalAmount.getValue()).doubleValue();
        }
        public void setDiscount(Double dsc){
            txtDiscountPercent.setValue(dsc);
        }
        public Double getDiscount(){
            return ((Number)txtDiscountPercent.getValue()).doubleValue();
        }
          public void setSVCCheck(boolean bl){
            checkBoxSVCByPercentage.setSelected(bl);
        }
        public void setDiscountCheck(boolean bl){
            checkBoxDiscountByPercentage.setSelected(bl);
        }
        public void setTenderedAmount(Double amt){
            txtTenderedAmount.setValue(amt);
        }
        public Double getTenderedAmount(){
            return ((Number)txtTenderedAmount.getValue()).doubleValue();
        }
        public void setChangeAmount(Double amt){
           txtAmountReturn.setValue(amt);
        }
        public Double getChangeAmount(){
            return ((Number)txtAmountReturn.getValue()).doubleValue();
        }
        public void setTableId(int i){
            TableId = i;
        }
        public int getTableId(){
            return TableId;
        }
         public int getMenuId(){
        return MenuId;
    }
    public void setMenuId(int id){
        MenuId = id;
    }
     public void setItemBaseUnit(String  item){
        ItemBaseUnit = item;
    }
    public String getItemBaseUnit(){
        return ItemBaseUnit;
    }
     public BigDecimal getRate(){
        return rate;
    }
    public void setRate(String rate){
        this.rate = new BigDecimal(rate).setScale(2, RoundingMode.HALF_UP);
    }
      //added code for amount in not editable for
      public double getSVCAmount(){
            return ((Number)txtSVCAmount.getValue()).doubleValue();
        }
        public void setSVCAmount(double db){
            txtSVCAmount.setValue(new Double(db));
        }
           public double getDiscountAmount(){
            return ((Number)txtDiscountAmount.getValue()).doubleValue();
        }
        public void setDiscountAmount(double ui){
           txtDiscountAmount.setValue(ui);
        }
         public void setVATAmount(double db){
            txtVATAmount.setValue(new Double(db));
        }
        public double getVATAmount(){
             return ((Number)txtVATAmount.getValue()).doubleValue();
        }
        
         public void setTotalAfterDiscount(Double ds){
            TotalAfterDiscount = ds;
        }
        public Double getTotalAfterDiscount(){
            return TotalAfterDiscount;
        }
         //returning textfield
          public JFormattedTextField returnSVC(){
              return txtSVCPercent;
          }
          public JFormattedTextField returnDiscount(){
              return txtDiscountPercent;
          }
          public JFormattedTextField returnTenderedAmount(){
              return txtTenderedAmount;
          }
           public void requestFocusOnButtonSimpleBillSave(){
         btnSave.requestFocus();
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
  
    public void setComboCustomerName(String[] name){
        DefaultComboBoxModel CustomerNameModel = new DefaultComboBoxModel(name);
        comboCustomerNameForOrder.setModel(CustomerNameModel);
    }
    public void setComboCustomerName(String name){
        comboCustomerNameForOrder.setSelectedItem(name);
    }
    public String getComboCustomerName(){
        return comboCustomerNameForOrder.getSelectedItem().toString();
    }
     public void setComboCustomerNameForBill(String[] name){
        DefaultComboBoxModel CustomerNameModel = new DefaultComboBoxModel(name);
        comboBoxCustomerName.setModel(CustomerNameModel);
    }
    public void setComboCustomerNameForBill(String name){
        comboBoxCustomerName.setSelectedItem(name);
    }
    public String getComboCustomerNameForBill(){
        return comboBoxCustomerName.getSelectedItem().toString();
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
    public void setQuantity(Double st){
        txtOrderQuantity.setValue(st);
    }
    public Double getQuantity(){
        return ((Number)txtOrderQuantity.getValue()).doubleValue();
    }
      
        public void setComplimentary(Boolean flag){
          //  if(flag == true){
                checkBoxComplimentary.setSelected(flag);
          //  }
        }
        public Boolean getBooleanDiscountPercentage(){
        return checkBoxDiscountByPercentage.isSelected();
        }
        public void  setBooleanDiscountPercentage(Boolean flag){
            checkBoxDiscountByPercentage.setSelected(flag);
        }
          public Boolean getBooleanSVCPercentage(){
        return checkBoxSVCByPercentage.isSelected();
        }
        public void  setBooleanSVCPercentage(Boolean flag){
            checkBoxSVCByPercentage.setSelected(flag);
        }
        public Boolean getPaymentType(){
        return radioCash.isSelected();
        }
         public Boolean getBooleanComplimentaryType(){
        return checkBoxComplimentary.isSelected();
        }
        public void setPaymentTypeForCash(Boolean flag){
            radioCash.setSelected(flag);
        }
        public void setPaymentTypeForCredit(Boolean flag){
            radioCredit.setSelected(flag);
           
        }
      
        //for complimentary method
          public void setComboComplimentaryName(Object[] data){
            DefaultComboBoxModel model = new DefaultComboBoxModel(data);
            comboboxComplimentaryName.setModel(model);
        }
        public void setComboComplimentaryName(String reason){
            comboboxComplimentaryName.setSelectedItem(reason);
        }
        public String getComboComplimentaryName(){
            return comboboxComplimentaryName.getSelectedItem().toString();
        }
        public  JComboBox returnComboComplimentaryName(){
            return comboboxComplimentaryName;
        }
        public void setComboComplimentaryEnable(boolean bl){
            comboboxComplimentaryName.setEnabled(bl);
            
        }
        public void setComplimentaryId(int id){
            ComplimentaryId = id;
        }
        public int getComplimentaryId(){
            return ComplimentaryId;
        }
        
     
        public String getcomboCustomerName(){
            return comboBoxCustomerName.getSelectedItem().toString();
        }
        
      
        public void refreshJTableBillInfo(DefaultTableModel model){
            tblBillInfo.setModel(model);
        }
        public void refreshJTableAddOrder(DefaultTableModel model){
            tblAddOrder.setModel(model);
        }
         public void refreshJTableComplimentarySelect(DefaultTableModel model){
            tblComplimentarySelect.setModel(model);
        }
        public void addComboCustomerNameForOrderListener(ActionListener ListenForCustomerClick){
         comboCustomerNameForOrder.addActionListener(ListenForCustomerClick);
     }
         public void addComboMenuNameListener(ActionListener ListenForMenuClick){
         comboMenuName.addActionListener(ListenForMenuClick);
     }
         public void addComboCustomerNameForBillListener(ActionListener Listen){
            comboBoxCustomerName.addActionListener(Listen);
         }
         public void addComboDepartmentNameListener(ActionListener Listen){
             comboDepartmentName.addActionListener(Listen);
         }
         public void clearAddData(){
       
        setMenuId(0);
        setQuantity(0.0);
        setRate("0");
        setItemBaseUnit("");
//        setSearchMenu("");
        //setComboTableName(new String());
        
        
    }
      
       
        public DefaultTableModel gettblBillInfo(){
            return (DefaultTableModel) tblBillInfo.getModel();
        }
          public DefaultTableModel gettblAddOrder(){
            return (DefaultTableModel) tblAddOrder.getModel();
        }
        public DefaultTableModel gettblComplimentarySelect(){
        return (DefaultTableModel) tblComplimentarySelect.getModel();
         }
          public void setSaveEditableTrue(){
            btnSave.setEnabled(true);
        }
        public void setSaveEditableFalse(){
            btnSave.setEnabled(false);
        }
        public void setCancelEditableTrue(){
            btnCancel.setEnabled(true);
        }
        public void setCancelEditableFalse(){
            btnCancel.setEnabled(false);
        }
        public void setBillSaveEditableFalse(){
            btnBillSave.setEnabled(false);
        }
        public void setBillSaveEditableTrue(){
            btnBillSave.setEnabled(true);
        }
        public void setBillCancelEditableFalse(){
            btnBillCancel.setEnabled(false);
        }
        public void setBillCancelEditableTrue(){
            btnBillCancel.setEnabled(true);
        }
        public void setBillSaveAndPrintEditableFalse(){
            btnBillSaveAndPrint.setEnabled(false);
        }
        public void setBillSaveAndPrintEditableTrue(){
            btnBillSaveAndPrint.setEnabled(true);
        }
       
        public void setCheckBoxComplimentaryTrue(){
            checkBoxComplimentary.setSelected(true);
        }
        public void setCheckBoxComplimentaryFalse(){
            checkBoxComplimentary.setSelected(false);
        }
         public void setCheckBoxSVCTrue(){
            checkBoxSVCByPercentage.setSelected(true);
        }
        public void setCheckBoxSVCFalse(){
            checkBoxSVCByPercentage.setSelected(false);
        }
         public void setCheckBoxDiscountTrue(){
            checkBoxDiscountByPercentage.setSelected(true);
        }
        public void setCheckBoxDiscountFalse(){
            checkBoxDiscountByPercentage.setSelected(false);
        }
         public void addAdd(ActionListener ListenForAddOrder){
         btnAdd.addActionListener(ListenForAddOrder);
     }
         public void addTextQuantityListener(ActionListener Listen){
             txtOrderQuantity.addActionListener(Listen);
         }
         public void addfocusonAddButton(){
             btnAdd.requestFocus();
         }
        public void addSaveListener(ActionListener ListenForSave){
            btnSave.addActionListener(ListenForSave);
        }
        public void addCancelListener(ActionListener ListenForCancel){
            btnCancel.addActionListener(ListenForCancel);
        }
        public void addBillSaveListener(ActionListener ListenForSave){
            btnBillSave.addActionListener(ListenForSave);
        }
        public void addBillSaveAndPrintListener(ActionListener ListenForSave){
            btnBillSaveAndPrint.addActionListener(ListenForSave);
        }
        public void addBillCancelListener(ActionListener ListenForCancel){
            btnBillCancel.addActionListener(ListenForCancel);
        }
         //adding  documetlistener
          public void addSVCDocumentListener(DocumentListener Listen){
              txtSVCPercent.getDocument().addDocumentListener(Listen);
          }
          public  void addDiscountDocumentListener(DocumentListener Listen){
              txtDiscountPercent.getDocument().addDocumentListener(Listen);
          }
          public void addTenderedAmountDocumentListener(DocumentListener Listen){
              txtTenderedAmount.getDocument().addDocumentListener(Listen);
          }
          public void addTotalDocumentListener(DocumentListener Listen){
              txtTotal.getDocument().addDocumentListener(Listen);
          }
          public void addGrandTotalDocumentListener(DocumentListener Listen){
              txtGrandTotal1.getDocument().addDocumentListener(Listen);
          }
        //adding enter on the tenderamount
          public void addTenderedAmountActionListener(ActionListener listen){
              txtTenderedAmount.addActionListener(listen);
          }
//        public void addRefreshListener(ActionListener ListenForreresh){
//            btnRefresh.addActionListener(ListenForreresh);
//        }
//          public void addSearchListener(ActionListener ListenForreresh){
//            btnSearchOrder.addActionListener(ListenForreresh);
//        }
//          public void addTextSearchListener(ActionListener ListenForSearch){
//             txtSearch.addActionListener(ListenForSearch);
//          }
        public void addComboClickCustomerNameActionListener(ActionListener ListenForClick){
            comboBoxCustomerName.addActionListener(ListenForClick);
        }
       
          public void addCheckBoxComplimentaryListener(ActionListener ListenForClick){
              checkBoxComplimentary.addActionListener(ListenForClick);
          }
          public void addCheckBoxComplimentaryItemListener(ItemListener ListenForSelect){
              checkBoxComplimentary.addItemListener(ListenForSelect);
          }
            public void addCheckDiscountListener(ItemListener listen){
              checkBoxDiscountByPercentage.addItemListener(listen);
          }
//          public void addCheckDiscountListener(ActionListener listen){
//              checkBoxDiscountByPercentage.addActionListener(listen);
//          }
          public void addCheckSVCListener(ItemListener listen){
              checkBoxSVCByPercentage.addItemListener(listen);
          }
//          public void addWindowListener(WindowAdapter ListenForClose){
//              
//          }
           /*
         * listenr for enter in table for add order
         */
        public void addDoubliClickListenerForAddOrder(MouseAdapter ListenForDoubleclick){
            tblAddOrder.addMouseListener(ListenForDoubleclick);
        }
        public void addDoubliClickListenerForComplimentarySelect(MouseAdapter ListenForDoubleclick){
            tblComplimentarySelect.addMouseListener(ListenForDoubleclick);
        }
        public void addCustomerWindow(ActionListener Listen){
            btnAddCustomer.addActionListener(Listen);
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
        public void addItemListenerForCashCredit(ItemListener ListenForChange){
            radioCash.addItemListener(ListenForChange);
            radioCredit.addItemListener(ListenForChange);
        }
        /*
        sending deligates for keyboard managers
        */
        public void addShortcutForDirectBill(KeyEventDispatcher ked){
            directbillKeyboardManager.addKeyEventDispatcher(ked);
        }
        public class addEnterFocusListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       JFormattedTextField jf = (JFormattedTextField)e.getSource();
      // System.out.println("walaoutside");
            if(jf == txtTenderedAmount){
               // System.out.println("wala");
                btnSave.requestFocusInWindow();
            }
        }
            
    
        }
      public final class SetFocusListener implements FocusListener{
      JTextField jf;
        public SetFocusListener(JTextField field){
         jf = field;   
        }

        @Override
        public void focusGained(FocusEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            SwingUtilities.invokeLater(new Runnable(){

                @Override
                public void run() {
                   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                jf.setBackground(new Color(136,249,168));
          jf.selectAll(); 
                }
                
            });
           
        }

        @Override
        public void focusLost(FocusEvent e) {
        //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        SwingUtilities.invokeLater(new Runnable(){

                @Override
                public void run() {
                   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 jf.setBackground(Color.white);
                }
                
            });
          
        }
        
    }
     public Double[] getBillRate(){
          Double[] data = new Double[8];
         // data[0] = getBillId();
          data[0] = getTotal();
          if(getBooleanSVCPercentage()){
//            System.out.println(getSVCAmount());
          data[1] = getSVCAmount();
          }
          else{
          data[1] = getSVC();
//           System.out.println(getSVC());
          }
          
          data[2] = getVATAmount();
          data[3 ]= getSubTotal();
          if(getBooleanDiscountPercentage()){
          data[4] = getDiscountAmount();
//          System.out.println(getDiscountAmount());
          }
          else{
           data[4] = getDiscount();
//           System.out.println(getDiscount());
          }
        
          data[5] = getGrandTotal1();
         // data[6] = getOrderId();
          data[6] = getTenderedAmount();
          data[7] = getChangeAmount();
          return data;
      }
      public String[] getBillOtherInfo(){
          String[] data =new String[7];
          data[0] = getBillId();
          data[1] = String.valueOf(getBooleanSVCPercentage());
          data[2] = String.valueOf(getBooleanDiscountPercentage());
          data[3] = String.valueOf(getCustomerId());
          data[4] = String.valueOf(getPaymentType());
          data[5] = String.valueOf(getBooleanComplimentaryType());
          data[6] = String.valueOf(getComplimentaryId());
//          data[7] = getDepartmentId();
          
          return data;
      }
      public void setOrderBill(){
          
      }
      public void clearOrderBill(){
          setBillId("0");
          setTotal(0.0);
          setSVC(0.0);
          setVAT(0.0);
           setSVCAmount(0.0);
          setDiscountAmount(0.0);
          setVATAmount(0.0);
          setCustomerId(0);
          setDiscount(0.0);
          setOrderId(0);
          setTenderedAmount(0.0);
          setChangeAmount(0.0);
          setDiscountAmount(0.0);
          ComplimentaryList.clear();
          setCheckBoxComplimentaryFalse();
          setCheckBoxDiscountFalse();
          setCheckBoxDiscountFalse();
          comboBoxCustomerName.setSelectedIndex(0);
          if(comboMenuName.getModel().getSize() != 0){
          comboMenuName.setSelectedIndex(0);
          }
          
          
      }
      
      public void requestFocusOnTenderedAmount(){
         txtTenderedAmount.requestFocus();
      }
       public void requestFocusOnButtonBillSave(){
         btnBillSave.requestFocus();
      }
         public void AddSelectInCombo(JComboBox jc){
      jc.insertItemAt("SELECT", 0);
      jc.setSelectedIndex(0);
    }
         public JComboBox returnComboMenuName(){
             return comboMenuName;
         }
         public JComboBox returnComboCustomerNameForBill(){
             return comboBoxCustomerName;
         }
         
         
         public void addAbstractActionListener(Action action){
             txtOrderQuantity.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "check");
             txtOrderQuantity.getActionMap().put("check", action);
             //sending enter object when combobox is entered
             comboMenuName.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "check");
             comboMenuName.getActionMap().put("check", action);
             //enter listening for tenderamount
             txtTenderedAmount.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"check");
             txtTenderedAmount.getActionMap().put("check", action);
         }

    /*
    returning the deligates
     */
    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JFormattedTextField getTxtOrderQuantity() {
        return txtOrderQuantity;
    }

    public JButton getBtnSave() {
        return btnSave;
    }
    

   
    
         
         
     
    

         
         //return parameters for printing bill after payment
         public Map getBillParam(){
             @SuppressWarnings("Convert2Diamond")
             Map param = new HashMap<Object, Object>();
             try{
                
             //putting parameter 
             param.put("billNo",lblBillNo.getText().trim());
             param.put("total",getTotal().toString());
             param.put("svc", getSVC().toString());
             param.put("vat", getVAT().toString());
             param.put("subtotal",getSubTotal().toString());
             param.put("discount",getDiscount().toString());
             param.put("grandtotal",getGrandTotal1().toString());
             param.put("amountTen",getTenderedAmount().toString());
             param.put("amountRetn",getChangeAmount().toString());
             param.put("terminal", 1);
             //param.put("orderId",Integer.parseInt(getOrderId()));
             
             }catch(ExceptionInInitializerError e){
                 JOptionPane.showMessageDialog(this,"resturant.orderbill.orderbillview"+e);
             }
             return param;
         }
    /*  public class BillPropertyChangeListener implements PropertyChangeListener{

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Object  name = evt.getSource();
        //System.out.println(name);
          if(txtSVC == name){
             
              setSubTotal(getTotal()+(getSVC()*getSVCPercentage())+(getVAT()*getVATPercentage()));
            
          }
          if(txtDiscountAmount == name){
              if(getBooleanDiscountPercentage()){
                  Double value = (getDiscount()*getSubTotal())/100;
                  setDiscount(value);
                  System.out.println("wala");
              }
          }
      }
      }
       */ 
           

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
            java.util.logging.Logger.getLogger(DirectBillView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DirectBillView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DirectBillView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DirectBillView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new DirectBillView().setVisible(true);
//            }
//        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JDialog JDialogAddOrder;
    public javax.swing.JDialog JDialogBillPayment;
    public javax.swing.JDialog JDialogComplimentary;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddCustomer;
    private javax.swing.JButton btnAddOrder;
    private javax.swing.JButton btnBillCancel;
    public javax.swing.JButton btnBillSave;
    private javax.swing.JButton btnBillSaveAndPrint;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.ButtonGroup buttonGroupPaymentType;
    private javax.swing.JCheckBox checkBoxComplimentary;
    private javax.swing.JCheckBox checkBoxDiscountByPercentage;
    private javax.swing.JCheckBox checkBoxSVCByPercentage;
    private javax.swing.JCheckBox checkBoxVATByPercentage;
    private javax.swing.JComboBox comboBoxCustomerName;
    private javax.swing.JComboBox comboCustomerNameForOrder;
    private javax.swing.JComboBox comboDepartmentName;
    public javax.swing.JComboBox comboMenuName;
    private javax.swing.JComboBox comboboxComplimentaryName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblAmountReturn;
    private javax.swing.JLabel lblBillNo;
    public javax.swing.JRadioButton radioCash;
    public javax.swing.JRadioButton radioCredit;
    public javax.swing.JTable tblAddOrder;
    public javax.swing.JTable tblBillInfo;
    public javax.swing.JTable tblComplimentarySelect;
    private javax.swing.JFormattedTextField txtAmountReturn;
    private javax.swing.JFormattedTextField txtDiscountAmount;
    private javax.swing.JFormattedTextField txtDiscountPercent;
    private javax.swing.JFormattedTextField txtGrandTotal1;
    private javax.swing.JFormattedTextField txtGrandTotal2;
    public javax.swing.JFormattedTextField txtOrderQuantity;
    private javax.swing.JFormattedTextField txtSVCAmount;
    private javax.swing.JFormattedTextField txtSVCPercent;
    private javax.swing.JFormattedTextField txtSubTotalAmount;
    private javax.swing.JFormattedTextField txtTenderedAmount;
    private javax.swing.JFormattedTextField txtTotal;
    private javax.swing.JFormattedTextField txtVATAmount;
    private javax.swing.JFormattedTextField txtVATPercent;
    // End of variables declaration//GEN-END:variables
}
