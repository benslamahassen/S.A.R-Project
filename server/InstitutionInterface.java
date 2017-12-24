import java.rmi.*;

public interface InstitutionInterface extends Remote {
    Employe add(String nom, String prenom, int cin, int telephone) throws RemoteException;
}