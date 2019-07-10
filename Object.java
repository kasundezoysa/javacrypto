import java.io.*;
import java.security.*;
import javax.crypto.*;

public class Object {

public static void main(String args[]) {

try {
//Generate a key
  KeyGenerator    gen = KeyGenerator.getInstance("AES");
  gen.init(256);
  Key sKey = gen.generateKey();

  Cipher c = Cipher.getInstance("AES");
  c.init(Cipher.ENCRYPT_MODE, sKey);

// do the sealing
  SealedObject so = new SealedObject("Secret",c);
		
// Save Object
  FileOutputStream out = new FileOutputStream("Objects");
  ObjectOutputStream oOut = new ObjectOutputStream(out);
  oOut.writeObject(so);
  oOut.writeObject(sKey);
  oOut.close();

  FileInputStream in = new FileInputStream("Objects");
  ObjectInputStream oIn = new ObjectInputStream(in);
  SealedObject o= (SealedObject) oIn.readObject();

//  c.init(Cipher.DECRYPT_MODE, sKey);
  String s = (String)o.getObject(sKey);
  
  System.out.println(s);

  } catch (Exception e) {
   System.out.println(e);
  }
 }
}
