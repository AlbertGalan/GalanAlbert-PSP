public class FilOperacio extends Thread {
    private Compte compte;
    private boolean sumar;

    public FilOperacio(Compte compte, boolean sumar) {
        this.compte = compte;
        this.sumar = sumar;
    }
    public void run() {
        for (int i = 0; i < 5; i++){
            if (sumar) {
                compte.FilSumar();
            }
            else{
                compte.FilRestar();
            }
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
