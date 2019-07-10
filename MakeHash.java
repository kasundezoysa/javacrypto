/*
Kasun De Zoysa @ UCSC
*/

import java.io.*;
import java.security.*;
import java.util.Formatter;

public class MakeHash {
 public static void main(String args[]) {
   try {
    
     MessageDigest      md  = MessageDigest.getInstance("SHA");
     
        FileInputStream     fis = new FileInputStream(args[0]);
        BufferedInputStream bis = new BufferedInputStream(fis);
        DigestInputStream   dis = new DigestInputStream(bis,md);

        // read the file and update the hash calculation
        while (dis.read() != -1);

        // get the hash value as byte array
        byte[] hash = md.digest();

 // save hash  
 BufferedWriter fos = new BufferedWriter(new FileWriter(new File("Hash.txt")));
 fos.write(byteArray2Hex(hash));
 fos.close();

 System.out.println("Digest Updated");
   } catch (Exception e) {
 System.out.println(e);
   }
 }

  private static String byteArray2Hex(byte[] hash) {
     Formatter formatter = new Formatter();
     for (byte b : hash) formatter.format("%02x", b);
     return formatter.toString();
  }

}
