package Metodes;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CrearArxiuIndex {
    public static void executar() {
        String nomFitxer = "encrypted.txt";
        File fitxer = new File(nomFitxer);

        // Comprovem si el fitxer existeix
        if (fitxer.exists()) {
            executarFuncio("crearArxiuIndex");
        } else {
            System.out.println("Error: El fitxer 'encrypted.txt' no existeix.");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Vols realitzar l'opció 3? (s/n): ");
            String resposta = scanner.nextLine().trim().toLowerCase();
            if (resposta.equals("s")) {
                SubstituirLletra.executar();
                System.out.println("Executant l'opció 3...");
            } else {
                System.out.println("Continuant amb l'opció 6...");
                executarFuncio("crearArxiuIndex");
            }
        }
    }

    private static void executarFuncio(String funcio) {
        try {
            ProcessBuilder builder = new ProcessBuilder("java", "-cp", "src\\Arxiujar\\psp_u1_fill_AGC.jar", "Main", funcio);
            Process process = builder.start();

            // Captura l'output del procés fill
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Captura els errors del procés fill
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
                System.out.println("ERROR: " + errorLine);
            }

            int exitCode = process.waitFor();
            System.out.println("El procés fill ha acabat amb codi de sortida: " + exitCode);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error executant el procés: " + e.getMessage());
        }
    }
}
