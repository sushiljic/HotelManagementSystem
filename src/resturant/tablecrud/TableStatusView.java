/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.tablecrud;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author SUSHIL
 */
public class TableStatusView extends JInternalFrame {
      JPanel Tablepanel = new JPanel(new GridLayout(0,1));
   
public TableStatusView(){
    super("Table Status",false,true,false,true);
    //setPreferredSize(new Dimension(300,500));
   // createAndShowUI();
    initComponents();
    
  //  Tablepanel.updateUI();
     Tablepanel.setBorder(BorderFactory.createLineBorder(Color.red));
//    add(Tablepanel);
   
   // add(drawPanel(data));
   
}
public void setPanel(/*TableStatusView view,*/JPanel pnl){
    
   
//    remove(Tablepanel);
    Tablepanel.removeAll();
    JScrollPane pane = new JScrollPane();
//    pnl.setSize(300, 400);
    pane.setViewportView(pnl);
    /*view.*/Tablepanel.add(pane);
    
//            Tablepanel.repaint();;
Tablepanel.validate();
    add(Tablepanel);
//    repaint();
    validate();
    repaint();
}


  /*  public void  createAndShowUI(){
      //  JFrame frame = new JFrame();
      //  frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        initComponents(frame);
       // frame.setResizable(false);
       // frame.pack();
       // frame.setVisible(true);
     //   frame.add()
    }
*/
 
 // drawPanel(data,/*frame,*/Tablepanel);
//this.add(drawPanel(data));
 
