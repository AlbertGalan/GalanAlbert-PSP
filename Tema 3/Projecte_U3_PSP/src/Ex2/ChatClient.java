package Ex2;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import org.json.JSONObject;

public class ChatClient {
    private static final String HOST = "localhost";
    private static final int PORT = 3333;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println(in.readLine());
            String userNumber = scanner.nextLine();
            out.println(userNumber);

            System.out.println("Indica el Num del receptor:");
            String receiverNumber = scanner.nextLine();

            String message;
            do {
                System.out.println("Escriu el missatge a enviar:");
                message = scanner.nextLine();

                JSONObject json = new JSONObject();
                json.put("sender", userNumber);
                json.put("receiver", receiverNumber);
                json.put("message", message);
                out.println(json.toString());

                String response = in.readLine();
                System.out.println(response);
            } while (!message.equalsIgnoreCase("adeu"));

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}