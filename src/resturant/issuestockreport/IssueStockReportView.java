/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.issuestockreport;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SUSHIL
 */
public class IssueStockReportView extends javax.swing.JDialog {
    private String ItemId = new String();
    private String UnitId= new String();
    private ListSelectionModel lstmodel ;
    private int Departmentid = 0;
 //   private String UnitId = new String();

    /**
     * Creates new form IssueStockReportView
     */
    public IssueStockReportView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
       
        initComponents();
        lstmodel = tblIssueStock.getSelectionModel();
        lstmodel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//       
        /*
         * centering the frame
         */
        setLocationRelativeTo(null);
     
       
        
    }
    /*
     * self written fucntuon
     */
   

    public void setItemId(String id){
        ItemId = id;
    }
    public String getItemId(){
        return ItemId;
    }
     public void setUnitId(String id){
       UnitId = id;
    }
    public String getUnitId(){
        return UnitId;
    }
    public void setItemName(String st){
        txtItemName.setText(st);
    }
    public String getItemName(){
         return txtItemName.getText().toString();
    }
    public void setTotalStockQuantity(String qty){
        txtTotalStockQuantity.setText(qty);
    }
    public String getTotalStockQuantity(){
        return txtTotalStockQuantity.getText().toString();
    }
     public void setRelativeStockQuantity(String qty){
        txtRelativeStockQuantity.setText(qty);
    }
    public String getRelativeStockQuantity(){
        return txtRelativeStockQuantity.getText().toString();
    }
    public void setComboRelativeUnitName(String[] st){
        DefaultComboBoxModel unitmodel = new DefaultComboBoxModel(st);
        ComboRelativeUnitName.setModel(unitmodel);
    }
    public void setComboRelativeUnitName(String st){
        ComboRelativeUnitName.setSelectedItem(st);
    }
    public String getComboRelativeUnitName(){
        return ComboRelativeUnitName.getSelectedItem().toString();
    }
    public int getScreenHeight(){
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

int height = gd.getDisplayMode().getHeight();
return height;
    }
    public int getScreenWidth(){
//         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        double width = screenSize.getWidth();
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

int width = gd.getDisplayMode().getWidth();
        return width;
    }
  

    public void setOnComboMenuNameSelect(Object[] data){
        setItemId(data[0].toString());
      //  setUnitId(data[2].toString());
    }
     //for store name
    public void setComboDepartmentName(Object[] name){
        DefaultComboBoxModel model = new DefaultComboBoxModel(name);
        ComboDepartmentName.setModel(model);
//        ComboDepartmentName.setSelectedIndex(0);
    }
    public void setComboDepartmentName(String selectedname){
        ComboDepartmentName.setSelectedItem(selectedname);
    }
    public String getComboDepartmentName(){
        return ComboDepartmentName.getSelectedItem().toString();
    }
    public JComboBox returnComboDepartmentName(){
    return ComboDepartmentName;
    }
    public JComboBox reutrnComboRelativeUnitName(){
        return ComboRelativeUnitName;
    }
 
   public void addPrintListener(ActionListener ListenForPrint){
       btnPrint.addActionListener(ListenForPrint);
   }
