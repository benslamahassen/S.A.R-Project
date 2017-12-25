import java.rmi.*;

public interface InstitutionInterface extends Remote {
    String add(Employe emp) throws RemoteException;
    void delete(int cin) throws RemoteException;
    void update(Employe emp) throws RemoteException;
    Employe search(int cin) throws RemoteException;
}