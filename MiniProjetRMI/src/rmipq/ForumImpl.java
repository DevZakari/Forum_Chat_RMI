package rmipq;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ForumImpl extends UnicastRemoteObject implements Forum {
    private ArrayList<proxy> users;

    public ForumImpl() throws RemoteException {
        users = new ArrayList<proxy>();
    }
    @Override
    public int entrer(proxy pr) throws RemoteException {
        int id = users.size();
        users.add(pr);
        return id;
    }
    @Override
    public void dire(int id, String msg) throws RemoteException {
    	for (proxy pr : users) {
            pr.ecouter("User -> " + id + ": " + msg);
        }
    }
    @Override
    public String qui() throws RemoteException {
        String usersStr = "";
        for (int i = 0; i < users.size(); i++) {
            usersStr += "User " + i + " connected ,";
        }
        return usersStr;
    }

	@Override
	public void quitter(int id) throws RemoteException {
		users.remove(id);
		
	}
}
