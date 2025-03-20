import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
public class BanyUnisex {
    // constantes
    private static final int BANY_BUIT = 0;
    private static final int BANY_AMB_HOMES = 1;
    private static final int BANY_AMB_DONES = 2;
    private static final int CAPACITAT_MAX = 3;
    private int estatActual = BANY_BUIT;
    private int ocupants = 0;
    private Semaphore capacitat;
    private ReentrantLock lockEstat;
    //constructor
    public BanyUnisex() {
        this.capacitat = new Semaphore(CAPACITAT_MAX, true);
        this.lockEstat = new ReentrantLock(true);
    }
    //metodos de entrada y salida
    public void entraHome() {
        while (true) {
            lockEstat.lock();
            try {
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_HOMES) {
                    if (capacitat.tryAcquire()) {
                        ocupants++;
                        estatActual = BANY_AMB_HOMES;
                        System.out.println("Home entra al bany. Ocupants: " + ocupants);
                        return;
                    }
                }
            } finally {
                lockEstat.unlock();
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    public void entraDona() {
        while (true) {
            lockEstat.lock();
            try {
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_DONES) {
                    if (capacitat.tryAcquire()) {
                        ocupants++;
                        estatActual = BANY_AMB_DONES;
                        System.out.println("Dona entra al bany. Ocupants: " + ocupants);
                        return;
                    }
                }
            } finally {
                lockEstat.unlock();
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    public void surtHome() {
        lockEstat.lock();
        try {
            ocupants--;
            System.out.println("Home surt del bany. Ocupants: " + ocupants);
            if (ocupants == 0) {
                estatActual = BANY_BUIT;
                System.out.println("El bany està buit");
            }
            capacitat.release();
        } finally {
            lockEstat.unlock();
        }
    }
    public void surtDona() {
        lockEstat.lock();
        try {
            ocupants--;
            System.out.println("Dona surt del bany. Ocupants: " + ocupants);
            if (ocupants == 0) {
                estatActual = BANY_BUIT;
                System.out.println("El bany està buit");
            }
            capacitat.release();
        } finally {
            lockEstat.unlock();
        }
    }
    public static void main(String[] args) {
        BanyUnisex bany = new BanyUnisex();
        for (int i = 0; i < 5; i++) {
            new Home("Home-" + i, bany).start();
            new Dona("Dona-" + i, bany).start();
        }
    }
}