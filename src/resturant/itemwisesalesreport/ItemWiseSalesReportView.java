/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.itemwisesalesreport;

import java.awt.Color;
import java.awt.Component;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author SUSHIL
 */
public class ItemWiseSalesReportView extends javax.swing.JDialog {
    private String MenuId = new String();
 //   private String UnitId = new String();
    private int Departmentid = 0;

    /**
     * Creates new form ItemWiseSalesReportView
     * @param parent
     * @param modal
     */
    public ItemWiseSalesReportView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
       
        initComponents();
//          comboBaseUnitName.setVisible(false);
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
       DialogPeriodType.setLocation(getScreenWidth()/2-150, getScreenHeight()/2-360);
       
        
    }
    /*
     * self written fucntuon
     */
    public void setComboMenuName(String[] itemName){
        DefaultComboBoxModel itemmodel = new DefaultComboBoxModel(itemName);
        comboMenuName.setModel(itemmodel);
    }
    public void setComboMenuName(String itemName){
        comboMenuName.setSelectedItem(itemName);
    }
    public String getComboMenuName(){
        return comboMenuName.getSelectedItem().toString();
    }
//     public void setComboBaseUnitName(String[] itemName){
//        DefaultComboBoxModel itemmodel = new DefaultComboBoxModel(itemName);
//        comboBaseUnitName.setModel(itemmodel);
//    }
//    public void setComboBaseUnitName(String itemName){
//        comboBaseUnitName.setSelectedItem(itemName);
//    }
//    public String getComboBaseUnitName(){
//        return comboBaseUnitName.getSelectedItem().toString();
//    }
    public void setMenuId(String id){
        MenuId = id;
    }
    public String getMenuId(){
        return MenuId;
    }
       public void setDepartmentId(int id){
        Departmentid = id;
    }
    public int getDepartmentId(){
        return Departmentid;
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
     public void setComboReportType(String itemName){
        ComboPeriodType.setSelectedItem(itemName);
    }
    public String getComboReportType(){
        return ComboPeriodType.getSelectedItem().toString();
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
//    public void setUnitId(String id){
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
    public void showMonthPanel(){
        MonthPanel.setVisible(true);
    }
    public void hideMonthPanel(){
        MonthPanel.setVisible(false);
    }
     public void showDayChooserPanel(){
        DayChooserPanel.setVisible(true);
    }
    public void hideDayChooserPanel(){
        DayChooserPanel.setVisible(false);
    }
    public boolean getBooleanIncludeAll(){
        return checkboxIncludeAll.isSelected();
    }
    public void setTrackableStatus(boolean flag){
        radioTrackable.setSelected(flag);
    }
    public void setNonTrackableStatus(boolean flag){
        radioNontrackable.setSelected(flag);
    }
    public void setBothStatus(boolean flag){
        radioBoth.setSelected(flag);
    }
    public boolean getTrackableStatus(){
        return radioTrackable.isSelected();
    }
    public boolean getNonTrackableStatus(){
        return radioNontrackable.isSelected();
    }
    public boolean getBothStatus(){
        return radioBoth.isSelected();
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
    public void setOnComboMenuNameSelect(Object[] data){
        setMenuId(data[0].toString());
      //  setUnitId(data[2].toString());
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
   public void addDayChooserListener(PropertyChangeListener ListenForClick){
     //  DayChooser.add
       DayChooser.addPropertyChangeListener(ListenForClick);
   }
   public void addMonthChooserListener(PropertyChangeListener ListenForClick){
       MonthChooser.addPropertyChangeListener(ListenForClick);
   }
    public void addComboDepartmentListener(ActionListener Listen){
       ComboDepartmentName.addActionListener(Listen);
   }
    public void addRadioTrackableListener(ActionListener Listen){
        radioTrackable.addActionListener(Listen);
    }
    public void addRadioNonTrackableListener(ActionListener Listen){
        radioNontrackable.addActionListener(Listen);
    }
    public void addRadioBothListener(ActionListener listen){
        radioBoth.addActionListener(listen);
    }
   public void refreshTableReport(DefaultTableModel dtm){
       tblReport.setModel(dtm);
   }
   public DefaultTableModel getTableReport(){
       return (DefaultTableModel)tblReport.getModel();
   }
 public void addComboMenuNameSelectListener(ActionListener ListenForSelect){
       comboMenuName.addActionListener(ListenForSelect);
   }
//   public void addComboBaseUnitListener(ActionListener ListenForSelect){
//       comboBaseUnitName.addActionListener(ListenForSelect);
//   }
   public void addCheckIncludeAllListener(ItemListener Listen){
       checkboxIncludeAll.addItemListener(Listen);
   }
   public void addComboReportTypeListener(ActionListener ListenForSelect){
       ComboPeriodType.addActionListener(ListenForSelect);
   }
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
   public void setEditableStartDateFalse(){
       StartDateChooser.setEnabled(false);
   }
   public void setEditableStartDateTrue(){
       StartDateChooser.setEnabled(true);
   }
   public void setEditableEndDateTrue(){
       EndDateChooser.setEnabled(true);
   }
    public void setEditableEndDateFalse(){
       EndDateChooser.setEnabled(false);
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
   public String[] getItemWiseSalesReport(){
       String[] data = new String[1];
       data[0] = getMenuId();
    //   data[1] = getUnitId();
     //  data[2] = String.valueOf(getBooleanRadioButton());
       return data;
      // data[2] = getStartDate();
   }
   public Date[] getItemWiseSalesReportDate(){
       Date[] date = new Date[2];
       date[0] = getStartDate();
       date[1] = getEndDate();
       return date;
   }
   public void clearAll(){
       setMenuId("");
    //   setUnitId("");
       setStartDate(null);
       setEndDate(null);
       radioBoth.setSelected(true);
//       checkRadioIssue();

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
      
      public void setDailyDate(Date d){
          pDay = d;
      }
        public void AddSelectInCombo(JComboBox jc){
      jc.insertItemAt("SELECT", 0);
      jc.setSelectedIndex(0);
    }
        public JComboBox returnMenuName(){
            return comboMenuName;
        }

    public JRadioButton getRadioBoth() {
        return radioBoth;
    }

    public JRadioButton getRadioNontrackable() {
        return radioNontrackable;
    }

    public JRadioButton getRadioTrackable() {
        return radioTrackable;
    }
        
      
      public Map getDailyAllParam(){
          Map para = new HashMap<>();
          para.put("pDay",pDay);
          para.put("title", "Daily Sales Report of All Item for " +getComboDepartmentName());
          para.put("depId", getDepartmentId());
          return para;
      }
      
      public Map getDailyAllTrack(){
          Map para = new HashMap<>();
          para.put("pDay",pDay);
          para.put("title", "Daily Sales Report fo All Trackable Item for " +getComboDepartmentName());
          para.put("depId", getDepartmentId());
          return para;
      }
      public Map getDailyAllUnTrack(){
          Map para = new HashMap<>();
          para.put("pDay",pDay);
          para.put("title", "Daily Sales Report of All Untrackable Item for " +getComboDepartmentName());
          para.put("depId", getDepartmentId());
          return para;
      }
      
      public Map getMonthlyAllParam(){
          Map para = new HashMap<>();
          para.put("frmDate",getStartDate());
          para.put("toDate",getEndDate());
          para.put("title", "Monthly Sales Report of All Item for " +getComboDepartmentName());
          para.put("depId", getDepartmentId());
          return para;
      }
      
      public Map getMonthlyAllTrack(){
          Map para = new HashMap<>();
          para.put("frmDate",getStartDate());
          para.put("toDate",getEndDate());
          para.put("title", "Monthly Sales Report of All Trackable Item for " +getComboDepartmentName());
          para.put("depId", getDepartmentId());
          return para;
      }
      
      public Map getMonthlyAllUnTrack(){
          Map para = new HashMap<>();
          para.put("frmDate",getStartDate());
          para.put("toDate",getEndDate());
          para.put("title", "Monthly Sales Report fo All Untrackable Item for " +getComboDepartmentName());
          para.put("depId", getDepartmentId());
          return para;
      }
      
      public Map getDailyParam(){
          Map para = new HashMap<>();
          para.put("pDay",pDay);
          para.put("pId", Integer.parseInt(getMenuId()));
          para.put("title", "Daily Item Sales Report of " +getComboMenuName() + " of " +getComboDepartmentName());
          para.put("depId", getDepartmentId());
          return para;
      }
      
     public Map getDailyTrack(){
          Map para = new HashMap<>();
          para.put("pDay",pDay);
          para.put("pId", Integer.parseInt(getMenuId()));
          para.put("title", "Daily Sales Report of Trackable Item " +getComboMenuName() + " of " +getComboDepartmentName());
          para.put("depId", getDepartmentId());
          return para;
      }
      
     public Map getDailyUnTrack(){
          Map para = new HashMap<>();
          para.put("pDay",pDay);
          para.put("pId", Integer.parseInt(getMenuId()));
          para.put("title", "Daily Sales Report of Untrackable Item " +getComboMenuName() + " of " +getComboDepartmentName());
          para.put("depId", getDepartmentId());
          return para;
      }
      public Map getMonthlyParam(){
          Map para = new HashMap<>();
          para.put("frmDate",getStartDate());
          para.put("toDate", getEndDate());
          para.put("pId", Integer.parseInt(getMenuId()));
          para.put("title", "Monthly Item Sales Report of " +getComboMenuName() + " of " +getComboDepartmentName());
          para.put("depId",getDepartmentId());
          return para;
      }
      
      public Map getMonthlyTrack(){
          Map para = new HashMap<>();
          para.put("frmDate",getStartDate());
          para.put("toDate", getEndDate());
          para.put("pId", Integer.parseInt(getMenuId()));
          para.put("title", "Monthly Sales Report of Trackable Item " +getComboMenuName() + " of " +getComboDepartmentName());
          para.put("depId",getDepartmentId());
          return para;
      }
      
      public Map getMonthlyUnTrack(){
          Map para = new HashMap<>();
          para.put("frmDate",getStartDate());
          para.put("toDate", getEndDate());
          para.put("pId", Integer.parseInt(getMenuId()));
          para.put("title", "Monthly Sales Report of Untrackable Item " +getComboMenuName() + " of " +getComboDepartmentName());
          para.put("depId",getDepartmentId());
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
        jDayChooser1 = new com.toedter.calendar.JDayChooser();
        DialogPeriodType = new javax.swing.JDialog();
        MonthPanel = new javax.swing.JPanel();
        MonthChooser = new com.toedter.calendar.JMonthChooser();
        YearChooser = new com.toedter.calendar.JYearChooser();
        DayChooserPanel = new javax.swing.JPanel();
        DayChooser = new com.toedter.calendar.JCalendar();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        StartDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        EndDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        ComboPeriodType = new javax.swing.JComboBox();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        checkboxIncludeAll = new javax.swing.JCheckBox();
        comboMenuName = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        ComboDepartmentName = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        radioBoth = new javax.swing.JRadioButton();
        radioNontrackable = new javax.swing.JRadioButton();
        radioTrackable = new javax.swing.JRadioButton();

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
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

        javax.swing.GroupLayout MonthPanelLayout = new javax.swing.GroupLayout(MonthPanel);
        MonthPanel.setLayout(MonthPanelLayout);
        MonthPanelLayout.setHorizontalGroup(
            MonthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MonthPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MonthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(YearChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        MonthPanelLayout.setVerticalGroup(
            MonthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MonthPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MonthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MonthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(YearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout DayChooserPanelLayout = new javax.swing.GroupLayout(DayChooserPanel);
        DayChooserPanel.setLayout(DayChooserPanelLayout);
        DayChooserPanelLayout.setHorizontalGroup(
            DayChooserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DayChooserPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DayChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        DayChooserPanelLayout.setVerticalGroup(
            DayChooserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DayChooserPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DayChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout DialogPeriodTypeLayout = new javax.swing.GroupLayout(DialogPeriodType.getContentPane());
        DialogPeriodType.getContentPane().setLayout(DialogPeriodTypeLayout);
        DialogPeriodTypeLayout.setHorizontalGroup(
            DialogPeriodTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogPeriodTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DialogPeriodTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MonthPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(DialogPeriodTypeLayout.createSequentialGroup()
                        .addComponent(DayChooserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        DialogPeriodTypeLayout.setVerticalGroup(
            DialogPeriodTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogPeriodTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DayChooserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MonthPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Itemwise Sales Report");
        setModalityType(java.awt.Dialog.ModalityType.DOCUMENT_MODAL);
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Report Period"));

        StartDateChooser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Start Date:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("End Date:");

        EndDateChooser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Select Period:");

        ComboPeriodType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ComboPeriodType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Daily", "Monthly" }));
        ComboPeriodType.setActionCommand("comboPeriodType");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(EndDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(StartDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(ComboPeriodType, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboPeriodType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(StartDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EndDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ComboPeriodType, StartDateChooser});

        btnOk.setText("Ok");

        btnCancel.setText("Cancel");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Menu Name");

        checkboxIncludeAll.setText("Include All");

        comboMenuName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboMenuName.setActionCommand("ComboMenuName");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Department Name:");

        ComboDepartmentName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Choose Menu Type"));

        buttonGroup1.add(radioBoth);
        radioBoth.setText("Both");
        radioBoth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBothActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioNontrackable);
        radioNontrackable.setText("Non Trackable");

        buttonGroup1.add(radioTrackable);
        radioTrackable.setText("Trackable");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(radioTrackable)
                .addGap(89, 89, 89)
                .addComponent(radioNontrackable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(radioBoth)
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioTrackable)
                    .addComponent(radioNontrackable)
                    .addComponent(radioBoth))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkboxIncludeAll, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(118, 118, 118)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ComboDepartmentName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboMenuName, 0, 142, Short.MAX_VALUE))
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancel, btnOk});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ComboDepartmentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMenuName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkboxIncludeAll))
                .addGap(14, 14, 14)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancel, btnOk});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ComboDepartmentName, comboMenuName});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioBothActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBothActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioBothActionPerformed

    /**
     * @param args the command line arguments
     */
    
    
    private Date pDay;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboDepartmentName;
    private javax.swing.JComboBox ComboPeriodType;
    public javax.swing.JDialog DailogReport;
    public com.toedter.calendar.JCalendar DayChooser;
    private javax.swing.JPanel DayChooserPanel;
    public javax.swing.JDialog DialogPeriodType;
    public com.toedter.calendar.JDateChooser EndDateChooser;
    public com.toedter.calendar.JMonthChooser MonthChooser;
    public javax.swing.JPanel MonthPanel;
    public com.toedter.calendar.JDateChooser StartDateChooser;
    public com.toedter.calendar.JYearChooser YearChooser;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReportCancel;
    private javax.swing.JButton btnSaveAsExcel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox checkboxIncludeAll;
    public javax.swing.JComboBox comboMenuName;
    private com.toedter.calendar.JDayChooser jDayChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEndDate;
    private javax.swing.JLabel lblReportTitle;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JRadioButton radioBoth;
    private javax.swing.JRadioButton radioNontrackable;
    private javax.swing.JRadioButton radioTrackable;
    public javax.swing.JTable tblReport;
    // End of variables declaration//GEN-END:variables
}
