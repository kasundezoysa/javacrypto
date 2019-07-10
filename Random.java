/*
Kasun De Zoysa @ UCSC
*/
import java.security.*;
import java.io.*;
import java.util.Random;

public class Random{

public static void main(String args[]) {

  try {
   // Create two secure number generators with the same seed
   SecureRandom sr1 = SecureRandom.getInstance("SHA1PRNG");
   SecureRandom sr2 = SecureRandom.getInstance("SHA1PRNG");

   int seedByteCount = 5;
   byte[] seed1 = sr1.generateSeed(seedByteCount);
   byte[] seed2 = sr2.generateSeed(seedByteCount);
  
 // Get 256 random bits   
   byte[] bytes = new byte[256/8];
   
   System.out.println("Five Secure Random Numbers:");
   sr1.setSeed(seed1);
   for(int i=0;i<5;i++){
    sr1.nextBytes(bytes);
    for(byte b:bytes) System.out.format("%02x",b);
    System.out.println("");
   }

   sr2.setSeed(seed1);
   System.out.println("Five Secure Random Numbers:");
   for(int i=0;i<5;i++){
    sr2.nextBytes(bytes);
    for(byte b:bytes) System.out.format("%02x",b);
    System.out.println("");
   } 

   } catch (Exception e) {
   System.out.println(e);
  }
 }
}
