public class Main {
    public static void main(String[] args) {
        Magatzem magatzem = new Magatzem();

        Productor productor = new Productor(magatzem);
        Consumidor consumidor = new Consumidor(magatzem);

        productor.start();
        consumidor.start();
    }
}