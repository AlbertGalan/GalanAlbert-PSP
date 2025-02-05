import java.io.*;
import java.net.Socket;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import javax.crypto.SecretKey;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
            System.out.println("Connectat al servidor.");

            // Fluxos d'entrada i sortida
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // Rebre certificat del servidor
            Certificate cert = (Certificate) in.readObject();
            System.out.println("Certificat rebut del servidor.");

            // Verificar certificat
            try {
                cert.verify(cert.getPublicKey());
                System.out.println("Certificat verificat.");
            } catch (CertificateException e) {
                System.err.println("Error: Certificat no vàlid!");
                return;
            }

            // Obtenir clau pública del certificat
            PublicKey serverPublicKey = cert.getPublicKey();

            // Generar clau simètrica
            SecretKey sharedKey = AES_Simetric.keygenKeyGeneration(128);

            // Xifrar clau simètrica amb la clau pública del servidor
            byte[] encryptedKey = RSA_Asimetric.encryptData(sharedKey.getEncoded(), serverPublicKey);

            // Enviar clau simètrica xifrada al servidor
            out.writeObject(encryptedKey);
            out.flush();
            System.out.println("Clau simètrica enviada al servidor.");

            // Crear un escàner per llegir l'entrada de l'usuari
            Scanner scanner = new Scanner(System.in);

            // Enviar dades al servidor
            while (true) {
                System.out.print("Escriu un missatge: ");
                String message = scanner.nextLine();
                byte[] encryptedMessage = AES_Simetric.encryptData(sharedKey, message.getBytes());
                byte[] messageHash = Hash.hash(message.getBytes());
                out.writeObject(new packet(encryptedMessage, messageHash));
                out.flush();

                // Rebre resposta del servidor
                packet responsePacket = (packet) in.readObject();
                byte[] decryptedResponse = AES_Simetric.decryptData(sharedKey, responsePacket.message);

                // Verificar integritat de la resposta
                byte[] calculatedResponseHash = Hash.hash(decryptedResponse);
                if (java.util.Arrays.equals(responsePacket.hash, calculatedResponseHash)) {
                    System.out.println("Resposta del servidor (integritat verificada): " + new String(decryptedResponse));
                } else {
                    System.err.println("Error: hash de la resposta no coincideix!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}