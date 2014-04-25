/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.departmentissuereport;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
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
public class IssueReportView extends javax.swing.JDialog {
    private int ItemId;
    private int StoreID;

    /**
     * Creates new form IssueReportView
     */
    public IssueReportView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
       
        initComponents();
//          comboStoreName.setVisible(false);
//        jLabel4.setVisible(false);
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
    public void setComboItemName(String[] itemName){
        DefaultComboBoxModel itemmodel = new DefaultComboBoxModel(itemName);
        comboItemName.setModel(itemmodel);
    }
    public void setComboItemName(String itemName){
        comboItemName.setSelectedItem(itemName);
    }
    public String getComboItemName(){
        return comboItemName.getSelectedItem().toString();
    }
     public void setComboStoreName(String[] itemName){
        DefaultComboBoxModel itemmodel = new DefaultComboBoxModel(itemName);
        comboStoreName.setModel(itemmodel);
    }
    public void setComboBaseUnitName(String itemName){
        comboStoreName.setSelectedItem(itemName);
    }
    public String getComboStoreName(){
        return comboStoreName.getSelectedItem().toString();
    }
    public void setItemId(int id){
        ItemId = id;
    }
    public int getItemId(){
        return ItemId;
    }
    public void setStoreId(int id){
        StoreID = id;
    }
    public int getStoreId(){
        return StoreID;
    }
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
    public boolean getBooleanIncludeAllItemName(){
        return checkboxIncludeAll.isSelected();
    }
    public boolean getBooleanIncludeAllDepartment(){
        return checkboxIncludeAllDepartment.isSelected();
    }
    public void setCheckIncludeAllDepartmentVisible(boolean bl){
        checkboxIncludeAllDepartment.setVisible(bl);
    }
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
    public void setOnComboItemNameSelect(Object[] data){
        setItemId(Integer.parseInt(data[0].toString()));
//        System.out.println(getItemId());
       
    }
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
       tblReport.setModel(dtm);
   }
   public DefaultTableModel getTableReport(){
       return (DefaultTableModel)tblReport.getModel();
   }
   public void addComboItemNameSelectListener(ActionListener ListenForSelect){
       comboItemName.addActionListener(ListenForSelect);
   }
   public void addComboStoreNameListener(ActionListener ListenForSelect){
       comboStoreName.addActionListener(ListenForSelect);
   }
   public void addCheckIncludeAllListener(ItemListener Listen){
       checkboxIncludeAll.addItemListener(Listen);
   }
   public void  addCheckIncludeAllDepartmentListener(ItemListener Listen){
       checkboxIncludeAllDepartment.addItemListener(Listen);
   }
   public void addRadioIssueListener(ItemListener Listen){
       radioIssue.addItemListener(Listen);
       
   }
   public void addRadioIssueReturnListener(ItemListener Listen){
       radioIssueReturn.addItemListener(Listen);
   }
   public boolean getBooleanRadioButton(){
        return radioIssue.isSelected();
   }
   public void setlblReportTitle(String st){
       lblReportTitle.setText(st);
   }
   public void setlblStartDate(String st){
       lblStartDate.setText(st);
   }
   public void setlblEndDate(String st){
       lblEndDate.setText(st);
   }
   public void setlblStatus(String st){
       lblStatus.setText(st);
   }
   public String getlblStatus(){
       return lblStatus.getText();
   }
   public void checkRadioIssue(){
       radioIssue.setSelected(true);
       radioIssueReturn.setSelected(false);
   }
   public void uncheckedRadioIssue(){
       
       radioIssue.setSelected(false);
       radioIssueReturn.setSelected(true);
   }
   public String[] getIssueReport(){
       String[] data = new String[3];
       data[0] = String.valueOf(getItemId());
       data[1] = String.valueOf(getStoreId());
       data[2] = String.valueOf(getBooleanRadioButton());
       return data;
      // data[2] = getStartDate();
   }
   public Date[] getIssueReportDate(){
       Date[] date = new Date[2];
       date[0] = getStartDate();
       date[1] = getEndDate();
       return date;
   }
   public void clearAll(){
       setItemId(0);
       setStoreId(0);
       setStartDate(null);
       setEndDate(null);
       checkRadioIssue();
       if(comboStoreName.getModel().getSize() >=1){
       comboStoreName.setSelectedIndex(0);
       }
       if(comboItemName.getModel().getSize() >=1){
       comboItemName.setSelectedIndex(0);
       }

   }
     public void AddSelectInCombo(JComboBox jc){
      jc.insertItemAt("SELECT", 0);
      jc.setSelectedIndex(0);
    }
     public JComboBox returnComboStoreName(){
         return comboStoreName;
     }
     public JComboBox returnComboItemName(){
         return comboItemName;
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
     
     public Map getIssueItemAllParams(){
         Map param = new HashMap<>();
         param.put("title", "Issue Report of All Item");
         param.put("frmDate", getStartDate());
         param.put("toDate", getEndDate());
         return param;
     }
     
     public Map getIssueDepAll(){
         Map param = new HashMap<>();
         param.put("title", "Issue Report of All Item for " + getComboStoreName());
         param.put("frmDate", getStartDate());
         param.put("toDate", getEndDate());
         param.put("department", getStoreId());
         return param;
     }
     
     public Map getIssueAllDepItem(){
         Map param = new HashMap<>();
         param.put("title", "Issue Return Report of " + getComboItemName());
         param.put("frmDate", getStartDate());
         param.put("toDate", getEndDate());
         param.put("item", getItemId());
         return param;
     }
      
     public Map getIssueDepItem(){
         Map param = new HashMap<>();
         param.put("title", "Issue Report of " +getComboItemName() + " for " + getComboStoreName());
         param.put("frmDate", getStartDate());
         param.put("toDate", getEndDate());
         param.put("item", getItemId());
         param.put("department", getStoreId());
         return param;
     }
     
     public Map getReturnItemAllParams(){
         Map param = new HashMap<>();
         param.put("title", "Issue Return Report of All Item");
         param.put("frmDate", getStartDate());
         param.put("toDate", getEndDate());
         return param;
     }
     
     public Map getReturnDepAll(){
         Map param = new HashMap<>();
         param.put("title", "Issue Return Report of All Item for " +getComboStoreName());
         param.put("frmDate", getStartDate());
         param.put("toDate", getEndDate());
         param.put("department", getStoreId());
         return param;
     }
     
     public Map getReturnAllDepItem(){
         Map param = new HashMap<>();
         param.put("title", "Issue Return Report of " +getComboItemName());
         param.put("frmDate", getStartDate());
         param.put("toDate", getEndDate());
         param.put("item", getItemId());
         return param;
     }
      
     public Map getReturnDepItem(){
         Map param = new HashMap<>();
         param.put("title", "Issue Report of " +getComboItemName() + " for " + getComboStoreName());
         param.put("frmDate", getStartDate());
         param.put("toDate", getEndDate());
         param.put("item", getItemId());
         param.put("department", getStoreId());
         return param;
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
        tblReport = new javax.swing.JTable(){
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
                    if(type.equals("Total")){
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
        comboItemName = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        radioIssue = new javax.swing.JRadioButton();
        radioIssueReturn = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        StartDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        EndDateChooser = new com.toedter.calendar.JDateChooser();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        comboStoreName = new javax.swing.JComboBox();
        lblStatus = new javax.swing.JLabel();
        checkboxIncludeAll = new javax.swing.JCheckBox();
        checkboxIncludeAllDepartment = new javax.swing.JCheckBox();

        tblReport.setBorder(new javax.swing.border.MatteBorder(null));
        tblReport.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblReport.setModel(new javax.swing.table.DefaultTableModel(
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
        tblReport.setRowHeight(20);
        jScrollPane1.setViewportView(tblReport);

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
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
        setTitle("Item Wise Issue/IssueReturn Report");
        setModalityType(java.awt.Dialog.ModalityType.DOCUMENT_MODAL);
        setResizable(false);

        comboItemName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboItemName.setActionCommand("ComboItemName");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Item Name:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Report Category"));

        buttonGroup1.add(radioIssue);
        radioIssue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioIssue.setText("Issue");

        buttonGroup1.add(radioIssueReturn);
        radioIssueReturn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioIssueReturn.setText("Issue Return");
        radioIssueReturn.setToolTipText("");
        radioIssueReturn.setActionCommand("IssueReturn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(radioIssue)
                .addGap(87, 87, 87)
                .addComponent(radioIssueReturn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioIssue)
                    .addComponent(radioIssueReturn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
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

        btnOk.setText("Ok");

        btnCancel.setText("Cancel");

        comboStoreName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboStoreName.setActionCommand("ComboItemName");

        lblStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        checkboxIncludeAll.setText("Include All");

        checkboxIncludeAllDepartment.setText("Include All Department");
        checkboxIncludeAllDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxIncludeAllDepartmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(checkboxIncludeAll, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboStoreName, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkboxIncludeAllDepartment))
                .addGap(20, 20, 20))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancel, btnOk});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(checkboxIncludeAllDepartment)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboStoreName, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkboxIncludeAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancel, btnOk});

        checkboxIncludeAllDepartment.setVisible(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkboxIncludeAllDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxIncludeAllDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkboxIncludeAllDepartmentActionPerformed

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
            java.util.logging.Logger.getLogger(IssueReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                IssueReportView dialog = new IssueReportView(new javax.swing.JFrame(), true);
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
    public javax.swing.JDialog DailogReport;
    private com.toedter.calendar.JDateChooser EndDateChooser;
    private com.toedter.calendar.JDateChooser StartDateChooser;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReportCancel;
    private javax.swing.JButton btnSaveAsExcel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox checkboxIncludeAll;
    private javax.swing.JCheckBox checkboxIncludeAllDepartment;
    public javax.swing.JComboBox comboItemName;
    public javax.swing.JComboBox comboStoreName;
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
    private javax.swing.JLabel lblStatus;
    private javax.swing.JRadioButton radioIssue;
    private javax.swing.JRadioButton radioIssueReturn;
    public javax.swing.JTable tblReport;
    // End of variables declaration//GEN-END:variables
}
