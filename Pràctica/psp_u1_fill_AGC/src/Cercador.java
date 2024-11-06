import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Cercador {
    public static void cercarParaula(String paraula) {
        String nomFitxer = "contingut.html";
        int comptador = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(nomFitxer))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                String[] paraulesLinia = linia.split("\\W+");
                for (String p : paraulesLinia) {
                    if (p.equalsIgnoreCase(paraula)) {
                        comptador++;
                    }
                }
            }

            if (comptador > 0) {
                System.out.println("La paraula '" + paraula + "' apareix " + comptador + " vegades.");
            } else {
                System.out.println("La paraula '" + paraula + "' NO existeix al contingut.");
            }
        } catch (IOException e) {
            System.out.println("Error llegint el fitxer: " + e.getMessage());
        }
    }
}
