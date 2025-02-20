import java.util.Random;
public class Filosof extends Thread {
    //atributos
    private int iniciGana;
    private int fiGana;
    private int diferenciaGana;
    private int gana;
    private final Forquilla forquillaEsquerra;
    private final Forquilla forquillaDreta;
    private final Random random = new Random();
    public Filosof(String nom, Forquilla esquerra, Forquilla dreta) {
        super(nom);
        this.forquillaEsquerra = esquerra;
        this.forquillaDreta = dreta;
        resetGana();
    }
    private void pensar() throws InterruptedException {
        iniciGana = (int) System.currentTimeMillis() / 1000;
        System.out.printf("%s pensant%n", getName());
        Thread.sleep(500 + random.nextInt(500)); // Entre 0.5s i 1s
    }
    private void menjar() throws InterruptedException {
        while (true) {
            if (agafarForquilles()) {
                try {
                    fiGana = (int) System.currentTimeMillis() / 1000;
                    gana = calcularGana();
                    System.out.printf("%s té forquilles esq(%d) dreta(%d)%n", getName(), forquillaEsquerra.getNumero(), forquillaDreta.getNumero());
                    System.out.printf("%s menja amb gana %d%n", getName(), gana);
                    Thread.sleep(1000 + random.nextInt(1000)); // Entre 1s i 2s
                    resetGana();
                } finally {
                    deixarForquilles();
                }
                break;  // Surto del bucle si ha pogut menjar
            } else {
                gana++;
                System.out.printf("%s no pot menjar, gana %d%n", getName(), gana);
                Thread.sleep(500 + random.nextInt(500)); // Espera entre 0.5s i 1s
            }
        }
    }
    
    public int calcularGana() {
        return fiGana - iniciGana;
    }
    public void resetGana() {
        iniciGana = (int) System.currentTimeMillis() / 1000;
        gana = 0;
    }
    private boolean agafarForquilles() throws InterruptedException {
        forquillaEsquerra.agafar(); // Agafa la forquilla esquerra primer
        Thread.sleep(50); // Breu pausa per evitar interbloquejos
        try {
            forquillaDreta.agafar(); // Agafa la forquilla dreta després
            return true; // Si arriba aquí, ha agafat totes dues
        } catch (Exception e) {
            forquillaEsquerra.deixar(); // Si no pot agafar la dreta, deixa l'esquerra
            return false;
        }
    }
    
    
    private void agafaForquillaEsquerra()throws InterruptedException {
        forquillaEsquerra.agafar();
        System.out.printf("%s ha agafat la forquilla esquerra %d.%n", getName(), forquillaEsquerra.getNumero());
    }   
    private void agafaForquillaDreta()throws InterruptedException {
        forquillaDreta.agafar();
        System.out.printf("%s ha agafat la forquilla dreta %d.%n", getName(), forquillaDreta.getNumero());
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