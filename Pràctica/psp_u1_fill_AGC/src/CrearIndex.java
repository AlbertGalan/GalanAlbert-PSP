import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CrearIndex {
    public static void crearArxiuIndex() {
        String nomFitxerEntrada = "encrypted.txt";
        String nomFitxerSortida = "index.html";

        StringBuilder contingutBody = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(nomFitxerEntrada))) {
            String linia;
            boolean dinsBody = false; //amb un boolea feim que només llegesqui les linees que son dins les etiquetes <body>

            while ((linia = br.readLine()) != null) {
                if (linia.contains("<body>")) {
                    dinsBody = true;
                }

                if (dinsBody) {
                    contingutBody.append(linia).append("\n");
                }
                if (linia.contains("</body>")) { //una vegada han acabat les etiquetes el boolea torna fals i ja no llegeix la resta
                    dinsBody = false;
                }
            }
        } catch (IOException e) {
            System.out.println("Error llegint el fitxer: " + e.getMessage());
            return;
        }
//Ho guardam tot dins un fitxer mentres que ens escrigui abans una etiqueta html abans i despres del body
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomFitxerSortida))) {
            // Afegim les etiquetes html al principi i al final
            bw.write("<html>\n");
            bw.write(contingutBody.toString());
            bw.write("</html>\n");
            System.out.println("Arxiu 'index.html' creat amb èxit.");
        } catch (IOException e) {
            System.out.println("Error escrivint el fitxer: " + e.getMessage());
        }
    }
}
