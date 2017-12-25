import java.rmi.*;
import java.rmi.server.*;
import java.util.Objects;
import javax.json.*;
import java.io.*;

public class Institution extends UnicastRemoteObject implements InstitutionInterface{

    public Institution() throws RemoteException{
        super();
    };

    public String add(Employe emp) throws RemoteException{
        //Traitement des Fichiers

        //Lecture des données
        //Création de Input Stream
        try {
            InputStream in = new FileInputStream("FST.json");
        } catch (FileNotFoundException e) {
            System.out.println("Fichiers Inexistants");
            System.out.println(e.toString());
        }
        //Lecture de fichier
        JsonReader lecteurJson = createReader(in);
        //Lecture de l'objet Json globale
        JsonObject lecteurJsonObjectGlobale = lecteurJson.readObject();
        //Lecture du tableau d'objet Employes
        JsonArray lecteurTableauEmployes = lecteurJsonObjectGlobale.getJsonArray(employes);
        lecteurJson.close();

        //Préparation d'écriture des données
        JsonArrayBuilder nouveauTableauEmployesBuilder = Json.createArrayBuilder();
        for (int i = 0; i < lecteurTableauEmployes.size(); i++) {
            nouveauTableauEmployesBuilder.add(lecteurTableauEmployes.getJsonObject(i));
        }
        //Création de nouveau Employe récu du Client
        JsonObject nouveauEmploye = Json.createObjectBuilder();
        nouveauEmploye.add("cin",emp.getCin());
        nouveauEmploye.add("nom",emp.getNom());
        nouveauEmploye.add("prenom" ,emp.getPrenom());
        nouveauEmploye.add("telephone",emp.getTelephone());
        //Ajout de nouveau Employe 
        nouveauTableauEmployesBuilder.add(nouveauEmploye);
        //Préparation de la tableau d'objets Employe
        JsonArray nouveauTableauEmployes = nouveauTableauEmployesBuilder.build();
        //Création de l'objet globale de document Json
        JsonObjectBuilder JsonObjetGlobaleBuilder = Json.createObjectBuilder();
        //Ajout des données à l'objet globale
        JsonObjetGlobaleBuilder.add("institutionName", "FST");
        JsonObjetGlobaleBuilder.add("employes", nouveauTableauEmployes);
        //Préparation de l'objet globale Json
        JsonObject JsonObjetGlobale = JsonObjetGlobaleBuilder.build();

        //Ecriture des données
        //Création de Output Stream
        try {
            OutputStream out = new FileOutputStream("FST.json");
        } catch (FileNotFoundException e) {
            System.out.println("Fichiers Inexistants");
            System.out.println(e.toString());
        }
        //Ecriture de l'objet globale Json déja préparé
        JsonWriter ecrivainJson = Json.createWriter(out);
        ecrivainJson.writeObject(emp01);
        ecrivainJson.close();

        return "\nNom :" + emp.getNom() + 
            "\nPrenom :" + emp.getPrenom() + 
            "\nCin :" + emp.getCin() + 
            "\nTélèphone :" + emp.getTelephone();

    }
}