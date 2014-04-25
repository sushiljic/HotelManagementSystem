/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reusableClass;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author SUSHIL
 */
public class CyptoAES {
    private static final String AlgorithmName = "AES";
    private static final byte[] KeyValue = new byte[]{'D','R','E','A','M','S','Y','S','P','O','S','R','A','D','H','E'};
   
    //this function will encypt the string using Aes alogoritm
    public static String encrypt(String data){
         String encodedValue = null;
        try{
        Key key = generateKey(KeyValue, AlgorithmName);
        Cipher c= Cipher.getInstance(AlgorithmName);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encyptedValue = c.doFinal(data.getBytes());
         encodedValue = new String(Base64.encodeBase64(encyptedValue));
//         System.out.println(encodedValue);
        
        }
        catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException se){
            DisplayMessages.displayError(null, se.getMessage()+"from encrypt CyptoAES.java ", "Error");
        }
        return encodedValue;
        
    }
    //this function will deceypt the string 
    public static String decypt(String data){
        byte[] decodedValue = null;
        String decyptedValue = null;
        try{
            Key key = generateKey(KeyValue, AlgorithmName);
            Cipher c= Cipher.getInstance(AlgorithmName);
            c.init(Cipher.DECRYPT_MODE, key);
            decodedValue = Base64.decodeBase64(data.getBytes());
            byte[] decyptedbtye = c.doFinal(decodedValue);
            decyptedValue = new String(decyptedbtye);
            
        }
        catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException se){
            DisplayMessages.displayError(null, se.getMessage()+"from encrypt CyptoAES.java ", "Error");
        }
        return decyptedValue;
        
    }
    private static Key generateKey(byte[] key,String algo){
        Key ky = new SecretKeySpec(key, algo);
        return ky;
    }
}
