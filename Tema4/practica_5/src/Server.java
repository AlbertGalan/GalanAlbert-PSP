import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import javax.crypto.SecretKey;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor esperant connexions...");

            // Carregar el KeyStore creat amb keytool
            KeyStore keyStore = KeyStore.getInstance("JKS");
            try (FileInputStream keyStoreFile = new FileInputStream("serverkeystore.jks")) {
                keyStore.load(keyStoreFile, "uep123".toCharArray());
            }

            // Obtenir la clau privada i el certificat amb la contrasenya que hem posat
            PrivateKey privateKey = (PrivateKey) keyStore.getKey("clauservidor", "uep123".toCharArray());
            Certificate cert = keyStore.getCertificate("clauservidor");

            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Client connectat.");

                    // Fluxos d'entrada i sortida
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                    // Enviar certificat al client
                    out.writeObject(cert);
                    out.flush();
                    System.out.println("Certificat enviat al client.");

                    // Rebre la clau simètrica encriptada del client
                    byte[] encryptedKey = (byte[]) in.readObject();
                    byte[] decryptedKey = RSA_Asimetric.decryptData(encryptedKey, privateKey);

                    // Convertir la clau simètrica a SecretKey
                    SecretKey sharedKey = new javax.crypto.spec.SecretKeySpec(decryptedKey, "AES");
                    System.out.println("Clau simètrica rebuda i desxifrada.");

                    // Rebre dades del client en un bucle
                    while (true) {
                        try {
                            packet receivedPacket = (packet) in.readObject();
                            byte[] decryptedMessage = AES_Simetric.decryptData(sharedKey, receivedPacket.message);

                            // Verificar integritat
                            byte[] calculatedHash = Hash.hash(decryptedMessage);
                            if (java.util.Arrays.equals(receivedPacket.hash, calculatedHash)) {
                                System.out.println("Missatge rebut (integritat verificada): " + new String(decryptedMessage));
                            } else {
                                System.err.println("Error: hash no coincideix!");
                            }

                            // Enviar acús de rebut al client
                            String response = "DataReceived";
                            byte[] encryptedResponse = AES_Simetric.encryptData(sharedKey, response.getBytes());
                            byte[] responseHash = Hash.hash(response.getBytes());
                            out.writeObject(new packet(encryptedResponse, responseHash));
                            out.flush();
                        } catch (EOFException e) {
                            System.out.println("Client desconnectat.");
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}