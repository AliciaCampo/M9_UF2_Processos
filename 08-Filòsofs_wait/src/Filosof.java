import java.util.Random;

public class Filosof extends Thread {
    private final int id;
    private final Forquilla forquillaEsquerra;
    private final Forquilla forquillaDreta;
    private int gana = 0;
    private final Random random = new Random();

    public Filosof(String nom, Forquilla esquerra, Forquilla dreta) {
        super(nom);
        this.id = Integer.parseInt(nom.replace("fil", ""));
        this.forquillaEsquerra = esquerra;
        this.forquillaDreta = dreta;
    }

    private void pensar() throws InterruptedException {
        System.out.printf("Filòsof %d està pensant.%n", id);
        Thread.sleep(1000 + random.nextInt(1000)); // Entre 1s i 2s
    }

    private void menjar() throws InterruptedException {
        while (true) {
            if (agafarForquilles()) {
                System.out.printf("Filòsof %d està menjant.%n", id);
                Thread.sleep(1000 + random.nextInt(1000)); // Entre 1s i 2s
                deixarForquilles();
                return;
            } else {
                gana++;
                System.out.printf("Filòsof %d no pot menjar, gana=%d. Esperant...%n", id, gana);
                Thread.sleep(500 + random.nextInt(500)); // Espera entre 0.5s i 1s
            }
        }
    }

    private boolean agafarForquilles() {
        try {
            agafaForquillaEsquerra();
            agafaForquillaDreta();
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }

    private void agafaForquillaEsquerra() throws InterruptedException {
        forquillaEsquerra.agafar(id);
        System.out.printf("%s ha agafat la forquilla esquerra %d.%n", getName(), forquillaEsquerra.getNumero());
    }

    private void agafaForquillaDreta() throws InterruptedException {
        forquillaDreta.agafar(id);
        System.out.printf("%s ha agafat la forquilla dreta %d.%n", getName(), forquillaDreta.getNumero());
    }

    private void deixarForquilles() {
        forquillaDreta.deixar();
        forquillaEsquerra.deixar();
        System.out.printf("%s ha deixat les forquilles.%n", getName());
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                menjar();
            }
        } catch (InterruptedException e) {
            System.out.printf("Filòsof %d interromput.%n", id);
        }
    }
}