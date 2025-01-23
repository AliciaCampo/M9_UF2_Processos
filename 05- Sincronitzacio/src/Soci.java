import java.util.Random;
public class Soci extends Thread {
    private Compte compte;
    private  float aportacio ;
    private int esperaMax;
    private Random random = new Random();
    private static int maxAnys;
    public Soci(Compte compte) {
        this.compte = compte;
        this.aportacio = 10f;
        this.esperaMax = 100;
        this.random = random;
        this.maxAnys = 10;
    }
     public Compte getCompte() {
        return compte;
    }
    @Override
    public void run() {
        for ( int i = 0; i <maxAnys; i++){
            for (int mes = 1; mes <= 12; mes++) {
                if (mes % 2 == 0){
                    compte.ingresar(aportacio);
                }else{
                    compte.retirar(aportacio);
                }
                try {
                    Thread.sleep(random.nextInt(esperaMax));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}