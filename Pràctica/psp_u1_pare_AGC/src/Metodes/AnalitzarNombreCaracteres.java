package Metodes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AnalitzarNombreCaracteres {
    public static void executar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdueix el caràcter que vols comptar: ");
        String caracter = scanner.next();

        if (caracter.length() == 1) {
            executarFuncio("analitzarNombreCaracteres", caracter);
        } else {
            System.out.println("Error: Has d'introduir un sol caràcter.");
        }
    }

    private static void executarFuncio(String funcio, String caracter) {
        try {
            ProcessBuilder builder = new ProcessBuilder("java", "-cp", "src\\Arxiujar\\psp_u1_fill_AGC.jar", "Main", funcio, caracter);
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