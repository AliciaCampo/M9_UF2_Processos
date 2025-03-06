import java.util.LinkedList;
import java.util.Queue;

public class Barberia extends Thread {
    public Queue<Client> salaEspera;
    public int cadires;
    public Object condBarber;
    public static Barberia instance;
    
    // Constructor amb el nombre de cadires
    public Barberia(int cadires) {
        this.cadires = cadires;
        this.salaEspera = new LinkedList<>();
        this.condBarber = new Object();
        instance = this;
    }
    
    // Retorna el següent client de la cua o null si la cua està buida
    public Client seguentClient() {
        if (!salaEspera.isEmpty()) {
            return salaEspera.poll();
        }
        return null;
    }
    
    // Permet que un client entri a la sala d'espera i notifica el barber, o se'n va si no hi ha cadires
    public void entrarClient(Client client) {
        synchronized(condBarber) {
            if (salaEspera.size() < cadires) {
                salaEspera.offer(client);
                System.out.println("Client " + client.getNom() + " en espera");
                condBarber.notify();
            } else {
                System.out.println("No queden cadires, client " + client.getNom() + " se'n va");
            }
        }
    }
    
    @Override
    public void run() {
        int id = 1;
        // Primera ronda: 10 clients, un cada 0,5 segons
        for (int i = 0; i < 10; i++) {
            Client client = new Client(id, "");
            entrarClient(client);
            id++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Espera de 10 segons
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Segona ronda: 10 clients, un cada 0,5 segons
        for (int i = 0; i < 10; i++) {
            Client client = new Client(id, "");
            entrarClient(client);
            id++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        // Crea la barberia amb 3 cadires
        Barberia barberia = new Barberia(3);
        // Crea el barber amb nom "Pepe"
        Barber barber = new Barber("Pepe");
        barber.start();
        barberia.start();
    }
}
