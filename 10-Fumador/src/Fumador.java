import java.util.Random;

public class Fumador extends Thread {
    private Estanc estanc;
    private int id;
    private Tabac tabac;
    private Paper paper;
    private Llumi llumi;
    private int fumades;
    private Random random = new Random();

    public Fumador(Estanc estanc, int id) {
        this.estanc = estanc;
        this.id = id;
        this.fumades = 0;
    }

    private void fuma() throws InterruptedException {
        System.out.println("Fumador " + id + " fumant");
        Thread.sleep(500 + random.nextInt(500));
        fumades++;
        tabac = null;
        paper = null;
        llumi = null;
        System.out.println("Fumador " + id + " ha fumat " + fumades + " vegades");
    }

    private void compraTabac() throws InterruptedException {
        tabac = estanc.venTabac();
        if (tabac != null) {
            System.out.println("Fumador " + id + " comprant Tabac");
        }
    }

    private void compraPaper() throws InterruptedException {
        paper = estanc.venPaper();
        if (paper != null) {
            System.out.println("Fumador " + id + " comprant Paper");
        }
    }

    private void compraLlumi() throws InterruptedException {
        llumi = estanc.venLlumi();
        if (llumi != null) {
            System.out.println("Fumador " + id + " comprant Llumi");
        }
    }

    @Override
    public void run() {
        try {
            while (fumades < 3) {
                compraTabac();
                compraPaper();
                compraLlumi();
                if (tabac != null && paper != null && llumi != null) {
                    fuma();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
