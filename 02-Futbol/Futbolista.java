import java.util.Random;
public class Futbolista extends Thread {
    public final static int NUM_JUGADORS= 11;
    public final static int NUM_TIRADES = 20;
    public final static Float PROBABILITAT = 0.5f;
    private int ngols = 0;
    private int ntirades = 0;
    private String [] jugadores = new String[NUM_JUGADORS];
    public int getNgols() {
        return ngols;
    }
    public int getNtirades() {
        return ntirades;
    }
    public Futbolista(String name) {
        super(name); // Usa el nombre del jugador como nombre del hilo
    }
    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < NUM_TIRADES; i++){
            ntirades++;
            if (random.nextFloat() < PROBABILITAT) {
                ngols++;
            }
        }
    }
    public static void main(String[] args) {
        String[] nombres = { 
            "Piqué", "Vinicius", "Torres", "Ramos", "Ronaldo", 
            "Lewan", "Belli", "Arnau", "Aspas", "Messi", "MBapé" 
        };
        Futbolista[] jugadors = new Futbolista[NUM_JUGADORS];
        System.out.println("Inici dels xuts ----------------------");
        for (int i = 0; i < NUM_JUGADORS; i++) {
            jugadors[i] = new Futbolista(nombres[i]);
            jugadors[i].start(); // Inicia el hilo
            try {
                jugadors[i].join(); // Espera a que el hilo termine
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }        
        System.out.println("Fi dels xuts -------------------------");
        System.out.println("--- Estadístiques -------------------");
        for (Futbolista jugador : jugadors) {
            System.out.println(jugador.getName() + " -> " + jugador.getNgols() + " gols");
        }
    }
}