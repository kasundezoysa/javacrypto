/*
Kasun De Zoysa @ UCSC
*/
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Signature;


public class SignatureExample {

  public static void main(String[] args)throws Exception {


     KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
     keyGen.initialize(4048,new SecureRandom());
     KeyPair keyPair = keyGen.generateKeyPair();

     Signature  signature = Signature.getInstance("SHA1withRSA");
     // generate a signature
     signature.initSign(keyPair.getPrivate());
     String msg1="Hello Kasun How are you?";
     signature.update(msg1.getBytes());
     byte[]  sigBytes = signature.sign();

        
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

