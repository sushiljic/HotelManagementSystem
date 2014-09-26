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
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class TableModelListenerDemo {
  public static void main(String args[]) {

    final Object rowData[][] = { { "1", "one", "I" }, { "2", "two", "II" }, { "3", "three", "III" } };
    final String columnNames[] = { "#", "English", "Roman" };

    final JTable table = new JTable(rowData, columnNames);
    JScrollPane scrollPane = new JScrollPane(table);
    table.getModel().addTableModelListener(new TableModelListener() {

      public void tableChanged(TableModelEvent e) {
         System.out.println(e);
         
      }
    });

    table.setValueAt("",0,0);
    JFrame frame = new JFrame("Resizing Table");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.add(scrollPane, BorderLayout.CENTER);

    frame.setSize(300, 150);
    frame.setVisible(true);

  }
}