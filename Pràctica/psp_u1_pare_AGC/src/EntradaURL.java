import java.util.Scanner;

public class EntradaURL {

    public static String getURL() {
        Scanner scanner = new Scanner(System.in);
        String url;

        while (true) {
            System.out.println("Introdueix una URL:");
            System.out.println("(Ha de començar per (http:// o https://)");
            System.out.println("(Ha d'acabar per (.com , .es , .cat)");
            url = scanner.nextLine();

            if (ValidarURL(url)) {
                System.out.println("URL introduïda correctament: " + url);
                break;
            } else {
                System.out.println("Error:  Comprova que el format sigui correcte.");
            }
        }
        return url;
    }

    private static boolean ValidarURL(String url) {
        return (url.startsWith("http://") || url.startsWith("https://")) &&
                (url.endsWith(".com") || url.endsWith(".cat") || url.endsWith(".es"));
    }
}
