import java.util.List;
import java.util.ArrayList;
public class Esdeveniment {
    private List<Assistent> assistents = new ArrayList<>();
    private int placesMax = 10;
    private int placesDisponibles  ;
    public Esdeveniment(int placesMax){
        this.placesMax = placesMax;
        this.placesDisponibles = placesMax;
    }
    public synchronized void ferReserva(Assistent assistent) throws InterruptedException {
        while (placesDisponibles == 0) {
            try {
                wait();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        assistents.add(assistent);
        placesDisponibles--;
        System.out.println(assistent.getName() + " ha fet una reserva. Places disponibles: " + placesDisponibles);
        notifyAll();
    }

    public synchronized void cancelaReserva(Assistent assistent) {
        if (assistents.remove(assistent)) {
            placesDisponibles++;
            System.out.println(assistent.getName() + " ha cancel·lat una reserva. Places disponibles: " + placesDisponibles);
            notifyAll();
        } else {
            System.out.println(assistent.getName() + " no ha pogut cancel·lar una reserva inexistent. Places disponibles: " + placesDisponibles);
        }
    }
}