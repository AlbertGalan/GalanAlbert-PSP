
public class Productor extends Thread {
    private final Magatzem magatzem;

    public Productor(Magatzem magatzem) {
        this.magatzem = magatzem;
    }

    @Override
    public void run() {
        try {
            while (true) {
                magatzem.produir();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}