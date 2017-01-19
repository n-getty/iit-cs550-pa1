package main.java.host;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.Arrays.asList;

public class ServerImpl implements IndexInt {

	Map<String, List<String>> peerMap;

    public ServerImpl() {
    	peerMap = new HashMap<String, List<String>>();
		try {
			IndexInt stub = (IndexInt) UnicastRemoteObject.exportObject(this, 0);

			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("PeerInt", stub);

			System.err.println("ServerImpl ready");
		} catch (Exception e) {
			System.err.println("ServerImpl exception: " + e.toString());
			e.printStackTrace();
		}
	}

	public void register(String peerId, String fileName){
		if(peerMap.containsValue(fileName)){
			peerMap.get(fileName).add(peerId);
		}
		else{
			peerMap.put(fileName, asList(peerId));
		}
	}

	public List<String> lookup(String fileName) {
		return peerMap.get(fileName);
	}
}