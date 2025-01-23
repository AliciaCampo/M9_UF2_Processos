public class Compte {
    private float saldo;
    private static Compte instance;

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    private Compte(){}
    public static Compte getInstance(){
        if(instance == null){
            instance = new Compte();
        }
        return instance;
    }
    public void ingresar(float valor){
        this.saldo += valor;
    }public void retirar(float valor){
        this.saldo -= valor;
    }
}