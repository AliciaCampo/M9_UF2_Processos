public class Forquilla {
    private int propietari=-1;
    public int numero;
    public static final int LLIURE = -1; 
    public boolean enUs = false;
    public Forquilla(int numero) {
        this.numero = numero;
        this.propietari = LLIURE;
    }
    public Forquilla(){}
    public  void agafar(int id) throws InterruptedException{
        while(this.propietari != LLIURE){
            wait();
        }
        this.propietari = id;
    }
    public synchronized void deixar(){
        propietari = LLIURE;
        notifyAll();
    }
    public int getPropietari() {return propietari;}
    public void setPropietari(int propietari) {this.propietari = propietari;}
    public int getNumero() {return numero;}
    public void setNumero(int numero) {this.numero = numero;}
}