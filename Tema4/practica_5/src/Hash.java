import java.security.MessageDigest;

public class Hash {
    public static byte[] hash(byte[] data) {
        byte[] digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            digest = md.digest(data);
        } catch (Exception ex) {
            System.err.println("Error generant el hash: " + ex);
        }
        return digest;
    }
}
