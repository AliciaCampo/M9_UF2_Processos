import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Estanc extends Thread {
    private List<Llumi> llumis = new ArrayList<>();
    private List<Tabac> tabacs = new ArrayList<>();
    private List<Paper> papers = new ArrayList<>();
    private final Random random = new Random();
    private boolean obert = true;

    public Estanc() {}

    public synchronized void nouSubministrament() {
        int tipus = random.nextInt(3);
        switch (tipus) {
            case 0 -> addTabac();
            case 1 -> addPaper();
            case 2 -> addLlumi();
        }
    }

    public synchronized void addTabac() {
        this.tabacs.add(new Tabac());
        System.out.println("Afegint tabac");
        notifyAll();
    }

    public synchronized void addPaper() {
        this.papers.add(new Paper());
        System.out.println("Afegint paper");
        notifyAll();
    }

    public synchronized void addLlumi() {
        this.llumis.add(new Llumi());
        System.out.println("Afegint llum");
        notifyAll();
    }

    public synchronized Tabac venTabac() throws InterruptedException {
        while (tabacs.isEmpty() && obert) wait();
        return tabacs.isEmpty() ? null : tabacs.remove(0);
    }

    public synchronized Paper venPaper() throws InterruptedException {
        while (papers.isEmpty() && obert) wait();
        return papers.isEmpty() ? null : papers.remove(0);
    }

    public synchronized Llumi venLlumi() throws InterruptedException {
        while (llumis.isEmpty() && obert) wait();
        return llumis.isEmpty() ? null : llumis.remove(0);
    }

    public synchronized void tancarEstanc() {
        obert = false;
        notifyAll();
    }

    @Override
    public void run() {
        while (obert) {
            nouSubministrament();
            try {
                Thread.sleep(500 + random.nextInt(1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Estanc tancat");
    }
}
