import java.rmi.*;
import java.io.Serializable;

public class Employe implements Remote, Serializable{

    private String nom;
    private String prenom;
    private int cin;
    private int telephone;

    Employe(String nom, String prenom, int cin, int telephone) throws RemoteException{
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.telephone = telephone;
    }

    public String getNom() throws RemoteException{
        return nom;
    }

    public String getPrenom() throws RemoteException{
        return prenom;
    }

    public int getCin() throws RemoteException{
        return cin;
    }

    public int getTelephone() throws RemoteException{
        return telephone;
    }

    @Override
    public String toString() throws RemoteException{
        return "Nom :" + this.nom + "\nPrenom :" + this.prenom + "\nCin :" + this.cin + "\nTélèphone :" + this.telephone;
    }
}