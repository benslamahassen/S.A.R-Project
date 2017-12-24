import java.rmi.registry.*;

public class Server {

    public static void main(String[] args) {
        try {
            //Construction RMI Registry
            Registry reg = LocateRegistry.createRegistry(1099);
            System.out.println("Construction de RMI Registry");

            //Construction Fabrique
            FabriqueInterface fab = new Fabrique();
            System.out.println("Construction de la Fabrique");

            //Liaison de Fabrique
            reg.rebind("Fabrique", fab);
            System.out.println("Exposition de Fabrique");
            
            System.out.println("Serveur prêt.");
            System.out.println("Attente des invocations des clients ...");

        } catch (Exception e) {

            System.out.println("Erreur de liaison de l'objet Fabrique");
            System.out.println(e.toString());
        }
    }
}