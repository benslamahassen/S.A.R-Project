import java.rmi.registry.*;
import java.rmi.*;
import java.util.Scanner;

public class Client {
    
    public Client() {
        //Boucle Infinie pour l'affichage contenue de la Menu
        while (true) {
            //Affichage de menu
            System.out.println("Tapez:\n"
            +"   "+"1: pour ajouter un employé.\n"
            +"   "+"2: pour supprimer un employé.\n"
            +"   "+"3: pour mettre à jour un employé.\n"
            +"   "+"4: pour chercher un employé.\n"
            +"   "+"5: pour quitter.");
            Scanner choixMenuScanner = new Scanner(System.in);
            int choixMenu = choixMenuScanner.nextInt();
            //Lire le choix de l'utilisateur
            switch (choixMenu){
                case 1:
                    //Saisie donnée Employe à ajouter
                    System.out.println("Tapez le nom de l'employé à ajouter.\n"+"Nom de l'employé à ajouter:");
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
                    System.out.println("Tapez le nom d'institution. Exemple FST");
                    Scanner nomInstitutionScanner = new Scanner(System.in);
                    String nomInstitution = nomInstitutionScanner.next();
                    try {
                        if (System.getSecurityManager() == null)
                        System.setSecurityManager(new RMISecurityManager());
                        //Interroger de RMIregistry
                        Registry reg = LocateRegistry.getRegistry("localhost", 1099);
                        //Trouver une réference vers la Fabrique
                        FabriqueInterface fab = (FabriqueInterface) reg.lookup("Fabrique");
                        //Création d'institution
                        InstitutionInterface institution = (InstitutionInterface) fab.createInstitution();
                        //Création Employe
                        Employe emp = new Employe(nomEmp, prenomEmp , cinEmp , telephoneEmp);
                        String resultat = institution.add(emp,nomInstitution);
                        System.out.println("Succés de l'ajout.\n"+resultat);
                    } catch (Exception e) {
                        System.out.println("Erreur d'acces à l'objet distant.");
                        System.out.println(e.toString());
                    }
                break;
                case 2:
                    //Saisie de CIN de l'employe à supprimer
                    System.out.println("Tapez le nom de l'employe à Supprimer. Exemple : 11111111\n"+"CIN de l'employé à supprimer:");
                    Scanner cinEmpSupprimerScanner = new Scanner(System.in);
                    int cinEmpSupprimer = cinEmpSupprimerScanner.nextInt();
                    System.out.println("Tapez le nom d'institution. Exemple FST");
                    Scanner nomInstitutionDeleteScanner = new Scanner(System.in);
                    String nomInstitutionDelete = nomInstitutionDeleteScanner.next();
                    try {
                        if (System.getSecurityManager() == null)
                        System.setSecurityManager(new RMISecurityManager());
                        //Interroger de RMIregistry
                        Registry reg = LocateRegistry.getRegistry("localhost", 1099);
                        //Trouver une réference vers la Fabrique
                        FabriqueInterface fab = (FabriqueInterface) reg.lookup("Fabrique");
                        //Création d'institution
                        InstitutionInterface institution = (InstitutionInterface) fab.createInstitution();
                        //Création Employe
                        institution.delete(cinEmpSupprimer,nomInstitutionDelete);
                        System.out.println("Succées de la suppression.");                   
                    } catch (Exception e) {
                        System.out.println("Erreur d'acces à l'objet distant.");
                        System.out.println(e.toString());
                    }
                break;
                case 3:
                    //Saisie donnée Employe à mettre à jour
                    System.out.println("Tapez le CIN de l'employé à mettre à jour.\n"+"CIN de l'employé à mettre à jour:");
                    Scanner cinEmpUpdateScanner = new Scanner(System.in);
                    int cinEmpUpdate = cinEmpUpdateScanner.nextInt();
                    System.out.println("Tapez le nom de l'employé à mettre à jour.\n"+"Nom de l'employé à mettre à jour:");
                    Scanner nomEmpUpdateScanner = new Scanner(System.in);
                    String nomEmpUpdate = nomEmpUpdateScanner.next();
                    System.out.println("Tapez le prenom de l'employé à mettre à jour.\n"+"Prenom de l'employé à mettre à jour:");
                    Scanner prenomEmpUpdateScanner = new Scanner(System.in);
                    String prenomEmpUpdate = prenomEmpUpdateScanner.next();
                    System.out.println("Tapez le télèphone de l'employé à mettre à jour.\n"+"Télèphone de l'employé à mettre à jour:");
                    Scanner telephoneEmpUpdateScanner = new Scanner(System.in);
                    int telephoneEmpUpdate = telephoneEmpUpdateScanner.nextInt();
                    System.out.println("Tapez le nom d'institution. Exemple FST");
                    Scanner nomInstitutionUpdateScanner = new Scanner(System.in);
                    String nomInstitutionUpdate = nomInstitutionUpdateScanner.next();
                    try {
                        if (System.getSecurityManager() == null)
                        System.setSecurityManager(new RMISecurityManager());
                        //Interroger de RMIregistry
                        Registry reg = LocateRegistry.getRegistry("localhost", 1099);
                        //Trouver une réference vers la Fabrique
                        FabriqueInterface fab = (FabriqueInterface) reg.lookup("Fabrique");
                        //Création d'institution
                        InstitutionInterface institution = (InstitutionInterface) fab.createInstitution();
                        //Création Employe
                        Employe empUpdate = new Employe(nomEmpUpdate, prenomEmpUpdate , cinEmpUpdate , telephoneEmpUpdate);
                        institution.update(empUpdate,nomInstitutionUpdate);
                        System.out.println("L'employe"+ nomEmpUpdate+" "+prenomEmpUpdate+" est modifié.");
                    } catch (Exception e) {
                        System.out.println("Erreur d'acces à l'objet distant.");
                        System.out.println(e.toString());
                    }
                break;
                case 4:
                    //Saisie donnée Employe à cherecher
                    System.out.println("Tapez le CIN de l'employé à rechercher. Exemple 44444444\n"+"CIN de l'employé à chercher:");
                    Scanner cinEmpSearchScanner = new Scanner(System.in);
                    int cinEmpSearch = cinEmpSearchScanner.nextInt();
                    System.out.println("Tapez le nom d'institution. Exemple FST");
                    Scanner nomInstitutionSearchScanner = new Scanner(System.in);
                    String nomInstitutionSearch = nomInstitutionSearchScanner.next();
                    try {
                        if (System.getSecurityManager() == null)
                        System.setSecurityManager(new RMISecurityManager());
                        //Interroger de RMIregistry
                        Registry reg = LocateRegistry.getRegistry("localhost", 1099);
                        //Trouver une réference vers la Fabrique
                        FabriqueInterface fab = (FabriqueInterface) reg.lookup("Fabrique");
                        //Création d'institution
                        InstitutionInterface institution = (InstitutionInterface) fab.createInstitution();
                        //Création Employe
                        Employe resSearch = institution.search(cinEmpSearch,nomInstitutionSearch);
                        System.out.println("Employé Trouver."
                            +"\n"+"   "+"Nom : "+resSearch.getNom()
                            +"\n"+"   "+"Prenom : "+resSearch.getPrenom()
                            +"\n"+"   "+"CIN : "+resSearch.getCin()
                            +"\n"+"   "+"Télèphone : "+resSearch.getTelephone());                      
                    } catch (Exception e) {
                        System.out.println("Erreur d'acces à l'objet distant.");
                        System.out.println(e.toString());
                    }
                break;
                case 5:
                System.exit(0);
            }
        }  
    }
}
