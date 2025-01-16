import java.util.Random;
public class Treballador extends Thread {
    private float nou_anual_brut;
    private int edat_inici_treball;
    private int edat_fin_treball;
    private int edat = 0;
    private float cobrat;
    private Random rnd = new Random();
    public Treballador(float nouAnualBrut, int edatIniciTreball, int edatFiTreball, String nom) {
        super(nom);
        this.nou_anual_brut = nouAnualBrut;
        this.edat_inici_treball = edatIniciTreball;
        this.edat_fin_treball= edatFiTreball;
        this.edat = 0;
        this.cobrat = 0.0f;
        this.rnd = new Random();
    }
    public int getEdat() {
        return edat;
    }
    public float getCobrat() {
        return cobrat;
    }
    public void cobra(){
        //debe sumara lo cobrat una dozena parte del sueldo bruto
        float souMensual = nou_anual_brut/12;
        cobrat += souMensual;
    }
    public void pagaImpostos(){
        //debe hacer pagar 24% de lo que ha cobrado en el mes 
        float impostos = (nou_anual_brut /12)* 0.24f;
        cobrat -= impostos;
    }
    @Override
    public void run (){
        for(edat = 0; edat < edat_fin_treball;edat++) {
            if(edat >= edat_inici_treball && edat <= edat_fin_treball){
                cobra();
                pagaImpostos();
                try {
                    Thread.sleep(100 + rnd.nextInt(101));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }  
}