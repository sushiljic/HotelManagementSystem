/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package resturant.menulist;

import hotelmanagementsystem.MainFrameView;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.miginfocom.swing.MigLayout;
import resturant.order.ExecuteOrder;
import resturant.order.OrderView;
import reusableClass.DisplayMessages;

/**
 *
 * @author SUSHIL
 */
public final class MenuListDetailController {
     MenuListView tview;
    MenuListModel tmodel;
    MainFrameView mainview;
    public JButton MenuId;
    public  JPanel[] menupanel;
    JPanel Tpanel;
    Object[][] data = null;
    public MenuListDetailController(MenuListModel model,MenuListView view,MainFrameView mainview){
        tview = view;
        tmodel = model;
        tview.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent se){
             tview.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
       this.mainview = mainview;
        try{
           
         data = tmodel.getMenuInfoObject(this.mainview.getUserId());
           
        tview.setPanel(drawPanel(data));
//        System.out.println("wala");
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(mainview, e+" From menuList");
        }
        tview.addSearchListener(new SearchListener());
        tview.addtxtSearchListener(new TxtSearchListener());
        tview.addPrintListener(new PrintListener(Tpanel) );
        tview.addTextSearchDocumentListener(new SearchDocumentListener());
    }
     public JPanel drawPanel(Object[][] data){
//         rsget.getString("menu_id"),rsget.getString("menu_name"),rsget.getBoolean("item_type"),rsget.getString("item_id"),rsget.getString("item_name"),rsget.getString("unit_id"),rsget.getString("unit_name"),rsget.getBigDecimal("quantity"),rsget.getBigDecimal("retail_price"),rsget.getString("wholesale_price"),rsget.getString("image_path"),rsget.getBoolean("hybrid_type")
       
         Tpanel = new JPanel(new GridLayout(3,0));
       
        
//        Tpanel.setBackground(Color.GRAY);
        Tpanel.setBackground(new Color(76,76,187));
        Tpanel.setOpaque(true);
       
     
   
     /* initialaling the jpanel
     
     */
//        System.out.println(data.length +"wala");
   menupanel  = new JPanel[data.length];
  
//     int ctn =0;
    for(int row = 0; row<data.length;row++){
        
         
                /* Loading image
                
                */
//                final Image  menuimg = Toolkit.getDefaultToolkit().createImage("/images/"+data[row][10]);
//              System.out.println(getClass().getResource("/images/"+data[row][10]));
                menupanel[row] = new JPanel(new MigLayout("wrap 3"));
//                menupanel[row].setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.CYAN) );
//                menupanel[row].setBorder(BorderFactory.createLineBorder(Color.GRAY,10));
//                menupanel[row].setBackground(Color.WHITE);
                menupanel[row].setBorder(BorderFactory.createLineBorder(new Color(76,76,187),10));
//             
                
//         for(int col =0;col<data[row].length;col++){
                JLabel MenuIcon ;
              
//                 MenuIcon = new JLabel(new ImageIcon(menuimg));
//                if(data[row][10] == null|| data[row][10].equals("")){
//                    MenuIcon = new JLabel();
////                    System.out.println("wala");
//                    
//                }
//                else{
//                    
//                }
//                     MenuIcon = new JLabel(new ImageIcon("resources/images/"+data[row][10]));
                    if(data[row][13] == null){
                        MenuIcon = new JLabel();
                    }
                    else{
                    MenuIcon = new JLabel(new ImageIcon((byte[])data[row][13]));
                    }
//                     MenuIcon = new JLabel();
//                     System.out.println("wala1"+data[row][3]);
                
                
//                  MenuIcon = new JLabel(new ImageIcon(getClass().getResource("/images/"+data[row][10])));
//                   System.out.println(getClass().getResource("/images/"+data[row][10]));
                   MenuIcon.setPreferredSize(new Dimension(100,100));
              
                 MenuId = new JButton(data[row][1].toString());
             
//                MenuId.setBorder(BorderFactory.createBevelBorder(0));
                  JTable ItemInfo  = new JTable();
                  ItemInfo.setFocusable(false);
                  JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
                  String ItemType = new String();
                 BigDecimal AvailStock = BigDecimal.ZERO;
                 Float strAvailStock = null;
                 /*
                 adding the listener to the MenuId
                 */
               MenuId.setActionCommand(data[row][0].toString());
              //adding actionlistener along with button
                tview.addActionListener(new ButtonActionListener(), MenuId);
                /*
                identifiying the itemtype
                */
                if(data[row][2].equals(true)){
                    //if it is trackable type
                    if(data[row][11].equals(true)){
                        ItemType = "Hybrid Trackable Item";
                        ItemInfo.setModel(tmodel.getHybridTrackableItem(data[row][0].toString()));
                       AvailStock = tmodel.getHybridItemAvailable(data[row][0].toString()).setScale(0, RoundingMode.DOWN);
                        
                        
                    }
                    else{
                        ItemType = "Single Trackable Item";
                       
                        
                         ItemInfo.setModel(tmodel.getSingleTrackableItem(data[row][0].toString()));
                         String strItemStock = ItemInfo.getValueAt(0, 1).toString().replaceAll("[^0-9,.]", "");
//                        System.out.println(strItemStock);
                         BigDecimal ItemStock = new BigDecimal(strItemStock);
//                         
//                          AvailStock = ItemStock.divide(new BigDecimal());
//                          System.out.println(AvailStock);
                         strAvailStock = Float.parseFloat(strItemStock)/((Number)(data[row][7])).floatValue();
                         AvailStock = new BigDecimal(strAvailStock).setScale(0, RoundingMode.DOWN);
                          
                         
                         
                    }
                    
                }
                else{
                    ItemType = "Non Trackable Item";
                }
               
//                MenuId.setForeground(new Color(12,34,255));
                JLabel MenuName = new JLabel(data[row][0]+": "+data[row][1].toString());
               
                String unit = data[row][7].toString()+data[row][6].toString();
//                JLabel Unittype = new JLabel(unit);
                JLabel Rate = new JLabel("Rs: " +data[row][8].toString());
               //adding other store information
                JLabel MenuTitle = new JLabel(ItemType+"( "+data[row][12]+")");
              
                
                /*
                custom editing to gui
                */
//                MenuTitle.setBackground(Color.BLACK);
                MenuTitle.setOpaque(true);
                MenuTitle.setFont(new Font("tahoma",1,16));
                MenuName.setPreferredSize(new Dimension(80,20));
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
//               System.out.println("wala2");
                menupanel[row].add(MenuName,"gapright 30");
//                menupanel[row].add(Unittype);
                menupanel[row].add(Rate);
                
               
                  /*
                adding table
                */
                if(ItemType.equalsIgnoreCase("Single Trackable Item")){
//                      System.out.println("wala");
     
              jScrollPane1.setViewportView(ItemInfo);              
              jScrollPane1.setPreferredSize(new Dimension(250,60));
              JLabel MenuItemAvail = new JLabel(AvailStock+" unit  (approx) Available");
              menupanel[row].add(MenuItemAvail,"span 2");
              menupanel[row].add(jScrollPane1,"cell 0 4 3 2");
             
             
//                  
                }
                else if (ItemType.equalsIgnoreCase("Hybrid Trackable Item")){
                      jScrollPane1.setViewportView(ItemInfo);
                      jScrollPane1.setPreferredSize(new Dimension(250,60));
                        JLabel MenuItemAvail = new JLabel(AvailStock+" unit  (approx) Available");
               menupanel[row].add(MenuItemAvail,"span 2");
               menupanel[row].add(jScrollPane1,"cell 0 4 3 2");
                }
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
                if (e.getActionCommand().equalsIgnoreCase(data1[0].toString())) {
//                    JOptionPane.showMessageDialog(mainview, data1[1] + " is clicked");
               
                    /*
                    if it exist
                    */
                      /*
                 * experinmenting with the internal frame
                 */
              
                if(mainview.getCountForOrder() == 0){
              
              //authenticate the user 
                Object[] respdep = tmodel.getRespectiveDepartmentFromMenuId(Integer.parseInt(data1[0].toString()));
                Boolean authflag =false;
                
                ExecuteOrder order = new ExecuteOrder(mainview);
                //incase the day is not opened return from here
                if(order.OrderView == null){
                    return;
                }
                //checking whether they are legal to issue the menuid
                ComboBoxModel departmentmodel = order.OrderView.returnComboDepartmentName().getModel();
                //check agiant each row
                for(int i=0;i<departmentmodel.getSize();i++){
                    if(departmentmodel.getElementAt(i).equals(respdep[1])){
                        authflag = true;
                        break;
                    }
                }
                if(authflag){
                    order.OrderView.setcomboDepartmentName(respdep[1].toString());
                }
                else{
                    DisplayMessages.displayInfo(tview, "You are not Validated User.", "Auth Window");
                    return;
                }
                mainview.desktop.add(order.OrderView);
                order.OrderView.setSelected(true);
                
                order.OrderView.setComboMenuName(data1[1].toString());
                mainview.incrementCountForOrder();
              
//                  order.OrderView.setFocusOntxtOrderQuantity();
//                    order.OrderView.pack();
             
              
                break;
                
                }
                else{
                      JInternalFrame[] frames=  mainview.desktop.getAllFrames();
                    for (JInternalFrame frame : frames) {
                        if (frame.getTitle().equalsIgnoreCase("Order Window")) {
//                         System.out.println("wala");
//                            frame.setSelected(true);
//                            frame.dispose();
                    OrderView view =         (OrderView)frame;
                     //authenticate the user 
                Object[] respdep = tmodel.getRespectiveDepartmentFromMenuId(Integer.parseInt(data1[0].toString()));
                Boolean authflag =false;
                  //checking whether they are legal to issue the menuid
                ComboBoxModel departmentmodel = view.returnComboDepartmentName().getModel();
                //check agiant each row
                for(int i=0;i<departmentmodel.getSize();i++){
                    if(departmentmodel.getElementAt(i).equals(respdep[1])){
                        authflag = true;
                        break;
                    }
                }
                if(authflag){
                    
                    view.setcomboDepartmentName(respdep[1].toString());
                }
                else{
                    DisplayMessages.displayInfo(tview, "You are not Validated User.", "Auth Window");
                    return;
                }
//              
                view.setSelected(true);
                view.setComboMenuName(data1[1].toString());
//                order.OrderView.setFocusOntxtOrderQuantity();
//                mainview.desktop.add(order.OrderView);
               
                break;
                            
                            
                        }
                    }
                    
                }
               
       
                }
            }
        }
        catch(HeadlessException | PropertyVetoException | NumberFormatException be){
            JOptionPane.showMessageDialog(null, be+"from ButtonActionListener");
        }
        }
        
    }
       public class SearchListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String strsrc = new String();
            boolean flag= false;
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            strsrc = tview.gettxtSearch();
         
               
              ArrayList<JPanel> panelarray = new ArrayList<>();
             for(Component comp: Tpanel.getComponents()){
               JPanel panel = (JPanel)comp;
             for(Component but:  panel.getComponents()){
//                 if(but.get)
                 if(but instanceof  JButton){
                 JButton button = (JButton) but;
                 if(button.getText().toLowerCase().startsWith(strsrc.toLowerCase())){
                    button.requestFocusInWindow();
                    flag = true;
                    panel.scrollRectToVisible(/*new Rectangle(button.getX(),button.getY(),button.getWidth()+100,button.getHeight()+100)*/new Rectangle(600, 600));
                 }
                 }
             }
             }
               
     
          if(flag == false){
              JOptionPane.showMessageDialog(tview, "Menu Name Not Found");
          }
