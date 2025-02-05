import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.util.Base64;

public class Server {
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private SecretKey sharedKey;

    public Server() throws Exception {
        // Generar parella de claus RSA
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void startServer() throws Exception {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Servidor en espera de connexions...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connectat.");
            handleClient(clientSocket);
        }
    }

    private void handleClient(Socket clientSocket) throws Exception {
        DataInputStream input = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

        // Enviar la clau pública al client
        output.writeUTF(Base64.getEncoder().encodeToString(publicKey.getEncoded()));

        // Rebre la clau simètrica xifrada
        String encryptedKey = input.readUTF();
        byte[] decryptedKey = decryptRSA(Base64.getDecoder().decode(encryptedKey), privateKey);

        // Validar la integritat de la clau simètrica amb hash
        String receivedHash = input.readUTF();
        String calculatedHash = hash(decryptedKey);

        if (!receivedHash.equals(calculatedHash)) {
            System.out.println("Error d'integritat de la clau.");
            clientSocket.close();
            return;
        }

        this.sharedKey = new SecretKeySpec(decryptedKey, 0, decryptedKey.length, "AES");
        System.out.println("Clau compartida establerta correctament.");

        // Rebre missatges del client
        while (true) {
            String encryptedMessage = input.readUTF();
            byte[] decryptedMessage = decryptAES(Base64.getDecoder().decode(encryptedMessage), sharedKey);

            String messageHash = input.readUTF();
            if (!hash(decryptedMessage).equals(messageHash)) {
                System.out.println("Error d'integritat en el missatge.");
                continue;
            }

            System.out.println("Missatge rebut: " + new String(decryptedMessage));
            output.writeUTF("Missatge rebut correctament.");
        }
    }

    private byte[] decryptRSA(byte[] data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    private byte[] decryptAES(byte[] data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    private String hash(byte[] data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data);
        return Base64.getEncoder().encodeToString(hash);
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
