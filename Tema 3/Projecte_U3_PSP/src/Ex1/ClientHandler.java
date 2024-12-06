package Ex1;

import java.io.*;
import java.net.*;
import java.util.NoSuchElementException;

class ClientHandler extends Thread {
    private Socket socket;
    private Database database;

    public ClientHandler(Socket socket, Database database) {
        this.socket = socket;
        this.database = database;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String command;
            while ((command = in.readLine()) != null) {
                switch (command.toLowerCase()) {
                    case "insert":
                        int id = Integer.parseInt(in.readLine());
                        String name = in.readLine();
                        String surname = in.readLine();
                        try {
                            database.insert(new Person(id, name, surname));
                            out.println("Insert successful");
                        } catch (IllegalArgumentException e) {
                            out.println("Error: " + e.getMessage());
                        }
                        break;

                    case "select":
                        id = Integer.parseInt(in.readLine());
                        try {
                            Person person = database.select(id);
                            out.println("Found: " + person);
                        } catch (NoSuchElementException e) {
                            out.println("Error: " + e.getMessage());
                        }
                        break;

                    case "delete":
                        id = Integer.parseInt(in.readLine());
                        try {
                            database.delete(id);
                            out.println("Delete successful");
                        } catch (NoSuchElementException e) {
                            out.println("Error: " + e.getMessage());
                        }
                        break;

                    case "exit":
                        out.println("Connection closed");
                        socket.close();
                        return;

                    default:
                        out.println("Invalid command.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        }
    }
}