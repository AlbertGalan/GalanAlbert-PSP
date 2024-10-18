import java.util.Scanner;

public class ExercicisMultiproces1_ParellSenar {
    public boolean parell(int num) {
        return (num % 2 == 0);
    }

    public static void main(String[] args) {
        ExercicisMultiproces1_ParellSenar s = new ExercicisMultiproces1_ParellSenar();
        int num = Integer.parseInt(args[0]);
        if (s.parell(num)){
            System.out.println("Parell");
        }else {
            System.out.println("Senar");
        }
    }
}