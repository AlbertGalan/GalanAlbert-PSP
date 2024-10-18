import com.sun.source.tree.TryTree;

import java.util.Scanner;

public class ExercicisMultiproces1
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String inputStr = "";
        int num = 0;
        while (inputStr != "exit") {
            System.out.println("Escriu un nombre enter");
            inputStr = scanner.nextLine();

            try {
                num = Integer.valueOf(inputStr);
                
            } catch (NumberFormatException e) {
                if (inputStr == "exit")
                {
                    break;
                }
                System.out.println("Invalid integer input");
            }

        }
    }
}
