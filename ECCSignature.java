/*
Kasun De Zoysa @ UCSC
Compile:
javac -cp ../BC/bcprov-jdk16-146.jar ECCSignature.java
Execute:
java -cp ../BC/bcprov-jdk16-146.jar:. ECCSignature
*/

import java.security.Security;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Signature;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ECCSignature{

  public static void main(String[] args)throws Exception {


     Security.addProvider(new BouncyCastleProvider());
     KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA");
     keyGen.initialize(256,new SecureRandom());
     KeyPair keyPair = keyGen.generateKeyPair();

     Signature  signature = Signature.getInstance("SHA256withECDSA");

     // generate a signature
     signature.initSign(keyPair.getPrivate());
     String msg1="Hello Kasun How are you?";
     signature.update(msg1.getBytes());
     byte[]  sigBytes = signature.sign();

     System.out.println("Signature: ");
     for(byte b:sigBytes) System.out.format("%02x",b);
     System.out.println("");

     // verify a signature
     signature.initVerify(keyPair.getPublic());
     String msg2="Hello Kasun How are you?";

     signature.update(msg2.getBytes());
     if (signature.verify(sigBytes)){
            System.out.println("Signature verification succeeded.");
     }  else  {
            System.out.println("Signature verification failed.");
     }

  }

}
