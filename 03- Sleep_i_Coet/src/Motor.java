import java.util.Random;
public class Motor extends Thread {
    private int potenciaActual = 0;
    private int potenciaObjetivo = 0;
    private boolean enFuncionamiento = true; // Controla si el hilo sigue activo
    private int id; // Identificador del motor para los logs
    public Motor(int id) {
        this.id = id;
    }
    // Setter sincronizado para establecer la potencia objetivo
    public synchronized void setPotencia(int potenciaObjetivo) {
        this.potenciaObjetivo = potenciaObjetivo;
        notify(); // Notificamos al hilo que puede continuar
    }
    // Método principal del hilo
    @Override
    public void run() {
        Random random = new Random();
        try {
            while (enFuncionamiento) {
                synchronized (this) {
                    while (potenciaActual == potenciaObjetivo && enFuncionamiento) {
                        wait(); // Esperamos hasta que haya un cambio en la potencia objetivo
                    }
                    if (potenciaActual == potenciaObjetivo) {
                        System.out.printf("Motor %d: FerRes Objectiu: %d Actual: %d%n", id, potenciaObjetivo, potenciaActual);
                    }
                    if (potenciaActual < potenciaObjetivo) {
                        potenciaActual++;
                        System.out.printf("Motor %d: Incre. Objectiu: %d Actual: %d%n", id, potenciaObjetivo, potenciaActual);
                    } else if (potenciaActual > potenciaObjetivo) {
                        potenciaActual--;
                        System.out.printf("Motor %d: Decre. Objectiu: %d Actual: %d%n", id, potenciaObjetivo, potenciaActual);
                    } else {
                        System.out.printf("Motor %d: FerRes Objectiu: %d Actual: %d%n", id, potenciaObjetivo, potenciaActual);
                    }
                    // Si la potencia es 0, apagamos el motor
                    if (potenciaActual == 0 && potenciaObjetivo == 0) {
                        enFuncionamiento = false; // Detenemos el motor
                    }
                }
                // Pausa aleatoria entre 1 y 2 segundos
                Thread.sleep(1000 + random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            System.err.printf("Motor %d interrumpido.%n", id);
        }
    }
}