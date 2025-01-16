public class Treballador extends Thread {
    private float nou_anual_brut;
    private int edat_inici_treball;
    private int edat_fin_treball;
    private int edat_actual = 0;
    private float cobrat = 0.0f;
    private float pagaImpostos;

    public Treballador(int edat_fin_treball, int edat_inici_treball, float nou_anual_brut, float pagaImpostos,String nombre) {
        super(nombre);
        this.edat_fin_treball = edat_fin_treball;
        this.edat_inici_treball = edat_inici_treball;
        this.nou_anual_brut = nou_anual_brut;
        this.pagaImpostos = pagaImpostos;
    }

    public int getEdat_actual() {
        return edat_actual;
    }

    public float getCobrat() {
        return cobrat;
    }
    public void cobra
    

    
}
