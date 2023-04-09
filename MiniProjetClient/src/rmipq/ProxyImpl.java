package rmipq;
import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;

import Gui.User;

public class ProxyImpl extends UnicastRemoteObject implements proxy {
    private User cli;

    public ProxyImpl(User cli) throws RemoteException {
        this.cli = cli;
    }

    public void ecouter(String msg) throws RemoteException {
        cli.ecrire(msg);
    }
}
