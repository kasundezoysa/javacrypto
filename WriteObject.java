/*
Kasun De Zoysa @ UCSC
*/
import java.io.*;
import java.security.*;
import javax.crypto.*;

public class WriteObject {

public static void main(String args[]) {

try {
//Generate a key
  KeyGenerator    gen = KeyGenerator.getInstance("AES");
  gen.init(128);
  Key sKey = gen.generateKey();

  Cipher c = Cipher.getInstance("AES");
  c.init(Cipher.ENCRYPT_MODE,sKey);

// do the sealing
  Car car=new Car("GK-1234",2018);
  SealedObject so = new SealedObject(car,c);

  FileOutputStream out = new FileOutputStream("./Objects.obj");
  ObjectOutputStream oOut = new ObjectOutputStream(out);

//Save the key
  oOut.writeObject(sKey);

//Save the sealed object
  oOut.writeObject(so);
  oOut.close();
  System.out.println("SealedObject was written to Object file");

  } catch (Exception e) {
   System.out.println(e);
  }
 }
}
