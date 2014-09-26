/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testinggithub;

/**
 *
 * @author SUSHIL
 */
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
public class AbstractTableDemo extends JPanel{
 
 public AbstractTableDemo() {
 super(new GridLayout(1,0));
 
 final JTable table = new JTable(new MyTableModel());
 table.setPreferredScrollableViewportSize(new Dimension(500, 70));
 table.setFillsViewportHeight(true);
 
 Object[] values = {"String", 10, 20.0, 30.2, new Boolean(false)};
 MyTableModel a = (MyTableModel) table.getModel();
 a.insertData(values);
 
 
 //Create the scroll pane and add the table to it.
 JScrollPane scrollPane = new JScrollPane(table);
 
 //Add the scroll pane to this panel.
 add(scrollPane);
 }
 
 class MyTableModel extends AbstractTableModel {
 private String[] columnNames = {"Title A", "Title B", "Title C", "Title D", "Title E"};
 
 private Vector data = new Vector();
 
 public final Object[] longValues = {"", new Integer(20), new Float(20), new Float(20), Boolean.TRUE};
 
 @Override
 public int getColumnCount() {
 return columnNames.length;
 }
 
@Override
 public int getRowCount() {
 return data.size();
 }
 
@Override
 public Object getValueAt(int row, int col) {
 return ((Vector) data.get(row)).get(col);
 }
 
 public String getColumnName(int col){
 return columnNames[col];
 }
 public Class getColumnClass(int c){
 return getValueAt(0,c).getClass();
 }
 
 public void setValueAt(Object value, int row, int col){
 ((Vector) data.get(row)).setElementAt(value, col);
 fireTableCellUpdated(row,col);
 }
 
 public boolean isCellEditable(int row, int col){
 if (4 == col){
 return true;
 }
 else if(3==col){
 return true;
 }
 else {
 return false;
 }
 }
 
 public void insertData(Object[] values){
 data.add(new Vector());
 for(int i =0; i<values.length; i++){
 ((Vector) data.get(data.size()-1)).add(values[i]);
 }
 fireTableDataChanged();
 }
 
 public void removeRow(int row){
 data.removeElementAt(row);
 fireTableDataChanged();
 }
 }
 
 private static void createAndShowGUI(){
 JFrame frame = new JFrame("Abstract Table Demo");
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
 AbstractTableDemo newContentPane = new AbstractTableDemo();
 frame.setContentPane(newContentPane);
 
 //Display the window.
 frame.pack();
 frame.setVisible(true);
 }
 
 public static void main(String[] args){
 javax.swing.SwingUtilities.invokeLater(new Runnable() {
 public void run() {
 createAndShowGUI();
 }
 });
 }
}
