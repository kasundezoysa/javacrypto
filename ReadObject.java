/*
Kasun De Zoysa @ UCSC
*/
import java.io.*;
import java.security.*;
import javax.crypto.*;

public class ReadObject {

public static void main(String args[]) {

try {
  FileInputStream in = new FileInputStream("Objects.obj");
  ObjectInputStream oIn = new ObjectInputStream(in);

//Read the key
  Key sKey= (Key) oIn.readObject();

//Read the sealed object
  SealedObject so= (SealedObject) oIn.readObject();
//unsealded the object
  Car car = (Car) so.getObject(sKey);
  car.getNo();
  car.getYear();

  } catch (Exception e) {
   System.out.println(e);
  }
 }
}
