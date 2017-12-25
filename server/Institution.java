import java.rmi.*;
import java.rmi.server.*;

public class Institution extends UnicastRemoteObject implements InstitutionInterface{

    public Institution() throws RemoteException{};

    public String add(Employe emp) throws RemoteException{

        // Traiter Les Fichiers
        return emp.toString();
    }
}