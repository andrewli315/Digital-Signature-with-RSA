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
public class verify{
	public static void main(String[] args)throws Exception{
		//original data
		String data = "hello world";
		//Signature with baes64 encoded
		String b64sig = "Oebbp754ozaX+/539cRYomsZSOVhZ8L7NcNbLIw+hUWn0HMLvYjmK/B78ixMMQD+vk1zB6RRo2kyYeqoWka+FQ==";
		//public key with base64 encoded
		String b64pubkey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAI5SXpw1SSsM3FN43JVKn4gb+oGXfjL7rCDluqydAyHZ8vV7ySqi8oM1CoHRC9U2ST7IldydsQ+4cjC9xfzexxcCAwEAAQ==";
		//transform the base64 string to byte[]
		byte[] pk = Base64.getDecoder().decode(b64pubkey);
		byte[] sig = Base64.getDecoder().decode(b64sig);
		System.out.println(new String(sig));
		//get PublicKey Object from byte[]
		X509EncodedKeySpec spec = new X509EncodedKeySpec( pk );
		KeyFactory kf = KeyFactory.getInstance( "RSA" );
		PublicKey pubKey = kf.generatePublic( spec );		
		//verify the Signature
		Signature signature = Signature.getInstance( "SHA256withRSA" );
		signature.initVerify( pubKey );
		signature.update( data.getBytes() );
		boolean ret = signature.verify( sig );
		System.out.println(ret);
		return;
	}
}