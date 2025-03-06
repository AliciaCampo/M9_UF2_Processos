public class Client {
    private int id;
    private String nom; 
    public void tallarseElCabell (){
        System.out.println("Tallant cabell a " + nom);
    }
    public Client(int id, String nom){
        this.id = id;
        this.nom = "Client-" + id;
    }
    public String getNom(){
        return nom;
    }
}