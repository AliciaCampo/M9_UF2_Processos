import java.util.Random;
public class Dona extends Thread {
    private Random random = new Random();
    private BanyUnisex bany;

    public Dona(String nom, BanyUnisex bany) {
        super(nom);
        this.bany = bany;
    }

    public void run() {
        System.out.println(getName() + " vol entrar al bany");
        bany.entraDona();
        utilitzaLavabo();
        bany.surtDona();
        System.out.println(getName() + " ha acabat d'usar el bany");
    }

    public void utilitzaLavabo() {
        try {
            Thread.sleep(2000 + random.nextInt(1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}