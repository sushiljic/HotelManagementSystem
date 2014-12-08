/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package admin;

import hotelmanagementsystem.MainFrameView;
import javax.swing.JFrame;

/**
 *
 * @author SUSHIL
 */
public class ExecuteAdminPanel {
    private final AdminPanelView adminPanelView;
    private final AdminPanelModel adminPanelModel;
    private final AdminPanelController adminPanelController;
    
    public ExecuteAdminPanel(JFrame parent,Boolean modal){
            adminPanelView = new AdminPanelView(parent, modal);
            adminPanelModel = new AdminPanelModel();
            adminPanelController = new AdminPanelController(adminPanelView, adminPanelModel, (MainFrameView)parent);
            adminPanelView.setVisible(true);
    }
    
}
