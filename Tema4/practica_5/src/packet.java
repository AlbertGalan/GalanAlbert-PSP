import java.io.Serializable;

public class packet implements Serializable {
    byte[] message; // Missatge xifrat
    byte[] hash;    // Hash del missatge

    public packet(byte[] m, byte[] k) {
        this.message = m;
        this.hash = k;
    }
}