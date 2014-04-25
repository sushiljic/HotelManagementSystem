/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.discountreport;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author SUSHIL
 */
public class DiscountReportView extends javax.swing.JDialog {
    private String ItemId = new String();
//    private String UnitId = new String();
    private int Departmentid = 0;

    /**
     * Creates new form IssueReportView
     */
    public DiscountReportView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
       
        initComponents();
       //   comboBaseUnitName.setVisible(false);
      //  jLabel4.setVisible(false);
        setButtonForEnter(btnOk);
        setButtonForEnter(btnCancel);
        setButtonForEnter(btnPrint);
        setButtonForEnter(btnReportCancel);
        setButtonForEnter(btnSaveAsExcel);
         /*
         * centering the frame
         */
        setLocationRelativeTo(null);
               
       
        
    }
    /*
     * self written fucntuon
     */
//    public void setComboMenuName(String[] itemName){
//        DefaultComboBoxModel itemmodel = new DefaultComboBoxModel(itemName);
//        comboMenuName.setModel(itemmodel);
//    }
//    public void setComboMenuName(String itemName){
//        comboMenuName.setSelectedItem(itemName);
//    }
//    public String getComboMenuName(){
//        return comboMenuName.getSelectedItem().toString();
//    }
//     public void setComboBaseUnitName(String[] itemName){
//        DefaultComboBoxModel itemmodel = new DefaultComboBoxModel(itemName);
////        comboBaseUnitName.setModel(itemmodel);
//    }
//    public void setComboBaseUnitName(String itemName){
////        comboBaseUnitName.setSelectedItem(itemName);
//    }
//    public String getComboBaseUnitName(){
//        return comboBaseUnitName.getSelectedItem().toString();
//    }
//    public void setMenuId(String id){
//        ItemId = id;
//    }
//    public String getMenuId(){
//        return ItemId;
//    }
////    public void setUnitId(String id){
//        UnitId = id;
//    }
//    public String getUnitId(){
//        return UnitId;
//    }
    public Date getStartDate(){
        return StartDateChooser.getDate();
    }
    public void setStartDate(Date dt){
        StartDateChooser.setDate(dt);
    }
    public Date getEndDate(){
      return   EndDateChooser.getDate();
    }
    public void setEndDate(Date dt){
        EndDateChooser.setDate(dt);
    }
        public void setDepartmentId(int id){
        Departmentid = id;
    }
    public int getDepartmentId(){
        return Departmentid;
    }
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
//    public boolean getBooleanIncludeAll(){
//        if(checkboxIncludeAll.isSelected()){
//            return true;
//        }
//        else 
//            return false;
//    }
//     public Date getStartDateString(){
//        return StartDateChooser.getDate();
//    }
//    public void setStartDate(Date dt){
//        StartDateChooser.setDate(dt);
//    }
//    public Date getEndDate(){
//      return   EndDateChooser.getDate();
//    }
//    public void setEndDate(Date dt){
//        EndDateChooser.setDate(dt);
//    }
//    public void setOnComboMenuNameSelect(Object[] data){
//        setMenuId(data[0].toString());
//        //setUnitId(data[2].toString());
//    }
   public void addOkListener(ActionListener ListenForOk){
       btnOk.addActionListener(ListenForOk);
   }
   public void addCancelListener(ActionListener ListenForCancel){
       btnCancel.addActionListener(ListenForCancel);
   }
   public void addPrintListener(ActionListener ListenForPrint){
       btnPrint.addActionListener(ListenForPrint);
   }
   public void addSaveAsExcelListener(ActionListener ListenForSave){
       btnSaveAsExcel.addActionListener(ListenForSave);
   }
   public void addCancelReportListener(ActionListener ListenForCancel){
       btnReportCancel.addActionListener(ListenForCancel);
   }
   public void refreshTableReport(DefaultTableModel dtm){
       tblDiscountReport.setModel(dtm);
   }
     public void addComboDepartmentListener(ActionListener Listen){
       ComboDepartmentName.addActionListener(Listen);
   }
   public DefaultTableModel getTableReport(){
       return (DefaultTableModel)tblDiscountReport.getModel();
   }
//   public void addComboMenuNameSelectListener(ActionListener ListenForSelect){
//       comboMenuName.addActionListener(ListenForSelect);
//   }
////   public void addComboBaseUnitListener(ActionListener ListenForSelect){
////       comboBaseUnitName.addActionListener(ListenForSelect);
////   }
//   public void addCheckIncludeAllListener(ItemListener Listen){
//       checkboxIncludeAll.addItemListener(Listen);
//   }
//   public boolean getBooleanRadioButton(){
//       if(radioIssue.isSelected()){
//           return true;
//       }
//       else {
//           return false;
//       }
//   }
   public void setlblReportTitle(String st){
       lblReportTitle.setText(st);
   }
   public void setlblStartDate(String st){
       lblStartDate.setText(st);
   }
   public void setlblEndDate(String st){
       lblEndDate.setText(st);
   }
