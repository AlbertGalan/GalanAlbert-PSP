import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Carregador {

    public static void carregarPagina(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connexio = (HttpURLConnection) url.openConnection();
            connexio.setRequestMethod("GET");

            // Comprovem la resposta de la connexió
            int status = connexio.getResponseCode();
            if (status == 200) { // 200 indica que la connexió és correcta
                InputStream inputStream = connexio.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String linia;
                StringBuilder contingut = new StringBuilder();

                while ((linia = reader.readLine()) != null) { //ho llegeix tot
                    contingut.append(linia).append("\n");
                }

                reader.close();

                // Imprimim  tot el llegit  per pantalla
                System.out.println("Contingut descarregat de la pàgina: " + urlString);
                System.out.println(contingut.toString());

                // Ho guardam el contingut al fitxer "contingut.html" per fer-ho servir més endavant
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("contingut.html"))) {
                    writer.write(contingut.toString());
                    System.out.println("El contingut s'ha guardat a contingut.html");
                } catch (IOException e) {
                    System.out.println("Error guardant el contingut al fitxer: " + e.getMessage());
                }
            } else {
                System.out.println("Error: No s'ha pogut accedir a la pàgina. Codi de resposta: " + status);
            }
        } catch (IOException e) {
            System.out.println("Error carregant la pàgina: " + e.getMessage());
        }
    }
}

