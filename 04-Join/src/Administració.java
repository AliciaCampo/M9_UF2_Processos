class Administracio {
    private final int numPoblacioActiva = 50;
    private final Treballador[] poblacioActiva;

    public Administracio() {
        poblacioActiva = new Treballador[numPoblacioActiva];
        for (int i = 0; i < numPoblacioActiva; i++) {
            poblacioActiva[i] = new Treballador(25000f, 20, 65, "Ciutadà-" + i);
        }
    }

    public void principal() {
        for (Treballador treballador : poblacioActiva) {
            treballador.start();
        }

        for (Treballador treballador : poblacioActiva) {
            try {
                treballador.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Treballador treballador : poblacioActiva) {
            System.out.printf("%s -> edat: %d / total: %.2f\n",
                    treballador.getName(), treballador.getEdat(), treballador.getCobrat());
        }
    }
    public static void main(String[] args) {
        Administracio administracio = new Administracio();
        administracio.principal();
    }
}