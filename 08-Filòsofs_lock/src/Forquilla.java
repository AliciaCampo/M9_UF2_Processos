import java.util.concurrent.locks.ReentrantLock;

public class Forquilla {
    public int numero;
    public ReentrantLock bloqueig = new ReentrantLock(true);
    public Forquilla(int numero) {
        this.numero = numero;
    }
    public Forquilla(){}
    public int getNumero() {return numero;}
    public  void agafar() throws InterruptedException{
        bloqueig.lock();
    }
    public  void deixar(){
        bloqueig.unlock();
    }
}