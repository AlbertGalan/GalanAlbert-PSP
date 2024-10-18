import java.util.Scanner;

public class ExercicisMultiproces2_ModificarString {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine(); // Ficam dins la variable input el que hem escrit per pantalla amb el scanner

                /*Variable output agafa variable input(el que hem escrit), li deim que ho pasi a majúscules(toUpperCase()),
                i que ens substituesqui(replaceAll), el primer valor [AEIOU], per el segon valor[_]]*/
                String output = input.toUpperCase().replaceAll("[AEIOU]", "_");

                //Llavors imprimim per pantalla el resultat
                System.out.println("Fill ha processat: " + output);
                System.out.flush();  // Assegura que la sortida s'envia immediatament
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}