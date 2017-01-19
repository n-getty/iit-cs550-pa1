package main.java.host;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IndexInt extends Remote {
    void register(String peerId, String fileName) throws RemoteException;
    List<String> lookup(String fileName) throws RemoteException;
}
