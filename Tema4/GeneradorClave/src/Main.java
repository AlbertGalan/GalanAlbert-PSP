import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introdueix el nom de l'arxiu: ");

        String fileName = scanner.nextLine();


        System.out.println("Introdueix una contrasenya: ");

        String password = scanner.nextLine();

        File inputFile = new File("src/" + fileName);
        File outputFile;

        try {
            if (fileName.endsWith(".aes")) {
                outputFile = new File("src/" + fileName.substring(0, fileName.length() - 4));
                FileProtector.decrypt(password, inputFile, outputFile);
                FileProtector.wipeFile(inputFile);
                System.out.println("Archivo desencriptado correctamente.");
            } else {
                outputFile = new File("src/" + fileName + ".aes");
                FileProtector.encrypt(password, inputFile, outputFile);
                FileProtector.wipeFile(inputFile);
                System.out.println("Archivo encriptado correctamente.");
            }
        } catch (Exception e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }
    }
}