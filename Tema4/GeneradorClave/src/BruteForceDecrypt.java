import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class BruteForceDecrypt {
    public static void main(String[] args) {
        byte[] enc = {
                (byte)0xEC, (byte)0xC4, (byte)0xD5, (byte)0x89,
                (byte)0x02, (byte)0xE3, (byte)0xD5, (byte)0xCC,
                (byte)0x5E, (byte)0xC6, (byte)0xAF, (byte)0x6C,
                (byte)0x61, (byte)0x8B, (byte)0xC2, (byte)0xA5
        };

        System.out.println("Searching for key...");
        long startTime = System.currentTimeMillis();

        try {
            for (int i = 0; i <= 9999; i++) {
                String password = String.format("%04d", i);

                // Generate key using SHA-256
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                byte[] key = new byte[24]; // 192 bits
                System.arraycopy(hash, 0, key, 0, 24);

                try {
                    // Create AES cipher
                    SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
                    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                    cipher.init(Cipher.DECRYPT_MODE, secretKey);

                    // Attempt decryption
                    byte[] decrypted = cipher.doFinal(enc);
                    String result = new String(decrypted, StandardCharsets.UTF_8);

                    // Check if result contains only printable ASCII characters
                    if (result.matches("^[\\x20-\\x7E]+$")) {
                        System.out.println("\nSuccess! Found valid decryption:");
                        System.out.println("Password: " + password);
                        System.out.println("Missatge encriptat: " + result);
                        System.out.printf("Search completed in: %.2f seconds%n",
                                (System.currentTimeMillis() - startTime) / 1000.0);
                        return;
                    }
                } catch (Exception e) {
                    // Invalid decryption, continue to next password
                }

                if (i % 1000 == 0) {
                    System.out.printf("\rTrying combinations... %d%%", i/100);
                }
            }

            System.out.println("\nNo valid decryption found.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.printf("Search completed in: %.2f seconds%n",
                (System.currentTimeMillis() - startTime) / 1000.0);
    }
}