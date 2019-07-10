/*
Kasun De Zoysa @ UCSC
*/

import java.security.*;
import java.io.*;


public class UCSCHash{

public static void main(String args[]) {

 try {
//Add the new provider
      Security.addProvider(new UCSCProvider());
      Security.removeProvider("SUN");

//Message
    String data = "kasun!";
    byte dataBytes[] = data.getBytes();

//Digest creation
    MessageDigest md1 = MessageDigest.getInstance("SHA");
    md1.update(dataBytes);
    byte digest1[] = md1.digest();

    System.out.println("Orig. digest: ");
    for(byte b:digest1) System.out.format("%02x",b);
    System.out.println("");

//Digest verification
    String data2 = "kasun!";
    MessageDigest md2 = MessageDigest.getInstance("SHA");
    md2.update(data2.getBytes());
    byte digest2[] = md2.digest();

    System.out.println("New digest: ");
    for(byte b:digest2) System.out.format("%02x",b);
    System.out.println("");

    if(md2.isEqual(digest1,digest2))
      System.out.println("Digest Verified");
    else
      System.out.println("Digest Verification failed!");

  } catch (Exception e) {
     System.out.println(e);
  }

 }

}
