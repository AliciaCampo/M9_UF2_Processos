public class Forquilla {
    private int propietari;
    private int numero;
    public static final int LLIURE = -1; 
    public Forquilla(int numero) {
        this.numero = numero;
        this.propietari = LLIURE;
    }
    public Forquilla(){}
    public void agafar(int id)throws InterruptedException{
        while(propietari != LLIURE){
            wait();
        }
        propietari = id;
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
    
    public void deixar(){
        propietari = LLIURE;
        notifyAll();
    }
}