//   public void checkRadioIssue(){
//       radioIssue.setSelected(true);
//       radioIssueReturn.setSelected(false);
//   }
//   public void uncheckedRadioIssue(){
//       
//       radioIssue.setSelected(false);
//       radioIssueReturn.setSelected(true);
//   }
//   public String[] getIssueReport(){
//       String[] data = new String[3];
//       data[0] = getMenuId();
//       data[1] = getUnitId();
//      // data[2] = String.valueOf(getBooleanRadioButton());
//       return data;
//      // data[2] = getStartDate();
//   }
   public Date[] getComplimentaryReportDate(){
       Date[] date = new Date[2];
       date[0] = getStartDate();
       date[1] = getEndDate();
       return date;
   }
   public void clearAll(){
     //  setMenuId("");
      // setUnitId("");
       setStartDate(null);
       setEndDate(null);
   //    checkRadioIssue();

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
      
      public Map getReportParam(){
          Map para = new HashMap<>();
          para.put("title","Discount Report of " + getComboDepartmentName());
          para.put("frmDate",getStartDate());
          para.put("toDate", getEndDate());
          para.put("depId", getDepartmentId());
          return para;
      }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        DailogReport = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDiscountReport = new javax.swing.JTable(){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
                Component c = super.prepareRenderer(renderer,row,column);
                if(!isRowSelected(row)){
                    c.setBackground(getBackground());
                    //   c.setBackground(new Color(227,197,132));
                    int ModelRow = convertRowIndexToModel(row);
                    // int ModelColumn = convertColumnIndexToModel(column);

                    //getting the value of boolean for true and false and changin color
                    Object type = getModel().getValueAt(ModelRow,0);
                    //  Boolean hybridType = (Boolean)getModel().getValueAt(ModelRow,9);
                    //            if(type == "Cash" ){
                        //                c.setBackground(getBackground());
                        //                /* if(true == hybridType ){
                            //                    getColumnModel().getColumn(9).s
                            //                }
                        //                */
                        //            }
                    //            if("Credit" == type ){
                        //                c.setBackground(new Color(205,185,215));
                        //            }
                    if(type.equals("Total Discount Amount")){
                        c.setBackground(new Color(227,197,132));

                    }

                }
                return c;
            }

        };
        lblReportTitle = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblStartDate = new javax.swing.JLabel();
        lblEndDate = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnReportCancel = new javax.swing.JButton();
        btnSaveAsExcel = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnOk = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        StartDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        EndDateChooser = new com.toedter.calendar.JDateChooser();
        btnCancel = new javax.swing.JButton();
        ComboDepartmentName = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        tblDiscountReport.setBorder(new javax.swing.border.MatteBorder(null));
        tblDiscountReport.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblDiscountReport.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDiscountReport.setRowHeight(20);
        jScrollPane1.setViewportView(tblDiscountReport);

        lblReportTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel6.setText("Period:");

        jLabel7.setText("to");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/excel.png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(251, 250, 250));

        btnReportCancel.setBackground(new java.awt.Color(255, 255, 255));
        btnReportCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        btnReportCancel.setText("Cancel");
        btnReportCancel.setActionCommand("ReportCancel");

        btnSaveAsExcel.setBackground(new java.awt.Color(255, 255, 255));
        btnSaveAsExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/xl.png"))); // NOI18N
        btnSaveAsExcel.setText("SaveAsExcel");

        btnPrint.setBackground(new java.awt.Color(255, 255, 255));
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print.png"))); // NOI18N
        btnPrint.setText("Print");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSaveAsExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReportCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSaveAsExcel)
                    .addComponent(btnReportCancel))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnPrint, btnReportCancel, btnSaveAsExcel});

        btnReportCancel.setHorizontalTextPosition(SwingConstants.CENTER);
        btnReportCancel.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnSaveAsExcel.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSaveAsExcel.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnPrint.setHorizontalTextPosition(SwingConstants.CENTER);
        btnPrint.setVerticalTextPosition(SwingConstants.BOTTOM);

        javax.swing.GroupLayout DailogReportLayout = new javax.swing.GroupLayout(DailogReport.getContentPane());
        DailogReport.getContentPane().setLayout(DailogReportLayout);
        DailogReportLayout.setHorizontalGroup(
            DailogReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DailogReportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DailogReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                    .addGroup(DailogReportLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DailogReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(DailogReportLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(lblEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblReportTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DailogReportLayout.setVerticalGroup(
            DailogReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DailogReportLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(DailogReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(DailogReportLayout.createSequentialGroup()
                        .addComponent(lblReportTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(DailogReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(lblStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEndDate)
                            .addComponent(jLabel7)))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        DailogReportLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel6, lblEndDate, lblStartDate});

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Discount Report");
        setModalityType(java.awt.Dialog.ModalityType.DOCUMENT_MODAL);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Discount Report"));

        btnOk.setText("Ok");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Report Period"));

        StartDateChooser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Start Date:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("End Date:");

        EndDateChooser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(EndDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(StartDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                .addGap(63, 63, 63))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(StartDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EndDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnCancel.setText("Cancel");

        ComboDepartmentName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Department Name:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(85, 85, 85)
                        .addComponent(ComboDepartmentName, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancel, btnOk});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboDepartmentName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancel, btnOk});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(DiscountReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DiscountReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DiscountReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DiscountReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DiscountReportView dialog = new DiscountReportView(new javax.swing.JFrame(), true);
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
    public javax.swing.JDialog DailogReport;
    private com.toedter.calendar.JDateChooser EndDateChooser;
    private com.toedter.calendar.JDateChooser StartDateChooser;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReportCancel;
    private javax.swing.JButton btnSaveAsExcel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEndDate;
    private javax.swing.JLabel lblReportTitle;
    private javax.swing.JLabel lblStartDate;
    public javax.swing.JTable tblDiscountReport;
    // End of variables declaration//GEN-END:variables
}
