package Ex1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 2222;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            String command;
            do {
                System.out.println("Insert, select, delete, or exit:");
                command = scanner.nextLine();
                out.println(command);

                if (command.equalsIgnoreCase("exit")) {
                    break;
                }

                switch (command.toLowerCase()) {
                    case "insert":
                        System.out.println("Enter ID:");
                        String id = scanner.nextLine();
                        out.println(id);
                        System.out.println("Enter name:");
                        String name = scanner.nextLine();
                        out.println(name);
                        System.out.println("Enter surname:");
                        String surname = scanner.nextLine();
                        out.println(surname);
                        break;

                    case "select":
                        System.out.println("Enter ID:");
                        id = scanner.nextLine();
                        out.println(id);
                        break;

                    case "delete":
                        System.out.println("Enter ID:");
                        id = scanner.nextLine();
                        out.println(id);
                        break;

                    default:
                        System.out.println("Invalid command.");
                        continue;
                }

                String response = in.readLine();
                System.out.println("Ex1.Server response: " + response);
            } while (!command.equalsIgnoreCase("exit"));

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
