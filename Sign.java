/*
Kasun De Zoysa @ UCSC
*/

import java.io.*;
import java.security.*;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.Signature;
import java.security.interfaces.RSAPrivateCrtKey;
import javax.crypto.Cipher;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Sign{

  public static void main(String[] args)throws Exception {

    // load the keystore
     KeyStore p12 = KeyStore.getInstance("pkcs12");
     p12.load(new FileInputStream("KEYSTORE.p12"), "passwd".toCharArray());

    // load the private key entry from the keystore
     Key key = p12.getKey("mykey", "passwd".toCharArray());
     PrivateKey privKey = (PrivateKey) key;

     // generate a signature
     Signature  signature = Signature.getInstance("SHA256withRSA");
     signature.initSign(privKey);
     String msg1="Hello Kasun How are you?";
     signature.update(msg1.getBytes());
     byte[]  sigBytes = signature.sign();

     System.out.println("Signature: ");
     for(byte b:sigBytes) System.out.format("%02x",b);
     System.out.println("");


     // verify a signature
     X509Certificate cert = (X509Certificate) p12.getCertificate("mykey");
     //System.out.println(cert);
     PublicKey pubKey = (PublicKey) cert.getPublicKey();
     signature.initVerify(pubKey);
     String msg2="Hello Kasun How are you?";
     signature.update(msg2.getBytes());

     if (signature.verify(sigBytes)){
            System.out.println("Signature verification succeeded.");

     }  else  {
            System.out.println("Signature verification failed.");
     }
  }
}
