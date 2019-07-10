/*
Kasun De Zoysa @ UCSC
*/

import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class Decrypt {



public static void main(String args[]) {

  if(args.length<1) {
    System.out.println("Usage: Decrypt ciphertext plaintext");
    return;
  }

  try {
//Read a password
   System.out.print("Enter Your Password :");
   char [] ch = System.console().readPassword();
   String s= new String(ch);

//Generate a key
   SecureRandom sr1 = SecureRandom.getInstance("SHA1PRNG");
   sr1.setSeed(s.getBytes());
   byte[] k = new byte[128/8]; 
   sr1.nextBytes(k);
   SecretKeySpec   decKey = new SecretKeySpec(k,"AES");
  
// init encryption
   Cipher  cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
   cipher.init(Cipher.DECRYPT_MODE, decKey);
 

// Open input file
   FileInputStream fis = new FileInputStream(args[0]);
   CipherInputStream cIn = new CipherInputStream(fis,cipher);


// Open output file
   FileOutputStream fos = new FileOutputStream(args[1]);
   byte[] b = new byte[8];
   int i = cIn.read(b);
   while (i != -1) {
     fos.write(b, 0, i);
     i = cIn.read(b);
   }
   fos.close();

//Delete the cipher text file
  File f=new File(args[0]);
  f.delete();

  } catch (Exception e) {

     System.out.println(e);

  }
 System.out.println("Decrypted!");

 }

}
