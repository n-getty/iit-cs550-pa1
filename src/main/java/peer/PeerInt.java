package main.java.peer;
import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PeerInt extends Remote {
    void retrieve(String fileName, PeerInt clien) throws RemoteException;
    void sendData(String fileName, byte[] data, int len) throws RemoteException;
}
