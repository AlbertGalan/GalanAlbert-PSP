package Ex1;

import java.io.*;
import java.net.*;

public class Server {
    private static final int PORT = 2222;
    private Database database = new Database();

    public static void main(String[] args) {
        new Server().start();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Ex1.Server started. Waiting for clients...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Ex1.Client connected.");
                new ClientHandler(clientSocket, database).start();
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}