//   public void addSaveAsExcelListener(ActionListener ListenForSave){
//       btnSaveAsExcel.addActionListener(ListenForSave);
//   }
   public void addCancelReportListener(ActionListener ListenForCancel){
       btnReportCancel.addActionListener(ListenForCancel);
   }
  public void addListSelectionListener(ListSelectionListener ListenForSelect){
      lstmodel.addListSelectionListener(ListenForSelect);
  }
  public void addComboUnitNameListener(ActionListener listen){
      ComboRelativeUnitName.addActionListener(listen);
  }
  public void addComboDepartmentNameListener(ActionListener Listen){
      ComboDepartmentName.addActionListener(Listen);
  }
   public void refreshTableIssueStockReport(DefaultTableModel dtm){
       tblIssueStock.setModel(dtm);
   }
   public DefaultTableModel getTableIssueStockReport(){
       return (DefaultTableModel)tblIssueStock.getModel();
   }
     public void setDepartmentId(int id){
        Departmentid = id;
    }
    public int getDepartmentId(){
        return Departmentid;
    }
   public void setIssueStock(String[] data){
       setItemId(data[0]);
       setItemName(data[1]);
       setTotalStockQuantity(data[2]);
//      String unitname = data[3].toString().replaceAll("[0-9,.]", "");
//       setComboRelativeUnitName(unitname);
//       System.out.println(unitname);
   }
   

  
  
   public void clearAll(){
       setItemId("");
       setTotalStockQuantity("");
       setRelativeStockQuantity("");
       setItemName("");
     

   }
      public void AddSelectInCombo(JComboBox jc){
      jc.insertItemAt("SELECT", 0);
      jc.setSelectedIndex(0);
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DialogIssueItem = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTotalStockQuantity = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtRelativeStockQuantity = new javax.swing.JTextField();
        ComboRelativeUnitName = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        btnReportCancel = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblIssueStock = new javax.swing.JTable();
        ComboDepartmentName = new javax.swing.JComboBox();
        lblstorename = new javax.swing.JLabel();

        DialogIssueItem.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DialogIssueItem.setTitle("Item Stock Detail");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Item Name:");

        txtItemName.setEditable(false);
        txtItemName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Total Stock Quantity:");

        txtTotalStockQuantity.setEditable(false);
        txtTotalStockQuantity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTotalStockQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalStockQuantityActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Relative Stock Quantity:");

        txtRelativeStockQuantity.setEditable(false);
        txtRelativeStockQuantity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        ComboRelativeUnitName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ComboRelativeUnitName.setActionCommand("ComboUnitNameListener");

        javax.swing.GroupLayout DialogIssueItemLayout = new javax.swing.GroupLayout(DialogIssueItem.getContentPane());
        DialogIssueItem.getContentPane().setLayout(DialogIssueItemLayout);
        DialogIssueItemLayout.setHorizontalGroup(
            DialogIssueItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogIssueItemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DialogIssueItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DialogIssueItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtItemName)
                    .addComponent(txtTotalStockQuantity)
                    .addComponent(txtRelativeStockQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(ComboRelativeUnitName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        DialogIssueItemLayout.setVerticalGroup(
            DialogIssueItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogIssueItemLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(DialogIssueItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(DialogIssueItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTotalStockQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(DialogIssueItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRelativeStockQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboRelativeUnitName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        DialogIssueItemLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ComboRelativeUnitName, txtItemName, txtRelativeStockQuantity, txtTotalStockQuantity});

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sales Report");
        setModalityType(java.awt.Dialog.ModalityType.DOCUMENT_MODAL);

        jPanel4.setBackground(new java.awt.Color(251, 250, 250));

        btnReportCancel.setBackground(new java.awt.Color(255, 255, 255));
        btnReportCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        btnReportCancel.setText("Cancel");
        btnReportCancel.setActionCommand("ReportCancel");

        btnPrint.setBackground(new java.awt.Color(255, 255, 255));
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print.png"))); // NOI18N
        btnPrint.setText("Print");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReportCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(435, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReportCancel))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnPrint, btnReportCancel});

        btnReportCancel.setHorizontalTextPosition(SwingConstants.CENTER);
        btnReportCancel.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnPrint.setHorizontalTextPosition(SwingConstants.CENTER);
        btnPrint.setVerticalTextPosition(SwingConstants.BOTTOM);

        tblIssueStock.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblIssueStock.setModel(new javax.swing.table.DefaultTableModel(
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
        tblIssueStock.setRowHeight(25);
        jScrollPane2.setViewportView(tblIssueStock);
        tblIssueStock.getTableHeader().setReorderingAllowed(false);

        ComboDepartmentName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ComboDepartmentName.setActionCommand("ComboStoreName");

        lblstorename.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblstorename.setText("Department Name:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblstorename)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ComboDepartmentName, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblstorename, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboDepartmentName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTotalStockQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalStockQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalStockQuantityActionPerformed

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
            java.util.logging.Logger.getLogger(IssueStockReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueStockReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueStockReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueStockReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                IssueStockReportView dialog = new IssueStockReportView(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                   setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//                    }
//                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboDepartmentName;
    private javax.swing.JComboBox ComboRelativeUnitName;
    public javax.swing.JDialog DialogIssueItem;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReportCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblstorename;
    public javax.swing.JTable tblIssueStock;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtRelativeStockQuantity;
    private javax.swing.JTextField txtTotalStockQuantity;
    // End of variables declaration//GEN-END:variables
}
