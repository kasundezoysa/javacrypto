/*
Kasun De Zoysa @ UCSC
*/

import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class Encrypt {

public static void main(String args[]) {
  if(args.length<2) {
    System.out.println("Usage:java Encrypt plaintext ciphertext");
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
   SecretKeySpec   encKey = new SecretKeySpec(k,"AES");

// init encryption
   Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
   cipher.init(Cipher.ENCRYPT_MODE,encKey);

// Open input file
   FileInputStream fis = new FileInputStream(args[0]);

//Open output file input and out fileencryption pass
   FileOutputStream fos = new FileOutputStream(args[1]);
   CipherOutputStream cOut = new CipherOutputStream(fos,cipher);

//Read from plain text input file and write to cipher text output file
   byte[] b = new byte[8];
   int i = fis.read(b);
   while (i != -1) {
     cOut.write(b, 0, i);
     i = fis.read(b);
   }
   cOut.close();

//Delete the plain text file
  File f=new File(args[0]);
  f.delete();
  } catch (Exception e) {
     System.out.println(e);
  }
 System.out.println("Encrypted!");
 }
}
