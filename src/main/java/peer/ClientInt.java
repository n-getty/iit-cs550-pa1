package main.java.host;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientInt extends Remote {
    void retrieve(String fileName, String peerId) throws RemoteException;
}












