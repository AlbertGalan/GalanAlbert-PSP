public class Consumidor extends Thread {
    private final Magatzem magatzem;

    public Consumidor(Magatzem magatzem) {
        this.magatzem = magatzem;
    }

    @Override
    public void run() {
        try {
            while (true) {
                magatzem.consumir();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}