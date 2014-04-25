/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package resturant.tablecrud;

import hotelmanagementsystem.MainFrameView;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import resturant.order.ExecuteOrder;
import resturant.order.OrderView;
import resturant.orderbill.ExecuteOrderBill;
import resturant.orderbill.OrderBillView;
import reusableClass.DisplayMessages;

/**
 *
 * @author SUSHIL
 */
public final class TableStatusController {
    TableStatusView tview;
    TableCrudModel tmodel;
    MainFrameView mainview;
    Object[][] data;
    public TableStatusController(TableCrudModel model,TableStatusView view,MainFrameView mainview){
        tview = view;
        tmodel = model;
       this.mainview = mainview;
       //loading data
                data = tmodel.getTableInfoObject();
                tview.setPanel(drawPanel(data));
        
          /*
                making it immovvable
                */
                    BasicInternalFrameUI ui = (BasicInternalFrameUI)tview.getUI();
                    Component north = ui.getNorthPane();
                    MouseMotionListener[] actions = (MouseMotionListener[])north.getListeners(MouseMotionListener.class);
                    for (MouseMotionListener action : actions) {
                        north.removeMouseMotionListener(action);
                    }
         /*
                    adding the internal frame listener for resting the count value
                    */
        tview.addInternalFrameListener(new InteralFrameCloseListener());
        /*
        setting the location of the right side of desktop pane
        */
         tview.setLocation(mainview.desktop.getSize().width- tview.getSize().width-1,1);
         Timer refreshTable = new Timer(10000,new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            try{
                 data = tmodel.getTableInfoObject();
                tview.setPanel(drawPanel(data));
            }
            catch(Exception se){
                DisplayMessages.displayError(tview, se.getMessage()+"from refreshtable"+getClass().getName(), "Error");
            }
            }
             
         });
         refreshTable.start();
        
    }
     public class InteralFrameCloseListener extends InternalFrameAdapter{
     @Override
     public void  internalFrameClosing(InternalFrameEvent e){
//      System.out.println("wala");
         mainview.setCountForTableStatus(0);
         e.getInternalFrame().dispose();
     }
   }
     public JPanel drawPanel(Object[][] data/*,final JFrame frame, JPanel Tablepanel */){
       
        JPanel Tpanel = new JPanel(new GridLayout(0,1));
        Tpanel.setBackground(new Color(255,255,255));
        Tpanel.setOpaque(true);
       
        //Tablepanel.setBackground(Color.red);
     //   Tablepanel.setLayout(new MigLayout());
       // js.setBounds(10, 10, 100, 100);
//       Tpanel.setLayout(new GridLayout(0,1));
      // Tpanel.setLayout(new ());
//       JScrollPane scroll = new JScrollPane(Tpanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//      scroll.setPreferredSize(new Dimension(400,500));
       // Tpanel.setAutoscrolls(true);
        //js.add(Tablepanel);
        //js.setPreferredSize(new Dimension(100,100));
       // frame.add(js);
        
     //   js.add(Tablepanel);
       // Tablepanel.add(js);
       // js.setViewportView(Tablepanel);
       // frame.add(js);
//     Tpanel.setPreferredSize(new Dimension(300, 600));
    // JScrollPane scroll = new JScrollPane(Tpanel);
     Tpanel.setBorder(BorderFactory.createLineBorder(Color.gray));
    // Tablepanel.repaint();
    // Tablepanel.revalidate();
         ImageIcon imgoff = new ImageIcon(getClass().getResource("/images/tablestatusoff.jpg"));
             ImageIcon imgon = new ImageIcon(getClass().getResource("/images/tablestatuson.jpg"));
    ArrayList<String> Group = new ArrayList<>(); 
        for (Object[] data1 : data) {
            Group.add(data1[2].toString());
        }
            //coveritng arraylist to hashset removing duplicate values
           // System.out.println(Group+"\n"+Group.size());
            LinkedHashSet<String> HashGroup = new LinkedHashSet<>(Group);
            //coveting again to to arraylist
            ArrayList<String> FinalGroup = new ArrayList<>(HashGroup);
          //  System.out.println(FinalGroup+"\n"+FinalGroup.size());
            
            JPanel[] panel = new JPanel[FinalGroup.size()];
             JPanel[] grandpanel = new JPanel[FinalGroup.size()];
            JScrollPane[] scrollz = new JScrollPane[FinalGroup.size()];
         
            /*
             * drawing of the table accorgin to parent group
             */
             for(int i=0; i<FinalGroup.size();i++){
                   panel[i] = new JPanel();
                   panel[i].setBackground(new Color(255,255,255));
                   grandpanel[i] = new JPanel();
//                   grandpanel[i].setBackground(new Color(255,255,255));
//                   grandpanel[i].setBackground(Color.red);
//                   panel[i].setSize(100, 100);
                  scrollz[i] = new JScrollPane();
                panel[i].setLayout(new GridLayout(0,4));
//                grandpanel[i].setSize(300, 160);
                
                scrollz[i].setViewportView(panel[i]);
                grandpanel[i].add(scrollz[i]);
                Tpanel.add(grandpanel[i]);
//                Tpanel.add(scrollz[i]);
              //  ji.setAutoscrolls(true);
//              scrollz[i] = new JScrollPane(panel[i],ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
              // panel[i].setPreferredSize(new Dimension(300,200));
                 
             }
            for(int i=0; i<FinalGroup.size();i++){
            for (Object[] data1 : data) {
                if (FinalGroup.get(i).equals(data1[2])) {
                    JButton lbl = new JButton(data1[1].toString());
//                       lbl.setName(data[i][1].toString());
                    lbl.setHorizontalTextPosition(SwingConstants.CENTER);
                    lbl.setVerticalTextPosition(SwingConstants.BOTTOM);
                    lbl.setBackground(new Color(55, 150, 150));
                    /*
                    setting name
                     */
//                       lbl.setName(data[j][1].toString());
//                       System.out.println(lbl.getName());
                    lbl.setActionCommand(data1[1].toString());
                    lbl.setToolTipText(data1[1].toString());
                    /*
                    adding action listener in for repective funtion calling
                     */
//                       ActionListener ac = null;
                    tview.addActionListener( new ButtonActionListener(), lbl);
                    // System.out.println(data[i][3]);
                    if (data1[3].equals(true)) {
                        lbl.setIcon(imgon);
                    } else {
                        lbl.setIcon(imgoff);
                    }
                    lbl.setPreferredSize(new Dimension(70, 100));
//            lbl.setBorder(BorderFactory.createLineBorder(new Color(55, 150, 150), 2,false));
                    panel[i].add(lbl);
//            panel[i].revalidate();
//            panel[i].repaint();
                    
                    //  Tablepanel.repaint();
                    //  Tablepanel.validate();
                }
            }
               // panel[i].add(new JLabel(FinalGroup.get(i)));
                // System.out.println(panel[i]);
                panel[i].setBorder(BorderFactory.createTitledBorder(FinalGroup.get(i)));
//                grandpanel[i].add(panel[i]);
              //  scroll[i] = new JScrollPane(panel[i]);
              //  Tablepanel.add(ji);
//               Tpanel.add(panel[i]);
//                grandpanel[i].add(panel[i]);
//               pack();
//                Tpanel.add(grandpanel[i]);
//                pack();
                
//             Tpanel.add(scrollz[i]);
               //Tpanel.add(scroll[i]);
//                Tpanel.revalidate();
//                Tpanel.repaint();
//               validate();
                
               
            }
           
          
        /*     for(int i = 0;i<data.length;i++){
        // for(int j=0;j<data[i].length;j++){
                // String panel = FinalGroup.get
               //  JPanel FinalGroup = new JPanel();
             JButton lbl = new JButton(data[i][1].toString());
             lbl.setPreferredSize(new Dimension(60, 50));
            // System.out.println(data[i][3]);
           if(data[i][3].equals(true)){
               lbl.setIcon(imgon);
           }
           else{
             lbl.setIcon(imgoff);   
           }
            
            lbl.setBorder(BorderFactory.createLineBorder(Color.green, 2,false));
         //lbl.setBounds(10, 10, 20, 20);
         
         //lbl.setHorizontalAlignment(SwingConstants.LEFT);
         Tablepanel.add(lbl);
         Tablepanel.revalidate();
         Tablepanel.repaint();
         frame.pack(); 
      //   }
     }
     */
     /*for(int i=0;i<100;i++){
         JButton lbl = new JButton("try");
         
        lbl.setBorder(BorderFactory.createLineBorder(Color.green, 2,false));
         //lbl.setBounds(10, 10, 20, 20);
         lbl.setPreferredSize(new Dimension(60, 50));
         //lbl.setHorizontalAlignment(SwingConstants.LEFT);
         Tablepanel.add(lbl);
         Tablepanel.revalidate();
         Tablepanel.repaint();
         frame.pack();
     }*/
    //  JScrollPane js = new JScrollPane(Tablepanel);
      //js.setVerticalScrollBar();
    //  js.setAutoscrolls(true);
     // js.setViewportView(Tablepanel);
     // js.setPreferredSize(new Dimension(400,300));
   // add(js);
        //    add(Tablepanel);
            return Tpanel;
        
    }
      class ButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            for (Object[] data1 : data) {
                if (e.getActionCommand().equalsIgnoreCase(data1[1].toString())) {
//                    JOptionPane.showMessageDialog(mainview, data1[1] + " is clicked");
                if(data1[3].equals(false)){
                    /*
                    it is not packed
                    */
                      /*
                 * experinmenting with the internal frame
                 */
               
//                 System.out.println("wala");
                if(mainview.getCountForOrder() == 0){
              
              
                mainview.incrementCountForOrder();
                 ExecuteOrder order = new ExecuteOrder(mainview);
                mainview.desktop.add(order.OrderView);
                order.OrderView.setComboTableName(data1[1].toString());
             
                order.OrderView.setSelected(true);
                break;
                
                }
                else{
                      JInternalFrame[] frames=  mainview.desktop.getAllFrames();
                    for (JInternalFrame frame : frames) {
                        if (frame.getTitle().equalsIgnoreCase("Order Window")) {
//                     
               
             OrderView view = (OrderView) frame;
            view.setSelected(true);
              view.setComboTableName(data1[1].toString());
              
              
                break;
                            
                            
                        }
                    }
                    
                }
                }
                else{
                    /*
                    it is not packed
                    */
//                    JOptionPane.showMessageDialog(mainview, "not packe");
                    //check whether they are validated user to issue the bill
                    
                        if(mainview.getCountForPay() == 0){

                        /*
                         * expering menting with  jinternal fames
                         */
        //             getting data from ordered table of pay
//                             System.out.println("wala");
                            //getting  deparment info in which that that order was generated
                         Object[] dep  = tmodel.getRespectiveDepartmentFromTableId(Integer.parseInt(data1[0].toString()));
//                         if(did != mainview.)
                         
                       ExecuteOrderBill billpay = new ExecuteOrderBill(mainview);
//                       checking whether it is user is authorised to accept the bill
                       ComboBoxModel departmentcombo = billpay.OrderBillView.returnComboDepartmentName().getModel();
                       boolean authoriseflag = false;
                       for(int i=0;i<departmentcombo.getSize();i++){
                           if(departmentcombo.getElementAt(i).equals(dep[1])){
                               authoriseflag = true;
//                                mainview.desktop.add(billpay.OrderBillView);
//                               billpay.OrderBillView.setSelected(true);
                               billpay.OrderBillView.setcomboDepartmentName(dep[1].toString());
//                               System.out.println(dep[1]);
                               break;
                           }
                       }
                       if(!authoriseflag){
                           JOptionPane.showMessageDialog(mainview, "This Order has been ordered by another Department.");
                           return;
                       }
                            for( int i=0;i<billpay.OrderBillView.gettblOrderList().getRowCount();i++){
                                if(billpay.OrderBillView.gettblOrderList().getValueAt(i, 1) != null){
                                if(billpay.OrderBillView.gettblOrderList().getValueAt(i, 1).equals(data1[1])){
                                    
//                                    System.out.println("wala");
                                    
                                      mainview.desktop.add(billpay.OrderBillView);
                                       billpay.OrderBillView.setSelected(true);
                                       

                                       billpay.OrderBillView.tblOrderedList.scrollRectToVisible(billpay.OrderBillView.tblOrderedList.getCellRect(i, i, true));

                                       billpay.OrderBillView.tblOrderedList.setRowSelectionInterval(i, i);
                                       

        //                 MainFrameView.desktop.add(scrollpane);

                                        break;

                                }
                                }
                            }
                             mainview.incrementCountForPay(); 
                            break;
                         

                        }
                        else{
                          JInternalFrame[] frames=  mainview.desktop.getAllFrames();
                                for (JInternalFrame frame : frames) {
                                    if (frame.getTitle().equalsIgnoreCase("Order Bill Pay Window")) {
        //                         System.out.println("wala");
        //                                frame.setSelected(true);
//                                   frame.dispose();
                                        OrderBillView BillView = (OrderBillView)frame;
                                           //getting  deparment info in which that that order was generated
                         Object[] dep  = tmodel.getRespectiveDepartmentFromTableId(Integer.parseInt(data1[0].toString()));
                                         ComboBoxModel departmentcombo =BillView.returnComboDepartmentName().getModel();
                                        boolean authoriseflag = false;
                                        for(int i=0;i<departmentcombo.getSize();i++){
                                            if(departmentcombo.getElementAt(i).equals(dep[1])){
                                                authoriseflag = true;
                 //                                mainview.desktop.add(billpay.OrderBillView);
                 //                               billpay.OrderBillView.setSelected(true);
                                               BillView.setcomboDepartmentName(dep[1].toString());
                 //                               System.out.println(dep[1]);
                                                break;
                                            }
                                        }
                                        if(!authoriseflag){
                                            JOptionPane.showMessageDialog(mainview, "This Order has been ordered by another Department.");
                                            return;
                                        }       
                                  
                                        for( int i=0;i<BillView.gettblOrderList().getRowCount();i++){
                                            if(BillView.gettblOrderList().getValueAt(i, 1) != null){
                                if(BillView.gettblOrderList().getValueAt(i, 1).equals(data1[1])){

//                                      mainview.desktop.add(billpay.OrderBillView);
//                                       billpay.OrderBillView.setSelected(true);
//
//                                       billpay.OrderBillView.tblOrderedList.scrollRectToVisible(billpay.OrderBillView.tblOrderedList.getCellRect(i, i, true));
//
//                                       billpay.OrderBillView.tblOrderedList.setRowSelectionInterval(i, i);
                                      
                                        BillView.setSelected(true);

                                       BillView.tblOrderedList.scrollRectToVisible(BillView.tblOrderedList.getCellRect(i, i, true));

                                      BillView.tblOrderedList.setRowSelectionInterval(i, i);
                                       

        //                 MainFrameView.desktop.add(scrollpane);

                                        break;

                                }
                                            }
                            }
        //                          
                                    }

                                }
                                break;

                        }
                }
                }
            }
        }
        catch(HeadlessException | PropertyVetoException be){
            JOptionPane.showMessageDialog(null, be+"from ButtonActionListener");
        }
        catch(Exception se){
             JOptionPane.showMessageDialog(null, se+"from ButtonActionListener");
        }
        }
        
    }
    
}
