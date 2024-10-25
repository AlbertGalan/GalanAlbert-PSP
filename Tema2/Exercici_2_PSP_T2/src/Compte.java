public class Compte {
    public int[] valor;

    public Compte(){
        valor = new int[1];
        valor[0] = 1000;
    }

    public synchronized void FilSumar() {
        int valornou = valor[0];
        System.out.println("Fil suma => compte: " + valor[0]);

        valornou += 10;
        System.out.println("Fil suma => registre: " + valornou);

        valor[0] = valornou;
        System.out.println("Fil suma => compte: " + valor[0]);
    }
    public synchronized void FilRestar() {
        int valornou = valor[0];
        System.out.println("Fil resta => compte: " + valor[0]);

        valornou -= 10;
        System.out.println("Fil resta => registre: " + valornou);

        valor[0] = valornou;
        System.out.println("Fil resta => compte: " + valor[0]);
    }
}
