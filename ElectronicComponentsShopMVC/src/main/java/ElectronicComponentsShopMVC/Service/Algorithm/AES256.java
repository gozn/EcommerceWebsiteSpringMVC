package ElectronicComponentsShopMVC.Service.Algorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
 
public class AES256 {
    final static String password = "123";
    final static String salt = "9f9506b0572f21ec";
    public static String encrypt(String textToEncrypt){
        TextEncryptor encryptor = Encryptors.text(password, salt);      
        System.out.println("Salt: \"" + salt + "\"");
        System.out.println("Original text: \"" + textToEncrypt + "\"");

        String encryptedText = encryptor.encrypt(textToEncrypt);
        System.out.println("Encrypted text: \"" + encryptedText + "\"");
        
        return encryptedText;
    }
    
    public static String decrypt(String encryptedText) {
        TextEncryptor decryptor = Encryptors.text(password, salt);
        String decryptedText = decryptor.decrypt(encryptedText);
        //System.out.println("Decrypted text: \"" + decryptedText + "\""); 
        return decryptedText;
    }
    
    public static String encrypt2(String textToEncrypt, String pass){
        TextEncryptor encryptor = Encryptors.text(pass, salt);      
        //System.out.println("Salt: \"" + salt + "\"");
        //System.out.println("Original text: \"" + textToEncrypt + "\"");

        String encryptedText = encryptor.encrypt(textToEncrypt);
        //System.out.println("Encrypted text: \"" + encryptedText + "\"");
        
        return encryptedText;
    }
    
    public static String decrypt2(String encryptedText, String pass) {
        TextEncryptor decryptor = Encryptors.text(pass, salt);
        String decryptedText = decryptor.decrypt(encryptedText);
        //System.out.println(decryptedText); 
        return decryptedText;
    }
    
    public static String encryptSHA1(String stringToHash) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
        messageDigest.update(stringToHash.getBytes());
        byte[] digiest = messageDigest.digest();
        String hashed = DatatypeConverter.printHexBinary(digiest);
        return hashed;
    };
    
    public static void main(String[] args) {
        
        try {
            String d = decrypt2("7e7ea794e102c0c220bd9efdaf744bb4916857070de5eb10d1ba32d02bd7b74a", "123");
            System.out.println("Decrypt: " + d);
        } catch (Exception e) {
            System.err.println("Mật khẩu giải mã không đúng!");
        }
        
        
    }

}
