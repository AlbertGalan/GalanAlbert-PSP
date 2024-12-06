package Ex2;

import java.io.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

public class ChatHandler extends Thread {
    private Socket socket;
    private ConcurrentHashMap<String, PrintWriter> clients;
    private String userNumber;

    public ChatHandler(Socket socket, ConcurrentHashMap<String, PrintWriter> clients) {
        this.socket = socket;
        this.clients = clients;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            // Register client
            out.println("Indica el teu Num:");
            userNumber = in.readLine();
            clients.put(userNumber, out);

            String input;
            while ((input = in.readLine()) != null) {
                JSONObject json = new JSONObject(input);
                String sender = json.getString("sender");
                String receiver = json.getString("receiver");
                String message = json.getString("message");

                if (message.equalsIgnoreCase("adeu")) {
                    out.println("Connection closed");
                    break;
                }

                String chatFileName = "chat_" + sender + "_" + receiver + ".txt";
                File chatFile = new File(chatFileName);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(chatFile, true))) {
                    writer.write(sender + ": " + message);
                    writer.newLine();
                }

                StringBuilder chatContent = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new FileReader(chatFile))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        chatContent.append(line).append("\n");
                    }
                }

                out.println("Contingut actual de la conversa:\n---------------------------------\n" + chatContent + "---------------------------------");

                PrintWriter receiverOut = clients.get(receiver);
                if (receiverOut != null) {
                    receiverOut.println("Contingut actual de la conversa:\n---------------------------------\n" + chatContent + "---------------------------------");
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            if (userNumber != null) {
                clients.remove(userNumber);
            }
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
        }
    }
}