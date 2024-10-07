import java.util.Scanner;

public class ExercicisMultiproces1_ParellSenar
{
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Introdueix un nombre: ");
            int num = scanner.nextInt();
            if (num % 2 == 0)
            {
                System.out.println("El nombre" + " " + num + " " + "es parell");
            } else {
                System.out.println("El nombre" + " " + num + " " + "es senar");
            }
        } catch (java.util.InputMismatchException e)
        {
            System.out.println("Introdueix un nombre vàlid.");
        }

        scanner.close();
    }
}