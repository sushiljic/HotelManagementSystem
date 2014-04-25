/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reusableClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author SUSHIL
 */
public class Register {
    //this function generated the macaddres with the current date vaule
      public static String generateMac(){
         //return the mac address only with - and add F at last of the index
        String password= new String();
         String Mixed = new String();
        try{
         Process pro = Runtime.getRuntime().exec("getmac  /fo csv /nh");
        BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
        String line = in.readLine();
         String[] Splitedline = line.split(",");
         password = Splitedline[0].replace('"',' ').trim();
        password = password.replaceAll("-","");
        //add FF as false status
       //getting the date and ecncrting its value with respect to A
        SimpleDateFormat df = new SimpleDateFormat("MMMd");
        String date = df.format(new Date());
//        System.out.println(date);
//        String EncDate = new String();
//        for(char c:date.toCharArray()){
//            //add A to the date
////            System.out.println((int)c);
//            int ascii = (int)c+(int)'A';
////            System.out.println((char)ascii);
//            EncDate +=(char)ascii; 
//        }
        //mix the number into the string
//        password = date+password;
         date = date.toUpperCase();
        char[] charDate = date.toCharArray();
        char[] charpassword = password.toCharArray();
//        System.out.println(charpassword);
       
        Mixed =  String.valueOf(charDate,0,3);//month
        Mixed += String.valueOf(charpassword, 0, 2);
        Mixed += String.valueOf(charDate,3, 1);//1st digit Day
        Mixed += String.valueOf(charpassword, 2, 3);
        Mixed += String.valueOf(charDate, 4, 1);//2nd digit day
        Mixed += String.valueOf(charpassword ,2,charpassword.length-2);
        }
        
        catch(IOException se){
            JOptionPane.showMessageDialog(null, se.getMessage()+"from generateMac");
        }
       
        return Mixed;
               
    }
     //Encode the st to char + its index
     public static String EncodeSerailCharCodeByIndex(String st){
        String charstring = new String();
        int encodechar = 0;
        for(int i =0;i<st.length();i++){
            
            char c = st.charAt(i);
            if(Character.isDigit(c)){
                 encodechar = (int) c;
            }
            else{
                  encodechar = (int)c+i;
            }
            
           
//            int encodechar = (int)c+'A';
            charstring +=(char)encodechar;
        }
        return charstring;
    }
    //Decode the st to original string with mac addres
     public static String DecodeSerialCharCodeByIndex(String st){
        String originalString = new String();
        int decodechar =0;
        for(int i=0;i<st.length();i++){
            char c = st.charAt(i);
             if(Character.isDigit(c)){
                 decodechar = (int) c;
            }
            else{
                  decodechar = (int)c-i;
            }
            
//            int decodechar = (int)c - 'A';
            originalString += (char)decodechar;
        }
        return originalString;
    }
      //Encode the st to char + its index
     public static String EncodeRegisterCharCodeByIndex(String st){
        String charstring = new String();
        int encodechar = 0;
        for(int i =0;i<st.length();i++){
            
            char c = st.charAt(i);
            if(Character.isDigit(c)){
                //if it is digit divide take modulus
                 encodechar = (int) c;
            }
            else{
                  encodechar = (int)c+i;
            }
            
           
//            int encodechar = (int)c+'A';
            charstring +=(char)encodechar;
        }
        return charstring;
    }
    //Decode the st to original string with mac addres
     public static String DecodeRegisterCharCodeByIndex(String st){
        String originalString = new String();
        int decodechar =0;
        for(int i=0;i<st.length();i++){
            char c = st.charAt(i);
             if(Character.isDigit(c)){
                 decodechar = (int) c;
            }
            else{
                  decodechar = (int)c-i;
            }
            
//            int decodechar = (int)c - 'A';
            originalString += (char)decodechar;
        }
        return originalString;
    }
    //return 5 random character as String 
    public static String returnRandomString(){
        Random r = new Random();
        String rand = new String();
        for(int i=0;i<5;i++){
            char c = (char)(r.nextInt(26)+'A');
            rand += c;
        }
        return rand;
    }
     
     
}
