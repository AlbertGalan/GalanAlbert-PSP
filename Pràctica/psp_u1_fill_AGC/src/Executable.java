import java.io.IOException;

public class Executable {

    public static void executarIndex() {
        String nomFitxer = "index.html";

        try {
            // Obtenim la direcció completa de on es index.html perque ho posi com a url al navegador
            String url = "file://" + new java.io.File(nomFitxer).getAbsolutePath();

            // Segons el sistema operatiu les comandes per executar-ho son unes o unes altres
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) { // no he comprovat si funciona amb els altres SO, pero al manco amb Windows funciona
                Runtime.getRuntime().exec(new String[]{"rundll32", "url.dll,FileProtocolHandler", url});
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec(new String[]{"/usr/bin/open", url});
            } else if (os.contains("nix") || os.contains("nux")) {
                Runtime.getRuntime().exec(new String[]{"xdg-open", url});
            } else {
                System.out.println("Sistema operatiu no suportat.");
            }

        } catch (IOException e) {
            System.out.println("Error obrint el navegador: " + e.getMessage());
        }
    }
}
