/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package databaseCRUD;

import database.DBConnect;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import reusableClass.DisplayMessages;

/**
 *
 * @author SUSHIL
 */
public  class BackUp extends DBConnect {
    private static String ip;
    private static String port;
    private static String database;
    private static String user;
    private static String pass;
    private static String path;
    
    public BackUp(){
        filechooser();
    }
     public void filechooser(){
        JFileChooser chooser = new JFileChooser("."){
            //this will check some process 
            @Override
            public void approveSelection(){
                File f = getSelectedFile();
                String fname = f.getName();
               
                //check it is exist for not/if exist ast to overwrite the existing file
                if(f.exists()){
                   if(DisplayMessages.displayInputYesNo(this, "Do You want to Overrite the Existing File?","Duplicate File")){
                       super.approveSelection();
                       
                   }
                   else{
//                       super.cancelSelection();
                       
                   }
                }
                else{
                    //check for other character such as . or , 
                    if(fname.contains(".")||fname.contains(",")){
                       DisplayMessages.displayInfo(this, "Please Enter valid filename.", "Filename Validation");
                       return;
                    }
                    
                    super.approveSelection();
                }
            }
        };
        chooser.setDialogTitle("Backup  your Data");
        
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        chooser.setAcceptAllFileFilterUsed(false);
       
        chooser.addChoosableFileFilter(new FileFilter(){

           @Override
            public boolean accept(File f) {
                if(f.isDirectory()){
                    return true;
                }
               
                else{
                    
                    return f.getName().endsWith(".sql");
                  
                }
                
            }

            @Override
            public String getDescription() {
                return ".sql ";
            }
            
        });
         int result = chooser.showSaveDialog(null);
         
         if(result == JFileChooser.APPROVE_OPTION){
             
            try{
            String st = chooser.getSelectedFile().getCanonicalPath().concat(".sql");
            //get datababase connection and file name
            
            try{
                initConnection();
            }
            catch(Exception se){
                DisplayMessages.displayError(chooser, "Unable to fetch user information for backup", "Back up error");
                System.exit(1);
            }
            backupDB(getDatabaseName(), getUserName(), getPassword(), st);
            }
            catch(IOException se){
                se.printStackTrace();
            }
        }
        else{
            
        }
        
        
    }
     public boolean backupDB(String dbName, String dbUserName, String dbPassword, String path) {
        
 
        String executeCmd = "mysqldump -u " + dbUserName + " -p" + dbPassword + " --add-drop-database -B " + dbName + " -r " + path;
        Process runtimeProcess;
        try {
 
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
 
            if (processComplete == 0) {
                DisplayMessages.displayInfo(null, "Backup created successfully", "Backup Data");
                System.out.println("Backup created successfully");
                return true;
            } else {
                DisplayMessages.displayError(null, "Could not create the backup", "Backup Data");
                System.out.println("Could not create the backup");
            }
        } catch (Exception ex) {
            DisplayMessages.displayError(null, "Could not create the backup.\n"+ex.getMessage(), "Backup Data");
            ex.printStackTrace();
        }
 
        return false;
    }
     //main execution
     public static void main(String arg[]){
         BackUp backdata = new BackUp();
//         backdata.filechooser();
     }
    
}
