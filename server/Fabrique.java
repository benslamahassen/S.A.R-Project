import java.rmi.*;
import java.rmi.server.*;

public class Fabrique extends UnicastRemoteObject implements FabriqueInterface {

    public Fabrique() throws RemoteException {};

    public InstitutionInterface createInstitution() throws RemoteException {
        return new Institution();
    }
}