    public void initComponents(/* final JFrame frame*/){
         setSize(322, 582);
         setPreferredSize(new Dimension(322, 585));
    
  //  this.getContentPane().setLayout(new FlowLayout());
    this.setVisible(true);
      // Tablepanel.setSize(300, 500);
       
   //  add(Tablepanel);
     
   
      
    // frame.setPreferredSize(new Dimension(200, 300));
    // frame.add(frame);
    }
   
//    public JPanel drawPanel(Object[][] data/*,final JFrame frame, JPanel Tablepanel */){
//       
//        JPanel Tpanel = new JPanel(new GridLayout(0,1));
//        Tpanel.setBackground(new Color(255,255,255));
//        Tpanel.setOpaque(true);
//       
//        //Tablepanel.setBackground(Color.red);
//     //   Tablepanel.setLayout(new MigLayout());
//       // js.setBounds(10, 10, 100, 100);
////       Tpanel.setLayout(new GridLayout(0,1));
//      // Tpanel.setLayout(new ());
////       JScrollPane scroll = new JScrollPane(Tpanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
////      scroll.setPreferredSize(new Dimension(400,500));
//       // Tpanel.setAutoscrolls(true);
//        //js.add(Tablepanel);
//        //js.setPreferredSize(new Dimension(100,100));
//       // frame.add(js);
//        
//     //   js.add(Tablepanel);
//       // Tablepanel.add(js);
//       // js.setViewportView(Tablepanel);
//       // frame.add(js);
////     Tpanel.setPreferredSize(new Dimension(300, 600));
//    // JScrollPane scroll = new JScrollPane(Tpanel);
//     Tpanel.setBorder(BorderFactory.createLineBorder(Color.yellow));
//    // Tablepanel.repaint();
//    // Tablepanel.revalidate();
//         ImageIcon imgoff = new ImageIcon("images/tablestatusoff.jpg");
//             ImageIcon imgon = new ImageIcon("images/tablestatuson.jpg");
//    ArrayList<String> Group = new ArrayList<String>(); 
//            for(int i=0;i<data.length;i++){
//                Group.add(data[i][2].toString());
//            }
//            //coveritng arraylist to hashset removing duplicate values
//           // System.out.println(Group+"\n"+Group.size());
//            LinkedHashSet<String> HashGroup = new LinkedHashSet<String>(Group);
//            //coveting again to to arraylist
//            ArrayList<String> FinalGroup = new ArrayList<String>(HashGroup);
//          //  System.out.println(FinalGroup+"\n"+FinalGroup.size());
//            
//            JPanel[] panel = new JPanel[FinalGroup.size()];
//             JPanel[] grandpanel = new JPanel[FinalGroup.size()];
//            JScrollPane[] scrollz = new JScrollPane[FinalGroup.size()];
//         
//            /*
//             * drawing of the table accorgin to parent group
//             */
//             for(int i=0; i<FinalGroup.size();i++){
//                   panel[i] = new JPanel();
//                   panel[i].setBackground(new Color(255,255,255));
//                   grandpanel[i] = new JPanel();
//                   grandpanel[i].setBackground(new Color(255,255,255));
////                   grandpanel[i].setBackground(Color.red);
////                   panel[i].setSize(100, 100);
//                  scrollz[i] = new JScrollPane();
//                panel[i].setLayout(new GridLayout(0,4));
//                grandpanel[i].setSize(300, 160);
//                
//                scrollz[i].setViewportView(panel[i]);
//                grandpanel[i].add(scrollz[i]);
//                Tpanel.add(grandpanel[i]);
////                Tpanel.add(scrollz[i]);
//              //  ji.setAutoscrolls(true);
////              scrollz[i] = new JScrollPane(panel[i],ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//              // panel[i].setPreferredSize(new Dimension(300,200));
//                 
//             }
//            for(int i=0; i<FinalGroup.size();i++){
//               // String st = FinalGroup.get(i);
//                //JPanel FinalGroup[i]   = new JPanel();
//               // panel[i] = new JPanel();
//               // panel.add(new JPanel(FinalGroup[i]));
//              // JPanel FinalGroup.get(i) = new JPanel();
//              //  panel[i] = new JPanel();
//              //  panel[i].setLayout(new FlowLayout());
//              //  panel[i].setAutoscrolls(true);
//               // JScrollPane ji = new JScrollPane(panel[i]);
//              //  ji.setAutoscrolls(true);
//                
//              //  panel[i].setPreferredSize(new Dimension(300,200));
//            //  JPanel  FinalGroup.get(i) = new JPanel();
//                /*
//                 * for adding the respective button to thier parent group
//                 */
//                for(int j=0;j<data.length;j++){
//                    if(FinalGroup.get(i).equals(data[j][2])){
//                       JButton lbl = new JButton(data[j][1].toString());
////                       lbl.setName(data[i][1].toString());
//                       lbl.setHorizontalTextPosition(SwingConstants.CENTER);
//                       lbl.setVerticalTextPosition(SwingConstants.BOTTOM);
//                       lbl.setBackground(new Color(255, 255,255));
//                      /*
//                       setting name
//                       */
////                       lbl.setName(data[j][1].toString());
////                       System.out.println(lbl.getName());
//                       lbl.setActionCommand(data[j][1].toString());
//                       /*
//                       adding action listener in for repective funtion calling 
//                       */
////                       ActionListener ac = null;
//                       addActionListener( new ButtonActionListener(), lbl);
//                    
//                       
//             
//            // System.out.println(data[i][3]);
//           if(data[j][3].equals(true)){
//               lbl.setIcon(imgon);
//           }
//           else{
//             lbl.setIcon(imgoff);   
//           }
//           
//            lbl.setPreferredSize(new Dimension(60, 100));
//            lbl.setBorder(BorderFactory.createLineBorder(new Color(102,153,255), 2,false));
//            panel[i].add(lbl);
////            panel[i].revalidate();
////            panel[i].repaint();
//           
//          //  Tablepanel.repaint();
//           //  Tablepanel.validate();
//                    }
//                }
//               // panel[i].add(new JLabel(FinalGroup.get(i)));
//                // System.out.println(panel[i]);
//                panel[i].setBorder(BorderFactory.createTitledBorder(FinalGroup.get(i)));
////                grandpanel[i].add(panel[i]);
//              //  scroll[i] = new JScrollPane(panel[i]);
//              //  Tablepanel.add(ji);
////               Tpanel.add(panel[i]);
////                grandpanel[i].add(panel[i]);
////               pack();
////                Tpanel.add(grandpanel[i]);
////                pack();
//                
////             Tpanel.add(scrollz[i]);
//               //Tpanel.add(scroll[i]);
////                Tpanel.revalidate();
////                Tpanel.repaint();
////               validate();
//                
//               
//            }
//           
//          
//        /*     for(int i = 0;i<data.length;i++){
//        // for(int j=0;j<data[i].length;j++){
//                // String panel = FinalGroup.get
//               //  JPanel FinalGroup = new JPanel();
//             JButton lbl = new JButton(data[i][1].toString());
//             lbl.setPreferredSize(new Dimension(60, 50));
//            // System.out.println(data[i][3]);
//           if(data[i][3].equals(true)){
//               lbl.setIcon(imgon);
//           }
//           else{
//             lbl.setIcon(imgoff);   
//           }
//            
//            lbl.setBorder(BorderFactory.createLineBorder(Color.green, 2,false));
//         //lbl.setBounds(10, 10, 20, 20);
//         
//         //lbl.setHorizontalAlignment(SwingConstants.LEFT);
//         Tablepanel.add(lbl);
//         Tablepanel.revalidate();
//         Tablepanel.repaint();
//         frame.pack(); 
//      //   }
//     }
//     */
//     /*for(int i=0;i<100;i++){
//         JButton lbl = new JButton("try");
//         
//        lbl.setBorder(BorderFactory.createLineBorder(Color.green, 2,false));
//         //lbl.setBounds(10, 10, 20, 20);
//         lbl.setPreferredSize(new Dimension(60, 50));
//         //lbl.setHorizontalAlignment(SwingConstants.LEFT);
//         Tablepanel.add(lbl);
//         Tablepanel.revalidate();
//         Tablepanel.repaint();
//         frame.pack();
//     }*/
//    //  JScrollPane js = new JScrollPane(Tablepanel);
//      //js.setVerticalScrollBar();
//    //  js.setAutoscrolls(true);
//     // js.setViewportView(Tablepanel);
//     // js.setPreferredSize(new Dimension(400,300));
//   // add(js);
//        //    add(Tablepanel);
//            return Tpanel;
//        
//    }
    public void addActionListener(ActionListener Listen,JButton btn){
        btn.addActionListener(Listen);
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        SwingUtilities.invokeLater(new Runnable(){
//
//            @Override
//            public void run() {
//              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            TableStatusView vw =  new  TableStatusView();
//           // vw.setVisible(true);
//            TableCrudModel model = new TableCrudModel();
// Object[][] data = model.getTableInfoObject();
//            vw.setPanel(/*vw,*/drawPanel(data));
//            }
//        });
    }
}
