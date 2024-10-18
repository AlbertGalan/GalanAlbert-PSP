import java.io.*;
import java.util.Scanner;

public class Lanzador {
    static final String dirPath = "C:\\Users\\Albert\\IdeaProjects\\AsignaturaPSP\\GalanAlbert-PSP\\Streams\\src";
    static final String[] command = {
            "java",
            "Sqrt.java"
    };


    private static Process execPrograma() throws IOException { //Empram el throw que el main ho tendrà previst,
        ProcessBuilder programa = new ProcessBuilder(command);
        programa.directory(new File(dirPath));
        programa.redirectError(new File(dirPath+"error.txt"));
        return programa.start();

        }

        private static void enviar(Process proces, String n) throws IOException{ //El fill te standard input per llegir per pantalla.
            OutputStream outS = proces.getOutputStream(); // Es el fluxe de dades que està conectat a l'input del procés al qual cridam.
            OutputStreamWriter outSW = new OutputStreamWriter(outS); //De normal escriu en Bytes i això es per que agafi altres dades com per exemple un String
            BufferedWriter outBW = new BufferedWriter(outSW);//Memòria que guarda dades de manera temporal(exemple tecles) (exemple video YouTube linea grisa)

            outBW.write(n);
            outBW.newLine();
            outBW.flush(); //guarda
            outBW.close();//close es per alliberar recursos
        }

        private static String llegir(Process proces) throws IOException {
            InputStream inS = proces.getInputStream(); //Si deim input es que rep cap a dins, si poses Output es que surt cap a defora
            InputStreamReader inSR = new InputStreamReader(inS);
            BufferedReader inBR = new BufferedReader(inSR);

            return inBR.readLine();
        }

    public static void main(String[] args) throws IOException {
        try (Scanner scan = new Scanner(System.in)) { //Prova això, si no funciona es faria un catch, i es pot fer alguna cosa amb aquell error i que el programa segueixi funcionant
            while(true) {
                System.out.println("Pare: Escriu un int: ");
                String n = scan.nextLine();

                if ("exit".equals(n)) return;

                Process proces = execPrograma(); // Cream un procés que executi el programa

                enviar(proces, n);
                String retorn = llegir(proces);
                System.out.println(retorn);
                System.out.println("Fi");
            }
        } catch (Exception e) {
            e.printStackTrace(); //així agafa tots els errors, en comptes de a cada classe fer un throw
        }
    }
}