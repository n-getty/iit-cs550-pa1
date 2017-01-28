package main.java.peer;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for peer data transfer methods
 */
public interface PeerInt extends Remote {
    void retrieve(String fileName, PeerInt client) throws RemoteException;
    void sendData(String fileName, byte[] data, int len) throws RemoteException;
}
