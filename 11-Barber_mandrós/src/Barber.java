public class Barber extends Thread {
    public Barber(String nom) {
        super(nom);
    }
    
    @Override
    public void run() {
        while (true) {
            Client client;
            synchronized(Barberia.instance.condBarber) {
                client = Barberia.instance.seguentClient();
                if (client == null) {
                    System.out.println("Ningú en espera");
                    System.out.println("Barber " + getName() + " dormint");
                    try {
                        Barberia.instance.condBarber.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
            }
            System.out.println("Li toca al client " + client.getNom());
            client.tallarseElCabell();
            try {
                // Tallar el cabell: 0,9s + temps aleatori fins a 0,1s
                long sleepTime = 900 + (long)(Math.random() * 100);
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

