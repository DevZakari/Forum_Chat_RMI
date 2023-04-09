package rmipq;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface proxy extends Remote{
	
	public void ecouter(String msg) throws RemoteException;
}
