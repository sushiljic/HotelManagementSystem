/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.menulistreport;

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
public class MenuListReportView extends javax.swing.JDialog {
    private int CategoryId = 0 ;
    private int SubCategoryId =0;
    private int Departmentid =0;
//    private int 

    /**
     * Creates new form IssueReportView
     * @param parent
     * @param modal
     */
    public MenuListReportView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
       
        initComponents();
//          comboBaseUnitName.setVisible(false);
          comboSubCategoryName.setEditable(false);
       SubCategoryPanel.setVisible(false);
     
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
    public void setComboGroupName(String[] itemName){
        DefaultComboBoxModel itemmodel = new DefaultComboBoxModel(itemName);
        comboGroupName.setModel(itemmodel);
    }
    public void setComboGroupName(String itemName){
        comboGroupName.setSelectedItem(itemName);
    }
    public String getComboGroupName(){
        return comboGroupName.getSelectedItem().toString();
    }
     public void setComboCategoryName(String[] itemName){
        DefaultComboBoxModel itemmodel = new DefaultComboBoxModel(itemName);
        comboSubCategoryName.setModel(itemmodel);
    }
    public void setComboCategoryName(String itemName){
        comboSubCategoryName.setSelectedItem(itemName);
    }
    public String getComboCategoryName(){
        return comboSubCategoryName.getSelectedItem().toString();
    }
    public void setGroupId(int id){
        CategoryId = id;
    }
    public int getGroupId(){
        return CategoryId;
    }
    public void setCategoryId(int id){
        SubCategoryId = id;
    }
    public int getCategoryId(){
        return SubCategoryId;
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
//    public Date getStartDate(){
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
    public void setBooleanIncludeAllCategory(boolean bn){
        checkboxIncludeAllCategory.setSelected(bn);
    }
    public void setBooleanIncludeAllSubCategory(boolean bn){
        checkboxIncludeAllSubCategory.setSelected(bn);
    }
    public boolean getBooleanIncludeAllCategory(){
        return checkboxIncludeAllCategory.isSelected();
    }
    public boolean getBooleanIncludeAllSubCategory(){
        return checkboxIncludeAllSubCategory.isSelected();
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
        setGroupId(Integer.parseInt(data[0].toString()));
        setCategoryId(Integer.parseInt(data[2].toString()));
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
    public void addComboDepartmentListener(ActionListener Listen){
       ComboDepartmentName.addActionListener(Listen);
   }
   public void refreshTableReport(DefaultTableModel dtm){
       tblReport.setModel(dtm);
   }
   public DefaultTableModel getTableReport(){
       return (DefaultTableModel)tblReport.getModel();
   }
   public void addComboCategoryNameSelectListener(ActionListener ListenForSelect){
       comboGroupName.addActionListener(ListenForSelect);
   }
   public void addComboSubCategoryListener(ActionListener ListenForSelect){
       comboSubCategoryName.addActionListener(ListenForSelect);
   }
   public void addCheckIncludeAllListener(ItemListener Listen){
       checkboxIncludeAllCategory.addItemListener(Listen);
   }
    public void addCheckIncludeAllSubCategoryListener(ItemListener Listen){
       checkboxIncludeAllSubCategory.addItemListener(Listen);
   }
   public boolean getBooleanAllButton(){
        return checkAll.isSelected();
   }
  public boolean getBooleanRetailPriceButton(){
      return checkRetailPrice.isSelected();
  }
  public boolean getBooleanWholesalePriceButton(){
      return checkWholesalePrice.isSelected();
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
   public void checkRadioIssue(){
       checkAll.setSelected(true);
//       radioIssueReturn.setSelected(false);
   }
   public void uncheckedRadioIssue(){
       
       checkAll.setSelected(false);
       checkRetailPrice.setSelected(true);
   }
   public String[] getIssueReport(){
       String[] data = new String[3];
       data[0] = String.valueOf(getGroupId());
       data[1] = String.valueOf(getCategoryId());
       data[2] = String.valueOf(getBooleanAllButton());
       return data;
      // data[2] = getStartDate();
   }
   public Date[] getIssueReportDate(){
       Date[] date = new Date[2];
//       date[0] = getStartDate();
//       date[1] = getEndDate();
       return date;
   }
   public void clearAll(){
       setGroupId(0);
       setCategoryId(0);
       
//       setStartDate(null);
//       setEndDate(null);
       checkRadioIssue();
//        System.out.println("wala1");
       if(comboSubCategoryName.getModel().getSize() >1){
       comboSubCategoryName.setSelectedIndex(0);
       }
       if(comboGroupName.getModel().getSize() >1){
       comboGroupName.setSelectedIndex(0);
       }
       if(ComboDepartmentName.getModel().getSize() >1){
           ComboDepartmentName.setSelectedIndex(0);
       }
//        System.out.println("wala2");

   }
     public void AddSelectInCombo(JComboBox jc){
      jc.insertItemAt("SELECT", 0);
      jc.setSelectedIndex(0);
    }
     public JComboBox returnComboBaseUnitName(){
         return comboSubCategoryName;
     }
     public JComboBox returnComboItemName(){
         return comboGroupName;
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
      
     public Map getAllParam(){
         Map param = new HashMap<>();
         param.put("title", "Menu Report of " +getComboDepartmentName());
         param.put("depId", getDepartmentId());
//         param.put("frmDate", getStartDate());
//         param.put("toDate", getEndDate());
         return param;
     }
     
     public Map getAllParent(){
         Map param = new HashMap<>();
         param.put("title", "Menu Report of " +getComboDepartmentName());
         param.put("parentId", getGroupId()); //parent
         param.put("depId", getDepartmentId());
         
//         param.put("frmDate", getStartDate());
//         param.put("toDate", getEndDate());
         return param;
     }
      
     public Map getAllParentCategory(){
          Map param = new HashMap<>();
         param.put("title", "Menu Report of " +getComboDepartmentName());
         param.put("parentId", getGroupId()); //parent
         param.put("categoryId", getCategoryId());
         param.put("depId", getDepartmentId());
         
//         param.put("frmDate", getStartDate());
//         param.put("toDate", getEndDate());
         return param;
     }
     
     public Map getRetailParam(){
         Map param = new HashMap<>();
         param.put("title", "Menu Report of " +getComboDepartmentName());
         param.put("depId", getDepartmentId());
//         param.put("frmDate", getStartDate());
//         param.put("toDate", getEndDate());
         return param;
     }
     
     public Map getRetailParent(){
         Map param = new HashMap<>();
         param.put("title", "Menu Report of " +getComboDepartmentName());
         param.put("parentId", getGroupId()); //parent
         param.put("depId", getDepartmentId());
         
//         param.put("frmDate", getStartDate());
//         param.put("toDate", getEndDate());
         return param;
     }
      
     public Map getRetailParentCategory(){
          Map param = new HashMap<>();
         param.put("title", "Menu Report of " +getComboDepartmentName());
         param.put("parentId", getGroupId()); //parent
         param.put("categoryId", getCategoryId());
         param.put("depId", getDepartmentId());
         
//         param.put("frmDate", getStartDate());
//         param.put("toDate", getEndDate());
         return param;
     }
     public Map getWholesaleParam(){
         Map param = new HashMap<>();
         param.put("title", "Menu Report of " +getComboDepartmentName());
         param.put("depId", getDepartmentId());
//         param.put("frmDate", getStartDate());
//         param.put("toDate", getEndDate());
         return param;
     }
     
     public Map getWholesaleParent(){
         Map param = new HashMap<>();
         param.put("title", "Menu Report of " +getComboDepartmentName());
         param.put("parentId", getGroupId()); //parent
         param.put("depId", getDepartmentId());
         
//         param.put("frmDate", getStartDate());
//         param.put("toDate", getEndDate());
         return param;
     }
      
     public Map getWholesaleParentCategory(){
          Map param = new HashMap<>();
         param.put("title", "Menu Report of " +getComboDepartmentName());
         param.put("parentId", getGroupId()); //parent
         param.put("categoryId", getCategoryId());
         param.put("depId", getDepartmentId());
         
//         param.put("frmDate", getStartDate());
//         param.put("toDate", getEndDate());
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
        jPanel1 = new javax.swing.JPanel();
        checkAll = new javax.swing.JRadioButton();
        checkRetailPrice = new javax.swing.JRadioButton();
        checkWholesalePrice = new javax.swing.JRadioButton();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        SubCategoryPanel = new javax.swing.JPanel();
        comboSubCategoryName = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        checkboxIncludeAllSubCategory = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        checkboxIncludeAllCategory = new javax.swing.JCheckBox();
        comboGroupName = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        ComboDepartmentName = new javax.swing.JComboBox();

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
        setTitle("Issue/IssueReturn Report");
        setModalityType(java.awt.Dialog.ModalityType.DOCUMENT_MODAL);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Report Category"));

        buttonGroup1.add(checkAll);
        checkAll.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        checkAll.setSelected(true);
        checkAll.setText("All");
        checkAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAllActionPerformed(evt);
            }
        });

        buttonGroup1.add(checkRetailPrice);
        checkRetailPrice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        checkRetailPrice.setText("Retail Price");
        checkRetailPrice.setToolTipText("");
        checkRetailPrice.setActionCommand("IssueReturn");

        buttonGroup1.add(checkWholesalePrice);
        checkWholesalePrice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        checkWholesalePrice.setText("WholeSale Price");
        checkWholesalePrice.setToolTipText("");
        checkWholesalePrice.setActionCommand("IssueReturn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(checkAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(checkRetailPrice)
                .addGap(29, 29, 29)
                .addComponent(checkWholesalePrice)
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkAll)
                    .addComponent(checkRetailPrice)
                    .addComponent(checkWholesalePrice))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        btnOk.setText("Ok");

        btnCancel.setText("Cancel");

        SubCategoryPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        comboSubCategoryName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboSubCategoryName.setActionCommand("ComboItemName");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Sub Category Name:");

        checkboxIncludeAllSubCategory.setText("Include All");
        checkboxIncludeAllSubCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxIncludeAllSubCategoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SubCategoryPanelLayout = new javax.swing.GroupLayout(SubCategoryPanel);
        SubCategoryPanel.setLayout(SubCategoryPanelLayout);
        SubCategoryPanelLayout.setHorizontalGroup(
            SubCategoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SubCategoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(74, 74, 74)
                .addComponent(comboSubCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(SubCategoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(SubCategoryPanelLayout.createSequentialGroup()
                    .addGap(152, 152, 152)
                    .addComponent(checkboxIncludeAllSubCategory)
                    .addContainerGap(153, Short.MAX_VALUE)))
        );
        SubCategoryPanelLayout.setVerticalGroup(
            SubCategoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SubCategoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SubCategoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboSubCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(SubCategoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(SubCategoryPanelLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(checkboxIncludeAllSubCategory)
                    .addContainerGap(15, Short.MAX_VALUE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        checkboxIncludeAllCategory.setText("Include All");
        checkboxIncludeAllCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxIncludeAllCategoryActionPerformed(evt);
            }
        });

        comboGroupName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboGroupName.setActionCommand("ComboItemName");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Category Name");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkboxIncludeAllCategory)
                .addGap(41, 41, 41)
                .addComponent(comboGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkboxIncludeAllCategory))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Department Name:");

        ComboDepartmentName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(85, 85, 85)
                .addComponent(ComboDepartmentName, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboDepartmentName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SubCategoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancel, btnOk});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SubCategoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancel, btnOk});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAllActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkAllActionPerformed

    private void checkboxIncludeAllCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxIncludeAllCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkboxIncludeAllCategoryActionPerformed

    private void checkboxIncludeAllSubCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxIncludeAllSubCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkboxIncludeAllSubCategoryActionPerformed

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
            java.util.logging.Logger.getLogger(MenuListReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuListReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuListReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuListReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MenuListReportView dialog = new MenuListReportView(new javax.swing.JFrame(), true);
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
    public javax.swing.JPanel SubCategoryPanel;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReportCancel;
    private javax.swing.JButton btnSaveAsExcel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton checkAll;
    private javax.swing.JRadioButton checkRetailPrice;
    private javax.swing.JRadioButton checkWholesalePrice;
    private javax.swing.JCheckBox checkboxIncludeAllCategory;
    private javax.swing.JCheckBox checkboxIncludeAllSubCategory;
    public javax.swing.JComboBox comboGroupName;
    public javax.swing.JComboBox comboSubCategoryName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEndDate;
    private javax.swing.JLabel lblReportTitle;
    private javax.swing.JLabel lblStartDate;
    public javax.swing.JTable tblReport;
    // End of variables declaration//GEN-END:variables
}
