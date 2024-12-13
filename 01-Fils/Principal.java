public class Principal {
    public static void main(String[] args) {
        Object lock = new Object(); // Objeto compartido para sincronización
        // Crear hilos con sus identificadores
        Fil juan = new Fil("Juan", 1, lock);
        Fil pepe = new Fil("Pepe", 2, lock);
        // Mensaje principal
        System.out.println("Termina thread main");
        // Iniciar los hilos
        juan.start();
        pepe.start();
        // Esperar a que terminen ambos hilos
        try {
            juan.join();
            pepe.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Mensajes finales
        System.out.println("Termina el fil Pepe");
        System.out.println("Termina el fil Juan");
    }
}