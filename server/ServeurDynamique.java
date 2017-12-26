import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.rmi.server.RMIClassLoader;
import java.util.Properties;

public class ServeurDynamique {
    public static void main(String[] args) {
        try {
            if(System.getSecurityManager() == null)
            System.setSecurityManager(new RMISecurityManager());
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("Serveur : Construction de l'impl");
            Properties p = System.getProperties();
            String url = p.getProperty("java.rmi.server.codebase");
            Class ClasseFabrique = RMIClassLoader.loadClass(url, "Fabrique");
            registry.rebind("Fabrique", (Remote) ClasseFabrique.newInstance());
            System.out.println("L'objet Fabrique lié dans le RMIregistry");
            System.out.println("Attente des invocations des clients");
        } catch (Exception e) {
            System.out.println("Erreur de liaison de l'objet Fabrique");
            System.out.println(e.toString());
        }
    }
}