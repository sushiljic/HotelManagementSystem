/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package resturant.menulist;

import hotelmanagementsystem.MainFrameView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import net.miginfocom.swing.MigLayout;
import resturant.order.ExecuteOrder;
import resturant.orderbill.ExecuteOrderBill;

/**
 *
 * @author SUSHIL
 */
public class MenuListController {
    MenuListView tview;
    MenuListModel tmodel;
    MainFrameView mainview;
    Object[][] data;
    public MenuListController(MenuListModel model,MenuListView view,MainFrameView mainview){
        tview = view;
        tmodel = model;
       this.mainview = mainview;
        try{
         data = tmodel.getMenuInfoObject(this.mainview.getUserId());
        tview.setPanel(drawPanel(data));   
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(mainview, e+"From MenuListController");
        }
         
       
        /*
        setting the location of the right side of desktop pane
        */
//         tview.setLocation(mainview.desktop.getSize().width- tview.getSize().width-1,1);
         /*
         adding actionlistener for the the jbutton in the table
         */
//         Object[][] buttondata = tmodel.getTableInfoObject();
//         for(int i=0;i<buttondata.length;i++){
//                   JButton lbl = new JButton(data[i][1].toString());
//                   
//         tview.addActionListener(new ButtonActionListener(), null);
//         }
        
    }
     public class InteralFrameCloseListener extends InternalFrameAdapter{
     @Override
     public void  internalFrameClosing(InternalFrameEvent e){
//      System.out.println("wala");
//         mainview.setCountForTableStatus(0);
         e.getInternalFrame().dispose();
     }
   }
     public JPanel drawPanel(Object[][] data/*,final JFrame frame, JPanel Tablepanel */){
//         rsget.getString("menu_id"),rsget.getString("menu_name"),rsget.getBoolean("item_type"),rsget.getString("item_id"),rsget.getString("item_name"),rsget.getString("unit_id"),rsget.getString("unit_name"),rsget.getBigDecimal("quantity"),rsget.getBigDecimal("retail_price"),rsget.getString("wholesale_price"),rsget.getString("image_path"),rsget.getBoolean("hybrid_type")
       
        JPanel Tpanel = new JPanel(new GridLayout(3,0));
        
        Tpanel.setBackground(Color.GRAY);
        Tpanel.setOpaque(true);
       
     
   
     /* initialaling the jpanel
     
     */
     JPanel[] menupanel = new JPanel[data.length];
//     int ctn =0;
    for(int row = 0; row<data.length;row++){
         
                /* Loading image
                
                */
   
     
             final Image  menuimg = Toolkit.getDefaultToolkit().createImage("resources/images/"+data[row][10]);
      
//              
                menupanel[row] = new JPanel(new MigLayout("wrap 3"));
//                menupanel[row].setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.CYAN) );
                menupanel[row].setBorder(BorderFactory.createLineBorder(Color.GRAY,10));
//             
                
//         for(int col =0;col<data[row].length;col++){
                JLabel MenuIcon ;
              
//                 MenuIcon = new JLabel(new ImageIcon(menuimg));
                 if(null == data[row][13]){
                        MenuIcon = new JLabel();
                    }
                    else{
                    MenuIcon = new JLabel(new ImageIcon((byte[])data[row][13]));
                    }
                   MenuIcon.setPreferredSize(new Dimension(100,100));
              
                JLabel MenuId = new JLabel(data[row][0].toString());
             
                MenuId.setBorder(BorderFactory.createBevelBorder(0));
                JTable ItemInfo  = new JTable();
                String ItemType = new String();
                /*
                identifiying the itemtype
                */
                if(data[row][2].equals(true)){
                    //if it is trackable type
                    if(data[row][11].equals(true)){
                        ItemType = "Hybrid Trackable Item";
                    }
                    else {
                        ItemType = "Single Trackable Item";
                        ItemInfo.setModel(tmodel.getSingleTrackableItem(data[row][0].toString()));
                        
                    }
                    
                }
                else{
                    ItemType = "Non Trackable Item";
                }
               
//                MenuId.setForeground(new Color(12,34,255));
                JLabel MenuName = new JLabel(data[row][1].toString());
               
                String unit = data[row][7].toString()+data[row][6].toString();
                JLabel Unittype = new JLabel(unit);
                JLabel Rate = new JLabel("Rs: " +data[row][8].toString());
                JLabel MenuTitle = new JLabel(ItemType);
                /*
                custom editing to gui
                */
//                MenuTitle.setBackground(Color.BLACK);
                MenuTitle.setOpaque(true);
                MenuTitle.setFont(new Font("tahoma",1,16));
                MenuTitle.setForeground(new Color(55, 150, 150));
               MenuName.setFont(new Font("tahoma",1, 16));
                MenuId.setFont(new Font("tahoma",1, 14)); 
                 MenuId.setForeground(new Color(12,34,255));
                  MenuName.setForeground(new Color(12,34,255));
                 Rate.setBorder(BorderFactory.createBevelBorder(0));
                 /*
                 process of addign in mig layout
                 */
                 menupanel[row].add(MenuTitle,"span");
                menupanel[row].add(MenuId,"gaptop 20");//for adding the gap between the data
                menupanel[row].add(MenuIcon,"span 2 2");//for making two row and col span
               
                menupanel[row].add(MenuName,"gapright 30");
                menupanel[row].add(Unittype);
                menupanel[row].add(Rate,"gapleft 50");
              
//            menupanel[row].add(new JLabel(String.valueOf(row)));
                Tpanel.add(menupanel[row]);
                
//         }
          
         
     }
   
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
                    it is packed
                    */
                      /*
                 * experinmenting with the internal frame
                 */
                mainview.incrementCountForOrder();
                 ExecuteOrder order = new ExecuteOrder(mainview);
                if(mainview.getCountForOrder() == 1){
              
              
               
                mainview.desktop.add(order.OrderView);
                order.OrderView.setComboTableName(data1[1].toString());
             
                order.OrderView.setSelected(true);
                break;
                
                }
                else{
                      JInternalFrame[] frames=  mainview.desktop.getAllFrames();
                    for (JInternalFrame frame : frames) {
                        if (frame.getTitle().equalsIgnoreCase("Order Window")) {
//                         System.out.println("wala");
//                            frame.setSelected(true);
                            frame.dispose();
                             
                order.OrderView.setComboTableName(data1[1].toString());
                mainview.desktop.add(order.OrderView);
               
             
                order.OrderView.setSelected(true);
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
                      mainview.incrementCountForPay();
                       ExecuteOrderBill billpay = new ExecuteOrderBill(mainview);
                        if(mainview.getCountForPay() == 1){

                        /*
                         * expering menting with  jinternal fames
                         */
        //             getting data from ordered table of pay
//                             System.out.println("wala");
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
                            break;

                        }
                        else{
                          JInternalFrame[] frames=  mainview.desktop.getAllFrames();
                                for (JInternalFrame frame : frames) {
                                    if (frame.getTitle().equalsIgnoreCase("Order Bill Pay Window")) {
        //                         System.out.println("wala");
        //                                frame.setSelected(true);
                                   frame.dispose();
                                  
                                        for( int i=0;i<billpay.OrderBillView.gettblOrderList().getRowCount();i++){
                                            if(billpay.OrderBillView.gettblOrderList().getValueAt(i, 1) != null){
                                if(billpay.OrderBillView.gettblOrderList().getValueAt(i, 1).equals(data1[1])){

                                      mainview.desktop.add(billpay.OrderBillView);
                                       billpay.OrderBillView.setSelected(true);

                                       billpay.OrderBillView.tblOrderedList.scrollRectToVisible(billpay.OrderBillView.tblOrderedList.getCellRect(i, i, true));

                                       billpay.OrderBillView.tblOrderedList.setRowSelectionInterval(i, i);

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
