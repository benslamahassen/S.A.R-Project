import java.rmi.registry.*;
import java.rmi.*;

public class Client {

    public Client() {
        try {
            if (System.getSecurityManager() == null)
                System.setSecurityManager(new RMISecurityManager());
            //Interroger de RMIregistry
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            //Trouver une réference vers la Fabrique
            FabriqueInterface fab = (FabriqueInterface) reg.lookup("Fabrique");
            //Création d'institution
            InstitutionInterface institution = (InstitutionInterface) fab.createInstitution();
            //Création de l'Employe
            Employe employe = new Employe("salah", "ali", 01234567, 93116960);
            //Invocation de la méthode Add
            String resultat = institution.add(employe);
            //Affichage
            System.out.println("Succée de l'ajout : \n" + resultat);

        } catch (Exception e) {

            System.out.println("Erreur d'acces à l'objet distant.");
            System.out.println(e.toString());

        }
    }
}