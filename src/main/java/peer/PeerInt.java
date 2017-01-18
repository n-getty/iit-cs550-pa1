package main.java.peer;
import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PeerInt extends Remote {
    File retrieve(String fileName) throws RemoteException;
}
