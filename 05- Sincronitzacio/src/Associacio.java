public class Associacio {
    private int numSocis = 1000;
    private Soci[] socis;
    public Associacio() {
        Compte compte = Compte.getInstance();
        socis = new Soci[numSocis];
        for (int i = 0; i < numSocis; i++) {
            socis[i] = new Soci(compte);
        }
        this.numSocis = 1000;
    }
    public void iniciaCompteTempsSocis(){
        for (Soci soci : socis){
            soci.start();
        }
    }
    public void esperaPeriodeSocis(){
        for (Soci soci : socis){
            try {
                soci.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }   
        }
    }
    public void mostraBalancComptes(){
        System.out.printf("Saldo: %.2f%n", Compte.getInstance().getSaldo());
    }
    public static void main(String[] args) {
        Associacio associacio = new Associacio();
        associacio.iniciaCompteTempsSocis();
        associacio.esperaPeriodeSocis();
        associacio.mostraBalancComptes();
    }   
}