//            System.out.println(strsrc);
        }
        catch(HeadlessException ee){
            JOptionPane.showMessageDialog(tview, ee+"from SearchListener");
        }
        }
        
          
      }
       public class PrintListener implements ActionListener,Printable{
        JPanel jpanel;
           PrintListener(JPanel panel){
               jpanel = panel;
           }

        @Override
        public void actionPerformed(ActionEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  PrinterJob job = PrinterJob.getPrinterJob();
         job.setPrintable(this);
         boolean ok = job.printDialog();
         if (ok) {
             try {
                  job.print();
             } catch (PrinterException ex) {
              /* The job did not successfully complete */
                 JOptionPane.showMessageDialog(tview, "Printer Error");
             }
         }
            
        }

        @Override
        public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        /* Now print the window and its visible contents */
        jpanel.printAll(g);

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS; 
        
        }
           
       }
       //class to handle document search
       public class SearchDocumentListener implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
            searchMenu(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            searchMenu(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            searchMenu(e);
        }
           
        //function to hanlde search
        private void searchMenu(DocumentEvent ee){
            try{
                SwingUtilities.invokeLater(new Runnable(){

                    @Override
                    public void run() {
                    tview.setTablePanel(drawPanel(tmodel.getMenuInfoObjectBySearchLike(mainview.getUserId(), tview.gettxtSearch())));
                    }
                    
                });
                
            }
            catch(Exception se){
                DisplayMessages.displayError(tview, se.getMessage()+" from "+getClass().getName() , "Error");
            }
        }
       }
       public class TxtSearchListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            tview.getBtnSearch().doClick();
        }
           
       }
}
