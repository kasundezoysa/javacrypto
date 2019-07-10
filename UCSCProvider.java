/* UCSC Provider
 Kasun De Zoysa @ UCSC
*/

import java.security.*;
public class UCSCProvider extends Provider {

    public UCSCProvider() {

	super("UCSC", 1.0, "UCSC Security Provider v1.0");
	put("Signature.SHA-1/UCSC", "UCSCSignature");
	put("KeyPairGenerator.XYZ", "UCSCKeyPairGenerator");
	put("MessageDigest.SHA-1", "UCSCMessageDigestSHA");
	put("Alg.Alias.MessageDigest.SHA", "SHA-1");
	put("Alg.Alias.MessageDigest.XYZ", "XOR");
	put("MessageDigest.UCSC-SHA","XORDigest");
	put("MessageDigest.SHA","XORDigest");  
    }



    public static void main(String args[]) {

	try {

	    Security.addProvider(new UCSCProvider());
	    System.out.println("done");
	    Provider provider = Security.getProvider("UCSC");
	    System.out.println(provider.getInfo());
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}

