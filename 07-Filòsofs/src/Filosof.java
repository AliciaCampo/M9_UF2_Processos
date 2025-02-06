import java.util.Random;

public class Filosof extends Thread {
    private Forquilla forquillaDreta ;
    private Forquilla forquillaEsquerra;
    private int gana = 0;
     private final Random random = new Random();
    public  Filosof( String nom, Forquilla forquillaEsquerra , Forquilla forquillaDreta ) {
        super(nom);
        this.forquillaEsquerra = forquillaEsquerra;
        this.forquillaDreta = forquillaDreta;
    
        this.gana = 0;
    }
    private void pensar() throws InterruptedException {
        System.out.println("Filòsof " + getName() + " pensant");
        Thread.sleep(1000 + random.nextInt(1000));  // 1s - 2s
    }
    private void menjar() throws InterruptedException {
        while (true) {
            // Intentar agafar la forquilla esquerra
            while (!forquillaEsquerra.agafar()) {
                Thread.sleep(500 + random.nextInt(500));  // Espera 0.5s - 1s
            }
            System.out.println("Filòsof " + getName() + " agafa la forquilla esquerra " + forquillaEsquerra.getNumero());

            // Intentar agafar la forquilla dreta
            if (!forquillaDreta.agafar()) {
                System.out.println("Filòsof " + getName() + " deixa la forquilla esquerra " + forquillaEsquerra.getNumero() + " i espera (dreta ocupada)");
                forquillaEsquerra.deixar();
                gana++;
                System.out.println("Filòsof " + getName() + " gana=" + gana);
                Thread.sleep(500 + random.nextInt(500));  // Espera 0.5s - 1s
                continue;
            }

            System.out.println("Filòsof " + getName() + " agafa la forquilla dreta " + forquillaDreta.getNumero());
            System.out.println("Filòsof " + getName() + " menja");
            Thread.sleep(1000 + random.nextInt(1000));  // 1s - 2s
            System.out.println("Filòsof " + getName() + " ha acabat de menjar");

            // Deixar forquilles
            forquillaDreta.deixar();
            forquillaEsquerra.deixar();
            return;  // Sale del método y vuelve a `run()`
        }
    }
    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                menjar();
                }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Forquilla getForquillaDreta() {
        return forquillaDreta;
    }

    public void setForquillaDreta(Forquilla forquillaDreta) {
        this.forquillaDreta = forquillaDreta;
    }

    public Forquilla getForquillaEsquerra() {
        return forquillaEsquerra;
    }

    public void setForquillaEsquerra(Forquilla forquillaEsquerra) {
        this.forquillaEsquerra = forquillaEsquerra;
    }
    
}