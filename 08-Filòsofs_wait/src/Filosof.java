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
        // Alternar el orden en que los filòsofs agarran las forquillas
        if (id % 2 == 0) {
            this.forquillaEsquerra = esquerra;
            this.forquillaDreta = dreta;
        } else {
            this.forquillaEsquerra = dreta;
            this.forquillaDreta = esquerra;
        }
    }

    private void pensar() throws InterruptedException {
        System.out.printf("Filòsof %d està pensant. Gana: %d%n", id, gana);
        Thread.sleep(1000 + random.nextInt(1000)); // Entre 1s i 2s
    }

    private void menjar() throws InterruptedException {
        while (true) {
            if (agafarForquilles()) {
                System.out.printf("Filòsof %d està menjant. Gana: %d%n", id, gana);
                Thread.sleep(1000 + random.nextInt(1000)); // Entre 1s i 2s
                deixarForquilles();
                gana = 0;
                return;
            } else {
                gana++;
                System.out.printf("Filòsof %d no pot menjar, gana=%d. Esperant...%n", id, gana);
                Thread.sleep(500 + random.nextInt(500)); // Espera entre 0.5s i 1s
            }
        }
    }

    private boolean agafarForquilles() throws InterruptedException {
        try {
            boolean esquerra = agafaForquillaEsquerra();
            boolean dreta = agafaForquillaDreta();
            
            if (esquerra && dreta) {
                return true;  // Si tiene ambas forquilles, puede comer
            } else {
                if (esquerra) deixarForquillaEsquerra();  // Si solo agarró la izquierda, la suelta
                if (dreta) deixarForquillaDreta();        // Si solo agarró la derecha, la suelta
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    private boolean deixarForquillaEsquerra() {
        if (forquillaEsquerra != null) { // Verifica si existe
            forquillaEsquerra.deixar();
            System.out.printf("%s ha deixat la forquilla esquerra %d.%n", getName(), forquillaEsquerra.getNumero());
            return true;
        }
        return false; // No había forquilla para soltar
    }
    
    private boolean deixarForquillaDreta() {
        if (forquillaDreta != null) { // Verifica si existe
            forquillaDreta.deixar();
            System.out.printf("%s ha deixat la forquilla dreta %d.%n", getName(), forquillaDreta.getNumero());
            return true;
        }
        return false; // No había forquilla para soltar
    }
    private boolean agafaForquillaEsquerra() {
        try {
            forquillaEsquerra.agafar(id);
            System.out.printf("%s ha agafat la forquilla esquerra %d.%n", getName(), forquillaEsquerra.getNumero());
            return true;  // Éxito al agarrar
        } catch (InterruptedException e) {
            return false; // Fallo al agarrar
        }
    }
    
    private boolean agafaForquillaDreta() {
        try {
            forquillaDreta.agafar(id);
            System.out.printf("%s ha agafat la forquilla dreta %d.%n", getName(), forquillaDreta.getNumero());
            return true;  // Éxito al agarrar
        } catch (InterruptedException e) {
            return false; // Fallo al agarrar
        }
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