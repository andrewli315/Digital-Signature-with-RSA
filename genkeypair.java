import java.security.KeyFactory;
import java.security.Signature;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.security.KeyPair;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.xml.bind.*;
public class genkeypair{
	
	public static void main(String[] args)throws Exception{
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512);
        KeyPair keys;
        keys = keyGen.genKeyPair();
        byte[] publicKey = keys.getPublic().getEncoded();
        byte[] b64pubkey = Base64.getEncoder().encode(publicKey);
        System.out.println("publicKey : \n"+ new String(b64pubkey));
        System.out.println("");

        PrivateKey privatekey = keys.getPrivate();
        
        byte[] b64encodepk = Base64.getEncoder().encode(privatekey.getEncoded());
        System.out.println( "PrivateKey : \n" + new String(b64encodepk) );

	}
}