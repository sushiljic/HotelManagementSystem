/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author SUSHIL
 */
public class readDatabase {
    public readDatabase(){
//      
//        readLine(Paths.get("database.dat"));
//        createDatabaseFile(Paths.get("database.dat"));
//        writeData(Paths.get("database.dat"));
    }
    public boolean checkDatabaseFile(Path path){
          Charset charset = Charset.forName("US-ASCII");
          
        Path filepath = Paths.get("resources/system/"+path);
        return Files.exists(filepath);
    }
    public String[] readLine(Path path){
        Charset charset = Charset.forName("US-ASCII");
        ArrayList<String> data = new ArrayList<>();
        String[] databasedata = null;
        Path filepath = Paths.get("resources/system/"+path);
        try(BufferedReader reader = Files.newBufferedReader(filepath, charset))
        {
            String line = null;
            while((line = reader.readLine())!= null){
//            System.out.println(line);
            data.add(line);
        }
            databasedata = data.toArray(new String[data.size()]);
        }
        catch(IOException ioe){
                JOptionPane.showMessageDialog(null, ioe+getClass().getName());
                System.exit(0);
                }
        return databasedata;
    }
    public void deleteFile(Path path){
          Charset charset = Charset.forName("US-ASCII");
        Path filepath = Paths.get("resources/system/"+path);
     try{
        Files.deleteIfExists(filepath);
     }
     catch(IOException ioe){
         JOptionPane.showMessageDialog(null, ioe);
     }
    }
    public void writeData(Path path,String uname,String upass,String ulocation,String udatabase){
        Charset charset = Charset.forName("US-ASCII");
        Path filepath = Paths.get("resources/system/"+path);
        deleteFile(path);
//         try(OutputStream out = new BufferedOutputStream(Files.newOutputStream(filepath, CREATE))){
       try(BufferedWriter out = new BufferedWriter(Files.newBufferedWriter(filepath,charset,CREATE))){
       String username = uname;
       String pass = upass;
       String lc = ulocation;
       String db = udatabase;
       
//       Byte[] user = ;
//             out.write(username.getBytes(), 0   , username.length());
//             out.write(newline.getBytes());
//             out.write(pass.getBytes());
//               out.write(newline.getBytes());
//             out.write(lc.getBytes());
//               out.write(newline.getBytes());
//             out.write(db.getBytes());
            out.write(username, 0, username.length());
            out.newLine();
            out.write(pass);
            out.newLine();
            out.write(lc);
            out.newLine();
            out.write(db);
           
                    
//             JOptionPane.showMessageDialog(null, "Database.dat file create Succesfully");
        }
        catch(IOException ioe){
            JOptionPane.showMessageDialog(null,ioe+" from createdatabasefile");
        }
    }
    public void createDatabaseFile(Path path){
         Charset charset = Charset.forName("US-ASCII");
        Path filepath = Paths.get("resources/system/"+path);
        if(!Files.exists(filepath))
        {
        try(OutputStream out = new BufferedOutputStream(Files.newOutputStream(filepath, CREATE))){
        JOptionPane.showMessageDialog(null, "Database.dat file create Succesfully");
        }
        catch(IOException ioe){
            JOptionPane.showMessageDialog(null,ioe+" from createdatabasefile");
        }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new readDatabase();
    }
    
}
