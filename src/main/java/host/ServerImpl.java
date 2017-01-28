package main.java.host;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.Arrays.asList;
import java.util.Scanner;


public class ServerImpl implements IndexInt {

    Map<String, List<String>> peerMap;

    public ServerImpl() {
	peerMap = new HashMap<String, List<String>>();

	try {
            IndexInt stub = (IndexInt) UnicastRemoteObject.exportObject(this, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("IndexInt", stub);

            System.err.println("INFO: hostServerImpl ready");
        } catch (Exception e) {
            System.err.println("ALERT: hostServerImpl exception: " + e.toString());
            e.printStackTrace();
        }

    }

    public void register(String peerId, String fileName){
	System.out.println("INFO: registering file: " + fileName + " to peer " + peerId);
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

    public static void main (String[] args) {
	
	System.setProperty( "java.rmi.server.hostname", "10.0.0.1" ) ;
        System.out.println("INFO: initiating server deamon");
        ServerImpl x = new ServerImpl();

	System.out.println("INFO: server initiated.");

        System.out.println("\nINFO: Press return Key to Exit.\n\n");
        Scanner scanner = new Scanner(System.in);
        String readString = scanner.nextLine();

	/*
        System.out.println("INFO: cleaning up...");
        // TODO exit and persistence ???
        System.out.println("INFO: Finished cleaning up\n");
        */

        System.out.println("\nALERT: Process exiting...\n Goodbye.");
        System.exit(0);
    }
}
