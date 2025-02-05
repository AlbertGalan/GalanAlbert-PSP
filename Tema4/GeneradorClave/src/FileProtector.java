import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class FileProtector {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    public static void encrypt(String key, File inputFile, File outputFile) throws Exception {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }

    public static void decrypt(String key, File inputFile, File outputFile) throws Exception {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    }
// Mètode per encriptar i desencriptar
    private static void doCrypto(int cipherMode, String key, File inputFile, File outputFile) throws Exception {
        SecretKeySpec secretKey = generateKey(key);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(cipherMode, secretKey);

        try (FileInputStream inputStream = new FileInputStream(inputFile);
             FileOutputStream outputStream = new FileOutputStream(outputFile)) {

            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            outputStream.write(outputBytes);
        }
    }

    //Generació de clau a partir de la contrasenya
    private static SecretKeySpec generateKey(String password) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] key = password.getBytes("UTF-8");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 24); // Use only first 192 bits
        return new SecretKeySpec(key, ALGORITHM);
    }

//Mètode per sobreescriure l'arxiu en zeros
    public static void wipeFile(File file) throws Exception {
        RandomAccessFile raf = new RandomAccessFile(file, "rws");
        byte[] data = new byte[(int) file.length()];
        raf.write(data);
        raf.close();
        Files.delete(Paths.get(file.getAbsolutePath()));
    }
    public static SecretKey keygenKeyGeneration(int keySize) {
        SecretKey sKey = null;
        if (keySize == 128 || keySize == 192 || keySize == 256) {
            try {
                KeyGenerator kgen = KeyGenerator.getInstance("AES");
                kgen.init(keySize);
                sKey = kgen.generateKey();
            } catch (NoSuchAlgorithmException ex) {
                System.err.println("Generador no disponible.");
            }
        }
        return sKey;
    }
}