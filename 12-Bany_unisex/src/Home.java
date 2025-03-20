import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Home extends Thread {
    private Random random = new Random();
    private BanyUnisex bany;

    public Home(String nom, BanyUnisex bany) {
        super(nom);
        this.bany = bany;
    }

    public void run() {
        System.out.println(getName() + " vol entrar al bany");
        bany.entraHome();
        utilitzaLavabo();
        bany.surtHome();
        System.out.println(getName() + " ha acabat d'usar el bany");
    }

    public void utilitzaLavabo() {
        try {
            Thread.sleep(1000 + random.nextInt(1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}