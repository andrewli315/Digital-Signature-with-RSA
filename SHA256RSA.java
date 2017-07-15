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

public class SHA256RSA {
    
    public static void main(String[] args) throws Exception {
        String input = "hello world";
        /*
        public key with base64 encoded:
        MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAI5SXpw1SSsM3FN43JVKn4gb+oGXfjL7rCDluqydAyHZ8vV7ySqi8oM1CoHRC9U2ST7IldydsQ+4cjC9xfzexxcCAwEAAQ==
        */
        String privatekey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAjlJenDVJKwzcU3jclUqfiBv6gZd+MvusIOW6rJ0DIdny9XvJKqLygzUKgdEL1TZJPsiV3J2xD7hyML3F/N7HFwIDAQABAkBpj3yu/fLcJHjSzMVHUgcLrDzcm+G1rOeZqusPlpG5K9PdSP2epVpcQOlL+rUpSq9ZIHq80kj9sEuWzjAaoXWJAiEAzS4GV9yuY7HXnXoncn0M3IucKiNoUyyFk6HwmPR1/5UCIQCxkqmnwx6dwXgbwfXMqUsUXEGA3Ja602h/L5xC0Oxw+wIgKMmRcT3pXOApoKB73wKE4ALQ1H+daYtcMnDqxz0xh9ECIHUPDP5F13IBry8FPwdK9u6WHqxXPposcW+esDEvzx5vAiAjhMh+ld+hVzQNfc64etsCBX9PZPUoBUSis7xq9kMnVA==";
        
        String sig = signSHA256RSA(input, privatekey);
        
        System.out.println("Signature="+ new String(sig));
    }

    // Create base64 encoded signature using SHA256/RSA.
    private static String signSHA256RSA(String input, String strPk) throws Exception {
               
        //decode the private key from base64
        byte[] b1 = Base64.getDecoder().decode(strPk);
        //get PrivateKey Object from byte[]
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b1);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey priv = kf.generatePrivate(spec);
        //generate signature
        //choose the algorithm
        Signature signature = Signature.getInstance("SHA256withRSA");
        //init the private key
        signature.initSign(priv);
        //update the data which is going to be signed
        signature.update(input.getBytes());
        //sign the data
        byte[] s = signature.sign();
        System.out.println(new String(s));
        //return the signature with base64 encodeed
        return Base64.getEncoder().encodeToString(s);
    }
}