# S.A.R-Project
Java RMI Academic Project.
## Using The Project
CodeBase
```
//Aller dans le repertoire CodeBase//
    user$ cd CodeBase
//Complier les fichiers .java//
    user$ javac -classpath ../lib/javax.json.jar:./ *.java
//Générer les souches client et serveur Institution_Stub.class et Institution_Skel.class//
    user$ rmic -v1.1 Institution
//Générer les souches//
    user$ rmic -v1.1 Fabrique
//Copier le Dossier CodeBase vers /var/www/html pour étre accessible depuis http avec apache
    user$ cp -r CodeBase/ /var/www/html
```
Serveur
```
//Aller dans le repertoire Server//
    user$ cd Server
//Complier les fichiers .java//
    user$ javac -classpath ../lib/javax.json.jar:./ *.java
//Lancer le serveur dynamique//
    user$ java -Djava.security.policy=server.security.policy -Djava.rmi.server.codebase=http://localhost/CodeBase/  -                   classpath ../lib/javax.json.jar:./ ServeurDynamique
```
Client
```
//Aller dans le répertoire du client//
    user$ cd ../client
//Compiler les fichiers .java//
    user$ javac -classpath ../lib/javax.json.jar:./ *.java
//Lancer le client dynamique//
    user$ java -Djava.security.policy=client.security.policy -Djava.rmi.server.codebase=http://localhost/CodeBase/  -                   classpath ../lib/javax.json.jar:./ ClientDynamique
```
