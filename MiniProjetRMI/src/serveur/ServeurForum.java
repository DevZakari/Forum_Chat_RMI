package serveur;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import rmipq.ForumImpl;

public class ServeurForum {

    public static void main(String args[]) {
        try {
        	
        	System.setProperty("sun.rmi.transport.tcp.responseTimeout", "300000");
        	
            Registry registry = LocateRegistry.createRegistry(1099);

            ForumImpl forum = new ForumImpl();

            Naming.rebind("rmi://localhost:1099/Forum",forum);

            System.out.println("Serveur est pret a l'ecoute ...");
        } catch (Exception e) {
            System.err.println("Erreur Serveur : " + e.toString());
            e.printStackTrace();
        }
    }
}
