/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author SUSHIL
 */
public class function   {
    
    public Object[][] returnData(ResultSet rs){
        Object[][] finaldata = null;
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        int row = 0;
        int col = 0;
        ResultSetMetaData rsmd;
        try{
          rsmd  = rs.getMetaData();
        col = rsmd.getColumnCount();
      //  row = getNumberOfRows(rs);
      //  data = new Object[row][col];
        
       // rs.beforeFirst();
        while(rs.next()){
            
         //   for(int i =0;i<row;i++){
           //     for(int j =0;j<col;col++){
               //     data[i][j] = rs.getObject(j+1);
              //  }
               // rs.next();
            Object[] obj = new Object[col];
            for(int i =0;i<col;i++){
               obj[i] = rs.getObject(i+1);
            }
            data.add(obj);
            }
        finaldata = data.toArray(new Object[data.size()][]);
        }
        
        catch(SQLException se){
            JOptionPane.showMessageDialog(null, se+"form returnData function");
        }
        return finaldata;
    }
    public int getNumberOfRows(ResultSet rs){
       int n = 0;
        try{ rs.last();
          n = rs.getRow();
        rs.beforeFirst();
        }
        catch(SQLException sqlEx){
            JOptionPane.showMessageDialog(null, sqlEx+"from getNumber of rows function");
        }
        return n;
    }
    /**
     * This is implement by image upload button
     */
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
                return p.getName().toLowerCase().endsWith(".png")||p.getName().toLowerCase().endsWith(".jpg")||p.getName().toLowerCase().endsWith(".gif");
            }
            }

            @Override
            public String getDescription() {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return "All suported image formats";
            }
        });
        int res = chooser.showSaveDialog(null);
        if(res == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();
             if(!chooser.getFileFilter().accept(file)){
                  file = new File(file.getAbsoluteFile()+".png");
              }
            
                    
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            // ImageIcon imgicon = new ImageIcon("images/Welcome Scan.jpg");
           
         //  ImageLabel.setIcon(imgicon);
           
          //  jLabel1.setIcon(icon);
            Rectangle rect = ImageLabel.getBounds();
           Image scaledimage = icon.getImage().getScaledInstance(rect.width, rect.height, Image.SCALE_DEFAULT);
         //  System.out.println(rect.width+"\n"+rect.height);
            icon = new ImageIcon(scaledimage);
            
            //fro saving image into test directory
          try{
             
              /*  String fileName = File.getCanonicalPath();
            if (!fileName.endsWith(".png")) {
                File = new File(fileName + ".png");
            }*/
           //  BufferedImage  img = null;
              //covertin image into buffered image
            // img = ImageIO.read(file)
             
             BufferedImage buffImage = new BufferedImage(rect.width,rect.height,BufferedImage.TYPE_INT_ARGB);
              buffImage.getGraphics().drawImage(scaledimage, 0, 0, null);
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
              /*
               * path of the image to save
               */
      File path = new File("images/"+ file.getName());  
//File path = new File(getClass().getClassLoader().getResource("")+"images/"+file.getName());
//System.out.println(getClass().getClassLoader().getResource("")+"images/"+file.getName());
      
//File path = new File(url.toURI());      
if(path.exists()){
        //   int result = JOptionPane.showConfirmDialog(this, "DO you want to overswrite the existing file","File already exists",JOptionPane.YES_NO_CANCEL_OPTION);
    //int result = 0;
              int   result = JOptionPane.showConfirmDialog(chooser,"DO you want to overswrite the existing file","File already exits", JOptionPane.YES_NO_CANCEL_OPTION);
    switch(result){
                    case JOptionPane.YES_OPTION:
                       break;
                        
                    case JOptionPane.NO_OPTION:
                        
                        return;
                    case JOptionPane.CANCEL_OPTION:
                    case JOptionPane.CLOSED_OPTION:
                       
                        return;
                      }
      } 
if(file.getName().endsWith(".png")){
               System.out.println("png type"); 
    
              ImageIO.write(buffImage,"png",path);
}
else if(file.getName().endsWith(".jpg")){
      System.out.println("jpg type");
    ImageIO.write(buffImage, "jpg", path);
}
else if(file.getName().endsWith(".gif")){
      System.out.println("gif type");
    ImageIO.write(buffImage, "gif", path);
}
            //  ImageIO.write
          }
          catch(IOException se){
             JOptionPane.showMessageDialog(null,se);
          } 
            ImageLabel.setIcon(icon);
            
            
        }
        else{
            JOptionPane.showMessageDialog(null, "cancelled by user");
        }
       
        }
    
}
}
