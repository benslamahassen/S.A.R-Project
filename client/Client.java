import java.rmi.registry.*;

public class Client {

    public static void main(String[] args) {
        try {
            //Interroger de RMIregistry
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            //Trouver une réference vers la Fabrique
            FabriqueInterface fab = (FabriqueInterface) reg.lookup("Fabrique");
            //Création d'institution
            InstitutionInterface institution = (InstitutionInterface) fab.createInstitution();
            //Récupération de l'objet Employe
            Employe employe = institution.add("salah", "ali", 01234567, 93116960);
            //Affichage
            System.out.println("Nom: " + employe.nom);
            System.out.println("Prenom: " + employe.prenom);
            System.out.println("CIN: " + employe.cin);
            System.out.println("Telephone: " + employe.telephone);

        } catch (Exception e) {

            System.out.println("Erreur d'acces à l'objet distant.");
            System.out.println(e.toString());

        }
    }
}