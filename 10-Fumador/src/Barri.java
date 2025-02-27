public class Barri {
    public static void main(String[] args) {
        Estanc estanc = new Estanc();
        Fumador[] fumadors = new Fumador[3];

        // Inicializar fumadores
        for (int i = 0; i < 3; i++) {
            fumadors[i] = new Fumador(estanc, i);
        }

        // Iniciar el estanco
        estanc.start();

        // Iniciar los fumadores
        for (Fumador fumador : fumadors) {
            fumador.start();
        }

        System.out.println("Estanc obert");

        // Esperar a que los fumadores terminen
        for (Fumador fumador : fumadors) {
            try {
                fumador.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Cerrar el estanco
        estanc.tancarEstanc();
    }
}
