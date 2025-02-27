import java.util.Random;
public class Filosof extends Thread {
    private int iniciGana;
    private int fiGana;
    private int gana;
    private final Forquilla forquillaEsquerra;
    private final Forquilla forquillaDreta;
    private final Random random = new Random();
    public Filosof(String nom, Forquilla esquerra, Forquilla dreta) {
        super(nom);
        this.forquillaEsquerra = esquerra;
        this.forquillaDreta = dreta;
        this.iniciGana = (int) (System.currentTimeMillis());
        resetGana();
    }
    private void pensar() throws InterruptedException {
        Thread.sleep(1000 + random.nextInt(1001));
    }    
    private void menjar() throws InterruptedException {
        while (true) {
            if (agafarForquilles()) {
                try {
                    fiGana = (int) System.currentTimeMillis() / 1000;
                    gana = calcularGana();
                    System.out.printf("%s té forquilles esq(%d) dreta(%d)%n", getName(), 
                        forquillaEsquerra.getNumero(), forquillaDreta.getNumero());
                    System.out.printf("%s menja amb gana %d%n", getName(), gana);
    
                    Thread.sleep(1000 + random.nextInt(1001)); // Simula menjar entre 1s i 2s
                    System.out.printf("%s ha acabat de menjar%n", getName());
                    resetGana();  // Comença a comptar la gana quan comença a pensar
                    return; // Surt del bucle després de menjar
                } finally {
                    deixarForquilles();
                }
            } else {
                gana++;
                System.out.printf("%s no pot menjar, gana %d%n", getName(), gana);
                Thread.sleep(500 + random.nextInt(501)); // Espera abans de tornar a intentar-ho
            }
        }
    }
    public int calcularGana() {
        return fiGana - iniciGana;
    }
    public void resetGana() {
        gana = 0;
        iniciGana = (int) System.currentTimeMillis() / 1000;
    }
    private boolean agafarForquilles() throws InterruptedException {
        agafaForquillaEsquerra();
        Thread.sleep(random.nextInt(50));
        try {
            agafaForquillaDreta();
            return true;
        } catch (Exception e) {
            forquillaEsquerra.deixar();
            return false;
        }
    }
    
    private void agafaForquillaEsquerra()throws InterruptedException {
        forquillaEsquerra.agafar();
    }   
    private void agafaForquillaDreta()throws InterruptedException {
        forquillaDreta.agafar();
    }
    private void deixarForquilles() {
        forquillaDreta.deixar();
        forquillaEsquerra.deixar();
        System.out.printf("%s deixa les forquilles%n", getName());
        System.out.printf("%s pensant%n", getName());
    }
    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                menjar();
            }
        } catch (InterruptedException e) {
            System.out.printf("%s interromput.%n", getName());
        }
    }
}