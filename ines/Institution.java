import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.rmi.*;
import java.rmi.server.*;
import java.util.Objects;
class Institution extends UnicastRemoteObject implements InstitutionInterface{
    public Institution() throws RemoteException{
        super();
    }
    public String add(Employe emp) throws RemoteException
    {
        //Traitement des Fichiers

        //Lecture des données
        //Creation un Input Stream
        InputStream in=null;
        try{
             in = new FileInputStream("FST.json");
        }
        catch(FileNotFoundException e){
            System.out.println("Fichiers Inexistants");
            e.printStackTrace();
        }
        //lecture de fichier JSON 
        JsonReader lecteurJson = createReader(in);
        //lecture de l'objet Institution
        JsonObjetct lecteurObjetInstituion  = lectureJson.ReadObject(); 
        //Lecture du tableau d'objet Employes
        JsonArray lecteurTableauEmployes = lecteurObjetInstituion.getJsonArray(employes);
        lecteurJson.close();
        try {
            in.close();
        } 
        catch (IOException e) {
            System.out.pritln("le fichier est ouvert en mode lecture");
            e.printStackTrace();
        }

        //Creation d'un model ArrayObject JSON vide
        JsonArrayBuilder nouveauTableauEmployesBuilder = Json.createArrayBuilder();
        //l'ajout de continu de institutionAray dans employeBuilder
        for(int i=0 ; i< lecteurTableauEmployes.size() ; i++){
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
         JsonObjectBuilder nouveauObjetInstitionBuilder = Json.createObjectBuilder();
         //Ajout des données à l'objet Institution
         nouveauObjetInstitionBuilder.add("institutionName", "FST");
         nouveauObjetInstitionBuilder.add("employes", nouveauTableauEmployes);
         //Préparation de l'objet Institution
         JsonObject nouveauObjetInstituion = nouveauObjetInstitionBuilder.build();
 
         //Ecriture des données
         //Création de Output Stream
         OutputStream out=null;
         try {
            out = new FileOutputStream("FST.json");
         } catch (FileNotFoundException e) {
             System.out.println("Fichiers Inexistants");
             System.out.println(e.toString());
         }
         //Ecriture de l'objet globale Json déja préparé
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
         InputStream in=null;
        
         try{
            in = new FileInputStream("FST.json");
        }
        catch(FileNotFoundException e){
            System.out.println("Fichiers Inexistants");
            e.printStackTrace();
        }
        //lecture de fichier JSON 
        JonReader lecteurJson = createReader(in);
        //lecture de l'objet Institution
        JsonObjetct lecteurObjetInstituion  = reader.ReadObject(); 
        //Lecture du tableau d'objet Employes
        JsonArray lecteurTableauEmployes = lecteurObjetInstituion.getJsonArray(employes);
        lecteurJson.close();
        try 
        {
            in.close();
        } 
        catch (IOException e) {
            System.out.pritln("le fichier est ouvert en mode lecture");
            e.printStackTrace();
        }
       
        JsonArrayBuilder nouveauTableauEmployesBuilder = json.createArrayBuilder();
        //
        for(int i=0 ; i<lecteurTableauEmployes.size(); i++)
        {
            if(!equals(lecteurTableauEmployes.getObject(i).getInt("cin"),cin)){
                 nouveauTableauEmployesBuilder = lecteurTableauEmployes.getJsonObject(i);
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
        JonReader lecteurJson = createReader(in);
        //lecture de l'objet Institution
        JsonObjetct lecteurObjetInstituion  = reader.ReadObject(); 
        //Lecture du tableau d'objet Employes
        JsonArray lecteurTableauEmployes = lecteurObjetInstituion.getJsonArray(employes);
        lecteurJson.close();
        try {
            in.close();
        } 
        catch (IOException e) {
            System.out.pritln("le fichier est ouvert en mode lecture");
            e.printStackTrace();
        }
        //
        JsonArrayBuilder nouveauTableauEmployesBuilder = json.createArrayBuilder();
        for(int i=0 ; i<lecteurTableauEmployes.size(); i++)
        {
            if(lecteurTableauEmployes.getObject(i).getInt("cin") != emp.getCin()){
                 nouveauTableauEmployesBuilder = lecteurTableauEmployes.getJsonObject(i);
            }
            else{
                nouveauTableauEmployesBuilder.add(Json.createObjectBuilder());
                nouveauEmploye.add("cin",emp.getCin());
                nouveauEmploye.add("nom",emp.getNom());
                nouveauEmploye.add("prenom" ,emp.getPrenom());
                nouveauEmploye.add("telephone",emp.getTelephone());
            }
        }
         
        //Préparation de la tableau d'objets Employe
        JsonArray nouveauTableauEmployes = nouveauTableauEmployesBuilder.build();
        //Création de l'objet globale de document Json
        JsonObjectBuilder nouveauObjetInstitionBuilder = Json.createObjectBuilder();
        //Ajout des données à l'objet Institution
        nouveauObjetInstitionBuilder.add("institutionName","FST");
        nouveauObjetInstitionBuilder.add("employes",nouveauTableauEmployes);
        //Préparation de l'objet Institution
        JsonObject nouveauObjetInstition = Json.build();
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
        ecrivainJson.writeObject(nouveauObjetInstituion);
        ecrivainJson.close();   
            
    }
    public Employe search(int cin) throws RemoteException{

        //Creation d'un Input Stream
        InputStream in=null;
        
        try{
            in = new FileInputStream("FST.json");
        }
        catch(FileNotFoundException e){
            System.out.pritln("le fichier est ouvert en mode lecture");
            e.printStackTrace();
        }
        //lecture de fichier JSON 
        JonReader lecteurJson = createReader(in);
        //lecture de l'objet Institution
        JsonObjetct lecteurObjetInstituion  = reader.ReadObject(); 
        //Lecture du tableau d'objet Employes
        JsonArray lecteurTableauEmployes = lecteurObjetInstituion.getJsonArray(employes);
        lecteurJson.close();
        try 
        {
            in.close();
        } 
        catch (IOException e) {
            System.out.pritln("le fichier est ouvert en mode lecture");
            e.printStackTrace();
        }
        for(int i=0 ; i<lecteurTableauEmployes.size(); i++)
        {
            if(equals(lecteurTableauEmployes.getObject(i).getInt("cin"),cin)){
                emp = new Employee(lecteurTableauEmployes.getJsonObject(i).getInt("cin"),lecteurTableauEmployes.getJsonObject(i).getString("nom"),lecteurTableauEmployes.getJsonObject(i).getString("prenom"),lecteurTableauEmployes.getJsonObject(i).getInt("telephone"));

            }
        }
        return emp;
    }
}
 