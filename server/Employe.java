import java.rmi.*;
import java.io.Serializable;

public class Employe implements Remote, Serializable{

    String nom;
    String prenom;
    int cin;
    int telephone;

    Employe(String nom, String prenom, int cin, int telephone) throws RemoteException{
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.telephone = telephone;
    }
}