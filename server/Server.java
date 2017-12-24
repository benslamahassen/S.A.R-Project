import java.rmi.*;

public class Server {

    public static void main(String[] args) {
        try {
            //Construction RMI Registry
            System.out.println("Construction de RMI Registry");
            Registry reg = LocateRegistry.createRegistry(1099);

            //Construction Fabrique
            System.out.println("Construction de la Fabrique");
            Fabrique fab = new Fabrique();

            //Liaison de Fabrique
            System.out.println("Exposition de Fabrique");
            reg.rebind("Fabrique", fab);
            
            System.out.println("Serveur prêt.");
            System.out.println("Attente des invocations des clients ...");

        } catch (Exception e) {

            System.out.println("Erreur de liaison de l'objet Fabrique");
            System.out.println(e.toString());
        }
    }
}