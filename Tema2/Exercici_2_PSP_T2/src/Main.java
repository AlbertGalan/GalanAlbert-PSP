public class Main {
    public static void main(String[] args) {
    Compte compte = new Compte();
    FilOperacio filSuma = new FilOperacio(compte, true);
    FilOperacio filResta = new FilOperacio(compte, false);

    filSuma.start();
    filResta.start();

    try {
        filSuma.join();
        filResta.join();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
        System.out.println("Valor final:  " + compte.valor[0]);
    }
}
