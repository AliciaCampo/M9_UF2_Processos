import java.util.Random;

public class Motor extends Thread {
    private int potenciaActual = 0;
    private int potenciaObjetivo = 0;
    private boolean enFuncionamiento = true; // Controla si el motor está activo
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
                        wait(); // Esperamos a que el motor reciba instrucciones
                    }

                    if (potenciaActual < potenciaObjetivo) {
                        potenciaActual++;
                        System.out.printf("Motor %d: Incre. Objetivo: %d Actual: %d%n", id, potenciaObjetivo, potenciaActual);
                    } else if (potenciaActual > potenciaObjetivo) {
                        potenciaActual--;
                        System.out.printf("Motor %d: Decre. Objetivo: %d Actual: %d%n", id, potenciaObjetivo, potenciaActual);
                    } else {
                        System.out.printf("Motor %d: FerRes Objetivo: %d Actual: %d%n", id, potenciaObjetivo, potenciaActual);

                        // Si la potencia actual y la objetivo son 0, apagamos el motor
                        if (potenciaActual == 0 && potenciaObjetivo == 0) {
                            enFuncionamiento = false;
                            System.out.printf("Motor %d: Apagado.%n", id);
                        }
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

