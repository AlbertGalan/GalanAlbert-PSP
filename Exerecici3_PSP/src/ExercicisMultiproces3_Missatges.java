import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.out;

public class ExercicisMultiproces3_Missatges
{
    public static void main(String[] args) {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String rebut;
        try{
            while(rebut = in.readLine(
            out.println("Fill: rep missatge del pare" + rebut);

            String resposta = "F";

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
