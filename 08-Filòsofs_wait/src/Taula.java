public class Taula {
    private Filosof [] comensals = new Filosof[4]; //4 filosofs
    private Forquilla [] forquilles; //2 por filosof
    public Taula(int numFilosofs){
        comensals = new Filosof[numFilosofs];
        forquilles = new Forquilla[numFilosofs];
        for (int i = 0; i < numFilosofs; i++) {
            forquilles[i] = new Forquilla(i);
        }
        for (int i = 0; i < numFilosofs; i++) {
            comensals[i] = new Filosof("fil" + i, forquilles[i], forquilles[(i + 1) % numFilosofs]);
        }
    }
    public void showTaula(){
        for ( int i =0 ; i< comensals.length ; i++){
            System.out.printf("Comensal: %s esq:%d dret:%d%n", 
            comensals[i].getName(), 
            forquilles[i].getPropietari(), 
            forquilles[(i + 1) % comensals.length].getPropietari());           
        }
        System.out.println("------------------------------");
    }
    public void cridaTaula(){
        for (Filosof fil : comensals){
            fil.start();
        }
    }
    public static void main(String[] args) {
        Taula taula = new Taula(4);
        taula.showTaula();
        taula.cridaTaula();
    }   
}