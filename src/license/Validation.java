package license;

public class Validation {
	
private String key ="SERIALKEYWORD";
	
	public boolean Validate(String serialcode,String LicenseKey){
		
		String decrSerial="";
		LicenseKey= LicenseKey.toUpperCase();
		
		
		if(LicenseKey.length()!=12){
//			System.out.println("Validation Failure");
			return false;
			
		}
		
		LicenseKey= LicenseKey.substring(2, 10);
//		System.out.println(LicenseKey +" is serial key");
		
		
	decrSerial =this.decipher(LicenseKey, key);
     System.out.println(decrSerial);
	if(decrSerial.equals(serialcode)){
		
//		System.out.println("Validation Successful");
		return true;
	}
		
//	System.out.println("Invalid Serial Key");
		return false;
	}

// Sets the key explicitly for decryption 	
public void setKey(String key){
	key =key.replaceAll("[^a-zA]", "");
	key =key.toUpperCase();
	this.key=key;
}

// returns the key being used for decryption
public String getKey(){
	
	return this.key;
}
public static String decipher(String s, String key){

StringBuilder builder = new StringBuilder();
for(int i = 0; i < s.length(); i ++){

if(s.charAt(i) < 65 || s.charAt(i) > 90){ //ASCII character (capital letter)

throw new IllegalArgumentException("" +

"Ciphertext must contain only capital letters");

}

//subtract shift modularly

char decyphered = s.charAt(i) - getShift(key, i) < 65 ? (char)((s.charAt(i) - getShift(key, i)) + 26) : (char)(s.charAt(i) - getShift(key, i));

builder.append(decyphered);

}

return builder.toString();

}


private static int getShift(String key, int i) {

if(key.charAt(i % key.length()) < 65 || key.charAt(i % key.length()) > 90){

throw new IllegalArgumentException("" +

"Key phrase must contain only capital letters");

}

return ((int)key.charAt(i % key.length())) - 65;

}
//public static void main(String[] args){
//	
//	 // use case
//	// Instantiate the class 
//  //  Invoke the method  Validation method  supplying the customer code and the serial key  as parameters
// //  if key is explictly defined in SerialKeyGeneration then same key must be defined using setKey method
////	 It will return  true if the serial key is valid or false if the serial key is invalid..............
//	Validation val = new Validation();
//	val.Validate("EFCFACDF","ZCWJTNANNJZU" );
//	
//  
//}
 

}
