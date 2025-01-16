import java.util.Random;
public class Motor extends Thread {
    private int potenciaActual = 0;
    private int potenciaObjetivo = 0;
    private boolean enFuncionamiento = true; // Controla si el hilo sigue activo
    private int id; // Identificador del motor para los logs
    private boolean objetivoEstablecido = false; // Bandera para verificar si ya se ha establecido un objetivo
    private boolean mensajeMostrado = false; // Bandera para controlar impresión única del mensaje
    public Motor(int id) {
        this.id = id;
    }
    // Setter para establecer la potencia objetivo
    public void setPotencia(int potenciaObjetivo) {
        this.potenciaObjetivo = potenciaObjetivo;
        this.objetivoEstablecido = true; 
        mensajeMostrado = false; 
    }
    // Método principal del hilo
    @Override
    public void run() {
        Random random = new Random();
        try {
            while (enFuncionamiento) {
                if (potenciaActual == potenciaObjetivo) {
                    if (objetivoEstablecido && !mensajeMostrado) {
                        System.out.printf("Motor %d: FerRes Objectiu: %d Actual: %d%n", id, potenciaObjetivo, potenciaActual);
                        mensajeMostrado = true; // Indicamos que el mensaje ya fue mostrado
                    }
                    // Pausa mientras no hay cambios en el objetivo
                    Thread.sleep(100);
                    continue;
                }

                // Ajustamos la potencia si hay una diferencia
                if (potenciaActual < potenciaObjetivo) {
                    potenciaActual++;
                    if (potenciaActual != potenciaObjetivo) {
                        System.out.printf("Motor %d: Incre. Objectiu: %d Actual: %d%n", id, potenciaObjetivo, potenciaActual);
                    }
                } else if (potenciaActual > potenciaObjetivo) {
                    if (potenciaActual != potenciaObjetivo) {
                        System.out.printf("Motor %d: Decre. Objectiu: %d Actual: %d%n", id, potenciaObjetivo, potenciaActual);
                    }
                    potenciaActual--;
                }
                // Si la potencia es 0 y no hay objetivo, apagamos el motor
                if (potenciaActual == 0 && potenciaObjetivo == 0 && objetivoEstablecido) {
                    System.out.printf("Motor %d: FerRes Objectiu: %d Actual: %d%n", id, potenciaObjetivo, potenciaActual);
                    enFuncionamiento = false; // Detenemos el motor
                }
                // Pausa aleatoria entre 1 y 2 segundos
                Thread.sleep(1000 + random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            System.err.printf("Motor %d interrumpido.%n", id);
        }
    }
}