
/*
Kasun De Zoysa @ UCSC
Compile:
javac -cp ../BC/bcprov-jdk16-146.jar PrecedenceTest.java
Execute:
java -cp ../BC/bcprov-jdk16-146.jar:. PrecedenceTest
*/

import javax.crypto.Cipher;
import java.security.Provider;
import java.security.Security;
import javax.crypto.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class PrecedenceTest {

   public static void main(String[]    args)  throws Exception  {

        Security.addProvider(new BouncyCastleProvider());

        Cipher  cipher = Cipher.getInstance("AES/ECB/NoPadding");
        System.out.println(cipher.getProvider());
        Security.removeProvider("sun");

        cipher = Cipher.getInstance("AES/ECB/NoPadding","BC");
        System.out.println(cipher.getProvider());

    }
}
