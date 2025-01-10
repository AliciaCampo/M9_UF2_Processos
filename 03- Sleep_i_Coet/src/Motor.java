import java.util.Random;

public class Motor extends Thread {
    private int potenciaActual = 0;
    private int potenciaObjetivo = 0;
    private boolean enFuncionamiento = true; // Controla si el hilo sigue activo
    private int id; // Identificador del motor para los logs
    private boolean objetivoEstablecido = false; // Bandera para verificar si ya se ha establecido un objetivo
    public Motor(int id) {
        this.id = id;
    }
    // Setter sincronizado para establecer la potencia objetivo
    public synchronized void setPotencia(int potenciaObjetivo) {
        this.potenciaObjetivo = potenciaObjetivo;
        objetivoEstablecido = true; // Activamos la bandera para indicar que ya hay un objetivo
        notify(); // Notificamos al hilo que puede continuar
    }
    // Método principal del hilo
    @Override
    public void run() {
        Random random = new Random();
        try {
            while (enFuncionamiento) {
                synchronized (this) {
                    if (potenciaActual == potenciaObjetivo) {
                        if (objetivoEstablecido) {
                            System.out.printf("Motor %d: FerRes Objectiu: %d Actual: %d%n", id, potenciaObjetivo, potenciaActual);
                        }
                        wait(); // Esperamos hasta que haya un cambio en la potencia objetivo
                        continue;
                    }
                    // Ajustamos la potencia si hay una diferencia
                    if (potenciaActual < potenciaObjetivo) {
                        potenciaActual++;
                        if (potenciaActual != potenciaObjetivo) {
                            System.out.printf("Motor %d: Incre. Objectiu: %d Actual: %d%n", id, potenciaObjetivo, potenciaActual);
                        }
                    } else if (potenciaActual > potenciaObjetivo) {
                        potenciaActual--;
                        if (potenciaActual != potenciaObjetivo) {
                            System.out.printf("Motor %d: Decre. Objectiu: %d Actual: %d%n", id, potenciaObjetivo, potenciaActual);
                        }
                    }
                    // Si la potencia es 0 y no hay objetivo, apagamos el motor
                    if (potenciaActual == 0 && potenciaObjetivo == 0 && objetivoEstablecido) {
                        System.out.printf("Motor %d: FerRes Objectiu: %d Actual: %d%n", id, potenciaObjetivo, potenciaActual);
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