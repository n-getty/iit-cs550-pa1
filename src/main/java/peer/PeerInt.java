package main.java.peer;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.*;
    
/**
 * Interface for peer data transfer methods
 */
public interface PeerInt extends Remote {
    byte[] retrieve(String fileName) throws RemoteException, IOException;
}
