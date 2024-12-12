public class Principal {
    public static void main(String[] args) {
        Fil juan = new Fil("Juan");
        Fil pepe = new Fil("Pepe");
        pepe.setPriority(Thread.MAX_PRIORITY); // Alta prioridad
        juan.setPriority(Thread.MIN_PRIORITY); // Baja prioridad

        juan.start();
        pepe.start();
        System.out.println("Termina thread main");
    }
}