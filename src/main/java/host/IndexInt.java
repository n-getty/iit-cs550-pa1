package main.java.host;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IndexInt extends Remote {
    void register(int peerId, String fileName) throws RemoteException;
    List<Integer> lookup(String fileName) throws RemoteException;
}
