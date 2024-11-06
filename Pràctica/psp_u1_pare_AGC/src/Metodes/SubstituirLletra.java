package Metodes;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SubstituirLletra {
    public static void executar() {
        String nomFitxer = "contingut.html";    //Controlam que contingut.html existeix
        File fitxer = new File(nomFitxer);
        if (!fitxer.exists()) {
            System.out.println("Error: El fitxer 'contingut.html' no existeix.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdueix la lletra que vols substituir: ");
        String lletraOriginal = scanner.next(); // scanner mos llegeix  sa lletra que volem canviar

        System.out.print("Introdueix la nova lletra: ");
        String lletraNova = scanner.next(); // scanner mos llegeix  sa lletra nova

        if (lletraOriginal.length() == 1 && lletraNova.length() == 1) { // comprovam que només pugui ser un caràcter.
            executarFuncio("substituirLletra", lletraOriginal, lletraNova);
        } else {
            System.out.println("Error: Has d'introduir exactament dues lletres.");
        }
    }

    private static void executarFuncio(String funcio, String lletraOriginal, String lletraNova) {
        try {
            ProcessBuilder builder = new ProcessBuilder("java", "-cp", "src\\Arxiujar\\psp_u1_fill_AGC.jar", "Main", funcio, lletraOriginal, lletraNova);
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
