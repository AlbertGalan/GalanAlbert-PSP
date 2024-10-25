import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExercicisMultiproces3 {
    static final String dirPath = "C:\\Users\\Albert\\IdeaProjects\\AsignaturaPSP\\GalanAlbert-PSP\\Exerecici3_PSP\\src";
    static final String[] command = {
            "java",
            "ExercicisMultiproces3_Missatges.java"
    };


    private static Process execPrograma() throws IOException {
        ProcessBuilder programa = new ProcessBuilder(command);
        programa.directory(new File(dirPath));
        programa.redirectError(new File(dirPath + "error.txt"));
        programa.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        return programa.start();

    }

    private static void enviar(Process proces, String n) throws IOException {
        OutputStream outS = proces.getOutputStream();
        OutputStreamWriter outSW = new OutputStreamWriter(outS);
        BufferedWriter outBW = new BufferedWriter(outSW);

        outBW.write(n);
        outBW.newLine();
        outBW.flush();
        outBW.close();
    }

    private static List<String> llegir(Process proces) throws IOException {
        InputStream inS = proces.getInputStream();
        InputStreamReader inSR = new InputStreamReader(inS);
        BufferedReader inBR = new BufferedReader(inSR);

        ArrayList<String> toReturn = new ArrayList<String>();
        String linea = "";
        while ((linea = inBR.readLine()) != null) {
            toReturn.add(linea);
        }
        return toReturn;
    }

    public static void main(String[] args) throws IOException {
        try (Scanner scan = new Scanner(System.in)) {
            while (true) {
                System.out.println("Pare: Envia missatge: ");

                Process proces = execPrograma();
                enviar(proces, "Salutacions del pare");

                proces.wait();
                List<String> missatge = llegir(proces);
                System.out.println(missatge.get(0));
                System.out.println(missatge.get(1));
                System.out.println(missatge.get(2));
                System.out.println("Pare: rep missatge del fill:" + missatge.get(2) + "\"");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}