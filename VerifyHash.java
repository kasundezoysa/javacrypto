/*
Kasun De Zoysa @ UCSC
*/

import java.io.*;
import java.security.*;

public class VerifyHash {

  public static void main(String args[]) {

    try {

 MessageDigest      md  = MessageDigest.getInstance("SHA");

 FileInputStream     fis = new FileInputStream(args[0]);
        BufferedInputStream bis = new BufferedInputStream(fis);
        DigestInputStream   dis = new DigestInputStream(bis,md);

        // read the file and update the hash calculation
        while (dis.read() != -1);

        // get the hash value as byte array
        byte[] newHash = md.digest();

 // read Orig. hash
 BufferedReader br = new BufferedReader(new FileReader(new File("Hash.txt")));
 byte[] oldHash=hexStringToByteArray(br.readLine());

 // compare
 if(md.isEqual(oldHash,newHash))
  System.out.println("Digest Verified");
 else  System.out.println("Digest Verification failed!");

      } catch (Exception e) {

  System.out.println(e);

      }

   }
 
 private static byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) <<4)+Character.digit(s.charAt(i+1), 16));
    }
    return data;
 }

}
