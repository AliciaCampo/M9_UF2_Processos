import java.util.Random;

public class Assistent extends Thread {
    private Esdeveniment esdeveniment ;
    public Assistent(String nom,Esdeveniment esdeveniment){
        super(nom);
        this.esdeveniment = esdeveniment;
    }
    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            try {
                // Espera aleatoria entre 0 y 1 segundo
                Thread.sleep(random.nextInt(1001));  // 0-1000 ms (0-1 segundo)
                
                synchronized (esdeveniment) {
                    // Generar un número aleatorio entre 0 y 1
                    if (random.nextDouble() < 0.5) {
                        esdeveniment.ferReserva(this);  // 50% de hacer reserva
                    } else {
                        esdeveniment.cancelaReserva(this);  // 50% de cancelar reserva
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}