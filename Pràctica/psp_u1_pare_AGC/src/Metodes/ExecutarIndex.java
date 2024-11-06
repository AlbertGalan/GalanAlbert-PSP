package Metodes;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ExecutarIndex {
    public static void executar() {
        String nomFitxer = "index.html";

        // Comprovam si index.html existeix
        File fitxer = new File(nomFitxer);
        if (!fitxer.exists()) { //si es fitxer no existeix li donam a triar a l'usuari si vol crear-ho
            System.out.println("Error: El fitxer 'index.html' no existeix.");
            System.out.print("Voleu crear l'arxiu 'index.html' primer? (s/n): ");
            Scanner scanner = new Scanner(System.in);
            String resposta = scanner.nextLine().trim().toUpperCase();
            if (resposta.equals("s")) {
                CrearArxiuIndex.executar();
            } else {
                System.out.println("No s'ha creat 'index.html'.");
                return; // Sortim del mètode si l'usuari no vol crear el fitxer i ja li botarà s'error a la clase fill
            }
        }

        // Cridam a nes procés fill
        executarFuncio("executarIndex");
    }

    private static void executarFuncio(String funcio) {
        try {
            ProcessBuilder builder = new ProcessBuilder("java", "-cp", "src\\Arxiujar\\psp_u1_fill_AGC.jar", "Main", funcio);
            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

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
