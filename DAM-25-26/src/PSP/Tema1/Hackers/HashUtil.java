package PSP.Tema1.Hackers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
    public static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) sb.append(String.format("%02x", b & 0xff));
        return sb.toString();
    }

    public static String hash(String input, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] dig = md.digest(input.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            return toHex(dig);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Uso:
    public static void main(String[] args) {
        System.out.println(hash("hola", "MD5"));        // md5
        System.out.println(hash("hola", "SHA-1"));      // sha1
        System.out.println(hash("hola", "SHA-256"));    // sha256 (recomendado sobre MD5)
    }
}
