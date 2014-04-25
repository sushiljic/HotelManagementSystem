/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserCreditial;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.KeyStroke;

/**
 *
 * @author SUSHIL
 */
public class UserCreditialView extends javax.swing.JDialog {
    private  int UserId = 0;
    private boolean checkCompanySetupStatus;
    private boolean checkCenterStoreSetupStatus;
    private boolean checkCenterStoreStatus;
    private boolean checkTerminalSetupStatus;
    private boolean checkTerminalStatus;
    private boolean checkTerminalReportStatus;
    private boolean checkCenterStoreReportStatus;
    private boolean checkSystemConfigStatus;
    private boolean checkSystemDateStatus;
    

    /**
     * Creates new form UserCreditialView
     * @param parent
     * @param modal
     */
    public UserCreditialView(java.awt.Frame parent, boolean modal) {
        super(parent,modal);
        initComponents();
//        jLabel1.setVisible(false);
        setButtonForEnter(btnSave);
        setButtonForEnter(btnCancel);
        setLocationRelativeTo(null);
    }
   public void setUserId(int id){
       UserId = id;
   }
   public int getUserId(){
       return UserId;
   }
   public void setcheckCompanySetup(Boolean sts){
       checkCompanySetupStatus = sts;
   }
//   public boolean getcheckCompanySetup(){
//        return checkCompanySetupStatus;
//   }
   public boolean getcheckCompanySetup(){
        return checkCompanySetup.isSelected();
   }
   public void setcheckCenterStoreSetup(Boolean sts){
       checkCenterStoreSetupStatus = sts;
       
   }
   public boolean getcheckCenterStoreSetup(){
        return checkCenterStoreSetup.isSelected();
   }
    public void setcheckCenterStore(Boolean sts){
       checkCenterStoreStatus = sts;
   }
   public boolean getcheckCenterStore(){
        return checkCenterStore.isSelected();
   }
    public void setcheckCenterStoreReport(Boolean sts){
       checkCenterStoreReportStatus = sts;
   }
   public boolean getcheckCenterReport(){
        return checkCenterStoreReport.isSelected();
   }
    public void setcheckTerminalSetup(Boolean sts){
       checkTerminalSetupStatus = sts;
   }
   public boolean getcheckTerminalSetup(){
        return checkTerminalSetup.isSelected();
   }
    public void setcheckTerminal(Boolean sts){
       checkTerminalStatus = sts;
   }
   public boolean getcheckTerminal(){
        return checkTerminal.isSelected();
   }
    public void setcheckTerminalReport(Boolean sts){
       checkTerminalReportStatus = sts;
   }
   public boolean getcheckTerminalReport(){
        return checkTerminalReport.isSelected();
   }
   public boolean getcheckSystemConfig(){
       return checkSystemConfig.isSelected();
   }
   public void setcheckSystemConfig(Boolean  sts){
       checkSystemConfigStatus = sts;
   }
   public void setcheckSystemDate(Boolean sts){
       checkSystemConfigStatus = sts;
   }
   public boolean getcheckSystemDate(){
       return checkSystemDate.isSelected();
   }
    public void addCheckBoxListener(ActionListener ListenForCheck){
        checkCompanySetup.addActionListener(ListenForCheck);
       
        checkCenterStoreSetup.addActionListener(ListenForCheck);
        checkCenterStore.addActionListener(ListenForCheck);
        checkTerminalSetup.addActionListener(ListenForCheck);
        checkTerminal.addActionListener(ListenForCheck);
        checkCenterStoreReport.addActionListener(ListenForCheck);
        checkTerminalReport.addActionListener(ListenForCheck);
        checkSystemConfig.addActionListener(ListenForCheck);
        checkSystemDate.addActionListener(ListenForCheck);
    }
    public void addSaveListener(ActionListener Listen){
        btnSave.addActionListener(Listen);
    }
    public void addCancelListener(ActionListener Listene){
        btnCancel.addActionListener(Listene);
    }
    public void setbtnCancelEnableTrue(){
        btnCancel.setEnabled(true);
    }
    public void setbtnCancelEnableFalse(){
        btnCancel.setEnabled(false);
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
       public ArrayList getAllMenuItemText(){
        ArrayList<String> MenuItemText = new ArrayList<>();
        ArrayList<JMenu> MenuList = new ArrayList<>();
           for (Component component : jMenuBar2.getComponents()) {
        JMenu menu = (JMenu)component;
//        System.out.println(menu.getText());
        MenuList.add(menu);
//        System.out.println(menu.getText());
//        for(Component MenuItem:menu.get){
////            if()
//           JMenuItem menuitem = (JMenuItem)MenuItem;
//            System.out.println(menuitem.getText());
        }
           
      return MenuList;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        checkCompanySetup = new javax.swing.JCheckBoxMenuItem();
        combo = new javax.swing.JMenu();
        checkCenterStoreSetup = new javax.swing.JCheckBoxMenuItem();
        centerstore = new javax.swing.JMenu();
        checkCenterStore = new javax.swing.JCheckBoxMenuItem();
        jMenu4 = new javax.swing.JMenu();
        checkTerminalSetup = new javax.swing.JCheckBoxMenuItem();
        jMenu6 = new javax.swing.JMenu();
        checkTerminal = new javax.swing.JCheckBoxMenuItem();
        jMenu2 = new javax.swing.JMenu();
        checkCenterStoreReport = new javax.swing.JCheckBoxMenuItem();
        jMenu5 = new javax.swing.JMenu();
        checkTerminalReport = new javax.swing.JCheckBoxMenuItem();
        jMenu3 = new javax.swing.JMenu();
        checkSystemDate = new javax.swing.JCheckBoxMenuItem();
        jMenu8 = new javax.swing.JMenu();
        checkSystemConfig = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Manage The MenuBar According To User");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/drmsys.png"))); // NOI18N

        jPanel1.setLayout(new java.awt.GridBagLayout());

        btnCancel.setMnemonic('C');
        btnCancel.setText("Cancel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 35;
        gridBagConstraints.ipady = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 18, 11, 78);
        jPanel1.add(btnCancel, gridBagConstraints);

        btnSave.setMnemonic('S');
        btnSave.setText("Save");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 43;
        gridBagConstraints.ipady = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 84, 11, 0);
        jPanel1.add(btnSave, gridBagConstraints);

        jMenuBar2.setAlignmentY(0.5F);

        jMenu1.setText("Company Setup");

        checkCompanySetup.setText("Allow");
        checkCompanySetup.setActionCommand("AllowCompanySetup");
        jMenu1.add(checkCompanySetup);

        jMenuBar2.add(jMenu1);

        combo.setText("CenterStore Setup");
        combo.setActionCommand("CenterStoreSetup");

        checkCenterStoreSetup.setText("Allow");
        checkCenterStoreSetup.setActionCommand("AllowCenterStoreSetup");
        combo.add(checkCenterStoreSetup);

        jMenuBar2.add(combo);

        centerstore.setText("CenterStore");

        checkCenterStore.setText("Allow");
        checkCenterStore.setActionCommand("AllowCenterStore");
        centerstore.add(checkCenterStore);

        jMenuBar2.add(centerstore);

        jMenu4.setText("Terminal Setup");

        checkTerminalSetup.setText("Allow");
        checkTerminalSetup.setActionCommand("AllowTerminalSetup");
        jMenu4.add(checkTerminalSetup);

        jMenuBar2.add(jMenu4);

        jMenu6.setText("Terminal");

        checkTerminal.setText("Allow");
        checkTerminal.setActionCommand("AllowTerminal");
        jMenu6.add(checkTerminal);

        jMenuBar2.add(jMenu6);

        jMenu2.setText("CenterStore Report");

        checkCenterStoreReport.setText("Allow");
        checkCenterStoreReport.setActionCommand("AllowCenterStoreReport");
        jMenu2.add(checkCenterStoreReport);

        jMenuBar2.add(jMenu2);

        jMenu5.setText("Terminal Report");

        checkTerminalReport.setText("Allow");
        checkTerminalReport.setActionCommand("AllowTerminalSetup");
        jMenu5.add(checkTerminalReport);

        jMenuBar2.add(jMenu5);

        jMenu3.setText("System Date");

        checkSystemDate.setSelected(true);
        checkSystemDate.setText("Allow");
        jMenu3.add(checkSystemDate);

        jMenuBar2.add(jMenu3);

        jMenu8.setText("System Config");

        checkSystemConfig.setText("Allow");
        checkSystemConfig.setActionCommand("AllowSystemConfig");
        jMenu8.add(checkSystemConfig);

        jMenuBar2.add(jMenu8);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JMenu centerstore;
    private javax.swing.JCheckBoxMenuItem checkCenterStore;
    private javax.swing.JCheckBoxMenuItem checkCenterStoreReport;
    private javax.swing.JCheckBoxMenuItem checkCenterStoreSetup;
    private javax.swing.JCheckBoxMenuItem checkCompanySetup;
    private javax.swing.JCheckBoxMenuItem checkSystemConfig;
    private javax.swing.JCheckBoxMenuItem checkSystemDate;
    private javax.swing.JCheckBoxMenuItem checkTerminal;
    private javax.swing.JCheckBoxMenuItem checkTerminalReport;
    private javax.swing.JCheckBoxMenuItem checkTerminalSetup;
    private javax.swing.JMenu combo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
