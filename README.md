# S.A.R-Project
Java RMI Academic Project.
## Using The Project
Serveur
```
//Aller dans le repertoire server//
  user$ cd server
//Complier les fichiers .java//
  user$ javac -classpath ../lib/.javax.json.jar:./ *.java
//Générer les souches client et serveur Institution_Stub.class et Institution_Skel.class//
  user$ rmic -v1.1 Institution
//Générer les souches//
  user$ rmic -v1.1 Fabrique
//Lancer le serveur d'objets//
  user$ java -classpath ../lib/.javax.json.jar:./ Server
```
Client
```
//Aller dans le répertoire du client//
  user$ cd ../client
//Copier l'interface InstitutionInterface.class dans le répertoire du client//
  user$ cp ../server/InstitutionInterface.class .
//Copier le stub client Institution_Stub.class dans le répertoire du client//
  user$ cp ../server/Institution_Stub.class .
//Copier l’interface FabriqueInterface.class//
  user$ cp ../server/FabriqueInterface.class .
// Copier le stub Fabrique_Stub.class//
  user$ cp ../server/Fabrique_Stub.class .
//Compiler le client Client.java//
  user$ javac -classpath ../lib/.javax.json.jar:./ *.java
//Lancer le client//
  user$ java -classpath ../lib/.javax.json.jar:./ Client
```
