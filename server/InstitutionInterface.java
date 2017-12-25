import java.rmi.*;

public interface InstitutionInterface extends Remote {
    String add(Employe emp) throws RemoteException;
}