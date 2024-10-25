import java.io.*;
import java.util.Objects;

import static java.lang.System.out;

public class ExercicisMultiproces3_Missatges
{
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = in.readLine();
        out.write("Fill: missatge rebut pare" + input);
        out.write("Fill: envia missatge del pare");
        out.write("Salutacions de part del fill");
        out.flush();
        out.close();
    }
}
