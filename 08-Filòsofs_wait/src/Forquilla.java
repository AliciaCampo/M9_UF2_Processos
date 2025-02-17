public class Forquilla {
    private int propietari=-1;
    private int numero;
    public static final int LLIURE = -1; 
    public Forquilla(int numero) {
        this.numero = numero;
        this.propietari = LLIURE;
    }
    public Forquilla(){}
    public  boolean agafar(int id)throws InterruptedException{
        while(this.propietari != LLIURE){
            wait();
        }
        this.propietari = id;
        return true;
    }
    public int getPropietari() {
        return propietari;
    }
    public void setPropietari(int propietari) {
        this.propietari = propietari;
    }
 
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public synchronized void deixar(){
        propietari = LLIURE;
        notifyAll();
    }
}