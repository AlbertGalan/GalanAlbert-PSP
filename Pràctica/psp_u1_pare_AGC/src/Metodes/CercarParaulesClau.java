package Metodes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CercarParaulesClau {
    public static void executar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdueix la paraula que vols cercar: ");
        String paraula = scanner.nextLine().trim();

        // Validació de la entrada
        if (!paraula.isEmpty() && paraula.split("\\s+").length == 1) {
            // Cridar al procés fill
            executarFuncio("cercarParaulesClau", paraula);
        } else {
            System.out.println("Error: Has d'introduir una única paraula.");
        }
    }

    private static void executarFuncio(String funcio, String paraula) {
        try {
            // Assegura't que la ruta i el nom del JAR són correctes
            ProcessBuilder builder = new ProcessBuilder("java", "-cp", "src\\Arxiujar\\psp_u1_fill_AGC.jar", "Main", funcio, paraula);
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
