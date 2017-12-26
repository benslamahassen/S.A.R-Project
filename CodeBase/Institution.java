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
        InputStream in = null;
        try {
            in = new FileInputStream("FST.json");
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
        nouveauObjetInstitionBuilder.add("institutionName", "FST");
        nouveauObjetInstitionBuilder.add("employes", nouveauTableauEmployes);
        //Préparation de l'objet Institution
        JsonObject nouveauObjetInstituion = nouveauObjetInstitionBuilder.build();
        
        //Ecriture des données
        //Création de Output Stream
        OutputStream out = null;
        try {
            out = new FileOutputStream("FST.json");
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
    public void delete(int cin) throws RemoteException{
        
        //Creation un Input Stream
        InputStream in = null;
        
        try{
            in = new FileInputStream("FST.json");
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
        nouveauObjetInstitionBuilder.add("institutionName", "FST");
        nouveauObjetInstitionBuilder.add("employes", nouveauTableauEmployes);
        //Préparation de l'objet Institution
        JsonObject nouveauObjetInstituion = nouveauObjetInstitionBuilder.build();
        
        //Création de Output Stream
        OutputStream out=null;
        try 
        {
            out = new FileOutputStream("FST.json");
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
    public void update(Employe emp) throws RemoteException{
        //Création un Input Stream
        InputStream in=null;
        
        try{
            in = new FileInputStream("FST.json");
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
            }
        }
        nouveauTableauEmployesBuilder.add(Json.createObjectBuilder());
        
        //Préparation de la tableau d'objets Employe
        JsonArray nouveauTableauEmployes = nouveauTableauEmployesBuilder.build();
        //Création de l'objet globale de document Json
        JsonObjectBuilder nouveauObjetInstitionBuilder = Json.createObjectBuilder();
        //Ajout des données à l'objet Institution
        nouveauObjetInstitionBuilder.add("institutionName","FST");
        nouveauObjetInstitionBuilder.add("employes",nouveauTableauEmployes);
        //Préparation de l'objet Institution
        JsonObject nouveauObjetInstition = nouveauObjetInstitionBuilder.build();
        //Creation d'un Output Stream
        OutputStream out=null;
        
        try {
            out = new FileOutputStream("FST.json");
        }
        catch (FileNotFoundException e) {
            System.out.println("Fichiers Inexistants");
            System.out.println(e.toString());
        }
        JsonWriter ecrivainJson = Json.createWriter(out);
        ecrivainJson.writeObject(nouveauObjetInstition);
        ecrivainJson.close();   
        
    }
    public Employe search(int cin) throws RemoteException{
        
        //Creation d'un Input Stream
        InputStream in = null;
        Employe emp = null;
        
        try{
            in = new FileInputStream("FST.json");
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
                emp = new Employe(searchNom, searchPrenom, searchCin, searchTel);        
            }
        }
        return emp;
    }
}