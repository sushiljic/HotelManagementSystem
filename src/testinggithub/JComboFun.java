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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JComboFun {
   public static void main(String[] args) {
//       //one way
//        String[] list = {"A", "B", "C"};
//JComboBox jcb = new JComboBox(list);
//jcb.setEditable(true);
//JOptionPane.showMessageDialog( null, jcb, "select or type a value", JOptionPane.QUESTION_MESSAGE);
//another
     String[] weekdays = { "Monday", "Tuesday", "Wednesday", "Thursday",
            "Friday" };
      final JComboBox<String> combo = new JComboBox<>(weekdays);

      String[] options = { "OK", "Cancel", "Fugedaboutit" };

      String title = "Title";
     /*  int selection = JOptionPane.showOptionDialog(null, combo, title,
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
            options, options[0]);

      if (selection > 0) {
         System.out.println("selection is: " + options[selection]);
      }

      Object weekday = combo.getSelectedItem();
      if (weekday != null) {
         System.out.println("weekday: " + weekday);
      }*/
      //another inserting panel in joptionane
        JPanel panel = new JPanel();
        panel.add(new JButton("Click"));
        panel.add(new JTextField(20));
        panel.add(combo);
        JOptionPane.showMessageDialog(null,panel,"Information",JOptionPane.INFORMATION_MESSAGE);
     

   }
}

