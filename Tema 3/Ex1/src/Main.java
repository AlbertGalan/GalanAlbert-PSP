import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        String desti = "localhost";
        int portDesti = 2222;

        Socket socket = new Socket();
        InetSocketAddress direccio = new InetSocketAddress(desti,  portDesti);

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String texte = reader.readLine();

        try {
            socket.connect(direccio);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.write(texte + "\n");
            writer.flush();

            BufferedReader readerServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String output =  readerServer.readLine();

            System.out.println("Hem rebut del servidor: " + output);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
