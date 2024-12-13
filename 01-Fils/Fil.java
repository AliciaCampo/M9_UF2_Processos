public class Fil extends Thread {
    private String nom;
    private Object lock;
    private static int turno = 1; // Controla el turno de ejecución
    private int id;              // Identificador del hilo (1 para Juan, 2 para Pepe)
    public Fil(String nom, int id, Object lock) {
        this.nom = nom;
        this.id = id;
        this.lock = lock;
    }
    @Override
    public void run() {
        for (int i = 1; i <= 9; i++) {
            synchronized (lock) {
                while (turno != id) { // Espera hasta que sea su turno
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // Ejecuta la acción de este hilo
                System.out.println(nom + " " + i);
                // Cambia el turno al otro hilo
                turno = (id == 1) ? 2 : 1;
                // Notifica a los otros hilos
                lock.notifyAll();
            }
        }
    }
}