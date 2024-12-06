import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Locale;

public class Server {
    static boolean exit = false;

    public static void main(String[] args) {
        int portDesti = 2222;

        try {
            ServerSocket serverSocket = new ServerSocket(portDesti);

            while (!exit) {
                Socket server = serverSocket.accept();
                System.out.println("Connexió establerta");

                String linia = read(server);
                System.out.println("Linea modificada: " + linia);

                PrintWriter pw = new PrintWriter(server.getOutputStream());
                pw.write(linia + "\n");
                pw.flush();
                pw.close();
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String read(Socket server){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(server.getInputStream()))){
            return br.readLine();
        }
        catch (IOException e){
            System.out.println("Error Read:\n" + e);
            return "";
        }
    }

    public static void write(Socket server, String text){
        try(PrintWriter pw = new PrintWriter(server.getOutputStream())){
            pw.write(text + "\n");
            pw.flush();
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Error Write:\n" + e);
        }
    }
}