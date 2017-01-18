package main.java.host;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import static java.util.Arrays.asList;

public class Server implements IndexInt {

	Map<String, List<Integer>> peerMap;

    public Server() {}

	public void register(int peerId, String fileName){
		if(peerMap.containsValue(fileName)){
			peerMap.get(fileName).add(peerId);
		}
		else{
			peerMap.put(fileName, asList(peerId));
		}
	}

	public List<Integer> lookup(String fileName) {
		return peerMap.get(fileName);
	}

    public static void main(String args[]) {

	try {
	    Server obj = new Server();
		IndexInt stub = (IndexInt) UnicastRemoteObject.exportObject(obj, 0);

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
