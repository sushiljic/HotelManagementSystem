/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package department;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author SUSHIL
 */
public class ExecuteDepartment {
    private DepartmentModel departmentModel;
    private DepartmentView departmentView;
    public ExecuteDepartment(JFrame parent,boolean modal){
        departmentView = new DepartmentView(parent, modal);
        departmentModel = new DepartmentModel();
        DepartmentController controller = new DepartmentController(departmentModel,departmentView);
//       JOptionPane.showMessageDialog(null, "radhe");
        departmentView.setVisible(true);
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           ExecuteDepartment executeDepartment = new ExecuteDepartment(new JFrame(), true);
//            JOptionPane.showMessageDialog(null, "radhe");
            }
        });
    }
    
}
