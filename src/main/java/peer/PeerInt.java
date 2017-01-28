package main.java.peer;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.*;
    
/**
 * Interface for peer data transfer methods
 */
public interface PeerInt extends Remote {
    String retrieve(String fileName, PeerInt client) throws RemoteException, IOException;
    void sendData(String fileName, byte[] data, int len) throws RemoteException;
}
