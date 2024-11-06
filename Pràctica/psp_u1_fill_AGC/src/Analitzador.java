import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class  Analitzador {

    public static void analitzarNombreCaracteres(char caracter) {
        String nomFitxer = "contingut.html";
        int comptador = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(nomFitxer))) {
            int c;
            while ((c = br.read()) != -1) {
                if (c == caracter) {
                    comptador++;
                }
            }
            System.out.println("El caràcter '" + caracter + "' apareix " + comptador + " vegades.");
        } catch (IOException e) {
            System.out.println("Error llegint el fitxer: " + e.getMessage());
        }
    }
}
