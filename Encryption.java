/*
Kasun De Zoysa @ UCSC
Compile:
javac -cp ../BC/bcprov-jdk16-146.jar Encryption.java
Execute:
java -cp ../BC/bcprov-jdk16-146.jar:. Encryption
*/
import java.io.*;
import java.util.*;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.xml.bind.DatatypeConverter;

public class Encryption {
  public static void main(String[]    args) throws Exception    {

 Security.addProvider(new BouncyCastleProvider());

 Cipher cipher = Cipher.getInstance("RSA/NONE/PKCS1Padding");

//Read Certificate
 FileInputStream fis = new FileInputStream("./kasun.der");
 BufferedInputStream bis = new BufferedInputStream(fis);

 CertificateFactory cf = CertificateFactory.getInstance("X.509");
 X509Certificate cert = (X509Certificate)cf.generateCertificate(bis);
 System.out.println(cert);
 Key pubKey = cert.getPublicKey();

//Read the message
  Scanner x = new Scanner(System.in);
  System.out.print("Enter Your Message:");
  String message=x.nextLine();

// encryption step
  cipher.init(Cipher.ENCRYPT_MODE,pubKey);
  byte[] cipherText = cipher.doFinal(message.getBytes());

  String encoded = DatatypeConverter.printBase64Binary(cipherText);
  System.out.println("Base64 ecncoded cipher Text  value is: " + encoded);
}
}
