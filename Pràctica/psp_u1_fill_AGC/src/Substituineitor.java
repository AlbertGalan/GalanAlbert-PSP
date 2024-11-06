import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Substituineitor {
    public static void substituirLletra(String lletraOriginal, String lletraNova) {
        String nomFitxer = "contingut.html";
        String nomFitxerSortida = "encrypted.txt";
        StringBuilder contingutNou = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(nomFitxer))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                contingutNou.append(linia.replace(lletraOriginal.charAt(0), lletraNova.charAt(0))).append("\n");
                //li deim que el primer argument que haviem introduït pel pare, el substituesqui pel segon argument.
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomFitxerSortida))) {
                bw.write(contingutNou.toString());
            }

            System.out.println("Arxiu creat amb èxit: " + nomFitxerSortida);
        } catch (IOException e) {
            System.out.println("Error llegint o escrivint el fitxer: " + e.getMessage());
        }
    }
}
