import java.rmi.*;

public interface InstitutionInterface extends Remote {
    String add(Employe emp, String nomInstitution) throws RemoteException;

    void delete(int cin, String nomInstitution) throws RemoteException;

    void update(Employe emp, String nomInstitution) throws RemoteException;

    Employe search(int cin, String nomInstitution) throws RemoteException;
}