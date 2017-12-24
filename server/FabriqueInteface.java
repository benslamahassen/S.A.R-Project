import java.rmi.*;

public interface FabriqueInstInterface extends Remote {
    public InstitutionInterface createInstitution() throws RemoteException;
}