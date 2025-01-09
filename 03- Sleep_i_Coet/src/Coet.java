class Coet {
    private Motor[] motores = new Motor[4];

    public Coet() {
        // Inicializamos los motores
        for (int i = 0; i < motores.length; i++) {
            motores[i] = new Motor(i);
        }
    }
    // Método para establecer la potencia de los motores
    public void pasaAPotencia(int potencia) {
        if (potencia < 0 || potencia > 10) {
            System.out.printf("Error: Potencia fuera de rango (0-10).%n");
            return;
        }
        System.out.printf("Passant a potència %d%n", potencia);
        for (Motor motor : motores) {
            motor.setPotencia(potencia);
        }
    }
    // Método para arrancar los motores
    public void arranca() {
        for (Motor motor : motores) {
            motor.start(); // Iniciamos los motores, pero esperan instrucciones
        }
    }
    // Método principal para manejar la entrada del usuario
    public void manejarEntrada() {
        int potencia;
        do {
            potencia = Integer.parseInt(Entrada.readLine()); // Leer entrada del usuario
            pasaAPotencia(potencia);
        } while (potencia != 0);

        // Esperamos a que los motores terminen
        for (Motor motor : motores) {
            try {
                motor.join(); // Espera a que el motor termine
            } catch (InterruptedException e) {
                System.err.printf("Error esperando a los motores.%n");
            }
        }
    }
    public static void main(String[] args) {
        Coet coet = new Coet();
        coet.arranca(); // Arrancamos los motores en estado de espera
        coet.manejarEntrada(); // Esperamos entrada del usuario
    }
}