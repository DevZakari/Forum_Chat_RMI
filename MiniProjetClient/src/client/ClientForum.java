package client;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmipq.Forum;
import Gui.UserImpl;



public class ClientForum {

    public static void main(String[] args) {
        try {
            
            Forum stub = (Forum) Naming.lookup("rmi://localhost:1099/Forum");
            UserImpl user = new UserImpl(stub);

        } catch (Exception e) {
            System.err.println("Erreur Client : " + e.toString());
            e.printStackTrace();
        }
    }
}
