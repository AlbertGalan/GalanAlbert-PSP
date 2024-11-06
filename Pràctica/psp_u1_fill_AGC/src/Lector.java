import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lector {
    public static void llegirEncrypted() {
        String nomFitxer = "encrypted.txt"; // Fitxer a llegir
//Simplement feim que llegesqui i imprimeixi per pantalla el fitxer indicat.
        try (BufferedReader br = new BufferedReader(new FileReader(nomFitxer))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                System.out.println(linia);
            }
        } catch (IOException e) {
            System.out.println("Error llegint el fitxer: " + e.getMessage());
        }
    }
}
