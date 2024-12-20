package src;
public class MainDemoFil {
    public static void main(String[] args) {
        // Capturar el hilo actual en ejecución (el hilo principal)
        Thread currentThread = Thread.currentThread();
        // Mostrar las propiedades del hilo principal
        System.out.println("MainDemoFil.main:");
        System.out.println("Prioritat -> " + currentThread.getPriority()+ ", Nom -> " + currentThread.getName());
        System.out.println("toString() -> " + currentThread.toString());
    }
}