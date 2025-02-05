import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.net.Socket;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Client {
    private PublicKey serverPublicKey;
    private SecretKey sharedKey;

    public void connectToServer() throws Exception {
        Socket socket = new Socket("localhost", 12345);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        // Obtenir la clau pública del servidor
        String publicKeyString = input.readUTF();
        this.serverPublicKey = getPublicKeyFromString(publicKeyString);

        // Generar una clau simètrica i enviar-la xifrada
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        this.sharedKey = keyGen.generateKey();

        byte[] encryptedKey = encryptRSA(sharedKey.getEncoded(), serverPublicKey);
        output.writeUTF(Base64.getEncoder().encodeToString(encryptedKey));

        // Enviar hash de la clau simètrica
        String keyHash = hash(sharedKey.getEncoded());
        output.writeUTF(keyHash);

        // Enviar missatges al servidor
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Entra un missatge: ");
            String message = reader.readLine();

            byte[] encryptedMessage = encryptAES(message.getBytes(), sharedKey);
            output.writeUTF(Base64.getEncoder().encodeToString(encryptedMessage));

            String messageHash = hash(message.getBytes());
            output.writeUTF(messageHash);

            String serverResponse = input.readUTF();
            System.out.println("Resposta del servidor: " + serverResponse);
        }
    }

    private byte[] encryptRSA(byte[] data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    private byte[] encryptAES(byte[] data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    private String hash(byte[] data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data);
        return Base64.getEncoder().encodeToString(hash);
    }

    private PublicKey getPublicKeyFromString(String key) throws Exception {
        byte[] byteKey = Base64.getDecoder().decode(key);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(new X509EncodedKeySpec(byteKey));
    }

    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.connectToServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
