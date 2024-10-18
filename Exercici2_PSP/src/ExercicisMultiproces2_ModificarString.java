import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExercicisMultiproces2_ModificarString
{
    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            String read = in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
