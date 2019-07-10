/*
Kasun De Zoysa @ UCSC
Compile:
javac -cp ../BC/bcprov-jdk16-146.jar RSAEncryption.java 
Execute:
java -cp ../BC/bcprov-jdk16-146.jar:. RSAEncryption
*/
import java.io.*;
import java.security.*;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;


import java.security.interfaces.RSAPrivateCrtKey;
import javax.crypto.Cipher;
import java.util.Formatter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class RSAEncryption {
  public static void main(String[]    args) throws Exception    {

 Security.addProvider(new BouncyCastleProvider());

  String        input = "Hello Kasun ..";
  Cipher cipher = Cipher.getInstance("RSA/NONE/PKCS1Padding");

// load the keystore
 KeyStore ks = KeyStore.getInstance("JKS", "SUN");
 ks.load(new FileInputStream("Key"),"passwd".toCharArray());
 X509Certificate cert = (X509Certificate) ks.getCertificate("mykey");
 
  System.out.println(cert);
  Key pubKey = cert.getPublicKey();

// encryption step
  cipher.init(Cipher.ENCRYPT_MODE,pubKey);
  byte[] cipherText = cipher.doFinal(input.getBytes());
  System.out.println("Cipher : " +byteArray2Hex(cipherText));

// load the private key entry from the keystore
 Key key = ks.getKey("mykey", "passwd".toCharArray());
 Key privKey = (RSAPrivateCrtKey) key;

// decryption step
  cipher.init(Cipher.DECRYPT_MODE,privKey);
  byte[] plainText = cipher.doFinal(cipherText);

  System.out.println("Plain : " +new String(plainText));
 }

 private static String byteArray2Hex(byte[] hash){
     Formatter formatter = new Formatter();
     for (byte b : hash) formatter.format("%02x", b);
     return formatter.toString();
  }

}
