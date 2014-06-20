/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package companysetup;

import hotelmanagementsystem.MainFrameView;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import reusableClass.DisplayMessages;
import reusableClass.Function;

/**
 *
 * @author SUSHIL
 */
public final class CompanySetupController {
    private final CompanySetupView companyview;
    private final CompanySetupModel companymodel;
    private final MainFrameView mainview;
    private String Identifier = new String();
    private Double[] ChargeData = new Double[2]; 
    private int status ;
     public CompanySetupController(CompanySetupView view,CompanySetupModel model,MainFrameView mview){
        companyview = view;
        companymodel = model;
        mainview = mview;
        ChargeData = companymodel.getChargeInfo();
       
       
        this.companyview.addRegisterListener(new CompanySetupListener());
        this.companyview.addUpdateListener(new CompanySetupListener());
        this.companyview.addViewListener(new CompanySetupListener());
       /* this.companyview.addRegisterKeyListener(new CompanySetupKeyListener());
        this.companyview.addUpdateKeyListener(new CompanySetupKeyListener());
        this.companyview.addViewKeyListener(new CompanySetupKeyListener());*/
           companyview.addSaveListener(new CompanySaveCancelListener());
           companyview.addCancelListener(new CompanySaveCancelListener());
             /*
              * this is for charge setup
              */
            companyview.addChargeSetupListener(new CompanyChargeSetupListener());
            /*
             * adding check listener
             */
            companyview.addVATIncludeListener(new VatListener());
            companyview.addChargeEditListener(new ChargeEditListener());
            companyview.addChargeSaveListener(new ChargeEditListener());
            companyview.addChargeCancelListener(new ChargeEditListener());
            /*
            for upload the image
            */
            companyview.addImageUploadListener(new UploadImage(companyview.ImageLabel));
           // companyview.addVATNotIncludeListener(new VatListener());
           // companyview.addSaveKeyListener(new CompanySaveCancelKeyListener());
           // companyview.addCancelKeyListener(new CompanySaveCancelKeyListener());
            
         //checking the status of company
            try{
             status= Function.getCompanyStatus();
            }
            catch(Exception se){
                DisplayMessages.displayError(view, se.getMessage(), "from CompanySetupController ");
                companyview.setVisible(false);
            }
            if(status == 0){
             companyview.hidebtnView();
             companyview.hidebtnUpdate();
             companyview.hidebtnChargeSetup();
            
             //this will click the register button
             companyview.clickBtnRegister();
//             JOptionPane.showMessageDialog(mainview, "Wala");
             companyview.pack();
              
             companyview.addWindowCloseListener(new SystemCloseListener());
            }
            else{
                
                companyview.hidebtnRegister();
             /*
              * showing view if regiester
              */
                companyview.showPanelCompany();
              /*
                * her setcompanyview set the view of companysetupjava ans getcompanyifo retrieves data from database
                */
                setCompanyView(companymodel.getCompanyInfo()); 
                companyview.hideSaveBtn();
                companyview.hideCancelBtn();
                companyview.hidelblCompanyLogo();
                companyview.hidebtnCompanyLogo();
                companyview.setTxtEditableFalse();
                companyview.addWindowCloseListener(new CompanyCloseListener());
            }
        
    }
//    class WindowCloseListener extends WindowAdapter{
//        @Override
//        public void windowClosing(WindowEvent we){
//          companyview.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//        }
//    }
    class CompanySetupListener implements ActionListener{
       
        
        @Override
        public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equalsIgnoreCase("Register")){
            Identifier = "Register";
           
            companyview.showPanelCompany();
            companyview.setTxtEditableTrue();
            companyview.showSaveBtn();
            companyview.showCancelBtn();
            companyview.pack();
//            companyview.repaint();
            
          
         //   companyview.addSaveKeyListener(new CompanySaveCancelKeyListener());
          //  companyview.addCancelKeyListener(new CompanySaveCancelKeyListener());
            
        }
        if(ae.getActionCommand().equalsIgnoreCase("Update")){
            Identifier = "Update";
             companyview.showPanelCompany();
             companyview.setTxtEditableTrue();
              companyview.showSaveBtn();
                      companyview.showCancelBtn();
                      companyview.showlblCompanyLogo();
                      companyview.showbtnCompanyLogo();
              /*
                     * her setcompanyview set the view of companysetupjava ans getcompanyifo retrieves data from database
                     */
                      setCompanyView(companymodel.getCompanyInfo());
                      
          
           // Identifier = null;
            
        }
        if(ae.getActionCommand().equalsIgnoreCase("View")){
            Identifier = "View";
             companyview.showPanelCompany();
              /*
                     * her setcompanyview set the view of companysetupjava ans getcompanyifo retrieves data from database
                     */
                      setCompanyView(companymodel.getCompanyInfo()); 
                      companyview.hideSaveBtn();
                      companyview.hideCancelBtn();
                      companyview.setTxtEditableFalse();
                      
                     // setCompanyView(companymodel.getCompanyInfo());
                      
         
            
        }
            
        }
       
        
        
        
    }
     class CompanySaveCancelListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent scae){
                //this is dont so that we know whether the save button is from which ancestor
                if(!Identifier.isEmpty()){
                if(scae.getActionCommand().equalsIgnoreCase("Save") && Identifier.equalsIgnoreCase("Register")){
                   try{
                       
                       if(Function.checkBlankPhoneNumber(companyview.getPhone())){
                        JOptionPane.showMessageDialog(companyview,"Phone Number Cannot be Empty");
                        return;
                        } 
                       
                       if(companyview.getCompanyName().equals("")||companyview.getAddress().equals("")||companyview.getPan() ==0){
                        JOptionPane.showMessageDialog(companyview,"Blank Field Not Allowed.Recheck it");
                        return;
                        }   
                      
                    if(DisplayMessages.displayInputYesNo(companyview, "Do You Want To Register the Company?"," Register Window"))
                      {
                            /*
                       image method to save in images directories but now store indatabase
                            */
                       if(companyview.getBufferedImage() != null){
                              //checking it images folder exists or not if not create
                           Path imagepath = Paths.get("resources/images");
                           if(!Files.exists(imagepath)){
                               Files.createDirectories(imagepath);
                           }
//                           companymodel.SaveImage(companyview.getImageFile(), companyview.getBufferedImage());
                       }
                          
                   try{
                    if(companyview.getBufferedImage() != null){
//                            JOptionPane.showMessageDialog(mainview, "from no null bufrere image");
                        if(companymodel.registerCompany(getInfoIntoStringArray(),Function.returnFileFromBufferedImage(companyview.getImageFile(), companyview.getBufferedImage()))){
//                            DisplayMessages.displayInfo(companyview, "Company Inserted Successfully", "Company Insertion");
                        }   
                        
                        }
                        else{
                            if(companymodel.registerCompany(getInfoIntoStringArray(), null)){
                            
                            }
                        }
                   
                    /*
                      there the companymodel detail give information about the setcompany
                      */
                     DisplayMessages.displayInfo(companyview, "Company Inserted Successfully", "Company Insertion");  
                    mainview.setCompany(companymodel.getCompanyDetail());
                    companyview.showbtnView();
                    companyview.showbtnUpdate();
                    companyview.showbtnChargeSetup();
                    companyview.hidebtnRegister();
                    
                    Identifier = new String();
                    companyview.setVisible(false);
                    }
                   catch(FileNotFoundException | NoSuchAlgorithmException | SQLException se){
                       DisplayMessages.displayError(companyview, se.getMessage(), "From CompanySaveCancelListener ");
                   }
                       }
                   }
                   catch(HeadlessException | IOException e){
                       JOptionPane.showMessageDialog(companyview, e.getMessage()+"from Company register");
                   }
                   
                }
                if(scae.getActionCommand().equalsIgnoreCase("Cancel")&& Identifier.equalsIgnoreCase("Register")){
                    try{
                        companyview.hidePanelCompany();
                         Identifier = new String();
                        
                    }
                    catch(Exception e){
                         JOptionPane.showMessageDialog(companyview, e+"Jpanel problem");
                        
                    }
                }
                /*
                 * this is called if save button is clicked after update button is clicked
                 */
                if(scae.getActionCommand().equalsIgnoreCase("Save") && Identifier.equalsIgnoreCase("Update")){
                   try{
                       //code for update
//                       System.err.println(Function.checkPhoneNumberNull(companyview.getPhone()));
                       
                      if(Function.checkBlankPhoneNumber(companyview.getPhone())){
                        JOptionPane.showMessageDialog(companyview,"Phone Number Cannot be Empty");
                        return;
                        } 
                       if(companyview.getCompanyName().equals("")||companyview.getAddress().equals("")){
                            JOptionPane.showMessageDialog(companyview,"Blank Field Not Allowed.Recheck it");
                            return;
                        }
                             /*
                       image 
                      since image is loaded into datbase no nedd to execute this code
                        */
                       if(companyview.getBufferedImage() != null){
                           //checking it images folder exists or not if not create
                           Path imagepath = Paths.get("resources/images");
                           if(!Files.exists(imagepath)){
                               Files.createDirectories(imagepath);
                           }
//                           companymodel.SaveImage(companyview.getImageFile(), companyview.getBufferedImage());
                            /*
                       delete the old file in resources/images
                       */
                      
//                       Object[] st = companymodel.getCompanyDetail();
//                       String imgname = st[3].toString();
//                       Function.DeleteImage(Paths.get(imgname));
                       }
                      
                      try{
                        if(companyview.getBufferedImage() != null){
//                            JOptionPane.showMessageDialog(mainview, "from no null bufrere image");
                        companymodel.updateCompany(getInfoIntoStringArray(),Function.returnFileFromBufferedImage(companyview.getImageFile(), companyview.getBufferedImage()));   
                        
                        }
                        else{
                            companymodel.updateCompany(getInfoIntoStringArray(), null);
                        }
                        
                      
                      }
                      catch(Exception se){
                          JOptionPane.showMessageDialog(mainview, se+"from register");
                      }
                      /*
                      there the companymodel detail give information about the setcompany
                      */
                      try{
                      mainview.setCompany(companymodel.getCompanyDetail());
                      }
                      catch(Exception se){
                          JOptionPane.showMessageDialog(mainview, se+"from register");
                      }
                     // Identifier = null;
                      /*
                       * it wiil diretly redirect to view pageon succes of save
                       */
                                     
              /*
                     * her setcompanyview set the view of companysetupjava ans getcompanyifo retrieves data from database
                     */
                      
                      setCompanyView(companymodel.getCompanyInfo()); 
                      
                      companyview.hideSaveBtn();
                      companyview.hideCancelBtn();
                     
                      companyview.setTxtEditableFalse();
                      Identifier = new String();
                      companyview.dispose();
                  
                   }
                   catch(Exception e){
                       JOptionPane.showMessageDialog(companyview, e+"from Company info Update");
                   }
                   
                }
                if(scae.getActionCommand().equalsIgnoreCase("Cancel")&& Identifier.equalsIgnoreCase("Update")){
                    try{
                        companyview.hidePanelCompany();
                         Identifier = new String();
                        
                    }
                    catch(Exception e){
                         JOptionPane.showMessageDialog(companyview, e+"Jpanel problem");
                        
                    }
                }
                
                }
                
            }
        }
     class CompanyChargeSetupListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            if(e.getActionCommand().equalsIgnoreCase("TaxSetup")){
                 ChargeData = companymodel.getChargeInfo();
                  companyview.setSVC(ChargeData[0]);
                  
         companyview.setVAT(ChargeData[1]);
         if(companyview.getVAT().equals(0.0)){
             companyview.setradioVATIncludeCheckedFalse();
             companyview.setradioVATNotIncludeCheckedTrue();
         }
         else{
              companyview.setradioVATIncludeCheckedTrue();
              companyview.setradioVATIncludeCheckedFalse();
         }
         companyview.setChargeSVCEditableFalse();
         companyview.setChargeSaveEditableFalse();
         companyview.setChargeCancelEditableFalse();
         
        // companyview.setTitle("");
                companyview.JDailogChargeSetup.setVisible(true);
            }
        }
        catch(Exception se){
            JOptionPane.showMessageDialog(companyview.JDailogChargeSetup, "from CompanyChargeSetupListener");
        }
        }
         
     }
     class SystemCloseListener extends WindowAdapter{
         
         @Override
         public void windowClosing(WindowEvent we){
             if(DisplayMessages.displayInputYesNo(companyview, "This Will Close the System.\nDo You Wish to Close the System", "System Close From Register"))
                 System.exit(0);
                 }
     }
     class CompanyCloseListener extends WindowAdapter{
         @Override
         public void windowClosing(WindowEvent se){
             companyview.dispose();
         }
     }
     class VatListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       // JRadioButton jr = (JRadioButton)e.getSource();
       if(e.getStateChange() == 1){
           companyview.setVAT(new Double(13));
       }
       else{
          companyview.setVAT(new Double(0.0));
       }
         
        }
     }
     class ChargeEditListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getActionCommand().equalsIgnoreCase("ChargeEdit")){
            companyview.setChargeSaveEditableTrue();
            companyview.setChargeCancelEditableTrue();
            companyview.setChargeSVCEditableTrue();
            companyview.txtSVC.requestFocusInWindow();
            
           // System.out.println("sys");
       }
        if(e.getActionCommand().equalsIgnoreCase("ChargeSave")){
            companymodel.updateChargeCompany(companyview.getSVC(), companyview.getVAT());
            companyview.setChargeSaveEditableFalse();
            companyview.setChargeCancelEditableFalse();
            companyview.setChargeSVCEditableFalse();
            companyview.JDailogChargeSetup.setVisible(false);
          //  System.out.println(companyview.getSVC()+"\n"+companyview.getVAT());
            
        }
        if(e.getActionCommand().equalsIgnoreCase("ChargeCancel")){
              companyview.setChargeSaveEditableTrue();
            companyview.setChargeCancelEditableTrue();
            companyview.setSVC(0.0);
            
            
        }
        }
         
     }
      public class UploadImage implements ActionListener{
        JLabel ImageLabel;
        public UploadImage(JLabel jlabel){
            ImageLabel = jlabel;
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
         //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      JFileChooser chooser = new JFileChooser(){
         /*    public void approveSelection(){
                  File f = getSelectedFile();
            if(f.exists()){
                int result = JOptionPane.showConfirmDialog(this, "DO you want to overswrite the existing file","File already exists",JOptionPane.YES_NO_CANCEL_OPTION);
                switch(result){
                    case JOptionPane.YES_OPTION:
                        super.approveSelection();
                        return;
                    case JOptionPane.NO_OPTION:
                        return;
                    case JOptionPane.CANCEL_OPTION:
                    case JOptionPane.CLOSED_OPTION:
                        cancelSelection();
                        return;
            }
        }
           // super.approveSelection();
        }*/
        };
             
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
      /*  chooser.setFileFilter(new FileFilter(){
@override
            public String getDescription(){
                return "All Supported Image Formats";
            }
            @Override
            public boolean accept(File f) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(f.isDirectory()){
                return true;
            }
            else{
                return f.getName().toLowerCase().endsWith(".png")||f.getName().toLowerCase().endsWith("jpg")||f.getName().toLowerCase().endsWith(".gif");
            }
            }
            
        });
        * */
 
        chooser.setFileFilter(new FileFilter() {

            
            @Override
            public boolean accept(File p) {
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(p.isDirectory()){
                return true;
            }
            else{
                return p.getName().toLowerCase().endsWith(".png")||p.getName().toLowerCase().endsWith(".jpg")/*||p.getName().toLowerCase().endsWith(".gif")*/;
            }
            }

            @Override
            public String getDescription() {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return "png and jpeg image formats only";
            }
        });
        int res = chooser.showSaveDialog(null);
        if(res == JFileChooser.APPROVE_OPTION){
             companyview.setImageFile(chooser.getSelectedFile());
             if(!chooser.getFileFilter().accept(companyview.getImageFile())){
                  companyview.setImageFile(new File(companyview.getImageFile().getAbsoluteFile()+".png"));
              }
            
                    
            ImageIcon icon = new ImageIcon(companyview.getImageFile().getAbsolutePath());
            // ImageIcon imgicon = new ImageIcon("images/Welcome Scan.jpg");
           
         //  ImageLabel.setIcon(imgicon);
           
          //  jLabel1.setIcon(icon);
            Rectangle rect = ImageLabel.getBounds();
           Image scaledimage = icon.getImage().getScaledInstance(rect.width, rect.height, Image.SCALE_DEFAULT);
         //  System.out.println(rect.width+"\n"+rect.height);
            icon = new ImageIcon(scaledimage);
            
            //fro saving image into test directory
         
              /*  String fileName = File.getCanonicalPath();
            if (!fileName.endsWith(".png")) {
                File = new File(fileName + ".png");
            }*/
           //  BufferedImage  img = null;
              //covertin image into buffered image
            // img = ImageIO.read(file)
             
              companyview.setBufferedImage(new BufferedImage(rect.width,rect.height,BufferedImage.TYPE_INT_ARGB));
              companyview.getBufferedImage().getGraphics().drawImage(scaledimage, 0, 0, null);
             /*  Graphics2D bGr = buffImage.createGraphics();
    bGr.drawImage(scaledimage, 0, 0, null);
    bGr.dispose();
    */
          //   System.out.println(file.getName()); 
            ////  ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
////URL url = classLoader.getResource("");
//System.out.println(url.toURI());
//System.out.println(getClass().getClassLoader().getResource("")+"images");
//File file = new File(url.toURI());
              
            //  ImageIO.write
            //  ClassLoader cl = this.getClass().getClassLoader();
            //  String st = cl.getResource("")+"images/image.jpg";
              //URL url = getClass().getClassLoader().getResource("");
             // MenuEntryView.ImageLabel.setIcon(new ImageIcon(st));
       // System.out.println(cl.getResource(".")+"images/image.jpg");
            ImageLabel.setIcon(icon);
            companyview.setImagePath(companyview.getImageFile().getName());
            
            
        }
        else{
            JOptionPane.showMessageDialog(null, "cancelled by user");
        }
       
        }
    
}
//  
    
    public String[] getInfoIntoStringArray(){
       /*
        String strCName;
        String strCAddress;
        String strCCity;
        String strPhone;
        String strFax;
        String strWebsite;
        String strEmail;
        String strPanNo;
        string strcompanylogo
        * */
        String[] info = new String[10];
        try{
            
        info[0] = companyview.getCompanyName();
        info[1] = companyview.getAddress();
       //city is optional value so
       
        info[2] = companyview.getBillGreet();
        info[3] = String.valueOf(companyview.getPhone());
       
        info[4] = companyview.getFax();
        
        info[5] = companyview.getWebsite();
       
        info[6] = companyview.getEmail();
        info[7] = String.valueOf(companyview.getPan());
        info[8] = companyview.getImagePath();
        info[9] = companyview.getCompanySlogan();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(companyview,"fromInfoIntoString");
        }
        return info;
    }
    public void setCompanyView(Object[] cinfo){
        try{
        companyview.setCompanyName(cinfo[0].toString());
        companyview.setAddress(cinfo[1].toString());
       companyview.SetBillGreet(cinfo[2].toString());
        companyview.setPhone(cinfo[3].toString());
        companyview.setFax(cinfo[4].toString());
        companyview.setWebsite(cinfo[5].toString());
        companyview.setEmail(cinfo[6].toString());
        companyview.setPan(Integer.parseInt(cinfo[7].toString()));
        companyview.setImagePath(cinfo[8].toString());
        //this not nedd
//        companyview.loadImage(cinfo[8].toString());
        if(cinfo[10] == null){
        companyview.setImageLabel(null);
        }
        else{
        companyview.setImageLabel(new ImageIcon((byte[])cinfo[10]));
        }
        companyview.SetCompanySlogan(cinfo[9].toString());
        }
        catch(Exception es){
            JOptionPane.showMessageDialog(companyview, es+"from setCompanyview");
        }
    }
    
}
