package main.java.peer;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements PeerInt {

    public Server() {}

    public String sayHello(String name) {
	return "PeerInt, world:".concat(name).concat("!");
    }

    public static void main(String args[]) {

	try {
	    Server obj = new Server();
	    PeerInt stub = (PeerInt) UnicastRemoteObject.exportObject(obj, 0);

	    // Bind the remote object's stub in the registry
	    Registry registry = LocateRegistry.getRegistry();
	    registry.bind("PeerInt", stub);

	    System.err.println("Server ready");
	} catch (Exception e) {
	    System.err.println("Server exception: " + e.toString());
	    e.printStackTrace();
	}
    }
}
