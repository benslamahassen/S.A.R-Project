import java.rmi.*;

public interface InstitutionInterface extends Remote {
    Employe add(String nom, String prenom, double cin, double telephone) throws RemoteException;
}