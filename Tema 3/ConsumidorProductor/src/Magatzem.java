public class Magatzem {
    private int productes = 0; // per defecte un productor no ha produït  res
    private final int capacitat_max = 10;

    public synchronized void produir() throws InterruptedException { // Funcio emprada al productor
        while (productes == capacitat_max) {
            wait(); //Li diu a nes fil que esperi
        }
        productes++;
        System.out.println("Productes: " + productes); // quantitat de productes actuals (produïts)
        notifyAll();
    }

    public synchronized void consumir() throws InterruptedException { // Funcio emprada pel consumidor
        while (productes == 0) {
            wait();
        }
        productes--;
        System.out.println("Consumit: " + productes); // quantitat de productes consumits(pel consumidor)
        notifyAll();
    }
}
