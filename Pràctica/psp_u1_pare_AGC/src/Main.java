import Metodes.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String url = EntradaURL.getURL();

        boolean seguir = true;
        boolean cas1fet = false;
        Scanner scanner = new Scanner(System.in);

        while (seguir) {
            int opcio = mostrarMenu();

            switch (opcio) {
                case 1:
                   CarregarPagina.executar(url);
                   cas1fet = true;
                    break;
                case 2:
                    if (cas1fet == true) {
                        AnalitzarNombreCaracteres.executar();
                    } else {
                        System.out.println("Tira cap endarrera que no has carregat la pàgina.");
                    }
                    break;
                case 3:
                    if(cas1fet == true) {
                        SubstituirLletra.executar();
                    } else {
                        System.out.println("Tira cap endarrera que no has carregat la pàgina.");
                    }
                    break;
                case 4:
                        LlegirEncrypted.executar();
                    break;
                case 5:
                    if(cas1fet == true) {
                        CercarParaulesClau.executar();
                    } else {
                        System.out.println("Tira cap endarrera que no has carregat la pàgina");
                    }
                    break;
                case 6:
                        CrearArxiuIndex.executar();
                    break;
                case 7:
                    ExecutarIndex.executar();
                    break;
                case 8:
                    seguir = false;
                    break;
                default:
                    System.out.println("Opció no vàlida. Introdueix un número vàlid.");
            }
        }

        scanner.close();
    }

    private static int mostrarMenu() {
        System.out.println("-Menú:");
        System.out.println("·1 - Carregar pàgina Web");
        System.out.println("·2 - Analitzar el nombre de caràcters");
        System.out.println("·3 - Substituir lletra");
        System.out.println("·4 - Llegir encrypted.txt");
        System.out.println("·5 - Cercar paraules clau");
        System.out.println("·6 - Crear arxiu index.html");
        System.out.println("·7 - Executar arxiu index.html");
        System.out.println("·8 - Sortir");

        Scanner scanner = new Scanner(System.in);
        int opcio = -1;

        while (true) {
            try {
                opcio = scanner.nextInt();
                if (opcio >= 1 && opcio <= 8) {
                    break; //opció només pot ser més gran o igual que 1 o més petita o igual a 8
                } else {
                    System.out.println("Opció no vàlida. Introdueix un número entre 1 i 8.");//Error per numero que no esta entre 1 i 8
                }
            } catch (Exception e) {
                System.out.println("Error: Has d'introduir un número vàlid.");
                scanner.next(); // Si escrius algo que no sigui un numero amolla s'error.
            }
        }

        return opcio;
    }
}

