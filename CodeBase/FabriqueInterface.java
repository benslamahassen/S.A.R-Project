import java.rmi.*;

public interface FabriqueInterface extends Remote {
    public InstitutionInterface createInstitution() throws RemoteException;
}