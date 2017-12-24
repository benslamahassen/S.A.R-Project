import java.rmi.*;
import java.io.Serializable;

public class Employe implements Remote, Serializable{

    String nom;
    String prenom;
    double cin;
    double telephone;

    Employe(String nom, String prenom, double cin, double telephone) throws RemoteException{
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.telephone = telephone;
    }
}