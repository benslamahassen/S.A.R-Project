import java.rmi.registry.*;
import java.rmi.*;
import java.util.Scanner;

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

        } catch (Exception e) {

            System.out.println("Erreur d'acces à l'objet distant.");
            System.out.println(e.toString());

        }
        while (1) {
            System.out.println("Tapez:\n"
                    + "1: pour ajouter un employé.\n"
                    + "2: pour supprimer un employé.\n"
                    + "3: pour mettre à jour un employé.\n"
                    + "4: pour chercher un employé.\n"
                    + "5: pour quitter.");
            Scanner choixMenuScanner = new Scanner(System.in);
            int choixMenu = choixMenuScanner.nextInt();
            switch (choixMenu){
                case 1:
                    System.out.println("Tapez le nom de l'employé à ajouter.\n"+"Nom de l'employé à ajouter":);
                    Scanner nomEmpScanner = new Scanner(System.in);
                    String nomEmp = nomEmpScanner.next();
                    System.out.println("Tapez le prenom de l'employé à ajouter.\n"+"Prenom de l'employé à ajouter:");
                    Scanner prenomEmpScanner = new Scanner(System.in);
                    String prenomEmp = prenomEmpScanner.next();
                    System.out.println("Tapez le CIN de l'employé à ajouter.\n"+"CIN de l'employé à ajouter:");
                    Scanner cinEmpScanner = new Scanner(System.in);
                    int cinEmp = cinEmpScanner.nextInt();
                    System.out.println("Tapez le télèphone de l'employé à ajouter.\n"+"Télèphone de l'employé à ajouter:");
                    Scanner telephoneEmpScanner = new Scanner(System.in);
                    int telephoneEmp = telephoneEmpScanner.nextInt();
                    Employe emp = new Empolye(nomEmp, prenomEmp , cinEmp , telephoneEmp);
                    String resultat = institutiton.add(emp,nomInstitution);
                    System.out.println(resultat);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.exit(0);
            }
        }


    }
}
