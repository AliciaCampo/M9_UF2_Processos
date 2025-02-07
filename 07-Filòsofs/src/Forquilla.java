public class Forquilla {
    private int numero;
    private boolean enUs;
    public Forquilla( int numero) {
        this.enUs = false;
        this.numero = numero;
    }
    public Forquilla(){}
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public boolean isEnUs() {
        return enUs;
    }
    public void setEnUs(boolean enUs) {
        this.enUs = enUs;
    }
    public boolean agafar(){
       if(!enUs){
        enUs = true;
        return true;
       }
       return false;
    }
    public void deixar(){
        enUs = false;
    }
}