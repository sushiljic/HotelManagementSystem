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
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import reusableClass.CyptoAES;
 
public class PasswordEncryption {
public static void main(String[] args) {
 /*
// for basic encryptions
BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
String password = encryptor.encryptPassword("helloworld");

System.out.println("Password encrypted by Basic password encryptor: "
+ password);
 
// you can verify password
boolean isOkay = encryptor.checkPassword("helloworld", password);
 
// for Stron encryptions
StrongPasswordEncryptor encryptor2 = new StrongPasswordEncryptor();
String password2 = encryptor2.encryptPassword("helloworld");
System.out.println("Password encrypted by String password encryptor: "
+ password2);
 
// Even you can configure algorithm
ConfigurablePasswordEncryptor encryptor3 = new ConfigurablePasswordEncryptor();
encryptor3.setAlgorithm("SHA-512");
String password3 = encryptor3.encryptPassword("helloworld");
System.out
.println("Password encrypted by Configurable password encryptor: "
+ password3);
         */
    System.out.println(CyptoAES.decypt("zd+n7avvXr0pnAOw62l18A=="));
}
         
 
    
}
