import java.util.Random;
public class DormAleatori extends Thread {
    private int interval_aleatori;
    private Random random = new Random();
    private long tiempo;
    public DormAleatori(String nombre) {
        super(nombre);
        this.tiempo = System.currentTimeMillis();
    }
    public static void main(String[] args) {
        DormAleatori dormir1 = new DormAleatori("Joan");
        DormAleatori dormir2 = new DormAleatori("Pep");
        dormir1.start();
        dormir2.start();
        try {
            Thread.sleep(50);    
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-- Fi de main -----------");
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            interval_aleatori = random.nextInt(1000);
            long tiempoTotal = System.currentTimeMillis()-tiempo;
            System.out.printf("%s(%d) a dormir %dms total %d%n", getName(), i, interval_aleatori, tiempoTotal);
            try {
                Thread.sleep(interval_aleatori);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}