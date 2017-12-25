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

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @return the cin
     */
    public int getCin() {
        return cin;
    }
    /**
     * @return the telephone
     */
    public int getTelephone() {
        return telephone;
    }

    @Override
    public String toString() {
        return "Nom :" + this.nom + "\nPrenom :" + this.prenom + "\nCin :" + this.cin + "\nTélèphone :" + this.telephone;
    }
}