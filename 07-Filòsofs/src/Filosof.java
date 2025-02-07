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
        System.out.printf("Filòsof: %s pensant%n", getName());
        Thread.sleep(1000 + random.nextInt(1000));  // 1s - 2s
    }
    private void menjar() throws InterruptedException {
        while (true) {
            // Intentar agafar la forquilla esquerra
            while (!forquillaEsquerra.agafar()) {
                Thread.sleep(500 + random.nextInt(500));  // Espera 0.5s - 1s
            }
            System.out.printf("Filòsof: %s agafa la forquilla esquerra %d%n", getName(), forquillaEsquerra.getNumero());

            // Intentar agafar la forquilla dreta
            if (!forquillaDreta.agafar()) {
                System.out.printf("Filòsof %s deixa l'esquerra (%d) i espera (dreta ocupada)%n", getName(), forquillaEsquerra.getNumero());
                forquillaEsquerra.deixar();
                gana++;
                System.out.printf("Filòsof: %s gana=%d%n", getName(), gana);
                Thread.sleep(500 + random.nextInt(500));  // Espera 0.5s - 1s
                continue;
            }
            System.out.printf("Filòsof: %s agafa la forquilla dreta %d%n", getName(), forquillaDreta.getNumero());
            System.out.printf("Filòsof: %s menja%n", getName());
            Thread.sleep(1000 + random.nextInt(1000));  // 1s - 2s
            System.out.printf("Filòsof: %s ha acabat de menjar%n", getName());
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