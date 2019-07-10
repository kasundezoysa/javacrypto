/*
Kasun De Zoysa @ UCSC
Compile:
javac -cp ../BC/bcprov-jdk16-146.jar Decryption.java
Execute:
java -cp ../BC/bcprov-jdk16-146.jar:. Decryption

keytool -importkeystore -srckeystore Key -destkeystore KEYSTORE.p12 -srcstoretype JKS -deststoretype PKCS12 -srcstorepass passwd
-deststorepass passwd -srcalias mykey -destalias mykey -srckeypass passwd -destkeypass passwd -noprompt
*/
import java.io.*;
import java.security.*;
import java.security.KeyStore;
//import java.security.cert.CertificateFactory;
//import java.security.cert.X509Certificate;

import java.security.interfaces.RSAPrivateCrtKey;
import javax.crypto.Cipher;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.xml.bind.DatatypeConverter;

public class Decryption {
  public static void main(String[]    args) throws Exception    {

 Security.addProvider(new BouncyCastleProvider());

  Cipher cipher = Cipher.getInstance("RSA/NONE/PKCS1Padding");

  // load the keystore
  KeyStore p12 = KeyStore.getInstance("pkcs12");
  p12.load(new FileInputStream("./KEYSTORE.p12"), "passwd".toCharArray());
  //X509Certificate cert = (X509Certificate) p12.getCertificate("mykey");
  //System.out.println(cert);

  // load the private key entry from the keystore
  Key key = p12.getKey("mykey", "passwd".toCharArray());
  Key privKey = (RSAPrivateCrtKey) key;

// decryption step
  cipher.init(Cipher.DECRYPT_MODE,privKey);
  byte[] cipherText= DatatypeConverter.parseBase64Binary(args[0]);
  byte[] plainText = cipher.doFinal(cipherText);
  System.out.println("Plain : " +new String(plainText));

}
}
