package main.java.peer;
import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl implements PeerInt {

    public ServerImpl() {
		try {
			PeerInt stub = (PeerInt) UnicastRemoteObject.exportObject(this, 0);
			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("PeerInt", stub);
			System.err.println("ServerImpl ready");
		} catch (Exception e) {
			System.err.println("ServerImpl exception: " + e.toString());
			e.printStackTrace();
		}
	}

    public File retrieve(String fileName) {
		File requestedFile = new File(fileName);
		return requestedFile;
	}

}
