import java.rmi.*;
import java.rmi.server.*;

public class Institution extends UnicastRemoteObject implements InstitutionInterface{

    public Institution() throws RemoteException{};

    public Employe add(String nom, String prenom , int cin , int telephone) throws RemoteException{

        // Traiter Les Fichiers
        return new Employe(nom, prenom, cin, telephone);
    }
}