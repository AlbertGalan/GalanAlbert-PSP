package Metodes;

// LlegirEncrypted.java (projecte pare)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LlegirEncrypted {
    public static void executar() {
        executarFuncio("llegirEncrypted");
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
