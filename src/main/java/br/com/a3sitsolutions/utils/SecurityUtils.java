package br.com.a3sitsolutions.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import br.com.a3sitsolutions.models.User;
import io.smallrye.jwt.build.Jwt;
import javax.crypto.*;

public class SecurityUtils {

    public static String cript(String text) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(text.getBytes());
        return new String(encryptedData);
    }

    public static String decrypt(String text) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedData = cipher.doFinal(text.getBytes());
        return new String(decryptedData);
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

    public static String generateToken(User user) {
        return Jwt.issuer("jwt-token")
                .subject("phonebook")
                .groups("user")
                .claim("email", user.getEmail())
                .claim("id", user.getId())
                .claim("name", user.getName())
                .expiresIn(Duration.ofMinutes(10))
                .sign();
    }
}
