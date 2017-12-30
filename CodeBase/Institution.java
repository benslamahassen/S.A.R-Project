import java.rmi.*;
import java.rmi.server.*;
import java.util.Objects;
import javax.json.*;
import java.io.*;

public class Institution extends UnicastRemoteObject implements InstitutionInterface{

    public Institution() throws RemoteException{
        super();
    };
    //Méthode add() prend en paramétre l'objet Employe à ajouter et le nom de l'institution
    public String add(Employe emp, String nomInstitution) throws RemoteException{
        //Traitement des Fichiers

        //Lecture des données
        //Création de Input Stream
        InputStream in = null;
        try {
            in = new FileInputStream(nomInstitution + ".json");
        } catch (FileNotFoundException e) {
            System.out.println("Fichiers Inexistants");
            System.out.println(e.toString());
        }
        //Lecture de fichier
        JsonReader lecteurJson = Json.createReader(in);
        //Lecture de l'objet Institution
        JsonObject lecteurObjetInstituion = lecteurJson.readObject();
        //Lecture du tableau d'objet Employes
        JsonArray lecteurTableauEmployes = lecteurObjetInstituion.getJsonArray("employes");
        lecteurJson.close();

        //Préparation d'écriture des données
        JsonArrayBuilder nouveauTableauEmployesBuilder = Json.createArrayBuilder();
        for (int i = 0; i < lecteurTableauEmployes.size(); i++) {
            nouveauTableauEmployesBuilder.add(lecteurTableauEmployes.getJsonObject(i));
        }
        //Création de nouveau Employe récu du Client
        JsonObjectBuilder nouveauEmploye = Json.createObjectBuilder();
        nouveauEmploye.add("cin",emp.getCin());
        nouveauEmploye.add("nom",emp.getNom());
        nouveauEmploye.add("prenom" ,emp.getPrenom());
        nouveauEmploye.add("telephone",emp.getTelephone());
        //Ajout de nouveau Employe
        nouveauTableauEmployesBuilder.add(nouveauEmploye);
        //Préparation de la tableau d'objets Employe
        JsonArray nouveauTableauEmployes = nouveauTableauEmployesBuilder.build();
        //Création de l'objet globale de document Json
        JsonObjectBuilder nouveauObjetInstitionBuilder = Json.createObjectBuilder();
        //Ajout des données à l'objet Institution
        nouveauObjetInstitionBuilder.add("institutionName", nomInstitution);
        nouveauObjetInstitionBuilder.add("employes", nouveauTableauEmployes);
        //Préparation de l'objet Institution
        JsonObject nouveauObjetInstituion = nouveauObjetInstitionBuilder.build();

        //Ecriture des données
        //Création de Output Stream
        OutputStream out = null;
        try {
            out = new FileOutputStream(nomInstitution + ".json");
        } catch (FileNotFoundException e) {
            System.out.println("Fichiers Inexistants");
            System.out.println(e.toString());
        }
        //Ecriture de l'objet Instituion déja préparé
        JsonWriter ecrivainJson = Json.createWriter(out);
        ecrivainJson.writeObject(nouveauObjetInstituion);
        ecrivainJson.close();

        return "\nNom :" + emp.getNom() +
        "\nPrenom :" + emp.getPrenom() +
        "\nCin :" + emp.getCin() +
        "\nTélèphone :" + emp.getTelephone();
    }
    //Méthode delete() prend en paramétre le cin, comme identifiant de l'employe, et le nom de l'institution
    public void delete(int cin, String nomInstitution) throws RemoteException{

        //Creation un Input Stream
        InputStream in = null;

        try{
            in = new FileInputStream(nomInstitution+".json");
        }
        catch(FileNotFoundException e){
            System.out.println("Fichiers Inexistants");
            e.printStackTrace();
        }
        //lecture de fichier JSON
        JsonReader lecteurJson = Json.createReader(in);
        //lecture de l'objet Institution
        JsonObject lecteurObjetInstituion  = lecteurJson.readObject();
        //Lecture du tableau d'objet Employes
        JsonArray lecteurTableauEmployes = lecteurObjetInstituion.getJsonArray("employes");
        lecteurJson.close();
        try
        {
            in.close();
        }
        catch (IOException e) {
            System.out.println("le fichier est ouvert en mode lecture");
            e.printStackTrace();
        }

        JsonArrayBuilder nouveauTableauEmployesBuilder = Json.createArrayBuilder();
        //
        for(int i=0 ; i<lecteurTableauEmployes.size(); i++)
        {
            if(!Objects.equals(lecteurTableauEmployes.getJsonObject(i).getInt("cin"),cin)){
                nouveauTableauEmployesBuilder.add(lecteurTableauEmployes.getJsonObject(i));
            }
        }
        //Préparation de la tableau d'objets Employe
        JsonArray nouveauTableauEmployes = nouveauTableauEmployesBuilder.build();
        //Création de l'objet globale de document Json
        JsonObjectBuilder nouveauObjetInstitionBuilder = Json.createObjectBuilder();
        //Ajout des données à l'objet Institution
        nouveauObjetInstitionBuilder.add("institutionName", nomInstitution);
        nouveauObjetInstitionBuilder.add("employes", nouveauTableauEmployes);
        //Préparation de l'objet Institution
        JsonObject nouveauObjetInstituion = nouveauObjetInstitionBuilder.build();

        //Création de Output Stream
        OutputStream out=null;
        try
        {
            out = new FileOutputStream(nomInstitution+".json");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Fichiers Inexistants");
            System.out.println(e.toString());
        }
        JsonWriter ecrivainJson = Json.createWriter(out);
        ecrivainJson.writeObject(nouveauObjetInstituion);
        ecrivainJson.close();

    }
    //Méthode update() prend en paramétre l'objet Employe à mettre à jour et le nom de l'institution
    public void update(Employe emp, String nomInstitution) throws RemoteException{
        //Création un Input Stream
        InputStream in=null;

        try{
            in = new FileInputStream(nomInstitution+".json");
        }
        catch(FileNotFoundException e){
            System.out.println("Fichiers Inexistants");
            e.printStackTrace();
        }
        //lecture de fichier JSON
        JsonReader lecteurJson = Json.createReader(in);
        //lecture de l'objet Institution
        JsonObject lecteurObjetInstituion  = lecteurJson.readObject();
        //Lecture du tableau d'objet Employes
        JsonArray lecteurTableauEmployes = lecteurObjetInstituion.getJsonArray("employes");
        lecteurJson.close();
        try {
            in.close();
        }
        catch (IOException e) {
            System.out.println("le fichier est ouvert en mode lecture");
            e.printStackTrace();
        }
        //
        JsonArrayBuilder nouveauTableauEmployesBuilder = Json.createArrayBuilder();
        for(int i=0 ; i<lecteurTableauEmployes.size(); i++)
        {
            if(lecteurTableauEmployes.getJsonObject(i).getInt("cin") != emp.getCin()){
                nouveauTableauEmployesBuilder.add(lecteurTableauEmployes.getJsonObject(i));
            }else{
                //Mise à jour de l'Employe récu du Client
                JsonObjectBuilder nouveauEmploye = Json.createObjectBuilder();
                nouveauEmploye.add("cin",emp.getCin());
                nouveauEmploye.add("nom",emp.getNom());
                nouveauEmploye.add("prenom" ,emp.getPrenom());
                nouveauEmploye.add("telephone",emp.getTelephone());
                //Ajout de nouveau Employe
                nouveauTableauEmployesBuilder.add(nouveauEmploye); 
            }
        }     
        //Préparation de la tableau d'objets Employe
        JsonArray nouveauTableauEmployes = nouveauTableauEmployesBuilder.build();
        //Création de l'objet globale de document Json
        JsonObjectBuilder nouveauObjetInstitionBuilder = Json.createObjectBuilder();
        //Ajout des données à l'objet Institution
        nouveauObjetInstitionBuilder.add("institutionName",nomInstitution);
        nouveauObjetInstitionBuilder.add("employes",nouveauTableauEmployes);
        //Préparation de l'objet Institution
        JsonObject nouveauObjetInstition = nouveauObjetInstitionBuilder.build();
        //Creation d'un Output Stream
        OutputStream out=null;

        try {
            out = new FileOutputStream(nomInstitution+".json");
        }
        catch (FileNotFoundException e) {
            System.out.println("Fichiers Inexistants");
            System.out.println(e.toString());
        }
        JsonWriter ecrivainJson = Json.createWriter(out);
        ecrivainJson.writeObject(nouveauObjetInstition);
        ecrivainJson.close();

    }
    //Méthode search() prend en paramétre le cin, comme identifiant de l'employe, et le nom de l'institution 
    public Employe search(int cin, String nomInstitution) throws RemoteException{

        //Creation d'un Input Stream
        InputStream in = null;
        Employe employe = null;

        try{
            in = new FileInputStream(nomInstitution+".json");
        }
        catch(FileNotFoundException e){
            System.out.println("le fichier est ouvert en mode lecture");
            e.printStackTrace();
        }
        //lecture de fichier JSON
        JsonReader lecteurJson = Json.createReader(in);
        //lecture de l'objet Institution
        JsonObject lecteurObjetInstituion  = lecteurJson.readObject();
        //Lecture du tableau d'objet Employes
        JsonArray lecteurTableauEmployes = lecteurObjetInstituion.getJsonArray("employes");
        lecteurJson.close();
        try
        {
            in.close();
        }
        catch (IOException e) {
            System.out.println("le fichier est ouvert en mode lecture");
            e.printStackTrace();
        }
        for(int i=0 ; i<lecteurTableauEmployes.size(); i++)
        {
            if(lecteurTableauEmployes.getJsonObject(i).getInt("cin") == cin){
                int searchCin = lecteurTableauEmployes.getJsonObject(i).getInt("cin");
                String searchNom = lecteurTableauEmployes.getJsonObject(i).getString("nom");
                String searchPrenom = lecteurTableauEmployes.getJsonObject(i).getString("prenom");
                int searchTel = lecteurTableauEmployes.getJsonObject(i).getInt("telephone");
                employe = new Employe(searchNom, searchPrenom, searchCin, searchTel);
            }
        }
        return employe;
    }
}
