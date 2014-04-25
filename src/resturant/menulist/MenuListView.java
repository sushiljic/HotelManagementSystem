/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.menulist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 *
 * @author SUSHIL
 */
public class MenuListView extends JFrame {
    JPanel SearchPane = new JPanel(new FlowLayout());
      JPanel Tablepanel = new JPanel(new GridLayout(0,1));
      JLabel lblSearch ;
      JButton btnSearch;
      JButton btnPrint;
      JTextField txtSearch;
   
public MenuListView(JFrame parent,boolean modal){
//    super(parent,modal);
//    setModalityType(ModalityType.DOCUMENT_MODAL);
    //setPreferredSize(new Dimension(300,500));
   // createAndShowUI();
    initComponents();
    setButtonForEnter(btnSearch);
    txtSearch.addFocusListener(new SetFocusListener(txtSearch));
    
  //  Tablepanel.updateUI();
//     Tablepanel.setBorder(BorderFactory.createLineBorder(Color.red));
//    add(Tablepanel);
   
   // add(drawPanel(data));
   
}
public void setPanel(/*MenuListView view,*/JPanel pnl){
    
   
//    remove(Tablepanel);
    getContentPane().setLayout( new BorderLayout());
    Tablepanel.removeAll();
    JScrollPane pane = new JScrollPane();
//    pnl.setSize(300, 400);
    pane.setViewportView(pnl);
    /*view.*/Tablepanel.add(pane);
    
//            Tablepanel.repaint();;
Tablepanel.validate();
    add(SearchPane,BorderLayout.NORTH);
    add(Tablepanel,BorderLayout.CENTER);
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
        setTitle("Menu Item GUI View");
         setSize(900, 630);
         setPreferredSize(new Dimension(900, 630));
         setLocationRelativeTo(null);
         //adding components
         lblSearch = new JLabel("Search:    ");
         txtSearch = new JTextField(20);
         btnSearch = new JButton("Search");
         btnPrint = new JButton("Print");
         
         SearchPane.add(lblSearch);
         SearchPane.add(txtSearch);
         SearchPane.add(btnSearch);
         SearchPane.add(btnPrint);
//         pack();
    
  //  this.getContentPane().setLayout(new FlowLayout());
//    this.setVisible(true);
      // Tablepanel.setSize(300, 500);
       
   //  add(Tablepanel);
     
   
      
    // frame.setPreferredSize(new Dimension(200, 300));
    // frame.add(frame);
    }
   

    public void addActionListener(ActionListener Listen,JButton btn){
        btn.addActionListener(Listen);
    }
    public String gettxtSearch(){
        return txtSearch.getText().trim();
    }
    public void settxtSearch(String st){
       txtSearch.setText(st);
    }
    public void addSearchListener(ActionListener Listen){
        btnSearch.addActionListener(Listen);
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
         jf.selectAll();
        }

        @Override
        public void focusLost(FocusEvent e) {
        //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       jf.setBackground(Color.white);
        }
        
    }
     public void addTextSearchListener(ActionListener Listen){
         txtSearch.addActionListener(Listen);
     }
     public void addPrintListener(ActionListener Listne){
         btnPrint.addActionListener(Listne);
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
//            MenuListView vw =  new  MenuListView();
//           // vw.setVisible(true);
//            TableCrudModel model = new TableCrudModel();
// Object[][] data = model.getTableInfoObject();
//            vw.setPanel(/*vw,*/drawPanel(data));
//            }
//        });
    }
}
