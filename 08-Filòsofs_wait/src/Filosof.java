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

    public void menjar() throws InterruptedException {

        agafarForquilles();

        // come
        System.out.printf("Filòsof: %s menja%n", getName());
        Thread.sleep(random.nextInt(1000) + 1000);    
        // deja las forquillas
        deixarForquilles();
        System.out.printf("Filòsof: %s ha acabat de menjar%n", getName());
        gana = 0;
    
      }
    
    public void agafarForquilles() throws InterruptedException {
        while (true) {
          agafaForquillaEsquerra();
          agafaForquillaDreta();
          if (forquillaDreta.getPropietari() == this.id) break;
          else Thread.sleep(random.nextInt(500) + 500);
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
    public void agafaForquillaEsquerra() throws InterruptedException {

        while (forquillaEsquerra.getPropietari() != Forquilla.LLIURE) {
          synchronized (this) { wait(); }
        }
    
        forquillaEsquerra.setPropietari(this.id);
        System.out.printf("Filòsof: %s agafa la forquilla esquerra %d%n", getName(), forquillaEsquerra.numero);
      }
    
    
      public void agafaForquillaDreta() throws InterruptedException {
    
        if (forquillaDreta.getPropietari() != Forquilla.LLIURE) {
          forquillaEsquerra.deixar();
          synchronized (this) {
            notifyAll();
          }
          System.out.printf("Filòsof: %s deixa la esquerra(%d) (dreta ocupada)%n", getName(), forquillaEsquerra.numero);
          gana++;
          System.out.printf("Filòsof: %s gana=%d%n", getName(), gana);
        }
        else {
          forquillaDreta.setPropietari(this.id);
          System.out.printf("Filòsof: %s agafa la forquilla dreta %d%n", getName(), forquillaDreta.numero);
        }
    
      }
    private synchronized  void deixarForquilles() {
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